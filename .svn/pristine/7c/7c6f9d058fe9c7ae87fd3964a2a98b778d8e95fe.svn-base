package com.siping.integrate.transfer;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.stroma.framework.core.platform.web.freemarker.tag.TagSupport;
import org.stroma.framework.core.platform.web.site.URLBuilder;
import org.stroma.framework.core.setting.DeploymentSettings;
import org.stroma.framework.core.setting.SiteSettings;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class ParamPageTag extends TagSupport implements TemplateDirectiveModel {

    private final HttpServletRequest request;
    private final DeploymentSettings deploymentSettings;
    private final SiteSettings siteSettings;

    public ParamPageTag(HttpServletRequest request, DeploymentSettings deploymentSettings, SiteSettings siteSettings) {
        this.request = request;
        this.deploymentSettings = deploymentSettings;
        this.siteSettings = siteSettings;
    }

    @Override
    public void execute(Environment env, @SuppressWarnings("rawtypes") Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        assertNoBody(body);

        String url = getRequiredStringParam(params, "url");
        Integer pageNo = Integer.valueOf(getRequiredStringParam(params, "pageNo"));
        Integer pageSize = Integer.valueOf(getStringParam(params, "pageSize"));
        Integer totalRecords = Integer.valueOf(getRequiredStringParam(params, "totalRecords"));

        Writer output = env.getOut();
        output.write(createPageNav(url, pageNo, pageSize, totalRecords));
    }

    private String createOption(Integer pageSize) {
        int currentPagesize = null == pageSize || pageSize == 0 ? 10 : pageSize;
        int[] chooses = { 10, 20, 30, 50, 80 };
        StringBuilder options = new StringBuilder();
        options.append("<select class=\"my_radius\" id=\"selectedPageSize_\" onchange=\"pageSizeChange(this)\">");
        for (int choose : chooses) {
            if (choose == currentPagesize) {
                options.append("<option selected=\"selected\" value=\"" + choose + "\">" + choose + "</option>");
            } else {
                options.append("<option value=\"" + choose + "\">" + choose + "</option>");
            }
        }
        options.append("</select>");
        return options.toString();
    }

    private String createPageCtrl(String url, Integer pageNo, Integer pageSize, Integer totalRecords) {
        Integer totalPages = getTotalPages(pageSize, totalRecords);
        StringBuilder html = new StringBuilder();

        html.append("<div class=\"page_ctrol\">");
        String first = createUrl(url, 1, pageSize);
        String last = createUrl(url, totalPages, pageSize);
        String next = createUrl(url, pageNo + 1, pageSize);
        String previous = createUrl(url, pageNo - 1, pageSize);
        String rootPath = request.getScheme() + "://" + deploymentSettings.getHost() + ":" + deploymentSettings.getHTTPPort() + siteSettings.getStaticDir();
        if (pageNo == 1) {
            html.append("<span><img src=\"" + rootPath + "/icons/first0.png\"></span>");
            html.append("<span><img src=\"" + rootPath + "/icons/previous0.png\"></span>");
        } else {
            html.append("<span><a href=\"" + first + "\"><img src=\"" + rootPath + "/icons/first.png\"></a></span>");
            html.append("<span><a href=\"" + previous + "\"><img src=\"" + rootPath + "/icons/previous1.png\"></a></span>");
        }
        html.append("<span>第&nbsp;<font id=\"current_page_no\">" + pageNo + "</font>&nbsp;页</span>");
        if (pageNo >= totalPages) {
            html.append("<span><img src=\"" + rootPath + "/icons/next0.png\"></span>");
            html.append("<span><img src=\"" + rootPath + "/icons/last0.png\"></span>");
        } else {
            html.append("<span><a href=\"" + next + "\"><img src=\"" + rootPath + "/icons/next1.png\"></a></span>");
            html.append("<span><a href=\"" + last + "\"><img src=\"" + rootPath + "/icons/last.png\"></a></span>");
        }
        html.append("<span>共&nbsp;<font id=\"total_page_ct\">" + totalPages + "</font>&nbsp;页</span>");
        html.append("</div><div class=\"page_to\">");
        html.append("<span>转到<input id=\"gotoPageNo_\" class=\"my_radius font_text_indent\">页<button id=\"gotoPage\" data-page-url=\"" + url
            + "\" class=\"btn_common my_radius btn_submit\">go</button></span></div>");
        return html.toString();
    }

    protected String createPageNav(String url, int pageNo, int pageSize, int totalRecords) {
        StringBuilder div = new StringBuilder();
        div.append("<div class=\"page_nav\"><div class=\"page_sizes\"><span>显示条数</span>");
        div.append(createOption(pageSize));
        div.append("</div>");
        div.append(createPageCtrl(url, pageNo, pageSize, totalRecords));
        div.append("</div>");
        return div.toString();
    }

    private Integer getTotalPages(Integer pageSize, Integer totalRecords) {
        return (totalRecords + pageSize - 1) / pageSize;
    }

    private String createUrl(String url, Integer pageNo, Integer pageSize) {
        URLBuilder builder = new URLBuilder();
        builder.setContext(request.getContextPath(), deploymentSettings.getDeploymentContext());
        String perfix = url.indexOf('?') == -1 ? "?" : "&";
        return builder.constructRelativeURL(url + perfix + "pageNo=" + pageNo + "&pageSize=" + pageSize);
    }
}
