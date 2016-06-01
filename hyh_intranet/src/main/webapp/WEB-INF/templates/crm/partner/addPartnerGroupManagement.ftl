<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:65%;">
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div"></div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title"><span data-required="true"><#if partnerType?number==2 >供应商组名称<#else>客户组名称</#if></span></div>
            <div class="common_text_val"><input id="groupName" class="input_text_common my_radius" data-click-tip="数据唯一标识，不可重复"></div>
        </div>
        <div class="storage_text_div common_text_div">
            <div class="common_text_title">组描述</div>
            <div class="common_text_val">
                <textarea id="description" class="textarea_common my_radius"></textarea>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button class="btn_common my_radius btn_submit" onclick="addPartnerGroup('${partnerType!}')">添加</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>