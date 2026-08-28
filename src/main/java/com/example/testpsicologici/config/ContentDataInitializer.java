package com.example.testpsicologici.config;

import com.example.testpsicologici.persistence.InterpretationEntity;
import com.example.testpsicologici.persistence.InterpretationRepository;
import com.example.testpsicologici.persistence.TestAreaEntity;
import com.example.testpsicologici.persistence.TestAreaRepository;
import com.example.testpsicologici.persistence.TestDefinitionEntity;
import com.example.testpsicologici.persistence.TestDefinitionRepository;
import com.example.testpsicologici.persistence.TestQuestionEntity;
import com.example.testpsicologici.persistence.TestQuestionRepository;
import com.example.testpsicologici.persistence.TestReferenceEntity;
import com.example.testpsicologici.persistence.TestReferenceRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ContentDataInitializer implements ApplicationRunner {

    private final TestDefinitionRepository testRepository;
    private final TestAreaRepository areaRepository;
    private final TestQuestionRepository questionRepository;
    private final TestReferenceRepository referenceRepository;
    private final InterpretationRepository interpretationRepository;

    public ContentDataInitializer(TestDefinitionRepository testRepository, TestAreaRepository areaRepository,
                                  TestQuestionRepository questionRepository,
                                  TestReferenceRepository referenceRepository,
                                  InterpretationRepository interpretationRepository) {
        this.testRepository = testRepository;
        this.areaRepository = areaRepository;
        this.questionRepository = questionRepository;
        this.referenceRepository = referenceRepository;
        this.interpretationRepository = interpretationRepository;
    }

    @Override
    @Transactional
    public void run(ApplicationArguments args) {
        removeTest("vera-web-app");
        removeTest("equilibrio-quotidiano");
        seedAutismInformationTest();
        seedAdhdInformationTest();
        seedObsessiveCompulsiveInformationTest();
        seedSelfEsteemInformationTest();
        seedEmotionalDependenceInformationTest();
        seedAssertivenessInformationTest();
        seedEmotionalIntelligenceInformationTest();
        seedPerfectionismInformationTest();
        seedSocialAnxietyInformationTest();
        seedPerceivedNarcissisticRelationshipDynamicsTest();
        seedGeneralizedAnxietyInformationTest();
        seedDepressedMoodInformationTest();
        seedPeoplePleasingInformationTest();
        seedImpostorPhenomenonInformationTest();
        seedSelfSabotageInformationTest();
        seedBorderlineTraitsInformationTest();
        seedFearOfAbandonmentInformationTest();
        seedFomoInformationTest();
        seedLinguisticIntelligenceInformationTest();
        seedIntrapersonalIntelligenceInformationTest();
        seedPsychologicalResilienceInformationTest();
        seedPartnerJealousyInformationTest();
        seedLifeSatisfactionInformationTest();
        seedPtsdInformationTest();
        seedAttachmentStylesInformationTest();
        seedLimerenceInformationTest();
        seedParentificationInformationTest();
        seedGaslightingInformationTest();
        seedLoveBombingInformationTest();
        seedBreadcrumbingInformationTest();
        seedOrbitingInformationTest();
        synchronizeEvidenceReferences();
    }

    private void seedAutismInformationTest() {
        String id = "tratti-autistici-adulti";
        String version = "2.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Tratti autistici nell'adulto",
                "Autovalutazione informativa",
                "Questionario informativo per adulti su comunicazione sociale, segnali impliciti, routine, flessibilità, interessi e sensibilità sensoriale.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Le esperienze descritte possono avere spiegazioni diverse e il risultato non conferma né esclude l'autismo. Per una valutazione è necessario rivolgersi a professionisti qualificati.",
                version, false, true, 1).withSeo(
                "Test autismo adulti online: questionario | Spazio Test",
                "Questionario informativo per adulti su comunicazione sociale, routine e sensibilità sensoriale. 24 domande, 6 minuti, senza registrazione; non diagnostico.")
                .withResponseInstruction("Pensando a come ti capita abitualmente, con quale frequenza riconosci questa esperienza?"));

        saveReference(id, "Clinical testing and diagnosis for autism spectrum disorder — CDC",
                "https://www.cdc.gov/autism/hcp/diagnosis/index.html", 1);
        saveReference(id, "Autism spectrum disorder in adults: diagnosis and management — NICE CG142",
                "https://www.nice.org.uk/guidance/cg142/chapter/Recommendations", 2);

        saveArea(id, "sociale", "Interazione sociale e reciprocità emotiva", 1);
        saveArea(id, "non_verbale", "Comunicazione non verbale e comprensione implicita", 2);
        saveArea(id, "routine", "Routine, flessibilità e comportamenti ripetitivi", 3);
        saveArea(id, "sensoriale", "Interessi focalizzati e sensibilità sensoriale", 4);

        saveQuestions(id, List.of(
                q("sociale", "Nelle conversazioni di gruppo mi è difficile capire quando è il momento di parlare."),
                q("sociale", "Dopo un incontro sociale ripenso a ciò che ho detto per capire se mi sono comportato come previsto."),
                qe("sociale", "Trovo faticoso mantenere una relazione quando le aspettative reciproche non vengono espresse chiaramente.",
                        "non so quanto spesso cercare l'altra persona, proporre un incontro o mostrare vicinanza."),
                q("sociale", "Quando qualcuno condivide un'emozione, non sempre capisco quale risposta si aspetta da me."),
                qe("sociale", "Durante interazioni sociali prolungate devo mantenere uno sforzo consapevole per partecipare.",
                        "seguo intenzionalmente i turni, preparo le risposte o controllo come sto apparendo."),
                q("sociale", "Quando una conversazione cambia rapidamente tono o argomento, mi è difficile adattare il mio modo di partecipare."),
                q("non_verbale", "Mi è difficile capire cosa prova una persona basandomi soltanto sull'espressione del viso o sul tono di voce."),
                q("non_verbale", "Durante una conversazione devo pensare consapevolmente a quanto contatto visivo mantenere."),
                q("non_verbale", "Interpreto in modo letterale battute, allusioni o richieste formulate indirettamente."),
                q("non_verbale", "Preparo mentalmente parole, espressioni o tono di voce prima di affrontare alcune situazioni sociali."),
                qe("non_verbale", "Gesti, posture o regole sociali non dette possono risultarmi difficili da interpretare.",
                        "devo capire quando salutare, quanto avvicinarmi o quando lasciare spazio."),
                q("non_verbale", "Mi capita di non accorgermi che qualcuno vuole concludere o cambiare una conversazione finché non lo dice chiaramente."),
                q("routine", "Un cambiamento imprevisto nei programmi mi richiede molto tempo per adattarmi."),
                q("routine", "Preferisco svolgere alcune attività seguendo ogni volta lo stesso ordine o la stessa procedura."),
                q("routine", "Passare rapidamente da un'attività a un'altra può essere difficile, anche quando so cosa devo fare."),
                qe("routine", "Ripetere piccoli movimenti o gesti mi aiuta a calmarmi.",
                        "tamburellare con le dita, dondolarmi o manipolare un oggetto."),
                q("routine", "Affronto meglio una situazione nuova quando posso conoscerne in anticipo dettagli e passaggi."),
                q("routine", "Se non posso completare un'attività nel modo che avevo previsto, faccio fatica a lasciarla e passare oltre."),
                q("sensoriale", "Mi concentro su un interesse fino a perdere la percezione del tempo."),
                q("sensoriale", "Alcuni miei interessi occupano molto spazio nei miei pensieri e mi spingono ad approfondire ogni dettaglio."),
                q("sensoriale", "Suoni, luci, odori, tessuti o temperature che altri tollerano possono risultarmi molto intensi o distraenti."),
                q("sensoriale", "Cerco volontariamente particolari sensazioni, movimenti, consistenze o suoni perché mi fanno stare bene."),
                q("sensoriale", "Dedicarmi ai miei interessi più intensi mi dà una sensazione importante di calma, energia o stabilità."),
                q("sensoriale", "Dopo un ambiente molto rumoroso, affollato o luminoso, ho bisogno di tempo in tranquillità per recuperare.")));

        saveGlobal(id, "LOW", "I tratti autistici esplorati sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le esperienze esplorate nelle quattro aree. Comunicazione sociale, comprensione implicita, bisogno di prevedibilità e sensibilità sensoriale non formano, nelle tue risposte, un insieme ampio e ricorrente.",
                "Questo profilo può indicare che tali esperienze sono occasionali, circoscritte oppure attualmente poco rilevanti nella tua vita. Non esclude caratteristiche non rappresentate dagli item, strategie di adattamento molto consolidate o difficoltà che emergono soltanto in contesti specifici. Per comprendere un eventuale dubbio contano storia dello sviluppo, continuità nel tempo e impatto quotidiano: il risultato non conferma né esclude l'autismo e non sostituisce una valutazione professionale.");
        saveGlobal(id, "MIXED", "I tratti autistici esplorati sembrano presenti in modo variabile",
                "Le tue risposte descrivono esperienze presenti in modo diverso tra comunicazione, segnali impliciti, routine e sensibilità sensoriale. Alcuni aspetti sembrano riconoscibili, mentre altri compaiono poco o soprattutto in determinate situazioni.",
                "Un profilo non uniforme può dipendere dalle richieste dell'ambiente, dalla familiarità delle situazioni, dalla stanchezza o dalle strategie utilizzate per adattarti. Esperienze simili possono inoltre essere condivise con ansia, ADHD, stress, differenze comunicative o sensoriali e non indicano da sole autismo. Osserva quali aree emergono sotto, da quando le riconosci e se richiedono uno sforzo significativo: la distribuzione è più informativa della sola media complessiva.");
        saveGlobal(id, "FOCUSED", "I tratti autistici esplorati sembrano più presenti in una o due aree",
                "Le tue risposte mettono in evidenza esperienze ricorrenti in una o due aree, mentre il resto del profilo appare meno coinvolto. La presenza complessiva dipende quindi soprattutto da aspetti specifici e non da un andamento uniforme tra tutte le dimensioni esplorate.",
                "La lettura delle singole aree può chiarire se il nucleo riguarda soprattutto interazione sociale, comunicazione implicita, flessibilità oppure interessi e sensibilità sensoriale. È utile osservare quando compare, se era riconoscibile anche nelle fasi precedenti della vita e quale costo comporta in contesti diversi. Un'area marcata può meritare attenzione, ma non permette da sola di formulare o escludere una diagnosi di autismo.");
        saveGlobal(id, "BROAD", "I tratti autistici esplorati sembrano frequentemente presenti in più aree",
                "Le tue risposte indicano una presenza frequente delle esperienze esplorate in almeno tre aree del questionario. Il profilo coinvolge quindi più aspetti tra comunicazione sociale, comprensione implicita, prevedibilità, interessi e sensibilità sensoriale, anziché concentrarsi in un solo ambito.",
                "Un andamento ampio rende utile considerare continuità fin dall'infanzia, richieste dei diversi ambienti, strategie di compensazione e conseguenze su energia, relazioni, studio, lavoro o autonomia. Anche ansia, ADHD, stress e altre condizioni possono contribuire a esperienze sovrapposte e devono essere considerate. Se il profilo descrive una fatica significativa o bisogni di supporto, puoi parlarne con un professionista esperto di autismo nell'adulto; il risultato resta informativo e non diagnostico.");

        saveAreaInsights(id, "sociale",
                "Nelle situazioni sociali riferisci generalmente poca difficoltà nel seguire lo scambio e comprendere cosa ci si aspetta reciprocamente.",
                "Le interazioni sociali sembrano richiederti uno sforzo variabile: alcune risultano naturali, mentre altre possono portarti ad analizzare consapevolmente ciò che accade o a recuperare energie dopo.",
                "Le tue risposte indicano che comprendere le aspettative reciproche, trovare il proprio spazio nella conversazione o sostenere interazioni prolungate può richiederti uno sforzo significativo.");
        saveAreaInsights(id, "non_verbale",
                "Tono di voce, espressioni, gesti e significati impliciti sembrano esserti in genere accessibili senza un particolare sforzo consapevole.",
                "In alcune situazioni potresti dover prestare attenzione in modo intenzionale a segnali non verbali, ironia, allusioni o regole sociali non espresse.",
                "Le risposte suggeriscono che interpretare segnali non verbali o impliciti e coordinare consapevolmente il tuo modo di comunicare può essere spesso impegnativo.");
        saveAreaInsights(id, "routine",
                "Cambiamenti, transizioni e modi diversi di svolgere le attività sembrano generalmente gestibili senza un forte bisogno di preparazione o ripetizione.",
                "Prevedibilità e continuità possono aiutarti in alcune circostanze, soprattutto quando cambiamenti o passaggi rapidi richiedono più tempo per essere elaborati.",
                "Le tue risposte mostrano un bisogno marcato di prevedibilità o continuità; routine e azioni ripetitive possono avere un ruolo importante nel ritrovare calma, concentrazione o stabilità.");
        saveAreaInsights(id, "sensoriale",
                "Stimoli sensoriali e interessi personali sembrano incidere in modo contenuto sulla tua attenzione e sull'organizzazione della giornata.",
                "Alcuni stimoli o interessi possono assumere per te una particolare intensità, offrendo benessere oppure richiedendo adattamenti in determinati contesti.",
                "Le risposte indicano interessi molto assorbenti e/o una sensibilità sensoriale significativa; questi aspetti possono essere fonti di benessere e competenza, ma anche richiedere protezione da sovraccarico e interruzioni.");
    }

    private void seedAdhdInformationTest() {
        String id = "tratti-adhd-adulti";
        String version = "2.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "ADHD nell'adulto: tratti associati",
                "Autovalutazione informativa",
                "Esplora difficoltà ricorrenti relative ad attenzione, organizzazione, gestione del tempo, impulsività e irrequietezza nell'adulto.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Rispondi pensando agli ultimi sei mesi; difficoltà simili possono dipendere anche da sonno, stress o altre condizioni. Una diagnosi di ADHD richiede una valutazione specialistica della storia e dell'impatto in più contesti.",
                version, false, true, 2).withSeo(
                "Test ADHD adulti online: questionario | Spazio Test",
                "Questionario informativo per adulti su attenzione, organizzazione, impulsività e irrequietezza. 24 domande, 6 minuti, senza registrazione; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi sei mesi, con quale frequenza ti è capitato?"));

        saveReference(id, "Attention deficit hyperactivity disorder: diagnosis and management — NICE NG87",
                "https://www.nice.org.uk/guidance/ng87/chapter/recommendations", 1);
        saveReference(id, "ADHD in adults — NHS",
                "https://www.nhs.uk/conditions/adhd-adults/", 2);

        saveArea(id, "attenzione", "Attenzione sostenuta e distraibilità", 1);
        saveArea(id, "organizzazione", "Organizzazione, memoria operativa e gestione del tempo", 2);
        saveArea(id, "impulsivita", "Impulsività e controllo della risposta", 3);
        saveArea(id, "irrequietezza", "Irrequietezza e bisogno di stimolazione", 4);

        saveQuestions(id, List.of(
                q("attenzione", "Durante una lettura prolungata, la mia mente si sposta altrove anche quando cerco di restare concentrato."),
                q("attenzione", "Rumori, notifiche o pensieri improvvisi interrompono facilmente quello che sto facendo."),
                q("attenzione", "Nelle attività che richiedono precisione mi capita di tralasciare dettagli o commettere errori di distrazione."),
                q("attenzione", "Fatico a mantenere l'attenzione su attività lunghe, ripetitive o poco stimolanti."),
                q("attenzione", "Inizio una nuova attività prima di aver concluso quella precedente perché la mia attenzione si è già spostata."),
                q("attenzione", "Durante spiegazioni o istruzioni articolate perdo alcuni passaggi anche quando sto cercando di ascoltare."),
                q("organizzazione", "Sottovaluto il tempo necessario per completare attività o spostamenti."),
                qe("organizzazione", "Mi è difficile suddividere un compito complesso in passaggi e decidere da dove iniziare.",
                        "devo stabilire il primo passo, l'ordine delle attività e le scadenze intermedie."),
                q("organizzazione", "Dimentico appuntamenti, scadenze o impegni anche quando per me sono importanti."),
                q("organizzazione", "Perdo o cerco spesso oggetti necessari, come chiavi, documenti o telefono."),
                q("organizzazione", "Rimando attività che richiedono concentrazione finché l'urgenza non mi costringe a iniziare."),
                qe("organizzazione", "Mi accorgo troppo tardi che più impegni, attività o scadenze si sovrappongono.",
                        "accetto un impegno e solo dopo noto che coincide con un altro."),
                q("impulsivita", "Interrompo le persone o completo le loro frasi prima che abbiano finito di parlare."),
                q("impulsivita", "Prendo decisioni rapidamente e ne valuto le conseguenze solo dopo."),
                q("impulsivita", "Aspettare il mio turno in una conversazione, una fila o un'attività mi richiede molto autocontrollo."),
                q("impulsivita", "Dico qualcosa d'impulso e poco dopo vorrei averci pensato più a lungo."),
                q("impulsivita", "Abbandono un piano per seguire un'idea o un impulso appena comparso, anche se avevo altre priorità."),
                q("impulsivita", "Nelle discussioni reagisco prima di aver compreso fino in fondo ciò che l'altra persona intende."),
                q("irrequietezza", "Quando devo restare fermo a lungo avverto un'irrequietezza interna difficile da ignorare."),
                q("irrequietezza", "Muovo mani o piedi, cambio spesso posizione o cerco occasioni per alzarmi."),
                qe("irrequietezza", "Mi è difficile riposare senza occuparmi contemporaneamente di qualcos'altro.",
                        "mentre guardo un film o riposo, controllo il telefono o avvio un'altra attività."),
                q("irrequietezza", "Quando un'attività non mi coinvolge, cerco rapidamente novità o stimoli più interessanti."),
                q("irrequietezza", "Attività molto lente o attese prolungate mi fanno sentire impaziente o agitato."),
                q("irrequietezza", "Mi sento come se dovessi essere in movimento o impegnato, anche nei momenti in cui vorrei rallentare.")));

        saveGlobal(id, "LOW", "Le esperienze associate all'ADHD sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le difficoltà esplorate nelle quattro aree. Distraibilità, organizzazione, gestione del tempo, irrequietezza e impulsività non formano, nelle tue risposte, un insieme ampio e ricorrente.",
                "Questo profilo può indicare che tali difficoltà sono occasionali, ben compensate o limitate ad attività particolari. Non esclude problemi recenti, condizioni ambientali sfavorevoli o aspetti non coperti dalle domande. Per comprendere un dubbio sull'ADHD contano esordio nell'infanzia, persistenza, presenza in più contesti e impatto funzionale: il risultato non conferma né esclude l'ADHD.");
        saveGlobal(id, "MIXED", "Le esperienze associate all'ADHD sembrano presenti in modo variabile",
                "Le tue risposte descrivono difficoltà che cambiano tra attenzione, organizzazione, irrequietezza e impulsività. Alcuni aspetti sembrano presenti, mentre altri emergono poco o soprattutto con attività e situazioni particolari.",
                "Sonno, stress, carico mentale, interesse, struttura dell'ambiente e richieste del compito possono modificare molto attenzione e autoregolazione. Anche ansia, umore, uso di sostanze o condizioni fisiche possono produrre esperienze simili o accentuarle. Osserva quali aree emergono sotto, se il modello era presente già nell'infanzia e se compare in più contesti: le risposte da sole non permettono di formulare una diagnosi.");
        saveGlobal(id, "FOCUSED", "Le esperienze associate all'ADHD sembrano più presenti in una o due aree",
                "Le tue risposte mettono in evidenza difficoltà ricorrenti in una o due aree, mentre gli altri aspetti risultano meno coinvolti. La presenza complessiva dipende quindi soprattutto da un nucleo specifico, non da difficoltà uniformi in tutto il profilo.",
                "Le schede di area possono mostrare se il nucleo riguarda attenzione sostenuta, pianificazione e memoria operativa, irrequietezza oppure impulsività. È utile verificare da quanto tempo è presente, quali supporti lo riducono e quanto incide su lavoro, studio, relazioni o gestione quotidiana. Un profilo circoscritto può comunque essere importante, ma non conferma né esclude l'ADHD.");
        saveGlobal(id, "BROAD", "Le esperienze associate all'ADHD sembrano frequentemente presenti in più aree",
                "Le tue risposte indicano difficoltà frequenti in almeno tre aree tra attenzione, organizzazione, gestione del tempo, irrequietezza e impulsività. Il profilo appare quindi esteso a più processi di autoregolazione e non limitato a una sola situazione.",
                "Per interpretarlo contano l'eventuale presenza fin dall'infanzia, la continuità in ambienti diversi e le conseguenze concrete su responsabilità, relazioni, studio, lavoro o sicurezza. Sonno, stress, ansia, depressione, sostanze e condizioni fisiche possono contribuire e richiedono una lettura distinta. Se le difficoltà sono persistenti e interferiscono con la vita quotidiana, puoi valutare una consulenza con un professionista esperto di ADHD nell'adulto; il risultato resta informativo e non diagnostico.");

        saveAreaInsights(id, "attenzione",
                "Riferisci generalmente una buona continuità dell'attenzione, con distrazioni o cali di concentrazione piuttosto occasionali.",
                "La tua capacità di mantenere l'attenzione sembra variare in base a durata, interesse, complessità e quantità di stimoli presenti.",
                "Le risposte indicano una distraibilità frequente o una marcata difficoltà nel mantenere l'attenzione, soprattutto in attività lunghe, ripetitive o ricche di dettagli.");
        saveAreaInsights(id, "organizzazione",
                "Organizzare passaggi, ricordare impegni e stimare i tempi sembra crearti difficoltà solo occasionalmente.",
                "Gestione del tempo, memoria degli impegni e avvio dei compiti possono richiederti strategie o strumenti esterni in alcune situazioni.",
                "Le tue risposte mostrano difficoltà ricorrenti nell'organizzare attività, stimare i tempi, ricordare impegni o iniziare compiti che richiedono uno sforzo mentale prolungato.");
        saveAreaInsights(id, "impulsivita",
                "Nelle tue risposte emerge generalmente la capacità di attendere e valutare prima di parlare, agire o cambiare programma.",
                "In alcune circostanze potresti rispondere o decidere rapidamente, soprattutto quando sei coinvolto, impaziente o sotto pressione.",
                "Le risposte suggeriscono che trattenere una risposta, attendere il proprio turno o considerare le conseguenze prima di agire può richiederti spesso uno sforzo significativo.");
        saveAreaInsights(id, "irrequietezza",
                "Irrequietezza e bisogno di nuovi stimoli sembrano comparire in modo contenuto e generalmente gestibile.",
                "Il bisogno di movimento o stimolazione può aumentare durante attese, attività lente o momenti poco coinvolgenti.",
                "Le tue risposte indicano una sensazione frequente di irrequietezza o un forte bisogno di movimento, attività e stimolazione, anche nei momenti destinati alla pausa.");
    }

    private void seedObsessiveCompulsiveInformationTest() {
        String id = "tratti-ossessivo-compulsivi";
        String version = "1.7";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Pensieri ossessivi e compulsioni (DOC)",
                "Autovalutazione informativa",
                "Esplora pensieri intrusivi, dubbio, bisogno di certezza, contaminazione, controlli e rituali nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Rispondi pensando all'ultimo mese; pensieri indesiderati o dubbi non indicano l'intenzione di agire. Una valutazione del DOC considera anche disagio, tempo occupato, interferenza e spiegazioni alternative.",
                version, false, true, 3).withSeo(
                "Test disturbo ossessivo-compulsivo (DOC) online | Spazio Test",
                "Questionario informativo su pensieri ossessivi, compulsioni, dubbi, controlli e rituali. 24 domande, circa 6 minuti, senza registrazione; non diagnostico.")
                .withResponseInstruction("Pensando all'ultimo mese, con quale frequenza ti è capitato?"));

        saveReference(id, "Obsessive compulsive disorder (OCD): symptoms — NHS",
                "https://www.nhs.uk/mental-health/conditions/obsessive-compulsive-disorder-ocd/symptoms/", 1);
        saveReference(id, "Obsessive-Compulsive Disorder: When Unwanted Thoughts or Repetitive Behaviors Take Over — NIMH",
                "https://www.nimh.nih.gov/health/publications/obsessive-compulsive-disorder-when-unwanted-thoughts-or-repetitive-behaviors-take-over", 2);

        saveArea(id, "intrusioni", "Pensieri intrusivi, dubbio e bisogno di certezza", 1);
        saveArea(id, "contaminazione", "Contaminazione, pulizia ed evitamento", 2);
        saveArea(id, "controllo", "Controllo, responsabilità e prevenzione del danno", 3);
        saveArea(id, "rituali", "Ordine, simmetria, ripetizione e rituali mentali", 4);

        saveQuestions(id, List.of(
                q("intrusioni", "Pensieri, immagini o impulsi indesiderati tornano nella mia mente anche quando cerco di lasciarli andare."),
                q("intrusioni", "Dubito di aver fatto qualcosa correttamente anche dopo averlo verificato."),
                qe("intrusioni", "Mi soffermo sul significato di un pensiero indesiderato e temo che dica qualcosa di negativo su di me.",
                        "temo che averlo pensato significhi che potrei volerlo davvero."),
                q("intrusioni", "Ho bisogno di sentirmi completamente certo prima di considerare chiusa una decisione o una situazione."),
                q("intrusioni", "Cerco rassicurazioni da altre persone per calmare dubbi che tendono a ripresentarsi."),
                q("intrusioni", "Evito persone, luoghi o situazioni perché potrebbero attivare pensieri o dubbi che mi mettono a disagio."),
                q("contaminazione", "Temo che oggetti, superfici o contatti possano contaminarmi anche quando il rischio sembra limitato."),
                q("contaminazione", "Lavo le mani, il corpo o alcuni oggetti più a lungo o più spesso di quanto avevo previsto per sentirmi al sicuro."),
                q("contaminazione", "Dopo essermi lavato o aver pulito qualcosa, il dubbio di non essere davvero pulito tende a tornare."),
                q("contaminazione", "Evito di toccare oggetti comuni o di frequentare alcuni luoghi per paura della contaminazione."),
                q("contaminazione", "Separo o maneggio vestiti e oggetti secondo regole precise per evitare di diffondere sporco o contaminazione."),
                q("contaminazione", "Pulizia, lavaggi o precauzioni contro la contaminazione rallentano o interrompono le mie attività."),
                q("controllo", "Controllo più volte di aver chiuso una porta anche quando so di averlo già verificato."),
                qe("controllo", "Mi sento particolarmente responsabile di impedire eventi negativi, anche quando sono poco probabili.",
                        "se non ricontrollo qualcosa, mi sento responsabile di ciò che potrebbe accadere."),
                q("controllo", "Torno sui miei passi o riapro un'attività per assicurarmi di non aver lasciato un pericolo o un errore."),
                qe("controllo", "Ripercorro mentalmente ciò che ho fatto per verificare di non aver causato danni o conseguenze indesiderate.",
                        "ripasso una conversazione, un tragitto o un'azione per accertarmi di non aver commesso un errore."),
                q("controllo", "Ripeto un controllo finché non provo una sensazione sufficiente di sicurezza, anche se i fatti non sono cambiati."),
                q("controllo", "Uscire di casa o concludere un compito può richiedermi più tempo a causa delle verifiche che sento di dover fare."),
                q("rituali", "Sistemo gli oggetti finché l'ordine o la simmetria non mi sembrano esattamente giusti."),
                q("rituali", "Provo un forte disagio quando qualcosa appare asimmetrico, incompleto o fuori posto."),
                q("rituali", "Conto, ripeto parole o formulo pensieri particolari per neutralizzare un dubbio o ridurre l'ansia."),
                q("rituali", "Ripeto gesti o tocco oggetti secondo una sequenza o un numero preciso di volte."),
                q("rituali", "Se una sequenza viene interrotta o non mi sembra eseguita correttamente, sento il bisogno di ricominciare."),
                q("rituali", "Dedico tempo a rendere un'azione esatta o perfetta anche quando non produce un vantaggio pratico.")));

        saveGlobal(id, "LOW", "Le esperienze ossessivo-compulsive sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza pensieri intrusivi, dubbi, controlli e rituali nelle aree esplorate. Le risposte non descrivono un ciclo ossessivo-compulsivo ampio e ricorrente tra contenuti e comportamenti diversi.",
                "Pensieri indesiderati, ricontrolli e preferenze per l'ordine possono comparire occasionalmente in molte persone. Un profilo contenuto non esclude però esperienze molto specifiche, recenti o non incluse negli item. Per una valutazione contano soprattutto disagio, tempo occupato, difficoltà a interrompere il ciclo ed effetto sulla vita: il risultato non conferma né esclude un disturbo ossessivo-compulsivo.");
        saveGlobal(id, "MIXED", "Le esperienze ossessivo-compulsive sembrano presenti in modo variabile",
                "Le tue risposte descrivono pensieri o comportamenti ripetitivi presenti in modo diverso tra le aree esplorate. Alcuni contenuti sembrano riconoscibili, mentre altri compaiono poco o soprattutto in particolari momenti e situazioni.",
                "Stress, ansia, responsabilità percepita, disgusto e bisogno di certezza possono intensificare dubbi e rituali senza produrre un quadro uniforme. È utile distinguere le abitudini preferite dai comportamenti sentiti come obbligati e osservare sollievo, durata e interferenza. Le schede sotto chiariscono dove si concentra il profilo, ma le risposte da sole non permettono di formulare una diagnosi.");
        saveGlobal(id, "FOCUSED", "Le esperienze ossessivo-compulsive sembrano più presenti in una o due aree",
                "Le tue risposte mettono in evidenza pensieri o comportamenti ricorrenti in una o due aree, mentre il resto del profilo appare meno coinvolto. La presenza complessiva è quindi sostenuta soprattutto da specifici temi o rituali, non da un andamento diffuso.",
                "L'analisi per area può indicare se emergono maggiormente intrusioni e dubbio, contaminazione, controllo oppure ordine e rituali mentali. Osserva quanto tempo richiedono, quale emozione cercano di ridurre e se determinano evitamenti, rassicurazioni o rallentamenti. Un profilo circoscritto può causare comunque sofferenza, ma non conferma né esclude un disturbo ossessivo-compulsivo.");
        saveGlobal(id, "BROAD", "Le esperienze ossessivo-compulsive sembrano frequentemente presenti in più aree",
                "Le tue risposte indicano pensieri intrusivi, dubbi o rituali frequenti in almeno tre aree del questionario. Il profilo coinvolge quindi più contenuti e modalità di neutralizzazione, controllo o evitamento anziché un'unica abitudine circoscritta.",
                "Un andamento ampio rende importante osservare tempo assorbito, sollievo temporaneo, difficoltà a resistere e conseguenze su autonomia e attività quotidiane. Ansia, depressione, stress, tic e altre condizioni possono coesistere o richiedere una spiegazione differente. Se le esperienze provocano forte disagio o interferiscono con relazioni, studio o lavoro, puoi parlarne con un professionista qualificato; il risultato resta informativo e non diagnostico.");

        saveAreaInsights(id, "intrusioni",
                "Pensieri indesiderati e dubbi sembrano generalmente passare senza occupare a lungo la tua attenzione o richiedere particolari rassicurazioni.",
                "In alcune situazioni pensieri intrusivi, dubbi o bisogno di certezza possono tornare e richiederti uno sforzo per lasciarli andare.",
                "Le risposte indicano pensieri intrusivi o dubbi ricorrenti e un forte bisogno di certezza, rassicurazione o evitamento per ridurre il disagio.");
        saveAreaInsights(id, "contaminazione",
                "Timori di contaminazione e attività di pulizia sembrano comparire in modo contenuto e generalmente proporzionato alla situazione.",
                "In alcuni contesti il timore di sporco o contaminazione può portarti a lavare, pulire, separare oggetti o evitare contatti più del previsto.",
                "Le risposte mostrano timori di contaminazione ricorrenti e rituali di pulizia o evitamento che possono richiedere tempo e limitare alcune attività.");
        saveAreaInsights(id, "controllo",
                "Controlli e preoccupazioni di aver causato errori o danni sembrano generalmente limitati e facili da concludere.",
                "In determinate situazioni potresti ripetere verifiche o ripercorrere mentalmente le tue azioni per raggiungere una sensazione sufficiente di sicurezza.",
                "Le risposte indicano controlli ripetuti e un forte senso di responsabilità nel prevenire errori o danni, anche dopo aver già verificato la situazione.");
        saveAreaInsights(id, "rituali",
                "Ordine, simmetria e piccole abitudini ripetitive sembrano incidere poco sul tuo tempo e sulla possibilità di cambiare sequenza.",
                "In alcune circostanze potresti cercare una sensazione di completezza attraverso ordine, conteggi, ripetizioni o rituali mentali.",
                "Le risposte mostrano un bisogno ricorrente di ordine, simmetria o ripetizione; sequenze e rituali mentali possono essere difficili da interrompere finché non sembrano eseguiti nel modo giusto.");
    }

    private void seedSelfEsteemInformationTest() {
        String id = "autostima";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Autostima",
                "Autovalutazione informativa",
                "Esplora il rapporto con il valore personale, la fiducia in sé, gli errori, le critiche e il confronto con gli altri.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e non clinicamente validato. Non misura il tuo valore come persona: descrive soltanto quanto spesso, negli ultimi tre mesi, hai riconosciuto alcune difficoltà nel rapporto con te stesso. Le risposte possono cambiare nel tempo e nei diversi contesti.",
                version, false,
                "Difficoltà complessive relative all'autostima",
                "Frequenza delle difficoltà",
                true, 4).withSeo(
                "Test autostima online: questionario | Spazio Test",
                "Questionario su valore personale, fiducia in sé, errori, critiche e confronto. 24 domande, 6 minuti, senza registrazione; non misura il tuo valore.")
                .withResponseInstruction("Pensando agli ultimi tre mesi, con quale frequenza ti è capitato?"));

        saveReference(id, "Rosenberg Self-Esteem Scale — University of Maryland",
                "https://socy.umd.edu/about-us/rosenberg-self-esteem-scale", 1);
        saveReference(id, "The Development of Self-Esteem",
                "https://doi.org/10.1177/0963721414547414", 2);

        saveArea(id, "valore", "Valore personale e autoaccettazione", 1);
        saveArea(id, "fiducia", "Fiducia personale ed espressione dei bisogni", 2);
        saveArea(id, "autocritica", "Autocritica e risposta agli errori", 3);
        saveArea(id, "approvazione", "Stabilità, confronto e bisogno di approvazione", 4);

        saveQuestions(id, List.of(
                q("valore", "Mi considero meno degno di rispetto o considerazione rispetto alle altre persone."),
                q("valore", "Quando penso ai miei difetti, faccio fatica a riconoscere anche le mie qualità."),
                q("valore", "Mi è difficile accettare parti di me che non corrispondono a come vorrei essere."),
                q("valore", "Quando ricevo un complimento, tendo a sminuirlo o a pensare che non sia davvero meritato."),
                q("valore", "Penso di avere poco di cui essere orgoglioso nel mio percorso personale."),
                qe("valore", "Parto da un singolo limite o difetto per giudicare negativamente il mio valore complessivo.",
                        "dopo un errore penso “sono incapace”, non soltanto “in questa occasione ho sbagliato”."),
                q("fiducia", "Rinuncio a provare qualcosa che mi interessa perché temo di non esserne capace."),
                q("fiducia", "Dubito delle mie decisioni anche dopo averle valutate con attenzione."),
                q("fiducia", "Ho bisogno che un'altra persona confermi la mia opinione prima di fidarmi del mio giudizio."),
                q("fiducia", "Evito di esprimere ciò che penso per paura che la mia opinione abbia poco valore."),
                q("fiducia", "Faccio fatica a dire di no o a proteggere i miei bisogni perché temo di non avere il diritto di farlo."),
                q("fiducia", "Considero gli errori una prova della mia incapacità, invece di valutarli come eventi specifici."),
                q("autocritica", "Dopo un errore mi rivolgo parole più dure di quelle che userei con una persona a cui voglio bene."),
                q("autocritica", "Ripenso a lungo ai miei sbagli anche quando non posso più modificarli."),
                q("autocritica", "Un insuccesso in un ambito mi fa sentire incapace anche in aspetti non collegati."),
                q("autocritica", "Pretendo da me stesso di non sbagliare anche quando sto imparando qualcosa di nuovo."),
                q("autocritica", "Mi è difficile perdonarmi per decisioni o comportamenti che oggi affronterei diversamente."),
                q("autocritica", "Se non raggiungo standard molto elevati, tendo a considerare privo di valore ciò che ho fatto."),
                qe("approvazione", "L'opinione che ho di me cambia sensibilmente dopo una critica o un commento negativo.",
                        "un commento negativo mi fa mettere in dubbio qualità che fino a poco prima riconoscevo."),
                q("approvazione", "Ho bisogno che i miei risultati vengano riconosciuti per sentirmi adeguato."),
                q("approvazione", "Cambio il mio modo di comportarmi per evitare la disapprovazione, anche quando non mi rappresenta."),
                q("approvazione", "Confrontare la mia vita o i miei risultati con quelli degli altri mi fa sentire inadeguato."),
                q("approvazione", "Cerco rassicurazioni o conferme dagli altri per riuscire a sentirmi a posto con me stesso."),
                qe("approvazione", "Un rifiuto, un'esclusione o un disaccordo mi porta a dubitare del mio valore personale.",
                        "interpreto un no a una mia proposta come un giudizio sul mio valore.")));

        saveGlobal(id, "LOW", "Le difficoltà legate all'autostima sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le difficoltà relative a valore personale, fiducia, autocritica e bisogno di approvazione. Le risposte descrivono un'immagine di te abbastanza stabile nelle diverse aree, senza una pressione diffusa sul senso di valore personale.",
                "Questo non significa sentirti sempre sicuro o non essere influenzato da errori, confronti e critiche. Il profilo suggerisce però che questi eventi tendono a non definire interamente il modo in cui ti consideri e che riesci generalmente a integrare qualità e limiti. Osserva comunque eventuali ambiti molto specifici non rappresentati dalla media: il questionario descrive le risposte attuali e non misura il tuo valore.");
        saveGlobal(id, "MIXED", "Le difficoltà legate all'autostima sembrano presenti in modo variabile",
                "Le tue risposte descrivono un rapporto con te stesso stabile in alcune aree e più vulnerabile in altre. Valore personale, fiducia, risposta agli errori e bisogno di conferme non si muovono quindi tutti nello stesso modo.",
                "Il tipo di compito, la relazione coinvolta, lo stress e il confronto con gli altri possono modificare temporaneamente il giudizio su di te. Individua nelle schede sotto dove l'equilibrio cambia e quali eventi trasformano un dubbio circoscritto in una valutazione globale della persona. Questa variabilità non definisce un livello fisso di autostima e può essere compresa meglio osservando situazioni e conseguenze concrete.");
        saveGlobal(id, "FOCUSED", "Le difficoltà legate all'autostima sembrano più presenti in una o due aree",
                "Le risposte evidenziano difficoltà ricorrenti in una o due aree, mentre il resto del profilo appare più stabile. La pressione sull'autostima sembra quindi concentrarsi su un meccanismo specifico anziché coinvolgere uniformemente ogni modo di valutarti.",
                "Le analisi per area possono chiarire se emergono soprattutto valore personale, espressione dei bisogni, risposta a errori e critiche oppure confronto e approvazione. Nota quali eventi attivano dubbi o autocritica, quanto durano e se influenzano decisioni che vanno oltre la situazione iniziale. Un nucleo circoscritto può meritare attenzione, ma questo questionario non è una valutazione clinica e non definisce il tuo valore personale.");
        saveGlobal(id, "BROAD", "Le difficoltà legate all'autostima sembrano frequentemente presenti in più aree",
                "Le tue risposte indicano difficoltà frequenti in almeno tre aree del rapporto con te stesso. Riconoscere il tuo valore, fidarti delle tue possibilità, attraversare errori e critiche e mantenere autonomia dal giudizio altrui sembrano quindi richiedere uno sforzo diffuso.",
                "Un profilo ampio può rendere più facile interpretare singoli insuccessi come giudizi sulla persona e sottovalutare risultati o qualità che non coincidono con quella lettura. Relazioni, esperienze passate, stress e condizioni di vita possono contribuire in modi differenti e vanno considerati insieme alle risposte. Se questo modo di guardarti provoca sofferenza, limita scelte e relazioni o porta a rinunce frequenti, parlarne con uno psicologo o psicoterapeuta può aiutarti; il risultato resta informativo e non diagnostico.");

        saveAreaInsights(id, "valore",
                "Riesci generalmente a riconoscere il tuo valore personale anche quando noti difetti, limiti o aspetti di te che vorresti cambiare.",
                "Il senso del tuo valore può indebolirsi in alcune circostanze, soprattutto quando l'attenzione si concentra su difetti, insicurezze o qualità che fai fatica a riconoscere.",
                "Le risposte mostrano una difficoltà frequente nel riconoscere qualità e dignità personale senza lasciare che limiti o difetti definiscano l'immagine complessiva di te.");
        saveAreaInsights(id, "fiducia",
                "Nelle tue risposte emerge generalmente fiducia nel tuo giudizio e la possibilità di esprimere opinioni, decisioni e bisogni anche senza continue conferme.",
                "La fiducia nelle tue capacità e nel diritto di esprimerti sembra variare: novità, decisioni importanti o timore del conflitto possono aumentare dubbi e bisogno di conferme.",
                "Le risposte indicano dubbi ricorrenti sulle tue capacità e decisioni, insieme alla difficoltà di dare spazio alle tue opinioni o proteggere i tuoi bisogni.");
        saveAreaInsights(id, "autocritica",
                "Errori e insuccessi sembrano generalmente restare eventi specifici, senza trasformarsi in un giudizio globale e duraturo sulla tua persona.",
                "In alcune situazioni l'autocritica può diventare severa o prolungata, soprattutto quando un risultato è importante o le aspettative verso te stesso sono elevate.",
                "Le risposte mostrano una risposta spesso dura agli errori: sbagli e insuccessi possono alimentare giudizi globali, rimuginio e difficoltà a perdonarti o riconoscere ciò che hai comunque fatto.");
        saveAreaInsights(id, "approvazione",
                "Critiche, risultati e confronti sembrano influenzarti senza determinare stabilmente il valore che attribuisci a te stesso.",
                "In alcuni contesti l'opinione che hai di te può dipendere maggiormente da risultati, conferme, appartenenza o confronto con le altre persone.",
                "Le risposte indicano che giudizi, rifiuti, risultati e confronto sociale possono modificare spesso e intensamente il modo in cui valuti te stesso.");
    }

    private void seedEmotionalDependenceInformationTest() {
        String id = "dipendenza-affettiva";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Dipendenza affettiva",
                "Autovalutazione informativa",
                "Esplora paura dell'abbandono, bisogno di rassicurazione, rinunce personali, autonomia e confini nelle relazioni affettive.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato; non stabilisce se una relazione sia sana. Rispondi pensando alla relazione attuale o a una relazione affettiva significativa recente. Non rileva abusi: controllo, minacce e violenza non sono responsabilità di chi li subisce; il 1522 offre aiuto gratuito alle donne vittime di violenza e stalking.",
                version, false,
                "Frequenza complessiva delle dinamiche esplorate",
                "Frequenza delle dinamiche",
                true, 5).withSeo(
                "Test dipendenza affettiva online | Spazio Test",
                "Questionario su paura dell'abbandono, rassicurazione, autonomia e confini nella relazione. 24 domande, 6 minuti, senza registrazione; non diagnostico.")
                .withResponseInstruction("Pensando alla relazione indicata nell'introduzione, con quale frequenza ti è capitato?"));

        saveReference(id, "I disturbi da addiction nelle dipendenze non legate a sostanze — Ministero della Salute",
                "https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_3313_allegato.pdf", 1);
        saveReference(id, "Conceptualizing love addiction within the attachment perspective",
                "https://pmc.ncbi.nlm.nih.gov/articles/PMC12284683/", 2);

        saveArea(id, "separazione", "Paura della separazione e bisogno di rassicurazione", 1);
        saveArea(id, "autonomia", "Autonomia, interessi e rete personale", 2);
        saveArea(id, "confini", "Confini, bisogni e reciprocità", 3);
        saveArea(id, "regolazione", "Regolazione emotiva e centralità della relazione", 4);

        saveQuestions(id, List.of(
                q("separazione", "Quando la persona a cui sono legato tarda a rispondere, temo che il suo interesse per me sia diminuito."),
                q("separazione", "Ho bisogno di ricevere conferme frequenti sui sentimenti che l'altra persona prova per me."),
                q("separazione", "La distanza o il tempo trascorso separati mi provocano un'ansia difficile da calmare."),
                q("separazione", "Interpreto silenzi, stanchezza o cambiamenti di tono come possibili segnali di rifiuto o abbandono."),
                q("separazione", "L'idea che la relazione possa finire mi sembra insopportabile anche quando al suo interno sto male."),
                q("separazione", "Dopo un conflitto sento di dover ristabilire subito il contatto, anche se avrei bisogno di tempo per capire cosa provo."),
                q("autonomia", "Cambio o cancello programmi personali per essere disponibile ogni volta che l'altra persona lo desidera."),
                q("autonomia", "Trascuro amicizie, famiglia o interessi per dedicare quasi tutto il mio tempo alla relazione."),
                q("autonomia", "Faccio fatica a godermi attività o momenti piacevoli quando sono senza la persona a cui sono legato."),
                qe("autonomia", "Organizzo decisioni importanti soprattutto in funzione della relazione, anche quando questo mi penalizza.",
                        "rinuncio a un'opportunità o scelgo dove vivere soprattutto per evitare la distanza."),
                q("autonomia", "Quando sono coinvolto in una relazione, obiettivi e progetti personali perdono importanza."),
                q("autonomia", "Se l'altra persona disapprova una mia scelta, tendo ad abbandonarla anche quando per me conta molto."),
                q("confini", "Accetto comportamenti che mi feriscono pur di evitare distanza, tensioni o una possibile rottura."),
                q("confini", "Mi è difficile dire di no all'altra persona quando ciò che chiede supera i miei limiti."),
                qe("confini", "Mi sento responsabile dell'umore e del benessere dell'altra persona anche quando non dipendono da me.",
                        "penso di dover calmare, rassicurare o rendere felice l'altra persona."),
                q("confini", "Metto da parte bisogni o emozioni importanti per non rischiare di compromettere il legame."),
                q("confini", "Giustifico mancanze di rispetto ripetute perché temo di perdere la relazione."),
                q("confini", "Controllo attentamente parole e comportamenti per evitare che l'altra persona si allontani o ritiri il proprio affetto."),
                q("regolazione", "Il mio umore dipende molto dall'attenzione o dalla disponibilità che ricevo dall'altra persona."),
                q("regolazione", "I pensieri sulla relazione occupano così tanto spazio da rendermi difficile concentrarmi su altro."),
                q("regolazione", "Quando temo un allontanamento, cerco ripetutamente di ristabilire il contatto."),
                q("regolazione", "Dopo una rottura o un allontanamento sento un impulso forte a ristabilire il rapporto, anche sapendo che mi faceva stare male."),
                qe("regolazione", "Minimizzo incompatibilità importanti per proteggere l'immagine della relazione.",
                        "metto in secondo piano differenze su valori, progetti o rispetto reciproco."),
                q("regolazione", "Senza una relazione affettiva mi sento privo di direzione.")));

        saveGlobal(id, "LOW", "Le dinamiche di dipendenza affettiva sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le dinamiche di dipendenza affettiva nelle quattro aree esplorate. Vicinanza, rassicurazione e importanza della relazione sembrano convivere generalmente con autonomia, confini e interessi personali.",
                "Questo non significa vivere ogni legame senza paura, gelosia o bisogno dell'altra persona. Le risposte suggeriscono però che tali esperienze tendono a non determinare stabilmente il tuo valore, le tue scelte o l'accesso alla rete personale. Osserva comunque eventuali relazioni o fasi specifiche che la media può non rappresentare: il risultato non definisce se una relazione sia sana e non rileva abusi o violenza.");
        saveGlobal(id, "MIXED", "Le dinamiche di dipendenza affettiva sembrano presenti in modo variabile",
                "Le tue risposte descrivono un equilibrio relazionale che cambia tra paura della separazione, autonomia, confini e regolazione emotiva. Alcune dinamiche sembrano presenti, mentre altre emergono poco o soprattutto in determinate fasi del legame.",
                "Distanza, conflitti, incertezza o periodi di vulnerabilità possono aumentare il bisogno di rassicurazione e rendere più difficile proteggere i tuoi spazi. Nota con quali persone e circostanze avviene, se la risposta si riduce quando il contesto cambia e quale costo comporta per interessi, riposo e relazioni. La variabilità è più informativa di una singola etichetta e il questionario non stabilisce la qualità complessiva della relazione.");
        saveGlobal(id, "FOCUSED", "Le dinamiche di dipendenza affettiva sembrano più presenti in una o due aree",
                "Una o due aree emergono con maggiore frequenza, mentre negli altri aspetti del legame sembra esserci più equilibrio. La presenza complessiva dipende quindi soprattutto da un meccanismo relazionale specifico e non da una perdita uniforme di autonomia.",
                "Le schede sotto possono chiarire se il nucleo riguarda separazione e rassicurazione, interessi personali, confini e reciprocità oppure stabilità emotiva. Osserva quali situazioni lo attivano, quali rinunce produce e se l'altra persona rispetta bisogni e limiti quando vengono espressi. Il risultato è informativo, non definisce te né la tua relazione e non può riconoscere una situazione abusante.");
        saveGlobal(id, "BROAD", "Le dinamiche di dipendenza affettiva sembrano frequentemente presenti in più aree",
                "Le risposte indicano dinamiche frequenti in almeno tre aree, con possibile riduzione dell'autonomia e forte dipendenza dal legame per sentirti stabile. Paura della separazione, rinunce, difficoltà nei confini e regolazione emotiva sembrano intrecciarsi anziché restare circoscritte a un solo aspetto.",
                "Un andamento ampio rende utile osservare isolamento, perdita di interessi, possibilità reale di dire no e conseguenze del rapporto sul benessere. Se queste dinamiche causano sofferenza o rendono difficile interrompere una relazione che ti fa stare male, confrontarti con uno psicologo o psicoterapeuta può aiutarti a comprenderle senza giudizio. Controllo, minacce e violenza non sono colpa tua: in questi casi la priorità è trovare un aiuto sicuro, indipendentemente dal punteggio; il questionario resta informativo e non diagnostico.");

        saveAreaInsights(id, "separazione",
                "Distanza, silenzi e conflitti sembrano generalmente gestibili senza un bisogno continuo di conferme o il timore immediato di perdere il legame.",
                "In alcune situazioni l'incertezza può aumentare il bisogno di rassicurazione e portarti a interpretare segnali ambigui come possibili indizi di allontanamento.",
                "Le risposte mostrano una paura frequente della separazione o del rifiuto, insieme a un bisogno urgente di conferme e ricontatto quando percepisci distanza.");
        saveAreaInsights(id, "autonomia",
                "La relazione sembra lasciare generalmente spazio ad amicizie, interessi, decisioni e progetti personali.",
                "Il tuo spazio personale può ridursi in alcuni periodi o relazioni, soprattutto quando temi che coltivare interessi e scelte autonome possa creare distanza.",
                "Le risposte indicano che la relazione tende spesso a prevalere su amicizie, attività, decisioni o obiettivi personali, limitando il tuo spazio autonomo.");
        saveAreaInsights(id, "confini",
                "Riesci generalmente a riconoscere i tuoi bisogni, esprimere limiti e distinguere ciò di cui sei responsabile da ciò che appartiene all'altra persona.",
                "In alcune circostanze potresti mettere da parte bisogni o limiti per proteggere il legame, soprattutto quando temi conflitti, disapprovazione o distanza.",
                "Le risposte mostrano una difficoltà frequente nel proteggere confini e bisogni, fino ad accettare rinunce o comportamenti dolorosi per evitare di perdere la relazione.");
        saveAreaInsights(id, "regolazione",
                "Il tuo equilibrio emotivo e la tua attenzione sembrano dipendere in modo contenuto dall'andamento della relazione o dalla disponibilità dell'altra persona.",
                "Pensieri, umore e bisogno di contatto possono diventare più intensi in momenti di incertezza, distanza o rottura, pur lasciando spazio anche ad altri aspetti della vita.",
                "Le risposte indicano che relazione, contatto e disponibilità dell'altra persona possono assorbire spesso pensieri ed emozioni, rendendo difficile ritrovare stabilità in modo autonomo.");
    }

    private void seedAssertivenessInformationTest() {
        String id = "assertivita";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Assertività",
                "Autovalutazione informativa",
                "Esplora quanto riesci a esprimere opinioni e bisogni, dire di no, proteggere i tuoi confini e affrontare i disaccordi.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Rispondi in base a ciò che fai davvero nei diversi contesti, non a ciò che ritieni desiderabile. L'assertività dipende anche da cultura, relazioni e sicurezza: non esporsi in una situazione minacciosa può essere una scelta protettiva.",
                version, false,
                "Frequenza complessiva dei comportamenti assertivi",
                "Frequenza dei comportamenti assertivi",
                true, 6).withSeo(
                "Test assertività online: questionario | Spazio Test",
                "Questionario informativo su opinioni, bisogni, capacità di dire di no, confini e disaccordi. 24 domande, circa 6 minuti e nessuna registrazione.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e ai diversi contesti, con quale frequenza lo hai fatto?"));

        saveReference(id, "A 30-Item Schedule for Assessing Assertive Behavior",
                "https://doi.org/10.1016/S0005-7894(73)80120-0", 1);
        saveReference(id, "Normative studies with the Scale for Interpersonal Behaviour",
                "https://doi.org/10.1016/S0191-8869(98)00252-9", 2);

        saveArea(id, "espressione", "Espressione di opinioni, bisogni ed emozioni", 1);
        saveArea(id, "confini", "Confini, rifiuto e tutela dei propri diritti", 2);
        saveArea(id, "confronto", "Confronto, critiche e gestione del disaccordo", 3);
        saveArea(id, "iniziativa", "Iniziativa, richieste e riconoscimento reciproco", 4);

        saveQuestions(id, List.of(
                q("espressione", "Esprimo la mia opinione anche quando è diversa da quella delle persone presenti."),
                q("espressione", "Comunico ciò di cui ho bisogno prima che il disagio si trasformi in frustrazione o risentimento."),
                qe("espressione", "Descrivo come mi sento parlando della mia esperienza personale.",
                        "dico “mi sono sentito escluso” invece di attribuire intenzioni all'altra persona."),
                q("espressione", "Quando una mia preferenza conta, la esprimo invece di adeguarmi automaticamente alle scelte altrui."),
                q("espressione", "Intervengo quando una mia idea, un contributo o un merito non vengono riconosciuti correttamente."),
                q("espressione", "Se vengo frainteso, chiarisco con calma ciò che intendevo dire o chiedere."),
                q("confini", "Dico di no a una richiesta quando non posso o non voglio accettarla, senza inventare scuse."),
                q("confini", "Segnalo in modo diretto quando un comportamento mi mette a disagio o supera un mio limite."),
                q("confini", "Proteggo il tempo e le energie necessari ai miei impegni anche quando qualcuno vorrebbe che fossi sempre disponibile."),
                q("confini", "Chiedo un trattamento corretto quando un accordo, un servizio o un mio diritto non vengono rispettati."),
                q("confini", "Mantengo un limite importante anche se l'altra persona mostra delusione o insiste perché lo cambi."),
                q("confini", "Chiedo con calma e precisione di modificare un comportamento che continua a crearmi difficoltà."),
                q("confronto", "Durante un disaccordo rimango sul problema concreto senza attaccare la persona o svalutarla."),
                q("confronto", "Ascolto il punto di vista altrui senza sentirmi obbligato ad abbandonare il mio."),
                q("confronto", "Quando ricevo una critica, cerco di valutarne la parte utile prima di difendermi o chiudermi."),
                qe("confronto", "Riconosco un mio errore senza svalutare l'intera mia posizione.",
                        "dico “su questo ho sbagliato” senza concludere che tutto ciò che penso non vale."),
                q("confronto", "Esprimo un disaccordo rispettoso anche con una persona autorevole o importante per me."),
                q("confronto", "Affronto una tensione significativa invece di evitarla a lungo o accumulare risentimento."),
                q("iniziativa", "Formulo richieste chiare anche quando esiste la possibilità che l'altra persona risponda di no."),
                q("iniziativa", "Chiedo aiuto, informazioni o chiarimenti quando ne ho bisogno."),
                q("iniziativa", "Prendo l'iniziativa per iniziare una conversazione o presentarmi in un contesto poco familiare."),
                q("iniziativa", "Esprimo apprezzamento o affetto in modo diretto quando lo provo."),
                q("iniziativa", "Accolgo un complimento o un riconoscimento senza sminuirlo o respingerlo automaticamente."),
                qe("iniziativa", "Nelle decisioni condivise propongo soluzioni che tengono conto anche delle mie priorità.",
                        "concordo tempi o attività includendo anche un mio impegno importante.")));

        saveGlobal(id, "LOW", "Le risorse assertive sembrano poco espresse",
                "Nel complesso hai indicato una frequenza contenuta dei comportamenti assertivi nelle quattro aree esplorate. Esprimere opinioni e bisogni, proteggere i confini, affrontare il confronto e prendere iniziativa sembrano quindi richiedere spesso cautela o rinuncia.",
                "Questo profilo può riflettere timore del conflitto, scarsa abitudine, ruoli gerarchici o conseguenze relazionali concrete, non una mancanza di valore o coraggio. Osserva se la difficoltà è simile con tutte le persone oppure aumenta nei contesti in cui dipendi maggiormente dall'altro o ti senti poco sicuro. L'assertività è un insieme di abilità allenabili gradualmente, ma in situazioni minacciose evitare il confronto può essere una scelta protettiva.");
        saveGlobal(id, "MIXED", "Le risorse assertive sembrano espresse in modo variabile",
                "Le tue risposte descrivono comportamenti assertivi presenti in alcune aree e meno accessibili in altre. La possibilità di esprimerti non appare quindi stabile, ma cambia con il tipo di richiesta, confronto o relazione.",
                "Potresti sentirti libero con alcune persone e trattenerti davanti ad autorità, conflitti, rifiuti o legami importanti. Individua nelle schede sotto se cambia soprattutto l'espressione, la protezione dei confini, la gestione delle critiche o l'iniziativa e quali conseguenze temi. Capire dove la tua voce si riduce permette di scegliere esercizi graduali e realistici, senza trasformare l'assertività in un obbligo a parlare sempre.");
        saveGlobal(id, "FOCUSED", "Le risorse assertive sembrano più espresse in una o due aree",
                "Una o due aree risultano particolarmente solide, mentre le altre sembrano richiedere più intenzionalità o allenamento. Il profilo mostra quindi risorse assertive riconoscibili, ma non ancora distribuite con la stessa continuità tra espressione, confini, confronto e iniziativa.",
                "Le competenze già presenti possono diventare un punto di partenza concreto. Osserva che cosa cambia nei contesti in cui riesci a esprimerti: chiarezza dell'obiettivo, rapporto di fiducia, tempo per prepararti o minore timore delle conseguenze. Trasferire gradualmente questi elementi alle aree meno accessibili può essere più utile che cercare di comportarti nello stesso modo in ogni situazione.");
        saveGlobal(id, "BROAD", "Le risorse assertive sembrano frequentemente espresse in più aree",
                "Le tue risposte indicano comportamenti assertivi frequenti in almeno tre aree del questionario. Esprimere opinioni e bisogni, porre limiti, affrontare disaccordi e prendere iniziativa sembrano risorse disponibili in una varietà di situazioni.",
                "Un profilo ampio non significa parlare sempre, non provare disagio o ottenere ciò che chiedi. Essere assertivi comprende anche ascoltare, negoziare e scegliere consapevolmente quando intervenire, adattando forma e intensità allo scopo. Sicurezza, gerarchie e conseguenze concrete restano importanti: il risultato descrive le tue risposte e non costituisce una valutazione clinica o morale del tuo modo di comunicare.");

        saveAreaInsights(id, "espressione",
                "Potresti trattenere spesso opinioni, emozioni o bisogni e adattarti alle preferenze altrui anche quando per te sarebbe importante esprimerti.",
                "Riesci a esprimere ciò che pensi e senti in diversi contesti, ma alcune persone o situazioni possono renderti più difficile parlare in modo diretto.",
                "Le risposte mostrano una buona capacità di rendere visibili opinioni, preferenze, bisogni ed emozioni senza trasformarli in accuse verso gli altri.");
        saveAreaInsights(id, "confini",
                "Dire di no, segnalare un disagio o mantenere un limite sembra spesso difficile, soprattutto quando temi di deludere o creare tensione.",
                "Sai proteggere alcuni limiti, mentre in altre circostanze insistenza, senso di colpa o timore delle reazioni possono portarti a cedere.",
                "Le risposte indicano una capacità frequente di rifiutare richieste, tutelare tempo e diritti e chiedere cambiamenti in modo diretto e rispettoso.");
        saveAreaInsights(id, "confronto",
                "Critiche e disaccordi possono portarti a evitare il confronto, chiuderti, cedere oppure accumulare tensione prima di affrontare il problema.",
                "In molti confronti riesci a restare presente e rispettoso, ma autorità, critiche o temi emotivamente importanti possono rendere più difficile mantenere la tua posizione.",
                "Le risposte mostrano una buona capacità di affrontare disaccordi e feedback restando sul problema, ascoltando l'altro e conservando il diritto alla tua posizione.");
        saveAreaInsights(id, "iniziativa",
                "Prendere l'iniziativa, chiedere aiuto o riconoscimento ed esprimere apprezzamento può risultarti difficile quando non conosci in anticipo la risposta altrui.",
                "Ti attivi con una certa facilità in situazioni familiari, mentre richieste, nuove interazioni o la possibilità di ricevere un rifiuto possono frenarti.",
                "Le risposte indicano una buona disponibilità a iniziare scambi, formulare richieste, chiedere aiuto e dare o ricevere riconoscimenti in modo aperto.");
    }

    private void seedEmotionalIntelligenceInformationTest() {
        String id = "intelligenza-emotiva";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Intelligenza emotiva",
                "Autovalutazione informativa",
                "Esplora come riconosci, comprendi, utilizzi e regoli le emozioni proprie e altrui nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Non misura un quoziente o un'abilità oggettiva: descrive come valuti alcuni tuoi comportamenti abituali legati alle emozioni. Cultura, esperienze, neurodiversità e contesto possono influenzare le risposte.",
                version, false,
                "Frequenza complessiva delle competenze emotive esplorate",
                "Frequenza delle competenze emotive",
                true, 7).withSeo(
                "Test intelligenza emotiva online | Spazio Test",
                "Questionario su consapevolezza, comprensione e regolazione delle emozioni proprie e altrui. 24 domande, circa 6 minuti; non misura un quoziente emotivo.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e a situazioni diverse, con quale frequenza lo hai fatto?"));

        saveReference(id, "The Ability Model of Emotional Intelligence: Principles and Updates",
                "https://doi.org/10.1177/1754073916639667", 1);
        saveReference(id, "Emotional Intelligence: New Ability or Eclectic Traits?",
                "https://doi.org/10.1037/0003-066X.63.6.503", 2);

        saveArea(id, "percezione", "Percezione e consapevolezza emotiva", 1);
        saveArea(id, "facilitazione", "Uso delle emozioni nel pensiero e nelle decisioni", 2);
        saveArea(id, "comprensione", "Comprensione di cause, sfumature e cambiamenti", 3);
        saveArea(id, "regolazione", "Regolazione ed espressione nelle relazioni", 4);

        saveQuestions(id, List.of(
                qe("percezione", "Mi accorgo dei segnali del corpo che accompagnano un'emozione prima che diventi molto intensa.",
                        "noto cambiamenti nel respiro, tensione alla mandibola o pressione al petto."),
                q("percezione", "Riesco a dare un nome abbastanza preciso a ciò che provo invece di fermarmi a un generico stare bene o stare male."),
                q("percezione", "Riconosco quando un'emozione sta aumentando, diminuendo o lasciando spazio a un'altra."),
                q("percezione", "Noto i segnali emotivi non verbali di una persona senza considerare infallibile la mia impressione."),
                q("percezione", "Mi accorgo quando il mio stato emotivo sta influenzando il mio comportamento."),
                q("percezione", "Quando non sono sicuro di ciò che prova qualcuno, verifico la mia interpretazione con domande rispettose."),
                q("facilitazione", "Considero ciò che provo come un'informazione utile quando devo prendere una decisione importante."),
                q("facilitazione", "Uso interesse, entusiasmo o disagio per capire meglio che cosa conta per me in una situazione."),
                q("facilitazione", "Quando un'emozione intensa riduce la mia lucidità, rimando se possibile le decisioni impulsive finché riesco a valutarle meglio."),
                qe("facilitazione", "Adatto il modo di affrontare un compito al mio stato emotivo.",
                        "se sono agitato, inizio da un passaggio semplice; se sono concentrato, affronto quello più complesso."),
                qe("facilitazione", "Se sono bloccato in un unico punto di vista, cerco una prospettiva emotiva diversa per vedere nuove possibilità.",
                        "mi chiedo come leggerei la situazione se fossi meno spaventato o più fiducioso."),
                q("facilitazione", "Distinguo l'impulso emotivo del momento dagli obiettivi e dai valori che voglio seguire nel lungo periodo."),
                q("comprensione", "Collego ciò che provo a eventi, bisogni, aspettative o interpretazioni che possono averlo attivato."),
                q("comprensione", "Riesco a riconoscere emozioni diverse o contrastanti presenti nello stesso momento."),
                q("comprensione", "Comprendo come un'emozione possa trasformarsi, per esempio da irritazione a delusione o da timore a sollievo."),
                q("comprensione", "Prima di una situazione importante considero come potrei reagire emotivamente."),
                q("comprensione", "Riesco a capire perché lo stesso evento può provocare emozioni diverse in persone diverse."),
                q("comprensione", "Rivedo la mia lettura di una reazione emotiva quando emergono informazioni nuove sul contesto."),
                qe("regolazione", "Riesco a restare in contatto con un'emozione intensa senza dover agire subito o fingere che non esista.",
                        "riconosco la rabbia senza attaccare qualcuno e senza fare finta che non ci sia."),
                q("regolazione", "Scelgo strategie diverse per gestire le emozioni in base alla situazione, invece di usare sempre la stessa risposta."),
                q("regolazione", "Esprimo ciò che provo in modo comprensibile e rispettoso."),
                q("regolazione", "Dopo una reazione emotiva forte riesco a recuperare e riflettere su ciò che è accaduto."),
                q("regolazione", "Quando qualcuno condivide un'emozione, ascolto senza minimizzarla."),
                q("regolazione", "Se il modo in cui sto gestendo un'emozione non aiuta, provo a cambiare strategia o a cercare sostegno.")));

        saveGlobal(id, "LOW", "Le competenze emotive percepite sembrano poco espresse",
                "Nel complesso hai indicato una frequenza contenuta delle competenze emotive percepite nelle quattro aree. Riconoscere, utilizzare, comprendere e regolare le informazioni emotive sembrano quindi processi non sempre accessibili con continuità nella vita quotidiana.",
                "Questo profilo può riflettere difficoltà nel notare i segnali, dare loro un significato o scegliere una risposta quando l'intensità aumenta. Cultura, neurodiversità, stress, sicurezza relazionale e familiarità con il linguaggio emotivo possono influenzare l'autovalutazione. Osserva dove il processo si interrompe nelle schede sotto: queste competenze possono essere sviluppate, ma il risultato riflette la tua percezione e non misura intelligenza, capacità oggettiva o valore personale.");
        saveGlobal(id, "MIXED", "Le competenze emotive percepite sembrano espresse in modo variabile",
                "Le tue risposte descrivono competenze emotive accessibili in alcune aree e meno continue in altre. Potresti quindi riconoscere bene alcuni segnali, ma incontrare più difficoltà nell'usarli, comprenderli o regolarli in particolari situazioni.",
                "Intensità emotiva, pressione, tipo di relazione e tempo disponibile possono modificare il passaggio da una fase all'altra. Le schede sotto aiutano a distinguere se la variabilità riguarda percezione, uso nel pensiero, comprensione o regolazione, evitando di ridurre tutto a un unico punteggio. Il profilo descrive abitudini percepite, non una prova di abilità, e può cambiare con contesto, esperienza e strategie apprese.");
        saveGlobal(id, "FOCUSED", "Le competenze emotive percepite sembrano più espresse in una o due aree",
                "Una o due aree emergono con maggiore continuità, mentre le altre sembrano dipendere maggiormente dal contesto o richiedere allenamento. Il profilo mostra quindi risorse emotive specifiche, senza suggerire lo stesso livello di accessibilità in ogni fase del processo.",
                "Le competenze già presenti possono sostenere quelle meno accessibili: riconoscere un'emozione può aiutare a comprenderla, oppure una buona regolazione può creare spazio per osservarne meglio le cause. Individua nelle schede sotto quale passaggio funziona già e quali condizioni lo rendono possibile. Il risultato resta un'autopercezione informativa e non consente di dedurre una capacità oggettiva generale.");
        saveGlobal(id, "BROAD", "Le competenze emotive percepite sembrano frequentemente espresse in più aree",
                "Le tue risposte indicano competenze emotive percepite con frequenza in almeno tre aree del questionario. Riconoscimento, uso, comprensione e regolazione sembrano quindi risorse abbastanza diffuse, anziché dipendere da un unico passaggio del processo emotivo.",
                "Questo non significa comprendere sempre gli altri, evitare emozioni intense o scegliere in ogni occasione la risposta più utile. Suggerisce piuttosto che disponi di più modi per osservare, interpretare e utilizzare le emozioni senza esserne guidato automaticamente. Contesto, motivazione e qualità delle relazioni continuano a influire sulle azioni; il risultato resta informativo e non è una misura oggettiva di abilità.");

        saveAreaInsights(id, "percezione",
                "Può esserti difficile accorgerti tempestivamente dei segnali emotivi, distinguerli con precisione o verificare ciò che percepisci nelle altre persone.",
                "Riconosci diversi segnali emotivi, ma intensità, fretta o ambiguità possono rendere meno chiaro ciò che provi o ciò che osservi negli altri.",
                "Le risposte indicano una buona attenzione ai segnali corporei, alle variazioni del tuo stato e agli indizi emotivi degli altri, mantenendo spazio per verificare le interpretazioni.");
        saveAreaInsights(id, "facilitazione",
                "Le emozioni possono sembrarti soprattutto qualcosa da seguire o da mettere da parte, rendendo difficile usarle come informazioni nel pensiero e nelle decisioni.",
                "In alcune situazioni utilizzi ciò che provi per orientarti, mentre emozioni intense o pressioni possono avvicinare impulso immediato e decisione.",
                "Le risposte mostrano una buona capacità di integrare le informazioni emotive con ragionamento, priorità e obiettivi senza lasciare che decidano automaticamente al tuo posto.");
        saveAreaInsights(id, "comprensione",
                "Individuare cause, sfumature, emozioni miste o possibili evoluzioni di ciò che provi può risultarti spesso complesso.",
                "Comprendi diverse dinamiche emotive, ma reazioni contrastanti, cambiamenti rapidi o prospettive molto diverse dalla tua possono creare incertezza.",
                "Le risposte indicano una buona capacità di collegare emozioni e contesto, riconoscere stati misti e aggiornare la tua lettura quando emergono nuove informazioni.");
        saveAreaInsights(id, "regolazione",
                "Quando un'emozione diventa intensa potresti agire subito, evitarla o affidarti a una risposta abituale anche quando non è adatta alla situazione.",
                "Disponi di alcune strategie per esprimere e gestire le emozioni, ma sotto pressione può diventare più difficile scegliere con flessibilità o chiedere sostegno.",
                "Le risposte mostrano una buona capacità di accogliere le emozioni, esprimerle con rispetto e modificare strategia quando la prima risposta non aiuta te o la relazione.");
    }

    private void seedPerfectionismInformationTest() {
        String id = "perfezionismo";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Perfezionismo",
                "Autovalutazione informativa",
                "Esplora standard personali, paura degli errori, pressione del giudizio, dubbi e bisogno di controllo.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Obiettivi ambiziosi e cura dei dettagli non sono di per sé problematici: qui si osserva quanto gli standard diventino rigidi o limitanti. Rispondi pensando alla frequenza reale nei diversi ambiti, non soltanto nel lavoro o nello studio.",
                version, false,
                "Frequenza complessiva delle dinamiche perfezionistiche",
                "Frequenza delle dinamiche perfezionistiche",
                true, 8).withSeo(
                "Test perfezionismo online | Spazio Test",
                "Questionario su standard personali, paura degli errori, giudizio, dubbi e controllo. 24 domande, circa 6 minuti, senza registrazione; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e ai diversi ambiti della tua vita, con quale frequenza ti è capitato?"));

        saveReference(id, "The dimensions of perfectionism",
                "https://doi.org/10.1007/BF01172967", 1);
        saveReference(id, "Perfectionism in the self and social contexts",
                "https://pubmed.ncbi.nlm.nih.gov/2027080/", 2);

        saveArea(id, "standard", "Standard elevati e valore legato ai risultati", 1);
        saveArea(id, "errori", "Paura degli errori, dubbi e autocritica", 2);
        saveArea(id, "giudizio", "Aspettative percepite e giudizio degli altri", 3);
        saveArea(id, "controllo", "Controllo, rigidità e difficoltà a concludere", 4);

        saveQuestions(id, List.of(
                q("standard", "Mi impongo obiettivi molto difficili anche quando un risultato meno ambizioso sarebbe adeguato alla situazione."),
                qe("standard", "Quando raggiungo un buon risultato, lo considero presto il nuovo minimo invece di riconoscerlo come un successo.",
                        "dopo un buon voto o feedback penso subito che la prossima volta dovrò fare almeno altrettanto."),
                q("standard", "Faccio fatica a sentirmi soddisfatto se il risultato presenta anche una piccola imperfezione."),
                qe("standard", "Valuto il mio valore personale soprattutto in base a produttività, risultati o prestazioni.",
                        "una giornata poco produttiva mi fa sentire meno valido come persona."),
                q("standard", "Alzo continuamente gli standard perché ciò che ho raggiunto non mi sembra mai abbastanza."),
                q("standard", "Rinuncio a riposo o tempo personale perché penso che potrei ancora migliorare ciò che sto facendo."),
                q("errori", "Un singolo errore mi porta a considerare fallimentare l'intero lavoro o la mia prestazione."),
                q("errori", "Ripenso a lungo ai miei errori anche dopo averli corretti o quando non posso più intervenire."),
                q("errori", "Dubito della qualità di ciò che ho fatto anche dopo averlo controllato più volte."),
                q("errori", "Rimando la consegna o la condivisione di un lavoro perché non mi sento mai abbastanza sicuro del risultato."),
                q("errori", "Mi critico per un errore con una durezza che non userei verso un'altra persona nella stessa situazione."),
                q("errori", "Evito attività nuove quando temo di non riuscire a farle bene fin dall'inizio."),
                q("giudizio", "Sento che le persone importanti per me si aspettano risultati molto elevati in quasi tutto ciò che faccio."),
                q("giudizio", "Temo che l'opinione che gli altri hanno di me peggiori se commetto un errore."),
                q("giudizio", "Nascondo i miei errori per non apparire meno competente agli occhi degli altri."),
                q("giudizio", "Confronto i miei risultati con le prestazioni migliori degli altri e finisco per sentirmi inadeguato."),
                q("giudizio", "Interpreto un feedback correttivo come la prova che non sono stato all'altezza delle aspettative."),
                q("giudizio", "Sento di dover apparire sempre competente anche quando sono in difficoltà."),
                qe("controllo", "Dedico a dettagli secondari molto più tempo di quanto il loro impatto sul risultato richiederebbe.",
                        "continuo a sistemare formato o ordine quando il contenuto è già adeguato."),
                q("controllo", "Rifaccio o ritocco attività già adeguate perché mi è difficile accettarle come concluse."),
                q("controllo", "Fatico a delegare perché temo che il compito non venga svolto esattamente come ritengo necessario."),
                q("controllo", "Un cambiamento imprevisto nel piano mi provoca una frustrazione intensa perché compromette il modo ideale di procedere."),
                q("controllo", "Rimando l'inizio di un'attività finché non ho trovato il metodo, il momento o le condizioni che considero perfetti."),
                q("controllo", "Mi è difficile prendere una decisione quando restano alternative da valutare.")));

        saveGlobal(id, "LOW", "Le dinamiche perfezionistiche sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le pressioni perfezionistiche nelle quattro aree esplorate. Standard, timore degli errori, aspettative percepite e bisogno di controllo non formano, nelle tue risposte, un insieme rigido e diffuso.",
                "Puoi avere obiettivi elevati e attenzione alla qualità mantenendo la possibilità di adattare tempi, metodo e livello di precisione. Questo profilo non esclude momenti di forte esigenza né un ambito molto specifico in cui il costo aumenta. Osserva se riesci generalmente a concludere, delegare e separare la prestazione dal valore personale: il risultato descrive tendenze attuali e non rappresenta una valutazione clinica.");
        saveGlobal(id, "MIXED", "Le dinamiche perfezionistiche sembrano presenti in modo variabile",
                "Le tue risposte descrivono pressioni perfezionistiche presenti in alcune aree e contenute in altre. La rigidità sembra quindi dipendere dal tipo di compito, dalla visibilità del risultato o dal significato personale attribuito alla prestazione.",
                "Potresti mantenere flessibilità in molte situazioni ma aumentare controlli, dubbi o autocritica quando temi il giudizio o consideri l'esito particolarmente importante. Le schede sotto aiutano a distinguere quale dimensione cambia e se il costo riguarda tempo, energia, rinvio o relazioni. Osservare quando lo standard smette di essere funzionale è più utile che definirti semplicemente perfezionista.");
        saveGlobal(id, "FOCUSED", "Le dinamiche perfezionistiche sembrano più presenti in una o due aree",
                "Una o due aree emergono con particolare frequenza, mentre negli altri aspetti sembra esserci maggiore flessibilità. La presenza complessiva è quindi sostenuta soprattutto da un meccanismo specifico e non da perfezionismo uniforme in ogni ambito.",
                "Le analisi per area possono mostrare se la pressione nasce soprattutto da standard e valore personale, paura degli errori, aspettative altrui oppure ordine e controllo. Osserva quali situazioni attivano il meccanismo, che beneficio immediato offre e quale costo produce nel concludere o partecipare. Distinguerne la funzione aiuta a capire quando la ricerca della qualità resta utile e quando diventa una regola difficile da adattare.");
        saveGlobal(id, "BROAD", "Le dinamiche perfezionistiche sembrano frequentemente presenti in più aree",
                "Le risposte indicano pressioni perfezionistiche frequenti in almeno tre aree del questionario. Standard, errori, giudizio percepito e controllo sembrano intrecciarsi, rendendo più difficile riconoscere un risultato come sufficiente e separare prestazione e valore personale.",
                "Un profilo ampio può sostenere cicli di preparazione e controllo eccessivi, rinvio, autocritica e ulteriore innalzamento degli standard. È utile considerare in quali ambiti il costo è maggiore e se il meccanismo limita riposo, relazioni, apprendimento o conclusione dei compiti. Se questa pressione causa sofferenza, blocchi, procrastinazione, esaurimento o rinunce importanti, confrontarti con uno psicologo o psicoterapeuta può aiutarti; il risultato resta informativo e non diagnostico.");

        saveAreaInsights(id, "standard",
                "I tuoi obiettivi sembrano generalmente adattabili alla situazione e il valore che riconosci a te stesso non dipende interamente dai risultati.",
                "In alcuni ambiti gli standard possono diventare molto esigenti e rendere più difficile riconoscere successi, riposo o risultati sufficientemente buoni.",
                "Le risposte mostrano standard frequentemente molto elevati e un forte legame tra prestazione e valore personale, con poco spazio per soddisfazione e recupero.");
        saveAreaInsights(id, "errori",
                "Errori e dubbi sembrano generalmente restare informazioni circoscritte, senza trasformarsi in giudizi globali o controlli prolungati.",
                "Alcuni errori o compiti importanti possono attivare autocritica, ripensamenti e bisogno di verificare, soprattutto quando l'esito è incerto.",
                "Le risposte indicano una paura frequente degli errori, accompagnata da dubbi persistenti, autocritica severa o evitamento delle situazioni in cui non puoi garantire un risultato elevato.");
        saveAreaInsights(id, "giudizio",
                "Le aspettative e i feedback degli altri sembrano influenzarti senza definire stabilmente competenza, accettazione o valore personale.",
                "In alcune relazioni o situazioni visibili potresti sentire una pressione maggiore a dimostrarti competente e a nascondere limiti o incertezze.",
                "Le risposte mostrano una forte pressione percepita dal giudizio altrui, con il timore che errori e difficoltà riducano rispetto, approvazione o affetto.");
        saveAreaInsights(id, "controllo",
                "Riesci generalmente a calibrare tempo e precisione, delegare e concludere quando il risultato è adeguato allo scopo.",
                "Dettagli, alternative o cambiamenti possono rallentarti in alcuni compiti, soprattutto quando non è chiaro quale livello di qualità sia sufficiente.",
                "Le risposte indicano un bisogno frequente di controllo e completezza che può portare a ritocchi, indecisione, difficoltà a delegare o rinvio dell'inizio e della conclusione.");
    }

    private void seedSocialAnxietyInformationTest() {
        String id = "ansia-sociale";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ansia sociale",
                "Autovalutazione informativa",
                "Esplora paura del giudizio, tensione nelle interazioni, autocontrollo, situazioni sotto osservazione ed evitamento sociale.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Rispondi pensando agli ultimi mesi e a contesti diversi; timidezza o disagio occasionale non indicano da soli un disturbo. Se ansia o evitamento limitano attività importanti, puoi parlarne con un professionista qualificato.",
                version, false,
                "Frequenza complessiva delle esperienze di ansia sociale",
                "Frequenza delle esperienze",
                true, 9).withSeo(
                "Test ansia sociale online | Spazio Test",
                "Questionario informativo su paura del giudizio, tensione, autocontrollo ed evitamento sociale. 24 domande, circa 6 minuti, senza registrazione; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e ai diversi contesti sociali, con quale frequenza ti è capitato?"));

        saveReference(id, "Social Anxiety Disorder: More Than Just Shyness — NIMH",
                "https://www.nimh.nih.gov/health/publications/social-anxiety-disorder-more-than-just-shyness", 1);
        saveReference(id, "Social anxiety disorder: assessment and diagnosis for adults — NICE CG159",
                "https://www.nice.org.uk/guidance/cg159/ifp/chapter/assessment-and-diagnosis-for-adults", 2);

        saveArea(id, "valutazione", "Paura del giudizio e dell'imbarazzo", 1);
        saveArea(id, "interazione", "Conversazioni e interazioni sociali", 2);
        saveArea(id, "prestazione", "Prestazione e situazioni sotto osservazione", 3);
        saveArea(id, "evitamento", "Anticipazione, evitamento e ripensamento", 4);

        saveQuestions(id, List.of(
                q("valutazione", "Prima di parlare con altre persone temo di dire qualcosa di imbarazzante."),
                q("valutazione", "Interpreto silenzi, espressioni neutre o risposte brevi come segnali che gli altri mi stanno giudicando negativamente."),
                q("valutazione", "Temo che rossore, tremore, sudorazione, voce incerta o altri segnali d'ansia siano visibili e mi facciano apparire male."),
                q("valutazione", "La possibilità di essere giudicato negativamente mi provoca una forte preoccupazione nelle situazioni sociali."),
                qe("valutazione", "Sento di dover controllare attentamente ciò che dico e faccio per evitare di dare un'impressione negativa.",
                        "mentre parlo controllo mentalmente parole, postura o tono di voce."),
                q("valutazione", "Un piccolo errore sociale mi sembra capace di compromettere a lungo l'opinione che gli altri hanno di me."),
                q("interazione", "Parlare con persone che conosco poco mi provoca una tensione difficile da ignorare."),
                q("interazione", "Mi è difficile iniziare una conversazione, presentarmi o trovare qualcosa da dire con persone nuove."),
                q("interazione", "Quando l'attenzione si sposta su di me durante una conversazione, la mente può diventare vuota o confusa."),
                qe("interazione", "Fare una richiesta in presenza di altre persone mi crea forte disagio.",
                        "chiedere un'informazione o segnalare un problema mentre altre persone ascoltano."),
                q("interazione", "Nei gruppi controllo così tanto come sto apparendo da faticare a seguire e partecipare spontaneamente alla conversazione."),
                q("interazione", "Conoscere nuove persone mi mette in forte soggezione."),
                q("prestazione", "Parlare, presentare un lavoro o esibirmi davanti a un gruppo mi provoca molta ansia."),
                q("prestazione", "Rispondere a una domanda o intervenire durante una riunione, una lezione o un incontro mi fa sentire fortemente esposto."),
                q("prestazione", "Essere osservato mentre scrivo, mangio, lavoro o svolgo un'attività rende più difficile comportarmi con naturalezza."),
                q("prestazione", "Essere presentato, ricevere un riconoscimento o diventare il centro dell'attenzione mi provoca un disagio intenso."),
                q("prestazione", "Colloqui, esami orali o valutazioni faccia a faccia mi preoccupano soprattutto per come potrei apparire agli altri."),
                q("prestazione", "Nelle situazioni di prestazione l'ansia interferisce con la voce, la memoria, la concentrazione o i movimenti."),
                q("evitamento", "Inizio a preoccuparmi per un evento sociale molto prima che accada, immaginando ciò che potrebbe andare storto."),
                q("evitamento", "Rifiuto inviti, opportunità o attività perché temo il disagio o il giudizio che potrei provare."),
                q("evitamento", "Durante le situazioni sociali uso accorgimenti per non farmi notare, come parlare poco, restare al telefono o preparare mentalmente ogni frase."),
                q("evitamento", "Cerco di lasciare presto una situazione sociale a causa dell'ansia."),
                q("evitamento", "Dopo un'interazione ripenso a lungo a ciò che ho detto o fatto, concentrandomi soprattutto sui possibili errori."),
                q("evitamento", "L'ansia sociale condiziona scelte importanti relative a relazioni, studio, lavoro o attività che vorrei svolgere.")));

        saveGlobal(id, "LOW", "Le esperienze di ansia sociale sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le esperienze di ansia sociale nelle quattro aree esplorate. Paura del giudizio, tensione nelle interazioni, difficoltà di prestazione ed evitamento non formano, nelle tue risposte, un insieme ampio e ricorrente.",
                "Questo non significa sentirti sempre disinvolto: timidezza, tensione e desiderio di fare una buona impressione sono esperienze comuni. Il profilo suggerisce però che giudizio ed esposizione tendono a non limitare stabilmente le tue scelte nei contesti considerati. Situazioni molto specifiche, recenti o non incluse negli item possono comunque avere un peso e vanno valutate per il disagio e l'interferenza che producono.");
        saveGlobal(id, "MIXED", "Le esperienze di ansia sociale sembrano presenti in modo variabile",
                "Le tue risposte descrivono ansia sociale presente in alcune aree e contenuta in altre. La libertà di partecipare sembra quindi cambiare con persone, familiarità, tipo di prestazione e livello di esposizione al giudizio.",
                "Potresti sentirti relativamente a tuo agio in contesti familiari ma provare maggiore tensione quando sei osservato, devi prendere iniziativa o temi una valutazione. Esclusione, discriminazione, differenze linguistiche o ambienti realmente ostili possono inoltre rendere alcuni timori comprensibili e contestuali. Le schede sotto aiutano a riconoscere dove aumentano anticipazione, protezioni ed evitamento, senza trasformare la variabilità in una diagnosi generale.");
        saveGlobal(id, "FOCUSED", "Le esperienze di ansia sociale sembrano più presenti in una o due aree",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore libertà. La presenza complessiva dipende quindi soprattutto da un tipo di situazione sociale o da una fase specifica del ciclo dell'ansia.",
                "Le analisi per area possono chiarire se il nucleo riguarda giudizio e imbarazzo, conversazioni, prestazione sotto osservazione oppure anticipazione ed evitamento. Osserva previsioni, segnali fisici, comportamenti protettivi e ripensamento successivo, verificando quali elementi mantengono la difficoltà. Il risultato non stabilisce una diagnosi, ma può aiutarti a descrivere con maggiore precisione ciò che accade e il suo impatto.");
        saveGlobal(id, "BROAD", "Le esperienze di ansia sociale sembrano frequentemente presenti in più aree",
                "Le risposte indicano paura, tensione o evitamento frequenti in almeno tre aree del questionario. Il profilo coinvolge più tipi di interazione e valutazione, con possibili effetti sia prima e durante le situazioni sia nel ripensamento successivo.",
                "Un andamento ampio rende utile osservare quanto la paura modifica partecipazione, opportunità, relazioni, studio o lavoro e quali comportamenti protettivi sembrano indispensabili. Esperienze di esclusione, altre forme d'ansia, depressione e differenze comunicative possono contribuire e richiedono una lettura contestuale. Se la difficoltà causa sofferenza o limitazioni importanti, una valutazione professionale può chiarire il quadro. L'ansia sociale è trattabile, ma questo questionario resta informativo e non diagnostico.");

        saveAreaInsights(id, "valutazione",
                "Il possibile giudizio degli altri sembra generare una preoccupazione contenuta e gli errori sociali tendono a restare episodi circoscritti.",
                "In alcune situazioni potresti controllare molto l'impressione che dai o interpretare segnali ambigui come possibili valutazioni negative.",
                "Le risposte mostrano una paura frequente di essere giudicato, rifiutato o notato per i segnali d'ansia, con forte attenzione a come potresti apparire.");
        saveAreaInsights(id, "interazione",
                "Conversazioni, richieste e nuove conoscenze sembrano generalmente affrontabili senza un'eccessiva sorveglianza di te stesso.",
                "Alcune interazioni, soprattutto con persone nuove, gruppi o interlocutori importanti, possono aumentare tensione e autocontrollo.",
                "Le risposte indicano una forte tensione nelle interazioni, con difficoltà a iniziare, partecipare o esprimerti quando l'attenzione può concentrarsi su di te.");
        saveAreaInsights(id, "prestazione",
                "Situazioni di esposizione o valutazione possono attivare normale tensione senza interferire frequentemente con la tua prestazione.",
                "Parlare in pubblico, essere osservato o affrontare valutazioni può provocarti ansia significativa in alcuni contesti specifici.",
                "Le risposte mostrano un'ansia frequente quando devi esibirti, intervenire o svolgere attività sotto osservazione, con possibile interferenza sulla prestazione.");
        saveAreaInsights(id, "evitamento",
                "Preoccupazione anticipata, strategie per nasconderti e ripensamenti successivi sembrano avere un ruolo contenuto nelle tue scelte sociali.",
                "Puoi anticipare o rielaborare alcune situazioni e talvolta ridurre la partecipazione per gestire il disagio, soprattutto quando l'evento è importante.",
                "Le risposte indicano un ciclo frequente di preoccupazione anticipata, evitamento o comportamenti protettivi e ripensamento negativo dopo gli incontri, con possibile impatto sulle opportunità.");
    }

    private void seedPerceivedNarcissisticRelationshipDynamicsTest() {
        String id = "dinamiche-narcisistiche-partner";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Dinamiche narcisistiche percepite nella relazione di coppia",
                "Riflessione sulla relazione",
                "Osserva la tua percezione di reciprocità, gestione del confronto, confini e impatto emotivo nella relazione, senza diagnosticare il partner.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e descrive la tua percezione: non può diagnosticare né etichettare il partner. Rispondi pensando a episodi concreti e ricorrenti degli ultimi mesi, non a un singolo litigio. Controllo, umiliazioni, minacce o violenza richiedono attenzione indipendentemente da diagnosi: nelle emergenze chiama il 112; il 1522 aiuta gratuitamente le donne vittime di violenza e stalking.",
                version, false,
                "Frequenza complessiva delle dinamiche osservate",
                "Frequenza delle dinamiche osservate",
                true, 10).withSeo(
                "Test partner narcisista: dinamiche di coppia | Spazio Test",
                "Questionario sulle dinamiche percepite nel partner: reciprocità, confronto, confini e impatto emotivo. 24 domande; non etichetta né diagnostica la persona.")
                .withResponseInstruction("Pensando a episodi concreti degli ultimi tre mesi, con quale frequenza è accaduto?"));

        saveReference(id, "Narcissistic Personality Disorder — Merck Manual Professional Edition",
                "https://www.merckmanuals.com/professional/psychiatric-disorders/personality-disorders/narcissistic-personality-disorder-npd", 1);
        saveReference(id, "Narcissism and Intimate Partner Violence: systematic review and meta-analysis — Oliver e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/37702183/", 2);

        saveArea(id, "reciprocita", "Reciprocità, empatia e spazio emotivo", 1);
        saveArea(id, "centralita", "Centralità, ammirazione e aspettative", 2);
        saveArea(id, "confronto", "Confronto, critica e responsabilità", 3);
        saveArea(id, "confini", "Confini, controllo e impatto sulla relazione", 4);

        saveQuestions(id, List.of(
                q("reciprocita", "Quando racconto un problema, il mio partner riporta presto la conversazione su di sé o sui propri bisogni."),
                q("reciprocita", "Il mio partner svaluta ciò che provo quando le mie emozioni sono scomode per lui o lei."),
                qe("reciprocita", "Il mio partner fatica a riconoscere l'effetto che le sue parole o azioni hanno su di me.",
                        "descrivo di essermi sentito ferito e la conversazione si sposta subito sulle sue intenzioni."),
                q("reciprocita", "Il sostegno del mio partner diminuisce quando ciò di cui ho bisogno entra in conflitto con i suoi programmi o desideri."),
                q("reciprocita", "Nella relazione, i bisogni e gli interessi del mio partner ricevono più spazio dei miei."),
                q("reciprocita", "Mi sento ascoltato soprattutto quando ciò che dico conferma il punto di vista o l'immagine che il mio partner ha di sé."),
                q("centralita", "Il mio partner cerca frequentemente complimenti, rassicurazioni o conferme del proprio valore."),
                q("centralita", "Il mio partner si irrita o si chiude quando l'attenzione, il riconoscimento o il successo vanno a qualcun altro."),
                q("centralita", "Il mio partner presenta spesso capacità, risultati o importanza personale in modo esagerato o competitivo."),
                q("centralita", "Il mio partner si aspetta eccezioni, precedenze o trattamenti speciali che non riconosce agli altri."),
                q("centralita", "Le decisioni di coppia finiscono frequentemente per ruotare intorno alle priorità e all'immagine del mio partner."),
                q("centralita", "Quando ottengo un successo, il mio partner tende a ridimensionarlo, competere o spostare il merito e l'attenzione su di sé."),
                q("confronto", "Il mio partner interpreta osservazioni o richieste ordinarie come attacchi, mancanze di rispetto o umiliazioni."),
                q("confronto", "Di fronte a una critica, il mio partner interrompe a lungo il dialogo."),
                q("confronto", "Durante i conflitti, il mio partner attribuisce a me o ad altri quasi tutta la responsabilità di ciò che è accaduto."),
                qe("confronto", "Il mio partner fatica a scusarsi in modo concreto e a modificare il comportamento che mi ha ferito.",
                        "dice che gli dispiace, ma ripete lo stesso comportamento senza affrontarne l'effetto."),
                q("confronto", "Quando non sono d'accordo, il mio partner svaluta la mia competenza, sensibilità o credibilità invece di discutere il problema."),
                q("confronto", "Dopo un conflitto, mi viene richiesto di rassicurare o riavvicinare il mio partner prima che ci sia spazio per il mio punto di vista."),
                q("confini", "Il mio partner fa pressione perché io modifichi amicizie, attività, abitudini o scelte personali secondo le sue preferenze."),
                qe("confini", "Il mio partner usa il senso di colpa per influenzare le mie decisioni.",
                        "davanti a una mia scelta autonoma insinua che non tengo abbastanza alla relazione."),
                q("confini", "Il mio partner passa dal valorizzarmi molto allo svalutarmi quando non soddisfo le sue aspettative."),
                q("confini", "Il mio partner oltrepassa limiti che ho espresso chiaramente, per esempio riguardo privacy, tempo, denaro, corpo o relazioni sociali."),
                q("confini", "Controllo attentamente ciò che dico o faccio per evitare reazioni sproporzionate del mio partner."),
                q("confini", "Questa relazione mi lascia frequentemente meno libero di riconoscere e proteggere i miei bisogni.")));

        saveGlobal(id, "LOW", "Le dinamiche narcisistiche percepite sembrano poco presenti nella relazione",
                "Nel complesso hai riconosciuto con poca frequenza le dinamiche esplorate nelle quattro aree. Scarsa reciprocità, bisogno di centralità, difficoltà nel confronto e pressioni sui confini non formano, nel tuo resoconto, un modello ampio e ricorrente.",
                "Ogni relazione attraversa momenti di difesa, egoismo o scarsa sintonia e un singolo episodio non definisce il partner. Il profilo suggerisce che i comportamenti considerati non si combinano abitualmente in più aree, ma non esclude problemi diversi o episodi specifici importanti. Il risultato descrive la tua percezione, non valuta direttamente l'altra persona e non riduce l'importanza di controllo, minacce o violenza, che richiedono attenzione indipendentemente dal punteggio.");
        saveGlobal(id, "MIXED", "Le dinamiche narcisistiche percepite sembrano presenti in modo variabile nella relazione",
                "Le risposte descrivono dinamiche presenti in alcune aree e meno riconoscibili in altre. Il funzionamento della relazione sembra quindi cambiare con il tema del confronto, la fase del rapporto o il modo in cui vengono espressi bisogni e limiti.",
                "Può essere utile annotare episodi concreti, frequenza, reazione a un confine e ciò che accade dopo il conflitto. Osserva se esistono ascolto, riparazione e cambiamenti verificabili oppure se alcuni schemi tornano senza coinvolgere ogni aspetto della relazione. La variabilità non permette di attribuire un disturbo di personalità al partner e non stabilisce da sola se la relazione sia sicura.");
        saveGlobal(id, "FOCUSED", "Le dinamiche narcisistiche percepite sembrano più presenti in una o due aree della relazione",
                "Una o due aree mostrano dinamiche frequenti, mentre negli altri aspetti la relazione sembra avere un funzionamento diverso. La presenza complessiva è quindi sostenuta soprattutto da uno specifico modello di reciprocità, centralità, conflitto o controllo.",
                "Concentrarti su comportamenti osservabili, conseguenze e possibilità di cambiamento può aiutarti più di un'etichetta sul partner. Le schede sotto chiariscono dove si concentra il problema e se incide su autostima, libertà di scelta o possibilità di esprimere un limite. Se la dinamica causa sofferenza, confusione o limita la tua autonomia, un confronto individuale con uno psicologo o psicoterapeuta può offrire uno spazio protetto di valutazione.");
        saveGlobal(id, "BROAD", "Le dinamiche narcisistiche percepite sembrano frequentemente presenti in più aree della relazione",
                "Le risposte indicano squilibri frequenti in almeno tre aree tra reciprocità, centralità, confronto e rispetto dei confini. Il resoconto descrive quindi un modello relazionale esteso, con possibile impatto su benessere, libertà di espressione e autonomia.",
                "Questo profilo non dimostra che il partner abbia un disturbo narcisistico di personalità e non spiega le cause dei suoi comportamenti. Indica però che più dinamiche meritano attenzione per il loro effetto concreto su di te, indipendentemente da qualsiasi diagnosi. Cerca sostegno professionale e valuta la tua sicurezza senza attendere un'etichetta: in caso di pericolo immediato chiama il 112; per donne vittime di violenza o stalking è disponibile gratuitamente il 1522, anche via chat.");

        saveAreaInsights(id, "reciprocita",
                "Le tue emozioni e necessità sembrano trovare generalmente ascolto, considerazione e uno spazio paragonabile a quello del partner.",
                "In alcune circostanze la reciprocità può ridursi e potresti sentirti ascoltato soprattutto quando i tuoi bisogni non entrano in conflitto con quelli del partner.",
                "Le risposte descrivono uno squilibrio frequente nello spazio emotivo, con i bisogni del partner spesso centrali e una limitata considerazione dell'effetto dei suoi comportamenti su di te.");
        saveAreaInsights(id, "centralita",
                "Ricerca di riconoscimento e desiderio di attenzione sembrano restare entro una dinamica che lascia spazio anche ai risultati e alle priorità altrui.",
                "In alcuni contesti il bisogno di conferme, attenzione o trattamento speciale del partner può condizionare decisioni e scambi nella coppia.",
                "Le risposte mostrano una richiesta frequente di centralità, ammirazione o trattamento speciale, con possibile competizione quando riconoscimento e successo riguardano te o altre persone.");
        saveAreaInsights(id, "confronto",
                "Disaccordi e osservazioni sembrano poter essere affrontati senza trasformarsi abitualmente in svalutazione, colpevolizzazione o chiusura prolungata.",
                "Alcuni confronti possono attivare reazioni difensive intense e rendere difficile arrivare a una responsabilità condivisa o a una riparazione concreta.",
                "Le risposte indicano reazioni frequenti di rabbia, ritiro, svalutazione o spostamento della colpa, con poco spazio per il tuo punto di vista e per cambiamenti successivi al conflitto.");
        saveAreaInsights(id, "confini",
                "Autonomia, limiti personali e libertà di scelta sembrano generalmente rispettati senza un impatto ricorrente sul tuo senso di sicurezza o valore.",
                "In alcune situazioni potresti adattare scelte, parole o confini per prevenire reazioni e mantenere l'equilibrio della relazione.",
                "Le risposte descrivono pressioni o violazioni dei confini frequenti e un impatto rilevante su libertà, chiarezza o benessere. Questi segnali meritano attenzione indipendentemente da qualsiasi etichetta diagnostica.");
    }

    private void seedGeneralizedAnxietyInformationTest() {
        String id = "ansia-generalizzata";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ansia generalizzata",
                "Autovalutazione informativa",
                "Esplora preoccupazione difficile da controllare, tensione, affaticamento, sonno e interferenza nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Rispondi pensando agli ultimi sei mesi; esperienze simili possono dipendere anche da stress, sonno, farmaci o condizioni mediche. Se l'ansia limita la tua vita o i sintomi fisici sono nuovi o intensi, parlane con un professionista qualificato.",
                version, false,
                "Frequenza complessiva delle esperienze di ansia",
                "Frequenza delle esperienze",
                true, 11).withSeo(
                "Test ansia generalizzata online | Spazio Test",
                "Questionario informativo su preoccupazione diffusa, tensione, affaticamento, sonno e impatto quotidiano. 24 domande, circa 6 minuti; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi sei mesi, con quale frequenza ti è capitato?"));

        saveReference(id, "Generalized Anxiety Disorder: What You Need to Know — NIMH",
                "https://www.nimh.nih.gov/health/publications/generalized-anxiety-disorder-gad", 1);
        saveReference(id, "Assessing generalised anxiety disorder — NICE CG113",
                "https://www.nice.org.uk/guidance/cg113/chapter/Appendix-Assessing-generalised-anxiety-disorder", 2);

        saveArea(id, "preoccupazione", "Preoccupazione diffusa e difficoltà di controllo", 1);
        saveArea(id, "attivazione", "Tensione fisica e difficoltà a rilassarsi", 2);
        saveArea(id, "risorse", "Concentrazione, affaticamento e irritabilità", 3);
        saveArea(id, "impatto", "Sonno e impatto sulla vita quotidiana", 4);

        saveQuestions(id, List.of(
                q("preoccupazione", "Mi preoccupo nello stesso periodo per molti ambiti diversi, come salute, lavoro, denaro, famiglia o impegni quotidiani."),
                q("preoccupazione", "Quando inizio a preoccuparmi, faccio fatica a fermare o spostare il corso dei pensieri."),
                q("preoccupazione", "Appena una preoccupazione si riduce, la mia mente trova rapidamente un altro possibile problema su cui concentrarsi."),
                q("preoccupazione", "Anticipo conseguenze negative anche quando ho poche informazioni che facciano pensare che accadranno."),
                q("preoccupazione", "Sento il bisogno di prevedere e prepararmi a molti possibili problemi prima di riuscire a sentirmi tranquillo."),
                q("preoccupazione", "Anche quando riconosco che una preoccupazione è sproporzionata, continuo a rimuginarci sopra."),
                q("attivazione", "Mi sento in allerta anche quando non ci sono pericoli immediati."),
                q("attivazione", "Accumulo tensione nei muscoli, per esempio a mandibola, collo, spalle, schiena o mani."),
                q("attivazione", "Faccio fatica a rilassare davvero il corpo anche quando ho tempo libero e non ci sono problemi immediati."),
                q("attivazione", "Rumori, imprevisti o richieste improvvise mi fanno sobbalzare o reagire con una forte tensione."),
                q("attivazione", "Nei periodi di preoccupazione avverto disturbi fisici come mal di testa, fastidi allo stomaco, tremore o sudorazione."),
                qe("attivazione", "Dopo una situazione stressante, il mio corpo rimane attivato a lungo anche quando il problema è terminato.",
                        "ore dopo sento ancora i muscoli tesi o il battito accelerato."),
                q("risorse", "Le preoccupazioni interrompono la concentrazione mentre lavoro, studio, leggo o seguo una conversazione."),
                qe("risorse", "Quando devo risolvere un problema, la mente sembra bloccarsi.",
                        "rileggo le stesse informazioni senza riuscire a scegliere il passo successivo."),
                q("risorse", "Mi sento affaticato anche dopo giornate che non richiederebbero uno sforzo eccezionale."),
                q("risorse", "Le normali richieste quotidiane mi sembrano più faticose perché una parte della mia attenzione resta assorbita dall'ansia."),
                q("risorse", "Quando sono preoccupato divento facilmente irritabile, impaziente o sensibile alle piccole difficoltà."),
                qe("risorse", "Valutare tutti i possibili rischi rende difficile prendere una decisione.",
                        "continuo a confrontare scenari finché rimando anche una scelta semplice."),
                q("impatto", "Faccio fatica ad addormentarmi perché la mente continua a esaminare problemi o scenari futuri."),
                q("impatto", "Mi sveglio durante la notte con pensieri di preoccupazione che rendono difficile riprendere sonno."),
                q("impatto", "Al risveglio mi sento poco riposato perché tensione o pensieri hanno disturbato il sonno."),
                q("impatto", "Rimando decisioni perché l'incertezza mi fa temere conseguenze negative."),
                q("impatto", "La preoccupazione interferisce con lavoro, studio, relazioni, cura di me o gestione delle attività quotidiane."),
                q("impatto", "Fatico a godermi un momento positivo o a essere presente perché sto già pensando a ciò che potrebbe andare storto.")));

        saveGlobal(id, "LOW", "Le esperienze associate all'ansia generalizzata sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le esperienze di ansia nelle quattro aree esplorate. Preoccupazione difficile da controllare, tensione, affaticamento e interferenza quotidiana non formano, nelle tue risposte, un insieme ampio e persistente.",
                "Questo non significa essere sempre tranquillo: preoccupazione e attivazione possono aumentare durante problemi concreti o periodi impegnativi. Il profilo suggerisce però che tendono a restare circoscritte e a ridursi senza coinvolgere stabilmente più aspetti della vita. Difficoltà recenti, sintomi fisici o temi non coperti dagli item possono comunque meritare attenzione; il risultato non conferma né esclude un disturbo d'ansia.");
        saveGlobal(id, "MIXED", "Le esperienze associate all'ansia generalizzata sembrano presenti in modo variabile",
                "Le tue risposte descrivono ansia presente in alcune aree e più contenuta in altre. Preoccupazione, attivazione fisica, risorse cognitive e funzionamento sembrano quindi cambiare con periodi, temi e condizioni di stress.",
                "Osserva se l'ansia diminuisce quando un problema concreto si risolve oppure continua a spostarsi tra scenari diversi. Sonno, caffeina, salute fisica, carico di responsabilità e altre difficoltà psicologiche possono modificare il profilo e richiedono una lettura contestuale. Durata, controllabilità e impatto sono più informativi della sola media; questo risultato non costituisce una diagnosi.");
        saveGlobal(id, "FOCUSED", "Le esperienze associate all'ansia generalizzata sembrano più presenti in una o due aree",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio. La presenza complessiva dipende quindi soprattutto da un nucleo di preoccupazione, attivazione o conseguenze quotidiane e non da ansia uniforme in tutto il profilo.",
                "L'analisi specifica può mostrare se emergono soprattutto difficoltà di controllo dei pensieri, tensione fisica, affaticamento cognitivo oppure sonno e funzionamento. Osserva durata, situazioni scatenanti e strategie usate per ottenere certezza o sollievo, verificando se riducono davvero il problema nel tempo. Se la difficoltà persiste, aumenta o causa sofferenza, puoi confrontarti con un professionista qualificato.");
        saveGlobal(id, "BROAD", "Le esperienze associate all'ansia generalizzata sembrano frequentemente presenti in più aree",
                "Le risposte indicano preoccupazione, tensione o conseguenze frequenti in almeno tre aree del questionario. Il profilo coinvolge quindi sia l'esperienza dell'ansia sia le risorse necessarie per concentrarti, riposare e partecipare alle attività quotidiane.",
                "Un andamento ampio rende utile considerare durata, difficoltà di controllo, interferenza e possibili fattori fisici, farmacologici o contestuali. Se le esperienze durano da mesi e limitano sonno, relazioni, studio o lavoro, una valutazione professionale può chiarire il quadro, escludere altre cause e individuare forme di aiuto efficaci. L'ansia è trattabile, ma questo questionario resta informativo e non diagnostico.");

        saveAreaInsights(id, "preoccupazione",
                "Le preoccupazioni sembrano generalmente legate a problemi specifici e tendono a ridursi o a diventare gestibili quando la situazione cambia.",
                "In alcuni periodi i pensieri possono estendersi a più ambiti e risultare difficili da interrompere, soprattutto davanti all'incertezza.",
                "Le risposte descrivono una preoccupazione frequente, diffusa tra temi diversi e difficile da controllare anche quando ne riconosci l'eccesso.");
        saveAreaInsights(id, "attivazione",
                "Il corpo sembra riuscire generalmente a ridurre l'allerta e la tensione una volta terminata una situazione stressante.",
                "In alcuni momenti puoi sentirti irrequieto, contratto o fisicamente attivato, con una certa difficoltà a rilassarti del tutto.",
                "Le risposte indicano uno stato frequente di allerta e tensione fisica che può persistere anche senza un pericolo immediato o dopo la fine dello stress.");
        saveAreaInsights(id, "risorse",
                "Concentrazione, energia e pazienza sembrano generalmente disponibili e non vengono assorbite in modo ricorrente dalle preoccupazioni.",
                "L'ansia può ridurre in alcuni contesti concentrazione ed energia, rendendo più faticose decisioni, impegni o piccoli imprevisti.",
                "Le risposte mostrano un'interferenza frequente su concentrazione, energia e tolleranza alle difficoltà, con possibile blocco decisionale o irritabilità.");
        saveAreaInsights(id, "impatto",
                "Sonno, attività e capacità di vivere il presente sembrano generalmente preservati, anche quando attraversi momenti di preoccupazione.",
                "In alcuni periodi l'ansia può disturbare il riposo, favorire rinvii o rendere più difficile essere presente nelle attività quotidiane.",
                "Le risposte indicano conseguenze frequenti sul sonno e sul funzionamento quotidiano, con possibili rinunce, rinvii o difficoltà a partecipare pienamente alla tua vita.");
    }

    private void seedDepressedMoodInformationTest() {
        String id = "umore-depresso";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Umore depresso e sintomi depressivi",
                "Autovalutazione informativa",
                "Esplora umore, perdita di interesse, energia, pensieri su di sé e funzionamento quotidiano nelle ultime due settimane.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato. Rispondi pensando alle ultime due settimane; il risultato non stabilisce un disturbo depressivo e non valuta il rischio suicidario. Se pensi di farti del male o non ti senti al sicuro, non attendere il risultato: chiama il 112 o vai al Pronto Soccorso.",
                version, false,
                "Frequenza complessiva delle esperienze legate all'umore",
                "Frequenza delle esperienze",
                true, 12).withSeo(
                "Test depressione online: umore e sintomi | Spazio Test",
                "Questionario su umore, interesse, energia e funzionamento nelle ultime due settimane. 24 domande; non diagnostico e non valuta il rischio suicidario.")
                .withResponseInstruction("Pensando alle ultime due settimane, con quale frequenza ti è capitato?"));

        saveReference(id, "Depressive disorder — World Health Organization",
                "https://www.who.int/news-room/fact-sheets/detail/depression", 1);
        saveReference(id, "Depression — National Institute of Mental Health",
                "https://www.nimh.nih.gov/health/publications/depression", 2);

        saveArea(id, "umore", "Tono dell'umore e capacità di provare piacere", 1);
        saveArea(id, "energia", "Energia, motivazione e attivazione", 2);
        saveArea(id, "pensieri", "Autostima, autocritica e prospettiva futura", 3);
        saveArea(id, "funzionamento", "Sonno, appetito, concentrazione e funzionamento", 4);

        saveQuestions(id, List.of(
                q("umore", "Mi sento triste, vuoto o emotivamente abbattuto per gran parte della giornata."),
                q("umore", "Le attività che di solito mi interessano o mi coinvolgono hanno perso attrattiva."),
                q("umore", "Faccio fatica a provare piacere o soddisfazione anche quando accade qualcosa di positivo."),
                q("umore", "Mi sento più irritabile del solito."),
                q("umore", "Tendo a ritirarmi dalle persone perché mi sento emotivamente distante."),
                q("umore", "Il tono dell'umore rimane basso anche quando ricevo sostegno o vivo un momento favorevole."),
                q("energia", "Inizio la giornata con poca energia, come se le mie risorse fossero già ridotte."),
                q("energia", "Attività quotidiane semplici richiedono uno sforzo molto maggiore del solito."),
                qe("energia", "Faccio fatica a iniziare azioni di cura personale, domestiche o lavorative anche quando so che sono necessarie.",
                        "rimando il lavarmi, preparare un pasto o iniziare un compito essenziale."),
                q("energia", "Mi sento rallentato nei pensieri, nei movimenti o nel modo di parlare."),
                q("energia", "Rimango inattivo più a lungo di quanto vorrei perché non riesco a trovare la spinta per muovermi."),
                q("energia", "Ho ridotto o interrotto attività e responsabilità per mancanza di motivazione o di forze."),
                q("pensieri", "Giudico me stesso in modo prevalentemente negativo, anche per caratteristiche o difficoltà comuni."),
                q("pensieri", "Provo un senso di colpa intenso o mi attribuisco responsabilità maggiori di quelle che ho realmente."),
                q("pensieri", "Interpreto errori o risultati deludenti come prove del mio scarso valore personale."),
                q("pensieri", "Mi sento inutile."),
                q("pensieri", "Immagino il futuro come privo di possibilità positive o di cambiamenti significativi."),
                q("pensieri", "Faccio fatica a riconoscere le mie capacità."),
                q("funzionamento", "Faccio fatica ad addormentarmi, mi sveglio spesso o mi sveglio molto prima del previsto."),
                q("funzionamento", "Dormo molto più del solito o faccio fatica ad alzarmi anche dopo molte ore di sonno."),
                q("funzionamento", "Il mio appetito è diminuito o aumentato in modo evidente rispetto al mio equilibrio abituale."),
                q("funzionamento", "Faccio fatica a concentrarmi, ricordare informazioni o seguire un'attività fino alla fine."),
                qe("funzionamento", "Anche decisioni semplici diventano difficili da prendere.",
                        "scegliere cosa mangiare o quale attività iniziare richiede molto tempo e sforzo."),
                q("funzionamento", "Il mio stato emotivo interferisce con lavoro, studio, relazioni, cura personale o gestione della vita quotidiana.")));

        saveGlobal(id, "LOW", "Le esperienze di umore depresso sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le esperienze legate all'umore depresso nelle quattro aree. Calo del piacere, riduzione dell'energia, pensieri negativi e difficoltà di funzionamento non formano, nelle tue risposte, un insieme frequente e diffuso nelle ultime due settimane.",
                "Questo non esclude giornate difficili, tristezza, lutto o stanchezza, né problemi specifici non inclusi nelle domande. Il profilo descrive il periodo attuale e può cambiare; osserva eventuali peggioramenti, durata e conseguenze sulla cura di te anche se la media è contenuta. Il risultato resta informativo e questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");
        saveGlobal(id, "MIXED", "Le esperienze di umore depresso sembrano presenti in modo variabile",
                "Le risposte descrivono esperienze depressive presenti in alcune aree e meno frequenti in altre. Umore, piacere, energia, pensieri e funzionamento non si muovono quindi tutti nello stesso modo nelle ultime due settimane.",
                "Osserva durata, eventi recenti, sonno, salute, risorse disponibili e capacità di recuperare quando il contesto cambia. Una distribuzione variabile non permette di distinguere stress, lutto, problemi fisici, effetti di sostanze o un disturbo dell'umore, ma può mostrare dove il costo è maggiore. Il risultato resta informativo e questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");
        saveGlobal(id, "FOCUSED", "Le esperienze di umore depresso sembrano più presenti in una o due aree",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio. La presenza complessiva dipende quindi soprattutto da un nucleo specifico di umore, energia, pensieri o funzionamento, non da un andamento uniforme.",
                "L'analisi per area può chiarire se la difficoltà riguarda soprattutto piacere e partecipazione, attivazione, modo di valutarti oppure attività quotidiane. Osserva da quanto dura, se sta peggiorando e quanto incide su relazioni, responsabilità e cura personale; se persiste o causa sofferenza, parlane con un professionista qualificato. Il risultato resta informativo e questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");
        saveGlobal(id, "BROAD", "Le esperienze di umore depresso sembrano frequentemente presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre aree tra umore e piacere, energia, pensieri e funzionamento. Il profilo appare quindi diffuso nelle ultime due settimane e può incidere contemporaneamente su vissuto emotivo, risorse e partecipazione quotidiana.",
                "È importante considerare durata, cambiamento rispetto al solito, cause mediche o farmacologiche, uso di sostanze ed eventuali periodi passati di energia insolitamente elevata. Se le difficoltà sono presenti per gran parte dei giorni e limitano la tua vita, una valutazione professionale può chiarire il quadro; i problemi depressivi sono trattabili e chiedere sostegno è appropriato. Il risultato resta informativo e questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");

        saveAreaInsights(id, "umore",
                "Interesse, partecipazione emotiva e capacità di provare piacere sembrano generalmente preservati, anche in presenza di giornate difficili.",
                "In alcuni momenti puoi avvertire tristezza, irritabilità, distacco o minore piacere, soprattutto durante periodi impegnativi.",
                "Le risposte descrivono un calo frequente del tono dell'umore e della capacità di interessarti o provare piacere, con possibile ritiro emotivo e sociale.");
        saveAreaInsights(id, "energia",
                "Energia e motivazione sembrano generalmente sufficienti per iniziare e portare avanti le attività quotidiane.",
                "In alcuni periodi compiti e responsabilità possono richiedere più sforzo, con rallentamento o difficoltà a trovare la spinta iniziale.",
                "Le risposte indicano una riduzione frequente di energia e motivazione, con possibile rallentamento, inattività o rinuncia ad attività importanti.");
        saveAreaInsights(id, "pensieri",
                "Il modo in cui valuti te stesso e il futuro sembra generalmente conservare equilibrio, riconoscendo limiti, risorse e possibilità di cambiamento.",
                "Nei momenti difficili possono aumentare autocritica, colpa o pessimismo, rendendo meno accessibili qualità e prospettive positive.",
                "Le risposte mostrano pensieri negativi frequenti su valore personale, responsabilità e futuro, con forte autocritica e difficoltà a riconoscere risorse e possibilità.");
        saveAreaInsights(id, "funzionamento",
                "Sonno, appetito, concentrazione e gestione delle attività sembrano generalmente vicini al tuo equilibrio abituale.",
                "Alcuni cambiamenti del riposo, dell'appetito o della concentrazione possono rendere più difficile mantenere ritmo e responsabilità in determinati periodi.",
                "Le risposte indicano cambiamenti frequenti nel sonno, nell'appetito o nelle capacità cognitive, con un impatto rilevante sulle attività e sulla cura quotidiana.");
    }

    private void seedPeoplePleasingInformationTest() {
        String id = "people-pleasing";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "People pleasing e bisogno di approvazione",
                "Autovalutazione informativa",
                "Esplora bisogno di approvazione, difficoltà a dire di no, paura del conflitto e spazio riservato ai propri bisogni.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo: “people pleaser” è un'espressione comune, non una diagnosi. Rispondi pensando agli ultimi mesi e a relazioni diverse; contesto e differenze di potere possono influenzare le risposte. In una relazione minacciosa, adattarsi o evitare il conflitto può essere una strategia protettiva.",
                version, false,
                "Frequenza complessiva delle dinamiche di compiacenza",
                "Frequenza delle dinamiche",
                true, 13).withSeo(
                "Test people pleaser: approvazione e confini | Spazio Test",
                "Questionario informativo su people pleasing, bisogno di approvazione, confini e difficoltà a dire di no. 24 domande, circa 6 minuti, senza registrazione.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e a relazioni diverse, con quale frequenza ti è capitato?"));

        saveReference(id, "Distinctions of unmitigated communion from communion",
                "https://pubmed.ncbi.nlm.nih.gov/9686454/", 1);
        saveReference(id, "A theory of unmitigated communion",
                "https://pubmed.ncbi.nlm.nih.gov/15647153/", 2);

        saveArea(id, "approvazione", "Bisogno di approvazione e paura del rifiuto", 1);
        saveArea(id, "confini", "Dire no, porre limiti e tollerare il dispiacere", 2);
        saveArea(id, "silenzio", "Autosilenziamento e gestione del conflitto", 3);
        saveArea(id, "sacrificio", "Sovraresponsabilità e trascuratezza di sé", 4);

        saveQuestions(id, List.of(
                q("approvazione", "Cambio opinione o preferenza quando percepisco che potrebbe non piacere alle persone presenti."),
                q("approvazione", "Cerco conferme che gli altri siano contenti di me prima di sentirmi tranquillo in una relazione."),
                q("approvazione", "Interpreto il disappunto di una persona come un possibile segnale di rifiuto o allontanamento."),
                q("approvazione", "Mi assumo rapidamente la colpa, anche prima di capire se la responsabilità sia davvero mia."),
                q("approvazione", "Il mio umore dipende molto dal sapere che le persone intorno a me sono soddisfatte di ciò che faccio."),
                q("approvazione", "Cerco di essere apprezzato anche da persone la cui opinione, a mente fredda, non è importante per me."),
                q("confini", "Accetto richieste quando vorrei dire di no, per evitare di deludere o sembrare egoista."),
                q("confini", "Fatico a rifiutare una richiesta eccessiva se l'altra persona sembra aver bisogno di me."),
                q("confini", "Quando pongo un limite, sento di doverlo giustificare a lungo perché sia considerato legittimo."),
                q("confini", "Modifico i miei programmi per assecondare gli altri anche quando non è realmente necessario."),
                q("confini", "Dopo aver detto di no provo colpa o ansia e ripenso a lungo alla possibile reazione dell'altra persona."),
                q("confini", "Se qualcuno insiste dopo un mio rifiuto, finisco spesso per cedere anche se il mio limite non è cambiato."),
                q("silenzio", "Evito di esprimere un disaccordo per non creare tensione o cambiare l'immagine positiva che gli altri hanno di me."),
                q("silenzio", "Nascondo la rabbia per mantenere un clima sereno nella relazione."),
                qe("silenzio", "Adatto molto il mio comportamento a ciò che penso gli altri preferiscano.",
                        "in un gruppo cambio tono, opinione o interessi mostrati per sentirmi accettato."),
                q("silenzio", "Rinuncio a chiedere aiuto o sostegno perché non voglio pesare sugli altri."),
                q("silenzio", "Mostro accordo anche quando penso qualcosa di diverso."),
                q("silenzio", "Dopo un conflitto mi concentro sul ristabilire subito l'armonia prima che il mio problema sia stato davvero ascoltato."),
                q("sacrificio", "Mi sento responsabile di migliorare l'umore delle persone a cui tengo."),
                qe("sacrificio", "Cerco di anticipare i bisogni degli altri senza aspettare che vengano espressi, per evitare loro qualsiasi difficoltà.",
                        "offro aiuto o cambio programmi prima che qualcuno me lo chieda."),
                q("sacrificio", "Continuo ad aiutare anche quando sono stanco, sovraccarico o avrei bisogno di fermarmi."),
                q("sacrificio", "Rimando la cura personale per occuparmi delle priorità altrui."),
                qe("sacrificio", "Mi sento particolarmente utile o degno di affetto quando qualcuno ha bisogno di me.",
                        "mi sento più sicuro del legame quando posso risolvere un problema per l'altra persona."),
                q("sacrificio", "Dopo essermi reso molto disponibile, provo risentimento o esaurimento perché i miei bisogni sono rimasti in secondo piano.")));

        saveGlobal(id, "LOW", "Le dinamiche di people pleasing sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le dinamiche di compiacenza nelle quattro aree esplorate. Bisogno di approvazione, difficoltà nei confini, autosilenziamento e sacrificio personale non formano, nelle tue risposte, un modello ampio e ricorrente.",
                "Puoi essere attento e disponibile senza rinunciare abitualmente alla tua voce, ai tuoi limiti o al recupero. Questo non significa essere sempre assertivo né dover rifiutare l'aiuto agli altri: reciprocità, contesto e libertà di scelta restano centrali. Osserva comunque eventuali relazioni o ruoli specifici in cui dire no ha conseguenze particolari, perché una media contenuta può non rappresentare ogni situazione.");
        saveGlobal(id, "MIXED", "Le dinamiche di people pleasing sembrano presenti in modo variabile",
                "Le risposte descrivono dinamiche di compiacenza presenti in alcune aree e più contenute in altre. La libertà di esprimere preferenze, mantenere limiti e proteggere le tue risorse sembra quindi cambiare con persone, richieste e situazioni.",
                "Potresti mantenere un buon equilibrio in molti rapporti ma adattarti maggiormente davanti a autorità, conflitto, bisogno altrui o rischio di disapprovazione. Nota con chi accade, quale reazione temi e se l'adattamento è una scelta sostenibile oppure lascia ansia, risentimento o stanchezza. Riconoscere questa distribuzione è più utile dell'etichetta “people pleaser” e permette di distinguere disponibilità volontaria, abitudine e risposta a un reale squilibrio di potere.");
        saveGlobal(id, "FOCUSED", "Le dinamiche di people pleasing sembrano più presenti in una o due aree",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore libertà. La presenza complessiva dipende quindi soprattutto da uno specifico meccanismo relazionale e non da una tendenza uniforme a compiacere chiunque.",
                "Le schede sotto possono chiarire se il costo nasce soprattutto dalla ricerca di approvazione, dai limiti, dal silenziamento o dall'eccessiva responsabilità verso gli altri. Osserva quali richieste lo attivano, se riesci a cambiare risposta con persone sicure e quali bisogni personali restano esclusi. Il profilo descrive abitudini e condizioni contestuali, non un'identità fissa né una mancanza di carattere.");
        saveGlobal(id, "BROAD", "Le dinamiche di people pleasing sembrano frequentemente presenti in più aree",
                "Le risposte indicano dinamiche frequenti in almeno tre aree, con possibile difficoltà a mantenere visibili bisogni, opinioni, limiti e risorse personali. Ricerca di approvazione, rinunce e responsabilità verso gli altri sembrano quindi rinforzarsi tra loro anziché restare circoscritte a un rapporto isolato.",
                "Un andamento ampio può rendere difficile distinguere la disponibilità scelta dall'adattamento guidato da colpa, paura del rifiuto o bisogno di ristabilire subito l'armonia. Se porta esaurimento, risentimento, ansia o legami poco reciproci, un confronto con uno psicologo o psicoterapeuta può aiutarti a costruire maggiore scelta e confini sostenibili. In situazioni minacciose o con forte squilibrio di potere, valuta prima la sicurezza e cerca un supporto adeguato; il risultato resta informativo e non diagnostico.");

        saveAreaInsights(id, "approvazione",
                "L'apprezzamento degli altri sembra piacevole ma non indispensabile per mantenere opinioni, scelte e una valutazione stabile di te stesso.",
                "In alcune relazioni la disapprovazione può attivare dubbi, scuse o bisogno di rassicurazione, soprattutto quando il legame conta molto.",
                "Le risposte indicano un bisogno frequente di approvazione e una forte sensibilità al possibile rifiuto, con tendenza ad adattare scelte o assumerti colpe per proteggere il legame.");
        saveAreaInsights(id, "confini",
                "Sembri generalmente capace di valutare richieste e limiti senza considerare automaticamente un rifiuto come egoismo o danno alla relazione.",
                "Alcune richieste o reazioni altrui possono rendere più difficile dire no, mantenere un limite o proteggere tempo e riposo.",
                "Le risposte descrivono una difficoltà frequente a rifiutare richieste e mantenere confini davanti a insistenza, colpa o timore di deludere.");
        saveAreaInsights(id, "silenzio",
                "Opinioni, emozioni e richieste sembrano trovare generalmente espressione anche quando potrebbero creare un confronto gestibile.",
                "In alcune situazioni potresti trattenere disaccordo o bisogni per mantenere armonia e una buona immagine nella relazione.",
                "Le risposte indicano un autosilenziamento frequente, con emozioni, opinioni o richieste nascoste per evitare conflitto, distanza o disagio altrui.");
        saveAreaInsights(id, "sacrificio",
                "La cura degli altri sembra generalmente convivere con attenzione alle tue energie, responsabilità e priorità personali.",
                "In alcuni periodi potresti assumerti più responsabilità del necessario e rimandare recupero o obiettivi per occuparti degli altri.",
                "Le risposte mostrano una sovraresponsabilità frequente verso bisogni ed emozioni altrui, con possibile trascuratezza di te, esaurimento o risentimento.");
    }

    private void seedImpostorPhenomenonInformationTest() {
        String id = "sindrome-impostore";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Sindrome dell'impostore",
                "Autovalutazione informativa",
                "Esplora difficoltà a riconoscere i successi, dubbi sulla propria competenza, perfezionismo e paura di essere smascherati.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo: il fenomeno dell'impostore non è una diagnosi e lo strumento non è clinicamente validato. Rispondi pensando agli ultimi mesi nello studio, nel lavoro o in responsabilità significative. Il risultato non misura la tua competenza reale; ruoli nuovi, aspettative poco chiare, esclusione o discriminazione possono influenzare le risposte.",
                version, false,
                "Frequenza complessiva delle esperienze di impostore",
                "Frequenza delle esperienze",
                true, 14).withSeo(
                "Test sindrome dell'impostore online | Spazio Test",
                "Questionario informativo su successi, dubbi di competenza, perfezionismo e paura di essere smascherati. 24 domande, circa 6 minuti; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e ai contesti per te significativi, con quale frequenza ti è capitato?"));

        saveReference(id, "The imposter phenomenon in high achieving women",
                "https://doi.org/10.1037/h0086006", 1);
        saveReference(id, "Impostor Phenomenon Measurement Scales: A Systematic Review",
                "https://pmc.ncbi.nlm.nih.gov/articles/PMC6463809/", 2);

        saveArea(id, "attribuzione", "Attribuzione dei successi e riconoscimento delle capacità", 1);
        saveArea(id, "esposizione", "Dubbi di competenza e paura di essere smascherati", 2);
        saveArea(id, "prestazione", "Pressione, perfezionismo e sovrapreparazione", 3);
        saveArea(id, "impatto", "Confronto, feedback e impatto sulle opportunità", 4);

        saveQuestions(id, List.of(
                q("attribuzione", "Dopo un buon risultato penso di aver avuto fortuna o condizioni favorevoli più che capacità."),
                q("attribuzione", "Considero complimenti e riconoscimenti più come gentilezza altrui che come riscontri attendibili sul mio lavoro."),
                q("attribuzione", "Quando valuto la mia competenza, fatico a tenere presenti i risultati positivi già ottenuti."),
                q("attribuzione", "Un successo mi dà sollievo sul momento, ma non aumenta la fiducia con cui affronto il compito successivo."),
                q("attribuzione", "Ridimensiono il mio contributo anche quando il mio apporto è stato importante."),
                q("attribuzione", "Quando raggiungo un obiettivo difficile, alzo subito lo standard successivo."),
                q("esposizione", "Temo che gli altri scoprano che so o valgo meno di quanto pensano."),
                qe("esposizione", "Ho la sensazione di aver dato un'immagine eccessiva delle mie capacità, anche senza aver cercato di ingannare nessuno.",
                        "dopo essere stato scelto per un ruolo penso che abbiano sopravvalutato ciò che so fare."),
                q("esposizione", "Davanti a una nuova responsabilità mi chiedo se merito davvero il ruolo o l'opportunità ricevuta."),
                q("esposizione", "Evito di fare domande o chiedere chiarimenti per paura di apparire incompetente."),
                qe("esposizione", "Interpreto una normale incertezza come prova del fatto che non dovrei trovarmi in quel contesto.",
                        "non conoscere subito una risposta mi fa pensare che non dovrei avere quel ruolo."),
                q("esposizione", "Mi sento fuori posto tra persone che considero competenti, anche quando ho qualifiche o risultati confrontabili."),
                q("prestazione", "Mi preparo molto oltre ciò che il compito richiede per ridurre il rischio che emergano mie presunte mancanze."),
                q("prestazione", "Rimando l'inizio o la consegna di un lavoro perché temo che il risultato non sia abbastanza buono."),
                q("prestazione", "Un piccolo errore mi sembra una prova generale della mia scarsa competenza."),
                q("prestazione", "Fatico a considerare concluso un compito perché continuo a controllarlo o perfezionarlo."),
                q("prestazione", "Solo un risultato quasi impeccabile mi sembra una conferma sufficiente di essere all'altezza."),
                qe("prestazione", "Quando la mia prestazione sarà visibile agli altri, tendo a sovraccaricarmi di lavoro.",
                        "per una breve presentazione lavoro molto più del necessario per evitare che si noti una lacuna."),
                q("impatto", "Confronto i miei dubbi interiori con la sicurezza che gli altri mostrano all'esterno."),
                q("impatto", "Svaluto i feedback positivi e resto concentrato soprattutto su quelli negativi o ambigui."),
                q("impatto", "Rinuncio a candidarmi a un'opportunità finché non mi sento completamente pronto."),
                q("impatto", "Quando devo presentare le mie capacità o i miei risultati, tendo a minimizzarli."),
                q("impatto", "Un successo aumenta soprattutto la pressione a mantenere le aspettative, invece della soddisfazione."),
                q("impatto", "I dubbi sul mio valore mi portano a partecipare meno alle attività che contano per me.")));

        saveGlobal(id, "LOW", "Le esperienze del fenomeno dell'impostore sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza le esperienze associate al fenomeno dell'impostore nelle quattro aree. Attribuzione esterna dei successi, paura di essere smascherato, pressione da prestazione e rinuncia alle opportunità non formano, nelle tue risposte, un modello ampio e ricorrente.",
                "Sembri riuscire generalmente a integrare risultati e feedback nella valutazione delle tue capacità, mantenendo i dubbi in proporzione al contesto. Questo non dimostra una competenza assoluta né esclude incertezze normali, soprattutto quando stai imparando o affrontando un ruolo nuovo. Valuta comunque eventuali ambienti molto competitivi o poco chiari che la media non rappresenta: un dubbio realistico può segnalare bisogno di formazione, feedback o condizioni più eque.");
        saveGlobal(id, "MIXED", "Le esperienze del fenomeno dell'impostore sembrano presenti in modo variabile",
                "Le risposte descrivono esperienze dell'impostore presenti in alcune aree e più contenute in altre. La capacità di riconoscere risultati e competenze sembra quindi cambiare davanti a specifiche persone, compiti, livelli di visibilità o passaggi di ruolo.",
                "Potresti fidarti delle tue capacità in molte situazioni ma dubitarne quando aumentano confronto, novità o ambiguità del feedback. Nota se il dubbio si riduce con esperienza e informazioni concrete oppure persiste anche dopo risultati coerenti e riconoscimenti attendibili. Distinguere i contesti aiuta a separare ciò che richiede apprendimento da ciò che nasce da standard rigidi, scarsa appartenenza o difficoltà a interiorizzare i successi.");
        saveGlobal(id, "FOCUSED", "Le esperienze del fenomeno dell'impostore sembrano più presenti in una o due aree",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio. La presenza complessiva dipende quindi soprattutto da un meccanismo specifico e non da dubbi uniformi in ogni esperienza di studio, lavoro o responsabilità.",
                "Le schede sotto chiariscono se il costo si concentra nell'attribuzione dei successi, nella paura di essere smascherato, nella pressione da prestazione oppure nella rinuncia a opportunità. Osserva quali compiti lo attivano e se porta a sovrapreparazione, silenzio, evitamento o difficoltà ad accettare riscontri positivi. Il risultato non misura la competenza effettiva e va letto insieme a esperienza reale, qualità dei feedback e caratteristiche dell'ambiente.");
        saveGlobal(id, "BROAD", "Le esperienze del fenomeno dell'impostore sembrano frequentemente presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre aree del modo di leggere risultati, competenza, prestazione e opportunità. Il vissuto dell'impostore appare quindi esteso e non limitato a un singolo compito o a una sola forma di dubbio.",
                "Questo schema può rendere difficile riconoscere prove di capacità, accettare una normale quota di apprendimento e partecipare senza sovraccaricarti. Se alimenta ansia, evitamento, esaurimento o rinunce importanti, un confronto professionale può aiutarti a esaminare pensieri, comportamenti e condizioni del contesto. Considera anche fattori reali come aspettative poco chiare, feedback inadeguati, esclusione o discriminazione: il risultato resta informativo, non diagnostico e non stabilisce quanto sei competente.");

        saveAreaInsights(id, "attribuzione",
                "Sembri generalmente capace di riconoscere il tuo contributo e usare successi e feedback come informazioni credibili sulle tue capacità.",
                "In alcune situazioni potresti attribuire risultati a fortuna, circostanze o aiuto altrui, rendendo meno stabile la fiducia costruita con l'esperienza.",
                "Le risposte indicano una difficoltà frequente a interiorizzare i successi, con tendenza a ridimensionare capacità e contributo personale anche davanti a riscontri positivi.");
        saveAreaInsights(id, "esposizione",
                "Dubbi e lacune sembrano generalmente compatibili con l'apprendimento, senza trasformarsi nella paura stabile di essere scoperto come inadeguato.",
                "Ruoli nuovi, valutazioni o persone considerate molto competenti possono attivare il timore di non meritare il posto o di mostrare limiti.",
                "Le risposte descrivono una paura frequente di essere smascherato come meno competente di quanto gli altri credano, con possibile difficoltà a chiedere chiarimenti o sentirti legittimato nel ruolo.");
        saveAreaInsights(id, "prestazione",
                "Sembri generalmente capace di prepararti e correggere gli errori senza richiedere una prestazione impeccabile per sentirti adeguato.",
                "Alcuni compiti visibili o importanti possono portarti a controllare molto, rimandare o investire più energie del necessario.",
                "Le risposte indicano pressione frequente verso prestazioni quasi perfette, con sovrapreparazione, controlli ripetuti o evitamento usati per proteggerti dal timore di fallire.");
        saveAreaInsights(id, "impatto",
                "Confronto e feedback sembrano generalmente lasciarti spazio per partecipare, presentare i risultati e cogliere opportunità compatibili con la tua esperienza.",
                "In alcuni contesti i dubbi possono rendere più difficile valorizzare feedback positivi, mostrarti o accettare opportunità prima di sentirti del tutto pronto.",
                "Le risposte mostrano un impatto frequente su partecipazione e opportunità, con confronti sfavorevoli, minimizzazione dei risultati e possibile rinuncia o sovraccarico.");
    }

    private void seedSelfSabotageInformationTest() {
        String id = "autosabotaggio";
        String version = "1.6";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Autosabotaggio e ostacoli agli obiettivi",
                "Autovalutazione informativa",
                "Esplora procrastinazione, evitamento e altri ostacoli ricorrenti tra intenzioni, scelte e obiettivi personalmente importanti.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo: “autosabotaggio” è un'espressione comune, non una diagnosi, e non misura volontà o disciplina. Rispondi pensando agli ultimi mesi e a obiettivi scelti davvero da te. Rimandare o abbandonare può essere adattivo quando un obiettivo è imposto, poco sicuro o incompatibile con le risorse disponibili.",
                version, false,
                "Frequenza complessiva degli ostacoli autoalimentati",
                "Frequenza degli ostacoli",
                true, 15).withSeo(
                "Test autosabotaggio online | Spazio Test",
                "Questionario informativo su procrastinazione, evitamento e ostacoli tra intenzioni e obiettivi. 24 domande, circa 6 minuti, senza registrazione.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e a obiettivi scelti da te, con quale frequenza ti è capitato?"));

        saveReference(id, "Self-defeating behavior patterns among normal individuals",
                "https://pubmed.ncbi.nlm.nih.gov/3043527/", 1);
        saveReference(id, "The nature of procrastination: a meta-analytic and theoretical review",
                "https://pubmed.ncbi.nlm.nih.gov/17201571/", 2);

        saveArea(id, "azione", "Avvio, pianificazione e procrastinazione", 1);
        saveArea(id, "protezione", "Paura della valutazione e auto-handicapping", 2);
        saveArea(id, "emozioni", "Emozioni difficili e sollievo immediato", 3);
        saveArea(id, "direzione", "Persistenza, flessibilità e scelte coerenti", 4);

        saveQuestions(id, List.of(
                q("azione", "Rimando l'inizio di attività importanti anche quando avrei tempo e so che il ritardo mi creerà difficoltà."),
                q("azione", "Aspetto di sentirmi pienamente pronto o motivato prima di compiere il primo passo verso un obiettivo."),
                qe("azione", "Mantengo gli obiettivi vaghi, senza tradurli in passi o tempi concreti, anche quando così è più difficile realizzarli.",
                        "penso “devo rimettermi in forma” senza decidere quale primo passo fare e quando."),
                q("azione", "Riempio il tempo con compiti secondari o urgenti per non affrontare quello che considero davvero prioritario."),
                q("azione", "Sottovaluto il tempo necessario e comincio soltanto quando la pressione è già elevata."),
                q("azione", "Non preparo in anticipo gli strumenti che so potrebbero aiutarmi a iniziare."),
                q("protezione", "Quando un risultato potrebbe essere giudicato, investo meno impegno di quanto potrei per non mettere davvero alla prova le mie capacità."),
                q("protezione", "Prima di una prova importante accumulo impegni o condizioni che potranno spiegare un eventuale risultato negativo."),
                q("protezione", "Evito feedback che potrebbero migliorare il risultato perché confrontarmi con le mie lacune mi mette a disagio."),
                q("protezione", "Non provo a cogliere un'opportunità realistica quando comporta il rischio visibile di un rifiuto o di un giudizio."),
                q("protezione", "Dichiaro in anticipo che un obiettivo non mi interessa molto o che andrà male, per proteggermi da un possibile insuccesso."),
                q("protezione", "Scelgo obiettivi troppo facili o quasi impossibili invece di una sfida realistica che permetterebbe di valutare il mio progresso."),
                q("emozioni", "Quando un compito suscita ansia, noia o frustrazione, passo rapidamente a un'attività che mi distrae."),
                qe("emozioni", "Scelgo un sollievo immediato anche quando prevedo che renderà più difficile ciò che conta per me in seguito.",
                        "uso il telefono per allontanare l'ansia anche sapendo che così ritardo il compito importante."),
                q("emozioni", "Dopo un errore, vergogna o autocritica occupano lo spazio che potrei usare per correggere o riprovare."),
                q("emozioni", "Rimando conversazioni scomode finché le conseguenze diventano più difficili da gestire."),
                q("emozioni", "Più un obiettivo è importante per me, più le emozioni che suscita mi portano a evitarlo."),
                q("emozioni", "Quando sono sotto pressione trascuro il riposo, anche se questo peggiora la mia capacità di proseguire."),
                q("direzione", "Inizio cambiamenti molto ambiziosi con grande slancio e li abbandono quando non riesco a sostenerne il ritmo."),
                qe("direzione", "Un'interruzione o un passo mancato diventa per me un motivo per considerare compromesso l'intero percorso.",
                        "dopo aver saltato un giorno considero fallito tutto il programma e smetto di seguirlo."),
                qe("direzione", "Continuo a usare una strategia che non funziona invece di modificarla.",
                        "mantengo lo stesso metodo di studio o lavoro anche quando i risultati mostrano che non mi aiuta."),
                q("direzione", "Abbandono obiettivi ancora realistici prima di aver dedicato loro un impegno abbastanza regolare da valutarli."),
                q("direzione", "Ripeto scelte che entrano prevedibilmente in conflitto con le mie priorità, anche dopo aver riconosciuto lo schema."),
                q("direzione", "Dopo una pausa faccio fatica a riprendere anche con un passo più piccolo.")));

        saveGlobal(id, "LOW", "I meccanismi di autosabotaggio esplorati sembrano poco presenti",
                "Nel complesso hai riconosciuto con poca frequenza gli ostacoli autoalimentati nelle quattro aree esplorate. Difficoltà di avvio, protezione dal giudizio, ricerca di sollievo immediato e rigidità nel percorso non formano, nelle tue risposte, un modello ampio e ricorrente.",
                "Sembri generalmente capace di trasformare intenzioni in passi sostenibili, attraversare il disagio e adattare il percorso quando serve. Questo non significa essere sempre produttivo: riposo, cambi di priorità e abbandono di obiettivi non più realistici possono essere forme sane di autoregolazione. Eventuali blocchi circoscritti possono dipendere dalle caratteristiche dell'obiettivo, dalle risorse disponibili o da ostacoli esterni e meritano una lettura concreta, non moralistica.");
        saveGlobal(id, "MIXED", "I meccanismi di autosabotaggio esplorati sembrano presenti in modo variabile",
                "Le risposte descrivono ostacoli presenti in alcune aree e più contenuti in altre. La continuità tra intenzioni e azioni sembra quindi cambiare con il tipo di obiettivo, le emozioni coinvolte e le condizioni in cui provi a procedere.",
                "Potresti avanzare con regolarità in molti ambiti ma bloccarti quando aumentano pressione, giudizio, incertezza o fatica. Nota quali passaggi interrompono il percorso, quale sollievo immediato ottieni e se strumenti, tempi o richieste dell'ambiente sono realistici. Individuare contesto e funzione è più utile dell'etichetta “autosabotaggio”, perché lo stesso comportamento può derivare da cause differenti.");
        saveGlobal(id, "FOCUSED", "I meccanismi di autosabotaggio esplorati sembrano più presenti in una o due aree",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio. La presenza complessiva dipende quindi soprattutto da uno specifico punto di interruzione tra obiettivi e azioni, non da difficoltà uniformi in ogni fase del percorso.",
                "Le schede sotto possono mostrare se il meccanismo prevalente riguarda avvio, protezione dal giudizio, sollievo emotivo oppure flessibilità nel mantenere e riprendere il percorso. Osserva antecedenti, conseguenze e condizioni in cui riesci invece a procedere, così da identificare supporti concreti senza trasformare il comportamento in un giudizio su di te. Non attribuisce intenzioni e va letto insieme a risorse disponibili, salute, carico reale e caratteristiche dell'ambiente.");
        saveGlobal(id, "BROAD", "I meccanismi di autosabotaggio esplorati sembrano frequentemente presenti in più aree",
                "Le risposte indicano schemi frequenti in almeno tre aree, con una distanza ricorrente tra ciò che per te conta e le azioni che riesci a sostenere. Avvio, gestione del giudizio e delle emozioni e capacità di adattare il percorso sembrano quindi ostacolarsi a vicenda anziché costituire episodi isolati.",
                "Procrastinazione, protezione dal giudizio, sollievo immediato e difficoltà a riprendere possono rinforzarsi senza essere scelte consapevoli. Se il costo riguarda più ambiti o genera forte autocritica, un confronto professionale può aiutare a comprenderne funzione, sequenza e contesto. Il risultato resta informativo e non diagnostico: non distingue da solo abitudini modificabili, ostacoli esterni, condizioni cliniche o difficoltà esecutive e non misura la tua forza di volontà.");

        saveAreaInsights(id, "azione",
                "Sembri generalmente capace di rendere concreti gli obiettivi e iniziare senza aspettare condizioni perfette, usando supporti quando servono.",
                "Alcuni compiti possono restare vaghi o slittare finché urgenza e pressione rendono più difficile affrontarli con calma.",
                "Le risposte indicano difficoltà frequenti nell'avvio e nella pianificazione, con rinvii, priorità secondarie o supporti non predisposti che aumentano pressione e costi.");
        saveAreaInsights(id, "protezione",
                "Valutazione e possibilità di fallire sembrano generalmente compatibili con un impegno realistico, la richiesta di feedback e l'accesso a opportunità.",
                "Quando un risultato tocca molto l'immagine che hai di te, potresti evitare esposizione o lasciare qualche ostacolo che renda meno diretto il giudizio sulle tue capacità.",
                "Le risposte descrivono un auto-handicapping frequente: riduzione dell'impegno, ostacoli o rinunce possono proteggere temporaneamente dal giudizio, limitando però apprendimento e opportunità.");
        saveAreaInsights(id, "emozioni",
                "Sembri generalmente capace di tollerare il disagio legato ai compiti senza sacrificare sistematicamente ciò che conta per ottenere sollievo immediato.",
                "Ansia, noia, vergogna o frustrazione possono talvolta spostarti verso distrazione e rinvio, soprattutto con obiettivi molto significativi.",
                "Le risposte indicano che il sollievo emotivo immediato prevale spesso sulle conseguenze future, alimentando evitamento, autocritica o trascuratezza delle risorse necessarie a proseguire.");
        saveAreaInsights(id, "direzione",
                "Sembri generalmente capace di mantenere un ritmo sostenibile, riprendere dopo le pause e modificare strategia o obiettivo quando le condizioni cambiano.",
                "Interruzioni, slancio iniziale o strategie poco efficaci possono rendere discontinuo il percorso, pur lasciando spazio a una ripresa.",
                "Le risposte mostrano una difficoltà frequente nel mantenere o adattare il percorso, con cicli di slancio e abbandono, rigidità o scelte ripetute poco coerenti con le priorità dichiarate.");
    }

    private void seedBorderlineTraitsInformationTest() {
        String id = "tratti-borderline-adulti";
        String version = "1.3";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Tratti associati al disturbo borderline di personalità",
                "Autovalutazione informativa",
                "Esplora esperienze recenti legate a emozioni, relazioni, immagine di sé, impulsività e reazioni sotto stress.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e non validato: non può confermare, escludere o stimare un disturbo borderline di personalità. Rispondi pensando agli ultimi tre mesi; esperienze simili possono avere spiegazioni alternative. Non valuta autolesionismo o pensieri suicidari: se pensi di farti del male o c'è un pericolo immediato, chiama il 112 o vai al Pronto Soccorso.",
                version, false,
                "Frequenza complessiva delle esperienze esplorate",
                "Frequenza delle esperienze",
                true, 16).withSeo(
                "Test sui tratti borderline nell'adulto | Spazio Test",
                "Questionario informativo su emozioni, relazioni, immagine di sé e reazioni sotto stress. 24 domande, circa 6 minuti, senza registrazione.")
                .withResponseInstruction("Pensando agli ultimi tre mesi, con quale frequenza ti è capitata questa esperienza?"));

        saveReference(id, "The Italian Version of the Borderline Personality Disorder Severity Index IV — di Giacomo e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/28604275/", 1);
        saveReference(id, "Clinical descriptions and diagnostic requirements for ICD-11 — WHO",
                "https://iris.who.int/bitstream/handle/10665/375767/9789240077263-eng.pdf?sequence=1", 2);
        saveReference(id, "Percorsi di cura per i disturbi gravi di personalità — Ministero della Salute",
                "https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_2461_allegato.pdf", 3);
        saveReference(id, "Diagnosi e trattamento del disturbo borderline di personalità — ISS, linea guida in produzione",
                "https://www.iss.it/-/diagnosi-trattamento-disturbo-borderline-personalit%C3%A0_in-prog", 4);

        saveArea(id, "emozioni", "Intensità emotiva e ritorno all'equilibrio", 1);
        saveArea(id, "relazioni", "Relazioni e sensibilità alla distanza", 2);
        saveArea(id, "identita", "Identità, immagine di sé e senso di vuoto", 3);
        saveArea(id, "impulsi", "Impulsività, rabbia e reazioni allo stress", 4);

        saveQuestions(id, List.of(
                q("emozioni", "Le mie emozioni cambiano rapidamente nell'arco della stessa giornata."),
                q("emozioni", "Una reazione emotiva intensa impiega molto tempo ad attenuarsi."),
                q("emozioni", "Durante un disaccordo, la mia reazione emotiva diventa molto intensa."),
                q("emozioni", "Dopo essermi sentito ferito, fatico a ritrovare un equilibrio emotivo."),
                q("emozioni", "Il mio umore cambia in base a ciò che accade nelle relazioni importanti."),
                qe("emozioni", "Durante un'emozione intensa, fatico a considerare informazioni diverse da ciò che sento.",
                        "durante un conflitto non riesco a tenere presenti parole rassicuranti o spiegazioni alternative."),
                q("relazioni", "Un ritardo nella risposta di una persona importante mi fa temere che si stia allontanando."),
                q("relazioni", "Cerco rassicurazioni ripetute quando percepisco distanza in una relazione."),
                q("relazioni", "Passo rapidamente dal sentirmi molto vicino a una persona al sentirmi profondamente deluso da lei."),
                q("relazioni", "Un cambiamento nel tono di una persona importante mi fa dubitare della stabilità del rapporto."),
                q("relazioni", "Quando temo un allontanamento, aumento molto i tentativi di contatto."),
                q("relazioni", "Un conflitto in una relazione importante occupa a lungo i miei pensieri."),
                qe("identita", "Il modo in cui descrivo chi sono cambia molto a seconda della situazione.",
                        "con persone diverse descrivo valori, gusti o qualità personali in modi molto differenti."),
                q("identita", "I miei obiettivi personali cambiano bruscamente da un periodo all'altro."),
                q("identita", "Provo un senso di vuoto anche in giornate che considero tranquille."),
                qe("identita", "Fatico a riconoscere preferenze che sento davvero mie.",
                        "lascio scegliere gli altri e poi non riesco a dire che cosa avrei preferito io."),
                q("identita", "Il giudizio di una persona importante cambia rapidamente il valore che attribuisco a me."),
                q("identita", "Mi sento privo di una direzione personale riconoscibile."),
                q("impulsi", "Durante emozioni intense, agisco prima di considerare le conseguenze."),
                q("impulsi", "Quando sono molto agitato, prendo decisioni importanti d'impulso."),
                q("impulsi", "Quando un impulso è forte, fatico a rimandare l'azione anche per poco."),
                q("impulsi", "La mia rabbia aumenta rapidamente durante un conflitto."),
                q("impulsi", "Sotto forte stress, interpreto le intenzioni altrui come ostili."),
                qe("impulsi", "Sotto forte stress, mi sento distaccato da ciò che accade intorno a me.",
                        "la situazione mi sembra irreale o come se la stessi osservando dall'esterno.")));

        saveGlobal(id, "LOW", "Le esperienze associate ai tratti borderline sembrano poco presenti",
                "Nelle risposte, le esperienze esplorate risultano poco frequenti in tutte e quattro le aree. Non emerge quindi una distribuzione ampia di cambiamenti emotivi, sensibilità relazionale, instabilità dell'immagine di sé o reazioni impulsive e sotto stress.",
                "Questo andamento descrive soltanto gli ultimi tre mesi e non esclude difficoltà circoscritte, recenti o non incluse nelle domande. Può essere utile osservare se alcune esperienze cambiano in particolari relazioni, durante periodi di stress o quando incidono sul funzionamento quotidiano. Il risultato non è una diagnosi e non conferma né esclude un disturbo borderline di personalità. Il questionario non valuta autolesionismo, pensieri suicidari o situazioni di pericolo: se pensi di farti del male o c'è un pericolo immediato, chiama il 112 o raggiungi il Pronto Soccorso più vicino.");
        saveGlobal(id, "MIXED", "Le esperienze associate ai tratti borderline sembrano presenti in modo variabile",
                "Le risposte descrivono esperienze presenti con frequenza diversa tra le quattro aree, senza aree che raggiungano il livello editoriale più alto. Il quadro può quindi cambiare in base al tipo di relazione, all'attivazione emotiva, allo stress e al periodo considerato.",
                "Le schede d'area aiutano a distinguere dove le esperienze sono più presenti senza trasformare piccole differenze in una classifica personale. Osserva situazioni, durata, conseguenze e condizioni in cui riesci invece a ritrovare equilibrio; stress, trauma, ansia, umore, ADHD, sostanze e contesti relazionali possono offrire spiegazioni alternative o concomitanti. Il risultato non è una diagnosi e le soglie usate sono esclusivamente editoriali. Il questionario non valuta autolesionismo, pensieri suicidari o situazioni di pericolo: se pensi di farti del male o c'è un pericolo immediato, chiama il 112 o raggiungi il Pronto Soccorso più vicino.");
        saveGlobal(id, "FOCUSED", "Le esperienze associate ai tratti borderline sembrano più presenti in una o due aree",
                "Una o due aree raccolgono esperienze riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo è quindi concentrato su aspetti specifici e non descrive un andamento uniforme dell'intero funzionamento.",
                "Consulta le schede sotto per capire se emergono soprattutto intensità emotiva, sensibilità alla distanza, immagine di sé oppure impulsività e reazioni allo stress. Nota da quanto tempo accade, in quali contesti, quale interferenza produce e quali risorse aiutano, tenendo presenti anche spiegazioni alternative o concomitanti. Il risultato non è una diagnosi; una o due aree frequenti possono essere approfondite con un professionista se causano sofferenza o limitazioni. Il questionario non valuta autolesionismo, pensieri suicidari o situazioni di pericolo: se pensi di farti del male o c'è un pericolo immediato, chiama il 112 o raggiungi il Pronto Soccorso più vicino.");
        saveGlobal(id, "BROAD", "Le esperienze associate ai tratti borderline sembrano frequentemente presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro aree esplorate. Cambiamenti emotivi, dinamiche relazionali, immagine di sé e reazioni impulsive o sotto stress possono quindi presentarsi in una distribuzione ampia nel periodo considerato.",
                "Questo profilo invita a osservare ampiezza, persistenza e interferenza concreta, senza interpretare le barre come una misura di gravità. Se le esperienze durano nel tempo, compaiono in più contesti o incidono su relazioni, lavoro, studio o benessere, un confronto con uno psicologo, psicoterapeuta o medico può aiutare a valutarle insieme alla storia personale e alle possibili alternative. Il risultato non è una diagnosi e non stima la presenza di un disturbo borderline di personalità. Il questionario non valuta autolesionismo, pensieri suicidari o situazioni di pericolo: se pensi di farti del male o c'è un pericolo immediato, chiama il 112 o raggiungi il Pronto Soccorso più vicino.");

        saveAreaInsights(id, "emozioni",
                "Le risposte descrivono cambiamenti emotivi intensi o prolungati poco frequenti nel periodo considerato. Questo non esclude reazioni circoscritte in situazioni particolarmente significative.",
                "In alcune situazioni le emozioni possono cambiare rapidamente o richiedere tempo per attenuarsi. Osserva quali eventi le attivano e che cosa facilita il ritorno all'equilibrio.",
                "Le risposte descrivono frequenti cambiamenti emotivi, reazioni intense o difficoltà nel ritorno all'equilibrio. È utile osservare durata, contesti, conseguenze e strategie che aiutano senza attribuire automaticamente queste esperienze a una diagnosi.");
        saveAreaInsights(id, "relazioni",
                "Timore di distanza, ricerca di rassicurazione e persistenza dei conflitti risultano poco frequenti nelle risposte. Una singola relazione o un periodo recente possono comunque avere un andamento diverso.",
                "Alcuni segnali di distanza o conflitto possono attivare dubbi sul legame e bisogno di rassicurazione. Considera se accade con persone o situazioni specifiche e come cambia quando la comunicazione è più chiara.",
                "Le risposte indicano una frequente sensibilità ai segnali di distanza, con rassicurazioni, aumento del contatto o oscillazioni nella percezione del legame. Il contesto e la sicurezza reale della relazione sono essenziali per comprendere queste esperienze.");
        saveAreaInsights(id, "identita",
                "Immagine di sé, preferenze, obiettivi e direzione personale appaiono generalmente stabili nel periodo considerato. Eventuali dubbi circoscritti possono essere compatibili con transizioni e cambiamenti normali.",
                "In alcuni momenti possono emergere vuoto, incertezza sulle preferenze o cambiamenti nel modo di valutarti. Osserva se dipendono soprattutto dal contesto, dal giudizio altrui o da una fase di transizione.",
                "Le risposte descrivono frequenti cambiamenti nell'immagine di sé, negli obiettivi o nel senso di direzione, oppure un ricorrente senso di vuoto. Queste esperienze possono avere significati diversi e vanno comprese nel tempo e nel contesto.");
        saveAreaInsights(id, "impulsi",
                "Azioni impulsive, incremento rapido della rabbia e reazioni di distacco o ostilità sotto stress risultano poco frequenti. Il questionario non valuta comunque tutte le possibili reazioni nelle situazioni critiche.",
                "In alcuni momenti di forte attivazione può essere più difficile rimandare un impulso, valutare conseguenze o interpretare con calma ciò che accade. Nota segnali iniziali, contesti e strategie che creano tempo prima di agire.",
                "Le risposte indicano frequenti difficoltà nel rallentare l'azione, un rapido aumento della rabbia o particolari reazioni percettive sotto forte stress. Poiché l'area riunisce fenomeni diversi, è importante distinguere quali esperienze sono presenti e quale interferenza producono.");
    }

    private void seedFearOfAbandonmentInformationTest() {
        String id = "paura-abbandono";
        String version = "1.3";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Paura dell'abbandono",
                "Autovalutazione informativa",
                "Esplora come vivi segnali di distanza, bisogno di rassicurazione, separazioni temporanee e confini nelle relazioni importanti.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo: non formula diagnosi, non classifica uno stile di attaccamento e non valuta la qualità reale delle relazioni. Rispondi pensando agli ultimi tre mesi e alle relazioni per te importanti. La paura di perdere un legame non giustifica controllo, coercizione o violenza: nelle emergenze chiama il 112; il 1522 aiuta gratuitamente le donne vittime di violenza e stalking.",
                version, false,
                "Frequenza complessiva delle esperienze esplorate",
                "Frequenza delle esperienze",
                true, 17).withSeo(
                "Paura dell'abbandono: test informativo | Spazio Test",
                "Questionario informativo sulla paura dell'abbandono nelle relazioni adulte. 24 domande, circa 6 minuti, senza registrazione.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e alle relazioni per te importanti, con quale frequenza ti è capitata questa esperienza?"));

        saveReference(id, "Italian Validation of the Adult Attachment Scale-Revised — Troisi, Parola e Margherita",
                "https://pubmed.ncbi.nlm.nih.gov/36407970/", 1);
        saveReference(id, "Psychometric properties of the Italian ECR-12 — Brugnera e colleghi",
                "https://pmc.ncbi.nlm.nih.gov/articles/PMC7453162/", 2);
        saveReference(id, "Separation anxiety in a community sample of Italian emerging adults — Iannattone e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/33937113/", 3);
        saveReference(id, "Attachment Theory and Affect Regulation — Mikulincer, Shaver e Pereg",
                "https://doi.org/10.1023/A:1024515519160", 4);

        saveArea(id, "segnali", "Sensibilità ai segnali di distanza", 1);
        saveArea(id, "rassicurazione", "Ricerca di rassicurazione e vicinanza", 2);
        saveArea(id, "distanza", "Pensieri ed emozioni durante la distanza", 3);
        saveArea(id, "confini", "Autonomia e confini quando si teme la perdita", 4);

        saveQuestions(id, List.of(
                q("segnali", "Dopo un ritardo nella risposta di una persona importante, penso che voglia allontanarsi."),
                q("segnali", "Un cambiamento nel tono di una persona importante mi sembra un segnale che il legame sia in pericolo."),
                q("segnali", "Durante un disaccordo, temo che la relazione possa finire."),
                q("segnali", "Quando una persona importante chiede spazio, penso che il suo affetto sia diminuito."),
                q("segnali", "Dopo che una persona importante annulla un programma, dubito del suo interesse."),
                qe("segnali", "Interpreto una minore disponibilità temporanea come segnale di un possibile abbandono.",
                        "una giornata molto impegnata riduce i messaggi e la leggo come possibile perdita del legame."),
                q("rassicurazione", "Chiedo conferme ripetute sull'affetto che una persona importante prova per me."),
                q("rassicurazione", "Controllo spesso se una persona importante ha letto i miei messaggi."),
                q("rassicurazione", "Aumento i tentativi di contatto quando percepisco distanza."),
                q("rassicurazione", "Cerco di sapere con precisione quando rivedrò una persona importante."),
                q("rassicurazione", "Fatico a lasciare spazio a una persona importante quando chiede tempo per sé."),
                q("rassicurazione", "Dopo una rassicurazione sul legame, il dubbio ritorna in breve tempo."),
                q("distanza", "Durante una separazione temporanea, penso a lungo alla possibilità di perdere il legame."),
                q("distanza", "Nei periodi in cui non posso contattare una persona importante, provo una forte agitazione."),
                q("distanza", "La possibilità che una relazione finisca occupa a lungo i miei pensieri."),
                q("distanza", "Prima di una separazione prevista, anticipo mentalmente scenari di rottura."),
                q("distanza", "Durante la distanza da una persona importante, fatico a concentrarmi sulle attività quotidiane."),
                q("distanza", "Dopo un saluto o una partenza, impiego molto tempo a ritrovare calma."),
                q("confini", "Trattengo un disaccordo quando temo che possa allontanare una persona importante."),
                q("confini", "Quando temo di perdere un legame, accetto richieste contrarie ai miei limiti."),
                q("confini", "Cambio programmi importanti quando penso che una persona potrebbe allontanarsi."),
                qe("confini", "Ritiro un confine quando una persona importante reagisce prendendo le distanze.",
                        "dopo aver detto di no, cambio risposta se la persona smette di scrivermi o diventa fredda."),
                q("confini", "Rinuncio a esprimere un bisogno quando temo che la relazione possa essere messa in discussione."),
                q("confini", "Quando temo che la distanza diventi definitiva, ristabilisco subito il contatto.")));

        saveGlobal(id, "LOW", "La paura dell'abbandono sembra poco presente",
                "Nelle risposte, le esperienze collegate al timore di perdere un legame risultano poco frequenti in tutte e quattro le aree. Segnali di distanza, richieste di rassicurazione, separazioni temporanee e scelte sui confini non formano quindi un andamento diffuso nel periodo considerato.",
                "Questo andamento non esclude episodi circoscritti, una relazione con caratteristiche diverse o reazioni comprensibili a segnali reali. Può essere utile osservare eventuali cambiamenti nel tempo, il contesto in cui compaiono e le conseguenze sul benessere e sulle relazioni. Il risultato non diagnostica una condizione e non classifica il tuo stile di attaccamento. La paura di perdere un legame non giustifica né rende accettabili controllo, coercizione o violenza, subiti o agiti: in un pericolo immediato chiama il 112; il 1522 offre supporto gratuito alle donne vittime di violenza e stalking.");
        saveGlobal(id, "MIXED", "La paura dell'abbandono sembra presente in modo variabile",
                "Le risposte descrivono una frequenza variabile tra le quattro aree, senza che una di esse raggiunga il livello editoriale più alto. Il timore può quindi comparire in alcuni passaggi o relazioni e restare più contenuto in altri.",
                "Le schede d'area aiutano a distinguere se il dubbio emerge soprattutto davanti a segnali ambigui, nella ricerca di rassicurazione, durante la distanza o nelle scelte sui confini. Confronta fatti osservabili e interpretazioni e nota se il quadro cambia dopo perdite, tradimenti, stress o in relazioni realmente incoerenti, perché associazioni di gruppo non spiegano il singolo caso. Il risultato non diagnostica una condizione e non classifica il tuo stile di attaccamento; le soglie sono esclusivamente editoriali. La paura di perdere un legame non giustifica né rende accettabili controllo, coercizione o violenza, subiti o agiti: in un pericolo immediato chiama il 112; il 1522 offre supporto gratuito alle donne vittime di violenza e stalking.");
        saveGlobal(id, "FOCUSED", "La paura dell'abbandono sembra più presente in una o due aree",
                "Una o due aree raccolgono esperienze riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo è quindi concentrato su modalità specifiche e non descrive allo stesso modo tutte le relazioni o tutti gli aspetti esplorati.",
                "Consulta le schede sotto per riconoscere quali passaggi emergono e in quali situazioni svolgono una funzione di protezione o producono conseguenze indesiderate. Osserva persistenza, interferenza, segnali reali, risorse disponibili e ciò che accade dopo una rassicurazione o una separazione; se la sofferenza è rilevante, un professionista può aiutare a ricostruire il contesto senza ridurlo a un'etichetta. Il risultato non diagnostica una condizione e non classifica il tuo stile di attaccamento. La paura di perdere un legame non giustifica né rende accettabili controllo, coercizione o violenza, subiti o agiti: in un pericolo immediato chiama il 112; il 1522 offre supporto gratuito alle donne vittime di violenza e stalking.");
        saveGlobal(id, "BROAD", "La paura dell'abbandono sembra frequentemente presente in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro aree esplorate. Nel periodo considerato, sensibilità ai segnali, rassicurazione, vissuti della distanza e scelte su autonomia e confini possono quindi presentarsi con una distribuzione ampia.",
                "Questo profilo invita a considerare ampiezza, persistenza e interferenza concreta, senza leggere le barre come una misura di gravità. Segnali reali, perdite recenti, relazioni incoerenti, stress, lutto, ansia o umore depresso possono offrire contesti alternativi o concomitanti; se le esperienze incidono sulla vita quotidiana, un confronto professionale può aiutare a comprenderle insieme alla storia personale. Il risultato non diagnostica una condizione e non classifica il tuo stile di attaccamento. La paura di perdere un legame non giustifica né rende accettabili controllo, coercizione o violenza, subiti o agiti: in un pericolo immediato chiama il 112; il 1522 offre supporto gratuito alle donne vittime di violenza e stalking.");

        saveAreaInsights(id, "segnali",
                "Ritardi, cambiamenti di tono, disaccordi o richieste di spazio vengono raramente interpretati come segnali di una perdita imminente. Questo non esclude reazioni a eventi specifici o a segnali reali in una particolare relazione.",
                "Alcuni segnali di distanza possono attivare dubbi sul legame, mentre in altri momenti restano compatibili con spiegazioni diverse. Osserva quali fatti precedono il dubbio, quanto dura e se si ripete nelle stesse relazioni o situazioni.",
                "Le risposte descrivono una frequente tendenza a leggere segnali ambigui o temporanei come possibile abbandono. È importante distinguere interpretazioni e fatti osservabili, considerando anche se la relazione presenta davvero incoerenza, svalutazione o insicurezza.");
        saveAreaInsights(id, "rassicurazione",
                "Ricerca ripetuta di conferme, controllo dei messaggi e aumento del contatto risultano poco frequenti. Sembri generalmente in grado di cercare vicinanza lasciando anche spazio all'altra persona.",
                "In alcuni momenti conferme, prevedibilità o maggiore contatto possono diventare particolarmente importanti. Nota quanto dura il sollievo, se la richiesta è condivisa e come cambia quando bisogni e tempi vengono comunicati con chiarezza.",
                "Le risposte indicano una frequente ricerca di rassicurazione o vicinanza, con dubbi che possono tornare rapidamente. Osserva l'effetto sul tuo benessere, sul consenso e sullo spazio reciproco, senza attribuire automaticamente intenzioni all'altra persona.");
        saveAreaInsights(id, "distanza",
                "Separazioni temporanee o periodi senza contatto sono raramente accompagnati da preoccupazione prolungata, forte agitazione o difficoltà a ritrovare calma. Una perdita recente o una situazione specifica possono comunque avere un andamento diverso.",
                "In alcune separazioni possono emergere pensieri persistenti, agitazione o minore concentrazione. Considera durata, prevedibilità, significato del legame e attività o relazioni che aiutano a mantenere continuità durante la distanza.",
                "Le risposte descrivono frequenti pensieri ed emozioni difficili durante separazioni reali o previste. Nota quanto occupano la giornata, quali contesti li intensificano e quali risorse favoriscono il ritorno alla calma, senza equipararli a una diagnosi di ansia di separazione.");
        saveAreaInsights(id, "confini",
                "Il timore di perdere un legame raramente porta a trattenere disaccordi, cambiare programmi o rinunciare a bisogni e limiti. Questo andamento non stabilisce comunque se ogni relazione sia sicura o equilibrata.",
                "In alcune situazioni il timore della distanza può rendere più difficile esprimere un bisogno o mantenere un confine. Osserva quali conseguenze temi, quali scelte restano davvero libere e se puoi chiedere supporto senza isolarti.",
                "Le risposte indicano frequenti cambiamenti di programmi, bisogni o confini per evitare una possibile perdita. Sicurezza e consenso hanno priorità sul mantenimento del legame: paura, pressione, controllo o minacce meritano attenzione indipendentemente dal risultato del test.");
    }

    private void seedFomoInformationTest() {
        String id = "fomo";
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "FOMO (Fear of Missing Out)",
                "Autovalutazione informativa",
                "Esplora la preoccupazione di perdere esperienze, il confronto con alternative e il bisogno di restare aggiornati.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo: la FOMO può comparire online e fuori dai social, ma non è una diagnosi. Rispondi pensando all'ultimo mese; il risultato non dimostra un uso problematico di social, Internet o smartphone. Se queste esperienze incidono su sonno, concentrazione, attività o relazioni, puoi parlarne con un professionista qualificato.",
                version, false,
                "Frequenza complessiva delle esperienze FOMO",
                "Frequenza delle esperienze",
                true, 18).withSeo(
                "Test FOMO: paura di perdersi qualcosa | Spazio Test",
                "Questionario informativo sulla FOMO, il confronto con le esperienze altrui e il bisogno di restare aggiornati. 24 domande, circa 6 minuti.")
                .withResponseInstruction("Pensando all'ultimo mese, con quale frequenza ti è capitata questa esperienza?"));

        saveReference(id, "Italian version of the Fear of Missing Out Scale — Casale e Fioravanti",
                "https://pubmed.ncbi.nlm.nih.gov/31704432/", 1);
        saveReference(id, "Italian version of the Online Fear of Missing Out — Sommantico e colleghi",
                "https://doi.org/10.1016/j.chbr.2024.100374", 2);
        saveReference(id, "Motivational, emotional, and behavioral correlates of fear of missing out — Przybylski e colleghi",
                "https://doi.org/10.1016/j.chb.2013.02.014", 3);
        saveReference(id, "FoMO, digital technology use, and psychological well-being: a scoping review — Groenestein e colleghi",
                "https://doi.org/10.1371/journal.pone.0308643", 4);

        saveArea(id, "inclusione", "Inclusione e appartenenza percepita", 1);
        saveArea(id, "confronto", "Confronto con esperienze alternative", 2);
        saveArea(id, "connessione", "Bisogno di restare aggiornati e connessi", 3);
        saveArea(id, "interferenza", "Interferenza su attenzione e scelte", 4);

        saveQuestions(id, List.of(
                q("inclusione", "Quando scopro che persone importanti hanno condiviso un'esperienza senza di me, mi sento escluso."),
                q("inclusione", "Quando resto fuori da un invito rivolto al mio gruppo, temo che il mio posto sia cambiato."),
                q("inclusione", "Se non posso partecipare a un incontro, temo di perdere vicinanza con chi è presente."),
                q("inclusione", "Quando vedo persone a me vicine insieme in mia assenza, dubito di essere ancora parte del gruppo."),
                q("inclusione", "Quando il gruppo discute un argomento che conosco poco, temo di restare ai margini."),
                q("inclusione", "Sapere che un gruppo si è incontrato senza di me mi fa dubitare della mia importanza per quelle persone."),
                q("confronto", "Penso che le esperienze degli altri siano più appaganti di ciò che sto facendo."),
                q("confronto", "Quando vedo un'attività a cui non partecipo, immagino che sia più interessante della mia."),
                q("confronto", "Dopo aver visto cosa fanno gli altri, considero meno soddisfacente la mia giornata."),
                q("confronto", "Penso di aver scelto l'attività sbagliata quando scopro alternative che sembrano migliori."),
                q("confronto", "Mi preoccupa che gli altri stiano vivendo più occasioni significative di me."),
                q("confronto", "Mentre vivo un'esperienza, penso che altrove potrebbe esserci qualcosa di migliore."),
                q("connessione", "Controllo gli aggiornamenti per sapere cosa stanno facendo le persone che conosco."),
                q("connessione", "Durante un periodo senza accesso ai social, penso a ciò che potrei perdermi."),
                q("connessione", "Tengo attive le notifiche per essere informato subito su ciò che accade."),
                q("connessione", "Torno a controllare gli aggiornamenti anche se li ho appena consultati."),
                q("connessione", "Mi sento inquieto quando per un po' non so cosa stanno facendo gli altri."),
                q("connessione", "Cerco informazioni su attività in corso per capire se sto perdendo qualcosa."),
                q("interferenza", "Interrompo ciò che sto facendo per verificare se ci sono novità."),
                q("interferenza", "Rimando il momento di dormire per restare aggiornato."),
                q("interferenza", "Durante un compito, il pensiero di perdermi qualcosa sposta la mia attenzione."),
                q("interferenza", "Cambio programma quando scopro un'attività che sembra più interessante."),
                q("interferenza", "Accetto un invito principalmente per evitare di perdermi un'esperienza."),
                q("interferenza", "Continuo a seguire aggiornamenti anche quando vorrei dedicare tempo ad altro.")));

        saveGlobal(id, "LOW", "Le esperienze di FOMO sembrano poco presenti",
                "Nelle risposte, le esperienze collegate alla FOMO risultano poco frequenti in tutte e quattro le aree. Preoccupazione per l'inclusione, confronto con alternative, bisogno di aggiornamenti e interferenza sulle scelte non formano quindi un andamento diffuso nell'ultimo mese.",
                "Questo andamento non esclude episodi circoscritti, cambiamenti recenti o situazioni di esclusione realmente vissute. Può essere utile osservare se le risposte cambiano in specifici gruppi, periodi o piattaforme e se alcune occasioni assumono un significato particolare. Il risultato non diagnostica una condizione e non dimostra un uso problematico di social o smartphone. Se queste esperienze incidono in modo rilevante su sonno, concentrazione, attività o relazioni, puoi parlarne con uno psicologo, psicoterapeuta o medico qualificato.");
        saveGlobal(id, "MIXED", "Le esperienze di FOMO sembrano presenti in modo variabile",
                "Le risposte descrivono una frequenza variabile tra le quattro aree, senza che una di esse raggiunga il livello editoriale più alto. La preoccupazione può quindi comparire in alcune situazioni o modalità e restare più contenuta in altre.",
                "Le schede d'area aiutano a distinguere se emergono soprattutto significato sociale dell'assenza, confronto con alternative, bisogno di connessione o conseguenze sulle scelte. Nota quali contesti, persone, orari o tipi di aggiornamento precedono l'esperienza e considera anche transizioni, isolamento reale, stress o un periodo insolitamente ricco di occasioni. Il risultato non diagnostica una condizione e non dimostra un uso problematico di social o smartphone; le soglie sono esclusivamente editoriali. Se queste esperienze incidono in modo rilevante su sonno, concentrazione, attività o relazioni, puoi parlarne con uno psicologo, psicoterapeuta o medico qualificato.");
        saveGlobal(id, "FOCUSED", "Le esperienze di FOMO sembrano più presenti in una o due aree",
                "Una o due aree raccolgono esperienze riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo è quindi concentrato su modalità specifiche e non descrive allo stesso modo il tuo rapporto con ogni gruppo, attività o tecnologia.",
                "Consulta le schede sotto per riconoscere se l'area emergente riguarda appartenenza, confronto, connessione oppure attenzione e scelte. Osserva che cosa accade prima e dopo, quanto dura l'eventuale sollievo ottenuto controllando gli aggiornamenti e quali contesti permettono invece di restare nella scelta presente. Il risultato non diagnostica una condizione e non dimostra un uso problematico di social o smartphone. Se queste esperienze incidono in modo rilevante su sonno, concentrazione, attività o relazioni, puoi parlarne con uno psicologo, psicoterapeuta o medico qualificato.");
        saveGlobal(id, "BROAD", "Le esperienze di FOMO sembrano frequentemente presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro aree esplorate. Nell'ultimo mese, inclusione, confronto, bisogno di aggiornamenti e interferenza possono quindi essersi presentati con una distribuzione ampia.",
                "Questo profilo invita a considerare ampiezza, persistenza e interferenza concreta senza leggere le barre come una misura di gravità. I social possono rendere più visibili le alternative, ma non sono una causa necessaria o sufficiente; anche eventi, relazioni, transizioni, solitudine o stress possono contribuire al contesto. Il risultato non diagnostica una condizione e non dimostra un uso problematico di social o smartphone. Se queste esperienze incidono in modo rilevante su sonno, concentrazione, attività o relazioni, puoi parlarne con uno psicologo, psicoterapeuta o medico qualificato.");

        saveAreaInsights(id, "inclusione",
                "Esperienze non condivise, inviti mancati o conversazioni del gruppo vengono raramente accompagnati da dubbi sulla tua appartenenza. Questo non esclude situazioni circoscritte o un'esclusione realmente presente.",
                "In alcune situazioni l'assenza da un'esperienza può attivare dubbi sul tuo posto o sulla vicinanza con gli altri. Osserva quali fatti sono presenti, con quali gruppi accade e come cambia dopo un contatto diretto.",
                "Le risposte descrivono frequenti dubbi su inclusione, vicinanza o importanza quando un'esperienza avviene senza di te. È utile distinguere il timore dalla presenza di segnali concreti e considerare la qualità reale delle relazioni coinvolte.");
        saveAreaInsights(id, "confronto",
                "Le esperienze altrui o le alternative disponibili raramente rendono meno soddisfacente ciò che stai vivendo. Singoli momenti di confronto possono comunque comparire senza definire un andamento stabile.",
                "In alcuni momenti ciò che fanno gli altri o un'alternativa non scelta può apparire migliore dell'esperienza presente. Nota quali informazioni alimentano il confronto e se la valutazione cambia quando conosci meglio il contesto.",
                "Le risposte indicano un frequente confronto sfavorevole tra ciò che vivi e le esperienze o alternative percepite. Osserva quanto le rappresentazioni visibili siano complete e come il confronto influisce sulla possibilità di riconoscere valore alla scelta presente.");
        saveAreaInsights(id, "connessione",
                "Controlli, notifiche e momenti senza aggiornamenti risultano raramente accompagnati dal bisogno di sapere subito cosa accade. Questo dato non misura il tempo trascorso online né la qualità delle abitudini digitali.",
                "In alcune situazioni restare aggiornato o controllare nuovamente può diventare particolarmente importante. Nota orari, piattaforme, persone e durata dell'inquietudine, distinguendo una scelta intenzionale da un automatismo.",
                "Le risposte descrivono un frequente bisogno di aggiornamenti o connessione e pensieri su ciò che potresti perderti. Non dimostra una dipendenza tecnologica: sono rilevanti contesto, possibilità di interrompere e conseguenze concrete.");
        saveAreaInsights(id, "interferenza",
                "Il timore di perdere esperienze raramente interrompe attività, sonno o programmi già scelti. Questo non esclude singole decisioni ragionevoli di cambiare piano quando emerge un'occasione importante.",
                "In alcuni momenti aggiornamenti e alternative possono spostare attenzione o modificare una scelta. Osserva se il cambiamento è intenzionale e soddisfacente oppure lascia stanchezza, dispersione o distanza dalle tue priorità.",
                "Le risposte indicano frequenti interruzioni o cambiamenti di attenzione, sonno e programmi collegati al timore di perdere qualcosa. È utile osservare persistenza, libertà della scelta e impatto quotidiano senza trasformare la frequenza in un'etichetta clinica.");
    }

    private void seedLinguisticIntelligenceInformationTest() {
        String id = "intelligenza-linguistica";
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Intelligenza linguistica",
                "Autovalutazione informativa",
                "Esplora come percepisci e usi comprensione, espressione orale, scrittura e flessibilità linguistica.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e riprende la cornice di Howard Gardner, ma non misura un'intelligenza indipendente, il QI o una competenza linguistica oggettiva. Rispondi pensando agli ultimi tre mesi e alle occasioni disponibili. Il risultato non certifica un talento o un limite e può risentire di istruzione, lingue usate, modalità comunicativa e opportunità.",
                version, false,
                "Frequenza complessiva delle risorse linguistiche riferite",
                "Frequenza delle risorse riferite",
                true, 19).withSeo(
                "Test intelligenza linguistica di Gardner | Spazio Test",
                "Questionario informativo sulle risorse linguistiche percepite: comprensione, espressione orale, scrittura e uso flessibile. 24 domande, non misura il QI.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e alle occasioni che hai avuto di usare la lingua, con quale frequenza ti è capitata questa esperienza?"));

        saveReference(id, "The Theory of Multiple Intelligences — Project Zero, Harvard",
                "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf", 1);
        saveReference(id, "Beyond g: Putting multiple intelligences theory to the test — Visser, Ashton e Vernon",
                "https://doi.org/10.1016/j.intell.2006.02.004", 2);
        saveReference(id, "CEFR Companion Volume: mediation and modes of communication — Council of Europe",
                "https://www.coe.int/en/web/common-european-framework-reference-languages/mediation", 3);
        saveReference(id, "L'indagine PIAAC sulle competenze degli adulti — INAPP",
                "https://www.inapp.gov.it/piaac/conosci-piaac/lindagine-piaac", 4);

        saveArea(id, "comprensione", "Comprensione e sensibilità al significato", 1);
        saveArea(id, "orale", "Espressione orale e adattamento", 2);
        saveArea(id, "scrittura", "Espressione scritta e revisione", 3);
        saveArea(id, "flessibilita", "Apprendimento e uso flessibile delle parole", 4);

        saveQuestions(id, List.of(
                q("comprensione", "Individuo l'idea centrale di un testo o di un discorso articolato."),
                q("comprensione", "Colgo differenze di significato tra parole simili."),
                q("comprensione", "Mi accorgo quando una frase può essere interpretata in più modi."),
                q("comprensione", "Seguo il collegamento tra le diverse parti di una spiegazione."),
                q("comprensione", "Ricavo dal contesto il possibile significato di una parola che non conosco."),
                q("comprensione", "Riconosco come una parola modifica il significato complessivo di una frase."),
                q("orale", "Organizzo ciò che voglio dire in una sequenza comprensibile."),
                q("orale", "Trovo parole precise per esprimere ciò che penso."),
                q("orale", "Spiego un argomento rendendo espliciti i passaggi principali."),
                q("orale", "Adatto il vocabolario alle conoscenze della persona con cui parlo."),
                q("orale", "Racconto un evento mantenendo riconoscibili i passaggi essenziali."),
                q("orale", "Se una spiegazione non viene compresa, la formulo in un altro modo."),
                q("scrittura", "Prima di scrivere, definisco i punti che voglio comunicare."),
                q("scrittura", "Scrivo frasi che rendono chiaro il messaggio principale."),
                q("scrittura", "Scelgo le parole in base alla precisione richiesta dal testo."),
                q("scrittura", "Collego le frasi in modo che il testo proceda con continuità."),
                q("scrittura", "Rileggo un testo per migliorarne chiarezza e ordine."),
                q("scrittura", "Esprimo per iscritto differenze sottili tra idee o punti di vista."),
                q("flessibilita", "Presto attenzione alle parole nuove che incontro."),
                q("flessibilita", "Uso in seguito una parola nuova che ho compreso."),
                q("flessibilita", "Cambio registro linguistico in base alla situazione."),
                q("flessibilita", "Trovo analogie verbali per rendere più accessibile un'idea."),
                q("flessibilita", "Esploro suoni, significati o combinazioni delle parole per creare espressioni nuove."),
                q("flessibilita", "Uso parole o appunti per sviluppare un'idea ancora poco definita.")));

        saveGlobal(id, "LOW", "Le risorse linguistiche percepite sembrano poco espresse",
                "Nelle risposte, i comportamenti linguistici proposti risultano poco frequenti in tutte e quattro le aree. Comprensione percepita, espressione orale, scrittura e uso flessibile delle parole non formano quindi un andamento ricorrente nelle occasioni considerate.",
                "Questo profilo non indica scarsa intelligenza e non esclude competenze che gli item o i contesti recenti non hanno permesso di esprimere. Considera quante occasioni hai avuto, quali lingue e modalità usi, quali richieste erano presenti e se preferisci forme comunicative diverse. Il risultato non misura l'intelligenza generale né una competenza linguistica oggettiva, non certifica un talento o un limite e non diagnostica condizioni. Se un cambiamento o una difficoltà nella comprensione o nell'espressione persiste e interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");
        saveGlobal(id, "MIXED", "Le risorse linguistiche percepite sembrano espresse in modo variabile",
                "Le risposte descrivono una frequenza variabile tra le quattro aree, senza che una di esse raggiunga il livello editoriale più alto. Alcune risorse possono quindi comparire in determinati compiti o modalità e restare meno accessibili in altri.",
                "Le schede d'area aiutano a distinguere comprensione, oralità, scrittura e uso flessibile senza trasformarle in una classifica. Osserva lingua, destinatario, familiarità del tema, tempo disponibile e opportunità reali: la variabilità può dipendere dalle richieste del contesto oltre che dall'autopercezione. Il risultato non misura l'intelligenza generale né una competenza linguistica oggettiva, non certifica un talento o un limite e non diagnostica condizioni; le soglie sono esclusivamente editoriali. Se un cambiamento o una difficoltà nella comprensione o nell'espressione persiste e interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");
        saveGlobal(id, "FOCUSED", "Le risorse linguistiche percepite sembrano più espresse in una o due aree",
                "Una o due aree raccolgono comportamenti riferiti con maggiore frequenza, mentre le altre risultano più contenute. Il profilo è quindi concentrato su modalità specifiche e non descrive una capacità generale valida in ogni situazione.",
                "Consulta le schede sotto per riconoscere dove l'uso linguistico è più ricorrente, senza definire le aree emergenti come talenti misurati. Può essere utile osservare se queste risorse si trasferiscono tra lingue, destinatari e compiti e quali condizioni rendono più accessibili le modalità usate meno spesso. Il risultato non misura l'intelligenza generale né una competenza linguistica oggettiva, non certifica un talento o un limite e non diagnostica condizioni. Se un cambiamento o una difficoltà nella comprensione o nell'espressione persiste e interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");
        saveGlobal(id, "BROAD", "Le risorse linguistiche percepite sembrano frequentemente espresse in più aree",
                "Le risposte indicano comportamenti riferiti con maggiore frequenza in almeno tre delle quattro aree esplorate. Negli ultimi tre mesi, comprensione, espressione, revisione e flessibilità linguistica possono quindi essere state utilizzate in modo ampio nelle occasioni disponibili.",
                "L'ampiezza riguarda la frequenza percepita e non dimostra accuratezza, livello di prestazione o indipendenza di una specifica intelligenza linguistica. Osserva in quali lingue, attività e relazioni queste risorse funzionano, quali richiedono maggiore sforzo e dove esistono ancora barriere o poche occasioni. Il risultato non misura l'intelligenza generale né una competenza linguistica oggettiva, non certifica un talento o un limite e non diagnostica condizioni. Se un cambiamento o una difficoltà nella comprensione o nell'espressione persiste e interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");

        saveAreaInsights(id, "comprensione",
                "Riconoscere idee centrali, sfumature, ambiguità e collegamenti risulta poco frequente nelle occasioni considerate. Questo dato non misura la comprensione effettiva e può dipendere da lingua, familiarità, formato, accessibilità e complessità dei contenuti.",
                "Alcuni aspetti del significato e della struttura vengono riconosciuti con una frequenza intermedia. Nota quali testi, discorsi, lingue e argomenti rendono la comprensione più immediata e quali richiedono tempo o supporti.",
                "Le risposte descrivono un frequente riconoscimento di idee centrali, sfumature, ambiguità e collegamenti. È una percezione d'uso, non una prova di accuratezza, velocità, vocabolario o comprensione linguistica oggettiva.");
        saveAreaInsights(id, "orale",
                "Organizzare, precisare e adattare l'espressione orale risulta poco frequente nelle occasioni considerate. Parlare poco, usare comunicazione segnata o preferire altri canali non implica minori capacità linguistiche.",
                "L'espressione orale organizzata e adattata compare in alcune situazioni più che in altre. Osserva l'effetto di destinatario, familiarità, tempo per prepararti, sicurezza del contesto e lingua utilizzata.",
                "Le risposte descrivono un uso frequente di organizzazione, precisione, adattamento e riformulazione nell'espressione orale. Non è una misura di eloquenza, pronuncia, persuasione o prestazione comunicativa.");
        saveAreaInsights(id, "scrittura",
                "Pianificare, collegare e rivedere testi risulta poco frequente nelle occasioni considerate. Il dato può riflettere poche attività di scrittura, strumenti usati, accessibilità o preferenza per altre modalità e non misura competenza.",
                "Pianificazione, precisione e revisione compaiono con una frequenza intermedia. Nota quali tipi di testo, destinatari, lingue, strumenti e tempi facilitano o rendono meno accessibile questo modo di esprimerti.",
                "Le risposte descrivono un uso frequente di pianificazione, precisione, coesione e revisione nella scrittura. Non è una prova di ortografia, velocità, creatività, literacy o qualità oggettiva dei testi.");
        saveAreaInsights(id, "flessibilita",
                "Prestare attenzione a parole nuove, cambiare registro e sperimentare con il linguaggio risulta poco frequente nelle occasioni considerate. Questo non indica minore creatività o capacità di apprendimento e può dipendere da interessi e contesti disponibili.",
                "Apprendimento e uso flessibile delle parole compaiono in alcune situazioni. Osserva quando curiosità, analogie, appunti o cambi di registro aiutano davvero a comprendere o comunicare e quando risultano meno pertinenti.",
                "Le risposte descrivono un frequente uso di parole nuove, registri, analogie e sperimentazione verbale. È una frequenza percepita e non una prova di memoria, creatività, apprendimento linguistico o mediazione.");
    }

    private void seedIntrapersonalIntelligenceInformationTest() {
        String id = "intelligenza-intrapersonale";
        String version = "1.1";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Intelligenza intrapersonale",
                "Autovalutazione informativa",
                "Esplora come riconosci stati interni, bisogni, valori e schemi personali e come riferisci di usarli nelle scelte.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e riprende la cornice di Howard Gardner, ma non misura un'intelligenza indipendente, il QI o l'accuratezza della conoscenza di sé. Rispondi pensando agli ultimi tre mesi e alle occasioni disponibili. Il risultato non certifica un talento o un limite: riflettere spesso non equivale necessariamente ad avere una conoscenza accurata di sé.",
                version, false,
                "Frequenza complessiva delle risorse intrapersonali riferite",
                "Frequenza delle risorse riferite",
                true, 20).withSeo(
                "Test intelligenza intrapersonale di Gardner | Spazio Test",
                "Questionario informativo sulle risorse intrapersonali percepite: stati interni, chiarezza personale, riflessione e scelte. 24 domande, non misura il QI.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e alle occasioni in cui hai potuto osservarti o fare una scelta, con quale frequenza ti è capitata questa esperienza?"));

        saveReference(id, "The Theory of Multiple Intelligences — Project Zero, Harvard",
                "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf", 1);
        saveReference(id, "Beyond g: Putting multiple intelligences theory to the test — Visser, Ashton e Vernon",
                "https://doi.org/10.1016/j.intell.2006.02.004", 2);
        saveReference(id, "The Self-Reflection and Insight Scale — Italian Version — Di Fabio e Svicher",
                "https://doi.org/10.14605/CS1532206", 3);
        saveReference(id, "Interoceptive accuracy and awareness in an Italian sample — Calì e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/26379571/", 4);

        saveArea(id, "stati-interni", "Riconoscimento degli stati interni", 1);
        saveArea(id, "chiarezza", "Chiarezza su bisogni, valori e motivazioni", 2);
        saveArea(id, "riflessione", "Riflessione su schemi e funzionamento personale", 3);
        saveArea(id, "orientamento", "Uso della conoscenza di sé nelle scelte", 4);

        saveQuestions(id, List.of(
                q("stati-interni", "Mi accorgo quando il mio stato emotivo cambia."),
                q("stati-interni", "Distinguo le emozioni che provo nello stesso momento."),
                q("stati-interni", "Noto i segnali del corpo collegati a come sto."),
                q("stati-interni", "Riconosco quando ho bisogno di una pausa."),
                q("stati-interni", "Mi accorgo dei pensieri che ritornano in determinate situazioni."),
                q("stati-interni", "Noto quando una situazione cambia il mio livello di energia."),
                q("chiarezza", "Metto a fuoco ciò che per me conta in una decisione."),
                q("chiarezza", "Distinguo un mio desiderio da ciò che percepisco atteso dagli altri."),
                q("chiarezza", "Identifico il bisogno che accompagna una mia reazione."),
                q("chiarezza", "Formulo il motivo per cui un obiettivo è importante per me."),
                q("chiarezza", "Riconosco le condizioni in cui mi sento a mio agio."),
                q("chiarezza", "Individuo le attività che per me hanno significato."),
                q("riflessione", "Ripercorro una mia reazione per comprenderne i passaggi."),
                q("riflessione", "Riconosco situazioni in cui tendo a reagire in modo simile."),
                q("riflessione", "Confronto ciò che intendevo fare con ciò che ho fatto."),
                q("riflessione", "Uso il feedback di una persona per riesaminare come mi vedo."),
                q("riflessione", "Individuo le condizioni che favoriscono una mia risorsa personale."),
                q("riflessione", "Individuo le condizioni che rendono per me un compito più difficile."),
                q("orientamento", "Tengo conto delle mie priorità quando scelgo come usare il tempo."),
                q("orientamento", "Adatto un obiettivo alle energie che percepisco disponibili."),
                q("orientamento", "Cambio strategia quando noto che una modalità non mi aiuta."),
                q("orientamento", "Comunico un mio limite quando riconosco che è importante farlo."),
                q("orientamento", "Preparo condizioni che mi aiutano ad affrontare un compito."),
                q("orientamento", "Dopo una scelta, osservo se l'esito è coerente con ciò che per me conta.")));

        saveGlobal(id, "LOW", "Le risorse intrapersonali percepite sembrano poco espresse",
                "Nelle risposte, i comportamenti di auto-osservazione e uso delle informazioni su di sé risultano poco frequenti in tutte e quattro le aree. Riconoscimento degli stati interni, chiarezza personale, riflessione e orientamento delle scelte non formano quindi un andamento ricorrente nelle occasioni considerate.",
                "Questo profilo non indica scarsa intelligenza o scarsa conoscenza di sé e non esclude processi rapidi, non verbalizzati o non coperti dagli item. Considera quante occasioni e quanta sicurezza hai avuto per fermarti, quali richieste erano presenti e se preferisci modi diversi di comprenderti. Il risultato non misura l'intelligenza generale né l'accuratezza della conoscenza di sé, non certifica un talento o un limite e non diagnostica condizioni. Se un cambiamento nella percezione di te, confusione, pensieri ripetitivi o difficoltà nelle decisioni causa disagio o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");
        saveGlobal(id, "MIXED", "Le risorse intrapersonali percepite sembrano espresse in modo variabile",
                "Le risposte descrivono una frequenza variabile tra le quattro aree, senza che una di esse raggiunga il livello editoriale più alto. Alcune forme di auto-osservazione possono quindi comparire in determinate situazioni e restare meno accessibili o meno utilizzate in altre.",
                "Le schede d'area aiutano a distinguere ciò che noti, chiarisci, riesamini e usi senza trasformare le differenze in una classifica personale. Osserva tempo, sicurezza, tipo di scelta, stato del momento e feedback disponibili: riflettere e raggiungere un nuovo insight non sono lo stesso processo. Il risultato non misura l'intelligenza generale né l'accuratezza della conoscenza di sé, non certifica un talento o un limite e non diagnostica condizioni; le soglie sono esclusivamente editoriali. Se un cambiamento nella percezione di te, confusione, pensieri ripetitivi o difficoltà nelle decisioni causa disagio o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");
        saveGlobal(id, "FOCUSED", "Le risorse intrapersonali percepite sembrano più espresse in una o due aree",
                "Una o due aree raccolgono comportamenti riferiti con maggiore frequenza, mentre le altre risultano più contenute. Il profilo è quindi concentrato su modalità specifiche di auto-osservazione o scelta e non descrive una capacità generale valida in ogni situazione.",
                "Consulta le schede sotto per riconoscere dove queste risorse ricorrono, senza definire le aree emergenti come talenti misurati o prove di accuratezza. Può essere utile osservare se ciò che noti si traduce in comprensioni verificabili e scelte coerenti in contesti diversi e quali condizioni rendono accessibili le aree meno usate. Il risultato non misura l'intelligenza generale né l'accuratezza della conoscenza di sé, non certifica un talento o un limite e non diagnostica condizioni. Se un cambiamento nella percezione di te, confusione, pensieri ripetitivi o difficoltà nelle decisioni causa disagio o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");
        saveGlobal(id, "BROAD", "Le risorse intrapersonali percepite sembrano frequentemente espresse in più aree",
                "Le risposte indicano comportamenti riferiti con maggiore frequenza in almeno tre delle quattro aree esplorate. Negli ultimi tre mesi, riconoscimento, chiarificazione, riflessione e uso delle informazioni su di sé possono quindi essere comparsi in modo ampio nelle occasioni disponibili.",
                "L'ampiezza riguarda la frequenza percepita e non dimostra che le interpretazioni su di te siano accurate, che le scelte siano efficaci o che esista un'intelligenza intrapersonale indipendente misurata. Osserva se le tue letture cambiano alla luce di comportamenti, esiti e feedback e se la riflessione rimane flessibile anziché ripetitiva. Il risultato non misura l'intelligenza generale né l'accuratezza della conoscenza di sé, non certifica un talento o un limite e non diagnostica condizioni. Se un cambiamento nella percezione di te, confusione, pensieri ripetitivi o difficoltà nelle decisioni causa disagio o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato.");

        saveAreaInsights(id, "stati-interni",
                "Accorgerti di cambiamenti emotivi, segnali corporei, pensieri ricorrenti, pause ed energia risulta poco frequente nelle occasioni considerate. Il dato può dipendere da contesto, stress, abitudini attentive o possibilità di fermarti e non misura accuratezza emotiva o interocettiva.",
                "Il riconoscimento degli stati interni compare in alcune situazioni più che in altre. Nota quali condizioni facilitano l'attenzione e distingui ciò che percepisci dall'interpretazione che ne dai, senza cercare una lettura perfetta.",
                "Le risposte descrivono un frequente riconoscimento di cambiamenti emotivi, segnali corporei, pensieri, bisogno di pausa ed energia. È una percezione autoriferita e non una prova di accuratezza interocettiva, regolazione emotiva o salute mentale.");
        saveAreaInsights(id, "chiarezza",
                "Mettere a fuoco bisogni, desideri, valori e motivazioni risulta poco frequente nelle occasioni considerate. Questo non indica assenza di valori o autonomia e può riflettere richieste urgenti, attese sociali, cultura o poco spazio per la riflessione.",
                "La chiarezza su ciò che conta compare con frequenza intermedia o dipende dalla situazione. Osserva quali decisioni rendono più facile distinguere bisogni, desideri, attese e significato e quali lasciano maggiore incertezza.",
                "Le risposte descrivono una frequente chiarificazione di bisogni, valori, motivazioni e condizioni personali. Non certifica autenticità, coerenza, indipendenza dalle influenze esterne o qualità delle decisioni.");
        saveAreaInsights(id, "riflessione",
                "Riesaminare reazioni, ricorrenze, intenzioni, feedback e condizioni personali risulta poco frequente. Il dato non implica mancanza di insight: alcune persone elaborano in altri modi o hanno avuto poche occasioni sicure per farlo.",
                "La riflessione sul tuo funzionamento compare in alcune situazioni. Nota quando conduce a una comprensione nuova o a un'ipotesi verificabile e quando invece ripete la stessa spiegazione senza aggiungere informazioni.",
                "Le risposte descrivono un frequente riesame di reazioni, schemi, feedback e condizioni facilitanti o ostacolanti. Riflettere spesso non prova che l'insight sia accurato e non esclude punti ciechi, bias o letture alternative.");
        saveAreaInsights(id, "orientamento",
                "Usare priorità, energie percepite, limiti ed esiti per orientare le scelte risulta poco frequente nelle occasioni considerate. Vincoli reali, urgenze, responsabilità e sicurezza possono ridurre il margine di scelta senza indicare minore capacità.",
                "L'uso delle informazioni su di te nelle scelte compare in alcune situazioni più che in altre. Osserva quali condizioni permettono di adattare obiettivi e strategie e quali richiedono sostegno, tempo o un margine decisionale diverso.",
                "Le risposte descrivono un frequente uso di priorità, limiti, strategie e verifica degli esiti nelle scelte. Non misura efficacia, produttività, autocontrollo o qualità oggettiva delle decisioni.");
    }

    private void seedPsychologicalResilienceInformationTest() {
        String id = "resilienza-psicologica";
        String version = "1.1";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Resilienza psicologica",
                "Autovalutazione informativa",
                "Esplora come hai recuperato, adattato strategie, usato supporti e mantenuto una direzione davanti a difficoltà recenti.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e descrive alcuni comportamenti riferiti davanti a difficoltà reali degli ultimi sei mesi. La resilienza è un processo legato al contesto, non una forza fissa: chiedere aiuto o soffrire non significa esserne privi; senza difficoltà recenti il risultato è poco informativo. Non predice reazioni future né formula diagnosi; in caso di pericolo immediato contatta il 112.",
                version, false,
                "Frequenza complessiva dei comportamenti di adattamento riferiti",
                "Frequenza dei comportamenti riferiti",
                true, 21).withSeo(
                "Test resilienza psicologica online | Spazio Test",
                "Questionario informativo di 24 domande su recupero, flessibilità, supporti e continuità davanti a difficoltà recenti. Non misura un tratto fisso.")
                .withResponseInstruction("Pensando agli ultimi sei mesi e alle difficoltà, pressioni o cambiamenti che hai realmente incontrato, con quale frequenza ti è capitato di comportarti in questo modo?"));

        saveReference(id, "Psychological Resilience: A Review and Critique — Fletcher e Sarkar",
                "https://doi.org/10.1027/1016-9040/a000124", 1);
        saveReference(id, "Resilience definitions, theory, and challenges — Southwick e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/25317257/", 2);
        saveReference(id, "The Resilience Scale for Adults in Italy — Bonfiglio e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/27031088/", 3);
        saveReference(id, "Italian version of the 14-item Resilience Scale — Cuoco e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/34850301/", 4);
        saveReference(id, "A methodological review of resilience measurement scales — Windle e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/21294858/", 5);

        saveArea(id, "recupero", "Recupero e regolazione dopo la difficoltà", 1);
        saveArea(id, "flessibilita", "Adattamento e revisione delle strategie", 2);
        saveArea(id, "risorse", "Accesso alle risorse relazionali e contestuali", 3);
        saveArea(id, "continuita", "Continuità personale e orientamento", 4);

        saveQuestions(id, List.of(
                q("recupero", "Dopo una difficoltà, riconosco ciò di cui ho bisogno nell'immediato."),
                q("recupero", "Quando la tensione aumenta, mi fermo per ridurre l'attivazione."),
                q("recupero", "Dopo un momento destabilizzante, riprendo gradualmente una routine."),
                q("recupero", "Mi concedo tempo per recuperare dopo uno sforzo intenso."),
                q("recupero", "Regolo il ritmo delle attività in base alle energie disponibili."),
                q("recupero", "Dopo una battuta d'arresto, torno a occuparmi di un'attività quotidiana."),
                q("flessibilita", "Distinguo la parte di un problema su cui posso intervenire."),
                q("flessibilita", "Divido una difficoltà complessa in passi affrontabili."),
                q("flessibilita", "Modifico un piano quando le condizioni cambiano."),
                q("flessibilita", "Provo un approccio diverso quando il primo non funziona."),
                q("flessibilita", "Aggiorno le mie aspettative alla luce di nuove informazioni."),
                q("flessibilita", "Ricavo da un tentativo non riuscito un'informazione per il passo successivo."),
                q("risorse", "Individuo una persona a cui posso rivolgermi per una difficoltà."),
                q("risorse", "Chiedo un aiuto specifico quando ne ho bisogno."),
                q("risorse", "Accetto un aiuto pertinente quando mi viene offerto."),
                q("risorse", "Condivido un carico quando supera le energie disponibili."),
                q("risorse", "Cerco informazioni affidabili quando non so come procedere."),
                q("risorse", "Uso una risorsa del contesto quando è pertinente alla difficoltà."),
                q("continuita", "Mantengo una piccola attività per me importante durante un periodo difficile."),
                q("continuita", "Scelgo un passo successivo coerente con le mie priorità."),
                q("continuita", "Rivedo un obiettivo quando la situazione lo richiede."),
                q("continuita", "Riconosco un progresso anche se il problema non è risolto."),
                q("continuita", "Distinguo l'esito di una situazione dal mio valore personale."),
                q("continuita", "Costruisco una nuova routine quando quella precedente non è più praticabile.")));

        saveGlobal(id, "LOW", "Le risorse di resilienza percepite sembrano poco espresse",
                "Nelle risposte, i comportamenti di recupero, adattamento, accesso ai supporti e continuità risultano poco frequenti in tutte e quattro le aree. Questo andamento descrive soltanto le occasioni e le difficoltà considerate negli ultimi sei mesi.",
                "Una frequenza contenuta non dimostra una scarsa resilienza e non esclude risposte efficaci non coperte dalle domande, difficoltà circoscritte o periodi con poche occasioni pertinenti. Osserva anche intensità e durata del carico, sicurezza, salute, condizioni materiali e supporti realmente disponibili: non tutto dipende dall'iniziativa individuale. Il questionario non misura il processo completo di resilienza, non valuta gli esiti e non predice come reagirai in futuro; le soglie sono esclusivamente editoriali. Se una difficoltà causa disagio persistente o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato. In caso di pericolo immediato, contatta il 112 o i servizi di emergenza.");
        saveGlobal(id, "MIXED", "Le risorse di resilienza percepite sembrano espresse in modo variabile",
                "Le risposte descrivono frequenze diverse tra recupero, adattamento, supporti e continuità, senza aree al livello editoriale più alto. Alcuni comportamenti possono quindi essere stati accessibili in certe situazioni e meno presenti o praticabili in altre.",
                "La variabilità può riflettere il tipo di difficoltà, il momento, le energie, il margine di scelta e le risorse del contesto, non una qualità personale incoerente. Confronta le schede d'area con episodi concreti e considera quali condizioni hanno favorito o ostacolato ciascuna risposta, senza ordinare le aree come una classifica. Il questionario non misura il processo completo di resilienza, non valuta gli esiti e non predice come reagirai in futuro; le soglie sono esclusivamente editoriali. Se una difficoltà causa disagio persistente o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato. In caso di pericolo immediato, contatta il 112 o i servizi di emergenza.");
        saveGlobal(id, "FOCUSED", "Le risorse di resilienza percepite sembrano più espresse in una o due aree",
                "Una o due aree raccolgono comportamenti riferiti con maggiore frequenza, mentre le altre risultano più contenute. Le risorse descritte appaiono quindi concentrate su alcune modalità o condizioni, non generalizzate a ogni difficoltà.",
                "Consulta le schede d'area per distinguere dove recupero, flessibilità, supporti o continuità sono comparsi più spesso e dove il contesto ha lasciato meno possibilità. Un'area emergente non certifica efficacia, invulnerabilità o capacità stabile; un'area contenuta non attribuisce responsabilità individuale quando mancano sicurezza, tempo o risorse. Il questionario non misura il processo completo di resilienza, non valuta gli esiti e non predice come reagirai in futuro; le soglie sono esclusivamente editoriali. Se una difficoltà causa disagio persistente o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato. In caso di pericolo immediato, contatta il 112 o i servizi di emergenza.");
        saveGlobal(id, "BROAD", "Le risorse di resilienza percepite sembrano frequentemente espresse in più aree",
                "Le risposte indicano comportamenti riferiti con maggiore frequenza in almeno tre delle quattro aree esplorate. Recupero, adattamento, uso dei supporti e continuità possono quindi essere comparsi in modo ampio davanti alle difficoltà considerate.",
                "L'ampiezza riguarda frequenze autoriferite e non dimostra che le azioni abbiano avuto l'esito desiderato, che la sofferenza sia assente o che queste risorse saranno disponibili in ogni situazione futura. Osserva quali richieste, relazioni e condizioni materiali hanno reso praticabili le risposte e dove rimangono costi, limiti o bisogni di supporto. Il questionario non misura il processo completo di resilienza, non certifica una forza personale e non predice come reagirai in futuro; le soglie sono esclusivamente editoriali. Se una difficoltà causa disagio persistente o interferisce con la vita quotidiana, puoi parlarne con un professionista qualificato. In caso di pericolo immediato, contatta il 112 o i servizi di emergenza.");

        saveAreaInsights(id, "recupero",
                "Riconoscere bisogni immediati, ridurre l'attivazione, regolare il ritmo e riprendere gradualmente attività risulta poco frequente nelle difficoltà considerate. Carico, salute, sicurezza e tempo disponibile possono limitarlo senza indicare una mancanza personale.",
                "I comportamenti di recupero e regolazione compaiono con frequenza intermedia o dipendono dalla situazione. Osserva quali segnali, pause, ritmi e routine sono stati accessibili e quali condizioni hanno reso il recupero più difficile.",
                "Le risposte descrivono un frequente uso di pause, regolazione del ritmo e ripresa graduale dopo la difficoltà. La frequenza non misura rapidità, assenza di sofferenza o qualità del recupero e non impone di tornare alla situazione precedente.");
        saveAreaInsights(id, "flessibilita",
                "Distinguere margini d'azione, suddividere problemi e modificare strategie risulta poco frequente nelle situazioni considerate. Alcuni problemi hanno pochi margini reali o richiedono risorse esterne e non possono essere risolti con la sola flessibilità individuale.",
                "L'adattamento delle strategie compare in alcune situazioni più che in altre. Nota quando informazioni nuove, passi più piccoli o approcci diversi hanno ampliato il margine d'azione e quando il contesto è rimasto vincolante.",
                "Le risposte descrivono un frequente adattamento di piani, aspettative e strategie davanti ai cambiamenti. Questo non prova che ogni modifica sia stata efficace né che insistere o adattarsi sia sempre la scelta più appropriata.");
        saveAreaInsights(id, "risorse",
                "Individuare, chiedere, accettare o usare supporti relazionali e contestuali risulta poco frequente nelle difficoltà considerate. Il dato può riflettere indisponibilità, inaccessibilità o scarsa sicurezza delle risorse, non soltanto una scelta personale.",
                "L'accesso ai supporti compare con frequenza intermedia o varia tra contesti. Osserva quali persone, informazioni e servizi erano realmente disponibili, pertinenti e sicuri e quali ostacoli hanno limitato il loro utilizzo.",
                "Le risposte descrivono un frequente ricorso a persone, informazioni e risorse del contesto quando pertinenti. La frequenza non misura qualità, adeguatezza o continuità del supporto e non significa che ogni bisogno sia stato soddisfatto.");
        saveAreaInsights(id, "continuita",
                "Mantenere attività significative, rivedere obiettivi e riconoscere progressi risulta poco frequente nelle difficoltà considerate. In fasi acute, proteggersi o sospendere attività può essere appropriato e non indica minore resilienza.",
                "La continuità personale e l'orientamento compaiono in alcune situazioni. Nota quali priorità, piccoli passi e nuove routine hanno mantenuto un filo con ciò che conta e quando è stato necessario fermarsi o cambiare direzione.",
                "Le risposte descrivono un frequente mantenimento o adattamento di attività, obiettivi e routine significative. Questo non misura produttività, ottimismo o successo e non richiede di attribuire un significato positivo a ogni difficoltà.");
    }

    private void seedPartnerJealousyInformationTest() {
        String id = "gelosia-partner";
        String version = "1.1";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Sono geloso/a nella relazione?",
                "Autovalutazione informativa",
                "Esplora pensieri, emozioni e comportamenti di gelosia riferiti verso il partner attuale.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e riguarda la relazione attuale negli ultimi tre mesi; non stabilisce se i sospetti siano fondati né accerta un'infedeltà. Provare gelosia non giustifica controllare dispositivi, sorvegliare, limitare, minacciare o aggredire. In caso di pericolo immediato chiama il 112; il 1522 offre aiuto gratuito alle donne vittime di violenza e stalking.",
                version, false,
                "Frequenza complessiva delle esperienze di gelosia riferite",
                "Frequenza delle esperienze riferite",
                true, 22).withSeo(
                "Test gelosia di coppia: sono geloso/a? | Spazio Test",
                "Questionario informativo di 24 domande sulla gelosia verso il partner: pensieri, emozioni, verifiche e interferenza. Non accerta infedeltà.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e alla tua relazione attuale, con quale frequenza ti è capitata questa esperienza?"));

        saveReference(id, "Validation of the Italian brief Multidimensional Jealousy Scale — Diotaiuti e colleghi",
                "https://doi.org/10.3389/fpsyg.2022.1013584", 1);
        saveReference(id, "Multidimensional Jealousy — Pfeiffer e Wong",
                "https://doi.org/10.1177/026540758900600203", 2);
        saveReference(id, "A systematic review of romantic jealousy in relationships — Martínez-León e colleghi",
                "https://doi.org/10.4067/S0718-48082017000200203", 3);
        saveReference(id, "Infidelity, romantic jealousy and intimate partner violence — Pichon e colleghi",
                "https://doi.org/10.3390/ijerph17165682", 4);
        saveReference(id, "Understanding and addressing intimate partner violence — WHO",
                "https://www.who.int/publications/i/item/WHO-RHR-12.36", 5);
        saveReference(id, "1522 — Numero antiviolenza e antistalking",
                "https://www.pariopportunita.gov.it/it/numeri-utili/1522-numero-antiviolenza-e-antistalking/", 6);

        saveArea(id, "minaccia", "Interpretazioni e preoccupazione per possibili rivali", 1);
        saveArea(id, "attivazione", "Reazioni emotive alla minaccia percepita", 2);
        saveArea(id, "verifica", "Ricerca di rassicurazione e verifica", 3);
        saveArea(id, "controllo", "Controllo e interferenza nella quotidianità", 4);

        saveQuestions(id, List.of(
                q("minaccia", "Interpreto un cambiamento nel modo di comunicare del partner come possibile interesse per un'altra persona."),
                q("minaccia", "Mi soffermo su segnali che potrebbero indicare attrazione del partner verso un'altra persona."),
                q("minaccia", "Immagino che il partner possa preferire un'altra persona a me."),
                q("minaccia", "Ripenso alle interazioni del partner cercando indizi di interesse reciproco."),
                q("minaccia", "Mi preoccupo quando non so con chi si trova il partner."),
                q("minaccia", "Collego una minore disponibilità del partner alla possibile presenza di un'altra persona."),
                q("attivazione", "Provo agitazione quando il partner dedica attenzione a una persona che considero un possibile rivale."),
                q("attivazione", "Mi sento insicuro/a quando il partner mostra ammirazione per un'altra persona."),
                q("attivazione", "Provo rabbia quando percepisco intimità tra il partner e un'altra persona."),
                q("attivazione", "Mi sento escluso/a quando il partner condivide un momento significativo con un'altra persona."),
                q("attivazione", "Il pensiero di poter perdere la relazione mi provoca paura."),
                q("attivazione", "Fatico a calmarmi dopo una situazione che ha attivato la mia gelosia."),
                q("verifica", "Chiedo al partner di rassicurarmi sul nostro rapporto."),
                q("verifica", "Chiedo dettagli sulle interazioni del partner con una persona che mi preoccupa."),
                q("verifica", "Controllo segnali online per capire con chi interagisce il partner."),
                q("verifica", "Cerco conferme da altre persone su ciò che fa il partner."),
                q("verifica", "Torno sullo stesso episodio dopo aver ricevuto una spiegazione."),
                q("verifica", "Confronto ciò che il partner mi dice con le informazioni che ho già."),
                q("controllo", "Chiedo al partner di ridurre i contatti con una persona che mi preoccupa."),
                q("controllo", "Insisto perché il partner mi comunichi dove si trova."),
                q("controllo", "Cerco di influenzare con chi il partner trascorre il proprio tempo."),
                q("controllo", "Mi presento in una situazione per verificare ciò che fa il partner."),
                q("controllo", "Rinuncio a una mia attività per controllare cosa sta facendo il partner."),
                q("controllo", "La gelosia riduce la mia concentrazione in un'attività quotidiana.")));

        saveGlobal(id, "LOW", "Le esperienze di gelosia verso il partner sembrano poco presenti",
                "Nelle risposte, interpretazioni di minaccia, reazioni emotive, verifiche e interferenza risultano poco frequenti in tutte e quattro le aree. Questo andamento descrive gli ultimi tre mesi e non esclude episodi circoscritti o situazioni non coperte dalle domande.",
                "Una frequenza contenuta non stabilisce che la relazione sia priva di difficoltà e non esclude preoccupazioni fondate, accordi violati o un singolo episodio con grande impatto. Distingui ciò che hai osservato direttamente dalle interpretazioni e considera quali accordi, spiegazioni e condizioni di sicurezza sono presenti. Il risultato non stabilisce se i sospetti siano fondati, non accerta un'infedeltà e non valuta violenza o sicurezza; le soglie sono esclusivamente editoriali. La gelosia non giustifica accessi senza consenso, sorveglianza, limitazioni, minacce o aggressioni. Se temi di poter agire in modo coercitivo o aggressivo, rivolgiti a un professionista qualificato; in caso di pericolo immediato contatta il 112. Per aiuto o consiglio su violenza e stalking è disponibile il 1522.");
        saveGlobal(id, "MIXED", "Le esperienze di gelosia verso il partner sembrano presenti in modo variabile",
                "Le risposte descrivono frequenze diverse tra interpretazioni, emozioni, verifiche e interferenza, senza aree al livello editoriale più alto. La gelosia può quindi comparire in alcune situazioni o modalità e restare meno presente in altre.",
                "La variabilità può dipendere dal tipo di situazione, dagli accordi della relazione, da eventi precedenti, dal grado di ambiguità e dal modo in cui cerchi informazioni o rassicurazione. Consulta le aree senza trasformarle in una classifica e separa fatti osservati, significati attribuiti, emozioni, azioni ed effetti. Il risultato non stabilisce se i sospetti siano fondati, non accerta un'infedeltà e non valuta violenza o sicurezza; le soglie sono esclusivamente editoriali. La gelosia non giustifica accessi senza consenso, sorveglianza, limitazioni, minacce o aggressioni. Se temi di poter agire in modo coercitivo o aggressivo, rivolgiti a un professionista qualificato; in caso di pericolo immediato contatta il 112. Per aiuto o consiglio su violenza e stalking è disponibile il 1522.");
        saveGlobal(id, "FOCUSED", "Le esperienze di gelosia verso il partner sembrano più presenti in una o due aree",
                "Una o due aree raccolgono esperienze riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo è quindi concentrato su specifiche interpretazioni, emozioni o azioni e non descrive ogni aspetto della relazione.",
                "Osserva quali situazioni attivano le aree emergenti e se la risposta resta un vissuto interno, diventa una richiesta condivisa oppure incide su privacy, autonomia e quotidianità. Una reazione emotiva frequente non rende inevitabile un comportamento; una verifica frequente non dimostra che il sospetto sia corretto. Il risultato non stabilisce se i sospetti siano fondati, non accerta un'infedeltà e non valuta violenza o sicurezza; le soglie sono esclusivamente editoriali. La gelosia non giustifica accessi senza consenso, sorveglianza, limitazioni, minacce o aggressioni. Se temi di poter agire in modo coercitivo o aggressivo, rivolgiti a un professionista qualificato; in caso di pericolo immediato contatta il 112. Per aiuto o consiglio su violenza e stalking è disponibile il 1522.");
        saveGlobal(id, "BROAD", "Le esperienze di gelosia verso il partner sembrano frequentemente presenti in più aree",
                "Le risposte indicano esperienze riferite con maggiore frequenza in almeno tre delle quattro aree esplorate. Negli ultimi tre mesi, la gelosia può quindi aver coinvolto in modo ampio pensieri, emozioni, ricerca di informazioni o interferenza nella quotidianità.",
                "L'ampiezza invita a osservare persistenza, contesti ed effetti sulla tua vita e sulla libertà del partner, ma non definisce la gelosia come patologica e non indica automaticamente pericolosità. Può essere utile ricostruire episodi concreti distinguendo eventi, interpretazioni, emozioni, azioni e conseguenze e parlarne con un professionista qualificato se il ciclo è difficile da interrompere. Il risultato non stabilisce se i sospetti siano fondati, non accerta un'infedeltà e non valuta violenza o sicurezza; le soglie sono esclusivamente editoriali. La gelosia non giustifica accessi senza consenso, sorveglianza, limitazioni, minacce o aggressioni. Se temi di poter agire in modo coercitivo o aggressivo, cerca supporto; in caso di pericolo immediato contatta il 112. Per aiuto o consiglio su violenza e stalking è disponibile il 1522.");

        saveAreaInsights(id, "minaccia",
                "Interpretare segnali, disponibilità o informazioni mancanti come possibile interesse per un'altra persona risulta poco frequente. Questo non stabilisce che ogni preoccupazione sia infondata né che gli accordi della relazione siano rispettati.",
                "Le interpretazioni di possibile minaccia compaiono in alcune situazioni più che in altre. Può essere utile distinguere il fatto osservato, le informazioni mancanti e il significato attribuito, senza pretendere certezza dal punteggio.",
                "Le risposte descrivono una frequente attenzione a possibili segnali di interesse o preferenza verso altre persone. La frequenza non misura accuratezza, infedeltà, intenzioni del partner o carattere delirante dei pensieri.");
        saveAreaInsights(id, "attivazione",
                "Agitazione, insicurezza, rabbia, esclusione o paura della perdita risultano poco frequenti nelle situazioni considerate. Un singolo episodio intenso può comunque avere rilievo e non viene rappresentato dalla sola frequenza.",
                "Le reazioni emotive compaiono con frequenza intermedia o dipendono dalla situazione. Osserva evento, intensità, durata, tempo necessario per calmarti e azione successiva, ricordando che emozione e comportamento non coincidono.",
                "Le risposte descrivono frequenti reazioni emotive davanti a possibili minacce alla relazione. Questo non rende inevitabili verifiche o controllo e non misura regolazione emotiva generale, attaccamento o fondatezza della situazione.");
        saveAreaInsights(id, "verifica",
                "Richiedere rassicurazioni, dettagli o conferme e confrontare informazioni risulta poco frequente. Il dato non misura fiducia, trasparenza o qualità della comunicazione e non esclude domande pertinenti su eventi concreti.",
                "Rassicurazione e verifica compaiono in alcune situazioni. Nota se una domanda produce informazioni nuove e condivise o se il dubbio ritorna invariato, mantenendo consenso e privacy come limiti distinti dalla gelosia.",
                "Le risposte descrivono una frequente ricerca di rassicurazioni, dettagli o conferme. La frequenza non dimostra inganno né autorizza accessi senza consenso, investigazioni o sorveglianza del partner.");
        saveAreaInsights(id, "controllo",
                "Richieste che limitano contatti, monitoraggio e interferenza nella quotidianità risultano poco frequenti. Un comportamento raro può comunque incidere su autonomia o sicurezza e va considerato per ciò che accade, non soltanto per il livello dell'area.",
                "Controllo o interferenza compaiono in alcune situazioni. Osserva consenso, pressione esercitata, libertà del partner, impatto sulle attività e possibilità di interrompere il comportamento senza usare la gelosia come giustificazione.",
                "Le risposte descrivono frequenti richieste, verifiche o rinunce che possono interferire con la quotidianità. Il livello non classifica violenza o pericolosità, ma rende importante valutare separatamente autonomia, consenso, impatto e sicurezza.");
    }

    private void seedLifeSatisfactionInformationTest() {
        String id = "soddisfazione-vita";
        String version = "1.1";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Sono soddisfatto/a della mia vita?",
                "Autovalutazione informativa",
                "Esplora con quale frequenza riconosci valutazioni positive della tua vita, della quotidianità e del percorso recente.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e riguarda le valutazioni positive riconosciute negli ultimi tre mesi. Esplora un giudizio soggettivo: non è la Satisfaction With Life Scale, non consente confronti con norme e una frequenza bassa non indica fallimento personale. Non formula diagnosi; se pensi di farti del male o c'è un pericolo immediato, chiama il 112 o vai al Pronto Soccorso.",
                version, false,
                "Frequenza complessiva delle valutazioni positive riferite",
                "Frequenza delle valutazioni positive riferite",
                true, 23).withSeo(
                "Test soddisfazione di vita: sono soddisfatto/a? | Spazio Test",
                "Questionario informativo di 24 domande sulle valutazioni positive della propria vita, quotidianità, priorità e percorso. Non è la SWLS.")
                .withResponseInstruction("Pensando agli ultimi tre mesi, con quale frequenza hai riconosciuto questa valutazione della tua vita?"));

        saveReference(id, "La Satisfaction With Life Scale: validazione italiana con lavoratori adulti — Di Fabio e Palazzeschi",
                "https://flore.unifi.it/handle/2158/656647", 1);
        saveReference(id, "La soddisfazione dei cittadini per le condizioni di vita — ISTAT",
                "https://www.istat.it/comunicato-stampa/soddisfazione-dei-cittadini-anno-2024/", 2);
        saveReference(id, "The Satisfaction With Life Scale — Diener e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/16367493/", 3);
        saveReference(id, "OECD Guidelines on Measuring Subjective Well-being — 2025 Update",
                "https://www.oecd.org/en/publications/oecd-guidelines-on-measuring-subjective-well-being-2025-update_9203632a-en/full-report/measuring-subjective-well-being_b4b53f27.html", 4);
        saveReference(id, "Measurement invariance of the Satisfaction With Life Scale — Emerson e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/28324322/", 5);
        saveReference(id, "Life satisfaction around the world — Jebb e colleghi",
                "https://doi.org/10.1371/journal.pone.0313107", 6);

        saveArea(id, "complessiva", "Valutazione complessiva della propria vita", 1);
        saveArea(id, "quotidianita", "Soddisfazione per la vita quotidiana", 2);
        saveArea(id, "coerenza", "Coerenza con priorità e criteri personali", 3);
        saveArea(id, "direzione", "Soddisfazione per direzione e percorso recente", 4);

        saveQuestions(id, List.of(
                q("complessiva", "Considero soddisfacente la mia vita nel suo insieme."),
                q("complessiva", "Il bilancio che faccio della mia vita è positivo."),
                q("complessiva", "Valuto favorevolmente la vita che sto conducendo."),
                q("complessiva", "Mi sento soddisfatto/a della mia situazione complessiva."),
                q("complessiva", "Riconosco aspetti della mia vita che mi danno soddisfazione."),
                q("complessiva", "Apprezzo l'assetto attuale della mia vita."),
                q("quotidianita", "Sono soddisfatto/a di come trascorro le mie giornate."),
                q("quotidianita", "Trovo soddisfacenti le attività che occupano il mio tempo."),
                q("quotidianita", "Apprezzo il ritmo delle mie giornate."),
                q("quotidianita", "Sono soddisfatto/a di come utilizzo il mio tempo quotidiano."),
                q("quotidianita", "Riconosco momenti soddisfacenti nelle mie giornate."),
                q("quotidianita", "Valuto positivamente l'organizzazione della mia vita quotidiana."),
                q("coerenza", "Valuto positivamente quanto la mia vita rispecchia ciò che conta per me."),
                q("coerenza", "Sono soddisfatto/a dello spazio che riservo alle mie priorità."),
                q("coerenza", "Il modo in cui vivo è coerente con i miei criteri personali."),
                q("coerenza", "Mi riconosco nelle scelte che stanno orientando la mia vita."),
                q("coerenza", "Sono soddisfatto/a di come distribuisco gli impegni rispetto alle mie priorità."),
                q("coerenza", "Mi sento in accordo con il modo in cui sto conducendo la mia vita."),
                q("direzione", "Sono soddisfatto/a della direzione attuale della mia vita."),
                q("direzione", "Valuto positivamente i passi compiuti negli ultimi mesi."),
                q("direzione", "Il percorso recente della mia vita mi sembra soddisfacente."),
                q("direzione", "Riconosco continuità tra la vita che conduco e quella che desidero."),
                q("direzione", "Sono soddisfatto/a dell'andamento recente della mia vita."),
                q("direzione", "Valuto positivamente il punto in cui mi trovo nel mio percorso.")));

        saveGlobal(id, "LOW", "La soddisfazione percepita per la propria vita sembra poco espressa nelle risposte",
                "Le valutazioni positive formulate risultano poco frequenti nel bilancio complessivo, nella quotidianità, nella coerenza con le priorità e nella direzione recente. Questo andamento descrive gli ultimi tre mesi e non esclude aspetti soddisfacenti circoscritti o esperienze non coperte dagli item.",
                "Una frequenza contenuta non dimostra fallimento, ingratitudine o incapacità di apprezzare la vita e può riflettere condizioni concrete, perdite, salute, vincoli o un periodo particolarmente difficile. Osserva quali aspetti pesano sul giudizio e quali bisogni o sostegni potrebbero meritare attenzione, senza trasformare le aree in una classifica. Il risultato non equivale alla SWLS o a una domanda 0–10, non misura felicità o salute mentale e non valuta la sicurezza; le soglie sono esclusivamente editoriali. Se la sofferenza persiste o interferisce con la vita quotidiana, puoi rivolgerti a un professionista qualificato. In presenza di pensieri di farti del male, intenzione suicidaria o pericolo immediato, contatta subito il 112 o recati al pronto soccorso.");
        saveGlobal(id, "MIXED", "La soddisfazione percepita per la propria vita sembra espressa in modo variabile tra le aree",
                "Le valutazioni positive compaiono con frequenze diverse tra bilancio complessivo, quotidianità, coerenza personale e percorso recente, senza aree al livello editoriale più alto. La soddisfazione può quindi essere riconosciuta in alcune prospettive o momenti e risultare più contenuta in altri.",
                "La variabilità può dipendere da criteri personali, condizioni del periodo, opportunità disponibili e differenze tra il giudizio sulla vita nel suo insieme e quello sulle giornate o sulla direzione recente. Consulta ogni area nel suo ordine teorico e osserva le circostanze che sostengono o limitano le valutazioni positive, senza interpretare differenze piccole come gerarchie. Il risultato non equivale alla SWLS o a una domanda 0–10, non misura felicità o salute mentale e non valuta la sicurezza; le soglie sono esclusivamente editoriali. Se la sofferenza persiste o interferisce con la vita quotidiana, puoi rivolgerti a un professionista qualificato. In presenza di pensieri di farti del male, intenzione suicidaria o pericolo immediato, contatta subito il 112 o recati al pronto soccorso.");
        saveGlobal(id, "FOCUSED", "La soddisfazione percepita per la propria vita sembra più espressa in una o due aree",
                "Una o due prospettive raccolgono valutazioni positive riferite con maggiore frequenza, mentre le altre risultano più contenute o dipendenti dal contesto. Il profilo descrive quindi specifiche fonti di soddisfazione percepita e non autorizza a generalizzarle all'intera vita.",
                "Le aree emergenti possono indicare dove riconosci più spesso un bilancio positivo, una quotidianità soddisfacente, coerenza personale o una direzione apprezzata. Nelle aree più contenute considera vincoli, margine di scelta, eventi recenti e criteri utilizzati, senza attribuire la differenza a un deficit personale. Il risultato non equivale alla SWLS o a una domanda 0–10, non misura felicità o salute mentale e non valuta la sicurezza; le soglie sono esclusivamente editoriali. Se la sofferenza persiste o interferisce con la vita quotidiana, puoi rivolgerti a un professionista qualificato. In presenza di pensieri di farti del male, intenzione suicidaria o pericolo immediato, contatta subito il 112 o recati al pronto soccorso.");
        saveGlobal(id, "BROAD", "La soddisfazione percepita per la propria vita sembra ampiamente espressa in più aree",
                "Le risposte indicano valutazioni positive più frequenti in almeno tre delle quattro prospettive esplorate. Negli ultimi tre mesi, la soddisfazione percepita appare quindi distribuita tra più modi di valutare la vita, senza implicare che ogni ambito o momento sia positivo.",
                "L'ampiezza descrive una presenza frequente di giudizi positivi, ma non dimostra condizioni oggettivamente favorevoli, felicità costante, assenza di sofferenza o stabilità futura. Può essere utile riconoscere quali condizioni, relazioni, scelte e risorse sostengono questo andamento e quali difficoltà circoscritte restano comunque importanti. Il risultato non equivale alla SWLS o a una domanda 0–10, non misura felicità o salute mentale e non valuta la sicurezza; le soglie sono esclusivamente editoriali. Se la sofferenza persiste o interferisce con la vita quotidiana, puoi rivolgerti a un professionista qualificato. In presenza di pensieri di farti del male, intenzione suicidaria o pericolo immediato, contatta subito il 112 o recati al pronto soccorso.");

        saveAreaInsights(id, "complessiva",
                "Le valutazioni positive della vita nel suo insieme risultano poco frequenti. Questo non stabilisce le cause, non definisce il tuo valore e non esclude aspetti circoscritti che consideri soddisfacenti.",
                "Il bilancio complessivo positivo compare in alcuni momenti o con frequenza intermedia. Nota quali criteri usi, quale periodo richiami e quanto eventi o condizioni attuali incidono sul giudizio.",
                "La vita nel suo insieme viene valutata positivamente con frequenza. Il dato non equivale a felicità costante, assenza di problemi o qualità oggettiva delle condizioni di vita.");
        saveAreaInsights(id, "quotidianita",
                "Giornate, attività, ritmo e organizzazione risultano poco spesso soddisfacenti. Vincoli, salute, carichi e opportunità concrete sono essenziali per leggere questa frequenza senza attribuirla a una mancanza personale.",
                "La soddisfazione quotidiana varia tra momenti o aspetti. Osserva quali condizioni del ritmo, delle attività e dell'uso del tempo la sostengono e quali la rendono meno accessibile.",
                "Le valutazioni positive della quotidianità sono frequenti. Questo non misura produttività, equilibrio ideale o qualità oggettiva e non esclude giornate difficili o bisogni specifici.");
        saveAreaInsights(id, "coerenza",
                "La vita viene raramente valutata come coerente con priorità e criteri personali. Il margine di scelta può essere limitato da responsabilità, risorse, sicurezza o altre condizioni del contesto.",
                "La coerenza percepita compare in alcune scelte o situazioni. Priorità concorrenti, cambiamenti e vincoli possono spiegare variazioni senza indicare che esista un'unica scelta corretta.",
                "La vita viene spesso percepita in accordo con priorità e criteri personali. La frequenza non certifica correttezza delle scelte, autonomia completa o un significato valido per tutte le persone.");
        saveAreaInsights(id, "direzione",
                "Direzione, passi e percorso recente risultano poco spesso soddisfacenti. Questo non predice l'andamento futuro e può riflettere una fase di cambiamento, attese o possibilità reali limitate.",
                "La soddisfazione per il percorso varia tra momenti o valutazioni. Nota quali passi riconosci, quali aspettative usi e quali cambiamenti recenti incidono sul giudizio.",
                "Direzione e percorso recente vengono valutati positivamente con frequenza. Il dato non dimostra conseguimento oggettivo, successo futuro o assenza di cambiamenti ancora desiderati.");
    }

    private void seedPtsdInformationTest() {
        String id = "ptsd-adulti";
        String version = "1.1";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Disturbo post-traumatico da stress (PTSD)",
                "Autovalutazione informativa",
                "Esplora la frequenza di esperienze post-traumatiche riferite a un evento o periodo scelto mentalmente come riferimento.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e non diagnostica il PTSD: scegli mentalmente un solo evento o periodo molto minaccioso, senza scriverlo né descriverlo, e riferisci le risposte all'ultimo mese; senza un evento di riferimento il test non è adatto. Se aumenta troppo il disagio puoi interrompere e non devi esporti autonomamente ai ricordi. In caso di pericolo immediato chiama il 112; per violenza o stalking il 1522 offre aiuto gratuito alle donne.",
                version, false,
                "Frequenza complessiva delle esperienze post-traumatiche riferite",
                "Frequenza delle esperienze riferite",
                true, 24).withSeo(
                "Test PTSD per adulti: esperienze post-traumatiche | Spazio Test",
                "Questionario informativo di 24 domande su intrusioni, evitamento, pensieri e umore, attivazione nell'ultimo mese. Non diagnostica il PTSD.")
                .withResponseInstruction("Pensando all'ultimo mese e all'evento o periodo che hai scelto come riferimento, con quale frequenza ti è capitata questa esperienza?"));

        saveReference(id, "Italian validation of the PTSD Checklist for DSM-5 — Di Tella e colleghi", "https://doi.org/10.3390/ijerph19095282", 1);
        saveReference(id, "Trauma exposure and post-traumatic stress disorder in Italy — Carmassi e colleghi", "https://pubmed.ncbi.nlm.nih.gov/25266475/", 2);
        saveReference(id, "PTSD and DSM-5 — National Center for PTSD", "https://www.ptsd.va.gov/professional/treat/essentials/dsm5_ptsd.asp", 3);
        saveReference(id, "Post-traumatic stress disorder — World Health Organization", "https://www.who.int/news-room/fact-sheets/detail/post-traumatic-stress-disorder", 4);
        saveReference(id, "Post-traumatic stress disorder — NICE NG116", "https://www.nice.org.uk/guidance/ng116/chapter/Recommendations", 5);
        saveReference(id, "Guidelines for conditions specifically related to stress — World Health Organization", "https://www.who.int/publications-detail-redirect/9789241505406", 6);

        saveArea(id, "intrusioni", "Ricordi e reazioni intrusive", 1);
        saveArea(id, "evitamento", "Evitamento di contenuti e richiami", 2);
        saveArea(id, "pensieri_umore", "Cambiamenti nei pensieri e nell'umore", 3);
        saveArea(id, "attivazione", "Attivazione e reattività", 4);

        saveQuestions(id, List.of(
                q("intrusioni", "Ricordi dell'evento sono comparsi senza che volessi richiamarli."),
                q("intrusioni", "Ho fatto sogni disturbanti collegati all'evento."),
                q("intrusioni", "Per alcuni momenti ho avuto la sensazione che l'evento stesse accadendo di nuovo."),
                q("intrusioni", "Davanti a un richiamo dell'evento ho provato una forte reazione emotiva."),
                q("intrusioni", "Davanti a un richiamo dell'evento il mio corpo ha reagito con forte attivazione."),
                q("intrusioni", "Un'immagine legata all'evento ha interrotto ciò che stavo facendo."),
                q("evitamento", "Ho cercato di non pensare all'evento."),
                q("evitamento", "Ho evitato di parlare dell'evento."),
                q("evitamento", "Ho cercato di allontanare le emozioni collegate all'evento."),
                q("evitamento", "Ho evitato luoghi che mi ricordavano l'evento."),
                q("evitamento", "Ho evitato attività che richiamavano l'evento."),
                q("evitamento", "Ho evitato persone associate al ricordo dell'evento."),
                q("pensieri_umore", "Mi sono giudicato/a negativamente a causa dell'evento."),
                q("pensieri_umore", "Ho percepito il mondo come meno sicuro dopo l'evento."),
                q("pensieri_umore", "Mi sono attribuito/a la colpa per ciò che è accaduto."),
                q("pensieri_umore", "Ho perso interesse per attività che prima erano importanti per me."),
                q("pensieri_umore", "Mi sono sentito/a distante dalle persone importanti per me."),
                q("pensieri_umore", "Ho faticato a provare emozioni piacevoli."),
                q("attivazione", "Sono rimasto/a in allerta anche senza segnali immediati di pericolo."),
                q("attivazione", "Ho reagito con forte spavento a uno stimolo improvviso."),
                q("attivazione", "Mi sono irritato/a più facilmente."),
                q("attivazione", "Ho avuto reazioni di rabbia difficili da fermare."),
                q("attivazione", "Ho avuto difficoltà a concentrarmi."),
                q("attivazione", "Ho avuto difficoltà a mantenere un sonno regolare.")));

        saveGlobal(id, "LOW", "Le esperienze post-traumatiche esplorate sembrano poco presenti nelle risposte",
                "Ricordi e reazioni intrusive, evitamento, cambiamenti nei pensieri e nell'umore e attivazione risultano poco frequenti nell'ultimo mese. Questo andamento non esclude un singolo episodio intenso, dissociazione, disagio, interferenza o esperienze non coperte dagli item.",
                "Una frequenza contenuta può dipendere dal tempo trascorso, dai richiami incontrati e dal contesto e non dimostra che l'evento non abbia avuto effetti importanti. Osserva eventuali episodi intensi, limitazioni, sonno e sicurezza senza costringerti a ricostruire l'accaduto o a esporti ai ricordi. Il risultato non stabilisce se l'evento soddisfi i criteri di esposizione, non diagnostica il PTSD, non distingue reazioni acute o altre condizioni e non valuta la sicurezza; le soglie sono esclusivamente editoriali. Se il disagio persiste o interferisce con la vita quotidiana, puoi rivolgerti a un professionista qualificato. In caso di pericolo immediato o impossibilità di mantenerti al sicuro contatta il 112; per aiuto o consiglio su violenza e stalking è disponibile il 1522.");
        saveGlobal(id, "MIXED", "Le esperienze post-traumatiche esplorate sembrano presenti in modo variabile tra le aree",
                "Le esperienze compaiono con frequenze diverse tra intrusioni, evitamento, pensieri e umore, attivazione, senza aree al livello editoriale più alto. La variabilità descrive l'ultimo mese e può riflettere situazioni o richiami differenti.",
                "Il profilo può cambiare con il contesto, il tempo dall'evento, la sicurezza attuale, il sonno, la salute e le condizioni concomitanti. Consulta le aree nel loro ordine teorico senza trasformarle in una classifica o attribuire significato clinico a differenze piccole. Il risultato non stabilisce se l'evento soddisfi i criteri di esposizione, non diagnostica il PTSD, non distingue reazioni acute o altre condizioni e non valuta la sicurezza; le soglie sono esclusivamente editoriali. Se il disagio persiste o interferisce con la vita quotidiana, puoi rivolgerti a un professionista qualificato. In caso di pericolo immediato o impossibilità di mantenerti al sicuro contatta il 112; per aiuto o consiglio su violenza e stalking è disponibile il 1522.");
        saveGlobal(id, "FOCUSED", "Le esperienze post-traumatiche esplorate sembrano più presenti in una o due aree",
                "Una o due famiglie di esperienze risultano più frequenti, mentre le altre sono più contenute o dipendenti dal contesto. Il profilo orienta l'osservazione verso aree specifiche e non descrive automaticamente un quadro generale.",
                "Può essere utile osservare quando compaiono le aree emergenti, da quanto persistono e se incidono su sonno, relazioni, studio, lavoro o attività quotidiane. Un'area frequente non equivale a un criterio clinico e le quattro aree originali non sostituiscono una valutazione di esposizione, durata, interferenza e alternative. Il risultato non stabilisce se l'evento soddisfi i criteri di esposizione, non diagnostica il PTSD, non distingue reazioni acute o altre condizioni e non valuta la sicurezza; le soglie sono esclusivamente editoriali. Se il disagio persiste o interferisce, puoi rivolgerti a un professionista qualificato. In caso di pericolo immediato o impossibilità di mantenerti al sicuro contatta il 112; per aiuto o consiglio su violenza e stalking è disponibile il 1522.");
        saveGlobal(id, "BROAD", "Le esperienze post-traumatiche esplorate sembrano frequentemente presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro famiglie esplorate. Nell'ultimo mese, l'andamento appare quindi ampio tra intrusioni, evitamento, pensieri e umore oppure attivazione, senza rappresentare una misura di gravità.",
                "L'ampiezza rende utile considerare persistenza, interferenza, contesto e sicurezza con un professionista qualificato, senza dedurre una diagnosi o una probabilità dal profilo. Esistono interventi con evidenze per il PTSD, ma richiedono valutazione e professionisti formati: il test non prescrive tecniche e non invita a esporsi autonomamente ai ricordi. Il risultato non stabilisce se l'evento soddisfi i criteri di esposizione, non diagnostica il PTSD, non distingue reazioni acute o altre condizioni e non valuta la sicurezza; le soglie sono esclusivamente editoriali. In caso di pericolo immediato o impossibilità di mantenerti al sicuro contatta il 112; per aiuto o consiglio su violenza e stalking è disponibile il 1522.");

        saveAreaInsights(id, "intrusioni",
                "Ricordi involontari, sogni, sensazione di rivivere e reazioni ai richiami risultano poco frequenti. Un episodio isolato intenso può comunque essere importante e non viene rappresentato dalla sola media.",
                "Ricordi o reazioni intrusive compaiono in alcune occasioni. La frequenza può dipendere dai richiami incontrati e non stabilisce accuratezza del ricordo, dissociazione o causa delle esperienze.",
                "Ricordi o reazioni intrusive risultano frequenti. Questo descrive le risposte nell'ultimo mese e non equivale al relativo criterio diagnostico né distingue PTSD, dissociazione o altre spiegazioni.");
        saveAreaInsights(id, "evitamento",
                "Tentativi di evitare pensieri, emozioni, conversazioni, luoghi, attività o persone associate risultano poco frequenti. Il dato non stabilisce se i richiami siano sicuri né esclude un episodio circoscritto.",
                "L'evitamento compare in alcune situazioni. Può proteggere da un pericolo attuale oppure limitare la vita in contesti ormai sicuri, distinzione che il questionario non effettua.",
                "L'evitamento di contenuti interni o richiami esterni risulta frequente. Non affrontare autonomamente situazioni pericolose o ricordi intensi sulla base del livello mostrato.");
        saveAreaInsights(id, "pensieri_umore",
                "Giudizi negativi, colpa, riduzione dell'interesse, distanza e difficoltà con emozioni piacevoli risultano poco frequenti. Questo non esclude depressione, lutto, dissociazione o altre difficoltà.",
                "Cambiamenti nei pensieri o nell'umore compaiono con frequenza intermedia. Contesto, perdite, salute e altre condizioni possono contribuire senza identificare una causa.",
                "Cambiamenti nei pensieri o nell'umore risultano frequenti. L'area non distingue PTSD, depressione, lutto, dolore, dissociazione o condizioni mediche.");
        saveAreaInsights(id, "attivazione",
                "Allerta, spavento, irritabilità, rabbia, difficoltà di concentrazione e sonno risultano poco frequenti. Il dato non esclude problemi circoscritti o cause diverse.",
                "Attivazione o reattività compaiono in alcune situazioni. Sicurezza attuale, sonno, sostanze, farmaci, dolore e salute possono influire sulle risposte.",
                "Attivazione o reattività risultano frequenti. Questo non misura pericolosità e non distingue PTSD, ansia, insonnia, ADHD, dolore o cause mediche.");
    }

    private void seedAttachmentStylesInformationTest() {
        String id = "stili-attaccamento";
        String version = "1.1";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Qual è il mio stile di attaccamento nelle relazioni?",
                "Autovalutazione informativa",
                "Esplora ansia ed evitamento in una relazione e la vicinanza delle risposte a quattro orientamenti di attaccamento adulto.",
                "7 min · 24 domande",
                "Questo questionario per adulti è informativo: scegli la relazione sentimentale attuale o quella significativa più recente e riferisci tutte le risposte alla stessa relazione. Il risultato descrive ansia ed evitamento, non una diagnosi, un'identità fissa o la qualità della relazione. Nessun orientamento giustifica controllo, minacce o violenza: nelle emergenze chiama il 112; il 1522 aiuta gratuitamente le donne vittime di violenza e stalking.",
                version, false,
                "",
                "Rappresentatività nella relazione scelta",
                true, 25).withSeo(
                "Test stile di attaccamento nelle relazioni | Spazio Test",
                "Questionario informativo di 24 domande su ansia, evitamento e quattro orientamenti di attaccamento adulto. Non assegna diagnosi o stili permanenti.")
                .withResponseInstruction("Pensando alla relazione che hai scelto come riferimento, quanto questa affermazione descrive il tuo modo abituale di viverla?")
                .withScoringModel("ATTACHMENT_DIMENSIONAL")
                .withAnswerScale("AGREEMENT"));

        saveReference(id, "Attachment styles among young adults — Bartholomew e Horowitz",
                "https://pubmed.ncbi.nlm.nih.gov/1920064/", 1);
        saveReference(id, "Italian validation of the ECR-R — Busonera e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/25074302/", 2);
        saveReference(id, "Italian validation of the ECR-12 — Brugnera e colleghi",
                "https://pmc.ncbi.nlm.nih.gov/articles/PMC7453162/", 3);
        saveReference(id, "Are adult attachment styles categorical or dimensional? — Fraley e colleghi",
                "https://pubmed.ncbi.nlm.nih.gov/25559192/", 4);
        saveReference(id, "Adult Attachment, Stress, and Romantic Relationships — Simpson e Rholes",
                "https://pmc.ncbi.nlm.nih.gov/articles/PMC4845754/", 5);
        saveReference(id, "Within-person variation in attachment — Girme e colleghi",
                "https://pmc.ncbi.nlm.nih.gov/articles/PMC5820166/", 6);

        saveArea(id, "ansia", "Ansia di attaccamento nella relazione", 1);
        saveArea(id, "evitamento", "Evitamento della vicinanza nella relazione", 2);

        saveQuestions(id, List.of(
                q("ansia", "Temo che l'altra persona possa smettere di volere la relazione."),
                q("ansia", "Un segnale di distanza mi porta a dubitare della stabilità del legame."),
                q("ansia", "Ho bisogno di conferme che la relazione continuerà."),
                q("ansia", "Quando non ricevo risposta, penso che l'altra persona si stia allontanando."),
                q("ansia", "Cerco rassicurazioni sul valore che ho per l'altra persona."),
                q("ansia", "Durante un conflitto sento urgente ristabilire subito la vicinanza."),
                q("ansia", "Se percepisco freddezza, aumento i tentativi di contatto."),
                q("ansia", "Fatico a calmarmi finché non ricevo un segnale di disponibilità."),
                q("ansia", "Confronto l'affetto che ricevo con quello che vorrei ricevere."),
                q("ansia", "Interpreto una minore iniziativa come segnale di minore interesse."),
                q("ansia", "Il mio valore nella relazione dipende molto da come l'altra persona mi considera."),
                q("ansia", "Rimango attento/a ai possibili segnali di rifiuto."),
                q("evitamento", "Mi sento a disagio quando la vicinanza emotiva diventa intensa."),
                q("evitamento", "Tengo per me vissuti personali anche quando vorrei essere compreso/a."),
                q("evitamento", "Limito ciò che mostro di vulnerabile all'altra persona."),
                q("evitamento", "Quando l'altra persona cerca maggiore intimità, sento il bisogno di creare distanza."),
                q("evitamento", "Preferisco affrontare da solo/a una difficoltà anziché affidarmi all'altra persona."),
                q("evitamento", "Mi è difficile chiedere conforto all'altra persona."),
                q("evitamento", "Evito di dipendere dall'altra persona anche quando potrebbe aiutarmi."),
                q("evitamento", "Quando l'altra persona conta su di me emotivamente, mi sento sotto pressione."),
                q("evitamento", "Durante un conflitto mi chiudo invece di condividere ciò che provo."),
                q("evitamento", "Ridimensiono l'importanza della relazione quando mi sento molto coinvolto/a."),
                q("evitamento", "Dopo un momento di forte vicinanza, cerco più distanza."),
                q("evitamento", "Quando sento di aver bisogno dell'altra persona, cerco di non pensarci.")));

        String commonLimit = "Il risultato riguarda soltanto la relazione scelta e non assegna uno stile vero, esclusivo o permanente. Non valuta l'altra persona, non spiega le cause e non misura qualità, compatibilità, violenza o sicurezza della relazione; distanza e rassicurazione possono avere significati diversi secondo il contesto reale. Le distanze dai prototipi e i margini usati sono scelte editoriali originali, non cut-off delle ECR e non producono probabilità, percentili o diagnosi. Nessun orientamento giustifica controllo, pressioni o violazioni del consenso; per una difficoltà persistente puoi rivolgerti a un professionista qualificato. In caso di pericolo immediato contatta il 112; per aiuto o consiglio su violenza e stalking è disponibile il 1522.";

        saveGlobal(id, "SECURE", "Le risposte sono più compatibili con un orientamento sicuro nelle relazioni",
                "Rispetto alla relazione scelta, ansia ed evitamento risultano più vicini al vertice basso-basso del modello editoriale. Le affermazioni su preoccupazione persistente e protezione attraverso la distanza descrivono quindi meno il modo abituale riferito di vivere questo legame.",
                "La vicinanza al prototipo sicuro può corrispondere a minore preoccupazione persistente per la disponibilità e minore bisogno di proteggersi dall'intimità. Non certifica fiducia, reciprocità, abilità relazionali o assenza di difficoltà: il questionario contiene item di insicurezza e un valore contenuto non misura direttamente tutte le risorse di una relazione. " + commonLimit);
        saveGlobal(id, "ANXIOUS_PREOCCUPIED", "Le risposte sono più compatibili con un orientamento ansioso-preoccupato nelle relazioni",
                "Ansia di attaccamento più espressa ed evitamento più contenuto avvicinano le risposte al prototipo ansioso-preoccupato. Nella relazione scelta possono risultare più rappresentative attenzione alla disponibilità, timore della distanza e ricerca di vicinanza o rassicurazione.",
                "Questo andamento può emergere soprattutto quando il legame sembra incerto o la risposta dell'altra persona è difficile da leggere. Non stabilisce che le preoccupazioni siano infondate e non attribuisce automaticamente il loro significato alla storia o alla personalità di chi compila. " + commonLimit);
        saveGlobal(id, "DISMISSING_AVOIDANT", "Le risposte sono più compatibili con un orientamento evitante-distanziante nelle relazioni",
                "Evitamento più espresso e ansia più contenuta avvicinano le risposte al prototipo evitante-distanziante. Nella relazione scelta possono risultare più rappresentativi protezione dell'autonomia, minore condivisione della vulnerabilità e riluttanza ad affidarsi.",
                "La distanza può essere una strategia abituale, una risposta al contesto o una protezione davanti a una relazione poco affidabile o non sicura. Il profilo non implica assenza di emozioni, incapacità di amare, introversione o scelta relazionale sbagliata. " + commonLimit);
        saveGlobal(id, "FEARFUL_AVOIDANT", "Le risposte sono più compatibili con un orientamento timoroso-evitante nelle relazioni",
                "Ansia ed evitamento più espressi avvicinano le risposte al prototipo timoroso-evitante. Nella relazione scelta possono coesistere desiderio di vicinanza, preoccupazione per disponibilità o perdita e protezione attraverso distanza o minore vulnerabilità.",
                "La compresenza delle due dimensioni può tradursi in avvicinamento e allontanamento secondo situazione, fiducia e segnali ricevuti. Non equivale ad attaccamento disorganizzato, trauma, disturbo di personalità o impossibilità di costruire relazioni soddisfacenti. " + commonLimit);
        saveGlobal(id, "INTERMEDIATE_SECURE_ANXIOUS", "Le risposte mostrano caratteristiche intermedie tra orientamento sicuro e ansioso-preoccupato",
                "I prototipi sicuro e ansioso-preoccupato risultano troppo vicini per indicarne uno come nettamente prevalente. L'evitamento appare relativamente contenuto, mentre preoccupazione e bisogno di rassicurazione possono descrivere la relazione in modo variabile.",
                "La vicinanza può essere generalmente accessibile e accompagnarsi in alcune situazioni a maggiore vigilanza sulla disponibilità o continuità del legame. Comportamento dell'altra persona, conflitti e momenti di separazione possono spostare le risposte senza trasformarle in un'identità. " + commonLimit);
        saveGlobal(id, "INTERMEDIATE_SECURE_DISMISSING", "Le risposte mostrano caratteristiche intermedie tra orientamento sicuro ed evitante-distanziante",
                "I prototipi sicuro ed evitante-distanziante risultano troppo vicini per indicarne uno come nettamente prevalente. L'ansia appare relativamente contenuta, mentre apertura alla vicinanza e protezione dell'autonomia possono cambiare tra situazioni.",
                "Alcuni momenti possono essere vissuti con agio nella vicinanza e altri con maggiore bisogno di distanza, riservatezza o autosufficienza. Fiducia, richieste reciproche e sicurezza reale sono necessari per comprendere il significato della variazione. " + commonLimit);
        saveGlobal(id, "INTERMEDIATE_ANXIOUS_FEARFUL", "Le risposte mostrano caratteristiche intermedie tra orientamento ansioso-preoccupato e timoroso-evitante",
                "I prototipi ansioso-preoccupato e timoroso-evitante risultano troppo vicini per indicarne uno come nettamente prevalente. L'ansia appare più espressa, mentre la tendenza a cercare o evitare la vicinanza può dipendere dalle situazioni.",
                "La ricerca di rassicurazione può accompagnarsi a maggiore distanza quando aumentano vulnerabilità, conflitto o incertezza. Il questionario non determina se questo andamento dipenda dalla persona, dall'altra parte, dalla loro interazione o da un pericolo reale. " + commonLimit);
        saveGlobal(id, "INTERMEDIATE_DISMISSING_FEARFUL", "Le risposte mostrano caratteristiche intermedie tra orientamento evitante-distanziante e timoroso-evitante",
                "I prototipi evitante-distanziante e timoroso-evitante risultano troppo vicini per indicarne uno come nettamente prevalente. L'evitamento appare più espresso, mentre preoccupazione per perdita o rifiuto può essere contenuta o cambiare secondo il contesto.",
                "La distanza può essere accompagnata da livelli variabili di attenzione alla disponibilità dell'altra persona, anche se tali preoccupazioni non sono sempre mostrate o ricercate consapevolmente. Questo non stabilisce disinteresse, intenzioni o qualità del legame. " + commonLimit);
        saveGlobal(id, "INTERMEDIATE_MULTIPLE", "Le risposte mostrano caratteristiche intermedie tra più orientamenti di attaccamento",
                "La posizione è vicina al centro del modello e le distanze tra i quattro prototipi risultano troppo simili per indicare una prevalenza leggibile. Ansia ed evitamento descrivono quindi in parte la relazione senza avvicinarla nettamente a un singolo vertice.",
                "Questo può riflettere risposte dipendenti dalle situazioni, una combinazione non ben rappresentata dai quattro prototipi o i limiti del set originale di item. Non è un risultato incoerente e non deve essere forzato in una categoria soltanto per ottenere un'etichetta. " + commonLimit);

        saveAreaInsights(id, "ansia",
                "Preoccupazione per disponibilità, rifiuto o perdita e ricerca urgente di rassicurazione risultano poco rappresentative. Questo non certifica sicurezza né esclude timori circoscritti o segnali reali della relazione.",
                "Preoccupazione e ricerca di rassicurazione descrivono in parte il modo di vivere la relazione. Possono variare secondo situazioni, conflitti, separazioni e segnali ricevuti dall'altra persona.",
                "Preoccupazione per il legame, vigilanza ai segnali e bisogno di rassicurazione risultano molto rappresentativi. Il dato non stabilisce se i timori siano fondati né identifica la loro causa.");
        saveAreaInsights(id, "evitamento",
                "Distanza, disagio con intimità e difficoltà ad affidarsi risultano poco rappresentativi. Questo non certifica apertura, reciprocità, fiducia o qualità della relazione.",
                "Protezione dell'autonomia e disponibilità alla vicinanza descrivono la relazione in modo variabile. Fiducia, conflitto, richieste reciproche e sicurezza reale possono incidere.",
                "Distanza, limitazione della vulnerabilità e riluttanza ad affidarsi risultano molto rappresentative. Il dato non implica assenza di affetto, introversione o incapacità relazionale.");

        saveStyle(id, "SECURE", "Prototipo con ansia ed evitamento contenuti: descrive maggiore agio con vicinanza e affidamento senza certificare qualità, reciprocità o sicurezza del legame.");
        saveStyle(id, "ANXIOUS_PREOCCUPIED", "Prototipo con ansia più espressa ed evitamento contenuto: descrive attenzione alla disponibilità e ricerca di vicinanza senza stabilire cause o fondatezza dei timori.");
        saveStyle(id, "DISMISSING_AVOIDANT", "Prototipo con ansia contenuta ed evitamento più espresso: descrive protezione attraverso autonomia e distanza senza implicare disinteresse o assenza di emozioni.");
        saveStyle(id, "FEARFUL_AVOIDANT", "Prototipo con ansia ed evitamento più espressi: descrive possibile coesistenza di bisogno di vicinanza e protezione attraverso distanza, senza equivalere a disorganizzazione o diagnosi.");
    }

    private void seedLimerenceInformationTest() {
        String id = "limerenza";
        String version = "1.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Limerenza: quando l'innamoramento diventa ossessivo?",
                "Autovalutazione informativa",
                "Esplora quanto pensieri persistenti, bisogno di reciprocità, idealizzazione e impatto quotidiano siano presenti verso una persona specifica.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo, non diagnostico né clinicamente validato: riferisci tutte le risposte alla stessa persona e agli ultimi tre mesi. Non stabilisce limerenza, OCD, dipendenza, reciprocità o consenso e un innamoramento intenso non è di per sé patologico. Se temi di non rispettare confini o un rifiuto, interrompi i contatti non desiderati e cerca supporto; in caso di pericolo immediato chiama il 112, mentre chi subisce stalking può rivolgersi al 1522.",
                version, false,
                "Presenza complessiva delle dinamiche esplorate",
                "Presenza delle esperienze nell'area",
                true, 26).withSeo(
                "Test sulla limerenza online: 24 domande | Spazio Test",
                "Questionario informativo sulla limerenza: pensieri persistenti, reciprocità, idealizzazione e impatto quotidiano. 24 domande; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e alla stessa persona, con quale frequenza ti è capitato?"));

        saveReference(id, "Development and Validation of the Limerence Questionnaire (LQ-11) — Marshall e colleghi",
                "https://doi.org/10.1177/00332941251394980", 1);
        saveReference(id, "Limerence, Hidden Obsession, Fixation, and Rumination — Bradbury, Short e Bleakley",
                "https://doi.org/10.1007/s11896-024-09674-x", 2);
        saveReference(id, "Exploring the Lived-Experience of Limerence — Willmott e Bentley",
                "https://doi.org/10.46743/2160-3715/2015.1420", 3);
        saveReference(id, "What fuels passion? — Carswell e Impett",
                "https://doi.org/10.1111/spc3.12629", 4);
        saveReference(id, "La rete dei servizi per la salute mentale — Ministero della Salute",
                "https://www.salute.gov.it/new/it/tema/salute-mentale/la-rete-dei-servizi-la-salute-mentale/", 5);

        saveArea(id, "focalizzazione", "Pensieri intrusivi e focalizzazione", 1);
        saveArea(id, "reciprocita", "Reciprocità e oscillazioni emotive", 2);
        saveArea(id, "idealizzazione", "Idealizzazione e interpretazione dei segnali", 3);
        saveArea(id, "impatto", "Azioni, confini e impatto quotidiano", 4);

        saveQuestions(id, List.of(
                q("focalizzazione", "I pensieri su questa persona hanno interrotto ciò che stavo facendo."),
                q("focalizzazione", "Ho ripercorso mentalmente conversazioni avute con questa persona."),
                q("focalizzazione", "Questa persona è comparsa nei miei pensieri senza che lo decidessi."),
                q("focalizzazione", "Ho faticato a riportare l'attenzione su altro dopo aver pensato a questa persona."),
                q("focalizzazione", "Ho dedicato molto tempo a immaginare possibili incontri con questa persona."),
                q("focalizzazione", "Mi sono accorto/a che gran parte del mio spazio mentale era occupato da questa persona."),
                q("reciprocita", "Ho sentito un bisogno urgente di sapere cosa provasse questa persona per me."),
                q("reciprocita", "L'attesa di un suo messaggio o contatto mi ha tenuto in tensione."),
                q("reciprocita", "Il mio umore è cambiato in base ai segnali di interesse che percepivo."),
                q("reciprocita", "Un suo gesto di attenzione ha prodotto in me uno slancio emotivo molto forte."),
                q("reciprocita", "Una sua distanza percepita mi ha fatto sentire respinto/a."),
                q("reciprocita", "Ho cercato rassicurazioni sulla possibilità che i miei sentimenti fossero ricambiati."),
                q("idealizzazione", "Ho attribuito un significato romantico a segnali che potevano avere più interpretazioni."),
                q("idealizzazione", "Ho immaginato qualità di questa persona che conoscevo solo in parte."),
                q("idealizzazione", "Ho costruito nella mente scene di una relazione che non erano ancora accadute."),
                q("idealizzazione", "Ho dato più peso agli indizi favorevoli che a quelli contrari alla relazione desiderata."),
                q("idealizzazione", "Ho confrontato altre persone con l'immagine che avevo di questa persona."),
                q("idealizzazione", "Ho mantenuto speranza in una relazione nonostante informazioni che la rendevano poco plausibile."),
                q("impatto", "Ho controllato profili, chat o aggiornamenti per sapere cosa facesse questa persona."),
                q("impatto", "Ho cercato informazioni su questa persona attraverso conoscenze comuni."),
                q("impatto", "Ho modificato i miei programmi sperando di incontrare o sentire questa persona."),
                q("impatto", "Ho cercato un nuovo contatto anche quando quello precedente non aveva ricevuto risposta."),
                q("impatto", "Ho trascurato un'attività importante perché la mia attenzione era rivolta a questa persona."),
                q("impatto", "I pensieri su questa persona hanno reso più difficile dormire.")));

        String commonSafety = "Il risultato non diagnostica limerenza, OCD o dipendenza, non accerta reciprocità o consenso e usa soglie esclusivamente editoriali. "
                + "Se pensieri o azioni causano sofferenza, interferenza o difficoltà a rispettare confini e rifiuti, confrontati con un professionista e interrompi i contatti non desiderati; in caso di pericolo immediato chiama il 112. "
                + "Se subisci controllo, contatti indesiderati o stalking, puoi rivolgerti al 1522.";

        saveGlobal(id, "LOW",
                "Le dinamiche associate alla limerenza sembrano molto poco presenti nelle tue risposte",
                "Pensieri intrusivi, bisogno di reciprocità, idealizzazione e impatto risultano poco frequenti in tutte e quattro le aree. Negli ultimi tre mesi non emerge quindi una configurazione diffusa delle esperienze esplorate verso la persona scelta.",
                "Questo andamento non esclude un episodio intenso, una difficoltà circoscritta o comportamenti non rappresentati dalla media. Considera eventuali momenti specifici, cambiamenti recenti e l'effetto concreto su attenzione, sonno, responsabilità e relazioni. " + commonSafety);
        saveGlobal(id, "MIXED",
                "Le dinamiche associate alla limerenza sembrano presenti in modo variabile",
                "Le risposte descrivono frequenze diverse tra focalizzazione, reciprocità, idealizzazione e impatto, senza aree al livello editoriale più alto. Le esperienze possono quindi dipendere da momenti, segnali o contesti specifici.",
                "La variabilità può riguardare ciò che accade internamente, le informazioni disponibili o il modo in cui reagisci a vicinanza e distanza percepite. Osserva la sequenza tra evento, interpretazione, impulso e conseguenza senza dedurre le intenzioni dell'altra persona. " + commonSafety);
        saveGlobal(id, "FOCUSED",
                "Le dinamiche associate alla limerenza sembrano più presenti in una o due aree",
                "Una o due aree raccolgono esperienze riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo orienta quindi verso aspetti specifici senza descrivere ogni pensiero, emozione o comportamento allo stesso modo.",
                "Consulta le aree emergenti per distinguere focalizzazione mentale, bisogno di reciprocità, interpretazioni e conseguenze concrete. È utile osservare persistenza, possibilità di spostare l'attenzione e rispetto dei limiti, senza trasformare una differenza tra barre in una spiegazione clinica. " + commonSafety);
        saveGlobal(id, "BROAD",
                "Le dinamiche associate alla limerenza sembrano molto presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro aree esplorate. Focalizzazione, bisogno di reciprocità, idealizzazione oppure impatto possono quindi formare una configurazione ampia negli ultimi tre mesi, senza rappresentare una misura di gravità.",
                "Considera quanto l'andamento persista, quali situazioni lo attivino e se limiti sonno, responsabilità, relazioni o libertà di scelta. Un confronto professionale può aiutare a distinguere innamoramento intenso, ruminazione, ansia, OCD, difficoltà relazionali e altre spiegazioni senza presupporre una causa. " + commonSafety);

        saveAreaInsights(id, "focalizzazione",
                "Pensieri involontari, ripasso mentale e difficoltà a spostare l'attenzione risultano poco frequenti. Un episodio circoscritto può comunque essere significativo e non viene rappresentato dalla sola media.",
                "La persona occupa l'attenzione in alcune situazioni o periodi. Osserva durata, trigger e possibilità di tornare intenzionalmente alle attività senza chiamare questi pensieri ossessioni cliniche.",
                "Pensieri, fantasie o ripassi mentali risultano frequenti e possono assorbire molto spazio attentivo. L'area non diagnostica OCD e non stabilisce cause, controllo volontario o interferenza clinica.");
        saveAreaInsights(id, "reciprocita",
                "Attesa, rassicurazione e variazioni emotive legate alla reciprocità percepita risultano poco frequenti. Questo non accerta ciò che l'altra persona prova o comunica.",
                "In alcuni momenti vicinanza, distanza o attesa influenzano il tono emotivo. È utile distinguere fatti osservabili, interpretazioni e bisogno di certezza senza dedurre intenzioni.",
                "Il bisogno di reciprocità e le reazioni a segnali percepiti risultano frequenti. Il punteggio non dimostra dipendenza, attaccamento ansioso o reciprocità reale e non sostituisce il consenso esplicito.");
        saveAreaInsights(id, "idealizzazione",
                "Scenari immaginati, lettura di segnali ambigui e selezione degli indizi risultano poco frequenti. Non indica che ogni interpretazione sia accurata.",
                "In alcune situazioni l'immagine desiderata della relazione orienta la lettura delle informazioni. Osserva quanto conosci direttamente e quali alternative restano possibili.",
                "Idealizzazione, scenari anticipati o attenzione selettiva ai segnali risultano frequenti. L'area non diagnostica delirio e non giudica la sincerità dei sentimenti, ma invita a separare desiderio e informazioni disponibili.");
        saveAreaInsights(id, "impatto",
                "Controlli, ricerca di contatto e conseguenze su attività o sonno risultano poco frequenti. Anche un singolo contatto non desiderato o il mancato rispetto di un limite resta importante indipendentemente dalla media.",
                "In alcune occasioni attenzione e azioni verso la persona incidono su programmi, contatti o quotidianità. Considera intenzionalità, conseguenze e confini espressi senza usare il livello come autorizzazione.",
                "Controlli, tentativi di contatto o interferenze quotidiane risultano frequenti. Il dato non predice stalking o pericolosità: contatti, sorveglianza o avvicinamenti non desiderati vanno interrotti e discussi subito con un professionista.");
    }

    private void seedParentificationInformationTest() {
        String id = "parentificazione";
        String version = "1.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Parentificazione: eri il genitore dei tuoi genitori?",
                "Autovalutazione informativa",
                "Ripensa alle responsabilità pratiche ed emotive assunte verso genitori o caregiver durante la crescita e allo spazio rimasto per i tuoi bisogni.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e non validato: ripensa al periodo prima dei 18 anni in cui vivevi con genitori o caregiver. Esplora responsabilità pratiche ed emotive, confini e spazio per i tuoi bisogni; aiutare in famiglia non equivale da solo a parentificazione e il ricordo può essere incompleto. Non formula diagnosi, non attribuisce colpe né dimostra cause degli eventuali vissuti attuali; puoi interromperti e cercare supporto se emergono disagio o ricordi difficili.",
                version, false,
                "Presenza complessiva delle esperienze esplorate",
                "Presenza delle esperienze nell'area",
                true, 27).withSeo(
                "Test sulla parentificazione: 24 domande | Spazio Test",
                "Questionario informativo per adulti sulle responsabilità familiari assunte durante la crescita, l'inversione dei ruoli e lo spazio per i propri bisogni.")
                .withResponseInstruction("Ripensando al periodo prima dei 18 anni in cui vivevi con genitori o caregiver, con quale frequenza ti capitava?"));

        saveReference(id, "Parentification Vulnerability, Reactivity, Resilience, and Thriving — Dariotis e colleghi",
                "https://doi.org/10.3390/ijerph20136197", 1);
        saveReference(id, "Parentification Among Young Carers: A Concept Analysis — Hendricks e colleghi",
                "https://doi.org/10.1007/s10560-021-00784-7", 2);
        saveReference(id, "Assessing Family Caregiving — Hooper e Doehler",
                "https://doi.org/10.1111/j.1752-0606.2011.00258.x", 3);
        saveReference(id, "Polish Parentification Inventory — Borchet e colleghi",
                "https://doi.org/10.1007/s10826-022-02338-6", 4);
        saveReference(id, "Parentification and distress in Italian adult siblings — Levante e colleghi",
                "https://doi.org/10.3389/fpsyt.2022.1079608", 5);
        saveReference(id, "Positive and negative aspects of parentification — Khafi e colleghi",
                "https://doi.org/10.1016/j.childyouth.2022.106709", 6);
        saveReference(id, "La rete dei servizi per la salute mentale — Ministero della Salute",
                "https://www.salute.gov.it/new/it/tema/salute-mentale/la-rete-dei-servizi-la-salute-mentale/", 7);

        saveArea(id, "pratica", "Responsabilità pratiche e organizzative", 1);
        saveArea(id, "emotiva", "Accudimento emotivo e mediazione", 2);
        saveArea(id, "ruoli", "Inversione dei ruoli e obbligo percepito", 3);
        saveArea(id, "spazio", "Spazio per i propri bisogni e riconoscimento", 4);

        saveQuestions(id, List.of(
                q("pratica", "Gestivo in autonomia attività necessarie al funzionamento della casa."),
                q("pratica", "Mi occupavo di risolvere problemi pratici di un genitore o caregiver."),
                q("pratica", "Gestivo appuntamenti, documenti o pagamenti per la famiglia."),
                q("pratica", "Organizzavo aspetti della vita quotidiana di un genitore o caregiver."),
                q("pratica", "Gli adulti contavano su di me per far funzionare le routine familiari."),
                q("pratica", "Prendevo decisioni sulla casa che di solito spettavano agli adulti."),
                q("emotiva", "Un genitore o caregiver mi confidava preoccupazioni personali."),
                q("emotiva", "Cercavo di calmare un genitore o caregiver quando era in crisi."),
                q("emotiva", "Mi occupavo di sostenere emotivamente un genitore o caregiver."),
                q("emotiva", "Facevo da mediatore nei conflitti tra adulti della famiglia."),
                q("emotiva", "Un genitore o caregiver cercava da me rassicurazione."),
                q("emotiva", "Ascoltavo problemi di coppia o familiari come se fossi un adulto."),
                q("ruoli", "Mi sentivo responsabile del benessere di un genitore o caregiver."),
                q("ruoli", "Sentivo di dover essere io la persona forte in famiglia."),
                q("ruoli", "Mi veniva chiesto di capire problemi che superavano la mia età."),
                q("ruoli", "Un genitore o caregiver si affidava a me per decidere cosa fare."),
                q("ruoli", "Sentivo di non potermi sottrarre alle responsabilità familiari."),
                q("ruoli", "Il sostegno nella relazione andava soprattutto da me verso il genitore o caregiver."),
                q("spazio", "Mettevo da parte le mie preoccupazioni per non pesare su un genitore o caregiver."),
                q("spazio", "Rinunciavo ad attività adatte alla mia età per occuparmi della famiglia."),
                q("spazio", "Le responsabilità familiari riducevano il tempo per le attività della mia età."),
                q("spazio", "I miei bisogni passavano dopo quelli di un genitore o caregiver."),
                q("spazio", "Sentivo che le responsabilità erano troppo pesanti per la mia età."),
                q("spazio", "Il mio impegno per la famiglia riceveva poco riconoscimento dagli adulti.")));

        String commonSafety = "Il risultato descrive ricordi retrospettivi e non dimostra parentificazione, trauma, abuso, neglect, colpa o intenzioni familiari, né spiega eventuali difficoltà adulte. "
                + "Aiutare in famiglia può essere adeguato all'età e al contesto: contano durata, obbligo, sostegno, riconoscimento e spazio per i bisogni. "
                + "Se emergono forte disagio o responsabilità attuali che limitano autonomia e benessere, puoi parlarne con un professionista o un Centro di Salute Mentale; in caso di pericolo immediato chiama il 112 e, per violenza o controllo attuali, puoi contattare il 1522.";

        saveGlobal(id, "LOW",
                "Le esperienze associate alla parentificazione sembrano molto poco presenti nelle tue risposte",
                "Responsabilità pratiche, accudimento emotivo, inversione dei ruoli e riduzione dello spazio personale risultano poco frequenti in tutte le aree. Nel ricordo della crescita non emerge quindi una configurazione diffusa delle esperienze esplorate.",
                "Questo andamento non esclude un periodo circoscritto, un compito molto impegnativo o un episodio importante non rappresentato dalla media. Età, durata, scelta, sostegno, riconoscimento e contesto culturale possono cambiare il significato dell'aiuto familiare. " + commonSafety);
        saveGlobal(id, "MIXED",
                "Le esperienze associate alla parentificazione sembrano presenti in modo variabile",
                "Le risposte cambiano tra responsabilità pratiche, sostegno emotivo, inversione dei ruoli e spazio per i propri bisogni, senza aree al livello editoriale più alto. Il vissuto può quindi essere dipeso da compiti, età o fasi familiari differenti.",
                "Osserva quali responsabilità erano occasionali e quali ricorrenti, se erano scelte o obbligate e quale sostegno adulto le accompagnava. Una distribuzione variabile non permette di ricostruire l'intera famiglia né di stabilire conseguenze nel presente. " + commonSafety);
        saveGlobal(id, "FOCUSED",
                "Le esperienze associate alla parentificazione sembrano più presenti in una o due aree",
                "Una o due aree raccolgono esperienze ricordate con maggiore frequenza, mentre le altre risultano più contenute. Il profilo orienta verso aspetti specifici senza estenderli automaticamente a tutta la crescita o a ogni relazione familiare.",
                "Consulta le aree emergenti per distinguere compiti pratici, cura emotiva, responsabilità per l'adulto e rinunce personali. Considera quando accadevano, quanto duravano e se esistevano scelta, supporto, riconoscimento e possibilità di tornare a un ruolo adeguato all'età. " + commonSafety);
        saveGlobal(id, "BROAD",
                "Le esperienze associate alla parentificazione sembrano molto presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro aree esplorate. Nel ricordo della crescita, responsabilità concrete ed emotive possono quindi essersi accompagnate a una marcata inversione dei ruoli o a meno spazio per bisogni e attività dell'età.",
                "Considera ampiezza e durata delle responsabilità, il margine di scelta, il sostegno disponibile e ciò che accadeva quando esprimevi un bisogno. Un confronto professionale può aiutare a ricostruire la storia con più contesto, distinguendo aiuto familiare, necessità temporanee, norme culturali e possibili confini intergenerazionali fragili senza partire da un'etichetta. " + commonSafety);

        saveAreaInsights(id, "pratica",
                "Gestione della casa, problemi, documenti, routine e decisioni adulte risultano poco frequenti. Un singolo compito intenso o un periodo circoscritto può comunque essere importante.",
                "Alcune responsabilità pratiche erano presenti durante la crescita. Il loro significato dipende da età, durata, autonomia richiesta, sostegno adulto e compatibilità con le attività dell'età.",
                "Responsabilità pratiche e organizzative normalmente sostenute dagli adulti risultano frequenti. L'area non stabilisce da sola parentificazione né giudica le necessità della famiglia.");
        saveAreaInsights(id, "emotiva",
                "Confidenze adulte, rassicurazione, regolazione emotiva e mediazione risultano poco frequenti. Questo non misura affetto, empatia o qualità complessiva dei rapporti.",
                "In alcune situazioni offrivi sostegno emotivo o mediazione agli adulti. Osserva contenuto, continuità, possibilità di sottrarti e presenza di altri adulti disponibili.",
                "Sostegno emotivo, rassicurazione o mediazione verso gli adulti risultano frequenti. L'area non diagnostica il caregiver e non dimostra che tu fossi responsabile delle sue emozioni.");
        saveAreaInsights(id, "ruoli",
                "Responsabilità per l'adulto, obbligo e direzione invertita del sostegno risultano poco frequenti. Non certifica che ogni confine familiare fosse adeguato.",
                "In alcune fasi potevi sentirti la persona forte o necessaria per un adulto. Considera età, scelta, aspettative e possibilità reale di affidarti a qualcuno.",
                "Obbligo, responsabilità per il benessere dell'adulto e direzione invertita del sostegno risultano frequenti. Il dato descrive il tuo ricordo e non attribuisce colpa o intenzioni.");
        saveAreaInsights(id, "spazio",
                "Rinunce, bisogni messi da parte, carico e scarso riconoscimento risultano poco frequenti. Non esclude episodi importanti o bisogni rimasti senza risposta.",
                "In alcune situazioni le responsabilità riducevano spazio, tempo o riconoscimento. Osserva quali attività o bisogni venivano rimandati e quali sostegni erano disponibili.",
                "Rinunce, priorità date ai bisogni dell'adulto e peso percepito risultano frequenti. L'area non dimostra trauma o neglect e non stabilisce automaticamente conseguenze adulte.");
    }

    private void seedGaslightingInformationTest() {
        String id = "gaslighting";
        String version = "1.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ho subito gaslighting?",
                "Autovalutazione informativa",
                "Osserva in una relazione specifica negazione degli eventi, svalutazione della tua credibilità, ribaltamento della responsabilità e possibili effetti sulla fiducia nel tuo giudizio.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e non validato. Scegli una sola persona e una relazione attuale o passata: esplorerai esperienze riferite, non la verità dei fatti né le intenzioni dell'altra persona. Il risultato non dimostra gaslighting, abuso o violenza e non formula diagnosi; puoi interromperti in qualsiasi momento e cercare supporto se emergono disagio, paura o problemi di sicurezza.",
                version, false,
                "Presenza complessiva delle esperienze esplorate",
                "Presenza delle esperienze nell'area",
                true, 28).withSeo(
                "Test gaslighting: 24 domande informative | Spazio Test",
                "Questionario informativo per adulti su negazione degli eventi, svalutazione, ribaltamento e autodubbio in una relazione specifica; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi sei mesi di contatto significativo con la persona scelta, o all'intero periodo se più breve, con quale frequenza accadeva?"));

        saveReference(id, "Defining Gaslighting in Gender-Based Violence — Adair",
                "https://doi.org/10.1177/15248380251344316", 1);
        saveReference(id, "The Gaslighting Relationship Exposure Inventory — Tager-Shafrir e colleghi",
                "https://doi.org/10.1177/02654075241266942", 2);
        saveReference(id, "A qualitative analysis of gaslighting in romantic relationships — Klein e colleghi",
                "https://doi.org/10.1111/pere.12510", 3);
        saveReference(id, "The Sociology of Gaslighting — Sweet",
                "https://doi.org/10.1177/0003122419874843", 4);
        saveReference(id, "Gaslighting Exposure During Emerging Adulthood — Bellomare e colleghi",
                "https://doi.org/10.21500/20112084.6306", 5);
        saveReference(id, "Definizioni e indicatori sulla violenza psicologica — Istat",
                "https://www.istat.it/statistiche-per-temi/focus/violenza-sulle-donne/il-contesto/definizioni-e-indicatori/", 6);
        saveReference(id, "Understanding Psychological Violence against Women — EIGE",
                "https://eige.europa.eu/publications-resources/publications/understanding-psychological-violence-against-women-need-harmonised-definitions-and-data-eu", 7);
        saveReference(id, "1522 — Numero Anti Violenza e Stalking",
                "https://www.1522.eu/cose-1522/", 8);

        saveArea(id, "realta", "Negazione e alterazione degli eventi", 1);
        saveArea(id, "credibilita", "Svalutazione di percezioni ed emozioni", 2);
        saveArea(id, "ribaltamento", "Ribaltamento della responsabilità e pressione", 3);
        saveArea(id, "autonomia", "Autodubbio e riduzione dell'autonomia", 4);

        saveQuestions(id, List.of(
                q("realta", "La persona negava di aver detto qualcosa che ricordavo."),
                q("realta", "La persona negava di aver compiuto un'azione che ricordavo."),
                q("realta", "La persona sosteneva che una sua versione precedente non fosse mai stata diversa."),
                q("realta", "La persona affermava che avevo immaginato un episodio di cui parlavo."),
                q("realta", "La persona metteva in dubbio la mia comprensione anche quando citavo messaggi o altre tracce."),
                q("realta", "La persona presentava una propria incoerenza come prova che io avevo frainteso."),
                q("credibilita", "La persona descriveva la mia memoria come inaffidabile durante un confronto."),
                q("credibilita", "La persona definiva esagerate le mie percezioni senza discuterne il contenuto."),
                q("credibilita", "La persona usava le mie emozioni per sostenere che il mio giudizio non fosse attendibile."),
                q("credibilita", "La persona richiamava miei errori passati per screditare ciò che osservavo nel presente."),
                q("credibilita", "La persona sosteneva che anche gli altri mi considerassero confuso o poco credibile."),
                q("credibilita", "La persona ridicolizzava il modo in cui ricordavo o descrivevo un episodio."),
                q("ribaltamento", "Quando chiedevo conto di un comportamento, la conversazione si spostava sui miei difetti."),
                q("ribaltamento", "Dopo aver espresso una preoccupazione, finivo per scusarmi io."),
                q("ribaltamento", "La persona attribuiva a me la responsabilità delle proprie azioni."),
                q("ribaltamento", "La persona trattava la mia richiesta di chiarimento come un attacco personale."),
                q("ribaltamento", "La persona insisteva sulla propria versione finché rinunciavo a esporre la mia."),
                q("ribaltamento", "La persona interrompeva il confronto quando non accettavo la sua ricostruzione."),
                q("autonomia", "Dopo i confronti controllavo messaggi o appunti per verificare la mia memoria."),
                q("autonomia", "Cercavo conferme da altre persone per capire se la mia percezione fosse ragionevole."),
                q("autonomia", "Esitavo a raccontare un episodio perché temevo di essere definito confuso."),
                q("autonomia", "Mi affidavo alla persona per decidere se le mie emozioni fossero giustificate."),
                q("autonomia", "Modificavo le mie scelte per evitare nuove discussioni su ciò che era accaduto."),
                q("autonomia", "Mi sentivo meno capace di fidarmi del mio giudizio dopo aver parlato con la persona.")));

        String commonSafety = "Il risultato non accerta i fatti e non dimostra gaslighting, abuso, violenza, diagnosi, intenzioni o colpe; differenze di memoria, conflitto e altri contesti possono produrre esperienze simili. "
                + "Se ti senti confuso, limitato o in difficoltà puoi parlarne con un professionista o una persona fidata, senza affrontare direttamente la situazione se non è sicuro. "
                + "Minacce, paura, violenza, isolamento o controllo meritano attenzione indipendentemente dal punteggio: in caso di pericolo immediato chiama il 112; se sei una donna e vivi violenza o stalking, il 1522 offre orientamento gratuito anche via chat.";

        saveGlobal(id, "LOW",
                "Le esperienze associate al gaslighting sembrano molto poco presenti nelle tue risposte",
                "Negazione degli eventi, svalutazione della credibilità, ribaltamento della responsabilità e autodubbio risultano poco frequenti in tutte le aree. Nella relazione scelta non emerge quindi una configurazione diffusa delle esperienze esplorate nel periodo considerato.",
                "Questo andamento non esclude un singolo episodio importante, una condotta non inclusa o altre forme di controllo o violenza. Disaccordi e differenze di memoria possono avere spiegazioni diverse, ma ciò che provoca paura, limita la libertà o compromette la sicurezza merita attenzione indipendentemente dalla media. " + commonSafety);
        saveGlobal(id, "MIXED",
                "Le esperienze associate al gaslighting sembrano presenti in modo variabile",
                "Le risposte cambiano tra alterazione degli eventi, svalutazione, ribaltamento e possibili effetti sulla fiducia in te, senza aree al livello editoriale più alto. Alcune dinamiche possono quindi comparire in momenti o forme specifiche, mentre altre risultano poco frequenti.",
                "Osserva quali episodi si ripetono, che cosa accade quando chiedi un chiarimento e se puoi mantenere il tuo punto di vista senza pressioni o conseguenze. Può essere utile distinguere fatti osservabili, interpretazioni e impatto, cercando confronto esterno soltanto quando è sicuro. " + commonSafety);
        saveGlobal(id, "FOCUSED",
                "Le esperienze associate al gaslighting sembrano più presenti in una o due aree",
                "Una o due aree raccolgono esperienze riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo orienta verso nuclei specifici senza definire l'intera relazione né stabilire che la persona agisca intenzionalmente.",
                "Consulta le aree emergenti per distinguere negazione dei fatti, delegittimazione, pressione nel confronto ed effetti sul tuo giudizio. Considera ripetizione, durata, asimmetria di potere, possibilità di verificare le informazioni e conseguenze concrete sulla tua autonomia. " + commonSafety);
        saveGlobal(id, "BROAD",
                "Le esperienze associate al gaslighting sembrano molto presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro aree esplorate. Negazione o svalutazione possono quindi accompagnarsi a pressione nel confronto e minore fiducia nel tuo giudizio, formando una configurazione ampia nel periodo considerato.",
                "Considera quanto queste dinamiche siano ripetute, se limitino decisioni, contatti o libertà e se siano presenti paura, minacce o altre forme di controllo. Un professionista o un servizio specializzato può aiutarti a ricostruire episodi, contesto e opzioni senza partire da un'etichetta; non raccogliere prove e non affrontare la persona se questo potrebbe aumentare il pericolo. " + commonSafety);

        saveAreaInsights(id, "realta",
                "Negazioni, cambi di versione e contraddizioni delle tracce risultano poco frequenti. Non stabilisce che ogni ricordo o disaccordo sia stato chiarito correttamente.",
                "In alcune situazioni la ricostruzione degli eventi veniva negata o modificata. Osserva ripetizione, risposta alle informazioni verificabili e possibilità di confrontare versioni senza pressione.",
                "Negazioni, cambi di versione o attribuzioni di fraintendimento risultano frequenti. L'area descrive la tua esperienza e non decide quale versione dei fatti sia oggettivamente corretta.");
        saveAreaInsights(id, "credibilita",
                "Svalutazione di memoria, percezioni ed emozioni risulta poco frequente. Un episodio umiliante o importante può comunque meritare attenzione.",
                "In alcuni confronti la tua credibilità veniva messa in dubbio invece di discutere il contenuto. Considera tono, ricorrenza, potere e conseguenze sulla possibilità di esprimerti.",
                "La delegittimazione di memoria, percezioni o emozioni risulta frequente. L'area non dimostra intenzione manipolativa né una diagnosi della persona indicata.");
        saveAreaInsights(id, "ribaltamento",
                "Spostamento della responsabilità e pressione per abbandonare il confronto risultano poco frequenti. Non valuta la qualità complessiva dei conflitti nella relazione.",
                "In alcuni confronti la questione si spostava sui tuoi difetti o terminava senza chiarimento. Osserva se puoi porre domande, dissentire e interrompere la conversazione in sicurezza.",
                "Ribaltamento, attribuzione delle azioni altrui e pressione nel confronto risultano frequenti. L'area non stabilisce colpa, intenzione o presenza di controllo coercitivo nel suo insieme.");
        saveAreaInsights(id, "autonomia",
                "Verifiche, ricerca di conferme e minore fiducia nel giudizio risultano poco frequenti. Non esclude disagio, paura o conseguenze non esplorate.",
                "In alcune situazioni cercavi conferme o modificavi il tuo comportamento dopo i confronti. Considera quanto questi effetti persistano e se riducano libertà, contatti o decisioni.",
                "Autodubbio, dipendenza da conferme o adattamento delle scelte risultano frequenti. L'area non dimostra che la relazione ne sia l'unica causa e non diagnostica trauma, ansia o depressione.");
    }

    private void seedLoveBombingInformationTest() {
        String id = "love-bombing";
        String version = "1.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ho subito love bombing?",
                "Autovalutazione informativa",
                "Osserva in una fase specifica della relazione intensità delle attenzioni, accelerazione del legame, rispetto dei confini e alternanza con possibili effetti sulla tua autonomia.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e non validato. Scegli una sola relazione romantica e la sua fase iniziale o un riavvicinamento significativo: affetto ed entusiasmo intensi non dimostrano da soli love bombing. Il risultato non accerta manipolazione, abuso, intenzioni o diagnosi dell'altra persona; puoi interromperti e cercare supporto se emergono disagio, paura o problemi di sicurezza.",
                version, false,
                "Presenza complessiva delle dinamiche esplorate",
                "Presenza delle dinamiche nell'area",
                true, 29).withSeo(
                "Test love bombing: 24 domande informative | Spazio Test",
                "Questionario informativo per adulti su attenzioni intense, accelerazione, confini e alternanza in una fase di una relazione romantica; non diagnostico.")
                .withResponseInstruction("Pensando ai primi sei mesi della fase scelta, o all'intera fase se più breve, con quale frequenza accadeva?"));

        saveReference(id, "Love-bombing: A Narcissistic Approach to Relationship Formation — Strutzenberg e colleghi",
                "https://doi.org/10.54119/discovery.zxgc9960", 1);
        saveReference(id, "Turkish Adaptation Study of Love Bombing Scale — Çalışkan Sarı",
                "https://doi.org/10.17336/igusbd.1651349", 2);
        saveReference(id, "Women's Experiences of Coercive Control — Choudhury e colleghi",
                "https://doi.org/10.1007/s10896-025-00970-6", 3);
        saveReference(id, "A qualitative analysis of gaslighting in romantic relationships — Klein e colleghi",
                "https://doi.org/10.1111/pere.12510", 4);
        saveReference(id, "Coercive control in intimate partner violence — Hamberger e colleghi",
                "https://doi.org/10.1016/j.avb.2017.08.003", 5);
        saveReference(id, "Definizioni e indicatori sulla violenza psicologica — Istat",
                "https://www.istat.it/statistiche-per-temi/focus/violenza-sulle-donne/il-contesto/definizioni-e-indicatori/", 6);
        saveReference(id, "Understanding Psychological Violence against Women — EIGE",
                "https://eige.europa.eu/publications-resources/publications/understanding-psychological-violence-against-women-need-harmonised-definitions-and-data-eu", 7);
        saveReference(id, "1522 — Numero Anti Violenza e Stalking",
                "https://www.1522.eu/cose-1522/", 8);

        saveArea(id, "intensita", "Intensità di attenzioni e idealizzazione", 1);
        saveArea(id, "accelerazione", "Accelerazione del legame e promesse", 2);
        saveArea(id, "confini", "Pressione, esclusività e rispetto dei confini", 3);
        saveArea(id, "alternanza", "Instabilità delle attenzioni e impatto sull'autonomia", 4);

        saveQuestions(id, List.of(
                q("intensita", "La persona mi contattava con una frequenza che mi lasciava poco spazio."),
                q("intensita", "La persona mi faceva complimenti molto intensi rispetto a quanto ci conoscevamo."),
                q("intensita", "La persona mi dedicava attenzioni continue fin dai primi incontri."),
                q("intensita", "La persona mi descriveva come eccezionale senza conoscermi ancora in modo approfondito."),
                q("intensita", "La persona mi faceva regali molto impegnativi nelle prime fasi."),
                q("intensita", "La persona mi chiedeva di trascorrere insieme gran parte del mio tempo libero."),
                q("accelerazione", "La persona faceva dichiarazioni d'amore nelle primissime fasi."),
                q("accelerazione", "La persona proponeva progetti di vita condivisi quando ci conoscevamo da poco."),
                q("accelerazione", "La persona descriveva il nostro legame come unico o predestinato."),
                q("accelerazione", "La persona mi chiedeva di impegnarmi subito nella relazione."),
                q("accelerazione", "La persona chiedeva confidenze molto personali all'inizio della conoscenza."),
                q("accelerazione", "La persona parlava del nostro futuro come se fosse già deciso."),
                q("confini", "La persona continuava a cercarmi quando chiedevo un po' di spazio."),
                q("confini", "La persona manifestava disappunto quando non potevo dedicarle attenzione."),
                q("confini", "La persona mi chiedeva di rispondere rapidamente ai suoi messaggi."),
                q("confini", "La persona mi chiedeva di ridurre il tempo con amici o familiari per stare insieme."),
                q("confini", "La persona diceva che svolgere attività separate indicava mancanza di affetto."),
                q("confini", "La persona insisteva per ottenere manifestazioni di affetto per cui non mi sentivo pronto."),
                q("alternanza", "Dopo periodi di attenzioni molto intense, la persona diventava improvvisamente distante."),
                q("alternanza", "Dopo un conflitto o un mio tentativo di allontanarmi, le attenzioni intense ricominciavano."),
                q("alternanza", "Le attenzioni della persona cambiavano quando non accettavo una sua richiesta."),
                q("alternanza", "Mi sentivo in debito per le attenzioni o i gesti ricevuti."),
                q("alternanza", "Cambiavo programmi o priorità per mantenere la vicinanza intensa."),
                q("alternanza", "Esitavo a porre un limite perché temevo che le attenzioni diminuissero.")));

        String commonSafety = "Il risultato non dimostra love bombing, manipolazione, abuso, intenzioni, narcisismo, diagnosi o colpe: entusiasmo reciproco, differenze comunicative e altri contesti possono produrre esperienze simili. "
                + "Se ti senti sotto pressione, meno libero o in difficoltà puoi parlarne con un professionista o una persona fidata, senza affrontare direttamente la situazione se non è sicuro. "
                + "Minacce, paura, violenza, isolamento o controllo meritano attenzione indipendentemente dal punteggio: in caso di pericolo immediato chiama il 112; se sei una donna e vivi violenza o stalking, il 1522 offre orientamento gratuito anche via chat.";

        saveGlobal(id, "LOW",
                "Le dinamiche associate al love bombing sembrano molto poco presenti nelle tue risposte",
                "Attenzioni molto intense, accelerazione del legame, pressione sui confini e alternanza risultano poco frequenti in tutte le aree. Nella fase scelta non emerge quindi una configurazione diffusa delle dinamiche esplorate.",
                "Questo andamento non esclude un singolo episodio importante, una condotta non inclusa o altre forme di controllo o violenza. Affetto e progetti condivisi possono essere vissuti in modi diversi: contano reciprocità, libertà di rallentare, stabilità e conseguenze concrete. " + commonSafety);
        saveGlobal(id, "MIXED",
                "Le dinamiche associate al love bombing sembrano presenti in modo variabile",
                "Le risposte cambiano tra intensità, accelerazione, rispetto dei confini e alternanza, senza aree al livello editoriale più alto. Alcune dinamiche possono quindi comparire in momenti o forme specifiche, mentre altre risultano poco frequenti.",
                "Osserva in quali episodi ti sentivi libero di scegliere il ritmo, mantenere i tuoi spazi e dire di no senza perdere affetto o subire pressioni. La variabilità può riflettere entusiasmo reciproco, aspettative diverse oppure un andamento instabile che richiede più contesto. " + commonSafety);
        saveGlobal(id, "FOCUSED",
                "Le dinamiche associate al love bombing sembrano più presenti in una o due aree",
                "Una o due aree raccolgono dinamiche riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo orienta verso nuclei specifici senza definire l'intera relazione né stabilire le motivazioni dell'altra persona.",
                "Consulta le aree emergenti per distinguere intensità delle attenzioni, accelerazione, pressione sui confini e instabilità con effetti sulla tua autonomia. Considera sequenza, durata, possibilità di rallentare, risposta al dissenso e conseguenze su relazioni, attività e decisioni. " + commonSafety);
        saveGlobal(id, "BROAD",
                "Le dinamiche associate al love bombing sembrano molto presenti in più aree",
                "Le risposte indicano dinamiche frequenti in almeno tre delle quattro aree esplorate. Attenzioni e promesse intense possono quindi accompagnarsi a pressione sui confini o instabilità, formando una configurazione ampia nella fase considerata.",
                "Considera se l'intensità fosse reciproca e stabile o se rendesse difficile rallentare, dissentire, mantenere contatti e decidere in autonomia. Un professionista o un servizio specializzato può aiutarti a ricostruire sequenza, contesto e opzioni senza partire da un'etichetta; non affrontare la persona se questo potrebbe aumentare il pericolo. " + commonSafety);

        saveAreaInsights(id, "intensita",
                "Contatti, complimenti, attenzioni e gesti molto intensi risultano poco frequenti. Non stabilisce la qualità o la sincerità dell'affetto presente nella relazione.",
                "In alcune situazioni le attenzioni erano particolarmente intense rispetto alla fase della conoscenza. Considera se il ritmo fosse reciproco e lasciasse spazio alle tue attività.",
                "Contatti, idealizzazione, gesti impegnativi o richieste di tempo risultano frequenti. L'intensità da sola non dimostra love bombing: contano consenso, confini e andamento successivo.");
        saveAreaInsights(id, "accelerazione",
                "Dichiarazioni, promesse e progetti molto precoci risultano poco frequenti. Non definisce quanto rapidamente una relazione dovrebbe svilupparsi.",
                "In alcuni momenti il legame procedeva rapidamente attraverso dichiarazioni, confidenze o progetti. Osserva se potevi scegliere il ritmo senza pressione.",
                "Dichiarazioni, promesse, confidenze o aspettative di impegno precoce risultano frequenti. L'area non dimostra la sincerità dei sentimenti né intenzioni manipolative.");
        saveAreaInsights(id, "confini",
                "Pressione sulla disponibilità, sull'esclusività o sulle manifestazioni di affetto risulta poco frequente. Non valuta ogni confine o forma di controllo possibile.",
                "In alcune situazioni il bisogno di spazio o attività separate incontrava aspettative o reazioni negative. Considera se i limiti venivano poi rispettati.",
                "Pressione su contatti, tempo, esclusività o affetto risulta frequente. Il dato non accerta controllo coercitivo o abuso, ma il rispetto dei confini merita attenzione.");
        saveAreaInsights(id, "alternanza",
                "Distanza improvvisa, ritorno delle attenzioni e adattamenti per mantenerle risultano poco frequenti. Non esclude altri effetti o cambiamenti della relazione.",
                "In alcuni momenti le attenzioni cambiavano o influenzavano limiti e priorità. Osserva la sequenza e se potevi restare autonomo senza temere la distanza.",
                "Instabilità delle attenzioni, senso di debito o cambiamenti delle tue scelte risultano frequenti. L'area non dimostra causalità, dipendenza o un ciclo di abuso.");
    }

    private void seedBreadcrumbingInformationTest() {
        String id = "breadcrumbing";
        String version = "1.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ho subito breadcrumbing?",
                "Autovalutazione informativa",
                "Osserva in una relazione o frequentazione specifica intermittenza dei contatti, segnali di interesse, coerenza tra parole e azioni e possibilità di chiarire il rapporto.",
                "6 min · 24 domande",
                "Questo questionario per adulti è informativo e non validato. Scegli una sola relazione o frequentazione romantica degli ultimi 12 mesi e pensa sempre alla stessa persona: segnali intermittenti o ambigui non dimostrano da soli breadcrumbing. Il risultato non accerta manipolazione, abuso, intenzioni o diagnosi; puoi interromperti e cercare supporto se emergono disagio o problemi di sicurezza.",
                version, false,
                "Presenza complessiva delle dinamiche esplorate",
                "Presenza delle dinamiche nell'area",
                true, 30).withSeo(
                "Test breadcrumbing: 24 domande informative | Spazio Test",
                "Questionario informativo per adulti su contatti intermittenti, segnali, seguito concreto e chiarezza in una relazione; non diagnostico.")
                .withResponseInstruction("Pensando agli ultimi 12 mesi della relazione o frequentazione scelta, o all'intero periodo se più breve, con quale frequenza accadeva?"));

        saveReference(id, "Psychological Correlates of Ghosting and Breadcrumbing Experiences — Navarro e colleghi",
                "https://doi.org/10.3390/ijerph17031116", 1);
        saveReference(id, "Ghosting and breadcrumbing: prevalence and association with online dating — Navarro e colleghi",
                "https://doi.org/10.24310/espsiescpsi.v13i2.9960", 2);
        saveReference(id, "Development and Validation of BREAD-ASR — Rodríguez-García e colleghi",
                "https://doi.org/10.3390/ijerph17249548", 3);
        saveReference(id, "Construcción de una escala de experiencias de breadcrumbing — Simil",
                "https://doi.org/10.34192/cienciaysalud.v7i3.688", 4);
        saveReference(id, "Breadcrumbing Experience Scale: Preliminary Validation in Spain — Navarro e Simil",
                "https://doi.org/10.1080/01639625.2025.2516636", 5);
        saveReference(id, "Breadcrumbing in Young Adults: A Qualitative Study — Khattar e colleghi",
                "https://doi.org/10.3390/soc13020041", 6);
        saveReference(id, "Ghosting, orbiting and breadcrumbing: conceptual distinctions — Schokkenbroek e colleghi",
                "https://doi.org/10.1016/j.chb.2025.108637", 7);
        saveReference(id, "1522 — Numero Anti Violenza e Stalking",
                "https://www.1522.eu/cose-1522/", 8);

        saveArea(id, "intermittenza", "Intermittenza dei contatti e riattivazioni", 1);
        saveArea(id, "segnali", "Segnali di interesse e aspettative", 2);
        saveArea(id, "incongruenza", "Coerenza tra parole e azioni", 3);
        saveArea(id, "chiarezza", "Chiarezza, reciprocità e progressione", 4);

        saveQuestions(id, List.of(
                q("intermittenza", "La persona alternava periodi di contatto frequente a periodi di silenzio."),
                q("intermittenza", "Dopo essersi allontanata, la persona riprendeva il contatto."),
                q("intermittenza", "La persona inviava brevi messaggi dopo lunghi intervalli senza contatti."),
                q("intermittenza", "Quando smettevo di cercarla, la persona tornava a farsi sentire."),
                q("intermittenza", "La persona manteneva il contatto tramite reazioni occasionali ai miei contenuti online."),
                q("intermittenza", "La persona avviava una conversazione e poi interrompeva le risposte."),
                q("segnali", "La persona esprimeva interesse romantico nei miei confronti."),
                q("segnali", "La persona diceva che avrebbe voluto incontrarmi presto."),
                q("segnali", "La persona parlava della possibilità di una relazione tra noi."),
                q("segnali", "La persona proponeva attività future da fare insieme."),
                q("segnali", "La persona usava espressioni affettuose dopo periodi di distanza."),
                q("segnali", "La persona accennava a una maggiore vicinanza futura."),
                q("incongruenza", "Le proposte di incontro della persona rimanevano senza seguito."),
                q("incongruenza", "La persona annullava incontri senza proporre una nuova occasione."),
                q("incongruenza", "Le promesse di ricontatto della persona rimanevano senza seguito."),
                q("incongruenza", "Le attività future nominate dalla persona non venivano poi organizzate."),
                q("incongruenza", "Le dichiarazioni di interesse non portavano a una maggiore continuità nei contatti."),
                q("incongruenza", "La disponibilità annunciata cambiava quando arrivava il momento di incontrarsi."),
                q("chiarezza", "Quando chiedevo che significato avesse il rapporto, la persona rispondeva in modo vago."),
                q("chiarezza", "La persona rimandava le conversazioni su cosa desiderava dal rapporto."),
                q("chiarezza", "Alle domande sul proseguimento della frequentazione, la persona non dava una risposta definita."),
                q("chiarezza", "La persona cambiava argomento quando parlavo delle aspettative reciproche."),
                q("chiarezza", "Gli accordi sul tipo di rapporto restavano indefiniti dopo i nostri confronti."),
                q("chiarezza", "Le mie richieste di decidere come proseguire rimanevano senza risposta.")));

        String commonSafety = "Il risultato descrive soltanto le risposte riferite: non dimostra breadcrumbing, manipolazione, abuso, intenzioni, diagnosi o colpe, e comportamenti simili possono dipendere anche da aspettative non esplicitate, disponibilità variabile o difficoltà comunicative. "
                + "Se l'incertezza ti provoca sofferenza o limita le tue scelte, puoi parlarne con un professionista o una persona fidata, senza affrontare direttamente la situazione se non è sicuro. "
                + "Paura, minacce, controllo, stalking o violenza meritano attenzione indipendentemente dal punteggio: in caso di pericolo immediato chiama il 112; se sei una donna e vivi violenza o stalking, il 1522 offre orientamento gratuito anche via chat.";

        saveGlobal(id, "LOW",
                "Le dinamiche associate al breadcrumbing sembrano molto poco presenti nelle tue risposte",
                "Intermittenza, segnali mantenuti senza seguito, incongruenza e difficoltà di chiarimento risultano poco frequenti in tutte le aree. Nella relazione scelta non emerge quindi una configurazione diffusa delle dinamiche esplorate.",
                "Questo andamento non esclude un episodio importante, una condotta non inclusa o una difficoltà circoscritta. Relazioni casuali concordate, disponibilità variabile e aspettative diverse possono produrre alcuni degli stessi segnali: contano sequenza, accordi e conseguenze concrete. " + commonSafety);
        saveGlobal(id, "MIXED",
                "Le dinamiche associate al breadcrumbing sembrano presenti in modo variabile",
                "Le risposte cambiano tra intermittenza, segnali di interesse, seguito concreto e possibilità di chiarire il rapporto, senza aree al livello editoriale più alto. Alcune esperienze possono quindi comparire in momenti specifici mentre altre risultano contenute.",
                "Osserva quali episodi alimentavano aspettative e quali portavano invece ad accordi chiari o azioni concrete. La variabilità può riflettere un rapporto casuale concordato, disponibilità mutevole, aspettative non condivise oppure un andamento ambiguo che richiede più contesto. " + commonSafety);
        saveGlobal(id, "FOCUSED",
                "Le dinamiche associate al breadcrumbing sembrano più presenti in una o due aree",
                "Una o due aree raccolgono esperienze riferite con maggiore frequenza, mentre le altre risultano più contenute. Il profilo orienta verso nuclei specifici senza definire l'intera relazione o le motivazioni dell'altra persona.",
                "Consulta le aree emergenti per distinguere ritorni intermittenti, segnali di interesse, incongruenza tra parole e azioni e possibilità di ottenere chiarezza. Considera durata, sequenza, reciprocità e libertà di scegliere senza restare sospeso nell'attesa. " + commonSafety);
        saveGlobal(id, "BROAD",
                "Le dinamiche associate al breadcrumbing sembrano molto presenti in più aree",
                "Le risposte indicano esperienze frequenti in almeno tre delle quattro aree esplorate. Intermittenza e segnali di interesse possono quindi accompagnarsi a scarso seguito concreto o difficoltà di chiarimento, formando una configurazione ampia nel periodo considerato.",
                "Considera se il rapporto lasciasse spazio a scelte reciproche e accordi chiari oppure mantenesse a lungo aspettative senza progressione condivisa. Un professionista può aiutarti a ricostruire sequenza, contesto, effetti e opzioni senza partire da un'etichetta; non affrontare direttamente la situazione se non è sicuro. " + commonSafety);

        saveAreaInsights(id, "intermittenza",
                "Alternanza tra contatti e silenzi, ritorni e conversazioni interrotte risultano poco frequenti. Non esclude un singolo episodio confuso o importante.",
                "In alcune occasioni il contatto si interrompeva e riprendeva. Osserva la sequenza, le spiegazioni disponibili e se l'andamento era condiviso o lasciava in attesa.",
                "Alternanza, riattivazioni o contatti isolati risultano frequenti. L'area descrive un andamento riferito e non dimostra una strategia, un'intenzione o il breadcrumbing.");
        saveAreaInsights(id, "segnali",
                "Dichiarazioni di interesse, proposte e riferimenti a una vicinanza futura risultano poco frequenti. Non stabilisce che cosa provasse l'altra persona.",
                "In alcuni momenti erano presenti segnali affettuosi o prospettive future. Considerali insieme a reciprocità, chiarezza e seguito concreto.",
                "Segnali di interesse, proposte o aspettative future risultano frequenti. Da soli non indicano breadcrumbing e possono appartenere a una relazione chiara e reciproca.");
        saveAreaInsights(id, "incongruenza",
                "Proposte, promesse o disponibilità senza seguito risultano poco frequenti. Non valuta ogni cambiamento di programma o impedimento possibile.",
                "In alcune situazioni parole e seguito concreto non coincidevano. Osserva ricorrenza, motivi comunicati e possibilità di concordare alternative.",
                "Proposte, promesse o dichiarazioni senza seguito concreto risultano frequenti. L'area non dimostra menzogna, colpa o intenzione manipolativa.");
        saveAreaInsights(id, "chiarezza",
                "Risposte vaghe, rinvii e accordi rimasti indefiniti risultano poco frequenti. Non stabilisce quale forma avrebbe dovuto avere il rapporto.",
                "In alcuni confronti era difficile ottenere una posizione condivisa sul rapporto. Considera se tempi, aspettative e possibilità di scelta venivano esplicitati.",
                "Vaghezza, rinvii o richieste di chiarimento senza risposta risultano frequenti. L'area non impone un modello di relazione e non attribuisce intenzioni.");
    }

    private void seedOrbitingInformationTest() {
        String id = "orbiting";
        String version = "1.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ho subito orbiting?",
                "Autovalutazione informativa",
                "Osserva, dopo l'interruzione di una relazione o frequentazione, cessazione del contatto diretto e presenza digitale ancora visibile.",
                "3 min · 12 domande",
                "Questo breve questionario per adulti è informativo e non validato. Scegli una sola relazione o frequentazione romantica conclusa o interrotta e pensa ai primi sei mesi successivi: se il contatto diretto non è cessato o le attività social non erano visibili, il risultato ha significato limitato. Visualizzazioni o interazioni online non dimostrano da sole orbiting, intenzioni, manipolazione o stalking; puoi interromperti se emerge disagio.",
                version, false,
                "Presenza complessiva delle dinamiche esplorate",
                "Presenza delle dinamiche nell'area",
                true, 31).withSeo(
                "Test orbiting: 12 domande informative | Spazio Test",
                "Questionario informativo per adulti su interruzione del contatto e presenza digitale visibile dopo una relazione; non diagnostico.")
                .withResponseInstruction("Pensando ai primi sei mesi dopo l'interruzione del contatto diretto con la persona scelta, o all'intero periodo se più breve, con quale frequenza accadeva?"));

        saveReference(id, "Ghosting and orbiting: An analysis of victims' experiences — Pancani e colleghi",
                "https://doi.org/10.1177/02654075211000417", 1);
        saveReference(id, "Relationship dissolution strategies: Comparing ghosting, orbiting, and rejection — Pancani e colleghi",
                "https://doi.org/10.5817/CP2022-2-9", 2);
        saveReference(id, "What is (not) ghosting? — Schokkenbroek e colleghi",
                "https://doi.org/10.1016/j.chb.2025.108637", 3);
        saveReference(id, "Unwanted and unfollowed: Defining ghosting and social media unfollowing — Collins e colleghi",
                "https://doi.org/10.1111/pere.12492", 4);
        saveReference(id, "Facebook surveillance of former romantic partners — Marshall",
                "https://pubmed.ncbi.nlm.nih.gov/22946958/", 5);
        saveReference(id, "1522 — Numero Anti Violenza e Stalking",
                "https://www.1522.eu/cose-1522/", 6);

        saveArea(id, "interruzione", "Interruzione e assenza di comunicazione diretta", 1);
        saveArea(id, "presenza-digitale", "Presenza digitale visibile senza contatto diretto", 2);

        saveQuestions(id, List.of(
                q("interruzione", "I miei messaggi diretti rimanevano senza risposta."),
                q("interruzione", "Le conversazioni tra noi si interrompevano senza una spiegazione conclusiva."),
                q("interruzione", "La persona smetteva di avviare conversazioni dirette con me."),
                q("interruzione", "Le mie richieste di chiarire la situazione rimanevano senza risposta."),
                q("interruzione", "I miei tentativi di contatto telefonico non ricevevano risposta."),
                q("interruzione", "La conclusione del rapporto non mi veniva comunicata direttamente."),
                q("presenza-digitale", "La persona visualizzava le mie storie dopo aver interrotto il contatto diretto."),
                q("presenza-digitale", "La persona metteva “Mi piace” ai miei contenuti dopo l'interruzione del contatto."),
                q("presenza-digitale", "La persona reagiva ai miei contenuti senza scrivermi direttamente."),
                q("presenza-digitale", "La persona condivideva contenuti pubblicati da me senza contattarmi direttamente."),
                q("presenza-digitale", "La persona continuava a seguire il mio profilo dopo aver interrotto il contatto."),
                q("presenza-digitale", "La persona interagiva con contenuti online in cui ero presente o taggato.")));

        String commonSafety = "Il risultato descrive soltanto attività riferite e visibili: non dimostra orbiting, ghosting, manipolazione, cyberstalking, abuso, intenzioni o colpe e non rivela visite invisibili al profilo. "
                + "Una conclusione già chiarita, accordi sul contatto online, reti condivise, abitudini d'uso o modalità della piattaforma possono contribuire a esperienze simili. "
                + "Se la situazione provoca sofferenza, paura o limita le tue scelte puoi parlarne con una persona fidata o un professionista, senza affrontare direttamente la persona se non è sicuro. In caso di pericolo immediato chiama il 112; se sei una donna e vivi violenza o stalking, il 1522 offre orientamento gratuito anche via chat.";

        saveGlobal(id, "LOW",
                "Le dinamiche associate all'orbiting sembrano molto poco presenti nelle tue risposte",
                "Interruzione della comunicazione senza chiarimento e presenza digitale visibile risultano poco frequenti in entrambe le aree. Nella fase scelta non emerge quindi una compresenza diffusa delle due componenti esplorate.",
                "Questo andamento non esclude un episodio importante, un canale non osservabile o un contatto indesiderato non incluso. Considera anche se la piattaforma rendesse effettivamente visibili le attività della persona nel periodo scelto. " + commonSafety);
        saveGlobal(id, "MIXED",
                "Le dinamiche associate all'orbiting sembrano presenti in modo variabile",
                "Alcuni aspetti dell'interruzione diretta o della presenza digitale compaiono con frequenza intermedia, ma nessuna area raggiunge il livello editoriale più alto. La configurazione risulta quindi occasionale o variabile nel periodo scelto.",
                "Osserva se la relazione fosse stata conclusa chiaramente e quali attività online fossero davvero visibili, ripetute e successive alla cessazione del contatto. La variabilità non permette di ricostruire da sola una sequenza coerente. " + commonSafety);
        saveGlobal(id, "FOCUSED",
                "Le dinamiche associate all'orbiting sembrano più presenti in una delle due aree",
                "Una delle due componenti risulta frequente, mentre l'altra è più contenuta. Può quindi emergere soprattutto un'interruzione del contatto oppure una presenza digitale, senza la compresenza necessaria per descrivere l'intera configurazione esplorata.",
                "Consulta l'area emergente: la sola cessazione della comunicazione può assomigliare al ghosting o seguire una chiusura già compresa, mentre la sola attività sui social può essere ordinaria o concordata. Conta la sequenza effettiva e non il significato attribuito automaticamente a una visualizzazione o a un “Mi piace”. " + commonSafety);
        saveGlobal(id, "BROAD",
                "Le dinamiche associate all'orbiting sembrano molto presenti in entrambe le aree",
                "Le risposte riferiscono sia una frequente interruzione della comunicazione diretta sia una frequente presenza digitale visibile nello stesso periodo. Le due componenti formano quindi una configurazione ampia e compatibile con le dinamiche esplorate.",
                "Considera quali attività fossero effettivamente visibili, se il rapporto fosse già stato concluso chiaramente e se la presenza online fosse concordata, neutra o indesiderata. La compresenza delle due aree non permette comunque di conoscere intenzioni, visite invisibili al profilo o motivazioni dell'altra persona. " + commonSafety);

        saveAreaInsights(id, "interruzione",
                "Messaggi senza risposta, conversazioni cessate e assenza di un chiarimento risultano poco frequenti. Non stabilisce come sia terminato complessivamente il rapporto.",
                "In alcune occasioni il contatto diretto si interrompeva o restava senza risposta. Considera se la conclusione fosse già stata comunicata o compresa da entrambe le persone.",
                "Interruzione delle risposte e mancanza di un confronto diretto risultano frequenti. Questa componente da sola non dimostra orbiting e può sovrapporsi al ghosting o ad altre conclusioni relazionali.");
        saveAreaInsights(id, "presenza-digitale",
                "Visualizzazioni, reazioni e altre interazioni social visibili risultano poco frequenti. Non esclude attività che la piattaforma non rende osservabile.",
                "In alcune occasioni la persona restava visibile attraverso attività sui social. Considera frequenza, canale, reti condivise e accordi sul contatto online.",
                "Visualizzazioni o interazioni periferiche risultano frequenti senza contatto diretto. L'area non dimostra visite al profilo, sorveglianza, interesse residuo o intenzione di tornare.");
    }

    private void synchronizeEvidenceReferences() {
        syncReferences("tratti-autistici-adulti", List.of(
                ref("Clinical testing and diagnosis for autism spectrum disorder — CDC", "https://www.cdc.gov/autism/hcp/diagnosis/index.html"),
                ref("Autism spectrum disorder in adults: diagnosis and management — NICE CG142", "https://www.nice.org.uk/guidance/cg142/chapter/Recommendations"),
                ref("Diagnosi e trattamento del disturbo dello spettro autistico negli adulti — ISS/SNLG", "https://www.iss.it/documents/20126/8968214/Linea_Guida_ASD_adulti.pdf/b15434a0-3bcd-60c0-46b2-e5b34dc170bd?t=1691389267884")));
        syncReferences("tratti-adhd-adulti", List.of(
                ref("Attention deficit hyperactivity disorder: diagnosis and management — NICE NG87", "https://www.nice.org.uk/guidance/ng87/chapter/recommendations"),
                ref("ADHD in adults — NHS", "https://www.nhs.uk/conditions/adhd-adults/"),
                ref("Validity of the Italian Version of DIVA-5 — Di Lorenzo e colleghi", "https://pubmed.ncbi.nlm.nih.gov/39942433/")));
        syncReferences("tratti-ossessivo-compulsivi", List.of(
                ref("Obsessive compulsive disorder (OCD): symptoms — NHS", "https://www.nhs.uk/mental-health/conditions/obsessive-compulsive-disorder-ocd/symptoms/"),
                ref("Obsessive-Compulsive Disorder — NIMH", "https://www.nimh.nih.gov/health/publications/obsessive-compulsive-disorder-when-unwanted-thoughts-or-repetitive-behaviors-take-over"),
                ref("The Italian version of the Obsessive Compulsive Inventory — Sica e colleghi", "https://pubmed.ncbi.nlm.nih.gov/18701254/")));
        syncReferences("autostima", List.of(
                ref("Rosenberg Self-Esteem Scale — University of Maryland", "https://socy.umd.edu/about-us/rosenberg-self-esteem-scale"),
                ref("The Development of Self-Esteem — Orth e Robins", "https://doi.org/10.1177/0963721414547414"),
                ref("On the factor structure of the Rosenberg Self-Esteem Scale — Alessandri e colleghi", "https://pubmed.ncbi.nlm.nih.gov/25580614/")));
        syncReferences("dipendenza-affettiva", List.of(
                ref("I disturbi da addiction nelle dipendenze non legate a sostanze — Ministero della Salute", "https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_3313_allegato.pdf"),
                ref("Conceptualizing love addiction within the attachment perspective", "https://pmc.ncbi.nlm.nih.gov/articles/PMC12284683/"),
                ref("Problematic Love Behaviors: systematic review and meta-analysis — Cavalli e colleghi", "https://pubmed.ncbi.nlm.nih.gov/42029817/")));
        syncReferences("assertivita", List.of(
                ref("A 30-Item Schedule for Assessing Assertive Behavior — Rathus", "https://doi.org/10.1016/S0005-7894(73)80120-0"),
                ref("Normative studies with the Scale for Interpersonal Behaviour — Arrindell e colleghi", "https://doi.org/10.1016/S0191-8869(98)00252-9"),
                ref("Cross-cultural validity of the Scale for Interpersonal Behavior — Nota e colleghi", "https://pubmed.ncbi.nlm.nih.gov/21721362/")));
        syncReferences("intelligenza-emotiva", List.of(
                ref("The Ability Model of Emotional Intelligence: Principles and Updates", "https://doi.org/10.1177/1754073916639667"),
                ref("Emotional Intelligence: New Ability or Eclectic Traits?", "https://doi.org/10.1037/0003-066X.63.6.503"),
                ref("Construct validity of the Italian MSCEIT v2.0 — Curci e colleghi", "https://pubmed.ncbi.nlm.nih.gov/23536991/")));
        syncReferences("perfezionismo", List.of(
                ref("The dimensions of perfectionism — Frost e colleghi", "https://doi.org/10.1007/BF01172967"),
                ref("Perfectionism in the self and social contexts — Hewitt e Flett", "https://pubmed.ncbi.nlm.nih.gov/2027080/"),
                ref("Short Forms of the Multidimensional Perfectionism Scale in Italian samples — Lombardo e colleghi", "https://pubmed.ncbi.nlm.nih.gov/33835908/")));
        syncReferences("ansia-sociale", List.of(
                ref("Social Anxiety Disorder: More Than Just Shyness — NIMH", "https://www.nimh.nih.gov/health/publications/social-anxiety-disorder-more-than-just-shyness"),
                ref("Social anxiety disorder: assessment and diagnosis for adults — NICE CG159", "https://www.nice.org.uk/guidance/cg159/ifp/chapter/assessment-and-diagnosis-for-adults"),
                ref("Psychometric properties of the Italian Social Phobia Inventory — Gori e colleghi", "https://www.clinicalneuropsychiatry.org/download/assessing-social-anxiety-disorder-psychometric-properties-of-the-italian-social-phobia-inventory-i-spin/")));
        syncReferences("dinamiche-narcisistiche-partner", List.of(
                ref("Narcissistic Personality Disorder — Merck Manual Professional Edition", "https://www.merckmanuals.com/professional/psychiatric-disorders/personality-disorders/narcissistic-personality-disorder-npd"),
                ref("Narcissism and Intimate Partner Violence: systematic review and meta-analysis — Oliver e colleghi", "https://pubmed.ncbi.nlm.nih.gov/37702183/"),
                ref("La violenza contro le donne dentro e fuori la famiglia — ISTAT 2025", "https://www.istat.it/wp-content/uploads/2025/11/La-violenza-contro-le-donne-dentro-e-fuori-la-famiglia_Anno-2025.pdf")));
        syncReferences("ansia-generalizzata", List.of(
                ref("Generalized Anxiety Disorder: What You Need to Know — NIMH", "https://www.nimh.nih.gov/health/publications/generalized-anxiety-disorder-gad"),
                ref("Assessing generalised anxiety disorder — NICE CG113", "https://www.nice.org.uk/guidance/cg113/chapter/Appendix-Assessing-generalised-anxiety-disorder"),
                ref("Psychometric properties of the GAD-7 in an Italian population — Bolgeo e colleghi", "https://pubmed.ncbi.nlm.nih.gov/37149049/")));
        syncReferences("umore-depresso", List.of(
                ref("Depressive disorder — World Health Organization", "https://www.who.int/news-room/fact-sheets/detail/depression"),
                ref("Depression — National Institute of Mental Health", "https://www.nimh.nih.gov/health/publications/depression"),
                ref("Consensus sulle terapie psicologiche per ansia e depressione — ISS", "https://www.iss.it/documents/20126/0/Consensus_1_2022_IT.pdf"),
                ref("Psychometric properties of the PHQ-9 in an Italian population — Bolgeo e colleghi", "https://pubmed.ncbi.nlm.nih.gov/39932691/")));
        syncReferences("people-pleasing", List.of(
                ref("Distinctions of unmitigated communion from communion — Fritz e Helgeson", "https://pubmed.ncbi.nlm.nih.gov/9686454/"),
                ref("A theory of unmitigated communion — Helgeson e Fritz", "https://pubmed.ncbi.nlm.nih.gov/15647153/"),
                ref("Self-silencing and women's health: a review — Maji e Dixit", "https://pubmed.ncbi.nlm.nih.gov/30518269/")));
        syncReferences("sindrome-impostore", List.of(
                ref("The imposter phenomenon in high achieving women — Clance e Imes", "https://doi.org/10.1037/h0086006"),
                ref("Impostor Phenomenon Measurement Scales: A Systematic Review", "https://pmc.ncbi.nlm.nih.gov/articles/PMC6463809/"),
                ref("Cross-cultural validation of the Impostor-Profile 30 — Ibrahim e colleghi", "https://doi.org/10.1007/s12144-025-07865-1")));
        syncReferences("autosabotaggio", List.of(
                ref("Self-defeating behavior patterns among normal individuals — Baumeister e Scher", "https://pubmed.ncbi.nlm.nih.gov/3043527/"),
                ref("The nature of procrastination: a meta-analytic and theoretical review — Steel", "https://pubmed.ncbi.nlm.nih.gov/17201571/"),
                ref("On the Measurement of Procrastination in Six European Countries — Svartdal e colleghi", "https://pubmed.ncbi.nlm.nih.gov/27630595/")));
        syncReferences("tratti-borderline-adulti", List.of(
                ref("The Italian Version of the Borderline Personality Disorder Severity Index IV — di Giacomo e colleghi", "https://pubmed.ncbi.nlm.nih.gov/28604275/"),
                ref("Clinical descriptions and diagnostic requirements for ICD-11 — WHO", "https://iris.who.int/bitstream/handle/10665/375767/9789240077263-eng.pdf?sequence=1"),
                ref("Percorsi di cura per i disturbi gravi di personalità — Ministero della Salute", "https://www.salute.gov.it/new/sites/default/files/imported/C_17_pubblicazioni_2461_allegato.pdf"),
                ref("Diagnosi e trattamento del disturbo borderline di personalità — ISS, linea guida in produzione", "https://www.iss.it/-/diagnosi-trattamento-disturbo-borderline-personalit%C3%A0_in-prog")));
        syncReferences("paura-abbandono", List.of(
                ref("Italian Validation of the Adult Attachment Scale-Revised — Troisi, Parola e Margherita", "https://pubmed.ncbi.nlm.nih.gov/36407970/"),
                ref("Psychometric properties of the Italian ECR-12 — Brugnera e colleghi", "https://pmc.ncbi.nlm.nih.gov/articles/PMC7453162/"),
                ref("Separation anxiety in a community sample of Italian emerging adults — Iannattone e colleghi", "https://pubmed.ncbi.nlm.nih.gov/33937113/"),
                ref("Attachment Theory and Affect Regulation — Mikulincer, Shaver e Pereg", "https://doi.org/10.1023/A:1024515519160")));
        syncReferences("fomo", List.of(
                ref("Italian version of the Fear of Missing Out Scale — Casale e Fioravanti", "https://pubmed.ncbi.nlm.nih.gov/31704432/"),
                ref("Italian version of the Online Fear of Missing Out — Sommantico e colleghi", "https://doi.org/10.1016/j.chbr.2024.100374"),
                ref("Motivational, emotional, and behavioral correlates of fear of missing out — Przybylski e colleghi", "https://doi.org/10.1016/j.chb.2013.02.014"),
                ref("FoMO, digital technology use, and psychological well-being: a scoping review — Groenestein e colleghi", "https://doi.org/10.1371/journal.pone.0308643")));
        syncReferences("intelligenza-linguistica", List.of(
                ref("The Theory of Multiple Intelligences — Project Zero, Harvard", "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf"),
                ref("Beyond g: Putting multiple intelligences theory to the test — Visser, Ashton e Vernon", "https://doi.org/10.1016/j.intell.2006.02.004"),
                ref("CEFR Companion Volume: mediation and modes of communication — Council of Europe", "https://www.coe.int/en/web/common-european-framework-reference-languages/mediation"),
                ref("L'indagine PIAAC sulle competenze degli adulti — INAPP", "https://www.inapp.gov.it/piaac/conosci-piaac/lindagine-piaac")));
        syncReferences("intelligenza-intrapersonale", List.of(
                ref("The Theory of Multiple Intelligences — Project Zero, Harvard", "https://pz.harvard.edu/sites/default/files/Theory%20of%20MI.pdf"),
                ref("Beyond g: Putting multiple intelligences theory to the test — Visser, Ashton e Vernon", "https://doi.org/10.1016/j.intell.2006.02.004"),
                ref("The Self-Reflection and Insight Scale — Italian Version — Di Fabio e Svicher", "https://doi.org/10.14605/CS1532206"),
                ref("Interoceptive accuracy and awareness in an Italian sample — Calì e colleghi", "https://pubmed.ncbi.nlm.nih.gov/26379571/")));
        syncReferences("resilienza-psicologica", List.of(
                ref("Psychological Resilience: A Review and Critique — Fletcher e Sarkar", "https://doi.org/10.1027/1016-9040/a000124"),
                ref("Resilience definitions, theory, and challenges — Southwick e colleghi", "https://pubmed.ncbi.nlm.nih.gov/25317257/"),
                ref("The Resilience Scale for Adults in Italy — Bonfiglio e colleghi", "https://pubmed.ncbi.nlm.nih.gov/27031088/"),
                ref("Italian version of the 14-item Resilience Scale — Cuoco e colleghi", "https://pubmed.ncbi.nlm.nih.gov/34850301/"),
                ref("A methodological review of resilience measurement scales — Windle e colleghi", "https://pubmed.ncbi.nlm.nih.gov/21294858/")));
        syncReferences("gelosia-partner", List.of(
                ref("Validation of the Italian brief Multidimensional Jealousy Scale — Diotaiuti e colleghi", "https://doi.org/10.3389/fpsyg.2022.1013584"),
                ref("Multidimensional Jealousy — Pfeiffer e Wong", "https://doi.org/10.1177/026540758900600203"),
                ref("A systematic review of romantic jealousy in relationships — Martínez-León e colleghi", "https://doi.org/10.4067/S0718-48082017000200203"),
                ref("Infidelity, romantic jealousy and intimate partner violence — Pichon e colleghi", "https://doi.org/10.3390/ijerph17165682"),
                ref("Understanding and addressing intimate partner violence — WHO", "https://www.who.int/publications/i/item/WHO-RHR-12.36"),
                ref("1522 — Numero antiviolenza e antistalking", "https://www.pariopportunita.gov.it/it/numeri-utili/1522-numero-antiviolenza-e-antistalking/")));
        syncReferences("soddisfazione-vita", List.of(
                ref("La Satisfaction With Life Scale: validazione italiana con lavoratori adulti — Di Fabio e Palazzeschi", "https://flore.unifi.it/handle/2158/656647"),
                ref("La soddisfazione dei cittadini per le condizioni di vita — ISTAT", "https://www.istat.it/comunicato-stampa/soddisfazione-dei-cittadini-anno-2024/"),
                ref("The Satisfaction With Life Scale — Diener e colleghi", "https://pubmed.ncbi.nlm.nih.gov/16367493/"),
                ref("OECD Guidelines on Measuring Subjective Well-being — 2025 Update", "https://www.oecd.org/en/publications/oecd-guidelines-on-measuring-subjective-well-being-2025-update_9203632a-en/full-report/measuring-subjective-well-being_b4b53f27.html"),
                ref("Measurement invariance of the Satisfaction With Life Scale — Emerson e colleghi", "https://pubmed.ncbi.nlm.nih.gov/28324322/"),
                ref("Life satisfaction around the world — Jebb e colleghi", "https://doi.org/10.1371/journal.pone.0313107")));
        syncReferences("ptsd-adulti", List.of(
                ref("Italian validation of the PTSD Checklist for DSM-5 — Di Tella e colleghi", "https://doi.org/10.3390/ijerph19095282"),
                ref("Trauma exposure and post-traumatic stress disorder in Italy — Carmassi e colleghi", "https://pubmed.ncbi.nlm.nih.gov/25266475/"),
                ref("PTSD and DSM-5 — National Center for PTSD", "https://www.ptsd.va.gov/professional/treat/essentials/dsm5_ptsd.asp"),
                ref("Post-traumatic stress disorder — World Health Organization", "https://www.who.int/news-room/fact-sheets/detail/post-traumatic-stress-disorder"),
                ref("Post-traumatic stress disorder — NICE NG116", "https://www.nice.org.uk/guidance/ng116/chapter/Recommendations"),
                ref("Guidelines for conditions specifically related to stress — World Health Organization", "https://www.who.int/publications-detail-redirect/9789241505406")));
        syncReferences("stili-attaccamento", List.of(
                ref("Attachment styles among young adults — Bartholomew e Horowitz", "https://pubmed.ncbi.nlm.nih.gov/1920064/"),
                ref("Italian validation of the ECR-R — Busonera e colleghi", "https://pubmed.ncbi.nlm.nih.gov/25074302/"),
                ref("Italian validation of the ECR-12 — Brugnera e colleghi", "https://pmc.ncbi.nlm.nih.gov/articles/PMC7453162/"),
                ref("Are adult attachment styles categorical or dimensional? — Fraley e colleghi", "https://pubmed.ncbi.nlm.nih.gov/25559192/"),
                ref("Adult Attachment, Stress, and Romantic Relationships — Simpson e Rholes", "https://pmc.ncbi.nlm.nih.gov/articles/PMC4845754/"),
                ref("Within-person variation in attachment — Girme e colleghi", "https://pmc.ncbi.nlm.nih.gov/articles/PMC5820166/")));
        syncReferences("limerenza", List.of(
                ref("Development and Validation of the Limerence Questionnaire (LQ-11) — Marshall e colleghi", "https://doi.org/10.1177/00332941251394980"),
                ref("Limerence, Hidden Obsession, Fixation, and Rumination — Bradbury, Short e Bleakley", "https://doi.org/10.1007/s11896-024-09674-x"),
                ref("Exploring the Lived-Experience of Limerence — Willmott e Bentley", "https://doi.org/10.46743/2160-3715/2015.1420"),
                ref("What fuels passion? — Carswell e Impett", "https://doi.org/10.1111/spc3.12629"),
                ref("La rete dei servizi per la salute mentale — Ministero della Salute", "https://www.salute.gov.it/new/it/tema/salute-mentale/la-rete-dei-servizi-la-salute-mentale/")));
        syncReferences("parentificazione", List.of(
                ref("Parentification Vulnerability, Reactivity, Resilience, and Thriving — Dariotis e colleghi", "https://doi.org/10.3390/ijerph20136197"),
                ref("Parentification Among Young Carers: A Concept Analysis — Hendricks e colleghi", "https://doi.org/10.1007/s10560-021-00784-7"),
                ref("Assessing Family Caregiving — Hooper e Doehler", "https://doi.org/10.1111/j.1752-0606.2011.00258.x"),
                ref("Polish Parentification Inventory — Borchet e colleghi", "https://doi.org/10.1007/s10826-022-02338-6"),
                ref("Parentification and distress in Italian adult siblings — Levante e colleghi", "https://doi.org/10.3389/fpsyt.2022.1079608"),
                ref("Positive and negative aspects of parentification — Khafi e colleghi", "https://doi.org/10.1016/j.childyouth.2022.106709"),
                ref("La rete dei servizi per la salute mentale — Ministero della Salute", "https://www.salute.gov.it/new/it/tema/salute-mentale/la-rete-dei-servizi-la-salute-mentale/")));
        syncReferences("gaslighting", List.of(
                ref("Defining Gaslighting in Gender-Based Violence — Adair", "https://doi.org/10.1177/15248380251344316"),
                ref("The Gaslighting Relationship Exposure Inventory — Tager-Shafrir e colleghi", "https://doi.org/10.1177/02654075241266942"),
                ref("A qualitative analysis of gaslighting in romantic relationships — Klein e colleghi", "https://doi.org/10.1111/pere.12510"),
                ref("The Sociology of Gaslighting — Sweet", "https://doi.org/10.1177/0003122419874843"),
                ref("Gaslighting Exposure During Emerging Adulthood — Bellomare e colleghi", "https://doi.org/10.21500/20112084.6306"),
                ref("Definizioni e indicatori sulla violenza psicologica — Istat", "https://www.istat.it/statistiche-per-temi/focus/violenza-sulle-donne/il-contesto/definizioni-e-indicatori/"),
                ref("Understanding Psychological Violence against Women — EIGE", "https://eige.europa.eu/publications-resources/publications/understanding-psychological-violence-against-women-need-harmonised-definitions-and-data-eu"),
                ref("1522 — Numero Anti Violenza e Stalking", "https://www.1522.eu/cose-1522/")));
        syncReferences("love-bombing", List.of(
                ref("Love-bombing: A Narcissistic Approach to Relationship Formation — Strutzenberg e colleghi", "https://doi.org/10.54119/discovery.zxgc9960"),
                ref("Turkish Adaptation Study of Love Bombing Scale — Çalışkan Sarı", "https://doi.org/10.17336/igusbd.1651349"),
                ref("Women's Experiences of Coercive Control — Choudhury e colleghi", "https://doi.org/10.1007/s10896-025-00970-6"),
                ref("A qualitative analysis of gaslighting in romantic relationships — Klein e colleghi", "https://doi.org/10.1111/pere.12510"),
                ref("Coercive control in intimate partner violence — Hamberger e colleghi", "https://doi.org/10.1016/j.avb.2017.08.003"),
                ref("Definizioni e indicatori sulla violenza psicologica — Istat", "https://www.istat.it/statistiche-per-temi/focus/violenza-sulle-donne/il-contesto/definizioni-e-indicatori/"),
                ref("Understanding Psychological Violence against Women — EIGE", "https://eige.europa.eu/publications-resources/publications/understanding-psychological-violence-against-women-need-harmonised-definitions-and-data-eu"),
                ref("1522 — Numero Anti Violenza e Stalking", "https://www.1522.eu/cose-1522/")));
        syncReferences("breadcrumbing", List.of(
                ref("Psychological Correlates of Ghosting and Breadcrumbing Experiences — Navarro e colleghi", "https://doi.org/10.3390/ijerph17031116"),
                ref("Ghosting and breadcrumbing: prevalence and association with online dating — Navarro e colleghi", "https://doi.org/10.24310/espsiescpsi.v13i2.9960"),
                ref("Development and Validation of BREAD-ASR — Rodríguez-García e colleghi", "https://doi.org/10.3390/ijerph17249548"),
                ref("Construcción de una escala de experiencias de breadcrumbing — Simil", "https://doi.org/10.34192/cienciaysalud.v7i3.688"),
                ref("Breadcrumbing Experience Scale: Preliminary Validation in Spain — Navarro e Simil", "https://doi.org/10.1080/01639625.2025.2516636"),
                ref("Breadcrumbing in Young Adults: A Qualitative Study — Khattar e colleghi", "https://doi.org/10.3390/soc13020041"),
                ref("Ghosting, orbiting and breadcrumbing: conceptual distinctions — Schokkenbroek e colleghi", "https://doi.org/10.1016/j.chb.2025.108637"),
                ref("1522 — Numero Anti Violenza e Stalking", "https://www.1522.eu/cose-1522/")));
        syncReferences("orbiting", List.of(
                ref("Ghosting and orbiting: An analysis of victims' experiences — Pancani e colleghi", "https://doi.org/10.1177/02654075211000417"),
                ref("Relationship dissolution strategies: Comparing ghosting, orbiting, and rejection — Pancani e colleghi", "https://doi.org/10.5817/CP2022-2-9"),
                ref("What is (not) ghosting? — Schokkenbroek e colleghi", "https://doi.org/10.1016/j.chb.2025.108637"),
                ref("Unwanted and unfollowed: Defining ghosting and social media unfollowing — Collins e colleghi", "https://doi.org/10.1111/pere.12492"),
                ref("Facebook surveillance of former romantic partners — Marshall", "https://pubmed.ncbi.nlm.nih.gov/22946958/"),
                ref("1522 — Numero Anti Violenza e Stalking", "https://www.1522.eu/cose-1522/")));
    }

    private void syncReferences(String testId, List<ReferenceSeed> expected) {
        List<TestReferenceEntity> current = referenceRepository.findByTestIdOrderByDisplayOrderAsc(testId);
        boolean alreadySynchronized = current.size() == expected.size();
        for (int index = 0; alreadySynchronized && index < expected.size(); index++) {
            TestReferenceEntity actual = current.get(index);
            ReferenceSeed desired = expected.get(index);
            alreadySynchronized = actual.getDisplayOrder() == index + 1
                    && actual.getTitle().equals(desired.title())
                    && actual.getUrl().equals(desired.url());
        }
        if (alreadySynchronized) return;

        referenceRepository.deleteByTestId(testId);
        for (int index = 0; index < expected.size(); index++) {
            ReferenceSeed reference = expected.get(index);
            saveReference(testId, reference.title(), reference.url(), index + 1);
        }
    }

    private ReferenceSeed ref(String title, String url) {
        return new ReferenceSeed(title, url);
    }

    private boolean requiresSeed(String testId, String version) {
        return testRepository.findById(testId)
                .map(test -> !version.equals(test.getVersion()))
                .orElse(true);
    }

    private void removeTest(String testId) {
        if (!testRepository.existsById(testId)) return;
        interpretationRepository.deleteByTestId(testId);
        referenceRepository.deleteByTestId(testId);
        questionRepository.deleteByTestId(testId);
        areaRepository.deleteByTestId(testId);
        testRepository.deleteById(testId);
        testRepository.flush();
    }

    private void saveTest(TestDefinitionEntity test) { testRepository.save(test); }
    private void saveReference(String testId, String title, String url, int order) {
        referenceRepository.save(new TestReferenceEntity(testId, title, url, order));
    }
    private void saveArea(String testId, String code, String name, int order) { areaRepository.save(new TestAreaEntity(testId, code, name, order)); }
    private QuestionSeed q(String areaCode, String text) { return new QuestionSeed(areaCode, text, null); }
    private QuestionSeed qe(String areaCode, String text, String example) { return new QuestionSeed(areaCode, text, example); }
    private void saveQuestions(String testId, List<QuestionSeed> questions) {
        Map<String, List<QuestionSeed>> questionsByArea = questions.stream()
                .collect(Collectors.groupingBy(QuestionSeed::areaCode, LinkedHashMap::new, Collectors.toList()));
        int longestArea = questionsByArea.values().stream().mapToInt(List::size).max().orElse(0);
        int position = 1;
        for (int offset = 0; offset < longestArea; offset++) {
            for (List<QuestionSeed> areaQuestions : questionsByArea.values()) {
                if (offset < areaQuestions.size()) {
                    QuestionSeed question = areaQuestions.get(offset);
                    questionRepository.save(new TestQuestionEntity(
                            testId, question.areaCode(), position++, question.text(), question.example()));
                }
            }
        }
    }
    private void saveGlobal(String testId, String code, String title, String description, String detail) {
        interpretationRepository.save(new InterpretationEntity(testId, "GLOBAL", null, code, title, description, detail));
    }
    private void saveAreaInsights(String testId, String areaCode, String low, String medium, String high) {
        interpretationRepository.save(new InterpretationEntity(testId, "AREA", areaCode, "LOW", null, low, null));
        interpretationRepository.save(new InterpretationEntity(testId, "AREA", areaCode, "MEDIUM", null, medium, null));
        interpretationRepository.save(new InterpretationEntity(testId, "AREA", areaCode, "HIGH", null, high, null));
    }
    private void saveStyle(String testId, String styleCode, String description) {
        interpretationRepository.save(new InterpretationEntity(
                testId, "STYLE", styleCode, "PROFILE", null, description, null));
    }

    private record QuestionSeed(String areaCode, String text, String example) {
    }

    private record ReferenceSeed(String title, String url) {
    }
}
