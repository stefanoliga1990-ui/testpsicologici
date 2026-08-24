package com.example.testpsicologici.service;

import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;

public final class StripeWebhookVerifier {

    private final String webhookSecret;

    public StripeWebhookVerifier(String webhookSecret) {
        this.webhookSecret = webhookSecret;
    }

    public Event verify(String payload, String signature) throws SignatureVerificationException {
        try {
            return Webhook.constructEvent(payload, signature, webhookSecret);
        } catch (SignatureVerificationException exception) {
            throw exception;
        } catch (RuntimeException exception) {
            throw new InvalidWebhookPayloadException("Payload Stripe non valido", exception);
        }
    }

    public static class InvalidWebhookPayloadException extends RuntimeException {
        public InvalidWebhookPayloadException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
