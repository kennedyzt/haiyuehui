<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.orderNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.billsDate}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>出库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.storageName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>客户</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.shopName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>发货日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.shipmentsDate}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>收货人姓名</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consigneeName}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>收货人电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consignessPhone}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>收货人地址</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consigneeAddress}</span></div>
        </div>
        <#-- <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>邮编</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consignessPostcode}</span></div>
        </div> -->
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">备注</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.summary}</span></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 2700px;">
                <thead>
                    <tr>
                        <td width="5%">商品货号</td>
                        <td width="5%">商品名称</td>
                        <td width="5%">规格型号</td>
                        <td width="5%">条码</td>
                        <td width="5%">计量单位</td>
                        <td width="5%">库位</td>
                        <td width="5%">批次号</td>
                        <td width="5%">出厂日期</td>
                        <td width="5%">到期日期</td>
                        <td width="5%">数量</td>
                        <td width="5%">单价</td>
                        <td width="5%">折扣</td>
                        <td width="5%">金额</td>
                        <td width="5%">税率</td>
                        <td width="5%">税额</td>
                        <td width="5%">总计</td>
                        <td width="5%">是否赠品</td>
                        <td width="5%">备注</td>
                    </tr>
                </thead>
                <tbody>
                    <#list readyShipments.items as i>
                        <tr>
                            <td>${i.materialNo}</td>
                            <td>${i.materialName}</td>
                            <td>${i.specificationsModel}</td>
                            <td>${i.barcode}</td>
                            <td>${i.unitName}</td>
                            <td>${i.locationName}</td>
                            <td>${i.batchNumber}</td>
                            <td>${i.productionDate}</td>
                            <td>${i.dueDate}</td>
                            <td>${i.counts}</td>
                            <td>${i.sellPrice}</td>
                            <td>${i.discount}</td>
                            <td>${i.afterDiscount}</td>
                            <td>${i.taxRate}</td>
                            <td>${i.tax}</td>
                            <td>${i.total}</td>
                            <td><input <#if i.isGift==true>checked</#if> disabled="disabled" type="checkbox"></td>
                            <td>${i.remark}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
    </div>
    <div class="order_btns_l common_text_div">
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>