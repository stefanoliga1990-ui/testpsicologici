package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.NotFoundPathAnalyticsService;
import com.example.testpsicologici.service.TestCatalogue;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.webmvc.autoconfigure.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Component
public class NotFoundErrorViewResolver implements ErrorViewResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotFoundErrorViewResolver.class);
    private final TestCatalogue testCatalogue;
    private final GuideCatalogue guideCatalogue;
    private final NotFoundPathAnalyticsService analyticsService;

    public NotFoundErrorViewResolver(TestCatalogue testCatalogue, GuideCatalogue guideCatalogue,
                                     NotFoundPathAnalyticsService analyticsService) {
        this.testCatalogue = testCatalogue;
        this.guideCatalogue = guideCatalogue;
        this.analyticsService = analyticsService;
    }

    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status,
                                         Map<String, Object> model) {
        if (status != HttpStatus.NOT_FOUND) {
            return null;
        }

        String requestUri = originalRequestUri(request);
        try {
            analyticsService.record(requestUri);
        } catch (RuntimeException analyticsFailure) {
            LOGGER.warn("Impossibile registrare il percorso 404", analyticsFailure);
        }

        ModelAndView page = new ModelAndView("not-found", HttpStatus.NOT_FOUND);
        page.addObject("requestedPath", NotFoundPathAnalyticsService.normalizedPath(requestUri));
        page.addObject("featuredTests", testCatalogue.findFeatured());
        page.addObject("featuredGuides",
                guideCatalogue.findAll().stream().limit(3).toList());
        return page;
    }

    private String originalRequestUri(HttpServletRequest request) {
        Object errorUri = request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        return errorUri instanceof String uri ? uri : request.getRequestURI();
    }
}
