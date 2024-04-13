const loginBtn = document.getElementById("login-btn");
const emailInput = document.querySelector(".email-field input");
const passwordInput = document.querySelector(".pass-field input");


document.cookie = encodeURI(emailInput.value)

loginBtn.addEventListener('click', async _ => {
    try {
        const response = await fetch('http://localhost:8081/login?emailAddress=' + encodeURI(emailInput.value) 
        + '&password=' + encodeURI(passwordInput.value), {
            method: "POST",
            mode: "no-cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json; charset=ascii"
            }
    });
        console.log('Completed!', response);
        return response;
    } catch (err) {
        console.error(`Error: ${err}`);
    }
})