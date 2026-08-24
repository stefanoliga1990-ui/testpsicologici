package com.example.testpsicologici.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class ContributionReturnControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(new ContributionReturnController()).build();
    }

    @Test
    void rendersTheThankYouReturnPageWithoutIndexingIt() throws Exception {
        mockMvc.perform(get("/supporto/grazie"))
                .andExpect(status().isOk())
                .andExpect(view().name("support-return"))
                .andExpect(model().attribute("status", "success"))
                .andExpect(model().attribute("reactPageData",
                        org.hamcrest.Matchers.hasEntry("page", "supportReturn")))
                .andExpect(header().string("X-Robots-Tag",
                        "noindex, nofollow, noarchive"));
    }

    @Test
    void rendersTheCancelledReturnPageWithoutIndexingIt() throws Exception {
        mockMvc.perform(get("/supporto/annullato"))
                .andExpect(status().isOk())
                .andExpect(view().name("support-return"))
                .andExpect(model().attribute("status", "cancelled"))
                .andExpect(model().attribute("reactPageData",
                        org.hamcrest.Matchers.hasEntry("status", "cancelled")))
                .andExpect(header().string("X-Robots-Tag",
                        containsString("noindex")));
    }
}
