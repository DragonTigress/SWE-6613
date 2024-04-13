
const timeZone = document.getElementById("timezone");
const ageRange = document.getElementById("age-range").value;
const selected = document.querySelector(".selected");
var microPhone;
var genderType;
var consolePreference = [];
const emailUser = document.cookie;
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

if (document.getElementById('microphone-yes').checked) {
  microPhone = document.getElementById('microphone-yes').value;
} else if (document.getElementById('microphone-no').checked) {
  microPhone = document.getElementById('microphone-no').value;
}

if (document.getElementById('male').checked) {
  genderType = document.getElementById('male').value;
} else if (document.getElementById('female').checked) {
  genderType = document.getElementById('female').value;
} else if (document.getElementById('other').checked) {
  genderType = document.getElementById('other').value;
}

if (document.getElementById('ps4').checked) {
  consolePreference.push('ps4');
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


const submitBtn = document.getElementById("submit-btn");
submitBtn.addEventListener('click', async _ => {
  try {
      const response = await fetch('http://localhost:8081/storeAttributes?timeZone=' + (timeZone.value) 
      + '&microPhone=' + (microPhone.value)
      + '&ageRange=' + (ageRange.value)
      + '&gender=' + (genderType.value)
      + '&consolePreference=' + (consolePreference.values)
      + '&emailAddress=' + (emailUser.valueOf) , {
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