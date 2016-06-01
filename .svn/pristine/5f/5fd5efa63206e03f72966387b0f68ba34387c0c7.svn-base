package com.siping.intranet.crm.partnertype.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.partnertype.service.PartnerTypeService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.partnertype.request.PartnerTypeRequest;
import com.siping.smartone.partnertype.response.PartnerTypeResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class PartnerTypeController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    PartnerTypeService partnerTypeService;

    @RequestMapping(value = "/partnertype/add", method = RequestMethod.POST)
    @ResponseBody
    public String addPartnerType(PartnerTypeRequest request, Map<String, Object> model) {
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(loginId);
            partnerTypeService.addPartnerType(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/partnertype/edit", method = RequestMethod.POST)
    public String editPartnerTypeById(PartnerTypeRequest request, Map<String, Object> model) {
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(loginId);
            partnerTypeService.editPartnerTypeById(request);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/partnertype/getlist", method = RequestMethod.GET)
    public String editPartnerGroupById(Map<String, Object> model) {
        try {
            List<PartnerTypeResponse> reponse = partnerTypeService.getAllPartnerType();
            model.put("partnerGroupList", reponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

}
