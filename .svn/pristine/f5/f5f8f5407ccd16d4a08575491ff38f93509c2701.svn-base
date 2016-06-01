<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <div class="head_top_">
    </div>
    <div class="search_section">
        <form action="<@url value='/group/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入名称关键字" placeholder="名称"></div>
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
                <td width="15%">账套</td>
                <td width="30%">描述</td>
                <td width="15%">菜单权限</td>
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
                            <a class="td_inner_a" href="<@url value='/group/edit?id=${t.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="15%">${t.id}</td>
                        <td width="15%">${t.groupName}</td>
                        <td width="15%">${t.userAccount}</td>
                        <td width="30%">${t.description}</td>
                        <td width="15%">
                            <a onclick="editWinPermission(${t.id},'${t.userAccount}')" >修改</a>
                        </td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/group/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</div>
    <script type="text/javascript">
        function openWinPermission(id, account){
            var url = 'group/viewPermissions?id='+id+'&useraccount='+account;
            location.href = createUrl(url);
        }
        function editWinPermission(id, account){
            var url = 'group/editPermissions?id='+id+'&useraccount='+account;
            location.href = createUrl(url);
        }
    </script>
</@insert>