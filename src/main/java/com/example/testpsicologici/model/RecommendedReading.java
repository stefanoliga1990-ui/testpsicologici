package com.example.testpsicologici.model;

public record RecommendedReading(
        String asin,
        String title,
        String authors,
        String description,
        String limit,
        String amazonUrl,
        String imageUrl,
        Integer imageWidth,
        Integer imageHeight
) {
    public RecommendedReading withImage(AmazonProductImage image) {
        if (image == null) {
            return this;
        }
        return new RecommendedReading(
                asin, title, authors, description, limit, amazonUrl,
                image.url(), image.width(), image.height());
    }
}
