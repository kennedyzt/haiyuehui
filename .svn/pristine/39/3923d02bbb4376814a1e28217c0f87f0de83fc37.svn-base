<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:65%;">
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">仓库编码</span></div>
            <div class="common_text_val"><span id="storageNo" class="readonly_text_common">${s.storageNo}</span><input id="id" type="hidden" value="${s.id}"></div>
        </div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">仓库名称</span></div>
            <div class="common_text_val"><input id="storageName" class="input_text_common my_radius" value="${s.storageName}"> <input name="isEnableLocation" id="isEnableLocation" type="hidden" value="1"/></div>
        </div>
        <!--<div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">是否启用库位</span></div>
            <div class="common_text_val"> <input name="isEnableLocation" id="isEnableLocation" type="checkbox" <#if s.isEnableLocation==true>checked="checked"</#if>/></div>
        </div>-->
        <div class="storage_text_div common_text_div">
            <div class="common_text_title">备注</div>
            <div class="common_text_val">
                <textarea id="description" class="textarea_common my_radius">${s.description}</textarea>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="storage_smt_edit" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>