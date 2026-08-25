import assert from 'node:assert/strict';
import test from 'node:test';
import {
  SERVER_RENDERED_FALLBACK_SELECTOR,
  removeServerRenderedFallback
} from './removeServerRenderedFallback.js';

test('removes only the explicitly marked server-rendered fallback', () => {
  let receivedSelector;
  let removed = false;
  const fallback = {
    remove() {
      removed = true;
    }
  };
  const documentRoot = {
    querySelector(selector) {
      receivedSelector = selector;
      return fallback;
    }
  };

  assert.equal(removeServerRenderedFallback(documentRoot), true);
  assert.equal(receivedSelector, SERVER_RENDERED_FALLBACK_SELECTOR);
  assert.equal(receivedSelector, 'body > main[data-react-fallback]');
  assert.equal(removed, true);
});

test('leaves the document unchanged when the fallback is absent', () => {
  const documentRoot = {
    querySelector() {
      return null;
    }
  };

  assert.equal(removeServerRenderedFallback(documentRoot), false);
});
