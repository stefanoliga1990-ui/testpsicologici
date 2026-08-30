# Relazione e benessere — specifica psicometrica v1.0

## Identità e uso previsto

| Campo | Definizione |
|---|---|
| Identificativo | `relazione-dannosa-benessere` |
| Titolo pubblico | La mia relazione sta danneggiando il mio benessere? |
| Versione | `1.0` |
| Popolazione | adulti che compilano autonomamente il questionario in italiano |
| Finalità | auto-osservazione informativa di esperienze, pressioni e conseguenze percepite in una relazione romantica specifica |
| Periodo di riferimento | ultimi tre mesi della relazione attuale o gli ultimi tre mesi di una relazione terminata nell'ultimo anno; intero rapporto se durato meno |
| Unità di riferimento | una sola relazione e una sola persona per tutta la compilazione |
| Somministrazione | 24 item originali, sei aree da quattro item, ordine intercalato |
| Scala | `Mai=1`, `Raramente=2`, `A volte=3`, `Spesso=4`, `Quasi sempre=5` |
| Stato | questionario originale, non validato e non normato |

## Costrutto e confini

L'espressione **“relazione tossica”** è colloquiale, ampia e non diagnostica. Questa versione non tenta di trasformarla in una categoria clinica: esplora invece comportamenti osservabili, limitazioni dell'autonomia, possibilità di esprimere confini, gestione della responsabilità e impatto riferito sul benessere in una relazione romantica attuale o recente.

Le aree integrano due piani che vanno letti separatamente: qualità negativa delle interazioni e comportamenti che possono rientrare in quadri di violenza psicologica o controllo. Un conflitto, un periodo difficile, una comunicazione inefficace o una differenza di bisogni non equivalgono automaticamente a abuso; allo stesso tempo, minacce, coercizione, paura, isolamento, pressione sessuale o restrizioni economiche non diventano irrilevanti perché episodici o perché altre parti della relazione funzionano bene.

Il questionario non misura e non può stabilire:

- se la relazione sia “tossica”, abusante, sana o da interrompere;
- una diagnosi, personalità, intenzione, colpa o pericolosità del partner;
- verità giuridica, responsabilità penale, rischio futuro o sicurezza complessiva;
- qualità oggettiva della relazione, punto di vista del partner o compatibilità di coppia;
- depressione, ansia, trauma, autostima o altre condizioni di chi compila;
- gravità, probabilità, prevalenza, percentile, punteggio clinico o confronto normativo;
- causalità tra relazione e benessere individuale.

Spiegazioni alternative o concorrenti includono stress esterno, malattia, difficoltà economiche, carichi di cura, conflitto reciproco, stili comunicativi, differenze culturali, neurodivergenza, eventi isolati, poche occasioni di osservazione o una relazione già in cambiamento. Queste possibilità non giustificano pressioni o violazioni dei confini e non riducono il diritto a chiedere aiuto.

## Scelte realmente necessarie prima del codice

1. **Comportamenti, non etichette.** Titolo e scheda riconoscono l'espressione comune, ma item e risultati descrivono episodi riferiti senza classificare la relazione o il partner.
2. **Una relazione specifica.** Mescolare partner o periodi diversi renderebbe le frequenze poco interpretabili. L'utente sceglie una sola relazione attuale o terminata nell'ultimo anno.
3. **Sei lenti editoriali.** Le sei aree separano rispetto, confini, controllo, paura, reciprocità e impatto. Non sono fattori importati dall'EAPA-P, dalla CASR-SF o da altri strumenti.
4. **Ventiquattro item.** Quattro item per area permettono di coprire indicatori distinti entro il limite dell'app. La copertura è preliminare e richiede revisione con esperti e persone con esperienza vissuta.
5. **Direzione uniforme.** Tutti gli item descrivono esperienze potenzialmente problematiche: valori maggiori indicano maggiore frequenza riferita, senza item invertiti.
6. **Sintesi globale non compensativa.** La media facilita la lettura ma non consente a molte risposte basse di annullare un singolo episodio grave. Le aree restano visibili e la sicurezza è discussa a ogni livello.
7. **Profilo ampio conservativo.** Con sei aree, `BROAD` richiede almeno cinque aree `HIGH`, secondo la regola editoriale generale `max(2, numero_aree - 1)`. Da una a quattro aree `HIGH` producono `FOCUSED`.
8. **Nessuno screening di violenza.** Anche gli strumenti pubblicati per l'IPV mostrano limiti di validità di contenuto e trasferibilità; il questionario originale non viene presentato come strumento di rilevazione o safety planning.
9. **Sicurezza indipendente dal punteggio.** Il 112 è indicato per il pericolo immediato; il 1522 viene descritto correttamente come servizio pubblico gratuito per donne vittime di violenza e stalking.
10. **Item originali.** Nessun item di EAPA-P, CASR-SF, PMWI, CTS o altri strumenti viene copiato, tradotto o adattato.

## Dimensioni e matrice degli item

| Ordine | Codice | Dimensione editoriale | Indicatori inclusi | Esclusioni intenzionali |
|---:|---|---|---|---|
| 1 | `rispetto` | Rispetto e svalutazione | insulti, bisogni sminuiti, umiliazione pubblica, confidenze usate per ferire | qualità globale della comunicazione; intenzione; diagnosi |
| 2 | `confini` | Autonomia, privacy e consenso | limiti ignorati, dispositivi, uso del tempo, pressione fisica o sessuale | preferenze condivise; accordi consensuali; accertamento di reati |
| 3 | `controllo` | Controllo, isolamento e risorse | contatti, lavoro/studio, spostamenti, denaro | normale coordinamento; singola gelosia; motivazione del partner |
| 4 | `paura` | Paura, pressione e conseguenze nel confronto | autocensura, paura di dire no, minacce, sospensione della comunicazione legata alla resa | rischio futuro; intenzione; conflitto completo |
| 5 | `reciprocita` | Reciprocità, responsabilità e riparazione | responsabilità spostata, danno non riconosciuto, decisioni unilaterali, rinunce nei compromessi | compatibilità oggettiva; soddisfazione del partner; colpa globale |
| 6 | `impatto` | Impatto percepito sul benessere | autodubbio, attenzione assorbita, allerta, rinuncia ad attività | diagnosi o causalità; funzionamento generale fuori dalla relazione |

### Item originali raggruppati per area

| Area | N. | Testo |
|---|---:|---|
| rispetto | 1 | Il partner mi insultava o ridicolizzava durante un disaccordo. |
| rispetto | 2 | Il partner trattava i miei bisogni come poco importanti. |
| rispetto | 3 | Il partner mi umiliava davanti ad altre persone. |
| rispetto | 4 | Il partner usava una mia confidenza per ferirmi. |
| confini | 1 | Il partner insisteva dopo che avevo espresso un limite. |
| confini | 2 | Il partner si aspettava di poter leggere i miei messaggi o controllare i miei dispositivi. |
| confini | 3 | Il partner decideva come avrei dovuto usare il mio tempo. |
| confini | 4 | Il partner faceva pressione perché accettassi un contatto fisico o sessuale che non desideravo. |
| controllo | 1 | Il partner ostacolava i miei contatti con amici o familiari. |
| controllo | 2 | Il partner rendeva difficile dedicarmi al lavoro o allo studio. |
| controllo | 3 | Il partner controllava i miei spostamenti. |
| controllo | 4 | Il partner limitava il mio accesso al denaro o alle decisioni economiche che mi riguardavano. |
| paura | 1 | Modificavo ciò che dicevo per evitare una reazione del partner. |
| paura | 2 | Avevo paura di dire di no al partner. |
| paura | 3 | Il partner mi minacciava per influenzare una mia decisione. |
| paura | 4 | Dopo un disaccordo, la comunicazione riprendeva soltanto quando accettavo la posizione del partner. |
| reciprocità | 1 | Il partner attribuiva a me la responsabilità dei problemi condivisi. |
| reciprocità | 2 | Dopo avermi ferito, il partner evitava di riconoscere l'accaduto. |
| reciprocità | 3 | Le decisioni che riguardavano entrambi venivano prese senza coinvolgermi. |
| reciprocità | 4 | I compromessi richiedevano soprattutto mie rinunce. |
| impatto | 1 | Dopo le interazioni con il partner, dubitavo del mio giudizio. |
| impatto | 2 | Le tensioni della relazione occupavano la mia attenzione durante le attività quotidiane. |
| impatto | 3 | Rimanevo in allerta per anticipare una reazione del partner. |
| impatto | 4 | Rinunciavo ad attività importanti per gestire le tensioni della relazione. |

Nel database gli item sono salvati per area e somministrati in ordine round-robin `rispetto → confini → controllo → paura → reciprocita → impatto`. Nel risultato le aree restano nell'ordine teorico sopra indicato.

## Istruzione e scala

Domanda mostrata: **“Pensando agli ultimi tre mesi della relazione scelta, o all'intero rapporto se è durato meno, con quale frequenza ti è capitata questa esperienza?”**

Le cinque categorie descrivono frequenza percepita. Non sono previste risposte mancanti: la persona può interrompere il questionario in qualsiasi momento; “Mai” va usato solo quando l'esperienza formulata non è avvenuta nel periodo scelto.

## Scoring descrittivo

- Ogni risposta vale da 1 a 5; pesi uguali e nessun item invertito.
- Ogni area è la media di quattro item; la media generale usa tutti i 24 item.
- Livelli: `LOW < 2,5`; `MEDIUM 2,5–<3,5`; `HIGH ≥ 3,5`.
- Profili: `LOW` con sei aree LOW; `MIXED` senza aree HIGH e almeno una MEDIUM; `FOCUSED` con una-quattro aree HIGH; `BROAD` con almeno cinque aree HIGH.
- Le barre trasformano linearmente la media da 1–5 a 0–100. Non sono percentuali di danno, abuso, sicurezza, gravità, probabilità o confronti normativi.
- La media generale aggrega condotte ed effetti diversi ed è soltanto una sintesi editoriale. Un episodio grave non va interpretato tramite la media.

## Profili globali previsti

| Profilo | Titolo specifico | Funzione |
|---|---|---|
| LOW | Le esperienze relazionali potenzialmente dannose sembrano poco presenti nelle risposte | frequenza contenuta in tutte le aree; non esclude episodi singoli importanti o condotte non incluse |
| MIXED | Le esperienze relazionali potenzialmente dannose sembrano presenti in modo variabile | differenze tra aree, senza livelli alti; invita a osservare contesto e ripetizione |
| FOCUSED | Le esperienze relazionali potenzialmente dannose sembrano più presenti in alcuni ambiti | da una a quattro aree emergenti; non generalizza all'intera relazione |
| BROAD | Le esperienze relazionali potenzialmente dannose sembrano molto presenti in gran parte degli ambiti | almeno cinque aree alte; considera ampiezza, persistenza, impatto e sicurezza senza formulare diagnosi |

Ogni profilo mantiene identici i messaggi di sicurezza. Un risultato basso non esclude violenza o bisogno di supporto; un risultato alto non accerta abuso, intenzione o responsabilità e non prescrive una decisione sulla relazione.

## Evidenze e matrice fonte → scelta → limite

| Affermazione o scelta | Fonte | Evidenza | Popolazione | Limite per l'app |
|---|---|---|---|---|
| La violenza psicologica nella coppia può includere pressione, controllo, manipolazione e coercizione | Lausi et al. (2021), [doi:10.3390/ijerph182312717](https://doi.org/10.3390/ijerph182312717) | validazione italiana EAPA-P | 1.435 adulti italiani, prevalentemente donne e campione online | strumento, item, fattori e punteggi non vengono trasferiti; non copre tutte le relazioni e non valida l'app |
| Nel contesto italiano sono descritti denigrazione, isolamento, controllo, intimidazione e restrizioni economiche | [Istat — Definizioni e indicatori](https://www.istat.it/statistiche-per-temi/focus/violenza-sulle-donne/il-contesto/definizioni-e-indicatori/) | definizioni operative istituzionali | donne e relazioni di coppia in Italia | perimetro di genere; non è una scala per tutti gli adulti e non classifica la singola relazione |
| A livello europeo la violenza psicologica è un pattern che può includere isolamento, minacce, controllo e umiliazione | [EIGE — psychological violence](https://eige.europa.eu/publications-resources/thesaurus/terms/1241?language_content_entity=en) | definizione e thesaurus istituzionale UE | quadro europeo sulla violenza di genere | definizione statistico-giuridica, non misura psicometrica; la pagina segnala possibili aggiornamenti |
| L'IPV comprende danno fisico, sessuale o psicologico e comportamenti controllanti | [WHO — Violence against women](https://www.who.int/health-topics/violence-against-women) | sintesi istituzionale internazionale | soprattutto donne e violenza di genere | non copre allo stesso modo ogni genere o configurazione relazionale e non valida item o soglie |
| Le misure di screening IPV disponibili presentano limiti rilevanti di validità di contenuto e criterio | Li et al. (2024), [doi:10.1371/journal.pone.0310297](https://doi.org/10.1371/journal.pone.0310297) | revisione sistematica COSMIN di 18 strumenti | popolazione generale in studi internazionali | eterogeneità di strumenti e contesti; sostiene la cautela, non il nuovo questionario |
| Il controllo coercitivo è associato a esiti di salute mentale a livello di gruppo | Lohmann et al. (2024), [doi:10.1177/15248380231162972](https://doi.org/10.1177/15248380231162972) | revisione sistematica e meta-analisi | studi internazionali, soprattutto donne esposte a IPV | associazione non equivale a causalità individuale; misure e campioni eterogenei |
| Qualità relazionale e benessere personale sono associati, con moderatori e possibili influenze reciproche | Proulx et al. (2007), [doi:10.1111/j.1741-3737.2007.00393.x](https://doi.org/10.1111/j.1741-3737.2007.00393.x) | meta-analisi di 93 studi | soprattutto persone sposate in studi internazionali | non definisce “relazione dannosa”, non prova causalità e non è specifica per adulti italiani |
| Il 1522 offre orientamento gratuito continuativo a donne vittime di violenza e stalking | [1522 — Dipartimento per le Pari Opportunità](https://www.1522.eu/cose-1522/) | informazione istituzionale sul servizio | territorio italiano | servizio rivolto alle donne; non sostituisce il 112 nelle emergenze e non è evidenza sul costrutto |

La fonte italiana EAPA-P informa il perimetro ma non autorizza a copiare item, fattori o soglie. Non è stata individuata una validazione italiana di uno strumento identico, inclusivo di tutti i generi e finalizzato alla sola auto-osservazione informativa del benessere relazionale. Le fonti internazionali sono quindi usate con limiti di trasferibilità espliciti.

## Sicurezza e limiti informativi

- Introduzione e risultato dichiarano che il questionario è originale, informativo, non validato e non decide se restare o lasciare la relazione.
- La persona può interrompersi; non viene richiesto di descrivere episodi o raccogliere prove.
- Non suggerire confronti diretti, terapia di coppia o comunicazione con il partner quando potrebbero aumentare il pericolo.
- Un singolo episodio di minaccia, coercizione, violazione del consenso o violenza merita attenzione indipendentemente dal punteggio.
- In pericolo immediato: 112. Per donne che vivono violenza o stalking: 1522, gratuito e attivo 24 ore su 24, anche per orientamento.
- Per sofferenza o interferenza senza pericolo immediato: persona fidata, medico, psicologo/psicoterapeuta o servizi territoriali, secondo sicurezza e preferenze.

## Piano di validazione necessario

Prima di usare termini come affidabile, valido o utile per lo screening servono: revisione di psicologi, psicoterapeuti, psicometristi, servizi antiviolenza ed esperti di consenso; coinvolgimento di adulti con esperienze e configurazioni relazionali diverse; interviste cognitive orientate anche alla sicurezza; studio pilota; valutazione di contenuto; EFA/CFA ordinali su campioni distinti; confronto tra sei aree e modelli alternativi; affidabilità ed errore di misura; convergenza e discriminazione con strumenti autorizzati; invarianza per genere, età, orientamento, relazione attuale/passata e cultura; studio degli effetti della restituzione e dei falsi rassicuranti; replica indipendente. Eventuali cut-off richiederebbero una diversa finalità, criteri esterni e procedure di safety planning non presenti nell'app.

## Test automatici richiesti

- versione `1.0`, 24 item unici, sei aree da quattro e intercalazione;
- periodo, singola relazione, scala di frequenza e direzione uniforme;
- profili `LOW/MIXED/FOCUSED/BROAD`, inclusi casi con quattro e cinque aree HIGH;
- titoli specifici, sintesi di almeno due frasi e approfondimenti di almeno tre;
- ordine teorico delle aree, barra generale e sei barre di area;
- sicurezza e limiti presenti a ogni profilo, nella guida e nel PDF;
- categoria, correlati, guida e sitemap;
- almeno una fonte italiana o europea pertinente, URL HTTPS unici e contributi specifici.
