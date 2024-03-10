function onLoad() {
    initCreate();
}

function initCreate() {
    let createDlg = document.getElementById('createDlg');
    let create = document.getElementById('create');
    create.addEventListener('click', () => {
        createDlg.showModal();
    })
}