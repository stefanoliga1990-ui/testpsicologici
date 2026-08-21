# Standard psicometrico per i questionari di Spazio Test

## Scopo e livello delle affermazioni

I questionari originali dell'app supportano auto-osservazione e psicoeducazione. Finché non esistono studi sullo specifico strumento, nella specifica popolazione e per lo specifico uso, non vanno descritti come test validati, diagnostici, predittivi o normativi. La validità riguarda le interpretazioni e gli usi dei punteggi, non il questionario in astratto.

Il processo segue come riferimenti generali gli [Standards for Educational and Psychological Testing](https://www.apa.org/science/programs/testing/standards), il [Test Review Model EFPA 2025](https://www.efpa.eu/sites/default/files/2025-08/efpa_test_review_model_v2025_5.pdf), la metodologia [COSMIN sulla validità di contenuto](https://pmc.ncbi.nlm.nih.gov/articles/PMC5891557/) e il processo di sviluppo descritto da [Boateng e colleghi](https://doi.org/10.3389/FPUBH.2018.00149).

## Processo obbligatorio

### 1. Definizione

- Specificare costrutto, confini concettuali, popolazione, contesti e finalità.
- Elencare ciò che il questionario non misura e le principali spiegazioni alternative.
- Verificare se esiste già uno strumento idoneo e chiarire perché serve un contenuto originale.
- Definire dimensioni e indicatori prima di scrivere gli item.

Output: tabella `dimensione → indicatori → item candidati → esclusioni`.

### 1 bis. Selezione e tracciabilità delle fonti

- Preparare una matrice `affermazione o scelta progettuale → fonte → disegno → popolazione → limite` e verificare che ogni fonte sostenga direttamente ciò che viene pubblicato.
- Per la popolazione italiana cercare prima evidenze scientifiche o istituzionali italiane, poi validazioni e studi europei, quindi revisioni, meta-analisi e linee guida internazionali.
- Preferire DOI, PubMed, rivista, università o ente produttore. Divulgazione istituzionale e materiali clinico-educativi possono chiarire il testo, ma non sostituiscono la base scientifica; evitare blog e fonti commerciali.
- Non trasferire norme, cut-off, prevalenze o accuratezza da un altro strumento, Paese o campione. Descrivere campioni specifici e risultati di invarianza, inclusi quelli contrari alla piena equivalenza culturale.
- La validazione italiana di uno strumento di riferimento informa il costrutto e la progettazione, ma non costituisce validazione del questionario originale dell'app.
- Quando l'evidenza italiana è insufficiente, usare le migliori fonti europee o internazionali e registrare esplicitamente la limitazione.

### 2. Sviluppo degli item

- Generare più item di quelli necessari usando letteratura e, quando possibile, interviste con persone della popolazione destinataria.
- Far valutare rilevanza, completezza, comprensibilità e possibili bias a esperti del costrutto e a utenti.
- Usare interviste cognitive per verificare comprensione, recupero dalla memoria, giudizio e scelta della risposta.
- Eliminare item doppi, ridondanti, troppo generici, suggestivi o dipendenti da un solo contesto non essenziale.
- Usare un esempio soltanto quando chiarisce un termine o processo astratto mantenendo invariato l'indicatore. Presentarlo separatamente dalla domanda, mantenerlo breve e non esaustivo e verificare con interviste cognitive che non restringa il recupero agli episodi nominati.
- Non introdurre item invertiti soltanto per contrastare l'acquiescenza: negazioni e inversioni possono creare un fattore di metodo. Se teoricamente necessari, vanno pretestati e codificati esplicitamente.

### 3. Istruzioni e risposte

- Il periodo di riferimento deve essere visibile su ogni pagina e adatto al costrutto.
- La domanda deve chiedere frequenza se le opzioni sono di frequenza, accordo se sono di accordo e intensità se sono di intensità.
- Tutte le categorie devono essere etichettate e ordinate nello stesso verso.
- Per l'attuale scala di frequenza si usa: `Mai=1`, `Raramente=2`, `A volte=3`, `Spesso=4`, `Quasi sempre=5`.
- Prevedere “non applicabile” solo quando è concettualmente legittimo e definire prima la gestione dei dati mancanti.

### 4. Struttura e somministrazione

- Ogni area deve avere copertura sufficiente e comparabile; un numero uguale di item non dimostra da solo la validità di contenuto.
- Gli item delle aree sono intercalati per ridurre dipendenze sequenziali e rendere meno evidente la chiave di lettura. La letteratura mostra che le risposte precedenti possono influenzare quelle successive e alterare le stime di affidabilità e validità ([Shimada et al., 2023](https://pubmed.ncbi.nlm.nih.gov/36085546/)).
- L'ordine delle aree nel risultato resta teorico e stabile. Differenze piccole tra medie ordinali non giustificano una classifica personale.

### 5. Scoring descrittivo prima della validazione

- Gli item hanno peso uguale salvo evidenza empirica replicata per una diversa ponderazione.
- Le medie di item ordinali sono usate soltanto per comporre una restituzione editoriale comprensibile.
- Soglie correnti, allineate ai punti medi tra categorie: `LOW < 2,5`, `MEDIUM 2,5–<3,5`, `HIGH ≥ 3,5`.
- Profilo `LOW`: tutte le aree LOW; `MIXED`: nessuna area HIGH ma almeno un'area MEDIUM; `FOCUSED`: una o due aree HIGH; `BROAD`: almeno tre aree HIGH.
- Le barre sono trasformazioni lineari da 1–5 a 0–100. Non sono percentuali della persona, percentili, probabilità, gravità clinica o confronti normativi.
- Le interpretazioni devono descrivere le risposte, includere contesto e alternative, evitare diagnosi e indicare quando rivolgersi a professionisti o servizi di emergenza.
- Ogni profilo complessivo deve includere una sintesi di almeno due frasi e un approfondimento di almeno tre: distribuzione tra aree, significato possibile, fattori contestuali o alternativi, cosa osservare e limite inferenziale. Le analisi per area restano separate e più specifiche.
- Il titolo di ogni profilo deve nominare esplicitamente il costrutto, le esperienze o le risorse esplorate e rendere riconoscibile `LOW`, `MIXED`, `FOCUSED` o `BROAD` anche se letto isolatamente. Titoli generici come “Un profilo variabile” non sono ammessi. Nei questionari sensibili il titolo descrive ciò che emerge **nelle risposte** e non afferma una diagnosi; nei questionari a direzione positiva descrive risorse percepite, non capacità certificate.

### 6. Validazione necessaria per affermazioni psicometriche

Prima di pubblicare coefficienti o cut-off:

1. preregistrare finalità, ipotesi e piano di analisi;
2. raccogliere un campione adeguato e rappresentativo della popolazione d'uso;
3. studiare distribuzioni, missing, effetti pavimento/soffitto e funzionamento delle categorie;
4. verificare dimensionalità con EFA e CFA su campioni distinti, usando metodi adatti a item ordinali;
5. stimare affidabilità per sottoscala (preferibilmente omega con intervalli) ed errore di misura; alpha da solo non basta;
6. verificare validità convergente, discriminante e rispetto a criteri coerenti con l'uso;
7. analizzare invarianza e funzionamento differenziale degli item nei gruppi rilevanti;
8. stimare stabilità o sensibilità al cambiamento soltanto se coerenti con il costrutto;
9. definire norme o cut-off su campioni indipendenti e documentarne conseguenze, falsi positivi e falsi negativi;
10. replicare e aggiornare versione, manuale e limiti.

## Checklist di revisione del codice

- [ ] Specifica teorica e matrice item-area aggiornate.
- [ ] Fonti e licenze verificabili.
- [ ] Un solo contenuto principale per item.
- [ ] Periodo di riferimento e scala coerenti.
- [ ] Direzione dello scoring uniforme e documentata.
- [ ] Sei item per area nell'architettura corrente; item intercalati.
- [ ] Soglie e interpretazioni testate ai confini.
- [ ] Titoli globali specifici, autonomamente comprensibili e coerenti con la direzione del test.
- [ ] Nessuna classifica delle aree né linguaggio normativo.
- [ ] Disclaimer e messaggi di sicurezza adeguati.
- [ ] Versione incrementata, test automatici verdi e audit aggiornato.
