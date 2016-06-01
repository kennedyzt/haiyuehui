<#setting classic_compatible=true>
<@insert template="login/right">
    <#if !response?? >
    <div class="head_top_">
        <div class="rect_block">
            <div class="rect_top"><a onclick="getOrderCopyFrom('purchaseorder')"><img src="<@static value='/icons/copy_from.png' />"></a></div>
            <div class="rect_bottom">复制从</div>
        </div>
    </div>
    </#if>
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据编号</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="orderNumber">${purchaseOrderNumber}</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" <#if purchaseApplyOrder?exists>value = "${ purchaseApplyOrder.billDate }"</#if> data-type="date" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>收货日期</span></div>
            <div class="common_text_val"><input id="po_receiptDate" <#if purchaseApplyOrder?exists>value = "${ purchaseApplyOrder.receiptDate }"</#if> data-type="date" class="input_text_common my_radius grid_page_text"></div>
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
        <span style="display:none" id="id" ></span>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">供应商编码</span></div>
            <div class="common_text_val"><input id="partnerCode" class="input_text_common my_radius grid_page_text" onclick="openWinGetPartner(2);"><span id="id" style="display:none;"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>供应商名称</span></div>
            <div class="common_text_val"><span id="partnerName" class="readonly_text_common grid_page_text"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>联系人</span></div>
            <div class="common_text_val"><span id="contactsFirstName" class="readonly_text_common grid_page_text"></span><span id="contactsLastName" class="readonly_text_common grid_page_text"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>移动电话</span></div>
            <div class="common_text_val"><span id="contactsMobilephone" class="readonly_text_common grid_page_text"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>币种</span></div>
            <div class="common_text_val">
                <select id="currency" class="my_radius grid_page_text">
                    <option value="人民币">人民币 RMB</option>
                    <option value="美元">美元 USD</option>
                    <option value="日元">日元 JPY</option>
                    <option value="欧元">欧元 EUR</option>
                    <option value="英镑">英镑 GBP</option>
                    <option value="德国马克">德国马克 DEM</option>
                    <option value="瑞士法郎">瑞士法郎 CHF</option>
                    <option value="法国法郎">法国法郎 FRF</option>
                    <option value="加拿大元">加拿大元 CAD</option>
                    <option value="澳大利亚元">澳大利亚元 AUD</option>
                    <option value="港币">港币 HKD</option>
                    <option value="意大利里拉">意大利里拉 ITL</option>
                    <option value="荷兰盾">荷兰盾 NLG</option>
                    <option value="葡萄牙埃斯库多">葡萄牙埃斯库多 PTE</option>
                    <option value="西班牙比塞塔">西班牙比塞塔 ESP</option>
                    <option value="俄罗斯卢布">俄罗斯卢布 SUR</option>
                    <option value="新加坡元">新加坡元 SGD</option>
                    <option value="韩国元">韩国元 KRW</option>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>汇率</span></div>
            <div class="common_text_val"><input id="exchangeRate" class="input_text_common my_radius grid_page_text" value="1"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="po_summary" <#if purchaseApplyOrder?exists>value="${purchaseApplyOrder.summary }"</#if> class="input_text_common my_radius grid_page_text"></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table id="po_table" style="width: 2550px;">
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
                <tbody id="dataSource">
                    <#if purchaseApplyOrder?exists>
                        <#assign step = "${purchaseApplyOrder.items?size}">
                        <#list purchaseApplyOrder.items as i>
                            <tr id= "${i_index+1}">
                                <td width="20px">${i_index+1}</td>
                                <td><img onclick="addPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                                <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" value = "${i.materialNo}" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" value = "${i.materialId}" name = "materialId"><div class="more" onclick="openWinGetMaterials('choosePurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                                <td><span name="materialName" class="grid_text grid_readonly_text my_radius">${i.materialName}</span></td>
                                <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius">${i.specificationsModel}</span></td>
                                <td><span name="brand" class="grid_text grid_readonly_text my_radius">${i.brand}</span></td>
                                <td><span name="season" class="grid_text grid_readonly_text my_radius">${i.season}</span></td>
                                <td><span name="barcode" class="grid_text grid_readonly_text my_radius">${i.barcode}</span></td>
                                <td><span name="unitName" class="grid_text grid_readonly_text my_radius">${i.unitName}</span><input type = "hidden" value = "${i.minOrder}" name = "minOrder"></input></td>
                                <td><input name="currencyPrice" onkeyup="getRMBPriceAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="counts" value = "${i.counts}" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="purchasePrice" value = "0.00" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="discount" value = "100.00" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="afterDiscount" value = "0.00" onblur = "getDiscount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><input name="taxRate" value = "0.00" onkeyup="getTotalAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
                                <td><span name="tax" class="grid_text grid_readonly_text my_radius">0.00</span></td>
                                <td><span name="total" class="grid_text grid_readonly_text my_radius">0.00</span></td>
                                <td><input name="isGift" onclick="reset(this);" type="checkbox"></td>
                                <td><input name="remark" value = "${i.remark}" class="input_text_common my_radius grid_text"></td>
                            </tr>
                        </#list>
                    <#else>
                        <#assign step = "0">
                    </#if>
                    <#list 0..(9-step?number) as i>
                        <tr id= "${step?number+i+1}">
                            <td width="20px">${step?number+i+1}</td>
                            <td><img onclick="addPurchaseOrderRow(this);" src="<@static value='/icons/tree_hide.png' />"><img onclick="removeGridRow(this)" src=<@static value="/icons/tree_show.png"/>"></td>
                            <td><div class="common_text_val common_text_val_table"><input data-type="purchaseMaterial" name="materialNo" class="input_choose_text input_choose_short_text my_radius grid_text"><input type = "hidden" name = "materialId"><div class="more" onclick="openWinGetMaterials('choosePurchaseOrderMaterial',true,false,false,this);"></div></div></td>
                            <td><span name="materialName" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="specificationsModel" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="brand" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="season" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="barcode" class="grid_text grid_readonly_text my_radius"></span></td>
                            <td><span name="unitName" class="grid_text grid_readonly_text my_radius"></span><input type = "hidden" name = "minOrder"></input></td>
                            <td><input name="currencyPrice" onkeyup="getRMBPriceAmount(this);" data-type="number" class="input_text_common my_radius grid_text"></td>
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
            <div class="common_text_val"><span id="totalPrice" class="readonly_text_common grid_page_text">0.000</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span id="giftPrice" class="readonly_text_common grid_page_text">0.000</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">优惠金额</div>
            <div class="common_text_val"><input id="favorablePrice" value="0.000" onkeyup="getTotalAmount(this.id);" data-type="number" class="input_text_common my_radius grid_page_text"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>应付金额</span></div>
            <div class="common_text_val"><span id="payPrice" class="readonly_text_common grid_page_text">0.000</span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" >${loginUserName}</span><input type="hidden" id = "loginId" value = "${loginId}"></div>
        </div>
    </div>
    <input id = "fromBillsNo" <#if purchaseApplyOrder?exists>value = "${purchaseApplyOrder.orderNumber}"</#if> type = "hidden">
    <div class="order_btns_l common_text_div">
        <button id="po_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="po_draft_smt" class="btn_common my_radius">存为草稿</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>