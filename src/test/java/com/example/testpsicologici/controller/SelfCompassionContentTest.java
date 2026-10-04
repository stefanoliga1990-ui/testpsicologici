package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
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
class SelfCompassionContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService resultService;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintIsOriginalBalancedPositiveAndLimited() {
        PsychologicalTest test = catalogue.findById("autocompassione");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("gentilezza", "umanita", "equilibrio");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "gentilezza", "umanita", "equilibrio",
                "gentilezza", "umanita", "equilibrio",
                "gentilezza", "umanita", "equilibrio",
                "gentilezza", "umanita", "equilibrio");
        assertThat(test.responseInstruction()).contains("ultimi sei mesi", "frequenza");
        assertThat(test.introductoryText()).contains("non validato", "soglie sono editoriali", "112");
        assertThat(test.references()).hasSize(5);
        assertThat(clusters.findByTestId("autocompassione").orElseThrow().slug())
                .isEqualTo("emozioni-risorse-e-benessere");
        assertThat(clusters.findRelatedTestIds("autocompassione", 3))
                .containsExactly("resilienza-psicologica", "intelligenza-intrapersonale", "empatia-percepita");
    }

    @Test
    void scoresAreDescriptiveAndSafetyIsIndependentOfProfile() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 3, 3);
        TestResult focused = analyze(5, 1, 1);
        TestResult broad = analyze(5, 5, 5);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title())
                .doesNotHaveDuplicates();
        assertThat(focused.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0, 0);
        for (TestResult result : List.of(low, mixed, focused, broad)) {
            assertThat(result.areaResults()).hasSize(3);
            assertThat(result.general().detail()).contains("non validato", "112");
        }
        assertThat(analyze(2, 3, 3).general().title()).contains("variano");
    }

    @Test
    void testGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/autocompassione"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimi sei mesi")))
                .andExpect(content().string(containsString("/approfondimenti/autocompassione")));
        mvc.perform(get("/approfondimenti/autocompassione"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Una risposta alla difficoltà")))
                .andExpect(content().string(containsString("/test/autocompassione")));

        PsychologicalTest test = catalogue.findById("autocompassione");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) attempt.answer(i, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-autocompassione", attempt);
        mvc.perform(get("/test/autocompassione/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Gentilezza verso di sé")))
                .andExpect(content().string(containsString("Umanità condivisa")));
        MvcResult pdf = mvc.perform(get("/test/autocompassione/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("autocompassione", "Gentilezza");
        }
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/autocompassione")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/autocompassione")));
    }

    private TestResult analyze(int gentilezza, int umanita, int equilibrio) {
        PsychologicalTest test = catalogue.findById("autocompassione");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) {
            attempt.answer(i, switch (test.questions().get(i).areaCode()) {
                case "gentilezza" -> gentilezza;
                case "umanita" -> umanita;
                default -> equilibrio;
            });
        }
        return resultService.analyze(test, attempt);
    }
}
