package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestDefinitionRepository extends JpaRepository<TestDefinitionEntity, String> {
    List<TestDefinitionEntity> findByActiveTrueOrderByDisplayOrderAsc();
    List<TestDefinitionEntity> findTop3ByActiveTrueOrderByDisplayOrderAsc();
    List<TestDefinitionEntity> findAllByOrderByDisplayOrderAsc();
}
