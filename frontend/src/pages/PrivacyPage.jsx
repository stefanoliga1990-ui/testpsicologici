import Footer from '../components/Footer';
import Navbar from '../components/Navbar';

export default function PrivacyPage({ privacyContactEmail }) {
  return (
    <main className="editorial-shell privacy-shell">
      <Navbar />
      <header className="editorial-hero privacy-hero">
        <p className="eyebrow">Trasparenza</p>
        <h1>Privacy e cookie</h1>
        <p>Spazio Test limita la raccolta ai dati strettamente necessari al funzionamento del sito e a una statistica giornaliera aggregata.</p>
      </header>
      <section className="privacy-content">
        <article><h2>Dati dei questionari</h2><p>Le risposte vengono mantenute soltanto nella sessione tecnica del browser per consentire lo svolgimento del questionario e mostrare il risultato. Non vengono associate a un account né salvate nel database delle statistiche.</p></article>
        <article><h2>Conteggio giornaliero delle visite</h2><p>Il sito conserva esclusivamente, per ogni data, il numero complessivo di browser distinti che hanno caricato almeno una pagina. Non vengono salvati indirizzo IP, User-Agent, pagina visitata, argomento consultato, provenienza o identificatori individuali.</p></article>
        <article><h2>Cookie utilizzati</h2><p><strong>__Host-st_visit_day</strong> è un cookie analytics di prima parte, uguale per tutti i visitatori della stessa giornata. Indica soltanto che il browser è già stato incluso nel conteggio e scade alla mezzanotte nel fuso Europe/Rome. <strong>JSESSIONID</strong> è un cookie tecnico temporaneo usato durante i questionari e per l'accesso riservato alla dashboard.</p></article>
        <article><h2>Finalità e conservazione</h2><p>La statistica serve esclusivamente a comprendere il volume d'uso complessivo di Spazio Test. I conteggi giornalieri aggregati possono essere conservati nel tempo perché non permettono di ricostruire la navigazione o identificare un visitatore.</p></article>
        <article><h2>Consenso</h2><p>Non vengono utilizzati cookie pubblicitari o di profilazione. Il cookie statistico è gestito direttamente da Spazio Test per produrre statistiche aggregate relative a questo solo sito; per questo non viene richiesto un consenso tramite banner.</p></article>
        <article><h2>Contatti</h2>{privacyContactEmail
          ? <p>Per richieste relative alla privacy puoi scrivere a <a href={`mailto:${privacyContactEmail}`}>{privacyContactEmail}</a>.</p>
          : <p>Il recapito privacy del titolare non è ancora stato configurato.</p>}</article>
      </section>
      <Footer />
    </main>
  );
}
