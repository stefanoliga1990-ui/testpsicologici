package com.example.testpsicologici.model;

import java.util.List;

public record TopicCluster(
        String slug,
        String title,
        String description,
        List<String> testIds) {
}
