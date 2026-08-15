import { useCallback, useEffect, useRef, useState } from 'react';

export default function useAsyncAction(action) {
  const controllerRef = useRef(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  useEffect(() => () => controllerRef.current?.abort(), []);

  const run = useCallback(async (...args) => {
    controllerRef.current?.abort();
    controllerRef.current = new AbortController();
    setLoading(true);
    setError('');
    try {
      await action(...args, controllerRef.current.signal);
    } catch (requestError) {
      if (requestError.name !== 'AbortError') {
        setError('Si è verificato un problema. Controlla la connessione e riprova.');
        setLoading(false);
      }
    }
  }, [action]);

  return { error, loading, run };
}
