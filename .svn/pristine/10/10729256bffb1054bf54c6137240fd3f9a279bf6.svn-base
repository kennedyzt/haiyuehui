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

import com.siping.service.material.service.MaterialTypeRestService;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.material.request.AddMaterialTypeRequest;
import com.siping.smartone.material.request.DeleteMaterialTypeRequest;
import com.siping.smartone.material.request.GetMaterialTypeListRequest;
import com.siping.smartone.material.request.GetMaterialTypeRequest;
import com.siping.smartone.material.response.GetMaterialTypeResponse;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
public class MateriaTypeRestController extends StromaWebserviceController {
    @Autowired
    private MaterialTypeRestService materialTypeRestService;

    @RequestMapping(value = "/materialtype/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddMaterialTypeRequest request) {
        return materialTypeRestService.add(request);
    }

    @RequestMapping(value = "/materialtype/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddMaterialTypeRequest request) {
        return materialTypeRestService.edit(request);
    }

    @RequestMapping(value = "/materialtype/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetMaterialTypeResponse get(@RequestBody GetMaterialTypeRequest request) {
        return materialTypeRestService.get(request);
    }

    @RequestMapping(value = "/materialtype/getlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialTypeResponse> getList() {
        return materialTypeRestService.getList();
    }
    
    @RequestMapping(value = "/materialtype/getlistforlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialTypeResponse> getList(@RequestBody GetMaterialTypeListRequest request) {
        return materialTypeRestService.getAllMaterialType(request);
    }
    
//    @RequestMapping(value = "/materialtype/delete/{id}/{updateBy}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public Boolean delete(@PathVariable String id, @PathVariable String updateBy) {
//        return materialTypeRestService.delete(id, updateBy);
//    }
    
    @RequestMapping(value = "/materialtype/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteMaterialTypeRequest request) {
        return materialTypeRestService.delete(request);
    }

    @RequestMapping(value = "/materialtype/getchild/{parentId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialTypeResponse> getChild(@PathVariable String parentId) {
        return materialTypeRestService.getChild(parentId);
    }


    @RequestMapping(value = "/materialtype/getallbusinesspartner", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialTypeResponse> getAllMaterialType(@RequestBody CommonBillsRequest request) {
        return materialTypeRestService.getAllMaterialType(request);
    }
    
    @RequestMapping(value = "/materialtype/geteditlist/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialTypeResponse> getStorageById(@PathVariable String id) {
        return materialTypeRestService.getList(id);
    }

    @RequestMapping(value = "/materialtype/getlistbyparentid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialTypeResponse> getListByParentId(@RequestBody GetMaterialTypeListRequest request) {
        return materialTypeRestService.getListByParentId(request);
    }
}
