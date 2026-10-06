package com.example.testpsicologici.service;

import com.example.testpsicologici.model.TopicCluster;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TopicClusterCatalogue {

    private static final List<TopicCluster> CLUSTERS = List.of(
            new TopicCluster(
                    "ansia-umore-e-trauma",
                    "Ansia, umore e trauma",
                    "Esperienze legate a preoccupazione, umore, pensieri ricorrenti e reazioni a eventi difficili.",
                    List.of(
                            "ansia-generalizzata",
                            "ansia-sociale",
                            "tratti-ossessivo-compulsivi",
                            "umore-depresso",
                            "ruminazione-mentale",
                            "bisogno-controllo",
                            "dismorfofobia",
                            "ptsd-adulti"
                    )),
            new TopicCluster(
                    "relazioni-e-attaccamento",
                    "Relazioni e attaccamento",
                    "Modi di vivere vicinanza, autonomia, fiducia, confini e sicurezza nelle relazioni.",
                    List.of(
                            "stili-attaccamento",
                            "disponibilita-emotiva",
                            "parentificazione",
                            "paura-abbandono",
                            "limerenza",
                            "dipendenza-affettiva",
                            "compatibilita-coppia",
                            "gelosia-partner",
                            "dinamiche-narcisistiche-partner",
                            "situationship",
                            "codipendenza-relazionale"
                    )),
            new TopicCluster(
                    "personalita-e-tratti",
                    "Personalità e tratti",
                    "Pattern di esperienza e relazione da osservare nel tempo e nei contesti, senza trasformarli in diagnosi online.",
                    List.of(
                            "introversione-estroversione",
                            "tratti-borderline-adulti",
                            "tratti-evitanti-personalita-adulti"
                    )),
            new TopicCluster(
                    "ambiguita-e-manipolazione-relazionale",
                    "Ambiguità e manipolazione relazionale",
                    "Dinamiche di comunicazione, attenzione e controllo che possono generare ambiguità, pressione o perdita di autonomia.",
                    List.of(
                            "relazione-dannosa-benessere",
                            "gaslighting",
                            "love-bombing",
                            "breadcrumbing",
                            "orbiting",
                            "hoovering",
                            "invalidazione-emotiva-subita",
                            "triangolazione-subita"
                    )),
            new TopicCluster(
                    "autostima-approvazione-e-obiettivi",
                    "Autostima, approvazione e obiettivi",
                    "Rapporto con il proprio valore, aspettative, giudizio altrui e ostacoli nel perseguire obiettivi.",
                    List.of(
                            "autostima",
                            "sindrome-impostore",
                            "perfezionismo",
                            "people-pleasing",
                            "autosabotaggio",
                            "fomo"
                    )),
            new TopicCluster(
                    "emozioni-risorse-e-benessere",
                    "Emozioni, risorse e benessere",
                    "Consapevolezza emotiva, comunicazione, adattamento e percezione del proprio benessere.",
                    List.of(
                            "autocompassione",
                            "intelligenza-emotiva",
                            "alessitimia",
                            "intelligenza-intrapersonale",
                            "assertivita",
                            "resilienza-psicologica",
                            "soddisfazione-vita",
                            "empatia-percepita"
                    )),
            new TopicCluster(
                    "lavoro-studio-e-stress",
                    "Lavoro, studio e stress",
                    "Esperienze legate alle richieste lavorative e al recupero, senza diagnosi online.",
                    List.of("burnout-percepito")),
            new TopicCluster(
                    "neurosviluppo-attenzione-e-linguaggio",
                    "Neurosviluppo, attenzione e linguaggio",
                    "Caratteristiche legate ad attenzione, comunicazione, flessibilità e uso del linguaggio.",
                    List.of(
                            "tratti-adhd-adulti",
                            "tratti-autistici-adulti",
                            "intelligenza-linguistica"
                    ))
    );

    public List<TopicCluster> findAll() {
        return CLUSTERS;
    }

    public Optional<TopicCluster> findByTestId(String testId) {
        return CLUSTERS.stream()
                .filter(cluster -> cluster.testIds().contains(testId))
                .findFirst();
    }

    public List<String> findRelatedTestIds(String testId, int maximum) {
        if (maximum <= 0) {
            return List.of();
        }
        if ("burnout-percepito".equals(testId)) {
            return List.of("resilienza-psicologica", "umore-depresso", "soddisfazione-vita")
                    .stream().limit(maximum).toList();
        }
        if ("autocompassione".equals(testId)) {
            return List.of("resilienza-psicologica", "intelligenza-intrapersonale", "empatia-percepita")
                    .stream().limit(maximum).toList();
        }
        if ("ruminazione-mentale".equals(testId)) {
            return List.of("umore-depresso", "ansia-generalizzata", "tratti-ossessivo-compulsivi")
                    .stream().limit(maximum).toList();
        }
        if ("bisogno-controllo".equals(testId)) {
            return List.of("ansia-generalizzata", "tratti-ossessivo-compulsivi", "ruminazione-mentale")
                    .stream().limit(maximum).toList();
        }
        if ("dismorfofobia".equals(testId)) {
            return List.of("tratti-ossessivo-compulsivi", "ansia-sociale", "umore-depresso")
                    .stream().limit(maximum).toList();
        }
        return findByTestId(testId)
                .map(cluster -> nearestNeighbours(cluster.testIds(), testId, maximum))
                .orElseGet(List::of);
    }

    private List<String> nearestNeighbours(List<String> ids, String testId, int maximum) {
        int currentIndex = ids.indexOf(testId);
        List<String> related = new ArrayList<>();
        for (int distance = 1; related.size() < Math.min(maximum, ids.size() - 1); distance++) {
            addIfNew(related, ids.get(Math.floorMod(currentIndex + distance, ids.size())), testId);
            if (related.size() < maximum) {
                addIfNew(related, ids.get(Math.floorMod(currentIndex - distance, ids.size())), testId);
            }
        }
        return List.copyOf(related);
    }

    private void addIfNew(List<String> related, String candidate, String testId) {
        if (!candidate.equals(testId) && !related.contains(candidate)) {
            related.add(candidate);
        }
    }
}
