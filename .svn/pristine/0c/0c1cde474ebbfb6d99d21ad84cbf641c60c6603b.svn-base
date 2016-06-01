<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="orderNumber">${sellOrder.orderNumber!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" value="${sellOrder.billsDate!}" data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">发货日期</span></div>
            <div class="common_text_val"><input id="" value="" data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">出库仓库</span></div>
            <div class="common_text_val">
                <select id="outboundStorageId" class="my_radius grid_page_text">
                    <option value=""></option>
                    <#list storages as s>
                        <option <#if s.id==sellOrder.outboundStorage>selected</#if> value="${s.id}">${s.storageName}</option>
                    </#list>>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">客户编号</span></div>
            <div class="common_text_val"><input value="${sellOrder.partnerCode!}" class="input_text_common my_radius grid_page_text" onkeyup="getSaleCusList(this)"><span id="id" style="display:none;">${sellOrder.partnerId!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>客户名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="partnerName">${sellOrder.partnerName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsFirstName">${sellOrder.partnerContactsFirstName!}</span><span class="readonly_text_common grid_page_text" id="contactsLastName">${sellOrder.partnerContactsLastName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="contactsMobilephone">${sellOrder.partnerContactsMobilephone!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input value="${sellOrder.summary!}" id="summary" class="input_text_common my_radius grid_page_text"></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2720px;">
                <thead>
                    <tr>
                        <td width="20px"></td>
                        <td width="150px">商品货号</td>
                        <td width="150px">商品描述</td>
                        <td width="150px">规格及型号</td>
                        <td width="150px">品牌</td>
                        <td width="150px">季节</td>
                        <td width="150px">国际编码</td>
                        <td width="150px">计量单位</td>
                        <td width="150px">数量</td>
                        <td width="150px">单价(元)</td>
                        <td width="150px">折扣(%)</td>
                        <td width="150px">税率</td>
                        <td width="150px">税额</td>
                        <td width="150px">总计(元)</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">发货数量</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="dataSource">
                	<#list sellOrder.orderItemList as p>
	                    <tr id="${p_index+1}" class="grid_row">
	                        <#if p_index==0>
	                        	<td><img id="sale_order_add_row" src="<@static value='/icons/tree_hide.png' />"></td>
	                        <#else>
	                        	<td><img onclick="removeGridRowData(this);" src="<@static value='/icons/tree_show.png' />"></td>
	                        </#if>
	                        <td><input value="${p.materialNo!}" class="input_text_common my_radius grid_text" onkeyup="getSaleOrderSurProList(this)"><span id="id${p_index+1}" style="display:none">${p.materialId!}</span></td>
	                        <td><span id="materialName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.materialName!}</span></td>
	                        <td><span id="specificationsModel${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.specificationsModel!}</span></td>
	                        <td><span id="brand${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.brand!}</span></td>
	                        <td><span id="season${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.season!}</span></td>
	                        <td><span id="barcode${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.barcode!}</span></td>
	                        <td><span id="unitName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.unitName!}</span></td>
	                        <td><input id="counts${p_index+1}" value="${p.counts!}" data-type="number" onkeyup="getSaleorderTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="purchasePrice${p_index+1}" value="${p.purchasePrice!}" data-type="number" onkeyup="getSaleorderTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="discount${p_index+1}" value="${p.discount!}" onkeyup="getSaleorderTotal(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
	                        <td><input id="taxRate${p_index+1}" value="${p.taxRate!}" data-type="number" onkeyup="getSaleorderTotal(this);" class="input_text_common my_radius grid_text"></td>
	                        <td><span id="tax${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.tax!}</span></td>
	                        <td><input id="total${p_index+1}" value="${p.total!}" data-type="number" class="input_text_common my_radius grid_text" onkeyup="getInverseDiscount(this);"></td>
	                        <td><input id="isGift${p_index+1}" <#if p.isGift==true>checked</#if> onclick="getSaleorderTotal(this);" type="checkbox"></td>
	                        <td><input id="shipmentsAmount${p_index+1}" value="${p.shipmentsAmount!}" class="input_text_common my_radius grid_text"></td>
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
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalPrice">${sellOrder.totalPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="giftPrice">${sellOrder.giftPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠</div>
            <div class="common_text_val"><input id="favorablePrice" value="${sellOrder.favorablePrice!}" onkeyup="getSaleorderTotal("favorablePrice");" data-type="number" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="payPrice">${sellOrder.payPrice!}</span></div>
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
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${loginUserName}</span><input id="loginId" value="${loginId}" type="hidden"></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="saleorder_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="saleorder_draft_smt" class="btn_common my_radius">存为草稿</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>