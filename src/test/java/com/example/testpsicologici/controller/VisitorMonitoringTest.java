package com.example.testpsicologici.controller;

import com.example.testpsicologici.persistence.DailySiteVisitRepository;
import com.example.testpsicologici.service.DailyVisitCookieService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class VisitorMonitoringTest {

    private static final String BROWSER_USER_AGENT =
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 Chrome/140 Safari/537.36";

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private DailySiteVisitRepository repository;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        mockMvc = webAppContextSetup(context).apply(springSecurity()).build();
    }

    @Test
    void browserIsCountedOnlyOncePerDay() throws Exception {
        MvcResult firstVisit = mockMvc.perform(post("/internal/visita")
                        .header(HttpHeaders.USER_AGENT, BROWSER_USER_AGENT))
                .andExpect(status().isNoContent())
                .andExpect(header().string(HttpHeaders.SET_COOKIE,
                        containsString(DailyVisitCookieService.COOKIE_NAME + "=")))
                .andReturn();

        Cookie dailyCookie = firstVisit.getResponse().getCookie(DailyVisitCookieService.COOKIE_NAME);
        assertThat(dailyCookie).isNotNull();
        assertThat(dailyCookie.isHttpOnly()).isTrue();
        assertThat(dailyCookie.getSecure()).isTrue();
        assertThat(repository.count()).isEqualTo(1);
        assertThat(repository.findAll().get(0).getVisitorCount()).isEqualTo(1);

        mockMvc.perform(post("/internal/visita")
                        .header(HttpHeaders.USER_AGENT, BROWSER_USER_AGENT)
                        .cookie(dailyCookie))
                .andExpect(status().isNoContent())
                .andExpect(header().doesNotExist(HttpHeaders.SET_COOKIE));

        assertThat(repository.findAll().get(0).getVisitorCount()).isEqualTo(1);
    }

    @Test
    void separateBrowsersIncrementTheSameDailyAggregate() throws Exception {
        mockMvc.perform(post("/internal/visita").header(HttpHeaders.USER_AGENT, BROWSER_USER_AGENT))
                .andExpect(status().isNoContent());
        mockMvc.perform(post("/internal/visita").header(HttpHeaders.USER_AGENT, BROWSER_USER_AGENT))
                .andExpect(status().isNoContent());

        assertThat(repository.findAll()).singleElement()
                .extracting(visit -> visit.getVisitorCount())
                .isEqualTo(2L);
    }

    @Test
    void automatedRequestsAreNotCounted() throws Exception {
        mockMvc.perform(post("/internal/visita")
                        .header(HttpHeaders.USER_AGENT, "Googlebot/2.1"))
                .andExpect(status().isNoContent())
                .andExpect(header().doesNotExist(HttpHeaders.SET_COOKIE));

        assertThat(repository.count()).isZero();
    }

    @Test
    void monitoringRequiresAuthenticationWhilePublicSiteRemainsPublic() throws Exception {
        mockMvc.perform(get("/monitoring"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/monitoring/login"));
        mockMvc.perform(get("/monitoring/login"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Monitoraggio visite")))
                .andExpect(content().string(containsString("name=\"_csrf\"")));
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
        mockMvc.perform(post("/test/{testId}/inizia", "tratti-adhd-adulti"))
                .andExpect(status().is3xxRedirection());
    }

    @Test
    void authenticatedOwnerCanReadDashboardAndFreshSnapshot() throws Exception {
        mockMvc.perform(post("/internal/visita").header(HttpHeaders.USER_AGENT, BROWSER_USER_AGENT))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/monitoring").with(user("owner").roles("MONITORING")))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Robots-Tag", "noindex, nofollow, noarchive"))
                .andExpect(header().string(HttpHeaders.CACHE_CONTROL, containsString("no-store")))
                .andExpect(content().string(containsString("Monitoraggio visite")))
                .andExpect(content().string(containsString("Visitatori distinti oggi")));

        mockMvc.perform(get("/monitoring/api/visite?days=7")
                        .with(user("owner").roles("MONITORING")))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CACHE_CONTROL, containsString("no-store")))
                .andExpect(jsonPath("$.todayVisitors").value(1))
                .andExpect(jsonPath("$.days.length()").value(7));
    }

    @Test
    void configuredCredentialsCreateAnAuthenticatedMonitoringSession() throws Exception {
        MvcResult login = mockMvc.perform(post("/monitoring/login")
                        .with(csrf())
                        .param("username", "monitoring-test")
                        .param("password", "monitoring-test-password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/monitoring"))
                .andReturn();

        mockMvc.perform(get("/monitoring").session(
                        (org.springframework.mock.web.MockHttpSession) login.getRequest().getSession(false)))
                .andExpect(status().isOk());
    }

    @Test
    void privacyPageDocumentsCookiesAndDataMinimization() throws Exception {
        mockMvc.perform(get("/privacy-e-cookie"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Privacy e cookie")))
                .andExpect(content().string(containsString("__Host-st_visit_day")))
                .andExpect(content().string(containsString("privacy@example.test")));
    }
}
