package com.siping.intranet.crm.businesspartner.web;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.businesspartner.service.BusinessPartnerService;
import com.siping.smartone.businesspartner.request.BusinessPartnerExportParamRequest;
import com.siping.smartone.businesspartner.request.BusinessPartnerRequest;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.common.DataConvertUtil;
import com.siping.smartone.common.ExportExcel;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class BusinessPartnerAjaxController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    BusinessPartnerService businessPartnerService;

    @RequestMapping(value = "/businesspartner/delete", method = RequestMethod.GET)
    @ResponseBody
    public ResultMsg deleteBusinessPartnerById(@RequestParam("id") List<Integer> ids) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            businessPartnerService.deleteBusinessPartnerById(ids, loginId);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.DELETE_SUCCESS));
        } catch (Exception e) {
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.DELETE_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/businesspartner/add", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg addBusinessPartner(BusinessPartnerRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setCreateBy(loginId);
            businessPartnerService.addBusinessPartner(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.ADD_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.ADD_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @RequestMapping(value = "/businesspartner/edit", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg editBusinessPartnerById(BusinessPartnerRequest request) {
        ResultMsg resultMsg = null;
        try {
            Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
            request.setUpdateBy(loginId);
            businessPartnerService.editBusinessPartnerById(request);
            resultMsg = new ResultMsg(Boolean.TRUE, i18nUtil.getMessage(ErrorCode.EDIT_SUCCESS));
        } catch (Exception e) {
            e.printStackTrace();
            resultMsg = new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.EDIT_ERROR, new Object[] { ":" + e.getMessage() }));
        }
        return resultMsg;
    }

    @SuppressWarnings("unchecked")
    @RequestMapping(value = "/businesspartner/export", method = RequestMethod.POST)
    @ResponseBody
    public ResultMsg export(BusinessPartnerExportParamRequest businessPartnerExportParamRequest, HttpServletRequest request, HttpServletResponse response) {
        ResultMsg resultMsg = null;
        List<Map<String, Object>> listMap = new LinkedList<Map<String, Object>>();
        try {
            List<BusinessPartnerResponse> businessPartners = businessPartnerService.get(businessPartnerExportParamRequest);
            Iterator<BusinessPartnerResponse> it = businessPartners.iterator();
            while (it.hasNext()) {
                BusinessPartnerResponse businessPartner = it.next();
                Map<String, Object> map = DataConvertUtil.convertBean(businessPartner);
                listMap.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] colName = { "partnerCode", "partnerType", "partnerName", "foreignName", "partnerGroup", "phone", "mobilephone", "fax", "email", "website", "bankAccount", "contactsLastName",
                "contactsFirstName", "contactsGender", "contactsPhone", "contactsMobilephone", "contactsDescription", "address" };
        String[] colRelName = { "编码", "类型", "名称", "英文名", "组", "电话", "移动电话", "传真", "邮箱", "站点", "银行账户", "联系人姓氏", "联系人名", "性别", "联系人电话", "联系人手机", "描述", "地址" };
        ExportExcel.exportToExcel("业务伙伴信息", "业务伙伴信息", "Sheet1", listMap, response, colName, colRelName);
        return resultMsg;
    }

    // 用于模糊查询业务伙伴信息
    @RequestMapping(value = "/businesspartner/getlistbykeyword", method = RequestMethod.GET)
    @ResponseBody
    public List<BusinessPartnerResponse> getPartnerListBykeyword(@RequestParam(value="keyword",required=false) String key, @RequestParam(value="type",required=false) Integer type, Map<String, Object> model) {
        List<BusinessPartnerResponse> response = null;
        try {
            response = businessPartnerService.getList(key, type);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return response;
    }

}
