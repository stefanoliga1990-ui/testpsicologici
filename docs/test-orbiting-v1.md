# Orbiting — specifica psicometrica v1.0

## Identità e uso previsto

| Campo | Definizione |
|---|---|
| Identificativo | `orbiting` |
| Titolo pubblico | Ho subito orbiting? |
| Versione | `1.0` |
| Popolazione | adulti che compilano autonomamente il questionario in italiano |
| Finalità | auto-osservazione informativa riferita alla conclusione o interruzione di una relazione o frequentazione romantica specifica |
| Periodo di riferimento | primi sei mesi dopo l'interruzione del contatto diretto, oppure l'intero periodo se più breve |
| Unità di riferimento | una sola persona e una sola relazione o frequentazione per tutta la compilazione |
| Somministrazione | 12 item originali, due aree da sei item, ordine intercalato |
| Scala | `Mai=1`, `Raramente=2`, `A volte=3`, `Spesso=4`, `Quasi sempre=5` |
| Stato | questionario originale, non validato e non normato |

## Costrutto e confini

In questa versione l'**orbiting** è trattato come un'etichetta emergente per una configurazione post-relazionale riferita composta da due elementi congiunti: la comunicazione diretta viene interrotta o lasciata senza risposta e, nello stesso periodo, la persona rimane visibilmente presente attraverso visualizzazioni o interazioni periferiche sui social network. Gli studi italiani di Pancani e colleghi hanno distinto l'orbiting dal ghosting proprio per questa persistente presenza digitale, ma la letteratura empirica è ancora molto limitata e non offre una scala validata per chi riceve tali comportamenti.

Il questionario rileva soltanto esperienze che chi compila poteva osservare. Non presume che continuare a seguire un profilo, visualizzare una storia, mettere un “Mi piace”, non rispondere o terminare un rapporto siano di per sé orbiting. L'intenzione, la consapevolezza e il motivo dell'altra persona non sono osservabili e non entrano nello scoring.

Il questionario non misura e non può stabilire:

- che sia avvenuto certamente orbiting, ghosting, manipolazione, abuso, cyberstalking o controllo;
- intenzioni, interesse residuo, desiderio di tornare, volontà di sorvegliare, colpa, personalità, attaccamento o diagnosi dell'altra persona;
- chi abbia terminato il rapporto o se la conclusione fosse concordata, salvo quanto ricordato da chi compila;
- la causa di ansia, confusione, speranza, rabbia o altri vissuti;
- prevalenza, gravità, rischio, probabilità, percentile o punteggio clinico;
- la sicurezza complessiva della situazione o l'assenza di episodi importanti quando il risultato è basso.

Spiegazioni alternative o concorrenti comprendono una separazione già chiarita, accordi espliciti sul mantenere un contatto online, amicizia residua, reti sociali condivise, uso abituale o distratto delle piattaforme, contenuti suggeriti automaticamente, impostazioni di privacy e visibilità, oppure una diversa interpretazione della conclusione del rapporto. Queste alternative non rendono irrilevanti contatti indesiderati, paura, minacce o sorveglianza invasiva.

## Scelte realmente necessarie prima del codice

1. **Dodici item e due aree.** La letteratura definisce l'orbiting attraverso due componenti essenziali e congiunte. Aggiungere una terza area avrebbe separato artificialmente visualizzazioni, reazioni e “Mi piace” senza evidenza di dimensioni autonome.
2. **Interruzione diretta e presenza digitale separate.** Una sola area alta non sostiene la configurazione completa: l'interruzione può descrivere ghosting o una chiusura; la presenza digitale può riflettere un contatto online ordinario.
3. **Profilo BROAD adattato al numero di aree.** Con due aree, `FOCUSED` indica una sola area HIGH e `BROAD` richiede entrambe HIGH. La regola generale diventa `BROAD` quando sono HIGH almeno `max(2, numero_aree - 1)` aree: non cambia i questionari correnti a quattro aree, dove ne richiede ancora tre.
4. **Effetti fuori dallo scoring.** Confusione, speranza, tristezza, rabbia e tentativi di dare un significato sono importanti nella scheda, ma non provano l'orbiting e non devono aumentare la media.
5. **Una sola relazione e una sola fase.** Chi compila considera sempre la stessa persona e i primi sei mesi successivi alla cessazione del contatto diretto.
6. **Nessuna domanda preliminare.** Se non vi è stata un'interruzione del contatto o la piattaforma non rende visibili le interazioni, l'introduzione dichiara che il risultato ha significato limitato; non si crea un criterio sì/no.
7. **Nessuna inferenza dalle piattaforme.** Il test usa soltanto attività effettivamente visibili a chi compila. Non afferma che visualizzazioni, suggerimenti o ordine dei contenuti indichino visite al profilo o sorveglianza.
8. **Sicurezza distinta.** Orbiting e cyberstalking non sono sinonimi. Ripetitività invasiva, contatti indesiderati, minacce o paura richiedono attenzione indipendentemente dal punteggio.
9. **Item originali.** Nessun item di altre misure o compiti di ricerca viene copiato, tradotto o adattato.

## Dimensioni e matrice degli item

| Ordine | Codice | Dimensione editoriale | Indicatori inclusi | Esclusioni intenzionali |
|---:|---|---|---|---|
| 1 | `interruzione` | Interruzione e assenza di comunicazione diretta | messaggi e chiamate senza risposta, conversazioni cessate, assenza di nuovi contatti, richieste di chiarimento, mancata comunicazione della conclusione | motivo, intenzione, colpa, rifiuto concordato, ghosting accertato |
| 2 | `presenza-digitale` | Presenza digitale visibile senza contatto diretto | visualizzazioni di storie, “Mi piace”, reazioni, condivisioni, mantenimento del follow, interazioni con contenuti taggati | visite invisibili al profilo, algoritmo, sorveglianza accertata, cyberstalking |

### Item originali raggruppati per area

| Area | N. | Testo |
|---|---:|---|
| interruzione | 1 | I miei messaggi diretti rimanevano senza risposta. |
| interruzione | 2 | Le conversazioni tra noi si interrompevano senza una spiegazione conclusiva. |
| interruzione | 3 | La persona smetteva di avviare conversazioni dirette con me. |
| interruzione | 4 | Le mie richieste di chiarire la situazione rimanevano senza risposta. |
| interruzione | 5 | I miei tentativi di contatto telefonico non ricevevano risposta. |
| interruzione | 6 | La conclusione del rapporto non mi veniva comunicata direttamente. |
| presenza digitale | 1 | La persona visualizzava le mie storie dopo aver interrotto il contatto diretto. |
| presenza digitale | 2 | La persona metteva “Mi piace” ai miei contenuti dopo l'interruzione del contatto. |
| presenza digitale | 3 | La persona reagiva ai miei contenuti senza scrivermi direttamente. |
| presenza digitale | 4 | La persona condivideva contenuti pubblicati da me senza contattarmi direttamente. |
| presenza digitale | 5 | La persona continuava a seguire il mio profilo dopo aver interrotto il contatto. |
| presenza digitale | 6 | La persona interagiva con contenuti online in cui ero presente o taggato. |

Gli item vengono somministrati intercalando `interruzione → presenza-digitale`. Nel risultato le due aree restano nello stesso ordine teorico. Le formulazioni negative sono limitate agli indicatori di assenza della comunicazione, elemento necessario del costrutto, e dovranno essere verificate con interviste cognitive.

## Istruzione e scala di risposta

Domanda mostrata: **“Pensando ai primi sei mesi dopo l'interruzione del contatto diretto con la persona scelta, o all'intero periodo se più breve, con quale frequenza accadeva?”**

Chi compila sceglie una sola relazione o frequentazione romantica conclusa o interrotta e pensa sempre alla stessa persona. Se non pubblicava contenuti, non poteva vedere le interazioni della piattaforma oppure il contatto diretto non era cessato, il risultato ha significato limitato. Le ancore descrivono frequenza percepita, non accordo con l'etichetta orbiting.

## Scoring descrittivo

- Ogni risposta vale da 1 a 5; tutti gli item hanno lo stesso peso e nessun item è invertito.
- Ogni area è la media dei suoi sei item; la media generale usa tutti i 12 item.
- Livelli editoriali: `LOW < 2,5`; `MEDIUM 2,5–<3,5`; `HIGH ≥ 3,5`.
- `LOW`: entrambe le aree LOW; `MIXED`: nessuna area HIGH e almeno una MEDIUM; `FOCUSED`: una sola area HIGH; `BROAD`: entrambe le aree HIGH.
- Le barre trasformano linearmente la media da 1–5 a 0–100. Non sono percentuali di orbiting, probabilità, gravità, percentili o confronti normativi.
- La media generale è soltanto una sintesi editoriale. L'interpretazione della configurazione richiede la lettura congiunta delle due aree; un livello alto in una sola area non dimostra orbiting.

## Profili globali previsti

| Profilo | Titolo specifico | Funzione interpretativa |
|---|---|---|
| LOW | Le dinamiche associate all'orbiting sembrano molto poco presenti nelle tue risposte | entrambe le componenti poco frequenti; non esclude episodi o canali non osservati |
| MIXED | Le dinamiche associate all'orbiting sembrano presenti in modo variabile | almeno una componente media e nessuna alta; configurazione parziale o occasionale |
| FOCUSED | Le dinamiche associate all'orbiting sembrano più presenti in una delle due aree | una componente alta senza l'altra; distinguere interruzione da sola e attività social da sola |
| BROAD | Le dinamiche associate all'orbiting sembrano molto presenti in entrambe le aree | interruzione diretta e presenza digitale entrambe frequenti; configurazione compatibile ma non accertata |

Ogni profilo mantiene gli stessi limiti e messaggi di sicurezza. `LOW` non esclude singoli episodi; `MIXED` esplicita la variabilità; `FOCUSED` non generalizza una componente isolata; `BROAD` descrive la compresenza senza attribuire intenzioni o trasformare le soglie in diagnosi.

## Evidenze e selezione delle fonti

| Affermazione o scelta | Fonte | Tipo di evidenza | Popolazione | Limite per l'app |
|---|---|---|---|---|
| L'orbiting è stato definito come ghosting accompagnato da una presenza ancora visibile sui social; le reazioni riportate includevano confusione, emozioni negative, tentativi di riparazione e accettazione | Pancani et al. (2021), [doi:10.1177/02654075211000417](https://doi.org/10.1177/02654075211000417) | studio qualitativo con richiamo di esperienze | 208 giovani adulti, ricerca condotta da università italiane | non sviluppa una scala; campione giovane, ricordo retrospettivo e inclusione di relazioni amicali |
| L'orbiting richiede tecnologie digitali e consiste in attività visibili sui social senza avviare comunicazione diretta; gli esiti non si distinguevano sempre da ghosting o rifiuto esplicito | Pancani, Aureli e Riva (2022), [doi:10.5817/CP2022-2-9](https://doi.org/10.5817/CP2022-2-9) | compito di richiamo e confronto tra condizioni | 176 giovani adulti finali, soprattutto italiani; 54 nella condizione orbiting | assegnazione al ricordo, alcuni episodi amicali, campione giovane; non valida item, frequenze o soglie |
| Ghosting, orbiting e breadcrumbing sono concetti vicini i cui confini richiedono ulteriore verifica | Schokkenbroek et al. (2025), [doi:10.1016/j.chb.2025.108637](https://doi.org/10.1016/j.chb.2025.108637) | analisi teorica della letteratura | letteratura internazionale | non è una validazione psicometrica e le definizioni restano legate alle tecnologie correnti |
| La cessazione della comunicazione e l'unfollowing sono aspetti variabili del ghosting, non una condizione sempre unilaterale e tutto-o-nulla | Collins, Thomas e Harris (2023), [doi:10.1111/pere.12492](https://doi.org/10.1111/pere.12492) | studio correlazionale | 260 partecipanti, soprattutto studenti statunitensi | riguarda ghosting, non orbiting ricevuto; campione di convenienza e risultati non trasferibili |
| Osservare attivamente un ex partner sui social è stato associato a un recupero post-rottura meno favorevole | Marshall (2012), [PMID 22946958](https://pubmed.ncbi.nlm.nih.gov/22946958/) | studio trasversale | adulti nel Regno Unito | studia chi osserva, non chi riceve la presenza digitale; associazione non causale e piattaforma storica |
| Il 1522 offre orientamento gratuito alle donne che vivono violenza o stalking | Dipartimento per le Pari Opportunità, [1522](https://www.1522.eu/cose-1522/) | informazione istituzionale sul servizio | territorio italiano | non è evidenza sull'orbiting e non sostituisce il 112 nelle emergenze |

Esistono studi empirici condotti da ricercatori e università italiani, ma non è stata individuata una validazione italiana di una scala specifica per l'orbiting ricevuto. Le fonti sostengono la delimitazione a due componenti e i limiti informativi; non validano i 12 item, le aree, la scala, le soglie o le restituzioni dell'app.

## Matrice sintetica scelta → evidenza → limite

| Scelta progettuale | Evidenza usata | Limite esplicito |
|---|---|---|
| Due aree congiunte: interruzione diretta e presenza digitale visibile | Pancani et al. 2021; Pancani et al. 2022 | aree editoriali, non fattori dimostrati; la definizione è emergente |
| Dodici item anziché 18 o 24 | eccezionale scarsità e ristrettezza del costrutto nelle fonti italiane | brevità teorica, non dimostrazione di adeguatezza psicometrica |
| Escludere vissuti ed effetti dallo scoring | risultati qualitativi e comparativi di Pancani et al. | effetti importanti ma aspecifici; non provano il comportamento o la sua causa |
| Non inferire visite invisibili, intenzione o sorveglianza | limiti delle piattaforme e delle fonti | il questionario registra soltanto attività visibile riferita |
| Distinguere da ghosting e cyberstalking | Pancani et al.; Collins et al.; Schokkenbroek et al. | sovrapposizioni possibili; sicurezza e contesto richiedono valutazione separata |
| BROAD solo con entrambe le aree HIGH | necessità congiunta delle due componenti e scelta editoriale | nessun cutoff, accuratezza o valore clinico dimostrato |

## Sicurezza e limiti informativi

- Prima e dopo il test: strumento informativo, originale e non validato; libertà di interrompersi.
- Il risultato descrive attività riferite e visibili, non accerta intenzioni, accessi al profilo, diagnosi, colpe o reati.
- Un livello basso non esclude contatti indesiderati o sorveglianza non visibile; un livello alto non dimostra orbiting o cyberstalking.
- Bloccare, silenziare o modificare la privacy sono opzioni personali e non obblighi; non suggerire confronti diretti quando potrebbero aumentare il pericolo.
- Per disagio o interferenza suggerire una persona fidata o un professionista. Per pericolo immediato indicare il 112; per donne che vivono violenza o stalking indicare il 1522.

## Piano di validazione necessario

Prima di descrivere lo strumento come affidabile o valido servono revisione di esperti in relazioni digitali, psicometria e cyberviolenza; revisione con utenti; interviste cognitive su interruzione, visibilità e attribuzione; studio pilota; verifica della struttura a due componenti con dati ordinali; affidabilità ed errore di misura; validità convergente e discriminante rispetto a strumenti autorizzati; analisi per età, genere, orientamento, piattaforma, privacy, relazione romantica/amicale e tipo di conclusione; DIF e invarianza; studio delle conseguenze della restituzione; replica indipendente.

## Test automatici richiesti

- versione `1.0`, 12 item unici, due aree da sei item e ordine intercalato;
- istruzione su una sola relazione, primi sei mesi, visibilità limitata e cinque ancore;
- profili `LOW/MIXED/FOCUSED/BROAD`, incluso `BROAD` con entrambe le aree HIGH;
- titoli specifici, testi minimi, barre, PDF, guida, categoria, correlati e sitemap;
- disclaimer su intenzione, ghosting, manipolazione, cyberstalking, algoritmo, alternative e sicurezza;
- fonti HTTPS uniche, contributi specifici e presenza delle evidenze italiane previste.
