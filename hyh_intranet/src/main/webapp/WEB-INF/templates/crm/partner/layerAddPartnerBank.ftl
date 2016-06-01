<#setting classic_compatible=true>
<@insert template="layout/layer">
    <div class="add_page">
        <div class="bank_text_div common_text_div"></div>
        <div class="bank_text_div common_text_div"></div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">银行名称</span></div>
            <div class="common_text_val"><input id="bankName" value="" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">银行国际代码</span></div>
            <div class="common_text_val"><input id="swiftCode" value="" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">开户行</span></div>
            <div class="common_text_val"><input id="bankAddress" value="" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title">户名</div>
            <div class="common_text_val"><input id="accountName" value="" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title">银行账号</div>
            <div class="common_text_val"><input id="accountNo" value="" class="input_text_common my_radius"></div>
        </div>
        <div class="bank_text_div common_text_div">
            <div class="common_text_title">备注</div>
            <div class="common_text_val"><input id="remark" value="" class="input_text_common my_radius"></div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <input type="hidden" id="partnerId" value="${partnerId}">
        <button id="bank_smt_add" class="btn_common my_radius btn_submit">保存</button>
    </div>
</@insert>