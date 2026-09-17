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
class RelationalCodependencyContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    void testAndGuideRenderDelimitationsAndSources() throws Exception {
        mockMvc.perform(get("/test/codipendenza-relazionale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Quanto mi riconosco in dinamiche di codipendenza?")))
                .andExpect(content().string(containsString("ultimi tre mesi")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/codipendenza-relazionale\"")))
                .andExpect(content().string(containsString("Co-Dependency Revisited")));

        mockMvc.perform(get("/approfondimenti/codipendenza-relazionale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("un concetto discusso da leggere con cautela")))
                .andExpect(content().string(containsString("non una diagnosi autonoma")))
                .andExpect(content().string(containsString("href=\"/test/codipendenza-relazionale\"")));
    }

    @Test
    void resultPdfAndSitemapContainTheCompleteContent() throws Exception {
        MockHttpSession session = completedAttempt(5);
        mockMvc.perform(get("/test/codipendenza-relazionale/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Focalizzazione sull'altra persona")))
                .andExpect(content().string(containsString("Sacrificio di bisogni e scelte")))
                .andExpect(content().string(containsString("Responsabilità e controllo relazionale")))
                .andExpect(content().string(containsString("Spazio per identità ed emozioni")))
                .andExpect(content().string(containsString("non dimostra codipendenza")));

        MvcResult pdf = mockMvc.perform(get("/test/codipendenza-relazionale/risultato/pdf").session(session))
                .andExpect(status().isOk()).andExpect(content().contentType("application/pdf")).andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document))
                    .contains("Quanto mi riconosco in dinamiche di codipendenza?")
                    .contains("Focalizzazione sull'altra persona")
                    .contains("finalità esclusivamente informative");
        }

        mockMvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/codipendenza-relazionale")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/codipendenza-relazionale")));
    }

    @Test
    void blueprintIsBalancedInterleavedAndOriginal() {
        PsychologicalTest test = catalogue.findById("codipendenza-relazionale");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.questions()).hasSize(20).allSatisfy(question -> assertThat(question.example()).isNull());
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("altro", "sacrificio", "controllo", "spazio");
        test.areas().forEach(area -> assertThat(test.questions())
                .filteredOn(question -> question.areaCode().equals(area.code())).hasSize(5));
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "altro", "sacrificio", "controllo", "spazio",
                "altro", "sacrificio", "controllo", "spazio",
                "altro", "sacrificio", "controllo", "spazio",
                "altro", "sacrificio", "controllo", "spazio",
                "altro", "sacrificio", "controllo", "spazio");
    }

    private MockHttpSession completedAttempt(int answer) {
        PsychologicalTest test = catalogue.findById("codipendenza-relazionale");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int question = 0; question < test.questions().size(); question++) attempt.answer(question, answer);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-codipendenza-relazionale", attempt);
        return session;
    }
}
