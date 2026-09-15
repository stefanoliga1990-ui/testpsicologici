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
            )
    );

    public List<RecommendedReading> findByTestId(String testId) {
        return readingsByTestId.getOrDefault(testId, List.of());
    }
}
