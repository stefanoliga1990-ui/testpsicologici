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
class MentalRuminationContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService resultService;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintIsBalancedInterleavedAndLimitedToThePastMonth() {
        PsychologicalTest test = catalogue.findById("ruminazione-mentale");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("ritorno", "valutazione", "persistenza");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "ritorno", "valutazione", "persistenza",
                "ritorno", "valutazione", "persistenza",
                "ritorno", "valutazione", "persistenza",
                "ritorno", "valutazione", "persistenza");
        assertThat(test.responseInstruction()).contains("ultimo mese", "frequenza");
        assertThat(test.introductoryText()).contains("non validato", "soglie sono editoriali", "112");
        assertThat(test.references()).hasSize(7);
        assertThat(clusters.findByTestId("ruminazione-mentale").orElseThrow().slug())
                .isEqualTo("ansia-umore-e-trauma");
        assertThat(clusters.findRelatedTestIds("ruminazione-mentale", 3))
                .containsExactly("umore-depresso", "ansia-generalizzata", "tratti-ossessivo-compulsivi");
    }

    @Test
    void profileBoundariesAndSafetyRemainDescriptive() {
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
        assertThat(analyze(3, 5, 1).general().title()).contains("Un'area");
        assertThat(analyze(5, 5, 1).general().title()).contains("Più aree");
    }

    @Test
    void testGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/ruminazione-mentale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimo mese")))
                .andExpect(content().string(containsString("/approfondimenti/ruminazione-mentale")));
        mvc.perform(get("/approfondimenti/ruminazione-mentale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pensieri ripetitivi sul passato")))
                .andExpect(content().string(containsString("/test/ruminazione-mentale")));

        PsychologicalTest test = catalogue.findById("ruminazione-mentale");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) attempt.answer(i, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-ruminazione-mentale", attempt);
        mvc.perform(get("/test/ruminazione-mentale/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ritorno agli episodi")))
                .andExpect(content().string(containsString("Persistenza e attenzione")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("overall-presence-track"))));
        MvcResult pdf = mvc.perform(get("/test/ruminazione-mentale/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document))
                    .contains("Quanto torno con il pensiero", "Ritorno agli episodi")
                    .doesNotContain("FREQUENZA MEDIA DELLE RISPOSTE RIFERITE");
        }
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/ruminazione-mentale")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/ruminazione-mentale")));
    }

    private TestResult analyze(int ritorno, int valutazione, int persistenza) {
        PsychologicalTest test = catalogue.findById("ruminazione-mentale");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) {
            attempt.answer(i, switch (test.questions().get(i).areaCode()) {
                case "ritorno" -> ritorno;
                case "valutazione" -> valutazione;
                default -> persistenza;
            });
        }
        return resultService.analyze(test, attempt);
    }
}
