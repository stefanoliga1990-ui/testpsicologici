package com.example.testpsicologici.config;

import com.example.testpsicologici.persistence.InterpretationEntity;
import com.example.testpsicologici.persistence.InterpretationRepository;
import com.example.testpsicologici.persistence.TestAreaEntity;
import com.example.testpsicologici.persistence.TestAreaRepository;
import com.example.testpsicologici.persistence.TestDefinitionEntity;
import com.example.testpsicologici.persistence.TestDefinitionRepository;
import com.example.testpsicologici.persistence.TestQuestionEntity;
import com.example.testpsicologici.persistence.TestQuestionRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ContentDataInitializer implements ApplicationRunner {

    private final TestDefinitionRepository testRepository;
    private final TestAreaRepository areaRepository;
    private final TestQuestionRepository questionRepository;
    private final InterpretationRepository interpretationRepository;

    public ContentDataInitializer(TestDefinitionRepository testRepository, TestAreaRepository areaRepository,
                                  TestQuestionRepository questionRepository,
                                  InterpretationRepository interpretationRepository) {
        this.testRepository = testRepository;
        this.areaRepository = areaRepository;
        this.questionRepository = questionRepository;
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
    }

    private void seedAutismInformationTest() {
        String id = "tratti-autistici-adulti";
        String version = "2.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Tratti autistici nell'adulto",
                "Autovalutazione informativa",
                "Un questionario per riflettere su esperienze legate alla comunicazione, alle relazioni, alla prevedibilità e alla sensibilità agli stimoli.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato: le esperienze descritte possono avere molte spiegazioni diverse. Per una valutazione dell'autismo è necessario rivolgersi a professionisti qualificati.",
                version, false, true, 1));

        saveArea(id, "sociale", "Interazione sociale e reciprocità emotiva", 1);
        saveArea(id, "non_verbale", "Comunicazione non verbale e comprensione implicita", 2);
        saveArea(id, "routine", "Routine, flessibilità e comportamenti ripetitivi", 3);
        saveArea(id, "sensoriale", "Interessi focalizzati e sensibilità sensoriale", 4);

        saveQuestions(id, List.of(
                q("sociale", "Nelle conversazioni di gruppo mi è difficile capire quando è il momento di parlare."),
                q("sociale", "Dopo un incontro sociale ripenso a ciò che ho detto per capire se mi sono comportato come previsto."),
                q("sociale", "Trovo faticoso mantenere una relazione quando le aspettative reciproche non vengono espresse chiaramente."),
                q("sociale", "Quando qualcuno condivide un'emozione, non sempre capisco quale risposta si aspetta da me."),
                q("sociale", "Le interazioni sociali prolungate mi richiedono uno sforzo consapevole o mi lasciano molto affaticato."),
                q("sociale", "Quando una conversazione cambia rapidamente tono o argomento, mi è difficile adattare il mio modo di partecipare."),
                q("non_verbale", "Mi è difficile capire cosa prova una persona basandomi soltanto sull'espressione del viso o sul tono di voce."),
                q("non_verbale", "Durante una conversazione devo pensare consapevolmente a quanto contatto visivo mantenere."),
                q("non_verbale", "Posso interpretare in modo letterale battute, allusioni o richieste formulate indirettamente."),
                q("non_verbale", "Preparo mentalmente parole, espressioni o tono di voce prima di affrontare alcune situazioni sociali."),
                q("non_verbale", "Gesti, posture o regole sociali non dette possono risultarmi difficili da interpretare."),
                q("non_verbale", "Mi capita di non accorgermi che qualcuno vuole concludere o cambiare una conversazione finché non lo dice chiaramente."),
                q("routine", "Un cambiamento imprevisto nei programmi può provocarmi forte disagio o richiedermi tempo per adattarmi."),
                q("routine", "Preferisco svolgere alcune attività seguendo ogni volta lo stesso ordine o la stessa procedura."),
                q("routine", "Passare rapidamente da un'attività a un'altra può essere difficile, anche quando so cosa devo fare."),
                q("routine", "Ripetere movimenti, parole, suoni o piccoli gesti mi aiuta a concentrarmi, calmarmi o regolare l'energia."),
                q("routine", "Affronto meglio una situazione nuova quando posso conoscerne in anticipo dettagli e passaggi."),
                q("routine", "Se non posso completare un'attività nel modo che avevo previsto, faccio fatica a lasciarla e passare oltre."),
                q("sensoriale", "Posso concentrarmi così intensamente su un interesse da perdere la percezione del tempo o trascurare altre attività."),
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
        String version = "2.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Tratti associati all'ADHD nell'adulto",
                "Autovalutazione informativa",
                "Un questionario per riflettere su attenzione, organizzazione, impulsività e irrequietezza nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato: difficoltà simili possono dipendere da stress, sonno, ansia, depressione, altre condizioni o circostanze personali. Una diagnosi di ADHD richiede una valutazione specialistica della storia dello sviluppo, dell'impatto quotidiano e della presenza delle difficoltà in più contesti.",
                version, false, true, 2));

        saveArea(id, "attenzione", "Attenzione sostenuta e distraibilità", 1);
        saveArea(id, "organizzazione", "Organizzazione, memoria operativa e gestione del tempo", 2);
        saveArea(id, "impulsivita", "Impulsività e controllo della risposta", 3);
        saveArea(id, "irrequietezza", "Irrequietezza e bisogno di stimolazione", 4);

        saveQuestions(id, List.of(
                q("attenzione", "Durante una lettura o una conversazione, la mia mente si sposta altrove anche quando cerco di restare concentrato."),
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
                q("impulsivita", "Prendo decisioni, faccio acquisti o accetto impegni rapidamente, valutando le conseguenze solo dopo."),
                q("impulsivita", "Aspettare il mio turno in una conversazione, una fila o un'attività mi richiede molto autocontrollo."),
                q("impulsivita", "Dico o faccio qualcosa nell'immediato e poco dopo vorrei averci pensato più a lungo."),
                q("impulsivita", "Abbandono un piano per seguire un'idea o un impulso appena comparso, anche se avevo altre priorità."),
                q("impulsivita", "Nelle discussioni reagisco prima di aver compreso fino in fondo ciò che l'altra persona intende."),
                q("irrequietezza", "Quando devo restare fermo a lungo avverto un'irrequietezza interna difficile da ignorare."),
                q("irrequietezza", "Muovo mani o piedi, cambio spesso posizione o cerco occasioni per alzarmi."),
                q("irrequietezza", "Mi è difficile riposare senza svolgere, controllare o pianificare contemporaneamente qualcos'altro."),
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
        String version = "1.0";
        if (!requiresSeed(id, version)) return;
        removeTest(id);

        saveTest(new TestDefinitionEntity(
                id,
                "Tratti ossessivo-compulsivi",
                "Autovalutazione informativa",
                "Un questionario per riflettere su pensieri intrusivi, bisogno di certezza, controlli e rituali nella vita quotidiana.",
                "6 min · 24 domande",
                "Questo questionario è rivolto ad adulti e ha finalità esclusivamente informative. Non è uno strumento diagnostico o clinicamente validato: pensieri indesiderati, dubbi e abitudini ripetitive possono comparire in molte persone e non indicano l'intenzione di agire su un pensiero. Una valutazione del disturbo ossessivo-compulsivo considera anche disagio, tempo occupato, interferenza con la vita quotidiana e possibili spiegazioni alternative.",
                version, false, true, 3));

        saveArea(id, "intrusioni", "Pensieri intrusivi, dubbio e bisogno di certezza", 1);
        saveArea(id, "contaminazione", "Contaminazione, pulizia ed evitamento", 2);
        saveArea(id, "controllo", "Controllo, responsabilità e prevenzione del danno", 3);
        saveArea(id, "rituali", "Ordine, simmetria, ripetizione e rituali mentali", 4);

        saveQuestions(id, List.of(
                q("intrusioni", "Pensieri, immagini o impulsi indesiderati tornano nella mia mente anche quando cerco di lasciarli andare."),
                q("intrusioni", "Dubito di aver capito, ricordato o fatto qualcosa correttamente anche dopo averlo verificato."),
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
                q("controllo", "Controllo più volte porte, elettrodomestici, messaggi o attività anche quando so di averlo già fatto."),
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

    private boolean requiresSeed(String testId, String version) {
        return testRepository.findById(testId)
                .map(test -> !version.equals(test.getVersion()))
                .orElse(true);
    }

    private void removeTest(String testId) {
        if (!testRepository.existsById(testId)) return;
        interpretationRepository.deleteByTestId(testId);
        questionRepository.deleteByTestId(testId);
        areaRepository.deleteByTestId(testId);
        testRepository.deleteById(testId);
        testRepository.flush();
    }

    private void saveTest(TestDefinitionEntity test) { testRepository.save(test); }
    private void saveArea(String testId, String code, String name, int order) { areaRepository.save(new TestAreaEntity(testId, code, name, order)); }
    private QuestionSeed q(String areaCode, String text) { return new QuestionSeed(areaCode, text); }
    private void saveQuestions(String testId, List<QuestionSeed> questions) {
        for (int index = 0; index < questions.size(); index++) {
            QuestionSeed question = questions.get(index);
            questionRepository.save(new TestQuestionEntity(testId, question.areaCode(), index + 1, question.text()));
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
