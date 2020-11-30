$("div#exit").ready(function () {

    $("#tab a").click(function (e) {
        e.preventDefault();
        $(this).tab("show");
    });

    $("button#registr").click(function () {
        if (validateForm() === true) {
            let photo = $("#file");
            let formData = new FormData;

            formData.append('photo', photo.prop('files')[0]);
            formData.append('name', $('#name').val());
            formData.append('lastName', $('#latName').val());
            formData.append('age', $('#age').val());
            formData.append('email', $('#email').val());
            formData.append('password', $('#password').val());

            let xhr = new XMLHttpRequest();
            xhr.open("POST", "/register");
            xhr.send(formData);
            if (xhr.status === 201) {
                console.log("User created");
            }
        }
    });
});

function validateForm() {
    let name = document.forms["my-form"]["name"].value;
    let lastName = document.forms["my-form"]["lastName"].value;
    let age = document.forms["my-form"]["age"].value;
    let email = document.forms["my-form"]["email"].value;
    let password = document.forms["my-form"]["password"].value;
    let photo = document.forms["my-form"]["file"].value;

    if (name == null || name == "") {
        alert("Please enter your name");
        return false;
    } else if (lastName == null || lastName == "") {
        alert("Please enter your lastName");
        return false;
    } else if (age == null || age == "") {
        alert("Please enter your age");
        return false;
    } else if (email == null || email == "") {
        alert("Please enter your email");
        return false;
    } else if (password == null || password == "") {
        alert("Please enter your password");
        return false;
    } else if (password.length < 5 || password.length > 25){
        alert("Password length < 5 or > 25");
        return false;
    } else if (photo == null || photo == "") {
        alert("Please load your photo");
        return false;
    }
    return true;
}