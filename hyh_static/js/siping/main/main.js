$(function(){
    $("#shift_icon").click(function(){
        var dispaly = $("#left_menus").css("display");
        if(dispaly=="none"){
            $(this).attr("src",staticPath+"/icons/shift_down.png");
            $("#left_menus").show(300);
        }else{
            $(this).attr("src",staticPath+"/icons/shift_up.png");
            $("#left_menus").hide(300);
        }
    });
    
    siping.ajax({
        method : "post",
        url : 'usr/getPermissions',
        data : null,
        dataType : "json",
        contentType : "application/json",
        success : function(result) {
            var data = result.permissions;
            var userId = result.id;
            if(userId == 0){
                $("#administrator_menus").show();
            }else{
                $("#administrator_menus").remove();
                if(result.userType == "BusiPartner"){
                    $("#busi_partner_menus").show();
                }else{
                    $("#busi_partner_menus").remove();
                    var menusHtml = "";
                    for(var i=0; i<data.length; i++){
                        var menu = data[i];
                        if(!menu.level || menu.level == 0){
                            menusHtml += '<div class="menu_level_1">';
                            menusHtml += '<div class="menu_click s_menu_name menu_1_style">';
                            menusHtml += '<div class="s_menu_l">';
                            menusHtml += '<img class="s_menu_icon" src="'+createMenuUrl(menu.icon)+'"><span>'+menu.nodeName+'</span>';
                            menusHtml += '</div>';
                            menusHtml += '<div class="s_menu_r">';
                            menusHtml += '<img class="s_tree_hide" src="'+staticPath+'/icons/tree_hide.png">';
                            menusHtml += '</div></div>';
                            menusHtml += createLevel2Html(menu, data);
                            menusHtml += '</div>';
                        }
                    }
                    $("#left_menus").html(menusHtml);
                }
            }
            addMenuEvent();
        }
    });

    function hasSubMenus(parentMenu, menus){
        for(var i=0; i<menus.length; i++){
            var menu = menus[i];
            if(menu.parentId == parentMenu.nodeId){
                return true;
            }
        }
        return false;
    }
    function createLevel4Html(parentMenu, menus){
        var l4menuHtml = "";
        for(var i=0; i<menus.length; i++){
            var menu = menus[i];
            if(menu.parentId == parentMenu.nodeId){
                if(true){
                    l4menuHtml += '<div class="menu_level_leaf menu_level" data-sign="postperiod">';
                    l4menuHtml += ' <div class="s_menu_name menu_leaf_style">';
                    l4menuHtml += '<a data-type="url" data-url="'+createUrl(menu.nodeUrl)+'">';
                    l4menuHtml += '<div class="s_menu3_l">';
                    l4menuHtml += '<img class="s_menu_icon" src="'+staticPath+'/icons/tree_3.png"><span>'+menu.nodeName+'</span>';
                    l4menuHtml += '</div>';
                    l4menuHtml += '</a>';
                    l4menuHtml += '</div>';
                    l4menuHtml += '</div>';
                }
            }
        }
        return l4menuHtml;
    }
    function createLevel3Html(parentMenu, menus){
        var l3menuHtml = "";
        for(var i=0; i<menus.length; i++){
            var menu = menus[i];
            if(menu.parentId == parentMenu.nodeId){
                if(hasSubMenus(menu, menus)){
                    l3menuHtml += '<div class="menu_level_3 menu_level">';
                    l3menuHtml += '<div class="menu_click s_menu_name menu_3_style">';
                    l3menuHtml += '<div class="s_menu_l">';
                    l3menuHtml += '<img class="s_menu_icon" src="'+staticPath+'/icons/tree_3.png"><span>'+menu.nodeName+'</span>';
                    l3menuHtml += '</div>';
                    l3menuHtml += '<div class="s_menu_r">';
                    l3menuHtml += '<img class="s_tree_hide" src="'+staticPath+'/icons/tree_hide.png">';
                    l3menuHtml += '</div>';
                    l3menuHtml += '</div>';
                    l3menuHtml +=  createLevel4Html(menu, menus);
                    l3menuHtml += '</div>';
                }else{
                    l3menuHtml += '<div class="menu_level_3 menu_level" data-sign="postperiod">';
                    l3menuHtml += '<div class="s_menu_name menu_3_style">';
                    l3menuHtml += '<a data-type="url" data-url="'+createUrl(menu.nodeUrl)+'">';
                    l3menuHtml += '<div class="s_menu3_l">';
                    l3menuHtml += '<img class="s_menu_icon" src="'+staticPath+'/icons/tree_3.png"><span>'+menu.nodeName+'</span>';
                    l3menuHtml += '</div></a>';
                    if(menu.isAdd && menu.menuRoot){
                       l3menuHtml += '<div class="s_menu3_r"><a data-type="url" data-url="'+createUrl(menu.menuRoot)+'" >新增</a></div>';
                    }
                    l3menuHtml += '</div></div>';
                }
            }
        }
        return l3menuHtml;
    }
    function createLevel2Html(parentMenu, menus){
        var l2menuHtml = "";
        for(var i=0; i<menus.length; i++){
            var menu = menus[i];
            if(menu.parentId == parentMenu.nodeId){
                if(hasSubMenus(menu, menus)){
                    l2menuHtml += '<div class="menu_level_2 menu_level">';
                    l2menuHtml += '<div class="menu_click s_menu_name menu_2_style">';
                    l2menuHtml += '<div class="s_menu_l">';
                    l2menuHtml += '<img class="s_menu_icon" src="'+staticPath+'/icons/tree_3.png"><span>'+menu.nodeName+'</span>';
                    l2menuHtml += '</div>';
                    l2menuHtml += '<div class="s_menu_r">';
                    l2menuHtml += '<img class="s_tree_hide" src="'+staticPath+'/icons/tree_hide.png">';
                    l2menuHtml += '</div></div>';
                    l2menuHtml += createLevel3Html(menu, menus);
                    l2menuHtml +='</div>';
                } else {
                    l2menuHtml += '<div class="menu_level_3 menu_level" data-sign="sellorder">';
                    l2menuHtml += '<div class="s_menu_name menu_3_style">';
                    l2menuHtml += '<a data-type="url" data-url="'+createUrl(menu.nodeUrl)+'">';
                    l2menuHtml += '<div class="s_menu3_l">';
                    l2menuHtml += '<img class="s_menu_icon" src="'+staticPath+'/icons/tree_3.png"><span>'+menu.nodeName+'</span>';
                    l2menuHtml += '</div></a>';
                    if(menu.isAdd && menu.menuRoot){
                       l2menuHtml += '<div class="s_menu3_r"><a data-type="url" data-url="'+createUrl(menu.menuRoot)+'" >新增</a></div>';
                    }
                    l2menuHtml += '</div></div>';
                }
            }
        }
        return l2menuHtml;
    }
});
function addMenuEvent(){
    $(".menu_click").click(function(){
        var display = $(this).next().css("display");
        if(display=="none"){
            $(this).find(".s_tree_hide").attr("src",staticPath+"/icons/tree_show.png");
            $(this).siblings().show();
            $(this).parent().siblings().find(".menu_level").hide();
            $(this).parent().siblings().find(".menu_click").find(".s_tree_hide").attr("src",staticPath+"/icons/tree_hide.png");
            $(this).parent().siblings(".menu_click").find(".s_tree_hide").attr("src",staticPath+"/icons/tree_show.png");
        }else{
            $(this).find(".s_tree_hide").attr("src",staticPath+"/icons/tree_hide.png");
            $(this).siblings().hide();
        }
    });
    $("[class^='s_menu_name']").mouseover(function(){
        $(this).find(".s_menu3_r").show();
    });
    $("[class^='s_menu_name']").mouseout(function(){
        $(this).find(".s_menu3_r").hide();
    });
    $("#left_menus").find('*').each(function(){
        if ($(this).attr("data-type") == "url") {
            $(this).click(function(){
                //遍历是否存在当前点击的tab
                var tabUrl = $(this).attr("data-url");
                var code = tabUrl.replace(/[\/?=]/g,"");
                if (mainTabControl.existsTab(code)) {
                    mainTabControl.activeTab(code);
                    var iframeId = "frame_"+code;
                    document.getElementById(iframeId).contentWindow.location = tabUrl;
                    return;
                }
                //新增tab标签
                if ($(this).html() == "新增"){
                    mainTabControl.addTab("新增"+$(this).parent().prev().find("span").html(), code,  $(this).attr("data-url"));
                }else{
                    mainTabControl.addTab($(this).find("span").html(), code, $(this).attr("data-url"));
                }
                var tabUlWidth = $("#tab-control_tabs").width();
                var tabMaxCnt = parseInt(tabUlWidth/146);
                var tabSize = $("#tab-control_tabs").find("li").length;
                if(tabSize >= tabMaxCnt){
                    var obj = new Object();
                    obj.target = $("#tab-control_tabs li:eq(1)").find("a");
                    mainTabControl.tabClose(obj);
                }
            });
        }
    });
}
function refreshIframePage(){
    var id = $("#tab-control_tabs").find("li[class='hover']").attr("id");
    var iframeId = "frame_" + id.substr(4);
    document.getElementById(iframeId).contentWindow.location.reload(true);
}
function resetMyPsw(){
    siping.openWindow("usr/resetpass","340px","240px","修改密码");
}