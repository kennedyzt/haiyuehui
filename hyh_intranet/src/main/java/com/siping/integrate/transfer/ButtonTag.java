package com.siping.integrate.transfer;

import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.stroma.framework.core.platform.SpringObjectFactory;
import org.stroma.framework.core.platform.web.freemarker.tag.TagSupport;
import org.stroma.framework.core.platform.web.session.SessionContext;

import com.siping.intranet.crm.permission.service.PermissionService;
import com.siping.smartone.permission.response.GetButtonPermissionResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ButtonTag extends TagSupport implements TemplateDirectiveModel {

    private final HttpServletRequest request;
    private PermissionService permissionService;
    private SessionContext sessionContext;

    public ButtonTag(SessionContext sessionContext, HttpServletRequest request) {
        this.sessionContext = sessionContext;
        this.request = request;
    }

    @Override
    public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        assertNoBody(body);
        String style = getRequiredStringParam(params, "class");
        String required = getRequiredStringParam(params, "required");
        Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        String buttons = getPageButtons(style, required, userId);
        Writer output = env.getOut();
        output.write(buttons);
    }

    private String getPageButtons(String style, String required, Integer userId) {
        if (style == null) {
            style = "";
        }
        String path = request.getRequestURI();
        String contextPath = request.getContextPath();
        if(path.contains(contextPath)){
            path = path.replaceAll(contextPath, "");
        }
        if(path.contains("/")){
            path = path.replaceAll("/", "=");
        }
        GetButtonPermissionResponse permission = getUserBtnPermission(userId, path);
        String btnHtml = "<div class=\"btn_permission_style\">";
        if (required == null) {
            btnHtml = "";
        } else {
            String[] arrBtn = required.split(",");
            List<String> require = Arrays.asList(arrBtn);
            if (require.contains("all") && require.size() == 1) {
                if (permission.getAddBtn() == 1) {
                    btnHtml += "<button id=\"tag_add_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_add();\" >添加</button>";
                } else if (permission.getDelBtn() == 1) {
                    btnHtml += "<button id=\"tag_delete_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_delete();\" >删除</button>";
                } else if (permission.getEditBtn() == 1) {
                    btnHtml += "<button id=\"tag_edit_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_edit();\" >编辑</button>";
                } else if (permission.getPrintBtn() == 1) {
                    btnHtml += "<button id=\"tag_print_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_print();\" >打印</button>";
                }
            } else if (!require.contains("all")) {
                if (require.contains("add") && permission.getAddBtn() == 1) {
                    btnHtml += "<button id=\"tag_add_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_add();\" >添加</button>";
                } else if (require.contains("delete") && permission.getDelBtn() == 1) {
                    btnHtml += "<button id=\"tag_delete_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_delete();\" >删除</button>";
                } else if (require.contains("edit") && permission.getEditBtn() == 1) {
                    btnHtml += "<button id=\"tag_edit_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_edit();\" >编辑</button>";
                } else if (require.contains("print") && permission.getPrintBtn() == 1) {
                    btnHtml += "<button id=\"tag_print_button\" type=\"button\" class=\"" + style + "\" onclick=\"spf_print();\" >打印</button>";
                }
            }
            btnHtml += "</div>";
        }
        return btnHtml;
    }

    private GetButtonPermissionResponse getUserBtnPermission(Integer userId, String path) {
        permissionService = SpringObjectFactory.getInstance().getBean(PermissionService.class);
        GetButtonPermissionResponse btnPermission = permissionService.getButtonPermissions(userId, path);
        return btnPermission;
    }
}
