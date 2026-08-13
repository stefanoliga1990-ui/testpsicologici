package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.SiteUrlService;
import com.example.testpsicologici.service.TestCatalogue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditorialController {

    private final TestCatalogue catalogue;
    private final SiteUrlService siteUrlService;

    public EditorialController(TestCatalogue catalogue, SiteUrlService siteUrlService) {
        this.catalogue = catalogue;
        this.siteUrlService = siteUrlService;
    }

    @GetMapping("/metodo-e-fonti")
    public String methodAndSources(HttpServletRequest request, Model model) {
        model.addAttribute("tests", catalogue.findAll());
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/metodo-e-fonti"));
        model.addAttribute("siteUrl", siteUrlService.canonicalUrl(request, "/"));
        model.addAttribute("projectUrl", siteUrlService.canonicalUrl(request, "/il-progetto"));
        return "method-and-sources";
    }

    @GetMapping("/il-progetto")
    public String project(HttpServletRequest request, Model model) {
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/il-progetto"));
        model.addAttribute("siteUrl", siteUrlService.canonicalUrl(request, "/"));
        model.addAttribute("methodUrl", siteUrlService.canonicalUrl(request, "/metodo-e-fonti"));
        return "project";
    }
}
