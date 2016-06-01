<#setting classic_compatible=true>
<@js src="js/siping/wms/detailOrderPick.js" />
<@insert template="login/right">
    <div class="head_top_">
        <div class="rect_block">
            <div class="rect_top"><a onclick = "preDoPrintOrderPick('${orderPick.orderNumber}');"><img src="<@static value='/icons/preview.png' />"></a></div>
            <div class="rect_bottom">打印预览</div>
        </div>
    </div>
    <div id = "detail_order_pick">
        <div>
            <div style = "margin-top :20px;margin-bottom:20px;">
                <div style = "text-align:center;">拣配清单（纸质打印单）</div>
            </div>
            <div class="head_section">
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>波次号</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${orderPick.orderNumber}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>操作日期</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${orderPick.billsDate}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>操作人</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${orderPick.loginUserName}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>仓库</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${orderPick.storageName}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>SKU总数</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">(${orderPick.skuCounts})</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>商品总数</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">(${orderPick.materialCounts})</span></div>
                </div>
            </div>
            <table class="table1" style="font-size:12px;">
                <tbody>
                    <tr>
                        <td width="3%">序号</td>
                        <td width="9%">库位号</td>
                        <td width="4%">商品序号</td>
                        <td width="9%">商品货号</td>
                        <td width="9%">国际编码</td>
                        <td width="9%">商品名</td>
                        <td width="9%">数量</td>
                        <td width="6%">单位</td>
                        <td width="9%">批次号</td>
                        <td width="9%">原始订单号</td>
                        <td width="9%">发货单号</td>
                        <td width="6%">拣货状态</td>
                    </tr>
                    <#list orderPick.storageLocationOrderPickItems as s>
                        <tr>
                            <td width="3%" rowspan="${s.items?size}">${s_index+1}</td>
                            <td width="9%" rowspan="${s.items?size}">${s.locationName}</td>
                            <#list s.items as i>
                                <#if i_index==0>
                                    <td width="4%">${i_index+1}</td>
                                    <td width="9%">${i.materialNo}</td>
                                    <td width="9%">${i.barcode}</td>
                                    <td width="6%">${i.materialName}</td>
                                    <td width="9%">${i.counts}</td>
                                    <td width="9%">${i.unitName}</td>
                                    <td width="9%">${i.batchNumber}</td>
                                    <td width="9%">${i.ecOrderNumber}</td>
                                    <td width="9%">${i.fromBillsNo}</td>
                                    <td width="6%"></td>
                                </#if>
                            </#list>
                        </tr>
                        <#list s.items as i>
                            <#if i_index!=0>
                                <tr>
                                    <td width="4%">${i_index+1}</td>
                                    <td width="9%">${i.materialNo}</td>
                                    <td width="9%">${i.barcode}</td>
                                    <td width="6%">${i.materialName}</td>
                                    <td width="9%">${i.counts}</td>
                                    <td width="9%">${i.unitName}</td>
                                    <td width="9%">${i.batchNumber}</td>
                                    <td width="9%">${i.ecOrderNumber}</td>
                                    <td width="9%">${i.fromBillsNo}</td>
                                    <td width="6%"></td>
                                </tr>
                            </#if>
                        </#list>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="head_section" style = "margin-top:20px;">
            <div class="partner_text_div_3 common_text_div">
            </div>
            <div class="partner_text_div_3 common_text_div">
            </div>
            <div class="partner_text_div_3 common_text_div">
                <div class="common_text_title"><span>拣货人签字</span></div>
                <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
            </div>
            <div class="partner_text_div_3 common_text_div">
            </div>
            <div class="partner_text_div_3 common_text_div">
                <div class="common_text_title"><span>接单单时间</span></div>
                <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
            </div>
            <div class="partner_text_div_3 common_text_div">
                <div class="common_text_title"><span>完成时间</span></div>
                <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
            </div>
            <div class="partner_text_div_3 common_text_div">
            </div>
            <div class="partner_text_div_3 common_text_div">
                <div class="common_text_title"><span>总体用时</span></div>
                <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
            </div>
        </div>
    </div>
    
    <input type = "hidden" id = "ready_shipments_size" value = "${readyShipmentsResponses?size}">
    <#list readyShipmentsResponses as readyShipments> 
        <div id ="ready_shipments${readyShipments_index}" style = "display:none;width:100%;">
            <div class="head_section">
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>单据编号</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="orderNumber_${readyShipments_index}">${readyShipments.orderNumber}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>物流运单号</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.trackingNo}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>海关通关号</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.customsCode}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>原始订单号</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.fromBillsNo}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>单据日期</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.billsDate}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>出库仓库</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.storageName}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>客户</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.shopName}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>发货日期</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.shipmentsDate}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>收货人姓名</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consigneeName}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>收货人电话</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consignessPhone}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>收货人地址</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consigneeAddress}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title"><span>邮编</span></div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.consignessPostcode}</span></div>
                </div>
                <div class="partner_text_div_3 common_text_div">
                    <div class="common_text_title">备注</div>
                    <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${readyShipments.summary}</span></div>
                </div>
            </div>
            <div class="head_section">
                <table style="width:100%;">
                    <thead>
                        <tr>
                            <td>商品货号</td>
                            <td>国际编码</td>
                            <td>商品名称</td>
                            <td>批次号</td>
                            <td>计量单位</td>
                            <td>数量</td>
                            <td>单价</td>
                            <td>折扣（%）</td>
                            <td>金额</td>
                            <td>税额</td>
                            <td>总计</td>
                        </tr>
                    </thead>
                    <tbody>
                        <#list readyShipments.items as i>
                            <tr>
                                <td>${i.materialNo}</td>
                                <td>${i.barcode}</td>
                                <td>${i.materialName}</td>
                                <td>${i.batchNumber}</td>
                                <td>${i.unitName}</td>
                                <td>${i.counts}</td>
                                <td>${i.sellPrice}</td>
                                <td>${i.discount}</td>
                                <td>${i.afterDiscount}</td>
                                <td>${i.tax}</td>
                                <td>${i.total}</td>
                            </tr>
                        </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </#list> 
    
    <div class="order_btns_l common_text_div">
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>