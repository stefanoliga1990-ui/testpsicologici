const INSTAGRAM_URL = 'https://www.instagram.com/spazio.test/';

export default function InstagramLink({ className = '' }) {
  const classes = ['social-link', className].filter(Boolean).join(' ');

  return (
    <a
      className={classes}
      href={INSTAGRAM_URL}
      target="_blank"
      rel="noopener noreferrer"
      aria-label="Visita Spazio Test su Instagram (si apre in una nuova scheda)"
      title="Spazio Test su Instagram"
    >
      <img src="/images/brand/instagram-spazio-test.png" alt="" width="24" height="24" />
    </a>
  );
}
