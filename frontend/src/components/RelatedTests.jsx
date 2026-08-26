import RelatedLinksPanel from './RelatedLinksPanel';

export default function RelatedTests({ relatedTests, topicCluster }) {
  return (
    <RelatedLinksPanel
      actionLabel="Apri il test"
      cluster={topicCluster}
      contextHref={`/#cluster-${topicCluster?.slug}`}
      items={relatedTests}
      title="Test correlati"
      urlFor={(test) => `/test/${test.id}`}
    />
  );
}
