package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.MonitoringSnapshot;
import com.example.testpsicologici.model.TestCompletionSnapshot;
import com.example.testpsicologici.model.TestCompletionSummary;
import com.example.testpsicologici.model.NotFoundPathStat;
import com.example.testpsicologici.service.TestCompletionAnalyticsService;
import com.example.testpsicologici.service.VisitorAnalyticsService;
import com.example.testpsicologici.service.NotFoundPathAnalyticsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Controller
public class MonitoringController {

    private static final int DEFAULT_DAYS = 30;
    private final VisitorAnalyticsService analyticsService;
    private final TestCompletionAnalyticsService completionAnalyticsService;
    private final NotFoundPathAnalyticsService notFoundAnalyticsService;

    public MonitoringController(VisitorAnalyticsService analyticsService,
                                TestCompletionAnalyticsService completionAnalyticsService,
                                NotFoundPathAnalyticsService notFoundAnalyticsService) {
        this.analyticsService = analyticsService;
        this.completionAnalyticsService = completionAnalyticsService;
        this.notFoundAnalyticsService = notFoundAnalyticsService;
    }

    @GetMapping("/monitoring/login")
    public String login(HttpServletResponse response) {
        privateResponse(response);
        return "monitoring-login";
    }

    @GetMapping("/monitoring")
    public String dashboard(CsrfToken csrfToken, HttpServletResponse response, Model model) {
        privateResponse(response);
        MonitoringSnapshot snapshot = analyticsService.snapshot(DEFAULT_DAYS);
        List<TestCompletionSummary> testCompletions = completionAnalyticsService.summaries();
        var notFoundPaths = notFoundAnalyticsService.mostFrequent();
        model.addAttribute("snapshot", snapshot);
        model.addAttribute("testCompletions", testCompletions);
        model.addAttribute("notFoundPaths", notFoundPaths);
        model.addAttribute("reactPageData", ReactPageData.of(
                "monitoring",
                "initialSnapshot", snapshot,
                "initialTestCompletions", testCompletions,
                "initialNotFoundPaths", notFoundPaths,
                "csrfParameterName", csrfToken.getParameterName(),
                "csrfToken", csrfToken.getToken()));
        return "monitoring";
    }

    @GetMapping("/monitoring/api/visite")
    @ResponseBody
    public ResponseEntity<MonitoringSnapshot> visits(@RequestParam(defaultValue = "30") int days) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore())
                .header("X-Robots-Tag", "noindex, nofollow, noarchive")
                .body(analyticsService.snapshot(days));
    }

    @GetMapping("/monitoring/api/test-completamenti")
    @ResponseBody
    public ResponseEntity<List<TestCompletionSummary>> testCompletions() {
        return privateJson(completionAnalyticsService.summaries());
    }

    @GetMapping("/monitoring/api/not-found-paths")
    @ResponseBody
    public ResponseEntity<List<NotFoundPathStat>> notFoundPaths() {
        return privateJson(notFoundAnalyticsService.mostFrequent());
    }

    @GetMapping("/monitoring/api/test-completamenti/{testId}")
    @ResponseBody
    public ResponseEntity<TestCompletionSnapshot> testCompletionHistory(
            @PathVariable String testId,
            @RequestParam(defaultValue = "30") int days) {
        try {
            return privateJson(completionAnalyticsService.snapshot(testId, days));
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(NOT_FOUND, "Test non trovato");
        }
    }

    private <T> ResponseEntity<T> privateJson(T body) {
        return ResponseEntity.ok()
                .cacheControl(CacheControl.noStore())
                .header("X-Robots-Tag", "noindex, nofollow, noarchive")
                .body(body);
    }

    private void privateResponse(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("X-Robots-Tag", "noindex, nofollow, noarchive");
    }
}
