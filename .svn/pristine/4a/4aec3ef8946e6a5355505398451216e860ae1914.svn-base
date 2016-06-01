package com.siping.system.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.smartone.common.OperationLog;
import com.siping.system.service.OperationLogRestService;

@Controller
public class OperationLogRestController extends StromaWebserviceController {
    @Autowired
    private OperationLogRestService operationLogRestService;

    @RequestMapping(value = "/operationlog/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<OperationLog> getList(@RequestBody OperationLog log) {
        return operationLogRestService.getList(log);

    }
    
    @RequestMapping(value = "/operationlog/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody OperationLog log) {
        return operationLogRestService.delete(log);

    }
}
