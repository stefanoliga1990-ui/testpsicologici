package com.example.testpsicologici.model;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record TestCompletionSnapshot(
        String testId,
        String testTitle,
        LocalDate today,
        long todayCompletions,
        long totalCompletions,
        OffsetDateTime generatedAt,
        List<DailyTestCompletionStat> days) {
}
