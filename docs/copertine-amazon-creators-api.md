# Copertine Amazon tramite Creators API

## Scopo

Le schede delle letture consigliate possono mostrare la copertina principale fornita da Amazon per l'ASIN selezionato. La copertina è un elemento facoltativo: titolo, descrizione, limiti editoriali e collegamento affiliato restano disponibili anche se l'API è disabilitata o temporaneamente non risponde.

## Scelte implementative

- Integrazione con Amazon Creators API, successore della Product Advertising API 5.0.
- Autenticazione OAuth 2.0 eseguita esclusivamente dal backend con credenziali conservate nelle variabili d'ambiente.
- Marketplace `www.amazon.it` e Partner Tag `spaziotest-21`.
- Richiesta `GetItems` in gruppi fino a 10 ASIN con le sole risorse `images.primary.large`, `images.primary.medium` e `images.primary.small`.
- Preferenza per l'immagine grande, con ripiego su media e piccola.
- Cache in memoria dell'URL e delle dimensioni per 23 ore; nessun download o salvataggio locale dell'immagine.
- Cache negativa di 15 minuti in caso di errore o copertina assente, per non ripetere una richiesta fallita a ogni visita.
- Token OAuth riutilizzato fino a cinque minuti prima della scadenza dichiarata da Amazon.
- Ogni copertina conduce allo stesso collegamento affiliato verificato della scheda e usa `rel="noopener noreferrer sponsored"`.

## ASIN configurati

| Titolo | ASIN | Edizione verificata |
|---|---|---|
| La differenza invisibile | `8868956004` | ISBN 9788868956004 |
| Esplorare il proprio autismo | `B0BT4WCXFB` | ISBN 9791254910689, edizione cartacea |

## Attivazione

L'integrazione rimane inattiva finché `AMAZON_CREATORS_API_ENABLED` non vale `true`. Quando è attiva, l'avvio dell'applicazione viene interrotto se Credential ID, Secret o Partner Tag sono assenti: in questo modo un errore di configurazione non viene nascosto in produzione.

Le variabili richieste e il relativo ordine di configurazione sono documentati in `docs/railway-deploy.md`.
