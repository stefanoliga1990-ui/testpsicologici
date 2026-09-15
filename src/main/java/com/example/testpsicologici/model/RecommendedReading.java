package com.example.testpsicologici.model;

public record RecommendedReading(
        String title,
        String authors,
        String description,
        String limit,
        String amazonUrl,
        boolean placeholderCover
) {
}
