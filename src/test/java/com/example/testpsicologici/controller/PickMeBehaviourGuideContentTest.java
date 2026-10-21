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
class PickMeBehaviourGuideContentTest {
    private static final String SLUG = "pick-me-behaviour";

    @Autowired private WebApplicationContext context;
    @Autowired private GuideCatalogue guides;
    @Autowired private GuideEditorialHistoryCatalogue histories;
    @Autowired private TestCatalogue tests;

    @Test
    void publishesGuideWithoutQuestionnaireAndWithEvidenceLimits() throws Exception {
        var guide = guides.findBySlug(SLUG).orElseThrow();
        assertThat(guide.testId()).isEqualTo(SLUG);
        assertThat(guide.sections()).hasSize(6);
        assertThat(guide.references()).hasSize(3);
        assertThat(guide.references()).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("pmc.ncbi.nlm.nih.gov/articles/PMC11230945"))
                .anyMatch(url -> url.contains("10.1177/08861099261451872"));
        assertThat(histories.forSlug(SLUG).publishedOn().toString()).isEqualTo("2026-10-21");
        assertThat(tests.findSuggestionsByIds(List.of(SLUG))).isEmpty();

        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/approfondimenti/{slug}", SLUG))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pick-me behaviour")))
                .andExpect(content().string(containsString("autosorveglianza")))
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
