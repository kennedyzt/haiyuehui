<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <div class="head_top_">
    </div>
    <div class="search_section">
        <form action="<@url value='/usr/getlist'/>" method="post">
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
                <td width="10%"><a class="td_head_a" onclick="deleteGroup()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="15%">编号</td>
                <td width="15%">名称</td>
                <td width="30%">描述</td>
                <td width="15%">菜单权限</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#list permissions as t>
                    <tr>
                        <td width="10%">
                            <input id="${t.nodeId}" type="radio" name="check_box">
                            <a class="td_inner_a" href="<@url value='/group/edit?id=${t.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="15%"><a href="<@url value='/group/get?id=${t.id}' />" data-hover-tip="查看详情">${t.id}</a></td>
                        <td width="15%">${t.nodeName}</td>
                        <td width="30%">${t.isAdd}</td>
                        <td width="15%">
                            <a onclick="openWinPermission(${t.id})" >查看</a>
                            <a onclick="editWinPermission(${t.id})" >修改</a>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
</div>
</@insert>