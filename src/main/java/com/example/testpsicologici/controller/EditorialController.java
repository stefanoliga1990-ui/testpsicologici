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
            "Psicologa · Specializzanda in Psicoterapia presso la Scuola di Psicologia della Salute.",
            "Psicologa con un percorso che unisce formazione scientifica, ricerca psicosociale ed esperienza diretta sul campo. Laureata magistrale in Psicologia sociale, del lavoro e delle organizzazioni presso l'Università degli Studi di Palermo con 110/110 e lode, ha conseguito un Master di II livello in Gestione e Sviluppo delle Risorse Umane e una formazione specialistica in autismo e neurodiversità.\n\n"
                    + "Ha lavorato in ambito psicoeducativo e sociale, occupandosi di inclusione, sostegno a persone e famiglie con bisogni speciali, prevenzione del disagio, promozione del benessere e progettazione di ricerche-intervento. È autrice di contributi scientifici e di divulgazione psicologica.\n\n"
                    + "È Docente di Psicologia di Comunità presso l'Università degli Studi \"Guglielmo Marconi\" e Cultrice della Materia in Psicologia Sociale presso l'Università degli Studi Niccolò Cusano.\n\n"
                    + "Attualmente conduce gruppi di supporto e psicoeducazione per la gestione di conflitti, ansia e stress, e percorsi di sostegno alla genitorialità orientati a valorizzare risorse e punti di forza. In qualità di consulente, progetta interventi di promozione della salute e programmi di inclusione e sviluppo giovanile, integrando i modelli delle Life Skills e del Service-Learning.",
            "Alessia.liga3@gmail.com",
            "+39 392 240 7494",
            "+393922407494",
            "https://www.linkedin.com/in/alessia-liga-a057b985");

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
        model.addAttribute("reviewerImageUrl", siteUrlService.canonicalUrl(
                request, "/images/alessia-liga-revisore-professionale-2.jpg"));
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
