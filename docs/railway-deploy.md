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

## Limiti della soluzione H2

H2 su volume è adatto a questa applicazione leggera e senza scritture concorrenti degli utenti. Il servizio deve restare a una sola replica. Se in futuro verranno aggiunti account, salvataggio dei risultati o più repliche, sarà opportuno migrare a PostgreSQL.
