<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:65%;">
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div">
            <span data-required="true">单据编号</span>
            <span id="checkNumber" class="readonly_text_common grid_page_text">${checkNumber}</span>
        </div>
        <div class="storage_text_div common_text_div">
            <span data-required="true">盘点仓库</span><input type="hidden" id="inventoryCheckId" value="0">
            <select id="selected_storage_for_inventory_check" class="my_radius" onchange="getStorageArea()">
                <option value=""></option>
                <#list storages as s>
                <option value="${s.id}">${s.storageName}</option>
                </#list>
            </select>
        </div>
         <!--<div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">是否启用库位</span></div>
            <div class="common_text_val"> </div>
        </div>-->
        <div class="storage_text_div common_text_div">
            <span>&nbsp;&nbsp;盘点库区</span>
            <select id="layerChooseStorageArea" class="my_radius" disabled="disabled">
                <option value=""></option>
            </select>
        </div>
        <div class="storage_text_div common_text_div">
            <span data-required="true">&nbsp;&nbsp;盘点人&nbsp;&nbsp;</span>
            <input id="nickName" style="width:28%" name="nickName" class="input_text_common my_radius grid_page_text" onclick="openWinGetUser();"><input id="userId" type = "hidden"><#-- 为了公用选择联系人的方法，这里的userId赋值是给的operatorId -->
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="pda_inventory_check_smt_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>