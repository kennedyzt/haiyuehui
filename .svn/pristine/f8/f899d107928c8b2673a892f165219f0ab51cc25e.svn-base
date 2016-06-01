<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>单据编号</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="shipmentsNumber"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" data-type="date" class="input_text_common my_radius grid_page_text" id="billsDate"></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">出库仓库</span></div>
            <div class="common_text_val">
                <select id="inboundStorageId" class="my_radius grid_page_text">
                    <option value=""></option>
                    <#list storages as s>
                        <option value="${s.id}">${s.storageName}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title">单据摘要</div>
            <div class="common_text_val"><input id="summary" class="input_text_common my_radius grid_page_text"></div>
        </div>
    </div>
    <div class="grid_section">
        <div class="data_add_grid">
            <table style="width: 1620px;">
                <thead>
                    <tr>
                        <td width="20px"></td>
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
                        <td width="150px">总计(元)</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="dataSource">
                    <#list inventoryShipments.shipmentsItemList as p>
                    <tr id="${p_index+1}" class="grid_row">
                        <#if p_index==0>
                             <td><img id="inventory_shipment_add_row" src="<@static value='/icons/tree_hide.png' />"></td>
                        <#else>
                             <td><img onclick="removeGridRowData(this);" src="<@static value='/icons/tree_show.png' />"></td>
                        </#if>
                        <td><input value="${p.materialNo!}" class="input_text_common my_radius grid_text" onkeyup="getSaleOrderSurProList(this)"><span id="id${p_index+1}" style="display:none">${p.materialId!}</span></td>
                        <td><span id="materialName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.materialName!}</span></td>
                        <td><span id="specificationsModel${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.specificationsModel!}</span></td>
                        <td><span id="brand${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.brand!}</span></td>
                        <td><span id="season${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.season!}</span></td>
                        <td><span id="barcode${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.barcode!}</span></td>
                        <td><input id="batchNumber${p_index+1}" class="input_text_common my_radius grid_text" value="${p.batchNumber!}"></td>
                        <td><input id="productionDate${p_index+1}" data-type="date" class="input_text_common my_radius grid_text" value="${p.productionDate!}"></td>
                        <td><span id="unitName${p_index+1}" class="grid_text grid_readonly_text my_radius">${p.unitName!}</span></td>
                        <td><input id="counts${p_index+1}" data-type="number" class="input_text_common my_radius grid_text" value="${p.counts!}" onkeyup="getInventoryTotal(this);"></td>
                        <td><input id="price${p_index+1}" data-type="number" class="input_text_common my_radius grid_text" value="${p.sellPrice!}" onkeyup="getInventoryTotal(this);"></td>
                        <td><input id="totalRow${p_index+1}" class="input_text_common my_radius grid_text" data-type="number" value="${p.total!}"></td>
                        <td><input id="isGift${p_index+1}" <#if p.isGift==true>checked='"checked"</#if> type="checkbox"></td>
                        <td><input id="remark${p_index+1}" class="input_text_common my_radius grid_text" value="${p.remark!}"></td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id=""></span></div>
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
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="owner"></span><input id="loginId" value="" type="hidden"></span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="storage_delivery_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>