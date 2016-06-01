<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="list_page">
        <div class="table_title">计量单位列表</div>
        <table>
            <thead>
                <tr>
                    <td width="10%"><a class="td_head_a" onclick="deleteMaterialUnit()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                    <td width="30%">单位编号</td>
                    <td width="30%">单位名称</td>
                    <td width="30%">单位描述</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid">
            <table>
                <tbody>
                   <#list pageModel.records as m>
                    <tr>
                        <td width="10%">
                            <input id="${m.id}" type="checkbox" name="material_unit_check_box">
                            <a class="td_inner_a" href="<@url value='/materialunit/edit?unitId=${m.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                        </td>
                        <td width="30%">${m.unitNo}</td>
                        <td width="30%">${m.unitName}</td>
                        <td width="30%">${m.description}</td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <#if pageModel.totalRecords??>
            <@pageByParam url="/materialunit/getlist" pageNo="${pageModel.pageNo}"  pageSize="${pageModel.pageSize}" totalRecords="${pageModel.totalRecords}"/>
        </#if>
    </div>
</@insert>