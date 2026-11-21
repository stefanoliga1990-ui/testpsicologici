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
class ProiezionePsicologicaReadingContentTest {

    @Autowired private WebApplicationContext context;

    @Test
    void guideShowsBothVerifiedAffiliateReadings() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        String html = mvc.perform(get("/approfondimenti/proiezione-psicologica"))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        assertThat(html).contains(
                "L'Io e i meccanismi di difesa",
                "I meccanismi di difesa. Teoria, valutazione, clinica",
                "/dp/8809768507",
                "/dp/8832855607",
                "tag=spaziotest-21",
                "rel=\"noopener noreferrer sponsored\"",
                "In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.");
        assertThat(html.split("recommended-reading-card has-placeholder-cover", -1)).hasSize(3);
        assertThat(html.split("class=\"recommended-reading-cover\"", -1)).hasSize(3);
        assertThat(html).doesNotContain("/test/proiezione-psicologica");
        mvc.perform(get("/test/proiezione-psicologica")).andExpect(status().isNotFound());
    }
}
