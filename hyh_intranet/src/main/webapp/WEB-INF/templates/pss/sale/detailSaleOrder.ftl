<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${(sellOrder.orderNumber)!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${(sellOrder.billsDate)!}</span></div>
        </div>
        <#-- <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>出库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${(sellOrder.outboundStorage)!}</span></div>
        </div> -->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>客户编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${(sellOrder.partnerCode)!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>客户名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${(sellOrder.partnerName)!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${(sellOrder.summary)!}</span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2720px;">
                <thead>
                    <tr>
                        <td class="detail_order_item_index_thead_td">序号</td>
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
                        <td width="150px">金额</td>
                        <td width="150px">税率</td>
                        <td width="150px">税额</td>
                        <td width="150px">总计(元)</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody>
                	<#list sellOrder.orderItemList as i>
	                    <tr>
	                        <td class="detail_order_item_index_tbody_td">${i_index+1}</td>
	                        <td>${i.materialNo!}</td>
	                        <td>${i.materialName!}</td>
	                        <td>${i.specificationsModel!}</td>
	                        <td>${i.brand!}</td>
	                        <td>${i.season!}</td>
	                        <td>${i.barcode!}</td>
	                        <td>${i.unitName!}</td>
	                        <td>${i.counts!}</td>
	                        <td>${i.sellPrice!}</td>
	                        <td>${i.discount!}</td>
	                        <td>${i.afterDiscount!}</td>
	                        <td>${i.taxRate!}</td>
	                        <td>${i.tax!}</td>
	                        <td>${i.total!}</td>
	                        <td><input disabled="disabled" <#if i.isGift==true>checked="true"</#if> type="checkbox"></td>
	                        <td>${i.remark!}</td>
	                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${sellOrder.totalPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${sellOrder.giftPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${sellOrder.favorablePrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${sellOrder.gatheringPrice!}</span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${sellOrder.orderNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${sellOrder.ownerName!''}</span></div>
        </div>-->
    </div>
    <div class="order_btns_l common_text_div">
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>