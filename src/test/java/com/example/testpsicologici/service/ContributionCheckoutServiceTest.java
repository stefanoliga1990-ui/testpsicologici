package com.example.testpsicologici.service;

import com.stripe.StripeClient;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.service.CheckoutService;
import com.stripe.service.checkout.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;

import java.net.URI;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ContributionCheckoutServiceTest {

    @Test
    void acceptsOnlyTheConfiguredContributionAmounts() {
        assertThat(ContributionCheckoutService.amountInCents(1)).isEqualTo(100L);
        assertThat(ContributionCheckoutService.amountInCents(3)).isEqualTo(300L);
        assertThat(ContributionCheckoutService.amountInCents(5)).isEqualTo(500L);
        assertThatThrownBy(() -> ContributionCheckoutService.amountInCents(2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Importo del contributo non valido");
    }

    @Test
    void createsAHostedCheckoutWithoutPsychologicalMetadata() throws Exception {
        StripeClient client = mock(StripeClient.class);
        CheckoutService checkout = mock(CheckoutService.class);
        SessionService sessions = mock(SessionService.class);
        Session session = mock(Session.class);
        when(client.checkout()).thenReturn(checkout);
        when(checkout.sessions()).thenReturn(sessions);
        when(session.getUrl()).thenReturn("https://checkout.stripe.com/c/pay/cs_test_example");
        when(sessions.create(any(SessionCreateParams.class))).thenReturn(session);

        ObjectProvider<StripeClient> provider = providerFor(client);
        ContributionCheckoutService service = new ContributionCheckoutService(provider);

        URI checkoutUri = service.createCheckout(3, "http://localhost:8080/");

        assertThat(checkoutUri).isEqualTo(URI.create(
                "https://checkout.stripe.com/c/pay/cs_test_example"));
        var parameters = forClass(SessionCreateParams.class);
        verify(sessions).create(parameters.capture());
        SessionCreateParams checkoutParameters = parameters.getValue();
        assertThat(checkoutParameters.getMode()).isEqualTo(SessionCreateParams.Mode.PAYMENT);
        assertThat(checkoutParameters.getSuccessUrl())
                .isEqualTo("http://localhost:8080/supporto/grazie"
                        + "?session_id={CHECKOUT_SESSION_ID}");
        assertThat(checkoutParameters.getCancelUrl())
                .isEqualTo("http://localhost:8080/supporto/annullato");
        assertThat(checkoutParameters.getMetadata())
                .containsExactly(Map.entry("purpose", "SUPPORT"));
        assertThat(checkoutParameters.getPaymentIntentData().getMetadata())
                .containsExactly(Map.entry("purpose", "SUPPORT"));
        assertThat(checkoutParameters.getLineItems()).singleElement().satisfies(lineItem -> {
            assertThat(lineItem.getQuantity()).isEqualTo(1L);
            assertThat(lineItem.getPriceData().getCurrency()).isEqualTo("eur");
            assertThat(lineItem.getPriceData().getUnitAmount()).isEqualTo(300L);
            assertThat(lineItem.getPriceData().getProductData().getName())
                    .isEqualTo("Contributo volontario a Spazio Test");
        });
    }

    @Test
    void remainsUnavailableUntilStripeIsExplicitlyEnabled() {
        ContributionCheckoutService service = new ContributionCheckoutService(providerFor(null));

        assertThatThrownBy(() -> service.createCheckout(1, "http://localhost:8080"))
                .isInstanceOf(ContributionCheckoutService.CheckoutUnavailableException.class)
                .hasMessage("I contributi non sono ancora disponibili");
    }

    @SuppressWarnings("unchecked")
    private ObjectProvider<StripeClient> providerFor(StripeClient client) {
        ObjectProvider<StripeClient> provider = mock(ObjectProvider.class);
        when(provider.getIfAvailable()).thenReturn(client);
        return provider;
    }
}
