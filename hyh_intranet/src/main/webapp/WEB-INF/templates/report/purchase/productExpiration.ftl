<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <#include "../../layout/constant/constant_content.ftl"/>
    <@js src="js/siping/report/productExp.js" />
</head>
<body>
<div class="list_page">
    <div class="head_top_">
        <div class="rect_block" onclick="preViewProductExpiration();"> 
            <div class="rect_top"><a><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
    </div>
    <div class="search_section">
        <div class="partner_text_div common_text_div" style="width: 50%;float:left;">
            <div class="common_text_title">过期天数</div>
            <div class="common_text_val"><input style="width: 40%;" value="30" name="expDays" id="exp_days_" onkeyup="checkNumForExpDays(this);" class="input_text_common my_radius" placeholder="输入天数" ></div>
        </div>
        <div class="search_btn common_text_div">
            <input type="hidden" id="user_language" value="ch">
            <button onclick="supplierExpGrid(1,10);" class="btn_common my_radius btn_search">查询</button>
        </div>
        <div style="clear:both"></div>
    </div>
    <div id="product_exp_grid"></div>
    <div style="display:none;" id="product_exp_grid_print"></div>
</div>
</body>
</html>