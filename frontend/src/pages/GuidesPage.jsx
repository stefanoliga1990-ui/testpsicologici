import Card from '../components/Card';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';

const links = [
  { href: '/metodo-e-fonti', label: 'Metodo e fonti' },
  { href: '/il-progetto', label: 'Il progetto' },
  { href: '/', label: 'Tutti i test' }
];

export default function GuidesPage({ guides }) {
  return (
    <main className="editorial-shell guides-shell">
      <Navbar links={links} />
      <header className="editorial-hero guides-hero">
        <p className="eyebrow">Conoscere per orientarsi</p><h1>Approfondimenti</h1>
        <p>Guide brevi e documentate per comprendere gli argomenti esplorati nei questionari, distinguere esperienze comuni e condizioni cliniche e sapere che cosa può richiedere una valutazione individuale.</p>
      </header>
      <section className="guide-index" aria-labelledby="guide-disponibili">
        <div className="editorial-section-heading">
          <p className="eyebrow">Guide disponibili</p><h2 id="guide-disponibili">Un argomento alla volta</h2>
          <p>Ogni approfondimento utilizza fonti istituzionali e scientifiche consultabili e rimane distinto dal questionario informativo collegato.</p>
        </div>
        <div className="guide-card-grid">
          {guides.map((guide) => (
            <Card as="a" className="guide-card" href={`/approfondimenti/${guide.slug}`} key={guide.slug}>
              <div><p className="eyebrow">Guida informativa</p><h3>{guide.cardTitle}</h3><p>{guide.summary}</p></div>
              <span>Leggi l'approfondimento <span aria-hidden="true">→</span></span>
            </Card>
          ))}
        </div>
      </section>
      <Footer />
    </main>
  );
}
