package com.example.testpsicologici.service;

import com.example.testpsicologici.model.AmazonProductImage;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AmazonCreatorsApiProductImageProvider implements AmazonProductImageProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AmazonCreatorsApiProductImageProvider.class);
    private static final URI EU_TOKEN_ENDPOINT = URI.create("https://api.amazon.co.uk/auth/o2/token");
    private static final URI CATALOG_ENDPOINT = URI.create("https://creatorsapi.amazon/catalog/v1/getItems");
    private static final String MARKETPLACE = "www.amazon.it";
    private static final Duration IMAGE_CACHE_DURATION = Duration.ofHours(23);
    private static final Duration FAILED_LOOKUP_DURATION = Duration.ofMinutes(15);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(12);

    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;
    private final Clock clock;
    private final boolean enabled;
    private final String clientId;
    private final String clientSecret;
    private final String partnerTag;
    private final URI tokenEndpoint;
    private final URI catalogEndpoint;
    private final Map<String, CachedImage> imageCache = new ConcurrentHashMap<>();
    private final Object refreshLock = new Object();
    private final Object tokenLock = new Object();

    private volatile CachedToken cachedToken;

    @Autowired
    public AmazonCreatorsApiProductImageProvider(
            ObjectMapper objectMapper,
            @Value("${app.amazon.creators-api.enabled:false}") boolean enabled,
            @Value("${app.amazon.creators-api.client-id:}") String clientId,
            @Value("${app.amazon.creators-api.client-secret:}") String clientSecret,
            @Value("${app.amazon.creators-api.partner-tag:spaziotest-21}") String partnerTag) {
        this(objectMapper,
                HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(5)).build(),
                Clock.systemUTC(), enabled, clientId, clientSecret, partnerTag,
                EU_TOKEN_ENDPOINT, CATALOG_ENDPOINT);
    }

    AmazonCreatorsApiProductImageProvider(
            ObjectMapper objectMapper,
            HttpClient httpClient,
            Clock clock,
            boolean enabled,
            String clientId,
            String clientSecret,
            String partnerTag,
            URI tokenEndpoint,
            URI catalogEndpoint) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
        this.clock = clock;
        this.enabled = enabled;
        this.clientId = normalized(clientId);
        this.clientSecret = normalized(clientSecret);
        this.partnerTag = normalized(partnerTag);
        this.tokenEndpoint = tokenEndpoint;
        this.catalogEndpoint = catalogEndpoint;
        validateConfiguration();
    }

    @Override
    public Map<String, AmazonProductImage> findImages(List<String> asins) {
        List<String> requestedAsins = asins.stream()
                .map(AmazonCreatorsApiProductImageProvider::normalized)
                .filter(asin -> !asin.isEmpty())
                .distinct()
                .limit(10)
                .toList();
        if (!enabled || requestedAsins.isEmpty()) {
            return Map.of();
        }

        refreshMissingImages(requestedAsins);
        Instant now = clock.instant();
        Map<String, AmazonProductImage> result = new LinkedHashMap<>();
        for (String asin : requestedAsins) {
            CachedImage cached = imageCache.get(asin);
            if (cached != null && now.isBefore(cached.expiresAt()) && cached.image() != null) {
                result.put(asin, cached.image());
            }
        }
        return Map.copyOf(result);
    }

    private void refreshMissingImages(List<String> requestedAsins) {
        Instant now = clock.instant();
        List<String> missing = missingAt(requestedAsins, now);
        if (missing.isEmpty()) {
            return;
        }

        synchronized (refreshLock) {
            now = clock.instant();
            missing = missingAt(requestedAsins, now);
            if (missing.isEmpty()) {
                return;
            }
            try {
                Map<String, AmazonProductImage> refreshed = requestImages(missing);
                for (String asin : missing) {
                    AmazonProductImage image = refreshed.get(asin);
                    Duration duration = image == null ? FAILED_LOOKUP_DURATION : IMAGE_CACHE_DURATION;
                    imageCache.put(asin, new CachedImage(image, now.plus(duration)));
                }
            } catch (RuntimeException exception) {
                for (String asin : missing) {
                    imageCache.put(asin, new CachedImage(null, now.plus(FAILED_LOOKUP_DURATION)));
                }
                LOGGER.warn("Copertine Amazon temporaneamente non disponibili: {}", exception.getMessage());
            }
        }
    }

    private List<String> missingAt(List<String> asins, Instant now) {
        List<String> missing = new ArrayList<>();
        for (String asin : asins) {
            CachedImage cached = imageCache.get(asin);
            if (cached == null || !now.isBefore(cached.expiresAt())) {
                missing.add(asin);
            }
        }
        return missing;
    }

    private Map<String, AmazonProductImage> requestImages(List<String> asins) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("itemIds", asins);
        body.put("itemIdType", "ASIN");
        body.put("marketplace", MARKETPLACE);
        body.put("partnerTag", partnerTag);
        body.put("resources", List.of(
                "images.primary.large",
                "images.primary.medium",
                "images.primary.small"));

        HttpRequest request = HttpRequest.newBuilder(catalogEndpoint)
                .timeout(REQUEST_TIMEOUT)
                .header("Authorization", "Bearer " + accessToken())
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .header("x-marketplace", MARKETPLACE)
                .POST(HttpRequest.BodyPublishers.ofString(toJson(body)))
                .build();
        JsonNode response = send(request, "catalogo Amazon");
        Map<String, AmazonProductImage> images = new LinkedHashMap<>();
        for (JsonNode item : response.path("itemsResult").path("items")) {
            String asin = item.path("asin").asText("");
            AmazonProductImage image = primaryImage(item.path("images").path("primary"));
            if (!asin.isBlank() && image != null) {
                images.put(asin, image);
            }
        }
        return images;
    }

    private AmazonProductImage primaryImage(JsonNode primary) {
        for (String size : List.of("large", "medium", "small")) {
            JsonNode image = primary.path(size);
            String url = image.path("url").asText("");
            if (url.startsWith("https://")) {
                return new AmazonProductImage(
                        url,
                        Math.max(1, image.path("width").asInt(1)),
                        Math.max(1, image.path("height").asInt(1)));
            }
        }
        return null;
    }

    private String accessToken() {
        Instant now = clock.instant();
        CachedToken current = cachedToken;
        if (current != null && now.isBefore(current.expiresAt())) {
            return current.value();
        }
        synchronized (tokenLock) {
            now = clock.instant();
            current = cachedToken;
            if (current != null && now.isBefore(current.expiresAt())) {
                return current.value();
            }

            Map<String, String> body = Map.of(
                    "grant_type", "client_credentials",
                    "client_id", clientId,
                    "client_secret", clientSecret,
                    "scope", "creatorsapi::default");
            HttpRequest request = HttpRequest.newBuilder(tokenEndpoint)
                    .timeout(REQUEST_TIMEOUT)
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(toJson(body)))
                    .build();
            JsonNode response = send(request, "autenticazione Amazon");
            String token = response.path("access_token").asText("");
            long expiresIn = response.path("expires_in").asLong(3600);
            if (token.isBlank()) {
                throw new IllegalStateException("Amazon non ha restituito un access token");
            }
            long cacheSeconds = Math.max(60, expiresIn - 300);
            cachedToken = new CachedToken(token, now.plusSeconds(cacheSeconds));
            return token;
        }
    }

    private JsonNode send(HttpRequest request, String operation) {
        try {
            HttpResponse<String> response = httpClient.send(
                    request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() < 200 || response.statusCode() >= 300) {
                throw new IllegalStateException(operation + " non riuscita (HTTP "
                        + response.statusCode() + ")");
            }
            return objectMapper.readTree(response.body());
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(operation + " interrotta", exception);
        } catch (IOException exception) {
            throw new IllegalStateException(operation + " non disponibile", exception);
        }
    }

    private String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JacksonException exception) {
            throw new IllegalStateException("Impossibile preparare la richiesta Amazon", exception);
        }
    }

    private void validateConfiguration() {
        if (!enabled) {
            return;
        }
        if (clientId.isEmpty() || clientSecret.isEmpty() || partnerTag.isEmpty()) {
            throw new IllegalStateException(
                    "Le immagini Amazon sono abilitate, ma le credenziali Creators API o il Partner Tag mancano");
        }
    }

    private static String normalized(String value) {
        return value == null ? "" : value.trim();
    }

    private record CachedImage(AmazonProductImage image, Instant expiresAt) {
    }

    private record CachedToken(String value, Instant expiresAt) {
    }
}
