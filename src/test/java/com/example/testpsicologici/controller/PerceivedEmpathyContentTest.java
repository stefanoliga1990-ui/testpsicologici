package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.TestResultService;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class PerceivedEmpathyContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService resultService;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = webAppContextSetup(context).build();
    }

    @Test
    void originalItemsAreBalancedInterleavedAndUseARecentReferencePeriod() {
        PsychologicalTest test = catalogue.findById("empatia-percepita");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.questions()).hasSize(15).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("prospettiva", "risonanza", "risposta");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "prospettiva", "risonanza", "risposta",
                "prospettiva", "risonanza", "risposta",
                "prospettiva", "risonanza", "risposta",
                "prospettiva", "risonanza", "risposta",
                "prospettiva", "risonanza", "risposta");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "frequenza");
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.introductoryText()).contains("non validato", "112");
        assertThat(test.references()).hasSize(6);
    }

    @Test
    void allProfilesDescribeDistributionWithoutCertifyingEmpathicAbility() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 1, 1);
        TestResult focused = analyze(5, 1, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title())
                .doesNotHaveDuplicates();
        assertThat(low.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 0, 0);
        assertThat(broad.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 100, 0);
        for (TestResult result : List.of(low, mixed, focused, broad)) {
            assertThat(result.general().title()).contains("empatia percepita");
            assertThat(result.general().detail()).contains("non è validato", "capacità empatiche", "112");
        }
    }

    @Test
    void questionGuideResultPdfAndSitemapAreAvailable() throws Exception {
        mvc.perform(get("/test/empatia-percepita"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimi tre mesi")))
                .andExpect(content().string(containsString("/approfondimenti/empatia-verso-gli-altri")));
        mvc.perform(get("/approfondimenti/empatia-verso-gli-altri"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("empatia cognitiva")))
                .andExpect(content().string(containsString("/test/empatia-percepita")));

        MockHttpSession session = completedAttempt(5);
        mvc.perform(get("/test/empatia-percepita/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Attenzione alla prospettiva altrui")))
                .andExpect(content().string(containsString("Risposta relazionale dichiarata")))
                .andExpect(content().string(containsString("112")));

        MvcResult pdf = mvc.perform(get("/test/empatia-percepita/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document))
                    .contains("empatia")
                    .contains("Attenzione alla prospettiva altrui")
                    .contains("finalità esclusivamente informative");
        }

        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/empatia-percepita")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/empatia-verso-gli-altri")));
    }

    private TestResult analyze(int perspective, int resonance, int response) {
        PsychologicalTest test = catalogue.findById("empatia-percepita");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            int value = switch (test.questions().get(index).areaCode()) {
                case "prospettiva" -> perspective;
                case "risonanza" -> resonance;
                default -> response;
            };
            attempt.answer(index, value);
        }
        return resultService.analyze(test, attempt);
    }

    private MockHttpSession completedAttempt(int answer) {
        PsychologicalTest test = catalogue.findById("empatia-percepita");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, answer);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-empatia-percepita", attempt);
        return session;
    }
}
