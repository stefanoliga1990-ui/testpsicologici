package com.example.testpsicologici.persistence;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "contribution_payment")
public class ContributionPaymentEntity {

    @Id
    @Column(name = "checkout_session_id", nullable = false, length = 255)
    private String checkoutSessionId;

    @Column(name = "payment_intent_id", length = 255)
    private String paymentIntentId;

    @Column(name = "amount_total", nullable = false)
    private long amountTotal;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(nullable = false, length = 24)
    private String status;

    @Column(name = "live_mode", nullable = false)
    private boolean liveMode;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    protected ContributionPaymentEntity() {
    }

    public ContributionPaymentEntity(String checkoutSessionId, String paymentIntentId,
                                     long amountTotal, String currency, String status,
                                     boolean liveMode, Instant createdAt, Instant updatedAt) {
        this.checkoutSessionId = checkoutSessionId;
        this.paymentIntentId = paymentIntentId;
        this.amountTotal = amountTotal;
        this.currency = currency;
        this.status = status;
        this.liveMode = liveMode;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void updateStatus(String newStatus, String newPaymentIntentId, Instant updatedAt) {
        status = newStatus;
        if (paymentIntentId == null && newPaymentIntentId != null) {
            paymentIntentId = newPaymentIntentId;
        }
        this.updatedAt = updatedAt;
    }

    public String getCheckoutSessionId() {
        return checkoutSessionId;
    }

    public String getPaymentIntentId() {
        return paymentIntentId;
    }

    public long getAmountTotal() {
        return amountTotal;
    }

    public String getCurrency() {
        return currency;
    }

    public String getStatus() {
        return status;
    }

    public boolean isLiveMode() {
        return liveMode;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
