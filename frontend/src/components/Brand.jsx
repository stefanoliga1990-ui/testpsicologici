export default function Brand({ className = 'brand' }) {
  return (
    <a className={className} href="/">
      <img className="brand-mark" src="/images/brand/logo-mark.svg" alt="" width="27" height="27" /> Spazio Test
    </a>
  );
}
