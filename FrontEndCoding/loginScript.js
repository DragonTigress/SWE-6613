const loginBtn = document.getElementById("login-btn");
const emailInput = document.querySelector(".email-field input");
const passwordInput = document.querySelector(".pass-field input");



loginBtn.addEventListener('click', async _ => {
    try {
        const response = await fetch('http://localhost:8081/login?=emailAddress' + encodeURI(emailInput.value) 
        + '&password=' + encodeURI(passwordInput.value), {
            method: "POST",
            mode: "no-cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json; charset=ascii"
            }
    });
        console.log('Completed!', response);
    } catch (err) {
        console.error(`Error: ${err}`);
    }
})