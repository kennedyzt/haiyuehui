<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <#include "../../layout/constant/constant_content.ftl"/>
    <@js src="js/siping/report/inventoryBalanceDetail.js" />
</head>
<body>
<div class="list_page">
    <div class="search_section">
        <div class="report_search_text common_text_div">
            <div class="common_text_title">日期</div>
            <div class="common_text_val"><input id="billsDate"  class="input_text_common my_radius" onclick="laydate({istime: false,format: 'YYYY-MM-DD'})" style="width:85px;" disabled="disabled"
                <#if request?? && (request.billsDate)?? && request.billsDate!='null'> value="${request.billsDate!''}" </#if>
            ></div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">商品</div>
            <div class="common_text_val"><input id="material" class="input_text_common my_radius" disabled="disabled"
                <#if request?? && (request.materialName)?? && request.materialName!='null'> value="${request.materialName!''}" </#if>
            ></div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">商品类型</div>
            <div class="common_text_val">
                <input id="fatherType" data-type="materialType" style="width:200px;" name="materialType" readonly class="input_text_common_small my_radius"  disabled="disabled"
                    <#if request?? && (request.materialTypeName)?? && request.materialTypeName!='null'> value="${request.materialTypeName!''}" </#if>
                >
            </div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">仓库</div>
            <div class="common_text_val"><input id="stroage" class="input_text_common my_radius" disabled="disabled"
                <#if request?? && (request.stroageName)?? && request.stroageName!='null'> value="${request.stroageName!''}" </#if>
            ></div>
        </div>
        <div class="report_button_text common_text_div" style="text-align:right;width:33%">
            <button onclick="preInventoryBalanceDetail();" class="btn_common my_radius btn_search">打印</button>
        </div>
        <div style="clear:both"></div>
        <div style="clear:both"></div>
    </div>
    <div id="inventory_balance_detail_grid"></div>
    <div style="display:none;" id="inventory_balance_detail_grid_print"></div>
</div>
</body>
</html>