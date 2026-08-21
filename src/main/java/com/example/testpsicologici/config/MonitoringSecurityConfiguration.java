package com.example.testpsicologici.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.UUID;

@Configuration
public class MonitoringSecurityConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringSecurityConfiguration.class);

    @Bean
    @Order(1)
    public SecurityFilterChain monitoringSecurityFilterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/monitoring/**")
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/monitoring/login").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/monitoring/login")
                        .loginProcessingUrl("/monitoring/login")
                        .defaultSuccessUrl("/monitoring", true)
                        .failureUrl("/monitoring/login?error"))
                .logout(logout -> logout
                        .logoutUrl("/monitoring/logout")
                        .logoutSuccessUrl("/monitoring/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"));
        return http.build();
    }

    @Bean
    public PasswordEncoder monitoringPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public UserDetailsService monitoringUsers(
            PasswordEncoder passwordEncoder,
            @Value("${app.monitoring.username:}") String configuredUsername,
            @Value("${app.monitoring.password:}") String configuredPassword) {
        boolean enabled = configuredUsername != null && !configuredUsername.isBlank()
                && configuredPassword != null && !configuredPassword.isBlank();
        String username = enabled ? configuredUsername : "monitoring-disabled";
        String password = enabled ? configuredPassword : UUID.randomUUID().toString();
        if (enabled) {
            LOGGER.info("Dashboard monitoring abilitata per l'utente configurato");
        } else {
            LOGGER.warn("Dashboard monitoring disabilitata: configurare MONITORING_USERNAME e "
                    + "MONITORING_PASSWORD");
        }
        var user = User.withUsername(username)
                .password(passwordEncoder.encode(password))
                .roles("MONITORING")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
