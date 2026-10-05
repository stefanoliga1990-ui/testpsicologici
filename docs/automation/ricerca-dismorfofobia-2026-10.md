# Dismorfofobia: decisione editoriale per la coda

## Decisione

Accodare un solo argomento, **Dismorfofobia**. Le preoccupazioni centrate sulla muscolatura rientrano nella ricerca per questo argomento, senza una seconda voce o un secondo questionario. Prima dell'implementazione servono la specifica psicometrica e la matrice delle fonti previste dallo standard del repository. Il questionario originale deve restituire esperienze auto-riferite senza diagnosticare il disturbo da dismorfismo corporeo.

Non suddividere la voce in questionari per singole parti del corpo, controlli allo specchio, evitamento, ricerca di trattamenti estetici o muscolatura: sono contenuti e comportamenti che possono coesistere nella stessa presentazione. Il numero e la struttura delle aree restano da decidere dopo la revisione delle evidenze.

Il questionario sull'apprezzamento del corpo già in coda (ordine 53) esplora un costrutto positivo. Non può fungere da misura inversa delle preoccupazioni dismorfiche. La voce sulla dismorfofobia deve suggerire un confronto professionale quando preoccupazioni e interferenza incidono sulla vita quotidiana, indipendentemente dal profilo editoriale ottenuto.

## Matrice della decisione

| Affermazione o scelta | Fonte e tipo di evidenza | Popolazione | Limite per questa decisione |
| --- | --- | --- | --- |
| Il dismorfismo corporeo comprende preoccupazione per l'aspetto, comportamenti ripetitivi o evitamento e possibile interferenza; è sensato un argomento generale. | [NICE, CG31, raccomandazioni sul riconoscimento e la valutazione](https://www.nice.org.uk/guidance/cg31/chapter/Recommendations), linea guida clinica britannica; [NHS, Body dysmorphic disorder](https://www.nhs.uk/mental-health/conditions/body-dysmorphia/), informazione istituzionale. | Persone che cercano assistenza, con indicazioni anche per la popolazione generale. | Le descrizioni cliniche non validano un questionario originale di auto-osservazione. |
| Esistono evidenze italiane sulla misurazione delle preoccupazioni dismorfiche. | [Luca et al., 2011, *Body Image*, DOI 10.1016/j.bodyim.2011.04.007](https://pubmed.ncbi.nlm.nih.gov/21664200/), studio psicometrico della versione italiana del Body Image Concern Inventory. | 412 volontari italiani del Centro e Sud, età 13–66 anni. | Campione non clinico e geograficamente limitato; la validazione riguarda lo strumento studiato, non quello dell'app. |
| La presentazione centrata sulla muscolatura va considerata nel tema generale, senza presumere che riguardi ogni persona. | [Cooper et al., 2020, *International Journal of Eating Disorders*, DOI 10.1002/eat.23349](https://doi.org/10.1002/eat.23349), revisione sistematica e meta-analitica di 40 studi. | Campioni eterogenei della letteratura internazionale. | La revisione trova evidenze insufficienti per stabilire se la dismorfia muscolare sia una condizione autonoma rispetto al dismorfismo corporeo o ad altri disturbi. |
| Esistono strumenti studiati in Italia sul tema muscolare, ma la trasferibilità dipende dalla popolazione. | [Santarnecchi e Dèttore, 2012, *Body Image*, DOI 10.1016/j.bodyim.2012.03.006](https://pubmed.ncbi.nlm.nih.gov/22521181/), studio di validazione italiana MDDI; [Riccobono et al., 2020, *Brain and Behavior*, DOI 10.1002/brb3.1666](https://pubmed.ncbi.nlm.nih.gov/32469110/), validazione italiana ACQ; [Cerea et al., 2022, *International Journal of Environmental Research and Public Health*, DOI 10.3390/ijerph19159487](https://doi.org/10.3390/ijerph19159487), validazione italiana MDDI in donne. | Culturisti, altri praticanti di attività fisica e controlli; lo studio ACQ comprende 322 uomini adulti. | Queste validazioni non forniscono norme o soglie per la popolazione generale né validano il futuro questionario dell'app. |

## Punti da verificare al momento dell'implementazione

- Delimitare la popolazione adulta e il periodo di riferimento; valutare separatamente l'eventuale uso con adolescenti.
- Verificare con fonti primarie aggiornate le differenze rispetto a disturbi alimentari, condizioni mediche e attività sportiva non problematica.
- Definire item osservabili e neutrali che non chiedano di giudicare se un difetto sia reale, né presuppongano un genere o la pratica di bodybuilding.
- Documentare la distinzione rispetto all'apprezzamento del corpo; se non emerge una restituzione responsabile, applicare la procedura `BLOCKED` alla voce.

## Esito dell'implementazione manuale

La ricerca successiva ha consentito una restituzione descrittiva, senza diagnosi. La [specifica v1.0](../test-dismorfofobia-v1.md) documenta quattro aree editoriali, 16 item originali, scoring, limiti e una matrice di fonti che include due studi italiani e una revisione COSMIN del 2026. Il titolo operativo del questionario descrive le preoccupazioni per l'aspetto invece di chiedere se la persona abbia la dismorfofobia. La guida mantiene esplicito il collegamento al disturbo da dismorfismo corporeo e separa le fonti scientifiche dalla lettura facoltativa della revisora Alessia Liga.
