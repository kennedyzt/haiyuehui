<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <#include "../../layout/constant/constant_content.ftl"/>
    <@js src="js/siping/report/customerSale.js" />
</head>
<body>
<div class="list_page">
    <div class="head_top_">
        <div class="rect_block" onclick="preViewCustomerSale();"> 
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
    </div>
    <div class="search_section">
        <div class="report_search_text common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val"><input id="keyword" class="input_text_common my_radius" placeholder="输入客户编码、名称"></div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">日期</div>
            <div class="common_text_val">
                <input id="dateFrom" data-type = "firstday" class="input_text_common my_radius" placeholder="开始日期" style="width:85px;">至
                <input id="dateTo" data-type = "lastday" class="input_text_common my_radius" placeholder="结束日期" style="width:85px;">
            </div>
        </div>
        <div class="report_button_text common_text_div">
            <button onclick="customerSaleGrid(1,10)" class="btn_common my_radius btn_search">查询</button>
        </div>
        <div style="clear:both"></div>
        <div style="clear:both"></div>
    </div>
    <div id="customer_sale_grid"></div>
    <div style="display:none;" id="customer_sale_grid_print"></div>
</div>
</body>
</html>
