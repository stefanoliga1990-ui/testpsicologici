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

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class PageRenderingTest {

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
    void homeRendersSearchAndFullyClickableTestCards() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("id=\"test-search-input\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("data-test-card")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/tratti-autistici-adulti\"")));
    }

    @Test
    void resultRendersOverallBarFromTheGeneralAverage() throws Exception {
        String testId = "tratti-adhd-adulti";
        PsychologicalTest test = catalogue.findById(testId);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int question = 0; question < test.questions().size(); question++) {
            attempt.answer(question, 3);
        }
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + testId, attempt);

        mockMvc.perform(get("/test/{testId}/risultato", testId).session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "class=\"overall-presence-track\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("aria-valuenow=\"50\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/tratti-adhd-adulti/risultato/pdf\"")));
    }

    @Test
    void completedResultCanBeDownloadedAsAReadablePdf() throws Exception {
        String testId = "tratti-adhd-adulti";
        MockHttpSession session = completedAttempt(testId, 3);

        MvcResult mvcResult = mockMvc.perform(get("/test/{testId}/risultato/pdf", testId).session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString(
                        "analisi-tratti-adhd-adulti.pdf")))
                .andExpect(header().string("Cache-Control", containsString("no-store")))
                .andReturn();

        byte[] pdf = mvcResult.getResponse().getContentAsByteArray();
        assertThat(pdf).startsWith("%PDF-".getBytes());

        String previewPath = System.getProperty("pdf.preview.path");
        if (previewPath != null && !previewPath.isBlank()) {
            Path output = Path.of(previewPath).toAbsolutePath();
            Files.createDirectories(output.getParent());
            Files.write(output, pdf);
        }

        try (PDDocument document = PDDocument.load(pdf)) {
            String text = new PDFTextStripper().getText(document);
            assertThat(document.getNumberOfPages()).isGreaterThanOrEqualTo(1);
            assertThat(text)
                    .contains("Spazio Test")
                    .contains("Tratti associati all'ADHD nell'adulto")
                    .contains("PRESENZA COMPLESSIVA DEL TRATTO")
                    .contains("Attenzione sostenuta e distraibilità")
                    .contains("finalità esclusivamente informative");
        }
    }

    @Test
    void pdfDownloadRedirectsToTheTestWhenTheAttemptIsMissing() throws Exception {
        mockMvc.perform(get("/test/{testId}/risultato/pdf", "tratti-adhd-adulti"))
                .andExpect(status().isSeeOther())
                .andExpect(header().string("Location", "/test/tratti-adhd-adulti"));
    }

    private MockHttpSession completedAttempt(String testId, int answer) {
        PsychologicalTest test = catalogue.findById(testId);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int question = 0; question < test.questions().size(); question++) {
            attempt.answer(question, answer);
        }
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + testId, attempt);
        return session;
    }
}
