package com.example.testpsicologici.controller;

import com.example.testpsicologici.config.ContentDataInitializer;
import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestQuestion;
import com.example.testpsicologici.persistence.TestDefinitionRepository;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.TestCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DisorderTitleAuditTest {

    private static final Map<String, String> TEST_TITLES = Map.of(
            "tratti-ossessivo-compulsivi", "Disturbo ossessivo-compulsivo (DOC): test informativo",
            "ansia-sociale", "Disturbo d'ansia sociale: test informativo",
            "ansia-generalizzata", "Disturbo d'ansia generalizzata: test informativo",
            "umore-depresso", "Depressione: test informativo sui sintomi");

    private static final Map<String, String> GUIDE_CARDS = Map.of(
            "disturbo-ossessivo-compulsivo", "Disturbo ossessivo-compulsivo (DOC)",
            "ansia-sociale", "Disturbo d'ansia sociale",
            "ansia-generalizzata", "Disturbo d'ansia generalizzata",
            "umore-depresso", "Depressione: umore e sintomi depressivi");

    @Autowired private TestCatalogue tests;
    @Autowired private GuideCatalogue guides;
    @Autowired private TestDefinitionRepository definitions;
    @Autowired private ContentDataInitializer initializer;

    @Test
    void testAndGuideLabelsNameTheConditionWithoutClaimingDiagnosis() {
        TEST_TITLES.forEach((id, title) -> {
            PsychologicalTest test = tests.findById(id);
            assertThat(test.title()).isEqualTo(title);
            assertThat(test.seoTitle()).containsIgnoringCase(title.split(":")[0].replace(" (DOC)", ""));
            assertThat(test.introductoryText()).contains("non diagnostico");
        });
        GUIDE_CARDS.forEach((slug, cardTitle) -> {
            var guide = guides.findBySlug(slug).orElseThrow();
            assertThat(guide.cardTitle()).isEqualTo(cardTitle);
            assertThat(guide.title()).startsWith(cardTitle.split(":")[0].replace(" (DOC)", ""));
        });
    }

    @Test
    @Transactional
    void startupUpdatesPersistedTitlesWithoutReseedingItems() {
        Map<String, List<String>> originalQuestions = TEST_TITLES.keySet().stream()
                .collect(java.util.stream.Collectors.toMap(id -> id,
                        id -> tests.findById(id).questions().stream().map(TestQuestion::text).toList()));
        TEST_TITLES.keySet().forEach(id -> {
            var definition = definitions.findById(id).orElseThrow();
            definition.updatePresentation("Titolo precedente", "Titolo precedente | Spazio Test");
            definitions.saveAndFlush(definition);
        });

        initializer.run(new DefaultApplicationArguments(new String[0]));

        TEST_TITLES.forEach((id, title) -> {
            PsychologicalTest test = tests.findById(id);
            assertThat(test.title()).isEqualTo(title);
            assertThat(test.questions().stream().map(TestQuestion::text).toList())
                    .containsExactlyElementsOf(originalQuestions.get(id));
        });
    }
}
