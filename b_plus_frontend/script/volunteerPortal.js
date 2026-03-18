const openMenu = document.querySelector(".bx-user-circle");
const accountMenu = document.querySelector(".account-menu");

const closeMenu = document.querySelector(".cut");

openMenu.addEventListener('click', () => {
    accountMenu.classList.add('active');
})

closeMenu.addEventListener('click', () => {
    accountMenu.classList.remove('active');
})