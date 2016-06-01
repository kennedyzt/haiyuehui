package com.siping.intranet.crm.businesspartner.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;
import org.stroma.framework.core.util.StringUtils;

import com.siping.integrate.constant.SessionConstants;
import com.siping.intranet.crm.businesspartner.service.BankAccountService;
import com.siping.intranet.crm.businesspartner.service.BusinessPartnerService;
import com.siping.intranet.crm.businesspartner.service.PartnerGroupService;
import com.siping.intranet.crm.countryregion.serivce.CountryRegionService;
import com.siping.smartone.businesspartner.request.GetBusinessPartnerListForm;
import com.siping.smartone.businesspartner.response.BankAccountResponse;
import com.siping.smartone.businesspartner.response.BusinessPartnerResponse;
import com.siping.smartone.businesspartner.response.CountryRegionResponse;
import com.siping.smartone.businesspartner.response.PartnerGroupResponse;
import com.siping.smartone.businesspartner.response.UploadBusinessPartnerResponse;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.common.ResultMsg;
import com.siping.smartone.error.ErrorCode;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class BusinessPartnerController extends StromaController {
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    BusinessPartnerService businessPartnerService;
    @Autowired
    PartnerGroupService partnerGroupService;
    @Autowired
    BankAccountService bankAccountService;
    @Autowired
    CountryRegionService countryRegionService;

    @RequestMapping(value = "/businesspartner/addsupplier", method = RequestMethod.GET)
    public String addSupplier(@RequestParam(value = "type", required = true) Integer partnerType, Map<String, Object> model) {
        List<PartnerGroupResponse> partnerGroups = null;
        List<CountryRegionResponse> countryRegions = null;
        try {
            partnerGroups = partnerGroupService.getList(partnerType);
            countryRegions = countryRegionService.getList();
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
            e.printStackTrace();
        }
        model.put("partnerType", partnerType);
        model.put("partnerGroups", partnerGroups);
        model.put("countryRegions", countryRegions);
        return PagePath.ADD_SUPPLIER;
    }

    @RequestMapping(value = "/businesspartner/addcustomer", method = RequestMethod.GET)
    public String addCustomer(@RequestParam(value = "type", required = true) Integer partnerType, Map<String, Object> model) {
        List<PartnerGroupResponse> response = null;
        try {
            response = partnerGroupService.getList(partnerType);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
            e.printStackTrace();
        }
        model.put("partnerType", partnerType);
        model.put("partnerGroups", response);
        return PagePath.ADD_CUSTOMER;
    }

    @RequestMapping(value = "/businesspartner/edit", method = RequestMethod.GET)
    public String editBusinessPartnerView(@RequestParam("id") Integer id, Map<String, Object> model) {
        List<PartnerGroupResponse> partnerGroups = null;
        try {
            partnerGroups = partnerGroupService.getList();
            BusinessPartnerResponse reponse = businessPartnerService.get(id);
            model.put("businessPartner", reponse);
            model.put("partnerGroups", partnerGroups);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.EDIT_PARTNER;
    }

    @RequestMapping(value = "/businesspartner/editsupplier", method = RequestMethod.GET)
    public String editSupplier(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            List<PartnerGroupResponse> partnerGroups = partnerGroupService.getList();
            BusinessPartnerResponse reponse = businessPartnerService.get(id);
            List<CountryRegionResponse> countryRegions = countryRegionService.getList();
            model.put("businessPartner", reponse);
            model.put("partnerGroups", partnerGroups);
            model.put("countryRegions", countryRegions);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return PagePath.EDIT_SUPPLIER;
    }

    @RequestMapping(value = "/businesspartner/getlist", method = { RequestMethod.POST, RequestMethod.GET })
    public String getCustomers(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                               @RequestParam(value = "partnerType", required = true) Integer partnerType, GetBusinessPartnerListForm request, Map<String, Object> model) {
        List<PartnerGroupResponse> partnerGroups = null;
        try {
            if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION)))
                request = jsonConverter.fromString(GetBusinessPartnerListForm.class, sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION));
            if (null == pageNo)
                sessionContext.set(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION, jsonConverter.toString(request));
            PagingResponse<BusinessPartnerResponse> pagingResponse = new PagingResponse<BusinessPartnerResponse>();
            pagingResponse = businessPartnerService.getAllBusinessPartner(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, partnerType, request);
            PageModel<BusinessPartnerResponse> pageModel = new PageModel<BusinessPartnerResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
                pagingResponse.getRecords());
            model.put("pageModel", pageModel);
            model.put("partnerType", partnerType);
            partnerGroups = partnerGroupService.getList(partnerType);
            model.put("partnerGroups", partnerGroups);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        if (partnerType == 2) {
            return PagePath.ALL_SUPPLIER;
        }
        return PagePath.ALL_CUSTOMER;
    }

    @RequestMapping(value = "/businesspartner/openwin", method = { RequestMethod.POST, RequestMethod.GET })
    public String openWinGetPartner(@RequestParam(value = "pageSize", required = false) Integer pageSize, @RequestParam(value = "pageNo", required = false) Integer pageNo,
                                    @RequestParam(value = "partnerType", required = true) Integer partnerType, GetBusinessPartnerListForm request, Map<String, Object> model) {
        List<PartnerGroupResponse> partnerGroups = null;
        if (null != pageNo && StringUtils.hasText(sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION)))
            request = jsonConverter.fromString(GetBusinessPartnerListForm.class, sessionContext.get(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION));
        if (null == pageNo)
            sessionContext.set(SessionConstants.BUSINESS_PARTNER_SEARCH_CONDITION, jsonConverter.toString(request));
        request.setIsEnable(true);
        PagingResponse<BusinessPartnerResponse> pagingResponse = new PagingResponse<BusinessPartnerResponse>();
        pagingResponse = businessPartnerService.getAllBusinessPartner(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, partnerType, request);
        PageModel<BusinessPartnerResponse> pageModel = new PageModel<BusinessPartnerResponse>(pageNo == null ? 1 : pageNo, pageSize == null ? 10 : pageSize, pagingResponse.getTotalRecords(),
            pagingResponse.getRecords());
        model.put("pageModel", pageModel);
        partnerGroups = partnerGroupService.getList(partnerType);
        model.put("partnerGroups", partnerGroups);
        return PagePath.OPEN_WIN_PARTNER;
    }

    @RequestMapping(value = "/businesspartner/batchadd", method = RequestMethod.POST)
    public String batchAdd(MultipartHttpServletRequest request, HttpServletResponse rep, Map<String, Object> model) throws IOException {
        Integer loginId = sessionContext.get(SecuritySessionConstants.LOGGED_ID);
        try {
            MultipartFile multipartFile = request.getFile("businessPartnerInfo");
            String contentType = multipartFile.getContentType();
            if (!"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet".equals(contentType))
                throw new BusinessProcessException("上传文件格式不正确！");
            UploadBusinessPartnerResponse response = businessPartnerService.batchAdd(multipartFile.getInputStream(), loginId);
            model.put("batchInfo", response);
        } catch (Exception e) {
            model.put("exception", e);
        }
        return "";
    }

    @RequestMapping(value = "/businesspartner/get", method = RequestMethod.GET)
    public String get(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            BusinessPartnerResponse reponse = businessPartnerService.get(id);
            model.put("businessPartner", reponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.DETAIL_PARTNER;
    }

    @RequestMapping(value = "/businesspartner/getsupplier", method = RequestMethod.GET)
    public String getSupplier(@RequestParam("id") Integer id, Map<String, Object> model) {
        try {
            BusinessPartnerResponse reponse = businessPartnerService.get(id);
            model.put("businessPartner", reponse);
        } catch (Exception e) {
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        return PagePath.DETAIL_SUPPLIER;
    }

    @RequestMapping(value = "/businesspartner/bank/getlist", method = RequestMethod.GET)
    public String getPartnerBankList(@RequestParam(value = "id", required = true) Integer id, Map<String, Object> model) {
        List<BankAccountResponse> response = null;
        try {
            response = bankAccountService.getList(id);
        } catch (Exception e) {
            e.printStackTrace();
            model.put("resultMsg", new ResultMsg(Boolean.FALSE, i18nUtil.getMessage(ErrorCode.QUERY_ERROR, new Object[] { ":" + e.getMessage() })));
        }
        model.put("bankAccounts", response);
        model.put("partnerId", id);
        return PagePath.ALL_PARTNER_BANK;
    }

    @RequestMapping(value = "/businesspartner/bank/add", method = RequestMethod.GET)
    public String addPartnerBank(@RequestParam(value = "id", required = true) Integer id, Map<String, Object> model) {
        model.put("partnerId", id);
        return PagePath.ADD_PARTNER_BANK;
    }

    // @RequestMapping(value = "/businesspartner/shops/getlist", method =
    // RequestMethod.GET)
    // public String getShopsList(@RequestParam(value = "keywords", required =
    // false) String keywords) {
    // return PagePath.ALL_SHOPS;
    // }
}
