package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.SiteUrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivacyController {

    private final SiteUrlService siteUrlService;
    private final String privacyContactEmail;

    public PrivacyController(SiteUrlService siteUrlService,
                             @Value("${app.privacy-contact-email:}") String privacyContactEmail) {
        this.siteUrlService = siteUrlService;
        this.privacyContactEmail = privacyContactEmail;
    }

    @GetMapping("/privacy-e-cookie")
    public String privacy(HttpServletRequest request, Model model) {
        model.addAttribute("privacyContactEmail", privacyContactEmail);
        model.addAttribute("reactPageData", ReactPageData.of(
                "privacy", "privacyContactEmail", privacyContactEmail));
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/privacy-e-cookie"));
        return "privacy";
    }
}
