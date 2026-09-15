package com.example.testpsicologici.config;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("reactAssetVersion")
public class ReactAssetVersion {

    private final String value;
    private final String stylesheetValue;

    public ReactAssetVersion(ResourceLoader resourceLoader) {
        this.value = lastModified(resourceLoader.getResource("classpath:/static/react/assets/app.js"));
        this.stylesheetValue = lastModified(resourceLoader.getResource("classpath:/static/css/app.css"));
    }

    public String getValue() {
        return value;
    }

    public String getStylesheetValue() {
        return stylesheetValue;
    }

    private String lastModified(Resource bundle) {
        try {
            return Long.toString(bundle.lastModified());
        } catch (IOException exception) {
            return "current";
        }
    }
}
