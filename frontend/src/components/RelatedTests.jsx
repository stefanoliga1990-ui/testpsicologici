import RelatedLinksPanel from './RelatedLinksPanel';

export default function RelatedTests({ className, relatedTests, topicCluster }) {
  return (
    <RelatedLinksPanel
      actionLabel="Apri il test"
      className={className}
      cluster={topicCluster}
      contextHref={`/#cluster-${topicCluster?.slug}`}
      items={relatedTests}
      title="Test correlati"
      urlFor={(test) => `/test/${test.id}`}
    />
  );
}
