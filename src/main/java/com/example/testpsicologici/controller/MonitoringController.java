package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.MonitoringSnapshot;
import com.example.testpsicologici.service.VisitorAnalyticsService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MonitoringController {

    private static final int DEFAULT_DAYS = 30;
    private final VisitorAnalyticsService analyticsService;

    public MonitoringController(VisitorAnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
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
        model.addAttribute("snapshot", snapshot);
        model.addAttribute("reactPageData", ReactPageData.of(
                "monitoring",
                "initialSnapshot", snapshot,
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

    private void privateResponse(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("X-Robots-Tag", "noindex, nofollow, noarchive");
    }
}
