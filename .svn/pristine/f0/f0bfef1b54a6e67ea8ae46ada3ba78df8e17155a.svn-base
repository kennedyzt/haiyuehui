package com.siping.service.material.ws;

import java.util.List;

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

import com.siping.service.material.service.MaterialUnitRestService;
import com.siping.smartone.material.request.AddMaterialUnitRequest;
import com.siping.smartone.material.request.DeleteMaterialRequest;
import com.siping.smartone.material.request.DeleteMaterialUnitRequest;
import com.siping.smartone.material.request.GetMaterialUnitListRequest;
import com.siping.smartone.material.request.GetMaterialUnitRequest;
import com.siping.smartone.material.response.GetMaterialUnitResponse;

@Controller
public class MaterialUnitRestController extends StromaWebserviceController {
    @Autowired
    private MaterialUnitRestService materialUnitRestService;

    @RequestMapping(value = "/materialunit/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddMaterialUnitRequest request) {
        return materialUnitRestService.add(request);
    }

    @RequestMapping(value = "/materialunit/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddMaterialUnitRequest request) {
        return materialUnitRestService.edit(request);
    }

    @RequestMapping(value = "/materialunit/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetMaterialUnitResponse get(@RequestBody GetMaterialUnitRequest request) {
        return materialUnitRestService.get(request);
    }

    @RequestMapping(value = "/materialunit/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialUnitResponse> getList(@RequestBody GetMaterialUnitListRequest request) {
        return materialUnitRestService.getList(request);
    }
    
    @RequestMapping(value = "/materialunit/getlistwithcondition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialUnitResponse> getListWithCondition(@RequestBody GetMaterialUnitListRequest request) {
        return materialUnitRestService.getListWithCondition(request);
    }


    @RequestMapping(value = "/materialunit/getalllist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialUnitResponse> getList() {
        return materialUnitRestService.getList();
    }
    
    @RequestMapping(value = "/materialunit/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteMaterialUnitRequest request) {
        return materialUnitRestService.delete(request);
    }

}
