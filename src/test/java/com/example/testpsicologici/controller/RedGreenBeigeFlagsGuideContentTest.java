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
class RedGreenBeigeFlagsGuideContentTest {
    private static final String SLUG = "red-green-beige-flags";

    @Autowired private WebApplicationContext context;
    @Autowired private GuideCatalogue guides;
    @Autowired private GuideEditorialHistoryCatalogue histories;
    @Autowired private TestCatalogue tests;

    @Test
    void publishesGuideOnlyWithoutConvertingFlagsIntoScoresOrSafetyVerdicts() throws Exception {
        var guide = guides.findBySlug(SLUG).orElseThrow();
        assertThat(guide.testId()).isEqualTo(SLUG);
        assertThat(guide.sections()).hasSize(6);
        assertThat(guide.references()).hasSize(6);
        assertThat(guide.references()).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("www.istat.it"))
                .anyMatch(url -> url.contains("eige.europa.eu"))
                .anyMatch(url -> url.contains("19485506231209076"));
        assertThat(histories.forSlug(SLUG).publishedOn().toString()).isEqualTo("2026-10-24");
        assertThat(tests.findSuggestionsByIds(List.of(SLUG))).isEmpty();

        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/approfondimenti/{slug}", SLUG))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Red, green e beige flags")))
                .andExpect(content().string(containsString("non contiene questionario")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString("112")))
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
