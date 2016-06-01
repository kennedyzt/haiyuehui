package com.siping.wms.readyreceipt.web;

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
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.intranet.crm.usr.service.UsrService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.login.request.GetUserListForm;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.storage.response.StorageResponse;
import com.siping.smartone.wms.response.ReadyReceiptResponse;
import com.siping.wms.readyreceipt.service.ReadyReceiptOrderService;

@Controller
@LoginRequired
public class ReadyReceiptOrderController extends StromaController {

    @Autowired
    ReadyReceiptOrderService readyReceiptOrderService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    PurchaseApplyOrderService paoService;
    @Autowired
    private UsrService usrService;
    @Autowired
    private StorageService storageService;

    @RequestMapping(value = "/readyreceipt/get", method = RequestMethod.GET)
    public String get(@RequestParam("orderNumber") String orderNumber, Map<String, Object> model) {
        try {
            ReadyReceiptResponse response = readyReceiptOrderService.get(orderNumber);
            model.put("readyReceipt", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.DETAIL_READY_RECEIPT;
    }

    @RequestMapping(value = "/readyreceipt/getlist", method = { RequestMethod.POST, RequestMethod.GET })
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
            PagingResponse<ReadyReceiptResponse> response = new PagingResponse<ReadyReceiptResponse>();
            response = readyReceiptOrderService.getList(request);
            PageModel<ReadyReceiptResponse> pageModel = new PageModel<ReadyReceiptResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("form", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_READY_RECEIPT;
    }

    // 预收货单专用
    @RequestMapping(value = "/readyreceipt/openconsigneewin", method = { RequestMethod.POST, RequestMethod.GET })
    public String openConsigneeWin(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo, GetUserListForm request,
                                   Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetUserListForm.class, sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<UserInfoResponse> pagingResponse = new PagingResponse<UserInfoResponse>();
            pagingResponse = usrService.getAllUsers(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, request);
            PageModel<UserInfoResponse> pageModel = new PageModel<UserInfoResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            List<StorageResponse> storages = storageService.getList(false);
            model.put("storages", storages);
            model.put("pageModel", pageModel);
            model.put("form", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.OPEN_WIN_CONSIGNEE;
    }
}
