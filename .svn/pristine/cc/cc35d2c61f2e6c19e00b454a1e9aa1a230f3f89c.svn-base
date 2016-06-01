<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_top_">
        <#-- <div class="rect_block">
            <div class="rect_top"><a href="<@url value='/purchaseorderreceipt/copyto?orderNumber=${ purchaseReceipt.orderNumber }' />"><img src="<@static value='/icons/copy_to.png' />"></a></div>
            <div class="rect_bottom">复制到</div>
        </div> -->
    </div>
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.orderNumber }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据日期</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.billDate }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>入库仓库</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.inBoundStorageName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.summary }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商编码</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.supplierCode }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.supplierName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.contactsFirstName }${ purchaseReceipt.contactsLastName }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.mobile }</span></div>
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
                <tbody  id="receipt_items">
                    <#list purchaseReceipt.items as item>
                    <tr>
                        <td class="detail_order_item_index_tbody_td">${item_index+1}</td>
                        <td>${item.materialNo}</td>
                        <td>${item.materialName}</td>
                        <td>${item.specificationsModel}</td>
                        <td>${item.brand}</td>
                        <td>${item.season}</td>
                        <td>${item.barcode}</td>
                        <td>${item.batchNumber}</td>
                        <td>${item.productionDate}</td>
                        <td>${item.unitName}</td>
                        <td>${item.counts}</td>
                        <td>${item.purchasePrice}</td>
                        <td>${item.discountRate}</td>
                        <td>${item.afterDiscount}</td>
                        <td>${item.taxRate}</td>
                        <td>${item.tax}</td>
                        <td>${item.amount}</td>
                        <#if item.isGift == true>
                            <td><input type="checkbox" disabled="disabled" checked="true"></td>
                        <#else>
                            <td><input type="checkbox" disabled="disabled"></td>
                        </#if>
                        <td>${item.remark}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.totalAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.giftAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠金额</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.discountAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付总金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.payAmount }</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="">${ purchaseReceipt.ownerName }</span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <#-- <button id="" class="btn_common my_radius">打印预览</button> -->
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>