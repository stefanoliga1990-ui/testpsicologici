package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.service.TestCatalogue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest(properties = {
        "app.payments.stripe.enabled=true",
        "app.payments.stripe.secret-key=sk_test_automated_test_key",
        "app.payments.stripe.webhook-secret=whsec_automated_test_secret"
})
class ContributionUiEnabledTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private TestCatalogue catalogue;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = webAppContextSetup(context).build();
    }

    @Test
    void resultExposesTheOptionalContributionInterfaceWhenStripeIsEnabled() throws Exception {
        String testId = "autostima";
        PsychologicalTest test = catalogue.findById(testId);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int index = 0; index < test.questions().size(); index++) {
            attempt.answer(index, 3);
        }
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + testId, attempt);

        mockMvc.perform(get("/test/{testId}/risultato", testId).session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ti va di offrirci un caffè?")))
                .andExpect(content().string(containsString("name=\"amount\" value=\"1\"")))
                .andExpect(content().string(containsString("name=\"amount\" value=\"3\"")))
                .andExpect(content().string(containsString("name=\"amount\" value=\"5\"")))
                .andExpect(content().string(containsString("\"contributionsEnabled\":true")));
    }
}
