package com.example.testpsicologici.service;

import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.Map.entry;

@Component
public class ReferenceContributionCatalogue {

    private static final String DEFAULT_CONTRIBUTION =
            "Fonte consultata per definire i temi esplorati e mantenere un linguaggio informativo e prudente.";

    private static final Map<String, String> CONTRIBUTIONS = Map.ofEntries(
            entry("https://www.cdc.gov/autism/hcp/diagnosis/index.html",
                    "Riferimento per comunicazione e interazione sociale, comportamenti ripetitivi, interessi, sensibilità sensoriale e necessità di considerare la storia dello sviluppo."),
            entry("https://www.nice.org.uk/guidance/cg142/chapter/Recommendations",
                    "Riferimento per la valutazione complessiva nell'adulto, il funzionamento nei diversi contesti, la storia dello sviluppo e le possibili spiegazioni alternative."),
            entry("https://www.nice.org.uk/guidance/ng87/chapter/recommendations",
                    "Riferimento per disattenzione, iperattività e impulsività, esordio nell'infanzia, impatto in più contesti e necessità di una valutazione specialistica."),
            entry("https://www.nhs.uk/conditions/adhd-adults/",
                    "Riferimento informativo per attenzione, gestione del tempo, organizzazione, irrequietezza, impazienza e impulsività nell'adulto."),
            entry("https://www.nhs.uk/mental-health/conditions/obsessive-compulsive-disorder-ocd/symptoms/",
                    "Riferimento per distinguere pensieri ossessivi e compulsioni, descrivere il loro ciclo e considerare disagio e interferenza nella vita quotidiana."),
            entry("https://www.nimh.nih.gov/health/publications/obsessive-compulsive-disorder-when-unwanted-thoughts-take-over",
                    "Riferimento per pensieri indesiderati, rituali ripetitivi, tempo occupato e impatto sul funzionamento, senza equiparare singole esperienze a una diagnosi."),
            entry("https://socy.umd.edu/about-us/rosenberg-self-esteem-scale",
                    "Riferimento per il costrutto di autostima globale e per la distinzione tra valutazioni positive e negative di sé."),
            entry("https://doi.org/10.1177/0963721414547414",
                    "Riferimento per considerare l'autostima relativamente stabile ma modificabile nel corso della vita."),
            entry("https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_3313_allegato.pdf",
                    "Documento del Consiglio Superiore di Sanità consultato per il quadro delle dipendenze comportamentali e della cosiddetta love addiction."),
            entry("https://pmc.ncbi.nlm.nih.gov/articles/PMC12284683/",
                    "Riferimento per il rapporto tra attaccamento insicuro, ansia di separazione e comportamenti relazionali problematici, tenendo conto dell'eterogeneità del costrutto."),
            entry("https://doi.org/10.1016/S0005-7894(73)80120-0",
                    "Riferimento storico per la misurazione comportamentale e situazionale dell'assertività."),
            entry("https://doi.org/10.1016/S0191-8869(98)00252-9",
                    "Riferimento per la struttura multidimensionale dell'assertività: espressione, limiti, iniziativa e comportamenti assertivi positivi."),
            entry("https://sites.usnh.edu/jdmayer/wp-content/uploads/sites/261/2024/03/rp2016-mayer-caruso-salovey.pdf",
                    "Riferimento per le aree di percezione, facilitazione del pensiero, comprensione e gestione delle emozioni."),
            entry("https://cdn2.psychologytoday.com/assets/attachments/1575/rp2008-mayersaloveycarusob.pdf",
                    "Riferimento per distinguere il modello di abilità dalle concezioni più ampie dell'intelligenza emotiva basate su tratti e competenze."),
            entry("https://doi.org/10.1007/BF01172967",
                    "Riferimento per preoccupazione per gli errori, standard personali, dubbi sulle azioni, aspettative percepite e organizzazione."),
            entry("https://pubmed.ncbi.nlm.nih.gov/2027080/",
                    "Riferimento per distinguere perfezionismo orientato verso di sé, verso gli altri e socialmente prescritto."),
            entry("https://www.nimh.nih.gov/health/publications/social-anxiety-disorder-more-than-just-shyness",
                    "Riferimento per paura di essere osservati o giudicati, manifestazioni nelle interazioni e nelle prestazioni, evitamento e impatto sul funzionamento."),
            entry("https://www.nice.org.uk/guidance/cg159/ifp/chapter/assessment-and-diagnosis-for-adults",
                    "Riferimento per preoccupazioni sociali, paura dell'imbarazzo o dell'attenzione, evitamento e interferenza nella vita quotidiana."),
            entry("https://www.merckmanuals.com/professional/psychiatric-disorders/personality-disorders/narcissistic-personality-disorder-npd",
                    "Riferimento per il carattere pervasivo del disturbo, grandiosità, bisogno di ammirazione, empatia e necessità di una valutazione basata su criteri clinici."),
            entry("https://www.psychiatry.org/News-room/APA-Blogs/What-Is-Narcissistic-Personality-Disorder",
                    "Riferimento istituzionale per distinguere caratteristiche narcisistiche occasionali da un disturbo di personalità pervasivo."),
            entry("https://www.nimh.nih.gov/health/publications/generalized-anxiety-disorder-gad",
                    "Riferimento per distinguere ansia occasionale e preoccupazione persistente e difficile da controllare, insieme ai sintomi fisici e cognitivi associati."),
            entry("https://www.nice.org.uk/guidance/cg113/chapter/Appendix-Assessing-generalised-anxiety-disorder",
                    "Riferimento per preoccupazione in più ambiti, difficoltà di controllo, durata, sintomi associati e funzionamento quotidiano."),
            entry("https://www.who.int/news-room/fact-sheets/detail/depression",
                    "Riferimento per umore depresso o perdita di interesse, durata, sintomi cognitivi e fisici, funzionamento, trattabilità e sicurezza."),
            entry("https://www.nimh.nih.gov/health/publications/depression",
                    "Riferimento per eterogeneità dei sintomi, piacere, energia, colpa, concentrazione, sonno, appetito, funzionamento e possibili cause alternative."),
            entry("https://pubmed.ncbi.nlm.nih.gov/9686454/",
                    "Riferimento per distinguere la disposizione positiva alla cura dalla focalizzazione sugli altri associata a dipendenza valutativa e trascuratezza di sé."),
            entry("https://pubmed.ncbi.nlm.nih.gov/15647153/",
                    "Riferimento teorico per la cura degli altri a esclusione del sé e per il possibile legame con il disagio psicologico, senza considerare problematica la cura in quanto tale."),
            entry("https://doi.org/10.1037/h0086006",
                    "Lavoro originario sul fenomeno dell'impostore, la difficoltà a interiorizzare i successi e l'attribuzione dei risultati a cause esterne."),
            entry("https://pmc.ncbi.nlm.nih.gov/articles/PMC6463809/",
                    "Revisione delle definizioni e delle proprietà psicometriche delle principali misure, utile per mantenere distinta questa esperienza da una condizione diagnosticabile."),
            entry("https://pubmed.ncbi.nlm.nih.gov/3043527/",
                    "Riferimento per distinguere condotte deliberatamente autodistruttive, compromessi con costi prevedibili e strategie controproducenti, mantenendo una lettura non moralistica."),
            entry("https://pubmed.ncbi.nlm.nih.gov/17201571/",
                    "Meta-analisi di riferimento per procrastinazione, autoregolazione, avversione al compito, impulsività, tempi e divario tra intenzione e azione."));

    public String findByUrl(String url) {
        return CONTRIBUTIONS.getOrDefault(url, DEFAULT_CONTRIBUTION);
    }
}
