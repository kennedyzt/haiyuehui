<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""><#if inventoryShipments??>${inventoryShipments.shipmentsNumber}</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""><#if inventoryShipments??>${inventoryShipments.billsDate}</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>出库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""><#if inventoryShipments??>${inventoryShipments.outboundStorageName}</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""><#if inventoryShipments??>${inventoryShipments.summary}</#if></span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2570px;">
                <thead>
                    <tr>
                        <td class="detail_order_item_index_thead_td">序号</td>
                        <td width="150px">商品货号</td>
                        <td width="150px">商品名称</td>
                        <td width="150px">规格及型号</td>
                        <td width="150px">品牌</td>
                        <td width="150px">季节</td>
                        <td width="150px">国际编码</td>
                        <td width="150px">批次号</td>
                        <td width="150px">生产日期</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">数量</td>
                        <td width="150px">单价(元)</td>
                        <td width="150px">总计</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody>
                    <#if inventoryShipments?? && (inventoryShipments.shipmentsItemList)??>
                        <#list inventoryShipments.shipmentsItemList as item>
                            <tr>
                                <td class="detail_order_item_index_tbody_td">${item_index+1}</td>
                                <td width="150px">${item.materialNo}</td>
                                <td width="150px">${item.materialName}</td>
                                <td width="150px">${item.specificationsModel}</td>
                                <td width="150px">${item.brand}</td>
                                <td width="150px">${item.season}</td>
                                <td width="150px">${item.barcode}</td>
                                <td width="150px">${item.batchNumber}</td>
                                <td width="150px">${item.productionDate}</td>
                                <td width="150px">${item.unitName}</td>
                                <td width="150px">${item.counts}</td>
                                <td width="150px">${item.sellPrice}</td>
                                <td width="150px">${item.total}</td>
                                <td width="150px">
                                    <#if (item.isGift)?? && item.isGift>是<#else>否</#if>
                                </td>
                                <td width="150px">${item.remark}</td>
                            </tr>
                        </#list>    
                    </#if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""><#if inventoryShipments??>${inventoryShipments.totalPrice}</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""><#if inventoryShipments??>${inventoryShipments.giftPrice}</#if></span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""><#if inventoryShipments??>${inventoryShipments.ownerName}</#if></span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <#-- <button id="" class="btn_common my_radius">打印预览</button> -->
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>