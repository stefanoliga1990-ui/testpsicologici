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
            ),
            "autostima", List.of(
                    new RecommendedReading(
                            "L'autostima si impara",
                            "Umberto Longoni",
                            "Un manuale pratico che propone esercizi e spunti di riflessione sul rapporto con se stessi, sull'immagine personale e sulla fiducia nelle proprie possibilità.",
                            "Gli esercizi possono offrire spunti personali, ma non misurano l'autostima né sostituiscono un confronto professionale quando la sofferenza è persistente o interferisce con la vita quotidiana.",
                            "https://www.amazon.it/-/en/Lautostima-impara-Esercizi-aumentare-fiducia/dp/8891790311?dib=eyJ2IjoiMSJ9.elWJmHYcVpcXWtYi6v-s8g.nP6d9qDk7bjI7MNQ4dasXwF4NbH4icIZliDd5LuCbTE&dib_tag=se&keywords=9788891790316&qid=1789518041&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=95df3009f0ae3e1518c7623a920b7980&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Mi vado bene?",
                            "Michele Giannantonio",
                            "Un testo di auto-aiuto su autostima e assertività, con esempi e attività dedicate anche a pensieri, critiche e relazioni quotidiane.",
                            "Propone un percorso divulgativo e non permette di dedurre il significato delle singole risposte al questionario né sostituisce un percorso psicoterapeutico.",
                            "https://www.amazon.it/-/en/dp/886137557X?&linkCode=ll2&tag=spaziotest-21&linkId=21827a0d23f8c1408df3538cd6d735eb&ref_=as_li_ss_tl",
                            true
                    )
            ),
            "dipendenza-affettiva", List.of(
                    new RecommendedReading(
                            "Quaderno di esercizi per vincere la dipendenza affettiva",
                            "Antonella Lebruto, Giulia Calamai e Laura Caccico",
                            "Un quaderno operativo che presenta il tema e propone attività strutturate su pensieri, emozioni, evitamenti, bisogni e relazioni.",
                            "È un testo di auto-aiuto: non stabilisce se una persona abbia una dipendenza affettiva e non sostituisce il supporto professionale, soprattutto in presenza di violenza, coercizione o pericolo.",
                            "https://www.amazon.it/-/en/dp/8859043735?&linkCode=ll2&tag=spaziotest-21&linkId=81dd7705e0f71ceb891150be89cccfee&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Dipendenza affettiva",
                            "Antonella Lebruto, Giulia Calamai, Laura Caccico e Valentina Ciorciari",
                            "Un manuale tecnico sul tema, con inquadramento, assessment, trattamento cognitivo-comportamentale e casi clinici.",
                            "È rivolto soprattutto a psicologi e psicoterapeuti; non va usato per interpretare autonomamente il questionario o per formulare una diagnosi.",
                            "https://www.amazon.it/-/en/dp/8859029759?&linkCode=ll2&tag=spaziotest-21&linkId=970a512c8ea1eccc9c7be79465413f8e&ref_=as_li_ss_tl",
                            true
                    )
            ),
            "assertivita", List.of(
                    new RecommendedReading(
                            "Quaderno di esercizi per sviluppare l'assertività",
                            "Federico Betti, Gabriele Costanzo e Simona Carniato",
                            "Un quaderno operativo con una parte introduttiva e attività in sette passaggi su stili di comunicazione, ostacoli, critiche e modi di esprimere i propri bisogni.",
                            "Gli esercizi offrono spunti di auto-osservazione e non misurano l'assertività né sostituiscono un supporto professionale quando le difficoltà relazionali causano sofferenza persistente.",
                            "https://www.amazon.it/-/en/dp/8859044650?&linkCode=ll2&tag=spaziotest-21&linkId=2b9a0fe7df327e34878f89547baa2ab2&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Training di comunicazione assertiva",
                            "Ezio Sanavio e Francesco Sanavio",
                            "Un manuale tecnico con inquadramento teorico e strumenti di osservazione sulla comunicazione assertiva e le relazioni interpersonali.",
                            "È rivolto soprattutto a professionisti e lettori che cercano un approfondimento tecnico; non permette di interpretare il risultato del questionario né di ricavare una valutazione personale.",
                            "https://www.amazon.it/-/en/dp/8859030277?&linkCode=ll2&tag=spaziotest-21&linkId=0a7cc530e5de01ba9b22c4bc22c46e30&ref_=as_li_ss_tl",
                            true
                    )
            ),
            "intelligenza-emotiva", List.of(
                    new RecommendedReading(
                            "Intelligenza emotiva",
                            "Daniel Goleman",
                            "Un saggio divulgativo che mette in relazione emozioni, autocontrollo, empatia e relazioni, ripercorrendo studi e applicazioni nella vita quotidiana, nella scuola e nel lavoro.",
                            "Propone un modello divulgativo, discusso e distinto da altri modelli di intelligenza emotiva; non misura le competenze della persona né permette di interpretare il risultato del questionario.",
                            "https://www.amazon.it/-/en/Intelligenza-emotiva-Daniel-Goleman/dp/8817050164?dib=eyJ2IjoiMSJ9.Cfpwf1tuyGA1wyvCqfstpQ.hDIcI2PVsgBHP9rN-l5WgNiBgnsoIVkUjm1_hrvcR8A&dib_tag=se&keywords=9788817050166&qid=1789550518&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=bda3b4587e8b8cb716b475fbbac6256f&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "L'intelligenza emotiva di tratto",
                            "Giacomo Mancini",
                            "Un manuale sul costrutto dell'intelligenza emotiva di tratto, sui modelli teorici, la misurazione, le evidenze empiriche e le applicazioni nei contesti socioeducativi e psicologici.",
                            "È un testo tecnico su uno specifico modello di intelligenza emotiva: non consente di interpretare il risultato del questionario né di certificare abilità individuali.",
                            "https://www.amazon.it/-/en/Lintelligenza-Prospettive-applicazioni-socioeducative-psicologiche/dp/8859044642?dib=eyJ2IjoiMSJ9.fKVM_VaEe2njomF8NZdysw.jwCuC3u7GxOnq2x6kkxycPu9gB7xg2O49_XNjW1SaR4&dib_tag=se&keywords=9788859044642&qid=1789550599&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=2f4ce82681af9284a72b5c4b6618200a&ref_=as_li_ss_tl",
                            true
                    )
            ),
            "perfezionismo", List.of(
                    new RecommendedReading(
                            "Nessuno è perfetto. Strategie per superare il perfezionismo. Nuova ediz.",
                            "Martin M. Antony e Richard P. Swinson",
                            "Un volume di auto-aiuto che descrive fattori cognitivi e comportamentali collegati al perfezionismo e propone esercizi per osservare standard rigidi, autocritica e abitudini di controllo.",
                            "Le proposte del volume sono spunti di auto-osservazione e pratica; non consentono di interpretare il risultato del questionario, formulare una diagnosi o sostituire una valutazione professionale.",
                            "https://www.amazon.it/dp/8859017513?&linkCode=ll2&tag=spaziotest-21&linkId=80d9fb435c5df5d9498a1ce3ac867f4d&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "La ricerca della perfezione. Smetti di inseguire il perfezionismo",
                            "Tal Ben-Shahar",
                            "Un saggio divulgativo che riflette sulle aspettative di perfezione, sul rapporto con fallimento e successo e propone esercizi di riflessione personale.",
                            "È una proposta divulgativa di auto-riflessione: non permette di interpretare il risultato del questionario, formulare una diagnosi o sostituire una valutazione professionale.",
                            "https://www.amazon.it/-/en/ricerca-perfezione-Smetti-inseguire-perfezionismo/dp/8809975499?dib=eyJ2IjoiMSJ9.pxGjWoQ4AxRjOPhRGsJDqw.Pnh4vICfh2k-iwkdZE9Rbx_xHJmD8Xe7iOJRzfgqIyE&dib_tag=se&keywords=9788809975491&qid=1789572363&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=9c446c9391f22ab143a2015e7eb94e38&ref_=as_li_ss_tl",
                            true
                    )
            ),
            "ansia-sociale", List.of(
                    new RecommendedReading(
                            "Stop all'ansia sociale. Strategie per affrontare e gestire la timidezza",
                            "Nicola Marsigli",
                            "Un manuale di auto-aiuto basato sulla terapia cognitivo-comportamentale, con strategie ed esercizi su ansia anticipatoria, valutazione post-evento, pensieri e graduale esposizione alle situazioni sociali.",
                            "È una risorsa editoriale di auto-aiuto: non interpreta il risultato del questionario, non formula una diagnosi e non sostituisce un percorso con un professionista.",
                            "https://www.amazon.it/-/en/allansia-sociale-Strategie-affrontare-timidezza/dp/8859016339?dib=eyJ2IjoiMSJ9.JKTYAqEHzOcxkdSH2jJXyQ.JLCrSqfn71eh-Ztbx8hreFDqgslXUp-tV5NsTrgS1J4&dib_tag=se&keywords=9788859016335&qid=1789583144&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=60976a536df3aea65e7b043352cff207&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Quaderno di esercizi per vincere l'ansia sociale",
                            "Duccio Baroni, Laura Caccico, Serena Ciandri e altri autori",
                            "Un quaderno operativo di auto-aiuto con dieci step, esercizi e attività per esplorare paura del giudizio, pensieri e comportamenti di evitamento nelle situazioni sociali.",
                            "Gli esercizi sono materiali di auto-aiuto e non sono una valutazione individuale: non permettono di interpretare il risultato del questionario, formulare una diagnosi o sostituire un supporto professionale.",
                            "https://www.amazon.it/-/en/Quaderno-esercizi-vincere-lansia-sociale/dp/8859025877?dib=eyJ2IjoiMSJ9.szV2dvrpHaRj-aDX8xH4Rw.qdbTHnEk_1DVb1sQ8fO65rQqmyoSPdpJKfl_Br4Wznw&dib_tag=se&keywords=9788859025870&qid=1789583183&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=6c67db138cb6455a94d1573dbe54043e&ref_=as_li_ss_tl",
                            true
                    )
            )
    );

    public List<RecommendedReading> findByTestId(String testId) {
        return readingsByTestId.getOrDefault(testId, List.of());
    }
}
