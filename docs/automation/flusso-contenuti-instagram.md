# Dalla coda dei contenuti ai caroselli Instagram

Verifica della configurazione locale eseguita il 5 settembre 2026.

## Coda applicativa e task Test

La fonte operativa è `docs/automation/coda-contenuti.json`, letta esplicitamente dall'automazione attiva `contenuto-psicologico-giornaliero`, collegata al task `Test` (`019fbc72-25e2-7483-9abe-4556c127f466`). La pianificazione effettiva è venerdì e sabato alle 22:15, fuso Europe/Rome. I metadati della coda sono stati allineati a questo calendario; il trigger effettivo resta la configurazione dell'automazione.

Il ciclo seleziona il primo `PENDING` per `order`. Le proposte sono soggette a ricerca e possono diventare `BLOCKED`; il ciclo prosegue fino al primo contenuto completato o a un impedimento tecnico. Una proposta in coda non equivale a un questionario disponibile.

Su richiesta dell'utente sono state aggiunte 21 voci `TEST_AND_GUIDE`: il tema del disturbo evitante di personalità (DEP), con titolo informativo sui tratti associati nell'adulto, è il primo `PENDING` (ordine 7); le venti proposte approvate sono in fondo alla coda (ordini 41–60), nell'ordine della proposta. L'ordine relativo e i metadati di lavorazione delle 39 voci precedenti sono conservati. La coda contiene ora 60 voci: 34 questionari da realizzare, 20 guide da realizzare, 3 contenuti completati e 3 bloccati. Nessun questionario è implementato da questa modifica.

## Catalogo pubblicato e task Instagram

L'automazione attiva `prepara-post-carosello-instagram-quotidiano`, denominata `Prepara post carosello Instagram lunedì e mercoledì`, è collegata al task `Instagram` (`01a02670-9e87-7bd3-9ae9-1dfdbe8335fa`). Parte lunedì e mercoledì alle 22:15 e prepara il carosello per il giorno successivo alle 14:00, fuso Europe/Rome.

Il prompt e `marketing/instagram/automation/POST_CAROUSEL_WORKFLOW.md` prevedono che ogni ciclo:

1. sincronizzi i contenuti realmente pubblicati usando `src/main/java/com/example/testpsicologici/config/ContentDataInitializer.java` e `src/main/java/com/example/testpsicologici/service/GuideCatalogue.java`;
2. inserisca i nuovi contenuti come `PENDING` nel registro separato `marketing/instagram/automation/post-carousel-state.json`;
3. distingua guide con test (`TEST_AND_GUIDE`) e guide senza test (`GUIDE_ONLY`);
4. scelga il primo `PENDING` del registro Instagram, salvo un contenuto già `AWAITING_APPROVAL`;
5. prepari sei slide e richieda conferma esplicita prima della programmazione finale in Meta Business Suite.

Il collegamento è quindi: **coda applicativa → realizzazione e pubblicazione nell'app → sincronizzazione nel registro Instagram → selezione del carosello → conferma e programmazione**. La sincronizzazione è un passaggio prescritto all'agente dal workflow, non un collegamento automatico fra i due JSON. L'automazione Instagram non deve leggere o avanzare la coda dei contenuti ancora da realizzare.

Non occorre aggiungere anticipatamente i 21 temi al registro Instagram né cambiare il prompt delle automazioni. I contenuti completati diventeranno candidati alla successiva sincronizzazione; la data del relativo post dipenderà dalla coda Instagram e dalla conferma finale. Un tema bloccato o non pubblicato non diventa automaticamente un carosello.

## Riscontro e limiti della verifica

Sono stati letti i prompt e i calendari salvati delle automazioni, il workflow dei caroselli, il validatore del registro e il registro stesso. Al momento della verifica il registro Instagram contiene 34 voci; invalidazione emotiva e triangolazione, già presenti nel catalogo applicativo, non vi sono ancora registrate. La sincronizzazione prevista al prossimo ciclo deve includere anche questi contenuti. Non è stata eseguita una preparazione o pubblicazione di prova.

Il precedente processo Reel è sospeso. Alcuni testi locali conservano vecchi riferimenti alle 23:00 e al processo applicativo delle 16:00; il validatore Instagram controlla ancora il metadato storico `dailyPreparationTime: 23:00`. Il calendario attivo del carosello è invece lunedì e mercoledì alle 22:15, come indicato nel prompt e nel trigger salvato. Questi file di marketing non sono stati modificati durante la verifica.

La verifica riguarda il percorso delle fonti e la configurazione, non garantisce l'esecuzione futura: disponibilità del computer, accesso a Meta, esito della ricerca e approvazione finale restano necessari.
