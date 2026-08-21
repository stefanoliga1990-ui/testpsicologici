package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "daily_site_visit")
public class DailySiteVisitEntity {

    @Id
    @Column(name = "visit_date", nullable = false)
    private LocalDate visitDate;

    @Column(name = "visitor_count", nullable = false)
    private long visitorCount;

    protected DailySiteVisitEntity() {
    }

    public DailySiteVisitEntity(LocalDate visitDate, long visitorCount) {
        this.visitDate = visitDate;
        this.visitorCount = visitorCount;
    }

    public LocalDate getVisitDate() {
        return visitDate;
    }

    public long getVisitorCount() {
        return visitorCount;
    }
}
