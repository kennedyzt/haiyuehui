<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:65%;">
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">仓库编码</span></div>
            <div class="common_text_val"><input id="storageNo" class="input_text_common my_radius" data-click-tip="数据唯一标识，不可重复"></div>
        </div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">仓库名称</span></div>
            <div class="common_text_val"><input id="storageName" class="input_text_common my_radius"><input name="isEnableLocation" id="isEnableLocation" type="hidden" value="1"/></div>
        </div>
         <!--<div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">是否启用库位</span></div>
            <div class="common_text_val"> </div>
        </div>-->
        <div class="storage_text_div common_text_div">
            <div class="common_text_title">备注</div>
            <div class="common_text_val">
                <textarea id="description" class="textarea_common my_radius"></textarea>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="storage_smt_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>