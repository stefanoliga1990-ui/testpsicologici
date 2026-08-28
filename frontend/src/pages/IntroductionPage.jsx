import { useCallback } from 'react';
import AsyncError from '../components/AsyncError';
import Button from '../components/Button';
import Card from '../components/Card';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import ReferenceList from '../components/ReferenceList';
import RelatedTests from '../components/RelatedTests';
import useAsyncAction from '../hooks/useAsyncAction';
import { startTest } from '../services/api';

export default function IntroductionPage({ guide, relatedTests, test, topicCluster }) {
  const action = useCallback((signal) => startTest(test.id, signal), [test.id]);
  const { error, loading, run } = useAsyncAction(action);
  const isAttachmentStyles = test.scoringModel === 'ATTACHMENT_DIMENSIONAL';
  const isOccurrenceScale = test.answerScale === 'OCCURRENCE';
  const attachmentStyles = [
    'Orientamento sicuro',
    'Orientamento ansioso-preoccupato',
    'Orientamento evitante-distanziante',
    'Orientamento timoroso-evitante'
  ];

  return (
    <main className="intro-shell">
      <Navbar />
      <Card className="intro-card">
        <p className="eyebrow">{test.eyebrow}</p>
        <h1>{test.title}</h1>
        <p className="intro-description">{test.description}</p>
        <a className="editorial-byline" href="/il-progetto">A cura di Spazio Test</a>
        <div className="intro-facts"><span>{test.duration}</span><span>Nessun account richiesto</span></div>
        <div className="notice-box">
          <span aria-hidden="true">i</span>
          <p><strong>Prima di iniziare</strong><span>{test.introductoryText}</span></p>
        </div>
        <form onSubmit={(event) => { event.preventDefault(); run(); }} aria-busy={loading}>
          <Button className="button-primary" type="submit" loading={loading}>
            {loading ? 'Avvio in corso' : 'Inizia il questionario'} {!loading && <span aria-hidden="true">→</span>}
          </Button>
          <AsyncError message={error} />
        </form>
        {guide && (
          <Button as="a" className="button-secondary intro-guide-link" href={`/approfondimenti/${guide.slug}`}>
            Approfondisci l'argomento <span aria-hidden="true">→</span>
          </Button>
        )}
      </Card>
      <RelatedTests className="intro-related-content" relatedTests={relatedTests} topicCluster={topicCluster} />

      <section className="intro-editorial" aria-label="Informazioni sul questionario">
        <div className="editorial-heading">
          <p className="eyebrow">Prima di rispondere</p>
          <h2>Che cosa esplora questo questionario</h2>
          <p>{isAttachmentStyles
            ? 'Le affermazioni esplorano ansia relazionale ed evitamento della vicinanza. La loro combinazione permette una lettura orientativa dei quattro stili descritti qui sotto, senza assegnare diagnosi o identità definitive.'
            : isOccurrenceScale
              ? 'Le affermazioni sono organizzate nelle aree indicate qui sotto. La restituzione aiuta a osservare quanti eventi riconosci nel periodo scelto, senza assegnare diagnosi o etichette.'
              : 'Le affermazioni sono organizzate nelle aree indicate qui sotto. La restituzione aiuta a osservare quali esperienze riconosci più spesso, senza assegnare diagnosi o etichette.'}</p>
        </div>
        <ul className="explored-areas">{(isAttachmentStyles ? attachmentStyles : test.areas.map((area) => area.name))
          .map((name) => <li key={name}>{name}</li>)}</ul>
        <div className="editorial-grid">
          <Card className="editorial-card"><p className="eyebrow">Come funziona</p><h2>Una risposta alla volta</h2><p>{isAttachmentStyles
            ? 'Per ogni affermazione indicherai quanto descrive il tuo modo abituale di vivere la relazione scelta come riferimento, da “Per nulla vero per me” a “Del tutto vero per me”. Puoi tornare alla domanda precedente e completare le 24 domande senza creare un account.'
            : isOccurrenceScale
              ? 'Per ogni affermazione indicherai quante volte è accaduta, da “Mai” a “Molte volte”, nel periodo indicato. Puoi tornare alla domanda precedente e completare il percorso in pochi minuti, senza creare un account.'
              : 'Per ogni affermazione indicherai una frequenza da “Mai” a “Quasi sempre”, riferita al periodo indicato. Puoi tornare alla domanda precedente e completare il percorso in pochi minuti, senza creare un account.'}</p></Card>
          <Card className="editorial-card"><p className="eyebrow">Il risultato</p><h2>Una lettura orientativa</h2><p>{isAttachmentStyles
            ? 'Il risultato mostra le due dimensioni esplorate e ordina i quattro orientamenti per vicinanza al profilo delle risposte. Se due orientamenti risultano vicini, la restituzione parla di caratteristiche intermedie; non sono percentuali, categorie cliniche o etichette stabili.'
            : isOccurrenceScale
              ? "Il risultato riassume l'andamento generale e le aree esplorate. Le barre descrivono il numero riferito degli eventi: non sono percentili, probabilità diagnostiche o misure di gravità clinica."
              : "Il risultato riassume l'andamento generale e le aree esplorate. Le barre descrivono la frequenza delle risposte: non sono percentili, probabilità diagnostiche o misure di gravità clinica."}</p></Card>
        </div>
        <section className="method-card">
          <div><p className="eyebrow">Metodo e trasparenza</p><h2>Contenuto editoriale, non scala clinica</h2></div>
          <div>
            <p>Le domande sono originali e costruite per finalità informative a partire dai temi descritti nelle fonti consultate. Non riproducono né sostituiscono un test psicologico validato o una valutazione condotta da un professionista.</p>
            <p>Le soglie e i testi del risultato servono esclusivamente a comporre una restituzione comprensibile. <a href="/metodo-e-fonti">Scopri il metodo editoriale</a>.</p>
          </div>
        </section>
        {test.references.length > 0 && (
          <section className="references-card">
            <p className="eyebrow">Fonti consultate</p><h2>Per approfondire</h2>
            <ReferenceList references={test.references} />
            <p>Le fonti orientano la struttura editoriale e il linguaggio del questionario; non ne costituiscono una validazione psicometrica. Consulta anche la pagina completa su <a href="/metodo-e-fonti">metodo e fonti</a>.</p>
          </section>
        )}
      </section>
      <Footer />
    </main>
  );
}
