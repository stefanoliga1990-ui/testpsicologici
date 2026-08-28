package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.PsychologicalTest;
import com.example.testpsicologici.model.TestAttempt;
import com.example.testpsicologici.service.TestCatalogue;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
class PageRenderingTest {

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
    void homeRendersSearchAndFullyClickableTestCards() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<main class=\"shell home-shell\" data-react-fallback")))
                .andExpect(content().string(containsString(
                        "<title>Test psicologici online informativi | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "<link rel=\"canonical\" href=\"http://localhost\"")))
                .andExpect(content().string(containsString(
                        "rel=\"icon\" type=\"image/svg+xml\" href=\"/images/brand/favicon.svg\"")))
                .andExpect(content().string(containsString(
                        "rel=\"apple-touch-icon\" sizes=\"180x180\" href=\"/images/brand/apple-touch-icon.png\"")))
                .andExpect(content().string(containsString(
                        "name=\"apple-mobile-web-app-title\" content=\"Spazio Test\"")))
                .andExpect(content().string(containsString(
                        "property=\"og:image\" content=\"http://localhost/images/brand/og-spazio-test.png\"")))
                .andExpect(content().string(containsString(
                        "name=\"twitter:card\" content=\"summary_large_image\"")))
                .andExpect(content().string(containsString(
                        "\"contentUrl\": \"http:\\/\\/localhost\\/images\\/brand\\/logo-512.png\"")))
                .andExpect(content().string(containsString(
                        "src=\"/images/brand/logo-mark.svg\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("id=\"test-search-input\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("data-test-card")))
                .andExpect(content().string(containsString("Ansia, umore e trauma")))
                .andExpect(content().string(containsString("Relazioni e attaccamento")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("Autostima, approvazione e obiettivi")))
                .andExpect(content().string(containsString("Emozioni, risorse e benessere")))
                .andExpect(content().string(containsString("Neurosviluppo, attenzione e linguaggio")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/tratti-autistici-adulti\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti\"")))
                .andExpect(content().string(containsString("href=\"/metodo-e-fonti\"")))
                .andExpect(content().string(containsString("href=\"/il-progetto\"")))
                .andExpect(content().string(containsString(
                        "href=\"https://www.instagram.com/spazio.test/\"")))
                .andExpect(content().string(containsString(
                        "src=\"/images/brand/instagram-spazio-test.png\"")))
                .andExpect(content().string(containsString(
                        "aria-label=\"Visita Spazio Test su Instagram (si apre in una nuova scheda)\"")))
                .andExpect(content().string(containsString("class=\"mobile-nav\"")))
                .andExpect(content().string(containsString("class=\"nav-toggle\"")))
                .andExpect(content().string(containsString("<span>Instagram</span>")));
    }

    @Test
    void brandAssetsArePubliclyServedWithExpectedContentTypes() throws Exception {
        mockMvc.perform(get("/images/brand/favicon.svg"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("image/svg+xml"));
        mockMvc.perform(get("/images/brand/favicon.ico"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("image/x-icon"));
        mockMvc.perform(get("/images/brand/apple-touch-icon.png"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("image/png"));
        mockMvc.perform(get("/images/brand/logo-512.png"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("image/png"));
        mockMvc.perform(get("/images/brand/og-spazio-test.png"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("image/png"));
        mockMvc.perform(get("/images/brand/instagram-spazio-test.png"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("image/png"));
    }

    @Test
    void introductionRendersUniqueSeoMetadataEditorialContentAndReferences() throws Exception {
        mockMvc.perform(get("/test/{testId}", "tratti-adhd-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<main class=\"intro-shell\" data-react-fallback")))
                .andExpect(content().string(containsString(
                        "<title>Test ADHD adulti online: questionario | Spazio Test</title>")))
                .andExpect(content().string(containsString("name=\"description\"")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/test/tratti-adhd-adulti\"")))
                .andExpect(content().string(containsString("Che cosa esplora questo questionario")))
                .andExpect(content().string(containsString("Contenuto editoriale, non scala clinica")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString("Riferimento informativo per attenzione")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione editoriale"))))
                .andExpect(content().string(containsString("ADHD in adults — NHS")))
                .andExpect(content().string(containsString("Test correlati")))
                .andExpect(content().string(containsString("class=\"related-content intro-related-content\"")))
                .andExpect(content().string(containsString("href=\"/test/tratti-autistici-adulti\"")))
                .andExpect(content().string(containsString("href=\"/test/intelligenza-linguistica\"")));

    }

    @Test
    void methodPageExplainsProcessAndGroupsSourcesByTest() throws Exception {
        mockMvc.perform(get("/metodo-e-fonti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Metodo e fonti di questionari e guide | Spazio Test</title>")))
                .andExpect(content().string(containsString("Come nasce un contenuto")))
                .andExpect(content().string(containsString("fonti scientifiche o istituzionali italiane")))
                .andExpect(content().string(containsString("Norme, prevalenze e cut-off non vengono trasferiti")))
                .andExpect(content().string(containsString("Fonti per ogni questionario")))
                .andExpect(content().string(containsString("ADHD nell&#39;adulto: tratti associati")))
                .andExpect(content().string(containsString("ADHD in adults — NHS")))
                .andExpect(content().string(containsString("Riferimenti delle guide")))
                .andExpect(content().string(containsString("Signs of autism in adults — NHS")))
                .andExpect(content().string(containsString("Diagnosi e trattamento del disturbo dello spettro autistico negli adulti — ISS/SNLG")))
                .andExpect(content().string(containsString(
                        "Attention-Deficit/Hyperactivity Disorder: What You Need to Know — NIMH")))
                .andExpect(content().string(containsString(
                        "Obsessive-Compulsive Disorder: When Unwanted Thoughts or Repetitive Behaviors Take Over — NIMH")))
                .andExpect(content().string(containsString("Raising low self-esteem — NHS")))
                .andExpect(content().string(containsString(
                        "Il 1522 — Dipartimento per le Pari Opportunità")))
                .andExpect(content().string(containsString(
                        "Improving Assertiveness — Centre for Clinical Interventions")))
                .andExpect(content().string(containsString(
                        "The Ability Model of Emotional Intelligence: Principles and Updates — Mayer, Caruso e Salovey")))
                .andExpect(content().string(containsString(
                        "Perfectionism Self-Help Resources — Centre for Clinical Interventions")))
                .andExpect(content().string(containsString(
                        "Social anxiety (social phobia) — NHS")))
                .andExpect(content().string(containsString(
                        "Violence against women — World Health Organization")))
                .andExpect(content().string(containsString(
                        "Generalised anxiety disorder (GAD) — NHS")))
                .andExpect(content().string(containsString("Depression in adults — NHS")))
                .andExpect(content().string(containsString(
                        "Distinctions of unmitigated communion from communion: self-neglect and overinvolvement with others — Fritz e Helgeson")))
                .andExpect(content().string(containsString(
                        "Contextualizing the Impostor “Syndrome” — Feenstra e colleghi")))
                .andExpect(content().string(containsString(
                        "Procrastination and Stress: A Conceptual Review of Why Context Matters — Sirois")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void projectPagePresentsEditorialPurposeAndResponsibility() throws Exception {
        mockMvc.perform(get("/il-progetto"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<title>Il progetto | Spazio Test</title>")))
                .andExpect(content().string(containsString("Uno spazio per osservarti con più chiarezza")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString("href=\"/metodo-e-fonti\"")));
    }

    @Test
    void guideIndexListsPublishedGuides() throws Exception {
        mockMvc.perform(get("/approfondimenti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Approfondimenti su psicologia e benessere | Spazio Test</title>")))
                .andExpect(content().string(containsString("Un argomento alla volta")))
                .andExpect(content().string(containsString("/js/guides.js")))
                .andExpect(content().string(containsString("/react/assets/app.js?v=react-14")))
                .andExpect(content().string(containsString("id=\"guide-search-input\"")))
                .andExpect(content().string(containsString("data-guide-card")))
                .andExpect(content().string(containsString("Ansia, umore e trauma")))
                .andExpect(content().string(containsString("Relazioni e attaccamento")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("Autostima, approvazione e obiettivi")))
                .andExpect(content().string(containsString("Emozioni, risorse e benessere")))
                .andExpect(content().string(containsString("Neurosviluppo, attenzione e linguaggio")))
                .andExpect(content().string(containsString("data-guide-empty")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/autismo-adulti\"")))
                .andExpect(content().string(containsString("Autismo nell&#39;adulto")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/adhd-adulti\"")))
                .andExpect(content().string(containsString("ADHD nell&#39;adulto")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/disturbo-ossessivo-compulsivo\"")))
                .andExpect(content().string(containsString("Pensieri ossessivi e compulsioni (DOC)")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/autostima\"")))
                .andExpect(content().string(containsString("<h3>Autostima</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/dipendenza-affettiva\"")))
                .andExpect(content().string(containsString("<h3>Dipendenza affettiva</h3>")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/assertivita\"")))
                .andExpect(content().string(containsString("<h3>Assertività</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/intelligenza-emotiva\"")))
                .andExpect(content().string(containsString("<h3>Intelligenza emotiva</h3>")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/perfezionismo\"")))
                .andExpect(content().string(containsString("<h3>Perfezionismo</h3>")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/ansia-sociale\"")))
                .andExpect(content().string(containsString("<h3>Ansia sociale</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/dinamiche-narcisistiche-coppia\"")))
                .andExpect(content().string(containsString(
                        "<h3>Dinamiche narcisistiche nella coppia</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/ansia-generalizzata\"")))
                .andExpect(content().string(containsString("<h3>Ansia generalizzata</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/umore-depresso\"")))
                .andExpect(content().string(containsString(
                        "<h3>Umore depresso e sintomi depressivi</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/people-pleasing\"")))
                .andExpect(content().string(containsString(
                        "<h3>People pleasing e bisogno di approvazione</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/sindrome-impostore\"")))
                .andExpect(content().string(containsString(
                        "<h3>Sindrome dell&#39;impostore</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/autosabotaggio\"")))
                .andExpect(content().string(containsString(
                        "<h3>Autosabotaggio e ostacoli agli obiettivi</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/disturbo-borderline-personalita\"")))
                .andExpect(content().string(containsString(
                        "<h3>Disturbo borderline di personalità</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/paura-abbandono\"")))
                .andExpect(content().string(containsString(
                        "<h3>Paura dell&#39;abbandono</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/fomo\"")))
                .andExpect(content().string(containsString(
                        "<h3>FOMO (Fear of Missing Out)</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/intelligenza-linguistica\"")))
                .andExpect(content().string(containsString(
                        "<h3>Intelligenza linguistica</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/intelligenza-intrapersonale\"")))
                .andExpect(content().string(containsString(
                        "<h3>Intelligenza intrapersonale</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/resilienza-psicologica\"")))
                .andExpect(content().string(containsString(
                        "<h3>Resilienza psicologica</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/gelosia-partner\"")))
                .andExpect(content().string(containsString(
                        "<h3>Gelosia nella relazione</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/soddisfazione-vita\"")))
                .andExpect(content().string(containsString(
                        "<h3>Soddisfazione di vita</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/ptsd-adulti\"")))
                .andExpect(content().string(containsString(
                        "<h3>Disturbo post-traumatico da stress</h3>")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/stili-attaccamento\"")))
                .andExpect(content().string(containsString(
                        "<h3>Stili di attaccamento nelle relazioni</h3>")));
    }

    @Test
    void autismGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/autismo-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Autismo negli adulti: caratteristiche e segnali | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/autismo-adulti\"")))
                .andExpect(content().string(containsString("Che cos&#39;è l&#39;autismo")))
                .andExpect(content().string(containsString("Come può manifestarsi nell&#39;adulto")))
                .andExpect(content().string(containsString("Singole caratteristiche non bastano per una diagnosi")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString("Signs of autism in adults — NHS")))
                .andExpect(content().string(containsString("href=\"/test/tratti-autistici-adulti\"")))
                .andExpect(content().string(containsString("Approfondimenti collegati")))
                .andExpect(content().string(containsString("class=\"related-content guide-related-content\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/adhd-adulti\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/intelligenza-linguistica\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void adhdGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/adhd-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>ADHD negli adulti: sintomi e caratteristiche | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/adhd-adulti\"")))
                .andExpect(content().string(containsString("Che cos&#39;è l&#39;ADHD")))
                .andExpect(content().string(containsString("Come può manifestarsi nell&#39;adulto")))
                .andExpect(content().string(containsString(
                        "Distrazione e impulsività non indicano sempre ADHD")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString("ADHD in adults — NHS")))
                .andExpect(content().string(containsString(
                        "Attention-Deficit/Hyperactivity Disorder: What You Need to Know — NIMH")))
                .andExpect(content().string(containsString("href=\"/test/tratti-adhd-adulti\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void obsessiveCompulsiveGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/disturbo-ossessivo-compulsivo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Disturbo ossessivo-compulsivo (DOC): sintomi | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/disturbo-ossessivo-compulsivo\"")))
                .andExpect(content().string(containsString("Che cosa sono ossessioni e compulsioni")))
                .andExpect(content().string(containsString(
                        "Come può mantenersi il ciclo ossessivo-compulsivo")))
                .andExpect(content().string(containsString(
                        "Un pensiero intrusivo non è un&#39;intenzione")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Symptoms – Obsessive compulsive disorder (OCD) — NHS")))
                .andExpect(content().string(containsString(
                        "Obsessive-compulsive disorder and body dysmorphic disorder: treatment — NICE CG31")))
                .andExpect(content().string(containsString("href=\"/test/tratti-ossessivo-compulsivi\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void selfEsteemGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/autostima"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Autostima: cos&#39;è e come migliorarla | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/autostima\"")))
                .andExpect(content().string(containsString("Che cos&#39;è l&#39;autostima")))
                .andExpect(content().string(containsString(
                        "Autostima e fiducia in sé non sono la stessa cosa")))
                .andExpect(content().string(containsString(
                        "Come può manifestarsi una bassa autostima")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Rosenberg Self-Esteem Scale — University of Maryland")))
                .andExpect(content().string(containsString("Raising low self-esteem — NHS")))
                .andExpect(content().string(containsString("href=\"/test/autostima\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void emotionalDependenceGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/dipendenza-affettiva"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Dipendenza affettiva: segnali e relazioni | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/dipendenza-affettiva\"")))
                .andExpect(content().string(containsString(
                        "Che cosa si intende per dipendenza affettiva")))
                .andExpect(content().string(containsString(
                        "Legame e interdipendenza non significano perdere sé stessi")))
                .andExpect(content().string(containsString("Il ruolo dell&#39;attaccamento")))
                .andExpect(content().string(containsString(
                        "Dipendenza relazionale, controllo e violenza non sono la stessa cosa")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "I disturbi da addiction nelle dipendenze non legate a sostanze — Ministero della Salute")))
                .andExpect(content().string(containsString(
                        "Il 1522 — Dipartimento per le Pari Opportunità")))
                .andExpect(content().string(containsString("href=\"/test/dipendenza-affettiva\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void assertivenessGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/assertivita"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Assertività: significato ed esempi pratici | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/assertivita\"")))
                .andExpect(content().string(containsString("Che cos&#39;è l&#39;assertività")))
                .andExpect(content().string(containsString(
                        "Passività, aggressività e assertività")))
                .andExpect(content().string(containsString("Le diverse forme dell&#39;assertività")))
                .andExpect(content().string(containsString(
                        "Strategie per comunicare in modo più assertivo")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "A 30-Item Schedule for Assessing Assertive Behavior — Rathus")))
                .andExpect(content().string(containsString(
                        "Improving Assertiveness — Centre for Clinical Interventions")))
                .andExpect(content().string(containsString("href=\"/test/assertivita\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void emotionalIntelligenceGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/intelligenza-emotiva"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Intelligenza emotiva: cos&#39;è e come svilupparla | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/intelligenza-emotiva\"")))
                .andExpect(content().string(containsString("Che cos&#39;è l&#39;intelligenza emotiva")))
                .andExpect(content().string(containsString(
                        "Non esiste un unico modo di definire e misurare il costrutto")))
                .andExpect(content().string(containsString(
                        "Percepire, usare, comprendere e regolare le emozioni")))
                .andExpect(content().string(containsString(
                        "Riconoscere un&#39;emozione non significa leggere la mente")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "The Ability Model of Emotional Intelligence: Principles and Updates — Mayer, Caruso e Salovey")))
                .andExpect(content().string(containsString(
                        "Emotional Intelligence: New Ability or Eclectic Traits? — Mayer, Salovey e Caruso")))
                .andExpect(content().string(containsString("href=\"/test/intelligenza-emotiva\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void perfectionismGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/perfezionismo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Perfezionismo: cos&#39;è e come gestirlo | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/perfezionismo\"")))
                .andExpect(content().string(containsString("Che cos&#39;è il perfezionismo")))
                .andExpect(content().string(containsString(
                        "Standard elevati e perfezionismo problematico non sono la stessa cosa")))
                .andExpect(content().string(containsString(
                        "Le diverse forme della pressione perfezionistica")))
                .andExpect(content().string(containsString(
                        "Perché controllo e procrastinazione possono mantenere il problema")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "The dimensions of perfectionism — Frost e colleghi")))
                .andExpect(content().string(containsString(
                        "Perfectionism Self-Help Resources — Centre for Clinical Interventions")))
                .andExpect(content().string(containsString("href=\"/test/perfezionismo\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void socialAnxietyGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/ansia-sociale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Ansia sociale: sintomi e come affrontarla | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/ansia-sociale\"")))
                .andExpect(content().string(containsString("Che cos&#39;è l&#39;ansia sociale")))
                .andExpect(content().string(containsString(
                        "Come può presentarsi prima, durante e dopo una situazione sociale")))
                .andExpect(content().string(containsString(
                        "Come evitamento e comportamenti protettivi mantengono la paura")))
                .andExpect(content().string(containsString(
                        "Ansia sociale, timidezza e contesto non sono la stessa cosa")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Social Anxiety Disorder: What You Need to Know — NIMH")))
                .andExpect(content().string(containsString(
                        "Social anxiety (social phobia) — NHS")))
                .andExpect(content().string(containsString("href=\"/test/ansia-sociale\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void narcissisticRelationshipDynamicsGuideRendersHelpfulContentSourcesAndBidirectionalLink()
            throws Exception {
        mockMvc.perform(get("/approfondimenti/dinamiche-narcisistiche-coppia"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Dinamiche narcisistiche nella coppia: segnali | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/dinamiche-narcisistiche-coppia\"")))
                .andExpect(content().string(containsString(
                        "Che cosa si intende per dinamiche narcisistiche nella coppia")))
                .andExpect(content().string(containsString(
                        "Si può capire se il partner è narcisista?")))
                .andExpect(content().string(containsString(
                        "Narcisismo, conflitto e abuso non sono sinonimi")))
                .andExpect(content().string(containsString(
                        "Come riflettere sulla relazione e cercare supporto")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Narcissistic Personality Disorder — Merck Manual Professional Edition")))
                .andExpect(content().string(containsString(
                        "Violence against women — World Health Organization")))
                .andExpect(content().string(containsString(
                        "Il 1522 — Dipartimento per le Pari Opportunità")))
                .andExpect(content().string(containsString(
                        "href=\"/test/dinamiche-narcisistiche-partner\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void generalizedAnxietyGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/ansia-generalizzata"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Ansia generalizzata: sintomi e cosa fare | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/ansia-generalizzata\"")))
                .andExpect(content().string(containsString("Che cos&#39;è l&#39;ansia generalizzata")))
                .andExpect(content().string(containsString(
                        "Preoccupazione utile e preoccupazione difficile da controllare")))
                .andExpect(content().string(containsString(
                        "Pensieri, corpo e risorse quotidiane")))
                .andExpect(content().string(containsString(
                        "Come può mantenersi il ciclo della preoccupazione")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Generalized Anxiety Disorder: What You Need to Know — NIMH")))
                .andExpect(content().string(containsString(
                        "Generalised anxiety disorder (GAD) — NHS")))
                .andExpect(content().string(containsString("href=\"/test/ansia-generalizzata\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void depressedMoodGuideRendersHelpfulContentSourcesSafetyAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/umore-depresso"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Depressione: sintomi e segnali da conoscere | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/umore-depresso\"")))
                .andExpect(content().string(containsString(
                        "Tristezza, umore depresso e depressione non sono la stessa cosa")))
                .andExpect(content().string(containsString(
                        "Come possono presentarsi i sintomi depressivi")))
                .andExpect(content().string(containsString(
                        "Perché è importante osservare il quadro completo")))
                .andExpect(content().string(containsString(
                        "Trattamenti disponibili e segnali da non affrontare da soli")))
                .andExpect(content().string(containsString("chiama subito il 112")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Depressive disorder (depression) — World Health Organization")))
                .andExpect(content().string(containsString("Depression in adults — NHS")))
                .andExpect(content().string(containsString("href=\"/test/umore-depresso\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void peoplePleasingGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/people-pleasing"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>People pleasing: segnali e confini | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/people-pleasing\"")))
                .andExpect(content().string(containsString("Che cos&#39;è il people pleasing")))
                .andExpect(content().string(containsString(
                        "Gentilezza, cura e compiacenza non sono la stessa cosa")))
                .andExpect(content().string(containsString(
                        "Come può mantenersi la ricerca di approvazione")))
                .andExpect(content().string(containsString(
                        "Potere, sicurezza e richiesta di supporto")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Distinctions of unmitigated communion from communion: self-neglect and overinvolvement with others — Fritz e Helgeson")))
                .andExpect(content().string(containsString(
                        "Improving Assertiveness — Centre for Clinical Interventions")))
                .andExpect(content().string(containsString("href=\"/test/people-pleasing\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void impostorPhenomenonGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/sindrome-impostore"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Sindrome dell&#39;impostore: cos&#39;è e segnali | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/sindrome-impostore\"")))
                .andExpect(content().string(containsString("Che cos&#39;è il fenomeno dell&#39;impostore")))
                .andExpect(content().string(containsString(
                        "Dubbio realistico e vissuto dell&#39;impostore non coincidono")))
                .andExpect(content().string(containsString(
                        "Perché un successo può non correggere il dubbio")))
                .andExpect(content().string(containsString("Non tutto nasce dentro la persona")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "Impostor Phenomenon Measurement Scales: A Systematic Review — Mak, Kleitman e Abbott")))
                .andExpect(content().string(containsString(
                        "Contextualizing the Impostor “Syndrome” — Feenstra e colleghi")))
                .andExpect(content().string(containsString("href=\"/test/sindrome-impostore\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void selfSabotageGuideRendersHelpfulContentSourcesAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/autosabotaggio"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Autosabotaggio: segnali e strategie utili | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/autosabotaggio\"")))
                .andExpect(content().string(containsString(
                        "Che cosa si intende per autosabotaggio")))
                .andExpect(content().string(containsString(
                        "Rimandare o abbandonare non è sempre autosabotaggio")))
                .andExpect(content().string(containsString(
                        "Come sollievo immediato e autocritica mantengono il blocco")))
                .andExpect(content().string(containsString("Quando guardare oltre le abitudini")))
                .andExpect(content().string(containsString("BreadcrumbList")))
                .andExpect(content().string(containsString("A cura di Spazio Test")))
                .andExpect(content().string(containsString(
                        "The nature of procrastination: a meta-analytic and theoretical review — Steel")))
                .andExpect(content().string(containsString(
                        "Procrastination Self-Help Resources — Centre for Clinical Interventions")))
                .andExpect(content().string(containsString("href=\"/test/autosabotaggio\"")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Ultimo aggiornamento"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("Versione 2"))));
    }

    @Test
    void borderlineGuideRendersEvidenceLimitsSafetyAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/disturbo-borderline-personalita"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Disturbo borderline di personalità: caratteristiche | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/disturbo-borderline-personalita\"")))
                .andExpect(content().string(containsString(
                        "Che cos&#39;è il disturbo borderline di personalità")))
                .andExpect(content().string(containsString(
                        "Quattro domini utili per orientarsi, non una checklist")))
                .andExpect(content().string(containsString(
                        "Quando serve un aiuto immediato")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("Pronto Soccorso")))
                .andExpect(content().string(containsString(
                        "The Italian Version of the Borderline Personality Disorder Severity Index IV")))
                .andExpect(content().string(containsString(
                        "Psychological therapies for people with borderline personality disorder")))
                .andExpect(content().string(containsString("href=\"/test/tratti-borderline-adulti\"")));
    }

    @Test
    void fearOfAbandonmentGuideRendersEvidenceDistinctionsSafetyAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/paura-abbandono"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Paura dell&#39;abbandono: segnali e significato | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/paura-abbandono\"")))
                .andExpect(content().string(containsString(
                        "Che cosa si intende per paura dell&#39;abbandono")))
                .andExpect(content().string(containsString(
                        "Non è una diagnosi né uno stile completo")))
                .andExpect(content().string(containsString(
                        "Segnali reali e interpretazioni non sono la stessa cosa")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString(
                        "Italian Validation of the Adult Attachment Scale-Revised")))
                .andExpect(content().string(containsString(
                        "How anxious and avoidant attachment affect romantic relationship quality differently")))
                .andExpect(content().string(containsString("href=\"/test/paura-abbandono\"")));
    }

    @Test
    void fomoGuideRendersEvidenceDistinctionsLimitsAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/fomo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>FOMO: significato, social e segnali | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/fomo\"")))
                .andExpect(content().string(containsString("Che cos&#39;è la FOMO")))
                .andExpect(content().string(containsString(
                        "FOMO e uso problematico non sono la stessa cosa")))
                .andExpect(content().string(containsString(
                        "I social rendono visibili le alternative, ma non sono necessari")))
                .andExpect(content().string(containsString(
                        "Italian version of the Fear of Missing Out Scale")))
                .andExpect(content().string(containsString(
                        "Fear of missing out and internet use: a systematic review and meta-analysis")))
                .andExpect(content().string(containsString("non sono percentuali della persona")))
                .andExpect(content().string(containsString("href=\"/test/fomo\"")));
    }

    @Test
    void linguisticIntelligenceGuideRendersTheoryDebateItalianContextAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/intelligenza-linguistica"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Intelligenza linguistica di Gardner: significato | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/intelligenza-linguistica\"")))
                .andExpect(content().string(containsString(
                        "Che cosa intende Gardner per intelligenza linguistica")))
                .andExpect(content().string(containsString("Otto intelligenze o nove?")))
                .andExpect(content().string(containsString(
                        "Autopercezione, uso e prestazione non coincidono")))
                .andExpect(content().string(containsString(
                        "non significa bassa intelligenza")))
                .andExpect(content().string(containsString("Project Zero, Harvard")))
                .andExpect(content().string(containsString("L&#39;indagine PIAAC")))
                .andExpect(content().string(containsString("href=\"/test/intelligenza-linguistica\"")));
    }

    @Test
    void intrapersonalIntelligenceGuideRendersTheoryInsightAccuracyAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/intelligenza-intrapersonale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Intelligenza intrapersonale di Gardner: significato | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/intelligenza-intrapersonale\"")))
                .andExpect(content().string(containsString(
                        "Che cosa intende Gardner per intelligenza intrapersonale")))
                .andExpect(content().string(containsString("Otto intelligenze o nove?")))
                .andExpect(content().string(containsString(
                        "Guardarsi dentro, ottenere insight ed essere accurati non coincidono")))
                .andExpect(content().string(containsString(
                        "non significa bassa intelligenza")))
                .andExpect(content().string(containsString("Self-Reflection and Insight Scale")))
                .andExpect(content().string(containsString("26379571")))
                .andExpect(content().string(containsString("href=\"/test/intelligenza-intrapersonale\"")));
    }

    @Test
    void psychologicalResilienceGuideRendersProcessContextItalianEvidenceAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/resilienza-psicologica"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Resilienza psicologica: significato e risorse | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/resilienza-psicologica\"")))
                .andExpect(content().string(containsString("Che cos&#39;è la resilienza psicologica")))
                .andExpect(content().string(containsString("Risorsa, processo ed esito non sono la stessa cosa")))
                .andExpect(content().string(containsString("La resilienza non dipende soltanto dalla persona")))
                .andExpect(content().string(containsString("Soffrire o chiedere aiuto non significa non essere resilienti")))
                .andExpect(content().string(containsString("The Resilience Scale for Adults in Italy")))
                .andExpect(content().string(containsString("27031088")))
                .andExpect(content().string(containsString("significato molto limitato")))
                .andExpect(content().string(containsString("href=\"/test/resilienza-psicologica\"")));
    }

    @Test
    void partnerJealousyGuideRendersMultidimensionalModelConsentSafetyAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/gelosia-partner"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Gelosia verso il partner: significato e confini | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/gelosia-partner\"")))
                .andExpect(content().string(containsString("Che cos&#39;è la gelosia romantica")))
                .andExpect(content().string(containsString("Gelosia, invidia e paura dell&#39;abbandono non coincidono")))
                .andExpect(content().string(containsString("Pensieri, emozioni e comportamenti possono seguire andamenti diversi")))
                .andExpect(content().string(containsString("Provare gelosia non autorizza il controllo")))
                .andExpect(content().string(containsString("2.928 adulti")))
                .andExpect(content().string(containsString("fpsyg.2022.1013584")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString("href=\"/test/gelosia-partner\"")));
    }

    @Test
    void lifeSatisfactionGuideRendersConstructMeasurementLimitsSafetyAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/soddisfazione-vita"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Soddisfazione di vita: significato e misura | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/soddisfazione-vita\"")))
                .andExpect(content().string(containsString("Che cos&#39;è la soddisfazione di vita")))
                .andExpect(content().string(containsString("Soddisfazione, felicità e salute mentale non coincidono")))
                .andExpect(content().string(containsString("La vita nel complesso non è la somma automatica dei suoi ambiti")))
                .andExpect(content().string(containsString("Frequenza e grado di soddisfazione sono domande diverse")))
                .andExpect(content().string(containsString("676 lavoratori adulti")))
                .andExpect(content().string(containsString("flore.unifi.it")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("href=\"/test/soddisfazione-vita\"")));
    }

    @Test
    void ptsdGuideRendersExposureClustersTraumaInformedLimitsSafetyAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/ptsd-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>PTSD nell&#39;adulto: sintomi e supporto | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/ptsd-adulti\"")))
                .andExpect(content().string(containsString("Evento, reazione e PTSD non sono la stessa cosa")))
                .andExpect(content().string(containsString("Quattro famiglie usate per orientare il colloquio clinico")))
                .andExpect(content().string(containsString("Quando allerta ed evitamento possono essere protettivi")))
                .andExpect(content().string(containsString("Non è necessario raccontare o rivivere l&#39;evento")))
                .andExpect(content().string(containsString("modello ibrido a sette fattori")))
                .andExpect(content().string(containsString("ijerph19095282")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString("href=\"/test/ptsd-adulti\"")));
    }

    @Test
    void attachmentStylesGuideRendersDimensionsPrototypesContextAndBidirectionalLink() throws Exception {
        mockMvc.perform(get("/approfondimenti/stili-attaccamento"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "<title>Stili di attaccamento nelle relazioni | Spazio Test</title>")))
                .andExpect(content().string(containsString(
                        "href=\"http://localhost/approfondimenti/stili-attaccamento\"")))
                .andExpect(content().string(containsString("Ansia ed evitamento sono continui, non interruttori")))
                .andExpect(content().string(containsString("Quattro prototipi derivati dalle combinazioni")))
                .andExpect(content().string(containsString("Timoroso-evitante non significa automaticamente disorganizzato")))
                .andExpect(content().string(containsString("Due barre e quattro vicinanze, senza un punteggio generale")))
                .andExpect(content().string(containsString("1.363 adulti italiani")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString("href=\"/test/stili-attaccamento\"")));
    }

    @Test
    void attachmentStylesIntroductionAndQuestionExposeTheDedicatedReferenceAndAnswerScale() throws Exception {
        mockMvc.perform(get("/test/stili-attaccamento"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("relazione sentimentale attuale")))
                .andExpect(content().string(containsString("Orientamento sicuro")))
                .andExpect(content().string(containsString("Orientamento timoroso-evitante")))
                .andExpect(content().string(containsString("Per nulla vero per me")))
                .andExpect(content().string(containsString("caratteristiche intermedie")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/stili-attaccamento\"")));

        MockHttpSession inProgress = new MockHttpSession();
        inProgress.setAttribute("test-attempt-stili-attaccamento",
                new TestAttempt(catalogue.findById("stili-attaccamento").questions().size()));
        mockMvc.perform(get("/test/stili-attaccamento/domanda/1").session(inProgress))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("relazione che hai scelto")))
                .andExpect(content().string(containsString("Per nulla vero per me")))
                .andExpect(content().string(containsString("Del tutto vero per me")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString(">Quasi sempre<"))));
    }

    @Test
    void attachmentStylesResultShowsTwoDimensionsAndFourOrderedOrientationsWithoutOverallBar() throws Exception {
        MockHttpSession session = completedAttempt("stili-attaccamento", 3);

        mockMvc.perform(get("/test/stili-attaccamento/risultato").session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("caratteristiche intermedie tra più orientamenti")))
                .andExpect(content().string(containsString("Le due dimensioni")))
                .andExpect(content().string(containsString("Dal più vicino al meno vicino")))
                .andExpect(content().string(containsString("Orientamento sicuro")))
                .andExpect(content().string(containsString("Orientamento ansioso-preoccupato")))
                .andExpect(content().string(containsString("Orientamento evitante-distanziante")))
                .andExpect(content().string(containsString("Orientamento timoroso-evitante")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("class=\"overall-presence\""))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString(">Le quattro aree<"))));
    }

    @Test
    void attachmentStylesResultPdfUsesTheDedicatedLayout() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/test/stili-attaccamento/risultato/pdf")
                        .session(completedAttempt("stili-attaccamento", 3)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString("analisi-stili-attaccamento.pdf")))
                .andReturn();

        try (PDDocument document = PDDocument.load(mvcResult.getResponse().getContentAsByteArray())) {
            String text = new PDFTextStripper().getText(document);
            assertThat(text)
                    .contains("Come si distribuiscono le risposte")
                    .contains("Dal più vicino al meno vicino")
                    .contains("Orientamento sicuro")
                    .contains("Orientamento timoroso-evitante")
                    .doesNotContain("LE QUATTRO AREE")
                    .doesNotContain("La barra sintetizza la media");
        }
    }

    @Test
    void testLinksToItsPublishedGuide() throws Exception {
        mockMvc.perform(get("/test/tratti-autistici-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/autismo-adulti\"")));

        mockMvc.perform(get("/test/tratti-adhd-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/adhd-adulti\"")));

        mockMvc.perform(get("/test/tratti-ossessivo-compulsivi"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/disturbo-ossessivo-compulsivo\"")));

        mockMvc.perform(get("/test/autostima"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/autostima\"")));

        mockMvc.perform(get("/test/dipendenza-affettiva"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/dipendenza-affettiva\"")));

        mockMvc.perform(get("/test/assertivita"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/assertivita\"")));

        mockMvc.perform(get("/test/intelligenza-emotiva"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/intelligenza-emotiva\"")));

        mockMvc.perform(get("/test/perfezionismo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/perfezionismo\"")));

        mockMvc.perform(get("/test/ansia-sociale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/ansia-sociale\"")));

        mockMvc.perform(get("/test/dinamiche-narcisistiche-partner"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/dinamiche-narcisistiche-coppia\"")));

        mockMvc.perform(get("/test/ansia-generalizzata"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/ansia-generalizzata\"")));

        mockMvc.perform(get("/test/umore-depresso"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/umore-depresso\"")));

        mockMvc.perform(get("/test/people-pleasing"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/people-pleasing\"")));

        mockMvc.perform(get("/test/sindrome-impostore"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/sindrome-impostore\"")));

        mockMvc.perform(get("/test/autosabotaggio"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/autosabotaggio\"")));

        mockMvc.perform(get("/test/tratti-borderline-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("non valuta autolesionismo")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/disturbo-borderline-personalita\"")));

        mockMvc.perform(get("/test/paura-abbandono"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("non classifica uno stile di attaccamento")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/paura-abbandono\"")));

        mockMvc.perform(get("/test/fomo"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("online e fuori dai social")))
                .andExpect(content().string(containsString("non dimostra un uso problematico")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/fomo\"")));

        mockMvc.perform(get("/test/intelligenza-linguistica"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("non misura un'intelligenza indipendente")))
                .andExpect(content().string(containsString("non certifica un talento o un limite")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/intelligenza-linguistica\"")));

        mockMvc.perform(get("/test/intelligenza-intrapersonale"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("non misura un'intelligenza indipendente")))
                .andExpect(content().string(containsString("riflettere spesso non equivale")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/intelligenza-intrapersonale\"")));

        mockMvc.perform(get("/test/resilienza-psicologica"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("processo legato al contesto")))
                .andExpect(content().string(containsString("poco informativo")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/resilienza-psicologica\"")));

        mockMvc.perform(get("/test/gelosia-partner"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("non stabilisce se i sospetti siano fondati")))
                .andExpect(content().string(containsString("non giustifica controllare dispositivi")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/gelosia-partner\"")));

        mockMvc.perform(get("/test/soddisfazione-vita"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("non è la Satisfaction With Life Scale")))
                .andExpect(content().string(containsString("non consente confronti con norme")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/soddisfazione-vita\"")));

        mockMvc.perform(get("/test/ptsd-adulti"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Approfondisci l'argomento")))
                .andExpect(content().string(containsString("senza scriverlo né descriverlo")))
                .andExpect(content().string(containsString("puoi interrompere")))
                .andExpect(content().string(containsString("non diagnostica il PTSD")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("1522")))
                .andExpect(content().string(containsString(
                        "href=\"/approfondimenti/ptsd-adulti\"")));
    }

    @Test
    void unknownGuideReturnsNotFound() throws Exception {
        mockMvc.perform(get("/approfondimenti/non-esiste"))
                .andExpect(status().isNotFound());
    }

    @Test
    void questionAndResultPagesAreExcludedFromSearchIndexing() throws Exception {
        String testId = "tratti-adhd-adulti";
        MockHttpSession inProgress = new MockHttpSession();
        inProgress.setAttribute("test-attempt-" + testId,
                new TestAttempt(catalogue.findById(testId).questions().size()));

        mockMvc.perform(get("/test/{testId}/domanda/1", testId).session(inProgress))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Robots-Tag", "noindex, follow, noarchive"))
                .andExpect(content().string(containsString(
                        "<meta name=\"robots\" content=\"noindex, follow, noarchive\"")));

        mockMvc.perform(get("/test/{testId}/risultato", testId).session(completedAttempt(testId, 3)))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Robots-Tag", "noindex, follow, noarchive"))
                .andExpect(content().string(containsString(
                        "/react/assets/app.css?v=topic-clusters-2")));
    }

    @Test
    void contributionReturnPagesRenderExpectedMessagesAndAreNotIndexed() throws Exception {
        mockMvc.perform(get("/supporto/grazie"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Robots-Tag", "noindex, nofollow, noarchive"))
                .andExpect(content().string(containsString(
                        "/react/assets/app.css?v=react-5")))
                .andExpect(content().string(containsString("Grazie per il tuo sostegno")))
                .andExpect(content().string(containsString("\"status\":\"success\"")));

        mockMvc.perform(get("/supporto/annullato"))
                .andExpect(status().isOk())
                .andExpect(header().string("X-Robots-Tag", "noindex, nofollow, noarchive"))
                .andExpect(content().string(containsString("Pagamento annullato")))
                .andExpect(content().string(containsString("I test e i risultati restano gratuiti")));
    }

    @Test
    void questionPageShowsTheQuestionnaireRecallPeriodAndNonAbsoluteFrequencyAnchors() throws Exception {
        String testId = "umore-depresso";
        MockHttpSession inProgress = new MockHttpSession();
        inProgress.setAttribute("test-attempt-" + testId,
                new TestAttempt(catalogue.findById(testId).questions().size()));

        mockMvc.perform(get("/test/{testId}/domanda/1", testId).session(inProgress))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pensando alle ultime due settimane")))
                .andExpect(content().string(containsString("Quasi sempre")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString(">Sempre<"))));
    }

    @Test
    void questionPageShowsAnOptionalExampleSeparatelyFromTheQuestion() throws Exception {
        String testId = "tratti-autistici-adulti";
        PsychologicalTest test = catalogue.findById(testId);
        int questionNumber = java.util.stream.IntStream.range(0, test.questions().size())
                .filter(index -> test.questions().get(index).example() != null)
                .findFirst()
                .orElseThrow() + 1;
        MockHttpSession inProgress = new MockHttpSession();
        inProgress.setAttribute("test-attempt-" + testId, new TestAttempt(test.questions().size()));

        mockMvc.perform(get("/test/{testId}/domanda/{questionNumber}", testId, questionNumber).session(inProgress))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("class=\"question-example\"")))
                .andExpect(content().string(containsString("Un esempio possibile:")))
                .andExpect(content().string(containsString(test.questions().get(questionNumber - 1).example())));
    }

    @Test
    void limerenceIntroductionAndGuideExposeCategoryRelatedContentAndSafetyLimits() throws Exception {
        mockMvc.perform(get("/test/limerenza"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Limerenza: quando l'innamoramento diventa ossessivo?")))
                .andExpect(content().string(containsString("Pensieri intrusivi e focalizzazione")))
                .andExpect(content().string(containsString("Relazioni e attaccamento")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/limerenza\"")))
                .andExpect(content().string(containsString("href=\"/test/paura-abbandono\"")))
                .andExpect(content().string(containsString("href=\"/test/dipendenza-affettiva\"")))
                .andExpect(content().string(containsString("href=\"/test/gelosia-partner\"")))
                .andExpect(content().string(containsString("112")))
                .andExpect(content().string(containsString("1522")));

        mockMvc.perform(get("/approfondimenti/limerenza"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Un costrutto emergente, non una diagnosi")))
                .andExpect(content().string(containsString("Desiderare reciprocità non dimostra reciprocità")))
                .andExpect(content().string(containsString("L'esperienza interna non autorizza azioni")))
                .andExpect(content().string(containsString("Relazioni e attaccamento")))
                .andExpect(content().string(containsString("href=\"/test/limerenza\"")))
                .andExpect(content().string(containsString("Approfondimenti collegati")));
    }

    @Test
    void limerenceResultRendersOverallAndFourAreaAnalysesWithRelatedTests() throws Exception {
        mockMvc.perform(get("/test/limerenza/risultato").session(completedAttempt("limerenza", 5)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Le dinamiche associate alla limerenza sembrano molto presenti in più aree")))
                .andExpect(content().string(containsString("Pensieri intrusivi e focalizzazione")))
                .andExpect(content().string(containsString("Reciprocità e oscillazioni emotive")))
                .andExpect(content().string(containsString("Idealizzazione e interpretazione dei segnali")))
                .andExpect(content().string(containsString("Azioni, confini e impatto quotidiano")))
                .andExpect(content().string(containsString("aria-valuenow=\"100\"")))
                .andExpect(content().string(containsString("href=\"/test/limerenza/risultato/pdf\"")))
                .andExpect(content().string(containsString("Test correlati")))
                .andExpect(content().string(containsString("href=\"/test/dipendenza-affettiva\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/limerenza\"")));
    }

    @Test
    void limerenceResultCanBeDownloadedAsReadablePdf() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/test/limerenza/risultato/pdf")
                        .session(completedAttempt("limerenza", 5)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString("analisi-limerenza.pdf")))
                .andReturn();

        try (PDDocument document = PDDocument.load(mvcResult.getResponse().getContentAsByteArray())) {
            String text = new PDFTextStripper().getText(document);
            assertThat(text)
                    .contains("Limerenza")
                    .contains("Pensieri intrusivi e focalizzazione")
                    .contains("Reciprocità e oscillazioni emotive")
                    .contains("non diagnostica limerenza")
                    .contains("consenso");
        }
    }

    @Test
    void parentificationIntroductionAndGuideExposeCategoryRelatedContentAndRetrospectiveLimits() throws Exception {
        mockMvc.perform(get("/test/parentificazione"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Parentificazione: eri il genitore dei tuoi genitori?")))
                .andExpect(content().string(containsString("Responsabilità pratiche e organizzative")))
                .andExpect(content().string(containsString("Relazioni e attaccamento")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/parentificazione\"")))
                .andExpect(content().string(containsString("href=\"/test/paura-abbandono\"")))
                .andExpect(content().string(containsString("href=\"/test/stili-attaccamento\"")))
                .andExpect(content().string(containsString("href=\"/test/limerenza\"")))
                .andExpect(content().string(containsString("prima dei 18 anni")))
                .andExpect(content().string(containsString("non attribuisce colpe")));

        mockMvc.perform(get("/approfondimenti/parentificazione"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Responsabilità da adulto durante la crescita")))
                .andExpect(content().string(containsString("Non ogni responsabilità familiare è parentificazione")))
                .andExpect(content().string(containsString("La memoria non è una registrazione completa")))
                .andExpect(content().string(containsString("Relazioni e attaccamento")))
                .andExpect(content().string(containsString("href=\"/test/parentificazione\"")))
                .andExpect(content().string(containsString("Approfondimenti collegati")));
    }

    @Test
    void parentificationResultRendersOverallAndFourAreaAnalysesWithRelatedTests() throws Exception {
        mockMvc.perform(get("/test/parentificazione/risultato")
                        .session(completedAttempt("parentificazione", 5)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Le esperienze associate alla parentificazione sembrano molto presenti in più aree")))
                .andExpect(content().string(containsString("Responsabilità pratiche e organizzative")))
                .andExpect(content().string(containsString("Accudimento emotivo e mediazione")))
                .andExpect(content().string(containsString("Inversione dei ruoli e obbligo percepito")))
                .andExpect(content().string(containsString("Spazio per i propri bisogni e riconoscimento")))
                .andExpect(content().string(containsString("aria-valuenow=\"100\"")))
                .andExpect(content().string(containsString("href=\"/test/parentificazione/risultato/pdf\"")))
                .andExpect(content().string(containsString("Test correlati")))
                .andExpect(content().string(containsString("href=\"/test/paura-abbandono\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/parentificazione\"")));
    }

    @Test
    void parentificationResultCanBeDownloadedAsReadablePdf() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/test/parentificazione/risultato/pdf")
                        .session(completedAttempt("parentificazione", 5)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString("analisi-parentificazione.pdf")))
                .andReturn();

        try (PDDocument document = PDDocument.load(mvcResult.getResponse().getContentAsByteArray())) {
            String text = new PDFTextStripper().getText(document);
            assertThat(text)
                    .contains("Parentificazione")
                    .contains("Responsabilità pratiche e organizzative")
                    .contains("Accudimento emotivo e mediazione")
                    .contains("ricordi")
                    .contains("retrospettivi")
                    .contains("dimostra parentificazione");
        }
    }

    @Test
    void gaslightingIntroductionAndGuideExposeCategoryRelatedContentAndSafetyLimits() throws Exception {
        mockMvc.perform(get("/test/gaslighting"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ho subito gaslighting?")))
                .andExpect(content().string(containsString("Negazione e alterazione degli eventi")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/gaslighting\"")))
                .andExpect(content().string(containsString("href=\"/test/love-bombing\"")))
                .andExpect(content().string(containsString("una sola persona")))
                .andExpect(content().string(containsString("non dimostra gaslighting")));

        mockMvc.perform(get("/approfondimenti/gaslighting"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Un andamento relazionale, non una singola frase")))
                .andExpect(content().string(containsString("Disaccordo, inganno e gaslighting non sono sinonimi")))
                .andExpect(content().string(containsString("Il bisogno di aiuto non dipende dal punteggio")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("href=\"/test/gaslighting\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/love-bombing\"")))
                .andExpect(content().string(containsString("Approfondimenti collegati")));
    }

    @Test
    void gaslightingResultRendersOverallAndFourAreaAnalysesWithRelatedTests() throws Exception {
        mockMvc.perform(get("/test/gaslighting/risultato")
                        .session(completedAttempt("gaslighting", 5)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Le esperienze associate al gaslighting sembrano molto presenti in più aree")))
                .andExpect(content().string(containsString("Negazione e alterazione degli eventi")))
                .andExpect(content().string(containsString("Svalutazione di percezioni ed emozioni")))
                .andExpect(content().string(containsString("Ribaltamento della responsabilità e pressione")))
                .andExpect(content().string(containsString("Autodubbio e riduzione dell'autonomia")))
                .andExpect(content().string(containsString("aria-valuenow=\"100\"")))
                .andExpect(content().string(containsString("href=\"/test/gaslighting/risultato/pdf\"")))
                .andExpect(content().string(containsString("Test correlati")))
                .andExpect(content().string(containsString("href=\"/test/love-bombing\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/gaslighting\"")));
    }

    @Test
    void gaslightingResultCanBeDownloadedAsReadablePdf() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/test/gaslighting/risultato/pdf")
                        .session(completedAttempt("gaslighting", 5)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString("analisi-gaslighting.pdf")))
                .andReturn();

        try (PDDocument document = PDDocument.load(mvcResult.getResponse().getContentAsByteArray())) {
            String text = new PDFTextStripper().getText(document);
            assertThat(text)
                    .contains("Ho subito gaslighting?")
                    .contains("Negazione e alterazione degli eventi")
                    .contains("Svalutazione di percezioni ed emozioni")
                    .contains("non accerta i fatti")
                    .contains("non dimostra gaslighting")
                    .contains("1522");
        }
    }

    @Test
    void loveBombingIntroductionAndGuideExposeCategoryRelatedContentAndSafetyLimits() throws Exception {
        mockMvc.perform(get("/test/love-bombing"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ho subito love bombing?")))
                .andExpect(content().string(containsString("Intensità di attenzioni e idealizzazione")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/love-bombing\"")))
                .andExpect(content().string(containsString("href=\"/test/gaslighting\"")))
                .andExpect(content().string(containsString("una sola relazione romantica")))
                .andExpect(content().string(containsString("non dimostrano da soli love bombing")));

        mockMvc.perform(get("/approfondimenti/love-bombing"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Un'etichetta emergente, non una diagnosi")))
                .andExpect(content().string(containsString("Affetto intenso e love bombing non sono sinonimi")))
                .andExpect(content().string(containsString("Le evidenze disponibili non offrono una soglia italiana")))
                .andExpect(content().string(containsString("Il bisogno di aiuto non dipende dal punteggio")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("href=\"/test/love-bombing\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/gaslighting\"")))
                .andExpect(content().string(containsString("Approfondimenti collegati")));
    }

    @Test
    void loveBombingResultRendersOverallAndFourAreaAnalysesWithRelatedTests() throws Exception {
        mockMvc.perform(get("/test/love-bombing/risultato")
                        .session(completedAttempt("love-bombing", 5)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Le dinamiche associate al love bombing sembrano molto presenti in più aree")))
                .andExpect(content().string(containsString("Intensità di attenzioni e idealizzazione")))
                .andExpect(content().string(containsString("Accelerazione del legame e promesse")))
                .andExpect(content().string(containsString("Pressione, esclusività e rispetto dei confini")))
                .andExpect(content().string(containsString("Instabilità delle attenzioni e impatto sull'autonomia")))
                .andExpect(content().string(containsString("aria-valuenow=\"100\"")))
                .andExpect(content().string(containsString("href=\"/test/love-bombing/risultato/pdf\"")))
                .andExpect(content().string(containsString("Test correlati")))
                .andExpect(content().string(containsString("href=\"/test/gaslighting\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/love-bombing\"")));
    }

    @Test
    void loveBombingResultCanBeDownloadedAsReadablePdf() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/test/love-bombing/risultato/pdf")
                        .session(completedAttempt("love-bombing", 5)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString("analisi-love-bombing.pdf")))
                .andReturn();

        try (PDDocument document = PDDocument.load(mvcResult.getResponse().getContentAsByteArray())) {
            String text = new PDFTextStripper().getText(document);
            assertThat(text)
                    .contains("Ho subito love bombing?")
                    .contains("Intensità di attenzioni e idealizzazione")
                    .contains("Accelerazione del legame e promesse")
                    .contains("non dimostra love bombing")
                    .contains("narcisismo")
                    .contains("1522");
        }
    }

    @Test
    void breadcrumbingIntroductionAndGuideExposeCategoryRelatedContentAndSafetyLimits() throws Exception {
        mockMvc.perform(get("/test/breadcrumbing"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Ho subito breadcrumbing?")))
                .andExpect(content().string(containsString("Intermittenza dei contatti e riattivazioni")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/breadcrumbing\"")))
                .andExpect(content().string(containsString("href=\"/test/love-bombing\"")))
                .andExpect(content().string(containsString("una sola relazione o frequentazione")))
                .andExpect(content().string(containsString("non dimostrano da soli breadcrumbing")));

        mockMvc.perform(get("/approfondimenti/breadcrumbing"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Un andamento osservabile, non una lettura delle intenzioni")))
                .andExpect(content().string(containsString("Breadcrumbing, ghosting e disponibilità variabile non sono sinonimi")))
                .andExpect(content().string(containsString("Non esiste una soglia validata per gli adulti italiani")))
                .andExpect(content().string(containsString("Il bisogno di aiuto non dipende dal punteggio")))
                .andExpect(content().string(containsString("Ambiguità e manipolazione relazionale")))
                .andExpect(content().string(containsString("href=\"/test/breadcrumbing\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/love-bombing\"")))
                .andExpect(content().string(containsString("Approfondimenti collegati")));
    }

    @Test
    void breadcrumbingResultRendersOverallAndFourAreaAnalysesWithRelatedTests() throws Exception {
        mockMvc.perform(get("/test/breadcrumbing/risultato")
                        .session(completedAttempt("breadcrumbing", 5)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(
                        "Le dinamiche associate al breadcrumbing sembrano molto presenti in più aree")))
                .andExpect(content().string(containsString("Intermittenza dei contatti e riattivazioni")))
                .andExpect(content().string(containsString("Segnali di interesse e aspettative")))
                .andExpect(content().string(containsString("Coerenza tra parole e azioni")))
                .andExpect(content().string(containsString("Chiarezza, reciprocità e progressione")))
                .andExpect(content().string(containsString("aria-valuenow=\"100\"")))
                .andExpect(content().string(containsString("href=\"/test/breadcrumbing/risultato/pdf\"")))
                .andExpect(content().string(containsString("Test correlati")))
                .andExpect(content().string(containsString("href=\"/test/love-bombing\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti/breadcrumbing\"")));
    }

    @Test
    void breadcrumbingResultCanBeDownloadedAsReadablePdf() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/test/breadcrumbing/risultato/pdf")
                        .session(completedAttempt("breadcrumbing", 5)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString("analisi-breadcrumbing.pdf")))
                .andReturn();

        try (PDDocument document = PDDocument.load(mvcResult.getResponse().getContentAsByteArray())) {
            String text = new PDFTextStripper().getText(document);
            assertThat(text)
                    .contains("Ho subito breadcrumbing?")
                    .contains("Intermittenza dei contatti e riattivazioni")
                    .contains("Coerenza tra parole e azioni")
                    .contains("non dimostra", "breadcrumbing")
                    .contains("intenzioni")
                    .contains("1522");
        }
    }

    @Test
    void robotsAndSitemapExposeOnlyCanonicalLandingPages() throws Exception {
        mockMvc.perform(get("/robots.txt"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("User-agent: *")))
                .andExpect(content().string(containsString("Sitemap: http://localhost/sitemap.xml")));

        mockMvc.perform(get("/sitemap.xml"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost/metodo-e-fonti")))
                .andExpect(content().string(containsString("http://localhost/il-progetto")))
                .andExpect(content().string(containsString("http://localhost/approfondimenti")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/autismo-adulti")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/adhd-adulti")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/disturbo-ossessivo-compulsivo")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/autostima")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/dipendenza-affettiva")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/assertivita")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/intelligenza-emotiva")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/perfezionismo")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/ansia-sociale")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/dinamiche-narcisistiche-coppia")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/ansia-generalizzata")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/umore-depresso")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/people-pleasing")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/sindrome-impostore")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/autosabotaggio")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/disturbo-borderline-personalita")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/paura-abbandono")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/fomo")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/intelligenza-linguistica")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/intelligenza-intrapersonale")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/resilienza-psicologica")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/gelosia-partner")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/soddisfazione-vita")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/ptsd-adulti")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/stili-attaccamento")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/limerenza")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/parentificazione")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/gaslighting")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/love-bombing")))
                .andExpect(content().string(containsString(
                        "http://localhost/approfondimenti/breadcrumbing")))
                .andExpect(content().string(containsString("http://localhost/test/tratti-autistici-adulti")))
                .andExpect(content().string(containsString("http://localhost/test/autosabotaggio")))
                .andExpect(content().string(containsString("http://localhost/test/tratti-borderline-adulti")))
                .andExpect(content().string(containsString("http://localhost/test/paura-abbandono")))
                .andExpect(content().string(containsString("http://localhost/test/fomo")))
                .andExpect(content().string(containsString("http://localhost/test/intelligenza-linguistica")))
                .andExpect(content().string(containsString("http://localhost/test/intelligenza-intrapersonale")))
                .andExpect(content().string(containsString("http://localhost/test/resilienza-psicologica")))
                .andExpect(content().string(containsString("http://localhost/test/gelosia-partner")))
                .andExpect(content().string(containsString("http://localhost/test/soddisfazione-vita")))
                .andExpect(content().string(containsString("http://localhost/test/ptsd-adulti")))
                .andExpect(content().string(containsString("http://localhost/test/stili-attaccamento")))
                .andExpect(content().string(containsString("http://localhost/test/limerenza")))
                .andExpect(content().string(containsString("http://localhost/test/parentificazione")))
                .andExpect(content().string(containsString("http://localhost/test/gaslighting")))
                .andExpect(content().string(containsString("http://localhost/test/breadcrumbing")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("/domanda/"))))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("/risultato"))));
    }

    @Test
    void resultRendersOverallBarFromTheGeneralAverage() throws Exception {
        String testId = "tratti-adhd-adulti";
        PsychologicalTest test = catalogue.findById(testId);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int question = 0; question < test.questions().size(); question++) {
            attempt.answer(question, 3);
        }
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + testId, attempt);

        mockMvc.perform(get("/test/{testId}/risultato", testId).session(session))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "class=\"overall-presence-track\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "Le aree esplorate")))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString(
                        ">Le quattro aree<"))))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("aria-valuenow=\"50\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/tratti-adhd-adulti/risultato/pdf\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "Approfondisci l'argomento")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Test correlati")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "class=\"related-content result-related-content\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/tratti-autistici-adulti\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/intelligenza-linguistica\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/approfondimenti/adhd-adulti\"")));
    }

    @Test
    void completedResultCanBeDownloadedAsAReadablePdf() throws Exception {
        String testId = "autostima";
        MockHttpSession session = completedAttempt(testId, 3);

        MvcResult mvcResult = mockMvc.perform(get("/test/{testId}/risultato/pdf", testId).session(session))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/pdf"))
                .andExpect(header().string("Content-Disposition", containsString(
                        "analisi-autostima.pdf")))
                .andExpect(header().string("Cache-Control", containsString("no-store")))
                .andExpect(header().string("X-Robots-Tag", "noindex, nofollow, noarchive"))
                .andReturn();

        byte[] pdf = mvcResult.getResponse().getContentAsByteArray();
        assertThat(pdf).startsWith("%PDF-".getBytes());

        String previewPath = System.getProperty("pdf.preview.path");
        if (previewPath != null && !previewPath.isBlank()) {
            Path output = Path.of(previewPath).toAbsolutePath();
            Files.createDirectories(output.getParent());
            Files.write(output, pdf);
        }

        try (PDDocument document = PDDocument.load(pdf)) {
            String text = new PDFTextStripper().getText(document);
            assertThat(document.getNumberOfPages()).isGreaterThanOrEqualTo(1);
            assertThat(text)
                    .contains("Spazio Test")
                    .contains("Autostima")
                    .contains("DIFFICOLTÀ COMPLESSIVE RELATIVE ALL'AUTOSTIMA")
                    .contains("Valore personale e autoaccettazione")
                    .contains("finalità esclusivamente informative");
        }
    }

    @Test
    void pdfDownloadRedirectsToTheTestWhenTheAttemptIsMissing() throws Exception {
        mockMvc.perform(get("/test/{testId}/risultato/pdf", "tratti-adhd-adulti"))
                .andExpect(status().isSeeOther())
                .andExpect(header().string("Location", "/test/tratti-adhd-adulti"));
    }

    private MockHttpSession completedAttempt(String testId, int answer) {
        PsychologicalTest test = catalogue.findById(testId);
        TestAttempt attempt = new TestAttempt(test.questions().size());
        for (int question = 0; question < test.questions().size(); question++) {
            attempt.answer(question, answer);
        }
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("test-attempt-" + testId, attempt);
        return session;
    }
}
