(() => {
    const searchInput = document.querySelector('#test-search-input');
    const testCards = [...document.querySelectorAll('[data-test-card]')];
    const testCount = document.querySelector('[data-test-count]');
    const emptyMessage = document.querySelector('[data-test-empty]');

    if (!searchInput || testCards.length === 0) {
        return;
    }

    const normalize = (value) => value
        .toLocaleLowerCase('it')
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .trim();

    const filterTests = () => {
        const query = normalize(searchInput.value);
        let visibleTests = 0;

        testCards.forEach((card) => {
            const matches = normalize(card.dataset.testTitle || '').includes(query);
            card.hidden = !matches;
            if (matches) {
                visibleTests += 1;
            }
        });

        if (testCount) {
            testCount.textContent = `${visibleTests} test`;
        }
        if (emptyMessage) {
            emptyMessage.hidden = visibleTests !== 0;
        }
    };

    searchInput.addEventListener('input', filterTests);
})();
