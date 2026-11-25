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
class FlowExperiencesContentTest {
    private static final String ID = "esperienze-flow";

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
        assertThat(test.areas()).extracting(area -> area.code()).containsExactly("assorbimento", "fluidita", "gratificazione");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "assorbimento", "fluidita", "gratificazione", "assorbimento", "fluidita", "gratificazione",
                "assorbimento", "fluidita", "gratificazione", "assorbimento", "fluidita", "gratificazione");
        assertThat(test.references()).hasSize(4).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("0182201"))
                .anyMatch(url -> url.contains("0335907"));
        assertThat(clusters.findByTestId(ID).orElseThrow().slug()).isEqualTo("emozioni-risorse-e-benessere");
        assertThat(clusters.findRelatedTestIds(ID, 3)).containsExactly(
                "assaporare-momenti-positivi", "curiosita-esplorazione", "significato-vita");
        assertThat(guides.findBySlug(ID).orElseThrow().references()).hasSize(4);
    }

    @Test
    void editorialProfilesAreaDirectionAndSafety() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 1, 1);
        TestResult focused = analyze(5, 1, 1);
        TestResult reverseFocused = analyze(1, 5, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title())
                .doesNotHaveDuplicates();
        assertThat(low.general().title()).contains("poco riferiti");
        assertThat(mixed.general().title()).contains("variabili");
        assertThat(focused.general().title()).contains("Un aspetto");
        assertThat(broad.general().title()).contains("Più aspetti");
        assertThat(focused.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0, 0);
        assertThat(reverseFocused.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 100, 0);
        for (TestResult result : List.of(low, mixed, focused, reverseFocused, broad)) {
            assertThat(result.general().detail()).contains("non validato", "112", "editoriali");
            assertThat(result.areaResults()).hasSize(3);
        }
    }

    @Test
    void testGuideResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/{id}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Flow:")))
                .andExpect(content().string(containsString("/approfondimenti/" + ID)));
        mvc.perform(get("/approfondimenti/{slug}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("flow")))
                .andExpect(content().string(containsString("/test/" + ID)));
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + ID, attempt);
        mvc.perform(get("/test/{id}/risultato", ID).session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Assorbimento nell'attività")));
        MvcResult pdf = mvc.perform(get("/test/{id}/risultato/pdf", ID).session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("flow", "Assorbimento");
        }
        mvc.perform(get("/sitemap.xml")).andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/" + ID)))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + ID)));
    }

    private TestResult analyze(int absorption, int fluency, int reward) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            int value = switch (test.questions().get(index).areaCode()) {
                case "assorbimento" -> absorption;
                case "fluidita" -> fluency;
                default -> reward;
            };
            attempt.answer(index, value);
        }
        return results.analyze(test, attempt);
    }
}
