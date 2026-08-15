import Brand from './Brand';

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
  return (
    <nav className="site-nav" aria-label="Navigazione principale">
      <Brand />
      <div className="nav-links">
        {links.map((link) => (
          <a href={link.href} aria-current={isCurrentPage(link.href) ? 'page' : undefined} key={link.href}>
            {link.label}
          </a>
        ))}
      </div>
    </nav>
  );
}
