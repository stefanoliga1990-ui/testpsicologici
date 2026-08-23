package com.example.testpsicologici.persistence;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class DailyTestCompletionId implements Serializable {

    private String testId;
    private LocalDate completionDate;

    public DailyTestCompletionId() {
    }

    public DailyTestCompletionId(String testId, LocalDate completionDate) {
        this.testId = testId;
        this.completionDate = completionDate;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof DailyTestCompletionId that)) return false;
        return Objects.equals(testId, that.testId)
                && Objects.equals(completionDate, that.completionDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId, completionDate);
    }
}
