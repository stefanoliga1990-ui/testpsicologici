import { useCallback, useState } from 'react';
import AsyncError from '../components/AsyncError';
import Footer from '../components/Footer';
import Navbar from '../components/Navbar';
import useAsyncAction from '../hooks/useAsyncAction';
import { saveAnswer } from '../services/api';
import { withSessionId } from '../utils/urls';

export default function QuestionPage({ answers, progress, question, questionCount, questionNumber, selectedAnswer, test }) {
  const [answer, setAnswer] = useState(selectedAnswer || '');
  const action = useCallback((value, signal) => saveAnswer(test.id, questionNumber, value, signal), [questionNumber, test.id]);
  const { error, loading, run } = useAsyncAction(action);
  const previousPath = withSessionId(`/test/${test.id}/domanda/${questionNumber - 1}`);
  const isLast = questionNumber === questionCount;

  return (
    <main className="test-shell">
      <Navbar />
      <section className="question-layout">
        <div className="question-meta">
          <span>Domanda {questionNumber} di {questionCount}</span>
          <div className="progress-track" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow={progress}>
            <div className="progress-value" style={{ width: `${progress}%` }} />
          </div>
        </div>
        <p className="question-prompt">{test.responseInstruction}</p>
        <h1>{question.text}</h1>
        {question.example ? <p className="question-example"><strong>Un esempio possibile:</strong> {question.example}</p> : null}
        <form className="answer-form" onSubmit={(event) => { event.preventDefault(); run(answer); }} aria-busy={loading}>
          <div className="answer-navigation">
            {questionNumber > 1 ? (
              <a className="answer-nav" href={previousPath} aria-label="Torna alla domanda precedente"><span aria-hidden="true">←</span></a>
            ) : (
              <span className="answer-nav answer-nav-disabled" aria-hidden="true"><span>←</span></span>
            )}
            <fieldset>
              <legend className="sr-only">Scegli una risposta</legend>
              {answers.map((label, index) => {
                const value = index + 1;
                return (
                  <label className="answer-option" key={label}>
                    <input type="radio" name="answer" value={value} checked={Number(answer) === value} onChange={() => setAnswer(value)} required />
                    <span className="custom-radio" aria-hidden="true" /><span>{label}</span>
                  </label>
                );
              })}
            </fieldset>
            <button className="answer-nav answer-nav-next" type="submit" disabled={loading} aria-label={isLast ? 'Mostra il risultato' : 'Vai alla domanda successiva'}>
              <span>{loading ? 'Attendi' : (isLast ? 'Scopri il risultato' : 'Continua')}</span><span aria-hidden="true">→</span>
            </button>
          </div>
          <AsyncError message={error} />
        </form>
      </section>
      <Footer />
    </main>
  );
}
