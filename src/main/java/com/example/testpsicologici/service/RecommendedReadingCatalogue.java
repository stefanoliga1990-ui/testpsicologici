package com.example.testpsicologici.service;

import com.example.testpsicologici.model.RecommendedReading;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RecommendedReadingCatalogue {

    private final Map<String, List<RecommendedReading>> readingsByTestId = Map.ofEntries(
            Map.entry("tratti-autistici-adulti", List.of(
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
            )),
            Map.entry("tratti-adhd-adulti", List.of(
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
            )),
            Map.entry("tratti-ossessivo-compulsivi", List.of(
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
            )),
            Map.entry("autostima", List.of(
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
            )),
            Map.entry("dipendenza-affettiva", List.of(
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
            )),
            Map.entry("assertivita", List.of(
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
            )),
            Map.entry("intelligenza-emotiva", List.of(
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
            )),
            Map.entry("perfezionismo", List.of(
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
            )),
            Map.entry("dinamiche-narcisistiche-partner", List.of(
                    new RecommendedReading(
                            "E questo sarebbe amore?",
                            "Sonja R. e Bärbel Wardetzki",
                            "Una testimonianza accompagnata dall'analisi di una psicoterapeuta, che ripercorre l'escalation di una relazione descritta come narcisistica e le sue conseguenze su forza e autostima.",
                            "Racconta un'esperienza specifica e usa una propria cornice interpretativa: non consente di etichettare un partner, stabilire se una relazione sia abusante o decidere cosa fare in una situazione di pericolo.",
                            "https://www.amazon.it/-/en/questo-sarebbe-amore-B%C3%A4rbel-Wardetzki/dp/8807091593?dib=eyJ2IjoiMSJ9.OY1gjsUwuVo8_uQC8ygi_A.2I9Xgzb_ZI29PXqx4rR96myZoEM45y4TlegYoIlaJUw&dib_tag=se&keywords=9788807091599&qid=1789593986&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=53952ab1c4586f32e4ca127b59da7ee2&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "I mille volti di Narciso. Fragilità e arroganza tra normalità e patologia",
                            "Fabio Madeddu",
                            "Un saggio ampio sul narcisismo, dalle teorie storiche al dibattito contemporaneo, con capitoli dedicati anche a innamoramento, coppia e orientamenti terapeutici.",
                            "È un testo teorico e clinico, con linguaggio anche tecnico: non permette di diagnosticare una persona, interpretare il risultato del questionario o valutare la sicurezza di una relazione.",
                            "https://www.amazon.it/-/en/Narciso-Fragilit%C3%A0-arroganza-normalit%C3%A0-patologia/dp/8832851520?dib=eyJ2IjoiMSJ9.HTIwoU4452J4cCSggZSZmA.I1IoanmGTjxAKeV0DfR99Az2Pa7UX4iA4IH7tzwUz8Y&dib_tag=se&keywords=9788832851526&qid=1789594078&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=93a73b038262154ff11361eb1d951570&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("ansia-generalizzata", List.of(
                    new RecommendedReading(
                            "Quaderno di esercizi per vincere l'ansia generalizzata",
                            "Daniele Piacentini e Daniela Leveni",
                            "Un quaderno di auto-aiuto in undici step con tecniche, strategie e attività dedicate a preoccupazioni ricorrenti, rimuginio, comportamenti disfunzionali e problemi quotidiani.",
                            "Gli esercizi sono materiali di auto-aiuto: non stabiliscono la presenza di un disturbo d'ansia generalizzata, non permettono di interpretare il risultato del questionario e non sostituiscono una valutazione professionale.",
                            "https://www.amazon.it/-/en/Quaderno-esercizi-vincere-lansia-generalizzata/dp/885904118X?dib=eyJ2IjoiMSJ9.8x86z45iKeD_rCr0rtmnVg.I3GFdaZ-DPr5b4xZLRUq1TNQtjQ1tJkiuhz8D--DbEM&dib_tag=se&keywords=9788859041184&qid=1789604773&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=c7f0234e07163b227944fa37506f0343&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Rimuginio. Teoria e terapia del pensiero ripetitivo",
                            "Gabriele Caselli, Giovanni M. Ruggiero e Sandra Sassaroli",
                            "Un saggio che ricapitola ricerca e modelli clinici sul pensiero negativo ripetitivo, sulle credenze metacognitive e sui comportamenti che possono mantenere il rimuginio.",
                            "È un testo teorico e clinico, in parte rivolto a chi lavora nella salute mentale: non consente di diagnosticare un disturbo, valutare una terapia o dedurre il significato delle risposte al questionario.",
                            "https://www.amazon.it/-/en/Rimuginio-Teoria-terapia-pensiero-ripetitivo/dp/8860309026?dib=eyJ2IjoiMSJ9.PHEpTm6OnHEohhblIoMy-A.9flKnlBdTorKbTqfwU-TbMbTtym_olrBk8winVwD8_A&dib_tag=se&keywords=9788860309020&qid=1789604805&sr=8-1&linkCode=ll2&tag=spaziotest-21&linkId=28fb3d1d1eb7e5b3fb2230cada97dba0&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("umore-depresso", List.of(
                    new RecommendedReading(
                            "Quaderno di esercizi per vincere la depressione",
                            "Daniele Piacentini, Daniela Leveni e Paolo Michielin",
                            "Un quaderno in undici step con informazioni ed esercizi su apatia, pensieri controproducenti, ruminazione, senso di colpa, rete sociale e monitoraggio dei cambiamenti.",
                            "Gli esercizi sono materiali di auto-aiuto: non stabiliscono la presenza di depressione, non consentono di interpretare il risultato del questionario e non sostituiscono una valutazione professionale o un intervento in caso di urgenza.",
                            "https://www.amazon.it/-/en/Quaderno-esercizi-vincere-depressione-Piacentini/dp/8859033527?dib=eyJ2IjoiMSJ9.Ffy0Hde5jMTFOCYZyOe10rJm_6SV0vzn_N1KP30RyIbb9Dxuma_Vdot0ujUfHugixyP3EPU3uQ780NbEJiGav5_B88Yj9jbpywNWZAUyX-vdrW5xn-giy3ZOSUXFLnIAiuDoWFs7SQiXN6k43JTRFS7noxnwt6La5NZvRf4dBawK9qufIw1BVg3VmjK6XCrQh6xPYFi9YtdEMtrqAcJF19U48q0UWsk6kn5saGI2ljg.OBZLAzBeJaKvFmmfsA-FGh5QF7g0nijjbInCemFFW2w&dib_tag=se&keywords=Quaderno+di+esercizi+per+vincere+la+depressione&qid=1789626427&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=63f104df5e7db46226a375d6e2c4996d&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Come sconfiggere la depressione",
                            "Robert L. Leahy",
                            "Un manuale di auto-aiuto che propone riflessioni ed esercizi su pensieri negativi, autocritica, solitudine, paura del fallimento e abitudini quotidiane.",
                            "Il titolo non implica un risultato garantito: il libro non formula diagnosi, non permette di dedurre il significato delle risposte al questionario e non sostituisce supporto professionale o interventi urgenti.",
                            "https://www.amazon.it/Come-sconfiggere-depressione-percorso-autoaiuto/dp/8860304962?__mk_it_IT=%C3%85M%C3%85%C5%BD%C3%95%C3%91&crid=2LZIWVS4SG5F5&dib=eyJ2IjoiMSJ9.iN7K_fjQzNkTsJEUOJ_5xw.AGsiU1LL7groL6Ary6M0lTTg_CFqyu_xP8fhi3P9gXg&dib_tag=se&keywords=9788860304964&qid=1789629863&sprefix=9788860304964%2Caps%2C240&sr=8-1&ufe=app_do%3Aamzn1.fos.8a1562af-dabe-4f1d-8eb5-1ded1ace4ef7&linkCode=ll2&tag=spaziotest-21&linkId=b8f57efb48a06541b223eb319b4716c1&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("people-pleasing", List.of(
                    new RecommendedReading(
                            "Quaderno di esercizi per sviluppare l'assertività",
                            "Federico Betti, Gabriele Costanzo e Simona Carniato",
                            "Un quaderno operativo con una parte introduttiva e attività in sette passaggi su stili di comunicazione, ostacoli, critiche e modi di esprimere i propri bisogni.",
                            "Gli esercizi offrono spunti di auto-osservazione e non misurano quanto una persona compiace gli altri né sostituiscono un supporto professionale quando le difficoltà relazionali causano sofferenza persistente.",
                            "https://www.amazon.it/dp/8859044650?&linkCode=ll2&tag=spaziotest-21&linkId=778b304dde4d5be4b130a097415516ca&ref_=as_li_ss_tl",
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
            )),
            Map.entry("paura-abbandono", List.of(
                    new RecommendedReading(
                            "Quaderno di esercizi per vincere la dipendenza affettiva",
                            "Antonella Lebruto, Giulia Calamai e Laura Caccico",
                            "Un quaderno operativo in 13 step che propone attività di auto-aiuto su dipendenza affettiva, pensieri, emozioni, evitamenti, bisogni e comportamenti nella relazione.",
                            "Il volume affronta la dipendenza affettiva, che non coincide con ogni paura dell'abbandono. Gli esercizi non interpretano il risultato del questionario, non definiscono la causa delle difficoltà individuali e non sostituiscono un supporto professionale.",
                            "https://www.amazon.it/-/en/Quaderno-esercizi-vincere-dipendenza-affettiva/dp/8859043735?dib=eyJ2IjoiMSJ9.irk_ZgbHxtWYpQlHEMJBtg.UBzXjHdrTLyO_2uEuX-sTiftnv6OK_3jaee0S836Ke0&dib_tag=se&keywords=9788859043737&qid=1790834471&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=105ac250eceb8b1a9bc64d6de34c76f2&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Costruzione e rottura dei legami affettivi",
                            "John Bowlby",
                            "Una selezione di lezioni e relazioni di John Bowlby che introduce i principi della teoria dell'attaccamento e il ruolo dei legami familiari nello sviluppo.",
                            "È un testo teorico e storico sullo sviluppo e sull'attaccamento, non una guida per valutare le proprie relazioni. Non permette di collegare automaticamente le esperienze infantili alle difficoltà attuali né di interpretare il risultato del questionario.",
                            "https://www.amazon.it/-/en/Costruzione-rottura-dei-legami-affettivi/dp/8832857928?dib=eyJ2IjoiMSJ9.InKuP-7GNobphDdSO0qYJQ.ET9F_vegG-bY1KmkGJSd-U016RzbGV-0Lr9q7N_NLWk&dib_tag=se&keywords=9788832857924&qid=1790834549&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=fb61acc7e308cfaed7022426c73fe760&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("intelligenza-linguistica", List.of(
                    new RecommendedReading(
                            "Formae mentis",
                            "Howard Gardner",
                            "Il testo di Howard Gardner presenta il modello delle intelligenze multiple, compresa l'intelligenza linguistica, e discute componenti, sviluppo e implicazioni educative.",
                            "È un saggio teorico sul modello delle intelligenze multiple, non un test personale. Non permette di misurare l'intelligenza linguistica, convalidare il questionario o dedurre capacità certificate dalle risposte.",
                            "https://www.amazon.it/-/en/Formae-mentis-Gardner/dp/8807882590?&linkCode=ll2&tag=spaziotest-21&linkId=88d2c865a87803234e80732e81ed7981&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Il cervello sintattico",
                            "Marco Tettamanti",
                            "Un'introduzione ai meccanismi sintattici del linguaggio, con riferimenti a psicolinguistica, neurolinguistica, acquisizione e funzionamento del cervello sintattico.",
                            "Approfondisce un ambito specifico del linguaggio e non misura la competenza linguistica nel suo insieme. Non interpreta le risposte al questionario e non consente di dedurre profili cognitivi o condizioni neurologiche individuali.",
                            "https://www.amazon.it/-/en/cervello-sintattico-Marco-Tettamanti/dp/8843096524?&linkCode=ll2&tag=spaziotest-21&linkId=6af8958af114aca19e783a6e376309a2&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("intelligenza-intrapersonale", List.of(
                    new RecommendedReading(
                            "Conoscere se stessi",
                            "Stephen M. Fleming",
                            "Un saggio divulgativo sulla metacognizione e sui modi in cui le persone valutano pensieri, decisioni e conoscenza di sé.",
                            "Tratta l'autoconsapevolezza e la metacognizione, non misura l'intelligenza intrapersonale e non interpreta le risposte al questionario. Non certifica accuratezza nel conoscere se stessi né sostituisce una valutazione professionale.",
                            "https://www.amazon.it/-/en/Conoscere-stessi-nuova-scienza-dellautoconsapevolezza/dp/8832854244?dib=eyJ2IjoiMSJ9.HUFrmIF3dCcJlbUdUjMJtw.zmPVBRAYM4Hvu4Sr8BDat1FmjmNV3ozj79EX6bkrPs0&dib_tag=se&keywords=9788832854244&qid=1790867502&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=e30aea70d285f9a2774b9f321c66af4e&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "L'identità personale",
                            "Massimo Marraffa e Cristina Meini",
                            "Un saggio di psicologia teorica e filosofia della mente su autocoscienza psicologica, identità narrativa e ruolo delle relazioni nella conoscenza di sé.",
                            "È una trattazione teorica sull'identità personale e sull'autocoscienza. Non propone un test dell'intelligenza intrapersonale, non traduce il risultato del questionario e non fornisce una lettura clinica individuale.",
                            "https://www.amazon.it/-/en/Lidentit%C3%A0-personale-Massimo-Marraffa/dp/884308268X?dib=eyJ2IjoiMSJ9.BPoc_UgYc5s099EHnTwwtg.TnwOrdvwXp8Y60S-Rupk-8t2-oQ3O9dru926hb6eocE&dib_tag=se&keywords=9788843082681&qid=1790867539&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=3456f6f5b3adbc62af06cac0094bf517&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("resilienza-psicologica", List.of(
                    new RecommendedReading(
                            "Resilienza e vulnerabilità psicologica nel corso dello sviluppo",
                            "Cristiano Inguglia e Alida Lo Coco",
                            "Un saggio accademico sulla resilienza e sulla vulnerabilità nel corso dello sviluppo, con attenzione a fattori di rischio e protezione, valutazione e adattamento psicosociale.",
                            "Si concentra sul corso dello sviluppo e sui contesti di vulnerabilità; non misura la resilienza della singola persona e non interpreta le risposte al questionario. Non permette di dedurre come una persona reagirà a un evento difficile.",
                            "https://www.amazon.it/-/en/Resilienza-vulnerabilit%C3%A0-psicologica-corso-sviluppo/dp/8815246010?dib=eyJ2IjoiMSJ9.ETfISa9uHT7RdlvY_UxYsw.JJ6CcwsMQ7PTpWPP6_vnhxhs4QLqYJfod212KA3gvj4&dib_tag=se&keywords=9788815246011&qid=1790877660&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=c94fae30ef4d613adca5a735b9e197c5&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Valutare la resilienza",
                            "Francisco Javier Fiz Pérez, Andrea Laudadio e Lavinia Mazzocchetti",
                            "Un volume che presenta il costrutto della resilienza, i principali modelli teorici e alcuni strumenti derivati da adattamenti italiani della letteratura internazionale.",
                            "È un testo tecnico su teorie e strumenti di valutazione, non una valutazione individuale. Gli strumenti descritti non convalidano il questionario dell’app e non permettono di tradurre il suo risultato in una misura clinica o predittiva.",
                            "https://www.amazon.it/-/en/Valutare-resilienza-Teorie-modelli-strumenti/dp/8843057499?dib=eyJ2IjoiMSJ9.0S3atonXxGGUflf51eSxEg.gj2_8O8lXYYXRzSvNZ_gQeL3lAIO8wMO8kcbUgokiKQ&dib_tag=se&keywords=9788843057498&qid=1790877750&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=7fc5c1988e0989afc8d3468e20760834&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("gelosia-partner", List.of(
                    new RecommendedReading(
                            "Psicologia della gelosia e dell'invidia",
                            "Valentina D'Urso",
                            "Un saggio di psicologia delle emozioni che affronta gelosia e invidia, includendo la gelosia amorosa, il triangolo relazionale, i contesti culturali e alcune strategie per fronteggiarne le conseguenze.",
                            "Tratta la gelosia in più contesti, non valuta una relazione specifica e non interpreta le risposte al questionario. Non consente di stabilire se la gelosia sia giustificata, problematica o causata da un singolo fattore.",
                            "https://www.amazon.it/-/en/Psicologia-della-gelosia-dellinvidia-Valentina/dp/8843069527?dib=eyJ2IjoiMSJ9.7ZeKAu3DFvEYdiI5H5lgTQ.jDzckXcuE0UXLyBnU2GndCYsC_x4ACQQC5ch-KQ_ZXs&dib_tag=se&keywords=9788843069521&qid=1790888457&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=2405d01038819ffa12e283ec1e53651a&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Relazioni d'amore",
                            "Otto F. Kernberg",
                            "Un saggio teorico sulle relazioni di coppia, la passione, la sessualità e le interazioni emotive tra partner, letto attraverso la teoria delle relazioni oggettuali.",
                            "È un testo teorico di orientamento psicoanalitico sulle relazioni amorose. Non misura la gelosia, non determina la qualità o la sicurezza di una relazione e non sostituisce un supporto professionale individuale o di coppia.",
                            "https://www.amazon.it/-/en/Relazioni-damore-Normalit%C3%A0-patologia-Kernberg/dp/8870783723?dib=eyJ2IjoiMSJ9.NWTRKD6LsqM-gsEVNzY2aw.ISItZ-tR3rAUcbz-AngomcmV7bJ1dvF9XiGfKGB-F30&dib_tag=se&keywords=9788870783728&qid=1790888517&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=73ec9f0d753e1a0927ad2e2d18f1f225&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("fomo", List.of(
                    new RecommendedReading(
                            "Fomo Sapiens",
                            "Patrick J. McGinnis",
                            "Un saggio divulgativo che presenta i concetti di FOMO e FOBO e invita a riflettere su scelte, alternative percepite e social network.",
                            "È una prospettiva divulgativa e di auto-riflessione, non un testo clinico: non stabilisce se una persona abbia un uso problematico dei social e non interpreta le risposte al questionario.",
                            "https://www.amazon.it/-/en/Sapiens-Impara-decidere-travolgere-possibili/dp/8817154695?dib=eyJ2IjoiMSJ9.rtu8DsRBVM9XHiKxMc9bJA.FLu9bzeh6bIzmw2yN87DPAw3PsO7zAouLahWRHJ-z1E&dib_tag=se&keywords=9788817154697&qid=1790845243&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=eecd9fe20e80aca3ab96b9851cb8da7c&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Minimalismo digitale",
                            "Cal Newport",
                            "Un saggio divulgativo sul ripensare il rapporto con tecnologie, dispositivi e distrazioni, attraverso la proposta del minimalismo digitale.",
                            "Non è un libro specifico sulla FOMO e propone una cornice di auto-riflessione: non determina un uso problematico della tecnologia, non interpreta il risultato del questionario e non sostituisce un supporto professionale.",
                            "https://www.amazon.it/-/en/Minimalismo-digitale-Rimettere-propria-distrazioni/dp/8836200680?&linkCode=ll2&tag=spaziotest-21&linkId=f06843b0b7977dd06ed76bb163e8855c&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("tratti-borderline-adulti", List.of(
                    new RecommendedReading(
                            "Superare il Disturbo Borderline di Personalità",
                            "Valerie Porr",
                            "Una guida rivolta soprattutto a familiari e persone vicine, che presenta il disturbo borderline di personalità, i trattamenti psicosociali e alcune strategie di comunicazione e coping.",
                            "È una guida per familiari e clinici, non uno strumento per riconoscere o confermare una diagnosi. Le strategie proposte non sostituiscono una valutazione, un piano di cura o indicazioni professionali individuali.",
                            "https://www.amazon.it/-/en/Superare-disturbo-borderline-personalit%C3%A0-familiari/dp/8859023246?dib=eyJ2IjoiMSJ9.2_fvHdqHEytQ7F-WJAbBZQ.-gwtzzRMNbMD2IjD4rOEWNVzYBdA1D99_dng3vFZ4aE&dib_tag=se&keywords=9788859023241&qid=1790823600&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=8b4fb798187b5fe3a851b6abd4fbd470&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Una vita degna di essere vissuta",
                            "Marsha M. Linehan",
                            "Un'autobiografia in cui Marsha Linehan racconta il proprio percorso personale e professionale e la nascita della terapia dialettico-comportamentale (DBT).",
                            "È un racconto autobiografico, non un manuale di auto-aiuto né una guida per autovalutarsi. La storia dell'autrice non permette di interpretare il risultato del questionario o di indicare un trattamento personale.",
                            "https://www.amazon.it/-/en/Una-vita-degna-essere-vissuta/dp/8832852748?dib=eyJ2IjoiMSJ9.58RRoUggnUMpJxfah6HfbQ._E8-rPynjV8eQVxdr5fWj5Qo7Xq1HsLxElm1Zu7h-WA&dib_tag=se&keywords=9788832852745&qid=1790823665&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=e6b2b432de6199eae87c980f490f60ce&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("autosabotaggio", List.of(
                    new RecommendedReading(
                            "Basta autosabotaggio!",
                            "Judy Ho",
                            "Un manuale divulgativo che propone un percorso in sei passaggi per riconoscere abitudini e pensieri controproducenti e riflettere sui propri obiettivi.",
                            "Le attività proposte sono materiali di auto-aiuto: non interpretano il risultato del questionario, non stabiliscono le cause individuali dell'autosabotaggio e non sostituiscono una valutazione professionale.",
                            "https://www.amazon.it/dp/885902420X?&linkCode=ll2&tag=spaziotest-21&linkId=4090807f63b50972cba6f1d7f91858a1&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Il piccolo sabotatore dentro di noi",
                            "Michaela Muthig",
                            "Una guida divulgativa che usa la metafora del sabotatore interiore per esplorare auto-ostacoli, autocritica e difficoltà a portare avanti progetti importanti.",
                            "La metafora del sabotatore è una cornice divulgativa e non identifica una parte della personalità, una diagnosi o una spiegazione certa delle difficoltà individuali.",
                            "https://www.amazon.it/-/en/piccolo-sabotatore-dentro-noi/dp/8807895331?dib=eyJ2IjoiMSJ9.4n1pMd7_ez6MaPXuVyO6Tg.3gRgcc7HhdINU9-ClJ5LvK_UvJ8zRw7fvFgFUc-jI_0&dib_tag=se&keywords=9788807895333&qid=1790812819&s=books&sr=1-1&linkCode=ll2&tag=spaziotest-21&linkId=8af3aafcf014e4d161c94fa6105a8649&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("sindrome-impostore", List.of(
                    new RecommendedReading(
                            "La sindrome dell'impostore",
                            "Sandi Mann",
                            "Una guida divulgativa che descrive il fenomeno dell'impostore, l'attribuzione dei risultati alla fortuna e il timore di essere smascherati, con spunti pratici di riflessione.",
                            "Il volume propone una cornice divulgativa e non consente di interpretare il risultato del questionario, formulare una diagnosi o dedurre le cause individuali di un senso di inadeguatezza.",
                            "https://www.amazon.it/dp/8807091496?&linkCode=ll2&tag=spaziotest-21&linkId=89257714000222a43ca77fb6a004d832&ref_=as_li_ss_tl",
                            true
                    ),
                    new RecommendedReading(
                            "Pensavo di essere io...",
                            "Florencia Di Stefano-Abichain",
                            "Un libro che intreccia l'esperienza personale dell'autrice e una proposta divulgativa sulla paura di essere smascherati, l'inadeguatezza e l'autostima.",
                            "Parte da un vissuto personale e da una proposta divulgativa: non permette di interpretare il risultato del questionario né sostituisce una valutazione o un supporto professionale.",
                            "https://www.amazon.it/dp/8850266235?&linkCode=ll2&tag=spaziotest-21&linkId=d00c6c6024dd48c6d63ef4f70b384411&ref_=as_li_ss_tl",
                            true
                    )
            )),
            Map.entry("ansia-sociale", List.of(
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
            ))
    );

    public List<RecommendedReading> findByTestId(String testId) {
        return readingsByTestId.getOrDefault(testId, List.of());
    }
}
