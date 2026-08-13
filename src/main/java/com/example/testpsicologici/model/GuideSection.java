package com.example.testpsicologici.model;

import java.util.List;

public record GuideSection(
        String eyebrow,
        String title,
        List<String> paragraphs,
        List<String> points
) {
}
