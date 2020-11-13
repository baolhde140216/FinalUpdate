function reply_click(clicked_id) {
    var id = clicked_id;
    var x = "input[id=" + id + "]";
    var i = "i[id=" + id + "]";
    var vip = document.querySelectorAll(x)[0].type;
    if (vip.includes("password")) {
        document.querySelectorAll(x)[0].type = "text";
        document.querySelectorAll(i)[0].className = "fas fa-eye-slash";
    } else {
        document.querySelectorAll(x)[0].type = "password";
        document.querySelectorAll(i)[0].className = "far fa-eye";
    }


}