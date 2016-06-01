<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="data_grid">
        <table style="text-align:center">
            <thead>
                <tr>
                    <#if partnerType?number==2>
                        <td width="10%"><a class="td_head_a" onclick="deletePartnerGroup()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                        <td>供应商组名称</td>
                        <td>供应商组描述</td>
                    <#else>
                        <td width="10%"><a class="td_head_a" onclick="deletePartnerGroup()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                        <td>客户组名称</td>
                        <td>客户组描述</td>
                    </#if>
                </tr>
            </thead>
            <tbody>
                <#list pageModel.records as p>
                    <tr>
                        <td>
                            <input id="${p.id}" type="radio" name="check_box">
                            <a class="td_inner_a" href="<@url value='/partnergroup/edit?id=${p.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td>${ p.groupName }</td>
                        <td>${ p.description }</td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <input hidden="hidden" id=partnerType value="${partnerType}">
    </div>
    <#if pageModel??>
        <@pageByParam url="/partnergroup/getlist?partnerType=${partnerType}" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
    </#if>
</@insert>