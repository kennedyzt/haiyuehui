package com.siping.intranet.crm.permission.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.permission.service.PermissionService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.permission.request.AddPermissionRequest;
import com.siping.smartone.permission.request.AddUserPermissionRequest;
import com.siping.smartone.permission.response.GetGroupPermissionResponse;
import com.siping.smartone.permission.response.GetUserPermissionResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class PermissionController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private PermissionService permissionService;

    @RequestMapping(value = "/permission/addlist", method = RequestMethod.POST)
    public String addPermissionByGroupId(AddPermissionRequest request, Map<String, Object> model) {
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setLoginId(loginId);
            permissionService.addPermissionByGroupId(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/permission/editlist", method = RequestMethod.POST)
    public String updatePermissionByGroupId(AddPermissionRequest request, Map<String, Object> model) {
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setLoginId(loginId);
            permissionService.updatePermissionByGroupId(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/userpermission/addlist", method = RequestMethod.POST)
    public String addUserPermissionByUserId(AddUserPermissionRequest request, Map<String, Object> model) {
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setLoginId(loginId);
            permissionService.addUserPermissionByUserId(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/userpermission/editlist", method = RequestMethod.POST)
    public String editUserPermissionByUserId(AddUserPermissionRequest request, Map<String, Object> model) {
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setLoginId(loginId);
            permissionService.editUserPermissionByUserId(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/permission/get", method = RequestMethod.GET)
    public String getGroupPermission(@RequestParam(value = "groupId", required = true) Integer groupId, Map<String, Object> model) {
        try {
            GetGroupPermissionResponse groupPermission = permissionService.getGroupPermission(groupId);
            model.put("groupPermission", groupPermission);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/userpermission/get", method = RequestMethod.GET)
    public String getUserPermission(@RequestParam(value = "userId", required = true) Integer userId, Map<String, Object> model) {
        try {
            GetUserPermissionResponse userPermission = permissionService.getUserPermission(userId);
            model.put("userPermission", userPermission);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }
}
