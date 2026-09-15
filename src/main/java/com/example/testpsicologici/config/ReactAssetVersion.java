package com.example.testpsicologici.config;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component("reactAssetVersion")
public class ReactAssetVersion {

    private final String value;

    public ReactAssetVersion(ResourceLoader resourceLoader) {
        Resource bundle = resourceLoader.getResource("classpath:/static/react/assets/app.js");
        this.value = lastModified(bundle);
    }

    public String getValue() {
        return value;
    }

    private String lastModified(Resource bundle) {
        try {
            return Long.toString(bundle.lastModified());
        } catch (IOException exception) {
            return "current";
        }
    }
}
