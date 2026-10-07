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
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class SocialChameleonContentTest {
    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService results;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void structureAndSourcesAreBalanced() {
        PsychologicalTest test = catalogue.findById("camaleonte-sociale");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.questions()).hasSize(12).extracting(q -> q.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(a -> a.code()).containsExactly("segnali", "espressione", "posizioni");
        assertThat(test.questions()).extracting(q -> q.areaCode()).containsExactly(
                "segnali", "espressione", "posizioni", "segnali", "espressione", "posizioni",
                "segnali", "espressione", "posizioni", "segnali", "espressione", "posizioni");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "frequenza");
        assertThat(test.introductoryText()).contains("non validato", "editoriali", "112");
        assertThat(test.references()).hasSize(4);
        assertThat(test.references().get(0).url()).contains("10.1482/26764");
        assertThat(clusters.findByTestId("camaleonte-sociale").orElseThrow().slug())
                .isEqualTo("autostima-approvazione-e-obiettivi");
        assertThat(clusters.findRelatedTestIds("camaleonte-sociale", 3))
                .containsExactly("people-pleasing", "autostima", "sindrome-impostore");
    }

    @Test
    void profilesStayDescriptiveAtBoundaries() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 3, 3);
        TestResult focused = analyze(5, 1, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(r -> r.general().title())
                .doesNotHaveDuplicates();
        assertThat(focused.areaResults()).extracting(a -> a.percentage()).containsExactly(100, 0, 0);
        for (TestResult result : List.of(low, mixed, focused, broad)) {
            assertThat(result.areaResults()).hasSize(3);
            assertThat(result.general().detail()).contains("non validato", "112");
        }
        assertThat(analyze(2, 3, 3).general().title()).contains("varia");
        assertThat(analyze(3, 5, 1).general().title()).contains("Un'area");
        assertThat(analyze(5, 5, 5).general().title()).contains("Più aree");
    }

    @Test
    void htmlPdfAndSitemapRenderWithoutGlobalBar() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/camaleonte-sociale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimi tre mesi")))
                .andExpect(content().string(containsString("/approfondimenti/camaleonte-sociale")));
        mvc.perform(get("/approfondimenti/camaleonte-sociale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Camaleonte sociale")))
                .andExpect(content().string(containsString("/test/camaleonte-sociale")));
        PsychologicalTest test = catalogue.findById("camaleonte-sociale");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) attempt.answer(i, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-camaleonte-sociale", attempt);
        mvc.perform(get("/test/camaleonte-sociale/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Attenzione ai segnali sociali")))
                .andExpect(content().string(not(containsString("overall-presence-track"))));
        MvcResult pdf = mvc.perform(get("/test/camaleonte-sociale/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document))
                    .contains("Camaleonte sociale", "Attenzione ai segnali sociali")
                    .doesNotContain("FREQUENZA MEDIA DELLE RISPOSTE RIFERITE");
        }
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/camaleonte-sociale")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/camaleonte-sociale")));
    }

    private TestResult analyze(int segnali, int espressione, int posizioni) {
        PsychologicalTest test = catalogue.findById("camaleonte-sociale");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) {
            attempt.answer(i, switch (test.questions().get(i).areaCode()) {
                case "segnali" -> segnali;
                case "espressione" -> espressione;
                default -> posizioni;
            });
        }
        return results.analyze(test, attempt);
    }
}
