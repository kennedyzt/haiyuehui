package com.siping.intranet.crm.businesspartner.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.businesspartner.service.PartnerGroupService;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;

@Controller
@LoginRequired
public class PartnerGroupController extends StromaController {
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    PartnerGroupService partnerGroupService;

    @RequestMapping(value = "/partnergroup/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getList(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                          @RequestParam(value = "partnerType", required = true) Integer partnerType, Map<String, Object> model) {
        try {
            PagingResponse<PartnerGroupResponse> pagingResponse = new PagingResponse<PartnerGroupResponse>();
            pagingResponse = partnerGroupService.getList(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, partnerType);
            PageModel<PartnerGroupResponse> pageModel = new PageModel<PartnerGroupResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            model.put("pageModel", pageModel);
            model.put("partnerType", partnerType);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.ALL_PARTNER_GROUP_MANAGEMENT;
    }

    @RequestMapping(value = "/partnergroup/add", method = RequestMethod.GET)
    public String add(@RequestParam(value = "partnerType", required = true) String partnerType, Map<String, Object> model) {
        model.put("partnerType", partnerType);
        return PagePath.ADD_PARTNER_GROUP_MANAGEMENT;
    }

    @RequestMapping(value = "/partnergroup/edit", method = RequestMethod.GET)
    public String edit(@RequestParam(value = "id", required = true) Integer id, Map<String, Object> model) {
        PartnerGroupResponse response = partnerGroupService.get(id);
        model.put("partnerGroup", response);
        return PagePath.EDIT_PARTNER_GROUP_MANAGEMENT;
    }

    @RequestMapping(value = "/partnergroup/get", method = RequestMethod.GET)
    public String getSupplierGroup(Map<String, Object> model) {
        return PagePath.ADD_PARTNER_GROUP_MANAGEMENT;
    }
}
