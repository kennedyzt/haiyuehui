package com.siping.wms.readyreceipt.web;

import java.util.LinkedList;
import java.util.List;
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

import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.wms.request.ESaleOrderRequest;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.wms.readyreceipt.service.ReadyShipmentsService;

@Controller
@LoginRequired
public class ReadyShipmentsAjaxController extends StromaController {
    @Autowired
    private ReadyShipmentsService readyShipmentsService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/readyshipments/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(ESaleOrderRequest request, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId.toString());
            Boolean isAdd = readyShipmentsService.add(request);
            return new ResultMsg(isAdd, isAdd ? i18nUtil.getMessage(ErrorCode.ADD_SUCCESS) : i18nUtil.getMessage(ErrorCode.ADD_ERROR_NO_PARAM));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/readyshipments/gets", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List<ReadyShipmentsResponse> gets(@RequestParam(value = "ids", required = true) List<String> ids, Map<String, Object> model) {
        List<ReadyShipmentsResponse> response = new LinkedList<>();
        try {
            for (String id : ids) {
                response.add(readyShipmentsService.get(id));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/readyshipments/updatestatus", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public ResultMsg updateStatus(@RequestParam(value = "ids", required = true) List<String> ids, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        try {
            for (String id : ids) {
                readyShipmentsService.updateStatus(id, 1, userId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
}
