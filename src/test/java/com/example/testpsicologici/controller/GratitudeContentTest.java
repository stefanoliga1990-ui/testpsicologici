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
class GratitudeContentTest {
    private static final String ID = "gratitudine";

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue tests;
    @Autowired private TestResultService results;
    @Autowired private GuideCatalogue guides;
    @Autowired private TopicClusterCatalogue clusters;

    @Test
    void blueprintIsBalancedInterleavedAndSourceBounded() {
        PsychologicalTest test = tests.findById(ID);
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "frequenza");
        assertThat(test.questions()).hasSize(10).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.questions()).allSatisfy(question -> assertThat(question.text()).containsIgnoringCase("ho "));
        assertThat(test.areas()).extracting(area -> area.code()).containsExactly("contributi", "esperienza");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "contributi", "esperienza", "contributi", "esperienza", "contributi",
                "esperienza", "contributi", "esperienza", "contributi", "esperienza");
        assertThat(test.references()).hasSize(5).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("CS921617"))
                .anyMatch(url -> url.contains("pb.bd"));
        assertThat(clusters.findByTestId(ID).orElseThrow().slug()).isEqualTo("emozioni-risorse-e-benessere");
        assertThat(clusters.findRelatedTestIds(ID, 3)).containsExactly(
                "soddisfazione-vita", "autocompassione", "speranza-obiettivi");
        assertThat(guides.findBySlug(ID).orElseThrow().references()).hasSize(5);
    }

    @Test
    void profilesDescribeAreasWithoutMoralJudgmentAndKeepSafety() {
        TestResult low = analyze(1, 1);
        TestResult mixed = analyze(3, 1);
        TestResult focusedPeople = analyze(5, 1);
        TestResult focusedExperience = analyze(1, 5);
        TestResult broad = analyze(5, 5);
        assertThat(List.of(low, mixed, focusedPeople, broad))
                .extracting(result -> result.general().title()).doesNotHaveDuplicates();
        assertThat(focusedPeople.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 0);
        assertThat(focusedExperience.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 100);
        for (TestResult result : List.of(low, mixed, focusedPeople, focusedExperience, broad)) {
            assertThat(result.general().title()).containsIgnoringCase("gratitudine");
            assertThat(result.general().detail()).contains("non validato", "112");
            assertThat(result.areaResults()).hasSize(2);
        }
        assertThat(low.general().description()).contains("non indica ingratitudine");
        assertThat(broad.general().description()).contains("non un grado di bontà");
    }

    @Test
    void editorialBoundariesFollowAreaMeans() {
        TestResult belowMedium = analyzeWithPeopleAnswers(2, 2, 2, 3, 3);
        TestResult aboveMedium = analyzeWithPeopleAnswers(2, 2, 3, 3, 3);
        TestResult belowHigh = analyzeWithPeopleAnswers(3, 3, 3, 4, 4);
        TestResult aboveHigh = analyzeWithPeopleAnswers(3, 3, 4, 4, 4);
        assertThat(belowMedium.general().title()).contains("compaiono poco");
        assertThat(aboveMedium.general().title()).contains("variano");
        assertThat(belowHigh.general().title()).contains("variano");
        assertThat(aboveHigh.general().title()).contains("Un'area");
    }

    @Test
    void testGuidePdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/{id}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Gratitudine")))
                .andExpect(content().string(containsString("/approfondimenti/" + ID)));
        mvc.perform(get("/approfondimenti/{slug}", ID)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Gratitudine")))
                .andExpect(content().string(containsString("/test/" + ID)));
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + ID, attempt);
        mvc.perform(get("/test/{id}/risultato", ID).session(session)).andExpect(status().isOk())
                .andExpect(content().string(containsString("Contributi altrui riconosciuti")));
        MvcResult pdf = mvc.perform(get("/test/{id}/risultato/pdf", ID).session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document)).contains("gratitudine", "Contributi altrui");
        }
        mvc.perform(get("/sitemap.xml")).andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/" + ID)))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + ID)));
    }

    private TestResult analyze(int people, int experience) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, test.questions().get(index).areaCode().equals("contributi") ? people : experience);
        }
        return results.analyze(test, attempt);
    }

    private TestResult analyzeWithPeopleAnswers(int... peopleAnswers) {
        PsychologicalTest test = tests.findById(ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        int peopleIndex = 0;
        for (int index = 0; index < test.questions().size(); index++) {
            if (test.questions().get(index).areaCode().equals("contributi")) {
                attempt.answer(index, peopleAnswers[peopleIndex++]);
            } else {
                attempt.answer(index, 1);
            }
        }
        return results.analyze(test, attempt);
    }
}
