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
const nameRegex = /^[a-zA-Z-,]+(\s{0,1}[a-zA-Z-, ])*$/;
const firstNameInput = document.querySelector(".first-field input");
const lastNameInput = document.querySelector(".last-field input");

// var isValidFirstName;
// var isValidLastName;
// var isValidPhone;
// var isValidEmail;
// var isValidPassword;
// var isValidConfirmPassword;

const submitBtn = document.getElementById("submit-btn");


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
        // isValidPassword = passwordInput;
        // isValidConfirmPassword = confirmPasswordInput;
    }
})

// Check if the email matches regex pattern
emailInput.addEventListener("keyup", function() {
    const isValid = emailRegex.test(emailInput.value);

    if(isValid){
        document.getElementById('email-msg').innerHTML='';
        // isValidEmail = emailInput;

    } else {
        document.getElementById('email-msg').innerHTML='Email not valid, enter a valid email';
    }
})

// Check if the email matches regex pattern
phoneInput.addEventListener("keyup", function() {
    const isValid = phoneRegex.test(phoneInput.value);

    if(isValid){
        document.getElementById('phone-msg').innerHTML='';
        // isValidPhone = phoneInput;

    } else {
        document.getElementById('phone-msg').innerHTML='Phone number not valid, enter a valid phone number';
    }
})

// Check if first name matches regex pattern
firstNameInput.addEventListener("keyup", function() {
    const isValid = nameRegex.test(firstNameInput.value);

    if(isValid){
        document.getElementById('first-msg').innerHTML='';
        // isValidFirstName = firstNameInput;

    } else {
        document.getElementById('first-msg').innerHTML='Your name doesn\'t match correct pattern, enter a valid name';
    }
})

// Check if first name matches regex pattern
lastNameInput.addEventListener("keyup", function() {
    const isValid = nameRegex.test(lastNameInput.value);

    if(isValid){
        document.getElementById('last-msg').innerHTML='';
        //  isValidLastName = lastNameInput;

    } else {
        document.getElementById('last-msg').innerHTML='Your name doesn\'t match correct pattern, enter a valid name';
    }
})

document.cookie = emailInput.value;
submitBtn.addEventListener('click', async _ => {
    try {
        const response = await fetch('http://localhost:8081/register?firstName=' + encodeURI(firstNameInput.value) 
        + '&lastName=' + encodeURI(lastNameInput.value) 
        + '&contactNumber=' + encodeURI(phoneInput.value) 
        + '&emailAddress=' + encodeURI(emailInput.value) 
        + '&password=' + encodeURI(passwordInput.value) 
        + '&confirmPassword=' + encodeURI(confirmPasswordInput.value), {
            method: "POST",
            mode: "no-cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json; charset=ascii"
            }
    });
        //Can't get it to read the response, skipping past this problem will come back if I have time.
        if (response = "User Registration Successful") {
            //would go to next webpage        
            window.location.href = "/FrontEndCoding/homepage.html";
        } else {
            //show the error message below submit?
            document.getElementById('submit-msg').innerHTML='The email already exists';
        }
    } catch (err) {
        console.error(`Error: ${err}`);
    }
})

// function sendUserData () {
//     fetch('http://localhost:8081/register?firstName=' + encodeURI(firstNameInput.value) 
//             + '&lastName=' + encodeURI(lastNameInput.value) 
//             + '&contactNumber=' + encodeURI(phoneInput.value) 
//             + '&emailAddress=' + encodeURI(emailInput.value) 
//             + '&password=' + encodeURI(passwordInput.value) 
//             + '&confirmPassword=' + encodeURI(confirmPasswordInput.value), {
//                 method: "POST",
//                 mode: "no-cors",
//                 credentials: "include",
//                 headers: {
//                     "Content-Type": "application/json; charset=ascii"
//                 }}
//     )
//         .then((response) => {
//             if (!response.ok) {
//                 throw new Error("Network response was not ok");
//             }
//             var res = respons.json();
//             return res;
//         })
// }

// submitBtn.addEventListener('click', sendUserData(res) {
//     if (res = "Data saved") {
//         //need to transfer email to next page
//     } else {
//         // We let a message saying the email already exists 
//     }
// })