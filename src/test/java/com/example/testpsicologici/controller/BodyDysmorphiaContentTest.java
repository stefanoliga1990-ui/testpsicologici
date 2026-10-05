package com.example.testpsicologici.controller;

import com.example.testpsicologici.config.ContentDataInitializer;
import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import com.example.testpsicologici.persistence.TestDefinitionRepository;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.TestResultService;
import com.example.testpsicologici.service.TopicClusterCatalogue;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
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
class BodyDysmorphiaContentTest {

    @Autowired private WebApplicationContext context;
    @Autowired private TestCatalogue catalogue;
    @Autowired private TestResultService resultService;
    @Autowired private TopicClusterCatalogue clusters;
    @Autowired private TestDefinitionRepository definitions;
    @Autowired private ContentDataInitializer initializer;

    @Test
    @Transactional
    void startupUpdatesExistingTitleWithoutReseedingTheQuestionnaire() {
        var definition = definitions.findById("dismorfofobia").orElseThrow();
        definition.updatePresentation("Titolo precedente", "Titolo precedente | Spazio Test");
        definitions.saveAndFlush(definition);

        initializer.run(new DefaultApplicationArguments(new String[0]));

        PsychologicalTest updated = catalogue.findById("dismorfofobia");
        assertThat(updated.title()).isEqualTo("Dismorfofobia: test informativo");
        assertThat(updated.seoTitle()).isEqualTo("Dismorfofobia: test informativo | Spazio Test");
        assertThat(updated.version()).isEqualTo("1.0");
        assertThat(updated.questions()).hasSize(16);
    }

    @Test
    void blueprintHasFourBalancedInterleavedAreasAndExplicitLimits() {
        PsychologicalTest test = catalogue.findById("dismorfofobia");
        assertThat(test.version()).isEqualTo("1.0");
        assertThat(test.title()).isEqualTo("Dismorfofobia: test informativo");
        assertThat(test.seoTitle()).startsWith("Dismorfofobia:");
        assertThat(test.scoringModel()).isEqualTo("AREA_PROFILE");
        assertThat(test.scoreVisible()).isFalse();
        assertThat(test.answerScale()).isEqualTo("FREQUENCY");
        assertThat(test.questions()).hasSize(16).extracting(question -> question.text()).doesNotHaveDuplicates();
        assertThat(test.areas()).extracting(area -> area.code())
                .containsExactly("pensieri", "verifiche", "gestione", "interferenza");
        assertThat(test.questions()).extracting(question -> question.areaCode()).containsExactly(
                "pensieri", "verifiche", "gestione", "interferenza",
                "pensieri", "verifiche", "gestione", "interferenza",
                "pensieri", "verifiche", "gestione", "interferenza",
                "pensieri", "verifiche", "gestione", "interferenza");
        assertThat(test.responseInstruction()).contains("ultimo mese", "frequenza");
        assertThat(test.introductoryText()).contains("non validato", "non identifica né esclude", "112");
        assertThat(test.references()).hasSize(7);
        assertThat(clusters.findByTestId("dismorfofobia").orElseThrow().slug())
                .isEqualTo("ansia-umore-e-trauma");
        assertThat(clusters.findRelatedTestIds("dismorfofobia", 3))
                .containsExactly("tratti-ossessivo-compulsivi", "ansia-sociale", "umore-depresso");
    }

    @Test
    void profileBoundariesAndIndependentSupportAreDescriptive() {
        TestResult low = analyze(1, 1, 1, 1);
        TestResult mixed = analyze(3, 3, 3, 3);
        TestResult focused = analyze(5, 5, 1, 1);
        TestResult broad = analyze(5, 5, 5, 1);
        assertThat(List.of(low, mixed, focused, broad)).extracting(result -> result.general().title())
                .doesNotHaveDuplicates();
        assertThat(focused.areaResults()).extracting(area -> area.percentage()).containsExactly(100, 100, 0, 0);
        for (TestResult result : List.of(low, mixed, focused, broad)) {
            assertThat(result.areaResults()).hasSize(4);
            assertThat(result.general().detail()).contains("non validato", "112", "qualunque sia il profilo");
        }
        assertThat(analyze(2, 3, 3, 3).general().title()).contains("variano");
        assertThat(analyze(3, 5, 1, 1).general().title()).contains("Una o due");
        assertThat(analyze(5, 5, 5, 5).general().title()).contains("Più aree");
    }

    @Test
    void testGuideReviewerReadingResultPdfAndSitemapRender() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        mvc.perform(get("/test/dismorfofobia"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ultimo mese")))
                .andExpect(content().string(containsString("/approfondimenti/dismorfofobia")));
        mvc.perform(get("/approfondimenti/dismorfofobia"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Dismorfofobia (disturbo da dismorfismo corporeo)")))
                .andExpect(content().string(containsString("disturbo da dismorfismo corporeo")))
                .andExpect(content().string(containsString("Letture facoltative")))
                .andExpect(content().string(containsString("Alessia Liga")))
                .andExpect(content().string(containsString("https://specialmente.substack.com/p/la-dismorfofobia")))
                .andExpect(content().string(containsString("/test/dismorfofobia")));

        PsychologicalTest test = catalogue.findById("dismorfofobia");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) attempt.answer(i, 3);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-dismorfofobia", attempt);
        mvc.perform(get("/test/dismorfofobia/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pensieri sull'aspetto")))
                .andExpect(content().string(containsString("Interferenza riferita")))
                .andExpect(content().string(not(containsString("overall-presence-track"))));
        MvcResult pdf = mvc.perform(get("/test/dismorfofobia/risultato/pdf").session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andReturn();
        try (PDDocument document = PDDocument.load(pdf.getResponse().getContentAsByteArray())) {
            assertThat(new PDFTextStripper().getText(document))
                    .contains("Dismorfofobia: test informativo", "Pensieri sull'aspetto")
                    .doesNotContain("FREQUENZA MEDIA DELLE RISPOSTE RIFERITE");
        }
        mvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/test/dismorfofobia")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti/dismorfofobia")));
    }

    private TestResult analyze(int pensieri, int verifiche, int gestione, int interferenza) {
        PsychologicalTest test = catalogue.findById("dismorfofobia");
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int i = 0; i < test.questions().size(); i++) {
            attempt.answer(i, switch (test.questions().get(i).areaCode()) {
                case "pensieri" -> pensieri;
                case "verifiche" -> verifiche;
                case "gestione" -> gestione;
                default -> interferenza;
            });
        }
        return resultService.analyze(test, attempt);
    }
}
