/*=============== SHOW MODAL ===============*/
const showModal = (openButton, modalContent) =>{
    const openBtn = document.getElementById(openButton),
    modalContainer = document.getElementById(modalContent)
    
    if(openBtn && modalContainer){
        openBtn.addEventListener('click', ()=>{
            modalContainer.classList.add('show-modal')
        })
    }
}

showModal('open-modal','modal-container')
showModal('open-modal-2','modal-container')

/*=============== CLOSE MODAL ===============*/
const closeBtn = document.querySelectorAll('.close-modal')

function closeModal(){
    const modalContainer = document.getElementById('modal-container')
    modalContainer.classList.remove('show-modal')
}

function openModalActive(){
    const modalContainer = document.getElementById("modal-container")
    modalContainer.classList.add('show-modal')
}


closeBtn.forEach(c => c.addEventListener('click', closeModal))