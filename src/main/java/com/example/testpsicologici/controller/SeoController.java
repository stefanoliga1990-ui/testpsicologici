package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.service.SiteUrlService;
import com.example.testpsicologici.service.TestCatalogue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.time.Duration;

@RestController
public class SeoController {

    private final TestCatalogue catalogue;
    private final SiteUrlService siteUrlService;

    public SeoController(TestCatalogue catalogue, SiteUrlService siteUrlService) {
        this.catalogue = catalogue;
        this.siteUrlService = siteUrlService;
    }

    @GetMapping(value = "/robots.txt", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> robots(HttpServletRequest request) {
        String body = "User-agent: *\n"
                + "Allow: /\n\n"
                + "Sitemap: " + siteUrlService.canonicalUrl(request, "/sitemap.xml") + "\n";
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofHours(12)).cachePublic())
                .body(body);
    }

    @GetMapping(value = "/sitemap.xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> sitemap(HttpServletRequest request) {
        StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n")
                .append("<urlset xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">\n")
                .append(urlEntry(siteUrlService.canonicalUrl(request, "/"), "1.0"));
        for (PsychologicalTest test : catalogue.findAll()) {
            xml.append(urlEntry(siteUrlService.canonicalUrl(request, "/test/" + test.id()), "0.8"));
        }
        xml.append("</urlset>\n");

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(Duration.ofHours(12)).cachePublic())
                .body(xml.toString());
    }

    private String urlEntry(String location, String priority) {
        return "  <url><loc>" + HtmlUtils.htmlEscape(location) + "</loc><priority>" + priority + "</priority></url>\n";
    }
}
