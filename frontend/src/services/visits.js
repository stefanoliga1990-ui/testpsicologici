let recordingStarted = false;

export function recordDailyVisit() {
  if (recordingStarted) return;
  recordingStarted = true;
  fetch('/internal/visita', {
    method: 'POST',
    credentials: 'same-origin',
    cache: 'no-store',
    keepalive: true,
    referrerPolicy: 'no-referrer',
    headers: { Accept: 'application/json' }
  }).catch(() => {
    // Le statistiche non devono mai interferire con l'uso del sito.
  });
}
