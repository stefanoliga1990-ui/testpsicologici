package com.example.testpsicologici.config;

import com.stripe.StripeClient;
import com.example.testpsicologici.service.StripeWebhookVerifier;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

class StripeConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(StripeConfiguration.class);

    @Test
    void doesNotCreateClientWhenPaymentsAreDisabled() {
        contextRunner.run(context -> assertThat(context).doesNotHaveBean(StripeClient.class));
    }

    @Test
    void createsClientWhenTestKeyIsConfigured() {
        contextRunner
                .withPropertyValues(
                        "app.payments.stripe.enabled=true",
                        "app.payments.stripe.secret-key=sk_test_placeholder",
                        "app.payments.stripe.webhook-secret=whsec_test_placeholder")
                .run(context -> {
                    assertThat(context).hasSingleBean(StripeClient.class);
                    assertThat(context).hasSingleBean(StripeWebhookVerifier.class);
                });
    }

    @Test
    void refusesToStartWhenEnabledWithoutSecretKey() {
        contextRunner
                .withPropertyValues("app.payments.stripe.enabled=true")
                .run(context -> {
                    assertThat(context).hasFailed();
                    assertThat(context.getStartupFailure()).hasRootCauseMessage(
                            "Stripe payments are enabled, but STRIPE_SECRET_KEY is not configured");
                });
    }

    @Test
    void refusesToStartWhenEnabledWithoutWebhookSecret() {
        contextRunner
                .withPropertyValues(
                        "app.payments.stripe.enabled=true",
                        "app.payments.stripe.secret-key=sk_test_placeholder")
                .run(context -> {
                    assertThat(context).hasFailed();
                    assertThat(context.getStartupFailure()).hasRootCauseMessage(
                            "Stripe payments are enabled, but STRIPE_WEBHOOK_SECRET is not configured");
                });
    }
}
