<@insert template="login/right">
    <div class="add_page" style="height:50%;">
        <div class="period_text_div common_text_div"></div>
        <div class="period_text_div common_text_div"></div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">期间代码</span></div>
            <div class="common_text_val"><span class="readonly_text_common" id="postPeriodNo">${p.postPeriodNo}</span><input type="hidden" id="id" value="${p.id}"></div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">期间名称</span></div>
            <div class="common_text_val"><span class="readonly_text_common" id="postPeriodName">${p.postPeriodName}</span></div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">过账期间(从)</span></div>
            <div class="common_text_val"><span class="readonly_text_common" id="postPeriodStartTime">${p.postPeriodStartTime}</span></div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">过账期间(止)</span></div>
            <div class="common_text_val">
                <#if endTime=="last">
                    <input id="postPeriodEndTime" data-type="date" class="input_text_common my_radius" value="${p.postPeriodEndTime}">
                <#else>
                    <span class="readonly_text_common" id="postPeriodEndTime">${p.postPeriodEndTime}</span>
                </#if>
            </div>
        </div>
        <div class="period_text_div common_text_div">
            <div class="common_text_title"><span data-required="true">期间状态</span></div>
            <div class="common_text_val">
                <select id="postPeriodFlag" class="my_radius">
                    <option value="1" <#if p.postPeriodFlag==true>selected</#if>>激活</option>
                    <option value="0" <#if p.postPeriodFlag==false>selected</#if>>锁定</option>
                </select>
            </div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="postperiod_smt_edit" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>