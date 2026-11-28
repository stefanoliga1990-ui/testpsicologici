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
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class PerceivedSocialSupportContentTest {
    private static final String ID = "supporto-sociale-percepito";

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
        assertThat(test.answerScale()).isEqualTo("AGREEMENT");
        assertThat(test.responseInstruction()).contains("questo periodo", "quanto è vera");
        assertThat(test.questions()).hasSize(12).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.questions()).allSatisfy(question -> assertThat(question.text())
                .matches("^(Penso|Sento) .*"));
        assertThat(test.areas()).extracting(area -> area.code()).containsExactly("ascolto", "orientamento", "pratico");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "ascolto", "orientamento", "pratico", "ascolto", "orientamento", "pratico",
                "ascolto", "orientamento", "pratico", "ascolto", "orientamento", "pratico");
        assertThat(test.references()).hasSize(5).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("10.1482/38446"))
                .anyMatch(url -> url.contains("ANN_18_04_07"))
                .allMatch(url -> url.startsWith("https://"));
        assertThat(clusters.findByTestId(ID).orElseThrow().slug()).isEqualTo("relazioni-e-attaccamento");
        assertThat(clusters.findRelatedTestIds(ID, 3)).containsExactly(
                "stili-attaccamento", "disponibilita-emotiva", "paura-abbandono");
        assertThat(guides.findBySlug(ID).orElseThrow().references()).hasSize(5);
    }

    @Test
    void profilesKeepAreasOrderedAndSafetyIndependentOfAnswers() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 1, 1);
        TestResult focused = analyze(5, 1, 1);
        TestResult reverseFocused = analyze(1, 5, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title()).doesNotHaveDuplicates();
        assertThat(low.general().title()).contains("poco accessibile");
        assertThat(mixed.general().title()).contains("variabile");
        assertThat(focused.general().title()).contains("Una forma");
        assertThat(broad.general().title()).contains("Più forme");
        assertThat(focused.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0, 0);
        assertThat(reverseFocused.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 100, 0);
        for (TestResult result : List.of(low, mixed, focused, reverseFocused, broad)) {
            assertThat(result.general().detail()).contains("non validato", "112", "editoriali", "non coincide");
            assertThat(result.areaResults()).hasSize(3);
        }
    }

    @Test
    void editorialThresholdBoundariesAreStable() {
        assertThat(analyzeWithListening(2, 2, 2, 3).general().title()).contains("poco accessibile");
        assertThat(analyzeWithListening(2, 2, 3, 3).general().title()).contains("variabile");
        assertThat(analyzeWithListening(3, 3, 3, 4).general().title()).contains("variabile");
        assertThat(analyzeWithListening(3, 3, 4, 4).general().title()).contains("Una forma");
    }

    @Test
    void testGuideResultPdfAndSitemapRenderWithoutPartnerAssumption() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/{id}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Supporto sociale percepito:")))
                .andExpect(content().string(containsString("Le tre aree descrivono")))
                .andExpect(content().string(not(containsString("verdetto sulla coppia"))))
                .andExpect(content().string(containsString("/approfondimenti/" + ID)));
        mvc.perform(get("/approfondimenti/{slug}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Supporto sociale percepito")))
                .andExpect(content().string(containsString("/test/" + ID)));
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + ID, attempt);
        mvc.perform(get("/test/{id}/risultato", ID).session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Ascolto e vicinanza")));
        MvcResult pdf = mvc.perform(get("/test/{id}/risultato/pdf", ID).session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("Supporto sociale", "Ascolto e vicinanza")
                    .doesNotContain("quanto spesso", "media delle risposte date alle 12 domande");
        }
        mvc.perform(get("/sitemap.xml")).andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/" + ID)))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + ID)));
    }

    private TestResult analyze(int listening, int orientation, int practical) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            int value = switch (test.questions().get(index).areaCode()) {
                case "ascolto" -> listening;
                case "orientamento" -> orientation;
                default -> practical;
            };
            attempt.answer(index, value);
        }
        return results.analyze(test, attempt);
    }

    private TestResult analyzeWithListening(int... answers) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        int position = 0;
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, test.questions().get(index).areaCode().equals("ascolto") ? answers[position++] : 1);
        }
        return results.analyze(test, attempt);
    }
}
