# Monitoraggio aggregato delle visite

## Obiettivo e definizione della metrica

La dashboard misura i **browser distinti giornalieri** che hanno eseguito il frontend di almeno una pagina pubblica di Spazio Test. Il conteggio non identifica persone fisiche: browser, profili o dispositivi differenti vengono conteggiati separatamente, mentre più pagine o più accessi dallo stesso browser nella stessa giornata valgono una sola volta.

La giornata è calcolata nel fuso `Europe/Rome`. Il valore odierno indica il totale accumulato dalla mezzanotte fino all'ultimo aggiornamento, non il numero di persone contemporaneamente online.

## Dati trattati

Il database contiene soltanto la tabella `daily_site_visit`:

| Campo | Contenuto |
| --- | --- |
| `visit_date` | data locale della visita, chiave primaria |
| `visitor_count` | totale aggregato dei browser conteggiati quel giorno |

Non vengono salvati IP, User-Agent, URL, referrer, argomento consultato, risposte ai questionari, ID di sessione o identificatori del visitatore. Lo User-Agent viene soltanto letto durante la richiesta per escludere i crawler più comuni e non viene registrato.

## Cookie giornaliero

`__Host-st_visit_day` è un cookie first-party, `Secure`, `HttpOnly`, `SameSite=Lax` e con `Path=/`. Contiene versione, data e firma HMAC; a parità di data il valore è uguale per tutti i browser. Scade alla successiva mezzanotte italiana.

La chiave HMAC è fornita da `VISITOR_COOKIE_SECRET`. Se manca, l'applicazione genera una chiave temporanea e continua a funzionare, ma dopo un riavvio i cookie già emessi non sono più riconoscibili e alcuni browser possono essere ricontati. In produzione la variabile è quindi obbligatoria.

## Flusso applicativo

1. React invia `POST /internal/visita` dopo il caricamento di una pagina pubblica, senza body e senza referrer.
2. Il server ignora crawler riconosciuti e browser che presentano già il cookie valido del giorno.
3. Per un nuovo browser incrementa atomicamente la riga della data e restituisce il cookie giornaliero.
4. Errori del monitoraggio vengono ignorati dal frontend e non impediscono la navigazione.

Healthcheck, asset, sitemap, PDF, login e dashboard non generano l'evento. La dashboard non espone pagine visitate perché queste informazioni non vengono raccolte.

## Dashboard e sicurezza

La dashboard è disponibile su `/monitoring`, con login su `/monitoring/login`. Spring Security protegge esclusivamente `/monitoring/**`; le pagine pubbliche e i POST dei questionari restano accessibili senza autenticazione.

Le credenziali sono lette da `MONITORING_USERNAME` e `MONITORING_PASSWORD`. Se una delle due manca, viene creato un account casuale non conoscibile e la dashboard resta di fatto disabilitata. La password configurata viene trasformata in BCrypt all'avvio e non è salvata nel repository o nel database.

Le risposte della dashboard usano `Cache-Control: no-store` e `X-Robots-Tag: noindex, nofollow, noarchive`. Il grafico interroga l'API protetta ogni 15 secondi e permette intervalli di 7, 30, 90 e 365 giorni.

## Informativa

La pagina pubblica `/privacy-e-cookie`, collegata dal footer, descrive sessione tecnica e conteggio aggregato. Il recapito mostrato è configurato con `APP_PRIVACY_CONTACT_EMAIL`.

La configurazione è stata progettata secondo l'indicazione del Garante per cui gli analytics gestiti direttamente dal titolare possono essere assimilati ai cookie tecnici se utilizzati per statistiche aggregate relative a un singolo sito. L'informativa resta necessaria anche quando non è richiesto il consenso: <https://www.garanteprivacy.it/faq/cookie>.

## Limiti

- cancellazione o blocco del cookie può produrre più conteggi nello stesso giorno;
- più dispositivi o browser della stessa persona vengono conteggiati separatamente;
- persone diverse nello stesso profilo browser vengono conteggiate insieme;
- due prime richieste realmente simultanee da schede diverse possono raramente produrre un doppio conteggio;
- il filtro crawler riduce, ma non elimina, tutto il traffico automatizzato.

In caso di passaggio a più repliche, il database deve essere migrato a PostgreSQL come già previsto dalla documentazione di deploy. L'incremento è protetto anche dalla chiave primaria, ma H2 su volume rimane una scelta per servizio a replica singola.
