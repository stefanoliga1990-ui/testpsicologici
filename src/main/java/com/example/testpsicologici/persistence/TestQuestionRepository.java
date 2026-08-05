package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TestQuestionRepository extends JpaRepository<TestQuestionEntity, Long> {
    List<TestQuestionEntity> findByTestIdOrderByPositionAsc(String testId);

    @Modifying
    @Query("delete from TestQuestionEntity question where question.testId = :testId")
    void deleteByTestId(@Param("testId") String testId);
}
