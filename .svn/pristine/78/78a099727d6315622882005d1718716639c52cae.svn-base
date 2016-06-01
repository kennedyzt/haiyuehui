package com.siping.service.group.ws;

import java.util.List;

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

import com.siping.service.group.service.GroupRestService;
import com.siping.smartone.group.request.GetGroupRequest;
import com.siping.smartone.group.request.GroupQueryParam;
import com.siping.smartone.group.request.GroupRequest;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;

@Controller
public class GroupRestController extends StromaWebserviceController {
    @Autowired
    private GroupRestService groupRestService;

    @RequestMapping(value = "/group/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addGroup(@RequestBody GroupRequest request) {
        return groupRestService.addGroup(request);
    }

    @RequestMapping(value = "/group/edit", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updateGroup(@RequestBody GroupRequest request) {
        return groupRestService.editGroup(request);
    }

    @RequestMapping(value = "/group/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GroupResponse getGroupById(@RequestBody GetGroupRequest request) {
        return groupRestService.get(request);
    }

    @RequestMapping(value = "/group/delete/{id}/{userid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteGroup(@PathVariable Integer id, @PathVariable Integer userid) {
        return groupRestService.deleteGroup(id, userid);
    }

    @RequestMapping(value = "/group/getalllist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GroupResponse> getAllGroupList() {
        return groupRestService.getAllGroupList();
    }

    @RequestMapping(value = "/group/getpagelist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<GroupResponse> getPageGroupList(@RequestBody GroupQueryParam request) {
        return groupRestService.getPageGroupList(request);
    }

    @RequestMapping(value = "/group/getGroupPermissions/{id}/{userAccount}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Permission> getGroupPermissions(@PathVariable("id") Integer id, @PathVariable("userAccount") String userAccount) {
        return groupRestService.getGroupPermissions(id, userAccount);
    }

    @RequestMapping(value = "/group/editGroupPermissions", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean editGroupPermissions(@RequestBody GroupRequest groupRequest) {
        return groupRestService.editGroupPermissions(groupRequest);
    }
}
