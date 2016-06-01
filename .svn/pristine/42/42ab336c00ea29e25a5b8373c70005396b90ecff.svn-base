package com.siping.intranet.crm.countryregion.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.countryregion.serivce.CountryRegionService;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;

@Controller
public class CountryRegionController extends StromaController {
    @Autowired
    private CountryRegionService countryRegionService;
    @Autowired
    private I18nUtil i18nUtil;

    @RequestMapping(value = "/countryregion/getlist", method = RequestMethod.GET)
    public String getList(Map<String, Object> model) {
        try {
            List<CountryRegionResponse> response = countryRegionService.getList();
            model.put("countryRegion", response);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_COUNTRY_REGION;
    }

    @RequestMapping(value = "/countryregion/add", method = RequestMethod.GET)
    public String add() {
        return PagePath.ADD_COUNTRY_REGION;
    }

    // @RequestMapping(value = "/countryregion/edit", method =
    // RequestMethod.GET)
    // public String viewEditGroup(@RequestParam("id") String id, Map<String,
    // Object> model) {
    // try {
    // GroupResponse gorup = countryRegionService.get(id, null);
    // model.put("group", gorup);
    // } catch (Exception e) {
    // model.put("resultMsg", new ResultMsg(Boolean.FALSE,
    // i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" +
    // e.getMessage() })));
    // return "";
    // }
    // return PagePath.EDIT_GROUP;
    // }
    //
    // @RequestMapping(value = "/countryregion/edit", method =
    // RequestMethod.POST)
    // @ResponseBody
    // public ResultMsg editGroup(@RequestBody GroupRequest request, Map<String,
    // Object> model) {
    // ResultMsg resultMsg = null;
    // try {
    // Integer userId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
    // request.setUpdateBy(userId);
    // if (countryRegionService.editGroup(request)) {
    // resultMsg = new ResultMsg(Boolean.TRUE,
    // i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
    // } else {
    // resultMsg = new ResultMsg(Boolean.TRUE,
    // i18nUtil.getMessage(ErrorCode.EDIT_ERROR));
    // }
    // } catch (Exception e) {
    // resultMsg = new ResultMsg(Boolean.FALSE,
    // i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" +
    // e.getMessage() }));
    // }
    // return resultMsg;
    // }
    //
    @RequestMapping(value = "/countryregion/get", method = RequestMethod.GET)
    @ResponseBody
    public String get(@RequestParam(value = "id", required = true) Integer id,Map<String,Object> model) {
        CountryRegionResponse response = null;
        try {
            response = countryRegionService.get(id);
            model.put("countryRegion", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @RequestMapping(value = "/countryregion/edit", method = RequestMethod.GET)
    @ResponseBody
    public String edit(@RequestParam(value = "id", required = true) Integer id,Map<String,Object> model) {
        CountryRegionResponse response = null;
        try {
            response = countryRegionService.get(id);
            model.put("countryRegion", response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.EDIT_COUNTRY_REGION;
    }
}
