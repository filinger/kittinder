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
    var userHTML = "";
    userHTML += '<img src="photo/' + user.photoId + '.jpg" style="width:500px;height:500px;">';
    userHTML += '<p>' + user.name + '</p>';
    document.getElementById("user").innerHTML = userHTML;
}

get("user/random", displayUser);
