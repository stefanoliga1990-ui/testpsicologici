package com.example.testpsicologici.model;

public record TestCompletionSummary(
        String testId,
        String testTitle,
        long todayCompletions,
        long totalCompletions) {
}
