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
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.storage.request.GetStorageListRequest;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class StorageController extends StromaController {

    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private StorageService storageService;
    @Autowired
    private JSONConverter jsonConverter;

    @RequestMapping(value = "/storage/add", method = RequestMethod.GET)
    public String addStorageView() {
        return PagePath.ADD_STORAGE;
    }

    @RequestMapping(value = "/storage/edit", method = RequestMethod.GET)
    public String editStorageView(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            StorageResponse response = storageService.get(id);
            model.put("s", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }

        return PagePath.EDIT_STORAGE;
    }

    // @RequestMapping(value = "/storage/getlist", method = RequestMethod.GET)
    // public String getStorageList(Map<String, Object> model) {
    // try {
    // List<StorageResponse> storageResponses = storageService.getList();
    // model.put("storages", storageResponses);
    // } catch (Exception e) {
    // e.printStackTrace();
    // model.put("resultMsg", new ResultMsg(Boolean.FALSE,
    // i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" +
    // e.getMessage() })));
    // }
    // return PagePath.ALL_STORAGE;
    // }

    @RequestMapping(value = "/storage/getlist", method = { RequestMethod.GET, RequestMethod.POST })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, GetStorageListRequest request,
                          Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.STORAGE_SEARCH_CONDITION_LIST)))
                request = jsonConverter.fromString(GetStorageListRequest.class, sessionContext.get(SessionConstants.STORAGE_SEARCH_CONDITION_LIST));
            if (null == pageNo)
                sessionContext.set(SessionConstants.INVENTORY_CHECK_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<StorageResponse> response = storageService.getList(pageNo, pageSize, request);
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            PageModel<StorageResponse> pageModel = new PageModel<StorageResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("storages", pageModel);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_STORAGE;
    }

    @RequestMapping(value = "/storage/layerallstorage", method = RequestMethod.GET)
    public String getAllStorageToChoose(@RequestParam(value = "isEnableLocation", required = false) Boolean isEnableLocation, Map<String, Object> model) {
        try {
            List<StorageResponse> storageResponses = storageService.getList();
            model.put("storages", storageResponses);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.LAYER_CHOOSE_STORAGE;
    }

    @RequestMapping(value = "/storage/getlistbyislocation", method = RequestMethod.GET)
    public String getListByIsLocation(@RequestParam(value = "isEnableLocation", required = false) Boolean isEnableLocation, Map<String, Object> model) {
        try {
            List<StorageResponse> storageResponses = storageService.getList(isEnableLocation);
            model.put("storages", storageResponses);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.LAYER_CHOOSE_STORAGE;
    }
}
