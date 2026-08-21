package com.example.testpsicologici.service;

import com.example.testpsicologici.model.DailyVisitStat;
import com.example.testpsicologici.model.MonitoringSnapshot;
import com.example.testpsicologici.persistence.DailySiteVisitEntity;
import com.example.testpsicologici.persistence.DailySiteVisitRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VisitorAnalyticsService {

    private static final int MAX_DAYS = 366;

    private final DailySiteVisitRepository repository;
    private final Clock clock;
    private final ZoneId zoneId;

    public VisitorAnalyticsService(DailySiteVisitRepository repository, Clock analyticsClock,
                                   ZoneId analyticsZoneId) {
        this.repository = repository;
        this.clock = analyticsClock;
        this.zoneId = analyticsZoneId;
    }

    public void recordToday() {
        LocalDate today = LocalDate.now(clock.withZone(zoneId));
        if (repository.increment(today) > 0) {
            return;
        }
        try {
            repository.saveAndFlush(new DailySiteVisitEntity(today, 1));
        } catch (DataIntegrityViolationException concurrentInsert) {
            repository.increment(today);
        }
    }

    public MonitoringSnapshot snapshot(int requestedDays) {
        int days = Math.max(1, Math.min(requestedDays, MAX_DAYS));
        LocalDate today = LocalDate.now(clock.withZone(zoneId));
        LocalDate start = today.minusDays(days - 1L);
        Map<LocalDate, Long> counts = repository
                .findAllByVisitDateBetweenOrderByVisitDateAsc(start, today)
                .stream()
                .collect(Collectors.toMap(
                        DailySiteVisitEntity::getVisitDate,
                        DailySiteVisitEntity::getVisitorCount));

        var stats = new ArrayList<DailyVisitStat>(days);
        for (LocalDate date = start; !date.isAfter(today); date = date.plusDays(1)) {
            stats.add(new DailyVisitStat(date, counts.getOrDefault(date, 0L)));
        }
        return new MonitoringSnapshot(
                today,
                counts.getOrDefault(today, 0L),
                OffsetDateTime.now(clock.withZone(zoneId)),
                List.copyOf(stats));
    }
}
