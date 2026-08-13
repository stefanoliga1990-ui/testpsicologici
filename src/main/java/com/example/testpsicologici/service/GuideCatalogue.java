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
