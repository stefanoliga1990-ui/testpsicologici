package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "test_reference", uniqueConstraints = @UniqueConstraint(columnNames = {"test_id", "display_order"}))
public class TestReferenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "test_id", nullable = false)
    private String testId;

    @Column(nullable = false, length = 300)
    private String title;

    @Column(nullable = false, length = 1000)
    private String url;

    @Column(name = "display_order", nullable = false)
    private int displayOrder;

    protected TestReferenceEntity() {
    }

    public TestReferenceEntity(String testId, String title, String url, int displayOrder) {
        this.testId = testId;
        this.title = title;
        this.url = url;
        this.displayOrder = displayOrder;
    }

    public String getTestId() { return testId; }
    public String getTitle() { return title; }
    public String getUrl() { return url; }
    public int getDisplayOrder() { return displayOrder; }
}
