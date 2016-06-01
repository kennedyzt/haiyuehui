<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#-- <div class="rect_block">
            <div class="rect_top"><a href="#"><img src="<@static value='/icons/copy_from.png' />"></a></div>
            <div class="rect_bottom">复制从</div>
        </div> -->
    </div>
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.returnsNumber!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.billsDate!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>入库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.storageName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.summary!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.partnerCode!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.partnerName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.contactsName!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.mobilephone!}</span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2570px;">
                <thead>
                    <tr>
                        <td class="detail_order_item_index_thead_td">序号</td>
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
                        <td width="150px">折扣(%)</td>
                        <td width="150px">金额</td>
                        <td width="150px">税率</td>
                        <td width="150px">税额</td>
                        <td width="150px">总计</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody>
                	<#list purchaseReturns.purchaseReturnsItems as p>
	                    <tr>
	                        <td class="detail_order_item_index_tbody_td">${p_index+1}</td>
	                        <td>${p.materialNo!}</td>
	                        <td>${p.materialName!}</td>
	                        <td>${p.specificationsModel!}</td>
	                        <td>${p.brand!}</td>
	                        <td>${p.season!}</td>
	                        <td>${p.barcode!}</td>
	                        <td>${p.batchNumber!}</td>
	                        <td>${p.productionDate}</td>
	                        <td>${p.unitName!}</td>
	                        <td>${p.counts!}</td>
	                        <td>${p.purchasePrice!}</td>
	                        <td>${p.discount!}</td>
	                        <td>${p.afterDiscount!}</td>
	                        <td>${p.taxRate!}</td>
	                        <td>${p.tax!}</td>
	                        <td>${p.total!}</td>
	                        <td><input disabled="disabled" <#if p.isGift==true>checked="true"</#if> type="checkbox"></td>
	                        <td>${p.remark!}</td>
	                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.totalPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.giftPrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.favorablePrice!}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.payPrice!}</span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.auditor!}</span></div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${purchaseReturns.ownerName!}</span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <#-- <button id="" class="btn_common my_radius">打印预览</button> -->
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>