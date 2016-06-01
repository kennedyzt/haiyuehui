package com.siping.service.storage.ws;

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

import com.siping.service.storage.service.StorageAreaRestService;
import com.siping.service.storage.service.StorageLocationRestService;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.GetStorageLocationListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.storage.response.StorageResponse;
import com.siping.smartone.wms.response.StorageLocationResponse;

@Controller
public class StorageLocationRestController extends StromaWebserviceController {

    @Autowired
    private StorageLocationRestService storageLocationRestService;
    @Autowired
    private StorageAreaRestService storageAreaRestService;

    @RequestMapping(value = "/storagelocation/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addStorageLocation(@RequestBody AddStorageLocationRequest request) {
        request.setLocationNo(request.getLocationNo());//编号加上库区编号(数据库不存，是在显示的时候拼接)storageAreaRestService.get(request.getStorageAreaId().toString()).getAreaNo()+"-"+
        return storageLocationRestService.addStorageLocation(request);
    }

    @RequestMapping(value = "/storagelocation/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean editStorageLocation(@RequestBody AddStorageLocationRequest request) {
        return storageLocationRestService.editStorageLocation(request);
    }
    
    @RequestMapping(value = "/storagelocation/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StorageLocationResponese getStorageById(@PathVariable String id) {
        return storageLocationRestService.get(id);
    }

    @RequestMapping(value = "/storagelocation/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteStorage(@RequestBody DeleteStorageRequest request) {
        return storageLocationRestService.delete(request);
    }

    @RequestMapping(value = "/storagelocation/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<StorageLocationResponese> getList(@RequestBody GetStorageLocationListRequest request) {
        return storageLocationRestService.getList(request);
    }

}
