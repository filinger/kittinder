var myUserId = 0;

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
    getUrl("user/like?userId=" + id + "&originUserId=" + myUserId, displayNewCandidate);
}

function dislikeUser(id) {
    getUrl("user/dislike?userId=" + id + "&originUserId=" + myUserId, displayNewCandidate);
}

function displayNewCandidate() {
    getUrl("user/candidate?originUserId=" + myUserId, displayUser);
}

function displayUser(userResponse) {
    var userHTML = "";

    if (userResponse.data != null) {
        var user = userResponse.data;
        userHTML += '<div class="imageDiv"><img class="profileImage" src="photo/' + user.photoId + '.jpg"></div>';
        userHTML += '<div class="descDiv">';
        userHTML += '<p>' + user.name + '</p>';
        userHTML += '<button onclick="likeUser(' + user.id + ')">Yay <i class="fa fa-thumbs-o-up"></i></button>';
        userHTML += '<button onclick="dislikeUser(' + user.id +')">Nay <i class="fa fa-thumbs-o-down"></i></button>';
        userHTML += '</div>'
    } else {
        userHTML += '<p>No more kittens for you, honey. Come back later.</p>';
    }

    document.getElementById("user").innerHTML = userHTML;
}

displayNewCandidate();
