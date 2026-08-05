package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.model.TestResult;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.TestResultService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class TestController {

    private static final String ATTEMPT_PREFIX = "test-attempt-";
    private static final List<String> ANSWER_OPTIONS = List.of("Mai", "Raramente", "A volte", "Spesso", "Sempre");

    private final TestCatalogue catalogue;
    private final TestResultService resultService;

    public TestController(TestCatalogue catalogue, TestResultService resultService) {
        this.catalogue = catalogue;
        this.resultService = resultService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("tests", catalogue.findAll());
        return "home";
    }

    @GetMapping("/test/{testId}")
    public String introduction(@PathVariable String testId, Model model) {
        model.addAttribute("test", findTest(testId));
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
                           HttpSession session, Model model) {
        PsychologicalTest test = findTest(testId);
        TestAttempt attempt = findAttempt(testId, session);
        if (attempt == null) return "redirect:/test/" + testId;
        int questionIndex = validQuestionIndex(questionNumber, test);

        model.addAttribute("test", test);
        model.addAttribute("question", test.questions().get(questionIndex));
        model.addAttribute("questionNumber", questionNumber);
        model.addAttribute("questionCount", test.questions().size());
        model.addAttribute("progress", (questionNumber - 1) * 100 / test.questions().size());
        model.addAttribute("answers", ANSWER_OPTIONS);
        model.addAttribute("selectedAnswer", attempt.answerAt(questionIndex));
        return "question";
    }

    @PostMapping("/test/{testId}/domanda/{questionNumber}")
    public String saveAnswer(@PathVariable String testId, @PathVariable int questionNumber,
                             @RequestParam(required = false) Integer answer, HttpSession session) {
        PsychologicalTest test = findTest(testId);
        int questionIndex = validQuestionIndex(questionNumber, test);
        if (answer == null || answer < 1 || answer > ANSWER_OPTIONS.size()) {
            return "redirect:/test/" + testId + "/domanda/" + questionNumber;
        }
        TestAttempt attempt = findAttempt(testId, session);
        if (attempt == null) return "redirect:/test/" + testId;

        attempt.answer(questionIndex, answer);
        return questionNumber == test.questions().size()
                ? "redirect:/test/" + testId + "/risultato"
                : "redirect:/test/" + testId + "/domanda/" + (questionNumber + 1);
    }

    @GetMapping("/test/{testId}/risultato")
    public String result(@PathVariable String testId, HttpSession session, Model model) {
        PsychologicalTest test = findTest(testId);
        TestAttempt attempt = findAttempt(testId, session);
        if (attempt == null || !attempt.isComplete()) return "redirect:/test/" + testId;

        TestResult result = resultService.analyze(test, attempt);
        model.addAttribute("test", test);
        model.addAttribute("score", result.score());
        model.addAttribute("percentage", result.percentage());
        model.addAttribute("result", result.general());
        model.addAttribute("areaResults", result.areaResults());
        return "result";
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
}
