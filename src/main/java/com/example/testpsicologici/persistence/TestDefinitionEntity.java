package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "test_definition")
public class TestDefinitionEntity {

    @Id
    private String id;
    private String title;
    @Column(name = "seo_title", length = 200)
    private String seoTitle;
    private String eyebrow;
    @Column(length = 1200)
    private String description;
    @Column(name = "seo_description", length = 400)
    private String seoDescription;
    private String duration;
    @Column(name = "introductory_text", length = 2400)
    private String introductoryText;
    @Column(name = "response_instruction", length = 400)
    private String responseInstruction;
    private String version;
    @Column(name = "score_visible")
    private boolean scoreVisible;
    @Column(name = "overall_metric_label")
    private String overallMetricLabel;
    @Column(name = "area_metric_label")
    private String areaMetricLabel;
    private boolean active;
    @Column(name = "display_order")
    private int displayOrder;

    protected TestDefinitionEntity() {
    }

    public TestDefinitionEntity(String id, String title, String eyebrow, String description, String duration,
                                String introductoryText, String version, boolean scoreVisible, boolean active,
                                int displayOrder) {
        this(id, title, eyebrow, description, duration, introductoryText, version, scoreVisible,
                "Presenza complessiva del tratto", "Presenza del tratto", active, displayOrder);
    }

    public TestDefinitionEntity(String id, String title, String eyebrow, String description, String duration,
                                String introductoryText, String version, boolean scoreVisible,
                                String overallMetricLabel, String areaMetricLabel, boolean active,
                                int displayOrder) {
        this.id = id;
        this.title = title;
        this.eyebrow = eyebrow;
        this.description = description;
        this.duration = duration;
        this.introductoryText = introductoryText;
        this.version = version;
        this.scoreVisible = scoreVisible;
        this.overallMetricLabel = overallMetricLabel;
        this.areaMetricLabel = areaMetricLabel;
        this.active = active;
        this.displayOrder = displayOrder;
    }

    public TestDefinitionEntity withSeo(String seoTitle, String seoDescription) {
        this.seoTitle = seoTitle;
        this.seoDescription = seoDescription;
        return this;
    }

    public TestDefinitionEntity withResponseInstruction(String responseInstruction) {
        this.responseInstruction = responseInstruction;
        return this;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getSeoTitle() { return seoTitle == null || seoTitle.isBlank() ? title + " · Spazio Test" : seoTitle; }
    public String getEyebrow() { return eyebrow; }
    public String getDescription() { return description; }
    public String getSeoDescription() { return seoDescription == null || seoDescription.isBlank() ? description : seoDescription; }
    public String getDuration() { return duration; }
    public String getIntroductoryText() { return introductoryText; }
    public String getResponseInstruction() {
        return responseInstruction == null || responseInstruction.isBlank()
                ? "Pensando agli ultimi mesi, con quale frequenza ti è capitato?"
                : responseInstruction;
    }
    public String getVersion() { return version; }
    public boolean isScoreVisible() { return scoreVisible; }
    public String getOverallMetricLabel() {
        return overallMetricLabel == null ? "Presenza complessiva del tratto" : overallMetricLabel;
    }
    public String getAreaMetricLabel() {
        return areaMetricLabel == null ? "Presenza del tratto" : areaMetricLabel;
    }
    public boolean isActive() { return active; }
    public int getDisplayOrder() { return displayOrder; }
}
