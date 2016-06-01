<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "../../layout/constant/constant_content.ftl"/>
    <@css href="css/siping-widget.css" />
    <@js src="js/siping/main/material.js" />
    <script type="text/javascript">
        $(function(){
            getMaterialTypeList(1, 10);
        });
    </script>
</head>
<body>
<div class="list_page">
    <div class="search_section">
        <div class="partner_text_div common_text_div">
            <div class="common_text_title">关键字</div>
            <div class="common_text_val">
                <input id="material_name_key" class="input_text_common my_radius" data-click-tip="类别名称关键字" placeholder="类别名称" >
                <button class="btn_common my_radius btn_search" onclick="getMaterialTypeList(1, 10);">查询</button>
            </div>
        </div>
        <div style="clear:both"></div>
    </div>
    <div id="materailtype_list_grid"></div>
</div>
</body>
</html>