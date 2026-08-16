package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.SiteUrlService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class BrandAssetModelAdvice {

    private final SiteUrlService siteUrlService;

    public BrandAssetModelAdvice(SiteUrlService siteUrlService) {
        this.siteUrlService = siteUrlService;
    }

    @ModelAttribute
    public void addBrandAssetUrls(HttpServletRequest request, Model model) {
        model.addAttribute("brandLogoUrl",
                siteUrlService.canonicalUrl(request, "/images/brand/logo-512.png"));
        model.addAttribute("socialImageUrl",
                siteUrlService.canonicalUrl(request, "/images/brand/og-spazio-test.png"));
    }
}
