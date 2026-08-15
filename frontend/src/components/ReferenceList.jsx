export default function ReferenceList({ references }) {
  return (
    <ul>
      {references.map((reference) => (
        <li key={reference.url}>
          <a href={reference.url} target="_blank" rel="noopener noreferrer">{reference.title}</a>
          <p>{reference.contribution}</p>
        </li>
      ))}
    </ul>
  );
}
