<#setting classic_compatible=true>
<@insert template="login/right">
    <#if !response?? >
    <div class="head_top_">
        <#-- <div class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/copy_from.png' />"></a></div>
            <div class="rect_bottom">复制从</div>
        </div> -->
    </div>
    </#if>
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="receipt_number">${ purchaseReceipt.orderNumber }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
        <div class="common_text_title"><span data-required="true">入库仓库</span></div>
            <div class="common_text_val">
                <select id="inboundStorageId" class="my_radius grid_page_text">
                    <option value=""></option>
                    <#list storages as s>
                        <option <#if s.id==purchaseReceipt.inBoundStorage>selected</#if> value="${s.id}">${s.storageName}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${ purchaseReceipt.billDate }"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="receipt_summary" value="${ purchaseReceipt.summary }" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">供应商编码</span></div>
            <div class="common_text_val"><input id="partnerCode" value="${ purchaseReceipt.supplierCode }" class="input_text_common my_radius grid_page_text" onclick="openWinGetPartner(2);"><span id="id" style="display:none;">${ purchaseReceipt.supplierId }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="partnerName">${ purchaseReceipt.supplierName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsFirstName">${ purchaseReceipt.contactsFirstName }</span><span id="contactsLastName" class="readonly_text_common grid_page_text"> ${ purchaseReceipt.contactsLastName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsMobilephone">${ purchaseReceipt.mobile }</span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table id="receipt_table" style="width: 2570px;">
                <thead>
                    <tr>
                        <td width="20px"></td>
                        <td width="40px"></td>
                        <td width="170px">商品货号</td>
                        <td width="150px">商品名称</td>
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
                    <#list purchaseReceipt.items as item>
                            <tr id= "${item_index+1}">
                                <td width="20px">${item_index+1}</td>
                                <td><img onclick="addReceiptPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                                <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type = "chooseReceiptPurchaseOrderMaterial" name="materialNo" value = "${ item.materialNo }" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" value = "${item.materialId }" name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseReceiptPurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                                <td><span name="materialName" class="grid_text grid_readonly_text my_radius">${ item.materialName }</span></td>
                                <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius">${ item.specificationsModel }</span></td>
                                <td><span name="brand" class="grid_text grid_readonly_text my_radius">${ item.brand }</span></td>
                                <td><span name="season" class="grid_text grid_readonly_text my_radius">${ item.season }</span></td>
                                <td><span name="barcode" class="grid_text grid_readonly_text my_radius">${ item.barcode }</span></td>
                                <td><span name="unitName" class="grid_text grid_readonly_text my_radius">${ item.unitName }</span><input type = "hidden" value = "${item.minOrder}" name = "minOrder"></input></td>
                                <#if item.isBatch == true>
                                    <td><input name="batchNumber" value = "${item.batchNumber }" class="input_text_common my_radius grid_text"></input></td>
                                    <td><input name="productDate" data-type="date" value = "${item.productionDate }" class="input_text_common my_radius grid_text"></input></td>
                                <#else>
                                    <td><input name="batchNumber" class="grid_text grid_readonly_text my_radius" disabled="disabled"></input></td>
                                    <td><input name="productDate" data-type="date" class="grid_text grid_readonly_text my_radius" disabled="disabled"></input></td>
                                </#if>
                                <td><input name="counts" value = "${ item.counts }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="purchasePrice" value = "${ item.purchasePrice }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="discount" value = "${ item.discountRate }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="afterDiscount" value = "${ item.afterDiscount }" onblur = "getDiscount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="taxRate" value = "${ item.taxRate }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><span name="tax" class="grid_text grid_readonly_text my_radius">${ item.tax }</span></td>
                                <td><span name="total" class="grid_text grid_readonly_text my_radius">${ item.amount }</span></td>
                                <td><input name="isGift" <#if item.isGift==true>checked</#if> onclick="reset(this);" type="checkbox"></td>
                                <td><input name="remark" value = "${ item.remark }" class="input_text_common my_radius grid_text"></td>
                            </tr>
                    </#list>
                    <#list 0..(9-purchaseReceipt.items?size) as i>
                        <tr id= "${i+1+purchaseReceipt.items?size}">
                            <td width="20px">${i+1+purchaseReceipt.items?size}</td>
                            <td><img onclick="addReceiptPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                            <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type = "chooseReceiptPurchaseOrderMaterial" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseReceiptPurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                            <td><span name="materialName" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="brand" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="season" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="barcode" class="grid_text grid_readonly_text my_radius"></span></td>
                            <input type="hidden" name="unitId"><td><span name="unitName" class="grid_text grid_readonly_text my_radius"></span><input type = "hidden" name = "minOrder"></input></td>
                            <td><input name="batchNumber" class="input_text_common my_radius grid_text"></input></td>
                            <td><input name="productDate" data-type="date" class="input_text_common my_radius grid_text"></input></td>
                            <td><input name="counts" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
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
            <div class="common_text_val"><span id="totalPrice" class="readonly_text_common grid_page_text">${ purchaseReceipt.totalAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span id="giftPrice" class="readonly_text_common grid_page_text">${ purchaseReceipt.giftAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠金额</div>
            <div class="common_text_val"><input id="favorablePrice" value="${ purchaseReceipt.discountAmount }" onkeyup="getTotalAmount(this.id);" data-type="number" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付金额</span></div>
            <div class="common_text_val"><span id="payPrice" class="readonly_text_common grid_page_text">${ purchaseReceipt.payAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" >${loginUserName}</span><input type="hidden" id = "loginId" value = "${loginId}"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span></span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="空白占位符2"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span></span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="空白占位符3"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span></span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="空白占位符4"></span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="receipt_edit" class="btn_common my_radius btn_submit">保存</button>
        <#-- <button id="receipt_draft_edit" class="btn_common my_radius">存为草稿</button> -->
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>