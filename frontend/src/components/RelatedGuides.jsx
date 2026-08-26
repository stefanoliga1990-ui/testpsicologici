import RelatedLinksPanel from './RelatedLinksPanel';

export default function RelatedGuides({ className, relatedGuides, topicCluster }) {
  return (
    <RelatedLinksPanel
      actionLabel="Leggi l'approfondimento"
      className={className}
      cluster={topicCluster}
      contextHref={`/approfondimenti#cluster-${topicCluster?.slug}`}
      items={relatedGuides}
      title="Approfondimenti collegati"
      urlFor={(guide) => `/approfondimenti/${guide.slug}`}
    />
  );
}
