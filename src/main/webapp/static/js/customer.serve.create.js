function saveCustomerServe() {
    $("#fm").form("submit",{
        url:ctx+"/customer_serve/save",
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data =JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自crm",data.msg,"info");
                $("#serveType").combobox("setValue","");
                $("#customer").val("");
                $("#serviceRequest").val("");
                $("#overview").val("");
            }else{
                $.messager.alert("来自crm",data.msg,"error");
            }
        }
    })
}