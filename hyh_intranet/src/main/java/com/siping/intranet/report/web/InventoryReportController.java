package com.siping.intranet.report.web;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.material.service.MaterialService;
import com.siping.intranet.crm.material.service.MaterialTypeService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.intranet.report.service.InventoryReportService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.inventory.request.GetInventoryBalanceRequest;
import com.siping.smartone.inventory.request.GetInventoryBatchRequest;
import com.siping.smartone.inventory.request.GetInventoryStatusRequest;
import com.siping.smartone.inventory.response.GetInventoryBalanceDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBalanceResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryBatchResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusDetailResponse;
import com.siping.smartone.inventory.response.GetInventoryStatusResponse;
import com.siping.smartone.report.response.SingleMaterialCostResponse;

@Controller
@LoginRequired
public class InventoryReportController extends StromaController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private InventoryReportService inventoryReportService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private MaterialTypeService materialTypeService;
    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/inventorybalance/topage", method = { RequestMethod.GET })
    public String toBalancePage(Map<String, Object> model) {
        return PagePath.INVENTORY_BALANCE_LIST;// 跳转到库存余额页面
    }

    @RequestMapping(value = "/inventorybalance/toinoutpage", method = { RequestMethod.GET })
    public String toInOutPage(GetInventoryBalanceRequest request, Map<String, Object> model) {
        model.put("request", request);
        sessionContext.set(SessionConstants.INVENTORY_BALANCE_DETAIL_SEARCH_CONDITION, jsonConverter.toString(request));
        return PagePath.INVENTORY_BALANCE_DETAIL;// 跳转到库存余额页面
    }

    @RequestMapping(value = "/inventorybalance/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public PageModel<GetInventoryBalanceResponse> getList(@RequestBody GetInventoryBalanceRequest request) {
        PageModel<GetInventoryBalanceResponse> pageModel = new PageModel<GetInventoryBalanceResponse>();
        try {
            if (null == request && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_BALANCE_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetInventoryBalanceRequest.class, sessionContext.get(SessionConstants.INVENTORY_BALANCE_SEARCH_CONDITION));
            if (null != request)
                sessionContext.set(SessionConstants.INVENTORY_BALANCE_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<GetInventoryBalanceResponse> response = inventoryReportService.getList(request);
            pageModel = new PageModel<GetInventoryBalanceResponse>(request.getPageNo() == null ? 1 : request.getPageNo(), request.getPageSize() == null ? 10 : request.getPageSize(),
                response.getTotalRecords(), response.getRecords(), response.getStats());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

    @RequestMapping(value = "/inventorybalance/getDetaillist", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public PageModel<GetInventoryBalanceDetailResponse> getBalanceDetailList() {
        PageModel<GetInventoryBalanceDetailResponse> pageModel = new PageModel<GetInventoryBalanceDetailResponse>();
        try {
            GetInventoryBalanceRequest request = jsonConverter.fromString(GetInventoryBalanceRequest.class, sessionContext.get(SessionConstants.INVENTORY_BALANCE_DETAIL_SEARCH_CONDITION));
            PagingResponse<GetInventoryBalanceDetailResponse> response = inventoryReportService.getBalanceDetailList(request);
            if (request.getIsPaging()) {
                pageModel = new PageModel<GetInventoryBalanceDetailResponse>(request.getPageNo() == null ? 1 : request.getPageNo(), request.getPageSize() == null ? 10 : request.getPageSize(),
                    response.getTotalRecords(), response.getRecords(), response.getStats());
            } else {
                pageModel = new PageModel<GetInventoryBalanceDetailResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

    @RequestMapping(value = "/reportinventorystatus/getlist", method = { RequestMethod.GET, RequestMethod.POST })
    public String inventoryStatusList(GetInventoryStatusRequest request, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                      @RequestParam(value = "pageSize", required = false) Integer pageSize, Map<String, Object> model) {
        if (null != pageNo && !StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION)))
            request = jsonConverter.fromString(GetInventoryStatusRequest.class, sessionContext.get(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION));
        if (null == pageNo)
            sessionContext.set(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION, jsonConverter.toString(request));
        PagingResponse<GetInventoryStatusResponse> response = inventoryReportService.getStatusList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
        if (CollectionUtils.isNotEmpty(response.getRecords())) {
            PageModel<GetInventoryStatusResponse> pageModel = new PageModel<GetInventoryStatusResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
                response.getRecords());
            model.put("pageModel", pageModel);
            Double addInventoryAmount = 0D, addPromisedAmount = 0D, addOrderedAmount = 0D, addAvailableAmount = 0D;
            for (GetInventoryStatusResponse status : response.getRecords()) {
                addInventoryAmount += status.getInventoryAmount() == null ? 0.0d : status.getInventoryAmount();
                addPromisedAmount += status.getPromisedAmount() == null ? 0.0d : status.getPromisedAmount();
                addOrderedAmount += status.getOrderedAmount() == null ? 0.0d : status.getOrderedAmount();
                addAvailableAmount += status.getAvailableAmount() == null ? 0.0d : status.getAvailableAmount();
            }
            model.put("addInventoryAmount", addInventoryAmount);
            model.put("addPromisedAmount", addPromisedAmount);
            model.put("addOrderedAmount", addOrderedAmount);
            model.put("addAvailableAmount", addAvailableAmount);
        }
        model.put("original", request);
        model.put("storages", storageService.getList());
        return PagePath.INVENTORY_STATUS_LIST;
    }

    @RequestMapping(value = "/reportinventorystatus/detail", method = RequestMethod.GET)
    public String inventoryStatusDetail(@RequestParam(value = "materialno", required = true) String materialno, Map<String, Object> model) {
        model.put("storages", storageService.getList());
        GetInventoryStatusRequest request = jsonConverter.fromString(GetInventoryStatusRequest.class, sessionContext.get(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION));
        // model.put("request", request);
        request.setMaterialNo(materialno);
        request.setMaterialName(materialService.get(null, materialno, null, null).getMaterialName());
        List<GetInventoryStatusDetailResponse> res = inventoryReportService.getStatusDetailList(request);
        Double addPromisedAmount = 0D, addOrderedAmount = 0D;
        if (CollectionUtils.isNotEmpty(res)) {
            for (GetInventoryStatusDetailResponse r : res) {
                addPromisedAmount += r.getPromisedAmount() == null ? 0.0d : r.getPromisedAmount();
                addOrderedAmount += r.getOrderedAmount() == null ? 0.0d : r.getOrderedAmount();
            }
            model.put("addPromisedAmount", addPromisedAmount);
            model.put("addOrderedAmount", addOrderedAmount);
        }
        model.put("statusList", res);
        model.put("original", request);
        return PagePath.INVENTORY_STATUS_DETAIL;
    }

    @RequestMapping(value = "/reportinventorybatch/getlist", method = { RequestMethod.GET, RequestMethod.POST })
    public String inventoryBatchList(GetInventoryBatchRequest request, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                     @RequestParam(value = "pageSize", required = false) Integer pageSize, Map<String, Object> model) {
        if (null != pageNo && !StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION)))
            request = jsonConverter.fromString(GetInventoryBatchRequest.class, sessionContext.get(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION));
        if (null == pageNo)
            sessionContext.set(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION, jsonConverter.toString(request));
        PagingResponse<GetInventoryBatchResponse> response = inventoryReportService.getBatchList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
        model.put("storages", storageService.getList());
        if (CollectionUtils.isNotEmpty(response.getRecords())) {
            PageModel<GetInventoryBatchResponse> pageModel = new PageModel<GetInventoryBatchResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
                response.getRecords());
            Double addCounts = 0d;
            for (GetInventoryBatchResponse batch : response.getRecords()) {
                addCounts += batch.getCounts() == null ? 0d : batch.getCounts();
            }
            model.put("addCounts", addCounts);
            model.put("pageModel", pageModel);
        }

        model.put("original", request);
        return PagePath.INVENTORY_BATCH_LIST;
    }

    @RequestMapping(value = "/reportinventorybatch/detail", method = RequestMethod.GET)
    public String inventoryBatchDetail(@RequestParam(value = "materialno", required = true) String materialno, Map<String, Object> model) {
        GetInventoryBatchRequest request = jsonConverter.fromString(GetInventoryBatchRequest.class, sessionContext.get(SessionConstants.INVENTORY_STATUS_SEARCH_CONDITION));
        request.setMaterialNo(materialno);
        request.setMaterialName(materialService.get(null, materialno, null, null).getMaterialName());
        List<GetInventoryBatchDetailResponse> response = inventoryReportService.getBatchDetailList(request);
        if (CollectionUtils.isNotEmpty(response)) {
            Double addCount = 0D;
            for (GetInventoryBatchDetailResponse detail : response) {
                addCount += detail.getCounts() == null ? 0D : detail.getCounts();
            }
            model.put("detailList", response);
            model.put("addCount", addCount);
        }
        model.put("original", request);
        model.put("storages", storageService.getList());
        return PagePath.INVENTORY_BATCH_DETAIL;
    }

    // 通过商品id获取成本
    @RequestMapping(value = "/inventorybalance/getcost", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public List<SingleMaterialCostResponse> getcost(@RequestParam(value = "materialid", required = true) String materialid) {
        List<SingleMaterialCostResponse> costs = null;
        try {
            costs = inventoryReportService.getCost(materialid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return costs;
    }
}
