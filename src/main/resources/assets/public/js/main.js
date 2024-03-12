function issueOnLoad() {
    initAdd();
}

function issuesOnLoad() {
    initCreate();
}

function initAdd() {
    let commentDlg = document.getElementById('commentDlg');
    let add = document.getElementById('add');
    add.addEventListener('click', () => {
        commentDlg.showModal();
    });
}

function initCreate() {
    let createDlg = document.getElementById('createDlg');
    let create = document.getElementById('create');
    create.addEventListener('click', () => {
        createDlg.showModal();
    });
}