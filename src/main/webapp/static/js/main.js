function openTab(text, url, iconCls) {
    if ($("#tabs").tabs("exists", text)) {
        $("#tabs").tabs("select", text);
    } else {
        var content = "<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' " +
            "src='" + url + "'></iframe>";
        $("#tabs").tabs("add", {
            title: text,
            iconCls: iconCls,
            closable: true,
            content: content
        });
    }
}

function openPasswordModifyDialog() {
    $("#dlg").dialog("open").dialog("setTitle", "密码修改");
}

function closePasswordModifyDialog() {
    $("#dlg").dialog("close");
}

function modifyPassword() {
    $("#fm").form("submit", {
        url: ctx + "/user/updateUserPassword",
        onSubmit: function () {
            return $("#fm").form("validate");
        },
        success: function (data) {
            data = JSON.parse(data);
            if (data.code == 200) {
                /**
                 * 更新成功，自动退出，重新登录
                 */
                $.messager.alert("来自CRM", "密码修改成功，系统将在3秒后自动退出，请重新登录...", "info");
                setTimeout(function () {
                    $.removeCookie("userIdStr");
                    $.removeCookie("userName");
                    $.removeCookie("trueName");
                    window.location.href = ctx + "/index";
                }, 3000);
            } else {
                $.messager.alert("来自CRM", data.msg, "error");
            }
        }
    })
}

function logout() {
    $.messager.confirm("来自CRM", "确认退出？", function (r) {
        if (r) {
            $.removeCookie("userIdStr");
            $.removeCookie("userName");
            $.removeCookie("trueName");
            window.location.href = ctx + "/index";
        }
    });
}















