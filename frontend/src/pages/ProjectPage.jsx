import Button from '../components/Button';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';

export default function ProjectPage() {
  return (
    <main className="editorial-shell project-shell">
      <Navbar />
      <header className="editorial-hero project-hero">
        <p className="eyebrow">Il progetto</p><h1>Uno spazio per osservarti con più chiarezza</h1>
        <p>Spazio Test è un progetto editoriale indipendente nato per rendere più accessibili l'auto-osservazione e l'informazione attraverso questionari e guide chiari, documentati e rispettosi della complessità delle esperienze personali.</p>
      </header>
      <section className="project-manifesto">
        <article className="project-statement"><p className="eyebrow">Perché esiste</p><h2>Comprendere, senza etichettare</h2><p>Riconoscere con quale frequenza viviamo alcune esperienze può aiutarci a descriverle meglio. Spazio Test offre un punto di partenza semplice e privato: non assegna identità, non definisce il valore di una persona e non trasforma un risultato in una diagnosi.</p></article>
        <div className="project-values">
          <article><span aria-hidden="true">✦</span><h3>Chiarezza</h3><p>Domande comprensibili, risultati leggibili e limiti dichiarati in modo diretto.</p></article>
          <article><span aria-hidden="true">✦</span><h3>Documentazione</h3><p>Fonti consultabili per comprendere da dove provengono i temi esplorati.</p></article>
          <article><span aria-hidden="true">✦</span><h3>Rispetto</h3><p>Un linguaggio non giudicante, attento al contesto e alle differenze individuali.</p></article>
          <article><span aria-hidden="true">✦</span><h3>Accessibilità</h3><p>Percorsi brevi e gratuiti, disponibili senza creare un account.</p></article>
        </div>
      </section>
      <section className="project-method-cta">
        <div><p className="eyebrow">Trasparenza</p><h2>Il metodo è parte del progetto</h2><p>Per questionari e approfondimenti rendiamo disponibili i riferimenti principali e spieghiamo come vengono costruiti i contenuti.</p></div>
        <Button as="a" className="button-light" href="/metodo-e-fonti">Scopri metodo e fonti <span aria-hidden="true">→</span></Button>
      </section>
      <section className="editorial-responsibility"><p className="eyebrow">Responsabilità editoriale</p><h2>A cura di Spazio Test</h2><p>I contenuti sono pubblicati sotto la responsabilità editoriale di Spazio Test. Il progetto mantiene distinti i questionari informativi dagli strumenti clinici e invita a rivolgersi a professionisti qualificati quando un'esperienza causa sofferenza, limita la vita quotidiana o richiede una valutazione individuale.</p></section>
      <Footer />
    </main>
  );
}
