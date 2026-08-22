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

function VisitChart({ days }) {
  const width = 960;
  const height = 330;
  const padding = { top: 24, right: 22, bottom: 52, left: 48 };
  const chartWidth = width - padding.left - padding.right;
  const chartHeight = height - padding.top - padding.bottom;
  const maxValue = Math.max(1, ...days.map((day) => day.visitors));
  const barSlot = chartWidth / Math.max(days.length, 1);
  const barWidth = Math.max(2, Math.min(24, barSlot * 0.68));
  const labelEvery = Math.max(1, Math.ceil(days.length / 8));
  const gridValues = [0, .25, .5, .75, 1].map((ratio) => Math.ceil(maxValue * ratio));

  return (
    <div className="monitoring-chart-scroll">
      <svg className="monitoring-chart" viewBox={`0 0 ${width} ${height}`} role="img"
        aria-label="Grafico dei visitatori distinti giornalieri">
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
          const barHeight = (day.visitors / maxValue) * chartHeight;
          const x = padding.left + index * barSlot + (barSlot - barWidth) / 2;
          const y = padding.top + chartHeight - barHeight;
          const showLabel = index % labelEvery === 0 || index === days.length - 1;
          return (
            <g className="monitoring-bar" key={day.date}>
              <title>{`${formatLongDate(day.date)}: ${day.visitors} visitatori`}</title>
              <rect x={x} y={y} width={barWidth} height={Math.max(barHeight, day.visitors ? 2 : 0)} rx="4" />
              {showLabel && <text className="monitoring-axis-date" x={x + barWidth / 2}
                y={height - 19} textAnchor="middle">{formatDate(day.date)}</text>}
            </g>
          );
        })}
      </svg>
    </div>
  );
}

export default function MonitoringPage({ initialSnapshot, csrfParameterName, csrfToken }) {
  const [range, setRange] = useState(30);
  const [snapshot, setSnapshot] = useState(initialSnapshot);
  const [error, setError] = useState('');
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
        <VisitChart days={snapshot.days} />
      </section>

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
