package com.example.testpsicologici.model;

import java.time.Instant;

public record NotFoundPathStat(
        String path,
        long hits,
        Instant firstSeenAt,
        Instant lastSeenAt) {
}
