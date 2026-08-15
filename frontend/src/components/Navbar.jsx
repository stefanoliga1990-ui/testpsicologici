import Brand from './Brand';

export default function Navbar({ links = [], note, brandClassName }) {
  return (
    <nav className="site-nav" aria-label="Navigazione principale">
      <Brand className={brandClassName} />
      {note ? <span className="nav-note">{note}</span> : (
        <div className="nav-links">
          {links.map((link) => <a href={link.href} key={link.href}>{link.label}</a>)}
        </div>
      )}
    </nav>
  );
}
