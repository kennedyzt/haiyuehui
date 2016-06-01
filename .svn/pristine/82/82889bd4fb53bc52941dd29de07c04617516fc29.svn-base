$(function(){
    $("#admin_user_add").click(function() {
        var addUser = {};
        addUser.userName = $("#userName").val();
        addUser.nickName = $("#nickName").val();
        addUser.pwd = $("#password").val();
        addUser.email = $("#email").val();
        addUser.userType = $("#userType").val();
        addUser.objectId = $("#objectId").val();
        addUser.userAccount = $("input[name='userAccount']:checked").val();

        var groups = [];
        $("input[name=groups]:checked").each(function(){
            groups.push($(this).val());
        });
        addUser.groups = groups;

        siping.ajax({
            method : "post",
            url : 'usr/add',
            data : JSON.stringify(addUser),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });

    $("#admin_user_edit").click(function() {
        var editUser = {};
        editUser.id = $("#id").val();
        editUser.nickName = $("#nickName").val();
        editUser.password = $("#password").val();
        editUser.email = $("#email").val();
        editUser.userType = $("#userType").val();
        editUser.objectId = $("#objectId").val();
        editUser.userAccount = $("input[name='userAccount']:checked").val();
        if(!editUser.userAccount){
            siping.tips("必选项","account_erp");
            return;
        }

        var groups = [];
        $("input[name=groups]:checked").each(function(){
            groups.push($(this).val());
        });
        editUser.groups = groups;

        siping.ajax({
            method : "post",
            url : 'usr/edit',
            data : JSON.stringify(editUser),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg, "usr/getlist");
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });

    $("#admin_group_add").click(function() {
        var addGroup = {};
        addGroup.groupName = $("#groupName").val();
        addGroup.description = $("#description").val();
        addGroup.isDelete = false;
        addGroup.userAccount = $("#userAccount").val();

        siping.ajax({
            method : "post",
            url : 'group/add',
            data : JSON.stringify(addGroup),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    $("#admin_group_edit").click(function() {
        var editGroup = {};
        editGroup.id = $("#id").val();
        editGroup.groupName = $("#groupName").val();
        editGroup.description = $("#description").val();
        editGroup.isDelete = false;

        siping.ajax({
            method : "post",
            url : 'group/edit',
            data : JSON.stringify(editGroup),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg,'group/getlist');
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });

    $("#admin_menunode_add").click(function() {
        var addMenuNode = {};
        addMenuNode.name = $("#name").val();
        addMenuNode.parentId = $("#parentMenuId").val();
        addMenuNode.nodeUrl = $("#nodeUrl").val();
        addMenuNode.description = $("#description").val();
        addMenuNode.sequence = $("#sequence").val();
        addMenuNode.icon = $("#icon").val();
        addMenuNode.menuRoot = $("#menuRoot").val();
        addMenuNode.userAccount = $("#userAccount").val();
        addMenuNode.isRoot = false;

        siping.ajax({
            method : "post",
            url : 'menunode/add',
            data : JSON.stringify(addMenuNode),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });

    $("#admin_menunode_edit").click(function() {
        var addMenuNode = {};
        addMenuNode.id = $("#id").val();
        addMenuNode.name = $("#name").val();
        addMenuNode.parentId = $("#parentMenuId").val();
        addMenuNode.nodeUrl = $("#nodeUrl").val();
        addMenuNode.description = $("#description").val();
        addMenuNode.sequence = $("#sequence").val();
        addMenuNode.icon = $("#icon").val();
        addMenuNode.menuRoot = $("#menuRoot").val();
        addMenuNode.isRoot = false;
        addMenuNode.userAccount = $("#userAccount").val();

        siping.ajax({
            method : "post",
            url : 'menunode/edit',
            data : JSON.stringify(addMenuNode),
            dataType : "json",
            contentType : "application/json",
            success : function(data) {
                if (data.success) {
                    siping.alert(1, data.msg);
                } else {
                    siping.alert(0, data.msg);
                }
            }
        });
    });
    userTypeChange();
    showUserGroups("erp");
});

function deleteUser() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids = checkbox[i].id;
    }
    if(ids!=""){
        siping.confirm('确定要将此用户置为不可用吗？此操作将使该用户无法登录系统,并清除用户已有权限', function() {
            siping.ajax({
                method : 'post',
                url : '/usr/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择数据行！");
    }
}

function deleteMenuNode() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids = checkbox[i].id;
    }
    if(ids!=""){
        siping.confirm('确定删除菜单吗？', function() {
            siping.ajax({
                method : 'post',
                url : '/menunode/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择要删除的数据行！");
    }
}

function deleteGroup() {
    var ids = [];
    var checkbox = $("input[name='check_box']:checked");
    for (var i = 0; i < checkbox.length; i++) {
        ids = checkbox[i].id;
    }
    if(ids!=""){
        siping.confirm('确定删除用户组吗？', function() {
            siping.ajax({
                method : 'post',
                url : '/group/delete?id=' + ids,
                async : true,
                success : function(data) {
                    if (data.success) {
                        siping.alert(1, data.msg);
                    } else {
                        siping.alert(0, data.msg);
                    }
                }
            });
        });
    }else{
        siping.alert(0, "请选择要删除的数据行！");
    }
}

function openWinGetParentMenu() {
    var title = "选择上级菜单";
    siping.openWindow('/menunode/openwin', '900px', '450px', title);
}

function userTypeChange(){
    var userType = $("#userType").val();
    if(userType  == "Employee" ){
        $("#objectName").val("");
        $("#objectId").val("");
        $("#objectName").attr("disabled", true);
        $("#objectId").attr("disabled", true);
        $("#account_wms").prop("disabled",false);
        $("input[class='group_sign_erp']").prop("disabled",false);
        $("input[class='group_sign_wms']").prop("disabled",false);
    }
    if(userType  == "BusiPartner"){
        $("#objectName").attr("disabled", false);
        $("#objectId").attr("disabled", false);
        $("#objectId").attr("readonly", true);
        $("#account_erp").prop("checked",true);
        $("#account_wms").prop("checked",false);
        $("#account_wms").prop("disabled",true);
        $("input[class='group_sign_erp']").prop("checked",false);
        $("input[class='group_sign_wms']").prop("checked",false);
        $("input[class='group_sign_erp']").prop("disabled",true);
        $("input[class='group_sign_wms']").prop("disabled",true);
    }
}

function chooseParMenuWin() {
    var id;
    var checkbox = $("input[name='check_box']:checked");
    id = $(checkbox).attr("id");
    var menuroot = $(checkbox).attr("data-menuroot");
    var icon = $(checkbox).attr("data-menuicon");
    if (id != undefined) {
        var idArray = [ "menuId", "menuName" ];
        for (var i = 0; i < idArray.length; i++) {
            var thisId = idArray[i];
            if (thisId == "menuId") {
                parent.$("#parentMenuId").val($("#" + thisId +"_"+ id).text());
            }
            if (thisId == "menuName"){
                parent.$("#parentMenuName").val($("#" + thisId +"_"+ id).text());
            }
        }
//        parent.$("#menuRoot").val(menuroot);
//        parent.$("#menuRoot").attr("readonly",true);
        parent.$("#icon").val(icon);
        parent.$("#icon").attr("readonly",true);
        parent.siping.close(partnerIndex);
    } else {
        siping.alert(0, "请选择");
    }
}
function openWinGetPartnerName(type){
    var title;
    if (type == 1) {
        title = "选择客户";
    } else {
        title = "选择供应商";
    }
    siping.openWindow('/usr/businesspartner/openwin?partnerType=' + type, '900px', '450px', title);
}
function chooseUserAcc(obj){
    var val = obj.value;
    var checked = obj.checked;
    if(val == "erp" && checked){
        $("#account_wms").prop("checked",false);
        showUserGroups(val);
    }
    if(val == "wms" && checked){
        $("#account_erp").prop("checked",false);
        showUserGroups(val);
    }
    var userType = $("#userType").val();
    if(userType  == "BusiPartner"){
        $("#account_wms").prop("disabled",true);
        $("#account_erp").prop("disabled",false);
    }
}
function showUserGroups(userAccount){
    if(userAccount == "erp"){
        $("input[class='group_sign_wms']").prop("checked",false);
        $("input[class='group_sign_wms']").prop("disabled",true);
        $("input[class='group_sign_erp']").prop("disabled",false);
    }
    if(userAccount == "wms"){
        $("input[class='group_sign_erp']").prop("checked",false);
        $("input[class='group_sign_erp']").prop("disabled",true);
        $("input[class='group_sign_wms']").prop("disabled",false);
    }
}
function resetPassSmt(){
    var opass = $("#original_pass").val();
    var npass = $("#new_pass").val();
    var cpass = $("#confirm_pass").val();
    if($.trim(opass) == ""){
        layer.msg("请输入原密码");
        return;
    }
    if($.trim(npass) == ""){
        layer.msg("请输入新密码");
        return;
    }
    if($.trim(cpass) == ""){
        layer.msg("请再次确认新密码");
        return;
    }
    if(npass != cpass){
        layer.msg("两次新密码不一致");
        return;
    }
    siping.ajax({
        method : "POST",
        url : "usr/resetpwd",
        async : true,
        data : {oldPwd : opass, pwd : npass},
        success : function(result){
            if(result.success){
                siping.alert(1,"修改成功，请重新登录","/logout");
            }else{
                layer.msg(result.msg);
            }
        }
    });
}