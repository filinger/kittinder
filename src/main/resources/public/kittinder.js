function get(url, callback) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200) {
            var response = JSON.parse(request.responseText);
            callback(response);
        }
    };
    request.open("GET", url, true);
    request.send();
}

function displayUser(user) {
    var userHTML = '<p>' + user.name + '</p>';
    document.getElementById("user").innerHTML = userHTML;
}

get("user/random", displayUser);
