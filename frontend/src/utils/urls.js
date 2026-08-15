export function withSessionId(path) {
  const match = window.location.pathname.match(/;jsessionid=[^/?#;]+/i);
  return match && !path.includes(';jsessionid=') ? `${path}${match[0]}` : path;
}
