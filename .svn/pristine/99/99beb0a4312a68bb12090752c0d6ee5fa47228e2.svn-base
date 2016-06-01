package com.siping.intranet.crm.group.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.group.serivce.UserGroupService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class UserGroupAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/usergroup/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg deleteUserGroup(@RequestParam("id") Integer id) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            userGroupService.deleteUserGroup(id, userId);
        } catch (Exception e) {
            return new ResultMsg(false, ErrorCode.DELETE_ERROR);
        }
        return new ResultMsg(true, ErrorCode.DELETE_SUCCESS);
    }
}
