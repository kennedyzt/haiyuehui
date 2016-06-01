package com.siping.service.menu.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.menu.service.MenuNodeRestService;
import com.siping.smartone.menu.request.MenuNodeQueryParam;
import com.siping.smartone.menu.request.MenuNodeRequest;
import com.siping.smartone.menu.response.MenuNodeResponse;

@Controller
public class MenuNodeRestController extends StromaWebserviceController {
    @Autowired
    private MenuNodeRestService menuNodeRestService;

    @RequestMapping(value = "/menunode/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<MenuNodeResponse> getList(@RequestBody MenuNodeQueryParam request) {
        return menuNodeRestService.getList(request);
    }

    @RequestMapping(value = "/menunode/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addMenuNode(@RequestBody MenuNodeRequest request) {
        return menuNodeRestService.addMenuNode(request);
    }

    @RequestMapping(value = "/menunode/delete/{id}/{userid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteMenuNode(@PathVariable Integer id, @PathVariable String userid) {
        return menuNodeRestService.deleteMenuNode(id, userid);
    }

    @RequestMapping(value = "/menunode/update", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateMenuNode(@RequestBody MenuNodeRequest request) {
        return menuNodeRestService.updateMenuNode(request);
    }

    @RequestMapping(value = "/menunode/get/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public MenuNodeResponse getMenuNodeById(@PathVariable Integer id) {
        return menuNodeRestService.getMenuNode(id);
    }
}
