$(function () {
    $("#dg").edatagrid({
        url:ctx+"/customer_repr/list?lossId="+$("#lossId").val(),
        saveUrl:ctx+"/customer_repr/save?lossId="+$("#lossId").val(),
        updateUrl:ctx+"/customer_repr/update"
    })
});

function saveCustomerReprieve() {
   $("#dg").edatagrid("saveRow");
   //console.log(result);
    $("#dg").edatagrid("load");
}

function  searchCustomerRepr() {
    $("#dg").edatagrid("load");
}

function delCustomerReprieve() {
   deleteRecode("dg",ctx+"/customer_repr/delete",searchCustomerRepr);
}

function updateCustomerLossState() {
    $.messager.confirm("来自crm","确定执行流失处理?",function (r) {
        if(r){
            $.messager.prompt("来自crm","请输入流失原因",function (r) {
                if(r){
                    $.ajax({
                        type:"post",
                        url:ctx+"/customer_loss/updateState",
                        data:{
                            lossReason:r,
                            lossId:$("#lossId").val()
                        },
                        dataType:"json",
                        success:function (data) {
                            if(data.code==200){
                                    $("#toolbar").hide();
                                    $("#dg").edatagrid("disableEditing");
                            }else{
                                $.messager.alert("来自crm",data.msg,"error");
                            }
                        }
                    })
                }
            })
        }
    })
}