import { useMemo, useState } from 'react';
import Card from '../components/Card';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import { normalizeSearch } from '../utils/text';

export default function HomePage({ initialQuery = '', tests, topicClusters }) {
  const [query, setQuery] = useState(initialQuery);
  const visibleTests = useMemo(() => {
    const normalized = normalizeSearch(query);
    return tests.filter((test) => normalizeSearch(test.title).includes(normalized));
  }, [query, tests]);
  const visibleTestIds = useMemo(
    () => new Set(visibleTests.map((test) => test.id)),
    [visibleTests]
  );
  const testsById = useMemo(
    () => new Map(tests.map((test) => [test.id, test])),
    [tests]
  );

  return (
    <main className="shell home-shell">
      <section className="home-panel">
        <Navbar />
        <div className="home-feature-layout">
          <div className="home-feature-stage">
            <div className="hero-clip">
              <section className="hero">
                <div className="hero-copy">
                  <p className="eyebrow">Test brevi · senza account</p>
                  <h1>Test psicologici online.<br /><em>Fatti Spazio.</em></h1>
                  <p className="lead">Questionari informativi, senza registrazione, per osservare con più chiarezza esperienze, emozioni e relazioni. Il risultato è immediato e non diagnostico.</p>
                  <a className="text-link" href="#test-disponibili">Esplora i test <span aria-hidden="true">↓</span></a>
                </div>
                <div className="hero-orbit" aria-hidden="true">
                  <div className="orbit-ring orbit-ring-one" />
                  <div className="orbit-ring orbit-ring-two" />
                  <div className="orbit-core">24<br /><span>domande</span></div>
                  <span className="spark spark-one">✦</span><span className="spark spark-two">✦</span>
                </div>
              </section>
            </div>
            <div className="home-tests-cta">
              <a className="tests-circle-link" href="#test-disponibili">
                <span>I nostri</span><strong>test</strong><span className="tests-circle-arrow" aria-hidden="true">↓</span>
              </a>
            </div>
          </div>
        </div>
      </section>

      <section id="test-disponibili" className="tests-section">
        <div className="test-search">
          <label className="test-search-label" htmlFor="test-search-input">Cerca un test</label>
          <div className="test-search-field">
            <svg aria-hidden="true" viewBox="0 0 24 24" focusable="false">
              <circle cx="11" cy="11" r="6.5" /><path d="m16 16 4 4" />
            </svg>
            <input
              id="test-search-input"
              type="search"
              placeholder="Scrivi il titolo del test..."
              autoComplete="off"
              spellCheck="false"
              value={query}
              onChange={(event) => setQuery(event.target.value)}
            />
          </div>
        </div>
        <div className="section-heading">
          <div><p className="eyebrow">Inizia da qui</p><h2>Test disponibili</h2></div>
          <span className="test-count" aria-live="polite">{visibleTests.length} test</span>
        </div>
        <div className="topic-clusters">
          {topicClusters.map((cluster) => {
            const clusterTests = cluster.testIds
              .filter((id) => visibleTestIds.has(id))
              .map((id) => testsById.get(id))
              .filter(Boolean);
            if (clusterTests.length === 0) return null;
            return (
              <section className="topic-cluster" id={`cluster-${cluster.slug}`} key={cluster.slug}>
                <header className="topic-cluster-heading">
                  <div><p className="eyebrow">Macro-argomento</p><h3>{cluster.title}</h3><p>{cluster.description}</p></div>
                  <span>{clusterTests.length} {clusterTests.length === 1 ? 'test' : 'test'}</span>
                </header>
                <div className="test-grid">
                  {clusterTests.map((test) => (
                    <Card
                      as="a"
                      className="test-card"
                      href={`/test/${test.id}`}
                      aria-label={`Apri il test: ${test.title}`}
                      key={test.id}
                    >
                      <div className="card-topline"><span className="card-icon">⌘</span><span>{test.eyebrow}</span></div>
                      <h3>{test.title}</h3>
                      <p>{test.description}</p>
                      <div className="card-footer"><span>{test.duration}</span><span className="round-link" aria-hidden="true">→</span></div>
                    </Card>
                  ))}
                </div>
              </section>
            );
          })}
        </div>
        {visibleTests.length === 0 && <p className="test-search-empty">Nessun test corrisponde alla ricerca.</p>}
      </section>
      <p className="disclaimer">Questi test hanno finalità puramente informative e non costituiscono una valutazione psicologica o clinica.</p>
      <Footer />
    </main>
  );
}
