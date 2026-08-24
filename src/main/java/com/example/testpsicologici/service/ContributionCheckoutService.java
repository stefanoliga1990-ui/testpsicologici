package com.example.testpsicologici.service;

import com.stripe.StripeClient;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class ContributionCheckoutService {

    static final String PURPOSE = "SUPPORT";

    private final ObjectProvider<StripeClient> stripeClientProvider;

    public ContributionCheckoutService(ObjectProvider<StripeClient> stripeClientProvider) {
        this.stripeClientProvider = stripeClientProvider;
    }

    public URI createCheckout(int amountInEuros, String baseUrl) throws StripeException {
        long amountInCents = amountInCents(amountInEuros);
        StripeClient stripeClient = stripeClientProvider.getIfAvailable();
        if (stripeClient == null) {
            throw new CheckoutUnavailableException("I contributi non sono ancora disponibili");
        }

        Session session = stripeClient.checkout().sessions().create(
                checkoutParameters(amountInCents, baseUrl));
        return validatedCheckoutUri(session == null ? null : session.getUrl());
    }

    static long amountInCents(int amountInEuros) {
        return switch (amountInEuros) {
            case 1 -> 100L;
            case 3 -> 300L;
            case 5 -> 500L;
            default -> throw new IllegalArgumentException("Importo del contributo non valido");
        };
    }

    static SessionCreateParams checkoutParameters(long amountInCents, String baseUrl) {
        String normalizedBaseUrl = baseUrl.endsWith("/")
                ? baseUrl.substring(0, baseUrl.length() - 1)
                : baseUrl;

        SessionCreateParams.LineItem.PriceData.ProductData product =
                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                        .setName("Contributo volontario a Spazio Test")
                        .setDescription("Sostegno facoltativo al progetto, senza contenuti o vantaggi in cambio")
                        .build();

        SessionCreateParams.LineItem.PriceData price =
                SessionCreateParams.LineItem.PriceData.builder()
                .setCurrency("eur")
                .setUnitAmount(amountInCents)
                .setProductData(product)
                .build();

        SessionCreateParams.PaymentIntentData paymentIntent =
                SessionCreateParams.PaymentIntentData.builder()
                        .putMetadata("purpose", PURPOSE)
                        .build();

        return SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(normalizedBaseUrl
                        + "/supporto/grazie?session_id={CHECKOUT_SESSION_ID}")
                .setCancelUrl(normalizedBaseUrl + "/supporto/annullato")
                .setPaymentIntentData(paymentIntent)
                .putMetadata("purpose", PURPOSE)
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(price)
                        .build())
                .build();
    }

    private URI validatedCheckoutUri(String checkoutUrl) {
        if (checkoutUrl == null || checkoutUrl.isBlank()) {
            throw new CheckoutUnavailableException("Stripe non ha restituito una pagina di pagamento");
        }
        URI uri = URI.create(checkoutUrl);
        if (!"https".equalsIgnoreCase(uri.getScheme())
                || !"checkout.stripe.com".equalsIgnoreCase(uri.getHost())) {
            throw new CheckoutUnavailableException("Stripe ha restituito un indirizzo inatteso");
        }
        return uri;
    }

    public static class CheckoutUnavailableException extends RuntimeException {
        public CheckoutUnavailableException(String message) {
            super(message);
        }
    }
}
