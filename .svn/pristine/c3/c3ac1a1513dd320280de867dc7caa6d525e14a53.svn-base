package com.siping.service.postperiod.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.postperiod.service.PostPeriodRestService;
import com.siping.smartone.postperiod.request.AddPostPeriodRequest;
import com.siping.smartone.postperiod.request.DeletePostPeriodRequest;
import com.siping.smartone.postperiod.request.GetPostPeriodListRequest;
import com.siping.smartone.postperiod.response.GetPostPeriodResponse;

@Controller
public class PostPeriodRestController extends StromaWebserviceController {
    @Autowired
    private PostPeriodRestService postPeriodRestService;

    @RequestMapping(value = "/postperiod/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddPostPeriodRequest request) {
        return postPeriodRestService.add(request);
    }

    @RequestMapping(value = "/postperiod/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddPostPeriodRequest request) {
        return postPeriodRestService.edit(request);
    }

    @RequestMapping(value = "/postperiod/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetPostPeriodResponse get(@PathVariable String id) {
        return postPeriodRestService.get(id);
    }

    @RequestMapping(value = "/postperiod/getMax", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetPostPeriodResponse getMax() {
        return postPeriodRestService.getMax();
    }
    
    @RequestMapping(value = "/postperiod/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetPostPeriodResponse> getList(@RequestBody GetPostPeriodListRequest request) {
        return postPeriodRestService.getList(request);
    }

    @RequestMapping(value = "/postperiod/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeletePostPeriodRequest request) {
        return postPeriodRestService.delete(request);
    }
}
