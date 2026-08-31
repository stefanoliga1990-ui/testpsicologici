package com.example.testpsicologici.controller;

import com.example.testpsicologici.model.EditorialReviewer;
import com.example.testpsicologici.service.SiteUrlService;
import com.example.testpsicologici.service.TestCatalogue;
import com.example.testpsicologici.service.GuideCatalogue;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EditorialController {

    private static final EditorialReviewer PROFESSIONAL_REVIEWER = new EditorialReviewer(
            "Alessia Liga",
            "Psicologa · Specializzanda in Psicoterapia della Salute",
            "Psicologa con una formazione centrata sulla psicologia sociale, del lavoro, di comunità e della salute, laureata magistrale in Psicologia sociale, del lavoro e delle organizzazioni presso l’Università degli Studi di Palermo con 110/110 e lode. Ha inoltre conseguito un Master di II livello in Gestione e Sviluppo delle Risorse Umane e ha approfondito l’ambito dell’autismo e della neurodiversità attraverso una formazione specialistica. Attualmente frequenta la Scuola di Specializzazione in Psicoterapia – Psicologia della Salute presso Sapienza Università di Roma. Nel corso della sua esperienza professionale ha lavorato in ambito psicoeducativo, scolastico e comunitario, occupandosi di sostegno a persone con disabilità e bisogni speciali, neurodiversità, inclusione e sviluppo dell’autonomia. Ha maturato esperienza nella prevenzione del disagio, nella promozione del benessere psicologico e organizzativo, nella progettazione di interventi di comunità e nella ricerca psicosociale. Ha inoltre svolto attività di formazione, tutoraggio e supporto metodologico in ambito universitario. Attualmente è Docente di Psicologia di Comunità presso l’Università degli Studi “Guglielmo Marconi” e Cultrice della Materia in Psicologia Sociale presso l’Università degli Studi Niccolò Cusano, partecipando anche a commissioni di laurea e d’esame.",
            "Alessia.liga3@gmail.com",
            "+39 392 240 7494",
            "+393922407494");

    private final TestCatalogue catalogue;
    private final SiteUrlService siteUrlService;
    private final GuideCatalogue guideCatalogue;

    public EditorialController(TestCatalogue catalogue, SiteUrlService siteUrlService,
                               GuideCatalogue guideCatalogue) {
        this.catalogue = catalogue;
        this.siteUrlService = siteUrlService;
        this.guideCatalogue = guideCatalogue;
    }

    @GetMapping("/metodo-e-fonti")
    public String methodAndSources(HttpServletRequest request, Model model) {
        var tests = catalogue.findAll();
        var guides = guideCatalogue.findAll();
        model.addAttribute("reviewer", PROFESSIONAL_REVIEWER);
        model.addAttribute("tests", tests);
        model.addAttribute("guides", guides);
        model.addAttribute("reactPageData", ReactPageData.of(
                "method", "tests", tests, "guides", guides, "reviewer", PROFESSIONAL_REVIEWER));
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/metodo-e-fonti"));
        model.addAttribute("siteUrl", siteUrlService.canonicalUrl(request, "/"));
        model.addAttribute("projectUrl", siteUrlService.canonicalUrl(request, "/il-progetto"));
        return "method-and-sources";
    }

    @GetMapping("/il-progetto")
    public String project(HttpServletRequest request, Model model) {
        model.addAttribute("reactPageData", ReactPageData.of("project"));
        model.addAttribute("canonicalUrl", siteUrlService.canonicalUrl(request, "/il-progetto"));
        model.addAttribute("siteUrl", siteUrlService.canonicalUrl(request, "/"));
        model.addAttribute("methodUrl", siteUrlService.canonicalUrl(request, "/metodo-e-fonti"));
        return "project";
    }
}
