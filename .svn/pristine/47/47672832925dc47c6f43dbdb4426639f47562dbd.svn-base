package com.siping.intranet.crm.storage.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SecureSessionContext;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.storage.service.StorageAreaService;
import com.siping.intranet.crm.storage.service.StorageLocationService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.request.GetStorageLocationListRequest;
import com.siping.smartone.storage.response.StorageAreaResponese;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class StorageLocationController extends StromaController {

    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SecureSessionContext secureSessionContxet;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private StorageLocationService storagelocationService;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StorageService storageService;
    @Autowired
    private StorageAreaService storageAreaService;

    @RequestMapping(value = "/storagelocation/add", method = RequestMethod.GET)
    public String addStorageLocation(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            StorageResponse response = storageService.get(id);
            List<StorageAreaResponese> storageAreaResponeses = storageAreaService.getList(id);
            model.put("s", response);
            model.put("storageareas", storageAreaResponeses);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ADD_STORAGE_LOCATION;
    }
    
    @RequestMapping(value = "storagelocation/getlist", method = {RequestMethod.GET,RequestMethod.POST})
    public String allStorageLocation(@RequestParam(value = "id",required = false) String storageId,@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize,GetStorageLocationListRequest request,Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.STORAGE_LOCATION_SEARCH_CONDITION_LIST)))
                request = jsonConverter.fromString(GetStorageLocationListRequest.class, sessionContext.get(SessionConstants.STORAGE_LOCATION_SEARCH_CONDITION_LIST));
            if (null == pageNo)
                sessionContext.set(SessionConstants.STORAGE_LOCATION_SEARCH_CONDITION_LIST, jsonConverter.toString(request));
            PagingResponse<StorageLocationResponese> responses = storagelocationService.getList(pageNo,pageSize,storageId, request);
            pageNo = pageNo==null?1:pageNo;
            pageSize = pageSize==null?10:pageSize;
            PageModel<StorageLocationResponese> pageModel = new PageModel<StorageLocationResponese>(pageNo, pageSize, responses.getTotalRecords(), responses.getRecords());
            model.put("storageLocation", pageModel);
            model.put("storageId", request.getId());
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_STORAGE_LOCATION;
    }
    
    @RequestMapping(value = "/storagelocation/edit", method = RequestMethod.GET)
    public String editStorageView(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            StorageLocationResponese responese = storagelocationService.get(id);
            List<StorageAreaResponese> storageAreaResponeses = storageAreaService.getList(responese.getStorageId().toString());
            model.put("storageLocation", responese);
            model.put("storageareas", storageAreaResponeses);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_STORAGE_LOCATION;
    }
}
