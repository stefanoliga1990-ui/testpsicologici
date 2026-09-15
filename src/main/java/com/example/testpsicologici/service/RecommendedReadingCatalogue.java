package com.example.testpsicologici.service;

import com.example.testpsicologici.model.RecommendedReading;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendedReadingCatalogue {

    private final Map<String, List<RecommendedReading>> readingsByTestId = Map.of(
            "tratti-autistici-adulti", List.of(
                    new RecommendedReading(
                            "La differenza invisibile",
                            "Julie Dachez e Mademoiselle Caroline",
                            "Una graphic novel sulla vita di Marguerite e sul modo in cui alcune esperienze sociali e sensoriali possono essere vissute nell'età adulta.",
                            "Racconta un'esperienza particolare: non è un test e non serve a stabilire se una persona sia autistica.",
                            "https://www.amazon.it/-/en/differenza-invisibile-Caroline-Mademoiselle/dp/8868956004?crid=2IJUWCYK4HXJF&dib=eyJ2IjoiMSJ9.epShUfSiSkQVueGrdMsLKQ.GS6okDmIatM2p7-iK3JcyplpgiieBP6r_12zBcJLI1Y&dib_tag=se&keywords=9788868956004&qid=1789482441&sprefix=9788868956004%2Caps%2C153&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=d798aa3baaa2f274290edcb540cdc5ec&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Esplorare il proprio autismo",
                            "Chiara Mangione e Francesca Mela",
                            "Un volume dedicato all'esplorazione dell'esperienza di adulti nello spettro, con capitoli tematici e testimonianze.",
                            "È rivolto a un contesto adulto nello spettro e non conferma il risultato del questionario né sostituisce una valutazione professionale.",
                            "https://www.amazon.it/-/en/Esplorare-proprio-autismo-formazione-benessere/dp/B0BT4WCXFB?crid=128Q68GPXJJJ9&dib=eyJ2IjoiMSJ9.f1PNBWyx7VetB2gPq9_EGQ.rG97biUWu2ThECB7p1jpi67jiYiIryxDwV0Kdt03xG4&dib_tag=se&keywords=9791254910689&qid=1789482565&sprefix=9791254910689%2Caps%2C141&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=dc6aa3de7170dc51f979a5b0e09beb6d&ref_=as_li_ss_tl",
                            true
                    )
            ),
            "tratti-adhd-adulti", List.of(
                    new RecommendedReading(
                            "L'anno che ho incontrato il mio cervello",
                            "Matilda Boseley",
                            "Un racconto in prima persona che intreccia l'esperienza dell'autrice, informazioni accessibili e temi come relazioni, studio, lavoro, burnout e cura di sé.",
                            "Racconta il percorso di una persona con diagnosi e non permette di dedurre che chi completa il questionario abbia l'ADHD.",
                            "https://www.amazon.it/-/en/incontrato-cervello-Diario-viaggio-scoperto/dp/8859044669?crid=3PFRX3QPBDULK&dib=eyJ2IjoiMSJ9.QOCxbW2MvIcVRWqZhRQLeQ.yWaO2YjrqjMYInT55owv73pyCrhIgPzhUiKK_mNTAyc&dib_tag=se&keywords=9788859044666&qid=1789503160&sprefix=9788859044666%2Caps%2C160&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=06292617b9f411b0bdcc49b1d3d1d774&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "ADHD negli adulti",
                            "Giovanni Migliarese, Viviana Venturi, Yacob Levin Reibman e Claudio Mencacci",
                            "Un manuale sull'ADHD in età adulta e su un modello di intervento psicoeducativo, con capitoli su manifestazioni, condizioni coesistenti, organizzazione, relazioni e materiali per gli utenti.",
                            "È rivolto principalmente a professionisti e non va presentato come strumento di autodiagnosi o come indicazione di trattamento per chi completa il test.",
                            "https://www.amazon.it/-/en/negli-adulti-modello-lintervento-psicoeducativo/dp/8859030293?dib=eyJ2IjoiMSJ9.Xwfk3JWdyrKgfws-RGVgfw.orwXL2bOHxgTCxRhhS-5s7X2aKxGUPnYLsfpe0FDGgo&dib_tag=se&keywords=9788859030294&qid=1789503354&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=a5799dce98e7af80ff45383edb70cc80&ref_=as_li_ss_tl",
                            true
                    )
            ),
            "tratti-ossessivo-compulsivi", List.of(
                    new RecommendedReading(
                            "Vincere le ossessioni",
                            "Gabriele Melli",
                            "Un testo introduttivo e operativo che presenta le caratteristiche del DOC, i suoi possibili meccanismi di mantenimento e un percorso di auto-aiuto basato sull'approccio cognitivo-comportamentale.",
                            "Un manuale di auto-aiuto non permette di stabilire una diagnosi e non sostituisce una valutazione o un trattamento concordato con un professionista.",
                            "https://www.amazon.it/-/en/Vincere-ossessioni-affrontare-disturbo-ossessivo-compulsivo/dp/8859044634?dib=eyJ2IjoiMSJ9.DzFfu6WwZQocYEfCi53IylV0qpMctNfXIbjh7vDLfg_IKl3VZOOVt3IRYdJdftL-.DVzqnucH4fUOWFe9wBjdm6LcfTpr0HobMjlOg-zOFcs&dib_tag=se&keywords=Vincere+le+ossessioni+Gabriele+Melli&qid=1789505096&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=2513be9f53d87f24a6ea8ffd828402da&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "La mente ossessiva",
                            "Francesco Mancini (a cura di)",
                            "Un volume specialistico che approfondisce un modello cognitivista di comprensione del disturbo ossessivo-compulsivo e della sua cura, collegandolo alla ricerca e alla pratica clinica.",
                            "È un testo tecnico legato a uno specifico modello teorico e non offre una lettura personalizzata delle risposte al questionario.",
                            "https://www.amazon.it/-/en/mente-ossessiva-Curare-disturbo-ossessivo-compulsivo/dp/8860308224?crid=3JTQABH2BWKIB&dib=eyJ2IjoiMSJ9.GEHjRcJvU04sDFt7pE3tCw.wSN9tKHCL6iDN8osFcY4pjwr_qBKdN1T29FqtgNQU_I&dib_tag=se&keywords=9788860308221&qid=1789505129&sprefix=9788860308221%2Caps%2C331&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=68d714a6089fb8ae408370d07a056ed8&ref_=as_li_ss_tl",
                            true
                    )
            )
    );

    public List<RecommendedReading> findByTestId(String testId) {
        return readingsByTestId.getOrDefault(testId, List.of());
    }
}
