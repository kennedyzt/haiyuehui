package com.siping.intranet.crm.storage.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.storage.service.StorageAreaService;
import com.siping.intranet.crm.storage.service.StorageLocationService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.request.AddStorageAreaRequest;
import com.siping.smartone.storage.request.AddStorageLocationRequest;
import com.siping.smartone.storage.response.StorageAreaResponese;

@Controller
@LoginRequired
public class StorageAreaAjaxController extends StromaController {

    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private StorageLocationService storageLocationService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private StorageAreaService storageAreaService;
    
    @RequestMapping(value = "storagearea/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(AddStorageAreaRequest request) {
        ResultMsg resultMsg = null;
        try {
            
            request.setCreateBy(sessionContext.get(SecuritySessionConstants.LOGGED_ID));
            storageAreaService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    @RequestMapping(value = "storagearea/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(AddStorageAreaRequest request) {
        ResultMsg resultMsg = null;
        try {
            
            request.setUpdateBy(sessionContext.get(SecuritySessionConstants.LOGGED_ID));
            storageAreaService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    
    @RequestMapping(value = "/storagearea/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<String> ids) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if(storageAreaService.delete(ids, updateBy.toString())){
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
            else {
                resultMsg = new ResultMsg(Boolean.FALSE, "删除失败");
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    
    @RequestMapping(value = "storagearea/getlistbyasync", method = RequestMethod.GET)
    @ResponseBody
    public List<StorageAreaResponese> getAllStorageArea(@RequestParam("storageId") String storageId) {
        List<StorageAreaResponese> responeses = null;
        try {
             responeses = storageAreaService.getList(storageId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responeses;
    }
}
