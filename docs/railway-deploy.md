# Deploy su Railway

## Architettura scelta

- Applicazione Spring Boot impacchettata come JAR ed eseguita in un'immagine Java 17.
- Frontend React compilato da Vite durante la build Maven/Docker e incluso nello stesso JAR; gli asset sono serviti direttamente da Spring Boot.
- Database H2 in modalità file.
- Un solo servizio e una sola replica.
- Volume Railway montato a `/data` per rendere persistente il file H2.
- Dati editoriali inizializzati dal `ContentDataInitializer`: un database vuoto riceve automaticamente gli stessi test e le stesse analisi presenti nel codice.

Il file locale `data/testpsicologici.mv.db` non deve essere pubblicato su GitHub né copiato nel container. Il codice di inizializzazione è la fonte riproducibile dei contenuti; il volume conserva poi il database tra riavvii e nuovi deploy.

Non serve un servizio Node in produzione: Node viene installato soltanto nello stage di build del `Dockerfile`. Routing pubblico, dominio Railway, cookie di sessione ed endpoint Spring restano sullo stesso origin.

## Configurazione Railway

1. Creare un progetto con **Deploy from GitHub repo** e selezionare questo repository.
2. Collegare un volume al servizio e impostare il mount path `/data`.
3. Non impostare manualmente `PORT`: Railway lo fornisce e Spring Boot lo legge automaticamente.
4. Non è necessario impostare `APP_DATABASE_URL`: collegando il volume, Railway fornisce `RAILWAY_VOLUME_MOUNT_PATH` e l'applicazione salva H2 in `/data/testpsicologici`.
5. Attendere che build, avvio e healthcheck `/health` risultino completati.
6. Aprire **Settings → Networking → Public Networking** e scegliere **Generate Domain**.

## Variabili opzionali

- `APP_DATABASE_URL`: permette di sostituire completamente l'URL JDBC predefinito.
- `APP_DATABASE_USERNAME`: valore predefinito `sa`.
- `APP_DATABASE_PASSWORD`: vuota per impostazione predefinita.

Per questa prima versione non è necessario definire nessuna delle variabili opzionali.

## Variabili per monitoraggio visite

Prima di usare la dashboard configurare in **Variables** del servizio Railway:

- `MONITORING_USERNAME`: nome utente scelto per `/monitoring`;
- `MONITORING_PASSWORD`: password lunga e univoca per la dashboard;
- `VISITOR_COOKIE_SECRET`: segreto casuale stabile usato per firmare il cookie giornaliero;
- `APP_PRIVACY_CONTACT_EMAIL`: indirizzo mostrato nella pagina `/privacy-e-cookie`.

Per generare una chiave casuale da PowerShell:

```powershell
[Convert]::ToBase64String([Security.Cryptography.RandomNumberGenerator]::GetBytes(32))
```

Railway tratta le variabili come segreti del servizio e avvia automaticamente un nuovo deploy dopo la modifica. Non inserire questi valori in `application.properties`, nei log o nel repository.

Se `MONITORING_USERNAME` o `MONITORING_PASSWORD` mancano, l'area riservata non è accessibile. Se manca `VISITOR_COOKIE_SECRET`, il conteggio funziona con una chiave temporanea ma può ricontare alcuni browser dopo un riavvio; in produzione va quindi sempre configurata.

La dashboard è disponibile su `https://spaziotest.me/monitoring` e aggiorna il valore odierno ogni 15 secondi. Le visite alla dashboard e alla pagina di login non entrano nelle statistiche. Dettagli tecnici e limiti sono documentati in [`docs/monitoraggio-visite.md`](monitoraggio-visite.md).

## Limiti della soluzione H2

H2 su volume è adatto a questa applicazione leggera e senza scritture concorrenti degli utenti. Il servizio deve restare a una sola replica. Se in futuro verranno aggiunti account, salvataggio dei risultati o più repliche, sarà opportuno migrare a PostgreSQL.
