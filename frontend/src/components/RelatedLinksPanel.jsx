export default function RelatedLinksPanel({ actionLabel, className = '', cluster, contextHref, items, title, urlFor }) {
  if (!cluster || !items?.length) return null;

  return (
    <aside className={`related-content${className ? ` ${className}` : ''}`} aria-label={title}>
      <p className="eyebrow">Nello stesso tema</p>
      <h2>{title}</h2>
      <a className="related-cluster-link" href={contextHref}>{cluster.title} <span aria-hidden="true">→</span></a>
      <nav className="related-links" aria-label={`${title}: ${cluster.title}`}>
        {items.map((item) => (
          <a className="related-link" href={urlFor(item)} key={item.id || item.slug}>
            <strong>{item.title}</strong>
            <span>{actionLabel} <span aria-hidden="true">→</span></span>
          </a>
        ))}
      </nav>
    </aside>
  );
}
