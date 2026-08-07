package com.example.testpsicologici.model;

import java.util.List;

public record PsychologicalTest(
        String id,
        String title,
        String eyebrow,
        String description,
        String duration,
        String introductoryText,
        String version,
        boolean scoreVisible,
        String overallMetricLabel,
        String areaMetricLabel,
        List<TestArea> areas,
        List<TestQuestion> questions
) {
}
