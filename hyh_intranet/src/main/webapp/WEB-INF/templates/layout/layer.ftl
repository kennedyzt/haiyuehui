<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title></title>
    <#include "../layout/constant/constant_content.ftl"/>
    <@css href="css/main/user.css" />
    <@css href="css/main/finance.css" />
    <@css href="css/main/material.css" />
    <@css href="css/main/partner.css" />
    <@css href="css/main/purchase.css" />
    <@css href="css/main/sale.css" />
    <@css href="css/main/storage.css" />
    <@css href="css/siping-widget.css" />

    <@js src="js/siping/main/user.js" />
    <@js src="js/siping/main/finance.js" />
    <@js src="js/siping/siping-widget.js" />
    <@js src="js/siping/main/material.js" />
    <@js src="js/siping/main/partner.js" />
    <@js src="js/siping/main/purchase.js" />
    <@js src="js/siping/main/sale.js" />
    <@js src="js/siping/main/storage.js" />
    <@js src="js/siping/main/common.js" />
</head>
<body style="width: 100%;background-color: #EEF3FA;">
    <div class="layer_header">
        <div class="layer_title"><span class="vertical_center"></span><span id="_layer_title"></span></div>
        <div class="layer_close"><img id="layer_close_icon" src="<@static value='/icons/close.png' />" /></div>
    </div>
    <div class="layer_main"><@body /></div>
</body>
</html>