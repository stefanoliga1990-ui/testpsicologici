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
class OptimismContentTest {
    private static final String ID = "ottimismo";

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue tests;
    @Autowired private TestResultService results;
    @Autowired private GuideCatalogue guides;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintKeepsDirectionsSeparateAndSourcesTraceable() {
        PsychologicalTest test = tests.findById(ID);
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("AGREEMENT");
        assertThat(test.questions()).hasSize(10).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code()).containsExactly("favorevoli", "sfavorevoli");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "favorevoli", "sfavorevoli", "favorevoli", "sfavorevoli", "favorevoli",
                "sfavorevoli", "favorevoli", "sfavorevoli", "favorevoli", "sfavorevoli");
        assertThat(test.references()).hasSize(4).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("CS1722402"))
                .anyMatch(url -> url.contains("25078402"));
        assertThat(clusters.findByTestId(ID).orElseThrow().slug()).isEqualTo("emozioni-risorse-e-benessere");
        assertThat(clusters.findRelatedTestIds(ID, 3)).containsExactly(
                "speranza-obiettivi", "soddisfazione-vita", "resilienza-psicologica");
        assertThat(guides.findBySlug(ID).orElseThrow().references()).hasSize(4);
    }

    @Test
    void allProfileBoundariesKeepSafetyAndInterpretationSeparate() {
        TestResult low = analyze(1, 1);
        TestResult mixed = analyze(3, 1);
        TestResult favorable = analyze(5, 1);
        TestResult unfavorable = analyze(1, 5);
        TestResult broad = analyze(5, 5);
        assertThat(List.of(low, mixed, favorable, broad)).extracting(result -> result.general().title())
                .doesNotHaveDuplicates();
        assertThat(favorable.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0);
        assertThat(unfavorable.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 100);
        assertThat(favorable.general().title()).isEqualTo(unfavorable.general().title());
        assertThat(broad.general().title()).contains("coesistono");
        for (TestResult result : List.of(low, mixed, favorable, unfavorable, broad)) {
            assertThat(result.areaResults()).hasSize(2);
            assertThat(result.general().detail()).contains("non validato", "112");
            assertThat(result.general().title()).containsIgnoringCase("aspettative");
        }
    }

    @Test
    void testGuidePdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/{id}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Ottimismo")))
                .andExpect(content().string(containsString("/approfondimenti/" + ID)));
        mvc.perform(get("/approfondimenti/{slug}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("aspettative favorevoli e sfavorevoli")))
                .andExpect(content().string(containsString("/test/" + ID)));
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + ID, attempt);
        mvc.perform(get("/test/{id}/risultato", ID).session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Aspettative favorevoli")));
        MvcResult pdf = mvc.perform(get("/test/{id}/risultato/pdf", ID).session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("aspettative", "Aspettative favorevoli");
        }
        mvc.perform(get("/sitemap.xml")).andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/" + ID)))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + ID)));
    }

    private TestResult analyze(int favorable, int unfavorable) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, test.questions().get(index).areaCode().equals("favorevoli") ? favorable : unfavorable);
        }
        return results.analyze(test, attempt);
    }
}
