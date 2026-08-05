package com.example.testpsicologici.model;

/** Area tematica usata per calcolare e presentare la lettura specifica. */
public record TestArea(
        String code,
        String name,
        String lowInsight,
        String mediumInsight,
        String highInsight
) {
}
