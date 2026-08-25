package com.example.testpsicologici.service;

import com.example.testpsicologici.model.NotFoundPathStat;
import com.example.testpsicologici.persistence.NotFoundPathEntity;
import com.example.testpsicologici.persistence.NotFoundPathRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.Instant;
import java.util.List;

@Service
public class NotFoundPathAnalyticsService {

    static final int MAX_PATH_LENGTH = 512;
    static final long MAX_TRACKED_PATHS = 1_000;

    private final NotFoundPathRepository repository;
    private final Clock clock;

    public NotFoundPathAnalyticsService(NotFoundPathRepository repository, Clock analyticsClock) {
        this.repository = repository;
        this.clock = analyticsClock;
    }

    public void record(String requestUri) {
        String path = normalizedPath(requestUri);
        Instant seenAt = clock.instant();
        if (repository.increment(path, seenAt) > 0) {
            return;
        }
        if (repository.count() >= MAX_TRACKED_PATHS) {
            return;
        }
        try {
            repository.saveAndFlush(new NotFoundPathEntity(path, 1, seenAt, seenAt));
        } catch (DataIntegrityViolationException concurrentInsert) {
            repository.increment(path, seenAt);
        }
    }

    public List<NotFoundPathStat> mostFrequent() {
        return repository.findTop20ByOrderByHitCountDescLastSeenAtDesc().stream()
                .map(entry -> new NotFoundPathStat(
                        entry.getPath(), entry.getHitCount(),
                        entry.getFirstSeenAt(), entry.getLastSeenAt()))
                .toList();
    }

    public static String normalizedPath(String requestUri) {
        if (requestUri == null || requestUri.isBlank()) {
            return "/";
        }
        int queryStart = requestUri.indexOf('?');
        String path = queryStart >= 0 ? requestUri.substring(0, queryStart) : requestUri;
        int fragmentStart = path.indexOf('#');
        if (fragmentStart >= 0) {
            path = path.substring(0, fragmentStart);
        }
        path = path.replaceAll("[\\p{Cntrl}]", "").trim();
        if (path.isEmpty()) {
            return "/";
        }
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return path.length() <= MAX_PATH_LENGTH ? path : path.substring(0, MAX_PATH_LENGTH);
    }
}
