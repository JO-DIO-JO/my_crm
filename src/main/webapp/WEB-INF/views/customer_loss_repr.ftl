<html>
<head>
    <#include "common.ftl" >
    <script type="text/javascript" src="${ctx}/static/js/base.js"></script>
    <script type="text/javascript" src="${ctx}/static/jquery-easyui-1.3.3/jquery.edatagrid.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/customer.loss.repr.js"></script>
</head>
<body style="margin: 15px">
<
<#--客户详情展示-->
<div id="p" class="easyui-panel" title="客户信息" style="width: 700px;height: 200px;padding: 10px">
    <input type="hidden" id="lossId"  value="${lossId!}" />
    <table cellspacing="8px">
        <tr>
            <td>客户名称：</td>
            <td><input type="text" readonly="readonly" value="${customer.name?if_exists}" /></td>
            <td>    </td>
            <td>客户编号</td>
            <td><input type="text" readonly="readonly" value="${customer.khno?if_exists}"/></td>
        </tr>
        <tr>
            <td>客户经理：</td>
            <td><input type="text" readonly="readonly" value="${customer.cusManager?if_exists}"/></td>
            <td>    </td>
            <td>创建时间：</td>
            <td><input type="text" readonly="readonly" value="${customer.createDate?string("yyyy-MM-dd")}"/></td>
        </tr>
    </table>
</div>
<br/>


<table id="dg" title="暂缓措施" style="width:700px;height:250px"
       toolbar="#toolbar" idField="id" pagination="true" rownumbers="true" fitColumns="true" singleSelect="true">
    <thead>
    <tr>
        <th field="id" width="50">编号</th>
        <th field="measure"  width="50"  editor="{type:'validatebox',options:{required:true}}">暂缓措施</th>
        <th field="createDate" width="100" >创建时间</th>
        <th field="updateDate" width="100" >更新时间</th>
    </tr>
    </thead>
</table>

<div id="toolbar">
    <a href="javascript:$('#dg').edatagrid('addRow')" class="easyui-linkbutton" iconCls="icon-add" plain="true" >添加暂缓</a>
    <a href="javascript:delCustomerReprieve()" class="easyui-linkbutton" iconCls="icon-remove" plain="true" >删除暂缓措施</a>
    <a href="javascript:saveCustomerReprieve()" class="easyui-linkbutton" iconCls="icon-save" plain="true" >保存暂缓措施</a>
    <a href="javascript:$('#dg').edatagrid('cancelRow')" class="easyui-linkbutton" iconCls="icon-undo" plain="true" >撤销行</a>
    <a href="javascript:updateCustomerLossState()" class="easyui-linkbutton" iconCls="icon-zzkf" plain="true" >确认流失</a>
</div>
