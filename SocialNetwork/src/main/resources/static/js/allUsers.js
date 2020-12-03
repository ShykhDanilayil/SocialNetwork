let users = null;

$.get("users", function (data) {
    if (data !== "") {
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
            "                            <i class='glyphicon glyphicon-envelope'></i> <a href='mailto:'"+ users.email +">"+ users.email +"</a>" + "</p>" +
            "                        <!-- Split button -->" +
            "                        <div class='btn-group'>" +
            "                            <button type='button' class='btn btn-primary'>" +
            "                                Social</button>" +
            "                            <button type='button' class='btn btn-primary dropdown-toggle' data-toggle='dropdown'>" +
            "                                <span class='caret'></span><span class='sr-only'>Social</span>" +
            "                            </button>" +
            "                            <ul class='dropdown-menu' role='menu'>" +
            "                                <li><a href='#'>Twitter</a></li>" +
            "                                <li><a href='https://plus.google.com/+Jquery2dotnet/posts'>Google +</a></li>" +
            "                                <li><a href='https://www.facebook.com/jquery2dotnet'>Facebook</a></li>" +
            "                                <li class='divider'></li>" +
            "                                <li><a href='#'>Github</a></li>" +
            "                            </ul>" +
            "                        </div>" +
            "                    </div>" +
            "                </div>" +
            "            </div>" +
            "        </div>" +
            "    </div>";
    });
    $("div#allUsers").html(content);
});