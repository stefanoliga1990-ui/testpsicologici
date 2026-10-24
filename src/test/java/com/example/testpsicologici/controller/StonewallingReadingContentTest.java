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
class StonewallingReadingContentTest {

    @Autowired private WebApplicationContext context;

    @Test
    void guideShowsVerifiedAffiliateReadingsWithoutInventingATest() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        String html = mvc.perform(get("/approfondimenti/stonewalling"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(html).contains(
                "D&#39;amore e non d&#39;accordo. Quando litigare rafforza la coppia",
                "Se la coppia è in crisi. Smetti di lottare",
                "/dp/8832857170",
                "/dp/883517645X",
                "tag=spaziotest-21",
                "rel=\"noopener noreferrer sponsored\"",
                "In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.",
                "recommended-reading-card has-placeholder-cover",
                "class=\"recommended-reading-cover\"");
        mvc.perform(get("/test/stonewalling")).andExpect(status().isNotFound());
    }
}
