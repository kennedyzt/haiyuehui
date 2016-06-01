<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="list_page">
        <div class="table_title">仓库列表</div>
        <table>
            <thead>
                <tr>
                    <td width="8%"><a class="td_head_a" onclick="deleteStorage()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                    <td width="18%">仓库编码</td>
                    <td width="18%">仓库名称</td>
                    <td width="18%">备注</td>
                    <td width="18%">库区</td>
                    <td width="20%">库位</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid">
            <table>
                <tbody>
                   <#list storages.records as s>
                    <tr>
                        <td width="8%">
                            <#if s.id != 0><input id="${s.id}" type="checkbox" name="check_box">
                            <a class="td_inner_a" href="<@url value='/storage/edit?id=${s.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a></#if>
                        </td>
                        <td width="18%">${s.storageNo}</td>
                        <td width="18%">${s.storageName}</td>
                        <td width="18%">${s.description}</td>
                        <td width="18%"><#if s.id != 0><#if s.isEnableLocation == true><a onclick="openWinAllStorageArea(${s.id})">查看</a><a onclick="openWinAddStorageArea(${s.id})" >添加</a> <#else>未启用库位</#if><#else>虚拟仓库不启用库位</#if></td>
                        <td width="20%"><#if s.id != 0><#if s.isEnableLocation == true><a onclick="openWinAllStorageLocation(${s.id})">查看</a><a onclick="openWinAddStorageLocation(${s.id})" >添加</a><#else>未启用库位</#if><#else>虚拟仓库不启用库位</#if></td>
                    </tr>
                    </#list>
                </tbody>
            </table>
        </div>
        <#if storages.totalRecords??>
            <@pageByParam url="/storage/getlist" pageNo="${storages.pageNo}"  pageSize="${storages.pageSize}" totalRecords="${storages.totalRecords}"/>
        </#if>
    </div>
</@insert>