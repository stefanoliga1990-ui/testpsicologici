package com.example.testpsicologici.service;

import com.example.testpsicologici.model.InformationGuide;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GuideEditorialHistoryCatalogueTest {

    @Autowired
    private GuideCatalogue guides;

    @Autowired
    private GuideEditorialHistoryCatalogue histories;

    @Test
    void everyPublishedGuideHasExactlyOneCoherentEditorialHistory() {
        assertThat(histories.findAll().keySet()).containsExactlyInAnyOrderElementsOf(
                guides.findAll().stream().map(InformationGuide::slug).toList());
        histories.findAll().values().forEach(history ->
                assertThat(history.revisedOn()).isAfterOrEqualTo(history.publishedOn()));
    }
}
