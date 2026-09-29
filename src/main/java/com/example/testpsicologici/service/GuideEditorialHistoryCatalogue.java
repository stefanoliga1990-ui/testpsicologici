package com.example.testpsicologici.service;

import com.example.testpsicologici.model.GuideEditorialHistory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

import static java.util.Map.entry;

@Service
public class GuideEditorialHistoryCatalogue {

    private static final Map<String, GuideEditorialHistory> HISTORY = Map.ofEntries(
            entry("autismo-adulti", history("2026-08-13", "2026-08-20")),
            entry("adhd-adulti", history("2026-08-13", "2026-08-20")),
            entry("disturbo-ossessivo-compulsivo", history("2026-08-13", "2026-08-20")),
            entry("autostima", history("2026-08-13", "2026-08-20")),
            entry("dipendenza-affettiva", history("2026-08-13", "2026-08-20")),
            entry("assertivita", history("2026-08-14", "2026-08-20")),
            entry("intelligenza-emotiva", history("2026-08-14", "2026-08-20")),
            entry("perfezionismo", history("2026-08-14", "2026-08-20")),
            entry("ansia-sociale", history("2026-08-14", "2026-08-20")),
            entry("dinamiche-narcisistiche-coppia", history("2026-08-14", "2026-08-20")),
            entry("ansia-generalizzata", history("2026-08-14", "2026-08-20")),
            entry("umore-depresso", history("2026-08-14", "2026-08-20")),
            entry("people-pleasing", history("2026-08-14", "2026-08-20")),
            entry("sindrome-impostore", history("2026-08-14", "2026-08-20")),
            entry("autosabotaggio", history("2026-08-14", "2026-08-20")),
            entry("disturbo-borderline-personalita", history("2026-08-20", "2026-08-20")),
            entry("paura-abbandono", history("2026-08-20", "2026-08-20")),
            entry("fomo", history("2026-08-20", "2026-08-20")),
            entry("intelligenza-linguistica", history("2026-08-20", "2026-08-21")),
            entry("intelligenza-intrapersonale", history("2026-08-21", "2026-08-21")),
            entry("resilienza-psicologica", history("2026-08-21", "2026-08-24")),
            entry("gelosia-partner", history("2026-08-24", "2026-08-25")),
            entry("soddisfazione-vita", history("2026-08-25", "2026-08-29")),
            entry("ptsd-adulti", history("2026-08-25", "2026-08-25")),
            entry("stili-attaccamento", history("2026-08-25", "2026-08-26")),
            entry("limerenza", history("2026-08-26", "2026-08-27")),
            entry("parentificazione", history("2026-08-27", "2026-08-28")),
            entry("gaslighting", history("2026-08-28", "2026-08-28")),
            entry("love-bombing", history("2026-08-28", "2026-08-28")),
            entry("breadcrumbing", history("2026-08-28", "2026-08-28")),
            entry("orbiting", history("2026-08-28", "2026-08-28")),
            entry("hoovering", history("2026-08-28", "2026-08-30")),
            entry("compatibilita-coppia", history("2026-08-30", "2026-08-30")),
            entry("relazione-dannosa-benessere", history("2026-08-30", "2026-09-02")),
            entry("invalidazione-emotiva", history("2026-09-02", "2026-09-05")),
            entry("triangolazione-relazionale", history("2026-09-04", "2026-09-04")),
            entry("disturbo-evitante-personalita", history("2026-09-05", "2026-09-07")),
            entry("disponibilita-emotiva", history("2026-09-07", "2026-09-08")),
            entry("alessitimia", history("2026-09-08", "2026-09-15")),
            entry("situationship", history("2026-09-15", "2026-09-15")),
            entry("codipendenza-relazionale", history("2026-09-17", "2026-09-17"))
    );

    public GuideEditorialHistory forSlug(String slug) {
        GuideEditorialHistory history = HISTORY.get(slug);
        if (history == null) {
            throw new IllegalArgumentException("Date editoriali mancanti per la guida: " + slug);
        }
        return history;
    }

    public Map<String, GuideEditorialHistory> findAll() {
        return HISTORY;
    }

    private static GuideEditorialHistory history(String publishedOn, String revisedOn) {
        return new GuideEditorialHistory(LocalDate.parse(publishedOn), LocalDate.parse(revisedOn));
    }
}
