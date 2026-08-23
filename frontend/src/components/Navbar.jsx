import { useEffect, useRef, useState } from 'react';
import Brand from './Brand';
import InstagramLink from './InstagramLink';

const links = [
  { href: '/approfondimenti', label: 'Approfondimenti' },
  { href: '/metodo-e-fonti', label: 'Metodo e fonti' },
  { href: '/il-progetto', label: 'Il progetto' }
];

function isCurrentPage(href) {
  return window.location.pathname === href
    || (href === '/approfondimenti' && window.location.pathname.startsWith('/approfondimenti/'));
}

export default function Navbar() {
  const [menuOpen, setMenuOpen] = useState(false);
  const navRef = useRef(null);
  const toggleRef = useRef(null);

  useEffect(() => {
    function closeFromOutside(event) {
      if (!navRef.current?.contains(event.target)) setMenuOpen(false);
    }

    function closeFromKeyboard(event) {
      if (event.key === 'Escape' && toggleRef.current?.getAttribute('aria-expanded') === 'true') {
        setMenuOpen(false);
        toggleRef.current?.focus();
      }
    }

    document.addEventListener('click', closeFromOutside);
    document.addEventListener('keydown', closeFromKeyboard);
    return () => {
      document.removeEventListener('click', closeFromOutside);
      document.removeEventListener('keydown', closeFromKeyboard);
    };
  }, []);

  return (
    <nav className="site-nav" aria-label="Navigazione principale" ref={navRef}>
      <Brand />
      <div className="nav-links">
        {links.map((link) => (
          <a href={link.href} aria-current={isCurrentPage(link.href) ? 'page' : undefined} key={link.href}>
            {link.label}
          </a>
        ))}
        <InstagramLink />
      </div>
      <button
        className="nav-toggle"
        type="button"
        aria-expanded={menuOpen}
        aria-controls="mobile-navigation-react"
        aria-label={menuOpen ? 'Chiudi il menu di navigazione' : 'Apri il menu di navigazione'}
        onClick={() => setMenuOpen((open) => !open)}
        ref={toggleRef}
      >
        <span aria-hidden="true" /><span aria-hidden="true" /><span aria-hidden="true" />
      </button>
      <div className="mobile-nav-menu" id="mobile-navigation-react" hidden={!menuOpen}>
        {links.map((link) => (
          <a href={link.href} aria-current={isCurrentPage(link.href) ? 'page' : undefined} key={link.href}>
            {link.label}
          </a>
        ))}
        <InstagramLink className="mobile-instagram-link" showLabel />
      </div>
    </nav>
  );
}
