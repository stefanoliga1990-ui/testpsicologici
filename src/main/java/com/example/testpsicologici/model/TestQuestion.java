package com.example.testpsicologici.model;

public record TestQuestion(String text, String example, String areaCode, String indicatorCode) {
    public TestQuestion(String text, String example, String areaCode) {
        this(text, example, areaCode, null);
    }
}
