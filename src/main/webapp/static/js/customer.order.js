function formatterState(val) {
    if(val==0){
        return "未支付";
    }else if(val==1){
        return "已支付";
    }
}

function formatterOp(val,rowData) {
    return "<a href='https://www.baidu.com'>查看</a>"
}

function searchCustomerOrdersByParams() {
    $("#dg").datagrid("load",{
        orderNo:$("#s_orderNo").val(),
        state:$("#s_state").combobox("getValue")
    })
}