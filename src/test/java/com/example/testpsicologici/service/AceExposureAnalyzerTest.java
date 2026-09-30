package com.example.testpsicologici.service;

import com.example.testpsicologici.model.AceExposureResult;
import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.ResultContent;
import com.example.testpsicologici.model.TestArea;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestQuestion;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AceExposureAnalyzerTest {

    private final AceExposureAnalyzer analyzer = new AceExposureAnalyzer();

    @Test
    void allNoMeansNoReportedExperiencesInExploredCategories() {
        AceExposureResult result = analyzer.analyze(test(), attemptFilledWith(AceExposureAnalyzer.NO));

        assertThat(result.profileCode()).isEqualTo("NONE_REPORTED");
        assertThat(result.partial()).isFalse();
        assertThat(result.groups()).extracting(group -> group.status())
                .containsExactly("NOT_REPORTED", "NOT_REPORTED", "NOT_REPORTED", "NOT_REPORTED");
    }

    @Test
    void aSingleYesIsNotDilutedByOtherNoAnswers() {
        PsychologicalTest test = test();
        TestAttempt attempt = attemptFilledWith(AceExposureAnalyzer.NO);
        attempt.answer(indexOf(test, "A05"), AceExposureAnalyzer.YES);

        AceExposureResult result = analyzer.analyze(test, attempt);

        assertThat(result.profileCode()).isEqualTo("ONE_GROUP");
        assertThat(result.groups()).extracting(group -> group.status())
                .containsExactly("PRESENT", "NOT_REPORTED", "NOT_REPORTED", "NOT_REPORTED");
    }

    @Test
    void multipleGroupsRemainDescriptiveAndInTheoreticalOrder() {
        PsychologicalTest test = test();
        TestAttempt attempt = attemptFilledWith(AceExposureAnalyzer.NO);
        attempt.answer(indexOf(test, "D06"), AceExposureAnalyzer.YES);
        attempt.answer(indexOf(test, "B01"), AceExposureAnalyzer.YES);

        AceExposureResult result = analyzer.analyze(test, attempt);

        assertThat(result.profileCode()).isEqualTo("MULTIPLE_GROUPS");
        assertThat(result.groups()).extracting(group -> group.code()).containsExactly("A", "B", "C", "D");
        assertThat(result.groups()).extracting(group -> group.status())
                .containsExactly("NOT_REPORTED", "PRESENT", "NOT_REPORTED", "PRESENT");
    }

    @Test
    void unknownAndSkippedNeverBecomeNo() {
        PsychologicalTest test = test();
        TestAttempt attempt = attemptFilledWith(AceExposureAnalyzer.NO);
        attempt.answer(indexOf(test, "A01"), AceExposureAnalyzer.UNKNOWN);
        attempt.answer(indexOf(test, "C03"), AceExposureAnalyzer.SKIPPED);

        AceExposureResult result = analyzer.analyze(test, attempt);

        assertThat(result.profileCode()).isEqualTo("UNDETERMINED");
        assertThat(result.partial()).isTrue();
        assertThat(result.groups()).extracting(group -> group.status())
                .containsExactly("UNDETERMINED", "NOT_REPORTED", "UNDETERMINED", "NOT_REPORTED");
    }

    @Test
    void allSkippedIsUndetermined() {
        AceExposureResult result = analyzer.analyze(test(), attemptFilledWith(AceExposureAnalyzer.SKIPPED));

        assertThat(result.profileCode()).isEqualTo("UNDETERMINED");
        assertThat(result.groups()).allSatisfy(group -> assertThat(group.status()).isEqualTo("UNDETERMINED"));
    }

    @Test
    void yesWithMissingAnswersKeepsTheExperienceAndMarksResultPartial() {
        PsychologicalTest test = test();
        TestAttempt attempt = attemptFilledWith(AceExposureAnalyzer.NO);
        attempt.answer(indexOf(test, "A05"), AceExposureAnalyzer.YES);
        attempt.answer(indexOf(test, "A06"), AceExposureAnalyzer.SKIPPED);

        AceExposureResult result = analyzer.analyze(test, attempt);

        assertThat(result.profileCode()).isEqualTo("ONE_GROUP");
        assertThat(result.partial()).isTrue();
        assertThat(result.groups().get(0).status()).isEqualTo("PRESENT");
    }

    @Test
    void invalidAnswersAndIndicatorMapsAreRejected() {
        PsychologicalTest test = test();
        TestAttempt invalidAnswer = attemptFilledWith(AceExposureAnalyzer.NO);
        invalidAnswer.answer(0, 5);
        assertThatThrownBy(() -> analyzer.analyze(test, invalidAnswer))
                .isInstanceOf(IllegalArgumentException.class);

        List<TestQuestion> questions = new ArrayList<>(test.questions());
        questions.set(0, new TestQuestion("Indicatore", null, "A", "A02"));
        PsychologicalTest duplicated = withQuestions(test, questions);
        assertThatThrownBy(() -> analyzer.analyze(duplicated, attemptFilledWith(AceExposureAnalyzer.NO)))
                .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void resultServiceUsesDedicatedProfileWithoutAreaAverages() {
        PsychologicalTest test = test();
        TestCatalogue catalogue = mock(TestCatalogue.class);
        ResultContent interpretation = new ResultContent("Esperienze riferite", "Sintesi.", "Dettaglio.");
        when(catalogue.findGlobalInterpretation(test.id(), "ONE_GROUP")).thenReturn(interpretation);
        TestAttempt attempt = attemptFilledWith(AceExposureAnalyzer.NO);
        attempt.answer(indexOf(test, "C01"), AceExposureAnalyzer.YES);

        var result = new TestResultService(catalogue, analyzer).analyze(test, attempt);

        assertThat(result.general()).isSameAs(interpretation);
        assertThat(result.aceExposure().profileCode()).isEqualTo("ONE_GROUP");
        assertThat(result.areaResults()).isEmpty();
        assertThat(result.styleResults()).isEmpty();
    }

    private int indexOf(PsychologicalTest test, String code) {
        for (int index = 0; index < test.questions().size(); index++) {
            if (code.equals(test.questions().get(index).indicatorCode())) return index;
        }
        throw new IllegalArgumentException(code);
    }

    private TestAttempt attemptFilledWith(int answer) {
        TestAttempt attempt = new TestAttempt(24);
        for (int index = 0; index < 24; index++) attempt.answer(index, answer);
        return attempt;
    }

    private PsychologicalTest test() {
        List<TestArea> areas = List.of(
                new TestArea("A", "Esperienze dirette", "", "", ""),
                new TestArea("B", "Bisogni e cura", "", "", ""),
                new TestArea("C", "Sicurezza in casa", "", "", ""),
                new TestArea("D", "Pari e comunità", "", "", ""));
        List<TestQuestion> questions = new ArrayList<>();
        for (int number = 1; number <= 6; number++) {
            for (String group : List.of("A", "B", "C", "D")) {
                questions.add(new TestQuestion("Indicatore", null, group, "%s%02d".formatted(group, number)));
            }
        }
        return new PsychologicalTest("esperienze-avverse-infanzia", "ACE", "ACE", "", "", "", "", "", "",
                "1.0", false, "", "", AceExposureAnalyzer.SCORING_MODEL, AceExposureAnalyzer.ANSWER_SCALE,
                areas, questions, List.of());
    }

    private PsychologicalTest withQuestions(PsychologicalTest original, List<TestQuestion> questions) {
        return new PsychologicalTest(original.id(), original.title(), original.seoTitle(), original.eyebrow(),
                original.description(), original.seoDescription(), original.duration(), original.introductoryText(),
                original.responseInstruction(), original.version(), original.scoreVisible(),
                original.overallMetricLabel(), original.areaMetricLabel(), original.scoringModel(),
                original.answerScale(), original.areas(), questions, original.references());
    }
}
