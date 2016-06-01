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
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.group.service.UserGroupRestService;
import com.siping.smartone.group.request.GetUserGroupRequest;
import com.siping.smartone.group.request.UserGroupRequest;
import com.siping.smartone.group.response.UserGroupResponse;

@Controller
public class UserGroupRestController extends StromaWebserviceController {
    @Autowired
    private UserGroupRestService userGroupRestService;

    @RequestMapping(value = "/usergroup/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addUserGroup(@RequestBody UserGroupRequest request) {
        return userGroupRestService.addUserGroup(request);
    }

    @RequestMapping(value = "/usergroup/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserGroupResponse getUserGroup(@RequestBody GetUserGroupRequest request) {
        return userGroupRestService.getUserGroup(request);
    }

    @RequestMapping(value = "/usergroup/getList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserGroupResponse> getUserGroupList(@RequestBody GetUserGroupRequest request) {
        return userGroupRestService.getUserGrouplist(request);
    }

    @RequestMapping(value = "/usergroup/delete/{id}/{userid}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteUserGroup(@PathVariable Integer id, @PathVariable Integer userid) {
        return userGroupRestService.deleteUserGroup(id, userid);
    }
}
