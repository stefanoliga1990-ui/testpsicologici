import { useState } from 'react';
import Button from '../components/Button';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';

function storedResultPath() {
  try {
    const path = window.sessionStorage.getItem('spazio-test:last-result');
    return path && /^\/test\/[a-z0-9-]+\/risultato(?:;jsessionid=[^/?#;]+)?$/i.test(path)
      ? path
      : null;
  } catch {
    return null;
  }
}

export default function SupportReturnPage({ status }) {
  const [resultPath] = useState(storedResultPath);
  const success = status === 'success';

  return (
    <main className="support-return-shell">
      <Navbar />
      <section className="support-return-card">
        <div className={`support-return-icon${success ? '' : ' support-return-icon-muted'}`} aria-hidden="true">
          {success ? '✓' : '←'}
        </div>
        <p className="eyebrow">{success ? 'Grazie' : 'Pagamento annullato'}</p>
        <h1>{success ? 'Grazie per il tuo sostegno' : 'Nessun problema'}</h1>
        <p>
          {success
            ? 'Il tuo contributo ci aiuta a mantenere gratuiti i test e i risultati e a continuare a migliorare Spazio Test.'
            : 'Il pagamento non è stato completato. I test e i risultati restano gratuiti e puoi continuare a usare Spazio Test normalmente.'}
        </p>
        <div className="support-return-actions">
          {resultPath && (
            <Button as="a" className="button-primary" href={resultPath}>Torna al risultato</Button>
          )}
          <Button as="a" className={resultPath ? 'button-secondary' : 'button-primary'} href="/">
            Torna alla home
          </Button>
        </div>
      </section>
      <Footer />
    </main>
  );
}
