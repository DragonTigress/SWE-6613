const timeZone = document.getElementById("timezone");
const ageRange = document.getElementById("age-range");
const selected = document.querySelector(".selected");
var genderType;
var consolePreference = [];
let emailUser = document.cookie;
const submitBtn = document.getElementById("submit-btn");
const optionsContainer = document.querySelector(".options-container");

// const optionsList = document.querySelectorAll(".option");

// selected.addEventListener("click", () => {
//   optionsContainer.classList.toggle("active");
// });

// optionsList.forEach(o => {
//   o.addEventListener("click", () => {
//     selected.innerHTML = o.querySelector("label").innerHTML;
//     optionsContainer.classList.remove("active");
//   });
// });

// if (document.getElementById('microphone-yes').checked) {
//    const microPhone = "yes";
// } else if (document.getElementById('microphone-no').checked) {
//    const microPhone = "no";
// }

// if (document.getElementById('male').checked) {
//   genderType = "male";
// } else if (document.getElementById('female').checked) {
//   genderType = "female";
// } else if (document.getElementById('other').checked) {
//   genderType = "other";
// }

// if (document.getElementById('ps4').checked) {
//   consolePreference.push('ps4');
// }

// if (document.getElementById('xbox').checked) {
//   consolePreference.push('xBox');
// }

// if (document.getElementById('switch').checked) {
//   consolePreference.push('nintendoSwitch');
// }

// if (document.getElementById('pc').checked) {
//   consolePreference.push('pc');
// }

// console.log(timeZone.value);
// console.log(microPhone);
// console.log(ageRange.value);
// console.log(genderType);
// console.log(consolePreference.values);
// console.log(emailUser.valueOf);

submitBtn.addEventListener('click', async _ => {
  if (document.getElementById('microphone-yes').checked) {
    microPhone = "yes";
  } else if (document.getElementById('microphone-no').checked) {
    microPhone = "no";
  }
  
  if (document.getElementById('male').checked) {
    genderType = "male";
  } else if (document.getElementById('female').checked) {
    genderType = "female";
  } else if (document.getElementById('other').checked) {
    genderType = "other";
  }
  
  if (document.getElementById('ps4').checked) {
    consolePreference.push('playStation');
  }
  
  if (document.getElementById('xbox').checked) {
    consolePreference.push('xBox');
  }
  
  if (document.getElementById('switch').checked) {
    consolePreference.push('nintendoSwitch');
  }
  
  if (document.getElementById('pc').checked) {
    consolePreference.push('pc');
  }
  console.log(timeZone.value);
  console.log(microPhone);
  console.log(ageRange.value);
  console.log(genderType);
  console.log(consolePreference.values);
  console.log(decodeURI(emailUser.valueOf))
  try {
      const response = await fetch('http://localhost:8081/storeAttributes?timeZone=' + encodeURI(timeZone.value) 
      + '&microPhone=' + encodeURI(microPhone)
      + '&ageRange=' + encodeURI(ageRange.value)
      + '&gender=' + encodeURI(genderType)
      + '&consolePreference=' + encodeURI(consolePreference)
      + '&emailAddress=' + encodeURI(emailUser.valueOf) , {
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