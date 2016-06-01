<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>系统登录</title>
    <#include "../layout/constant/constant_content.ftl"/>
    <@css href="css/web/login.css" />

    <@js src="js/jquery/jquery.form.js" />
    <@js src="js/jquery/jquery.md5.js" />
    <@js src="js/siping/web/login.js" />
    <script type="text/javascript">
        if (window != top) {
            top.location.href = rootPath;
        }
        $(function(){
            var logMsg = "${logMsg!}";
            if(logMsg != "ok"){
                layer.msg(logMsg);
            }
        });
    </script>
</head>
<body>
    <div class="log_bg"><img class="log_bg_img" src="<@static value='/imgs/bg_login.png'/>"></div>
    <div class="log_form transparent_login my_radius">
        <form id="" action="./login" method="post">
            <div class="login_left">
                <div class="left_blank"></div>
                <div class="company_logo">
                    <img src="<@static value='/imgs/logo.png'/>" >
                </div>
                <div class="sys_name">
                    <img src="<@static value='/imgs/logo_name.png'/>" >
                </div>
            </div>
            <div class="login_right">
                <div class="log_box">
                    <div class="log_welcome">
                        <span class="vertical_center"></span>
                        <span class="welcome_info">欢迎登录</span>
                    </div>
                    <div class="log_dv log_usr">
                        <div class="icon_dv">
                            <span class="vertical_center"></span>
                            <img class="log_icon" src="<@static value='/icons/log_user.png' />">
                            <span class="log_text_name">用户名</span>
                        </div>
                        <div class="text_dv">
                            <span class="vertical_center"></span>
                            <input type="text" placeholder="请输入您的账号" class="log_text input_text_common my_radius" id="username" name="username"/>
                        </div>
                    </div>
                    <div class="log_dv log_psw">
                        <div class="icon_dv">
                            <span class="vertical_center"></span>
                            <img class="log_icon" src="<@static value='/icons/log_pass.png' />">
                            <span class="log_text_name">密&nbsp;&nbsp;&nbsp;&nbsp;码</span>
                        </div>
                        <div class="text_dv">
                            <span class="vertical_center"></span>
                            <input type="password" placeholder="请输入您的密码" class="log_text input_text_common my_radius" id="password" name="password" />
                        </div>
                    </div>
                    <div class="log_on">
                        <span class="vertical_center"></span>
                        <button type="submit" class="log_btn my_radius" id="login_button_">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
    <div class="log_footer">技术支持&copy;<span class="vertical_center"></span><a href="http://www.sipingsoft.com" target="_blank">成都四平软件有限公司</a></div>
</body>
</html>