package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.InformationGuide;
import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.PdfResultService;
import com.example.testpsicologici.service.SiteUrlService;
import com.example.testpsicologici.service.TestResultService;
import com.example.testpsicologici.service.TestCompletionAnalyticsService;
import com.example.testpsicologici.service.TopicClusterCatalogue;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Controller
public class TestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
    private static final String ATTEMPT_PREFIX = "test-attempt-";
    private static final String RESULT_PREVIEW_TEST_ID = "autostima";
    private static final int MAX_SEARCH_QUERY_LENGTH = 120;
    private static final List<String> ANSWER_OPTIONS =
            List.of("Mai", "Raramente", "A volte", "Spesso", "Quasi sempre");
    private static final List<String> AGREEMENT_ANSWER_OPTIONS =
            List.of("Per nulla vero per me", "Poco vero per me", "In parte vero per me",
                    "Molto vero per me", "Del tutto vero per me");

    private final TestCatalogue catalogue;
    private final TestResultService resultService;
    private final PdfResultService pdfResultService;
    private final SiteUrlService siteUrlService;
    private final GuideCatalogue guideCatalogue;
    private final TestCompletionAnalyticsService completionAnalyticsService;
    private final TopicClusterCatalogue topicClusterCatalogue;
    private final boolean contributionsEnabled;

    public TestController(TestCatalogue catalogue, TestResultService resultService,
                          PdfResultService pdfResultService, SiteUrlService siteUrlService,
                          GuideCatalogue guideCatalogue,
                          TestCompletionAnalyticsService completionAnalyticsService,
                          TopicClusterCatalogue topicClusterCatalogue,
                          @Value("${app.payments.stripe.enabled:false}") boolean contributionsEnabled) {
        this.catalogue = catalogue;
        this.resultService = resultService;
        this.pdfResultService = pdfResultService;
        this.siteUrlService = siteUrlService;
        this.guideCatalogue = guideCatalogue;
        this.completionAnalyticsService = completionAnalyticsService;
        this.topicClusterCatalogue = topicClusterCatalogue;
        this.contributionsEnabled = contributionsEnabled;
    }

    @GetMapping("/")
    public String home(@RequestParam(name = "q", defaultValue = "") String searchQuery,
                       HttpServletRequest request, Model model) {
        String initialQuery = searchQuery.length() <= MAX_SEARCH_QUERY_LENGTH
                ? searchQuery : searchQuery.substring(0, MAX_SEARCH_QUERY_LENGTH);
        List<PsychologicalTest> tests = catalogue.findAll();
        var topicClusters = topicClusterCatalogue.findAll();
        model.addAttribute("tests", tests);
        model.addAttribute("topicClusters", topicClusters);
        model.addAttribute("testsById", tests.stream().collect(Collectors.toMap(
                PsychologicalTest::id, Function.identity(), (first, ignored) -> first, LinkedHashMap::new)));
        model.addAttribute("searchQuery", initialQuery);
        model.addAttribute("reactPageData", ReactPageData.of(
                "home", "tests", tests, "topicClusters", topicClusters, "initialQuery", initialQuery));
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/"));
        model.addAttribute("projectUrl", siteUrlService.canonicalUrl(request, "/il-progetto"));
        return "home";
    }

    @GetMapping("/test/{testId}")
    public String introduction(@PathVariable String testId, HttpServletRequest request, Model model) {
        PsychologicalTest test = findTest(testId);
        InformationGuide guide = guideCatalogue.findByTestId(testId).orElse(null);
        var topicCluster = topicClusterCatalogue.findByTestId(testId).orElse(null);
        var relatedTests = catalogue.findSuggestionsByIds(
                topicClusterCatalogue.findRelatedTestIds(testId, 3));
        model.addAttribute("test", test);
        model.addAttribute("guide", guide);
        model.addAttribute("topicCluster", topicCluster);
        model.addAttribute("relatedTests", relatedTests);
        model.addAttribute("reactPageData", ReactPageData.of(
                "introduction", "test", test, "guide", guide,
                "topicCluster", topicCluster, "relatedTests", relatedTests));
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/test/" + testId));
        model.addAttribute("projectUrl", siteUrlService.canonicalUrl(request, "/il-progetto"));
        model.addAttribute("methodUrl", siteUrlService.canonicalUrl(request, "/metodo-e-fonti"));
        return "test-introduction";
    }

    @PostMapping("/test/{testId}/inizia")
    public String start(@PathVariable String testId, HttpSession session) {
        PsychologicalTest test = findTest(testId);
        session.setAttribute(attemptKey(testId), new TestAttempt(test.questions().size()));
        return "redirect:/test/" + testId + "/domanda/1";
    }

    @GetMapping("/test/{testId}/domanda/{questionNumber}")
    public String question(@PathVariable String testId, @PathVariable int questionNumber,
                           HttpSession session, HttpServletResponse response, Model model) {
        response.setHeader("X-Robots-Tag", "noindex, follow, noarchive");
        PsychologicalTest test = findTest(testId);
        TestAttempt attempt = findAttempt(testId, session);
        if (attempt == null) return "redirect:/test/" + testId;
        int questionIndex = validQuestionIndex(questionNumber, test);

        model.addAttribute("test", test);
        model.addAttribute("question", test.questions().get(questionIndex));
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("questionCount", test.questions().size());
        model.addAttribute("progress", (questionNumber - 1) * 100 / test.questions().size());
        List<String> answerOptions = answerOptionsFor(test);
        model.addAttribute("answers", answerOptions);
        model.addAttribute("selectedAnswer", attempt.answerAt(questionIndex));
        model.addAttribute("reactPageData", ReactPageData.of(
                "question",
                "test", test,
                "question", test.questions().get(questionIndex),
                "questionNumber", questionNumber,
                "questionCount", test.questions().size(),
                "progress", (questionNumber - 1) * 100 / test.questions().size(),
                "answers", answerOptions,
                "selectedAnswer", attempt.answerAt(questionIndex)));
        return "question";
    }

    @PostMapping("/test/{testId}/domanda/{questionNumber}")
    public String saveAnswer(@PathVariable String testId, @PathVariable int questionNumber,
                             @RequestParam(required = false) Integer answer, HttpSession session) {
        PsychologicalTest test = findTest(testId);
        int questionIndex = validQuestionIndex(questionNumber, test);
        if (answer == null || answer < 1 || answer > answerOptionsFor(test).size()) {
            return "redirect:/test/" + testId + "/domanda/" + questionNumber;
        }
        TestAttempt attempt = findAttempt(testId, session);
        if (attempt == null) return "redirect:/test/" + testId;

        attempt.answer(questionIndex, answer);
        if (attempt.markCompletionRecorded()) {
            try {
                completionAnalyticsService.recordCompletion(testId);
            } catch (RuntimeException exception) {
                LOGGER.warn("Conteggio completamento non riuscito per il test {}", testId, exception);
            }
        }
        return questionNumber == test.questions().size()
                ? "redirect:/test/" + testId + "/risultato"
                : "redirect:/test/" + testId + "/domanda/" + (questionNumber + 1);
    }

    @GetMapping("/test/{testId}/risultato")
    public String result(@PathVariable String testId, HttpSession session,
                         HttpServletResponse response, Model model) {
        response.setHeader("X-Robots-Tag", "noindex, follow, noarchive");
        PsychologicalTest test = findTest(testId);
        TestAttempt attempt = findAttempt(testId, session);
        if (attempt == null || !attempt.isComplete()) return "redirect:/test/" + testId;

        return renderResult(test, attempt, model);
    }

    @GetMapping("/monitoring/anteprima/test/autostima/risultato/low")
    public String lowSelfEsteemResultPreview(HttpServletResponse response, Model model) {
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
        response.setHeader("X-Robots-Tag", "noindex, nofollow, noarchive");
        PsychologicalTest test = findTest(RESULT_PREVIEW_TEST_ID);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int questionIndex = 0; questionIndex < test.questions().size(); questionIndex++) {
            attempt.answer(questionIndex, 1);
        }

        return renderResult(test, attempt, model);
    }

    private String renderResult(PsychologicalTest test, TestAttempt attempt, Model model) {
        TestResult result = resultService.analyze(test, attempt);
        InformationGuide guide = guideCatalogue.findByTestId(test.id()).orElse(null);
        var topicCluster = topicClusterCatalogue.findByTestId(test.id()).orElse(null);
        var relatedTests = catalogue.findSuggestionsByIds(
                topicClusterCatalogue.findRelatedTestIds(test.id(), 3));
        model.addAttribute("test", test);
        model.addAttribute("guide", guide);
        model.addAttribute("topicCluster", topicCluster);
        model.addAttribute("relatedTests", relatedTests);
        model.addAttribute("score", result.score());
        model.addAttribute("percentage", result.percentage());
        model.addAttribute("result", result.general());
        model.addAttribute("areaResults", result.areaResults());
        model.addAttribute("styleResults", result.styleResults());
        model.addAttribute("reactPageData", ReactPageData.of(
                "result",
                "test", test,
                "score", result.score(),
                "percentage", result.percentage(),
                "result", result.general(),
                "areaResults", result.areaResults(),
                "styleResults", result.styleResults(),
                "guide", guide,
                "topicCluster", topicCluster,
                "relatedTests", relatedTests,
                "contributionsEnabled", contributionsEnabled));
        model.addAttribute("contributionsEnabled", contributionsEnabled);
        return "result";
    }

    @GetMapping(value = "/test/{testId}/risultato/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> downloadResultPdf(@PathVariable String testId, HttpSession session) {
        PsychologicalTest test = findTest(testId);
        TestAttempt attempt = findAttempt(testId, session);
        if (attempt == null || !attempt.isComplete()) {
            return ResponseEntity.<byte[]>status(HttpStatus.SEE_OTHER)
                    .location(URI.create("/test/" + testId))
                    .build();
        }

        TestResult result = resultService.analyze(test, attempt);
        byte[] pdf = pdfResultService.generate(test, result);
        String filename = "analisi-" + test.id() + ".pdf";
        String contentDisposition = ContentDisposition.attachment()
                .filename(filename, StandardCharsets.UTF_8)
                .build()
                .toString();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdf.length)
                .cacheControl(CacheControl.noStore())
                .header("X-Robots-Tag", "noindex, nofollow, noarchive")
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(pdf);
    }

    private PsychologicalTest findTest(String testId) {
        try {
            return catalogue.findById(testId);
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Test non trovato");
        }
    }

    private TestAttempt findAttempt(String testId, HttpSession session) {
        Object attempt = session.getAttribute(attemptKey(testId));
        return attempt instanceof TestAttempt testAttempt ? testAttempt : null;
    }

    private int validQuestionIndex(int questionNumber, PsychologicalTest test) {
        if (questionNumber < 1 || questionNumber > test.questions().size()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Domanda non trovata");
        }
        return questionNumber - 1;
    }

    private String attemptKey(String testId) {
        return ATTEMPT_PREFIX + testId;
    }

    private List<String> answerOptionsFor(PsychologicalTest test) {
        return "AGREEMENT".equals(test.answerScale()) ? AGREEMENT_ANSWER_OPTIONS : ANSWER_OPTIONS;
    }
}
