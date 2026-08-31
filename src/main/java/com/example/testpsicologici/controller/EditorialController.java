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
            "Laureata magistrale in Psicologia sociale, del lavoro e delle organizzazioni presso l’Università degli Studi di Palermo con 110/110 e lode. Ha conseguito un Master di II livello in Gestione e Sviluppo delle Risorse Umane e una formazione specialistica in autismo e neurodiversità.\n\n"
                    + "Nel corso della sua esperienza professionale ha lavorato in ambito psicoeducativo, scolastico e comunitario, occupandosi di supporto e sostegno a persone con disabilità e famiglie con bisogni speciali, prevenzione del disagio, promozione del benessere e ricerca psicosociale. È autrice di vari contributi scientifici accademici e di divulgazione psicologica.\n\n"
                    + "Attualmente è Docente di Psicologia di Comunità presso l’Università degli Studi “Guglielmo Marconi” e Cultrice della Materia in Psicologia Sociale presso l’Università degli Studi Niccolò Cusano.\n\n"
                    + "In qualità di consulente, si dedica alla progettazione di interventi di welfare di comunità e all’implementazione di servizi di psicologia delle cure primarie.\n\n"
                    + "Attraverso un approccio orientato alla salutogenesi, realizza workshop psicoeducativi per la gestione dello stress e promozione della salute, percorsi di sostegno alla genitorialità, mirati a far emergere i punti di forza e le risorse già presenti nelle dinamiche familiari per promuoverne la resilienza.\n\n"
                    + "Cura inoltre interventi in ambito scolastico orientati allo sviluppo giovanile, integrando i modelli delle Life Skills e del Service-Learning. A supporto di queste attività, conduce progetti di ricerca-intervento avvalendosi di metodologie miste, che uniscono l’analisi dei dati psicometrici all’approfondimento qualitativo tramite studi di caso.",
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
