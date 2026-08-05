package com.example.testpsicologici.controller;

import com.example.testpsicologici.service.TestCatalogue;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    private final TestCatalogue catalogue;

    public HealthController(TestCatalogue catalogue) {
        this.catalogue = catalogue;
    }

    @GetMapping(value = "/health", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> health() {
        return catalogue.findAll().isEmpty()
                ? ResponseEntity.status(503).body("not ready")
                : ResponseEntity.ok("ok");
    }
}
