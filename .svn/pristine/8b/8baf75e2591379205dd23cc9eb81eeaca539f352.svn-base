<@insert template="login/right">
    <div class="add_page">
        <div class="user_text_div common_text_div">
            <div class="common_text_title">用户名</div>
            <div class="common_text_val"><input id="userName" class="input_text_common my_radius"></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">昵称</div>
            <div class="common_text_val"><input id="nickName" class="input_text_common my_radius"></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">帐号类型</div>
            <div class="common_text_val">
                <select id="userType" class="my_radius" onChange="userTypeChange();">
                    <option value="Employee" selected="selected">员工</option>
                    <option value="BusiPartner">业务伙伴</option>
                </select>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">（供应商）名称</div>
            <div class="common_text_val">
                <input id="objectName" class="input_text_common my_radius" onclick="openWinGetPartnerName(2);" />
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">（供应商）编号</div>
            <div class="common_text_val">
                <input id="objectId" class="input_text_common my_radius"/>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">邮箱</div>
            <div class="common_text_val"><input id="email" class="input_text_common my_radius"></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">密码</div>
            <div class="common_text_val"><input type="password" id="password" class="input_text_common my_radius"></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title"> 用户账套</div>
            <div class="common_text_val">
                <input type="checkbox" name="userAccount" id="account_erp" value="erp" checked="checked" onclick="chooseUserAcc(this);"/>
                <label for="account_erp">ERP</label>
                <input type="checkbox" name="userAccount" id="account_wms" value="wms" onclick="chooseUserAcc(this);"/>
                <label for="account_wms">WMS</label>
            </div>
        </div>
        <div class="user_text_div common_text_div" style="height : auto;">
            <div class="common_text_title">权限组</div>
            <div class="common_text_val">
                <#list groups as group>
                <input type="checkbox" name="groups" class="group_sign_${group.userAccount}" id="group_${group_index+1}" value="${group.id}" />
                <label class="group_sign_${group.userAccount}" for="group_${group_index+1}">${group.groupName}</label>
                </#list>
            </div>
        </div>
    </div>
    <div class=" user_btns_l common_text_div">
        <button id="admin_user_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>