package com.example.testpsicologici.service;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        assertThat(test.version()).isEqualTo("2.5");
        assertThat(test.seoTitle()).startsWith("Test autismo adulti online");
        assertThat(test.seoDescription()).contains("senza registrazione");
        assertThat(test.references()).hasSize(3);
        assertThat(test.references()).extracting(reference -> reference.title())
                .anyMatch(title -> title.contains("ISS/SNLG"));
        assertThat(test.references()).allSatisfy(reference ->
                assertThat(reference.contribution()).isNotBlank());
    }

    @Test
    void lowAnswersSelectLowGeneralAndFourSpecificAnalyses() {
        TestResult result = analyzeWithAnswers(1, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("I tratti autistici esplorati sembrano poco presenti");
        assertThat(result.percentage()).isZero();
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults()).allSatisfy(area -> assertThat(area.percentage()).isZero());
    }

    @Test
    void oneHighAreaSelectsFocusedProfile() {
        TestResult result = analyzeWithAnswers(5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("I tratti autistici esplorati sembrano più presenti in una o due aree");
        assertThat(result.areaResults().get(0).title()).isEqualTo("Interazione sociale e reciprocità emotiva");
        assertThat(result.areaResults().get(0).description()).contains("spazio nella conversazione");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void highAnswersAcrossAreasSelectBroadProfile() {
        TestResult result = analyzeWithAnswers(5, 5, 5, 5);

        assertThat(result.general().title()).isEqualTo("I tratti autistici esplorati sembrano frequentemente presenti in più aree");
        assertThat(result.percentage()).isEqualTo(100);
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
        assertThat(test.version()).isEqualTo("2.5");
    }

    @Test
    void adhdFocusedProfileUsesTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("tratti-adhd-adulti", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze associate all'ADHD sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Attenzione sostenuta e distraibilità");
        assertThat(result.areaResults().get(0).description()).contains("distraibilità frequente");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void adhdHighAnswersAcrossAreasSelectBroadProfile() {
        TestResult result = analyzeWithAnswersForTest("tratti-adhd-adulti", 5, 5, 5, 5);

        assertThat(result.general().title()).isEqualTo("Le esperienze associate all'ADHD sembrano frequentemente presenti in più aree");
    }

    @Test
    void obsessiveCompulsiveTestIsLoadedWithTwentyFourQuestionsAndFourAreas() {
        PsychologicalTest test = catalogue.findById("tratti-ossessivo-compulsivi");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.6");
    }

    @Test
    void obsessiveCompulsiveFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("tratti-ossessivo-compulsivi", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze ossessivo-compulsive sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Pensieri intrusivi, dubbio e bisogno di certezza");
        assertThat(result.areaResults().get(0).description()).contains("pensieri intrusivi o dubbi ricorrenti");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void obsessiveCompulsiveLowAnswersSelectLowProfileAndEmptyBars() {
        TestResult result = analyzeWithAnswersForTest("tratti-ossessivo-compulsivi", 1, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze ossessivo-compulsive sembrano poco presenti");
        assertThat(result.areaResults()).allSatisfy(area -> assertThat(area.percentage()).isZero());
    }

    @Test
    void obsessiveCompulsiveHighAnswersAcrossAreasSelectBroadProfile() {
        TestResult result = analyzeWithAnswersForTest("tratti-ossessivo-compulsivi", 5, 5, 5, 5);

        assertThat(result.general().title()).isEqualTo("Le esperienze ossessivo-compulsive sembrano frequentemente presenti in più aree");
        assertThat(result.areaResults()).allSatisfy(area -> assertThat(area.percentage()).isEqualTo(100));
    }

    @Test
    void selfEsteemTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("autostima");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.overallMetricLabel()).isEqualTo("Difficoltà complessive relative all'autostima");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle difficoltà");
    }

    @Test
    void selfEsteemFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("autostima", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le difficoltà legate all'autostima sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Valore personale e autoaccettazione");
        assertThat(result.areaResults().get(0).description()).contains("dignità personale");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void selfEsteemLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("autostima", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("autostima", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le difficoltà legate all'autostima sembrano poco presenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le difficoltà legate all'autostima sembrano frequentemente presenti in più aree");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void emotionalDependenceTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("dipendenza-affettiva");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche esplorate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche");
    }

    @Test
    void emotionalDependenceFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("dipendenza-affettiva", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le dinamiche di dipendenza affettiva sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Paura della separazione e bisogno di rassicurazione");
        assertThat(result.areaResults().get(0).description()).contains("paura frequente della separazione");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void emotionalDependenceLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("dipendenza-affettiva", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("dipendenza-affettiva", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le dinamiche di dipendenza affettiva sembrano poco presenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le dinamiche di dipendenza affettiva sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("Controllo, minacce e violenza non sono colpa tua");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void assertivenessTestIsLoadedWithTwentyFourQuestionsAndPositiveLabels() {
        PsychologicalTest test = catalogue.findById("assertivita");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva dei comportamenti assertivi");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza dei comportamenti assertivi");
    }

    @Test
    void assertivenessFocusedProfileShowsTheStrongestAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("assertivita", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le risorse assertive sembrano più espresse in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Espressione di opinioni, bisogni ed emozioni");
        assertThat(result.areaResults().get(0).description()).contains("buona capacità di rendere visibili");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void assertivenessLowAndBroadProfilesFollowThePositiveScoringDirection() {
        TestResult low = analyzeWithAnswersForTest("assertivita", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("assertivita", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le risorse assertive sembrano poco espresse");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le risorse assertive sembrano frequentemente espresse in più aree");
        assertThat(broad.general().detail()).contains("ascoltare, negoziare");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void emotionalIntelligenceTestIsLoadedWithTwentyFourQuestionsAndPositiveLabels() {
        PsychologicalTest test = catalogue.findById("intelligenza-emotiva");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle competenze emotive esplorate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle competenze emotive");
    }

    @Test
    void emotionalIntelligenceFocusedProfileShowsTheStrongestAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("intelligenza-emotiva", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le competenze emotive percepite sembrano più espresse in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Percezione e consapevolezza emotiva");
        assertThat(result.areaResults().get(0).description()).contains("buona attenzione ai segnali corporei");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void emotionalIntelligenceLowAndBroadProfilesFollowThePositiveScoringDirection() {
        TestResult low = analyzeWithAnswersForTest("intelligenza-emotiva", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("intelligenza-emotiva", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le competenze emotive percepite sembrano poco espresse");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le competenze emotive percepite sembrano frequentemente espresse in più aree");
        assertThat(broad.general().detail()).contains("non è una misura oggettiva di abilità");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void perfectionismTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("perfezionismo");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche perfezionistiche");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche perfezionistiche");
    }

    @Test
    void perfectionismFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("perfezionismo", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le dinamiche perfezionistiche sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Standard elevati e valore legato ai risultati");
        assertThat(result.areaResults().get(0).description()).contains("standard frequentemente molto elevati");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void perfectionismLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("perfezionismo", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("perfezionismo", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le dinamiche perfezionistiche sembrano poco presenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le dinamiche perfezionistiche sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("procrastinazione, esaurimento");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void socialAnxietyTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("ansia-sociale");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze di ansia sociale");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void socialAnxietyFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("ansia-sociale", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze di ansia sociale sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Paura del giudizio e dell'imbarazzo");
        assertThat(result.areaResults().get(0).description()).contains("paura frequente di essere giudicato");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void socialAnxietyLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("ansia-sociale", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("ansia-sociale", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le esperienze di ansia sociale sembrano poco presenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le esperienze di ansia sociale sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("L'ansia sociale è trattabile");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void perceivedNarcissisticDynamicsTestIsLoadedWithTwentyFourQuestionsAndSafeLabels() {
        PsychologicalTest test = catalogue.findById("dinamiche-narcisistiche-partner");

        assertThat(test.title()).isEqualTo("Dinamiche narcisistiche percepite nella relazione di coppia");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.introductoryText()).contains("non può stabilire", "valutazione clinica diretta", "112", "1522");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche osservate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche osservate");
    }

    @Test
    void perceivedNarcissisticDynamicsFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("dinamiche-narcisistiche-partner", 1, 1, 1, 5);

        assertThat(result.general().title()).isEqualTo("Le dinamiche narcisistiche percepite sembrano più presenti in una o due aree della relazione");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(3).title()).isEqualTo("Confini, controllo e impatto sulla relazione");
        assertThat(result.areaResults().get(3).description()).contains("indipendentemente da qualsiasi etichetta diagnostica");
        assertThat(result.areaResults().get(3).percentage()).isEqualTo(100);
    }

    @Test
    void perceivedNarcissisticDynamicsLowAndBroadProfilesAvoidDiagnosingThePartner() {
        TestResult low = analyzeWithAnswersForTest("dinamiche-narcisistiche-partner", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("dinamiche-narcisistiche-partner", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le dinamiche narcisistiche percepite sembrano poco presenti nella relazione");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le dinamiche narcisistiche percepite sembrano frequentemente presenti in più aree della relazione");
        assertThat(broad.general().detail()).contains("non dimostra", "112", "1522");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void generalizedAnxietyTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("ansia-generalizzata");

        assertThat(test.title()).isEqualTo("Ansia generalizzata");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.introductoryText()).contains("ultimi sei mesi", "condizioni mediche", "Non è uno strumento diagnostico");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze di ansia");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void generalizedAnxietyFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("ansia-generalizzata", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze associate all'ansia generalizzata sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Preoccupazione diffusa e difficoltà di controllo");
        assertThat(result.areaResults().get(0).description()).contains("preoccupazione frequente");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void generalizedAnxietyLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("ansia-generalizzata", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("ansia-generalizzata", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le esperienze associate all'ansia generalizzata sembrano poco presenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le esperienze associate all'ansia generalizzata sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("escludere altre cause", "non diagnostico");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void depressedMoodTestIsLoadedWithTwentyFourQuestionsAndSafetyInformation() {
        PsychologicalTest test = catalogue.findById("umore-depresso");

        assertThat(test.title()).isEqualTo("Umore depresso e sintomi depressivi");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.introductoryText()).contains("ultime due settimane", "non valuta il rischio suicidario", "112", "Pronto Soccorso");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze legate all'umore");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void depressedMoodFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("umore-depresso", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze di umore depresso sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Tono dell'umore e capacità di provare piacere");
        assertThat(result.areaResults().get(0).description()).contains("calo frequente del tono dell'umore");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
        assertThat(result.general().detail()).contains("non valuta il rischio suicidario", "112");
    }

    @Test
    void depressedMoodLowAndBroadProfilesAlwaysIncludeSafetyInformation() {
        TestResult low = analyzeWithAnswersForTest("umore-depresso", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("umore-depresso", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le esperienze di umore depresso sembrano poco presenti");
        assertThat(low.general().detail()).contains("non valuta il rischio suicidario", "Pronto Soccorso");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le esperienze di umore depresso sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("problemi depressivi sono trattabili", "112");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void peoplePleasingTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("people-pleasing");

        assertThat(test.title()).isEqualTo("People pleasing e bisogno di approvazione");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.introductoryText()).contains("non una diagnosi", "differenze di potere", "strategia protettiva");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche di compiacenza");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche");
    }

    @Test
    void peoplePleasingFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("people-pleasing", 1, 1, 1, 5);

        assertThat(result.general().title()).isEqualTo("Le dinamiche di people pleasing sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(3).title()).isEqualTo("Sovraresponsabilità e trascuratezza di sé");
        assertThat(result.areaResults().get(3).description()).contains("sovraresponsabilità frequente");
        assertThat(result.areaResults().get(3).percentage()).isEqualTo(100);
    }

    @Test
    void peoplePleasingLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("people-pleasing", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("people-pleasing", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le dinamiche di people pleasing sembrano poco presenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le dinamiche di people pleasing sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("forte squilibrio di potere", "non diagnostico");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void impostorPhenomenonTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("sindrome-impostore");

        assertThat(test.title()).isEqualTo("Sindrome dell'impostore");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.introductoryText()).contains("fenomeno dell'impostore", "non è una diagnosi", "discriminazione", "non misura la tua competenza reale");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze di impostore");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void impostorPhenomenonFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("sindrome-impostore", 1, 5, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze del fenomeno dell'impostore sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(1).title()).isEqualTo("Dubbi di competenza e paura di essere smascherati");
        assertThat(result.areaResults().get(1).description()).contains("paura frequente di essere smascherato");
        assertThat(result.areaResults().get(1).percentage()).isEqualTo(100);
        assertThat(result.general().detail()).contains("non misura la competenza effettiva");
    }

    @Test
    void impostorPhenomenonLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("sindrome-impostore", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("sindrome-impostore", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Le esperienze del fenomeno dell'impostore sembrano poco presenti");
        assertThat(low.general().detail()).contains("ruolo nuovo");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Le esperienze del fenomeno dell'impostore sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("esclusione o discriminazione", "non diagnostico", "non stabilisce quanto sei competente");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void selfSabotageTestIsLoadedWithTwentyFourQuestionsAndContextualSafeguards() {
        PsychologicalTest test = catalogue.findById("autosabotaggio");

        assertThat(test.title()).isEqualTo("Autosabotaggio e ostacoli agli obiettivi");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.5");
        assertThat(test.introductoryText()).contains("non una diagnosi", "Non implica", "può essere adattivo", "difficoltà esecutive", "non moralistica");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva degli ostacoli autoalimentati");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza degli ostacoli");
    }

    @Test
    void selfSabotageFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("autosabotaggio", 1, 5, 1, 1);

        assertThat(result.general().title()).isEqualTo("I meccanismi di autosabotaggio esplorati sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(1).title()).isEqualTo("Paura della valutazione e auto-handicapping");
        assertThat(result.areaResults().get(1).description()).contains("auto-handicapping frequente");
        assertThat(result.areaResults().get(1).percentage()).isEqualTo(100);
        assertThat(result.general().detail()).contains("Non attribuisce intenzioni", "carico reale");
    }

    @Test
    void selfSabotageLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("autosabotaggio", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("autosabotaggio", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("I meccanismi di autosabotaggio esplorati sembrano poco presenti");
        assertThat(low.general().detail()).contains("abbandono di obiettivi non più realistici");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("I meccanismi di autosabotaggio esplorati sembrano frequentemente presenti in più aree");
        assertThat(broad.general().detail()).contains("non diagnostico", "difficoltà esecutive", "non misura la tua forza di volontà");
        assertThat(broad.percentage()).isEqualTo(100);
    }

    @Test
    void borderlineTraitsTestIsLoadedWithTwentyFourQuestionsAndSafetyLimits() {
        PsychologicalTest test = catalogue.findById("tratti-borderline-adulti");

        assertThat(test.title()).isEqualTo("Tratti associati al disturbo borderline di personalità");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("emozioni", "relazioni", "identita", "impulsi");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.2");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "frequenza");
        assertThat(test.introductoryText()).contains(
                "non può confermare, escludere o stimare",
                "non valuta autolesionismo",
                "pensieri suicidari",
                "112",
                "Pronto Soccorso",
                "spiegazioni alternative");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze esplorate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://pubmed.ncbi.nlm.nih.gov/28604275/",
                "https://iris.who.int/bitstream/handle/10665/375767/9789240077263-eng.pdf?sequence=1",
                "https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_2461_allegato.pdf",
                "https://www.iss.it/-/diagnosi-trattamento-disturbo-borderline-personalit%C3%A0_in-prog");
    }

    @Test
    void borderlineFocusedProfileKeepsTheoreticalAreaOrderAndAreaMeaning() {
        TestResult result = analyzeWithAnswersForTest("tratti-borderline-adulti", 1, 5, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze associate ai tratti borderline sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Intensità emotiva e ritorno all'equilibrio",
                "Relazioni e sensibilità alla distanza",
                "Identità, immagine di sé e senso di vuoto",
                "Impulsività, rabbia e reazioni allo stress");
        assertThat(result.areaResults().get(1).description()).contains("frequente sensibilità ai segnali di distanza");
        assertThat(result.areaResults().get(1).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void borderlineProfilesFollowEditorialRulesAndAlwaysRetainSafetyMessage() {
        TestResult low = analyzeWithAnswersForTest("tratti-borderline-adulti", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("tratti-borderline-adulti", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("tratti-borderline-adulti", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("tratti-borderline-adulti", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("Le esperienze associate ai tratti borderline sembrano poco presenti");
        assertThat(mixed.general().title()).isEqualTo("Le esperienze associate ai tratti borderline sembrano presenti in modo variabile");
        assertThat(focused.general().title()).isEqualTo("Le esperienze associate ai tratti borderline sembrano più presenti in una o due aree");
        assertThat(broad.general().title()).isEqualTo("Le esperienze associate ai tratti borderline sembrano frequentemente presenti in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non è una diagnosi",
                        "non valuta autolesionismo",
                        "pensieri suicidari",
                        "112",
                        "Pronto Soccorso"));
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void fearOfAbandonmentTestIsLoadedWithTwentyFourQuestionsAndRelationalSafeguards() {
        PsychologicalTest test = catalogue.findById("paura-abbandono");

        assertThat(test.title()).isEqualTo("Paura dell'abbandono");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("segnali", "rassicurazione", "distanza", "confini");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.2");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "relazioni per te importanti", "frequenza");
        assertThat(test.introductoryText()).contains(
                "non è qui una diagnosi",
                "non classifica uno stile di attaccamento",
                "qualità reale delle relazioni",
                "controllo, coercizione o violenza",
                "112",
                "1522");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze esplorate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://pubmed.ncbi.nlm.nih.gov/36407970/",
                "https://pmc.ncbi.nlm.nih.gov/articles/PMC7453162/",
                "https://pubmed.ncbi.nlm.nih.gov/33937113/",
                "https://doi.org/10.1023/A:1024515519160");
    }

    @Test
    void fearOfAbandonmentFocusedProfileKeepsTheoreticalAreaOrderAndAreaMeaning() {
        TestResult result = analyzeWithAnswersForTest("paura-abbandono", 1, 5, 1, 1);

        assertThat(result.general().title()).isEqualTo("La paura dell'abbandono sembra più presente in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Sensibilità ai segnali di distanza",
                "Ricerca di rassicurazione e vicinanza",
                "Pensieri ed emozioni durante la distanza",
                "Autonomia e confini quando si teme la perdita");
        assertThat(result.areaResults().get(1).description()).contains("frequente ricerca di rassicurazione");
        assertThat(result.areaResults().get(1).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void fearOfAbandonmentProfilesFollowEditorialRulesAndAlwaysRetainSafetyMessage() {
        TestResult low = analyzeWithAnswersForTest("paura-abbandono", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("paura-abbandono", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("paura-abbandono", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("paura-abbandono", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("La paura dell'abbandono sembra poco presente");
        assertThat(mixed.general().title()).isEqualTo("La paura dell'abbandono sembra presente in modo variabile");
        assertThat(focused.general().title()).isEqualTo("La paura dell'abbandono sembra più presente in una o due aree");
        assertThat(broad.general().title()).isEqualTo("La paura dell'abbandono sembra frequentemente presente in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non diagnostica una condizione",
                        "non classifica il tuo stile di attaccamento",
                        "controllo, coercizione o violenza",
                        "112",
                        "1522"));
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void fomoTestIsLoadedWithTwentyFourQuestionsAndConceptualLimits() {
        PsychologicalTest test = catalogue.findById("fomo");

        assertThat(test.title()).isEqualTo("FOMO (Fear of Missing Out)");
        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("inclusione", "confronto", "connessione", "interferenza");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.1");
        assertThat(test.responseInstruction()).contains("ultimo mese", "frequenza");
        assertThat(test.introductoryText()).contains(
                "online e fuori dai social",
                "non è una diagnosi",
                "non valuta né dimostra un uso problematico",
                "non stabiliscono",
                "sonno, concentrazione, attività o relazioni");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze FOMO");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://pubmed.ncbi.nlm.nih.gov/31704432/",
                "https://doi.org/10.1016/j.chbr.2024.100374",
                "https://doi.org/10.1016/j.chb.2013.02.014",
                "https://doi.org/10.1371/journal.pone.0308643");
    }

    @Test
    void fomoFocusedProfileKeepsTheoreticalAreaOrderAndAreaMeaning() {
        TestResult result = analyzeWithAnswersForTest("fomo", 1, 1, 5, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze di FOMO sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Inclusione e appartenenza percepita",
                "Confronto con esperienze alternative",
                "Bisogno di restare aggiornati e connessi",
                "Interferenza su attenzione e scelte");
        assertThat(result.areaResults().get(2).description()).contains("frequente bisogno di aggiornamenti");
        assertThat(result.areaResults().get(2).description()).contains("Non dimostra una dipendenza tecnologica");
        assertThat(result.areaResults().get(2).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void fomoProfilesFollowEditorialRulesAndAlwaysRetainSupportMessage() {
        TestResult low = analyzeWithAnswersForTest("fomo", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("fomo", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("fomo", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("fomo", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("Le esperienze di FOMO sembrano poco presenti");
        assertThat(mixed.general().title()).isEqualTo("Le esperienze di FOMO sembrano presenti in modo variabile");
        assertThat(focused.general().title()).isEqualTo("Le esperienze di FOMO sembrano più presenti in una o due aree");
        assertThat(broad.general().title()).isEqualTo("Le esperienze di FOMO sembrano frequentemente presenti in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non diagnostica una condizione",
                        "non dimostra un uso problematico di social o smartphone",
                        "sonno, concentrazione, attività o relazioni",
                        "psicologo, psicoterapeuta o medico qualificato"));
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void linguisticIntelligenceTestIsLoadedWithTwentyFourOriginalQuestionsAndMeasurementLimits() {
        PsychologicalTest test = catalogue.findById("intelligenza-linguistica");

        assertThat(test.title()).isEqualTo("Intelligenza linguistica");
        assertThat(test.questions()).hasSize(24).allSatisfy(question ->
                assertThat(question.example()).isNull());
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("comprensione", "orale", "scrittura", "flessibilita");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.1");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "occasioni", "frequenza");
        assertThat(test.introductoryText()).contains(
                "Howard Gardner",
                "non misura un'intelligenza indipendente",
                "competenza linguistica oggettiva",
                "non certifica un talento o un limite",
                "professionista qualificato");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle risorse linguistiche riferite");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle risorse riferite");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf",
                "https://doi.org/10.1016/j.intell.2006.02.004",
                "https://www.coe.int/en/web/common-european-framework-reference-languages/mediation",
                "https://www.inapp.gov.it/piaac/conosci-piaac/lindagine-piaac");
    }

    @Test
    void linguisticIntelligenceFocusedProfileKeepsTheoreticalOrderAndPositiveDirection() {
        TestResult result = analyzeWithAnswersForTest("intelligenza-linguistica", 1, 1, 5, 1);

        assertThat(result.general().title()).isEqualTo("Le risorse linguistiche percepite sembrano più espresse in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Comprensione e sensibilità al significato",
                "Espressione orale e adattamento",
                "Espressione scritta e revisione",
                "Apprendimento e uso flessibile delle parole");
        assertThat(result.areaResults().get(2).description()).contains("uso frequente", "Non è una prova");
        assertThat(result.areaResults().get(2).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void linguisticIntelligenceProfilesFollowEditorialRulesAndAlwaysRetainMeasurementLimit() {
        TestResult low = analyzeWithAnswersForTest("intelligenza-linguistica", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("intelligenza-linguistica", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("intelligenza-linguistica", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("intelligenza-linguistica", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("Le risorse linguistiche percepite sembrano poco espresse");
        assertThat(mixed.general().title()).isEqualTo("Le risorse linguistiche percepite sembrano espresse in modo variabile");
        assertThat(focused.general().title()).isEqualTo("Le risorse linguistiche percepite sembrano più espresse in una o due aree");
        assertThat(broad.general().title()).isEqualTo("Le risorse linguistiche percepite sembrano frequentemente espresse in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non misura l'intelligenza generale né una competenza linguistica oggettiva",
                        "non certifica un talento o un limite",
                        "professionista qualificato"));
        assertThat(low.general().detail()).contains("non indica scarsa intelligenza");
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void intrapersonalIntelligenceTestIsLoadedWithTwentyFourOriginalQuestionsAndMeasurementLimits() {
        PsychologicalTest test = catalogue.findById("intelligenza-intrapersonale");

        assertThat(test.title()).isEqualTo("Intelligenza intrapersonale");
        assertThat(test.questions()).hasSize(24).allSatisfy(question ->
                assertThat(question.example()).isNull());
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("stati-interni", "chiarezza", "riflessione", "orientamento");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "occasioni", "frequenza");
        assertThat(test.introductoryText()).contains(
                "Howard Gardner",
                "non misura un'intelligenza indipendente",
                "accuratezza della conoscenza di sé",
                "Riflettere spesso non equivale",
                "professionista qualificato");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle risorse intrapersonali riferite");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle risorse riferite");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf",
                "https://doi.org/10.1016/j.intell.2006.02.004",
                "https://doi.org/10.14605/CS1532206",
                "https://pubmed.ncbi.nlm.nih.gov/26379571/");
    }

    @Test
    void intrapersonalIntelligenceFocusedProfileKeepsTheoreticalOrderAndPositiveDirection() {
        TestResult result = analyzeWithAnswersForTest("intelligenza-intrapersonale", 1, 1, 5, 1);

        assertThat(result.general().title()).isEqualTo("Le risorse intrapersonali percepite sembrano più espresse in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Riconoscimento degli stati interni",
                "Chiarezza su bisogni, valori e motivazioni",
                "Riflessione su schemi e funzionamento personale",
                "Uso della conoscenza di sé nelle scelte");
        assertThat(result.areaResults().get(2).description()).contains("frequente riesame", "non prova");
        assertThat(result.areaResults().get(2).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void intrapersonalIntelligenceProfilesFollowEditorialRulesAndAlwaysRetainMeasurementLimit() {
        TestResult low = analyzeWithAnswersForTest("intelligenza-intrapersonale", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("intelligenza-intrapersonale", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("intelligenza-intrapersonale", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("intelligenza-intrapersonale", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("Le risorse intrapersonali percepite sembrano poco espresse");
        assertThat(mixed.general().title()).isEqualTo("Le risorse intrapersonali percepite sembrano espresse in modo variabile");
        assertThat(focused.general().title()).isEqualTo("Le risorse intrapersonali percepite sembrano più espresse in una o due aree");
        assertThat(broad.general().title()).isEqualTo("Le risorse intrapersonali percepite sembrano frequentemente espresse in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non misura l'intelligenza generale né l'accuratezza della conoscenza di sé",
                        "non certifica un talento o un limite",
                        "professionista qualificato"));
        assertThat(low.general().detail()).contains("non indica scarsa intelligenza");
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void psychologicalResilienceTestIsLoadedWithTwentyFourOriginalQuestionsAndContextLimits() {
        PsychologicalTest test = catalogue.findById("resilienza-psicologica");

        assertThat(test.title()).isEqualTo("Resilienza psicologica");
        assertThat(test.questions()).hasSize(24).allSatisfy(question ->
                assertThat(question.example()).isNull());
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("recupero", "flessibilita", "risorse", "continuita");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.responseInstruction()).contains("ultimi sei mesi", "difficoltà", "frequenza");
        assertThat(test.introductoryText()).contains(
                "processo dinamico e contestuale",
                "non indica assenza di resilienza",
                "significato molto limitato",
                "non predice come reagirai in futuro",
                "professionista qualificato",
                "112");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva dei comportamenti di adattamento riferiti");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza dei comportamenti riferiti");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://doi.org/10.1027/1016-9040/a000124",
                "https://pubmed.ncbi.nlm.nih.gov/25317257/",
                "https://pubmed.ncbi.nlm.nih.gov/27031088/",
                "https://pubmed.ncbi.nlm.nih.gov/34850301/",
                "https://pubmed.ncbi.nlm.nih.gov/21294858/");
    }

    @Test
    void psychologicalResilienceFocusedProfileKeepsTheoreticalOrderAndPositiveDirection() {
        TestResult result = analyzeWithAnswersForTest("resilienza-psicologica", 1, 1, 5, 1);

        assertThat(result.general().title()).isEqualTo("Le risorse di resilienza percepite sembrano più espresse in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Recupero e regolazione dopo la difficoltà",
                "Adattamento e revisione delle strategie",
                "Accesso alle risorse relazionali e contestuali",
                "Continuità personale e orientamento");
        assertThat(result.areaResults().get(2).description()).contains("frequente ricorso", "non misura qualità");
        assertThat(result.areaResults().get(2).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void psychologicalResilienceProfilesFollowEditorialRulesAndRetainContextAndSafetyLimits() {
        TestResult low = analyzeWithAnswersForTest("resilienza-psicologica", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("resilienza-psicologica", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("resilienza-psicologica", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("resilienza-psicologica", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("Le risorse di resilienza percepite sembrano poco espresse");
        assertThat(mixed.general().title()).isEqualTo("Le risorse di resilienza percepite sembrano espresse in modo variabile");
        assertThat(focused.general().title()).isEqualTo("Le risorse di resilienza percepite sembrano più espresse in una o due aree");
        assertThat(broad.general().title()).isEqualTo("Le risorse di resilienza percepite sembrano frequentemente espresse in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non misura il processo completo di resilienza",
                        "non predice come reagirai in futuro",
                        "professionista qualificato",
                        "112"));
        assertThat(low.general().detail()).contains("non dimostra una scarsa resilienza", "poche occasioni pertinenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void partnerJealousyTestIsLoadedWithTwentyFourOriginalQuestionsAndSafetyLimits() {
        PsychologicalTest test = catalogue.findById("gelosia-partner");

        assertThat(test.title()).isEqualTo("Sono geloso/a nella relazione?");
        assertThat(test.questions()).hasSize(24).allSatisfy(question ->
                assertThat(question.example()).isNull());
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("minaccia", "attivazione", "verifica", "controllo");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "relazione attuale", "frequenza");
        assertThat(test.introductoryText()).contains(
                "minaccia reale, possibile o immaginata",
                "non stabilisce se i sospetti siano fondati",
                "non accerta un'infedeltà",
                "non giustifica accedere senza consenso",
                "non valuta violenza, stalking o sicurezza",
                "112",
                "1522");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze di gelosia riferite");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze riferite");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://doi.org/10.3389/fpsyg.2022.1013584",
                "https://doi.org/10.1177/026540758900600203",
                "https://doi.org/10.4067/S0718-48082017000200203",
                "https://doi.org/10.3390/ijerph17165682",
                "https://www.who.int/publications/i/item/WHO-RHR-12.36",
                "https://www.pariopportunita.gov.it/it/numeri-utili/1522-numero-antiviolenza-e-antistalking/");
    }

    @Test
    void partnerJealousyFocusedProfileKeepsTheoreticalOrderAndDifficultyDirection() {
        TestResult result = analyzeWithAnswersForTest("gelosia-partner", 1, 1, 1, 5);

        assertThat(result.general().title()).isEqualTo("Le esperienze di gelosia verso il partner sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Interpretazioni e preoccupazione per possibili rivali",
                "Reazioni emotive alla minaccia percepita",
                "Ricerca di rassicurazione e verifica",
                "Controllo e interferenza nella quotidianità");
        assertThat(result.areaResults().get(3).description()).contains("frequenti richieste", "autonomia", "sicurezza");
        assertThat(result.areaResults().get(3).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void partnerJealousyProfilesFollowEditorialRulesAndAlwaysRetainConsentAndSafetyMessages() {
        TestResult low = analyzeWithAnswersForTest("gelosia-partner", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("gelosia-partner", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("gelosia-partner", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("gelosia-partner", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("Le esperienze di gelosia verso il partner sembrano poco presenti");
        assertThat(mixed.general().title()).isEqualTo("Le esperienze di gelosia verso il partner sembrano presenti in modo variabile");
        assertThat(focused.general().title()).isEqualTo("Le esperienze di gelosia verso il partner sembrano più presenti in una o due aree");
        assertThat(broad.general().title()).isEqualTo("Le esperienze di gelosia verso il partner sembrano frequentemente presenti in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non stabilisce se i sospetti siano fondati",
                        "non accerta un'infedeltà",
                        "non valuta violenza o sicurezza",
                        "La gelosia non giustifica",
                        "112",
                        "1522"));
        assertThat(low.general().detail()).contains("non esclude preoccupazioni fondate", "singolo episodio");
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void lifeSatisfactionTestIsLoadedWithTwentyFourOriginalQuestionsAndMeasurementLimits() {
        PsychologicalTest test = catalogue.findById("soddisfazione-vita");

        assertThat(test.title()).isEqualTo("Sono soddisfatto/a della mia vita?");
        assertThat(test.questions()).hasSize(24).allSatisfy(question ->
                assertThat(question.example()).isNull());
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("complessiva", "quotidianita", "coerenza", "direzione");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "frequenza", "valutazione della tua vita");
        assertThat(test.introductoryText()).contains(
                "giudizio soggettivo e cognitivo",
                "non è la Satisfaction With Life Scale",
                "non fattori psicometrici dimostrati",
                "non indica fallimento, ingratitudine o incapacità",
                "non diagnostica depressione",
                "112");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle valutazioni positive riferite");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle valutazioni positive riferite");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://flore.unifi.it/handle/2158/656647",
                "https://www.istat.it/comunicato-stampa/soddisfazione-dei-cittadini-anno-2024/",
                "https://pubmed.ncbi.nlm.nih.gov/16367493/",
                "https://www.oecd.org/en/publications/oecd-guidelines-on-measuring-subjective-well-being-2025-update_9203632a-en/full-report/measuring-subjective-well-being_b4b53f27.html",
                "https://pubmed.ncbi.nlm.nih.gov/28324322/",
                "https://doi.org/10.1371/journal.pone.0313107");
    }

    @Test
    void lifeSatisfactionFocusedProfileKeepsTheoreticalOrderAndPositiveDirection() {
        TestResult result = analyzeWithAnswersForTest("soddisfazione-vita", 1, 1, 5, 1);

        assertThat(result.general().title()).isEqualTo("La soddisfazione percepita per la propria vita sembra più espressa in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Valutazione complessiva della propria vita",
                "Soddisfazione per la vita quotidiana",
                "Coerenza con priorità e criteri personali",
                "Soddisfazione per direzione e percorso recente");
        assertThat(result.areaResults().get(2).description()).contains("viene spesso percepita", "non certifica");
        assertThat(result.areaResults().get(2).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void lifeSatisfactionProfilesFollowEditorialRulesAndAlwaysRetainContextAndSafetyLimits() {
        TestResult low = analyzeWithAnswersForTest("soddisfazione-vita", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("soddisfazione-vita", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("soddisfazione-vita", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("soddisfazione-vita", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("La soddisfazione percepita per la propria vita sembra poco espressa nelle risposte");
        assertThat(mixed.general().title()).isEqualTo("La soddisfazione percepita per la propria vita sembra espressa in modo variabile tra le aree");
        assertThat(focused.general().title()).isEqualTo("La soddisfazione percepita per la propria vita sembra più espressa in una o due aree");
        assertThat(broad.general().title()).isEqualTo("La soddisfazione percepita per la propria vita sembra ampiamente espressa in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non equivale alla SWLS o a una domanda 0–10",
                        "non misura felicità o salute mentale",
                        "non valuta la sicurezza",
                        "professionista qualificato",
                        "112"));
        assertThat(low.general().detail()).contains("non dimostra fallimento, ingratitudine o incapacità");
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void ptsdTestIsLoadedWithTwentyFourOriginalQuestionsAndTraumaInformedLimits() {
        PsychologicalTest test = catalogue.findById("ptsd-adulti");

        assertThat(test.title()).isEqualTo("Disturbo post-traumatico da stress (PTSD)");
        assertThat(test.questions()).hasSize(24).allSatisfy(question ->
                assertThat(question.example()).isNull());
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("intrusioni", "evitamento", "pensieri_umore", "attivazione");
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.responseInstruction()).contains("ultimo mese", "evento o periodo", "frequenza");
        assertThat(test.introductoryText()).contains(
                "senza scriverlo né descriverlo",
                "non accerta se l'esperienza soddisfi i criteri diagnostici di esposizione",
                "puoi interrompere",
                "non devi esporti autonomamente ai ricordi",
                "non diagnostica il PTSD",
                "non valuta durata complessiva, interferenza o sicurezza",
                "112",
                "1522");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze post-traumatiche riferite");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze riferite");
        assertThat(test.references()).extracting(reference -> reference.url()).containsExactly(
                "https://doi.org/10.3390/ijerph19095282",
                "https://pubmed.ncbi.nlm.nih.gov/25266475/",
                "https://www.ptsd.va.gov/professional/treat/essentials/dsm5_ptsd.asp",
                "https://www.who.int/news-room/fact-sheets/detail/post-traumatic-stress-disorder",
                "https://www.nice.org.uk/guidance/ng116/chapter/Recommendations",
                "https://www.who.int/publications-detail-redirect/9789241505406");
    }

    @Test
    void ptsdFocusedProfileKeepsTheoreticalOrderAndDifficultyDirection() {
        TestResult result = analyzeWithAnswersForTest("ptsd-adulti", 1, 5, 1, 1);

        assertThat(result.general().title()).isEqualTo("Le esperienze post-traumatiche esplorate sembrano più presenti in una o due aree");
        assertThat(result.areaResults()).extracting(area -> area.title()).containsExactly(
                "Ricordi e reazioni intrusive",
                "Evitamento di contenuti e richiami",
                "Cambiamenti nei pensieri e nell'umore",
                "Attivazione e reattività");
        assertThat(result.areaResults().get(1).description()).contains("risulta frequente", "Non affrontare autonomamente");
        assertThat(result.areaResults().get(1).percentage()).isEqualTo(100);
        assertThat(result.areaResults().get(0).percentage()).isZero();
    }

    @Test
    void ptsdProfilesFollowEditorialRulesAndAlwaysRetainExposureSafetyAndDifferentialLimits() {
        TestResult low = analyzeWithAnswersForTest("ptsd-adulti", 1, 1, 1, 1);
        TestResult mixed = analyzeWithAnswersForTest("ptsd-adulti", 3, 1, 1, 1);
        TestResult focused = analyzeWithAnswersForTest("ptsd-adulti", 5, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("ptsd-adulti", 5, 5, 5, 1);

        assertThat(low.general().title()).isEqualTo("Le esperienze post-traumatiche esplorate sembrano poco presenti nelle risposte");
        assertThat(mixed.general().title()).isEqualTo("Le esperienze post-traumatiche esplorate sembrano presenti in modo variabile tra le aree");
        assertThat(focused.general().title()).isEqualTo("Le esperienze post-traumatiche esplorate sembrano più presenti in una o due aree");
        assertThat(broad.general().title()).isEqualTo("Le esperienze post-traumatiche esplorate sembrano frequentemente presenti in più aree");
        assertThat(List.of(low, mixed, focused, broad)).allSatisfy(result ->
                assertThat(result.general().detail()).contains(
                        "non stabilisce se l'evento soddisfi i criteri di esposizione",
                        "non diagnostica il PTSD",
                        "non distingue reazioni acute o altre condizioni",
                        "non valuta la sicurezza",
                        "professionista qualificato",
                        "112",
                        "1522"));
        assertThat(low.general().detail()).contains("episodi intensi", "sicurezza");
        assertThat(low.general().description()).contains("dissociazione", "interferenza");
        assertThat(low.percentage()).isZero();
        assertThat(broad.percentage()).isEqualTo(75);
    }

    @Test
    void onlyTheInformationTestsRemainAvailable() {
        assertThat(catalogue.findAll())
                .extracting(PsychologicalTest::id)
                .containsExactly("tratti-autistici-adulti", "tratti-adhd-adulti", "tratti-ossessivo-compulsivi", "autostima", "dipendenza-affettiva", "assertivita", "intelligenza-emotiva", "perfezionismo", "ansia-sociale", "dinamiche-narcisistiche-partner", "ansia-generalizzata", "umore-depresso", "people-pleasing", "sindrome-impostore", "autosabotaggio", "tratti-borderline-adulti", "paura-abbandono", "fomo", "intelligenza-linguistica", "intelligenza-intrapersonale", "resilienza-psicologica", "gelosia-partner", "soddisfazione-vita", "ptsd-adulti");
        assertThatIllegalArgumentException().isThrownBy(() -> catalogue.findById("vera-web-app"));
        assertThatIllegalArgumentException().isThrownBy(() -> catalogue.findById("equilibrio-quotidiano"));
    }

    @Test
    void areaBarMapsTheAnswerScaleFromZeroToOneHundred() {
        TestResult result = analyzeWithAnswersForTest("tratti-adhd-adulti", 3, 1, 1, 1);

        assertThat(result.areaResults().get(0).percentage()).isEqualTo(50);
        assertThat(result.areaResults().stream().skip(1)).allSatisfy(area -> assertThat(area.percentage()).isZero());
    }

    @Test
    void overallBarUsesTheAverageAcrossAllAnswers() {
        TestResult result = analyzeWithAnswersForTest("tratti-adhd-adulti", 5, 3, 1, 3);

        assertThat(result.percentage()).isEqualTo(50);
    }

    private TestResult analyzeWithAnswers(int social, int nonVerbal, int routine, int sensory) {
        return analyzeWithAnswersForTest("tratti-autistici-adulti", social, nonVerbal, routine, sensory);
    }

    private TestResult analyzeWithAnswersForTest(String testId, int first, int second, int third, int fourth) {
        PsychologicalTest test = catalogue.findById(testId);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        int[] areaAnswers = {first, second, third, fourth};
        for (int question = 0; question < test.questions().size(); question++) {
            String areaCode = test.questions().get(question).areaCode();
            int areaIndex = java.util.stream.IntStream.range(0, test.areas().size())
                    .filter(index -> test.areas().get(index).code().equals(areaCode))
                    .findFirst()
                    .orElseThrow();
            attempt.answer(question, areaAnswers[areaIndex]);
        }
        return resultService.analyze(test, attempt);
    }
}
