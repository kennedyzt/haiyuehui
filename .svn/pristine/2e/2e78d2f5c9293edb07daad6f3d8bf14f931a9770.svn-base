package com.siping.intranet.crm.partnertype.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.partnertype.service.PartnerTypeService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class PartnerTypeAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    PartnerTypeService partnerTypeService;

    @RequestMapping(value = "/partnertype/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg deletePartnerTypeById(@RequestParam("id") Integer id) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            partnerTypeService.deletePartnerTypeById(id, loginId);
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

}
