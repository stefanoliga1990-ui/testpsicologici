package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.service.TestCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class SocialChameleonReadingContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;

    @Test
    void guideAndResultShowVerifiedAffiliateReadings() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        PsychologicalTest test = catalogue.findById("camaleonte-sociale");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) attempt.answer(i, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-camaleonte-sociale", attempt);

        for (MvcResult page : List.of(
                mvc.perform(get("/approfondimenti/camaleonte-sociale")).andExpect(status().isOk()).andReturn(),
                mvc.perform(get("/test/camaleonte-sociale/risultato").session(session))
                        .andExpect(status().isOk()).andReturn())) {
            String html = page.getResponse().getContentAsString();
            assertThat(html).contains(
                    "Come essere assertivi in ogni situazione",
                    "Manuale di assertività. Teoria e pratica delle abilità relazionali",
                    "/dp/882041323X",
                    "/dp/8820414295",
                    "tag=spaziotest-21",
                    "rel=\"noopener noreferrer sponsored\"",
                    "In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.",
                    "recommended-reading-card has-placeholder-cover",
                    "class=\"recommended-reading-cover\"");
        }
    }
}
