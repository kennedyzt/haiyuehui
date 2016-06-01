package com.siping.intranet.crm.inventory.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.common.CommonBillsForm;
import com.siping.intranet.crm.inventory.service.InventoryShipmentsService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.inventory.response.GetInventoryShipmentsListResponse;
import com.siping.smartone.inventory.response.GetInventoryShipmentsResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class InventoryShipmentsController extends StromaController {
    @Autowired
    private InventoryShipmentsService inventoryShipmentsService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/inventoryshipments/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "shipmentsnumber", required = true) String shipmentsnumber, Map<String, Object> model) {
        try {
            GetInventoryShipmentsResponse response = inventoryShipmentsService.get(shipmentsnumber);
            model.put("inventoryShipments", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.DETAIL_INVENTORY_DELIVERY;
    }

    @RequestMapping(value = "/inventoryshipments/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                          Map<String, Object> model) {
        form.setIsDraft(false);
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_SHIPMENTS_SEARCH_CONDITION)))
                form = jsonConverter.fromString(CommonBillsForm.class, sessionContext.get(SessionConstants.INVENTORY_SHIPMENTS_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.INVENTORY_SHIPMENTS_SEARCH_CONDITION, jsonConverter.toString(form));
            PagingResponse<GetInventoryShipmentsListResponse> response = inventoryShipmentsService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, form);
            PageModel<GetInventoryShipmentsListResponse> pageModel = new PageModel<GetInventoryShipmentsListResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize,
                response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_INVENTORY_DELIVERY;
    }

    @RequestMapping(value = "/inventoryshipments/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        model.put("storages", storages);
        model.put("loginUserName", loginUserName);
        model.put("loginId", loginId);
        return PagePath.ADD_INVENTORY_DELIVERY;
    }

    private void getNecessaryData(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        model.put("storages", storages);
        model.put("loginUserName", loginUserName);
        model.put("loginId", loginId);
    }

    @RequestMapping(value = "/inventoryshipments/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "shipmentsNumber", required = true) String shipmentsnumber, Map<String, Object> model) {
        try {
            getNecessaryData(model);
            GetInventoryShipmentsResponse response = inventoryShipmentsService.get(shipmentsnumber);
            model.put("inventoryShipments", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_INVENTORY_DELIVERY;
    }

    @RequestMapping(value = "/inventoryshipments/print", method = RequestMethod.GET)
    public String getPrint(@RequestParam(value = "shipmentsnumber", required = true) String shipmentsnumber, Map<String, Object> model) {
        try {
            GetInventoryShipmentsResponse response = inventoryShipmentsService.get(shipmentsnumber);
            model.put("inventoryShipments", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.PRINT_INVENTORY_DELIVERY;
    }

    @RequestMapping(value = "/inventoryshipments/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getDraftList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                               Map<String, Object> model) {
        form.setIsDraft(true);
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_SHIPMENTS_SEARCH_CONDITION)))
                form = jsonConverter.fromString(CommonBillsForm.class, sessionContext.get(SessionConstants.INVENTORY_SHIPMENTS_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.INVENTORY_SHIPMENTS_SEARCH_CONDITION, jsonConverter.toString(form));
            PagingResponse<GetInventoryShipmentsListResponse> response = inventoryShipmentsService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, form);
            if (CollectionUtils.isNotEmpty(response.getRecords())) {
                PageModel<GetInventoryShipmentsListResponse> pageModel = new PageModel<GetInventoryShipmentsListResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize,
                    response.getTotalRecords(), response.getRecords());
                model.put("pageModel", pageModel);
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_DRAFT_INVENTORY_DELIVERY;
    }
}
