import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './app/App';
import './styles/react.css';

const rootElement = document.getElementById('react-root');
const dataElement = document.getElementById('react-page-data');

if (!rootElement || !dataElement) {
  throw new Error('Bootstrap React non disponibile');
}

const pageData = JSON.parse(dataElement.textContent);

createRoot(rootElement).render(
  <StrictMode>
    <App pageData={pageData} />
  </StrictMode>
);

document.body.classList.add('react-mounted');
