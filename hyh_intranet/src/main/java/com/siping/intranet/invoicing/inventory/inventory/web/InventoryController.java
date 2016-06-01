package com.siping.intranet.invoicing.inventory.inventory.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.common.CommonBillsForm;
import com.siping.intranet.invoicing.inventory.inventory.service.InventoryService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryResponse;

@Controller
@LoginRequired
public class InventoryController extends StromaController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/inventory/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "id", required = true) String id, Map<String, Object> model) {
        try {
            GetInventoryResponse response = inventoryService.get(id);
            model.put("inventory", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return "";
    }

    @RequestMapping(value = "/inventory/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public void getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                        Map<String, Object> model) {
        // TODO,返回GetInventoryListResponse
    }
}
