function openCustomerServeAssignDialog() {
    var rows = $("#dg" ).datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("来自crm", "请选择待分配的数据!", "warning");
        return;
    }


    if (rows.length > 1) {
        $.messager.alert("来自crm", "暂不支持批量分配!", "warning");
        return;
    }
    $("#fm").form("load",rows[0]) ;


    openDialog("dlg","服务分配");
}

function closeAssignDialog() {
    $("#dlg").dialog("close");
}


function doAssign() {
    $("#fm").form("submit",{
        url:ctx+"/customer_serve/updateCustomerServe",
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data = JSON.parse(data);
            if(data.code==200){
                closeAssignDialog();
                $("#dg").datagrid("load");
            }else {
                $.messager.alert("来自crm",data.msg,"error");
            }
    }
    })
}