<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
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
    <@js src="js/siping/main/material.js" />
    <@js src="js/siping/main/partner.js" />
    <@js src="js/siping/main/purchase.js" />
    <@js src="js/siping/main/sale.js" />
    <@js src="js/siping/main/storage.js" />
    <@js src="js/siping/main/postperiod.js" />
    <@js src="js/siping/main/common.js" />
    <@js src="js/siping/siping-widget.js" />
    <@js src="js/jquery/jquery.qrcode.min.js" />
    <script>
        function qrcode(){
            jQuery('#code').qrcode({ 
                render: "canvas", //table方式 
                width: 500,
                height: 400,
                background: "#ffffff", //背景颜色 
                foreground: "red", //前景颜色 
                text: "batch_number:123131312313&material_no=45646456" //
            });
        }
     </script>
</head>
<@body />
</html>