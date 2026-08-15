import { withSessionId } from '../utils/urls';

async function postForm(path, values, signal) {
  const response = await fetch(withSessionId(path), {
    method: 'POST',
    credentials: 'same-origin',
    redirect: 'follow',
    headers: {
      Accept: 'text/html',
      'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
    },
    body: new URLSearchParams(values),
    signal
  });

  if (!response.ok) {
    throw new Error(`Richiesta non riuscita (${response.status})`);
  }

  return response;
}

function follow(response) {
  window.location.assign(response.url);
}

export async function startTest(testId, signal) {
  follow(await postForm(`/test/${encodeURIComponent(testId)}/inizia`, {}, signal));
}

export async function saveAnswer(testId, questionNumber, answer, signal) {
  follow(await postForm(
    `/test/${encodeURIComponent(testId)}/domanda/${questionNumber}`,
    { answer: String(answer) },
    signal
  ));
}
