package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.ContributionCheckoutService;
import com.example.testpsicologici.service.ContributionCheckoutService.CheckoutUnavailableException;
import com.example.testpsicologici.service.SiteUrlService;
import com.stripe.exception.StripeException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;

@RestController
public class ContributionCheckoutController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContributionCheckoutController.class);

    private final ContributionCheckoutService checkoutService;
    private final SiteUrlService siteUrlService;

    public ContributionCheckoutController(ContributionCheckoutService checkoutService,
                                          SiteUrlService siteUrlService) {
        this.checkoutService = checkoutService;
        this.siteUrlService = siteUrlService;
    }

    @PostMapping("/supporto/checkout")
    public ResponseEntity<Void> createCheckout(@RequestParam int amount,
                                               HttpServletRequest request) {
        try {
            URI checkoutUri = checkoutService.createCheckout(amount, siteUrlService.baseUrl(request));
            return ResponseEntity.status(HttpStatus.SEE_OTHER)
                    .location(checkoutUri)
                    .cacheControl(CacheControl.noStore())
                    .header("X-Robots-Tag", "noindex, nofollow, noarchive")
                    .build();
        } catch (IllegalArgumentException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exception.getMessage());
        } catch (CheckoutUnavailableException exception) {
            LOGGER.warn("Checkout dei contributi non disponibile: {}", exception.getMessage());
            throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,
                    "Il pagamento non è temporaneamente disponibile");
        } catch (StripeException exception) {
            LOGGER.error("Creazione della sessione Stripe non riuscita", exception);
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY,
                    "Il servizio di pagamento non ha risposto correttamente");
        }
    }
}
