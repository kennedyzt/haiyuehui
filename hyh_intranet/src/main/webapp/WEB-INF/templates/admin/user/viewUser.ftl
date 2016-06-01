<#setting classic_compatible=true>
<@insert template="login/right">
    <div class="add_page" style="height:50%;">
        <input type="hidden" id="id" value="${userId}"/>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">用户名</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="userName" ></span></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">昵称</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="nickName"></span></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">帐号类型</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="userType"></span></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">（员工/业务伙伴）名称</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="objectName"></span></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">（员工/业务伙伴）编号</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="objectId"></span></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">邮箱</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="email"></span></div>
        </div>
        <div class="user_text_div common_text_div">
            <div class="common_text_title">权限组</div>
            <div class="common_text_val"><span class="readonly_text_common grid_page_text" id="groups"></span></div>
        </div>
    </div>
    <script type="text/javascript">
        $(function(){
                $.post(createUrl('usr/get'),
                [
                    { name: 'id', value: $("#id").val() }
                ],
                function(result) {
                    $("#userName").html(result.username);
                    $("#nickName").html(result.nickname);
                    $("#email").html(result.email);
                    if(result.userType == "BusiPartner"){
                        $("#userType").html("业务伙伴");
                        $("#objectId").html(result.objectId);
                    }else if(result.userType == "Employee"){
                        $("#userType").html("员工");
                    }else if(result.userType == "System"){
                        $("#userType").html("系统用户");
                    }
                    var groups = result.groups;
                    var groupHtml = "";
                    if(groups){
                        for(var i=0; i<groups.length; i++){
                            var group = groups[i];
                            groupHtml += group.groupName +"  ";
                        }
                    }
                    $("#groups").html(groupHtml);
                }
            );
        });
    </script>
</@insert>