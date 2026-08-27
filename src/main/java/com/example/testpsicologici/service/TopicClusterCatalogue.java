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
                            "ptsd-adulti"
                    )),
            new TopicCluster(
                    "relazioni-e-attaccamento",
                    "Relazioni e attaccamento",
                    "Modi di vivere vicinanza, autonomia, fiducia, confini e sicurezza nelle relazioni.",
                    List.of(
                            "stili-attaccamento",
                            "parentificazione",
                            "paura-abbandono",
                            "limerenza",
                            "dipendenza-affettiva",
                            "gelosia-partner",
                            "dinamiche-narcisistiche-partner",
                            "tratti-borderline-adulti"
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
                            "intelligenza-emotiva",
                            "intelligenza-intrapersonale",
                            "assertivita",
                            "resilienza-psicologica",
                            "soddisfazione-vita"
                    )),
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
