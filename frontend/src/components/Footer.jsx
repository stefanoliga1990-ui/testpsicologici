import Brand from './Brand';

export default function Footer() {
  return (
    <footer className="site-footer">
      <Brand className="footer-brand" />
      <nav className="footer-links" aria-label="Informazioni sul sito">
        <a href="/approfondimenti">Approfondimenti</a>
        <a href="/metodo-e-fonti">Metodo e fonti</a>
        <a href="/il-progetto">Il progetto</a>
        <a href="/privacy-e-cookie">Privacy e cookie</a>
        <a href="/">Tutti i test</a>
      </nav>
      <p>Questionari e guide informative per l'auto-osservazione. I contenuti non costituiscono diagnosi o valutazioni cliniche.</p>
    </footer>
  );
}
