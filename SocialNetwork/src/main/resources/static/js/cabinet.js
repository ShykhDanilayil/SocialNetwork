let user = null;

$.get("cabinet",function (data) {
    if (data !== ""){
        user = data;
    }
}).done(function () {
    let content = "<img src='' style='width:100%'>" +
        "    <br>" +
        "    <ul>" +
        "        <li><b>Name : </b>" + user.name + "</li>" +
        "        <li><b>Last Name : </b>" + user.lastName + "</li>" +
        "        <li><b>Age : </b>" + user.age + "</li>" +
        "        <li><b>Email : </b>" + user.email + "</li>" +
        "    </ul>";
    $("img").attr(
        'src',
        "data:image/png;photo,"
        + user.photo);
    $("div#response").html(content);
});