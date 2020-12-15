let user = null;
$.get("prof", function (data) {
    if (data === "login-failed") {
        let finalUrl = "";
        let url = window.location.href.split("/");
        for (let i = 0; i < url.length - 1; i++) {
            finalUrl += url[i] + "/";
        }
        finalUrl += "logIn";
        window.location.href = finalUrl;
    } else {
        user = data;
    }
}).done(function () {
    let content = "<img src='data:image/png;base64," + user.base64Image + "' style='width:200px; height:350px'>" +
        "    <br>" +
        "    <ul>" +
        "        <li><b>Name : </b>" + user.name + "</li>" +
        "        <li><b>Last Name : </b>" + user.lastName + "</li>" +
        "        <li><b>Age : </b>" + user.age + "</li>" +
        "        <li><b>Email : </b>" + user.email + "</li>" +
        "    </ul>";
    $("div#response").html(content);
});