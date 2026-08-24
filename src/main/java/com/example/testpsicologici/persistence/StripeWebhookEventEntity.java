package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "stripe_webhook_event")
public class StripeWebhookEventEntity {

    @Id
    @Column(name = "event_id", nullable = false, length = 255)
    private String eventId;

    @Column(name = "event_type", nullable = false, length = 100)
    private String eventType;

    @Column(name = "checkout_session_id", length = 255)
    private String checkoutSessionId;

    @Column(nullable = false, length = 24)
    private String outcome;

    @Column(name = "processed_at", nullable = false)
    private Instant processedAt;

    protected StripeWebhookEventEntity() {
    }

    public StripeWebhookEventEntity(String eventId, String eventType,
                                    String checkoutSessionId, String outcome,
                                    Instant processedAt) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.checkoutSessionId = checkoutSessionId;
        this.outcome = outcome;
        this.processedAt = processedAt;
    }

    public String getEventId() {
        return eventId;
    }

    public String getEventType() {
        return eventType;
    }

    public String getCheckoutSessionId() {
        return checkoutSessionId;
    }

    public String getOutcome() {
        return outcome;
    }

    public Instant getProcessedAt() {
        return processedAt;
    }
}
