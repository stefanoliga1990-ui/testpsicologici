import { Component } from 'react';
import GuidePage from '../pages/GuidePage';
import GuidesPage from '../pages/GuidesPage';
import HomePage from '../pages/HomePage';
import IntroductionPage from '../pages/IntroductionPage';
import MethodPage from '../pages/MethodPage';
import ProjectPage from '../pages/ProjectPage';
import QuestionPage from '../pages/QuestionPage';
import ResultPage from '../pages/ResultPage';

const pages = {
  guide: GuidePage,
  guides: GuidesPage,
  home: HomePage,
  introduction: IntroductionPage,
  method: MethodPage,
  project: ProjectPage,
  question: QuestionPage,
  result: ResultPage
};

class ErrorBoundary extends Component {
  constructor(props) {
    super(props);
    this.state = { failed: false };
  }

  static getDerivedStateFromError() {
    return { failed: true };
  }

  render() {
    if (this.state.failed) {
      return (
        <main className="react-error" role="alert">
          <h1>Non è stato possibile mostrare questa pagina.</h1>
          <p>Ricarica la pagina oppure torna alla home.</p>
          <a className="button button-primary" href="/">Torna alla home</a>
        </main>
      );
    }
    return this.props.children;
  }
}

function PageRenderer({ pageData }) {
  const Page = pages[pageData.page];

  if (!Page) {
    throw new Error(`Pagina React non supportata: ${pageData.page}`);
  }

  return <Page {...pageData} />;
}

export default function App({ pageData }) {
  return (
    <ErrorBoundary>
      <PageRenderer pageData={pageData} />
    </ErrorBoundary>
  );
}
