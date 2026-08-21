package com.example.testpsicologici.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;

@Configuration
public class AnalyticsConfiguration {

    @Bean
    public ZoneId analyticsZoneId(@Value("${app.analytics.time-zone:Europe/Rome}") String zone) {
        return ZoneId.of(zone);
    }

    @Bean
    public Clock analyticsClock(ZoneId analyticsZoneId) {
        return Clock.system(analyticsZoneId);
    }
}
