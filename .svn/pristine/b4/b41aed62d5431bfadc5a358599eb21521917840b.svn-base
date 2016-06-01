package com.siping.intranet.crm.group.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SecureSessionContext;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.group.serivce.UserGroupService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.group.request.UserGroupRequest;
import com.siping.smartone.group.response.UserGroupResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
@Controller
public class UserGroupController extends StromaController{
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private UserGroupService userGroupService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SecureSessionContext secureSessionContxet;
    
    @RequestMapping(value = "/usergroup/add", method = RequestMethod.POST)
    public String addUserGroup(UserGroupRequest request, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            model.put("isAdd", userGroupService.addUserGroup(request));
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return "";
    }

    @RequestMapping(value = "/usergroup/get", method = RequestMethod.GET)
    public String getUserGroupById(@RequestParam(value = "id", required = false) String id, @RequestParam(value = "userId", required = false) String userId,Map<String, Object> model) {
        try {
            UserGroupResponse userGroupResponse = userGroupService.getUserGroup(id,userId);
            model.put("userGroup", userGroupResponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return "";
    }
    @RequestMapping(value = "/usergroup/getList", method = RequestMethod.GET)
    public String getUserGroupList(@RequestParam(value = "groupId", required = false) String groupId,Map<String, Object> model) {
        try {
            UserGroupResponse userGroupResponse = userGroupService.getUserGroupList(groupId);
            model.put("userGroup", userGroupResponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
            return "";
        }
        return "";
    }
}
