document.querySelectorAll('[data-reviewer-dialog-open]').forEach((button) => {
    button.addEventListener('click', () => {
        const dialog = document.getElementById(button.dataset.reviewerDialogOpen);
        if (dialog && typeof dialog.showModal === 'function' && !dialog.open) {
            dialog.showModal();
        }
    });
});
