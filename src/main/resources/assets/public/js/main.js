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
    let editCommentDlg = document.getElementById('editCommentDlg');
    editCommentDlg.showModal();
}