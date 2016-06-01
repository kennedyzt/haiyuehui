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
import com.siping.smartone.storage.request.AddStorageAreaRequest;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageAreaResponese;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
public class StorageAreaRestController extends StromaWebserviceController {

    @Autowired
    private StorageAreaRestService storageAreaRestService;

    @RequestMapping(value = "/storagearea/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addStorageLocation(@RequestBody AddStorageAreaRequest request) {
        return storageAreaRestService.addStorageArea(request);
    }

    @RequestMapping(value = "/storagearea/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean editStorageLocation(@RequestBody AddStorageAreaRequest request) {
        return storageAreaRestService.editStorageArea(request);
    }
    
    @RequestMapping(value = "/storagearea/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StorageAreaResponese getStorageById(@PathVariable String id) {
        return storageAreaRestService.get(id);
    }

    @RequestMapping(value = "/storagearea/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteStorage(@RequestBody DeleteStorageRequest request) {
        return storageAreaRestService.delete(request);
    }

    @RequestMapping(value = "/storagearea/getlist/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StorageAreaResponese> getList(@PathVariable String key) {
        return storageAreaRestService.getList(key);
    }
}
