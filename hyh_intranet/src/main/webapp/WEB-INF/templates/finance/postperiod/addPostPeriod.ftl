<@insert template="login/right">
    <div class="add_page" style="height:50%;">
        <div class="period_text_div common_text_div"></div>
        <div class="period_text_div common_text_div"></div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">期间代码</span></div>
            <div class="common_text_val"><input id="postPeriodNo" class="input_text_common my_radius" data-click-tip="数据唯一标识，不可重复"></div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">期间名称</span></div>
            <div class="common_text_val"><input id="postPeriodName" class="input_text_common my_radius"></div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">过账期间(从)</span></div>
              <div class="common_text_val">
               <#if startTime=="0">
                    <input id="postPeriodStartTime" data-type="date" class="input_text_common my_radius">
                <#else>
                    <span class="readonly_text_common" id="postPeriodStartTime">${startTime}</span>
                </#if>
              </div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">过账期间(止)</span></div>
            <div class="common_text_val"><input id="postPeriodEndTime" data-type="date" class="input_text_common my_radius"></div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">期间状态</span></div>
            <div class="common_text_val">
                <select id="postPeriodFlag" class="my_radius">
                    <option value="1">激活</option>
                    <option value="0">锁定</option>
                </select>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="postperiod_smt_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>