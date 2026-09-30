package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.GuideSection;
import com.example.testpsicologici.model.InformationGuide;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.GuideEditorialHistoryCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class GuideEnrichmentAdsense01Test {

    private static final Map<String, String> EXPECTED_TITLES = Map.of(
            "alessitimia", "Da una sensazione a parole possibili, senza forzare un nome",
            "gelosia-partner", "Un messaggio senza risposta: dal dubbio alla scelta",
            "stili-attaccamento", "Un ritardo nella risposta non definisce uno stile");

    @Autowired
    private GuideCatalogue guides;

    @Autowired
    private GuideEditorialHistoryCatalogue history;

    @Autowired
    private WebApplicationContext context;

    @Test
    void enrichedGuidesRenderExamplesQuestionsSourcesAndRevisionDate() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        for (var expected : EXPECTED_TITLES.entrySet()) {
            String slug = expected.getKey();
            InformationGuide guide = guides.findBySlug(slug).orElseThrow();
            GuideSection section = guide.sections().stream()
                    .filter(candidate -> candidate.title().equals(expected.getValue()))
                    .findFirst().orElseThrow();

            assertThat(section.eyebrow()).isEqualTo("Esempio guidato");
            assertThat(section.paragraphs()).hasSizeGreaterThanOrEqualTo(2);
            assertThat(section.points()).hasSize(3);
            assertThat(guide.references()).isNotEmpty();
            assertThat(history.forSlug(slug).revisedOn()).isEqualTo(LocalDate.parse("2026-09-30"));

            String response = mvc.perform(get("/approfondimenti/{slug}", slug))
                    .andExpect(status().isOk())
                    .andExpect(content().string(containsString("<h2>" + expected.getValue() + "</h2>")))
                    .andExpect(content().string(containsString(section.paragraphs().get(0))))
                    .andExpect(content().string(containsString(section.points().get(0))))
                    .andExpect(content().string(containsString("dateModified")))
                    .andExpect(content().string(containsString("2026-09-30")))
                    .andExpect(content().string(containsString("Fonti consultate")))
                    .andReturn().getResponse().getContentAsString();
            // React receives the same guide sections and date in the serialized page data.
            assertThat(response).contains("react-page-data", "sections", "revisedOn");
        }
    }
}
