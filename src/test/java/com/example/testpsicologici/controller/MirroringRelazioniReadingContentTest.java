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
class MirroringRelazioniReadingContentTest {

    @Autowired private WebApplicationContext context;

    @Test
    void guideShowsBothVerifiedAffiliateReadings() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        String html = mvc.perform(get("/approfondimenti/mirroring-relazioni"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(html).contains(
                "La mente imitativa. Come e perché il nostro comportamento è influenzato dagli altri",
                "So quel che fai. Il cervello che agisce e i neuroni specchio",
                "/dp/8820416395",
                "/dp/8860300029",
                "tag=spaziotest-21",
                "rel=\"noopener noreferrer sponsored\"",
                "In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.");
        assertThat(html.split("recommended-reading-card has-placeholder-cover", -1)).hasSize(3);
        assertThat(html.split("class=\"recommended-reading-cover\"", -1)).hasSize(3);
        assertThat(html).doesNotContain("/test/mirroring-relazioni");
        mvc.perform(get("/test/mirroring-relazioni")).andExpect(status().isNotFound());
    }
}
