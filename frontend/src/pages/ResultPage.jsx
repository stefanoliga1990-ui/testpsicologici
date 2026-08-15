import Button from '../components/Button';
import Navbar from '../components/Navbar';
import ProgressBar from '../components/ProgressBar';
import { withSessionId } from '../utils/urls';

export default function ResultPage({ areaResults, percentage, result, score, test }) {
  return (
    <main className="result-shell">
      <Navbar />
      <section className="result-card">
        <p className="eyebrow">Il tuo risultato</p>
        {test.scoreVisible ? <div className="score-badge"><span>{score}</span><small>/100</small></div> : <div className="result-symbol" aria-hidden="true">✦</div>}
        <p className="result-label">{test.title}</p>
        <h1>{result.title}</h1>
        <p className="result-description">{result.description}</p>
        <p className="result-detail">{result.detail}</p>
        {areaResults.length > 0 && (
          <>
            <div className="overall-presence">
              <div className="overall-presence-label">{test.overallMetricLabel}</div>
              <ProgressBar className="overall-presence-track" label={test.overallMetricLabel} value={percentage} />
            </div>
            <div className="area-results">
              <div className="area-results-heading">
                <p className="eyebrow">Le quattro aree</p><h2>Una lettura più completa</h2>
                <p>Le barre rappresentano quanto spesso hai riconosciuto le esperienze esplorate in ciascuna area.</p>
              </div>
              <div className="area-result-grid">
                {areaResults.map((area) => (
                  <article className="area-result-card" key={area.code}>
                    <h3>{area.title}</h3>
                    <div className="area-presence-label"><span>{test.areaMetricLabel}</span></div>
                    <ProgressBar className="area-presence-track" label={`${test.areaMetricLabel}: ${area.title}`} value={area.percentage} />
                    <p>{area.description}</p>
                  </article>
                ))}
              </div>
            </div>
          </>
        )}
        {test.scoreVisible && <div className="score-line" aria-hidden="true"><span style={{ width: `${percentage}%` }} /></div>}
        <div className="result-actions">
          <Button as="a" className="button-primary" href={withSessionId(`/test/${test.id}/risultato/pdf`)}>Download PDF <span aria-hidden="true">↓</span></Button>
          <Button as="a" className="button-secondary" href={`/test/${test.id}`}>Rifai il test <span aria-hidden="true">↻</span></Button>
          <Button as="a" className="button-secondary" href="/">Torna alla home</Button>
        </div>
      </section>
      <p className="disclaimer">Questo questionario è informativo e non clinicamente validato: non conferma né esclude una diagnosi e non sostituisce una valutazione professionale.</p>
    </main>
  );
}
