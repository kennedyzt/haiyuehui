<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="shipmentsNumber">${sellShipments.shipmentsNumber!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" value="${sellShipments.billsDate!}" data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">出库仓库</span></div>
            <div class="common_text_val">
                <select id="outboundStorageId" class="my_radius grid_page_text">
                    <option value=""></option>
                    <#list storages as s>
                        <option <#if s.id?c==sellShipments.outboundStorage>selected</#if> value="${s.id}">${s.storageName}</option>
                    </#list>>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">客户</span></div>
            <div class="common_text_val"><input value="${sellShipments.partnerCode!}" class="input_text_common my_radius grid_page_text" onkeyup="getDeliverySaleOradrCustList(this)"><span id="id" style="display:none;">${sellShipments.partnerId!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>客户名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="partnerName">${sellShipments.partnerName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsFirstName">${sellShipments.partnerContactsFirstName!}</span><span class="readonly_text_common grid_page_text" id="contactsLastName">${sellShipments.partnerContactsLastName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsMobilephone">${sellShipments.partnerContactsMobilephone!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input value="${sellShipments.summary!}" id="summary" class="input_text_common my_radius grid_page_text"></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2570px;">
                <thead>
                    <tr>
                        <td width="20px"></td>
                        <td width="150px">商品货号</td>
                        <td width="150px">商品描述</td>
                        <td width="150px">规格及型号</td>
                        <td width="150px">品牌</td>
                        <td width="150px">季节</td>
                        <td width="150px">国际编码</td>
                        <td width="150px">批次号</td>
                        <td width="150px">生产日期</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">数量</td>
                        <td width="150px">单价(元)</td>
                        <td width="150px">折扣(%)</td>
                        <td width="150px">税率</td>
                        <td width="150px">税额</td>
                        <td width="150px">总计(元)</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="deliverySODate">
                	<#list sellShipments.shipmentsItemList as p>
	                    <tr id="${p_index+1}" class="grid_row">
	                    	<#if p_index==0>
	                        	<td><img id="delivery_sale_order_add_row" src="<@static value='/icons/tree_hide.png' />"></td>
	                        <#else>
	                        	<td><img onclick="removeGridRowData(this);" src="<@static value='/icons/tree_show.png' />"></td>
	                        </#if>
	                        <td><input value="${p.materialNo!}" class="input_text_common my_radius grid_text" onkeyup="getDeliverySaleOrderProList(this)"><span id="id${p_index+1}" style="display:none">${p.materialId!}</span></td>
	                        <td><span id="materialName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.materialName!}</span></td>
	                        <td><span id="specificationsModel${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.specificationsModel!}</span></td>
	                        <td><span id="brand${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.brand!}</span></td>
	                        <td><span id="season${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.season!}</span></td>
	                        <td><span id="barcode${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.barcode!}</span></td>
	                        <td><span id="batchNumber${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.batchNumber!}</span></td>
	                        <td><span id="productionDate${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.productionDate!}</span></td>
	                        <td><span id="unitName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.unitName!}</span></td>
	                        <td><input id="counts${p_index+1}" value="${p.counts!}" data-type="number" onkeyup="getDeliverySaleOrderTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="sellPrice${p_index+1}" value="${p.sellPrice!}" data-type="number" onkeyup="getDeliverySaleOrderTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="discount${p_index+1}" value="${p.discount!}" data-type="number" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="taxRate${p_index+1}" value="${p.taxRate!}" data-type="number" onkeyup="getDeliverySaleOrderTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><span id="tax${p_index+1}" onkeyup="getDeliverySaleOrderTotal(this);" class="grid_text grid_readonly_text my_radius">${p.tax!}</span></td>
	                        <td><input id="total${p_index+1}" value="${p.total!}" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getInverseDiscount(this);"></td>
	                        <td><input id="isGift${p_index+1}" <#if p.isGift==true>checked</#if> onkeyup="getDeliverySaleOrderTotal(this);" type="checkbox"></td>
	                        <td><input id="remark${p_index+1}" value="${p.remark!}" class="input_text_common my_radius grid_text"></td>
	                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalPrice">${sellShipments.totalPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="giftPrice">${sellShipments.giftPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠</div>
            <div class="common_text_val"><input id="favorablePrice" value="${sellShipments.favorablePrice!}" onkeyup="getDeliverySaleOrderTotal('favorablePrice');" data-type="number" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="gatheringPrice">${sellShipments.gatheringPrice!}</span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val">
                <select id="" class="my_radius grid_page_text">
                    <option value=""></option>
                    <option value="1">李四</option>
                    <option value="2">李六</option>
                </select>
            </div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${loginUserName}</span><input id="loginId" value="${loginId}" type="hidden"></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="delivery_so_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="delivery_so_draft_smt" class="btn_common my_radius">存为草稿</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
    <div id="hide_delivery_sale_order" style="display: none">
	</div>
</@insert>