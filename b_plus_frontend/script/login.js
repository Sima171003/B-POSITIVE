const container = document.querySelector(".container");

const registerBtn = document.querySelector(".register-btn");
const loginBtn = document.querySelector(".login-btn")
const cross = document.querySelector(".cross");

const otpContainers = document.querySelectorAll(".otp-container");

registerBtn.addEventListener('click', () => {
    container.classList.add('active');
})


loginBtn.addEventListener('click', () => {
    container.classList.remove('active');
})

cross.addEventListener('click', () => {
    window.parent.closePopup();
})

otpContainers.forEach(container => {

    const inputs = container.querySelectorAll(".otp");

    inputs.forEach((input,index) => {
        input.addEventListener('input', () => {
            if(input.value.length === 1 && index < inputs.length - 1){
                inputs[index + 1].focus();
            }
        });

        input.addEventListener('keydown', (e) => {
            if(e.key === 'Backspace' && input.value === "" && index > 0){
                inputs[index - 1].focus();
            }
        })
    })
})

