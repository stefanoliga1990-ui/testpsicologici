package com.example.testpsicologici.service;

import com.example.testpsicologici.model.AmazonProductImage;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RecommendedReadingCatalogueTest {

    @Test
    void enrichesConfiguredReadingsWithTheMatchingAmazonCover() {
        AmazonProductImage image = new AmazonProductImage(
                "https://m.media-amazon.com/images/I/cover.jpg", 318, 500);
        RecommendedReadingCatalogue catalogue = new RecommendedReadingCatalogue(
                asins -> Map.of("8868956004", image));

        var readings = catalogue.findByTestId("tratti-autistici-adulti");

        assertThat(readings).hasSize(2);
        assertThat(readings.get(0).asin()).isEqualTo("8868956004");
        assertThat(readings.get(0).imageUrl()).isEqualTo(image.url());
        assertThat(readings.get(0).imageWidth()).isEqualTo(318);
        assertThat(readings.get(0).imageHeight()).isEqualTo(500);
        assertThat(readings.get(1).imageUrl()).isNull();
    }
}
