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
class SavoringContentTest {
    private static final String ID = "assaporare-momenti-positivi";

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue tests;
    @Autowired private TestResultService results;
    @Autowired private GuideCatalogue guides;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintHasThreeInterleavedAreasAndSpecificSources() {
        PsychologicalTest test = tests.findById(ID);
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "frequenza");
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code()).containsExactly("anticipazione", "momento", "ricordo");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "anticipazione", "momento", "ricordo", "anticipazione", "momento", "ricordo",
                "anticipazione", "momento", "ricordo", "anticipazione", "momento", "ricordo");
        assertThat(test.references()).hasSize(4).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("s40359-023-01225-z"))
                .anyMatch(url -> url.contains("fpsyg.2018.00181"));
        assertThat(clusters.findByTestId(ID).orElseThrow().slug()).isEqualTo("emozioni-risorse-e-benessere");
        assertThat(clusters.findRelatedTestIds(ID, 3)).containsExactly("gratitudine", "soddisfazione-vita", "autocompassione");
        assertThat(guides.findBySlug(ID).orElseThrow().references()).hasSize(4);
    }

    @Test
    void profilesAndBoundariesAreDescriptiveAndKeepSafetyIndependent() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 1, 1);
        TestResult focused = analyze(5, 1, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad))
                .extracting(result -> result.general().title()).doesNotHaveDuplicates();
        assertThat(focused.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0, 0);
        assertThat(broad.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 100, 0);
        for (TestResult result : List.of(low, mixed, focused, broad)) {
            assertThat(result.general().title()).containsIgnoringCase("savoring");
            assertThat(result.general().detail()).contains("non validato", "112");
            assertThat(result.areaResults()).hasSize(3);
        }
        assertThat(analyzeWithAnticipation(2, 2, 2, 3).general().title()).contains("poco frequenti");
        assertThat(analyzeWithAnticipation(2, 2, 3, 3).general().title()).contains("variabili");
        assertThat(analyzeWithAnticipation(3, 3, 3, 4).general().title()).contains("variabili");
        assertThat(analyzeWithAnticipation(3, 3, 4, 4).general().title()).contains("Una forma");
    }

    @Test
    void testGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/{id}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Assaporare")))
                .andExpect(content().string(containsString("/approfondimenti/" + ID)));
        mvc.perform(get("/approfondimenti/{slug}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Savoring")))
                .andExpect(content().string(containsString("/test/" + ID)));
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + ID, attempt);
        mvc.perform(get("/test/{id}/risultato", ID).session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Anticipazione di esperienze piacevoli")));
        MvcResult pdf = mvc.perform(get("/test/{id}/risultato/pdf", ID).session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("savoring", "Anticipazione");
        }
        mvc.perform(get("/sitemap.xml")).andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/" + ID)))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + ID)));
    }

    private TestResult analyze(int anticipation, int moment, int memory) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            int value = switch (test.questions().get(index).areaCode()) {
                case "anticipazione" -> anticipation;
                case "momento" -> moment;
                default -> memory;
            };
            attempt.answer(index, value);
        }
        return results.analyze(test, attempt);
    }

    private TestResult analyzeWithAnticipation(int... answers) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        int position = 0;
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, test.questions().get(index).areaCode().equals("anticipazione") ? answers[position++] : 1);
        }
        return results.analyze(test, attempt);
    }
}
