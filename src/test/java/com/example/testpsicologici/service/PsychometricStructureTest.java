package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PsychometricStructureTest {

    private static final Pattern SENTENCE_END = Pattern.compile("[.!?](?=\\s|$)");
    private static final Map<String, String> RESULT_TITLE_SUBJECTS = Map.ofEntries(
            Map.entry("tratti-autistici-adulti", "tratti autistici esplorati"),
            Map.entry("tratti-adhd-adulti", "esperienze associate all'adhd"),
            Map.entry("tratti-ossessivo-compulsivi", "esperienze ossessivo-compulsive"),
            Map.entry("autostima", "difficoltà legate all'autostima"),
            Map.entry("dipendenza-affettiva", "dinamiche di dipendenza affettiva"),
            Map.entry("assertivita", "risorse assertive"),
            Map.entry("intelligenza-emotiva", "competenze emotive percepite"),
            Map.entry("perfezionismo", "dinamiche perfezionistiche"),
            Map.entry("ansia-sociale", "esperienze di ansia sociale"),
            Map.entry("dinamiche-narcisistiche-partner", "dinamiche narcisistiche percepite"),
            Map.entry("ansia-generalizzata", "esperienze associate all'ansia generalizzata"),
            Map.entry("umore-depresso", "esperienze di umore depresso"),
            Map.entry("people-pleasing", "dinamiche di people pleasing"),
            Map.entry("sindrome-impostore", "esperienze del fenomeno dell'impostore"),
            Map.entry("autosabotaggio", "meccanismi di autosabotaggio esplorati"),
            Map.entry("tratti-borderline-adulti", "esperienze associate ai tratti borderline"),
            Map.entry("paura-abbandono", "paura dell'abbandono"),
            Map.entry("fomo", "esperienze di fomo"),
            Map.entry("intelligenza-linguistica", "risorse linguistiche percepite"),
            Map.entry("intelligenza-intrapersonale", "risorse intrapersonali percepite"));

    @Autowired
    private TestCatalogue catalogue;

    @Autowired
    private TestResultService resultService;

    @Test
    void everyQuestionnaireHasACompleteBalancedAndInterleavedBlueprint() {
        assertThat(catalogue.findAll()).hasSize(20).allSatisfy(test -> {
            assertThat(test.responseInstruction()).isNotBlank().containsIgnoringCase("frequenza");
            assertThat(test.areas()).hasSize(4);
            assertThat(test.questions()).hasSize(24);
            assertThat(new HashSet<>(test.questions())).hasSize(24);

            test.areas().forEach(area -> assertThat(test.questions())
                    .filteredOn(question -> question.areaCode().equals(area.code()))
                    .hasSize(6));

            for (int index = 1; index < test.questions().size(); index++) {
                assertThat(test.questions().get(index).areaCode())
                        .as("Gli item consecutivi di %s devono appartenere ad aree diverse", test.id())
                        .isNotEqualTo(test.questions().get(index - 1).areaCode());
            }
        });
    }

    @Test
    void descriptiveProfilesDependOnAreaPatternRatherThanAnOpaqueOverallCutoff() {
        assertThat(resultService.profileCode(0, 4, 4)).isEqualTo("LOW");
        assertThat(resultService.profileCode(0, 3, 4)).isEqualTo("MIXED");
        assertThat(resultService.profileCode(1, 3, 4)).isEqualTo("FOCUSED");
        assertThat(resultService.profileCode(3, 1, 4)).isEqualTo("BROAD");
    }

    @Test
    void descriptiveThresholdsAreAlignedWithResponseCategoryMidpoints() {
        assertThat(TestResultService.LOW_LIMIT).isEqualTo(2.5);
        assertThat(TestResultService.HIGH_LIMIT).isEqualTo(3.5);
    }

    @Test
    void optionalExamplesRemainSeparateBriefAndNonExhaustive() {
        List<String> examples = catalogue.findAll().stream()
                .flatMap(test -> test.questions().stream())
                .map(question -> question.example())
                .filter(example -> example != null)
                .toList();

        assertThat(examples).hasSize(52).allSatisfy(example -> {
            assertThat(example)
                    .isNotBlank()
                    .hasSizeLessThanOrEqualTo(180)
                    .doesNotContainIgnoringCase("per esempio")
                    .endsWith(".");
            assertThat(Character.isLowerCase(example.codePointAt(0))).isTrue();
        });
    }

    @Test
    void everyOverallProfileProvidesAStandaloneDetailedInterpretation() {
        catalogue.findAll().forEach(test -> {
            List<TestResult> profiles = List.of(
                    analyzeWithAreaAnswers(test, 1, 1, 1, 1),
                    analyzeWithAreaAnswers(test, 3, 3, 3, 3),
                    analyzeWithAreaAnswers(test, 5, 1, 1, 1),
                    analyzeWithAreaAnswers(test, 5, 5, 5, 5));

            assertThat(profiles)
                    .as("I quattro profili complessivi di %s devono essere distinti", test.id())
                    .extracting(result -> result.general().title())
                    .doesNotHaveDuplicates();

            profiles.forEach(result -> {
                String description = result.general().description();
                String detail = result.general().detail();

                assertThat(description)
                        .as("Sintesi complessiva di %s: %s", test.id(), result.general().title())
                        .hasSizeGreaterThanOrEqualTo(150);
                assertThat(sentenceCount(description))
                        .as("La sintesi complessiva di %s deve contenere almeno due frasi", test.id())
                        .isGreaterThanOrEqualTo(2);
                assertThat(detail)
                        .as("Approfondimento complessivo di %s: %s", test.id(), result.general().title())
                        .hasSizeGreaterThanOrEqualTo(260);
                assertThat(sentenceCount(detail))
                        .as("L'approfondimento complessivo di %s deve contenere almeno tre frasi", test.id())
                        .isGreaterThanOrEqualTo(3);
            });
        });
    }

    @Test
    void everyOverallTitleNamesItsSpecificSubjectAndProfileDistribution() {
        assertThat(RESULT_TITLE_SUBJECTS).hasSize(catalogue.findAll().size());

        catalogue.findAll().forEach(test -> {
            List<TestResult> profiles = List.of(
                    analyzeWithAreaAnswers(test, 1, 1, 1, 1),
                    analyzeWithAreaAnswers(test, 3, 3, 3, 3),
                    analyzeWithAreaAnswers(test, 5, 1, 1, 1),
                    analyzeWithAreaAnswers(test, 5, 5, 5, 5));
            String subject = RESULT_TITLE_SUBJECTS.get(test.id());

            assertThat(subject).as("Descrittore del titolo di %s", test.id()).isNotBlank();
            assertThat(profiles).allSatisfy(result ->
                    assertThat(result.general().title()).containsIgnoringCase(subject));
            assertThat(profiles.get(0).general().title()).containsIgnoringCase("poco");
            assertThat(profiles.get(1).general().title()).containsIgnoringCase("modo variabile");
            assertThat(profiles.get(2).general().title()).containsIgnoringCase("una o due aree");
            assertThat(profiles.get(3).general().title()).containsIgnoringCase("più aree");
        });
    }

    private TestResult analyzeWithAreaAnswers(PsychologicalTest test, int... areaAnswers) {
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int questionIndex = 0; questionIndex < test.questions().size(); questionIndex++) {
            String areaCode = test.questions().get(questionIndex).areaCode();
            int areaIndex = java.util.stream.IntStream.range(0, test.areas().size())
                    .filter(index -> test.areas().get(index).code().equals(areaCode))
                    .findFirst()
                    .orElseThrow();
            attempt.answer(questionIndex, areaAnswers[areaIndex]);
        }
        return resultService.analyze(test, attempt);
    }

    private long sentenceCount(String text) {
        return SENTENCE_END.matcher(text).results().count();
    }
}
