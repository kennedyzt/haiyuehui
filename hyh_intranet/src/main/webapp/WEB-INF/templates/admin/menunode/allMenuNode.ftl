<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <div class="head_top_">
    </div>
    <div class="search_section">
        <form action="<@url value='/menunode/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入编码、名称关键字" placeholder="编码、名称"></div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
        <div style="clear:both"></div>
    </div>
    <div class="table_title">列表</div>
    <table>
        <thead>
            <tr>
                <td width="10%"><a class="td_head_a" onclick="deleteMenuNode()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="5%">编号</td>
                <td width="10%">菜单名称</td>
                <td width="10%">所属账套</td>
                <td width="15%">菜单链接</td>
                <td width="15%">描述</td>
                <td width="10%">上级菜单</td>
                <td width="10%">菜单等级</td>
                <td width="10%">菜单顺序</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#list pageModel.records as t>
                    <tr>
                        <td width="10%">
                            <input id="${t.id}" type="radio" name="check_box">
                            <a class="td_inner_a" href="<@url value='/menunode/edit?id=${t.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="5%">${t.id}</td>
                        <td width="10%">${t.name}</td>
                        <td width="10%">${t.userAccount}</td>
                        <td width="15%">${t.nodeUrl}</td>
                        <td width="15%">${t.description}</td>
                        <td width="10%">${t.parentName}</td>
                        <td width="10%">${t.level}</td>
                        <td width="10%">${t.sequence}</td>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/menunode/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</div>
</@insert>