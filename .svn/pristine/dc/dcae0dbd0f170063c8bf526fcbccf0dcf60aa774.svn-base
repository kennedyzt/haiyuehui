<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <#include "../../layout/constant/constant_content.ftl"/>
    <@js src="js/siping/report/purchaseDetail.js" />
</head>
<body>
<div class="list_page">
    <div class="search_section">
        <div class="report_search_text common_text_div">
            <div class="common_text_title">日期</div>
            <div class="common_text_val">
                <input id="dateFrom" class="input_text_common my_radius" placeholder="开始日期" value="${dateFrom!''}" style="width:85px;" readonly="readonly">至
                <input id="dateTo" class="input_text_common my_radius" placeholder="结束日期" value="${dateTo!''}" style="width:85px;" readonly="readonly">
            </div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">供应商</div>
            <div class="common_text_val">
                <input id="supplier_no" value="${supplierNo!''}" type="hidden">
                <input id="supplier_name" value="${supplierName!''}" class="input_text_common my_radius" style="width:85%;" readonly="readonly">
            </div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">商品</div>
            <div class="common_text_val">
                <input id="product_no" value="${productNo!''}" type="hidden">
                <input id="product_name" value="${productName!''}" class="input_text_common my_radius" style="width:85%;" readonly="readonly">
            </div>
        </div>
        <div class="report_button_text common_text_div">
            <button onclick="preViewPurchaseDetail();" class="btn_common my_radius btn_search">打印</button>
        </div>
        <div style="clear:both"></div>
    </div>
    <div id="purchase_detail_grid"></div>
    <div style="display:none;" id="purchase_detail_grid_print"></div>
</div>
</body>
</html>
