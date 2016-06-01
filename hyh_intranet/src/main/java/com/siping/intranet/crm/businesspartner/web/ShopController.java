package com.siping.intranet.crm.businesspartner.web;

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
import com.siping.intranet.crm.businesspartner.service.ShopService;
import com.siping.smartone.businesspartner.response.ShopResponse;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.common.request.CommonBillsRequest;
import com.siping.smartone.error.ErrorCode;

@Controller
@LoginRequired
public class ShopController extends StromaController{
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private ShopService shopService;
    @RequestMapping(value = "/shop/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsRequest request,Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.SHOP_CONDITION_LIST)))
                request = jsonConverter.fromString(CommonBillsRequest.class, sessionContext.get(SessionConstants.SHOP_CONDITION_LIST));
            if (null == pageNo)
                sessionContext.set(SessionConstants.SHOP_CONDITION_LIST, jsonConverter.toString(request));
            PagingResponse<ShopResponse> response = new PagingResponse<ShopResponse>();
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            response = shopService.getList(pageNo, pageSize, request);
            PageModel<ShopResponse> pageModel = new PageModel<ShopResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("searchCondition", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_SHOPS;
    }
    
    @RequestMapping(value = "/shop/openwin", method = { RequestMethod.POST, RequestMethod.GET })
    public String getLayerList(@RequestParam(value = "pageNo", required = false) Integer pageNo, @RequestParam(value = "pageSize", required = false) Integer pageSize, CommonBillsRequest request,Map<String, Object> model) {
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.SHOP_CONDITION_LIST)))
                request = jsonConverter.fromString(CommonBillsRequest.class, sessionContext.get(SessionConstants.SHOP_CONDITION_LIST));
            if (null == pageNo)
                sessionContext.set(SessionConstants.SHOP_CONDITION_LIST, jsonConverter.toString(request));
            PagingResponse<ShopResponse> response = new PagingResponse<ShopResponse>();
            if (pageNo == null) {
                pageNo = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            response = shopService.getList(pageNo, pageSize, request);
            PageModel<ShopResponse> pageModel = new PageModel<ShopResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
            model.put("searchCondition", request);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.GET_SHOPS;
    }
}
