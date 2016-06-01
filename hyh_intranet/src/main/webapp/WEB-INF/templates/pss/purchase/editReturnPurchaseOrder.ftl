<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="returnsNumber">${purchaseReturns.returnsNumber!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" value="${purchaseReturns.billsDate!}" data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">入库仓库</span></div>
            <div class="common_text_val">
                <select id="inboundStorageId" class="my_radius grid_page_text">
                    <option value=""></option>
                    <#list storages as s>
                        <option <#if s.id==purchaseReturns.outboundStorageId>selected</#if> value="${s.id!}">${s.storageName!}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input value="${purchaseReturns.summary!}" id="summary" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">供应商</span></div>
            <div class="common_text_val"><input value="${purchaseReturns.partnerCode!}" class="input_text_common my_radius grid_page_text" onkeyup="getRetSupList(this)"><span id="id" style="display:none;">${purchaseReturns.partnerId}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="partnerName">${purchaseReturns.partnerName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsFirstName">${purchaseReturns.contactsName!}</span><span class="readonly_text_common grid_page_text" id="contactsLastName"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsMobilephone">${purchaseReturns.mobilephone!}</span></div>
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
                <#--<tbody id="dataSource">
                	<#list purchaseReturns.purchaseReturnsItems as p>
	                    <tr id="${p_index+1}" class="grid_row">
	                    	<#if p_index==0>
	                        	<td><img id="return_purchase_add_row" src="<@static value='/icons/tree_hide.png' />"></td>
	                        <#else>
	                        	<td><img onclick="removeGridRowData(this);" src="<@static value='/icons/tree_show.png' />"></td>
	                        </#if>
	                        <td><input id="materialId${p_index+1}" value="${p.materialNo!}" class="input_text_common my_radius grid_text" onkeyup="getRetProList(this)"><span id="id${p_index+1}" style="display:none">${p.materialId!}</span></td>
	                        <td><span id="materialName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.materialName!}</span></td>
	                        <td><span id="specificationsModel${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.specificationsModel!}</span></td>
	                        <td><span id="brand${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.brand!}</span></td>
	                        <td><span id="season${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.season!}</span></td>
	                        <td><span id="barcode${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.barcode!}</span></td>
	                        <#if p.isBatch == true>
    	                        <td><input id="batchNumber${p_index+1}" value="${p.batchNumber!}" class="input_text_common my_radius grid_text" onclick="getBatchNumber();"></td>
    	                        <td><span id="productionDate'+id+'" class="grid_text grid_readonly_text my_radius">${p.productionDate!}</span></td>
	                        <#else>
    	                        <td><input id="batchNumber${p_index+1}" readonly class="input_text_common my_radius grid_text"></td>
                                <td><span id="productionDate'+id+'" class="grid_text grid_readonly_text my_radius"></span></td>
	                        </#if>
	                        <td><span id="unitName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.unitName!}</span></td>
	                        <td><input id="counts${p_index+1}" value="${p.counts!}" data-type="number" onkeyup="getPurchaseTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="purchasePrice${p_index+1}" value="${p.purchasePrice!}" data-type="number" onkeyup="getPurchaseTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="discount${p_index+1}" value="${p.discount!}" data-type="number" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="taxRate${p_index+1}" value="${p.taxRate!}" data-type="number" onkeyup="getPurchaseTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><span id="tax${p_index+1}"  onkeyup="getPurchaseTotal(this);" class="grid_text grid_readonly_text my_radius">${p.tax!}</span></td>
	                        <td><input id="total${p_index+1}" value="${p.total!}" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getInverseDiscount(this);"></td>
	                        <td><input id="isGift${p_index+1}" <#if p.isGift==true>checked</#if> onclick="getPurchaseTotal(this);" type="checkbox"></td>
	                        <td><input id="remark${p_index+1}" value="${p.remark!}" class="input_text_common my_radius grid_text"></td>
	                    </tr>
                    </#list>
                </tbody>-->
                <tbody id="dataSource">
                    <#list purchaseReturns.purchaseReturnsItems as item>
                            <tr id= "${item_index+1}">
                                <td width="20px">${item_index+1}</td>
                                <td><img onclick="addReturnPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                                <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type = "chooseReturnPurchaseMaterial" name="materialNo" value = "${ item.materialNo }" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" value = "${item.materialId }" name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseReturnPurchaseMaterial',true,false,false,this);"></div></div></td>
                                <td><span name="materialName" class="grid_text grid_readonly_text my_radius">${ item.materialName }</span></td>
                                <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius">${ item.specificationsModel }</span></td>
                                <td><span name="brand" class="grid_text grid_readonly_text my_radius">${ item.brand }</span></td>
                                <td><span name="season" class="grid_text grid_readonly_text my_radius">${ item.season }</span></td>
                                <td><span name="barcode" class="grid_text grid_readonly_text my_radius">${ item.barcode }</span></td>
                                <td><span name="unitName" class="grid_text grid_readonly_text my_radius">${ item.unitName }</span><input type = "hidden" value = "${item.minOrder}" name = "minOrder"></input></td>
                                <#if item.isBatch == true>
                                    <td><input name="batchNumber" value = "${item.batchNumber }" class="input_text_common my_radius grid_text"></input></td>
                                    <td><input name="productDate" disabled="disabled" data-type="date" value = "${item.productionDate }" class="input_text_common my_radius grid_text"></input></td>
                                <#else>
                                    <td><input name="batchNumber" class="grid_text grid_readonly_text my_radius" disabled="disabled"></input></td>
                                    <td><input name="productDate" data-type="date" class="grid_text grid_readonly_text my_radius" disabled="disabled"></input></td>
                                </#if>
                                <td><input name="counts" value = "${ item.counts }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="purchasePrice" value = "${ item.purchasePrice }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="discount" value = "${ item.discount }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="afterDiscount" value = "${ item.afterDiscount }" onblur = "getDiscount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="taxRate" value = "${ item.taxRate }" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><span name="tax" class="grid_text grid_readonly_text my_radius">${ item.tax }</span></td>
                                <td><span name="total" class="grid_text grid_readonly_text my_radius">${ item.amount }</span></td>
                                <td><input name="isGift" <#if item.isGift==true>checked</#if> onclick="reset(this);" type="checkbox"></td>
                                <td><input name="remark" value = "${ item.remark }" class="input_text_common my_radius grid_text"></td>
                            </tr>
                    </#list>
                    <#list 0..(9-purchaseReturns.purchaseReturnsItems?size) as i>
                        <tr id= "${i+1+purchaseReturns.purchaseReturnsItems?size}">
                            <td width="20px">${i+1+purchaseReturns.purchaseReturnsItems?size}</td>
                            <td><img onclick="addReturnPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                            <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" data-order-type = "chooseReturnPurchaseMaterial" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" name = "materialId"><div class="more" onclick="openWinGetMaterials('chooseReturnPurchaseMaterial',true,false,false,this);"></div></div></td>
                            <td><span name="materialName" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="brand" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="season" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="barcode" class="grid_text grid_readonly_text my_radius"></span></td>
                            <input type="hidden" name="unitId"><td><span name="unitName" class="grid_text grid_readonly_text my_radius"></span><input type = "hidden" name = "minOrder"></input></td>
                            <td><input name="batchNumber" class="input_text_common my_radius grid_text"></input></td>
                            <td><input name="productDate" disabled="disabled" data-type="date" class="input_text_common my_radius grid_text"></input></td>
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
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalPrice">${purchaseReturns.totalPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="giftPrice">${purchaseReturns.giftPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠</div>
            <div class="common_text_val"><input id="favorablePrice" value="${purchaseReturns.favorablePrice!}" onkeyup="getPurchaseTotal('favorablePrice');" data-type="number" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="payPrice">${purchaseReturns.payPrice!}</span></div>
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
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="owner">${loginUserName!}</span><input id="loginId" value="${loginId!}" type="hidden"></span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="return_po_edit_smt" class="btn_common my_radius btn_submit">保存</button>
        <#-- <button id="return_po_draft_edit_smt" class="btn_common my_radius">存为草稿</button> -->
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>