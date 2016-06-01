<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:50%;">
        <input type="hidden" id="id" value="${group.id}"/>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">组名</div>
            <div class="common_text_val"><input id="groupName" class="input_text_common my_radius" value="${group.groupName}"/></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">描述</div>
            <div class="common_text_val"><textarea id="description" class="textarea_common my_radius">${group.description}</textarea></div>
        </div>
    </div>
    <div class="user_btns_l common_text_div">
        <button id="admin_group_edit" class="btn_common my_radius btn_submit">保存</button>
        <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</@insert>