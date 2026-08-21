package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

public interface DailySiteVisitRepository extends JpaRepository<DailySiteVisitEntity, LocalDate> {

    List<DailySiteVisitEntity> findAllByVisitDateBetweenOrderByVisitDateAsc(
            LocalDate startDate, LocalDate endDate);

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update DailySiteVisitEntity visit "
            + "set visit.visitorCount = visit.visitorCount + 1 "
            + "where visit.visitDate = :visitDate")
    int increment(@Param("visitDate") LocalDate visitDate);
}
