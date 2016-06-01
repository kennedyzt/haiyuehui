<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:65%;">
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单位名称</span></div>
            <div class="common_text_val"><input id="unitName" class="input_text_common my_radius" data-click-tip="数据标识，不可重复"></div>
        </div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">单位编号</span></div>
            <div class="common_text_val"><input id="unitNo" class="input_text_common my_radius" data-click-tip="数据标识，不可重复"></div>
        </div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span>单位描述</span></div>
            <div class="common_text_val"><input id="description" class="input_text_common my_radius"></div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="material_unit_smt_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>