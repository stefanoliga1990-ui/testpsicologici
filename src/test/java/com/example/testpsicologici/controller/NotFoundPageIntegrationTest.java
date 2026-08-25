package com.example.testpsicologici.controller;

import com.example.testpsicologici.persistence.NotFoundPathRepository;
import com.example.testpsicologici.service.NotFoundPathAnalyticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotFoundPageIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private NotFoundPathRepository repository;

    @Autowired
    private NotFoundPathAnalyticsService analyticsService;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NEVER)
            .build();

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    void missingPageKeeps404AndProvidesSearchAndSuggestions() throws Exception {
        HttpResponse<String> response = get("/collegamento-rotto?dato=da-non-salvare");

        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(response.headers().firstValue("Location")).isEmpty();
        assertThat(response.headers().firstValue("X-Robots-Tag"))
                .contains("noindex, follow");
        assertThat(response.body())
                .contains("<meta name=\"robots\" content=\"noindex, follow\"")
                .contains("Pagina non trovata")
                .contains("Cerca tra i test")
                .contains("name=\"q\"")
                .contains("Test principali")
                .contains("Guide principali")
                .contains("href=\"/test/")
                .contains("href=\"/approfondimenti/")
                .doesNotContain("http-equiv=\"refresh\"");

        assertThat(repository.findAll()).singleElement().satisfies(entry -> {
            assertThat(entry.getPath()).isEqualTo("/collegamento-rotto");
            assertThat(entry.getPath()).doesNotContain("dato");
            assertThat(entry.getHitCount()).isEqualTo(1);
        });
    }

    @Test
    void controllerGenerated404UsesTheSamePage() throws Exception {
        HttpResponse<String> response = get("/approfondimenti/guida-non-esistente");

        assertThat(response.statusCode()).isEqualTo(404);
        assertThat(response.body()).contains("Qui non c’è la pagina che cercavi.");
        assertThat(repository.findById("/approfondimenti/guida-non-esistente")).isPresent();
    }

    @Test
    void repeatedPathsAreRankedByFrequency() throws Exception {
        get("/vecchio-collegamento");
        get("/altro-collegamento");
        get("/vecchio-collegamento?campagna=esclusa");

        assertThat(analyticsService.mostFrequent()).hasSize(2);
        assertThat(analyticsService.mostFrequent().get(0).path())
                .isEqualTo("/vecchio-collegamento");
        assertThat(analyticsService.mostFrequent().get(0).hits()).isEqualTo(2);
    }

    @Test
    void searchSubmittedFrom404PrepopulatesHomeFilter() throws Exception {
        HttpResponse<String> response = get("/?q=autostima");

        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.body())
                .contains("id=\"test-search-input\"")
                .contains("value=\"autostima\"")
                .contains("\"initialQuery\":\"autostima\"");
    }

    private HttpResponse<String> get(String path) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:" + port + path))
                .header("Accept", "text/html")
                .GET()
                .build();
        return httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    }
}
