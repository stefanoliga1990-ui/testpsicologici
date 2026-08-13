package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestReferenceRepository extends JpaRepository<TestReferenceEntity, Long> {

    List<TestReferenceEntity> findByTestIdOrderByDisplayOrderAsc(String testId);

    @Modifying
    @Query("delete from TestReferenceEntity reference where reference.testId = :testId")
    void deleteByTestId(@Param("testId") String testId);
}
