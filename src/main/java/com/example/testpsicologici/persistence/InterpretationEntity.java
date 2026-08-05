package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "test_interpretation", uniqueConstraints = @UniqueConstraint(columnNames = {"test_id", "scope", "area_code", "code"}))
public class InterpretationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "test_id", nullable = false)
    private String testId;
    @Column(nullable = false)
    private String scope;
    @Column(name = "area_code")
    private String areaCode;
    @Column(nullable = false)
    private String code;
    private String title;
    @Column(length = 2400)
    private String description;
    @Column(length = 2400)
    private String detail;

    protected InterpretationEntity() {
    }

    public InterpretationEntity(String testId, String scope, String areaCode, String code,
                                String title, String description, String detail) {
        this.testId = testId;
        this.scope = scope;
        this.areaCode = areaCode;
        this.code = code;
        this.title = title;
        this.description = description;
        this.detail = detail;
    }

    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getDetail() { return detail; }
}
