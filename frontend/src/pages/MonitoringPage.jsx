import { useEffect, useMemo, useState } from 'react';
import Brand from '../components/Brand';
import InstagramLink from '../components/InstagramLink';

const ranges = [7, 30, 90, 365];

function formatDate(date) {
  return new Intl.DateTimeFormat('it-IT', { day: '2-digit', month: 'short' })
    .format(new Date(`${date}T00:00:00`));
}

function formatLongDate(date) {
  return new Intl.DateTimeFormat('it-IT', {
    weekday: 'long', day: 'numeric', month: 'long', year: 'numeric'
  }).format(new Date(`${date}T00:00:00`));
}

function formatUpdatedAt(value) {
  return new Intl.DateTimeFormat('it-IT', {
    hour: '2-digit', minute: '2-digit', second: '2-digit'
  }).format(new Date(value));
}

function DailyChart({ days, valueKey, unitLabel, ariaLabel }) {
  const width = 960;
  const height = 330;
  const padding = { top: 24, right: 22, bottom: 52, left: 48 };
  const chartWidth = width - padding.left - padding.right;
  const chartHeight = height - padding.top - padding.bottom;
  const maxValue = Math.max(1, ...days.map((day) => day[valueKey]));
  const barSlot = chartWidth / Math.max(days.length, 1);
  const barWidth = Math.max(2, Math.min(24, barSlot * 0.68));
  const labelEvery = Math.max(1, Math.ceil(days.length / 8));
  const gridValues = [0, .25, .5, .75, 1].map((ratio) => Math.ceil(maxValue * ratio));

  return (
    <div className="monitoring-chart-scroll">
      <svg className="monitoring-chart" viewBox={`0 0 ${width} ${height}`} role="img"
        aria-label={ariaLabel}>
        {gridValues.map((value) => {
          const y = padding.top + chartHeight - (value / maxValue) * chartHeight;
          return (
            <g key={value}>
              <line x1={padding.left} x2={width - padding.right} y1={y} y2={y} />
              <text x={padding.left - 10} y={y + 4} textAnchor="end">{value}</text>
            </g>
          );
        })}
        {days.map((day, index) => {
          const value = day[valueKey];
          const barHeight = (value / maxValue) * chartHeight;
          const x = padding.left + index * barSlot + (barSlot - barWidth) / 2;
          const y = padding.top + chartHeight - barHeight;
          const showLabel = index % labelEvery === 0 || index === days.length - 1;
          return (
            <g className="monitoring-bar" key={day.date}>
              <title>{`${formatLongDate(day.date)}: ${value} ${unitLabel}`}</title>
              <rect x={x} y={y} width={barWidth} height={Math.max(barHeight, value ? 2 : 0)} rx="4" />
              {showLabel && <text className="monitoring-axis-date" x={x + barWidth / 2}
                y={height - 19} textAnchor="middle">{formatDate(day.date)}</text>}
            </g>
          );
        })}
      </svg>
    </div>
  );
}

export default function MonitoringPage({
  initialSnapshot, initialTestCompletions = [], csrfParameterName, csrfToken
}) {
  const [range, setRange] = useState(30);
  const [snapshot, setSnapshot] = useState(initialSnapshot);
  const [error, setError] = useState('');
  const [testCompletions, setTestCompletions] = useState(initialTestCompletions);
  const [selectedTestId, setSelectedTestId] = useState(null);
  const [testRange, setTestRange] = useState(30);
  const [testSnapshot, setTestSnapshot] = useState(null);
  const [testError, setTestError] = useState('');
  const total = useMemo(
    () => snapshot.days.reduce((sum, day) => sum + day.visitors, 0),
    [snapshot.days]
  );

  useEffect(() => {
    let active = true;
    async function refresh() {
      try {
        const response = await fetch(`/monitoring/api/visite?days=${range}`, {
          credentials: 'same-origin', cache: 'no-store'
        });
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const data = await response.json();
        if (active) {
          setSnapshot(data);
          setError('');
        }
      } catch {
        if (active) setError('Aggiornamento non riuscito. Riproverò automaticamente.');
      }
    }
    refresh();
    const timer = window.setInterval(refresh, 15000);
    return () => {
      active = false;
      window.clearInterval(timer);
    };
  }, [range]);

  useEffect(() => {
    let active = true;
    async function refreshTestList() {
      try {
        const response = await fetch('/monitoring/api/test-completamenti', {
          credentials: 'same-origin', cache: 'no-store'
        });
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const data = await response.json();
        if (active) setTestCompletions(data);
      } catch {
        // Il grafico visitatori deve restare utilizzabile anche se questa sezione non risponde.
      }
    }
    const timer = window.setInterval(refreshTestList, 15000);
    return () => {
      active = false;
      window.clearInterval(timer);
    };
  }, []);

  useEffect(() => {
    if (!selectedTestId) {
      setTestSnapshot(null);
      setTestError('');
      return undefined;
    }
    let active = true;
    async function refreshTestChart() {
      try {
        const response = await fetch(
          `/monitoring/api/test-completamenti/${encodeURIComponent(selectedTestId)}?days=${testRange}`,
          { credentials: 'same-origin', cache: 'no-store' }
        );
        if (!response.ok) throw new Error(`HTTP ${response.status}`);
        const data = await response.json();
        if (active) {
          setTestSnapshot(data);
          setTestError('');
        }
      } catch {
        if (active) setTestError('Dati del test non disponibili. Riproverò automaticamente.');
      }
    }
    setTestSnapshot(null);
    refreshTestChart();
    const timer = window.setInterval(refreshTestChart, 15000);
    return () => {
      active = false;
      window.clearInterval(timer);
    };
  }, [selectedTestId, testRange]);

  return (
    <main className="monitoring-shell">
      <header className="monitoring-nav">
        <Brand />
        <div className="monitoring-actions">
          <InstagramLink />
          <form action="/monitoring/logout" method="post">
            <input type="hidden" name={csrfParameterName} value={csrfToken} />
            <button type="submit">Esci</button>
          </form>
        </div>
      </header>

      <section className="monitoring-heading">
        <div><p className="eyebrow">Accesso riservato</p><h1>Monitoraggio visite</h1></div>
        <p>Aggiornato alle {formatUpdatedAt(snapshot.generatedAt)}</p>
      </section>

      <section className="monitoring-summary" aria-live="polite">
        <article className="monitoring-today">
          <p>Visitatori distinti oggi</p>
          <strong>{snapshot.todayVisitors.toLocaleString('it-IT')}</strong>
          <span>{formatLongDate(snapshot.today)}</span>
        </article>
        <article>
          <p>Totale nel periodo</p>
          <strong>{total.toLocaleString('it-IT')}</strong>
          <span>Somma dei conteggi giornalieri</span>
        </article>
      </section>

      <section className="monitoring-panel">
        <div className="monitoring-panel-heading">
          <div><p className="eyebrow">Andamento</p><h2>Visitatori per giorno</h2></div>
          <div className="monitoring-ranges" aria-label="Intervallo del grafico">
            {ranges.map((days) => (
              <button className={range === days ? 'active' : ''} type="button" key={days}
                aria-pressed={range === days} onClick={() => setRange(days)}>
                {days === 365 ? '1 anno' : `${days} gg`}
              </button>
            ))}
          </div>
        </div>
        {error && <p className="monitoring-error" role="status">{error}</p>}
        <DailyChart days={snapshot.days} valueKey="visitors" unitLabel="visitatori"
          ariaLabel="Grafico dei visitatori distinti giornalieri" />
      </section>

      <section className="monitoring-tests-panel">
        <div className="monitoring-panel-heading">
          <div>
            <p className="eyebrow">Questionari conclusi</p>
            <h2>Completamenti per test</h2>
          </div>
          <p>Seleziona un test per aprire il grafico giornaliero.</p>
        </div>
        <div className="monitoring-test-list">
          {testCompletions.map((testCompletion) => {
            const selected = selectedTestId === testCompletion.testId;
            return (
              <button className={selected ? 'active' : ''} type="button"
                key={testCompletion.testId} aria-expanded={selected}
                onClick={() => setSelectedTestId(selected ? null : testCompletion.testId)}>
                <span className="monitoring-test-name">{testCompletion.testTitle}</span>
                <span className="monitoring-test-counts">
                  <span><strong>{testCompletion.todayCompletions}</strong> oggi</span>
                  <span><strong>{testCompletion.totalCompletions}</strong> totali</span>
                </span>
                <span className="monitoring-test-chevron" aria-hidden="true">{selected ? '−' : '+'}</span>
              </button>
            );
          })}
        </div>
      </section>

      {selectedTestId && (
        <section className="monitoring-panel monitoring-test-chart-panel">
          <div className="monitoring-panel-heading">
            <div>
              <p className="eyebrow">Andamento del test</p>
              <h2>{testSnapshot?.testTitle
                || testCompletions.find((item) => item.testId === selectedTestId)?.testTitle}</h2>
            </div>
            <div className="monitoring-ranges" aria-label="Intervallo del grafico completamenti">
              {ranges.map((days) => (
                <button className={testRange === days ? 'active' : ''} type="button" key={days}
                  aria-pressed={testRange === days} onClick={() => setTestRange(days)}>
                  {days === 365 ? '1 anno' : `${days} gg`}
                </button>
              ))}
            </div>
          </div>
          {testError && <p className="monitoring-error" role="status">{testError}</p>}
          {testSnapshot
            ? <>
              <div className="monitoring-test-chart-summary">
                <span><strong>{testSnapshot.todayCompletions}</strong> completati oggi</span>
                <span><strong>{testSnapshot.totalCompletions}</strong> completati dall’attivazione</span>
              </div>
              <DailyChart days={testSnapshot.days} valueKey="completions"
                unitLabel="completamenti" ariaLabel={`Grafico dei completamenti giornalieri di ${testSnapshot.testTitle}`} />
            </>
            : !testError && <p className="monitoring-loading" role="status">Caricamento del grafico…</p>}
        </section>
      )}

      <section className="monitoring-table-panel">
        <h2>Dettaglio giornaliero</h2>
        <div className="monitoring-table-scroll">
          <table>
            <thead><tr><th>Data</th><th>Visitatori distinti</th></tr></thead>
            <tbody>{[...snapshot.days].reverse().map((day) => (
              <tr key={day.date}><td>{formatLongDate(day.date)}</td><td>{day.visitors}</td></tr>
            ))}</tbody>
          </table>
        </div>
      </section>
    </main>
  );
}
