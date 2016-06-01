<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统后台管理</title>
    <#include "../layout/constant/constant_content.ftl"/>
    <@css href="css/main/user.css" />

    <@js src="js/siping/main/user.js" />
</head>
<body style="width: 99.8%;background-color: #EEF3FA;">
    <div class="smart_header">
        <#include "../layout/constant/constant_header.ftl"/>
    </div>
    <div class="smart_main">
        <div class="left_menus">
            <div class="menu_level_1">
                <div class="menu_click s_menu_name menu_1_style">
                    <div class="s_menu_l">
                        <img class="s_menu_icon" src="/static/icons/tree_1.png">系统设置
                    </div>
                    <div class="s_menu_r">
                        <img class="s_tree_hide" src="/static/icons/tree_hide.png">
                    </div>
                </div>
                <div class="menu_level_2">
                    <div class="menu_click s_menu_name menu_2_style">
                        <div class="s_menu_l">
                            <img class="s_menu_icon" src="/static/icons/tree_2.png">用户管理
                        </div>
                        <div class="s_menu_r">
                            <img class="s_tree_hide" src="/static/icons/tree_show.png">
                        </div>
                    </div>
                    <div class="menu_level_3">
                        <div class="s_menu_name menu_3_style">
                            <a href="<@url value='/usr/getallusers'/>">
                                <div class="s_menu3_l">
                                    <img class="s_menu_icon" src="/static/icons/tree_3.png">用户
                                </div>
                                <div class="s_menu3_r"><a href="<@url value='/usr/getallusers'/>" >新增</a></div>
                            </a>
                        </div>
                    </div>
                    <div class="menu_level_3">
                        <div class="s_menu_name menu_3_style">
                            <a href="<@url value='/usr/getallusers'/>" >
                                <div class="s_menu3_l">
                                    <img class="s_menu_icon" src="/static/icons/tree_3.png">权限
                                </div>
                                <div class="s_menu3_r"></div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="right_main">
            <div class="master_tab"><#include "../layout/constant/constant_tab.ftl"/></div>
            <div class="master_body"><@body /></div>
        </div>
    </div>
</body>
</html>