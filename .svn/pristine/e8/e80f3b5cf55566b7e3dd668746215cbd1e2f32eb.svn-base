package com.siping.intranet.crm.menu.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.menu.service.MenuService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.menu.request.MenuTreeRequest;
import com.siping.smartone.menu.response.MenuTreeResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class MenuController extends StromaController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/menutree/add", method = RequestMethod.POST)
    public String addMenuTree(MenuTreeRequest menuTreeRequest, Map<String, Object> model, @RequestParam(value = "id", required = false) Integer id) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            menuTreeRequest.setCreateBy(userId);
            model.put("isAdd", menuService.addMenuTree(menuTreeRequest));
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return "";
    }

    @RequestMapping(value = "/menutree/edit", method = RequestMethod.POST)
    public String editMenuTree(MenuTreeRequest menuTreeRequest, Map<String, Object> model) {
        Boolean isEdit = false;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            menuTreeRequest.setUpdateBy(userId);
            isEdit = menuService.updateMenuTree(menuTreeRequest);
            model.put("isEdit", isEdit);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return "";
    }

    @RequestMapping(value = "/menutree/get", method = RequestMethod.GET)
    public String getMenuTreeById(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            MenuTreeResponse menuTreeResponse = menuService.getMenuTree(id);
            model.put("menuTree", menuTreeResponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return "";
    }

    @RequestMapping(value = "/menutree/getlist", method = RequestMethod.GET)
    public String getTreeList(Map<String, Object> model) {
        try {
            List<MenuTreeResponse> treeList = menuService.getTreeList();
            model.put("menuTreeList", treeList);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return "";
    }
}
