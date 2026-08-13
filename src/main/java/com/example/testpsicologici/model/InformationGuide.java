package com.example.testpsicologici.model;

import java.util.List;

public record InformationGuide(
        String slug,
        String testId,
        String cardTitle,
        String title,
        String seoTitle,
        String seoDescription,
        String summary,
        List<GuideSection> sections,
        String testConnection,
        List<GuideReference> references
) {
}
