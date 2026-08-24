import { useEffect, useRef } from 'react';

export default function SupportIntroDialog({ onDismiss, open }) {
  const dialogRef = useRef(null);
  const primaryButtonRef = useRef(null);

  useEffect(() => {
    const dialog = dialogRef.current;
    if (!open || !dialog) return undefined;

    function handleCancel(event) {
      event.preventDefault();
      onDismiss();
    }

    dialog.addEventListener('cancel', handleCancel);
    if (!dialog.open) dialog.showModal();
    primaryButtonRef.current?.focus();
    document.body.classList.add('support-dialog-open');

    return () => {
      dialog.removeEventListener('cancel', handleCancel);
      document.body.classList.remove('support-dialog-open');
      if (dialog.open) dialog.close();
    };
  }, [onDismiss, open]);

  return (
    <dialog
      className="support-intro-dialog"
      aria-labelledby="support-intro-title"
      aria-describedby="support-intro-description support-intro-free"
      ref={dialogRef}
    >
      <button
        className="support-intro-close"
        type="button"
        aria-label="Chiudi e vai al risultato"
        onClick={onDismiss}
      >
        <span aria-hidden="true">×</span>
      </button>
      <div className="support-intro-symbol" aria-hidden="true">
        <svg viewBox="0 0 48 48" focusable="false">
          <path d="M11 18h23v9a11 11 0 0 1-11 11h-1a11 11 0 0 1-11-11v-9Z" />
          <path d="M34 21h3a5 5 0 0 1 0 10h-3" />
          <path d="M9 41h30" />
          <path d="M18 13c-2-2-2-4 0-6" />
          <path d="M26 13c-2-2-2-4 0-6" />
        </svg>
      </div>
      <p className="eyebrow">Prima del risultato</p>
      <h2 id="support-intro-title">I test e i risultati sono gratuiti</h2>
      <p id="support-intro-description">
        Se vorrai, dopo aver letto il risultato potrai aiutarci con un piccolo contributo
        volontario per sostenere Spazio Test.
      </p>
      <p className="support-intro-free" id="support-intro-free">
        Non è richiesto alcun pagamento per continuare.
      </p>
      <button
        className="button button-primary support-intro-action"
        type="button"
        onClick={onDismiss}
        ref={primaryButtonRef}
      >
        Ci penso! <span aria-hidden="true">→</span>
      </button>
    </dialog>
  );
}
