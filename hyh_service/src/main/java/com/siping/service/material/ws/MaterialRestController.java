package com.siping.service.material.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.material.service.MaterialRestService;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfo;
import com.siping.smartone.inventory.response.GetInventoryCheckWithMaterialAndLocationInfoRequest;
import com.siping.smartone.material.request.AddMaterialRequest;
import com.siping.smartone.material.request.DeleteMaterialRequest;
import com.siping.smartone.material.request.GetMaterialBatchRequest;
import com.siping.smartone.material.request.GetMaterialListByStorageRequest;
import com.siping.smartone.material.request.GetMaterialListRequest;
import com.siping.smartone.material.request.GetMaterialRequest;
import com.siping.smartone.material.request.GetOpeanWinMaterialRequest;
import com.siping.smartone.material.request.MaterialExportParamRequest;
import com.siping.smartone.material.response.GetMaterialBatchResponse;
import com.siping.smartone.material.response.GetMaterialResponse;

@Controller
public class MaterialRestController extends StromaWebserviceController {
    @Autowired
    private MaterialRestService materialRestService;

    @RequestMapping(value = "/material/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddMaterialRequest request) {
        return materialRestService.add(request);
    }

    @RequestMapping(value = "/material/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody AddMaterialRequest request) {
        return materialRestService.edit(request);
    }

    @RequestMapping(value = "/material/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetMaterialResponse get(@RequestBody GetMaterialRequest request) {
        return materialRestService.get(request);
    }
    
    @RequestMapping(value = "/material/getmaterialbatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetMaterialBatchResponse getMaterialBatch(@RequestBody GetMaterialBatchRequest request) {
        return materialRestService.getMaterialBatch(request);
    }    

    @RequestMapping(value = "/material/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialResponse> getList(@RequestBody GetMaterialListRequest request) {
        return materialRestService.getList(request);
    }

    @RequestMapping(value = "/material/supplier/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialResponse> getListForSupplier(@RequestBody GetMaterialListRequest request) {
        return materialRestService.getListForSupplier(request);
    }

    @RequestMapping(value = "/material/export", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialResponse> export(@RequestBody MaterialExportParamRequest request) {
        return materialRestService.export(request);
    }

    @RequestMapping(value = "/material/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@RequestBody DeleteMaterialRequest request) {
        return materialRestService.delete(request);
    }

    @RequestMapping(value = "/material/getlistbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialResponse> getList(@RequestBody GetMaterialRequest request) {
        return materialRestService.getList(request);
    }

    @RequestMapping(value = "/material/getlist", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetMaterialResponse> getList(@RequestBody GetMaterialListByStorageRequest request) {
        return materialRestService.getList(request);
    }
    
    @RequestMapping(value = "/material/getopenwinlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GetMaterialResponse> getOpenWinList(@RequestBody GetOpeanWinMaterialRequest request) {
        return materialRestService.getOpenWinList(request);
    }
    
    @RequestMapping(value = "/material/getInventoryCheckInfoWithStorageIdWithStorageArea", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GetInventoryCheckWithMaterialAndLocationInfo> getInventoryCheckInfoWithStorageIdWithStorageArea(@RequestBody GetInventoryCheckWithMaterialAndLocationInfoRequest request) {
        return materialRestService.getInventoryCheckInfoWithStorageIdWithStorageArea(request);
    }
    

}
