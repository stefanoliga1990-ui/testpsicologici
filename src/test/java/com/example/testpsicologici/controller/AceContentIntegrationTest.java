package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.TestCatalogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class AceContentIntegrationTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private GuideCatalogue guides;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = webAppContextSetup(context).build();
    }

    @Test
    void originalBlueprintIsBalancedInterleavedAndHasSpecificSources() {
        PsychologicalTest test = catalogue.findById("esperienze-avverse-infanzia");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.scoringModel()).isEqualTo("ACE_EXPOSURE");
        assertThat(test.answerScale()).isEqualTo("ACE_PRESENCE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.questions()).hasSize(24);
        assertThat(test.questions()).extracting(question -> question.indicatorCode()).doesNotHaveDuplicates();
        assertThat(test.questions()).extracting(question -> question.areaCode())
                .containsExactly("A", "B", "C", "D", "A", "B", "C", "D", "A", "B", "C", "D",
                        "A", "B", "C", "D", "A", "B", "C", "D", "A", "B", "C", "D");
        assertThat(test.references()).hasSize(7).allSatisfy(reference ->
                assertThat(reference.contribution()).hasSizeGreaterThan(40));
        assertThat(guides.findByTestId(test.id())).isPresent();
    }

    @Test
    void introductionGuideSitemapAndPrivacyRulesAreExposed() throws Exception {
        mvc.perform(get("/test/esperienze-avverse-infanzia"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("violenza, contatti sessuali")))
                .andExpect(content().string(containsString("non sono state svolte interviste cognitive")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/esperienze-avverse-infanzia\"")));
        mvc.perform(get("/approfondimenti/esperienze-avverse-infanzia"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("senza ridurre la storia a un numero")))
                .andExpect(content().string(containsString("non sono state effettuate interviste cognitive")))
                .andExpect(content().string(containsString("non è l'ACE-IQ")))
                .andExpect(content().string(containsString("href=\"/test/esperienze-avverse-infanzia\"")));
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/esperienze-avverse-infanzia")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/esperienze-avverse-infanzia")));
    }

    @Test
    void resultIsDescriptiveAndPdfRemainsUnavailable() throws Exception {
        PsychologicalTest test = catalogue.findById("esperienze-avverse-infanzia");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, 2);
        attempt.answer(0, 1);
        attempt.answer(1, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-esperienze-avverse-infanzia", attempt);

        mvc.perform(get("/test/esperienze-avverse-infanzia/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(header().string("Cache-Control", containsString("no-store")))
                .andExpect(content().string(containsString("Esperienze avverse riferite in un ambito")))
                .andExpect(content().string(containsString("non sono state verificate con interviste cognitive")))
                .andExpect(content().string(containsString("il quadro è parziale")))
                .andExpect(content().string(not(containsString("/risultato/pdf"))));
        mvc.perform(get("/test/esperienze-avverse-infanzia/risultato/pdf").session(session))
                .andExpect(status().isNotFound());
    }
}
