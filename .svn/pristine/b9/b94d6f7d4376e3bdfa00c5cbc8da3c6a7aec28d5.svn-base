package com.siping.wms.report.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.smartone.report.CommonReportRequest;
import com.siping.smartone.report.response.QueryGoodsTransportResponse;
import com.siping.wms.report.service.QueryGoodsTransportRestService;

@Controller
public class QueryGoodsTransportRestController extends StromaWebserviceController {

    @Autowired
    private QueryGoodsTransportRestService queryGoodsTransportRestService;

    @RequestMapping(value = "/report/querygoodstransport/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<QueryGoodsTransportResponse> getList(@RequestBody CommonReportRequest request) {
        return queryGoodsTransportRestService.getList(request);
    }
}
