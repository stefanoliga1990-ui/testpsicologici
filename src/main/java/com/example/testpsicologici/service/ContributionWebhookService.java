package com.example.testpsicologici.service;

import com.example.testpsicologici.persistence.ContributionPaymentEntity;
import com.example.testpsicologici.persistence.ContributionPaymentRepository;
import com.example.testpsicologici.persistence.StripeWebhookEventEntity;
import com.example.testpsicologici.persistence.StripeWebhookEventRepository;
import com.stripe.exception.EventDataObjectDeserializationException;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Instant;
import java.util.Locale;
import java.util.Set;

@Service
@ConditionalOnProperty(name = "app.payments.stripe.enabled", havingValue = "true")
public class ContributionWebhookService {

    static final String COMPLETED = "checkout.session.completed";
    static final String ASYNC_SUCCEEDED = "checkout.session.async_payment_succeeded";
    static final String ASYNC_FAILED = "checkout.session.async_payment_failed";
    private static final Set<String> HANDLED_EVENTS =
            Set.of(COMPLETED, ASYNC_SUCCEEDED, ASYNC_FAILED);
    private static final Set<Long> ALLOWED_AMOUNTS = Set.of(100L, 300L, 500L);

    private final StripeWebhookVerifier verifier;
    private final StripeWebhookEventRepository eventRepository;
    private final ContributionPaymentRepository paymentRepository;
    private final Clock clock;

    public ContributionWebhookService(StripeWebhookVerifier verifier,
                                      StripeWebhookEventRepository eventRepository,
                                      ContributionPaymentRepository paymentRepository,
                                      Clock analyticsClock) {
        this.verifier = verifier;
        this.eventRepository = eventRepository;
        this.paymentRepository = paymentRepository;
        this.clock = analyticsClock;
    }

    @Transactional
    public ProcessingResult process(String payload, String signature)
            throws SignatureVerificationException {
        Event event = verifier.verify(payload, signature);
        String eventId = required(event.getId(), "Identificativo evento Stripe mancante");
        String eventType = required(event.getType(), "Tipo evento Stripe mancante");
        if (eventRepository.existsById(eventId)) {
            return ProcessingResult.DUPLICATE;
        }

        if (!HANDLED_EVENTS.contains(eventType)) {
            recordEvent(eventId, eventType, null, ProcessingResult.IGNORED);
            return ProcessingResult.IGNORED;
        }

        Session session = checkoutSession(event);
        String sessionId = required(session.getId(), "Identificativo Checkout Session mancante");
        if (!ContributionCheckoutService.PURPOSE.equals(
                session.getMetadata() == null ? null : session.getMetadata().get("purpose"))) {
            recordEvent(eventId, eventType, sessionId, ProcessingResult.IGNORED);
            return ProcessingResult.IGNORED;
        }

        validateSupportSession(session);
        ProcessingResult result = statusFor(eventType, session.getPaymentStatus());
        upsertPayment(session, result);
        recordEvent(eventId, eventType, sessionId, result);
        return result;
    }

    private Session checkoutSession(Event event) {
        StripeObject object = event.getDataObjectDeserializer().getObject().orElseGet(() -> {
            try {
                return event.getDataObjectDeserializer().deserializeUnsafe();
            } catch (EventDataObjectDeserializationException exception) {
                throw new WebhookProcessingException(
                        "Impossibile interpretare la Checkout Session Stripe", exception);
            }
        });
        if (!(object instanceof Session session)) {
            throw new WebhookProcessingException(
                    "L'evento Stripe non contiene una Checkout Session");
        }
        return session;
    }

    private void validateSupportSession(Session session) {
        if (!"payment".equals(session.getMode())) {
            throw new WebhookProcessingException("Modalità della Checkout Session inattesa");
        }
        String currency = session.getCurrency();
        if (currency == null || !"eur".equals(currency.toLowerCase(Locale.ROOT))) {
            throw new WebhookProcessingException("Valuta del contributo inattesa");
        }
        if (session.getAmountTotal() == null || !ALLOWED_AMOUNTS.contains(session.getAmountTotal())) {
            throw new WebhookProcessingException("Importo del contributo inatteso");
        }
    }

    private ProcessingResult statusFor(String eventType, String paymentStatus) {
        if (ASYNC_FAILED.equals(eventType)) {
            return ProcessingResult.FAILED;
        }
        if (ASYNC_SUCCEEDED.equals(eventType) || "paid".equals(paymentStatus)) {
            return ProcessingResult.PAID;
        }
        return ProcessingResult.PENDING;
    }

    private void upsertPayment(Session session, ProcessingResult result) {
        Instant now = clock.instant();
        ContributionPaymentEntity payment = paymentRepository.findById(session.getId())
                .map(existing -> validateExisting(existing, session))
                .orElseGet(() -> new ContributionPaymentEntity(
                        session.getId(),
                        session.getPaymentIntent(),
                        session.getAmountTotal(),
                        session.getCurrency().toLowerCase(Locale.ROOT),
                        result.name(),
                        Boolean.TRUE.equals(session.getLivemode()),
                        session.getCreated() == null
                                ? now
                                : Instant.ofEpochSecond(session.getCreated()),
                        now));
        payment.updateStatus(mergedStatus(payment.getStatus(), result),
                session.getPaymentIntent(), now);
        paymentRepository.save(payment);
    }

    private String mergedStatus(String currentStatus, ProcessingResult incoming) {
        if (ProcessingResult.PAID.name().equals(currentStatus)
                || incoming == ProcessingResult.PAID) {
            return ProcessingResult.PAID.name();
        }
        if (ProcessingResult.FAILED.name().equals(currentStatus)
                || incoming == ProcessingResult.FAILED) {
            return ProcessingResult.FAILED.name();
        }
        return ProcessingResult.PENDING.name();
    }

    private ContributionPaymentEntity validateExisting(ContributionPaymentEntity existing,
                                                         Session session) {
        if (existing.getAmountTotal() != session.getAmountTotal()
                || !existing.getCurrency().equalsIgnoreCase(session.getCurrency())
                || existing.isLiveMode() != Boolean.TRUE.equals(session.getLivemode())) {
            throw new WebhookProcessingException(
                    "La Checkout Session non coincide con il contributo già registrato");
        }
        return existing;
    }

    private void recordEvent(String eventId, String eventType, String sessionId,
                             ProcessingResult result) {
        eventRepository.save(new StripeWebhookEventEntity(
                eventId, eventType, sessionId, result.name(), clock.instant()));
    }

    private String required(String value, String message) {
        if (value == null || value.isBlank()) {
            throw new WebhookProcessingException(message);
        }
        return value;
    }

    public enum ProcessingResult {
        PAID,
        PENDING,
        FAILED,
        IGNORED,
        DUPLICATE
    }

    public static class WebhookProcessingException extends RuntimeException {
        public WebhookProcessingException(String message) {
            super(message);
        }

        public WebhookProcessingException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
