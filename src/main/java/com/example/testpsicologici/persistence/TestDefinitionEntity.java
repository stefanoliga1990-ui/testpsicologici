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
    private String eyebrow;
    @Column(length = 1200)
    private String description;
    private String duration;
    @Column(name = "introductory_text", length = 2400)
    private String introductoryText;
    private String version;
    @Column(name = "score_visible")
    private boolean scoreVisible;
    private boolean active;
    @Column(name = "display_order")
    private int displayOrder;

    protected TestDefinitionEntity() {
    }

    public TestDefinitionEntity(String id, String title, String eyebrow, String description, String duration,
                                String introductoryText, String version, boolean scoreVisible, boolean active,
                                int displayOrder) {
        this.id = id;
        this.title = title;
        this.eyebrow = eyebrow;
        this.description = description;
        this.duration = duration;
        this.introductoryText = introductoryText;
        this.version = version;
        this.scoreVisible = scoreVisible;
        this.active = active;
        this.displayOrder = displayOrder;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getEyebrow() { return eyebrow; }
    public String getDescription() { return description; }
    public String getDuration() { return duration; }
    public String getIntroductoryText() { return introductoryText; }
    public String getVersion() { return version; }
    public boolean isScoreVisible() { return scoreVisible; }
    public boolean isActive() { return active; }
    public int getDisplayOrder() { return displayOrder; }
}
