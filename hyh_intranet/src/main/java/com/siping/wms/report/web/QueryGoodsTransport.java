package com.siping.wms.report.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;

import com.siping.smartone.common.PagePath;
import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.response.QueryGoodsTransportResponse;
import com.siping.wms.report.service.QueryGoodsTransportService;

@Controller
@LoginRequired
class QueryGoodsTransport extends StromaController {
    @Autowired
    QueryGoodsTransportService queryGoodsTransportService;

    @RequestMapping(value = "report/querygoodstransport", method = RequestMethod.GET)
    public String supplierPurchase(Map<String, Object> model) {
        return PagePath.QUERY_GOODS_TRANSPORT;
    }

    @RequestMapping(value = "report/querygoodstransport/getlist", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<QueryGoodsTransportResponse> getList(@RequestBody CommonReportRequest request, Map<String, Object> model) {
        PagingResponse<QueryGoodsTransportResponse> response = new PagingResponse<QueryGoodsTransportResponse>();
        PageModel<QueryGoodsTransportResponse> pageModel = null;
        try {
            response = queryGoodsTransportService.getList(request);
            if (request.getIsPaging() == true) {
                pageModel = new PageModel<QueryGoodsTransportResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords());
            } else {
                pageModel = new PageModel<QueryGoodsTransportResponse>(1, 100, response.getTotalRecords(), response.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }

}
