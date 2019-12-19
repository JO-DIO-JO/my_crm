<!--
  服务分配主页面
-->
<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.serve.assign.js"></script>
</head>
<body style="margin: 1px">
<table id="dg"  class="easyui-datagrid"
       fitColumns="true" pagination="true" rownumbers="true"
       url="${ctx}/customer_serve/list?state=fw002" fit="true" toolbar="#tb" singleSelect="true">
    <thead>
    <tr>
        <th field="cb" checkbox="true" align="center"></th>
        <th field="id" width="50" align="center">编号</th>
        <th field="customer" width="200" align="center" >客户</th>
        <th field="serveType" width="50" align="center">服务类型</th>
        <th field="serviceRequest" width="50" align="center">服务请求内容</th>
        <th field="overview" width="50" align="center">概要信息</th>
        <th field="assignTime" width="50" align="center">分配时间</th>
        <th field="createDate" width="50" align="center">创建时间</th>
        <th field="updateDate" width="50" align="center">更新时间</th>

    </tr>
    </thead>
</table>
<div id="tb">
    <div>
        <a href="javascript:openCustomerServeAssignDialog()" class="easyui-linkbutton" iconCls="icon-add" plain="true">执行处理</a>
    </div>
    <div>
        客户名： <input type="text" id="s_customer" size="20" onkeydown="if(event.keyCode==13) searchCustomer()"/>
        服务类型：<input type="text" class="easyui-combobox" id="serveType" name="serveType"
                    valueField="id" textField="dicValue"
                    url="${ctx}/data_dic/queryDataDicByDicName?dicName=服务类型" panelHeight="auto" onkeydown="if(event.keyCode==13) searchCustomer()"/>
        <a href="javascript:searchCustomer()" class="easyui-linkbutton" iconCls="icon-search" plain="true">搜索</a>
    </div>
</div>




<div id="dlg" class="easyui-dialog" style="width:600px;height:260px;padding: 10px 20px"
     closed="true" buttons="#dlg-buttons">
    <form id="fm" method="post">
        <table cellspacing="8px">
            <tr>
                <td>客户名：</td>
                <td><input type="text" id="customer" name="customer" class="easyui-validatebox" required="true" readonly="readonly"/> <font color="red">*</font></td>
                <td>    </td>
                <td>服务类型</td>
                <td><input type="text" class="easyui-combobox" id="serveType" name="serveType"
                           valueField="id" textField="dicValue"
                           url="${ctx}/data_dic/queryDataDicByDicName?dicName=服务类型" panelHeight="auto" /></td>
            </tr>
            <tr>
                <td>请求内容：</td>
                <td><input type="text" id="serviceRequest" name="serviceRequest" class="easyui-validatebox" required="true"  readonly="readonly"/> <font color="red">*</font></td>
                <td>    </td>
                <td>概要：</td>
                <td><input type="text" id="overview" name="overview" readonly="readonly" /></td>
            </tr>
            <tr>
                <td>分配人：</td>
                <td><input type="text" class="easyui-combobox" id="assigner" name="assigner"
                          valueField="id" textField="trueName"
                          url="${ctx}/queryUsersByCustomerManager" panelHeight="auto" />
                    <font color="red">*</font>
                </td>

            </tr>
        </table>
        <input name="id" type="hidden" id="id"/>
    </form>
</div>

<div id="dlg-buttons">
    <a href="javascript:doAssign()" class="easyui-linkbutton" iconCls="icon-ok">执行分配</a>
    <a href="javascript:closeAssignDialog()" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>





</body>
</html>
