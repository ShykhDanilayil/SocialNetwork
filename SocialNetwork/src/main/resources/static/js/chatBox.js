allfriends();

$("div.container").ready(function () {
    $("button.msg_send_btn").click(function () {
        newMessage();
    });
});

$(window).on('keydown', function (e) {
    if (e.which == 13) {
        newMessage();
        return false;
    }
});

function theFunctionClick(idOther) {
    let formData = new FormData;
    formData.set("idOtherUser", idOther);
    $(".chat_list").css('background-color', '#f8f8f8');
    document.getElementById("" + idOther + "").style.backgroundColor = "#f1f1f1";
    allMessages(formData, idOther);
    document.getElementById("mesgs").style.backgroundColor = "#f1f1f1";
}

function goToProfile() {
    let finalUrl = "";
    let url = window.location.href.split("/");
    for (let i = 0; i < url.length - 1; i++) {
        finalUrl += url[i] + "/";
    }
    finalUrl += "profile";
    window.location.href = finalUrl;
}

function allUsers() {
    let finalUrl = "";
    let url = window.location.href.split("/");
    for (let i = 0; i < url.length - 1; i++) {
        finalUrl += url[i] + "/";
    }
    finalUrl += "allUsers";
    window.location.href = finalUrl;
}

function newMessage() {
    message = $("input#message").val();
    if ($.trim(message) == '') {
        return false;
    }
    let data = new FormData;
    data.append("text", message);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/message");
    xhr.send(data);

    xhr.onload = function () {
        if (xhr.status === 201) {
            user = JSON.parse(xhr.responseText);
            let formData = new FormData;
            formData.set("idOtherUser", user.id);
            allMessages(formData, user.id);
            $("input#message").val(null);
            $("div#" + user.id + " p").html(message);
        }
    }
}

function allfriends() {
    let users = null;
    $.get("chatPeople", function (data) {
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
    }).success(function () {
        let content = "";
        jQuery.each(users, function (i, users) {
            content +=
                "<div class='chat_list' id='" + users.id + "' style='cursor: pointer' onclick='theFunctionClick(\"" + users.id + "\")'>" +
                "   <div class='chat_people'>" +
                "       <div class='chat_img'> <img src='data:image/png;base64," + users.base64Image + "'> </div>" +
                "           <div class='chat_ib'>" +
                "               <h5>" + users.name + " " + users.lastName;
            if (users.date !== null) {
                content += "<span class='chat_date'>" + users.date + "</span>";
            }
            content += "</h5>";
            if (users.lastText !== null) {
                content += "<p>" + users.lastText + "</p>";
            }
            content +=
                "           </div>" +
                "       </div>" +
                "   </div>";
        });
        $("div.inbox_chat").html(content);
        $("div#all").show();
    });
}

function allMessages(formData, idOther) {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/messages");
    xhr.send(formData);
    xhr.onload = function () {

        if (xhr.status === 200) {
            let messages = JSON.parse(xhr.responseText);
            let content = "";
            jQuery.each(messages, function (i, messages) {
                if (messages.idUserFrom === idOther) {
                    content += "<div class='incoming_msg'>" +
                        "   <div class='incoming_msg_img'> <img src='data:image/png;base64," + messages.base64Image + "' onclick='goToProfile()' style='cursor: pointer'> </div>" +
                        "   <div class='received_msg'>" +
                        "       <div class='received_withd_msg'>" +
                        "           <p>" + messages.text + "</p>" +
                        "       <span class='time_date'>" + messages.time + " | " + messages.date + "</span></div>" +
                        "   </div>" +
                        "</div>";
                } else {
                    content += "<div class='outgoing_msg'>" +
                        "   <div class='sent_msg'>" +
                        "       <p>" + messages.text + "</p>" +
                        "   <span class='time_date'>" + messages.time + " | " + messages.date + "</span> </div>" +
                        "</div>";
                }
            });
            $("div.msg_history").html(content);
            $("#noMessage").hide();
            $("div.type_msg").show();
            let div = document.getElementById("scroll");
            div.scrollTop = div.scrollHeight;
        }
    };
}

function search() {
    var input, filter, h5, i, txtValue;
    input = document.getElementById("myInput");
    filter = input.value.toUpperCase();
    h5 = document.getElementsByTagName("h5");

    $(".chat_list").css('background-color', '#f8f8f8');

    for (i = 0; i < h5.length; i++) {
        txtValue = h5[i].textContent || h5[i].innerText;
        if (txtValue.toUpperCase().indexOf(filter) > -1) {
            h5[i].parentElement.parentElement.parentElement.style.backgroundColor = "#d8d8d8";
        }
    }
}