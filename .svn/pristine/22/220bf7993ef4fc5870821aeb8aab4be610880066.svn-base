package com.siping.intranet.system.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.stroma.framework.core.platform.web.StromaController;

import com.siping.smartone.common.PagePath;

@Controller
public class UnitManagement extends StromaController {

    @RequestMapping(value = "/unitmanage/add", method = RequestMethod.GET)
    public String addUnitManagement(Map<String, Object> model) {
        return PagePath.ADD_UNIT_MANAGEMENT;
    }

    @RequestMapping(value = "/unitmanage/edit", method = RequestMethod.GET)
    public String editUnitManagement(Map<String, Object> model) {
        return PagePath.EDIT_UNIT_MANAGEMENT;
    }

    @RequestMapping(value = "/unitmanage/get", method = RequestMethod.GET)
    public String getUnitManagement(Map<String, Object> model) {
        return PagePath.ADD_UNIT_MANAGEMENT;
    }

    @RequestMapping(value = "/unitmanage/getList", method = RequestMethod.GET)
    public String getUnitManagementList(Map<String, Object> model) {
        return PagePath.ALL_UNIT_MANAGEMENT;
    }

}
