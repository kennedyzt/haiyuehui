<@insert template="layout/layer">
    <div class="list_page">
        <div class="table_title">库区列表</div>
        <table>
            <thead>
                <tr>
                    <td width="10%"><a class="td_head_a" onclick="deleteStorageArea()" data-hover-tip="删除"><img src="<@static value='/icons/delete.png' />"></a></td>
                    <td width="30%">库区编号</td>
                    <td width="30%">库区名称</td>
                    <td width="30%">描述</td>
                </tr>
            </thead>
        </table>
        <div class="data_grid" style="height:80%;overflow-y:auto">
            <table>
                <tbody>
                    <#list storageArea as s>
                        <tr>
                            <td width="10%">
                                <input id="${s.id}" type="checkbox" name="check_box">
                                <a class="td_inner_a" href="<@url value='/storagearea/edit?id=${s.id}' />" data-hover-tip="修改"><img src="<@static value='/icons/edit.png' />"></a>
                            </td>
                            <td width="30%">${s.areaNo!''}</td>
                            <td width="30%">${s.areaName!''}</td>
                            <td width="30%">${s.remark!''}</td>
                        </tr>
                    </#list>
                </tbody>
            </table>
        </div>
    </div>
</@insert>