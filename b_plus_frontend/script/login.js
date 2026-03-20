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
//Register Section

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
        const res = await fetch("http://localhost:8080/auth/register/request-otp", { 
            method: "POST", 
            headers: {
                "Content-Type": "application/json"
            }, 
            body: JSON.stringify({ name ,email }) 
        }); 
        
        const data = await res.json(); 
        
        if(res.ok){ 
            alert(data.message); 
            formBox.classList.add("show-otp"); 
        } else { 
            alert(data.error); 
        } 
    } catch(err){ 
        console.error(err); 
        alert("Server error. Try again later.");
    } 
});

const registerVerifyButton = document.querySelector(".verifyRegisterOTP");
registerVerifyButton.addEventListener("click", async () => {
    const otpContainer = registerVerifyButton.closest(".otp-container");
    const formBox = registerVerifyButton.closest(".form-box");

    const email = formBox.querySelector('input[type="email"]').value;
    // const nameInput = formBox.querySelector('input[type="text"]');
    const otp = getOTP(otpContainer);

    if(!otp || otp.length !== 4){
        alert("Enter the 4-digit OTP");
        return;
    }

    try{

        const res = await fetch('http://localhost:8080/auth/register/verify-otp', {
            method : "POST",
            headers : { "Content-Type": "application/json" },
            body: JSON.stringify({email ,otp})
        });

        const data = await res.json();

        if(data.success){
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

// Login section
const loginForm = document.querySelector(".login-fields");
loginForm.addEventListener("submit", async (e) =>{
    e.preventDefault();

    const formBox = loginForm.closest(".form-box");
    const email = getEmail(formBox);
    const role = getName(formBox);

    if(!email){
        alert("Enter A Email");
        return;
    }

    if(!role){
        alert("Enter A Valid Role");
        return;
    }

    try{

        const res = await fetch('http://localhost:8080/auth/login/request-otp', {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({ email,role })
        })

        const data = await res.json();

        if(data.success){
            alert(data.message);
            formBox.classList.add("show-otp");
        } else {
            alert(data.message);
        }
    }catch(err){
        console.error(err);
        alert("Server Error");
    }
});

const loginVerifyButton = document.querySelector(".verifyLoginOTP");
loginVerifyButton.addEventListener("click", async () => {

    const formBox = loginVerifyButton.closest(".form-box");
    const otpContainer = loginVerifyButton.closest(".otp-container");

    const email = getEmail(formBox);
    const role = getName(formBox);
    const otp = getOTP(otpContainer);

    if(!otp || otp.length !== 4){
        alert("Enter Your 4-digit OTP");
        return;
    }

    try {

        const res = await fetch('http://localhost:8080/auth/login/verify-otp', {
            method: "POST",
            headers: { "Content-Type": "application/json"},
            body: JSON.stringify({ email,role,otp })
        });

        const data = await res.json();
        if(data.success){
            alert("Login Succesful")
            if(data.role && data.role.toLower() == "volunteer"){
                window.parent.location.href = "./volunteerPortal.html";
            } else{
                window.parent.location.href = "./user-donorPortal.html";
            }
        }else{
            alert(data.message);
        }

    } catch(err){
        console.error(err);
        alert("Server Error");
    }
});