package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.GuideSection;
import com.example.testpsicologici.model.InformationGuide;
import com.example.testpsicologici.service.GuideCatalogue;
import com.example.testpsicologici.service.GuideEditorialHistoryCatalogue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class GuideEnrichmentAdsense02Test {

    private static final Map<String, String> EXPECTED_TITLES = Map.of(
            "soddisfazione-vita", "Un ambito che pesa senza cancellare gli altri",
            "resilienza-psicologica", "Una difficoltà di cura: distinguere impegno e condizioni",
            "ptsd-adulti", "Un rumore improvviso: distinguere richiamo e sicurezza attuale");
    private static final Pattern PAGE_DATA = Pattern.compile(
            "<script id=\"react-page-data\" type=\"application/json\">(.*?)</script>",
            Pattern.DOTALL);

    @Autowired
    private GuideCatalogue guides;

    @Autowired
    private GuideEditorialHistoryCatalogue history;

    @Autowired
    private WebApplicationContext context;

    @Test
    void assignedGuidesExposeDistinctEnrichmentInHtmlAndReactData() throws Exception {
        MockMvc mvc = webAppContextSetup(context).build();
        assertThat(EXPECTED_TITLES.values()).doesNotHaveDuplicates();

        for (var expected : EXPECTED_TITLES.entrySet()) {
            String slug = expected.getKey();
            InformationGuide guide = guides.findBySlug(slug).orElseThrow();
            GuideSection section = guide.sections().stream()
                    .filter(candidate -> candidate.title().equals(expected.getValue()))
                    .findFirst().orElseThrow();
            assertThat(section.eyebrow()).isEqualTo("Esempio guidato");
            assertThat(section.paragraphs()).hasSize(2);
            assertThat(section.points()).hasSize(3);
            assertThat(guide.references()).isNotEmpty();
            assertThat(history.forSlug(slug).revisedOn()).isEqualTo(LocalDate.parse("2026-09-30"));

            String html = new String(mvc.perform(get("/approfondimenti/{slug}", slug))
                    .andExpect(status().isOk()).andReturn().getResponse().getContentAsByteArray(),
                    StandardCharsets.UTF_8);
            assertThat(html).contains("<h2>" + expected.getValue() + "</h2>");
            String openingSentence = section.paragraphs().get(0);
            openingSentence = openingSentence.substring(0, openingSentence.indexOf('.') + 1);
            assertThat(html).contains(openingSentence, section.points().get(0));
            assertThat(html).contains("dateModified", "2026-09-30", "Fonti consultate");
            assertThat(html).contains(guide.references().get(0).url());

            Matcher matcher = PAGE_DATA.matcher(html);
            assertThat(matcher.find()).isTrue();
            String data = matcher.group(1).replace("\\/", "/");
            assertThat(data).contains("\"editorialHistory\"", "\"revisedOn\":\"2026-09-30\"");
            assertThat(data).contains("\"guide\"", "\"sections\"", expected.getValue(),
                    section.paragraphs().get(0), section.points().get(0));
            assertThat(data).contains("\"references\"", guide.references().get(0).url());
        }
    }
}
