/*
 * 用户登录
 */
function login() {
    var userName = $("#username").val();
    var userPwd = $("#password").val();
    if (isEmpty(userName)) {
        alert("用户名不能为空！");
        return;
    }
    if (isEmpty(userPwd)) {
        alert("用户密码不能为空！");
        return;
    }
    $.ajax({
        type:"post",
        url:ctx + "/user/login",
        data:{
            userName: userName,
            userPwd: userPwd
        },
        dataType: "json",
        success: function (data) {
            if (data.code == 200) {
                // 用户信息写入Cookie
                $.cookie("userIdStr",  data.entity.userIdStr);
                $.cookie("userName",  data.entity.userName);
                $.cookie("trueName",  data.entity.trueName);
                window.location.href = ctx + "/main";
            } else {
                alert(data.msg);
            }
        }
    })
}