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
class EmotionalDumpingGuideContentTest {
    private static final String SLUG = "emotional-dumping";

    @Autowired private WebApplicationContext context;
    @Autowired private GuideCatalogue guides;
    @Autowired private GuideEditorialHistoryCatalogue histories;
    @Autowired private TestCatalogue tests;

    @Test
    void publishesGuideWithoutQuestionnaireAndWithEvidenceLimits() throws Exception {
        var guide = guides.findBySlug(SLUG).orElseThrow();
        assertThat(guide.testId()).isEqualTo(SLUG);
        assertThat(guide.sections()).hasSize(6);
        assertThat(guide.references()).hasSize(5);
        assertThat(guide.references().get(0).url()).contains("erickson.it");
        assertThat(histories.forSlug(SLUG).publishedOn().toString()).isEqualTo("2026-10-19");
        assertThat(tests.findSuggestionsByIds(List.of(SLUG))).isEmpty();

        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/approfondimenti/{slug}", SLUG))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Emotional dumping")))
                .andExpect(content().string(containsString("Non è una diagnosi")))
                .andExpect(content().string(containsString("disponibilità")))
                .andExpect(content().string(containsString("non contiene un test")))
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
