<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <#include "../../layout/constant/constant_content.ftl"/>
    <@css href="css/siping-widget.css" />
    <@js src="js/highcharts/highcharts.js" />
    <script>
        $(document).ready(function(){
            var now=new Date();
            var nowYear = now.getFullYear();
            var selector='<select id="keys" class="my_radius">'; 
            var input= '<input id="keys" class="input_text_common my_radius" onclick="laydate({istime: false,format: \'YYYY-MM\'})" placeholder="日期" style="width:85px;">';    
            for(var i=-20;i<21;i++){
                var num=nowYear+i;
                if(num==nowYear){
                    selector+='<option value="'+num+'" selected="selected" >'+num+'</option>'; 
                }else{
                    selector+='<option value="'+num+'">'+num+'</option>'; 
                }
            }
            selector+='</select>';
            $("#date").append(selector);
            $("#btn").trigger("click");
        });
        function change(){
            var now=new Date();
            var nowYear = now.getFullYear();
            var selector='<select id="keys" class="my_radius">'; 
            var input= '<input id="keys" class="input_text_common my_radius" onclick="laydate({istime: false,format: \'YYYY-MM\'})" placeholder="日期" style="width:85px;">';    
            for(var i=-20;i<21;i++){
                var num=nowYear+i;
                if(num==nowYear){
                    selector+='<option value="'+num+'" selected="selected" >'+num+'</option>'; 
                }else{
                    selector+='<option value="'+num+'">'+num+'</option>'; 
                }
            }
            selector+='</select>';
            if( $('#reportType').val()==1){
                $("#date").empty();
                $("#date").append(selector);
                query();
            }else if($('#reportType').val()==2){
                $("#date").empty();
                $("#date").append(selector);
                query();
            }else if($('#reportType').val()==3){
                $("#date").empty();
                $("#date").append(input);
                var currentMonth=now.getMonth()+1;
                if(parseInt(currentMonth) < 10){
                    $("#keys").val(nowYear+'-0'+ currentMonth);
                }else{
                    $("#keys").val(nowYear+'-'+ currentMonth);
                }
                query();  
            }          
        }
        
       function query(){
            var request = {};
            request.queryType = $("#reportType").val();
            request.keys =  $("#keys").val();
            siping.ajax({
                method : "post",
                url : 'sellreport/getsellordercount',
                async : true, 
                data :  JSON.stringify(request),
                contentType : "application/json",
                success : function(data) {
                    highcharts(data)
                }
            });
       }
       
       function highcharts(data){
            $('#container').highcharts({
            chart: {
                type: 'column'
            },
            title: {
                text: '订单量'
            },
            xAxis: {
                categories: data.categories,
                crosshair: true
            },
            yAxis: {
                min: 0,
                title: {
                    text: '订单量'
                }
            },
            tooltip: {
                headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
                pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                    '<td style="padding:0"><b>{point.y:.1f} </b></td></tr>',
                footerFormat: '</table>',
                shared: true,
                useHTML: true
            },
            plotOptions: {
                column: {
                    pointPadding: 0.2,
                    borderWidth: 0
                }
            },
            series: [{
                name: '订单量',
                data: data.series
            }]
        });
       }
    </script>
</head>
<body>
<div class="list_page">
    <div class="search_section">
        <div class="report_search_text common_text_div">
            <div class="common_text_title">类型</div>
            <div class="common_text_val">
                <select id="reportType" class="my_radius" onchange="change();">
                    <option value="1" selected="selected">按年</option>
                    <option value="2">按月</option>
                    <option value="3">按日</option>
                </select>
            </div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">日期</div>
            <div id="date" class="common_text_val">
           </div>
        </div>
        <div class="report_search_text common_text_div" style="text-align:right;">
            <button id="btn" onclick="query();" class="btn_common my_radius btn_search">查询</button>
        </div>
        <div style="clear:both"></div>
        <div style="clear:both"></div>
    </div>
    <div id="container"></div>
</div>
</body>
</html>
