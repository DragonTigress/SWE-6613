

const selected = document.querySelector(".selected");
const optionsContainer = document.querySelector(".options-container");

const optionsList = document.querySelectorAll(".option");

selected.addEventListener("click", () => {
  optionsContainer.classList.toggle("active");
});

optionsList.forEach(o => {
  o.addEventListener("click", () => {
    selected.innerHTML = o.querySelector("label").innerHTML;
    optionsContainer.classList.remove("active");
  });
});





// const submitBtn = document.getElementById("submit-btn");
// submitBtn.addEventListener('click', async _ => {
//   try {
//       const response = await fetch('http://localhost:8081/storeAttributes?timeZone=EST&microPhone=Yes&ageRange=&gender=Male&consolePreference=pc&
//       consolePreference=xBox&consolePreference=nintendoSwitch&emailAddress=aditi@g
//       mail.com

          // 'http://localhost:8081/register?firstName=' + encodeURI(firstNameInput.value) 
//       + '&lastName=' + encodeURI(lastNameInput.value) 
//       + '&contactNumber=' + encodeURI(phoneInput.value) 
//       + '&emailAddress=' + encodeURI(emailInput.value) 
//       + '&password=' + encodeURI(passwordInput.value) 
//       + '&confirmPassword=' + encodeURI(confirmPasswordInput.value), {
//           method: "POST",
//           mode: "no-cors",
//           credentials: "include",
//           headers: {
//               "Content-Type": "application/json; charset=ascii"
//           }
//   });
//       console.log('Completed!', response);
//   } catch (err) {
//       console.error(`Error: ${err}`);
//   }
// })