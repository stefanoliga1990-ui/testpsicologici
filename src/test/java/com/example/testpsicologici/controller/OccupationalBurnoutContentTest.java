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
class OccupationalBurnoutContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService resultService;
    private MockMvc mvc;

    @BeforeEach
    void setUp() {
        mvc = webAppContextSetup(context).build();
    }

    @Test
    void blueprintUsesFifteenOriginalBalancedInterleavedItemsAndWorkContext() {
        PsychologicalTest test = catalogue.findById("burnout-percepito");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.questions()).hasSize(15).allSatisfy(question -> {
            assertThat(question.example()).isNull();
            assertThat(question.text()).startsWith("Ho ");
        });
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("esaurimento", "distacco", "efficacia");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "esaurimento", "distacco", "efficacia",
                "esaurimento", "distacco", "efficacia",
                "esaurimento", "distacco", "efficacia",
                "esaurimento", "distacco", "efficacia",
                "esaurimento", "distacco", "efficacia");
        assertThat(test.responseInstruction()).contains("ultimi tre mesi", "lavoro", "frequenza");
        assertThat(test.introductoryText()).contains("non validato", "112");
        assertThat(test.references()).hasSize(5);
    }

    @Test
    void profilesFollowAreaPatternWithSafetyIndependentOfLevel() {
        TestResult low = analyze(1, 1, 1);
        TestResult mixed = analyze(3, 1, 1);
        TestResult focused = analyze(5, 1, 1);
        TestResult broad = analyze(5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title())
                .doesNotHaveDuplicates();
        assertThat(low.areaResults()).extracting(area -> area.percentage()).containsExactly(0, 0, 0);
        assertThat(broad.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 100, 0);
        for (TestResult result : List.of(low, mixed, focused, broad)) {
            assertThat(result.general().detail()).contains("non è validato", "112", "Pronto Soccorso");
        }
    }

    @Test
    void introductionGuideResultPdfAndSitemapAreAvailable() throws Exception {
        mvc.perform(get("/test/burnout-percepito"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimi tre mesi")))
                .andExpect(content().string(containsString("/approfondimenti/burnout-lavorativo")));
        mvc.perform(get("/approfondimenti/burnout-lavorativo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("fenomeno occupazionale")))
                .andExpect(content().string(containsString("/test/burnout-percepito")));

        MockHttpSession session = completedAttempt(5);
        mvc.perform(get("/test/burnout-percepito/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Esaurimento legato al lavoro")))
                .andExpect(content().string(containsString("Distanza mentale dal lavoro")))
                .andExpect(content().string(containsString("Efficacia professionale percepita come ridotta")))
                .andExpect(content().string(containsString("Pronto Soccorso")));

        MvcResult pdf = mvc.perform(get("/test/burnout-percepito/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document))
                    .contains("burnout")
                    .contains("Esaurimento legato al lavoro")
                    .contains("finalità esclusivamente informative");
        }

        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/burnout-percepito")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/burnout-lavorativo")));
    }

    private TestResult analyze(int exhaustion, int distancing, int efficacy) {
        PsychologicalTest test = catalogue.findById("burnout-percepito");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            int value = switch (test.questions().get(index).areaCode()) {
                case "esaurimento" -> exhaustion;
                case "distacco" -> distancing;
                default -> efficacy;
            };
            attempt.answer(index, value);
        }
        return resultService.analyze(test, attempt);
    }

    private MockHttpSession completedAttempt(int answer) {
        PsychologicalTest test = catalogue.findById("burnout-percepito");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) attempt.answer(index, answer);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-burnout-percepito", attempt);
        return session;
    }
}
