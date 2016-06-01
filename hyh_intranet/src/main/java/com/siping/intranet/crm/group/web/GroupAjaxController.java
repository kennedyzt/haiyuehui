package com.siping.intranet.crm.group.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.group.serivce.GroupService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class GroupAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private GroupService groupService;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/group/delete", method = RequestMethod.POST)
    @LoginRequired
    @ResponseBody
    public ResultMsg deleteGroup(@RequestParam("id") Integer id) {
        try {
            Integer deleteUserId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            groupService.deleteGroup(id, deleteUserId);
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return new ResultMsg(true, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
    }

}
