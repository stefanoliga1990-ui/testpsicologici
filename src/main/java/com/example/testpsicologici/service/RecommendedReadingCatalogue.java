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
                            "https://www.amazon.it/-/en/differenza-invisibile-Caroline-Mademoiselle/dp/8868956004?crid=2IJUWCYK4HXJF&dib=eyJ2IjoiMSJ9.epShUfSiSkQVueGrdMsLKQ.GS6okDmIatM2p7-iK3JcyplpgiieBP6r_12zBcJLI1Y&dib_tag=se&keywords=9788868956004&qid=1789482441&sprefix=9788868956004%2Caps%2C153&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=d798aa3baaa2f274290edcb540cdc5ec&ref_=as_li_ss_tl"
                    ),
                    new RecommendedReading(
                            "Esplorare il proprio autismo",
                            "Chiara Mangione e Francesca Mela",
                            "Un volume dedicato all'esplorazione dell'esperienza di adulti nello spettro, con capitoli tematici e testimonianze.",
                            "È rivolto a un contesto adulto nello spettro e non conferma il risultato del questionario né sostituisce una valutazione professionale.",
                            "https://www.amazon.it/-/en/Esplorare-proprio-autismo-formazione-benessere/dp/B0BT4WCXFB?crid=128Q68GPXJJJ9&dib=eyJ2IjoiMSJ9.f1PNBWyx7VetB2gPq9_EGQ.rG97biUWu2ThECB7p1jpi67jiYiIryxDwV0Kdt03xG4&dib_tag=se&keywords=9791254910689&qid=1789482565&sprefix=9791254910689%2Caps%2C141&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=dc6aa3de7170dc51f979a5b0e09beb6d&ref_=as_li_ss_tl"
                    )
            ),
            "tratti-adhd-adulti", List.of(
                    new RecommendedReading(
                            "L'anno che ho incontrato il mio cervello",
                            "Matilda Boseley",
                            "Un racconto in prima persona che intreccia l'esperienza dell'autrice, informazioni accessibili e temi come relazioni, studio, lavoro, burnout e cura di sé.",
                            "Racconta il percorso di una persona con diagnosi e non permette di dedurre che chi completa il questionario abbia l'ADHD.",
                            "https://www.amazon.it/-/en/incontrato-cervello-Diario-viaggio-scoperto/dp/8859044669?crid=3PFRX3QPBDULK&dib=eyJ2IjoiMSJ9.QOCxbW2MvIcVRWqZhRQLeQ.yWaO2YjrqjMYInT55owv73pyCrhIgPzhUiKK_mNTAyc&dib_tag=se&keywords=9788859044666&qid=1789503160&sprefix=9788859044666%2Caps%2C160&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=06292617b9f411b0bdcc49b1d3d1d774&ref_=as_li_ss_tl"
                    ),
                    new RecommendedReading(
                            "ADHD negli adulti",
                            "Giovanni Migliarese, Viviana Venturi, Yacob Levin Reibman e Claudio Mencacci",
                            "Un manuale sull'ADHD in età adulta e su un modello di intervento psicoeducativo, con capitoli su manifestazioni, condizioni coesistenti, organizzazione, relazioni e materiali per gli utenti.",
                            "È rivolto principalmente a professionisti e non va presentato come strumento di autodiagnosi o come indicazione di trattamento per chi completa il test.",
                            "https://www.amazon.it/-/en/negli-adulti-modello-lintervento-psicoeducativo/dp/8859030293?dib=eyJ2IjoiMSJ9.Xwfk3JWdyrKgfws-RGVgfw.orwXL2bOHxgTCxRhhS-5s7X2aKxGUPnYLsfpe0FDGgo&dib_tag=se&keywords=9788859030294&qid=1789503354&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=a5799dce98e7af80ff45383edb70cc80&ref_=as_li_ss_tl"
                    )
            )
    );

    public List<RecommendedReading> findByTestId(String testId) {
        return readingsByTestId.getOrDefault(testId, List.of());
    }
}
