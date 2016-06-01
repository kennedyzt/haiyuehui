package com.siping.intranet.crm.countryregion.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.countryregion.serivce.CountryRegionService;
import com.siping.smartone.businesspartner.request.CountryRegionRequest;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
public class CountryRegionAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private CountryRegionService countryRegionService;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/countryregion/delete", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") Integer id) {
        ResultMsg msg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            countryRegionService.delete(id, loginId);
            msg = new ResultMsg(true, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
        } catch (Exception e) {
            msg = new ResultMsg(Boolean.FALSE, "该国家/地区被引用不能删除");
        }
        return msg;
    }

    @RequestMapping(value = "/countryregion/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(CountryRegionRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setLoginId(loginId);
            countryRegionService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
        } catch (BusinessProcessException e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/countryregion/getalllist", method = RequestMethod.POST)
    @ResponseBody
    public List<CountryRegionResponse> getList() {
        List<CountryRegionResponse> response = null;
        try {
            response = countryRegionService.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
