package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InterpretationRepository extends JpaRepository<InterpretationEntity, Long> {
    Optional<InterpretationEntity> findByTestIdAndScopeAndAreaCodeAndCode(
            String testId, String scope, String areaCode, String code);

    Optional<InterpretationEntity> findByTestIdAndScopeAndAreaCodeIsNullAndCode(
            String testId, String scope, String code);

    @Modifying
    @Query("delete from InterpretationEntity interpretation where interpretation.testId = :testId")
    void deleteByTestId(@Param("testId") String testId);
}
