<!doctype html>
<html>
<head>
    <#include "common.ftl" >

</head>
<body>

<div id="container" style="min-width:400px;height:400px"></div>

<script type="text/javascript" src="https://code.highcharts.com.cn/highcharts/highcharts.js"></script>
<script type="text/javascript" src="https://code.highcharts.com.cn/highcharts/modules/exporting.js"></script>
<script type="text/javascript" src="https://img.hcharts.cn/highcharts-plugins/highcharts-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/static/js/customer.gc.js"></script>
<#--<script type="text/javascript">
    var chart = Highcharts.chart('container',{
        chart: {
            type: 'column'
        },
        title: {
            text: 'crm 客户构成分析'
        },
        subtitle: {
            text: '数据来源: www.crm.com'
        },
        xAxis: {
            categories: ['大客户','合作伙伴','战略合作伙伴'],
            crosshair: true
        },
        yAxis: {
            min: 0,
            title: {
                text: '个'
            }
        },
        tooltip: {
            // head + 每个 point + footer 拼接成完整的 table
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} 个</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        plotOptions: {
            column: {
                borderWidth: 0
            }
        },
        series: [
            {
                name: '客户数量',
                data: [200,100,50]
            }
        ]
    });
</script>-->
</body>