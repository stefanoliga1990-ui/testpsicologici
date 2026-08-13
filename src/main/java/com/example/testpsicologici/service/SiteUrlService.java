package com.example.testpsicologici.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SiteUrlService {

    private final String configuredSiteUrl;

    public SiteUrlService(@Value("${app.site-url:}") String configuredSiteUrl) {
        this.configuredSiteUrl = stripTrailingSlash(configuredSiteUrl == null ? "" : configuredSiteUrl.trim());
    }

    public String baseUrl(HttpServletRequest request) {
        if (!configuredSiteUrl.isBlank()) return configuredSiteUrl;

        String scheme = request.getScheme();
        int port = request.getServerPort();
        boolean defaultPort = ("http".equalsIgnoreCase(scheme) && port == 80)
                || ("https".equalsIgnoreCase(scheme) && port == 443);
        String authority = request.getServerName() + (defaultPort ? "" : ":" + port);
        return stripTrailingSlash(scheme + "://" + authority + request.getContextPath());
    }

    public String canonicalUrl(HttpServletRequest request, String path) {
        String normalizedPath = path == null || path.isBlank() || "/".equals(path)
                ? ""
                : (path.startsWith("/") ? path : "/" + path);
        return baseUrl(request) + normalizedPath;
    }

    private String stripTrailingSlash(String value) {
        return value.endsWith("/") ? value.substring(0, value.length() - 1) : value;
    }
}
