package com.siping.intranet.report.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.page.PageModel;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.StringUtils;

import com.siping.intranet.report.service.ProductReportService;
import com.siping.smartone.common.PagePath;
import com.siping.smartone.inventory.request.ProductExpirationRequest;
import com.siping.smartone.inventory.response.ProductExpirationResponse;
import com.siping.smartone.login.response.UserLoginResponse;
import com.siping.smartone.security.constants.SecuritySessionConstants;

@Controller
@LoginRequired
public class ProductReportController extends StromaController {

    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private ProductReportService productReportService;

    @RequestMapping(value = "/report/expiration/getlist", method = RequestMethod.GET)
    public String getExpListForSupplier() {
        return PagePath.ALL_MATERIAL_EXP;
    }

    @RequestMapping(value = "/report/productexp/getlist", method = RequestMethod.GET)
    public String getProductExpList() {
        return PagePath.REPORT_PRODUCT_EXP;
    }

    @RequestMapping(value = "/report/productexp/getdata", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PageModel<ProductExpirationResponse> getProductExpData(@RequestBody ProductExpirationRequest request) {
        PageModel<ProductExpirationResponse> pageModel = null;
        try {
            PagingResponse<ProductExpirationResponse> response = new PagingResponse<ProductExpirationResponse>();
            UserLoginResponse user = jsonConverter.fromString(UserLoginResponse.class, sessionContext.get(SecuritySessionConstants.LOGGED_USER));
            if ("BusiPartner".equals(user.getUserType().toString())) {
                request.setSupplierId(user.getObjectId());
            }
            if (!StringUtils.hasText(request.getRemainDays())) {
                request.setRemainDays("30");
            }
            response = productReportService.getExpListForSupplier(request);
            if (request.getIsPaging()) {
                pageModel = new PageModel<ProductExpirationResponse>(request.getPageNo(), request.getPageSize(), response.getTotalRecords(), response.getRecords());
            } else {
                pageModel = new PageModel<ProductExpirationResponse>(1, 100, response.getTotalRecords(), response.getRecords());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageModel;
    }
}
