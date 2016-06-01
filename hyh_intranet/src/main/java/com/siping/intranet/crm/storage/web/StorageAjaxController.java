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

import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;
import com.siping.smartone.storage.request.StorageRequest;
import com.siping.smartone.storage.response.StorageResponse;

@Controller
@LoginRequired
public class StorageAjaxController extends StromaController {

    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private StorageService storageService;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/storage/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(StorageRequest request) {
        ResultMsg resultMsg = null;
        try {
            storageService.add(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
    

    @RequestMapping(value = "/storage/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg edit(StorageRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(userId);
            storageService.edit(request);
            resultMsg = new ResultMsg(Boolean.TRUE, "修改成功");
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/storage/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg delete(@RequestParam("id") List<String> ids) {
        ResultMsg resultMsg = null;
        try {
            Integer updateBy = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            if (storageService.delete(ids, updateBy.toString()))
                ;
            {
                resultMsg = new ResultMsg(Boolean.TRUE, "删除成功");
            }
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/storage/getlistbykey", method = RequestMethod.GET)
    @ResponseBody
    public List<StorageResponse> getStorageList(@RequestParam("key") String key, Map<String, Object> model) {
        List<StorageResponse> response = null;
        try {
            response = storageService.getList(key);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }

    @RequestMapping(value = "/storage/getlist/withnoparam", method = RequestMethod.GET)
    @ResponseBody
    public List<StorageResponse> getAllStorage() {
        List<StorageResponse> response = null;
        try {
            response = storageService.getList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
