import RelatedLinksPanel from './RelatedLinksPanel';

export default function RelatedGuides({ relatedGuides, topicCluster }) {
  return (
    <RelatedLinksPanel
      actionLabel="Leggi l'approfondimento"
      cluster={topicCluster}
      contextHref={`/approfondimenti#cluster-${topicCluster?.slug}`}
      items={relatedGuides}
      title="Approfondimenti collegati"
      urlFor={(guide) => `/approfondimenti/${guide.slug}`}
    />
  );
}
