package com.example.testpsicologici.config;

import com.example.testpsicologici.service.StripeWebhookVerifier;
import com.stripe.StripeClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfiguration {

    @Bean
    @ConditionalOnProperty(name = "app.payments.stripe.enabled", havingValue = "true")
    StripeClient stripeClient(@Value("${app.payments.stripe.secret-key:}") String secretKey) {
        if (secretKey == null || secretKey.isBlank()) {
            throw new IllegalStateException(
                    "Stripe payments are enabled, but STRIPE_SECRET_KEY is not configured");
        }
        if (!secretKey.startsWith("sk_test_") && !secretKey.startsWith("sk_live_")) {
            throw new IllegalStateException("STRIPE_SECRET_KEY has an unexpected format");
        }
        return new StripeClient(secretKey);
    }

    @Bean
    @ConditionalOnProperty(name = "app.payments.stripe.enabled", havingValue = "true")
    StripeWebhookVerifier stripeWebhookVerifier(
            @Value("${app.payments.stripe.webhook-secret:}") String webhookSecret) {
        if (webhookSecret == null || webhookSecret.isBlank()) {
            throw new IllegalStateException(
                    "Stripe payments are enabled, but STRIPE_WEBHOOK_SECRET is not configured");
        }
        if (!webhookSecret.startsWith("whsec_")) {
            throw new IllegalStateException("STRIPE_WEBHOOK_SECRET has an unexpected format");
        }
        return new StripeWebhookVerifier(webhookSecret);
    }
}
