package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.InformationGuide;
import com.example.testpsicologici.service.GuideCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class GuideOriginalContributionTest {

    private static final List<String> SLUGS = List.of(
            "ansia-generalizzata", "ansia-sociale", "disturbo-ossessivo-compulsivo",
            "umore-depresso", "ptsd-adulti", "disturbo-borderline-personalita",
            "disturbo-evitante-personalita", "adhd-adulti", "autismo-adulti",
            "intelligenza-linguistica");

    private static final List<String> RELATIONSHIP_SLUGS = List.of(
            "stili-attaccamento", "disponibilita-emotiva", "parentificazione",
            "paura-abbandono", "limerenza", "dipendenza-affettiva",
            "compatibilita-coppia", "gelosia-partner", "dinamiche-narcisistiche-coppia",
            "situationship", "codipendenza-relazionale");

    private static final List<String> AMBIGUOUS_DYNAMICS_SLUGS = List.of(
            "relazione-dannosa-benessere", "gaslighting", "love-bombing",
            "breadcrumbing", "orbiting", "hoovering", "invalidazione-emotiva",
            "triangolazione-relazionale");

    @Autowired
    private GuideCatalogue guides;

    @Autowired
    private WebApplicationContext context;

    @Test
    void selectedGuidesHaveDistinctContributionsVisibleInFallbackAndReactData() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        List<String> contributions = SLUGS.stream()
                .map(slug -> guides.findBySlug(slug).orElseThrow())
                .map(InformationGuide::originalContribution)
                .toList();

        assertThat(contributions).allSatisfy(text -> assertThat(text).isNotBlank());
        assertThat(contributions).doesNotHaveDuplicates();

        for (String slug : SLUGS) {
            String contribution = guides.findBySlug(slug).orElseThrow().originalContribution();
            mvc.perform(get("/approfondimenti/{slug}", slug))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("class=\"guide-section guide-original-contribution\"")))
                    .andExpect(content().string(containsString("Il contributo di Spazio Test")))
                    .andExpect(content().string(containsString(contribution)))
                    .andExpect(content().string(containsString("originalContribution")));
        }
    }

    @Test
    void testIntroductionsExplainTheOriginalQuestions() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        for (String slug : SLUGS) {
            InformationGuide guide = guides.findBySlug(slug).orElseThrow();
            mvc.perform(get("/test/{id}", guide.testId()))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("Le domande sono originali")));
        }
    }

    @Test
    void relationshipGuidesHaveDistinctContributionsInFallbackAndReactData() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        List<String> contributions = RELATIONSHIP_SLUGS.stream()
                .map(slug -> guides.findBySlug(slug).orElseThrow().originalContribution())
                .toList();

        assertThat(contributions).allSatisfy(text -> assertThat(text).isNotBlank());
        assertThat(contributions).doesNotHaveDuplicates();

        for (String slug : RELATIONSHIP_SLUGS) {
            String contribution = guides.findBySlug(slug).orElseThrow().originalContribution();
            mvc.perform(get("/approfondimenti/{slug}", slug))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("class=\"guide-section guide-original-contribution\"")))
                    .andExpect(content().string(containsString("Il contributo di Spazio Test")))
                    .andExpect(content().string(containsString(contribution)))
                    .andExpect(content().string(containsString("originalContribution")))
                    .andExpect(content().string(containsString("Fonti consultate")));
        }
    }

    @Test
    void ambiguousDynamicsGuidesHaveDistinctContributionsInFallbackAndReactData() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        List<String> contributions = AMBIGUOUS_DYNAMICS_SLUGS.stream()
                .map(slug -> guides.findBySlug(slug).orElseThrow().originalContribution())
                .toList();

        assertThat(contributions).allSatisfy(text -> assertThat(text).isNotBlank());
        assertThat(contributions).doesNotHaveDuplicates();

        for (String slug : AMBIGUOUS_DYNAMICS_SLUGS) {
            String contribution = guides.findBySlug(slug).orElseThrow().originalContribution();
            mvc.perform(get("/approfondimenti/{slug}", slug))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("class=\"guide-section guide-original-contribution\"")))
                    .andExpect(content().string(containsString("Il contributo di Spazio Test")))
                    .andExpect(content().string(containsString(contribution)))
                    .andExpect(content().string(containsString("originalContribution")))
                    .andExpect(content().string(containsString("Fonti consultate")));
        }
    }
}
