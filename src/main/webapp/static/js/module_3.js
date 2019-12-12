function formatterGrade(value) {
    if(value==0){
        return "顶级菜单"
    }else  if(value==1){
        return "二级菜单"
    }else  if(value==2){
        return "三级菜单"
    }else{
        return "菜单未知"
    }
}

function searchModule() {
    $("#dg").datagrid("load",{
        moduleName:$("#s_moduleName").val(),
        optValue:$("#s_optValue").val()
    })
}


function openModuleAddDialog() {
    openDialog("dlg","模块添加");
}

function closeModuleDialog() {
    closeDialog("dlg");
}

function saveOrUpdateModule() {
    saveOrUpdateRecode("fm",ctx+"/module/save",ctx+"/module/update","dlg",searchModule);
}

function openModuleModifyDialog() {
    openModifyDialog("dg","fm","dlg","模块更新");
}

function deleteModule() {
    deleteRecode("dg",ctx+"/module/delete",searchModule);
}
