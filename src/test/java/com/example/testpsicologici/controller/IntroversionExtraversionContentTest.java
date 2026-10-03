package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.TestResultService;
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
class IntroversionExtraversionContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService resultService;

    @Test
    void questionnaireIsBalancedInterleavedAndClearlyUnvalidated() {
        PsychologicalTest test = catalogue.findById("introversione-estroversione");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("PREVALENT_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("socialita", "espressione", "vitalita");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "socialita", "espressione", "vitalita",
                "socialita", "espressione", "vitalita",
                "socialita", "espressione", "vitalita",
                "socialita", "espressione", "vitalita");
        assertThat(test.responseInstruction()).contains("ultimi sei mesi", "frequenza");
        assertThat(test.references()).hasSize(5);
        assertThat(test.introductoryText()).contains("non validato", "soglie sono editoriali", "112");
    }

    @Test
    void profilesUseAllThreeAreasAndKeepSafetySeparate() {
        TestResult introvert = analyze(1, 1, 1);
        TestResult intermediate = analyze(3, 3, 3);
        TestResult faceted = analyze(5, 1, 1);
        TestResult extravert = analyze(5, 5, 5);
        assertThat(List.of(introvert, intermediate, faceted, extravert))
                .extracting(result -> result.general().title()).doesNotHaveDuplicates();
        assertThat(faceted.areaResults()).extracting(area -> area.percentage())
                .containsExactly(100, 0, 0);
        for (TestResult result : List.of(introvert, intermediate, faceted, extravert)) {
            assertThat(result.percentage()).isZero();
            assertThat(result.areaResults()).hasSize(3);
            assertThat(result.general().detail()).contains("non validato", "112");
        }
        assertThat(analyze(2, 3, 4).general().title()).contains("diverse fra aree");
    }

    @Test
    void questionnaireGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/introversione-estroversione"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimi sei mesi")))
                .andExpect(content().string(containsString("/approfondimenti/introversione-estroversione")));
        mvc.perform(get("/approfondimenti/introversione-estroversione"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Un continuum di personalità")))
                .andExpect(content().string(containsString("/test/introversione-estroversione")));

        PsychologicalTest test = catalogue.findById("introversione-estroversione");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) attempt.answer(i, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-introversione-estroversione", attempt);
        mvc.perform(get("/test/introversione-estroversione/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Socialità")))
                .andExpect(content().string(containsString("Ritmo e vitalità")));
        MvcResult pdf = mvc.perform(get("/test/introversione-estroversione/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("introversione", "Socialità");
        }
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/introversione-estroversione")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/introversione-estroversione")));
    }

    private TestResult analyze(int socialita, int espressione, int vitalita) {
        PsychologicalTest test = catalogue.findById("introversione-estroversione");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) {
            attempt.answer(i, switch (test.questions().get(i).areaCode()) {
                case "socialita" -> socialita;
                case "espressione" -> espressione;
                default -> vitalita;
            });
        }
        return resultService.analyze(test, attempt);
    }
}
