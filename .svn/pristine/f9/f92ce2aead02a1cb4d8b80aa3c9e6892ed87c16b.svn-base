<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <#include "../../layout/constant/constant_content.ftl"/>
    <@css href="css/siping-widget.css" />
    <@js src="js/siping/siping-widget.js" />
    <@js src="js/siping/report/inventoryBalance.js" />
</head>
<body>
<div class="list_page">
    <div class="head_top_">
        <div class="rect_block" onclick="preViewInventoryBalance();"> 
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
    </div>
    <div class="search_section">
        <div class="report_search_text common_text_div">
            <div class="common_text_title">日期</div>
            <div class="common_text_val"><input id="billsDate"  class="input_text_common my_radius" onclick="laydate({istime: false,format: 'YYYY-MM-DD'})" placeholder="日期" style="width:85px;"></div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">商品</div>
            <div class="common_text_val"><input id="material" class="input_text_common my_radius" placeholder="请输入商品货号、名称"></div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">商品类型</div>
            <div class="common_text_val">
                 <input type="hidden" id="father_id" name="materialTypeId" value="" >
                <input id="fatherType" data-type="materialType" style="width:200px;" name="materialType" readonly class="input_text_common_small my_radius" value="" >
                <div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" style="width:200px;" hidden="hidden"></div>
            </div>
        </div>
        <div class="report_search_text common_text_div">
            <div class="common_text_title">仓库</div>
            <div class="common_text_val"><input id="stroage" class="input_text_common my_radius" placeholder="请输入仓库编码、名称"></div>
        </div>
        <div class="report_button_text common_text_div">
            <button onclick="inventoryBalanceGrid(1,10)" class="btn_common my_radius btn_search">查询</button>
        </div>
        <div style="clear:both"></div>
        <div style="clear:both"></div>
    </div>
    <div id="inventory_balance_grid"></div>
    <div style="display:none;" id="inventory_balance_grid_print"></div>
</div>
</body>
</html>