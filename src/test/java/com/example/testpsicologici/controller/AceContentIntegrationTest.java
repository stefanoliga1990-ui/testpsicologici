package com.example.testpsicologici.controller;

import com.example.testpsicologici.persistence.TestDefinitionRepository;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.TestCatalogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class AceContentIntegrationTest {

    private static final String TEST_ID = "esperienze-avverse-infanzia";

    @Autowired private WebApplicationContext context;
    @Autowired private TestDefinitionRepository repository;
    @Autowired private TestCatalogue catalogue;
    @Autowired private GuideCatalogue guides;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = webAppContextSetup(context).build();
    }

    @Test
    void questionnaireIsRetainedButInactive() {
        var definition = repository.findById(TEST_ID).orElseThrow();
        assertThat(definition.getVersion()).isEqualTo("1.1");
        assertThat(definition.isActive()).isFalse();
        assertThat(catalogue.findAll()).extracting(test -> test.id()).doesNotContain(TEST_ID);
        assertThatThrownBy(() -> catalogue.findById(TEST_ID))
                .isInstanceOf(IllegalArgumentException.class);
        assertThat(guides.findBySlug(TEST_ID)).isPresent();
    }

    @Test
    void directQuestionnaireRoutesAreUnavailable() throws Exception {
        mvc.perform(get("/test/" + TEST_ID)).andExpect(status().isNotFound());
        mvc.perform(post("/test/" + TEST_ID + "/inizia")).andExpect(status().isNotFound());
        mvc.perform(get("/test/" + TEST_ID + "/domanda/1")).andExpect(status().isNotFound());
        mvc.perform(get("/test/" + TEST_ID + "/risultato")).andExpect(status().isNotFound());
        mvc.perform(get("/test/" + TEST_ID + "/risultato/pdf")).andExpect(status().isNotFound());
    }

    @Test
    void guideRemainsAvailableWithoutQuestionnaireLinks() throws Exception {
        mvc.perform(get("/approfondimenti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Guide autonome")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/" + TEST_ID + "\"")));
        mvc.perform(get("/approfondimenti/" + TEST_ID))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Adverse Childhood Experiences (ACEs)")))
                .andExpect(content().string(not(containsString("href=\"/test/" + TEST_ID + "\""))));
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/approfondimenti/" + TEST_ID)))
                .andExpect(content().string(not(containsString("http://localhost/test/" + TEST_ID))));
    }
}
