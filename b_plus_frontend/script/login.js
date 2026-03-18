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

function getEmail(formBox){
    return formBox.querySelector('input[type="email"]').value;
}
function getOTP(otpContainer){
    const inputs = otpContainer.querySelectorAll('.otp');

    let otp = "";

    inputs.forEach(input => {
        otp += input.value
    })

    return otp;
}
const registerForm = document.querySelector(".register-fields");
registerForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const formBox = registerForm.closest(".form-box");
    const name = formBox.querySelector('input[type="text"]').value;
    const email = getEmail(formBox);

    if(!name || !email){
        alert("Enter Your Email");
        return;
    }

    let users = JSON.parse(localStorage.getItem("users")) || [];

    const exists = users.find(user => user.email === email);

    if(exists){
        alert("User already Exist");
        return;
    }

    users.push({
        name: name,
        email: email,
        role: "User"   // default role
    });

    localStorage.setItem("users", JSON.stringify(users));

    alert("Registered successfully!");

    try{

        const res = await fetch("http://localhost:3000/request-otp", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({email})
        });

        const data = await res.json();
        if(res.ok){
            alert(data.message);
            formBox.classList.add("show-otp");
        } else {
            alert(data.error);
        }

    }catch(err){
        console.error(err);
        alert("Server error. Try again later.");
    }


});

const loginForm = document.querySelector(".login-fields");

loginForm.addEventListener("submit", async (e) => {
    e.preventDefault();

    const formBox = loginForm.closest(".form-box");
    const role = formBox.querySelector('input[type="text"]').value;
    const email = getEmail(formBox);

    if(role.trim() === "" || email.trim() === ""){
        alert("Give Email and Role");
        return;
    }

    let users = JSON.parse(localStorage.getItem("users")) || [];

    users = [
        {
            name: "Demo User",
            email: "user@gmail.com",
            role: "User"
        },
        {
            name: "Demo Volunteer",
            email: "pratiknaskar.11@gmail.com",
            role: "Volunteer"
        }
    ];

    localStorage.setItem("users", JSON.stringify(users));

    const user = users.find(u => u.email === email && u.role.toLowerCase() === role.toLowerCase());

    if(!user){
        alert("User not registered");
        return;
    }

    alert("Login successful");

    try{

        const res = await fetch("http://localhost:3000/request-otp", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({email})
        });

        const data = await res.json();
        if(res.ok){
            alert(data.message);
            formBox.classList.add("show-otp");
        } else {
            alert(data.error);
        }
    }catch(err){
        console.error(err);
        alert("Server error. Try again later.");
    }

})

otpContainers.forEach(container => {
    const verifyBtn = container.querySelector(".verifyOTP");
    verifyBtn.addEventListener("click", async () => {
        const formBox = container.closest(".form-box");
        const email = getEmail(formBox);
        const otp = getOTP(container);

        if(otp.length < 4){ alert("Enter 4-digit OTP"); return; }

        try {
            const res = await fetch("http://localhost:3000/verify-otp", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email, otp })
            });
            const data = await res.json();
            if(res.ok){
                alert(data.message);
                if(formBox.classList.contains("register")){
                    window.parent.location.href = "./user-donorPortal.html";
                }

                if(formBox.classList.contains("login")){
                    let users = JSON.parse(localStorage.getItem("users")) || [];
                    const user = users.find(u => u.email === email);

                    if(user.role === "Volunteer"){
                        window.parent.location.href = "./volunteerPortal.html";
                    }else{
                        window.parent.location.href = "./user-donorPortal.html";
                    }
                }
            } else {
                alert(data.error);
            }
        } catch(err) {
            console.error(err);
            alert("Server error. Try again later.");
        }
    });

    const resendBtn = container.querySelector(".resendOTP");
    resendBtn.addEventListener("click", async () => {
        const formBox = container.closest(".form-box");
        const email = getEmail(formBox);
        if(!email){ alert("Enter your email first!"); return; }

        try {
            const res = await fetch("http://localhost:3000/request-otp", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ email })
            });
            const data = await res.json();
            if(res.ok) alert(data.message);
            else alert(data.error);
        } catch(err) {
            console.error(err);
            alert("Server error. Try again later.");
        }
    });
});
