<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span><input type = "hidden" id = "fromBillsNo" value = "${inventoryReceipt.fromBillsNo}"></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="receiptNumber">${inventoryReceipt.receiptNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${inventoryReceipt.billsDate}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>入库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${inventoryReceipt.storageName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${inventoryReceipt.consigneeName}</span></div>
        </div>
    </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2570px;">
                <thead>
                    <tr>
                        <td class="detail_order_item_index_thead_td">序号</td>
                        <td width="100px">商品货号</td>
                        <td width="100px">商品名称</td>
                        <td width="100px">规格及型号</td>
                        <td width="100px">品牌</td>
                        <td width="100px">季节</td>
                        <td width="100px">国际编码</td>
                        <td width="100px">计量单位</td>
                        <td width="100px">批次号</td>
                        <td width="100px">生产日期</td>
                        <td width="100px">到期日期</td>
                        <td width="100px">应收数量</td>
                        <td width="100px">实收数量</td>
                        <td width="100px">状态</td>
                    </tr>
                </thead>
                <tbody id = "dataSource">
                	<#list inventoryReceipt.inventoryReceiptItemResponses as i>
	                    <tr>
	                        <td class="detail_order_item_index_tbody_td">${i_index+1}</td>
	                        <td width="100px">${i.materialNo}<input type =hidden name = "rowNumber" value = "${i.rowNumber}"></td>
	                        <td width="100px">${i.materialName}</td>
	                        <td width="100px">${i.specificationsModel}</td>
	                        <td width="100px">${i.brand}</td>
	                        <td width="100px">${i.season}</td>
	                        <td width="100px">${i.barcode}</td>
	                        <td width="100px">${i.unitName}</td>
	                        <td name = "batchNumber" width="100px">${i.batchNumber}</td>
	                        <td name = "productionDate" width="100px">${i.productionDate}</td>
	                        <td width="100px">${i.expirationDate}</td>
	                        <td width="100px">${i.counts}</td>
	                        <td width="100px">${i.receiptCounts}/${i.counts}<input name = "receiptCounts" type = "hidden" value = "${i.receiptCounts}"></td>
	                        <td width="100px"><#if i.status = 0>未确认<#else>已确认</#if><input name = "status" type = "hidden" value = "${i.status}"></td>
	                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button <#if inventoryReceipt.status == 2>disabled="disabled"</#if> onclick = "this.disabled=true;confirmReceipt();" class="btn_common my_radius">确认</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>
<script>
function confirmReceipt(){
    var itemReceiptCounts = [];
    $("#dataSource tr").each(function(){
        if ( Number($(this).find("input[name = 'status']").val()) == 0 && $(this).find("input[name = 'receiptCounts']").val() != 0) {
            var item = {};
            item.rowNumber = $(this).find("input[name = 'rowNumber']").val();
            item.receiptCounts = $(this).find("input[name = 'receiptCounts']").val();
            item.batchNumber = $(this).find("td[name = 'batchNumber']").text();
            item.productionDate = $(this).find("td[name = 'productionDate']").text();
            itemReceiptCounts.push(item);
        }
    });
    if(itemReceiptCounts==""){
        siping.alert(0, '没有需确认收货的项不能提交');
        return;
    }
    var request = {};
    request.fromBillsNo = $("#fromBillsNo").val();
    request.receiptNumber = $("#receiptNumber").text();
    request.itemReceiptCounts = itemReceiptCounts;
    siping.ajax({
        method : "post",
        url : 'inventoryreceipt/confirmreceipt',
        data : JSON.stringify(request),
        dataType : "json",
        contentType : "application/json",
        success : function(data) {
            if (data.success) {
                siping.alert(1, data.msg);
            } else {
                siping.alert(0, data.msg);
            }
        }
    });
}
</script>