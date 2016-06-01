<#setting number_format=",###.##">
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>瑞乾科技 进销存管理</title>
    <#include "../layout/constant/constant_content.ftl" />
    <@js src="js/siping/wms/orderAudit.js" />
</head>
<body>
<div class="head_section">
<div style="height:50px;line-height:50px;">
    <div style="float:left"><span style="color:green;font-size:25px;">通过数</span><span id="auditNumber" style="color:green;font-size:25px;"></span></div>
    <div style="margin-left:10px;margin-right:10px;float:left">|</div>
    <div style="float:left"><span style="color:red;font-size:25px;">待核数</span><span id="noAuditNumber" style="color:red;font-size:25px;"></span></div>
</div>
<div style="clear:both"></div>
<div class="partner_text_div_3 common_text_div">
    <div class="common_text_title"><span>发货订单号</span></div>
    <div class="common_text_val"><input id="orderNumber" onchange="changeOrderNumber()" class="input_text_common my_radius"></div>
</div>
<div class="partner_text_div_3 common_text_div">
    <div class="common_text_title"><span>原始订单号</span></div>
    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="fromBillsNo" class="readonly_text_common grid_page_text"></span></div>
</div>
<div class="partner_text_div_3 common_text_div">
    <div class="common_text_title"><span>备注</span></div>
    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="summary" class="readonly_text_common grid_page_text"></span></div>
</div>
<div class="partner_text_div_3 common_text_div">
    <div class="common_text_title"><span>收货人</span></div>
    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="consigneeName" class="readonly_text_common grid_page_text"></span></div>
</div>
<div class="partner_text_div_3 common_text_div">
    <div class="common_text_title"><span>联系电话</span></div>
    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="consignessPhone" class="readonly_text_common grid_page_text"></span></div>
</div>
<div class="partner_text_div_3 common_text_div">
    <div class="common_text_title"><span>收货地址</span></div>
    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="consigneeAddress" class="readonly_text_common grid_page_text"></span></div>
</div>
<div class="partner_text_div_3 common_text_div">
    <div class="common_text_title"><span>商品二维码</span></div>
    <div class="common_text_val"><input id="materialInfo" onchange="scanMaterial()" class="input_text_common my_radius"></div>
</div>
</div>
<div class="grid_section" style="height:410px;overflow:auto;">
    <div class="data_add_grid" style="height:auto">
        <table style="">
            <thead>
                <tr>
                    <td width="12.5%">序号</td>
                    <td width="12.5%">商品条码</td>
                    <td width="12.5%">商品名称</td>
                    <td width="12.5%">批次号</td>
                    <td width="12.5%">订单数量</td>
                    <td width="12.5%">确认数量</td>
                    <td width="12.5%">计量单位</td>
                    <td width="12.5%">是否通过</td>
                </tr>
            </thead>
            <tbody id="auditTableTbody">
                
            </tbody>
        </table>
    </div>
</div>
<div class="grid_bottom">
</div>
<div class="order_btns_l common_text_div">
    <input type="hidden" value="" onclick="getOrderDataByTempAudit()" id="hiddenGetOrderDataByTempAudit"><#--方便layer的页面调用这个页面的JS使用的方法-->
    <button class="btn_common my_radius" onclick="getAllTempAuditOrder()">读取</button>
    <button class="btn_common my_radius" onclick="getAuditDataAndTemSave()">暂存</button>
    <button class="btn_common my_radius" onclick="confirmAuditWithoutFlag()">提交</button>
</div>
</body>
</html>