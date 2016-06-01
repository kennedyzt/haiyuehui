<@insert template="login/right">
    <div class="add_page" style="height:75%;">
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">商品类型名称</span></div>
            <div class="common_text_val"><input id="typeName" class="input_text_common my_radius" data-click-tip="数据唯一标识，不可重复"></div>
        </div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">所属商品类型</span></div>
            <div class="common_text_val">
                <input type="hidden" id="father_id">
                <input id="fatherType" data-type="materialType" name="materialType" readonly class="input_text_common my_radius">
                <div id="materialTypeTree" data-type="tree" class="tree_div tree_list_style" hidden></div>
            </div>
        </div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title">商品类型描述</div>
            <div class="common_text_val">
                <textarea id="description" class="textarea_common my_radius"></textarea>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="material_type_smt_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>