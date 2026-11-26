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
class EmotionalDumpingReadingContentTest {

    @Autowired private WebApplicationContext context;

    @Test
    void guideShowsBothVerifiedAffiliateReadings() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        String html = mvc.perform(get("/approfondimenti/emotional-dumping"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(html).contains(
                "Sentire l'altro. Conoscere e praticare l'empatia",
                "Assertività e training assertivo. Teoria e pratica per migliorare le capacità relazionali dei pazienti",
                "rel=\"noopener noreferrer sponsored\"",
                "In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.");
        assertThat(html).containsPattern("/dp/8860300169[^\"]*tag=spaziotest-21");
        assertThat(html).containsPattern("/dp/882041192X[^\"]*tag=spaziotest-21");
        assertThat(html.split("recommended-reading-card has-placeholder-cover", -1)).hasSize(3);
        assertThat(html.split("class=\"recommended-reading-cover\"", -1)).hasSize(3);
        assertThat(html).doesNotContain("/test/emotional-dumping");
        mvc.perform(get("/test/emotional-dumping")).andExpect(status().isNotFound());
    }
}
