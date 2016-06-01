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

import com.siping.service.material.service.MaterialGroupRelationRestService;
import com.siping.smartone.material.request.AddMaterialGroupRelationRequest;
import com.siping.smartone.material.request.GetMaterialConditionRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.request.GetMaterialUsageRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;
import com.siping.smartone.material.response.GetMaterialResponse;
import com.siping.smartone.material.response.GetMaterialUsageResponse;

@Controller
public class MaterialGroupRelationRestController extends StromaWebserviceController {
    @Autowired
    private MaterialGroupRelationRestService materialGroupRelationRestService;

    @RequestMapping(value = "/materialgrouprelation/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddMaterialGroupRelationRequest request) {
        return materialGroupRelationRestService.add(request);
    }

    @RequestMapping(value = "/materialgrouprelation/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddMaterialGroupRelationRequest request) {
        return materialGroupRelationRestService.edit(request);
    }

    @RequestMapping(value = "/materialgrouprelation/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialGroupResponse> get(@RequestBody GetMaterialRequest request) {
        return materialGroupRelationRestService.get(request);
    }

    @RequestMapping(value = "/materialgrouprelation/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialResponse> getList(@RequestBody GetMaterialConditionRequest request) {
        return materialGroupRelationRestService.getList(request);
    }

    @RequestMapping(value = "/materialgrouprelation/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@PathVariable String id) {
        return materialGroupRelationRestService.delete(id);
    }
}
