<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#-- <div class="rect_block">
            <div class="rect_top"><a href="<@url value='/purchaseapplyorder/copyto?orderNumber=${ purchaseApplyOrder.orderNumber }' />"><img src="<@static value='/icons/copy_to.png' />"></a></div>
            <div class="rect_bottom">复制到</div>
        </div> -->
    </div>
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseApplyOrder.orderNumber!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseApplyOrder.billDate!'' }</span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>有效期至</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseApplyOrder.orderExpireDate!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>必需日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseApplyOrder.lastDate!'' }</span></div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>申请者</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseApplyOrder.applierName!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseApplyOrder.summary!'' }</span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 1520px;">
                <thead>
                    <tr>
                        <td class="detail_order_item_index_thead_td">序号</td>
                        <td width="150px">商品货号</td>
                        <td width="150px">商品名称</td>
                        <td width="150px">规格及型号</td>
                        <td width="150px">品牌</td>
                        <td width="150px">季节</td>
                        <td width="150px">国际编码</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">数量</td>
                        <#--<td width="150px">必需日期</td>-->
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="apply_po_items">
                    <#list purchaseApplyOrder.items as item>
                    <tr>
                        <td class="detail_order_item_index_tbody_td">${item_index+1}</td>
                        <td>${ item.materialNo!'' }</td>
                        <td>${ item.materialName!'' }</td>
                        <td>${ item.specificationsModel!'' }</td>
                        <td>${ item.brand!'' }</td>
                        <td>${ item.season!'' }</td>
                        <td>${ item.barcode!'' }</td>
                        <td>${ item.unitName!'' }</td>
                        <td>${ item.counts!'' }</td>
                        <#-- <td>${ item.lastDate!'' }</td>-->
                        <td>${ item.remark!'' }</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseApplyOrder.ownerName }</span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <#-- <button id="" class="btn_common my_radius">打印预览</button> -->
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>