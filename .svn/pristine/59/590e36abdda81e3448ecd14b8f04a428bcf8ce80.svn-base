package com.siping.wms.readyreceipt.web;

import java.util.List;
import java.util.Map;

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
import com.siping.intranet.common.CommonBillsForm;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.wms.request.AddPackRequest;
import com.siping.smartone.wms.request.ReadyShipmentsRequest;
import com.siping.smartone.wms.response.OrderPickResponse;
import com.siping.smartone.wms.response.ReadyShipmentsResponse;
import com.siping.wms.readyreceipt.service.ReadyShipmentsService;

@Controller
@LoginRequired
public class ReadyShipmentsController extends StromaController {
    @Autowired
    private ReadyShipmentsService readyShipmentsService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/readyshipments/orderAudit", method = RequestMethod.GET) // 复核跳转
    public String orderAudit() {
        return PagePath.ORDER_AUDIT;
    }

    @RequestMapping(value = "/readyshipments/get", method = RequestMethod.GET)
    public String get(@RequestParam(value = "ordernumber", required = true) String orderNumber, Map<String, Object> model) {
        try {
            ReadyShipmentsResponse response = readyShipmentsService.get(orderNumber);
            model.put("readyShipments", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.DETAIL_RS;
    }

    @RequestMapping(value = "/readyshipments/getAllTempAuditOrder", method = RequestMethod.GET)
    public String getAllTempAuditOrder(Map<String, Object> model) {
        List<ReadyShipmentsResponse> responses = readyShipmentsService.getAllTempAuditOrder();
        model.put("orders", responses);
        return PagePath.LAYER_ALL_TEMP_ORDER_AUDIT;
    }

    @RequestMapping(value = "/readyshipments/getbyaudit", method = RequestMethod.GET)
    @ResponseBody
    public ReadyShipmentsResponse getByAudit(@RequestParam(value = "ordernumber", required = true) String orderNumber) {
        ReadyShipmentsResponse response = null;
        try {
            response = readyShipmentsService.get(orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/readyshipments/getbyTempAudit", method = RequestMethod.GET)
    @ResponseBody
    public ReadyShipmentsResponse getbyTempAudit(@RequestParam(value = "ordernumber", required = true) String orderNumber) {
        ReadyShipmentsResponse response = null;
        try {
            response = readyShipmentsService.getbyTempAudit(orderNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/readyshipments/confirmAudit", method = RequestMethod.GET) // 正式提交复核
    @ResponseBody
    public ResultMsg confirmAuditByOrderNumber(@RequestParam(value = "ordernumber", required = true) String orderNumber) {
        ResultMsg resultMsg = null;
        try {
            if (readyShipmentsService.confirmAuditByOrderNumber(orderNumber)) {
                resultMsg = new ResultMsg(Boolean.TRUE, "复核成功");
            } else {
                resultMsg = new ResultMsg(Boolean.FALSE, "复核失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage("复核失败", new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/readyshipments/savePack", method = RequestMethod.POST) // 保存包装单
    @ResponseBody
    public ResultMsg savePack(@RequestBody AddPackRequest request) {
        ResultMsg resultMsg = null;
        request.setLoginId(sessionContext.get(SecuritySessionConstants.LOGGED_ID).toString());
        try {
            if (readyShipmentsService.savePack(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.PACK_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/readyshipments/tempSaveAudit", method = RequestMethod.POST) // 暂存复核单
    @ResponseBody
    public ResultMsg tempSaveAudit(@RequestBody ReadyShipmentsRequest request) {
        ResultMsg resultMsg = null;
        try {
            if (readyShipmentsService.tempSaveAudit(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/readyshipments/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                          Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.SELL_ORDER_SEARCH_CONDITION)))
                form = jsonConverter.fromString(CommonBillsForm.class, sessionContext.get(SessionConstants.SELL_ORDER_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.SELL_ORDER_SEARCH_CONDITION, jsonConverter.toString(form));
            if (null == form.getStatus())
                form.setStatus(0);
            PagingResponse<ReadyShipmentsResponse> response = readyShipmentsService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, form);
            PageModel<ReadyShipmentsResponse> pageModel = new PageModel<ReadyShipmentsResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
                response.getRecords());
            model.put("pageModel", pageModel);
            model.put("form", form);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_READY_SHIPMENTS;
    }

    // 预览拣货单
    @RequestMapping(value = "/readyshipments/preview", method = RequestMethod.GET)
    public String get(@RequestParam(value = "orderNumbers", required = true) List<String> orderNumbers, Map<String, Object> model) {
        OrderPickResponse response = readyShipmentsService.get(orderNumbers);
        model.put("orderPicks", response);
        return PagePath.ORDER_PICK_PREVIEW;
    }
    
    @RequestMapping(value = "/readyshipments/printems", method = { RequestMethod.POST, RequestMethod.GET })
    public String printEMS(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsForm form,
                          Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.SELL_ORDER_SEARCH_CONDITION)))
                form = jsonConverter.fromString(CommonBillsForm.class, sessionContext.get(SessionConstants.SELL_ORDER_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.SELL_ORDER_SEARCH_CONDITION, jsonConverter.toString(form));
            if (null == form.getStatus())
                form.setStatus(0);
            PagingResponse<ReadyShipmentsResponse> response = readyShipmentsService.printEMS(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, form);
            PageModel<ReadyShipmentsResponse> pageModel = new PageModel<ReadyShipmentsResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, response.getTotalRecords(),
                response.getRecords());
            model.put("pageModel", pageModel);
            model.put("form", form);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_PRINT_EMS;
    }
}
