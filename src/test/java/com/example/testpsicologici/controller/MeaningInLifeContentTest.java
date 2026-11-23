package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.TestResultService;
import com.example.testpsicologici.service.TopicClusterCatalogue;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class MeaningInLifeContentTest {
    private static final String ID = "significato-vita";

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue tests;
    @Autowired private TestResultService results;
    @Autowired private GuideCatalogue guides;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintSeparatesPresenceAndSearchWithoutAVisibleTotal() {
        PsychologicalTest test = tests.findById(ID);
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("AGREEMENT");
        assertThat(test.responseInstruction()).contains("vita attuale", "quanto è vera per te");
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code()).containsExactly("presenza", "ricerca");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "presenza", "ricerca", "presenza", "ricerca", "presenza", "ricerca",
                "presenza", "ricerca", "presenza", "ricerca", "presenza", "ricerca");
        assertThat(test.references()).hasSize(4).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("0033294118821302"))
                .anyMatch(url -> url.contains("s11482-023-10150-7"));
        assertThat(clusters.findByTestId(ID).orElseThrow().slug()).isEqualTo("emozioni-risorse-e-benessere");
        assertThat(clusters.findRelatedTestIds(ID, 3)).containsExactly(
                "soddisfazione-vita", "speranza-obiettivi", "intelligenza-intrapersonale");
        assertThat(guides.findBySlug(ID).orElseThrow().references()).hasSize(4);
    }

    @Test
    void profilesKeepDimensionsDistinctAndSafetyIndependent() {
        TestResult low = analyze(1, 1);
        TestResult mixed = analyze(3, 1);
        TestResult focusedPresence = analyze(5, 1);
        TestResult focusedSearch = analyze(1, 5);
        TestResult broad = analyze(5, 5);
        assertThat(List.of(low, mixed, focusedPresence, broad))
                .extracting(result -> result.general().title()).doesNotHaveDuplicates();
        assertThat(focusedPresence.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0);
        assertThat(focusedSearch.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 100);
        for (TestResult result : List.of(low, mixed, focusedPresence, focusedSearch, broad)) {
            assertThat(result.general().title()).containsIgnoringCase("significato");
            assertThat(result.general().detail()).contains("non validato", "112", "non si sommano");
            assertThat(result.areaResults()).hasSize(2);
        }
        assertThat(broad.general().description()).contains("non indica un livello superiore di benessere");
        assertThat(focusedSearch.areaResults().get(1).description()).contains("non indicano di per sé disagio");
    }

    @Test
    void editorialMeanBoundariesAreTestedInBothDirections() {
        assertThat(analyzeWithPresence(2, 2, 2, 2, 3, 3).general().title()).contains("poco rappresentate");
        assertThat(analyzeWithPresence(2, 2, 2, 3, 3, 3).general().title()).contains("modo variabile");
        assertThat(analyzeWithPresence(3, 3, 3, 3, 4, 4).general().title()).contains("modo variabile");
        assertThat(analyzeWithPresence(3, 3, 3, 4, 4, 4).general().title()).contains("una sola area");
        assertThat(analyze(1, 5).general().title()).contains("una sola area");
    }

    @Test
    void testGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/{id}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Significato nella vita")))
                .andExpect(content().string(containsString("/approfondimenti/" + ID)));
        mvc.perform(get("/approfondimenti/{slug}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Significato nella vita")))
                .andExpect(content().string(containsString("/test/" + ID)));
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + ID, attempt);
        mvc.perform(get("/test/{id}/risultato", ID).session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Presenza percepita di significato")));
        MvcResult pdf = mvc.perform(get("/test/{id}/risultato/pdf", ID).session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("significato", "Presenza percepita");
        }
        mvc.perform(get("/sitemap.xml")).andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/" + ID)))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + ID)));
    }

    private TestResult analyze(int presence, int search) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, test.questions().get(index).areaCode().equals("presenza") ? presence : search);
        }
        return results.analyze(test, attempt);
    }

    private TestResult analyzeWithPresence(int... answers) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        int position = 0;
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, test.questions().get(index).areaCode().equals("presenza") ? answers[position++] : 1);
        }
        return results.analyze(test, attempt);
    }
}
