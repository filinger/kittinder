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

function displayNewCandidate() {
    getUrl("user/candidate?originUserId=1", displayUser);
}

function likeUser(id) {
    getUrl("user/like?userId=" + id + "&originUserId=1");
    displayNewCandidate();
}

function dislikeUser(id) {
    getUrl("user/dislike?userId=" + id + "&originUserId=1");
    displayNewCandidate();
}

function displayUser(user) {
    var userHTML = "";
    userHTML += '<img src="photo/' + user.photoId + '.jpg" style="width:500px;height:500px;">';
    userHTML += '<p>' + user.name + '</p>';
    userHTML += '<button type="button" onclick="likeUser(' + user.id + ')">Yay!</button>';
    userHTML += '<button type="button" onclick="dislikeUser(' + user.id +')">Nay...</button>';

    document.getElementById("user").innerHTML = userHTML;
}

displayNewCandidate();
