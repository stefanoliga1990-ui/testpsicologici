export default function Brand({ className = 'brand' }) {
  return (
    <a className={className} href="/">
      <span className="brand-mark" aria-hidden="true">✦</span> Spazio Test
    </a>
  );
}
