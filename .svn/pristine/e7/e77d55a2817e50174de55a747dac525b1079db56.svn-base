package com.siping.wms.readyreceipt.web;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.siping.intranet.crm.purchase.service.PurchaseApplyOrderService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.wms.readyreceipt.service.OrderPickService;
import com.siping.wms.readyreceipt.service.ReadyShipmentsService;

@Controller
@LoginRequired
public class OrderPickController extends StromaController {

    @Autowired
    OrderPickService orderPickService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    PurchaseApplyOrderService paoService;
    @Autowired
    private ReadyShipmentsService readyShipmentsService;

    @RequestMapping(value = "/orderpick/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, CommonBillsRequest request,
                          Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.PO_SEARCH_CONDITION)))
                request = jsonConverter.fromString(CommonBillsRequest.class, sessionContext.get(SessionConstants.PO_SEARCH_CONDITION));
            else {
                sessionContext.set(SessionConstants.PO_SEARCH_CONDITION, jsonConverter.toString(request));
                pageNo = 1;
                pageSize = 10;
            }
            request.setPageNo(pageNo);
            request.setPageSize(pageSize);
            if (null == request.getIsDraft() || !request.getIsDraft()) {
                request.setIsDraft(false);
                model.put("isDraft", false);
            } else {
                model.put("isDraft", true);
            }
            if (null == request.getStatus())
                request.setStatus(0);
            PagingResponse<OrderPickResponse> response = new PagingResponse<OrderPickResponse>();
            response = orderPickService.getList(request);
            PageModel<OrderPickResponse> pageModel = new PageModel<OrderPickResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("form", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_ORDER_PICK;
    }

    @RequestMapping(value = "/orderpick/get", method = RequestMethod.GET)
    public String get(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            OrderPickResponse response = orderPickService.get(orderNumber);

            String fromBillsNoStr = response.getFromBillsNo();
            String[] fromBillsNos = fromBillsNoStr.replace("[", "").replace("]", "").split(",");
            List<ReadyShipmentsResponse> readyShipmentsResponses = new LinkedList<>();
            for (String fromBillsNo : fromBillsNos) {
                ReadyShipmentsResponse readyShipmentsResponse = readyShipmentsService.get(fromBillsNo.trim());
                readyShipmentsResponses.add(readyShipmentsResponse);
            }
            model.put("orderPick", response);
            model.put("readyShipmentsResponses", readyShipmentsResponses);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.DETAIL_ORDER_PICK;
    }
}
