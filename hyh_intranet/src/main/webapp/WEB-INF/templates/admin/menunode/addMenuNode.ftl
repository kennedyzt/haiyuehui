<@insert template="login/right">
    <div class="add_page" style="height:60%;">
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单名称</div>
            <div class="common_text_val"><input type="text" id="name" class="input_text_common my_radius"/></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单URL</div>
            <div class="common_text_val"><input type="text" id="nodeUrl" class="input_text_common my_radius"/></div>
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
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                </select>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">上级菜单</div>
            <div class="common_text_val">
                <input  type="text" id="parentMenuName" class="input_text_common my_radius" onClick="openWinGetParentMenu()"/>
                <input  type="hidden" id="parentMenuId" class="input_text_common my_radius"/>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单图标</div>
            <div class="common_text_val">
                <input  type="text" id="icon" class="input_text_common my_radius"/>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">URL(新增)</div>
            <div class="common_text_val">
                <input type="text" id="menuRoot" class="input_text_common my_radius"/>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">所属账套</div>
            <div class="common_text_val">
                <select id="userAccount" class="my_radius">
                    <option value="erp" selected="selected">ERP</option>
                    <option value="wms">WMS</option>
                </select>
            </div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">菜单描述</div>
            <div class="common_text_val"><textarea id="description" class="textarea_common my_radius"></textarea></div>
        </div>
    </div>
    <div class=" user_btns_l common_text_div">
        <button id="admin_menunode_add" class="btn_common my_radius btn_submit">保存</button>
        <button id="cancel_btn" class="btn_common my_radius">取消</button>
    </div>
</@insert>