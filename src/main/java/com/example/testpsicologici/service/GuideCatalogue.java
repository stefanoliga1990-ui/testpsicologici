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
                                    "https://sites.usnh.edu/jdmayer/wp-content/uploads/sites/261/2024/03/rp2016-mayer-caruso-salovey.pdf",
                                    "Fonte primaria per la definizione dell'intelligenza emotiva come abilità, le quattro aree di percezione, uso, comprensione e regolazione e la distinzione tra prove di abilità e autovalutazioni."
                            ),
                            new GuideReference(
                                    "Emotional Intelligence: New Ability or Eclectic Traits? — Mayer, Salovey e Caruso",
                                    "https://cdn2.psychologytoday.com/assets/attachments/1575/rp2008-mayersaloveycarusob.pdf",
                                    "Approfondimento scientifico consultato per distinguere il modello di abilità dagli approcci che riuniscono tratti e qualità personali più ampie sotto la stessa etichetta."
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
                                            "Tratti come ricerca di conferme, egocentrismo o sensibilità alle critiche possono comparire anche senza un disturbo di personalità. Una valutazione attendibile richiede il coinvolgimento diretto della persona interessata e un professionista qualificato: la percezione del partner può descrivere la relazione, non stabilire una diagnosi."
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
                                    "What Is Narcissistic Personality Disorder? — American Psychiatric Association",
                                    "https://www.psychiatry.org/News-room/APA-Blogs/What-Is-Narcissistic-Personality-Disorder",
                                    "Fonte istituzionale consultata per distinguere l'uso comune del termine narcisista, la presenza di singoli tratti e un disturbo persistente e problematico in più contesti."
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
