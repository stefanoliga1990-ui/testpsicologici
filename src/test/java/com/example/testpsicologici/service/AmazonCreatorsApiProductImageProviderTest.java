package com.example.testpsicologici.service;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.time.Clock;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;

class AmazonCreatorsApiProductImageProviderTest {

    private HttpServer server;
    private final AtomicInteger tokenCalls = new AtomicInteger();
    private final AtomicInteger catalogCalls = new AtomicInteger();
    private final AtomicReference<String> catalogAuthorization = new AtomicReference<>();
    private final AtomicReference<String> catalogMarketplace = new AtomicReference<>();
    private final AtomicReference<String> catalogBody = new AtomicReference<>();

    @BeforeEach
    void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress("127.0.0.1", 0), 0);
        server.createContext("/auth/o2/token", exchange -> {
            tokenCalls.incrementAndGet();
            respond(exchange, 200, "{\"access_token\":\"test-token\",\"expires_in\":3600}");
        });
        server.createContext("/catalog/v1/getItems", exchange -> {
            catalogCalls.incrementAndGet();
            catalogAuthorization.set(exchange.getRequestHeaders().getFirst("Authorization"));
            catalogMarketplace.set(exchange.getRequestHeaders().getFirst("x-marketplace"));
            catalogBody.set(new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8));
            respond(exchange, 200, """
                    {
                      "itemsResult": {
                        "items": [
                          {
                            "asin": "8868956004",
                            "images": {"primary": {"large": {
                              "url": "https://m.media-amazon.com/images/I/cover-one.jpg",
                              "width": 318,
                              "height": 500
                            }}}
                          },
                          {
                            "asin": "B0BT4WCXFB",
                            "images": {"primary": {"medium": {
                              "url": "https://m.media-amazon.com/images/I/cover-two.jpg",
                              "width": 160,
                              "height": 240
                            }}}
                          }
                        ]
                      }
                    }
                    """);
        });
        server.start();
    }

    @AfterEach
    void stopServer() {
        server.stop(0);
    }

    @Test
    void retrievesPrimaryImagesAndCachesThem() throws Exception {
        URI baseUri = URI.create("http://127.0.0.1:" + server.getAddress().getPort());
        AmazonCreatorsApiProductImageProvider provider = new AmazonCreatorsApiProductImageProvider(
                new ObjectMapper(),
                HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build(),
                Clock.systemUTC(),
                true,
                "credential-id",
                "credential-secret",
                "spaziotest-21",
                baseUri.resolve("/auth/o2/token"),
                baseUri.resolve("/catalog/v1/getItems"));

        var first = provider.findImages(List.of("8868956004", "B0BT4WCXFB"));
        var second = provider.findImages(List.of("8868956004", "B0BT4WCXFB"));

        assertThat(first.get("8868956004").url())
                .isEqualTo("https://m.media-amazon.com/images/I/cover-one.jpg");
        assertThat(first.get("8868956004").width()).isEqualTo(318);
        assertThat(first.get("B0BT4WCXFB").url())
                .isEqualTo("https://m.media-amazon.com/images/I/cover-two.jpg");
        assertThat(second).isEqualTo(first);
        assertThat(tokenCalls).hasValue(1);
        assertThat(catalogCalls).hasValue(1);
        assertThat(catalogAuthorization).hasValue("Bearer test-token");
        assertThat(catalogMarketplace).hasValue("www.amazon.it");

        var requestJson = new ObjectMapper().readTree(catalogBody.get());
        assertThat(requestJson.path("partnerTag").asText()).isEqualTo("spaziotest-21");
        assertThat(requestJson.path("itemIds").size()).isEqualTo(2);
        assertThat(requestJson.path("resources").toString())
                .contains("images.primary.large", "images.primary.medium", "images.primary.small");
    }

    @Test
    void staysInactiveWithoutCredentialsWhenDisabled() {
        URI baseUri = URI.create("http://127.0.0.1:" + server.getAddress().getPort());
        AmazonCreatorsApiProductImageProvider provider = new AmazonCreatorsApiProductImageProvider(
                new ObjectMapper(), HttpClient.newHttpClient(), Clock.systemUTC(),
                false, "", "", "", baseUri, baseUri);

        assertThat(provider.findImages(List.of("8868956004"))).isEmpty();
        assertThat(tokenCalls).hasValue(0);
        assertThat(catalogCalls).hasValue(0);
    }

    private void respond(HttpExchange exchange, int status, String body) throws IOException {
        byte[] bytes = body.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);
        exchange.getResponseBody().write(bytes);
        exchange.close();
    }
}
