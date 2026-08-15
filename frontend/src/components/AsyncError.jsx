export default function AsyncError({ message }) {
  return message ? <p className="form-error" role="alert">{message}</p> : null;
}
