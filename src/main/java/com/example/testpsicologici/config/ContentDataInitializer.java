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
    }

    private void seedAutismInformationTest() {
        String id = "tratti-autistici-adulti";
        String version = "2.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Tratti autistici nell'adulto",
                "Autovalutazione informativa",
                "Questionario informativo per adulti su comunicazione sociale, segnali impliciti, routine, flessibilità, interessi e sensibilità sensoriale.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato: le esperienze descritte possono avere molte spiegazioni diverse. Per una valutazione dell'autismo è necessario rivolgersi a professionisti qualificati.",
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
                q("sociale", "Trovo faticoso mantenere una relazione quando le aspettative reciproche non vengono espresse chiaramente."),
                q("sociale", "Quando qualcuno condivide un'emozione, non sempre capisco quale risposta si aspetta da me."),
                q("sociale", "Durante interazioni sociali prolungate devo mantenere uno sforzo consapevole per partecipare."),
                q("sociale", "Quando una conversazione cambia rapidamente tono o argomento, mi è difficile adattare il mio modo di partecipare."),
                q("non_verbale", "Mi è difficile capire cosa prova una persona basandomi soltanto sull'espressione del viso o sul tono di voce."),
                q("non_verbale", "Durante una conversazione devo pensare consapevolmente a quanto contatto visivo mantenere."),
                q("non_verbale", "Interpreto in modo letterale battute, allusioni o richieste formulate indirettamente."),
                q("non_verbale", "Preparo mentalmente parole, espressioni o tono di voce prima di affrontare alcune situazioni sociali."),
                q("non_verbale", "Gesti, posture o regole sociali non dette possono risultarmi difficili da interpretare."),
                q("non_verbale", "Mi capita di non accorgermi che qualcuno vuole concludere o cambiare una conversazione finché non lo dice chiaramente."),
                q("routine", "Un cambiamento imprevisto nei programmi mi richiede molto tempo per adattarmi."),
                q("routine", "Preferisco svolgere alcune attività seguendo ogni volta lo stesso ordine o la stessa procedura."),
                q("routine", "Passare rapidamente da un'attività a un'altra può essere difficile, anche quando so cosa devo fare."),
                q("routine", "Ripetere piccoli movimenti o gesti mi aiuta a calmarmi."),
                q("routine", "Affronto meglio una situazione nuova quando posso conoscerne in anticipo dettagli e passaggi."),
                q("routine", "Se non posso completare un'attività nel modo che avevo previsto, faccio fatica a lasciarla e passare oltre."),
                q("sensoriale", "Mi concentro su un interesse fino a perdere la percezione del tempo."),
                q("sensoriale", "Alcuni miei interessi occupano molto spazio nei miei pensieri e mi spingono ad approfondire ogni dettaglio."),
                q("sensoriale", "Suoni, luci, odori, tessuti o temperature che altri tollerano possono risultarmi molto intensi o distraenti."),
                q("sensoriale", "Cerco volontariamente particolari sensazioni, movimenti, consistenze o suoni perché mi fanno stare bene."),
                q("sensoriale", "Dedicarmi ai miei interessi più intensi mi dà una sensazione importante di calma, energia o stabilità."),
                q("sensoriale", "Dopo un ambiente molto rumoroso, affollato o luminoso, ho bisogno di tempo in tranquillità per recuperare.")));

        saveGlobal(id, "LOW", "Poche esperienze ricorrenti",
                "Nel complesso hai indicato una presenza contenuta delle esperienze esplorate dal questionario.",
                "Questo risultato non esclude né conferma l'autismo: un questionario non validato non può sostituire una valutazione della storia personale, dello sviluppo e del funzionamento nei diversi contesti.");
        saveGlobal(id, "MIXED", "Un profilo variabile",
                "Le tue risposte descrivono esperienze presenti in modo diverso a seconda dell'ambito o della situazione.",
                "Il quadro non è uniforme: alcune modalità possono essere occasionali, legate al contesto o condivise con condizioni ed esperienze diverse dall'autismo.");
        saveGlobal(id, "FOCUSED", "Alcuni aspetti emergono più chiaramente",
                "Le tue risposte mettono in evidenza alcune esperienze ricorrenti, mentre altre sembrano meno presenti.",
                "Può essere utile osservare quando questi aspetti compaiono, da quanto tempo sono presenti e quanto incidono sulla vita quotidiana. Da soli non permettono di formulare una diagnosi.");
        saveGlobal(id, "BROAD", "Esperienze presenti in più ambiti",
                "Le tue risposte indicano che molte delle esperienze esplorate compaiono con una certa continuità in più aspetti della vita quotidiana.",
                "Se queste esperienze sono presenti fin dall'infanzia, causano fatica o limitano relazioni, studio, lavoro o benessere, puoi valutare di parlarne con un professionista esperto di autismo nell'adulto. Il risultato resta informativo e non diagnostico.");

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
        String version = "2.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "ADHD nell'adulto: tratti associati",
                "Autovalutazione informativa",
                "Esplora difficoltà ricorrenti relative ad attenzione, organizzazione, gestione del tempo, impulsività e irrequietezza nell'adulto.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato: difficoltà simili possono dipendere da stress, sonno, ansia, depressione, altre condizioni o circostanze personali. Una diagnosi di ADHD richiede una valutazione specialistica della storia dello sviluppo, dell'impatto quotidiano e della presenza delle difficoltà in più contesti.",
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
                q("organizzazione", "Mi è difficile suddividere un compito complesso in passaggi e decidere da dove iniziare."),
                q("organizzazione", "Dimentico appuntamenti, scadenze o impegni anche quando per me sono importanti."),
                q("organizzazione", "Perdo o cerco spesso oggetti necessari, come chiavi, documenti o telefono."),
                q("organizzazione", "Rimando attività che richiedono concentrazione finché l'urgenza non mi costringe a iniziare."),
                q("organizzazione", "Mi accorgo troppo tardi che più impegni, attività o scadenze si sovrappongono."),
                q("impulsivita", "Interrompo le persone o completo le loro frasi prima che abbiano finito di parlare."),
                q("impulsivita", "Prendo decisioni rapidamente e ne valuto le conseguenze solo dopo."),
                q("impulsivita", "Aspettare il mio turno in una conversazione, una fila o un'attività mi richiede molto autocontrollo."),
                q("impulsivita", "Dico qualcosa d'impulso e poco dopo vorrei averci pensato più a lungo."),
                q("impulsivita", "Abbandono un piano per seguire un'idea o un impulso appena comparso, anche se avevo altre priorità."),
                q("impulsivita", "Nelle discussioni reagisco prima di aver compreso fino in fondo ciò che l'altra persona intende."),
                q("irrequietezza", "Quando devo restare fermo a lungo avverto un'irrequietezza interna difficile da ignorare."),
                q("irrequietezza", "Muovo mani o piedi, cambio spesso posizione o cerco occasioni per alzarmi."),
                q("irrequietezza", "Mi è difficile riposare senza occuparmi contemporaneamente di qualcos'altro."),
                q("irrequietezza", "Quando un'attività non mi coinvolge, cerco rapidamente novità o stimoli più interessanti."),
                q("irrequietezza", "Attività molto lente o attese prolungate mi fanno sentire impaziente o agitato."),
                q("irrequietezza", "Mi sento come se dovessi essere in movimento o impegnato, anche nei momenti in cui vorrei rallentare.")));

        saveGlobal(id, "LOW", "Poche difficoltà ricorrenti",
                "Nel complesso hai indicato una presenza contenuta delle esperienze esplorate dal questionario.",
                "Questo risultato non esclude né conferma l'ADHD. La frequenza percepita può cambiare nel tempo e un questionario non validato non valuta esordio nell'infanzia, impatto funzionale, presenza in più contesti o possibili spiegazioni alternative.");
        saveGlobal(id, "MIXED", "Un andamento variabile",
                "Le tue risposte descrivono difficoltà presenti in modo diverso secondo il tipo di attività o la situazione.",
                "Il quadro non è uniforme: sonno, stress, carico mentale, interesse e ambiente possono incidere molto su attenzione e autoregolazione. Le risposte, da sole, non permettono di formulare una diagnosi.");
        saveGlobal(id, "FOCUSED", "Alcuni aspetti emergono con chiarezza",
                "Le tue risposte mettono in evidenza difficoltà ricorrenti in alcuni ambiti, mentre altri sembrano meno coinvolti.",
                "Può essere utile osservare da quanto tempo sono presenti, in quali contesti compaiono e quanto incidono su lavoro, studio, relazioni o gestione quotidiana. Un profilo circoscritto non conferma né esclude l'ADHD.");
        saveGlobal(id, "BROAD", "Difficoltà presenti in più ambiti",
                "Le tue risposte indicano che molte delle esperienze esplorate ricorrono in diversi aspetti della vita quotidiana.",
                "Se queste difficoltà erano presenti già nell'infanzia, compaiono in più contesti e interferiscono con lavoro, studio, relazioni o benessere, puoi valutare una consulenza con un professionista esperto di ADHD nell'adulto. Il risultato resta informativo e non diagnostico.");

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
        String version = "1.3";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Pensieri ossessivi e compulsioni (DOC)",
                "Autovalutazione informativa",
                "Esplora pensieri intrusivi, dubbio, bisogno di certezza, contaminazione, controlli e rituali nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato: pensieri indesiderati, dubbi e abitudini ripetitive possono comparire in molte persone e non indicano l'intenzione di agire su un pensiero. Una valutazione del disturbo ossessivo-compulsivo considera anche disagio, tempo occupato, interferenza con la vita quotidiana e possibili spiegazioni alternative.",
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
                q("intrusioni", "Mi soffermo sul significato di un pensiero indesiderato e temo che dica qualcosa di negativo su di me."),
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
                q("controllo", "Mi sento particolarmente responsabile di impedire eventi negativi, anche quando sono poco probabili."),
                q("controllo", "Torno sui miei passi o riapro un'attività per assicurarmi di non aver lasciato un pericolo o un errore."),
                q("controllo", "Ripercorro mentalmente ciò che ho fatto per verificare di non aver causato danni o conseguenze indesiderate."),
                q("controllo", "Ripeto un controllo finché non provo una sensazione sufficiente di sicurezza, anche se i fatti non sono cambiati."),
                q("controllo", "Uscire di casa o concludere un compito può richiedermi più tempo a causa delle verifiche che sento di dover fare."),
                q("rituali", "Sistemo gli oggetti finché l'ordine o la simmetria non mi sembrano esattamente giusti."),
                q("rituali", "Provo un forte disagio quando qualcosa appare asimmetrico, incompleto o fuori posto."),
                q("rituali", "Conto, ripeto parole o formulo pensieri particolari per neutralizzare un dubbio o ridurre l'ansia."),
                q("rituali", "Ripeto gesti o tocco oggetti secondo una sequenza o un numero preciso di volte."),
                q("rituali", "Se una sequenza viene interrotta o non mi sembra eseguita correttamente, sento il bisogno di ricominciare."),
                q("rituali", "Dedico tempo a rendere un'azione esatta o perfetta anche quando non produce un vantaggio pratico.")));

        saveGlobal(id, "LOW", "Poche esperienze ricorrenti",
                "Nel complesso hai indicato una presenza contenuta dei pensieri, dubbi e comportamenti ripetitivi esplorati dal questionario.",
                "Questo risultato non conferma né esclude un disturbo ossessivo-compulsivo. Pensieri indesiderati e controlli occasionali sono comuni; per una valutazione contano soprattutto disagio, tempo occupato, difficoltà a interromperli e impatto sulla vita quotidiana.");
        saveGlobal(id, "MIXED", "Un andamento variabile",
                "Le tue risposte descrivono esperienze presenti in modo diverso secondo il contenuto, la situazione o il momento.",
                "Il quadro non è uniforme: stress, ansia, responsabilità percepita e bisogno di certezza possono influenzare dubbi e rituali. Le risposte, da sole, non permettono di formulare una diagnosi.");
        saveGlobal(id, "FOCUSED", "Alcuni aspetti emergono con chiarezza",
                "Le tue risposte mettono in evidenza pensieri o comportamenti ricorrenti in alcuni ambiti, mentre altri sembrano meno coinvolti.",
                "Può essere utile osservare quanto tempo richiedono, quanto disagio provocano e se portano a evitamenti o rallentamenti. Un profilo circoscritto non conferma né esclude un disturbo ossessivo-compulsivo.");
        saveGlobal(id, "BROAD", "Esperienze ricorrenti in più ambiti",
                "Le tue risposte indicano che molte delle esperienze esplorate compaiono con continuità in diversi aspetti della vita quotidiana.",
                "Se pensieri intrusivi, controlli o rituali occupano molto tempo, provocano forte disagio o interferiscono con relazioni, studio, lavoro o autonomia, puoi valutare di parlarne con un professionista qualificato. Il risultato resta informativo e non diagnostico.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Autostima",
                "Autovalutazione informativa",
                "Esplora il rapporto con il valore personale, la fiducia in sé, gli errori, le critiche e il confronto con gli altri.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento clinicamente validato e non misura il tuo valore come persona. L'autostima può cambiare nel tempo e nei diversi contesti; le risposte descrivono soltanto quanto spesso riconosci alcune difficoltà legate al rapporto con te stesso.",
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
                q("valore", "Parto da un singolo limite o difetto per giudicare negativamente il mio valore complessivo."),
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
                q("approvazione", "L'opinione che ho di me cambia sensibilmente dopo una critica o un commento negativo."),
                q("approvazione", "Ho bisogno che i miei risultati vengano riconosciuti per sentirmi adeguato."),
                q("approvazione", "Cambio il mio modo di comportarmi per evitare la disapprovazione, anche quando non mi rappresenta."),
                q("approvazione", "Confrontare la mia vita o i miei risultati con quelli degli altri mi fa sentire inadeguato."),
                q("approvazione", "Cerco rassicurazioni o conferme dagli altri per riuscire a sentirmi a posto con me stesso."),
                q("approvazione", "Un rifiuto, un'esclusione o un disaccordo mi porta a dubitare del mio valore personale.")));

        saveGlobal(id, "LOW", "Un senso di valore generalmente solido",
                "Nel complesso hai indicato una presenza contenuta delle difficoltà relative all'autostima esplorate dal questionario.",
                "Questo non significa sentirsi sempre sicuri: dubbi, confronti ed errori possono influenzare chiunque. Le risposte suggeriscono però che, in genere, riesci a mantenere un'immagine di te abbastanza stabile senza far dipendere tutto da un singolo limite o giudizio.");
        saveGlobal(id, "MIXED", "Un equilibrio che cambia con il contesto",
                "Le tue risposte descrivono un rapporto con te stesso che può essere stabile in alcune situazioni e più vulnerabile in altre.",
                "Autostima e fiducia personale possono risentire del tipo di compito, delle relazioni, dello stress o del confronto con gli altri. Osservare i contesti in cui il giudizio su di te cambia può offrire indicazioni più utili di un punteggio isolato.");
        saveGlobal(id, "FOCUSED", "Un'area mette più alla prova la tua autostima",
                "Le risposte evidenziano difficoltà ricorrenti soprattutto in uno o due aspetti, mentre il resto del profilo appare più stabile.",
                "Può essere utile notare quali eventi attivano maggiormente dubbi, autocritica o bisogno di conferme e quanto a lungo ne risente l'opinione che hai di te. Questo questionario non è una valutazione clinica e non definisce il tuo valore personale.");
        saveGlobal(id, "BROAD", "Un'autostima spesso sotto pressione",
                "Le tue risposte indicano difficoltà frequenti nel riconoscere il tuo valore, fidarti di te o mantenere un'immagine stabile dopo errori e giudizi.",
                "Se questo modo di guardarti provoca sofferenza, limita scelte e relazioni o ti porta a rinunciare spesso a ciò che conta per te, parlarne con uno psicologo o psicoterapeuta può aiutarti a comprenderne le origini e costruire un rapporto più equilibrato con te stesso. Il risultato resta informativo e non diagnostico.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Dipendenza affettiva",
                "Autovalutazione informativa",
                "Esplora paura dell'abbandono, bisogno di rassicurazione, rinunce personali, autonomia e confini nelle relazioni affettive.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato e non stabilisce se una relazione sia sana o patologica. Rispondi pensando alla relazione attuale o, se non ne hai una, a una relazione affettiva significativa recente. Il legame e l'interdipendenza fanno parte delle relazioni: qui si esplora soltanto quanto alcune dinamiche diventano frequenti, rigide o limitanti. Il questionario non rileva gli abusi e la violenza non è mai responsabilità di chi la subisce. In presenza di controllo, minacce o violenza, cerca un aiuto sicuro; per le donne vittime di violenza e stalking il 1522 è gratuito e attivo 24 ore su 24.",
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
                q("autonomia", "Organizzo decisioni importanti soprattutto in funzione della relazione, anche quando questo mi penalizza."),
                q("autonomia", "Quando sono coinvolto in una relazione, obiettivi e progetti personali perdono importanza."),
                q("autonomia", "Se l'altra persona disapprova una mia scelta, tendo ad abbandonarla anche quando per me conta molto."),
                q("confini", "Accetto comportamenti che mi feriscono pur di evitare distanza, tensioni o una possibile rottura."),
                q("confini", "Mi è difficile dire di no all'altra persona quando ciò che chiede supera i miei limiti."),
                q("confini", "Mi sento responsabile dell'umore e del benessere dell'altra persona anche quando non dipendono da me."),
                q("confini", "Metto da parte bisogni o emozioni importanti per non rischiare di compromettere il legame."),
                q("confini", "Giustifico mancanze di rispetto ripetute perché temo di perdere la relazione."),
                q("confini", "Controllo attentamente parole e comportamenti per evitare che l'altra persona si allontani o ritiri il proprio affetto."),
                q("regolazione", "Il mio umore dipende molto dall'attenzione o dalla disponibilità che ricevo dall'altra persona."),
                q("regolazione", "I pensieri sulla relazione occupano così tanto spazio da rendermi difficile concentrarmi su altro."),
                q("regolazione", "Quando temo un allontanamento, cerco ripetutamente di ristabilire il contatto."),
                q("regolazione", "Dopo una rottura o un allontanamento sento un impulso forte a ristabilire il rapporto, anche sapendo che mi faceva stare male."),
                q("regolazione", "Minimizzo incompatibilità importanti per proteggere l'immagine della relazione."),
                q("regolazione", "Senza una relazione affettiva mi sento privo di direzione.")));

        saveGlobal(id, "LOW", "Legame e autonomia generalmente in equilibrio",
                "Nel complesso hai indicato una presenza contenuta delle dinamiche di dipendenza affettiva esplorate.",
                "Questo non significa vivere ogni relazione senza paure o bisogno di vicinanza. Le risposte suggeriscono però che, in genere, riesci a mantenere bisogni, interessi e valore personale senza farli dipendere interamente dal legame.");
        saveGlobal(id, "MIXED", "Un equilibrio sensibile ad alcune situazioni",
                "Le tue risposte descrivono dinamiche che cambiano in base al momento, alla relazione o al grado di incertezza percepito.",
                "Distanza, conflitti o periodi di maggiore vulnerabilità possono aumentare il bisogno di rassicurazione o rendere più difficile proteggere i tuoi spazi. Osservare quando accade e quale impatto produce può essere più utile di una singola etichetta.");
        saveGlobal(id, "FOCUSED", "Una dinamica relazionale richiede più attenzione",
                "Una o due aree emergono con maggiore frequenza, mentre negli altri aspetti sembra esserci più equilibrio.",
                "Può essere utile notare quali situazioni attivano maggiormente paura della perdita, rinunce, difficoltà nei confini o bisogno urgente di contatto. Il risultato è informativo e non definisce te né la tua relazione.");
        saveGlobal(id, "BROAD", "La relazione occupa uno spazio molto vincolante",
                "Le risposte indicano dinamiche frequenti in più aree, con possibile riduzione dell'autonomia e forte dipendenza dal legame per sentirti stabile.",
                "Se queste dinamiche causano sofferenza, isolamento, rinunce importanti o rendono difficile interrompere una relazione che ti fa stare male, confrontarti con uno psicologo o psicoterapeuta può aiutarti a comprenderle senza giudizio. Controllo, minacce e violenza non sono colpa tua: in questi casi la priorità è trovare un aiuto sicuro. Il questionario resta informativo e non diagnostico.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Assertività",
                "Autovalutazione informativa",
                "Esplora quanto riesci a esprimere opinioni e bisogni, dire di no, proteggere i tuoi confini e affrontare i disaccordi.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato e non misura il tuo valore, la tua forza o il tuo coraggio. Rispondi pensando a quanto spesso riesci davvero a mettere in pratica ciascun comportamento nei diversi contesti, non a quanto lo ritieni desiderabile. L'assertività può cambiare in base alla situazione, alla relazione, alla cultura e alla sicurezza percepita; non esporsi in un contesto minaccioso o con un forte squilibrio di potere può essere una scelta protettiva, non una carenza personale.",
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
                q("espressione", "Descrivo come mi sento parlando della mia esperienza personale."),
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
                q("confronto", "Riconosco un mio errore senza svalutare l'intera mia posizione."),
                q("confronto", "Esprimo un disaccordo rispettoso anche con una persona autorevole o importante per me."),
                q("confronto", "Affronto una tensione significativa invece di evitarla a lungo o accumulare risentimento."),
                q("iniziativa", "Formulo richieste chiare anche quando esiste la possibilità che l'altra persona risponda di no."),
                q("iniziativa", "Chiedo aiuto, informazioni o chiarimenti quando ne ho bisogno."),
                q("iniziativa", "Prendo l'iniziativa per iniziare una conversazione o presentarmi in un contesto poco familiare."),
                q("iniziativa", "Esprimo apprezzamento o affetto in modo diretto quando lo provo."),
                q("iniziativa", "Accolgo un complimento o un riconoscimento senza sminuirlo o respingerlo automaticamente."),
                q("iniziativa", "Nelle decisioni condivise propongo soluzioni che tengono conto anche delle mie priorità.")));

        saveGlobal(id, "LOW", "La tua voce trova ancora poco spazio",
                "Nel complesso hai indicato una frequenza contenuta dei comportamenti assertivi esplorati.",
                "Potresti tendere a trattenere opinioni, bisogni o richieste, soprattutto quando temi conflitti, giudizi o conseguenze relazionali. L'assertività è un insieme di abilità che può essere allenato gradualmente e il risultato non dice nulla sul tuo valore o sul tuo coraggio.");
        saveGlobal(id, "MIXED", "Un'assertività che cambia con il contesto",
                "Le tue risposte descrivono comportamenti assertivi presenti in alcune situazioni e più difficili in altre.",
                "Potresti sentirti libero di esprimerti con alcune persone ma faticare davanti a conflitti, autorità, richieste o legami importanti. Individuare i contesti in cui la tua voce si riduce può offrire indicazioni più utili di un'etichetta generale.");
        saveGlobal(id, "FOCUSED", "Una risorsa assertiva emerge con chiarezza",
                "Una o due aree risultano particolarmente solide, mentre altre sembrano richiedere più intenzionalità o allenamento.",
                "Le competenze già presenti possono diventare un punto di partenza: osservare che cosa ti aiuta in quei contesti può facilitare il trasferimento dello stesso equilibrio alle situazioni in cui esprimerti è più difficile.");
        saveGlobal(id, "BROAD", "Competenze assertive diffuse",
                "Le tue risposte indicano che esprimi con frequenza opinioni, bisogni e limiti mantenendo attenzione anche ai diritti altrui.",
                "Un profilo ampio non significa dover parlare sempre o ottenere sempre ciò che chiedi: essere assertivi comprende anche ascoltare, negoziare e scegliere consapevolmente quando intervenire. Situazione, sicurezza e conseguenze concrete restano importanti. Il risultato è informativo e non costituisce una valutazione clinica.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Intelligenza emotiva",
                "Autovalutazione informativa",
                "Esplora come riconosci, comprendi, utilizzi e regoli le emozioni proprie e altrui nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato e non misura un quoziente di intelligenza emotiva o un'abilità oggettiva. Essendo un'autovalutazione, descrive soprattutto quanto riconosci e metti in pratica alcuni comportamenti legati alle emozioni; è possibile sottovalutarsi o sopravvalutarsi. Rispondi pensando a ciò che fai abitualmente nei diversi contesti. Le emozioni non sono giuste o sbagliate e regolarle non significa reprimerle. Cultura, esperienze personali, neurodiversità e sicurezza del contesto possono influenzare percezione ed espressione emotiva.",
                version, false,
                "Frequenza complessiva delle competenze emotive esplorate",
                "Frequenza delle competenze emotive",
                true, 7).withSeo(
                "Test intelligenza emotiva online | Spazio Test",
                "Questionario su consapevolezza, comprensione e regolazione delle emozioni proprie e altrui. 24 domande, circa 6 minuti; non misura un quoziente emotivo.")
                .withResponseInstruction("Pensando agli ultimi tre mesi e a situazioni diverse, con quale frequenza lo hai fatto?"));

        saveReference(id, "The Ability Model of Emotional Intelligence: Principles and Updates",
                "https://sites.usnh.edu/jdmayer/wp-content/uploads/sites/261/2024/03/rp2016-mayer-caruso-salovey.pdf", 1);
        saveReference(id, "Emotional Intelligence: New Ability or Eclectic Traits?",
                "https://cdn2.psychologytoday.com/assets/attachments/1575/rp2008-mayersaloveycarusob.pdf", 2);

        saveArea(id, "percezione", "Percezione e consapevolezza emotiva", 1);
        saveArea(id, "facilitazione", "Uso delle emozioni nel pensiero e nelle decisioni", 2);
        saveArea(id, "comprensione", "Comprensione di cause, sfumature e cambiamenti", 3);
        saveArea(id, "regolazione", "Regolazione ed espressione nelle relazioni", 4);

        saveQuestions(id, List.of(
                q("percezione", "Mi accorgo dei segnali del corpo che accompagnano un'emozione prima che diventi molto intensa."),
                q("percezione", "Riesco a dare un nome abbastanza preciso a ciò che provo invece di fermarmi a un generico stare bene o stare male."),
                q("percezione", "Riconosco quando un'emozione sta aumentando, diminuendo o lasciando spazio a un'altra."),
                q("percezione", "Noto i segnali emotivi non verbali di una persona senza considerare infallibile la mia impressione."),
                q("percezione", "Mi accorgo quando il mio stato emotivo sta influenzando il mio comportamento."),
                q("percezione", "Quando non sono sicuro di ciò che prova qualcuno, verifico la mia interpretazione con domande rispettose."),
                q("facilitazione", "Considero ciò che provo come un'informazione utile quando devo prendere una decisione importante."),
                q("facilitazione", "Uso interesse, entusiasmo o disagio per capire meglio che cosa conta per me in una situazione."),
                q("facilitazione", "Quando un'emozione intensa riduce la mia lucidità, rimando se possibile le decisioni impulsive finché riesco a valutarle meglio."),
                q("facilitazione", "Adatto il modo di affrontare un compito al mio stato emotivo."),
                q("facilitazione", "Se sono bloccato in un unico punto di vista, cerco una prospettiva emotiva diversa per vedere nuove possibilità."),
                q("facilitazione", "Distinguo l'impulso emotivo del momento dagli obiettivi e dai valori che voglio seguire nel lungo periodo."),
                q("comprensione", "Collego ciò che provo a eventi, bisogni, aspettative o interpretazioni che possono averlo attivato."),
                q("comprensione", "Riesco a riconoscere emozioni diverse o contrastanti presenti nello stesso momento."),
                q("comprensione", "Comprendo come un'emozione possa trasformarsi, per esempio da irritazione a delusione o da timore a sollievo."),
                q("comprensione", "Prima di una situazione importante considero come potrei reagire emotivamente."),
                q("comprensione", "Riesco a capire perché lo stesso evento può provocare emozioni diverse in persone diverse."),
                q("comprensione", "Rivedo la mia lettura di una reazione emotiva quando emergono informazioni nuove sul contesto."),
                q("regolazione", "Riesco a restare in contatto con un'emozione intensa senza dover agire subito o fingere che non esista."),
                q("regolazione", "Scelgo strategie diverse per gestire le emozioni in base alla situazione, invece di usare sempre la stessa risposta."),
                q("regolazione", "Esprimo ciò che provo in modo comprensibile e rispettoso."),
                q("regolazione", "Dopo una reazione emotiva forte riesco a recuperare e riflettere su ciò che è accaduto."),
                q("regolazione", "Quando qualcuno condivide un'emozione, ascolto senza minimizzarla."),
                q("regolazione", "Se il modo in cui sto gestendo un'emozione non aiuta, provo a cambiare strategia o a cercare sostegno.")));

        saveGlobal(id, "LOW", "Competenze emotive ancora poco accessibili",
                "Nel complesso hai indicato una frequenza contenuta dei comportamenti emotivi esplorati.",
                "Potresti trovare difficile riconoscere con precisione ciò che accade dentro di te, usare quelle informazioni o scegliere come rispondere. Queste competenze possono essere sviluppate con osservazione ed esperienza; il risultato riflette la tua percezione attuale e non misura la tua intelligenza o il tuo valore.");
        saveGlobal(id, "MIXED", "Un profilo emotivo che cambia con le situazioni",
                "Le tue risposte descrivono competenze accessibili in alcuni momenti e più difficili quando emozioni, relazioni o pressioni diventano intense.",
                "È comune riconoscere bene alcuni stati ma faticare a comprenderli o regolarli in determinati contesti. Osservare dove il processo si interrompe può essere più utile di cercare un unico punteggio di intelligenza emotiva.");
        saveGlobal(id, "FOCUSED", "Alcune competenze emotive sono già solide",
                "Una o due aree emergono con maggiore continuità, mentre le altre sembrano dipendere maggiormente dal contesto o richiedere allenamento.",
                "Le risorse già presenti possono sostenere le aree meno accessibili: per esempio, riconoscere bene un'emozione può diventare il punto di partenza per comprenderla, usarne le informazioni e scegliere una risposta più flessibile.");
        saveGlobal(id, "BROAD", "Competenze emotive diffuse e flessibili",
                "Le tue risposte indicano una presenza frequente delle competenze esplorate in più momenti del processo emotivo.",
                "Questo non significa comprendere sempre gli altri, non provare emozioni intense o riuscire a regolarle in ogni situazione. Suggerisce piuttosto che disponi di più strumenti per osservare, interpretare e utilizzare le emozioni senza esserne guidato automaticamente. Il risultato resta informativo e non è una misura oggettiva di abilità.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Perfezionismo",
                "Autovalutazione informativa",
                "Esplora standard personali, paura degli errori, pressione del giudizio, dubbi e bisogno di controllo.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato e non stabilisce la presenza di un disturbo. Avere obiettivi ambiziosi, cura per i dettagli e desiderio di migliorare non è di per sé problematico: qui si esplora quanto gli standard diventino rigidi, quanto il valore personale dipenda dai risultati e quanto errori, dubbi o controllo producano fatica e limitazioni. Rispondi pensando alla frequenza reale nei diversi ambiti della tua vita, non soltanto al lavoro o allo studio.",
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
                q("standard", "Quando raggiungo un buon risultato, lo considero presto il nuovo minimo invece di riconoscerlo come un successo."),
                q("standard", "Faccio fatica a sentirmi soddisfatto se il risultato presenta anche una piccola imperfezione."),
                q("standard", "Valuto il mio valore personale soprattutto in base a produttività, risultati o prestazioni."),
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
                q("controllo", "Dedico a dettagli secondari molto più tempo di quanto il loro impatto sul risultato richiederebbe."),
                q("controllo", "Rifaccio o ritocco attività già adeguate perché mi è difficile accettarle come concluse."),
                q("controllo", "Fatico a delegare perché temo che il compito non venga svolto esattamente come ritengo necessario."),
                q("controllo", "Un cambiamento imprevisto nel piano mi provoca una frustrazione intensa perché compromette il modo ideale di procedere."),
                q("controllo", "Rimando l'inizio di un'attività finché non ho trovato il metodo, il momento o le condizioni che considero perfetti."),
                q("controllo", "Mi è difficile prendere una decisione quando restano alternative da valutare.")));

        saveGlobal(id, "LOW", "Poche dinamiche perfezionistiche ricorrenti",
                "Nel complesso hai indicato una presenza contenuta delle pressioni perfezionistiche esplorate.",
                "Puoi avere standard elevati e attenzione alla qualità senza lasciare che errori, giudizi o dettagli determinino stabilmente il tuo valore e le tue scelte. Questo risultato non esclude momenti di forte esigenza e non rappresenta una valutazione clinica.");
        saveGlobal(id, "MIXED", "Un perfezionismo che emerge in alcuni contesti",
                "Le tue risposte descrivono dinamiche variabili, più presenti in determinati ambiti, compiti o relazioni.",
                "Potresti mantenere flessibilità in molte situazioni ma diventare più rigido quando il risultato è importante, visibile o legato al giudizio altrui. Osservare dove il costo aumenta può essere più utile di definirti semplicemente perfezionista.");
        saveGlobal(id, "FOCUSED", "Un'area concentra la pressione perfezionistica",
                "Una o due aree emergono con particolare frequenza, mentre negli altri aspetti sembra esserci maggiore flessibilità.",
                "Può essere utile notare se la pressione nasce soprattutto dagli standard, dalla paura degli errori, dalle aspettative percepite o dal bisogno di controllo. Distinguere il meccanismo prevalente aiuta a capire quando la ricerca della qualità smette di essere funzionale.");
        saveGlobal(id, "BROAD", "Una pressione perfezionistica diffusa",
                "Le risposte indicano dinamiche frequenti in più aree, con possibile difficoltà a riconoscere un risultato come sufficiente e a separare prestazione e valore personale.",
                "Se questa pressione causa sofferenza, blocchi, procrastinazione, esaurimento o rinunce importanti, confrontarti con uno psicologo o psicoterapeuta può aiutarti a costruire standard più flessibili senza abbandonare ciò che per te conta. Il risultato resta informativo e non diagnostico.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ansia sociale",
                "Autovalutazione informativa",
                "Esplora paura del giudizio, tensione nelle interazioni, autocontrollo, situazioni sotto osservazione ed evitamento sociale.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato e non permette di stabilire la presenza di un disturbo d'ansia sociale. Timidezza, riservatezza e disagio occasionale sono esperienze comuni; una valutazione clinica considera durata, intensità, proporzione rispetto al rischio reale e impatto sulla vita. Esperienze simili possono dipendere anche da stress, depressione, trauma, neurodiversità, difficoltà comunicative o contesti realmente ostili e discriminatori. Rispondi pensando alla frequenza delle esperienze negli ultimi mesi e nei diversi contesti. Se ansia o evitamento limitano relazioni, studio, lavoro o attività importanti, puoi parlarne con uno psicologo, psicoterapeuta o medico qualificato.",
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
                q("valutazione", "Sento di dover controllare attentamente ciò che dico e faccio per evitare di dare un'impressione negativa."),
                q("valutazione", "Un piccolo errore sociale mi sembra capace di compromettere a lungo l'opinione che gli altri hanno di me."),
                q("interazione", "Parlare con persone che conosco poco mi provoca una tensione difficile da ignorare."),
                q("interazione", "Mi è difficile iniziare una conversazione, presentarmi o trovare qualcosa da dire con persone nuove."),
                q("interazione", "Quando l'attenzione si sposta su di me durante una conversazione, la mente può diventare vuota o confusa."),
                q("interazione", "Fare una richiesta in presenza di altre persone mi crea forte disagio."),
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

        saveGlobal(id, "LOW", "Poche difficoltà sociali ricorrenti",
                "Nel complesso hai indicato una presenza contenuta delle esperienze di ansia sociale esplorate.",
                "Questo non significa sentirsi sempre disinvolti: timidezza, tensione e desiderio di fare una buona impressione possono comparire in chiunque. Le risposte suggeriscono però che giudizio ed esposizione sociale tendono a non limitare in modo costante le tue scelte.");
        saveGlobal(id, "MIXED", "Un'ansia sociale legata ad alcuni contesti",
                "Le tue risposte descrivono difficoltà variabili, più evidenti con certe persone, situazioni o livelli di esposizione.",
                "Potresti sentirti relativamente a tuo agio in contesti familiari ma provare forte tensione quando sei osservato, devi prendere iniziativa o temi una valutazione. Notare quali condizioni fanno aumentare l'ansia può essere più utile di una conclusione generale.");
        saveGlobal(id, "FOCUSED", "Un ambito sociale emerge con chiarezza",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore libertà.",
                "Può essere utile osservare se la difficoltà riguarda soprattutto il giudizio, le conversazioni, la prestazione o il ciclo di anticipazione ed evitamento. Il risultato non stabilisce una diagnosi, ma può aiutarti a descrivere con maggiore precisione ciò che accade.");
        saveGlobal(id, "BROAD", "Ansia sociale presente in più ambiti",
                "Le risposte indicano paura, tensione o evitamento frequenti in diversi tipi di situazione sociale o valutativa.",
                "Se queste esperienze causano sofferenza o limitano relazioni, studio, lavoro e attività importanti, una valutazione con uno psicologo, psicoterapeuta o medico può chiarire il quadro e le possibili forme di aiuto. L'ansia sociale è trattabile, ma questo questionario resta informativo e non diagnostico.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Dinamiche narcisistiche percepite nella relazione di coppia",
                "Riflessione sulla relazione",
                "Osserva la tua percezione di reciprocità, gestione del confronto, confini e impatto emotivo nella relazione, senza diagnosticare il partner.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Descrive la tua percezione di alcune dinamiche nella relazione e non può stabilire se il partner abbia tratti narcisistici o un disturbo narcisistico di personalità. Una diagnosi richiede una valutazione clinica diretta, completa e condotta da un professionista qualificato; singoli comportamenti possono avere spiegazioni diverse. Rispondi pensando a episodi concreti e ricorrenti degli ultimi mesi, non a un singolo litigio. Il risultato serve a riflettere su reciprocità, rispetto e impatto della relazione, non a etichettare il partner. Comportamenti di controllo, umiliazione, minaccia o violenza vanno presi sul serio indipendentemente da qualsiasi diagnosi: se temi per la tua sicurezza, cerca supporto da una persona fidata o da servizi qualificati; in un'emergenza chiama il 112. Se sei una donna vittima di violenza o stalking, il 1522 offre gratuitamente ascolto e orientamento 24 ore su 24.",
                version, false,
                "Frequenza complessiva delle dinamiche osservate",
                "Frequenza delle dinamiche osservate",
                true, 10).withSeo(
                "Test partner narcisista: dinamiche di coppia | Spazio Test",
                "Questionario sulle dinamiche percepite nel partner: reciprocità, confronto, confini e impatto emotivo. 24 domande; non etichetta né diagnostica la persona.")
                .withResponseInstruction("Pensando a episodi concreti degli ultimi tre mesi, con quale frequenza è accaduto?"));

        saveReference(id, "Narcissistic Personality Disorder — Merck Manual Professional Edition",
                "https://www.merckmanuals.com/professional/psychiatric-disorders/personality-disorders/narcissistic-personality-disorder-npd", 1);
        saveReference(id, "What Is Narcissistic Personality Disorder? — American Psychiatric Association",
                "https://www.psychiatry.org/News-room/APA-Blogs/What-Is-Narcissistic-Personality-Disorder", 2);

        saveArea(id, "reciprocita", "Reciprocità, empatia e spazio emotivo", 1);
        saveArea(id, "centralita", "Centralità, ammirazione e aspettative", 2);
        saveArea(id, "confronto", "Confronto, critica e responsabilità", 3);
        saveArea(id, "confini", "Confini, controllo e impatto sulla relazione", 4);

        saveQuestions(id, List.of(
                q("reciprocita", "Quando racconto un problema, il mio partner riporta presto la conversazione su di sé o sui propri bisogni."),
                q("reciprocita", "Il mio partner svaluta ciò che provo quando le mie emozioni sono scomode per lui o lei."),
                q("reciprocita", "Il mio partner fatica a riconoscere l'effetto che le sue parole o azioni hanno su di me."),
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
                q("confronto", "Il mio partner fatica a scusarsi in modo concreto e a modificare il comportamento che mi ha ferito."),
                q("confronto", "Quando non sono d'accordo, il mio partner svaluta la mia competenza, sensibilità o credibilità invece di discutere il problema."),
                q("confronto", "Dopo un conflitto, mi viene richiesto di rassicurare o riavvicinare il mio partner prima che ci sia spazio per il mio punto di vista."),
                q("confini", "Il mio partner fa pressione perché io modifichi amicizie, attività, abitudini o scelte personali secondo le sue preferenze."),
                q("confini", "Il mio partner usa il senso di colpa per influenzare le mie decisioni."),
                q("confini", "Il mio partner passa dal valorizzarmi molto allo svalutarmi quando non soddisfo le sue aspettative."),
                q("confini", "Il mio partner oltrepassa limiti che ho espresso chiaramente, per esempio riguardo privacy, tempo, denaro, corpo o relazioni sociali."),
                q("confini", "Controllo attentamente ciò che dico o faccio per evitare reazioni sproporzionate del mio partner."),
                q("confini", "Questa relazione mi lascia frequentemente meno libero di riconoscere e proteggere i miei bisogni.")));

        saveGlobal(id, "LOW", "Poche dinamiche relazionali di questo tipo",
                "Nel complesso hai indicato una presenza contenuta delle dinamiche esplorate dal questionario.",
                "Ogni relazione attraversa momenti di squilibrio, difesa o scarsa sintonia. Le tue risposte suggeriscono che questi episodi non formano abitualmente un modello esteso. Il risultato non valuta direttamente il partner e non esclude problemi diversi da quelli considerati.");
        saveGlobal(id, "MIXED", "Alcune dinamiche emergono in determinati contesti",
                "Le risposte descrivono comportamenti variabili, più evidenti in alcune situazioni o fasi della relazione.",
                "Può essere utile osservare quando compaiono, quanto durano e se dopo un conflitto esistono ascolto, riparazione e cambiamenti concreti. La loro presenza non permette di attribuire un disturbo di personalità al partner.");
        saveGlobal(id, "FOCUSED", "Un'area relazionale richiede attenzione",
                "Una o due aree mostrano dinamiche frequenti, mentre negli altri aspetti la relazione sembra avere un funzionamento diverso.",
                "Concentrarti sui comportamenti specifici e sul loro effetto può aiutarti più di un'etichetta. Se la dinamica causa sofferenza, confusione o limita la tua autonomia, un confronto individuale con uno psicologo o psicoterapeuta può offrirti uno spazio protetto di valutazione.");
        saveGlobal(id, "BROAD", "Dinamiche problematiche presenti in più aree",
                "Le risposte indicano squilibri frequenti nella reciprocità, nel confronto o nel rispetto dei confini, con possibile impatto significativo sul tuo benessere.",
                "Questo profilo non dimostra che il partner abbia un disturbo narcisistico di personalità. Indica però che diverse dinamiche meritano attenzione per il loro effetto su di te. Cerca sostegno professionale e valuta la tua sicurezza senza attendere una diagnosi: in caso di pericolo immediato chiama il 112; per donne vittime di violenza o stalking è disponibile gratuitamente il 1522, anche via chat.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Ansia generalizzata",
                "Autovalutazione informativa",
                "Esplora preoccupazione difficile da controllare, tensione, affaticamento, sonno e interferenza nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato e non permette di stabilire la presenza di un disturbo d'ansia generalizzata. Preoccuparsi in alcuni periodi è una normale risposta alle difficoltà; una valutazione clinica considera invece durata, intensità, difficoltà di controllo, presenza in diversi ambiti e impatto sulla vita. Rispondi pensando alla frequenza delle esperienze negli ultimi sei mesi. Sintomi simili possono dipendere anche da stress, depressione, altri disturbi d'ansia, esperienze traumatiche, problemi del sonno, condizioni mediche, farmaci o sostanze. Se l'ansia limita la tua vita o i sintomi fisici sono nuovi, intensi o preoccupanti, parlane con uno psicologo, psicoterapeuta o medico qualificato.",
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
                q("attivazione", "Dopo una situazione stressante, il mio corpo rimane attivato a lungo anche quando il problema è terminato."),
                q("risorse", "Le preoccupazioni interrompono la concentrazione mentre lavoro, studio, leggo o seguo una conversazione."),
                q("risorse", "Quando devo risolvere un problema, la mente sembra bloccarsi."),
                q("risorse", "Mi sento affaticato anche dopo giornate che non richiederebbero uno sforzo eccezionale."),
                q("risorse", "Le normali richieste quotidiane mi sembrano più faticose perché una parte della mia attenzione resta assorbita dall'ansia."),
                q("risorse", "Quando sono preoccupato divento facilmente irritabile, impaziente o sensibile alle piccole difficoltà."),
                q("risorse", "Valutare tutti i possibili rischi rende difficile prendere una decisione."),
                q("impatto", "Faccio fatica ad addormentarmi perché la mente continua a esaminare problemi o scenari futuri."),
                q("impatto", "Mi sveglio durante la notte con pensieri di preoccupazione che rendono difficile riprendere sonno."),
                q("impatto", "Al risveglio mi sento poco riposato perché tensione o pensieri hanno disturbato il sonno."),
                q("impatto", "Rimando decisioni perché l'incertezza mi fa temere conseguenze negative."),
                q("impatto", "La preoccupazione interferisce con lavoro, studio, relazioni, cura di me o gestione delle attività quotidiane."),
                q("impatto", "Fatico a godermi un momento positivo o a essere presente perché sto già pensando a ciò che potrebbe andare storto.")));

        saveGlobal(id, "LOW", "Preoccupazione generalmente circoscritta",
                "Nel complesso hai indicato una presenza contenuta delle esperienze di ansia esplorate.",
                "Questo non significa essere sempre tranquilli: preoccupazione e tensione possono aumentare in periodi impegnativi. Le risposte suggeriscono però che tendono a restare circoscritte e a non interferire stabilmente in più aree della vita.");
        saveGlobal(id, "MIXED", "Un'ansia che varia con periodi e situazioni",
                "Le tue risposte descrivono esperienze variabili, più evidenti in alcuni momenti, ambiti o condizioni di stress.",
                "Può essere utile osservare se l'ansia diminuisce quando il problema concreto si risolve oppure continua a spostarsi tra temi diversi. Durata, contesto e impatto sono più informativi di una semplice media e questo risultato non costituisce una diagnosi.");
        saveGlobal(id, "FOCUSED", "Un'area concentra maggiormente la tensione",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio.",
                "L'analisi specifica può aiutarti a riconoscere se emergono soprattutto preoccupazione difficile da controllare, attivazione fisica, affaticamento cognitivo oppure sonno e funzionamento quotidiano. Se la difficoltà persiste o causa sofferenza, puoi confrontarti con un professionista qualificato.");
        saveGlobal(id, "BROAD", "Ansia frequente in più aspetti della vita",
                "Le risposte indicano preoccupazione, tensione o conseguenze frequenti in diverse aree esplorate dal questionario.",
                "Se queste esperienze durano da mesi e limitano sonno, relazioni, studio, lavoro o attività importanti, una valutazione con uno psicologo, psicoterapeuta o medico può chiarire il quadro, escludere altre cause e individuare forme di aiuto efficaci. L'ansia è trattabile, ma questo questionario resta informativo e non diagnostico.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Umore depresso e sintomi depressivi",
                "Autovalutazione informativa",
                "Esplora umore, perdita di interesse, energia, pensieri su di sé e funzionamento quotidiano nelle ultime due settimane.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato e non permette di stabilire la presenza di un disturbo depressivo. Tristezza, stanchezza e calo della motivazione possono comparire durante stress, lutti o cambiamenti importanti; una valutazione clinica considera durata, intensità, storia personale, funzionamento, eventuali periodi di umore insolitamente elevato e possibili cause mediche, farmacologiche o legate a sostanze. Rispondi pensando alla frequenza delle esperienze nelle ultime due settimane. Questo questionario non valuta il rischio suicidario: se stai pensando di farti del male o non ti senti al sicuro, non attendere il risultato; chiama subito il 112 o vai al Pronto Soccorso. Se le difficoltà persistono o interferiscono con la tua vita, puoi parlarne con uno psicologo, psicoterapeuta o medico qualificato.",
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
                q("energia", "Faccio fatica a iniziare azioni di cura personale, domestiche o lavorative anche quando so che sono necessarie."),
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
                q("funzionamento", "Anche decisioni semplici diventano difficili da prendere."),
                q("funzionamento", "Il mio stato emotivo interferisce con lavoro, studio, relazioni, cura personale o gestione della vita quotidiana.")));

        saveGlobal(id, "LOW", "Umore generalmente preservato",
                "Nel complesso hai indicato una presenza contenuta delle esperienze legate all'umore depresso esplorate.",
                "Questo non esclude giornate difficili, tristezza o stanchezza, ma suggerisce che al momento non formano un quadro frequente e diffuso nelle aree considerate. Il risultato resta informativo. Questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");
        saveGlobal(id, "MIXED", "Un calo dell'umore legato ad alcuni periodi o contesti",
                "Le risposte descrivono esperienze variabili, più evidenti in determinati momenti o aspetti della vita.",
                "Può essere utile osservare durata, eventi recenti, risorse disponibili e capacità di recuperare quando il contesto cambia. Una media non permette di distinguere stress, lutto, problemi fisici o un disturbo dell'umore. Questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");
        saveGlobal(id, "FOCUSED", "Un'area del benessere emotivo emerge con chiarezza",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio.",
                "L'analisi specifica può aiutarti a descrivere se la difficoltà riguarda soprattutto piacere e partecipazione, energia, pensieri su di te oppure funzionamento quotidiano. Se persiste o causa sofferenza, parlane con un professionista qualificato. Questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");
        saveGlobal(id, "BROAD", "Umore depresso presente in più aree",
                "Le risposte indicano esperienze frequenti che coinvolgono diversi aspetti dell'umore, delle energie, dei pensieri o del funzionamento.",
                "Se queste difficoltà sono presenti per gran parte dei giorni e limitano la tua vita, una valutazione con uno psicologo, psicoterapeuta o medico può chiarire il quadro, considerare altre possibili cause e individuare un aiuto adeguato. I problemi depressivi sono trattabili e chiedere sostegno è appropriato. Questo questionario non valuta il rischio suicidario: se pensi di farti del male o non ti senti al sicuro, chiama subito il 112 o vai al Pronto Soccorso.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "People pleasing e bisogno di approvazione",
                "Autovalutazione informativa",
                "Esplora bisogno di approvazione, difficoltà a dire di no, paura del conflitto e spazio riservato ai propri bisogni.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. “People pleaser” è un'espressione comune, non una diagnosi o una categoria clinica. Gentilezza, collaborazione e disponibilità non sono di per sé problematiche: il questionario esplora quanto spesso l'attenzione agli altri comporti silenziare bisogni, superare limiti o dipendere dalla loro approvazione. Rispondi pensando agli ultimi mesi e a relazioni diverse. Cultura, ruoli di cura, ambiente, dipendenza economica e differenze di potere possono influenzare le risposte. In una relazione minacciosa o poco sicura, adattarsi ed evitare il conflitto può essere una strategia protettiva, non una mancanza di assertività. Se questi schemi causano sofferenza, risentimento, esaurimento o relazioni sbilanciate, puoi parlarne con uno psicologo o psicoterapeuta qualificato.",
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
                q("silenzio", "Adatto molto il mio comportamento a ciò che penso gli altri preferiscano."),
                q("silenzio", "Rinuncio a chiedere aiuto o sostegno perché non voglio pesare sugli altri."),
                q("silenzio", "Mostro accordo anche quando penso qualcosa di diverso."),
                q("silenzio", "Dopo un conflitto mi concentro sul ristabilire subito l'armonia prima che il mio problema sia stato davvero ascoltato."),
                q("sacrificio", "Mi sento responsabile di migliorare l'umore delle persone a cui tengo."),
                q("sacrificio", "Cerco di anticipare i bisogni degli altri senza aspettare che vengano espressi, per evitare loro qualsiasi difficoltà."),
                q("sacrificio", "Continuo ad aiutare anche quando sono stanco, sovraccarico o avrei bisogno di fermarmi."),
                q("sacrificio", "Rimando la cura personale per occuparmi delle priorità altrui."),
                q("sacrificio", "Mi sento particolarmente utile o degno di affetto quando qualcuno ha bisogno di me."),
                q("sacrificio", "Dopo essermi reso molto disponibile, provo risentimento o esaurimento perché i miei bisogni sono rimasti in secondo piano.")));

        saveGlobal(id, "LOW", "Disponibilità e bisogni personali in buon equilibrio",
                "Nel complesso hai indicato una presenza contenuta delle dinamiche di compiacenza esplorate.",
                "Puoi essere attento e disponibile senza rinunciare abitualmente alla tua voce, ai tuoi limiti o al recupero. Questo non significa essere sempre assertivi né dover rifiutare l'aiuto agli altri: reciprocità, contesto e libertà di scelta restano centrali.");
        saveGlobal(id, "MIXED", "La compiacenza emerge in alcuni contesti",
                "Le risposte descrivono dinamiche variabili, più frequenti con alcune persone, richieste o situazioni.",
                "Potresti mantenere un buon equilibrio in molti rapporti ma adattarti maggiormente davanti a autorità, conflitto, bisogno altrui o rischio di disapprovazione. Riconoscere dove accade può essere più utile dell'etichetta “people pleaser”.");
        saveGlobal(id, "FOCUSED", "Un meccanismo di compiacenza emerge con chiarezza",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore libertà.",
                "Può essere utile osservare se il costo nasce soprattutto dalla ricerca di approvazione, dai limiti, dal silenziamento o dall'eccessiva responsabilità verso gli altri. Il profilo descrive abitudini modificabili, non un'identità fissa.");
        saveGlobal(id, "BROAD", "I bisogni altrui occupano spesso il primo posto",
                "Le risposte indicano dinamiche frequenti in più aree, con possibile difficoltà a mantenere visibili bisogni, opinioni, limiti e risorse personali.",
                "Se questo modo di stare nelle relazioni porta esaurimento, risentimento, ansia o legami poco reciproci, un confronto con uno psicologo o psicoterapeuta può aiutarti a costruire maggiore scelta e confini sostenibili. In situazioni minacciose o con forte squilibrio di potere, valuta prima la sicurezza e cerca un supporto adeguato; il risultato resta informativo e non diagnostico.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Sindrome dell'impostore",
                "Autovalutazione informativa",
                "Esplora difficoltà a riconoscere i successi, dubbi sulla propria competenza, perfezionismo e paura di essere smascherati.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. L'espressione comune “sindrome dell'impostore” indica ciò che nella letteratura scientifica è spesso chiamato fenomeno dell'impostore: non è una diagnosi né un disturbo riconosciuto e questo questionario non è uno strumento clinico o validato. Rispondi pensando agli ultimi mesi e ai contesti di studio, lavoro o responsabilità per te significativi. Il risultato descrive la frequenza di alcune esperienze soggettive, non misura la tua competenza reale. Dubbi e bisogno di preparazione possono essere appropriati quando un ruolo è nuovo, mancano conoscenze o le aspettative non sono chiare. Esclusione, discriminazione, scarsa rappresentazione, feedback ambigui e ambienti molto competitivi possono inoltre creare o amplificare il senso di non appartenenza: non tutto va attribuito alla persona. Se questi vissuti limitano opportunità, riposo o benessere, puoi parlarne con uno psicologo o psicoterapeuta qualificato.",
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
                q("esposizione", "Ho la sensazione di aver dato un'immagine eccessiva delle mie capacità, anche senza aver cercato di ingannare nessuno."),
                q("esposizione", "Davanti a una nuova responsabilità mi chiedo se merito davvero il ruolo o l'opportunità ricevuta."),
                q("esposizione", "Evito di fare domande o chiedere chiarimenti per paura di apparire incompetente."),
                q("esposizione", "Interpreto una normale incertezza come prova del fatto che non dovrei trovarmi in quel contesto."),
                q("esposizione", "Mi sento fuori posto tra persone che considero competenti, anche quando ho qualifiche o risultati confrontabili."),
                q("prestazione", "Mi preparo molto oltre ciò che il compito richiede per ridurre il rischio che emergano mie presunte mancanze."),
                q("prestazione", "Rimando l'inizio o la consegna di un lavoro perché temo che il risultato non sia abbastanza buono."),
                q("prestazione", "Un piccolo errore mi sembra una prova generale della mia scarsa competenza."),
                q("prestazione", "Fatico a considerare concluso un compito perché continuo a controllarlo o perfezionarlo."),
                q("prestazione", "Solo un risultato quasi impeccabile mi sembra una conferma sufficiente di essere all'altezza."),
                q("prestazione", "Quando la mia prestazione sarà visibile agli altri, tendo a sovraccaricarmi di lavoro."),
                q("impatto", "Confronto i miei dubbi interiori con la sicurezza che gli altri mostrano all'esterno."),
                q("impatto", "Svaluto i feedback positivi e resto concentrato soprattutto su quelli negativi o ambigui."),
                q("impatto", "Rinuncio a candidarmi a un'opportunità finché non mi sento completamente pronto."),
                q("impatto", "Quando devo presentare le mie capacità o i miei risultati, tendo a minimizzarli."),
                q("impatto", "Un successo aumenta soprattutto la pressione a mantenere le aspettative, invece della soddisfazione."),
                q("impatto", "I dubbi sul mio valore mi portano a partecipare meno alle attività che contano per me.")));

        saveGlobal(id, "LOW", "Successi e competenze generalmente riconosciuti",
                "Nel complesso hai indicato una presenza contenuta delle esperienze associate al fenomeno dell'impostore.",
                "Sembri riuscire generalmente a integrare risultati e feedback nella valutazione delle tue capacità, mantenendo i dubbi in proporzione al contesto. Questo non dimostra una competenza assoluta né esclude incertezze normali, soprattutto quando stai imparando o affrontando un ruolo nuovo.");
        saveGlobal(id, "MIXED", "Dubbi che emergono in alcuni contesti",
                "Le risposte descrivono esperienze variabili, più frequenti davanti ad alcune persone, compiti o passaggi di ruolo.",
                "Potresti riconoscere le tue capacità in molte situazioni ma dubitarne quando aumentano visibilità, confronto, novità o ambiguità del feedback. Distinguere i contesti può aiutare a separare ciò che richiede apprendimento da ciò che nasce da standard rigidi, scarsa appartenenza o difficoltà a interiorizzare i risultati.");
        saveGlobal(id, "FOCUSED", "Un meccanismo dell'impostore emerge con chiarezza",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio.",
                "Il profilo invita a osservare dove si concentra il costo: attribuzione dei successi, paura di essere smascherati, pressione da prestazione oppure rinuncia a opportunità. Il risultato non misura la competenza effettiva e va letto insieme a esperienza reale, qualità dei feedback e caratteristiche dell'ambiente.");
        saveGlobal(id, "BROAD", "Il vissuto dell'impostore è presente in più aree",
                "Le risposte indicano esperienze frequenti e diffuse nel modo di leggere risultati, competenza, prestazione e opportunità.",
                "Questo schema può rendere difficile riconoscere prove di capacità, accettare una normale quota di apprendimento e partecipare senza sovraccaricarti. Se alimenta ansia, evitamento, esaurimento o rinunce importanti, un confronto professionale può aiutarti. Considera anche fattori reali come aspettative poco chiare, feedback inadeguati, esclusione o discriminazione: il risultato resta informativo, non diagnostico e non stabilisce quanto sei competente.");

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
        String version = "1.2";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Autosabotaggio e ostacoli agli obiettivi",
                "Autovalutazione informativa",
                "Esplora procrastinazione, evitamento e altri ostacoli ricorrenti tra intenzioni, scelte e obiettivi personalmente importanti.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. “Autosabotaggio” è un'espressione comune, non una diagnosi né un singolo costrutto clinico: qui indica schemi ricorrenti che possono aumentare ostacoli o costi rispetto a obiettivi personalmente importanti. Non implica che tali comportamenti siano deliberati e il questionario non misura volontà, disciplina o valore personale. Rispondi pensando agli ultimi mesi e a obiettivi che hai scelto davvero tu. Rimandare, ridurre l'impegno o abbandonare può essere adattivo quando un obiettivo è irrealistico, non più significativo, imposto, poco sicuro o incompatibile con le risorse disponibili. Carichi di cura, difficoltà economiche o ambientali, problemi di salute, stress, sonno insufficiente, ansia, umore depresso, trauma, ADHD o altre difficoltà esecutive possono produrre esperienze simili e richiedono una lettura distinta, non moralistica. Se questi schemi causano sofferenza o compromettono ripetutamente benessere, relazioni, studio o lavoro, puoi parlarne con uno psicologo o psicoterapeuta qualificato.",
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
                q("azione", "Mantengo gli obiettivi vaghi, senza tradurli in passi o tempi concreti, anche quando così è più difficile realizzarli."),
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
                q("emozioni", "Scelgo un sollievo immediato anche quando prevedo che renderà più difficile ciò che conta per me in seguito."),
                q("emozioni", "Dopo un errore, vergogna o autocritica occupano lo spazio che potrei usare per correggere o riprovare."),
                q("emozioni", "Rimando conversazioni scomode finché le conseguenze diventano più difficili da gestire."),
                q("emozioni", "Più un obiettivo è importante per me, più le emozioni che suscita mi portano a evitarlo."),
                q("emozioni", "Quando sono sotto pressione trascuro il riposo, anche se questo peggiora la mia capacità di proseguire."),
                q("direzione", "Inizio cambiamenti molto ambiziosi con grande slancio e li abbandono quando non riesco a sostenerne il ritmo."),
                q("direzione", "Un'interruzione o un passo mancato diventa per me un motivo per considerare compromesso l'intero percorso."),
                q("direzione", "Continuo a usare una strategia che non funziona invece di modificarla."),
                q("direzione", "Abbandono obiettivi ancora realistici prima di aver dedicato loro un impegno abbastanza regolare da valutarli."),
                q("direzione", "Ripeto scelte che entrano prevedibilmente in conflitto con le mie priorità, anche dopo aver riconosciuto lo schema."),
                q("direzione", "Dopo una pausa faccio fatica a riprendere anche con un passo più piccolo.")));

        saveGlobal(id, "LOW", "Scelte generalmente coerenti con i tuoi obiettivi",
                "Nel complesso hai indicato una presenza contenuta degli ostacoli autoalimentati esplorati dal questionario.",
                "Sembri generalmente capace di trasformare intenzioni in passi sostenibili, attraversare il disagio e adattare il percorso quando serve. Questo non significa essere sempre produttivo: riposo, cambi di priorità e abbandono di obiettivi non più realistici possono essere forme sane di autoregolazione.");
        saveGlobal(id, "MIXED", "Gli ostacoli emergono in alcune situazioni",
                "Le risposte descrivono schemi variabili, più frequenti con alcuni obiettivi, emozioni o condizioni.",
                "Potresti procedere con continuità in molti ambiti ma bloccarti quando aumentano pressione, giudizio, incertezza o fatica. Individuare quando e a quale costo accade è più utile dell'etichetta “autosabotaggio”, perché lo stesso comportamento può avere funzioni e cause differenti.");
        saveGlobal(id, "FOCUSED", "Un meccanismo di autosabotaggio emerge con chiarezza",
                "Una o due aree risultano particolarmente frequenti, mentre negli altri aspetti sembra esserci maggiore equilibrio.",
                "Il profilo suggerisce di osservare il meccanismo prevalente: difficoltà ad avviare, protezione dal giudizio, ricerca di sollievo emotivo oppure scarsa flessibilità nel mantenere il percorso. Non attribuisce intenzioni e va letto insieme a risorse disponibili, salute, carico reale e caratteristiche dell'ambiente.");
        saveGlobal(id, "BROAD", "Più meccanismi ostacolano i tuoi obiettivi",
                "Le risposte indicano schemi frequenti in più aree, con una distanza ricorrente tra ciò che per te conta e le azioni che riesci a sostenere.",
                "Procrastinazione, protezione dal giudizio, sollievo immediato e difficoltà a riprendere possono rinforzarsi a vicenda senza essere scelte consapevoli. Se il costo riguarda più ambiti o genera forte autocritica, un confronto professionale può aiutare a comprenderne funzione e contesto. Il risultato resta informativo e non diagnostico: non distingue da solo abitudini modificabili, ostacoli esterni, condizioni cliniche o difficoltà esecutive e non misura la tua forza di volontà.");

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
    private QuestionSeed q(String areaCode, String text) { return new QuestionSeed(areaCode, text); }
    private void saveQuestions(String testId, List<QuestionSeed> questions) {
        Map<String, List<QuestionSeed>> questionsByArea = questions.stream()
                .collect(Collectors.groupingBy(QuestionSeed::areaCode, LinkedHashMap::new, Collectors.toList()));
        int longestArea = questionsByArea.values().stream().mapToInt(List::size).max().orElse(0);
        int position = 1;
        for (int offset = 0; offset < longestArea; offset++) {
            for (List<QuestionSeed> areaQuestions : questionsByArea.values()) {
                if (offset < areaQuestions.size()) {
                    QuestionSeed question = areaQuestions.get(offset);
                    questionRepository.save(new TestQuestionEntity(testId, question.areaCode(), position++, question.text()));
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

    private record QuestionSeed(String areaCode, String text) {
    }
}
