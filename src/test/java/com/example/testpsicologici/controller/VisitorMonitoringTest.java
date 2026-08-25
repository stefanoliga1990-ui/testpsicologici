package com.example.testpsicologici.controller;

import com.example.testpsicologici.persistence.DailySiteVisitRepository;
import com.example.testpsicologici.persistence.DailyTestCompletionRepository;
import com.example.testpsicologici.persistence.NotFoundPathRepository;
import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.service.DailyVisitCookieService;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.NotFoundPathAnalyticsService;
import jakarta.servlet.http.Cookie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpHeaders;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
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

    @Autowired
    private DailyTestCompletionRepository completionRepository;

    @Autowired
    private NotFoundPathRepository notFoundPathRepository;

    @Autowired
    private NotFoundPathAnalyticsService notFoundAnalyticsService;

    @Autowired
    private TestCatalogue catalogue;

    @Autowired
    private Environment environment;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
        completionRepository.deleteAll();
        notFoundPathRepository.deleteAll();
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
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/test/tratti-adhd-adulti/domanda/1"))
                .andExpect(header().string(HttpHeaders.LOCATION, not(containsString(";jsessionid="))));
    }

    @Test
    void sessionsAreTrackedOnlyWithCookies() {
        assertThat(environment.getProperty("server.servlet.session.tracking-modes"))
                .isEqualTo("cookie");
    }

    @Test
    void authenticatedOwnerCanReadDashboardAndFreshSnapshot() throws Exception {
        mockMvc.perform(post("/internal/visita").header(HttpHeaders.USER_AGENT, BROWSER_USER_AGENT))
                .andExpect(status().isNoContent());
        notFoundAnalyticsService.record("/vecchio-link");
        notFoundAnalyticsService.record("/vecchio-link?query=non-salvata");

        mockMvc.perform(get("/monitoring").with(user("owner").roles("MONITORING")))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Robots-Tag", "noindex, nofollow, noarchive"))
                .andExpect(header().string(HttpHeaders.CACHE_CONTROL, containsString("no-store")))
                .andExpect(content().string(containsString("Monitoraggio visite")))
                .andExpect(content().string(containsString("Visitatori distinti oggi")))
                .andExpect(content().string(containsString("Completamenti per test")))
                .andExpect(content().string(containsString("URL 404 più frequenti")))
                .andExpect(content().string(containsString("/vecchio-link")));

        mockMvc.perform(get("/monitoring/api/visite?days=7")
                        .with(user("owner").roles("MONITORING")))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CACHE_CONTROL, containsString("no-store")))
                .andExpect(jsonPath("$.todayVisitors").value(1))
                .andExpect(jsonPath("$.days.length()").value(7));

        mockMvc.perform(get("/monitoring/api/not-found-paths")
                        .with(user("owner").roles("MONITORING")))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CACHE_CONTROL, containsString("no-store")))
                .andExpect(header().string("X-Robots-Tag", "noindex, nofollow, noarchive"))
                .andExpect(jsonPath("$[0].path").value("/vecchio-link"))
                .andExpect(jsonPath("$[0].hits").value(2));
    }

    @Test
    void completedTestIsCountedOncePerAttemptWithoutAdditionalCookies() throws Exception {
        String testId = "autostima";
        PsychologicalTest test = catalogue.findById(testId);
        int lastQuestion = test.questions().size();
        MockHttpSession firstAttempt = almostCompletedAttempt(test);

        mockMvc.perform(post("/test/{testId}/domanda/{questionNumber}", testId, lastQuestion)
                        .session(firstAttempt)
                        .param("answer", "3"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/test/" + testId + "/risultato"))
                .andExpect(header().doesNotExist(HttpHeaders.SET_COOKIE));

        mockMvc.perform(get("/test/{testId}/risultato", testId).session(firstAttempt))
                .andExpect(status().isOk());
        mockMvc.perform(post("/test/{testId}/domanda/{questionNumber}", testId, lastQuestion)
                        .session(firstAttempt)
                        .param("answer", "4"))
                .andExpect(status().is3xxRedirection());

        assertThat(completionRepository.findAll()).singleElement()
                .extracting(completion -> completion.getCompletionCount())
                .isEqualTo(1L);

        MockHttpSession secondAttempt = almostCompletedAttempt(test);
        mockMvc.perform(post("/test/{testId}/domanda/{questionNumber}", testId, lastQuestion)
                        .session(secondAttempt)
                        .param("answer", "2"))
                .andExpect(status().is3xxRedirection());

        assertThat(completionRepository.findAll()).singleElement()
                .extracting(completion -> completion.getCompletionCount())
                .isEqualTo(2L);
    }

    @Test
    void authenticatedOwnerCanOpenOneTestCompletionChartAtATime() throws Exception {
        String testId = "autostima";
        PsychologicalTest test = catalogue.findById(testId);
        MockHttpSession attempt = almostCompletedAttempt(test);
        mockMvc.perform(post("/test/{testId}/domanda/{questionNumber}",
                        testId, test.questions().size())
                        .session(attempt)
                        .param("answer", "3"))
                .andExpect(status().is3xxRedirection());

        mockMvc.perform(get("/monitoring/api/test-completamenti")
                        .with(user("owner").roles("MONITORING")))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CACHE_CONTROL, containsString("no-store")))
                .andExpect(content().string(containsString("\"testId\":\"autostima\"")))
                .andExpect(content().string(containsString("\"totalCompletions\":1")));

        mockMvc.perform(get("/monitoring/api/test-completamenti/{testId}?days=7", testId)
                        .with(user("owner").roles("MONITORING")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.testId").value(testId))
                .andExpect(jsonPath("$.todayCompletions").value(1))
                .andExpect(jsonPath("$.totalCompletions").value(1))
                .andExpect(jsonPath("$.days.length()").value(7));

        mockMvc.perform(get("/monitoring/api/test-completamenti/non-esistente")
                        .with(user("owner").roles("MONITORING")))
                .andExpect(status().isNotFound());
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
                .andExpect(content().string(containsString("Contributi e pagamenti tramite Stripe")))
                .andExpect(content().string(containsString("https://stripe.com/privacy")))
                .andExpect(content().string(containsString("privacy@example.test")));
    }

    private MockHttpSession almostCompletedAttempt(PsychologicalTest test) {
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int question = 0; question < test.questions().size() - 1; question++) {
            attempt.answer(question, 3);
        }
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + test.id(), attempt);
        return session;
    }
}
