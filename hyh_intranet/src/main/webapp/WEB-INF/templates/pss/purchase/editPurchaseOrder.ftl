<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="orderNumber">${ purchaseOrder.orderNumber!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" data-type="date" class="input_text_common my_radius grid_page_text" value="${purchaseOrder.billDate!'' }"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>收货日期</span></div>
            <div class="common_text_val"><input id="po_receiptDate" value="${ purchaseOrder.receiptDate!'' }" data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">入库仓库</div>
            <div class="common_text_val">
                <select id="inboundStorageId" class="my_radius grid_page_text">
                  <option value="0">虚拟仓库</option>
                   <#-- <#list storages as s>
                        <option value="${s.id}">${s.storageName}</option>
                   </#list> -->
                </select>
            </div>
        </div>
        <span style="display:none" id="id" >${ purchaseOrder.supplierId!'' }</span>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">供应商编码</span></div>
            <div class="common_text_val"><input id="partnerCode" value="${ purchaseOrder.supplierCode!'' }" class="input_text_common my_radius grid_page_text" onclick="openWinGetPartner(2);"><span id="id" style="display:none;"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="partnerName">${ purchaseOrder.supplierName!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span id="contactsFirstName" class="readonly_text_common grid_page_text">${ purchaseOrder.contactsFirstName!'' }</span><span id="contactsLastName" class="readonly_text_common grid_page_text"> ${ purchaseOrder.contactsLastName!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsMobilephone">${ purchaseOrder.mobile!'' }</span></div>
        </div>
         <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>币种</span></div>
            <div class="common_text_val">
                <select id="currency" class="my_radius grid_page_text" disabled="disabled">
                    <option <#if purchaseOrder.currency=="人民币">selected</#if> value="人民币">人民币 RMB</option>
                    <option <#if purchaseOrder.currency=="美元">selected</#if> value="美元">美元 USD</option>
                    <option <#if purchaseOrder.currency=="日元">selected</#if> value="日元">日元 JPY</option>
                    <option <#if purchaseOrder.currency=="欧元">selected</#if> value="欧元">欧元 EUR</option>
                    <option <#if purchaseOrder.currency=="英镑">selected</#if> value="英镑">英镑 GBP</option>
                    <option <#if purchaseOrder.currency=="德国马克">selected</#if> value="德国马克">德国马克 DEM</option>
                    <option <#if purchaseOrder.currency=="瑞士法郎">selected</#if> value="瑞士法郎">瑞士法郎 CHF</option>
                    <option <#if purchaseOrder.currency=="法国法郎">selected</#if> value="法国法郎">法国法郎 FRF</option>
                    <option <#if purchaseOrder.currency=="加拿大元">selected</#if> value="加拿大元">加拿大元 CAD</option>
                    <option <#if purchaseOrder.currency=="澳大利亚元">selected</#if> value="澳大利亚元">澳大利亚元 AUD</option>
                    <option <#if purchaseOrder.currency=="港币">selected</#if> value="港币">港币 HKD</option>
                    <option <#if purchaseOrder.currency=="意大利里拉">selected</#if> value="意大利里拉">意大利里拉 ITL</option>
                    <option <#if purchaseOrder.currency=="荷兰盾">selected</#if> value="荷兰盾">荷兰盾 NLG</option>
                    <option <#if purchaseOrder.currency=="葡萄牙埃斯库多">selected</#if> value="葡萄牙埃斯库多">葡萄牙埃斯库多 PTE</option>
                    <option <#if purchaseOrder.currency=="西班牙比塞塔">selected</#if> value="西班牙比塞塔">西班牙比塞塔 ESP</option>
                    <option <#if purchaseOrder.currency=="俄罗斯卢布">selected</#if> value="俄罗斯卢布">俄罗斯卢布 SUR</option>
                    <option <#if purchaseOrder.currency=="新加坡元">selected</#if> value="新加坡元">新加坡元 SGD</option>
                    <option <#if purchaseOrder.currency=="韩国元">selected</#if> value="韩国元">韩国元 KRW</option>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>汇率</span></div>
            <div class="common_text_val"><input id="exchangeRate" disabled="disabled" class="input_text_common my_radius grid_page_text" value="${purchaseOrder.exchangeRate!''}"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="po_summary" value="${ purchaseOrder.summary!'' }" class="input_text_common my_radius grid_page_text"></div>
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
                        <td width="150px">商品名称</td>
                        <td width="150px">规格及型号</td>
                        <td width="150px">品牌</td>
                        <td width="150px">季节</td>
                        <td width="150px">国际编码</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">外币单价</td>
                        <td width="150px">数量</td>
                        <td width="150px">单价(元)</td>
                        <td width="150px">折扣(%)</td>
                        <td width="150px">金额</td>
                        <td width="150px">税率(%)</td>
                        <td width="150px">税额</td>
                        <td width="150px">总计</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <#--<tbody id="dataSource">
                    <#list purchaseOrder.items as item>
                    <tr id=${item_index+1} class="grid_row">
                        <#if item_index==0> <td><img id="po_add_row" src="<@static value='/icons/tree_hide.png' />"></td>
                        <#else> <td><img onclick="removeGridRowData(this)" src="<@static value='/icons/tree_show.png' />"></td>
                        </#if>
                        <td><span style="display:none" id="id${item_index+1}">${item.materialId!''}</span><input id="po_materialNo${item_index+1}" value="${ item.materialNo!'' }" class="input_text_common my_radius grid_text" onkeyup="getProListInPurchase(this)"></td>
                        <td><span id="materialName${item_index+1}" class="grid_text grid_readonly_text my_radius">${ item.materialName!'' }</span></td>
                        <td><span id="specificationsModel${item_index+1}" class="grid_text grid_readonly_text my_radius">${ item.specificationsModel!'' }</span></td>
                        <td><span id="brand${item_index+1}" class="grid_text grid_readonly_text my_radius">${ item.brand!'' }</span></td>
                        <td><span id="season${item_index+1}" class="grid_text grid_readonly_text my_radius">${ item.season!'' }</span></td>
                        <td><span id="barcode${item_index+1}" class="grid_text grid_readonly_text my_radius">${ item.barcode!'' }</span></td>
                        <input type="hidden" id="unitId${item_index+1}">
                        <td><span id="unitName${item_index+1}" class="grid_text grid_readonly_text my_radius">${ item.unitName!'' }</span></td>
                        <td><input id="counts${item_index+1}" onkeyup="getPurchaseTotal(this);"  value="${ item.counts!'' }" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input id="purchasePrice${item_index+1}" onkeyup="getPurchaseTotal(this);"  value="${ item.purchasePrice!'' }" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input id="discount${item_index+1}" onkeyup="getPurchaseTotal(this);"  value="${ item.discountRate!'' }" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input id="taxRate${item_index+1}" onkeyup="getPurchaseTotal(this);" value="${ item.taxRate!'' }" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><span id="tax${item_index+1}" class="grid_text grid_readonly_text my_radius">${ item.tax!'' }</span></td>
                        <td><input id="total${item_index+1}" value="${ item.amount!'' }" class="input_text_common my_radius grid_text"></td>
                        <td><input id="isGift${item_index+1}" <#if item.isGift==true>checked</#if> onclick="getPurchaseTotal(this);" type="checkbox"></td>
                        <td><input id="receiptCounts${item_index+1}" value="${ item.receiptCounts!'' }" data-type="number" class="input_text_common my_radius grid_text"></td>
                        <td><input id="remark${item_index+1}" value="${ item.remark!'' }" class="input_text_common my_radius grid_text"></td>
                    </tr>
                    </#list>
                </tbody>-->
                 <tbody id="dataSource">
                    <#list purchaseOrder.items as item>
                            <tr id= "${item_index+1}">
                                <td width="20px">${item_index+1}</td>
                                <td><img onclick="addPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                                <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" name="materialNo" value = "${ item.materialNo }" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" value = "${item.materialId }" name = "materialId"><div class="more" onclick="openWinGetMaterials('choosePurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                                <td><span name="materialName" class="grid_text grid_readonly_text my_radius">${ item.materialName }</span></td>
                                <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius">${ item.specificationsModel }</span></td>
                                <td><span name="brand" class="grid_text grid_readonly_text my_radius">${ item.brand }</span></td>
                                <td><span name="season" class="grid_text grid_readonly_text my_radius">${ item.season }</span></td>
                                <td><span name="barcode" class="grid_text grid_readonly_text my_radius">${ item.barcode }</span></td>
                                <td><span name="unitName" class="grid_text grid_readonly_text my_radius">${ item.unitName }</span><input type = "hidden" value = "${item.minOrder}" name = "minOrder"></input></td>
                                <td><input name="currencyPrice" value = "${ item.currencyPrice }" onkeyup="getRMBPriceAmount(this)" data-type="number" class="input_text_common my_radius grid_text"></td>
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
                    <#--<#list 0..(9-purchaseOrder.items?size) as i>
                        <tr id= "${i+1+purchaseOrder.items?size}">
                            <td width="20px">${i+1+purchaseOrder.items?size}</td>
                            <td><img onclick="addPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                            <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" name = "materialId"><div class="more" onclick="openWinGetMaterials('choosePurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                            <td><span name="materialName" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="brand" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="season" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="barcode" class="grid_text grid_readonly_text my_radius"></span></td>
                            <input type="hidden" name="unitId"><td><span name="unitName" class="grid_text grid_readonly_text my_radius"></span><input type = "hidden" name = "minOrder"></input></td>
                            <td><input name="currencyPrice" onkeyup="getRMBPriceAmount(this)" data-type="number" class="input_text_common my_radius grid_text"></td>
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
                    </#list>-->
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalPrice">${ purchaseOrder.totalAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span id="giftPrice" class="readonly_text_common grid_page_text">${ purchaseOrder.giftAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠金额</div>
            <div class="common_text_val"><input id="favorablePrice" value="${ purchaseOrder.discountAmount }" data-type="number" onkeyup="getTotalAmount(this.id);" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="payPrice">${ purchaseOrder.payAmount }</span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val">
                <select id="po_auditor" class="my_radius grid_page_text">
                    <option value=""></option>
                    <option value="1">李四</option>
                    <option value="2">李六</option>
                </select>
            </div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <input type="hidden" id="owner">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" >${loginUserName}</span><input type="hidden" id = "loginId" value = "${loginId}"></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="po_edit" class="btn_common my_radius btn_submit">保存</button>
        <button id="po_draft_edit" class="btn_common my_radius">存为草稿</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>