package com.example.testpsicologici.model;

import java.time.LocalDate;

public record GuideEditorialHistory(LocalDate publishedOn, LocalDate revisedOn) {
    public GuideEditorialHistory {
        if (publishedOn == null || revisedOn == null || revisedOn.isBefore(publishedOn)) {
            throw new IllegalArgumentException("Le date editoriali della guida non sono valide");
        }
    }
}
