<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="head_section">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单据日期</span></div>
            <div class="common_text_val"><input id="billsDate" data-type="date" class="input_text_common my_radius grid_page_text"></div>
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
    <div class="order_add_prowin">
        <input class="input_text_common my_radius">
        <button class="my_radius" onclick="openWinGetMaterial('chooseInventoryDeliveryMaterial',false,false,true);" type="button">添加</button>
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
                        <td width="150px">计量单位</td>
                        <td width="150px">批次号</td>
                        <td width="150px">生产日期</td>
                        <td width="150px">数量</td>
                        <td width="150px">单价(元)</td>
                        <td width="150px">总计(元)</td>
                        <td width="150px">是否赠品</td>
                        <td width="150px">备注</td>
                    </tr>
                </thead>
                <tbody id="dataSource">
                </tbody>
            </table>
        </div>
    </div>
    <div class="grid_bottom">
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>总计金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="totalPrice"></span></div>
        </div>
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>赠品金额</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="giftPrice"></span></div>
        </div>
     
        <div class="grid_page_text_div common_text_div">
            <div class="common_text_title"><span>所有人</span></div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="owner"></span><input id="loginId" value="${loginId!}" type="hidden"></span></div>
        </div>
    </div>
    <div class="order_btns_l common_text_div">
        <button id="storage_delivery_smt" class="btn_common my_radius btn_submit">保存</button>
        <button id="storage_delivery_draft_smt" class="btn_common my_radius">存为草稿</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
    <div id="hide_delivery_sale_order" style="display: none">
</@insert>