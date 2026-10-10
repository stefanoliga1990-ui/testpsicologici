package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.GuideEditorialHistoryCatalogue;
import com.example.testpsicologici.service.TestCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class TraumaBondingGuideContentTest {
    @Autowired private WebApplicationContext context;
    @Autowired private GuideCatalogue guides;
    @Autowired private GuideEditorialHistoryCatalogue histories;
    @Autowired private TestCatalogue tests;

    @Test
    void publishesOnlyAnAutonomousSafetyConsciousGuide() throws Exception {
        var guide = guides.findBySlug("trauma-bonding").orElseThrow();
        assertThat(guide.testId()).isEqualTo("trauma-bonding");
        assertThat(guide.sections()).hasSize(6);
        assertThat(guide.references()).hasSizeGreaterThanOrEqualTo(3);
        assertThat(histories.forSlug("trauma-bonding").publishedOn().toString()).isEqualTo("2026-10-10");
        assertThat(tests.findSuggestionsByIds(java.util.List.of("trauma-bonding"))).isEmpty();

        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/approfondimenti/trauma-bonding"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Trauma bonding: comprendere il legame")))
                .andExpect(content().string(containsString("Il contributo di Spazio Test")))
                .andExpect(content().string(containsString("10.3389/fpsyg.2021.769584")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(not(containsString("class=\"guide-test-cta\""))))
                .andExpect(content().string(not(containsString("/test/trauma-bonding"))));
        mvc.perform(get("/approfondimenti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Guide autonome")))
                .andExpect(content().string(containsString("/approfondimenti/trauma-bonding")));
        mvc.perform(get("/test/trauma-bonding")).andExpect(status().isNotFound());
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/approfondimenti/trauma-bonding")))
                .andExpect(content().string(not(containsString("http://localhost/test/trauma-bonding"))));
    }
}
