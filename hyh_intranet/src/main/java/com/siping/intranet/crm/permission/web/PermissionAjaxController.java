package com.siping.intranet.crm.permission.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.permission.service.PermissionService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.permission.request.AddUserPermissionRequest;

@Controller
public class PermissionAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/permission/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg deletePermissionByGroupId(@RequestParam(value = "groupId", required = true) Integer groupId) {
        try {
            Boolean delete = permissionService.deletePermissionByGroupId(groupId);
            return new ResultMsg(delete, delete ? i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS) : i18nUtil.getMessage(ErrorCode.DELETE_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/userpermission/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg deleteUserPermissionByUserId(@RequestParam("userId") Integer userId) {
        try {
            AddUserPermissionRequest request = new AddUserPermissionRequest();
            request.setUserId(userId);
            Boolean delete = permissionService.deleteUserPermissionByUserId(request);
            return new ResultMsg(delete, delete ? i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS) : i18nUtil.getMessage(ErrorCode.DELETE_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }
}
