function searchCustomersByParams() {
    $("#dg").datagrid("load",{
        khno:$("#s_khno").val(),
        name:$("#s_name").val(),
        xyd:$("#s_xyd").combobox("getValue"),
        state:$("#s_state").combobox("getValue")
    })
}

function formatterState(val) {
    if(val==0){
        return "未流失"
    }else{
        return "已流失"
    }
}

function openCustomerAddDialog() {
    openDialog("dlg","客户添加");
}

function closeCustomerDialog() {
    closeDialog("dlg");
}

function saveOrUpdateCustomer() {
    saveOrUpdateRecode("fm",ctx+"/customer/save",ctx+"/customer/update","dlg",searchCustomersByParams);
}

function openCustomerModifyDialog() {
    openModifyDialog("dg","fm","dlg","客户更新");
}

function deleteCustomer() {
    deleteRecode("dg",ctx+"/customer/delete",searchCustomersByParams);
}

$(function () {
    $("#dlg").dialog({
        onClose:function () {
            $("#name").val("");
            $("#id").val("");
            $("#fr").val("");
            $("#phone").val("");
            $("#fax").val("");
            $("#cusManager").val("");
            $("#address").val("");
            $("#post_code").val("");
            $("#webSite").val("");
            $("#yyzzzch").val("");
            $("#area").val("");
            $("#zczj").val("");
            $("#level").combobox("setValue","");
            $("#myd").combobox("setValue","");
            $("#xyd").combobox("setValue","");
        }
    })
});

/**
 * 订单查看选项卡展示
 */
function openOrderInfoTab() {
    var rows = $("#dg" ).datagrid("getSelections");
    if (rows.length == 0) {
        $.messager.alert("来自crm", "请选择客户记录!", "warning");
        return;
    }

    if (rows.length > 1) {
        $.messager.alert("来自crm", "暂不支持批量订单查看!", "warning");
        return;
    }

    window.parent.openTab(rows[0].name+"_订单列表",ctx+"/customer/orderInfo?id="+rows[0].id);
}
