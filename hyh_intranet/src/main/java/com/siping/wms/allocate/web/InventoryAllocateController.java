package com.siping.wms.allocate.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.SpringController;
import org.stroma.framework.core.platform.web.StromaWebserviceController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;
import org.stroma.framework.core.util.URLUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.wms.request.AddInventoryAllocateRequest;
import com.siping.smartone.wms.request.GetInventoryAllocateListRequest;
import com.siping.smartone.wms.response.GetInventoryAllocateResponse;
import com.siping.wms.allocate.service.InventoryAllocateService;

@Controller
public class InventoryAllocateController extends StromaWebserviceController implements SpringController {
    @Autowired
    private InventoryAllocateService inventoryAllocateService;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private SessionContext sessionContext;

    @RequestMapping(value = "/inventoryallocate/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    // TODO 实际环境中,需要将这个改为POST方式
    public String getInventoryAllocateListByPc(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                               GetInventoryAllocateListRequest getInventoryAllocateListRequest, Map<String, Object> model) {
        PagingResponse<GetInventoryAllocateResponse> response = null;
        if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.INVENTORY_ALLOCATE_SEARCH_CONDITION)))
            getInventoryAllocateListRequest = jsonConverter.fromString(GetInventoryAllocateListRequest.class, sessionContext.get(SessionConstants.INVENTORY_ALLOCATE_SEARCH_CONDITION));
        if (null == pageNo)
            sessionContext.set(SessionConstants.INVENTORY_ALLOCATE_SEARCH_CONDITION, jsonConverter.toString(getInventoryAllocateListRequest));
        if (pageSize == null)
            pageSize = 10;
        if (pageNo == null)
            pageNo = 1;
        try {
            response = inventoryAllocateService.getInventoryAllocationList(pageNo, pageSize, getInventoryAllocateListRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != response) {
            PageModel<GetInventoryAllocateResponse> pageModel = new PageModel<GetInventoryAllocateResponse>(pageNo, pageSize, response.getTotalRecords(), response.getRecords());
            model.put("pageModel", pageModel);
        }
        return PagePath.ALL_INVENTORYALLOCATE;
    }

    @RequestMapping(value = "/pda/inventoryallocate/getlist", method = RequestMethod.POST)
    @ResponseBody
    public PagingResponse<GetInventoryAllocateResponse> getInventoryAllocateList(@RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                                                 @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                                                                 GetInventoryAllocateListRequest getInventoryAllocateListRequest) {// pageSize和pageNo在PDA上面不需要的话可以删除
        PagingResponse<GetInventoryAllocateResponse> response = null;
        try {
            response = inventoryAllocateService.getInventoryAllocationList(pageNo, pageSize, getInventoryAllocateListRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/pda/inventoryallocate/get", method = RequestMethod.GET)
    @ResponseBody
    public GetInventoryAllocateResponse get(@RequestParam("allocateNumber") String allocateNumber) {
        GetInventoryAllocateResponse response = null;
        try {
            response = inventoryAllocateService.get(allocateNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/pda/inventoryallocate/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg add(@RequestBody AddInventoryAllocateRequest request, HttpServletRequest req) {
        ResultMsg resultMsg = null;
        try {
            String remoteIp = URLUtils.getRemoteIp(req);
            request.setIp(remoteIp);
            request.setMac(URLUtils.getMACAddress(remoteIp));
            if (inventoryAllocateService.add(request)) {
                resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
            } else {
                resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR));
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }
}
