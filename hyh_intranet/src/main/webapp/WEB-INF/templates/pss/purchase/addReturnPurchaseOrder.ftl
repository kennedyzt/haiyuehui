<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#-- <div class="rect_block">
            <div class="rect_top"><a onclick="getOrderCopyFrom('purchaseorderreturn')"><img src="<@static value='/icons/copy_from.png' />"></a></div>
            <div class="rect_bottom">复制从</div>
        </div> -->
    </div>
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据编号</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="returnsNumber">${returnsNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" <#if purchaseReceiptOrder?exists>value = "${ purchaseReceiptOrder.billDate }"</#if> data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">出库仓库</span></div>
            <div class="common_text_val">
                <select id="inboundStorageId" class="my_radius grid_page_text">
                    <option value="0">虚拟仓库</option>
                <#--<#list storages as s>
                        <option <#if purchaseReceiptOrder?exists><#if purchaseReceiptOrder.inBoundStorage == s.id>selected</#if></#if> value="${s.id}">${s.storageName}</option>
                    </#list>-->
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="summary" class="input_text_common my_radius grid_page_text"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.summary }</#if></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">供应商编码</span></div>
            <div class="common_text_val"><input id="partnerCode" <#if purchaseReceiptOrder?exists>value = "${ purchaseReceiptOrder.supplierCode }"</#if> class="input_text_common my_radius grid_page_text" onclick="openWinGetPartner(2);"><span id="id" style="display:none;"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="partnerName"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.supplierName }</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsFirstName"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.contactsFirstName }</#if></span><span class="readonly_text_common grid_page_text" id="contactsLastName"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.contactsLastName }</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsMobilephone"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.mobile }</#if></span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2570px;">
                <thead>
                    <tr>
                        <td width="20px"></td>
                        <td width="40px"></td>
                        <td width="150px">商品货号</td>
                        <td width="150px">商品描述</td>
                        <td width="150px">规格及型号</td>
                        <td width="150px">品牌</td>
                        <td width="150px">季节</td>
                        <td width="150px">国际编码</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">批次号</td>
                        <td width="150px">生产日期</td>
                        <td width="150px">数量</td>
                        <td width="150px">单价(元)</td>
                        <td width="150px">折扣(%)</td>
                        <td width="150px">金额</td>
                        <td width="150px">税率</td>
                        <td width="150px">税额</td>
                        <td width="150px">总计</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="dataSource">
                <#if purchaseReceiptOrder?exists>
                    <#assign step = "${purchaseReceiptOrder.items?size}">
                    <#list purchaseReceiptOrder.items as i>
                        <tr id= "${i_index+1}">
                        <td width="20px">${i_index+1}</td>
                        <td><img onclick="addReturnPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                        <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type = "returnPurchaseOrder" value = "${i.materialNo}" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" value = "${i.materialId}" name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseReturnPurchaseMaterial',true,false,false,this);"></div></div></td>
                        <td><span name="materialName" class="grid_text grid_readonly_text my_radius">${i.materialName}</span></td>
                        <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius">${i.specificationsModel}</span></td>
                        <td><span name="brand" class="grid_text grid_readonly_text my_radius">${i.brand}</span></td>
                        <td><span name="season" class="grid_text grid_readonly_text my_radius">${i.season}</span></td>
                        <td><span name="barcode" class="grid_text grid_readonly_text my_radius">${i.barcode}</span></td>
                        <td><span name="unitName" class="grid_text grid_readonly_text my_radius">${i.unitName}</span><input type = "hidden" name = "minOrder"></input></td>
                        <#if i.isBatch == true >
                            <td><input name="batchNumber" value = "${i.batchNumber}" onclick="getBatchNumList(this);" class="input_text_common my_radius grid_text"></input></td>
                        <#else>
                            <td><input name="batchNumber" class="grid_text grid_readonly_text my_radius" disabled = "true"></input></td>
                        </#if>
                        <td><span name="productDate" disabled = "disabled" class="grid_text grid_readonly_text my_radius">${i.productionDate}</span></td>
                        <td><input name="batchCounts" type="hidden"><input name="counts" value = "${i.counts}" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input name="purchasePrice" value = "${i.purchasePrice}" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input name="discount" value = "${i.discountRate}" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input name="afterDiscount" value = "${i.afterDiscount}" onblur = "getDiscount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input name="taxRate" value = "${i.taxRate}" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><span name="tax" class="grid_text grid_readonly_text my_radius">${i.tax}</span></td>
                        <td><span name="total" class="grid_text grid_readonly_text my_radius">${i.amount}</span></td>
                        <td><input <#if i.isGift == true>checked</#if> name="isGift" onclick="reset(this);" type="checkbox"></td>
                        <td><input name="remark" value = "${i.remark}" class="input_text_common my_radius grid_text"></td>
                    </tr>
                    </#list>
                    <#else>
                        <#assign step = "0">
                    </#if>
                    <#list 0..(9-step?number) as i>
                        <tr id= "${step?number+i+1}">
                            <td width="20px">${step?number+i+1}</td>
                            <td><img onclick="addReturnPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                            <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type = "chooseReturnPurchaseMaterial" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseReturnPurchaseMaterial',true,false,false,this);"></div></div></td>
                            <td><span name="materialName" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="brand" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="season" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="barcode" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="unitName" class="grid_text grid_readonly_text my_radius"></span><input type = "hidden" name = "minOrder"></input></td>
                            <td><input name="batchNumber" onclick="getBatchNumList(this);" class="input_text_common my_radius grid_text"></input></td>
                            <td><span name="productDate" disabled = "disabled" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><input name="batchCounts" type="hidden"><input name="counts" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                            <td><input name="purchasePrice" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                            <td><input name="discount" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                            <td><input name="afterDiscount" onblur = "getDiscount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                            <td><input name="taxRate" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                            <td><span name="tax" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="total" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><input name="isGift" onclick="reset(this);" type="checkbox"></td>
                            <td><input name="remark" class="input_text_common my_radius grid_text"></td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalPrice"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.totalAmount }<#else>0.00</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="giftPrice"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.giftAmount }<#else>0.00</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠</div>
            <div class="common_text_val"><input id="favorablePrice" <#if purchaseReceiptOrder?exists>value = "${ purchaseReceiptOrder.discountAmount }"<#else>value="0.00"</#if> onkeyup="getTotalAmount(this.id);" data-type="number" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="payPrice"><#if purchaseReceiptOrder?exists>${ purchaseReceiptOrder.payAmount }<#else>0.00</#if></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="owner">${loginUserName}</span><input id="loginId" value="${loginId}" type="hidden"></span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="return_po_smt" class="btn_common my_radius btn_submit">保存</button>
        <#-- <button id="return_draft_po_smt" class="btn_common my_radius">存为草稿</button> -->
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
        <div id="hide_batch_number_data" style="display: none">
    </div>
</@insert>