package com.siping.intranet.invoicing.sell.shipments.web;

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
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.intranet.invoicing.sell.shipments.service.SellShipmentsService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsListResponse;
import com.siping.smartone.invoicing.sell.shipments.response.GetSellShipmentsResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class SellShipmentsController extends StromaController {
    @Autowired
    private SellShipmentsService sellShipmentsService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/sellshipments/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "shipmentsnumber", required = true) String shipmentsnumber, Map<String, Object> model) {
        try {
            GetSellShipmentsResponse response = sellShipmentsService.get(shipmentsnumber);
            model.put("sellShipments", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.DETAIL_DELIVERY_SO;
    }

    @RequestMapping(value = "/sellshipments/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                          Map<String, Object> model) {
        form.setIsDraft(false);
        buildSellShipmentsResponse(pageNo, pageSize, form, model);
        return PagePath.ALL_DELIVERY_SO;
    }

    private void buildSellShipmentsResponse(Integer pageNo, Integer pageSize, CommonBillsForm form, Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.SELL_SHIPMENTS_SEARCH_CONDITION)))
                form = jsonConverter.fromString(CommonBillsForm.class, sessionContext.get(SessionConstants.SELL_SHIPMENTS_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.SELL_SHIPMENTS_SEARCH_CONDITION, jsonConverter.toString(form));
            PagingResponse<GetSellShipmentsListResponse> response = sellShipmentsService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, form);
            if (CollectionUtils.isNotEmpty(response.getRecords())) {
                PageModel<GetSellShipmentsListResponse> pageModel = new PageModel<GetSellShipmentsListResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize,
                    response.getTotalRecords(), response.getRecords());
                model.put("pageModel", pageModel);
            }
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
    }

    @RequestMapping(value = "/sellshipments/add", method = RequestMethod.GET)
    public String add(Map<String, Object> model) {
        getNecessaryData(model);
        return PagePath.ADD_DELIVERY_SO;
    }

    private void getNecessaryData(Map<String, Object> model) {
        List<StorageResponse> storages = storageService.getList();
        String loginUserName = sessionContext.get(SecuritySessionConstants.LOGGED_USER_NAME);
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        model.put("storages", storages);
        model.put("loginUserName", loginUserName);
        model.put("loginId", loginId);
    }

    @RequestMapping(value = "/sellshipments/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("shipmentsNumber") String shipmentsNumber, Map<String, Object> model) {
        getNecessaryData(model);
        GetSellShipmentsResponse response = sellShipmentsService.get(shipmentsNumber);
        model.put("sellShipments", response);
        return PagePath.EDIT_DELIVERY_SO;
    }

    @RequestMapping(value = "/sellshipments/getdraftlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getDraftList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                               Map<String, Object> model) {
        form.setIsDraft(true);
        buildSellShipmentsResponse(pageNo, pageSize, form, model);
        return PagePath.ALL_DRAFT_DELIVERY_SO;
    }

    @RequestMapping(value = "/sellshipments/print", method = RequestMethod.GET)
    public String getPrint(@RequestParam(value = "shipmentsnumber", required = true) String shipmentsnumber, Map<String, Object> model) {
        try {
            GetSellShipmentsResponse response = sellShipmentsService.get(shipmentsnumber);
            model.put("sellShipments", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.PRINT_DELIVERY_SO;
    }
}
