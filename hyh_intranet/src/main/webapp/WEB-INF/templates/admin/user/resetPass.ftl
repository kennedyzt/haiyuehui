<#setting classic_compatible=true>
<@insert template="layout/layer">
    <div class="add_page" style="margin: 10px 0 0 0;">
        <div class="partner_text_div common_text_div" style="width:99%;">
            <div class="common_text_title"><span data-required="true">原密码</span></div>
            <div class="common_text_val"><input id="original_pass" type="password" class="input_text_common my_radius"></div>
        </div>
        <div class="partner_text_div common_text_div" style="width:99%;">
            <div class="common_text_title"><span data-required="true">新密码</span></div>
            <div class="common_text_val"><input id="new_pass" type="password" class="input_text_common my_radius"></div>
        </div>
        <div class="partner_text_div common_text_div" style="width:99%;">
            <div class="common_text_title"><span data-required="true">确认新密码</span></div>
            <div class="common_text_val"><input id="confirm_pass" type="password" class="input_text_common my_radius"></div>
        </div>
    </div>
    <div class="user_btns_l common_text_div" style="height:60px;line-height: 60px;margin: 0;">
        <button id="psw_reset_save" onclick="resetPassSmt();" class="btn_common my_radius btn_submit">保存</button>
    </div>
</@insert>