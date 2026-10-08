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
class StonewallingGuideContentTest {
    @Autowired private WebApplicationContext context;
    @Autowired private GuideCatalogue guides;
    @Autowired private GuideEditorialHistoryCatalogue histories;
    @Autowired private TestCatalogue tests;

    @Test
    void onlyAnAutonomousGuideIsPublished() throws Exception {
        var guide = guides.findBySlug("stonewalling").orElseThrow();
        assertThat(guide.testId()).isEqualTo("stonewalling");
        assertThat(guide.sections()).hasSize(6);
        assertThat(guide.references()).hasSizeGreaterThanOrEqualTo(3);
        assertThat(histories.forSlug("stonewalling").publishedOn().toString()).isEqualTo("2026-10-08");
        assertThat(tests.findSuggestionsByIds(java.util.List.of("stonewalling"))).isEmpty();

        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/approfondimenti/stonewalling"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Stonewalling: quando il dialogo si interrompe")))
                .andExpect(content().string(containsString("Il contributo di Spazio Test")))
                .andExpect(content().string(containsString("10.1016/j.sbspro.2014.04.410")))
                .andExpect(content().string(not(containsString("class=\"guide-test-cta\""))))
                .andExpect(content().string(not(containsString("/test/stonewalling"))));
        mvc.perform(get("/approfondimenti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Guide autonome")))
                .andExpect(content().string(containsString("/approfondimenti/stonewalling")));
        mvc.perform(get("/test/stonewalling")).andExpect(status().isNotFound());
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/approfondimenti/stonewalling")))
                .andExpect(content().string(not(containsString("http://localhost/test/stonewalling"))));
    }
}
