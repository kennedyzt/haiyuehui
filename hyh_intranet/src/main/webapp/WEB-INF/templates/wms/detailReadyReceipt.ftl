<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <a><div class="rect_block" onclick="openWinGetConsignee();">
            <div class="rect_top"><img src="<@static value='/icons/copy_to.png' />"></div>
            <div class="rect_bottom">生成收货单</div>
        </div></a>
    </div>
    <div class="search_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span id="orderNumber" class="readonly_text_common grid_page_text">${ readyReceipt.orderNumber }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.billDate }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>收货日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.receiptDate }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>入库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.inBoundStorageName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商编码</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.partnerCode }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.partnerName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.contactsFirstName }${ readyReceipt.contactsLastName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.mobile }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.ownerName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ readyReceipt.summary }</span></div>
        </div>
    </div>
    <div class="data_add_grid">
        <table style="width: 1841px;font-size:13px">
            <thead>
                <tr>
                    <td style="width: 35px;" class="detail_order_item_index_thead_td">序号</td>
                    <td style="width: 50px;"><input id="selectAll" type="checkbox" onclick="selectAll();" /></td>
                    <td style="width: 200px;">商品货号</td>
                    <td style="width: 384.1px;">商品名称</td>
                    <td style="width: 200px;">规格及型号</td>
                    <td style="width: 150px;">品牌</td>
                    <td style="width: 200px;">季节</td>
                    <td style="width: 200px;">国际编码</td>
                    <td style="width: 100px;">计量单位</td>
                    <td style="width: 100px;">数量</td>
                    <td style="width: 200px;">备注</td>
                </tr>
            </thead>
            <tbody id="po_items">
                <#list readyReceipt.items as item>
                <tr>
                    <td class="detail_order_item_index_tbody_td">${item_index+1}</td>
                    <td><input id="${ item.rowNumber }" type="checkbox" <#if item.readyStatus == 1>disabled = "true"</#if> name="check_box"></td>
                    <td>${ item.materialNo }</td>
                    <td>${ item.materialName }</td>
                    <td>${ item.specificationsModel }</td>
                    <td>${ item.brand }</td>
                    <td>${ item.season }</td>
                    <td>${ item.barcode }</td>
                    <td>${ item.unitName }</td>
                    <td>${ item.counts }</td>
                    <td>${ item.remark }</td>
                </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <@js src="js/siping/wms/detailReadyReceipt.js" />
</@insert>