package com.siping.intranet.crm.businesspartner.web;

import java.util.Map;

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

import com.siping.intranet.crm.businesspartner.service.PartnerGroupService;
import com.siping.smartone.businesspartner.request.PartnerGroupManagementRequest;
import com.siping.smartone.businesspartner.request.PartnerGroupRequest;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class PartnerGroupAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    PartnerGroupService partnerGroupService;

    @RequestMapping(value = "/partnergroup/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addPartnerGroup(PartnerGroupRequest request, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(loginId);
            partnerGroupService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/partnergroup/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg editPartnerGroupById(PartnerGroupRequest request, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(loginId);
            partnerGroupService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    // @RequestMapping(value = "/partnergroup/delete", method =
    // RequestMethod.GET)
    // @ResponseBody
    public ResultMsg deletePartnerGroupById(@RequestParam("id") Integer id) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            partnerGroupService.deletePartnerGroupById(id, loginId);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/partnergroup/management", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg PartnerGroupManage(PartnerGroupManagementRequest request) {
        ResultMsg resultMsg = null;
        try {
            partnerGroupService.management(request);
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/partnergorup/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg deletePartnerGroup(@RequestParam(required = true) Integer id, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            partnerGroupService.delete(id, updateBy);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
}
