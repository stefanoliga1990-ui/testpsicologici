package com.example.testpsicologici.model;

import java.time.OffsetDateTime;
import java.time.LocalDate;
import java.util.List;

public record MonitoringSnapshot(
        LocalDate today,
        long todayVisitors,
        OffsetDateTime generatedAt,
        List<DailyVisitStat> days) {
}
