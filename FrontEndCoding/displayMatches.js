
document.addEventListener('DOMContentLoaded', function() {
    const queryParams = new URLSearchParams(window.location.search);
    const matchedProfilesJSON = queryParams.get('matchedProfiles');
    const matchedProfiles = JSON.parse(matchedProfilesJSON);

    displayMatches(matchedProfiles);
});

function displayMatches(matches) {
    const matchesContainer = document.getElementById('matchesContainer');

    if (matches.length === 0) {
        matchesContainer.innerHTML = '<p>No matches found.</p>';
    } else {
        matches.forEach(match => {
            const matchDiv = document.createElement('div');
            matchDiv.classList.add('match');

            // Construct HTML for each match
            const matchHTML = `
                <p>Name: ${match.firstName} ${match.lastName}</p>
                <p>Age: ${match.age}</p>
                <p>Gender: ${match.gender}</p>
                <p>Microphone: ${match.microPhone}</p>
                <p>Console Preferences: ${match.consolePreferences.join(', ')}</p>
                <p>Time Zone: ${match.timeZone}</p>
                <hr>
            `;
            matchDiv.innerHTML = matchHTML;
            matchesContainer.appendChild(matchDiv);
        });
    }
}
