package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@SpringBootTest
class TestResultServiceTest {

    @Autowired
    private TestCatalogue catalogue;

    @Autowired
    private TestResultService resultService;

    @Test
    void autismTestIsLoadedFromDatabaseWithItsInternalStructure() {
        PsychologicalTest test = catalogue.findById("tratti-autistici-adulti");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("2.0");
    }

    @Test
    void lowAnswersSelectLowGeneralAndFourSpecificAnalyses() {
        TestResult result = analyzeWithAnswers(1, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Poche esperienze ricorrenti");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults()).allSatisfy(area -> assertThat(area.percentage()).isZero());
    }

    @Test
    void oneHighAreaSelectsFocusedProfile() {
        TestResult result = analyzeWithAnswers(5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Alcuni aspetti emergono più chiaramente");
        assertThat(result.areaResults().get(0).title()).isEqualTo("Interazione sociale e reciprocità emotiva");
        assertThat(result.areaResults().get(0).description()).contains("spazio nella conversazione");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void highAnswersAcrossAreasSelectBroadProfile() {
        TestResult result = analyzeWithAnswers(5, 5, 5, 5);

        assertThat(result.general().title()).isEqualTo("Esperienze presenti in più ambiti");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults()).allSatisfy(area -> assertThat(area.percentage()).isEqualTo(100));
    }

    @Test
    void adhdTestIsLoadedFromDatabaseWithItsInternalStructure() {
        PsychologicalTest test = catalogue.findById("tratti-adhd-adulti");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("2.0");
    }

    @Test
    void adhdFocusedProfileUsesTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("tratti-adhd-adulti", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Alcuni aspetti emergono con chiarezza");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Attenzione sostenuta e distraibilità");
        assertThat(result.areaResults().get(0).description()).contains("distraibilità frequente");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void adhdHighAnswersAcrossAreasSelectBroadProfile() {
        TestResult result = analyzeWithAnswersForTest("tratti-adhd-adulti", 5, 5, 5, 5);

        assertThat(result.general().title()).isEqualTo("Difficoltà presenti in più ambiti");
    }

    @Test
    void obsessiveCompulsiveTestIsLoadedWithTwentyFourQuestionsAndFourAreas() {
        PsychologicalTest test = catalogue.findById("tratti-ossessivo-compulsivi");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.0");
    }

    @Test
    void obsessiveCompulsiveFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("tratti-ossessivo-compulsivi", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Alcuni aspetti emergono con chiarezza");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Pensieri intrusivi, dubbio e bisogno di certezza");
        assertThat(result.areaResults().get(0).description()).contains("pensieri intrusivi o dubbi ricorrenti");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void obsessiveCompulsiveLowAnswersSelectLowProfileAndEmptyBars() {
        TestResult result = analyzeWithAnswersForTest("tratti-ossessivo-compulsivi", 1, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Poche esperienze ricorrenti");
        assertThat(result.areaResults()).allSatisfy(area -> assertThat(area.percentage()).isZero());
    }

    @Test
    void obsessiveCompulsiveHighAnswersAcrossAreasSelectBroadProfile() {
        TestResult result = analyzeWithAnswersForTest("tratti-ossessivo-compulsivi", 5, 5, 5, 5);

        assertThat(result.general().title()).isEqualTo("Esperienze ricorrenti in più ambiti");
        assertThat(result.areaResults()).allSatisfy(area -> assertThat(area.percentage()).isEqualTo(100));
    }

    @Test
    void onlyTheInformationTestsRemainAvailable() {
        assertThat(catalogue.findAll())
                .extracting(PsychologicalTest::id)
                .containsExactly("tratti-autistici-adulti", "tratti-adhd-adulti", "tratti-ossessivo-compulsivi");
        assertThatIllegalArgumentException().isThrownBy(() -> catalogue.findById("vera-web-app"));
        assertThatIllegalArgumentException().isThrownBy(() -> catalogue.findById("equilibrio-quotidiano"));
    }

    @Test
    void areaBarMapsTheAnswerScaleFromZeroToOneHundred() {
        TestResult result = analyzeWithAnswersForTest("tratti-adhd-adulti", 3, 1, 1, 1);

        assertThat(result.areaResults().get(0).percentage()).isEqualTo(50);
        assertThat(result.areaResults().stream().skip(1)).allSatisfy(area -> assertThat(area.percentage()).isZero());
    }

    private TestResult analyzeWithAnswers(int social, int nonVerbal, int routine, int sensory) {
        return analyzeWithAnswersForTest("tratti-autistici-adulti", social, nonVerbal, routine, sensory);
    }

    private TestResult analyzeWithAnswersForTest(String testId, int first, int second, int third, int fourth) {
        PsychologicalTest test = catalogue.findById(testId);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        int[] areaAnswers = {first, second, third, fourth};
        for (int area = 0; area < areaAnswers.length; area++) {
            for (int offset = 0; offset < 6; offset++) {
                attempt.answer(area * 6 + offset, areaAnswers[area]);
            }
        }
        return resultService.analyze(test, attempt);
    }
}
