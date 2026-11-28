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
class PickMeReadingContentTest {

    @Autowired private WebApplicationContext context;

    @Test
    void guideShowsBothVerifiedAffiliateReadings() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        String html = mvc.perform(get("/approfondimenti/pick-me-behaviour"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(html).contains(
                "Il coraggio di non piacere. Liberati dal giudizio degli altri e trova l'autentica felicità",
                "Sapersi perdonare. Imparare ad accettarsi e a superare la vergogna con la mindfulness e la self compassion",
                "rel=\"noopener noreferrer sponsored\"",
                "In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.");
        assertThat(html).containsPattern("/dp/8851169918[^\"]*tag=spaziotest-21");
        assertThat(html).containsPattern("/dp/8835174260[^\"]*tag=spaziotest-21");
        assertThat(html.split("recommended-reading-card has-placeholder-cover", -1)).hasSize(3);
        assertThat(html.split("class=\"recommended-reading-cover\"", -1)).hasSize(3);
        assertThat(html).doesNotContain("/test/pick-me-behaviour");
        mvc.perform(get("/test/pick-me-behaviour")).andExpect(status().isNotFound());
    }
}
