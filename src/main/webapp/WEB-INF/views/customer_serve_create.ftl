<!--
  服务创建主页面
-->
<!doctype html>
<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/common.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.serve.create.js"></script>
</head>
<body style="margin: 1px">

<div id="p" class="easyui-panel" style="width: 700px;height: 350px;padding: 10px">
    <form id="fm" method="post" ">
        <table cellspacing="8px">
            <tr>
                <td>服务类型：</td>
                <td><input type="text" class="easyui-combobox" id="serveType" name="serveType"
                           valueField="id" textField="dicValue"
                           url="${ctx}/data_dic/queryDataDicByDicName?dicName=服务类型" panelHeight="auto" /> </td>
                <td>    </td>
                <td>客户名</td>
                <td><input type="text" id="customer" name="customer" /></td>
            </tr>
            <tr>
                <td>请求内容：</td>
                <td><input type="text" id="serviceRequest" name="serviceRequest" class="easyui-validatebox" required="true" /> <font color="red">*</font></td>
                <td>    </td>
                <td>概要：</td>
                <td><input type="text" id="overview" name="overview"  /></td>
            </tr>
            <tr>
                <td><a class="easyui-linkbutton" href="javascript:saveCustomerServe()" iconCls="icon-save" plain="true">提交</a></td>
                <td><a class="easyui-linkbutton" href="" iconCls="icon-save" plain="true">重置</a></td>
            </tr>
        </table>
    </form>
</div>



</body>
</html>
