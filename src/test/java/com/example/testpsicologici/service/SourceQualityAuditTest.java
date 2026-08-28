package com.example.testpsicologici.service;

import com.example.testpsicologici.model.GuideReference;
import com.example.testpsicologici.model.InformationGuide;
import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SourceQualityAuditTest {

    private static final Map<String, String> ITALIAN_OR_EUROPEAN_EVIDENCE = Map.ofEntries(
            Map.entry("tratti-autistici-adulti", "www.iss.it"),
            Map.entry("tratti-adhd-adulti", "39942433"),
            Map.entry("tratti-ossessivo-compulsivi", "18701254"),
            Map.entry("autostima", "25580614"),
            Map.entry("dipendenza-affettiva", "www.salute.gov.it"),
            Map.entry("assertivita", "21721362"),
            Map.entry("intelligenza-emotiva", "23536991"),
            Map.entry("perfezionismo", "33835908"),
            Map.entry("ansia-sociale", "italian-social-phobia-inventory"),
            Map.entry("dinamiche-narcisistiche-partner", "www.istat.it"),
            Map.entry("ansia-generalizzata", "37149049"),
            Map.entry("umore-depresso", "www.iss.it"),
            Map.entry("people-pleasing", "30518269"),
            Map.entry("sindrome-impostore", "s12144-025-07865-1"),
            Map.entry("autosabotaggio", "27630595"),
            Map.entry("tratti-borderline-adulti", "28604275"),
            Map.entry("paura-abbandono", "36407970"),
            Map.entry("fomo", "31704432"),
            Map.entry("intelligenza-linguistica", "www.inapp.gov.it"),
            Map.entry("intelligenza-intrapersonale", "26379571"),
            Map.entry("resilienza-psicologica", "27031088"),
            Map.entry("gelosia-partner", "fpsyg.2022.1013584"),
            Map.entry("soddisfazione-vita", "flore.unifi.it"),
            Map.entry("ptsd-adulti", "ijerph19095282"),
            Map.entry("stili-attaccamento", "25074302"),
            Map.entry("limerenza", "salute.gov.it"),
            Map.entry("parentificazione", "fpsyt.2022.1079608"),
            Map.entry("gaslighting", "20112084.6306"),
            Map.entry("love-bombing", "www.istat.it")
    );

    @Autowired
    private TestCatalogue testCatalogue;

    @Autowired
    private GuideCatalogue guideCatalogue;

    @Test
    void everyQuestionnaireHasTraceableSourcesAndDocumentedContextualEvidence() {
        assertThat(testCatalogue.findAll()).hasSize(29).allSatisfy(test -> {
            assertThat(test.references()).hasSizeGreaterThanOrEqualTo(3);
            assertThat(test.references()).extracting(TestReference::url)
                    .allMatch(url -> url.startsWith("https://"))
                    .doesNotHaveDuplicates()
                    .anyMatch(url -> url.contains(ITALIAN_OR_EUROPEAN_EVIDENCE.get(test.id())));
            assertThat(test.references()).extracting(TestReference::contribution)
                    .allMatch(contribution -> contribution != null && contribution.length() >= 40)
                    .noneMatch(contribution -> contribution.startsWith("Fonte consultata per definire"));
        });
    }

    @Test
    void everyGuideHasTraceableSpecificSourcesAndNoThirdPartyScientificCopies() {
        assertThat(guideCatalogue.findAll()).hasSize(29).allSatisfy(guide -> {
            assertThat(guide.references()).hasSizeGreaterThanOrEqualTo(3);
            assertThat(guide.references()).extracting(GuideReference::url)
                    .allMatch(url -> url.startsWith("https://"))
                    .doesNotHaveDuplicates()
                    .noneMatch(url -> url.contains("psychologytoday.com"));
            assertThat(guide.references()).extracting(GuideReference::contribution)
                    .allMatch(contribution -> contribution != null && contribution.length() >= 40);
        });
    }

    @Test
    void everyQuestionnaireHasItsOwnGuideAndContextLimitIsVisibleWhereEvidenceIsSparse() {
        assertThat(guideCatalogue.findAll()).extracting(InformationGuide::testId)
                .containsExactlyInAnyOrderElementsOf(testCatalogue.findAll().stream()
                        .map(PsychologicalTest::id)
                        .toList());

        InformationGuide peoplePleasing = guideCatalogue.findBySlug("people-pleasing").orElseThrow();
        assertThat(peoplePleasing.sections().stream()
                .flatMap(section -> section.paragraphs().stream()))
                .anyMatch(paragraph -> paragraph.contains("validazione italiana consolidata"));
    }
}
