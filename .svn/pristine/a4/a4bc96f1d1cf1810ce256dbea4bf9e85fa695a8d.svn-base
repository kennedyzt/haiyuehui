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
import com.siping.smartone.storage.response.StorageAreaResponese;
import com.siping.smartone.storage.response.StorageLocationResponese;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class StorageAreaController extends StromaController {

    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SecureSessionContext secureSessionContxet;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private StorageService storageService;
    @Autowired
    private StorageAreaService storageAreaService;

    @RequestMapping(value = "/storagearea/add", method = RequestMethod.GET)
    public String addStorageArea(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            StorageResponse response = storageService.get(id);
            model.put("s", response);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ADD_STORAGE_AREA;
    }
    
    @RequestMapping(value = "storagearea/getlist", method = RequestMethod.GET)
    public String allStorageArea(@RequestParam("id") String storageId, Map<String, Object> model) {
        try {
            List<StorageAreaResponese> responeses = storageAreaService.getList(storageId);
            model.put("storageArea", responeses);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_STORAGE_AREA;
    }
    
    @RequestMapping(value = "/storagearea/edit", method = RequestMethod.GET)
    public String editStorageView(@RequestParam("id") String id, Map<String, Object> model) {
        try {
            StorageAreaResponese responese = storageAreaService.get(id);
            model.put("storageArea", responese);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.EDIT_STORAGE_AREA;
    }
}
