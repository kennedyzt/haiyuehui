package com.siping.intranet.report.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.report.service.SellReportService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.report.request.CustomerSaleRequest;
import com.siping.smartone.report.request.GetSalesRrequest;
import com.siping.smartone.report.response.CustomerSaleResponse;
import com.siping.smartone.report.response.GetSalesReportWithExtraResponse;
import com.siping.smartone.report.response.GetSalesResponse;

@Controller
@LoginRequired
public class SellReportController extends StromaController {
    @Autowired
    private SellReportService sellReportService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;

    // 销售单量统计
    @RequestMapping(value = "/sellreport/tosellordercountpage", method = { RequestMethod.GET })
    public String toSellOrderCountPage(Map<String, Object> model) {
        return PagePath.SELL_ORDER_COUNT_REPORT;// 跳转到销售单量统计页面
    }

    // 销售日报，月报，季报，年报
    @RequestMapping(value = "/sellreport/tosaleroompage", method = { RequestMethod.GET })
    public String toSaleroomPage(Map<String, Object> model) {
        return PagePath.SALEROOM_REPORT;// 跳转到销售日报，月报，季报，年报
    }

    // 销售单量
    @RequestMapping(value = "/sellreport/getsellordercount", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public GetSalesResponse getSellOrderCount(@RequestBody GetSalesRrequest request) {
        GetSalesResponse response = null;
        try {
            if (null == request && StringUtils.hasText(sessionContext.get(SessionConstants.SELL_ORDER_COUNT_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetSalesRrequest.class, sessionContext.get(SessionConstants.SELL_ORDER_COUNT_SEARCH_CONDITION));
            if (null != request)
                sessionContext.set(SessionConstants.SELL_ORDER_COUNT_SEARCH_CONDITION, jsonConverter.toString(request));
            response = sellReportService.getSellOrderCount(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    // 销售额
    @RequestMapping(value = "/sellreport/getsaleroom", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public GetSalesResponse getSaleroom(@RequestBody GetSalesRrequest request) {
        GetSalesResponse response = null;
        try {
            if (null == request && StringUtils.hasText(sessionContext.get(SessionConstants.SALEROOM_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetSalesRrequest.class, sessionContext.get(SessionConstants.SALEROOM_SEARCH_CONDITION));
            if (null != request)
                sessionContext.set(SessionConstants.SALEROOM_SEARCH_CONDITION, jsonConverter.toString(request));
            response = sellReportService.getSaleroom(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/sellreport/getsaleroomwithextra", method = { RequestMethod.POST, RequestMethod.GET })
    @ResponseBody
    public GetSalesReportWithExtraResponse getSaleroomWithExtra(@RequestBody GetSalesRrequest request) {
        GetSalesReportWithExtraResponse response = null;
        try {
            if (null == request && StringUtils.hasText(sessionContext.get(SessionConstants.SALEROOM_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetSalesRrequest.class, sessionContext.get(SessionConstants.SALEROOM_SEARCH_CONDITION));
            if (null != request)
                sessionContext.set(SessionConstants.SALEROOM_SEARCH_CONDITION, jsonConverter.toString(request));
            response = sellReportService.getSaleroomWithExtra(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
    
    @RequestMapping(value = "/report/customersale", method = RequestMethod.GET)
    public String customerSale() {
        return PagePath.CUSTOMER_SALE;
    }

    @RequestMapping(value = "/report/customersale/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<CustomerSaleResponse> getCustomerSaleData(@RequestBody CustomerSaleRequest request) {
        PagingResponse<CustomerSaleResponse> response = new PagingResponse<CustomerSaleResponse>();
        response = sellReportService.getCustomerSaleData(request);
        PageModel<CustomerSaleResponse> pageModel = null;
        if (request.getIsPaging() == true) {
            pageModel = new PageModel<CustomerSaleResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords(), response.getStats());
        } else {
            pageModel = new PageModel<CustomerSaleResponse>(1, 100, response.getTotalRecords(), response.getRecords(), response.getStats());
        }
        return pageModel;
    }
}
