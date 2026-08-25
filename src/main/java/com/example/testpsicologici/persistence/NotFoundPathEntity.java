package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "not_found_path")
public class NotFoundPathEntity {

    @Id
    @Column(name = "path", nullable = false, length = 512)
    private String path;

    @Column(name = "hit_count", nullable = false)
    private long hitCount;

    @Column(name = "first_seen_at", nullable = false)
    private Instant firstSeenAt;

    @Column(name = "last_seen_at", nullable = false)
    private Instant lastSeenAt;

    protected NotFoundPathEntity() {
    }

    public NotFoundPathEntity(String path, long hitCount, Instant firstSeenAt, Instant lastSeenAt) {
        this.path = path;
        this.hitCount = hitCount;
        this.firstSeenAt = firstSeenAt;
        this.lastSeenAt = lastSeenAt;
    }

    public String getPath() {
        return path;
    }

    public long getHitCount() {
        return hitCount;
    }

    public Instant getFirstSeenAt() {
        return firstSeenAt;
    }

    public Instant getLastSeenAt() {
        return lastSeenAt;
    }
}
