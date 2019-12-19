$(function () {
   loadData();
});

function loadData() {

    $.ajax({
       type:"post",
       url:ctx+"/customer/countCustomerLevelGroupByLevel",
        dataType:"json",
        success:function (data) {

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
                    categories: data.data1,
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
                        data:data.data2
                    }
                ]
            });
        }
    });


}