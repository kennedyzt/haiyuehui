<#setting classic_compatible=true>
<@insert template="layout/layer">
<div class="list_page">
    <div class="search_section">
        <form action="<@url value='/menunode/openwin'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入编码、名称关键字" placeholder="编码、名称"></div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
    </div>
    <table>
        <thead>
            <tr>
                <td width="10%"></td>
                <td width="10%">编号</td>
                <td width="10%">菜单名称</td>
                <td width="15%">菜单链接</td>
                <td width="15%">描述</td>
                <td width="10%">上级菜单</td>
                <td width="10%">菜单等级</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid_forwin">
        <table>
            <tbody>
                <#list pageModel.records as t>
                    <tr>
                        <td width="10%">
                            <input id="${t.id}" data-menuroot="${t.menuRoot}" data-menuicon="${t.icon}" type="radio" name="check_box">
                        </td>
                        <td id="menuId_${t.id}" width="10%">${t.id}</td>
                        <td id="menuName_${t.id}" width="10%">${t.name}</td>
                        <td width="15%">${t.nodeUrl}</td>
                        <td width="15%">${t.description}</td>
                        <td width="10%">${t.parentId}</td>
                        <td width="10%">${t.level}</td>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/menunode/openwin" pageNo="${pageModel.pageNo}" pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
    <div class="order_btns_l common_text_div">
        <button onclick="chooseParMenuWin();" class="btn_common my_radius">确定</button>
    </div>
</div>
</@insert>