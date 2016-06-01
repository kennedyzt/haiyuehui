<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="receiptNumber">${inventoryReceipt.receiptNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" value="${inventoryReceipt.billsDate}" data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">入库仓库</span></div>
            <div class="common_text_val">
                <select id="inboundStorageId" class="my_radius grid_page_text">
                    <option value=""></option>
                    <#list storages as s>
                        <option <#if s.id == inventoryReceipt.inboundStorageId>selected</#if> value="${s.id}">${s.storageName}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="summary" value="${inventoryReceipt.summary}" class="input_text_common my_radius grid_page_text"></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 1620px;">
                <thead>
                    <tr>
                        <td width="20px"></td>
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
                        <td width="150px">总计(元)</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="dataSource">
                    <#list inventoryReceipt.inventoryReceiptItemResponses as i>
                        <tr id="${i_index+1}" class="grid_row">
                            <#if p_index==0>
                                <td><img id="return_purchase_add_row" src="<@static value='/icons/tree_hide.png' />"></td>
                            <#else>
                                <td><img onclick="removeGridRowData(this);" src="<@static value='/icons/tree_show.png' />"></td>
                            </#if>
                            <td><input value="${i.materialNo}" class="input_text_common my_radius grid_text" onkeyup="getInventorySurProList(this)"><span id="id${i_index+1}" style="display:none;">${i.materialId}</span></td>
                            <td><span id="materialName${i_index+1}" class="grid_text grid_readonly_text my_radius">${i.materialName}</span></td>
                            <td><span id="specificationsModel${i_index+1}" class="grid_text grid_readonly_text my_radius">${i.specificationsModel}</span></td>
                            <td><span id="brand${i_index+1}" class="grid_text grid_readonly_text my_radius">${i.brand}</td>
                            <td><span id="season${i_index+1}" class="grid_text grid_readonly_text my_radius">${i.season}</span></td>
                            <td><span id="barcode${i_index+1}" class="grid_text grid_readonly_text my_radius">${i.barcode}</span></td>
                            <#if i.isBatch == true>
                                <td><input id="batchNumber${i_index+1}" value="${i.batchNumber!}" class="input_text_common my_radius grid_text" onclick="getBatchNumber();"></td>
                                <td><input id="productionDate${i_index+1}" value="${i.productionDate!}" data-type="date" class="input_text_common my_radius grid_text"></td>
                            <#else>
                                <td><input id="batchNumber${i_index+1}" readonly class="grid_text grid_readonly_text my_radius"></td>
                                <td><input id="productionDate${i_index+1}" readonly class="grid_text grid_readonly_text my_radius"></td>
                            </#if>
                            <td><span id="unitName${i_index+1}" class="grid_text grid_readonly_text my_radius"></span></td>
                        <td><input id="counts${i_index+1}" value="${i.counts}" data-type="number" onkeyup="getTotal(this);" class="input_text_common my_radius grid_text"></td>
                        <td><input id="price${i_index+1}" value="${i.purchasePrice}" data-type="number" onkeyup="getTotal(this);" class="input_text_common my_radius grid_text"></td>
                        <td><span id="total${i_index+1}" class="grid_text grid_readonly_text my_radius">${i.total}</span></td>
                        <td><input id="isGift${i_index+1}" <#if i.isGift == true>selected</#if>  type="checkbox"></td>
                        <td><input id="remark${i_index+1}" value="${i.remark}" class="input_text_common my_radius grid_text"></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalPrice">${inventoryReceipt.totalPrice}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalGiftPrice">${inventoryReceipt.totalGiftPrice}</span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val">
                <select id="auditor" class="my_radius grid_page_text">
                    <option value=""></option>
                    <option value="1">李四</option>
                    <option value="2">李六</option>
                </select>
            </div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="owner">${loginUserName}</span><input id="loginId" value="${loginId}" type="hidden"></span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="storage_receipt_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="storage_receipt_draft_smt" class="btn_common my_radius">存为草稿</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>