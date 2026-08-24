export default function SupportContributionCard() {
  return (
    <section className="support-card" aria-labelledby="support-card-title">
      <div className="support-card-copy">
        <p className="eyebrow">Sostieni Spazio Test</p>
        <h2 id="support-card-title">Ti va di offrirci un caffè?</h2>
        <p>
          Spazio Test resta gratuito. Se il progetto ti è stato utile, puoi contribuire
          volontariamente ai suoi costi. Il contributo non sblocca contenuti o funzionalità.
        </p>
      </div>
      <form className="support-amount-form" action="/supporto/checkout" method="post">
        <fieldset>
          <legend>Scegli l'importo del contributo</legend>
          <button className="support-amount" type="submit" name="amount" value="1">
            <span>€1</span>
          </button>
          <button className="support-amount support-amount-suggested" type="submit" name="amount" value="3">
            <span>€3</span>
            <small>Suggerito</small>
          </button>
          <button className="support-amount" type="submit" name="amount" value="5">
            <span>€5</span>
          </button>
        </fieldset>
      </form>
      <p className="support-card-note">
        Pagamento sicuro tramite Stripe. Nessun dato relativo al test o al risultato viene
        inviato al gestore del pagamento.
      </p>
    </section>
  );
}
