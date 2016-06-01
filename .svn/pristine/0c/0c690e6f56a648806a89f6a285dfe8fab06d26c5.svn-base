<#setting classic_compatible=true>
<@insert template="login/right">
<!--startprint-->
<div id="container">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.orderNumber!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.billDate!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>收货日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.receiptDate!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>入库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.inBoundStorageName!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商编码</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.supplierCode!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.supplierName!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.contactsFirstName!'' }${ purchaseOrder.contactsLastName!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.mobile!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.summary!'' }</span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid" style="height:auto">
             <table style="page-break-after:always;">
                <thead style="display:table-header-group">
                    <tr>
                        <td>商品货号</td>
                        <td>商品名称</td>
                        <td>规格及型号</td>
                        <td>品牌</td>
                        <td>计量单位</td>
                        <td>数量</td>
                        <td>单价(元)</td>
                        <td>折扣(%)</td>
                        <td>金额</td>
                        <td>税率</td>
                        <td>税额</td>
                        <td>总计</td>
                        <td>赠品</td>
                        <td>收货数量</td>
                        <td>备注</td>
                    </tr>
                </thead>
                <tbody id="po_items">
                    <#list purchaseOrder.items as item>
                    <tr>
                        <td>${ item.materialNo!'' }</td>
                        <td>${ item.materialName!'' }</td>
                        <td>${ item.specificationsModel!'' }</td>
                        <td>${ item.brand!'' }</td>
                        <td>${ item.unitName!'' }</td>
                        <td>${ item.counts!'' }</td>
                        <td>${ item.purchasePrice!'' }</td>
                        <td>${ item.discountRate!'' }</td>
                        <td>${ item.afterDiscount!'' }</td>
                        <td>${ item.taxRate!'' }</td>
                        <td>${ item.tax!'' }</td>
                        <td>${ item.amount!'' }</td>
                        <#if item.isGift == true>
                            <td><input type="checkbox" disabled="disabled" checked="true"></td>
                        <#else>
                            <td><input type="checkbox" disabled="disabled"></td>
                        </#if>
                        <td>${ item.receiptCounts!'' }</td>
                        <td>${ item.remark!'' }</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.totalAmount!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.discountAmount!'' }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.payAmount!'' }</span></div>
        </div>
        <#--<div class="grid_page_text_div common_text_div">
            <div class="common_text_title">审核人</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.auditorName!'' }</span></div>
        </div>-->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text">${ purchaseOrder.ownerName!'' }</span></div>
        </div>
    </div>
</div>
    <!--endprint-->
        <div class="order_btns_l common_text_div">
            <button class="btn_common my_radius" onclick="preview()"">打印</button>
            <button class="btn_common my_radius">返回</button>
        </div>
</@insert>