import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import ReferenceList from '../components/ReferenceList';

function SourceGroup({ eyebrow, title, href, references }) {
  return (
    <article className="source-test-group">
      <div className="source-test-heading">
        <div><p className="eyebrow">{eyebrow}</p><h3>{title}</h3></div>
        <a href={href}>Vai {eyebrow === 'Questionario' ? 'al test' : 'alla guida'} <span aria-hidden="true">→</span></a>
      </div>
      <ReferenceList references={references} />
    </article>
  );
}

export default function MethodPage({ guides, tests }) {
  return (
    <main className="editorial-shell">
      <Navbar />
      <header className="editorial-hero">
        <p className="eyebrow">Trasparenza editoriale</p><h1>Metodo e fonti</h1>
        <p>Spazio Test realizza questionari informativi originali e guide dedicate all'auto-osservazione e al benessere psicologico. Per definire i temi, le aree e il linguaggio vengono consultate fonti istituzionali, linee guida e pubblicazioni scientifiche pertinenti.</p>
      </header>
      <section className="editorial-process" aria-labelledby="come-nasce">
        <div className="editorial-section-heading"><p className="eyebrow">Il processo</p><h2 id="come-nasce">Come nasce un contenuto</h2></div>
        <ol className="process-grid">
          <li><span>01</span><h3>Consultazione</h3><p>Si cercano prima fonti scientifiche o istituzionali italiane, poi studi europei e le migliori sintesi internazionali pertinenti.</p></li>
          <li><span>02</span><h3>Struttura</h3><p>I concetti ricorrenti vengono organizzati in aree o sezioni chiare, evitando di trasformare singole esperienze in etichette.</p></li>
          <li><span>03</span><h3>Scrittura</h3><p>Domande e guide sono scritte in forma originale, con un linguaggio accessibile, specifico e non giudicante.</p></li>
          <li><span>04</span><h3>Limiti</h3><p>Ogni contenuto distingue informazione, auto-osservazione e valutazione clinica e collega direttamente i riferimenti consultati.</p></li>
        </ol>
      </section>
      <section className="editorial-principles">
        <article><p className="eyebrow">Selezione delle fonti</p><h2>Riferimenti tracciabili e pertinenti</h2><p>La priorità va a fonti scientifiche o istituzionali italiane, studi europei, linee guida, revisioni sistematiche e meta-analisi. Si preferiscono collegamenti primari tramite ente produttore, rivista, DOI, PubMed o università; la divulgazione resta un supporto secondario.</p></article>
        <article><p className="eyebrow">Interpretazione</p><h2>Una lettura orientativa</h2><p>Le barre e le soglie servono a organizzare le risposte in una restituzione comprensibile. Non rappresentano percentili, probabilità diagnostiche, misure di gravità clinica o confronti con una popolazione di riferimento.</p></article>
        <article><p className="eyebrow">Limiti</p><h2>Contesto italiano senza trasferimenti automatici</h2><p>Una validazione italiana di uno strumento citato non valida i questionari originali di Spazio Test. Norme, prevalenze e cut-off non vengono trasferiti da altri strumenti, Paesi o campioni; quando mancano evidenze italiane solide, il limite viene dichiarato.</p></article>
      </section>
      <section className="source-library" aria-labelledby="fonti-per-test">
        <div className="editorial-section-heading source-library-heading"><p className="eyebrow">Bibliografia consultabile</p><h2 id="fonti-per-test">Fonti per ogni questionario</h2><p>Ogni riferimento è accompagnato da una breve indicazione del contributo dato alla costruzione editoriale del questionario.</p></div>
        <div className="source-test-list">{tests.map((test) => <SourceGroup eyebrow="Questionario" title={test.title} href={`/test/${test.id}`} references={test.references} key={test.id} />)}</div>
      </section>
      {guides.length > 0 && (
        <section className="source-library guide-source-library" aria-labelledby="fonti-guide">
          <div className="editorial-section-heading source-library-heading"><p className="eyebrow">Fonti degli approfondimenti</p><h2 id="fonti-guide">Riferimenti delle guide</h2><p>Le fonti delle guide servono a spiegare l'argomento e restano distinte da quelle impiegate per costruire domande e restituzioni dei questionari.</p></div>
          <div className="source-test-list">{guides.map((guide) => <SourceGroup eyebrow="Approfondimento" title={guide.cardTitle} href={`/approfondimenti/${guide.slug}`} references={guide.references} key={guide.slug} />)}</div>
        </section>
      )}
      <Footer />
    </main>
  );
}
