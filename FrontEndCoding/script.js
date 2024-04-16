document.getElementById('findMatchesBtn').addEventListener('click', function() {
    // Replace 'playerEmail' with the actual value from your player attributes page
    const playerEmail = 'player@example.com';
  
    // Simulate sending email ID to backend (replace this with actual backend call)
    sendEmailToBackend(playerEmail);
  });
  
  function sendEmailToBackend(email) {
    // Simulated backend response with matched profiles (replace with actual backend call)
    const matchedProfiles = [
        {
            "firstName": "John",
            "lastName": "Doe",
            "age": "21",
            "gender": "male",
            "microPhone": "Yes",
            "consolePreferences": [
                "pc",
                "xBox"
            ],
            "timeZone": "EST"
        },
        {
            "firstName": "Jenny",
            "lastName": "Doe",
            "age": "25",
            "gender": "female",
            "microPhone": "Yes",
            "consolePreferences": [
                "pc"
            ],
            "timeZone": "PST"
        }
    ];
  
    // Redirect to matches.html with matched profiles data as URL parameter
    redirectToMatchesPage(matchedProfiles);
  }
  
  function redirectToMatchesPage(matchedProfiles) {
    const queryParams = new URLSearchParams();
    queryParams.set('matchedProfiles', JSON.stringify(matchedProfiles));
    const url = 'matches.html?' + queryParams.toString();
    window.location.href = url;
  }
  