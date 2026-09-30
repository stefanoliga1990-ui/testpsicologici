package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "test_question", uniqueConstraints = @UniqueConstraint(columnNames = {"test_id", "question_order"}))
public class TestQuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "test_id", nullable = false)
    private String testId;
    @Column(name = "area_code", nullable = false)
    private String areaCode;
    @Column(name = "question_order", nullable = false)
    private int position;
    @Column(nullable = false, length = 1200)
    private String text;
    @Column(length = 800)
    private String example;
    @Column(name = "indicator_code", length = 8)
    private String indicatorCode;

    protected TestQuestionEntity() {
    }

    public TestQuestionEntity(String testId, String areaCode, int position, String text, String example) {
        this(testId, areaCode, position, text, example, null);
    }

    public TestQuestionEntity(String testId, String areaCode, int position, String text, String example,
                              String indicatorCode) {
        this.testId = testId;
        this.areaCode = areaCode;
        this.position = position;
        this.text = text;
        this.example = example;
        this.indicatorCode = indicatorCode;
    }

    public String getAreaCode() { return areaCode; }
    public int getPosition() { return position; }
    public String getText() { return text; }
    public String getExample() { return example; }
    public String getIndicatorCode() { return indicatorCode; }
}
