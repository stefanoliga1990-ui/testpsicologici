package com.example.testpsicologici.service;

import com.example.testpsicologici.model.GuideReference;
import com.example.testpsicologici.model.GuideSection;
import com.example.testpsicologici.model.InformationGuide;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GuideCatalogue {

    private final List<InformationGuide> guides = List.of(
            new InformationGuide(
                    "autismo-adulti",
                    "tratti-autistici-adulti",
                    "Autismo nell'adulto",
                    "Autismo nell'adulto: caratteristiche, segnali e valutazione",
                    "Autismo negli adulti: caratteristiche e segnali | Spazio Test",
                    "Una guida concisa all'autismo nell'adulto: caratteristiche, differenze sensoriali, masking, valutazione specialistica e limiti dei test online.",
                    "L'autismo è una condizione del neurosviluppo che può influenzare comunicazione, interazione, flessibilità, interessi e percezione sensoriale. Nell'adulto può essere riconosciuto tardi e presentarsi in modi molto diversi da persona a persona.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è l'autismo",
                                    List.of(
                                            "L'autismo è una differenza del neurosviluppo presente fin dalle prime fasi della vita. Non è una malattia acquisita e non coincide con un unico modo di pensare, comunicare o vivere le relazioni.",
                                            "Si parla di spettro perché caratteristiche, bisogni di supporto, punti di forza e difficoltà possono combinarsi con intensità molto diverse. Una persona può essere autonoma in molte aree e incontrare comunque fatica in contesti sociali, sensoriali o poco prevedibili."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Esperienze possibili",
                                    "Come può manifestarsi nell'adulto",
                                    List.of(
                                            "Le caratteristiche non sono identiche per tutti e non devono essere tutte presenti nello stesso modo. Alcuni adulti sviluppano strategie per adattarsi alle aspettative sociali: questo processo, spesso chiamato masking, può rendere i segnali meno visibili e richiedere molta energia."
                                    ),
                                    List.of(
                                            "Comunicazione e reciprocità: interpretare tono, gesti, sottintesi o turni della conversazione può richiedere attenzione consapevole.",
                                            "Routine e cambiamenti: programmare in anticipo e mantenere una certa prevedibilità può aiutare a sentirsi più stabili.",
                                            "Interessi e attenzione: alcuni argomenti possono essere vissuti con particolare intensità, precisione e continuità.",
                                            "Percezione sensoriale: suoni, luci, odori, consistenze, temperature o contatto fisico possono essere avvertiti in modo più o meno intenso."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Singole caratteristiche non bastano per una diagnosi",
                                    List.of(
                                            "Preferire la routine, sentirsi affaticati dopo una situazione sociale o essere sensibili ai rumori non significa automaticamente essere autistici. Esperienze simili possono comparire anche in relazione ad ansia, ADHD, difficoltà comunicative, stress, depressione, disturbi ossessivo-compulsivi o condizioni fisiche e sensoriali.",
                                            "Per questo contano la storia dello sviluppo, la presenza delle caratteristiche in più contesti, il loro andamento nel tempo e l'impatto concreto sulla vita. Anche eventuali condizioni coesistenti devono essere considerate senza attribuire ogni esperienza a un'unica spiegazione."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Valutazione",
                                    "Che cosa considera un percorso specialistico",
                                    List.of(
                                            "Una valutazione dell'autismo nell'adulto è un processo complessivo condotto da professionisti con competenze specifiche. Può includere colloqui, strumenti strutturati, osservazione, informazioni sulla vita attuale e, quando disponibili e pertinenti, elementi relativi all'infanzia.",
                                            "L'obiettivo non è contare alcuni segnali isolati, ma comprendere comunicazione sociale, comportamenti e interessi, sensibilità sensoriale, funzionamento quotidiano, bisogni di supporto e possibili spiegazioni alternative. Un questionario online, compreso quello di Spazio Test, non può confermare né escludere una diagnosi."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto",
                                    "Quando può essere utile parlarne con qualcuno",
                                    List.of(
                                            "Può essere utile chiedere un confronto qualificato se queste esperienze sono presenti da molto tempo, richiedono uno sforzo significativo o incidono su relazioni, studio, lavoro, autonomia o benessere. Un primo passo può essere parlarne con il medico di base o informarsi sui servizi specializzati disponibili nel proprio territorio.",
                                            "Cercare una valutazione non serve a dimostrare qualcosa partendo da un risultato online: può invece aiutare a comprendere meglio la propria storia, distinguere spiegazioni diverse e individuare eventuali forme di supporto."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, comunicazione sociale, segnali impliciti, routine, flessibilità, interessi e sensibilità sensoriale. Le risposte offrono una traccia di auto-osservazione e non una conclusione clinica.",
                    List.of(
                            new GuideReference(
                                    "What is autism? — NHS",
                                    "https://www.nhs.uk/conditions/autism/what-is-autism/",
                                    "Riferimento per descrivere l'autismo come differenza del neurosviluppo e chiarire la varietà delle esperienze e delle condizioni che possono coesistere."
                            ),
                            new GuideReference(
                                    "Signs of autism in adults — NHS",
                                    "https://www.nhs.uk/conditions/autism/signs-in-adults/",
                                    "Riferimento per comunicazione, linguaggio implicito, routine, interessi, differenze sensoriali e masking nell'età adulta."
                            ),
                            new GuideReference(
                                    "Autism spectrum disorder in adults: diagnosis and management — NICE CG142",
                                    "https://www.nice.org.uk/guidance/cg142/chapter/Recommendations",
                                    "Riferimento per storia dello sviluppo, funzionamento nei diversi contesti, valutazione complessiva e possibili condizioni alternative o coesistenti."
                            ),
                            new GuideReference(
                                    "Clinical testing and diagnosis for autism spectrum disorder — CDC",
                                    "https://www.cdc.gov/autism/hcp/diagnosis/index.html",
                                    "Riferimento per le aree della comunicazione e interazione sociale, i comportamenti ripetitivi, gli interessi, la flessibilità e la reattività sensoriale."
                            ),
                            new GuideReference(
                                    "Diagnosi e trattamento del disturbo dello spettro autistico negli adulti — ISS/SNLG",
                                    "https://www.iss.it/documents/20126/8968214/Linea_Guida_ASD_adulti.pdf/b15434a0-3bcd-60c0-46b2-e5b34dc170bd?t=1691389267884",
                                    "Linea guida italiana consultata per valutazione complessiva, diagnosi differenziale, condizioni coesistenti e organizzazione del supporto nell'adulto; non valida il questionario dell'app."
                            )
                    )
            ),
            new InformationGuide(
                    "adhd-adulti",
                    "tratti-adhd-adulti",
                    "ADHD nell'adulto",
                    "ADHD nell'adulto: caratteristiche, segnali e valutazione",
                    "ADHD negli adulti: sintomi e caratteristiche | Spazio Test",
                    "Una guida concisa all'ADHD nell'adulto: disattenzione, irrequietezza, impulsività, valutazione specialistica e limiti dei test online.",
                    "L'ADHD è una condizione del neurosviluppo associata a modalità persistenti di disattenzione e/o iperattività-impulsività. Nell'adulto può incidere su organizzazione, gestione del tempo, continuità nelle attività, relazioni e lavoro, ma si presenta in modi diversi da persona a persona.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è l'ADHD",
                                    List.of(
                                            "L'ADHD, o disturbo da deficit di attenzione e iperattività, è una condizione del neurosviluppo. Le sue caratteristiche iniziano nell'infanzia, anche quando vengono riconosciute soltanto in età adulta, e possono cambiare nel modo in cui si manifestano nel corso della vita.",
                                            "Non tutte le persone presentano lo stesso profilo: per alcune prevalgono le difficoltà attentive e organizzative, per altre irrequietezza e impulsività, mentre molte riconoscono aspetti di entrambe le aree."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Esperienze possibili",
                                    "Come può manifestarsi nell'adulto",
                                    List.of(
                                            "Nell'adulto l'iperattività non coincide necessariamente con un movimento evidente e continuo: può essere vissuta come irrequietezza interna o difficoltà a rallentare. Alcune persone sviluppano inoltre strategie di compensazione che rendono le difficoltà meno visibili, pur richiedendo molto impegno."
                                    ),
                                    List.of(
                                            "Attenzione: distrarsi facilmente, perdere il filo, dimenticare informazioni o faticare a mantenere la concentrazione su compiti poco stimolanti.",
                                            "Organizzazione e tempo: iniziare o completare attività, stabilire priorità, rispettare scadenze e ricordare appuntamenti può richiedere uno sforzo particolare.",
                                            "Irrequietezza: sentirsi spesso in movimento, cercare stimoli, parlare molto o trovare difficile restare fermi e attendere.",
                                            "Impulsività: interrompere, rispondere prima del tempo o prendere decisioni rapide senza riuscire a considerare pienamente le conseguenze."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Distrazione e impulsività non indicano sempre ADHD",
                                    List.of(
                                            "Dimenticare un appuntamento, rimandare un compito o attraversare un periodo di scarsa concentrazione è comune e non basta per parlare di ADHD. Stress, sonno insufficiente, ansia, depressione, uso di sostanze, condizioni fisiche o altri profili neurodivergenti possono produrre esperienze simili o coesistere con l'ADHD.",
                                            "In una valutazione contano la persistenza nel tempo, l'esordio nell'infanzia, la presenza in più contesti e l'interferenza con aspetti importanti della vita. Le difficoltà non vengono interpretate come mancanza di volontà o di interesse."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Valutazione",
                                    "Che cosa considera un percorso specialistico",
                                    List.of(
                                            "La diagnosi viene formulata da un professionista qualificato attraverso una valutazione clinica e psicosociale complessiva. Il percorso considera storia dello sviluppo, esperienze scolastiche e lavorative, funzionamento attuale, salute fisica e mentale e manifestazioni nei diversi ambienti di vita.",
                                            "Questionari e scale possono offrire informazioni aggiuntive, ma non sono sufficienti da soli. È necessario valutare anche possibili spiegazioni alternative e condizioni coesistenti. Il questionario di Spazio Test non può quindi confermare né escludere l'ADHD."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto",
                                    "Quando può essere utile chiedere un confronto",
                                    List.of(
                                            "Può essere utile parlarne con un professionista se queste esperienze erano presenti già nell'infanzia, compaiono in più situazioni e interferiscono con studio, lavoro, relazioni, gestione domestica, sicurezza o benessere. Un primo passo può essere rivolgersi al medico di base o informarsi sui servizi specializzati disponibili nel proprio territorio.",
                                            "Una valutazione accurata può aiutare a comprendere il proprio profilo, distinguere cause diverse e individuare strategie, adattamenti o forme di supporto appropriate alla situazione individuale."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, attenzione, organizzazione, gestione del tempo, irrequietezza e impulsività. Le risposte aiutano a osservare la frequenza percepita di queste esperienze e non costituiscono uno screening validato o una diagnosi.",
                    List.of(
                            new GuideReference(
                                    "ADHD in adults — NHS",
                                    "https://www.nhs.uk/conditions/adhd-adults/",
                                    "Riferimento per disattenzione, organizzazione, irrequietezza, impulsività, variabilità dei profili e possibili condizioni alternative o coesistenti."
                            ),
                            new GuideReference(
                                    "Attention deficit hyperactivity disorder: diagnosis and management — NICE NG87",
                                    "https://www.nice.org.uk/guidance/ng87/chapter/recommendations",
                                    "Riferimento per esordio nell'infanzia, presenza in più contesti, impatto sul funzionamento e valutazione clinica e psicosociale specialistica."
                            ),
                            new GuideReference(
                                    "Attention-Deficit/Hyperactivity Disorder: What You Need to Know — NIMH",
                                    "https://www.nimh.nih.gov/health/publications/attention-deficit-hyperactivity-disorder-what-you-need-to-know",
                                    "Riferimento per manifestazioni nell'adulto, andamento nel corso della vita, raccolta della storia personale e necessità di considerare sonno, stress, salute mentale e condizioni fisiche."
                            ),
                            new GuideReference(
                                    "Validity of the Italian Version of DIVA-5 — Di Lorenzo e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/39942433/",
                                    "Studio italiano recente su un'intervista clinica per adulti, consultato per il contesto nazionale e la valutazione strutturata; il campione specialistico e lo strumento diverso impediscono di trasferire proprietà al test online."
                            )
                    )
            ),
            new InformationGuide(
                    "disturbo-ossessivo-compulsivo",
                    "tratti-ossessivo-compulsivi",
                    "Pensieri ossessivi e compulsioni (DOC)",
                    "Disturbo ossessivo-compulsivo (DOC): pensieri e compulsioni",
                    "Disturbo ossessivo-compulsivo (DOC): sintomi | Spazio Test",
                    "Una guida concisa al disturbo ossessivo-compulsivo: ossessioni, compulsioni, rituali mentali, impatto quotidiano e possibilità di supporto.",
                    "Il disturbo ossessivo-compulsivo (DOC) è caratterizzato da ossessioni, compulsioni o entrambe. I pensieri, le immagini o gli impulsi intrusivi possono provocare forte disagio; i comportamenti o gli atti mentali ripetitivi cercano di ridurlo, ma spesso alimentano un ciclo che tende a ripresentarsi.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cosa sono ossessioni e compulsioni",
                                    List.of(
                                            "Le ossessioni sono pensieri, immagini, impulsi o dubbi ricorrenti, indesiderati e intrusivi. Possono riguardare contaminazione, responsabilità e paura di causare danni, ordine, simmetria o contenuti percepiti come inaccettabili.",
                                            "Le compulsioni sono comportamenti o atti mentali ripetitivi che la persona sente di dover eseguire. Controllare, lavarsi, contare, riordinare, ripetere parole mentalmente, cercare rassicurazioni o evitare determinate situazioni sono alcuni esempi possibili. Non tutte le compulsioni sono visibili agli altri."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Il meccanismo",
                                    "Come può mantenersi il ciclo ossessivo-compulsivo",
                                    List.of(
                                            "Un'ossessione può generare ansia, disgusto, colpa o una forte sensazione di incertezza. La compulsione viene messa in atto per neutralizzare il pensiero, prevenire una conseguenza temuta o ottenere la sensazione che qualcosa sia finalmente a posto.",
                                            "Il sollievo prodotto dal rituale è generalmente temporaneo. Quando il dubbio o il disagio ritornano, la persona può sentire il bisogno di ripetere il comportamento, rafforzando il ciclo tra ossessione e compulsione."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Un pensiero intrusivo non è un'intenzione",
                                    List.of(
                                            "Pensieri indesiderati, anche insoliti o disturbanti, possono comparire in molte persone. Avere un'immagine mentale violenta, sessuale o offensiva non significa desiderarla né essere sul punto di agire: nel DOC questi contenuti sono spesso vissuti proprio come estranei, spaventosi o contrari ai propri valori.",
                                            "Anche ricontrollare occasionalmente una porta o preferire un certo ordine non indica di per sé un disturbo. Diventano rilevanti la persistenza, la difficoltà a interrompere il ciclo, il tempo assorbito, il disagio e l'interferenza con relazioni, studio, lavoro o attività quotidiane."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Valutazione",
                                    "Che cosa considera un percorso specialistico",
                                    List.of(
                                            "Una valutazione considera la natura di ossessioni e compulsioni, il tempo che occupano, il livello di sofferenza, gli evitamenti e il loro effetto sul funzionamento. Esplora inoltre l'andamento nel tempo, la salute generale e l'eventuale presenza di ansia, depressione, tic o altre condizioni che possono coesistere o richiedere una lettura differente.",
                                            "Un questionario può aiutare a descrivere alcune esperienze, ma non basta a formulare o escludere una diagnosi. Il risultato del test di Spazio Test deve quindi essere letto esclusivamente come spunto di auto-osservazione."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto",
                                    "Quando può essere utile chiedere un confronto",
                                    List.of(
                                            "Può essere utile rivolgersi a un professionista se pensieri intrusivi, rituali, richieste di rassicurazione o evitamenti provocano forte disagio, richiedono molto tempo o limitano la vita quotidiana. Parlare apertamente dei contenuti può essere difficile, ma i sintomi del DOC sono involontari e non costituiscono una colpa personale.",
                                            "Esistono trattamenti specifici. Le linee guida includono tra le opzioni la terapia cognitivo-comportamentale con esposizione e prevenzione della risposta (ERP) e, secondo le necessità valutate con un professionista, trattamenti farmacologici. Il percorso più appropriato dipende dalla situazione individuale."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, pensieri intrusivi e dubbio, contaminazione e pulizia, controlli, responsabilità, ordine, ripetizione e rituali mentali. Non misura da solo disagio, storia personale e funzionamento necessari per una valutazione clinica.",
                    List.of(
                            new GuideReference(
                                    "Symptoms – Obsessive compulsive disorder (OCD) — NHS",
                                    "https://www.nhs.uk/mental-health/conditions/obsessive-compulsive-disorder-ocd/symptoms/",
                                    "Riferimento per distinguere ossessioni, disagio e compulsioni, descrivere il sollievo temporaneo dei rituali e chiarire che un pensiero intrusivo non equivale all'intenzione di agire."
                            ),
                            new GuideReference(
                                    "Obsessive-Compulsive Disorder: When Unwanted Thoughts or Repetitive Behaviors Take Over — NIMH",
                                    "https://www.nimh.nih.gov/health/publications/obsessive-compulsive-disorder-when-unwanted-thoughts-or-repetitive-behaviors-take-over",
                                    "Riferimento per esempi di ossessioni e compulsioni e per considerare controllo, tempo occupato, sollievo temporaneo, disagio e interferenza nella vita quotidiana."
                            ),
                            new GuideReference(
                                    "Obsessive-compulsive disorder and body dysmorphic disorder: treatment — NICE CG31",
                                    "https://www.nice.org.uk/guidance/cg31/chapter/Recommendations",
                                    "Riferimento per la natura involontaria dei sintomi, la valutazione del disagio e del funzionamento e le opzioni di trattamento per gli adulti, inclusa la CBT con ERP."
                            ),
                            new GuideReference(
                                    "The Italian version of the Obsessive Compulsive Inventory — Sica e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/18701254/",
                                    "Validazione italiana su campioni comunitario e clinico consultata per multidimensionalità e sensibilità al livello di istruzione; punteggi e proprietà dell'OCI-R non sono trasferiti al questionario dell'app."
                            )
                    )
            ),
            new InformationGuide(
                    "autostima",
                    "autostima",
                    "Autostima",
                    "Autostima: che cos'è, come cambia e come sostenerla",
                    "Autostima: cos'è e come migliorarla | Spazio Test",
                    "Una guida concisa all'autostima: valore personale, fiducia, autocritica, confronto con gli altri e modi concreti per costruire un rapporto più equilibrato con sé.",
                    "L'autostima riguarda la valutazione soggettiva del proprio valore come persona. Non coincide con l'essere sempre sicuri, con il riuscire in tutto o con il sentirsi superiori agli altri: una base sufficientemente solida permette di riconoscere qualità e limiti senza far dipendere il proprio valore da un singolo risultato o giudizio.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è l'autostima",
                                    List.of(
                                            "L'autostima comprende il modo in cui una persona considera, accetta e rispetta sé stessa. È una valutazione interna e non una misura oggettiva di capacità, risultati, aspetto o approvazione ricevuta dagli altri.",
                                            "Avere un'autostima equilibrata non significa pensarsi perfetti. Significa riuscire a mantenere un senso di dignità e valore anche quando si commette un errore, si riceve una critica o si incontra un limite."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni utili",
                                    "Autostima e fiducia in sé non sono la stessa cosa",
                                    List.of(
                                            "La fiducia personale può riferirsi a un compito o a una situazione specifica: ci si può sentire competenti nel lavoro e insicuri nelle relazioni, oppure avere dubbi su una capacità mantenendo comunque un senso di valore personale.",
                                            "Anche autostima e narcisismo non coincidono. Riconoscersi degni di rispetto non richiede di considerarsi migliori degli altri né di ignorare i propri limiti."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Esperienze possibili",
                                    "Come può manifestarsi una bassa autostima",
                                    List.of(
                                            "Tutti attraversano momenti di insicurezza. Quando però il giudizio negativo diventa abituale, può portare a leggere errori e critiche come prove di scarso valore, svalutare i risultati positivi o dipendere molto da confronto, conferme e approvazione.",
                                            "Evitare sfide, relazioni o occasioni per paura di fallire può offrire sollievo nel breve periodo, ma nel tempo può rafforzare i dubbi e ridurre le opportunità di costruire esperienze diverse."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Da che cosa può essere influenzata",
                                    List.of(
                                            "L'autostima può risentire dei messaggi ricevuti durante la crescita, delle aspettative proprie e altrui, delle relazioni, del confronto sociale, dello stress e di eventi difficili come perdite, malattie o cambiamenti importanti. Non esiste però una causa unica valida per tutti.",
                                            "Gli studi longitudinali la descrivono come relativamente stabile, ma non immutabile: può evolvere nel corso della vita e cambiare attraverso nuove esperienze, relazioni e modi diversi di interpretare sé stessi."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Strumenti e supporto",
                                    "Come costruire un rapporto più equilibrato con sé",
                                    List.of(
                                            "Può essere utile osservare le convinzioni negative automatiche e verificare se descrivono davvero l'intera situazione; riconoscere qualità e progressi senza negare le difficoltà; rivolgersi a sé con la stessa misura che si userebbe con una persona cara; esercitare confini e assertività; affrontare obiettivi realistici per passi graduali.",
                                            "Non si tratta di imporsi pensieri positivi o di eliminare ogni insicurezza. Se autocritica, vergogna, evitamento o bisogno di approvazione provocano sofferenza persistente o limitano scelte e relazioni, un confronto con uno psicologo o psicoterapeuta può aiutare a comprenderne le origini e i meccanismi."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, valore personale e autoaccettazione, fiducia ed espressione dei bisogni, risposta a errori e critiche, confronto e bisogno di approvazione. Non misura il valore della persona e non costituisce una valutazione clinica.",
                    List.of(
                            new GuideReference(
                                    "Rosenberg Self-Esteem Scale — University of Maryland",
                                    "https://socy.umd.edu/about-us/rosenberg-self-esteem-scale",
                                    "Riferimento per il costrutto di autostima globale e per considerare insieme valutazioni positive di sé e tendenze all'autosvalutazione."
                            ),
                            new GuideReference(
                                    "The Development of Self-Esteem — Orth e Robins",
                                    "https://doi.org/10.1177/0963721414547414",
                                    "Revisione di studi longitudinali consultata per definire l'autostima come valutazione soggettiva del proprio valore, distinta da capacità oggettive e narcisismo, e relativamente stabile ma modificabile nel tempo."
                            ),
                            new GuideReference(
                                    "Raising low self-esteem — NHS",
                                    "https://www.nhs.uk/mental-health/self-help/tips-and-support/raise-low-self-esteem/",
                                    "Riferimento per oscillazioni comuni, possibili influenze, evitamento, autocritica, assertività, obiettivi graduali e possibilità di ricorrere a un supporto professionale."
                            ),
                            new GuideReference(
                                    "On the factor structure of the Rosenberg Self-Esteem Scale — Alessandri e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/25580614/",
                                    "Studio con campioni italiano ed europei consultato per struttura e invarianza della misura di Rosenberg; non fornisce norme o soglie applicabili a Spazio Test."
                            )
                    )
            ),
            new InformationGuide(
                    "dipendenza-affettiva",
                    "dipendenza-affettiva",
                    "Dipendenza affettiva",
                    "Dipendenza affettiva: segnali, confini e autonomia",
                    "Dipendenza affettiva: segnali e relazioni | Spazio Test",
                    "Una guida concisa alla dipendenza affettiva: paura dell'abbandono, rassicurazione, autonomia, confini, attaccamento e differenza tra difficoltà relazionali e violenza.",
                    "L'espressione “dipendenza affettiva” viene usata per descrivere dinamiche in cui la relazione assume una centralità rigida e il bisogno dell'altra persona limita autonomia, benessere o capacità di proteggere i propri confini. Non esiste però una definizione diagnostica condivisa: è più utile osservare esperienze, contesto e conseguenze concrete che applicare un'etichetta.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cosa si intende per dipendenza affettiva",
                                    List.of(
                                            "Nella letteratura si incontrano espressioni come dipendenza affettiva, dipendenza emotiva e love addiction. Il costrutto è ancora eterogeneo e non dispone di criteri diagnostici ufficiali condivisi; un questionario online non può quindi stabilire la presenza di un disturbo.",
                                            "Il termine può comunque aiutare a parlare di pattern relazionali che persistono nonostante sofferenza, rinunce o conseguenze negative, purché non trasformi il normale bisogno di vicinanza in qualcosa di automaticamente patologico."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Relazioni",
                                    "Legame e interdipendenza non significano perdere sé stessi",
                                    List.of(
                                            "Avere bisogno di affetto, sostegno e rassicurazione è parte delle relazioni. In un'interdipendenza equilibrata, vicinanza e reciprocità possono convivere con interessi, amicizie, decisioni e spazi personali.",
                                            "Una dinamica può diventare limitante quando la paura della separazione domina le scelte, il proprio valore dipende quasi interamente dall'altro o preservare il rapporto richiede rinunce continue e la soppressione dei propri bisogni."
                                    ),
                                    List.of(
                                            "Cercare conferme continue o vivere distanze e ritardi come segnali di abbandono.",
                                            "Ridurre interessi, amicizie, obiettivi o autonomia per mantenere la relazione.",
                                            "Faticare a dire no, esprimere bisogni o riconoscere mancanze di reciprocità.",
                                            "Sentire che umore, stabilità e identità dipendono quasi interamente dalla disponibilità dell'altra persona."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Il ruolo dell'attaccamento",
                                    List.of(
                                            "Una revisione sistematica ha rilevato una relazione tra le misure di love addiction e l'attaccamento ansioso, che può comprendere paura dell'abbandono e intenso bisogno di rassicurazione. Si tratta di un'associazione statistica, non di una causa certa né di una diagnosi individuale.",
                                            "Storia personale, autostima, esperienze relazionali, stress e caratteristiche della relazione attuale possono intrecciarsi in modi diversi. Riconoscere un pattern di attaccamento non significa essere destinati a ripeterlo: nuove esperienze e un percorso di consapevolezza possono modificare il modo di vivere i legami."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Sicurezza",
                                    "Dipendenza relazionale, controllo e violenza non sono la stessa cosa",
                                    List.of(
                                            "Paura della perdita e difficoltà nei confini descrivono l'esperienza di chi risponde; minacce, umiliazioni, isolamento imposto, controllo economico, sessuale o digitale e violenza descrivono invece comportamenti dell'altra persona. Nessuna dinamica di dipendenza rende qualcuno responsabile della violenza che subisce.",
                                            "Questo questionario non può riconoscere o valutare una situazione abusante. Se temi per la tua sicurezza, cerca supporto senza esporti a ulteriori rischi. In Italia il 1522 offre gratuitamente, ogni giorno e a ogni ora, ascolto e orientamento alle donne vittime di violenza e stalking; in caso di pericolo immediato contatta i servizi di emergenza."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto",
                                    "Quando può essere utile chiedere un confronto",
                                    List.of(
                                            "Può essere utile parlare con uno psicologo o psicoterapeuta se la relazione occupa quasi tutto lo spazio mentale, provoca isolamento o rinunce importanti, rende difficile proteggere bisogni e confini oppure continua a causare sofferenza pur sembrando impossibile allontanarsi.",
                                            "Un percorso non serve ad assegnare colpe o a imporre una decisione sulla relazione. Può aiutare a comprendere i meccanismi coinvolti, rafforzare autonomia e rete personale, riconoscere i propri bisogni e costruire modi più flessibili e sicuri di stare in relazione."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, paura della separazione e bisogno di rassicurazione, autonomia e interessi personali, confini e reciprocità, regolazione emotiva e centralità della relazione. Non formula diagnosi, non definisce se una relazione sia sana e non rileva abusi o violenza.",
                    List.of(
                            new GuideReference(
                                    "I disturbi da addiction nelle dipendenze non legate a sostanze — Ministero della Salute",
                                    "https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_3313_allegato.pdf",
                                    "Documento del Consiglio Superiore di Sanità consultato per l'inquadramento della cosiddetta love addiction tra le dipendenze comportamentali emergenti e per le dinamiche di centralità, persistenza e conseguenze negative."
                            ),
                            new GuideReference(
                                    "Conceptualizing love addiction within the attachment perspective — Cavalli e colleghi",
                                    "https://pmc.ncbi.nlm.nih.gov/articles/PMC12284683/",
                                    "Revisione sistematica e meta-analisi consultata per l'assenza di una definizione condivisa, l'eterogeneità del costrutto e l'associazione tra love addiction e dimensioni dell'attaccamento insicuro."
                            ),
                            new GuideReference(
                                    "Problematic Love Behaviors: systematic review and meta-analysis — Cavalli e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/42029817/",
                                    "Sintesi recente consultata per distinguere dipendenza emotiva, love addiction e amore maniacale e descrivere correlati senza trasformare associazioni in cause o diagnosi individuali."
                            ),
                            new GuideReference(
                                    "Il 1522 — Dipartimento per le Pari Opportunità",
                                    "https://www.1522.eu/cose-1522/",
                                    "Riferimento istituzionale per il servizio pubblico gratuito di ascolto e orientamento dedicato alle donne vittime di violenza e stalking, attivo ogni giorno e a ogni ora."
                            )
                    )
            ),
            new InformationGuide(
                    "assertivita",
                    "assertivita",
                    "Assertività",
                    "Assertività: comunicare bisogni, limiti e opinioni",
                    "Assertività: significato ed esempi pratici | Spazio Test",
                    "Una guida concisa all'assertività: differenze tra comunicazione passiva, aggressiva e assertiva, confini, capacità di dire no, critiche e strategie pratiche.",
                    "L'assertività è la capacità di esprimere in modo chiaro e diretto opinioni, emozioni, bisogni e limiti, rispettando contemporaneamente i diritti e la dignità delle altre persone. Non significa ottenere sempre ciò che si vuole, evitare ogni conflitto o parlare in qualsiasi circostanza.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è l'assertività",
                                    List.of(
                                            "Essere assertivi significa rendere visibile il proprio punto di vista senza presentarlo come l'unico possibile. Include saper formulare richieste, rifiutare, esprimere disaccordo, chiedere chiarimenti e riconoscere sia i propri diritti sia quelli altrui.",
                                            "È un insieme di comportamenti che può essere appreso e allenato. Una persona può esprimersi con facilità in alcuni rapporti e bloccarsi in altri, soprattutto quando teme giudizio, conflitto, rifiuto o conseguenze concrete."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Stili comunicativi",
                                    "Passività, aggressività e assertività",
                                    List.of(
                                            "Nella comunicazione passiva i propri bisogni e limiti tendono a restare in secondo piano; questo può ridurre la tensione immediata, ma nel tempo favorire frustrazione, sovraccarico o risentimento.",
                                            "Nella comunicazione aggressiva il proprio obiettivo viene perseguito ignorando, svalutando o forzando l'altra persona. L'assertività cerca invece chiarezza e fermezza senza minacce, umiliazioni o pretese di controllo."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Le diverse forme dell'assertività",
                                    List.of(
                                            "Gli strumenti di ricerca sull'assertività mostrano che non esiste una sola abilità generale. Contano sia la probabilità di mettere in atto un comportamento sia il disagio provato nel farlo: si può riuscire a dire no sentendosi comunque molto in ansia, oppure sentirsi tranquilli ma scegliere di non intervenire."
                                    ),
                                    List.of(
                                            "Espressione: comunicare opinioni, preferenze, emozioni e bisogni in modo comprensibile.",
                                            "Confini: dire no, proteggere tempo e risorse e chiedere che un comportamento cambi.",
                                            "Confronto: affrontare disaccordi, dare o ricevere critiche e riconoscere un errore senza annullarsi o attaccare.",
                                            "Iniziativa: fare richieste, chiedere aiuto, iniziare una conversazione ed esprimere o accogliere apprezzamento."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Non parlare è sempre mancanza di assertività?",
                                    List.of(
                                            "No. Scegliere consapevolmente di rimandare una conversazione, non condividere un'informazione o non esporsi può essere appropriato. L'assertività comprende anche valutare obiettivi, momento, interlocutore e possibili conseguenze.",
                                            "Cultura, ruoli, dipendenza economica, gerarchie e sicurezza influenzano ciò che è possibile esprimere. In un contesto minaccioso o con un forte squilibrio di potere, adattarsi o evitare il confronto può essere una strategia protettiva e non una carenza personale."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Allenamento",
                                    "Strategie per comunicare in modo più assertivo",
                                    List.of(
                                            "Può aiutare descrivere il fatto concreto, parlare in prima persona, spiegare l'effetto che produce e formulare una richiesta specifica. Un rifiuto può essere breve e rispettoso, senza lunghe giustificazioni; se l'altra persona insiste, ripetere con calma lo stesso limite evita di entrare in una discussione infinita.",
                                            "È utile iniziare da situazioni gestibili, preparare le parole, osservare tono e postura e valutare dopo che cosa ha funzionato. Se paura del conflitto, difficoltà nei confini o reazioni aggressive compromettono relazioni e benessere, un confronto con uno psicologo o psicoterapeuta può aiutare ad allenare queste abilità in modo adatto al contesto."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, espressione di opinioni, bisogni ed emozioni, capacità di dire no e proteggere i confini, gestione di critiche e disaccordi, richieste, iniziativa e riconoscimento reciproco. Non misura valore, forza o coraggio e non costituisce una valutazione clinica.",
                    List.of(
                            new GuideReference(
                                    "A 30-Item Schedule for Assessing Assertive Behavior — Rathus",
                                    "https://doi.org/10.1016/S0005-7894(73)80120-0",
                                    "Riferimento storico per la valutazione comportamentale e situazionale dell'assertività e per esempi relativi a espressione, iniziativa, richieste e confronto interpersonale."
                            ),
                            new GuideReference(
                                    "Normative studies with the Scale for Interpersonal Behaviour — Arrindell e colleghi",
                                    "https://doi.org/10.1016/S0191-8869(98)00252-9",
                                    "Riferimento per la natura multidimensionale dell'assertività e per distinguere comportamento e disagio nelle aree di espressione, limiti, iniziativa e assertività positiva."
                            ),
                            new GuideReference(
                                    "Cross-cultural validity of the Scale for Interpersonal Behavior — Nota e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/21721362/",
                                    "Validazione su un ampio campione studentesco italiano consultata per struttura e differenze culturali rispetto al campione olandese; non viene usata per norme individuali."
                            ),
                            new GuideReference(
                                    "Improving Assertiveness — Centre for Clinical Interventions",
                                    "https://www.cci.health.wa.gov.au/en/Resources/Looking-After-Yourself/Assertiveness",
                                    "Risorsa istituzionale consultata per la definizione di comunicazione chiara e rispettosa, la distinzione tra stili comunicativi e le strategie pratiche su rifiuto, critiche e allenamento graduale."
                            )
                    )
            ),
            new InformationGuide(
                    "intelligenza-emotiva",
                    "intelligenza-emotiva",
                    "Intelligenza emotiva",
                    "Intelligenza emotiva: riconoscere, comprendere e regolare le emozioni",
                    "Intelligenza emotiva: cos'è e come svilupparla | Spazio Test",
                    "Una guida concisa all'intelligenza emotiva: significato, quattro aree del modello di abilità, differenze rispetto ai tratti personali e strategie pratiche.",
                    "L'intelligenza emotiva descrive la capacità di ragionare sulle emozioni e di usare le informazioni emotive nel pensiero. Nel modello di abilità comprende percezione, uso, comprensione e regolazione delle emozioni: aree collegate, ma non equivalenti a essere sempre calmi, socievoli o empatici.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è l'intelligenza emotiva",
                                    List.of(
                                            "Le emozioni forniscono informazioni su ciò che accade dentro di noi e nelle relazioni. L'intelligenza emotiva riguarda il modo in cui queste informazioni vengono riconosciute, interpretate e integrate nel ragionamento e nelle decisioni.",
                                            "Non sostituisce l'intelligenza generale e non coincide con personalità, autostima, ottimismo, gentilezza o maturità morale. Una persona può comprendere bene un'emozione e scegliere comunque un comportamento poco utile, perché sulle azioni influiscono anche obiettivi, abitudini, contesto e motivazione."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Modelli e misure",
                                    "Non esiste un unico modo di definire e misurare il costrutto",
                                    List.of(
                                            "Nella ricerca convivono modelli diversi. Il modello di abilità considera l'intelligenza emotiva una forma di ragionamento su emozioni e informazioni collegate; altri approcci includono invece competenze, disposizioni e tratti personali più ampi. Risultati ottenuti con strumenti basati su modelli differenti non sono quindi direttamente intercambiabili.",
                                            "Una prova di abilità propone problemi da risolvere e valuta le risposte secondo criteri definiti. Un questionario di autovalutazione, come quello di Spazio Test, descrive invece come la persona percepisce le proprie abitudini: è utile per riflettere, ma non fornisce un quoziente emotivo né una misura oggettiva delle capacità."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Le quattro aree",
                                    "Percepire, usare, comprendere e regolare le emozioni",
                                    List.of(
                                            "Il modello di abilità organizza il ragionamento emotivo in quattro aree. Non sono gradini rigidi e nella vita quotidiana vengono spesso utilizzate insieme."
                                    ),
                                    List.of(
                                            "Percezione: riconoscere segnali emotivi nelle proprie sensazioni, nei pensieri, nella voce, nelle espressioni e nel contesto, evitando di trattare una prima impressione come una certezza.",
                                            "Uso nel pensiero: considerare come gli stati emotivi orientano attenzione, priorità e prospettive e impiegare queste informazioni senza lasciare che decidano automaticamente al posto nostro.",
                                            "Comprensione: distinguere sfumature, possibili cause, combinazioni e cambiamenti delle emozioni, ampliando il vocabolario con cui vengono descritte.",
                                            "Regolazione: scegliere come rispondere alle emozioni proprie e altrui, modulandone espressione e intensità quando è utile. Regolare non significa reprimere ogni emozione spiacevole o controllare ciò che provano gli altri."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Riconoscere un'emozione non significa leggere la mente",
                                    List.of(
                                            "Espressioni, tono e comportamento possono avere più interpretazioni. Cultura, esperienza, caratteristiche individuali e neurodivergenze influenzano sia il modo di esprimere le emozioni sia quello di riconoscerle; con le altre persone, formulare un'ipotesi e verificarla con rispetto è più affidabile che presumere di sapere cosa provano.",
                                            "Stress, stanchezza, salute e condizioni di sicurezza possono rendere più difficile identificare o regolare ciò che si prova. Una difficoltà in un momento o in un contesto non definisce il valore della persona e non dimostra, da sola, una carenza stabile."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Allenamento",
                                    "Strategie per sviluppare consapevolezza e regolazione",
                                    List.of(
                                            "Può essere utile fermarsi e descrivere separatamente sensazioni fisiche, emozione, possibile causa, bisogno e impulso ad agire. Dare un nome più preciso a ciò che si prova, distinguere il sentimento dall'azione e osservare a posteriori l'effetto di una risposta aiuta a costruire alternative.",
                                            "Nelle relazioni, ascoltare, chiedere chiarimenti e restituire con parole proprie ciò che si è compreso permette di correggere interpretazioni affrettate. Per la regolazione si possono sperimentare pause, respirazione, movimento, riformulazione del problema, supporto sociale e pianificazione, valutando quale strategia è adatta alla situazione. Se le difficoltà emotive provocano sofferenza persistente o interferiscono con relazioni, studio o lavoro, può essere utile parlarne con uno psicologo o psicoterapeuta."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, la percezione delle proprie abitudini nelle quattro aree del modello: riconoscere le emozioni, utilizzarne le informazioni, comprenderne cause e cambiamenti e regolarne espressione e intensità. Non è una prova di abilità, non assegna un quoziente emotivo e non costituisce una valutazione clinica.",
                    List.of(
                            new GuideReference(
                                    "The Ability Model of Emotional Intelligence: Principles and Updates — Mayer, Caruso e Salovey",
                                    "https://doi.org/10.1177/1754073916639667",
                                    "Fonte primaria per la definizione dell'intelligenza emotiva come abilità, le quattro aree di percezione, uso, comprensione e regolazione e la distinzione tra prove di abilità e autovalutazioni."
                            ),
                            new GuideReference(
                                    "Emotional Intelligence: New Ability or Eclectic Traits? — Mayer, Salovey e Caruso",
                                    "https://doi.org/10.1037/0003-066X.63.6.503",
                                    "Approfondimento scientifico consultato per distinguere il modello di abilità dagli approcci che riuniscono tratti e qualità personali più ampie sotto la stessa etichetta."
                            ),
                            new GuideReference(
                                    "Construct validity of the Italian MSCEIT v2.0 — Curci e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/23536991/",
                                    "Validazione italiana consultata per struttura e distinzione tra abilità emotiva, intelligenza e personalità; il MSCEIT è una prova di abilità e non valida l'autovalutazione dell'app."
                            )
                    )
            ),
            new InformationGuide(
                    "perfezionismo",
                    "perfezionismo",
                    "Perfezionismo",
                    "Perfezionismo: quando gli standard diventano una pressione",
                    "Perfezionismo: cos'è e come gestirlo | Spazio Test",
                    "Una guida concisa al perfezionismo: differenza dagli standard elevati, paura degli errori, rigidità, procrastinazione e strategie pratiche.",
                    "Il perfezionismo non coincide semplicemente con ambizione, precisione o desiderio di migliorare. Diventa fonte di difficoltà quando gli standard sono rigidi e incessanti, il valore personale dipende soprattutto dai risultati e la ricerca della prestazione ideale continua nonostante stress, blocchi o rinunce.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è il perfezionismo",
                                    List.of(
                                            "La ricerca descrive il perfezionismo come un insieme di dimensioni personali e sociali. Possono essere presenti standard molto elevati, forte preoccupazione per gli errori, dubbi sulla qualità delle proprie azioni, bisogno di ordine e la percezione che gli altri si aspettino prestazioni impeccabili.",
                                            "Non è una diagnosi e non si manifesta allo stesso modo in ogni ambito. Una persona può essere molto esigente nel lavoro o nello studio e più flessibile nelle relazioni, nell'aspetto personale o nelle attività quotidiane."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Standard elevati e perfezionismo problematico non sono la stessa cosa",
                                    List.of(
                                            "Uno standard elevato può orientare l'impegno restando adattabile allo scopo, al tempo e alle risorse disponibili. Consente di imparare dagli errori, riconoscere un risultato adeguato, cambiare metodo e separare la qualità di una prestazione dal valore della persona.",
                                            "La pressione perfezionistica tende invece a trasformare preferenze e obiettivi in regole assolute: il risultato deve essere impeccabile, l'errore sembra dimostrare incapacità e concludere, delegare o mostrarsi prima di sentirsi completamente pronti diventa difficile. È il costo complessivo, non l'ambizione in sé, a rendere importante osservare il meccanismo."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Dimensioni",
                                    "Le diverse forme della pressione perfezionistica",
                                    List.of(
                                            "I principali modelli mostrano che non esiste un solo perfezionismo. Le dimensioni possono sovrapporsi, ma distinguerle aiuta a capire quale processo pesa maggiormente in una determinata situazione."
                                    ),
                                    List.of(
                                            "Standard e valore personale: obiettivi molto esigenti diventano il principale criterio con cui valutarsi.",
                                            "Errori, dubbi e autocritica: un'imprecisione viene interpretata come fallimento e il lavoro continua a sembrare incompleto o insufficiente.",
                                            "Aspettative sociali: si percepisce che approvazione, rispetto o appartenenza dipendano dal soddisfare richieste molto elevate degli altri.",
                                            "Ordine e controllo: pianificazione, dettagli, verifiche e procedure diventano rigidi, rendendo più difficile adattarsi, delegare o terminare.",
                                            "Perfezionismo rivolto agli altri: aspettative inflessibili verso le prestazioni altrui possono alimentare frustrazione, critica e tensioni nelle relazioni."
                                    )
                            ),
                            new GuideSection(
                                    "Ciclo",
                                    "Perché controllo e procrastinazione possono mantenere il problema",
                                    List.of(
                                            "Di fronte a uno standard rigido, il timore di sbagliare può portare a preparazione eccessiva, controlli ripetuti, ricerca continua di rassicurazioni oppure evitamento e rinvio. Queste strategie riducono temporaneamente l'incertezza, ma impediscono di verificare se un risultato meno che perfetto sarebbe stato comunque adeguato.",
                                            "Quando il compito riesce, il successo può essere attribuito proprio al controllo estremo e lo standard viene alzato ancora; quando qualcosa non riesce, l'errore sembra confermare la necessità di essere più severi. Per questo perfezionismo e procrastinazione possono convivere, anche se dall'esterno appaiono opposti."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Strategie",
                                    "Come rendere gli standard più flessibili",
                                    List.of(
                                            "Può aiutare definire in anticipo lo scopo del compito, un livello sufficientemente adeguato, il tempo disponibile e il numero di controlli. Piccoli esperimenti in situazioni sicure — consegnare un lavoro adeguato senza un'ultima revisione, delegare una parte o iniziare prima che tutte le condizioni siano ideali — permettono di confrontare le conseguenze temute con quelle reali.",
                                            "È utile trasformare le regole assolute in preferenze, valutare costi e benefici dello standard, includere risultati positivi e limiti reali nel giudizio e coltivare fonti di valore personale diverse dalla prestazione. L'obiettivo non è rinunciare alla qualità, ma scegliere quando l'impegno aggiuntivo è davvero utile. Se rigidità, autocritica o rinvii causano sofferenza persistente o interferiscono con salute, relazioni, studio o lavoro, un confronto con uno psicologo o psicoterapeuta può offrire un percorso adatto alla situazione."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, standard elevati e valore legato ai risultati, paura degli errori e dubbi, aspettative e giudizio percepiti, ordine e controllo. Descrive la frequenza di alcune dinamiche riferite dalla persona, ma non misura capacità o valore e non costituisce una valutazione clinica.",
                    List.of(
                            new GuideReference(
                                    "The dimensions of perfectionism — Frost e colleghi",
                                    "https://doi.org/10.1007/BF01172967",
                                    "Fonte primaria per la natura multidimensionale del perfezionismo e per le aree di standard personali, errori, dubbi, aspettative, critica, ordine e organizzazione."
                            ),
                            new GuideReference(
                                    "Perfectionism in the self and social contexts — Hewitt e Flett",
                                    "https://pubmed.ncbi.nlm.nih.gov/2027080/",
                                    "Fonte primaria per distinguere perfezionismo orientato verso di sé, rivolto agli altri e socialmente prescritto."
                            ),
                            new GuideReference(
                                    "Perfectionism Self-Help Resources — Centre for Clinical Interventions",
                                    "https://www.cci.health.wa.gov.au/resources/looking-after-yourself/perfectionism",
                                    "Risorsa istituzionale consultata per distinguere standard elevati utili e standard incessanti, comprendere il ciclo di mantenimento e proporre strategie graduali e concrete."
                            ),
                            new GuideReference(
                                    "Short Forms of the Multidimensional Perfectionism Scale in Italian samples — Lombardo e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/33835908/",
                                    "Confronto psicometrico italiano consultato per multidimensionalità e per i risultati misti sulla struttura delle forme brevi; nessuna soglia viene trasferita al test."
                            )
                    )
            ),
            new InformationGuide(
                    "ansia-sociale",
                    "ansia-sociale",
                    "Ansia sociale",
                    "Ansia sociale: comprendere paura del giudizio ed evitamento",
                    "Ansia sociale: sintomi e come affrontarla | Spazio Test",
                    "Una guida concisa all'ansia sociale: differenze dalla timidezza, paura del giudizio, sintomi, evitamento, ciclo di mantenimento e possibili aiuti.",
                    "L'ansia sociale riguarda una paura intensa di essere osservati, valutati, umiliati o rifiutati nelle interazioni e nelle situazioni di prestazione. Un certo disagio sociale è comune; diventa importante chiedere aiuto quando la paura persiste, causa forte sofferenza o limita relazioni, studio, lavoro e attività desiderate.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è l'ansia sociale",
                                    List.of(
                                            "La paura può comparire durante conversazioni, incontri con persone nuove, richieste, appuntamenti, colloqui, lezioni, riunioni o attività svolte sotto lo sguardo altrui. In alcune persone riguarda molti contesti; in altre emerge soprattutto quando devono parlare, esibirsi o essere valutate.",
                                            "Una diagnosi di disturbo d'ansia sociale non si basa su un singolo episodio o su un questionario online. Richiede una valutazione della durata, dell'intensità, della proporzione rispetto alla situazione, dell'evitamento e dell'impatto sulla vita, considerando anche possibili spiegazioni alternative o condizioni associate."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Manifestazioni",
                                    "Come può presentarsi prima, durante e dopo una situazione sociale",
                                    List.of(
                                            "Prima dell'evento possono comparire previsioni negative e immagini di ciò che potrebbe andare storto. Durante la situazione, l'attenzione può concentrarsi su voce, postura, parole e segnali fisici; rossore, tremore, sudorazione, nausea, battito accelerato o la sensazione di avere la mente vuota possono aumentare il timore che l'ansia sia visibile.",
                                            "Dopo, è possibile ripercorrere a lungo la conversazione selezionando pause, frasi o espressioni considerate sbagliate e sottovalutando i segnali neutri o positivi. Questo ripensamento non dimostra che l'interazione sia realmente andata male."
                                    ),
                                    List.of(
                                            "Giudizio: paura di apparire incompetenti, noiosi, strani, impacciati o visibilmente ansiosi.",
                                            "Interazione: difficoltà a iniziare o mantenere conversazioni, esprimere opinioni, fare richieste o conoscere persone.",
                                            "Prestazione: ansia nel parlare in pubblico, rispondere, sostenere un colloquio, essere osservati o diventare il centro dell'attenzione.",
                                            "Anticipazione ed evitamento: preoccupazione prolungata, rinuncia a opportunità, uscita anticipata e analisi critica successiva."
                                    )
                            ),
                            new GuideSection(
                                    "Ciclo",
                                    "Come evitamento e comportamenti protettivi mantengono la paura",
                                    List.of(
                                            "Evitare una situazione o usare accorgimenti per non farsi notare — parlare pochissimo, preparare ogni frase, controllare continuamente il proprio aspetto, restare al telefono o cercare sempre una via d'uscita — può ridurre l'ansia nell'immediato.",
                                            "Il sollievo, però, può rafforzare l'idea che senza quelle protezioni sarebbe accaduto qualcosa di grave. Inoltre l'attenzione rivolta a sé rende più difficile seguire lo scambio e raccogliere informazioni reali sulle reazioni altrui, mentre anticipazione e ripensamento mantengono disponibili soprattutto le interpretazioni negative."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Ansia sociale, timidezza e contesto non sono la stessa cosa",
                                    List.of(
                                            "La timidezza è una caratteristica comune e non implica necessariamente sofferenza o limitazioni. Anche introversione e preferenza per gruppi piccoli non sono disturbi: il punto non è quanto una persona sia socievole, ma quanta libertà conserva nel partecipare alle situazioni che per lei contano.",
                                            "Esperienze di esclusione, bullismo, discriminazione o ambienti realmente ostili possono rendere il timore del giudizio comprensibile e fondato. Trauma, depressione, altre forme d'ansia, neurodivergenze, differenze culturali o linguistiche e difficoltà comunicative possono inoltre produrre esperienze simili; una lettura professionale considera la persona e il contesto, non soltanto i sintomi."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Possibili aiuti",
                                    "Affrontare gradualmente ciò che conta",
                                    List.of(
                                            "Può essere utile osservare situazione, previsione temuta, segnali fisici, attenzione e comportamento adottato, distinguendo i fatti dalle interpretazioni. Suddividere una difficoltà in passi gestibili, riportare l'attenzione sulla conversazione e ridurre gradualmente un comportamento protettivo permette di raccogliere nuove informazioni, senza pretendere di eliminare l'ansia prima di agire.",
                                            "La terapia cognitivo-comportamentale specifica per l'ansia sociale e l'autoaiuto guidato basato sulla CBT sono interventi raccomandati; l'esposizione graduale può farne parte. Se la paura è intensa, è preferibile pianificare questi passi con un professionista, rispettando sicurezza e condizioni personali. Quando ansia o evitamento limitano la vita, uno psicologo, psicoterapeuta o medico può effettuare una valutazione completa e discutere le opzioni, incluse quelle farmacologiche quando appropriate."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, paura del giudizio e dell'imbarazzo, tensione nelle interazioni, situazioni di prestazione o osservazione e ciclo di anticipazione, protezione, evitamento e ripensamento. Descrive esperienze riferite dalla persona, ma non stabilisce la presenza di un disturbo d'ansia sociale e non sostituisce una valutazione professionale.",
                    List.of(
                            new GuideReference(
                                    "Social Anxiety Disorder: What You Need to Know — NIMH",
                                    "https://www.nimh.nih.gov/health/publications/social-anxiety-disorder-more-than-just-shyness",
                                    "Fonte istituzionale per definizione, situazioni temute, segnali fisici e cognitivi, evitamento, criteri considerati nella diagnosi e possibilità di trattamento."
                            ),
                            new GuideReference(
                                    "Social anxiety disorder: recognition, assessment and treatment — NICE CG159",
                                    "https://www.nice.org.uk/guidance/cg159",
                                    "Linea guida clinica consultata per valutazione di paura, evitamento e impatto, ciclo di attenzione e comportamenti protettivi e interventi raccomandati per gli adulti."
                            ),
                            new GuideReference(
                                    "Social anxiety (social phobia) — NHS",
                                    "https://www.nhs.uk/mental-health/conditions/social-anxiety/",
                                    "Risorsa del servizio sanitario britannico consultata per distinguere ansia sociale e timidezza, descrivere manifestazioni comuni e proporre primi passi graduali e opzioni di aiuto."
                            ),
                            new GuideReference(
                                    "Psychometric properties of the Italian Social Phobia Inventory — Gori e colleghi",
                                    "https://www.clinicalneuropsychiatry.org/download/assessing-social-anxiety-disorder-psychometric-properties-of-the-italian-social-phobia-inventory-i-spin/",
                                    "Studio peer-reviewed sulla versione italiana dello SPIN consultato per pertinenza linguistica e culturale; non autorizza a trasferire cut-off o proprietà al questionario dell'app."
                            )
                    )
            ),
            new InformationGuide(
                    "dinamiche-narcisistiche-coppia",
                    "dinamiche-narcisistiche-partner",
                    "Dinamiche narcisistiche nella coppia",
                    "Dinamiche narcisistiche nella coppia: osservare comportamenti e impatto",
                    "Dinamiche narcisistiche nella coppia: segnali | Spazio Test",
                    "Una guida alle dinamiche narcisistiche percepite nella coppia: reciprocità, bisogno di ammirazione, conflitti, confini, controllo e sicurezza.",
                    "L'espressione “partner narcisista” viene spesso usata per spiegare relazioni sbilanciate o dolorose, ma non consente di sapere se una persona abbia un disturbo narcisistico di personalità. È più affidabile osservare comportamenti ricorrenti, possibilità di confronto, rispetto dei confini e conseguenze della relazione sul benessere e sulla libertà di entrambi.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cosa si intende per dinamiche narcisistiche nella coppia",
                                    List.of(
                                            "Il termine può descrivere scambi in cui bisogno di centralità, ammirazione o trattamento speciale, scarsa considerazione dell'esperienza altrui e forte sensibilità alla critica condizionano ripetutamente la relazione. Possono emergere competizione, svalutazione, difficoltà a riconoscere responsabilità e poco spazio per bisogni diversi dai propri.",
                                            "Un singolo litigio, una reazione difensiva o un comportamento egoista non definiscono una persona. Contano frequenza, rigidità, presenza in contesti diversi, possibilità di riparare e cambiare e impatto concreto sulla relazione."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Diagnosi",
                                    "Si può capire se il partner è narcisista?",
                                    List.of(
                                            "No, non attraverso un questionario compilato dall'altra persona. Il disturbo narcisistico di personalità è un quadro clinico pervasivo caratterizzato da grandiosità, bisogno di ammirazione e difficoltà empatiche; la diagnosi considera criteri specifici, storia personale, funzionamento in più contesti e possibili spiegazioni alternative.",
                                            "Tratti come ricerca di conferme, egocentrismo o sensibilità alle critiche possono comparire anche senza un disturbo di personalità. Una meta-analisi ha rilevato un'associazione debole, a livello di gruppo, tra narcisismo e violenza di coppia: non consente di spiegare un singolo comportamento né di dedurre una diagnosi. Una valutazione attendibile richiede il coinvolgimento diretto della persona interessata e un professionista qualificato: la percezione del partner può descrivere la relazione, non stabilire una diagnosi."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Quali aspetti della relazione può essere utile osservare",
                                    List.of(
                                            "Concentrarsi su episodi verificabili e modelli ricorrenti riduce il rischio di trasformare un'etichetta in una spiegazione universale. Quattro aree aiutano a descrivere che cosa accade nella coppia."
                                    ),
                                    List.of(
                                            "Reciprocità: emozioni, bisogni e successi di entrambi ricevono ascolto e spazio, anche quando sono scomodi o differenti.",
                                            "Centralità e aspettative: attenzione, decisioni ed eccezioni ruotano stabilmente intorno a una sola persona oppure possono essere negoziate.",
                                            "Confronto e responsabilità: critiche e disaccordi portano a dialogo, scuse e cambiamenti concreti oppure a rabbia, disprezzo, ritiro, svalutazione e attribuzione costante della colpa.",
                                            "Confini e autonomia: limiti su privacy, corpo, denaro, tempo, amicizie e scelte vengono rispettati oppure pressioni e reazioni inducono a restringere la propria libertà."
                                    )
                            ),
                            new GuideSection(
                                    "Sicurezza",
                                    "Narcisismo, conflitto e abuso non sono sinonimi",
                                    List.of(
                                            "Una comunicazione difficile o una dinamica sbilanciata non dimostrano automaticamente abuso; allo stesso tempo, umiliazioni, minacce, coercizione sessuale, isolamento imposto, controllo economico o digitale e violenza non diventano meno importanti se il partner non ha alcuna diagnosi. Sono i comportamenti e il loro effetto sulla sicurezza e sulla libertà a richiedere attenzione.",
                                            "In una relazione abusante la responsabilità appartiene a chi mette in atto la violenza o il controllo. Se esprimere un limite o annunciare una separazione potrebbe aumentare il pericolo, è prudente non affrontare la situazione da soli e cercare prima un sostegno individuale e specializzato."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Orientamento",
                                    "Come riflettere sulla relazione e cercare supporto",
                                    List.of(
                                            "Può essere utile annotare episodi concreti, contesto, frequenza, reazione a un limite e ciò che accade dopo il conflitto: esistono ascolto, riparazione e cambiamenti osservabili oppure il modello si ripete? Considera anche come la relazione incide su autostima, lucidità, relazioni sociali, autonomia economica, possibilità di dire no e senso di sicurezza.",
                                            "Un confronto individuale con uno psicologo o psicoterapeuta può aiutare a ordinare le esperienze senza dover prima definire il partner. In presenza di controllo, minacce o violenza, è indicato rivolgersi a servizi specializzati e valutare la sicurezza prima di un confronto di coppia. In caso di pericolo immediato chiama il 112; in Italia il 1522 offre gratuitamente e in forma anonima, ogni giorno e a ogni ora, ascolto e orientamento alle donne vittime di violenza e stalking, anche tramite chat."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, la percezione di reciprocità ed empatia, centralità e bisogno di ammirazione, gestione di critiche e responsabilità, confini, controllo e impatto emotivo nella relazione. Non valuta direttamente il partner, non diagnostica un disturbo narcisistico di personalità e non può stabilire se una relazione sia abusante o sicura.",
                    List.of(
                            new GuideReference(
                                    "Narcissistic Personality Disorder — Merck Manual Professional Edition",
                                    "https://www.merckmanuals.com/professional/psychiatric-disorders/personality-disorders/narcissistic-personality-disorder-npd",
                                    "Riferimento clinico per il carattere pervasivo del disturbo, grandiosità, bisogno di ammirazione, empatia, sensibilità alla critica e necessità di una diagnosi professionale basata su criteri."
                            ),
                            new GuideReference(
                                    "Narcissism and Intimate Partner Violence: systematic review and meta-analysis — Oliver e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/37702183/",
                                    "Meta-analisi consultata per descrivere un'associazione debole a livello di gruppo e per evitare di usare il narcisismo come diagnosi del partner o spiegazione del singolo comportamento abusante."
                            ),
                            new GuideReference(
                                    "La violenza contro le donne dentro e fuori la famiglia — ISTAT 2025",
                                    "https://www.istat.it/wp-content/uploads/2025/11/La-violenza-contro-le-donne-dentro-e-fuori-la-famiglia_Anno-2025.pdf",
                                    "Fonte statistica italiana per descrivere isolamento, controllo, svalorizzazione, minacce e violenza economica come comportamenti osservabili nel contesto della coppia."
                            ),
                            new GuideReference(
                                    "Violence against women — World Health Organization",
                                    "https://www.who.int/news-room/fact-sheets/detail/violence-against-women",
                                    "Fonte istituzionale per distinguere i tratti di personalità dalla violenza del partner, che può includere aggressione, coercizione sessuale, abuso psicologico e comportamenti di controllo."
                            ),
                            new GuideReference(
                                    "Il 1522 — Dipartimento per le Pari Opportunità",
                                    "https://www.1522.eu/cose-1522/",
                                    "Riferimento istituzionale per il servizio pubblico gratuito e anonimo di ascolto e orientamento dedicato alle donne vittime di violenza e stalking, attivo ogni giorno e a ogni ora."
                            )
                    )
            ),
            new InformationGuide(
                    "ansia-generalizzata",
                    "ansia-generalizzata",
                    "Ansia generalizzata",
                    "Ansia generalizzata: comprendere preoccupazione e tensione persistenti",
                    "Ansia generalizzata: sintomi e cosa fare | Spazio Test",
                    "Una guida concisa all'ansia generalizzata: differenze dalla normale preoccupazione, sintomi fisici e cognitivi, sonno e possibili forme di aiuto.",
                    "Preoccuparsi è una risposta comune davanti a problemi e incertezze. Nell'ansia generalizzata, però, la preoccupazione tende a riguardare molti ambiti, a presentarsi con frequenza, a essere difficile da controllare e ad accompagnarsi a tensione, affaticamento o difficoltà quotidiane.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è l'ansia generalizzata",
                                    List.of(
                                            "L'ansia generalizzata è caratterizzata da preoccupazione e ansia eccessive su diversi aspetti della vita, come salute, lavoro, relazioni, denaro o responsabilità. I pensieri possono passare rapidamente da un tema all'altro e risultare difficili da interrompere anche quando non esiste un pericolo immediato.",
                                            "Una diagnosi non dipende da un singolo sintomo o da un questionario online. Un professionista considera nel complesso durata e frequenza — in genere ansia presente per gran parte del tempo per almeno sei mesi — difficoltà di controllo, sintomi associati, impatto sul funzionamento e possibili spiegazioni alternative, comprese condizioni fisiche o altre difficoltà psicologiche."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Preoccupazione utile e preoccupazione difficile da controllare",
                                    List.of(
                                            "Una preoccupazione può essere utile quando segnala un problema concreto, porta a individuare un'azione possibile e si riduce dopo averla pianificata. Diventa più problematica quando è sproporzionata, continua a generare scenari ipotetici e ricerca di certezza senza arrivare a una conclusione praticabile.",
                                            "Nell'ansia generalizzata la mente può tentare di prevenire ogni esito negativo attraverso anticipazione, controllo o preparazione continua. Non è mancanza di volontà: il sollievo cercato tende a essere breve e l'incertezza riapre presto una nuova catena di domande e timori."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Manifestazioni",
                                    "Pensieri, corpo e risorse quotidiane",
                                    List.of(
                                            "L'ansia generalizzata non riguarda soltanto i pensieri. Può coinvolgere attivazione fisica, qualità del sonno, energia e capacità di concentrarsi, con combinazioni e intensità diverse da persona a persona."
                                    ),
                                    List.of(
                                            "Preoccupazione: timori su più ambiti, sequenze di scenari negativi, bisogno di rassicurazioni o difficoltà a tollerare di non sapere.",
                                            "Attivazione: irrequietezza, sensazione di essere sul chi va là, tensione muscolare, mal di testa o disturbi gastrointestinali.",
                                            "Risorse: affaticamento, irritabilità, indecisione e difficoltà a mantenere l'attenzione quando molta energia è assorbita dai timori.",
                                            "Impatto: difficoltà ad addormentarsi o restare addormentati, rinvii, evitamenti e minore presenza nel lavoro, nello studio o nelle relazioni."
                                    )
                            ),
                            new GuideSection(
                                    "Ciclo",
                                    "Come può mantenersi il ciclo della preoccupazione",
                                    List.of(
                                            "Un dubbio può attivare scenari del tipo «e se...?», seguiti da analisi ripetute, controlli, rassicurazioni o evitamento. Queste risposte possono ridurre temporaneamente la tensione, ma anche rendere più credibile l'idea che l'incertezza sia pericolosa e debba essere eliminata prima di agire.",
                                            "Distinguere un problema attuale da uno ipotetico aiuta a orientarsi: per il primo si può definire un passo concreto, realistico e circoscritto; per il secondo può essere più utile riconoscere che non esiste un'azione risolutiva nel presente e riportare gradualmente l'attenzione a ciò che si sta facendo."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Orientamento",
                                    "Strategie quotidiane e quando chiedere aiuto",
                                    List.of(
                                            "Può essere utile annotare situazione, pensiero, intensità e risposta adottata, separare ciò che è modificabile da ciò che non lo è e trasformare i problemi concreti in passi limitati. Sonno regolare, movimento, tecniche di rilassamento o mindfulness e riduzione dell'eccesso di caffeina possono sostenere la gestione dell'ansia, ma non sostituiscono un trattamento quando la difficoltà è significativa.",
                                            "Se preoccupazione, tensione o sonno limitano stabilmente la vita quotidiana, è indicato parlarne con il medico o con un professionista della salute mentale. La terapia cognitivo-comportamentale è tra gli interventi più studiati; eventuali farmaci richiedono una valutazione medica individuale. Sintomi fisici nuovi, intensi o insoliti non vanno attribuiti automaticamente all'ansia e meritano un confronto sanitario."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, ampiezza e controllabilità della preoccupazione, tensione e attivazione, concentrazione, affaticamento, irritabilità, sonno e interferenza nella vita quotidiana negli ultimi sei mesi. Non formula una diagnosi e non permette di distinguere da solo ansia generalizzata, stress, altre difficoltà psicologiche o condizioni fisiche.",
                    List.of(
                            new GuideReference(
                                    "Generalized Anxiety Disorder: What You Need to Know — NIMH",
                                    "https://www.nimh.nih.gov/health/publications/generalized-anxiety-disorder-gad",
                                    "Fonte istituzionale per definizione, differenza dalla normale preoccupazione, sintomi cognitivi e fisici, durata e impatto considerati nella diagnosi e possibilità di trattamento e supporto."
                            ),
                            new GuideReference(
                                    "Generalised anxiety disorder and panic disorder in adults: management — NICE CG113",
                                    "https://www.nice.org.uk/guidance/cg113",
                                    "Linea guida clinica consultata per valutazione di gravità, sofferenza e compromissione funzionale e per gli interventi psicologici e sanitari raccomandati negli adulti."
                            ),
                            new GuideReference(
                                    "Generalised anxiety disorder (GAD) — NHS",
                                    "https://www.nhs.uk/mental-health/conditions/generalised-anxiety-disorder-gad/",
                                    "Risorsa del servizio sanitario britannico consultata per manifestazioni comuni, criteri considerati nella valutazione, opzioni di aiuto e accorgimenti quotidiani prudenti."
                            ),
                            new GuideReference(
                                    "Psychometric properties of the GAD-7 in an Italian population — Bolgeo e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/37149049/",
                                    "Studio italiano su struttura e invarianza del GAD-7; il campione cardiologico e prevalentemente maschile limita la generalizzazione e non fornisce soglie per il questionario dell'app."
                            )
                    )
            ),
            new InformationGuide(
                    "umore-depresso",
                    "umore-depresso",
                    "Umore depresso e sintomi depressivi",
                    "Umore depresso e sintomi depressivi: come riconoscerli",
                    "Depressione: sintomi e segnali da conoscere | Spazio Test",
                    "Una guida a umore depresso e sintomi della depressione: perdita di interesse, energia, sonno, pensieri, funzionamento, trattamenti e richiesta di aiuto.",
                    "Sentirsi tristi o scarichi in alcuni periodi fa parte dell'esperienza umana. Un quadro depressivo coinvolge invece un cambiamento più persistente dell'umore o della capacità di provare interesse e piacere, insieme ad altri sintomi che possono incidere sulla vita quotidiana.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Tristezza, umore depresso e depressione non sono la stessa cosa",
                                    List.of(
                                            "Tristezza e calo dell'umore possono comparire durante stress, perdite o cambiamenti e spesso si modificano con il tempo e il contesto. In un episodio depressivo, umore depresso oppure perdita di interesse o piacere sono presenti per gran parte del giorno, quasi ogni giorno, per almeno due settimane, insieme ad altri sintomi e a sofferenza o difficoltà nel funzionamento.",
                                            "Un singolo segnale o un questionario online non permettono di formulare una diagnosi. La valutazione considera frequenza, durata, intensità e impatto, storia personale, lutti e condizioni di vita, oltre a possibili cause mediche, effetti di farmaci o sostanze e altre condizioni psicologiche."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Manifestazioni",
                                    "Come possono presentarsi i sintomi depressivi",
                                    List.of(
                                            "La depressione non si manifesta nello stesso modo in tutte le persone e non coincide necessariamente con una tristezza evidente. Irritabilità, distacco, rallentamento o sintomi fisici possono essere più riconoscibili del tono dell'umore."
                                    ),
                                    List.of(
                                            "Umore e piacere: tristezza, vuoto, irritabilità, distacco oppure minore interesse e difficoltà a provare piacere.",
                                            "Energia e attivazione: stanchezza, rallentamento, minore motivazione e grande sforzo anche per attività semplici o di cura personale.",
                                            "Pensieri: colpa eccessiva, autosvalutazione, senso di inutilità o di essere un peso e visione del futuro senza possibilità di cambiamento.",
                                            "Funzionamento: cambiamenti nel sonno o nell'appetito, difficoltà di concentrazione e decisione, ritiro e interferenza con lavoro, studio, relazioni o responsabilità."
                                    )
                            ),
                            new GuideSection(
                                    "Valutazione",
                                    "Perché è importante osservare il quadro completo",
                                    List.of(
                                            "Sintomi simili possono accompagnare un lutto, stress prolungato, dolore cronico, disturbi del sonno o condizioni mediche; possono inoltre essere influenzati da farmaci, alcol o altre sostanze. Questo non rende la sofferenza meno reale, ma mostra perché è utile evitare autodiagnosi e considerare spiegazioni e bisogni diversi.",
                                            "È importante riferire al professionista anche eventuali periodi passati di umore insolitamente elevato o molto irritabile, forte aumento di energia o attività, ridotto bisogno di dormire, pensieri accelerati o comportamenti impulsivi. Queste esperienze possono orientare verso un quadro differente e incidere sulle scelte di trattamento."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Primi passi",
                                    "Ridurre il ritiro senza trasformare tutto in una prova",
                                    List.of(
                                            "Quando energia e piacere diminuiscono, è naturale ridurre attività e contatti. Nel tempo, però, il ritiro può lasciare ancora meno occasioni di movimento, relazione, efficacia o significato e contribuire al mantenimento del calo dell'umore. Non è pigrizia né mancanza di volontà.",
                                            "Può aiutare scegliere un'azione molto piccola e realistica, come alzarsi a un orario sostenibile, fare una breve passeggiata, consumare un pasto regolare o contattare una persona fidata. Annotare sintomi, durata, sonno, appetito e impatto può facilitare il confronto con un professionista. Questi accorgimenti possono sostenere, ma non sostituiscono una cura quando i sintomi persistono o limitano la vita."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aiuto e sicurezza",
                                    "Trattamenti disponibili e segnali da non affrontare da soli",
                                    List.of(
                                            "La depressione è trattabile. Gli interventi possono comprendere psicoterapia, strategie guidate e, quando indicato, farmaci prescritti e monitorati da un medico; la scelta dipende da gravità, storia, preferenze e condizioni della persona. È opportuno chiedere aiuto se i sintomi durano, peggiorano o interferiscono con attività, relazioni o cura di sé.",
                                            "Pensieri di morte, di non voler vivere o di farsi del male richiedono attenzione indipendentemente dal risultato di qualsiasi test. Se temi un pericolo immediato o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso e, se possibile, resta con una persona di fiducia. Se non c'è un pericolo immediato ma questi pensieri sono presenti, parlane al più presto con un medico o un professionista della salute mentale e con qualcuno di cui ti fidi."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, umore e capacità di provare piacere, energia e attivazione, pensieri su di sé e sul futuro, sonno, appetito, concentrazione e funzionamento nelle ultime due settimane. Non formula una diagnosi e non valuta pensieri suicidari o rischio di autolesionismo: un risultato basso non esclude la necessità di chiedere aiuto.",
                    List.of(
                            new GuideReference(
                                    "Depressive disorder (depression) — World Health Organization",
                                    "https://www.who.int/news-room/fact-sheets/detail/depression",
                                    "Fonte istituzionale per differenza dalle comuni variazioni dell'umore, sintomi, durata, impatto sul funzionamento, trattabilità, strategie di cura di sé e indicazioni di sicurezza."
                            ),
                            new GuideReference(
                                    "Depression — National Institute of Mental Health",
                                    "https://www.nimh.nih.gov/health/publications/depression",
                                    "Fonte istituzionale per varietà delle manifestazioni, perdita di interesse, energia, colpa, sonno, appetito, possibili cause alternative, valutazione e trattamenti."
                            ),
                            new GuideReference(
                                    "Depression in adults — NHS",
                                    "https://www.nhs.uk/mental-health/conditions/depression-in-adults/overview/",
                                    "Risorsa del servizio sanitario britannico consultata per distinguere il calo temporaneo dell'umore dalla depressione e descrivere richiesta di aiuto, supporto e opzioni di trattamento."
                            ),
                            new GuideReference(
                                    "Il numero 118 e il Numero di emergenza unico europeo (112) — Ministero della Salute",
                                    "https://www.salute.gov.it/new/it/tema/112-118-e-pronto-soccorso/il-numero-118-e-il-numero-di-emergenza-unico-europeo-112/",
                                    "Riferimento istituzionale italiano per il ricorso immediato ai servizi di emergenza e al Pronto Soccorso quando la persona è in pericolo o non si sente al sicuro."
                            ),
                            new GuideReference(
                                    "Consensus sulle terapie psicologiche per ansia e depressione — ISS",
                                    "https://www.iss.it/documents/20126/0/Consensus_1_2022_IT.pdf",
                                    "Documento istituzionale consultato per il contesto italiano dell'accesso e degli interventi psicologici; non sostiene lo scoring o la validità del questionario."
                            ),
                            new GuideReference(
                                    "Psychometric properties of the PHQ-9 in an Italian population — Bolgeo e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/39932691/",
                                    "Studio italiano su struttura e invarianza del PHQ-9 in persone con cardiopatia; il campione clinico specifico impedisce di trasferire norme e cut-off alla popolazione generale o a Spazio Test."
                            )
                    )
            ),
            new InformationGuide(
                    "people-pleasing",
                    "people-pleasing",
                    "People pleasing e bisogno di approvazione",
                    "People pleasing: bisogno di approvazione, confini e reciprocità",
                    "People pleasing: segnali e confini | Spazio Test",
                    "Una guida al people pleasing: differenze dalla gentilezza, bisogno di approvazione, difficoltà a dire no, autosilenziamento e confini più sostenibili.",
                    "“People pleasing” descrive l'abitudine a dare priorità alla soddisfazione altrui per evitare disapprovazione, rifiuto o conflitto, anche quando questo richiede di nascondere bisogni e opinioni o superare i propri limiti. Non è una diagnosi né un'identità fissa.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è il people pleasing",
                                    List.of(
                                            "People pleaser è un'espressione comune, non una categoria clinica. Può indicare un insieme di comportamenti come cercare conferme, dire sì controvoglia, adattare molto il proprio modo di mostrarsi, assumersi rapidamente la colpa o sentirsi responsabili delle emozioni degli altri.",
                                            "Nella ricerca, un concetto vicino è la “comunione non mitigata”: una focalizzazione sugli altri a esclusione di sé, distinta dalla positiva disponibilità alla cura perché comprende sovracoinvolgimento, dipendenza dalla valutazione esterna e trascuratezza dei propri bisogni."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Gentilezza, cura e compiacenza non sono la stessa cosa",
                                    List.of(
                                            "Aiutare, collaborare e fare sacrifici può essere libero, coerente con i propri valori e importante per una relazione. La cura tende a essere sostenibile quando lascia spazio a scelta, reciprocità, riposo, possibilità di chiedere aiuto e libertà di esprimere un limite senza temere di perdere automaticamente il legame.",
                                            "La stessa azione può avere significati diversi: dire sì perché lo si desidera non equivale a farlo perché il senso di valore dipende dall'approvazione o perché un rifiuto sembra intollerabile. È quindi utile osservare motivazione, libertà percepita, costo personale e risposta dell'altra persona ai propri bisogni."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Quali dinamiche può essere utile osservare",
                                    List.of(
                                            "Il people pleasing può comparire in alcune relazioni e non in altre. Descrivere il meccanismo concreto è più utile che applicarsi un'etichetta generale."
                                    ),
                                    List.of(
                                            "Approvazione: il disappunto altrui viene vissuto come possibile rifiuto e la valutazione di sé dipende molto dalle conferme ricevute.",
                                            "Confini: dire no, mantenere una decisione o proteggere tempo, riposo e priorità suscita colpa, ansia o lunghe giustificazioni.",
                                            "Autosilenziamento: opinioni, rabbia, delusione o richieste vengono nascoste per preservare armonia e immagine positiva.",
                                            "Sovraresponsabilità: ci si sente incaricati di anticipare bisogni e risolvere emozioni o problemi altrui, fino a trascurarsi e provare esaurimento o risentimento."
                                    )
                            ),
                            new GuideSection(
                                    "Ciclo",
                                    "Come può mantenersi la ricerca di approvazione",
                                    List.of(
                                            "Una richiesta o un segnale di disappunto può attivare il timore di deludere. Dire subito sì, scusarsi, tacere o intervenire per sistemare la situazione riduce la tensione nel breve periodo e talvolta porta approvazione; questo sollievo può rinforzare l'idea che il legame dipenda dal continuare ad adattarsi.",
                                            "Nel tempo possono aumentare sovraccarico, distanza dai propri bisogni e risentimento. Se poi la fatica viene interpretata come egoismo o insufficienza, la persona può cercare ancora più conferme e disponibilità, invece di riconoscere che il modello è diventato poco sostenibile."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Pratica",
                                    "Costruire più scelta e confini sostenibili",
                                    List.of(
                                            "Un primo passo è creare una pausa prima di rispondere: «Controllo e ti faccio sapere» permette di valutare desiderio, energie, priorità e conseguenze reali. Si può iniziare da situazioni a basso rischio, con risposte brevi e rispettose come «Non riesco questa volta» oppure «Posso aiutarti in questo modo, non in quello».",
                                            "Esprimere un'opinione, fare una richiesta o tollerare un piccolo disappunto offre informazioni sulla relazione. Un confine non garantisce che l'altro sia contento e non serve a controllarne la reazione: chiarisce ciò che si è disponibili a fare e ciò che si farà per proteggere il proprio limite. Procedere gradualmente è più utile che trasformare ogni interazione in una prova di assertività."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Potere, sicurezza e richiesta di supporto",
                                    List.of(
                                            "Cultura, ruoli familiari e di cura, gerarchie lavorative, dipendenza economica, disabilità e altre differenze di potere influenzano quanto sia realmente possibile rifiutare o negoziare. Adattarsi in una situazione minacciosa può essere una strategia di protezione, non una carenza personale da correggere con maggiore fermezza.",
                                            "Non è disponibile una validazione italiana consolidata del people pleasing come costrutto unitario: le fonti descrivono costrutti vicini, come comunione non mitigata e autosilenziamento, sensibili a genere e cultura. Se porre un limite può provocare minacce, controllo o violenza, è prudente cercare prima un sostegno individuale e specializzato. In Italia, in caso di pericolo immediato chiama il 112; il 1522 offre gratuitamente e in forma anonima ascolto e orientamento alle donne vittime di violenza e stalking. Se invece il modello causa ansia, esaurimento, risentimento o relazioni poco reciproche, uno psicologo o psicoterapeuta può aiutare a comprenderne funzioni e contesto e ad ampliare gradualmente le possibilità di scelta."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, bisogno di approvazione e paura del rifiuto, difficoltà a dire no e mantenere confini, autosilenziamento nel conflitto e sovraresponsabilità verso gli altri. Non formula una diagnosi, non misura quanto una persona sia gentile e non può stabilire da solo se un comportamento sia libero, imposto dal ruolo o necessario per la sicurezza.",
                    List.of(
                            new GuideReference(
                                    "Distinctions of unmitigated communion from communion: self-neglect and overinvolvement with others — Fritz e Helgeson",
                                    "https://pubmed.ncbi.nlm.nih.gov/9686454/",
                                    "Studio di riferimento per distinguere la cura positiva degli altri dalla focalizzazione sugli altri a esclusione di sé e per i legami con valutazione esterna, sovracoinvolgimento, trascuratezza di sé e disagio."
                            ),
                            new GuideReference(
                                    "A theory of unmitigated communion — Helgeson e Fritz",
                                    "https://pubmed.ncbi.nlm.nih.gov/15647153/",
                                    "Riferimento teorico per caratteristiche cognitive e comportamentali della comunione non mitigata e possibili implicazioni per il benessere psicologico e fisico."
                            ),
                            new GuideReference(
                                    "Self-silencing and women's health: a review — Maji e Dixit",
                                    "https://pubmed.ncbi.nlm.nih.gov/30518269/",
                                    "Revisione consultata per autosilenziamento, relazioni, genere e cultura; non viene presentata come validazione italiana del people pleasing né come prova causale."
                            ),
                            new GuideReference(
                                    "Improving Assertiveness — Centre for Clinical Interventions",
                                    "https://www.cci.health.wa.gov.au/Resources/Looking-After-Yourself/Assertiveness",
                                    "Risorsa clinico-educativa consultata per comunicazione chiara e rispettosa, difficoltà a dire no, pensieri che ostacolano i limiti e pratica assertiva adattata al contesto."
                            ),
                            new GuideReference(
                                    "Il 1522 — Dipartimento per le Pari Opportunità",
                                    "https://www.1522.eu/cose-1522/",
                                    "Riferimento istituzionale per il servizio pubblico gratuito e anonimo di ascolto e orientamento dedicato alle donne vittime di violenza e stalking."
                            )
                    )
            ),
            new InformationGuide(
                    "sindrome-impostore",
                    "sindrome-impostore",
                    "Sindrome dell'impostore",
                    "Sindrome dell'impostore: comprendere il fenomeno e i suoi effetti",
                    "Sindrome dell'impostore: cos'è e segnali | Spazio Test",
                    "Una guida alla sindrome dell'impostore: difficoltà a riconoscere i successi, paura di essere smascherati, perfezionismo, contesto e strategie utili.",
                    "La cosiddetta sindrome dell'impostore descrive il vissuto di non meritare risultati, ruoli o riconoscimenti e il timore che gli altri scoprano una presunta incompetenza, anche quando esistono prove concrete di capacità. Nella ricerca si parla più spesso di fenomeno dell'impostore, perché non è una diagnosi.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è il fenomeno dell'impostore",
                                    List.of(
                                            "Il fenomeno dell'impostore comprende difficoltà a interiorizzare i successi, attribuzione dei risultati a fortuna, circostanze, aiuto o sforzo eccezionale e paura di non riuscire a ripetere la prestazione. Complimenti e risultati positivi possono dare sollievo senza modificare stabilmente la percezione della propria competenza.",
                                            "Non è un disturbo riconosciuto né una categoria diagnostica. Gli strumenti disponibili misurano esperienze soggettive con definizioni e proprietà differenti; un punteggio non stabilisce quanto una persona sia competente, preparata o adatta a un ruolo."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Dubbio realistico e vissuto dell'impostore non coincidono",
                                    List.of(
                                            "Dubbi, bisogno di imparare e richiesta di feedback sono appropriati quando un compito è nuovo, le aspettative sono poco chiare o mancano conoscenze necessarie. Riconoscere una lacuna concreta permette di cercare informazioni, formazione o supervisione e non implica svalutare l'intera competenza personale.",
                                            "Nel vissuto dell'impostore, invece, una normale incertezza viene facilmente interpretata come prova di non meritare il ruolo, mentre successi, qualifiche e riscontri vengono esclusi o spiegati soltanto con cause esterne. Sentirsi competenti e possedere competenze non sono la stessa cosa: entrambi vanno valutati con evidenze specifiche e contestuali."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Come può manifestarsi il fenomeno dell'impostore",
                                    List.of(
                                            "Le esperienze possono concentrarsi in un solo contesto o comparire soprattutto durante passaggi di ruolo, maggiore visibilità e confronto con persone percepite come molto competenti."
                                    ),
                                    List.of(
                                            "Attribuzione: successi e feedback positivi vengono ridimensionati, mentre fortuna, circostanze e aiuto altrui spiegano quasi interamente il risultato.",
                                            "Esposizione: emerge il timore di essere smascherati, con difficoltà a fare domande, ammettere di non sapere o sentirsi legittimati nel ruolo.",
                                            "Prestazione: perfezionismo, sovrapreparazione, controlli ripetuti o procrastinazione servono a ridurre il rischio di una valutazione negativa.",
                                            "Impatto: confronto sfavorevole, minimizzazione dei risultati e bisogno di sentirsi completamente pronti possono limitare partecipazione, riposo e accesso a opportunità."
                                    )
                            ),
                            new GuideSection(
                                    "Ciclo",
                                    "Perché un successo può non correggere il dubbio",
                                    List.of(
                                            "Davanti a una prova, la paura di fallire può portare a prepararsi molto oltre il necessario oppure a rimandare finché l'urgenza impone di agire. Se il risultato è positivo, viene spiegato con lo sforzo eccessivo, la fortuna o aspettative basse; se è imperfetto, diventa conferma dell'inadeguatezza temuta.",
                                            "In entrambi i casi la valutazione di sé cambia poco. Il successo può persino aumentare la pressione a mantenere aspettative percepite come immeritate, riavviando sovraccarico, evitamento e paura di esporsi alla prova successiva."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Non tutto nasce dentro la persona",
                                    List.of(
                                            "Feedback vaghi o incoerenti, ruoli senza criteri chiari, culture molto competitive, scarsa rappresentazione e transizioni con poco supporto possono rendere più difficile capire se si sta procedendo adeguatamente. Stereotipi, esclusione, microaggressioni e discriminazione possono inoltre comunicare che una persona non appartiene davvero al contesto.",
                                            "Attribuire automaticamente queste esperienze a una fragilità individuale rischia di ignorare problemi reali dell'ambiente. Una lettura equilibrata considera insieme il modo in cui la persona interpreta le prove e la qualità concreta di accesso, feedback, riconoscimento, sicurezza e appartenenza offerta dal contesto. Uno studio europeo recente ha inoltre rilevato deviazioni nel campione italiano di una misura dell'impostore: è un motivo in più per non importare norme o interpretazioni da altri Paesi."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Primi passi",
                                    "Trasformare il dubbio in informazioni più verificabili",
                                    List.of(
                                            "Può essere utile separare fatti e interpretazioni: annotare risultato, proprio contributo, aiuti ricevuti, competenze utilizzate e aspetti ancora da apprendere costruisce un'attribuzione più completa, senza negare né il contesto né il merito personale. Prima di un compito, definire che cosa significhi “abbastanza buono” può limitare controlli e preparazione senza fine.",
                                            "Chiedere feedback specifici — che cosa ha funzionato, che cosa migliorare e quale standard è atteso — è più informativo di una rassicurazione generale. Condividere il vissuto con una persona fidata, un pari o un supervisore disponibile può ridurre l'isolamento e rendere visibili dubbi comuni. Se paura, perfezionismo o autosvalutazione causano ansia, umore depresso, esaurimento o rinunce importanti, uno psicologo o psicoterapeuta può aiutare a lavorare sui problemi associati; le prove su interventi specifici per il solo fenomeno dell'impostore sono ancora limitate."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, attribuzione dei successi, paura di essere smascherati, pressione perfezionistica e impatto di confronto e feedback sulle opportunità. Non formula una diagnosi e non misura competenza, preparazione o adeguatezza reale: il risultato va letto insieme a evidenze concrete, qualità dei riscontri e caratteristiche dell'ambiente.",
                    List.of(
                            new GuideReference(
                                    "The imposter phenomenon in high achieving women — Clance e Imes",
                                    "https://doi.org/10.1037/h0086006",
                                    "Lavoro originario sul fenomeno dell'impostore, sulla difficoltà a interiorizzare i successi e sull'attribuzione dei risultati a cause esterne nonostante riscontri di capacità."
                            ),
                            new GuideReference(
                                    "Impostor Phenomenon Measurement Scales: A Systematic Review — Mak, Kleitman e Abbott",
                                    "https://pmc.ncbi.nlm.nih.gov/articles/PMC6463809/",
                                    "Revisione sistematica delle definizioni e delle proprietà delle principali misure, consultata per chiarire che il fenomeno non è diagnosticabile e non dispone di un unico strumento di riferimento."
                            ),
                            new GuideReference(
                                    "Contextualizing the Impostor “Syndrome” — Feenstra e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/33312149/",
                                    "Contributo scientifico per integrare il ruolo di sottorappresentazione, stereotipi, trattamento diseguale e contesto istituzionale, evitando di collocare cause e soluzioni soltanto nell'individuo."
                            ),
                            new GuideReference(
                                    "Interventions addressing the impostor phenomenon: a scoping review — Para e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/38605843/",
                                    "Revisione degli interventi in ambito professionale, consultata per educazione sul fenomeno, supporto di gruppo e limiti ed eterogeneità delle prove disponibili."
                            ),
                            new GuideReference(
                                    "Cross-cultural validation of the Impostor-Profile 30 — Ibrahim e colleghi",
                                    "https://doi.org/10.1007/s12144-025-07865-1",
                                    "Studio europeo con versione italiana consultato per invarianza culturale; le deviazioni osservate nel campione italiano impediscono di assumere equivalenza piena o trasferire norme al test dell'app."
                            )
                    )
            ),
            new InformationGuide(
                    "autosabotaggio",
                    "autosabotaggio",
                    "Autosabotaggio e ostacoli agli obiettivi",
                    "Autosabotaggio e ostacoli agli obiettivi: capire i meccanismi",
                    "Autosabotaggio: segnali e strategie utili | Spazio Test",
                    "Una guida all'autosabotaggio: procrastinazione, paura del giudizio, sollievo immediato, ostacoli reali e strategie per avvicinarsi ai propri obiettivi.",
                    "“Autosabotaggio” è un'espressione comune usata quando alcune azioni aumentano ripetutamente la distanza da obiettivi importanti. Non indica necessariamente una scelta consapevole: spesso descrive tentativi di ridurre disagio, proteggersi dal giudizio o procedere con strategie che funzionano nel breve periodo ma presentano costi successivi.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cosa si intende per autosabotaggio",
                                    List.of(
                                            "Il termine può comprendere procrastinare, evitare feedback o opportunità, ridurre l'impegno quando si teme una valutazione, iniziare con ritmi insostenibili o continuare a usare una strategia inefficace. Questi comportamenti non formano un'unica diagnosi e possono avere funzioni e cause differenti.",
                                            "La ricerca sui comportamenti controproducenti distingue il danno deliberato dai compromessi che privilegiano un beneficio immediato e dalle strategie che producono conseguenze non desiderate. Nelle comuni esperienze quotidiane non emerge una chiara intenzione generale di danneggiare sé stessi: parlare di ostacoli autoalimentati è spesso più preciso e meno colpevolizzante."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Rimandare o abbandonare non è sempre autosabotaggio",
                                    List.of(
                                            "Posticipare può essere ragionevole quando mancano informazioni, tempo, salute, denaro o sicurezza; ridurre un impegno può proteggere energie limitate; abbandonare un obiettivo può essere una scelta sana se non è più significativo, realistico o veramente proprio. La produttività continua non è il criterio del benessere.",
                                            "Per capire se esiste un ostacolo ricorrente è utile chiedersi: l'obiettivo conta ancora per me? Le risorse e le condizioni sono sufficienti? Il comportamento offre un beneficio immediato? Quale costo prevedibile produce? Lo stesso rinvio può segnalare evitamento, riposo necessario, conflitto di priorità oppure un problema pratico da risolvere."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Quali meccanismi possono ostacolare gli obiettivi",
                                    List.of(
                                            "Descrivere il meccanismo specifico permette di scegliere una risposta più adatta rispetto a giudicarsi semplicemente poco motivati o disciplinati."
                                    ),
                                    List.of(
                                            "Azione: obiettivi vaghi, difficoltà a iniziare, sottostima dei tempi e attesa di sentirsi completamente pronti o motivati.",
                                            "Protezione dal giudizio: rinuncia, minore impegno o ostacoli creati prima della prova possono rendere meno diretto il confronto con le proprie capacità, ma riducono apprendimento e opportunità.",
                                            "Sollievo emotivo: distrazione ed evitamento allontanano temporaneamente ansia, noia, vergogna o frustrazione, anche se aumentano pressione e costi futuri.",
                                            "Direzione: ritmi iniziali eccessivi, lettura di una pausa come fallimento, rigidità della strategia e difficoltà a riprendere con un passo più piccolo."
                                    )
                            ),
                            new GuideSection(
                                    "Ciclo",
                                    "Come sollievo immediato e autocritica mantengono il blocco",
                                    List.of(
                                            "Un compito importante può attivare disagio, regole rigide o paura di fallire. Passare a qualcosa di più semplice, rimandare o non esporsi riduce rapidamente quella sensazione; proprio questo sollievo rende più probabile ripetere la stessa risposta davanti a un disagio simile.",
                                            "Con il tempo aumentano urgenza, conseguenze e autocritica, e il compito diventa ancora più minaccioso. Criticarsi duramente può sembrare un modo per recuperare controllo, ma spesso aggiunge vergogna e rende più difficile tornare all'azione. Interrompere il ciclo significa modificare sia il compito sia il modo di attraversare il disagio, non aspettare che ogni emozione scomoda scompaia."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Pratica",
                                    "Rendere il passo successivo più chiaro e sostenibile",
                                    List.of(
                                            "Dopo aver verificato che l'obiettivo sia ancora scelto e praticabile, può essere utile definirne la prossima azione osservabile: non “lavorare al progetto”, ma aprire il documento e scrivere tre punti; non “rimettersi in forma”, ma preparare ciò che serve per una breve attività. Stabilire quando, dove e per quanto tempo riduce decisioni e ambiguità al momento di iniziare.",
                                            "Preparare in anticipo materiali, promemoria, ambiente e supporti riduce l'attrito. Si può anche prevedere il disagio — «potrei sentirmi annoiato o inadeguato» — e scegliere una risposta breve, come restare sul compito per pochi minuti prima di rivalutare. Alla fine, osservare che cosa ha aiutato e adattare il piano trasforma il tentativo in informazione, invece che in un giudizio definitivo sul proprio valore."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Quando guardare oltre le abitudini",
                                    List.of(
                                            "Stress elevato, sonno insufficiente, dolore o malattie, carichi di cura, difficoltà economiche, ambienti caotici o poco sicuri possono ridurre le risorse necessarie per iniziare e perseverare. Ansia, umore depresso, trauma, ADHD e altre difficoltà esecutive possono produrre esperienze simili e richiedono una valutazione distinta, non un'etichetta sulla volontà.",
                                            "Se i blocchi compaiono in molti ambiti, durano nel tempo o compromettono studio, lavoro, relazioni, salute o cura personale, un confronto con uno psicologo, psicoterapeuta o medico può aiutare a chiarire i fattori coinvolti e individuare supporti adeguati. L'obiettivo non è diventare sempre produttivi, ma aumentare libertà di scelta e coerenza con priorità sostenibili."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, avvio e pianificazione, protezione dal giudizio e auto-handicapping, ricerca di sollievo emotivo e capacità di mantenere o adattare il percorso. Non formula una diagnosi, non attribuisce intenzioni e non distingue da solo abitudini modificabili, ostacoli ambientali, condizioni di salute o difficoltà esecutive; non misura volontà, disciplina o valore personale.",
                    List.of(
                            new GuideReference(
                                    "Self-defeating behavior patterns among normal individuals — Baumeister e Scher",
                                    "https://pubmed.ncbi.nlm.nih.gov/3043527/",
                                    "Revisione di riferimento per distinguere danno deliberato, compromessi con benefici immediati e strategie controproducenti, mantenendo una lettura non intenzionale e non moralistica delle comuni esperienze."
                            ),
                            new GuideReference(
                                    "The nature of procrastination: a meta-analytic and theoretical review — Steel",
                                    "https://pubmed.ncbi.nlm.nih.gov/17201571/",
                                    "Meta-analisi di riferimento per definizione della procrastinazione, autoregolazione, avversione al compito, impulsività, tempi e divario tra intenzione e azione."
                            ),
                            new GuideReference(
                                    "Procrastination and Stress: A Conceptual Review of Why Context Matters — Sirois",
                                    "https://pmc.ncbi.nlm.nih.gov/articles/PMC10049005/",
                                    "Revisione consultata per il ruolo di stress, risorse di coping, emozioni legate al compito e sollievo a breve termine, con una lettura più contestuale e compassionevole della procrastinazione."
                            ),
                            new GuideReference(
                                    "Procrastination Self-Help Resources — Centre for Clinical Interventions",
                                    "https://www.cci.health.wa.gov.au/Resources/Looking-After-Yourself/Procrastination",
                                    "Risorsa clinico-educativa per ciclo della procrastinazione, regole e giustificazioni, tolleranza del disagio, motivazione non critica e strategie pratiche di avvio e mantenimento."
                            ),
                            new GuideReference(
                                    "On the Measurement of Procrastination in Six European Countries — Svartdal e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/27630595/",
                                    "Confronto psicometrico in sei Paesi europei, inclusa l'Italia, consultato per la misura della procrastinazione; non valida un costrutto unitario di autosabotaggio."
                            )
                    )
            ),
            new InformationGuide(
                    "disturbo-borderline-personalita",
                    "tratti-borderline-adulti",
                    "Disturbo borderline di personalità",
                    "Disturbo borderline di personalità: caratteristiche, valutazione e supporto",
                    "Disturbo borderline di personalità: caratteristiche | Spazio Test",
                    "Una guida al disturbo borderline di personalità: emozioni, relazioni, immagine di sé, valutazione professionale, supporto e limiti dei test online.",
                    "Il disturbo borderline di personalità riguarda un pattern duraturo e diffuso di difficoltà nella regolazione delle emozioni, nelle relazioni, nell'immagine di sé e nel controllo delle azioni. Le esperienze possono combinarsi in modi differenti e il nome del disturbo non definisce l'identità, il valore o tutte le caratteristiche di una persona.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è il disturbo borderline di personalità",
                                    List.of(
                                            "Il termine descrive un modo di funzionare che coinvolge più aree della vita e che viene valutato nel suo andamento nel tempo. Non coincide con avere emozioni intense, attraversare una relazione difficile o reagire impulsivamente in un singolo periodo.",
                                            "Le classificazioni cliniche considerano il funzionamento complessivo della persona, la persistenza, la diffusione nei contesti e le conseguenze concrete. Il pattern borderline può includere particolare instabilità emotiva e relazionale, difficoltà nell'immagine di sé, impulsività e reazioni insolite sotto forte stress, ma la combinazione varia da persona a persona."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Esperienze possibili",
                                    "Quattro domini utili per orientarsi, non una checklist",
                                    List.of(
                                            "Raggruppare le esperienze in domini aiuta a parlarne con più precisione, ma non permette di contare criteri o formulare una diagnosi. Un singolo dominio può essere rilevante anche in molte altre condizioni o fasi di vita."
                                    ),
                                    List.of(
                                            "Emozioni: cambiamenti rapidi, reazioni intense e tempo necessario per ritrovare un equilibrio dopo un evento significativo.",
                                            "Relazioni: forte sensibilità ai segnali di distanza, ricerca di rassicurazione e oscillazioni nel modo di percepire un legame importante.",
                                            "Identità: immagine di sé, obiettivi o preferenze che possono apparire instabili, insieme a momenti di vuoto o scarsa direzione personale.",
                                            "Azioni e stress: difficoltà a creare una pausa prima di agire, rapido aumento della rabbia e, sotto forte stress, percezioni di ostilità o distacco da ciò che accade."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Frequenza recente e pattern duraturo non sono la stessa cosa",
                                    List.of(
                                            "Una diagnosi di personalità non si basa soltanto su ciò che è accaduto negli ultimi mesi. Servono storia nel tempo, presenza in più contesti, funzionamento, sofferenza, risorse e relazione tra le diverse esperienze; fasi di transizione o crisi possono rendere alcuni fenomeni più visibili senza indicare un pattern stabile.",
                                            "Stress intenso, lutto, trauma, ansia, depressione, disturbi dell'umore, ADHD, uso di sostanze, condizioni dissociative, neurodivergenza e contesti relazionali instabili o non sicuri possono produrre esperienze sovrapposte. Le associazioni osservate nei gruppi non spiegano il singolo caso e condizioni differenti possono anche coesistere."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Valutazione",
                                    "Che cosa considera un percorso professionale",
                                    List.of(
                                            "Una valutazione viene svolta da professionisti qualificati attraverso colloqui clinici e, quando opportuno, strumenti strutturati. Considera andamento dall'adolescenza all'età adulta, situazioni in cui le difficoltà compaiono, interferenza, salute fisica e mentale, uso di sostanze, esperienze traumatiche, condizioni concomitanti, risorse e obiettivi della persona.",
                                            "Il percorso non dovrebbe ridursi a un'etichetta o al conteggio automatico di alcune esperienze. Serve a costruire una comprensione condivisa e a distinguere spiegazioni diverse; una validazione italiana di un'intervista clinica non rende valido il questionario originale di Spazio Test e non permette di trasferirne punteggi o soglie."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto",
                                    "Esistono percorsi di cura e possibilità di cambiamento",
                                    List.of(
                                            "Un confronto professionale può essere utile quando le difficoltà persistono, compaiono in più ambiti o incidono su benessere, relazioni, studio, lavoro o cura di sé. In Italia un primo orientamento può passare dal medico di base, dai servizi territoriali di salute mentale o da uno psicologo, psicoterapeuta o psichiatra con esperienza nel funzionamento di personalità.",
                                            "La ricerca ha studiato diverse psicoterapie strutturate. Una revisione Cochrane ha rilevato benefici possibili rispetto alla cura abituale, ma la certezza varia tra gli esiti, i campioni erano soprattutto femminili e non emerge una soluzione unica adatta a ogni persona. La scelta del percorso richiede quindi valutazione individuale, obiettivi condivisi e monitoraggio nel tempo."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Sicurezza",
                                    "Quando serve un aiuto immediato",
                                    List.of(
                                            "Autolesionismo e pensieri suicidari possono comparire in persone con storie e diagnosi molto diverse. Non vanno usati per autodiagnosticarsi e non sono valutati dal questionario collegato, perché un flusso automatico non può comprendere urgenza, intenzione, mezzi disponibili, protezioni o supporti.",
                                            "Se pensi di farti del male, non riesci a restare al sicuro o c'è un pericolo immediato, chiama il 112 o raggiungi il Pronto Soccorso più vicino. Se non c'è un'emergenza immediata ma queste esperienze sono presenti, parlane quanto prima con un professionista sanitario o con un servizio di salute mentale, coinvolgendo se possibile una persona fidata."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Limiti",
                                    "Come leggere le informazioni e il questionario",
                                    List.of(
                                            "Le fonti italiane disponibili aiutano a inquadrare domini clinici e percorsi di cura, ma non validano le 24 domande, le quattro aree o le soglie editoriali di Spazio Test. La linea guida ISS specifica sul disturbo borderline è ancora in produzione, perciò non viene presentata come fonte di raccomandazioni definitive.",
                                            "Il risultato del questionario descrive soltanto frequenza e distribuzione delle risposte negli ultimi tre mesi. Le barre non sono percentuali della persona, probabilità, percentili o misure di gravità e non possono confermare né escludere una diagnosi."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, intensità emotiva e ritorno all'equilibrio, sensibilità alla distanza nelle relazioni, immagine di sé e senso di vuoto, impulsività e reazioni sotto stress. Non è validato, non conta criteri clinici, non valuta autolesionismo o pensieri suicidari e non può confermare, escludere o stimare la presenza di un disturbo borderline di personalità.",
                    List.of(
                            new GuideReference(
                                    "The Italian Version of the Borderline Personality Disorder Severity Index IV — di Giacomo e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/28604275/",
                                    "Validazione multicentrica italiana di un'intervista strutturata consultata per eterogeneità e domini affettivi, comportamentali, impulsivi e relazionali; non valida il questionario dell'app."
                            ),
                            new GuideReference(
                                    "Clinical descriptions and diagnostic requirements for ICD-11 — World Health Organization",
                                    "https://iris.who.int/bitstream/handle/10665/375767/9789240077263-eng.pdf?sequence=1",
                                    "Descrizioni cliniche internazionali consultate per funzionamento complessivo di personalità, andamento nel tempo e pattern borderline; l'applicazione al singolo caso richiede valutazione professionale."
                            ),
                            new GuideReference(
                                    "Percorsi di cura per i disturbi gravi di personalità — Ministero della Salute",
                                    "https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_2461_allegato.pdf",
                                    "Documento tecnico italiano consultato per valutazione clinica e dimensionale, funzionamento, condizioni concomitanti e organizzazione dei percorsi nei servizi di salute mentale."
                            ),
                            new GuideReference(
                                    "Borderline personality disorder: recognition and management — NICE CG78",
                                    "https://www.nice.org.uk/guidance/cg78",
                                    "Linea guida europea consultata per riconoscimento, valutazione, comunicazione, organizzazione della presa in carico e necessità di un percorso strutturato."
                            ),
                            new GuideReference(
                                    "Psychological therapies for people with borderline personality disorder — Storebø e colleghi",
                                    "https://pmc.ncbi.nlm.nih.gov/articles/PMC7199382/",
                                    "Revisione sistematica Cochrane di 75 studi randomizzati consultata per benefici e limiti delle psicoterapie; molti esiti hanno certezza bassa e i campioni erano prevalentemente femminili."
                            ),
                            new GuideReference(
                                    "Il numero 118 e il Numero di emergenza unico europeo (112) — Ministero della Salute",
                                    "https://www.salute.gov.it/new/it/tema/112-118-e-pronto-soccorso/il-numero-118-e-il-numero-di-emergenza-unico-europeo-112/",
                                    "Riferimento istituzionale italiano per l'indicazione di chiamare il 112 nelle situazioni di pericolo immediato e per l'attivazione del soccorso sanitario."
                            ),
                            new GuideReference(
                                    "Diagnosi e trattamento del disturbo borderline di personalità — ISS, linea guida in produzione",
                                    "https://www.iss.it/-/diagnosi-trattamento-disturbo-borderline-personalit%C3%A0_in-prog",
                                    "Pagina ISS consultata per documentare lo sviluppo di una linea guida italiana specifica; non essendo ancora conclusa, non sostiene raccomandazioni definitive."
                            )
                    )
            ),
            new InformationGuide(
                    "paura-abbandono",
                    "paura-abbandono",
                    "Paura dell'abbandono",
                    "Paura dell'abbandono: comprenderla nelle relazioni adulte",
                    "Paura dell'abbandono: segnali e significato | Spazio Test",
                    "Una guida informativa alla paura dell'abbandono nelle relazioni adulte: segnali di distanza, rassicurazione, separazioni, confini e supporto.",
                    "La paura dell'abbandono descrive la preoccupazione ricorrente che una persona importante possa rifiutare, ridurre la vicinanza o interrompere il legame. Può coinvolgere il modo di interpretare i segnali, cercare rassicurazione, vivere la distanza e proteggere i propri bisogni e confini.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cosa si intende per paura dell'abbandono",
                                    List.of(
                                            "Paura dell'abbandono è un'espressione descrittiva, non il nome di una diagnosi autonoma. In questa guida indica un insieme di pensieri, emozioni e comportamenti che possono attivarsi quando un legame importante sembra meno disponibile o in pericolo.",
                                            "Le ricerche sull'attaccamento adulto descrivono una dimensione di ansia legata a disponibilità, affetto e possibile rifiuto. Questa vicinanza teorica aiuta a comprendere alcune esperienze, ma non permette di classificare automaticamente lo stile di attaccamento di una persona né di ricostruirne le cause."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Non è una diagnosi né uno stile completo",
                                    List.of(
                                            "L'ansia di attaccamento comprende un insieme più ampio di aspettative e strategie relazionali; la paura dell'abbandono ne rappresenta soltanto un possibile aspetto. Il disturbo d'ansia di separazione nell'adulto richiede invece una valutazione specifica di sintomi, durata, contesto e interferenza.",
                                            "La stessa esperienza non equivale a dipendenza affettiva, disturbo borderline di personalità o disturbo dipendente di personalità. Questi concetti hanno confini diversi e non possono essere dedotti dalla frequenza di alcune risposte online."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Quattro passaggi utili per osservare l'esperienza",
                                    List.of(
                                            "Le quattro aree del questionario organizzano l'auto-osservazione senza costituire sottoscale cliniche. Possono aiutare a distinguere dove si attiva il timore e che cosa accade dopo, evitando di trasformare piccole differenze in una classifica personale."
                                    ),
                                    List.of(
                                            "Segnali di distanza: come vengono interpretati ritardi, cambiamenti di tono, disaccordi, programmi annullati o richieste di spazio.",
                                            "Rassicurazione e vicinanza: conferme, controllo dei messaggi, prevedibilità e aumento del contatto quando emerge un dubbio.",
                                            "Esperienza della distanza: pensieri, agitazione, concentrazione e ritorno alla calma durante separazioni reali o previste.",
                                            "Autonomia e confini: espressione di bisogni e disaccordi, programmi personali e mantenimento dei limiti quando si teme di perdere il legame."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Segnali reali e interpretazioni non sono la stessa cosa",
                                    List.of(
                                            "Un ritardo o una richiesta di spazio possono avere molte spiegazioni, ma esistono anche relazioni realmente incoerenti, svalutanti o non sicure. Osservare la paura non significa attribuirla automaticamente a una vulnerabilità interna né ignorare fatti, promesse disattese, minacce o comportamenti di controllo.",
                                            "Perdite, tradimenti, separazioni e cambiamenti recenti possono aumentare temporaneamente la sensibilità alla distanza. Stress, lutto, ansia, umore depresso e altre difficoltà possono inoltre sovrapporsi; le associazioni trovate negli studi di gruppo non spiegano il singolo caso e non stabiliscono causalità."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Possibile ciclo",
                                    "Quando la rassicurazione dà sollievo solo per poco",
                                    List.of(
                                            "In alcuni casi un segnale incerto può aumentare l'attenzione verso il legame, portare a cercare contatto o conferme e produrre un sollievo temporaneo. Se il dubbio ritorna presto, il ciclo può ripetersi e occupare sempre più attenzione.",
                                            "Questo è un modello possibile, non una spiegazione inevitabile. Chiedere vicinanza è una parte normale delle relazioni; diventano importanti la libertà reciproca, il consenso, la proporzione rispetto ai fatti e le conseguenze per entrambe le persone."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Auto-osservazione",
                                    "Domande concrete da portare nel quotidiano",
                                    List.of(
                                            "Può essere utile separare ciò che hai osservato dall'interpretazione che ne è seguita, annotando situazione, pensiero, emozione, azione e conseguenza. Nota anche le eccezioni: relazioni o momenti in cui riesci a tollerare l'incertezza, comunicare una richiesta specifica e riprendere le tue attività.",
                                            "Osserva se una rassicurazione risponde davvero al bisogno, quanto dura il sollievo e se programmi, bisogni o confini restano scelti liberamente. Questi spunti non sono un trattamento e non sostituiscono una comprensione condivisa della relazione."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto",
                                    "Quando può essere utile parlarne con un professionista",
                                    List.of(
                                            "Un confronto con uno psicologo, psicoterapeuta o medico può essere utile quando la preoccupazione persiste, occupa molto tempo, interferisce con sonno, concentrazione o attività, oppure rende difficile mantenere relazioni, autonomia e confini. Il percorso dovrebbe considerare storia personale, eventi recenti, sicurezza reale delle relazioni, risorse e possibili condizioni concomitanti.",
                                            "Lo scopo non è assegnare automaticamente un'etichetta, ma comprendere funzioni e contesti dell'esperienza e definire obiettivi utili per la persona. Una validazione italiana di scale sull'attaccamento non rende valido il questionario originale di Spazio Test e non consente di trasferirne punteggi o soglie."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Sicurezza",
                                    "Paura, pressione e violenza richiedono letture distinte",
                                    List.of(
                                            "La paura di perdere una relazione non giustifica controllo dei dispositivi, isolamento, minacce, coercizione, stalking o violenza, subiti o agiti. Sicurezza e consenso hanno priorità sul mantenimento del legame e il risultato del test non valuta il pericolo né stabilisce la responsabilità di ciò che accade.",
                                            "Se c'è un pericolo immediato chiama il 112. Il 1522 è un servizio pubblico gratuito, attivo 24 ore su 24, rivolto alle donne vittime di violenza e stalking; può offrire ascolto e orientamento, ma non va presentato come un servizio universale per ogni situazione."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Limiti",
                                    "Come leggere fonti, guida e questionario",
                                    List.of(
                                            "Le evidenze italiane disponibili riguardano soprattutto adattamenti di altri strumenti e campioni prevalentemente giovani, non la paura dell'abbandono come test autonomo. Gli studi internazionali aiutano a descrivere associazioni medie tra attaccamento e relazioni, ma non prevedono la storia o il comportamento del singolo individuo.",
                                            "Le 24 domande, le quattro aree e le soglie di Spazio Test sono originali, editoriali e non validate. Le barre mostrano soltanto la frequenza relativa delle risposte: non sono percentuali della persona, probabilità, percentili o misure cliniche."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, sensibilità ai segnali di distanza, ricerca di rassicurazione, pensieri ed emozioni durante la distanza e autonomia nei confini. Non è validato, non classifica uno stile di attaccamento e non può confermare, escludere o stimare una condizione psicologica o la qualità reale di una relazione.",
                    List.of(
                            new GuideReference(
                                    "Italian Validation of the Adult Attachment Scale-Revised — Troisi, Parola e Margherita",
                                    "https://pubmed.ncbi.nlm.nih.gov/36407970/",
                                    "Validazione su 1.546 adulti italiani consultata per la dimensione di ansia dell'attaccamento e i timori di rifiuto e abbandono; strumento e punteggi non sono trasferibili all'app."
                            ),
                            new GuideReference(
                                    "Psychometric properties of the Italian ECR-12 — Brugnera e colleghi",
                                    "https://pmc.ncbi.nlm.nih.gov/articles/PMC7453162/",
                                    "Validazione su 1.197 adulti madrelingua italiani consultata per distinguere ansia ed evitamento e per le associazioni con ruminazione e difficoltà interpersonali; il campione era giovane e prevalentemente femminile."
                            ),
                            new GuideReference(
                                    "Attachment Theory and Affect Regulation — Mikulincer, Shaver e Pereg",
                                    "https://doi.org/10.1023/A:1024515519160",
                                    "Revisione teorica consultata per attenzione alla disponibilità, strategie di prossimità e regolazione affettiva; non dimostra cause individuali né valida item o interventi."
                            ),
                            new GuideReference(
                                    "How anxious and avoidant attachment affect romantic relationship quality differently — Li e Chan",
                                    "https://doi.org/10.1002/ejsp.1842",
                                    "Meta-analisi di 73 studi consultata per le differenti associazioni tra ansia, evitamento, conflitto e qualità relazionale; risultati di gruppo, prevalentemente romantici e non applicabili automaticamente al singolo legame."
                            ),
                            new GuideReference(
                                    "Attachment and social support in romantic dyads — Gajwani e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/31566266/",
                                    "Revisione sistematica di 43 studi consultata per ricerca e interpretazione del supporto nelle coppie; non copre tutte le relazioni importanti e non indica una strategia universale."
                            ),
                            new GuideReference(
                                    "Separation anxiety in a community sample of Italian emerging adults — Iannattone e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/33937113/",
                                    "Studio correlazionale italiano consultato per distinguere ansia di separazione, ansia generale e dimensioni borderline; il campione di studenti giovani limita la generalizzazione."
                            ),
                            new GuideReference(
                                    "Il numero 118 e il Numero di emergenza unico europeo (112) — Ministero della Salute",
                                    "https://www.salute.gov.it/new/it/tema/112-118-e-pronto-soccorso/il-numero-118-e-il-numero-di-emergenza-unico-europeo-112/",
                                    "Riferimento istituzionale italiano per l'indicazione di chiamare il 112 nelle situazioni di pericolo immediato."
                            ),
                            new GuideReference(
                                    "Cos'è il 1522 — Dipartimento per le Pari Opportunità",
                                    "https://www.1522.eu/cose-1522/",
                                    "Riferimento istituzionale per descrivere correttamente il servizio pubblico gratuito rivolto alle donne vittime di violenza e stalking."
                            )
                    )
            ),
            new InformationGuide(
                    "fomo",
                    "fomo",
                    "FOMO (Fear of Missing Out)",
                    "FOMO: comprendere la paura di perdersi qualcosa",
                    "FOMO: significato, social e segnali | Spazio Test",
                    "Una guida informativa alla FOMO: significato, confronto sociale, bisogno di aggiornamenti, rapporto con i social e limiti dei test online.",
                    "FOMO significa Fear of Missing Out e descrive la preoccupazione di perdere esperienze rilevanti vissute da altre persone, insieme al desiderio di restare aggiornati e connessi. Può comparire attraverso i social media, ma anche quando si viene a conoscenza direttamente di incontri, conversazioni o opportunità a cui non si partecipa.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cos'è la FOMO",
                                    List.of(
                                            "La FOMO è un'esperienza psicologica descrittiva, non una diagnosi. La formulazione più citata unisce la preoccupazione che altri stiano vivendo esperienze gratificanti in propria assenza al desiderio di restare continuamente informati su ciò che fanno.",
                                            "La ricerca non ha ancora risolto in modo uniforme se la FOMO sia soprattutto una disposizione relativamente stabile, uno stato che cambia nelle situazioni o una combinazione delle due. Anche gli stati emotivi, i confini con il confronto sociale e la struttura delle misure variano tra gli studi."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "FOMO e uso problematico non sono la stessa cosa",
                                    List.of(
                                            "Controllare aggiornamenti o voler partecipare a esperienze importanti non costituisce di per sé un problema psicologico. FOMO, tempo online, uso problematico dei social, dipendenza da smartphone, ansia sociale, invidia ed esclusione reale sono concetti distinti, anche quando risultano associati negli studi.",
                                            "Una correlazione non stabilisce che la FOMO provochi un certo uso della tecnologia o che l'uso della tecnologia provochi la FOMO. Le relazioni possono variare tra persone e periodi, essere reciproche oppure dipendere da condizioni non misurate."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Quattro passaggi utili per osservare l'esperienza",
                                    List.of(
                                            "Il questionario organizza l'auto-osservazione in quattro aree editoriali. Non sono sottoscale cliniche e non riproducono la struttura di FoMOs o ON-FoMO."
                                    ),
                                    List.of(
                                            "Inclusione e appartenenza: il significato attribuito a inviti, incontri e conversazioni avvenuti in propria assenza.",
                                            "Confronto con alternative: come ciò che fanno gli altri o le possibilità non scelte cambiano la valutazione dell'esperienza presente.",
                                            "Aggiornamento e connessione: controlli, notifiche, ricerca di informazioni e vissuti dei momenti senza accesso.",
                                            "Attenzione e scelte: eventuali interruzioni, rinvii del sonno o cambiamenti nei programmi collegati al timore di perdere qualcosa."
                                    )
                            ),
                            new GuideSection(
                                    "Online e offline",
                                    "I social rendono visibili le alternative, ma non sono necessari",
                                    List.of(
                                            "Le piattaforme possono mostrare molte esperienze alternative quasi in tempo reale, spesso attraverso selezioni brevi e curate. Questo può aumentare le occasioni di confronto e la sensazione che altrove stia accadendo qualcosa di più rilevante, ma non dimostra che la piattaforma sia la causa dell'esperienza.",
                                            "Studi quotidiani suggeriscono che la FOMO può comparire anche quando l'informazione arriva senza social e tende a essere più presente per attività sociali. Il significato personale dell'occasione, il gruppo coinvolto e ciò che si sta facendo nel momento possono essere più informativi del semplice tempo online."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Possibile ciclo",
                                    "Dal segnale al controllo degli aggiornamenti",
                                    List.of(
                                            "In alcuni casi un invito mancato, un post o una conversazione possono attivare confronto e preoccupazione. Controllare aggiornamenti può ridurre per poco l'incertezza, ma può anche mostrare nuove alternative e riportare l'attenzione su ciò che non si sta vivendo.",
                                            "Questo ciclo è un'ipotesi descrittiva, non una sequenza inevitabile né una spiegazione causale. Per comprenderlo sono utili ciò che accade prima, la durata del sollievo, la possibilità di interrompere e le conseguenze sulla scelta presente."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Auto-osservazione",
                                    "Fatti, interpretazioni e conseguenze",
                                    List.of(
                                            "Può essere utile annotare il segnale osservato, l'interpretazione, l'emozione, l'azione successiva e ciò che cambia dopo. Distingui, per esempio, il fatto di non aver ricevuto un invito dal significato attribuito al tuo posto nel gruppo, senza ignorare eventuali pattern reali di esclusione.",
                                            "Nota quando notifiche e controlli sono scelti intenzionalmente e quando invece interrompono sonno, concentrazione o attività a cui vuoi dedicarti. Osserva anche le eccezioni: momenti in cui resti presente nella tua scelta, tolleri di non sapere subito o chiedi direttamente informazioni rilevanti. Questi spunti non costituiscono un trattamento."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto",
                                    "Quando può essere utile parlarne",
                                    List.of(
                                            "Un confronto con uno psicologo, psicoterapeuta o medico può essere utile quando preoccupazione e controlli persistono, occupano molto tempo o incidono in modo rilevante su sonno, concentrazione, lavoro, studio, relazioni o possibilità di scegliere liberamente le proprie attività.",
                                            "Una valutazione individuale considera storia recente, relazioni e gruppi, isolamento o esclusione reali, umore, ansia, stress, abitudini digitali e risorse. Non parte automaticamente dall'idea che il problema sia il telefono o da una classificazione ottenuta online."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Limiti",
                                    "Come leggere ricerca e questionario",
                                    List.of(
                                            "Le validazioni italiane disponibili riguardano strumenti diversi e soprattutto adolescenti, adulti emergenti o giovani adulti. Le revisioni includono studi molto eterogenei e in gran parte trasversali, perciò descrivono associazioni medie senza spiegare cause o conseguenze nel singolo caso.",
                                            "Le 24 domande, le quattro aree e le soglie di Spazio Test sono originali, editoriali e non validate. Le barre mostrano soltanto la frequenza relativa delle risposte nell'ultimo mese: non sono percentuali della persona, probabilità, percentili o misure cliniche."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato esplora, con finalità esclusivamente informative, inclusione percepita, confronto con alternative, bisogno di restare aggiornati e possibile interferenza su attenzione e scelte. Non è validato, non diagnostica una condizione e non dimostra un uso problematico di social media, Internet o smartphone.",
                    List.of(
                            new GuideReference(
                                    "Factor structure and psychometric properties of the Italian version of the Fear of Missing Out Scale — Casale e Fioravanti",
                                    "https://pubmed.ncbi.nlm.nih.gov/31704432/",
                                    "Validazione italiana consultata per i nuclei di preoccupazione e desiderio di connessione; riguarda adolescenti e adulti emergenti, uno strumento diverso e una struttura non trasferibile all'app."
                            ),
                            new GuideReference(
                                    "Factor structure and psychometric properties of the Italian version of the Online Fear of Missing Out — Sommantico e colleghi",
                                    "https://doi.org/10.1016/j.chbr.2024.100374",
                                    "Due studi italiani su giovani adulti consultati per appartenenza, riconoscimento, disconnessione e conseguenze dell'uso online; item, fattori e punteggi non validano il questionario dell'app."
                            ),
                            new GuideReference(
                                    "Psychometric properties of the Italian State Fear of Missing Out Inventory — Servidio, Soraci e Holte",
                                    "https://pubmed.ncbi.nlm.nih.gov/41987296/",
                                    "Validazione italiana del 2026 consultata per la distinzione tra esperienza di stato e disposizione; i campioni erano giovani adulti e lo strumento è diverso."
                            ),
                            new GuideReference(
                                    "Motivational, emotional, and behavioral correlates of fear of missing out — Przybylski e colleghi",
                                    "https://doi.org/10.1016/j.chb.2013.02.014",
                                    "Lavoro fondativo consultato per definizione, bisogni psicologici e associazioni con coinvolgimento nei social; non offre inferenze causali né validazione italiana."
                            ),
                            new GuideReference(
                                    "The relationship between fear of missing out, digital technology use, and psychological well-being — Groenestein e colleghi",
                                    "https://doi.org/10.1371/journal.pone.0308643",
                                    "Scoping review preregistrata di 106 studi consultata per eterogeneità concettuale, stato-tratto, confini online e offline e limiti delle interpretazioni causali."
                            ),
                            new GuideReference(
                                    "Fear of missing out and internet use: a systematic review and meta-analysis — Akbari e colleghi",
                                    "https://pmc.ncbi.nlm.nih.gov/articles/PMC8987430/",
                                    "Sintesi di 86 effetti e 55.134 partecipanti consultata per la variabilità dell'associazione tra FOMO e uso di Internet; campioni prevalentemente giovani e nessuna bidirezionalità dimostrata."
                            ),
                            new GuideReference(
                                    "Fear of missing out and social networking sites use and abuse: a meta-analysis — Fioravanti e colleghi",
                                    "https://doi.org/10.1016/j.chb.2021.106839",
                                    "Meta-analisi di 33 campioni consultata per le associazioni con uso e uso problematico dei social; le correlazioni di gruppo non identificano cause o condizioni individuali."
                            ),
                            new GuideReference(
                                    "Fear of missing out: prevalence, dynamics, and consequences — Milyavskaya e colleghi",
                                    "https://doi.org/10.1007/s11031-018-9683-5",
                                    "Studio con vignette e rilevazioni quotidiane consultato per la natura situazionale e la possibilità di FOMO anche fuori dai social; il campione era composto soprattutto da matricole molto giovani."
                            )
                    )
            ),
            new InformationGuide(
                    "intelligenza-linguistica",
                    "intelligenza-linguistica",
                    "Intelligenza linguistica",
                    "Intelligenza linguistica: significato, teoria e limiti",
                    "Intelligenza linguistica di Gardner: significato | Spazio Test",
                    "Una guida all'intelligenza linguistica nella teoria di Gardner, al dibattito scientifico e alla differenza tra autopercezione e competenza misurata.",
                    "L'intelligenza linguistica è uno dei domini proposti da Howard Gardner nella teoria delle intelligenze multiple. Indica, nella sua cornice, la sensibilità al linguaggio parlato e scritto e la possibilità di usarlo per comprendere, esprimere e raggiungere scopi; non è però una capacità indipendente che questo questionario possa misurare oggettivamente.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cosa intende Gardner per intelligenza linguistica",
                                    List.of(
                                            "Gardner ha proposto una visione pluralistica dell'intelligenza, distinguendo domini nei quali le persone possono mostrare combinazioni diverse di risorse. Il dominio linguistico riguarda la sensibilità a parole, significati e usi della lingua, non soltanto il parlare molto o conoscere molte parole.",
                                            "La teoria può offrire una cornice per osservare modalità differenti di partecipazione e apprendimento. Questo valore descrittivo o educativo non equivale però alla dimostrazione che ogni dominio sia un'intelligenza psicometricamente indipendente."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Una precisazione",
                                    "Otto intelligenze o nove?",
                                    List.of(
                                            "La teoria originaria del 1983 presentava sette intelligenze; la naturalistica è stata aggiunta in seguito. La sintesi ufficiale di Project Zero elenca otto intelligenze identificate.",
                                            "Gardner ha discusso l'intelligenza esistenziale come possibile nona candidata, senza collocarla nello stesso modo tra quelle identificate. Dire semplicemente che esistono nove intelligenze è quindi una semplificazione popolare, non una conclusione scientifica definitiva."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Evidenze",
                                    "Una teoria discussa, non un test di abilità",
                                    List.of(
                                            "In uno studio su 200 adulti, prove costruite per rappresentare otto domini mostravano correlazioni importanti e, per diversi domini cognitivi, forti legami con un fattore generale. Gli autori trovarono soltanto un supporto modesto per componenti specifiche ai domini; Gardner ha contestato che quei compiti rappresentassero adeguatamente la propria teoria.",
                                            "Il dibattito riguarda dunque anche come trasformare una teoria ampia in misure verificabili. In assenza di validazione dello strumento dell'app, il risultato deve restare un'autodescrizione di comportamenti e non una conferma, graduatoria o percentuale d'intelligenza."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Autopercezione, uso e prestazione non coincidono",
                                    List.of(
                                            "Dire che spesso si trova una formulazione chiara descrive un'esperienza percepita; dimostrare accuratezza o competenza richiede invece compiti osservabili, criteri e procedure adatte allo scopo. PIAAC, per esempio, misura la literacy adulta con prove cognitive e raccoglie separatamente informazioni sull'uso delle competenze nella vita quotidiana.",
                                            "Il QCER descrive inoltre l'attività linguistica attraverso ricezione, produzione, interazione e mediazione e riconosce repertori plurilingui. Nessuno di questi quadri coincide con l'intelligenza linguistica di Gardner o valida le quattro aree editoriali del questionario."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Quattro prospettive per l'auto-osservazione",
                                    List.of(
                                            "Le quattro aree organizzano comportamenti quotidiani e non sono fattori scientificamente confermati. Non devono essere usate per mettere in classifica modalità o persone."
                                    ),
                                    List.of(
                                            "Comprensione e sensibilità al significato: idee centrali, sfumature, ambiguità e collegamenti percepiti.",
                                            "Espressione orale e adattamento: ordine, precisione, spiegazione e riformulazione nel parlato pertinente alla persona.",
                                            "Espressione scritta e revisione: pianificazione, coesione, scelta delle parole e rilettura dei testi.",
                                            "Apprendimento e uso flessibile: attenzione a parole nuove, registri, analogie e sperimentazione verbale."
                                    )
                            ),
                            new GuideSection(
                                    "Contesto",
                                    "Lingue, modalità e opportunità cambiano ciò che emerge",
                                    List.of(
                                            "Istruzione, professione, abitudini, familiarità con un argomento, tempo disponibile e sicurezza del contesto cambiano le occasioni di usare determinate risorse. Una persona può comprendere bene ma parlare poco, oppure scrivere spesso per necessità senza preferire quella modalità.",
                                            "Plurilinguismo, lingua della compilazione, comunicazione segnata, strumenti assistivi, disabilità e neurodivergenza richiedono letture non gerarchiche. Una frequenza contenuta non significa bassa intelligenza; una frequenza elevata non certifica talento, correttezza o superiorità."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Auto-osservazione",
                                    "Osservare compiti e condizioni, non cercare un'etichetta",
                                    List.of(
                                            "Può essere utile annotare in quale lingua e attività comprendi o ti esprimi con maggiore facilità, quale destinatario è presente, quanto tempo hai e quali strumenti usi. Cerca anche le eccezioni: un compito difficile in un contesto può diventare accessibile con preparazione, un formato diverso o un argomento familiare.",
                                            "Per esplorare le risorse puoi confrontare versioni diverse di una spiegazione, rivedere un testo dopo una pausa, raccogliere parole nuove o provare a rendere accessibile un'idea. Sono attività di osservazione e pratica, non trattamenti né metodi dimostrati per aumentare un tipo di intelligenza."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto e limiti",
                                    "Quando serve una valutazione diversa",
                                    List.of(
                                            "Se noti un cambiamento nuovo o persistente nella comprensione, nel trovare parole, nel parlare, nello scrivere o nel comunicare e questo interferisce con la vita quotidiana, parlane con un medico o altro professionista qualificato. Una valutazione appropriata considera storia, lingue, modalità, accessibilità e usa strumenti scelti per la domanda specifica.",
                                            "La ricerca non ha fornito una validazione italiana pertinente di questo self-report adulto sull'intelligenza linguistica. Le 24 domande, le quattro aree e le soglie di Spazio Test sono originali e non validate; le barre mostrano soltanto frequenze relative, non intelligenza, competenza, probabilità, percentili o confronti con altre persone."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato usa la cornice di Gardner soltanto come punto di partenza per osservare la frequenza percepita di comprensione, espressione orale, scrittura e flessibilità linguistica. Non misura intelligenza generale o competenza, non certifica talenti o limiti e non diagnostica condizioni.",
                    List.of(
                            new GuideReference(
                                    "The Theory of Multiple Intelligences — Project Zero, Harvard",
                                    "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf",
                                    "Sintesi istituzionale consultata per la definizione del dominio linguistico e l'elenco di otto intelligenze identificate; presenta la teoria e non costituisce una validazione indipendente."
                            ),
                            new GuideReference(
                                    "A Resurgence of Interest in Existential Intelligence: Why Now? — Howard Gardner",
                                    "https://www.howardgardner.com/howards-blog/a-resurgence-of-interest-in-existential-intelligence-why-now",
                                    "Fonte primaria dell'autore consultata per precisare che l'intelligenza esistenziale fu proposta come possibile nona candidata, senza trattare il numero nove come conclusione empirica."
                            ),
                            new GuideReference(
                                    "Beyond g: Putting multiple intelligences theory to the test — Visser, Ashton e Vernon",
                                    "https://doi.org/10.1016/j.intell.2006.02.004",
                                    "Studio su prove di prestazione in adulti consultato per correlazioni tra domini, fattore generale e modesto supporto specifico; il campione canadese e l'operazionalizzazione sono limiti espliciti."
                            ),
                            new GuideReference(
                                    "The Science of Multiple Intelligences Theory: A Response to Lynn Waterhouse — Gardner e Moran",
                                    "https://doi.org/10.1207/s15326985ep4104_2",
                                    "Risposta teorica consultata per rappresentare la posizione degli autori sulle finalità e sull'operazionalizzazione della teoria; non è una conferma indipendente della sua struttura."
                            ),
                            new GuideReference(
                                    "g and the measurement of Multiple Intelligences: A response to Gardner — Visser, Ashton e Vernon",
                                    "https://doi.org/10.1016/j.intell.2006.04.006",
                                    "Replica metodologica consultata per rendere visibile il dibattito su fattore generale e contenuto dei compiti, senza presentare un singolo studio come verdetto definitivo."
                            ),
                            new GuideReference(
                                    "CEFR Companion Volume — Council of Europe",
                                    "https://book.coe.int/en/education-and-modern-languages/8152-common-european-framework-of-reference-for-languages-learning-teaching-assessment-companion-volume.html",
                                    "Quadro europeo consultato per ricezione, produzione, interazione, mediazione e repertori plurilingui; descrive competenze linguistiche e non l'intelligenza di Gardner."
                            ),
                            new GuideReference(
                                    "PIAAC Cycle 2 assessment framework: Literacy — OECD",
                                    "https://www.oecd.org/en/publications/the-assessment-frameworks-for-cycle-2-of-the-programme-for-the-international-assessment-of-adult-competencies_4bc2342d-en/full-report/component-5.html",
                                    "Framework consultato per distinguere prove cognitive di literacy e autodescrizioni d'uso; riguarda soprattutto testi scritti e non misura il costrutto dell'app."
                            ),
                            new GuideReference(
                                    "L'indagine PIAAC — INAPP",
                                    "https://www.inapp.gov.it/piaac/conosci-piaac/lindagine-piaac",
                                    "Fonte istituzionale italiana consultata per la distinzione tra prove cognitive autosomministrate e questionario sugli usi delle competenze nella popolazione adulta 16–65; nessun livello è trasferito."
                            )
                    )
            ),
            new InformationGuide(
                    "intelligenza-intrapersonale",
                    "intelligenza-intrapersonale",
                    "Intelligenza intrapersonale",
                    "Intelligenza intrapersonale: significato, teoria e limiti",
                    "Intelligenza intrapersonale di Gardner: significato | Spazio Test",
                    "Una guida all'intelligenza intrapersonale nella teoria di Gardner e alla differenza tra auto-osservazione, insight e conoscenza accurata di sé.",
                    "L'intelligenza intrapersonale è uno dei domini proposti da Howard Gardner nella teoria delle intelligenze multiple. Nella sua cornice riguarda il distinguere e usare informazioni su di sé; non è però una capacità indipendente che questo questionario possa misurare oggettivamente, né una garanzia di insight accurato.",
                    List.of(
                            new GuideSection(
                                    "In breve",
                                    "Che cosa intende Gardner per intelligenza intrapersonale",
                                    List.of(
                                            "Gardner ha proposto una visione pluralistica dell'intelligenza, distinguendo domini nei quali le persone possono mostrare combinazioni diverse di risorse. La sintesi di Project Zero descrive il dominio intrapersonale come la possibilità di distinguere e usare informazioni su di sé, collegando comprensione personale e regolazione del comportamento orientato a obiettivi.",
                                            "Questa cornice può offrire un linguaggio per osservare come una persona nota stati interni e tiene conto di ciò che sa di sé. Il suo valore descrittivo o educativo non equivale però alla dimostrazione che il dominio sia un'intelligenza psicometricamente indipendente."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Una precisazione",
                                    "Otto intelligenze o nove?",
                                    List.of(
                                            "La teoria originaria del 1983 presentava sette intelligenze; la naturalistica è stata aggiunta in seguito. La sintesi ufficiale di Project Zero elenca otto intelligenze identificate, tra cui quella intrapersonale.",
                                            "Gardner ha discusso l'intelligenza esistenziale come possibile nona candidata, senza collocarla nello stesso modo tra quelle identificate. Parlare semplicemente di nove intelligenze è quindi una semplificazione popolare, non una conclusione scientifica definitiva."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Evidenze",
                                    "Una teoria discussa, non un test di autoconsapevolezza",
                                    List.of(
                                            "In uno studio su 200 adulti, prove costruite per rappresentare otto domini mostravano correlazioni importanti e un fattore generale per diverse abilità. Gli autori trovarono soltanto un supporto modesto per componenti specifiche ai domini; Gardner ha contestato che quei compiti rappresentassero adeguatamente la propria teoria.",
                                            "Il dibattito riguarda anche come rendere verificabile una teoria ampia. In assenza di validazione dello strumento dell'app, il risultato deve restare un'autodescrizione di comportamenti e non una conferma, graduatoria o percentuale d'intelligenza intrapersonale."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Distinzioni",
                                    "Guardarsi dentro, ottenere insight ed essere accurati non coincidono",
                                    List.of(
                                            "La Self-Reflection and Insight Scale distingue il processo di esaminare pensieri, emozioni e azioni dall'insight che può derivarne. Il primo studio della versione italiana ha ritrovato due fattori in 112 studenti toscani, ma il campione è ristretto e lo strumento non misura il dominio di Gardner.",
                                            "Inoltre, l'accesso ai propri pensieri non rende infallibili le spiegazioni sulle loro cause. Comportamenti osservati, esiti nel tempo e feedback possono integrare l'introspezione; una lettura personale resta un'ipotesi rivedibile, non una verità certificata dal punteggio."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Aree",
                                    "Quattro prospettive per l'auto-osservazione",
                                    List.of(
                                            "Le quattro aree organizzano comportamenti quotidiani e non sono fattori scientificamente confermati. Seguono la sequenza editoriale notare, chiarire, riesaminare e usare, senza formare una gerarchia o una classifica personale."
                                    ),
                                    List.of(
                                            "Riconoscimento degli stati interni: cambiamenti emotivi, segnali corporei, pensieri, bisogno di pausa ed energia percepita.",
                                            "Chiarezza su bisogni, valori e motivazioni: ciò che conta, desideri, attese percepite, condizioni di agio e significato.",
                                            "Riflessione su schemi e funzionamento personale: reazioni ricorrenti, intenzioni, azioni, feedback e condizioni che facilitano o ostacolano.",
                                            "Uso della conoscenza di sé nelle scelte: priorità, adattamento degli obiettivi, strategie, limiti, preparazione e verifica degli esiti."
                                    )
                            ),
                            new GuideSection(
                                    "Corpo e contesto",
                                    "Percepire un segnale non significa interpretarlo con precisione",
                                    List.of(
                                            "Uno studio italiano sull'interocezione ha distinto la consapevolezza corporea autoriferita dall'accuratezza in un compito sul battito cardiaco, trovando una sostanziale indipendenza tra le misure. Il campione era formato soprattutto da studentesse di psicologia e non rappresenta tutti gli adulti italiani, ma mostra perché una risposta soggettiva non va trasformata in prova di accuratezza.",
                                            "Tempo, privacy, sicurezza, cultura, lingua, stress, umore, sonno, dolore, neurodivergenza e disabilità cambiano ciò che è disponibile all'attenzione. In un contesto minaccioso o urgente, concentrarsi sull'esterno può essere appropriato; una frequenza bassa non significa bassa intelligenza."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Auto-osservazione",
                                    "Confrontare ipotesi, azioni ed esiti",
                                    List.of(
                                            "Può essere utile annotare separatamente situazione, stato percepito, interpretazione, azione ed esito. Tornare in seguito sulla nota permette di vedere se una spiegazione regge, cambia o richiede informazioni diverse, senza cercare una coerenza perfetta.",
                                            "Puoi anche confrontare ciò che avevi previsto con ciò che è accaduto o chiedere un feedback specifico a una persona affidabile. Sono pratiche di osservazione, non trattamenti né metodi dimostrati per aumentare un tipo di intelligenza; se la riflessione diventa ripetitiva e non aggiunge informazioni, fermarsi o cambiare canale può essere più utile che insistere."
                                    ),
                                    List.of()
                            ),
                            new GuideSection(
                                    "Supporto e limiti",
                                    "Quando serve una valutazione diversa",
                                    List.of(
                                            "Se noti un cambiamento nella percezione di te, confusione persistente, pensieri ripetitivi o difficoltà nelle decisioni e questo causa disagio o interferisce con la vita quotidiana, parlane con uno psicologo, psicoterapeuta, medico o altro professionista qualificato. Una valutazione appropriata considera storia, contesto, sicurezza e obiettivi e usa strumenti scelti per la domanda specifica.",
                                            "La ricerca non ha fornito una validazione italiana pertinente di questo self-report adulto sull'intelligenza intrapersonale. Le 24 domande, le quattro aree e le soglie di Spazio Test sono originali e non validate; le barre mostrano soltanto frequenze relative, non intelligenza, accuratezza, benessere, probabilità, percentili o confronti con altre persone."
                                    ),
                                    List.of()
                            )
                    ),
                    "Il questionario collegato usa la cornice di Gardner soltanto come punto di partenza per osservare la frequenza percepita di riconoscimento, chiarezza, riflessione e uso delle informazioni su di sé. Non misura intelligenza generale o accuratezza, non certifica talenti o limiti e non diagnostica condizioni.",
                    List.of(
                            new GuideReference(
                                    "The Theory of Multiple Intelligences — Project Zero, Harvard",
                                    "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf",
                                    "Sintesi istituzionale consultata per la definizione del dominio intrapersonale e l'elenco di otto intelligenze identificate; presenta la teoria e non costituisce una validazione indipendente."
                            ),
                            new GuideReference(
                                    "A Resurgence of Interest in Existential Intelligence: Why Now? — Howard Gardner",
                                    "https://www.howardgardner.com/howards-blog/a-resurgence-of-interest-in-existential-intelligence-why-now",
                                    "Fonte primaria dell'autore consultata per precisare che l'intelligenza esistenziale fu proposta come possibile nona candidata, senza trattare il numero nove come conclusione empirica."
                            ),
                            new GuideReference(
                                    "Beyond g: Putting multiple intelligences theory to the test — Visser, Ashton e Vernon",
                                    "https://doi.org/10.1016/j.intell.2006.02.004",
                                    "Studio su prove di prestazione in adulti consultato per correlazioni tra domini, fattore generale e modesto supporto specifico; campione canadese e operazionalizzazione sono limiti espliciti."
                            ),
                            new GuideReference(
                                    "The Science of Multiple Intelligences Theory: A Response to Lynn Waterhouse — Gardner e Moran",
                                    "https://doi.org/10.1207/s15326985ep4104_2",
                                    "Risposta teorica consultata per rappresentare la posizione degli autori sulle finalità e sull'operazionalizzazione della teoria; non è una conferma indipendente della sua struttura."
                            ),
                            new GuideReference(
                                    "The Self-Reflection and Insight Scale — Grant, Franklin e Langford",
                                    "https://doi.org/10.2224/sbp.2002.30.8.821",
                                    "Studio originario consultato per distinguere autoriflessione e insight nel processo di autoregolazione; la scala e i suoi item non sono stati copiati né adattati."
                            ),
                            new GuideReference(
                                    "The Self-Reflection and Insight Scale — Italian Version — Di Fabio e Svicher",
                                    "https://doi.org/10.14605/CS1532206",
                                    "Primo studio italiano consultato per la distinzione tra riflessione e insight; i 112 studenti toscani e il disegno esplorativo limitano la trasferibilità."
                            ),
                            new GuideReference(
                                    "The Introspection Illusion — Pronin",
                                    "https://doi.org/10.1016/S0065-2601(08)00401-2",
                                    "Revisione teorica consultata per i limiti dell'accesso introspettivo alle cause di giudizi e comportamenti; non implica che ogni introspezione sia errata o inutile."
                            ),
                            new GuideReference(
                                    "Investigating interoceptive accuracy and awareness — Calì e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/26379571/",
                                    "Studio italiano consultato per distinguere consapevolezza corporea autoriferita e accuratezza a un compito; il campione studentesco prevalentemente femminile non è rappresentativo."
                            ),
                            new GuideReference(
                                    "Psychometric validation of the Italian Emotional Style Questionnaire — Malandrone e colleghi",
                                    "https://pubmed.ncbi.nlm.nih.gov/36459526/",
                                    "Validazione italiana consultata per contestualizzare la self-awareness come componente di un altro modello emotivo; struttura e risultati non sono trasferiti al test dell'app."
                            )
                    )
            )
    );

    public List<InformationGuide> findAll() {
        return guides;
    }

    public Optional<InformationGuide> findBySlug(String slug) {
        return guides.stream().filter(guide -> guide.slug().equals(slug)).findFirst();
    }

    public Optional<InformationGuide> findByTestId(String testId) {
        return guides.stream().filter(guide -> guide.testId().equals(testId)).findFirst();
    }
}
