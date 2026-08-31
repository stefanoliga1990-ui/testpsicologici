import { useRef } from 'react';
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

function ReviewerSection({ reviewer }) {
  const dialogRef = useRef(null);

  return (
    <>
      <section className="reviewer-section" aria-labelledby="revisione-professionale">
        <div className="editorial-section-heading reviewer-section-heading">
          <p className="eyebrow">Autorialità e competenze</p>
          <h2 id="revisione-professionale">Revisione professionale dei questionari</h2>
          <p>Per ogni nuovo questionario, la revisione professionale accompagna la definizione delle linee guida e il controllo di struttura, linguaggio, limiti e modalità di restituzione prima della pubblicazione.</p>
        </div>
        <article className="reviewer-card" itemScope itemType="https://schema.org/Person">
          <div className="reviewer-photo-frame">
            <img className="reviewer-photo" src="/images/alessia-liga-revisore-professionale.jpg" alt="Ritratto di Alessia Liga, revisore professionale di Spazio Test" width="400" height="400" loading="lazy" decoding="async" itemProp="image" />
          </div>
          <div className="reviewer-card-content">
            <h3>Il Revisore Professionale</h3>
            <p className="reviewer-role"><span itemProp="name">{reviewer.name}</span> · <span itemProp="jobTitle">{reviewer.role}</span></p>
            <p className="reviewer-biography" itemProp="description">{reviewer.biography}</p>
            <button className="button button-secondary reviewer-contact-button" type="button" onClick={() => dialogRef.current?.showModal()}>Contatti</button>
          </div>
        </article>
        <p className="reviewer-scope-note"><strong>Ambito della revisione.</strong> La revisione professionale migliora la qualità editoriale e la chiarezza dei contenuti, ma non equivale a validazione psicometrica, definizione di norme o valutazione clinica individuale.</p>
      </section>
      <dialog className="reviewer-contact-dialog" ref={dialogRef} aria-labelledby="reviewer-contact-title">
        <form method="dialog">
          <button className="reviewer-dialog-close" type="submit" aria-label="Chiudi contatti"><span aria-hidden="true">×</span></button>
        </form>
        <p className="eyebrow">Contatti professionali</p>
        <h2 id="reviewer-contact-title">Contatti di {reviewer.name}</h2>
        <address className="reviewer-contact-list">
          <p><span>Nome</span><strong>{reviewer.name}</strong></p>
          <p><span>Email</span><a href={`mailto:${reviewer.email}`}>{reviewer.email}</a></p>
          <p><span>Telefono</span><a href={`tel:${reviewer.phoneHref}`}>{reviewer.phoneDisplay}</a></p>
        </address>
      </dialog>
    </>
  );
}

export default function MethodPage({ guides, reviewer, tests }) {
  return (
    <main className="editorial-shell">
      <Navbar />
      <header className="editorial-hero">
        <p className="eyebrow">Trasparenza editoriale</p><h1>Metodo e fonti</h1>
        <p>Spazio Test realizza questionari informativi originali e guide dedicate all'auto-osservazione e al benessere psicologico. Il processo combina fonti istituzionali e scientifiche pertinenti, scrittura originale, limiti espliciti e revisione professionale dei nuovi questionari.</p>
      </header>
      <section className="editorial-process" aria-labelledby="come-nasce">
        <div className="editorial-section-heading"><p className="eyebrow">Il processo</p><h2 id="come-nasce">Come nasce un contenuto</h2></div>
        <ol className="process-grid">
          <li><span>01</span><h3>Consultazione</h3><p>Si cercano prima fonti scientifiche o istituzionali italiane, poi studi europei e le migliori sintesi internazionali pertinenti.</p></li>
          <li><span>02</span><h3>Struttura</h3><p>I concetti ricorrenti vengono organizzati in aree o sezioni chiare, evitando di trasformare singole esperienze in etichette.</p></li>
          <li><span>03</span><h3>Scrittura</h3><p>Domande e guide sono scritte in forma originale, con un linguaggio accessibile, specifico e non giudicante.</p></li>
          <li><span>04</span><h3>Revisione professionale</h3><p>Ogni nuovo questionario viene revisionato da una psicologa, che fornisce linee guida metodologiche e verifica struttura, formulazioni, limiti e restituzioni.</p></li>
        </ol>
      </section>
      <ReviewerSection reviewer={reviewer} />
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
