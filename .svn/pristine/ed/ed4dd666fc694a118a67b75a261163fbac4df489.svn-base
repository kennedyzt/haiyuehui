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

import com.siping.service.storage.service.StorageRestService;
import com.siping.smartone.storage.request.DeleteStorageRequest;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
public class StorageRestController extends StromaWebserviceController {

    @Autowired
    private StorageRestService storageRestService;

    @RequestMapping(value = "/storage/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addStorage(@RequestBody StorageRequest request) {
        return storageRestService.addStorage(request);
    }

    @RequestMapping(value = "/storage/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateStorage(@RequestBody StorageRequest request) {
        return storageRestService.edit(request);
    }

    @RequestMapping(value = "/storage/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public StorageResponse getStorageById(@PathVariable String id) {
        return storageRestService.get(id);
    }

    @RequestMapping(value = "/storage/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteStorage(@RequestBody DeleteStorageRequest request) {
        return storageRestService.delete(request);
    }

    @RequestMapping(value = "/storage/getlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StorageResponse> getList() {
        return storageRestService.getList();
    }

    @RequestMapping(value = "/storage/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<StorageResponse> getList(@RequestBody GetStorageListRequest request) {
        return storageRestService.getList(request);
    }

    @RequestMapping(value = "/storage/getlist/{key}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StorageResponse> getList(@PathVariable String key) {
        return storageRestService.getList(key);
    }

    @RequestMapping(value = "/storage/getlistbyislocation/{isEnableLocation}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<StorageResponse> getList(@PathVariable Boolean isEnableLocation) {
        return storageRestService.getList(isEnableLocation);
    }
}
