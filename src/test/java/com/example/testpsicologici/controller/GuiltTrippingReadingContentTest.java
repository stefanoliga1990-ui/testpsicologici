package com.example.testpsicologici.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class GuiltTrippingReadingContentTest {

    @Autowired private WebApplicationContext context;

    @Test
    void autonomousGuideShowsBothVerifiedAffiliateReadings() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        String html = mvc.perform(get("/approfondimenti/guilt-tripping"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(html).contains(
                "Il senso di colpa. Liberarsi dai ricatti morali",
                "Sapersi perdonare",
                "/dp/8850276788",
                "/dp/8835174260",
                "tag=spaziotest-21",
                "rel=\"noopener noreferrer sponsored\"",
                "In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.",
                "recommended-reading-card has-placeholder-cover",
                "class=\"recommended-reading-cover\"");
        assertThat(html).doesNotContain("/test/guilt-tripping");
        mvc.perform(get("/test/guilt-tripping")).andExpect(status().isNotFound());
    }
}
