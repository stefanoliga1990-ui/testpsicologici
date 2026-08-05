package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "test_area", uniqueConstraints = @UniqueConstraint(columnNames = {"test_id", "code"}))
public class TestAreaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "test_id", nullable = false)
    private String testId;
    @Column(nullable = false)
    private String code;
    @Column(name = "internal_name", nullable = false)
    private String internalName;
    @Column(name = "display_order")
    private int displayOrder;

    protected TestAreaEntity() {
    }

    public TestAreaEntity(String testId, String code, String internalName, int displayOrder) {
        this.testId = testId;
        this.code = code;
        this.internalName = internalName;
        this.displayOrder = displayOrder;
    }

    public String getTestId() { return testId; }
    public String getCode() { return code; }
    public String getInternalName() { return internalName; }
    public int getDisplayOrder() { return displayOrder; }
}
