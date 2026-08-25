package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

public interface NotFoundPathRepository extends JpaRepository<NotFoundPathEntity, String> {

    List<NotFoundPathEntity> findTop20ByOrderByHitCountDescLastSeenAtDesc();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Transactional
    @Query("update NotFoundPathEntity entry "
            + "set entry.hitCount = entry.hitCount + 1, entry.lastSeenAt = :seenAt "
            + "where entry.path = :path")
    int increment(@Param("path") String path, @Param("seenAt") Instant seenAt);
}
