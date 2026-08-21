package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.DailyVisitCookieService;
import com.example.testpsicologici.service.VisitorAnalyticsService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.regex.Pattern;

@RestController
public class VisitorAnalyticsController {

    private static final Pattern AUTOMATED_USER_AGENT = Pattern.compile(
            "bot|crawler|spider|slurp|bingpreview|facebookexternalhit|headless|lighthouse|uptime",
            Pattern.CASE_INSENSITIVE);

    private final VisitorAnalyticsService analyticsService;
    private final DailyVisitCookieService cookieService;

    public VisitorAnalyticsController(VisitorAnalyticsService analyticsService,
                                      DailyVisitCookieService cookieService) {
        this.analyticsService = analyticsService;
        this.cookieService = cookieService;
    }

    @PostMapping("/internal/visita")
    public ResponseEntity<Void> recordVisit(HttpServletRequest request) {
        if (cookieService.wasAlreadyCounted(request) || isAutomated(request)) {
            return noContent().build();
        }
        analyticsService.recordToday();
        return noContent()
                .header(HttpHeaders.SET_COOKIE, cookieService.cookieForToday().toString())
                .build();
    }

    private ResponseEntity.HeadersBuilder<?> noContent() {
        return ResponseEntity.noContent()
                .cacheControl(CacheControl.noStore())
                .header("X-Robots-Tag", "noindex, nofollow, noarchive");
    }

    private boolean isAutomated(HttpServletRequest request) {
        String userAgent = request.getHeader(HttpHeaders.USER_AGENT);
        return userAgent == null
                || userAgent.isBlank()
                || AUTOMATED_USER_AGENT.matcher(userAgent.toLowerCase(Locale.ROOT)).find();
    }
}
