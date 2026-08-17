import { useMemo, useState } from 'react';
import Card from '../components/Card';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import { normalizeSearch } from '../utils/text';

export default function GuidesPage({ guides }) {
  const [query, setQuery] = useState('');
  const visibleGuides = useMemo(() => {
    const normalized = normalizeSearch(query);
    return guides.filter((guide) => normalizeSearch(guide.cardTitle).includes(normalized));
  }, [guides, query]);

  return (
    <main className="editorial-shell guides-shell">
      <Navbar />
      <header className="editorial-hero guides-hero">
        <p className="eyebrow">Conoscere per orientarsi</p><h1>Approfondimenti</h1>
        <p>Guide brevi e documentate per comprendere gli argomenti esplorati nei questionari, distinguere esperienze comuni e condizioni cliniche e sapere che cosa può richiedere una valutazione individuale.</p>
      </header>
      <section className="guide-index" aria-labelledby="guide-disponibili">
        <div className="editorial-section-heading">
          <p className="eyebrow">Guide disponibili</p><h2 id="guide-disponibili">Un argomento alla volta</h2>
          <p>Ogni approfondimento utilizza fonti istituzionali e scientifiche consultabili e rimane distinto dal questionario informativo collegato.</p>
        </div>
        <div className="test-search guide-search">
          <div className="guide-search-heading">
            <label className="test-search-label" htmlFor="guide-search-input">Cerca un approfondimento</label>
            <span className="guide-search-count" aria-live="polite">
              {visibleGuides.length} {visibleGuides.length === 1 ? 'approfondimento' : 'approfondimenti'}
            </span>
          </div>
          <div className="test-search-field">
            <svg aria-hidden="true" viewBox="0 0 24 24" focusable="false">
              <circle cx="11" cy="11" r="6.5" /><path d="m16 16 4 4" />
            </svg>
            <input
              id="guide-search-input"
              type="search"
              placeholder="Scrivi il titolo dell'approfondimento..."
              autoComplete="off"
              spellCheck="false"
              aria-controls="guide-card-grid"
              value={query}
              onChange={(event) => setQuery(event.target.value)}
            />
          </div>
        </div>
        <div className="guide-card-grid" id="guide-card-grid">
          {visibleGuides.map((guide) => (
            <Card as="a" className="guide-card" href={`/approfondimenti/${guide.slug}`} key={guide.slug}>
              <div><p className="eyebrow">Guida informativa</p><h3>{guide.cardTitle}</h3><p>{guide.summary}</p></div>
              <span>Leggi l'approfondimento <span aria-hidden="true">→</span></span>
            </Card>
          ))}
        </div>
        {visibleGuides.length === 0 && <p className="test-search-empty guide-search-empty">Nessun approfondimento corrisponde alla ricerca.</p>}
      </section>
      <Footer />
    </main>
  );
}
