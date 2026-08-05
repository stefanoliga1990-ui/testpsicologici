# Tratti autistici nell'adulto — specifica editoriale v2.0

## Stato e finalità

- Pubblico: adulti.
- Finalità: autovalutazione esclusivamente informativa.
- Stato: contenuto originale non validato clinicamente e non revisionato da un professionista.
- Il risultato non conferma, esclude o stima la probabilità di una diagnosi.

## Fonti consultate

- CDC, *Clinical Testing and Diagnosis for Autism Spectrum Disorder*: criteri relativi a comunicazione e interazione sociale, comportamenti ripetitivi, interessi e sensibilità sensoriale.
  https://www.cdc.gov/autism/hcp/diagnosis/index.html
- NICE CG142, *Autism spectrum disorder in adults: diagnosis and management*: valutazione complessiva, storia dello sviluppo, funzionamento, diagnosi differenziali e sensibilità sensoriali.
  https://www.nice.org.uk/guidance/cg142/chapter/Recommendations
- NHS, *Signs of autism in adults*: comunicazione implicita, routine, interessi, sensibilità sensoriale e masking nell'adulto.
  https://www.nhs.uk/conditions/autism/signs-in-adults/

Le domande non riproducono questionari clinici esistenti: sono formulazioni originali ricavate dai temi generali descritti dalle fonti.

## Aree interne

1. Interazione sociale e reciprocità emotiva.
2. Comunicazione non verbale e comprensione implicita.
3. Routine, flessibilità e comportamenti ripetitivi.
4. Interessi focalizzati e sensibilità sensoriale.

Ogni area contiene sei domande. Nel risultato l'utente vede il nome dell'area, il testo interpretativo e una barra di presenza normalizzata; non vede valori numerici, livelli o soglie.

## Regole di restituzione

Ogni risposta vale da 1 (`Mai`) a 5 (`Sempre`). Il motore calcola una media generale e una media per area.

- `LOW`: media generale inferiore a 2,4 e nessuna area alta.
- `MIXED`: media generale almeno 2,4, senza aree alte.
- `FOCUSED`: una o due aree con media almeno 3,75.
- `BROAD`: almeno tre aree con media almeno 3,75, oppure media generale almeno 4.

Le soglie sono regole editoriali per comporre una restituzione leggibile; non sono cut-off clinici. Il risultato comprende un testo generale e quattro schede specifiche, ordinate dall'area più presente alla meno presente. La barra usa la formula `(media area - 1) / 4 × 100`: tutte risposte `Mai` corrispondono a una barra vuota, tutte `Sempre` a una barra completa.
