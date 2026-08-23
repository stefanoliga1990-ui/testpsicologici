package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@IdClass(DailyTestCompletionId.class)
@Table(name = "daily_test_completion")
public class DailyTestCompletionEntity {

    @Id
    @Column(name = "test_id", nullable = false, length = 120)
    private String testId;

    @Id
    @Column(name = "completion_date", nullable = false)
    private LocalDate completionDate;

    @Column(name = "completion_count", nullable = false)
    private long completionCount;

    protected DailyTestCompletionEntity() {
    }

    public DailyTestCompletionEntity(String testId, LocalDate completionDate, long completionCount) {
        this.testId = testId;
        this.completionDate = completionDate;
        this.completionCount = completionCount;
    }

    public String getTestId() {
        return testId;
    }

    public LocalDate getCompletionDate() {
        return completionDate;
    }

    public long getCompletionCount() {
        return completionCount;
    }
}
