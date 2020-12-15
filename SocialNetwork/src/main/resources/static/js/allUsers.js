getContent();

function getContent() {
    let users = null;
    $.get("users", function (data) {
        if (data === "login-failed") {
            let finalUrl = "";
            let url = window.location.href.split("/");
            for (let i = 0; i < url.length - 1; i++) {
                finalUrl += url[i] + "/";
            }
            finalUrl += "logIn";
            window.location.href = finalUrl;
        } else {
            users = data;
        }
    }).done(function () {
        let content = "";
        jQuery.each(users, function (i, users) {
            content +=
                "<div class='row borderradius' id='color'>" +
                "        <div class='col-xs-12 col-sm-6 col-md-6'  id='width100'>" +
                "            <div class='well-sm background'>" +
                "                <div class='row'>" +
                "                    <div class='col-sm-6 col-md-4'>" +
                "                        <img src='data:image/png;base64," + users.base64Image + "' class='img-rounded img-responsive' style='width=200px; height:200px'/>" +
                "                    </div>" +
                "                    <div class='col-sm-6 col-md-8'>" +
                "                        <h4> " + users.name + " " + users.lastName + "</h4>" +
                "                        <p>" +
                "                            <i class=\"glyphicon glyphicon-gift\"> </i>" + users.age +
                "                            <br />" +
                "                            <i class='glyphicon glyphicon-envelope'></i> <a href='mailto:'" + users.email + ">" + users.email + "</a>" + "</p>";
            if (users.isFriend === "false") {
                content +=
                    "                        <div class='btn-group'>" +
                    "                            <button type='button' class='btn btn-primary' id='" + users.id + "' onclick='addFriend(\"" + users.id + "\")'>" +
                    "                                add friend</button>" +
                    "                        </div>";
            } else if (users.isFriend === "true") {
                content +=
                    "                        <div class='btn-group'>" +
                    "                            <button type='button' class='btn btn-primary' id='" + users.id + "' onclick='deleteFriend(\"" + users.id + "\")'>" +
                    "                                delete friend</button>" +
                    "                        </div>";
            } else if (users.isFriend === "followers") {
                content +=
                    "                        <div class='btn-group'>" +
                    "                            <button type='button' class='btn btn-primary' id='" + users.id + "' onclick='addFriend(\"" + users.id + "\")'>" +
                    "                                also subscribe</button>" +
                    "                        </div>";
            } else if (users.isFriend === "following") {
                content +=
                    "                        <div class='btn-group'>" +
                    "                            <button type='button' class='btn btn-primary' id='" + users.id + "' onclick='deleteFriend(\"" + users.id + "\")'>" +
                    "                                delete request</button>" +
                    "                        </div>";
            }
            content +=
                "                    </div>" +
                "                </div>" +
                "            </div>" +
                "        </div>" +
                "    </div>";
        });
        $("div#allUsers").html(content);
    });
}

function addFriend(otherId) {
    let formData = new FormData;
    formData.set("idOtherUser", otherId);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/messages");
    xhr.send(formData);
    xhr.onload = function () {
        if (xhr.status === 200) {
            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/following");
            xhr.send(formData);
            xhr.onload = function () {
                getContent();
            }
        }
    }
}

function deleteFriend(otherId) {
    let formData = new FormData;
    formData.set("idOtherUser", otherId);
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/messages");
    xhr.send(formData);
    xhr.onload = function () {
        if (xhr.status === 200) {
            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/follow");
            xhr.send(formData);
            xhr.onload = function () {
                getContent();
            }
        }
    }
}