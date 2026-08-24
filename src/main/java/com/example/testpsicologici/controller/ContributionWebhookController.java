package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.ContributionWebhookService;
import com.example.testpsicologici.service.ContributionWebhookService.WebhookProcessingException;
import com.example.testpsicologici.service.StripeWebhookVerifier.InvalidWebhookPayloadException;
import com.stripe.exception.SignatureVerificationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@ConditionalOnProperty(name = "app.payments.stripe.enabled", havingValue = "true")
public class ContributionWebhookController {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ContributionWebhookController.class);

    private final ContributionWebhookService webhookService;

    public ContributionWebhookController(ContributionWebhookService webhookService) {
        this.webhookService = webhookService;
    }

    @PostMapping(value = "/supporto/webhook", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> receive(
            @RequestBody String payload,
            @RequestHeader(name = "Stripe-Signature", required = false) String signature) {
        if (signature == null || signature.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Firma Stripe mancante");
        }
        try {
            ContributionWebhookService.ProcessingResult result =
                    webhookService.process(payload, signature);
            LOGGER.info("Webhook Stripe elaborato con esito {}", result);
            return ResponseEntity.ok()
                    .cacheControl(CacheControl.noStore())
                    .header("X-Robots-Tag", "noindex, nofollow, noarchive")
                    .build();
        } catch (SignatureVerificationException | InvalidWebhookPayloadException exception) {
            LOGGER.warn("Webhook Stripe rifiutato: firma o payload non validi");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Webhook Stripe non valido");
        } catch (WebhookProcessingException exception) {
            LOGGER.error("Webhook Stripe valido ma non elaborabile: {}", exception.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Webhook Stripe non elaborabile");
        }
    }
}
