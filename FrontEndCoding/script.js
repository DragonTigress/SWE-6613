//.pass-field is the class name for the password input
const passwordInput = document.querySelector(".pass-field input");
//i is what is after the < just like the input above
const eyeIcon = document.querySelector(".pass-field i");
const requirementsList = document.querySelectorAll(".requirement-list li");
//.confirm-pass is the class name for the confirm password input
const confirmPasswordInput = document.querySelector(".confirm-pass input");
//.email-field is the class name for email input
const emailInput = document.querySelector(".email-field input");
const emailRegex = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
//.phone-field is the class name for phone input 
const phoneInput = document.querySelector(".phone-field input");
const phoneRegex = /^[0-9]{10}$/;


const requirements = [
    {regex: /.{8,}/, index: 0}, // Minimum 8 characters long
    {regex: /[0-9]/, index: 1}, // At least one number
    {regex: /[a-z]/, index: 2}, // At least one lowercase letter
    {regex: /[^A-Za-z0-9]/, index: 3}, // At least one special character
    {regex: /[A-Z]/, index: 4}, //At least one uppercase letter
]
passwordInput.addEventListener("keyup", (e) => {
    requirements.forEach(item => {
        // Check if the password matches the requirements regex
        const isValid = item.regex.test(e.target.value);
        const requirementItem = requirementsList[item.index];

        if(isValid) {
            requirementItem.firstElementChild.className = "fa-solid fa check";
            requirementItem.classList.add("valid");
        } else {
            requirementItem.firstElementChild.className = "fa-solid fa-circle";
            requirementItem.classList.remove("valid");
        }
    })
})

eyeIcon.addEventListener("click", () => {
    //Toggle the password input type between "password" and "text"
    passwordInput.type = passwordInput.type === "password" ? "text" : "password"

    //Update the eye icon class based on the password input type
    eyeIcon.className = `fa-solid fa-eye${passwordInput.type === "password" ? "" : "-slash"}`;
})

// Check if the passwords match
confirmPasswordInput.addEventListener("keyup", function() {

    if((passwordInput.value != confirmPasswordInput.value) && (confirmPasswordInput.value != '')){
        document.getElementById('error-msg').innerHTML='Passwords don\'t match, re-enter passwords';
    } else {
        document.getElementById('error-msg').innerHTML='';
    }
})

// Check if the email matches regex pattern
emailInput.addEventListener("keyup", function() {
    const isValid = emailRegex.test(emailInput.value);

    if(isValid){
        document.getElementById('email-msg').innerHTML='';

    } else {
        document.getElementById('email-msg').innerHTML='Email not valid, enter a valid email';
    }
})

// Check if the email matches regex pattern
phoneInput.addEventListener("keyup", function() {
    const isValid = phoneRegex.test(phoneInput.value);

    if(isValid){
        document.getElementById('phone-msg').innerHTML='';

    } else {
        document.getElementById('phone-msg').innerHTML='Phone number not valid, enter a valid phone number';
    }
})