export const SERVER_RENDERED_FALLBACK_SELECTOR = 'body > main[data-react-fallback]';

export function removeServerRenderedFallback(documentRoot = document) {
  const fallback = documentRoot.querySelector(SERVER_RENDERED_FALLBACK_SELECTOR);
  if (!fallback) return false;

  fallback.remove();
  return true;
}
