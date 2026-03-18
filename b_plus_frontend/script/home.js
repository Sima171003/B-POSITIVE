const popUp = document.querySelector("#loginPop");

const log = document.querySelector(".log");

log.addEventListener('click', () => {
    popUp.classList.add('active');
})

function closePopup(){
    popUp.classList.remove('active');
}