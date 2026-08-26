(() => {
    const searchInput = document.querySelector('#guide-search-input');
    const guideCards = [...document.querySelectorAll('[data-guide-card]')];
    const guideCount = document.querySelector('[data-guide-count]');
    const emptyMessage = document.querySelector('[data-guide-empty]');
    const topicClusters = [...document.querySelectorAll('[data-topic-cluster]')];

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

        topicClusters.forEach((cluster) => {
            const visibleCards = [...cluster.querySelectorAll('[data-guide-card]')]
                .filter((card) => !card.hidden).length;
            cluster.hidden = visibleCards === 0;
            const clusterCount = cluster.querySelector('[data-cluster-count]');
            if (clusterCount) {
                const label = visibleCards === 1 ? 'guida' : 'guide';
                clusterCount.textContent = `${visibleCards} ${label}`;
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
    filterGuides();
})();
