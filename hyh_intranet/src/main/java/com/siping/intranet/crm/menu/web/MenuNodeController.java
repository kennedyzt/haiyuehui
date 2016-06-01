package com.siping.intranet.crm.menu.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.menu.service.MenuNodeService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.menu.request.GetMenuNodeListForm;
import com.siping.smartone.menu.request.MenuNodeRequest;
import com.siping.smartone.menu.response.MenuNodeResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class MenuNodeController extends StromaController {
    @Autowired
    private MenuNodeService menuNodeService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/menunode/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getMenuNodeList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, GetMenuNodeListForm request,
                              Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MENUNODE_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetMenuNodeListForm.class, sessionContext.get(SessionConstants.MENUNODE_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.MENUNODE_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<MenuNodeResponse> pagingResponse = new PagingResponse<MenuNodeResponse>();
            pagingResponse = menuNodeService.getAllMenuNodes(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            PageModel<MenuNodeResponse> pageModel = new PageModel<MenuNodeResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_MENUNODE;
    }

    @RequestMapping(value = "/menunode/add", method = RequestMethod.GET)
    public String addMenuNodeView() {
        return PagePath.ADD_MENUNODE;
    }

    @RequestMapping(value = "/menunode/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addUser(@RequestBody MenuNodeRequest addUserRequest) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            addUserRequest.setCreateBy(userId);
            if (menuNodeService.addMenuNode(addUserRequest)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/menunode/edit", method = RequestMethod.GET)
    public String viewEditMenuNode(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            MenuNodeResponse menunode = menuNodeService.getMenuNode(id);
            model.put("menunode", menunode);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return PagePath.EDIT_MENUNODE;
    }

    @RequestMapping(value = "/menunode/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg editMenuNode(@RequestBody MenuNodeRequest request, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(userId);
            if (menuNodeService.updateMenuNode(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/menunode/get", method = RequestMethod.GET)
    public String getMenuNodeById(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            MenuNodeResponse menuNodeResponse = menuNodeService.getMenuNode(id);
            model.put("menunode", menuNodeResponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return PagePath.VIEW_MENUNODE;
    }

    @RequestMapping(value = "/menunode/openwin", method = { RequestMethod.POST, RequestMethod.GET })
    public String openWinGetMenu(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                 GetMenuNodeListForm request, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.MENUNODE_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetMenuNodeListForm.class, sessionContext.get(SessionConstants.MENUNODE_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.MENUNODE_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<MenuNodeResponse> pagingResponse = new PagingResponse<MenuNodeResponse>();
            pagingResponse = menuNodeService.getAllMenuNodes(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            PageModel<MenuNodeResponse> pageModel = new PageModel<MenuNodeResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.OPEN_WIN_MENUNODE;
    }
}
