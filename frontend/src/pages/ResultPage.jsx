import { useCallback, useEffect, useState } from 'react';
import Button from '../components/Button';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import ProgressBar from '../components/ProgressBar';
import RelatedTests from '../components/RelatedTests';
import SupportContributionCard from '../components/SupportContributionCard';
import SupportIntroDialog from '../components/SupportIntroDialog';
import { withSessionId } from '../utils/urls';

function shouldShowSupportIntro(enabled, testId) {
  if (!enabled) return false;
  try {
    return window.sessionStorage.getItem(`spazio-test:support-intro:${testId}`) !== 'seen';
  } catch {
    return true;
  }
}

export default function ResultPage({ areaResults, contributionsEnabled = false, guide, percentage, relatedTests, result, score, styleResults = [], test, topicCluster }) {
  const [showSupportIntro, setShowSupportIntro] = useState(
    () => shouldShowSupportIntro(contributionsEnabled, test.id)
  );

  useEffect(() => {
    try {
      window.sessionStorage.setItem('spazio-test:last-result', window.location.pathname);
    } catch {
      // Il collegamento di ritorno è un miglioramento facoltativo: il risultato resta accessibile.
    }
  }, []);

  const dismissSupportIntro = useCallback(() => {
    try {
      window.sessionStorage.setItem(`spazio-test:support-intro:${test.id}`, 'seen');
    } catch {
      // La chiusura del dialogo non dipende dalla disponibilità dello storage del browser.
    }
    setShowSupportIntro(false);
  }, [test.id]);
  const isAttachmentStyles = test.scoringModel === 'ATTACHMENT_DIMENSIONAL';

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
        {areaResults.length > 0 && !isAttachmentStyles && (
          <>
            <div className="overall-presence">
              <div className="overall-presence-label">{test.overallMetricLabel}</div>
              <ProgressBar className="overall-presence-track" label={test.overallMetricLabel} value={percentage} />
            </div>
            <div className="area-results">
              <div className="area-results-heading">
                <p className="eyebrow">Le quattro aree</p><h2>Una lettura più completa</h2>
                <p>Le barre riassumono le risposte agli item di ciascuna area. Non sono percentili, probabilità diagnostiche o confronti con altre persone.</p>
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
        {isAttachmentStyles && areaResults.length > 0 && (
          <>
            <div className="area-results attachment-dimensions">
              <div className="area-results-heading">
                <p className="eyebrow">Le due dimensioni</p><h2>Come si distribuiscono le risposte</h2>
                <p>Le barre sintetizzano quanto le affermazioni di ansia relazionale e di evitamento descrivono la relazione scelta. Non sono percentili, probabilità o confronti con altre persone.</p>
              </div>
              <div className="area-result-grid">
                {areaResults.map((area) => (
                  <article className="area-result-card" key={area.code}>
                    <h3>{area.title}</h3>
                    <div className="area-presence-label"><span>Quanto emerge nelle risposte</span></div>
                    <ProgressBar className="area-presence-track" label={`Quanto emerge nelle risposte: ${area.title}`} value={area.percentage} />
                    <p>{area.description}</p>
                  </article>
                ))}
              </div>
            </div>
            <section className="attachment-style-results" aria-labelledby="attachment-styles-title">
              <div className="area-results-heading">
                <p className="eyebrow">I quattro orientamenti</p>
                <h2 id="attachment-styles-title">Dal più vicino al meno vicino</h2>
                <p>L'ordine deriva dalla combinazione delle due dimensioni. Una posizione non definisce chi sei e può cambiare in relazioni o momenti diversi.</p>
              </div>
              <div className="attachment-style-grid">
                {styleResults.map((style) => (
                  <article className={`attachment-style-card${style.rank === 1 ? ' attachment-style-card-primary' : ''}`} key={style.code}>
                    <span className="attachment-style-rank">{style.rank === 1 ? 'Più vicino' : `${style.rank}° nell’ordine`}</span>
                    <h3>{style.title}</h3>
                    <p>{style.description}</p>
                  </article>
                ))}
              </div>
            </section>
          </>
        )}
        {test.scoreVisible && <div className="score-line" aria-hidden="true"><span style={{ width: `${percentage}%` }} /></div>}
        <div className="result-actions">
          <Button as="a" className="button-primary" href={withSessionId(`/test/${test.id}/risultato/pdf`)}>Download PDF <span aria-hidden="true">↓</span></Button>
          {guide && <Button as="a" className="button-secondary" href={`/approfondimenti/${guide.slug}`}>Approfondisci l'argomento <span aria-hidden="true">→</span></Button>}
          <Button as="a" className="button-secondary" href={`/test/${test.id}`}>Rifai il test <span aria-hidden="true">↻</span></Button>
          <Button as="a" className="button-secondary" href="/">Torna alla home</Button>
        </div>
      </section>
      <RelatedTests className="result-related-content" relatedTests={relatedTests} topicCluster={topicCluster} />
      {contributionsEnabled && <SupportContributionCard />}
      <p className="disclaimer">Questo questionario è informativo e non clinicamente validato: non conferma né esclude una diagnosi e non sostituisce una valutazione professionale.</p>
      <Footer />
      <SupportIntroDialog open={showSupportIntro} onDismiss={dismissSupportIntro} />
    </main>
  );
}
