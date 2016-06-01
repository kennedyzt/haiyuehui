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
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.material.service.MaterialGroupRestService;
import com.siping.smartone.material.request.AddMaterialGroupRequest;
import com.siping.smartone.material.request.GetMaterialUsageRequest;
import com.siping.smartone.material.response.GetMaterialGroupResponse;

@Controller
public class MaterialGroupRestController extends StromaWebserviceController {
    @Autowired
    private MaterialGroupRestService materialGroupRestService;

    @RequestMapping(value = "/materialgroup/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddMaterialGroupRequest request) {
        return materialGroupRestService.add(request);
    }

    @RequestMapping(value = "/materialgroup/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddMaterialGroupRequest request) {
        return materialGroupRestService.edit(request);
    }

    @RequestMapping(value = "/materialgroup/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetMaterialGroupResponse get(@RequestBody GetMaterialUsageRequest request) {
        return materialGroupRestService.get(request);
    }

    @RequestMapping(value = "/materialgroup/getlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialGroupResponse> getList() {
        return materialGroupRestService.getList();
    }

    @RequestMapping(value = "/materialgroup/delete/{id}/{updateBy}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@PathVariable String id, @PathVariable String updateBy) {
        return materialGroupRestService.delete(id, updateBy);
    }
}
