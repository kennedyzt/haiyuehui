<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <div class="head_top_">
    </div>
    <div class="search_section" style="overflow:hidden;">
        <form action="<@url value='/usr/getlist'/>" method="post">
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">关键字</div>
                <div class="common_text_val"><input name="keywords" class="input_text_common my_radius" data-click-tip="输入账号、昵称关键字" placeholder="账号、昵称"></div>
            </div>
            <div class="partner_text_div common_text_div">
                <div class="common_text_title">状态</div>
                <div class="common_text_val">
                    <select class="my_radius" id="isDeleted" name="isDeleted">
                        <option value="0">可用</option>
                        <option value="1">不可用</option>
                    </select>
                </div>
            </div>
            <div class="search_btn common_text_div">
                <button type="submit" class="btn_common my_radius btn_search">查询</button>
            </div>
        </form>
    </div>
    <div class="table_title">用户列表</div>
    <table>
        <thead>
            <tr>
                <td width="10%"><a class="td_head_a" onclick="deleteUser()" data-hover-tip="置为不可用"><img src="<@static value='/icons/delete.png' />"></a></td>
                <td width="15%">帐号</td>
                <td width="15%">昵称</td>
                <td width="15%">邮箱</td>
                <td width="15%">类型</td>
                <td width="10%">用户账套</td>
                <td width="10%">关联业务伙伴</td>
                <td width="10%">状态</td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#list pageModel.records as t>
                    <tr>
                        <td width="10%">
                            <#if t.isDeleted == 0 >
                            <input id="${t.id}" type="radio" name="check_box">
                            <a class="td_inner_a" href="<@url value='/usr/edit?id=${t.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                            </#if>
                        </td>
                        <#-- <td width="10%"><a href="<@url value='/usr/get?id=${t.id}' />" data-hover-tip="查看详情">${t.id}</a></td> -->
                        <td width="15%">${t.userName}</td>
                        <td width="15%">${t.nickName}</td>
                        <td width="15%">${t.email}</td>
                        <td width="15%"><#if t.userType == 'Employee' >员工<#else>业务伙伴</#if></td>
                        <td width="10%">${t.userAccount}</td>
                        <td width="10%">${t.partnerName}</td>
                        <td width="10%"><#if t.isDeleted == 1 >不可用<#else>可用</#if></td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <#if pageModel.totalRecords??>
        <@pageByParam url="/usr/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</div>
</@insert>