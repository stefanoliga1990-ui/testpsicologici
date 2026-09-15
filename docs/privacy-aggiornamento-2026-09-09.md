# Aggiornamento dei dati del gestore e della privacy

Dati confermati dal gestore il 9 settembre 2026: Stefano Liga, privato; contatto pubblico stefano.liga1990@gmail.com; hosting Railway; contributi volontari reali tramite Stripe. Aggiornate le pagine React e Thymeleaf del progetto e della privacy. Nessuna modifica ai questionari o allo scoring.

Il controller dei test conserva TestAttempt nella HttpSession del server. Il testo precedente «soltanto nella sessione tecnica del browser» era inesatto. Il nuovo testo descrive scadenza o invalidazione della sessione senza promettere una durata non verificata in produzione. L'assenza di IP e identificatori è riferita al conteggio statistico, non all'intera infrastruttura. Sono descritti Railway, Google Fonts, contatto e diritti.

Questo intervento aggiorna i fatti verificati e non certifica la completezza legale dell'informativa. Prima di dichiarare chiuso l'audit privacy restano da verificare:

- regione e configurazione effettiva Railway, log, backup e durata delle sessioni in produzione;
- tempi o criteri di conservazione dei dati di pagamento e delle richieste email, con relativa gestione pratica;
- basi giuridiche per finalità e condizione applicabile ai dati eventualmente relativi alla salute (articolo 9 GDPR): non dichiarare un consenso esplicito che il flusso non raccoglie;
- ruoli contrattuali dei fornitori, destinatari e garanzie applicabili ai trasferimenti, senza dedurli dalla sola informativa generale del fornitore.

Fonti consultate il 9 settembre 2026:

- [Garante: principi del trattamento e informativa](https://www.garanteprivacy.it/home/principi-fondamentali-del-trattamento): trasparenza, basi giuridiche, categorie particolari e diritti; non prova di conformità del sito.
- [Railway: privacy](https://railway.com/legal/privacy): informativa del fornitore; non dimostra regione e impostazioni del progetto.
- [Stripe: privacy](https://stripe.com/it/privacy): trattamento del fornitore dei pagamenti; non determina gli obblighi fiscali del gestore privato.

La precedente variabile APP_PRIVACY_CONTACT_EMAIL non è più usata nel valore della proprietà applicativa. Eventuali override diretti Spring della proprietà restano da controllare al deploy. Le modifiche locali non equivalgono alla pubblicazione su Railway.

Verifica: build frontend Vite riuscita; suite Maven con settings.xml e Java 17: 243 test, zero fallimenti o errori (BUILD SUCCESS). Verificati 11 blocchi privacy identici tra React e fallback HTML; git diff --check superato. Log locale in target/privacy-verifica-maven.log.
