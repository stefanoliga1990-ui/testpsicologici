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
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/tratti-autistici-adulti\"")))
                .andExpect(content().string(containsString("href=\"/approfondimenti\"")))
                .andExpect(content().string(containsString("href=\"/metodo-e-fonti\"")))
                .andExpect(content().string(containsString("href=\"/il-progetto\"")));
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
    }

    @Test
    void introductionRendersUniqueSeoMetadataEditorialContentAndReferences() throws Exception {
        mockMvc.perform(get("/test/{testId}", "tratti-adhd-adulti"))
                .andExpect(status().isOk())
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
                .andExpect(content().string(containsString("ADHD in adults — NHS")));
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
                .andExpect(content().string(containsString("/react/assets/app.js?v=react-3")))
                .andExpect(content().string(containsString("id=\"guide-search-input\"")))
                .andExpect(content().string(containsString("data-guide-card")))
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
                        "<h3>Paura dell&#39;abbandono</h3>")));
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
                .andExpect(header().string("X-Robots-Tag", "noindex, follow, noarchive"));
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
                .andExpect(content().string(containsString("http://localhost/test/tratti-autistici-adulti")))
                .andExpect(content().string(containsString("http://localhost/test/autosabotaggio")))
                .andExpect(content().string(containsString("http://localhost/test/tratti-borderline-adulti")))
                .andExpect(content().string(containsString("http://localhost/test/paura-abbandono")))
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
                        "class=\"notice-box result-notice\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "Come leggere il risultato")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("aria-valuenow=\"50\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "href=\"/test/tratti-adhd-adulti/risultato/pdf\"")));
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
