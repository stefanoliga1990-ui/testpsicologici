package com.example.testpsicologici.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StripeWebhookEventRepository
        extends JpaRepository<StripeWebhookEventEntity, String> {
}
