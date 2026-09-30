package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestQuestion;
import com.example.testpsicologici.service.AceExposureAnalyzer;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.PdfResultService;
import com.example.testpsicologici.service.RecommendedReadingCatalogue;
import com.example.testpsicologici.service.SiteUrlService;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.TestCompletionAnalyticsService;
import com.example.testpsicologici.service.TestResultService;
import com.example.testpsicologici.service.TopicClusterCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AceQuestionFlowTest {

    private final TestCatalogue catalogue = mock(TestCatalogue.class);
    private final TestCompletionAnalyticsService analytics = mock(TestCompletionAnalyticsService.class);
    private final TestController controller = new TestController(catalogue, mock(TestResultService.class),
            mock(PdfResultService.class), mock(SiteUrlService.class), mock(GuideCatalogue.class),
            mock(RecommendedReadingCatalogue.class), analytics, mock(TopicClusterCatalogue.class), false);

    @Test
    void anOmittedAceAnswerIsStoredAsSkippedAndCanCompleteTheAttempt() {
        PsychologicalTest test = test(AceExposureAnalyzer.SCORING_MODEL, AceExposureAnalyzer.ANSWER_SCALE);
        when(catalogue.findById(test.id())).thenReturn(test);
        MockHttpSession session = new MockHttpSession();
        TestAttempt attempt = new TestAttempt(1);
        session.setAttribute("test-attempt-" + test.id(), attempt);

        String redirect = controller.saveAnswer(test.id(), 1, null, session);

        assertThat(redirect).isEqualTo("redirect:/test/esperienze-avverse-infanzia/risultato");
        assertThat(attempt.answerAt(0)).isEqualTo(AceExposureAnalyzer.SKIPPED);
        assertThat(attempt.isComplete()).isTrue();
        verify(analytics).recordCompletion(test.id());
    }

    @Test
    void missingAnswerForAnExistingScaleStillCannotAdvance() {
        PsychologicalTest test = test("AREA_PROFILE", "FREQUENCY");
        when(catalogue.findById(test.id())).thenReturn(test);
        MockHttpSession session = new MockHttpSession();
        TestAttempt attempt = new TestAttempt(1);
        session.setAttribute("test-attempt-" + test.id(), attempt);

        String redirect = controller.saveAnswer(test.id(), 1, null, session);

        assertThat(redirect).isEqualTo("redirect:/test/esperienze-avverse-infanzia/domanda/1");
        assertThat(attempt.answerAt(0)).isZero();
    }

    @Test
    void aceQuestionIsNotCachedAndPdfIsUnavailable() {
        PsychologicalTest test = test(AceExposureAnalyzer.SCORING_MODEL, AceExposureAnalyzer.ANSWER_SCALE);
        when(catalogue.findById(test.id())).thenReturn(test);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + test.id(), new TestAttempt(1));
        MockHttpServletResponse response = new MockHttpServletResponse();
        ExtendedModelMap model = new ExtendedModelMap();

        controller.question(test.id(), 1, session, response, model);

        assertThat(response.getHeader("Cache-Control")).isEqualTo("no-store");
        assertThat(model.get("answers")).isEqualTo(
                List.of("Sì", "No", "Non ricordo", "Preferisco non rispondere"));
        assertThatThrownBy(() -> controller.downloadResultPdf(test.id(), session))
                .isInstanceOf(ResponseStatusException.class)
                .hasMessageContaining("404");
    }

    private PsychologicalTest test(String scoringModel, String answerScale) {
        return new PsychologicalTest("esperienze-avverse-infanzia", "ACE", "ACE", "", "", "", "", "", "",
                "1.0", false, "", "", scoringModel, answerScale, List.of(),
                List.of(new TestQuestion("Una domanda", null, "A", "A01")), List.of());
    }
}
