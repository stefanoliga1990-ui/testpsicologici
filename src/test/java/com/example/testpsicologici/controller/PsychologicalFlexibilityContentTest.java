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
class PsychologicalFlexibilityContentTest {
    private static final String ID = "flessibilita-psicologica";

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue tests;
    @Autowired private TestResultService results;
    @Autowired private GuideCatalogue guides;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintSourcesAndRelatedContent() {
        PsychologicalTest test = tests.findById(ID);
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "quanto spesso");
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.questions()).allSatisfy(question -> assertThat(question.text()).startsWith("Ho "));
        assertThat(test.areas()).extracting(area -> area.code()).containsExactly("apertura", "prospettiva", "azione");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "apertura", "prospettiva", "azione", "apertura", "prospettiva", "azione",
                "apertura", "prospettiva", "azione", "apertura", "prospettiva", "azione");
        assertThat(test.references()).hasSize(4).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("jcbs.2021.05.007"))
                .anyMatch(url -> url.contains("bs16040510"));
        assertThat(clusters.findByTestId(ID).orElseThrow().slug()).isEqualTo("emozioni-risorse-e-benessere");
        assertThat(clusters.findRelatedTestIds(ID, 3)).containsExactly(
                "resilienza-psicologica", "presenza-mentale", "autocompassione");
        assertThat(guides.findBySlug(ID).orElseThrow().references()).hasSize(4);
    }

    @Test
    void descriptiveProfilesAndSafetyAreStable() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 1, 1);
        TestResult focused = analyze(5, 1, 1);
        TestResult reverseFocused = analyze(1, 5, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title()).doesNotHaveDuplicates();
        assertThat(low.general().title()).contains("poco riferiti");
        assertThat(mixed.general().title()).contains("variabili");
        assertThat(focused.general().title()).contains("Un aspetto");
        assertThat(broad.general().title()).contains("Più aspetti");
        assertThat(focused.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0, 0);
        assertThat(reverseFocused.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 100, 0);
        for (TestResult result : List.of(low, mixed, focused, reverseFocused, broad)) {
            assertThat(result.general().detail()).contains("non validato", "112", "abuso", "editoriali");
            assertThat(result.areaResults()).hasSize(3);
        }
    }

    @Test
    void editorialBoundaryAtTwoAndHalfAndThreeAndHalfIsStable() {
        assertThat(analyzeWithOpening(2, 2, 2, 3).general().title()).contains("poco riferiti");
        assertThat(analyzeWithOpening(2, 2, 3, 3).general().title()).contains("variabili");
        assertThat(analyzeWithOpening(3, 3, 3, 4).general().title()).contains("variabili");
        assertThat(analyzeWithOpening(3, 3, 4, 4).general().title()).contains("Un aspetto");
    }

    @Test
    void testGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/{id}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Flessibilità psicologica:")))
                .andExpect(content().string(containsString("/approfondimenti/" + ID)));
        mvc.perform(get("/approfondimenti/{slug}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Flessibilità psicologica")))
                .andExpect(content().string(containsString("/test/" + ID)));
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + ID, attempt);
        mvc.perform(get("/test/{id}/risultato", ID).session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Spazio all'esperienza interna")));
        MvcResult pdf = mvc.perform(get("/test/{id}/risultato/pdf", ID).session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("flessibilità", "Spazio all'esperienza interna");
        }
        mvc.perform(get("/sitemap.xml")).andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/" + ID)))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + ID)));
    }

    private TestResult analyze(int opening, int perspective, int action) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            int value = switch (test.questions().get(index).areaCode()) {
                case "apertura" -> opening;
                case "prospettiva" -> perspective;
                default -> action;
            };
            attempt.answer(index, value);
        }
        return results.analyze(test, attempt);
    }

    private TestResult analyzeWithOpening(int... answers) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        int position = 0;
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, test.questions().get(index).areaCode().equals("apertura") ? answers[position++] : 1);
        }
        return results.analyze(test, attempt);
    }
}
