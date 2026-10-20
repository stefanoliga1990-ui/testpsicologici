package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.GuideEditorialHistoryCatalogue;
import com.example.testpsicologici.service.TestCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class MainCharacterSyndromeGuideContentTest {
    private static final String SLUG = "main-character-syndrome";

    @Autowired private WebApplicationContext context;
    @Autowired private GuideCatalogue guides;
    @Autowired private GuideEditorialHistoryCatalogue histories;
    @Autowired private TestCatalogue tests;

    @Test
    void publishesOnlyGuideWithResearchAndClinicalLimits() throws Exception {
        var guide = guides.findBySlug(SLUG).orElseThrow();
        assertThat(guide.testId()).isEqualTo(SLUG);
        assertThat(guide.sections()).hasSize(6);
        assertThat(guide.references()).hasSize(4);
        assertThat(guide.references()).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("ora.uniurb.it"))
                .anyMatch(url -> url.contains("pubmed.ncbi.nlm.nih.gov/42696901"));
        assertThat(histories.forSlug(SLUG).publishedOn().toString()).isEqualTo("2026-10-20");
        assertThat(tests.findSuggestionsByIds(List.of(SLUG))).isEmpty();

        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/approfondimenti/{slug}", SLUG))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Main character syndrome")))
                .andExpect(content().string(containsString("non è una diagnosi")))
                .andExpect(content().string(containsString("campioni libanesi")))
                .andExpect(content().string(containsString("non contiene questionario")))
                .andExpect(content().string(not(containsString("class=\"guide-test-cta\""))))
                .andExpect(content().string(not(containsString("/test/" + SLUG))));
        mvc.perform(get("/approfondimenti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("/approfondimenti/" + SLUG)));
        mvc.perform(get("/test/{id}", SLUG)).andExpect(status().isNotFound());
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + SLUG)))
                .andExpect(content().string(not(containsString("http://localhost/test/" + SLUG))));
    }
}
