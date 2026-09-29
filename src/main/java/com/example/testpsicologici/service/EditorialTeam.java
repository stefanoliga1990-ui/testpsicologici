package com.example.testpsicologici.service;

import com.example.testpsicologici.model.EditorialAuthor;
import com.example.testpsicologici.model.EditorialReviewer;

public final class EditorialTeam {

    public static final EditorialAuthor GUIDE_AUTHOR = new EditorialAuthor(
            "Stefano Liga",
            "Responsabile editoriale di Spazio Test, appassionato di psicologia. Non è uno psicologo.");

    public static final EditorialReviewer PROFESSIONAL_REVIEWER = new EditorialReviewer(
            "Alessia Liga",
            "Psicologa · Specializzanda in Psicoterapia presso la Scuola di Psicologia della Salute.",
            "Psicologa con un percorso che unisce formazione scientifica, ricerca psicosociale ed esperienza diretta sul campo. Laureata magistrale in Psicologia sociale, del lavoro e delle organizzazioni presso l'Università degli Studi di Palermo con 110/110 e lode, ha conseguito un Master di II livello in Gestione e Sviluppo delle Risorse Umane e una formazione specialistica in autismo e neurodiversità.\n\n"
                    + "Ha lavorato in ambito psicoeducativo e sociale, occupandosi di inclusione, sostegno a persone e famiglie con bisogni speciali, prevenzione del disagio, promozione del benessere e progettazione di ricerche-intervento. È autrice di contributi scientifici e di divulgazione psicologica.\n\n"
                    + "È Docente di Psicologia di Comunità presso l'Università degli Studi \"Guglielmo Marconi\" e Cultrice della Materia in Psicologia Sociale presso l'Università degli Studi Niccolò Cusano.\n\n"
                    + "Attualmente conduce gruppi di supporto e psicoeducazione per la gestione di conflitti, ansia e stress, e percorsi di sostegno alla genitorialità orientati a valorizzare risorse e punti di forza. In qualità di consulente, progetta interventi di promozione della salute e programmi di inclusione e sviluppo giovanile, integrando i modelli delle Life Skills e del Service-Learning.",
            "Alessia.liga3@gmail.com",
            "+39 392 240 7494",
            "+393922407494",
            "https://www.linkedin.com/in/alessia-liga-a057b985",
            "https://alessialigapsicologa.com/");

    private EditorialTeam() {
    }
}
