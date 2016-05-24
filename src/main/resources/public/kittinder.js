function getUrl(url, callback) {
    var request = new XMLHttpRequest();
    request.onreadystatechange = function() {
        if (request.readyState == 4 && request.status == 200) {
            if (callback != null) {
                var response = JSON.parse(request.responseText);
                callback(response);
            }
        }
    };
    request.open("GET", url, true);
    request.send();
}

function likeUser(id) {
    getUrl("user/like?userId=" + id + "&originUserId=1", displayNewCandidate);
}

function dislikeUser(id) {
    getUrl("user/dislike?userId=" + id + "&originUserId=1", displayNewCandidate);
}

function displayNewCandidate() {
    getUrl("user/candidate?originUserId=1", displayUser);
}

function displayUser(userResponse) {
    var userHTML = "";

    if (userResponse.data != null) {
        var user = userResponse.data;
        userHTML += '<img src="photo/' + user.photoId + '.jpg" style="width:500px;height:500px;">';
        userHTML += '<p>' + user.name + '</p>';
        userHTML += '<button type="button" onclick="likeUser(' + user.id + ')">Yay!</button>';
        userHTML += '<button type="button" onclick="dislikeUser(' + user.id +')">Nay...</button>';
    } else {
        userHTML += '<p>No more kittens for you, honey. Come back later.</p>';
    }

    document.getElementById("user").innerHTML = userHTML;
}

displayNewCandidate();
