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
        assertThat(test.version()).isEqualTo("2.3");
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

        assertThat(result.general().title()).isEqualTo("Poche esperienze ricorrenti");
        assertThat(result.percentage()).isZero();
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
        assertThat(test.version()).isEqualTo("2.3");
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
        assertThat(test.version()).isEqualTo("1.4");
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
    void selfEsteemTestIsLoadedWithTwentyFourQuestionsAndSpecificLabels() {
        PsychologicalTest test = catalogue.findById("autostima");

        assertThat(test.questions()).hasSize(24);
        assertThat(test.areas()).hasSize(4);
        assertThat(test.areas()).allSatisfy(area ->
                assertThat(test.questions()).filteredOn(question -> question.areaCode().equals(area.code())).hasSize(6));
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.overallMetricLabel()).isEqualTo("Difficoltà complessive relative all'autostima");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle difficoltà");
    }

    @Test
    void selfEsteemFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("autostima", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Un'area mette più alla prova la tua autostima");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Valore personale e autoaccettazione");
        assertThat(result.areaResults().get(0).description()).contains("dignità personale");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void selfEsteemLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("autostima", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("autostima", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Un senso di valore generalmente solido");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Un'autostima spesso sotto pressione");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche esplorate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche");
    }

    @Test
    void emotionalDependenceFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("dipendenza-affettiva", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Una dinamica relazionale richiede più attenzione");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Paura della separazione e bisogno di rassicurazione");
        assertThat(result.areaResults().get(0).description()).contains("paura frequente della separazione");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void emotionalDependenceLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("dipendenza-affettiva", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("dipendenza-affettiva", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Legame e autonomia generalmente in equilibrio");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("La relazione occupa uno spazio molto vincolante");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva dei comportamenti assertivi");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza dei comportamenti assertivi");
    }

    @Test
    void assertivenessFocusedProfileShowsTheStrongestAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("assertivita", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Una risorsa assertiva emerge con chiarezza");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Espressione di opinioni, bisogni ed emozioni");
        assertThat(result.areaResults().get(0).description()).contains("buona capacità di rendere visibili");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void assertivenessLowAndBroadProfilesFollowThePositiveScoringDirection() {
        TestResult low = analyzeWithAnswersForTest("assertivita", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("assertivita", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("La tua voce trova ancora poco spazio");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Competenze assertive diffuse");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle competenze emotive esplorate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle competenze emotive");
    }

    @Test
    void emotionalIntelligenceFocusedProfileShowsTheStrongestAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("intelligenza-emotiva", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Alcune competenze emotive sono già solide");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Percezione e consapevolezza emotiva");
        assertThat(result.areaResults().get(0).description()).contains("buona attenzione ai segnali corporei");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void emotionalIntelligenceLowAndBroadProfilesFollowThePositiveScoringDirection() {
        TestResult low = analyzeWithAnswersForTest("intelligenza-emotiva", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("intelligenza-emotiva", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Competenze emotive ancora poco accessibili");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Competenze emotive diffuse e flessibili");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche perfezionistiche");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche perfezionistiche");
    }

    @Test
    void perfectionismFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("perfezionismo", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Un'area concentra la pressione perfezionistica");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Standard elevati e valore legato ai risultati");
        assertThat(result.areaResults().get(0).description()).contains("standard frequentemente molto elevati");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void perfectionismLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("perfezionismo", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("perfezionismo", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Poche dinamiche perfezionistiche ricorrenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Una pressione perfezionistica diffusa");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze di ansia sociale");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void socialAnxietyFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("ansia-sociale", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Un ambito sociale emerge con chiarezza");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Paura del giudizio e dell'imbarazzo");
        assertThat(result.areaResults().get(0).description()).contains("paura frequente di essere giudicato");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void socialAnxietyLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("ansia-sociale", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("ansia-sociale", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Poche difficoltà sociali ricorrenti");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Ansia sociale presente in più ambiti");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.introductoryText()).contains("non può stabilire", "valutazione clinica diretta", "112", "1522");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche osservate");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche osservate");
    }

    @Test
    void perceivedNarcissisticDynamicsFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("dinamiche-narcisistiche-partner", 1, 1, 1, 5);

        assertThat(result.general().title()).isEqualTo("Un'area relazionale richiede attenzione");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(3).title()).isEqualTo("Confini, controllo e impatto sulla relazione");
        assertThat(result.areaResults().get(3).description()).contains("indipendentemente da qualsiasi etichetta diagnostica");
        assertThat(result.areaResults().get(3).percentage()).isEqualTo(100);
    }

    @Test
    void perceivedNarcissisticDynamicsLowAndBroadProfilesAvoidDiagnosingThePartner() {
        TestResult low = analyzeWithAnswersForTest("dinamiche-narcisistiche-partner", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("dinamiche-narcisistiche-partner", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Poche dinamiche relazionali di questo tipo");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Dinamiche problematiche presenti in più aree");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.introductoryText()).contains("ultimi sei mesi", "condizioni mediche", "Non è uno strumento diagnostico");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze di ansia");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void generalizedAnxietyFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("ansia-generalizzata", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Un'area concentra maggiormente la tensione");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(0).title()).isEqualTo("Preoccupazione diffusa e difficoltà di controllo");
        assertThat(result.areaResults().get(0).description()).contains("preoccupazione frequente");
        assertThat(result.areaResults().get(0).percentage()).isEqualTo(100);
    }

    @Test
    void generalizedAnxietyLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("ansia-generalizzata", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("ansia-generalizzata", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Preoccupazione generalmente circoscritta");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Ansia frequente in più aspetti della vita");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.introductoryText()).contains("ultime due settimane", "non valuta il rischio suicidario", "112", "Pronto Soccorso");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze legate all'umore");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void depressedMoodFocusedProfileShowsTheMostRelevantAreaFirst() {
        TestResult result = analyzeWithAnswersForTest("umore-depresso", 5, 1, 1, 1);

        assertThat(result.general().title()).isEqualTo("Un'area del benessere emotivo emerge con chiarezza");
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

        assertThat(low.general().title()).isEqualTo("Umore generalmente preservato");
        assertThat(low.general().detail()).contains("non valuta il rischio suicidario", "Pronto Soccorso");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Umore depresso presente in più aree");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.introductoryText()).contains("non una diagnosi", "differenze di potere", "strategia protettiva");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle dinamiche di compiacenza");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle dinamiche");
    }

    @Test
    void peoplePleasingFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("people-pleasing", 1, 1, 1, 5);

        assertThat(result.general().title()).isEqualTo("Un meccanismo di compiacenza emerge con chiarezza");
        assertThat(result.areaResults()).hasSize(4);
        assertThat(result.areaResults().get(3).title()).isEqualTo("Sovraresponsabilità e trascuratezza di sé");
        assertThat(result.areaResults().get(3).description()).contains("sovraresponsabilità frequente");
        assertThat(result.areaResults().get(3).percentage()).isEqualTo(100);
    }

    @Test
    void peoplePleasingLowAndBroadProfilesFollowTheGeneralRules() {
        TestResult low = analyzeWithAnswersForTest("people-pleasing", 1, 1, 1, 1);
        TestResult broad = analyzeWithAnswersForTest("people-pleasing", 5, 5, 5, 5);

        assertThat(low.general().title()).isEqualTo("Disponibilità e bisogni personali in buon equilibrio");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("I bisogni altrui occupano spesso il primo posto");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.introductoryText()).contains("fenomeno dell'impostore", "non è una diagnosi", "discriminazione", "non misura la tua competenza reale");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva delle esperienze di impostore");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza delle esperienze");
    }

    @Test
    void impostorPhenomenonFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("sindrome-impostore", 1, 5, 1, 1);

        assertThat(result.general().title()).isEqualTo("Un meccanismo dell'impostore emerge con chiarezza");
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

        assertThat(low.general().title()).isEqualTo("Successi e competenze generalmente riconosciuti");
        assertThat(low.general().detail()).contains("ruolo nuovo");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Il vissuto dell'impostore è presente in più aree");
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
        assertThat(test.version()).isEqualTo("1.3");
        assertThat(test.introductoryText()).contains("non una diagnosi", "Non implica", "può essere adattivo", "difficoltà esecutive", "non moralistica");
        assertThat(test.overallMetricLabel()).isEqualTo("Frequenza complessiva degli ostacoli autoalimentati");
        assertThat(test.areaMetricLabel()).isEqualTo("Frequenza degli ostacoli");
    }

    @Test
    void selfSabotageFocusedProfileKeepsTheoreticalAreaOrder() {
        TestResult result = analyzeWithAnswersForTest("autosabotaggio", 1, 5, 1, 1);

        assertThat(result.general().title()).isEqualTo("Un meccanismo di autosabotaggio emerge con chiarezza");
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

        assertThat(low.general().title()).isEqualTo("Scelte generalmente coerenti con i tuoi obiettivi");
        assertThat(low.general().detail()).contains("abbandono di obiettivi non più realistici");
        assertThat(low.percentage()).isZero();
        assertThat(broad.general().title()).isEqualTo("Più meccanismi ostacolano i tuoi obiettivi");
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
        assertThat(test.version()).isEqualTo("1.0");
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

        assertThat(result.general().title()).isEqualTo("Una o due aree emergono con maggiore frequenza");
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

        assertThat(low.general().title()).isEqualTo("Esperienze poco frequenti nelle quattro aree");
        assertThat(mixed.general().title()).isEqualTo("Un andamento che varia tra aree e contesti");
        assertThat(focused.general().title()).isEqualTo("Una o due aree emergono con maggiore frequenza");
        assertThat(broad.general().title()).isEqualTo("Esperienze frequenti distribuite in più aree");
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
    void onlyTheInformationTestsRemainAvailable() {
        assertThat(catalogue.findAll())
                .extracting(PsychologicalTest::id)
                .containsExactly("tratti-autistici-adulti", "tratti-adhd-adulti", "tratti-ossessivo-compulsivi", "autostima", "dipendenza-affettiva", "assertivita", "intelligenza-emotiva", "perfezionismo", "ansia-sociale", "dinamiche-narcisistiche-partner", "ansia-generalizzata", "umore-depresso", "people-pleasing", "sindrome-impostore", "autosabotaggio", "tratti-borderline-adulti");
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
