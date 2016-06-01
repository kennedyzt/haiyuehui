package com.siping.service.menu.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.menu.service.MenuRestService;
import com.siping.smartone.menu.request.MenuTreeRequest;
import com.siping.smartone.menu.response.MenuTreeResponse;

@Controller
public class MenuRestController extends StromaWebserviceController {
    @Autowired
    private MenuRestService menuRestService;

    @RequestMapping(value = "/menutree/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addMenuTree(@RequestBody MenuTreeRequest request) {
        return menuRestService.addMenuTree(request);
    }

    @RequestMapping(value = "/menutree/delete/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteMenuTree(@PathVariable Integer id) {
        return menuRestService.deleteMenuTree(id);
    }

    @RequestMapping(value = "/menutree/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateMenuTree(@RequestBody MenuTreeRequest request) {
        return menuRestService.updateMenuTree(request);
    }

    @RequestMapping(value = "/menutree/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MenuTreeResponse getMenuTreeById(@PathVariable Integer id) {
        return menuRestService.getMenuTree(id);
    }

    @RequestMapping(value = "/menutree/getlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<MenuTreeResponse> getList() {
        return menuRestService.getList();
    }

}
