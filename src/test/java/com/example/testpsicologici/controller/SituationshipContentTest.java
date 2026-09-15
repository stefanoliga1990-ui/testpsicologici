package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.service.TestCatalogue;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class SituationshipContentTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestCatalogue catalogue;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    void introductionAndGuideRenderTheSituationshipContent() throws Exception {
        mockMvc.perform(get("/test/situationship"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Sto vivendo una situationship?")))
                .andExpect(content().string(containsString("ultimi tre mesi")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/situationship\"")))
                .andExpect(content().string(containsString("Development and Validation of the Romantic Experience Uncertainty Scale")));

        mockMvc.perform(get("/approfondimenti/situationship"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Situationship: quando vicinanza e definizione non coincidono")))
                .andExpect(content().string(containsString("L'assenza di un'etichetta non rende automaticamente problematica una relazione")))
                .andExpect(content().string(containsString("href=\"/test/situationship\"")))
                .andExpect(content().string(containsString("Romantic Experience Uncertainty Scale")));
    }

    @Test
    void resultAndPdfRenderAllFiveAreasAndSafetyLimits() throws Exception {
        MockHttpSession session = completedAttempt(5);

        mockMvc.perform(get("/test/situationship/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Definizione condivisa del rapporto")))
                .andExpect(content().string(containsString("Reciprocità del coinvolgimento")))
                .andExpect(content().string(containsString("Intenzioni e direzione futura")))
                .andExpect(content().string(containsString("Accordi e confini")))
                .andExpect(content().string(containsString("Bisogni e impatto")))
                .andExpect(content().string(containsString("non dimostra una situationship")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/situationship\"")));

        MvcResult mvcResult = mockMvc.perform(get("/test/situationship/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();

        try (PDDocument document = PDDocument.load(mvcResult.getResponse().getContentAsByteArray())) {
            String text = new PDFTextStripper().getText(document);
            assertThat(text)
                    .contains("Sto vivendo una situationship?")
                    .contains("Definizione condivisa del rapporto")
                    .contains("Accordi e confini")
                    .contains("finalità esclusivamente informative");
        }
    }

    @Test
    void sitemapContainsTestAndGuideUrls() throws Exception {
        mockMvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/situationship")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/situationship")));
    }

    private MockHttpSession completedAttempt(int answer) {
        PsychologicalTest test = catalogue.findById("situationship");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int question = 0; question < test.questions().size(); question++) {
            attempt.answer(question, answer);
        }
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-situationship", attempt);
        return session;
    }
}
