function formatterState(val) {
    if(val==0){
        return "暂缓流失";
    }else if(val==1){
        return "确认流失"
    }
}

function searchCustomerLoss() {
    $("#dg").datagrid("load",{
        cusNo:$("#s_cusNo").val(),
        cusName:$("#s_cusName").val(),
        state:$("#s_state").combobox("getValue")
    })
}

function formatterOp(val,rowData) {
    var state=rowData.state;
    var href="javascript:openAddRepriTab('"+rowData.cusName+"_暂缓管理','"+rowData.cusNo+"',"+rowData.id+")";
    if(state==0){
        return "<a href="+href+">暂缓流失</a>"
    }
    if(state==1){
        return "<a href="+href+">确认流失</a>";
    }
}

function openAddRepriTab(title,cusNo,lossId) {
    window.parent.openTab(title,ctx+"/customer_loss/openRepr?cusNo="+cusNo+"&lossId="+lossId);
}