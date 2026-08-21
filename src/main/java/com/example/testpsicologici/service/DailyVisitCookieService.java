package com.example.testpsicologici.service;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;

@Service
public class DailyVisitCookieService {

    public static final String COOKIE_NAME = "__Host-st_visit_day";
    private static final Logger LOGGER = LoggerFactory.getLogger(DailyVisitCookieService.class);
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final String VALUE_VERSION = "v1";

    private final Clock clock;
    private final ZoneId zoneId;
    private final byte[] secret;

    public DailyVisitCookieService(Clock analyticsClock, ZoneId analyticsZoneId,
                                   @Value("${app.analytics.cookie-secret:}") String configuredSecret) {
        this.clock = analyticsClock;
        this.zoneId = analyticsZoneId;
        if (configuredSecret == null || configuredSecret.isBlank()) {
            byte[] generatedSecret = new byte[32];
            new SecureRandom().nextBytes(generatedSecret);
            this.secret = generatedSecret;
            LOGGER.warn("VISITOR_COOKIE_SECRET non configurata: è stata generata una chiave temporanea; "
                    + "i cookie visita diventeranno non validi al prossimo riavvio");
        } else {
            this.secret = configuredSecret.getBytes(StandardCharsets.UTF_8);
        }
    }

    public boolean wasAlreadyCounted(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            return false;
        }
        String expected = valueFor(today());
        for (Cookie cookie : cookies) {
            if (COOKIE_NAME.equals(cookie.getName())) {
                return MessageDigest.isEqual(
                        expected.getBytes(StandardCharsets.UTF_8),
                        cookie.getValue().getBytes(StandardCharsets.UTF_8));
            }
        }
        return false;
    }

    public ResponseCookie cookieForToday() {
        Instant now = clock.instant();
        Instant nextMidnight = today().plusDays(1).atStartOfDay(zoneId).toInstant();
        Duration maxAge = Duration.between(now, nextMidnight);
        return ResponseCookie.from(COOKIE_NAME, valueFor(today()))
                .httpOnly(true)
                .secure(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(maxAge)
                .build();
    }

    private LocalDate today() {
        return LocalDate.now(clock.withZone(zoneId));
    }

    private String valueFor(LocalDate date) {
        String payload = VALUE_VERSION + "." + date;
        return payload + "." + sign(payload);
    }

    private String sign(String payload) {
        try {
            Mac mac = Mac.getInstance(HMAC_ALGORITHM);
            mac.init(new SecretKeySpec(secret, HMAC_ALGORITHM));
            return Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(mac.doFinal(payload.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception exception) {
            throw new IllegalStateException("Impossibile firmare il cookie visita", exception);
        }
    }
}
