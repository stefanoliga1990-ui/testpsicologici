import Button from '../components/Button';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import ReferenceList from '../components/ReferenceList';
import RelatedGuides from '../components/RelatedGuides';
import RecommendedReadings from '../components/RecommendedReadings';

function italianDate(isoDate) {
  return new Intl.DateTimeFormat('it-IT', { day: 'numeric', month: 'long', year: 'numeric', timeZone: 'UTC' })
    .format(new Date(`${isoDate}T12:00:00Z`));
}

export default function GuidePage({ author, editorialHistory, guide, recommendedReadings = [], relatedGuides, reviewer, test, topicCluster }) {
  return (
    <main className="guide-shell">
      <Navbar />
      <nav className="breadcrumbs" aria-label="Percorso di navigazione">
        <a href="/">Home</a><span aria-hidden="true">/</span><a href="/approfondimenti">Approfondimenti</a><span aria-hidden="true">/</span><span aria-current="page">{guide.cardTitle}</span>
      </nav>
      <article className="guide-article">
        <header className="guide-hero">
          <p className="eyebrow">Guida informativa</p><h1>{guide.title}</h1><p className="guide-lead">{guide.summary}</p>
          <div className="guide-editorial-credits">
            <p><span>Autore</span> <a href="/il-progetto">{author.name}</a> · {author.description}</p>
            <p><span>Revisione professionale</span> <a href="/metodo-e-fonti#revisione-professionale">{reviewer.name}</a> · {reviewer.role}</p>
            <p><span>Pubblicata</span> <time dateTime={editorialHistory.publishedOn}>{italianDate(editorialHistory.publishedOn)}</time><span className="guide-credit-separator" aria-hidden="true"> · </span><span>Ultima revisione del contenuto</span> <time dateTime={editorialHistory.revisedOn}>{italianDate(editorialHistory.revisedOn)}</time></p>
          </div>
        </header>
        <div className="guide-content">
          {guide.originalContribution && (
            <section className="guide-section guide-original-contribution">
              <p className="eyebrow">Il contributo di Spazio Test</p><h2>Come leggere questa guida</h2>
              <p>{guide.originalContribution}</p>
            </section>
          )}
          {guide.sections.map((section) => (
            <section className="guide-section" key={`${section.eyebrow}-${section.title}`}>
              <p className="eyebrow">{section.eyebrow}</p><h2>{section.title}</h2>
              {section.paragraphs.map((paragraph) => <p key={paragraph}>{paragraph}</p>)}
              {section.points.length > 0 && <ul>{section.points.map((point) => <li key={point}>{point}</li>)}</ul>}
            </section>
          ))}
          <RecommendedReadings readings={recommendedReadings} />
          <aside className="guide-test-cta">
            <div><p className="eyebrow">Auto-osservazione</p><h2>Questionario: {test.title}</h2><p>{guide.testConnection}</p></div>
            <Button as="a" className="button-light" href={`/test/${test.id}`}>Vai al questionario <span aria-hidden="true">→</span></Button>
          </aside>
          <RelatedGuides className="guide-related-content" relatedGuides={relatedGuides} topicCluster={topicCluster} />
          <section className="guide-sources" aria-labelledby="fonti-guida">
            <p className="eyebrow">Riferimenti</p><h2 id="fonti-guida">Fonti consultate</h2>
            <p>Questi riferimenti hanno orientato la spiegazione dell'argomento. Sono distinti dalle fonti impiegate per costruire il questionario.</p>
            <ReferenceList references={guide.references} />
            <p className="guide-method-note">Per conoscere criteri e limiti del processo editoriale, consulta <a href="/metodo-e-fonti">Metodo e fonti</a>.</p>
          </section>
        </div>
      </article>
      <Footer />
    </main>
  );
}
