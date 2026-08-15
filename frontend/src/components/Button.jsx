export default function Button({ as = 'button', children, className = '', loading = false, ...props }) {
  const classes = `button ${className}`.trim();
  if (as === 'a') {
    return <a className={classes} {...props}>{children}</a>;
  }
  return (
    <button className={classes} {...props} disabled={loading || props.disabled}>
      {children}
      {loading && <span className="button-loader" aria-hidden="true" />}
    </button>
  );
}
