//.pass-field is the class name for the password input
const passwordInput = document.querySelector(".pass-field input")
//i is what is after the < just like the input above
const eyeIcon = document.querySelector(".pass-field i")
const requirementsList = document.querySelectorAll(".requirement-list li")

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
  
// function solve() { 
//     let password =  
//         document.getElementById('password').value; 
//     let repassword =  
//         document.getElementById('repassword').value; 
//     let mobile =  
//         document.getElementById('mobile').value; 
//     let mail = 
//         document.getElementById('email').value; 
//     let flag = 1; 
    
//     let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; 
  
//     if (!emailRegex.test(mail)) { 
//         flag = 0; 
//         pass.innerText =  
//             'Please enter a valid email address.'; 
//         setTimeout(() => { 
//             pass.innerText = ""; 
//         }, 3000); 
//     } 
  
//     if (password !== repassword) { 
//         flag = 0; 
//         pass.innerText = 
//             "Passwords do not match. Please re-enter."; 
//         setTimeout(() => { 
//             pass.innerText = ""; 
//         }, 3000); 
//     } 
  
//     let passwordRegex =  
//         /^(?=.*\d)(?=.*[a-zA-Z])(?=.*[^a-zA-Z0-9])\S{8,}$/; 
  
//     if (!passwordRegex.test(password)) { 
//         flag = 0; 
//         pass.innerText = 
//             'Password must be at least 8 characters'+ 
//             ' long and include at least one number,'+ 
//             ' one alphabet, and one symbol.'; 
//         setTimeout(() => { 
//             pass.innerText = ""; 
//         }, 3000); 
//     } 
//     let mobileRegex =  /^[0-9]{10}$/;
//     if(!mobileRegex.test(mobile)) {
//         flag=0;
//         pass.innerText = 'Please enter a valid number.';
//         setTimeout(() => { 
//             pass.innerText = ""; 
//         }, 3000);   
//     }   
//     if (flag) 
//         alert("Form submitted"); 
// }