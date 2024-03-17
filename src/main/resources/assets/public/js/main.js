function issueOnLoad() {
    initAdd();
    initEdits();
    initEditIssue();
    initConfirmDeleteComment();
    initConfirmDeleteIssue();
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

function initEditIssue() {
    let link = document.getElementById('editIssue');
    link.addEventListener('click', () => {
        openEditIssueDialog(link);
    });
}

function initConfirmDeleteComment() {
    let links = document.getElementsByClassName('deleteComment');
    for (var i = 0; links[i]; i++) {
        let link = links[i];
        link.addEventListener('click', () => {
            openConfirmDeleteCommentDialog(link);
        });
    }
}

function initConfirmDeleteIssue() {
    let link = document.getElementById('deleteIssue');
    link.addEventListener('click', () => {
        openConfirmDeleteIssueDialog();
    });
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
    let summaryText = detailsEl.getElementsByClassName('summary')[0].innerText;
    let innerText = detailsEl.getElementsByClassName('text')[0].innerText;
    let editCommentDlg = document.getElementById('editCommentDlg');
    editCommentDlg.getElementsByTagName('input').namedItem('summary').value = summaryText;
    editCommentDlg.getElementsByTagName('input').namedItem('text').value = innerText;
    editCommentDlg.getElementsByTagName('input').namedItem('comment').value = comment;
    editCommentDlg.showModal();
}

function openEditIssueDialog() {
    let title = document.getElementById('title');
    let subtitle = document.getElementById('subtitle');
    let editIssueDlg = document.getElementById('editIssueDlg');
    editIssueDlg.getElementsByTagName('input').namedItem('title').value = title;
    editIssueDlg.getElementsByTagName('input').namedItem('description').value = subtitle;
    editIssueDlg.showModal();
}

function openConfirmDeleteCommentDialog(link) {
    let detailsEl = link.closest('details');
    let comment = detailsEl.getAttribute('data-comment');
    let summaryText = detailsEl.getElementsByTagName('summary').item(0).innerText;
    let deleteCommentDlg = document.getElementById('deleteCommentDlg');
    let text = deleteCommentDlg.getElementsByTagName('p').namedItem('text');
    text.innerText = `Are you sure to delete "${summaryText}"?`;
    deleteCommentDlg.getElementsByTagName('input').namedItem('summary').value = summaryText;
    deleteCommentDlg.getElementsByTagName('input').namedItem('comment').value = comment;
    deleteCommentDlg.showModal();
}

function openConfirmDeleteIssueDialog() {
    let deleteIssueDlg = document.getElementById('deleteIssueDlg');
    deleteIssueDlg.showModal();
}