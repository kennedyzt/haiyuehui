package com.siping.intranet.report.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.platform.intercept.LoginRequired;
import org.stroma.framework.core.platform.web.StromaController;
import org.stroma.framework.core.platform.web.session.SessionContext;
import org.stroma.framework.core.util.I18nUtil;

import com.siping.intranet.crm.material.service.MaterialService;
import com.siping.intranet.crm.material.service.MaterialTypeService;
import com.siping.intranet.crm.storage.service.StorageService;
import com.siping.intranet.report.service.InventoryReportService;

@Controller
@LoginRequired
public class InventoryPrintController extends StromaController {
    @Autowired
    private StorageService storageService;
    @Autowired
    private InventoryReportService inventoryReportService;
    @Autowired
    private SessionContext sessionContext;
    @Autowired
    private I18nUtil i18nUtil;
    @Autowired
    private JSONConverter jsonConverter;
    @Autowired
    private MaterialTypeService materialTypeService;
    @Autowired
    private MaterialService materialService;

    @RequestMapping(value = "/print", method = { RequestMethod.GET, RequestMethod.POST })
    public String print(Map<String, Object> model) throws IOException {
        return "print/print";
    }
    
    @RequestMapping(value = "/qrcode", method = { RequestMethod.GET, RequestMethod.POST })
    public String qrcode(Map<String, Object> model) throws IOException {
        return "print/qrcode";
    }
}
