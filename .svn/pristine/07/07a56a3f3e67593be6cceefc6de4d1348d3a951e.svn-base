<#setting classic_compatible=true>
<@insert template="login/right">
<div class="list_page">
    <div class="head_top_">
    </div>
    <div class="table_title">列表</div>
    <input type="hidden" id="groupId"value="${groupId}" />
    <table>
        <thead>
            <tr>
                <td width="5%"></td>
                <td width="10%">菜单编号</td>
                <td width="15%">菜单名称</td>
                <td width="30%">菜单Url</td>
                <td width="10%">新增</td>
                <td width="10%">删除</td>
                <td width="10%">修改</td>
                <td width="10%">打印</td>
                <td width="5%"></td>
            </tr>
        </thead>
    </table>
    <div class="data_grid">
        <table>
            <tbody>
                <#list permissions as t>
                    <tr>
                        <td width="5%"></td>
                        <td width="10%"  class="nodeId">${t.nodeId}</td>
                        <td width="15%">${t.nodeName}</td>
                        <td width="30%">${t.nodeUrl}</td>
                        <td width="10%">
                            <#if t.isAdd>
                                <input name="isAdd"  id="isAdd_${t.nodeId}" type="checkbox" checked="checked"/>
                            <#else>
                                <input name="isAdd" id="isAdd_${t.nodeId}" type="checkbox"/>
                            </#if>
                        </td>
                        <td width="10%">
                            <#if t.isDelete>
                                <input name="isDelete"  id="isDelete_${t.nodeId}" type="checkbox" checked="checked"/>
                            <#else>
                                <input name="isDelete" id="isDelete_${t.nodeId}" type="checkbox"/>
                            </#if>
                        </td>
                        <td width="10%">
                            <#if t.isEdit>
                                <input name="isEdit"  id="isEdit_${t.nodeId}" type="checkbox" checked="checked"/>
                            <#else>
                                <input name="isEdit" id="isEdit_${t.nodeId}" type="checkbox"/>
                            </#if>
                        </td>
                        <td width="10%">
                            <#if t.isPrint>
                                <input name="isPrint"  id="isPrint_${t.nodeId}" type="checkbox" checked="checked"/>
                            <#else>
                                <input name="isPrint" id="isPrint_${t.nodeId}" type="checkbox"/>
                            </#if>
                        </td>
                         <td width="5%"></td>
                    </tr>
                </#list>
            </tbody>
        </table>
    </div>
    <div class=" user_btns_l common_text_div">
            <button id="admin_group_permissions_save" class="btn_common my_radius btn_submit">保存</button>
            <button id="goback_btn" class="btn_common my_radius">返回</button>
    </div>
</div>
    <script type="text/javascript">
        $(function(){
            $("#admin_group_permissions_save").click(function() {
                var groupId = $("#groupId").val();
                var permissions = [];
                $("td.nodeId").each(function(){
                    var nodeId = $(this).html();
                    var permission = {};
                    permission.nodeId = nodeId;
                    permission.groupId = groupId;
                    permission.isAdd = $("#isAdd_"+nodeId).is(':checked');
                    permission.isEdit = $("#isEdit_"+nodeId).is(':checked');
                    permission.isDelete = $("#isDelete_"+nodeId).is(':checked');
                    permission.isPrint = $("#isPrint_"+nodeId).is(':checked');
                    permissions.push(permission);
                });
                var groupRequest = {};
                groupRequest.id =  groupId;
                groupRequest.permissions = permissions;
                console.log(groupRequest);

                siping.ajax({
                    method : "post",
                    url : 'group/editpermissions',
                    data : JSON.stringify(groupRequest),
                    dataType : "json",
                    contentType : "application/json",
                    success : function(data) {
                        console.log(data);
                        if (data.success) {
                            siping.alert(1, data.msg);
                        } else {
                            siping.alert(0, data.msg);
                        }
                    }
                });
            });
        });
    </script>
</@insert>