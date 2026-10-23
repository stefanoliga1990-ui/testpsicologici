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
class LavoroEmotivoGuideContentTest {
    private static final String SLUG = "lavoro-emotivo";

    @Autowired private WebApplicationContext context;
    @Autowired private GuideCatalogue guides;
    @Autowired private GuideEditorialHistoryCatalogue histories;
    @Autowired private TestCatalogue tests;

    @Test
    void publishesGuideOnlyWithDistinctContextsAndSourceLimits() throws Exception {
        var guide = guides.findBySlug(SLUG).orElseThrow();
        assertThat(guide.testId()).isEqualTo(SLUG);
        assertThat(guide.sections()).hasSize(6);
        assertThat(guide.references()).hasSize(6);
        assertThat(guide.references()).extracting(reference -> reference.url())
                .anyMatch(url -> url.contains("pubmed.ncbi.nlm.nih.gov/30556535"))
                .anyMatch(url -> url.contains("rivisteweb.it/doi/10.1423/78749"))
                .anyMatch(url -> url.contains("recyt.fecyt.es/index.php/res/article/view/65604"));
        assertThat(histories.forSlug(SLUG).publishedOn().toString()).isEqualTo("2026-10-23");
        assertThat(tests.findSuggestionsByIds(List.of(SLUG))).isEmpty();

        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/approfondimenti/{slug}", SLUG))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Lavoro emotivo")))
                .andExpect(content().string(containsString("Lavoro retribuito")))
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
