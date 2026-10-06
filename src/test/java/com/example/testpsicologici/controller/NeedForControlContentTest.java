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
class NeedForControlContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService resultService;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintIsBalancedInterleavedAndNonDiagnostic() {
        PsychologicalTest test = catalogue.findById("bisogno-controllo");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("anticipo", "gestione", "cambiamenti");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "anticipo", "gestione", "cambiamenti",
                "anticipo", "gestione", "cambiamenti",
                "anticipo", "gestione", "cambiamenti",
                "anticipo", "gestione", "cambiamenti");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "frequenza");
        assertThat(test.introductoryText()).contains("non validato", "soglie sono editoriali", "112");
        assertThat(test.references()).hasSize(4);
        assertThat(clusters.findByTestId("bisogno-controllo").orElseThrow().slug())
                .isEqualTo("ansia-umore-e-trauma");
        assertThat(clusters.findRelatedTestIds("bisogno-controllo", 3))
                .containsExactly("ansia-generalizzata", "tratti-ossessivo-compulsivi", "ruminazione-mentale");
    }

    @Test
    void boundariesDirectionAndSafetyStayDescriptive() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 3, 3);
        TestResult focused = analyze(5, 1, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title())
                .doesNotHaveDuplicates();
        assertThat(focused.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0, 0);
        for (TestResult result : List.of(low, mixed, focused, broad)) {
            assertThat(result.areaResults()).hasSize(3);
            assertThat(result.general().detail()).contains("non validato", "112");
        }
        assertThat(analyze(2, 3, 3).general().title()).contains("varia");
        assertThat(analyze(3, 5, 1).general().title()).contains("Un'area");
        assertThat(analyze(5, 5, 5).general().title()).contains("Più aree");
    }

    @Test
    void testGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/bisogno-controllo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimi tre mesi")))
                .andExpect(content().string(containsString("/approfondimenti/bisogno-controllo")));
        mvc.perform(get("/approfondimenti/bisogno-controllo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Bisogno di controllo")))
                .andExpect(content().string(containsString("/test/bisogno-controllo")));

        PsychologicalTest test = catalogue.findById("bisogno-controllo");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) attempt.answer(i, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-bisogno-controllo", attempt);
        mvc.perform(get("/test/bisogno-controllo/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Anticipo e prevedibilità")))
                .andExpect(content().string(containsString("Adattamento ai cambiamenti")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("overall-presence-track"))));
        MvcResult pdf = mvc.perform(get("/test/bisogno-controllo/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document))
                    .contains("Bisogno di controllo", "Anticipo e prevedibilità")
                    .doesNotContain("FREQUENZA MEDIA DELLE RISPOSTE RIFERITE");
        }
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/bisogno-controllo")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/bisogno-controllo")));
    }

    private TestResult analyze(int anticipo, int gestione, int cambiamenti) {
        PsychologicalTest test = catalogue.findById("bisogno-controllo");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) {
            attempt.answer(i, switch (test.questions().get(i).areaCode()) {
                case "anticipo" -> anticipo;
                case "gestione" -> gestione;
                default -> cambiamenti;
            });
        }
        return resultService.analyze(test, attempt);
    }
}
