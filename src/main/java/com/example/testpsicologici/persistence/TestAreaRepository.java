package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestAreaRepository extends JpaRepository<TestAreaEntity, Long> {
    List<TestAreaEntity> findByTestIdOrderByDisplayOrderAsc(String testId);

    @Modifying
    @Query("delete from TestAreaEntity area where area.testId = :testId")
    void deleteByTestId(@Param("testId") String testId);
}
