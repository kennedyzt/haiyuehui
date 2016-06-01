<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:60%;">
        <input type="hidden" id="id"  value="${menunode.id}"/>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单名称</div>
            <div class="common_text_val"><input type="text" id="name" class="input_text_common my_radius" value="${menunode.name}"/></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单链接</div>
            <div class="common_text_val"><input type="text" id="nodeUrl" class="input_text_common my_radius" value="${menunode.nodeUrl}"/></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单排序</div>
            <div class="common_text_val">
                <select id="sequence" class="my_radius">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                </select>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">上级菜单</div>
            <div class="common_text_val">
                <input  type="text" id="parentMenuName" class="input_text_common my_radius" value="${menunode.parentName}" onClick="openWinGetParentMenu()"/>
                <input  type="hidden" id="parentMenuId" value="${menunode.parentId}"/>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单Icon</div>
            <div class="common_text_val">
                <input  type="text" id="icon" class="input_text_common my_radius" value="${menunode.icon}"/>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">添加Url</div>
            <div class="common_text_val">
                <input  type="text" id="menuRoot" class="input_text_common my_radius" value="${menunode.menuRoot}"/>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">所属账套</div>
            <div class="common_text_val">
                <select id="userAccount" class="my_radius">
                    <option value="erp">ERP</option>
                    <option value="wms">WMS</option>
                </select>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单描述</div>
            <div class="common_text_val"><textarea id="description" class="textarea_common my_radius">${menunode.description}</textarea></div>
        </div>
    </div>
    <div class=" user_btns_l common_text_div">
            <button id="admin_menunode_edit" class="btn_common my_radius btn_submit">保存</button>
            <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
    <script>
        var sequence = "${menunode.sequence}";
        $("#sequence").val(sequence);
        var userAccount = "${menunode.userAccount}";
        $("#userAccount").val(userAccount);
    </script>
</@insert>
