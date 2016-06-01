<div class="header_shift_div">
    <span class="vertical_center"></span>
    <img class="shift_icon header_icon_comm" id="shift_icon" src="<@static value='/icons/shift_down.png' />" title="隐藏/显示菜单栏">
</div>
<div class="header_right">
    <div class="refresh_setcut">
        <span class="vertical_center"></span>
        <a onclick="refreshIframePage();"><img class="refresh_icon header_icon_comm" src="<@static value='/icons/refresh.png' />" title="刷新"></a>
        <#-- <a href="#"><img class="shortcut_set_icon header_icon_comm" src="<@static value='/icons/apply_icon.png' />" title="设置快捷菜单"></a> -->
        <div class="s_userinfo my_radius" title="当前用户">
            <span class="vertical_center"></span>
            <img class="user_icon header_icon_comm" src="<@static value='/icons/user_icon.png' />">
            <span class="s_user_name">${loginUser.nickname}</span>
        </div>
        <a onclick="resetMyPsw();"><img class="refresh_icon header_icon_comm" src="<@static value='/icons/change_pass.png' />" title="修改密码"></a>
        <a href="<@url value='/logout' />"><img class="logout_icon header_icon_comm" src="<@static value='/icons/exit.png' />" title="退出登录"></a>
    </div>
</div>