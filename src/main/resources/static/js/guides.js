(() => {
    const searchInput = document.querySelector('#guide-search-input');
    const guideCards = [...document.querySelectorAll('[data-guide-card]')];
    const guideCount = document.querySelector('[data-guide-count]');
    const emptyMessage = document.querySelector('[data-guide-empty]');

    if (!searchInput || guideCards.length === 0) {
        return;
    }

    const normalize = (value) => value
        .toLocaleLowerCase('it')
        .normalize('NFD')
        .replace(/[\u0300-\u036f]/g, '')
        .trim();

    const filterGuides = () => {
        const query = normalize(searchInput.value);
        let visibleGuides = 0;

        guideCards.forEach((card) => {
            const matches = normalize(card.dataset.guideTitle || '').includes(query);
            card.hidden = !matches;
            if (matches) {
                visibleGuides += 1;
            }
        });

        if (guideCount) {
            const label = visibleGuides === 1 ? 'approfondimento' : 'approfondimenti';
            guideCount.textContent = `${visibleGuides} ${label}`;
        }
        if (emptyMessage) {
            emptyMessage.hidden = visibleGuides !== 0;
        }
    };

    searchInput.addEventListener('input', filterGuides);
})();
