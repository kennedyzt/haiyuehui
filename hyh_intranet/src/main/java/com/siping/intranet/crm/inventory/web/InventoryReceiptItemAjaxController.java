package com.siping.intranet.crm.inventory.web;

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

import com.siping.intranet.crm.inventory.service.InventoryReceiptItemService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.InventoryReceiptItemRequest;

@Controller
@LoginRequired
public class InventoryReceiptItemAjaxController extends StromaController {
    @Autowired
    private InventoryReceiptItemService inventoryReceiptItemService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/inventoryreceiptitem/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(InventoryReceiptItemRequest request) {
        ResultMsg resultMsg = null;
        try {
            if (inventoryReceiptItemService.add(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventoryreceiptitem/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam(value = "id", required = true) List<Integer> ids, Map<String, Object> model) {
        ResultMsg resultMsg = null;
        try {
            if (inventoryReceiptItemService.delete(ids)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/inventoryreceiptitem/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(InventoryReceiptItemRequest request) {
        ResultMsg resultMsg = null;
        try {
            if (inventoryReceiptItemService.edit(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR));
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

}
