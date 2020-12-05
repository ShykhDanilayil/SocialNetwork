let users = null;
$.get("chatPeople", function (data) {
    if (data !== "") {
    users = data;
}
}).done(function () {
    let content = "";
    jQuery.each(users, function (i, users) {
        content +=
            "<div class='chat_list' id='user' style='cursor: pointer' onclick='theFunctionClick(\"" + users.id + "\")'>" +
"   <div class='chat_people'>" +
"       <div class='chat_img'> <img src='data:image/png;base64," + users.base64Image +"'> </div>" +
"           <div class='chat_ib'>" +
"               <h5>" + users.name + " " + users.lastName + "<span class='chat_date'>" + users.date + "</span></h5>" +
"               <p>" + users.lastText + "</p>" +
"           </div>" +
"       </div>" +
"   </div>" ;
    });
    $("div.inbox_chat").html(content);
});

$("div.container").ready(function () {

    $("button.msg_send_btn").click(function() {
        newMessage();
    });
});

$(".msg_history").animate({scrollTop: $(document).height()}, "fast");

$(window).on('keydown', function(e) {
    if (e.which == 13) {
        alert("Enter");
        newMessage();
        return false;
    }
});

function theFunctionClick(idOther){
    let formData = new FormData;
    formData.append("idOtherUser", idOther);

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
                            "   <div class='incoming_msg_img'> <img src='https://ptetutorials.com/images/user-profile.png'> </div>" +
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
        }
    };

}

function newMessage() {
    message = $("input#message").val();
    if($.trim(message) == '') {
        return false;
    }
    let data = new FormData;

    data.append("text", message);

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "/message");
    xhr.send(data);

    xhr.onload = function () {
        if (xhr.status === 201) {
            let message = JSON.parse(xhr.responseText);
            $("div.msg_history").html("<div class='outgoing_msg'>" +
                "   <div class='sent_msg'>" +
                "       <p>" + message.text + "</p>" +
                "       <span class='time_date'>" + message.time + " | " + message.date + "</span> </div>" +
                "</div>");

            $("input#message").val(null);
            $(".msg_history").animate({scrollTop: $(document).height()}, "fast");
        }
    }
};