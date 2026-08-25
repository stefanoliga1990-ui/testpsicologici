package com.example.testpsicologici.model;

import java.util.List;

public record PsychologicalTest(
        String id,
        String title,
        String seoTitle,
        String eyebrow,
        String description,
        String seoDescription,
        String duration,
        String introductoryText,
        String responseInstruction,
        String version,
        boolean scoreVisible,
        String overallMetricLabel,
        String areaMetricLabel,
        String scoringModel,
        String answerScale,
        List<TestArea> areas,
        List<TestQuestion> questions,
        List<TestReference> references
) {
}
