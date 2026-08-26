package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.InformationGuide;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.SiteUrlService;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.TopicClusterCatalogue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class GuideController {

    private final GuideCatalogue guideCatalogue;
    private final TestCatalogue testCatalogue;
    private final SiteUrlService siteUrlService;
    private final TopicClusterCatalogue topicClusterCatalogue;

    public GuideController(GuideCatalogue guideCatalogue, TestCatalogue testCatalogue,
                           SiteUrlService siteUrlService,
                           TopicClusterCatalogue topicClusterCatalogue) {
        this.guideCatalogue = guideCatalogue;
        this.testCatalogue = testCatalogue;
        this.siteUrlService = siteUrlService;
        this.topicClusterCatalogue = topicClusterCatalogue;
    }

    @GetMapping("/approfondimenti")
    public String index(HttpServletRequest request, Model model) {
        var guides = guideCatalogue.findAll();
        var topicClusters = topicClusterCatalogue.findAll();
        model.addAttribute("guides", guides);
        model.addAttribute("topicClusters", topicClusters);
        model.addAttribute("guidesByTestId", guides.stream().collect(java.util.stream.Collectors.toMap(
                InformationGuide::testId, java.util.function.Function.identity(),
                (first, ignored) -> first, java.util.LinkedHashMap::new)));
        model.addAttribute("reactPageData", ReactPageData.of(
                "guides", "guides", guides, "topicClusters", topicClusters));
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/approfondimenti"));
        model.addAttribute("siteUrl", siteUrlService.canonicalUrl(request, "/"));
        return "guides";
    }

    @GetMapping("/approfondimenti/{slug}")
    public String detail(@PathVariable String slug, HttpServletRequest request, Model model) {
        InformationGuide guide = guideCatalogue.findBySlug(slug)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Approfondimento non trovato"));
        model.addAttribute("guide", guide);
        var test = testCatalogue.findById(guide.testId());
        var topicCluster = topicClusterCatalogue.findByTestId(guide.testId()).orElse(null);
        var relatedGuides = guideCatalogue.findSuggestionsByTestIds(
                topicClusterCatalogue.findRelatedTestIds(guide.testId(), 3));
        model.addAttribute("test", test);
        model.addAttribute("topicCluster", topicCluster);
        model.addAttribute("relatedGuides", relatedGuides);
        model.addAttribute("reactPageData", ReactPageData.of(
                "guide", "guide", guide, "test", test,
                "topicCluster", topicCluster, "relatedGuides", relatedGuides));
        model.addAttribute("canonicalUrl",
                siteUrlService.canonicalUrl(request, "/approfondimenti/" + guide.slug()));
        model.addAttribute("siteUrl", siteUrlService.canonicalUrl(request, "/"));
        model.addAttribute("guidesUrl", siteUrlService.canonicalUrl(request, "/approfondimenti"));
        model.addAttribute("projectUrl", siteUrlService.canonicalUrl(request, "/il-progetto"));
        return "guide";
    }
}
