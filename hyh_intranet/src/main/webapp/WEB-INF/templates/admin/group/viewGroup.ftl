<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:50%;">
        <input type="hidden" id="id" value="${group.id}"/>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">组名</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="groupName" >${group.groupName}</span></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">描述</div>
            <div class="common_text_val"> <span class="readonly_text_common grid_page_text" id="description" >${group.description}</span></div>
        </div>
    </div>
</@insert>