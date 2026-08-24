package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.ContributionCheckoutService;
import com.example.testpsicologici.service.ContributionCheckoutService.CheckoutUnavailableException;
import com.example.testpsicologici.service.SiteUrlService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.net.URI;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ContributionCheckoutControllerTest {

    private ContributionCheckoutService checkoutService;
    private SiteUrlService siteUrlService;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        checkoutService = mock(ContributionCheckoutService.class);
        siteUrlService = mock(SiteUrlService.class);
        mockMvc = standaloneSetup(new ContributionCheckoutController(checkoutService, siteUrlService))
                .build();
        when(siteUrlService.baseUrl(org.mockito.ArgumentMatchers.any()))
                .thenReturn("http://localhost:8080");
    }

    @Test
    void redirectsToTheHostedStripeCheckout() throws Exception {
        when(checkoutService.createCheckout(3, "http://localhost:8080"))
                .thenReturn(URI.create("https://checkout.stripe.com/c/pay/cs_test_example"));

        mockMvc.perform(post("/supporto/checkout").param("amount", "3"))
                .andExpect(status().isSeeOther())
                .andExpect(header().string("Location",
                        "https://checkout.stripe.com/c/pay/cs_test_example"))
                .andExpect(header().string("Cache-Control", "no-store"))
                .andExpect(header().string("X-Robots-Tag", "noindex, nofollow, noarchive"));
    }

    @Test
    void rejectsAnAlteredAmount() throws Exception {
        when(checkoutService.createCheckout(2, "http://localhost:8080"))
                .thenThrow(new IllegalArgumentException("Importo del contributo non valido"));

        mockMvc.perform(post("/supporto/checkout").param("amount", "2"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void reportsThatCheckoutIsUnavailableWhileStripeIsDisabled() throws Exception {
        when(checkoutService.createCheckout(1, "http://localhost:8080"))
                .thenThrow(new CheckoutUnavailableException(
                        "I contributi non sono ancora disponibili"));

        mockMvc.perform(post("/supporto/checkout").param("amount", "1"))
                .andExpect(status().isServiceUnavailable());
    }
}
