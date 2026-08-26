import Button from '../components/Button';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import ReferenceList from '../components/ReferenceList';
import RelatedGuides from '../components/RelatedGuides';

export default function GuidePage({ guide, relatedGuides, test, topicCluster }) {
  return (
    <main className="guide-shell">
      <Navbar />
      <nav className="breadcrumbs" aria-label="Percorso di navigazione">
        <a href="/">Home</a><span aria-hidden="true">/</span><a href="/approfondimenti">Approfondimenti</a><span aria-hidden="true">/</span><span aria-current="page">{guide.cardTitle}</span>
      </nav>
      <article className="guide-article">
        <div className="guide-hero-layout">
        <header className="guide-hero">
          <p className="eyebrow">Guida informativa</p><h1>{guide.title}</h1><p className="guide-lead">{guide.summary}</p>
          <a className="editorial-byline" href="/il-progetto">A cura di Spazio Test</a>
        </header>
        <RelatedGuides relatedGuides={relatedGuides} topicCluster={topicCluster} />
        </div>
        <div className="guide-content">
          {guide.sections.map((section) => (
            <section className="guide-section" key={`${section.eyebrow}-${section.title}`}>
              <p className="eyebrow">{section.eyebrow}</p><h2>{section.title}</h2>
              {section.paragraphs.map((paragraph) => <p key={paragraph}>{paragraph}</p>)}
              {section.points.length > 0 && <ul>{section.points.map((point) => <li key={point}>{point}</li>)}</ul>}
            </section>
          ))}
          <aside className="guide-test-cta">
            <div><p className="eyebrow">Auto-osservazione</p><h2>Questionario: {test.title}</h2><p>{guide.testConnection}</p></div>
            <Button as="a" className="button-light" href={`/test/${test.id}`}>Vai al questionario <span aria-hidden="true">→</span></Button>
          </aside>
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
