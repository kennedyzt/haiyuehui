package com.siping.intranet.invoicing.inventory.inventory.web;

import java.util.List;
import java.util.Map;

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

import com.siping.intranet.invoicing.inventory.inventory.service.InventoryService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.request.MaterialCountsRequest;
import com.siping.smartone.inventory.response.InventoryWarningMsg;
import com.siping.smartone.invoicing.inventory.inventory.request.AddInventoryRequest;
import com.siping.smartone.invoicing.inventory.inventory.request.GetInventoryIsEnoughRequest;
import com.siping.smartone.invoicing.inventory.inventory.response.GetInventoryLogResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class InventoryAjaxController extends StromaController {
    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/inventory/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(AddInventoryRequest request, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(1);
            Boolean isAdd = inventoryService.add(request);
            return new ResultMsg(isAdd, isAdd ? i18nUtil.getMessage(ErrorCode.ADD_SUCCESS) : i18nUtil.getMessage(ErrorCode.ADD_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/inventory/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(AddInventoryRequest request, Map<String, Object> model) {
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(1);
            Boolean isEdit = inventoryService.edit(request);
            return new ResultMsg(isEdit, isEdit ? i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS) : i18nUtil.getMessage(ErrorCode.EDIT_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    @RequestMapping(value = "/inventorylog/getlist", method = RequestMethod.GET)
    @ResponseBody
    public List<GetInventoryLogResponse> getList(@RequestParam(value = "materialId", required = true) String materialId, @RequestParam(value = "storageId", required = true) String storageId,
                                                 Map<String, Object> model) {
        List<GetInventoryLogResponse> response = null;
        try {
            response = inventoryService.getList(materialId, storageId);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }

    @RequestMapping(value = "/inventory/isenough", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg isEnough(GetInventoryIsEnoughRequest request, Map<String, Object> model) { // 库存是否足够
        try {
            Boolean query = inventoryService.isEnough(request);
            return new ResultMsg(query, query ? i18nUtil.getMessage(ErrorCode.QUERY_ERROR_NO_PARAM) : i18nUtil.getMessage(ErrorCode.EDIT_ERROR_NO_PARAM));
        } catch (Exception e) {
            return new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() }));
        }
    }

    // 仓库预警
    @RequestMapping(value = "/inventory/ismorethan", method = RequestMethod.POST)
    @ResponseBody
    public InventoryWarningMsg isMoreThan(@RequestBody List<MaterialCountsRequest> request) { // 库存是否足够
        InventoryWarningMsg inventoryWarningMsg = new InventoryWarningMsg();
        try {
            inventoryWarningMsg = inventoryService.isMoreThan(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inventoryWarningMsg;
    }
}
