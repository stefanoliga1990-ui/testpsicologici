package com.example.testpsicologici.service;

import com.example.testpsicologici.model.DailyTestCompletionStat;
import com.example.testpsicologici.model.TestCompletionSnapshot;
import com.example.testpsicologici.model.TestCompletionSummary;
import com.example.testpsicologici.persistence.DailyTestCompletionEntity;
import com.example.testpsicologici.persistence.DailyTestCompletionRepository;
import com.example.testpsicologici.persistence.TestDefinitionEntity;
import com.example.testpsicologici.persistence.TestDefinitionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TestCompletionAnalyticsService {

    private static final int MAX_DAYS = 366;

    private final DailyTestCompletionRepository completionRepository;
    private final TestDefinitionRepository testRepository;
    private final Clock clock;
    private final ZoneId zoneId;

    public TestCompletionAnalyticsService(DailyTestCompletionRepository completionRepository,
                                          TestDefinitionRepository testRepository,
                                          Clock analyticsClock,
                                          ZoneId analyticsZoneId) {
        this.completionRepository = completionRepository;
        this.testRepository = testRepository;
        this.clock = analyticsClock;
        this.zoneId = analyticsZoneId;
    }

    public void recordCompletion(String testId) {
        LocalDate today = today();
        if (completionRepository.increment(testId, today) > 0) {
            return;
        }
        try {
            completionRepository.saveAndFlush(new DailyTestCompletionEntity(testId, today, 1));
        } catch (DataIntegrityViolationException concurrentInsert) {
            completionRepository.increment(testId, today);
        }
    }

    public List<TestCompletionSummary> summaries() {
        LocalDate today = today();
        Map<String, Long> todayCounts = completionRepository.findAllByCompletionDate(today).stream()
                .collect(Collectors.toMap(
                        DailyTestCompletionEntity::getTestId,
                        DailyTestCompletionEntity::getCompletionCount));
        Map<String, Long> totals = completionRepository.findCompletionTotals().stream()
                .collect(Collectors.toMap(
                        DailyTestCompletionRepository.CompletionTotal::getTestId,
                        DailyTestCompletionRepository.CompletionTotal::getTotalCompletions));

        return testRepository.findAllByOrderByDisplayOrderAsc().stream()
                .map(test -> new TestCompletionSummary(
                        test.getId(),
                        test.getTitle(),
                        todayCounts.getOrDefault(test.getId(), 0L),
                        totals.getOrDefault(test.getId(), 0L)))
                .toList();
    }

    public TestCompletionSnapshot snapshot(String testId, int requestedDays) {
        TestDefinitionEntity test = testRepository.findById(testId)
                .orElseThrow(() -> new IllegalArgumentException("Test non trovato"));
        int days = Math.max(1, Math.min(requestedDays, MAX_DAYS));
        LocalDate today = today();
        LocalDate start = today.minusDays(days - 1L);
        Map<LocalDate, DailyTestCompletionEntity> storedDays = completionRepository
                .findAllByTestIdAndCompletionDateBetweenOrderByCompletionDateAsc(testId, start, today)
                .stream()
                .collect(Collectors.toMap(
                        DailyTestCompletionEntity::getCompletionDate,
                        Function.identity()));

        var stats = new ArrayList<DailyTestCompletionStat>(days);
        for (LocalDate date = start; !date.isAfter(today); date = date.plusDays(1)) {
            DailyTestCompletionEntity stored = storedDays.get(date);
            stats.add(new DailyTestCompletionStat(
                    date,
                    stored == null ? 0L : stored.getCompletionCount()));
        }
        long total = completionRepository.findCompletionTotals().stream()
                .filter(entry -> entry.getTestId().equals(testId))
                .mapToLong(DailyTestCompletionRepository.CompletionTotal::getTotalCompletions)
                .findFirst()
                .orElse(0L);

        return new TestCompletionSnapshot(
                testId,
                test.getTitle(),
                today,
                storedDays.containsKey(today) ? storedDays.get(today).getCompletionCount() : 0L,
                total,
                OffsetDateTime.now(clock.withZone(zoneId)),
                List.copyOf(stats));
    }

    private LocalDate today() {
        return LocalDate.now(clock.withZone(zoneId));
    }
}
