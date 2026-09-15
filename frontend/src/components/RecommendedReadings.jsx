import Button from './Button';

export default function RecommendedReadings({ readings }) {
  if (readings.length === 0) return null;

  return (
    <section className="recommended-readings" aria-labelledby="recommended-readings-title">
      <p className="eyebrow">Letture facoltative</p>
      <h2 id="recommended-readings-title">Per approfondire l'argomento</h2>
      <p className="recommended-readings-intro">
        Questi libri sono proposte editoriali facoltative, distinte dalle fonti scientifiche della pagina.
      </p>
      <div className="recommended-reading-grid">
        {readings.map((reading) => (
          <article className="recommended-reading-card" key={reading.amazonUrl}>
            {reading.imageUrl && (
              <a
                className="recommended-reading-cover"
                href={reading.amazonUrl}
                target="_blank"
                rel="noopener noreferrer sponsored"
                aria-label={`Vedi ${reading.title} su Amazon`}
              >
                <img
                  src={reading.imageUrl}
                  width={reading.imageWidth}
                  height={reading.imageHeight}
                  loading="lazy"
                  decoding="async"
                  alt={`Copertina di ${reading.title}`}
                />
              </a>
            )}
            <h3>{reading.title}</h3>
            <p className="recommended-reading-authors">{reading.authors}</p>
            <p>{reading.description}</p>
            <p className="recommended-reading-limit">{reading.limit}</p>
            <Button as="a" className="button-secondary" href={reading.amazonUrl} target="_blank" rel="noopener noreferrer sponsored">
              Vedi su Amazon <span aria-hidden="true">↗</span>
            </Button>
          </article>
        ))}
      </div>
      <p className="affiliate-disclosure">
        Link affiliati Amazon. In qualità di Affiliato Amazon io ricevo un guadagno dagli acquisti idonei.
      </p>
    </section>
  );
}
