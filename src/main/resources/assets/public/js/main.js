function issueOnLoad() {
    initAdd();
    initEdits();
}

function issuesOnLoad() {
    initCreate();
}

function initAdd() {
    let addCommentDlg = document.getElementById('addCommentDlg');
    let add = document.getElementById('addCommentBottom');
    add.addEventListener('click', () => {
        addCommentDlg.showModal();
    });
}

function initEdits() {
    let links = document.getElementsByClassName('editComment');
    for (var i = 0; links[i]; i++) {
        let link = links[i];
        link.addEventListener('click', () => {
            openEditCommentDialog(link);
        });
    }
}

function initCreate() {
    let createDlg = document.getElementById('createDlg');
    let create = document.getElementById('create');
    create.addEventListener('click', () => {
        createDlg.showModal();
    });
}

function openEditCommentDialog(link) {
    let detailsEl = link.closest('details');
    let comment = detailsEl.getAttribute('data-comment');
    let summaryText = detailsEl.getElementsByTagName('summary').item(0).innerText;
    let innerText = detailsEl.getElementsByTagName('p').item(0).innerText;
    let editCommentDlg = document.getElementById('editCommentDlg');
    editCommentDlg.getElementsByTagName('input').namedItem('summary').value = summaryText;
    editCommentDlg.getElementsByTagName('input').namedItem('text').value = innerText;
    editCommentDlg.getElementsByTagName('input').namedItem('comment').value = comment;
    editCommentDlg.showModal();
}