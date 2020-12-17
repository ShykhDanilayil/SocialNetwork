function logOutFunc() {
    $.get("logOut", function () {
        console.log("User log out");
    });
}