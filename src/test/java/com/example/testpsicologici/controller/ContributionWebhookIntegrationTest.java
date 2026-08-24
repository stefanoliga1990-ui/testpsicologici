package com.example.testpsicologici.controller;

import com.example.testpsicologici.persistence.ContributionPaymentRepository;
import com.example.testpsicologici.persistence.StripeWebhookEventRepository;
import com.stripe.Stripe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.HexFormat;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(properties = {
        "app.payments.stripe.enabled=true",
        "app.payments.stripe.secret-key=sk_test_webhook_integration_key",
        "app.payments.stripe.webhook-secret=whsec_webhook_integration_secret"
})
class ContributionWebhookIntegrationTest {

    private static final String WEBHOOK_SECRET = "whsec_webhook_integration_secret";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ContributionPaymentRepository paymentRepository;

    @Autowired
    private StripeWebhookEventRepository eventRepository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = webAppContextSetup(context).build();
        eventRepository.deleteAll();
        paymentRepository.deleteAll();
    }

    @Test
    void verifiesAndStoresAPaidContributionOnlyOnce() throws Exception {
        String payload = checkoutEvent(
                "evt_paid_support", "checkout.session.completed",
                "cs_test_paid_support", "paid", "SUPPORT");
        String signature = signature(payload);

        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", signature)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(header().string("Cache-Control", "no-store"))
                .andExpect(header().string("X-Robots-Tag",
                        "noindex, nofollow, noarchive"));

        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", signature)
                        .content(payload))
                .andExpect(status().isOk());

        assertThat(eventRepository.count()).isOne();
        assertThat(paymentRepository.findById("cs_test_paid_support"))
                .hasValueSatisfying(payment -> {
                    assertThat(payment.getAmountTotal()).isEqualTo(300L);
                    assertThat(payment.getCurrency()).isEqualTo("eur");
                    assertThat(payment.getStatus()).isEqualTo("PAID");
                    assertThat(payment.getPaymentIntentId()).isEqualTo("pi_test_paid_support");
                    assertThat(payment.isLiveMode()).isFalse();
                });
    }

    @Test
    void updatesAnAsynchronousContributionWithoutDuplicatingTheSession() throws Exception {
        String pending = checkoutEvent(
                "evt_pending_support", "checkout.session.completed",
                "cs_test_async_support", "unpaid", "SUPPORT");
        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", signature(pending))
                        .content(pending))
                .andExpect(status().isOk());

        String succeeded = checkoutEvent(
                "evt_async_support", "checkout.session.async_payment_succeeded",
                "cs_test_async_support", "paid", "SUPPORT");
        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", signature(succeeded))
                        .content(succeeded))
                .andExpect(status().isOk());

        assertThat(eventRepository.count()).isEqualTo(2L);
        assertThat(paymentRepository.count()).isOne();
        assertThat(paymentRepository.findById("cs_test_async_support"))
                .get().extracting(payment -> payment.getStatus()).isEqualTo("PAID");
    }

    @Test
    void doesNotRegressAPaidContributionWhenEventsArriveOutOfOrder() throws Exception {
        String succeeded = checkoutEvent(
                "evt_async_first", "checkout.session.async_payment_succeeded",
                "cs_test_out_of_order", "paid", "SUPPORT");
        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", signature(succeeded))
                        .content(succeeded))
                .andExpect(status().isOk());

        String completed = checkoutEvent(
                "evt_completed_late", "checkout.session.completed",
                "cs_test_out_of_order", "unpaid", "SUPPORT");
        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", signature(completed))
                        .content(completed))
                .andExpect(status().isOk());

        assertThat(paymentRepository.findById("cs_test_out_of_order"))
                .get().extracting(payment -> payment.getStatus()).isEqualTo("PAID");
    }

    @Test
    void rejectsAnInvalidSignatureWithoutStoringAnything() throws Exception {
        String payload = checkoutEvent(
                "evt_bad_signature", "checkout.session.completed",
                "cs_test_bad_signature", "paid", "SUPPORT");

        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", "t=1,v1=invalid")
                        .content(payload))
                .andExpect(status().isBadRequest());

        assertThat(eventRepository.count()).isZero();
        assertThat(paymentRepository.count()).isZero();
    }

    @Test
    void acknowledgesButDoesNotStoreCheckoutSessionsForOtherPurposes() throws Exception {
        String payload = checkoutEvent(
                "evt_other_purpose", "checkout.session.completed",
                "cs_test_other_purpose", "paid", "PDF_PURCHASE");

        mockMvc.perform(post("/supporto/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Stripe-Signature", signature(payload))
                        .content(payload))
                .andExpect(status().isOk());

        assertThat(eventRepository.count()).isOne();
        assertThat(paymentRepository.count()).isZero();
    }

    private String checkoutEvent(String eventId, String eventType, String sessionId,
                                 String paymentStatus, String purpose) {
        long created = Instant.now().getEpochSecond();
        return """
                {
                  "id": "%s",
                  "object": "event",
                  "api_version": "%s",
                  "created": %d,
                  "livemode": false,
                  "type": "%s",
                  "data": {
                    "object": {
                      "id": "%s",
                      "object": "checkout.session",
                      "amount_total": 300,
                      "created": %d,
                      "currency": "eur",
                      "livemode": false,
                      "metadata": {"purpose": "%s"},
                      "mode": "payment",
                      "payment_intent": "pi_test_%s",
                      "payment_status": "%s",
                      "status": "complete"
                    }
                  }
                }
                """.formatted(eventId, Stripe.API_VERSION, created, eventType, sessionId, created,
                purpose, sessionId.substring("cs_test_".length()), paymentStatus);
    }

    private String signature(String payload) throws Exception {
        long timestamp = Instant.now().getEpochSecond();
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(WEBHOOK_SECRET.getBytes(StandardCharsets.UTF_8),
                "HmacSHA256"));
        byte[] digest = mac.doFinal((timestamp + "." + payload)
                .getBytes(StandardCharsets.UTF_8));
        return "t=" + timestamp + ",v1=" + HexFormat.of().formatHex(digest);
    }
}
