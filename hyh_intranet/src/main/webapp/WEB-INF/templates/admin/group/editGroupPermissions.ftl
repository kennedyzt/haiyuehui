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
                <td width="25%">菜单名称</td>
                <td width="30%">菜单Url</td>
                <td width="25%">访问权限<span> <input id="selectAll" type="checkbox" onclick="selectAll();" /><span></td>
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
                        <td width="10%" class="nodeId">${t.nodeId}</td>
                        <td width="25%">${t.nodeName}</td>
                        <td width="30%">${t.nodeUrl}</td>
                        <td width="25%">
                            <#if t.isAdd>
                                <input name="isAdd"  id="isAdd_${t.nodeId}" type="checkbox" checked="checked"/>
                            <#else>
                                <input name="isAdd" id="isAdd_${t.nodeId}" type="checkbox"/>
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
        function selectAll(){  
            if ($("#selectAll").prop("checked")) {  
                $("[name='isAdd']").prop("checked", true);  
            } else {  
                $("[name='isAdd']").prop("checked", false);  
            }  
        }
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
                    permission.isEdit = false;
                    permission.isDelete = false;
                    permission.isPrint = false;
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
                            siping.alert(1, data.msg, "group/getlist");
                        } else {
                            siping.alert(0, data.msg);
                        }
                    }
                });
            });
        });
    </script>
</@insert>