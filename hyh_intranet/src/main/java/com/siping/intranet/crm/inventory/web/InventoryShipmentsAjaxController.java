package com.siping.intranet.crm.inventory.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.URLUtils;

import com.siping.intranet.crm.inventory.service.InventoryShipmentsService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.AddInventoryShipmentsRequest;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class InventoryShipmentsAjaxController extends StromaController {
    @Autowired
    private InventoryShipmentsService inventoryShipmentsService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/inventoryshipments/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody AddInventoryShipmentsRequest request, Map<String, Object> model, HttpServletRequest req) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(userId);
            String remoteIp = URLUtils.getRemoteIp(req);
            request.setIp(remoteIp);
            request.setMac(URLUtils.getMACAddress(remoteIp));
            Boolean isAdd = inventoryShipmentsService.add(request);
            return new ResultMsg(isAdd, isAdd ? i18nUtil.getMessage(ErrorCode.ADD_SUCCESS) : i18nUtil.getMessage(ErrorCode.ADD_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/inventoryshipments/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(AddInventoryShipmentsRequest request, Map<String, Object> model) {
        try {
            Boolean isEdit = inventoryShipmentsService.edit(request);
            return new ResultMsg(isEdit, isEdit ? i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS) : i18nUtil.getMessage(ErrorCode.EDIT_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/inventoryshipments/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam List<String> shipmentsNumberList, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            Boolean isDelete = inventoryShipmentsService.delete(shipmentsNumberList, userId);
            return new ResultMsg(isDelete, isDelete ? i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS) : i18nUtil.getMessage(ErrorCode.DELETE_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }
}
