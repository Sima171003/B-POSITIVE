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

function getOTP(otpContainers){
    const inputs = otpContainers.querySelectorAll(".otp");
    let otp = "";

    inputs.forEach(input => {
        otp += input.value;
    });

    return otp;

}
function getEmail(formBox){
    return formBox.querySelector('input[type="email"]').value;
}

function getName(formBox){
    return formBox.querySelector('input[type="text"]').value;
}

const registerForm = document.querySelector(".register-fields"); 
registerForm.addEventListener("submit", async (e) => { 
    e.preventDefault(); 
    const formBox = registerForm.closest(".form-box"); 
    const email = getEmail(formBox); 
    const name = getName(formBox);

    if(!name){ 
        alert("Enter Your Name"); 
        return; 
    } 

    if(!email){ 
        alert("Enter Your Email"); 
        return; 
    } 
    
    try{ 
        const res = await fetch("http://localhost:8080/auth/request-otp", { 
            method: "POST", 
            headers: {
                "Content-Type": "application/json"
            }, 
            body: JSON.stringify({ name ,email }) 
        }); 
        
        const data = await res.json(); 
        
        if(res.ok){ alert(data.message); 
            formBox.classList.add("show-otp"); 
        } else { 
            alert(data.error); 
        } 
    } catch(err){ 
        console.error(err); 
        alert("Server error. Try again later.");
    } 
});

const verifyButton = document.querySelectorAll(".verifyOTP");

verifyButton.forEach(verifyBtn => {
    verifyBtn.addEventListener("click", async () => {
        const otpContainer = verifyBtn.closest(".otp-container");
        const formBox = verifyBtn.closest(".form-box");

        const email = formBox.querySelector('input[type="email"]').value;
        // const nameInput = formBox.querySelector('input[type="text"]');
        const otp = getOTP(otpContainer);

        if(!otp || otp.length < 4){
            alert("Enter the 4-digit OTP");
            return;
        }

        try{

            const res = await fetch('http://localhost:8080/auth/verify-otp', {
                method : "POST",
                headers : { "Content-Type": "application/json" },
                body: JSON.stringify({email ,otp})
            });

            const data = await res.json();

            if(res.ok){
                alert("Registration Succesfully");
                window.parent.location.href = "./user-donorPortal.html" //testing the registrstion (temporary)
            }else{
                alert(data.error || "Invalid OTP. Try again.");
            }
        } catch(err){
            console.error(err);
            alert("Server error. Try again later.");
        }

    });
});