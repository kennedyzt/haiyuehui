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
    <div class="search_section">
        <div class="partner_text_div common_text_div" style="width: 50%;float:left;">
            <div class="common_text_title">Expiration Days</div>
            <div class="common_text_val"><input style="width: 40%;" value="30" name="expDays" id="exp_days_" onkeyup="checkNumForExpDays(this);" class="input_text_common my_radius" placeholder="enter a number of days" ></div>
        </div>
        <div class="search_btn common_text_div">
            <input type="hidden" id="user_language" value="en">
            <button onclick="supplierExpGrid(1,10);" class="btn_common my_radius btn_search">Search</button>
        </div>
        <div style="clear:both"></div>
    </div>
    <div id="product_exp_grid"></div>
</div>
</body>
</html>