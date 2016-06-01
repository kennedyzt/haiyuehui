package com.siping.service.permission.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.stroma.framework.core.platform.web.StromaWebserviceController;

import com.siping.service.permission.service.PermissionRestService;
import com.siping.smartone.permission.request.AddPermissionRequest;
import com.siping.smartone.permission.request.AddUserPermissionRequest;
import com.siping.smartone.permission.response.GetButtonPermissionResponse;
import com.siping.smartone.permission.response.GetGroupPermissionResponse;
import com.siping.smartone.permission.response.GetUserPermissionResponse;

@Controller
public class PermissionRestContrller extends StromaWebserviceController {
    @Autowired
    private PermissionRestService permissionService;

    @RequestMapping(value = "/permission/addlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addPermissionByGroupId(@RequestBody AddPermissionRequest request) {
        return permissionService.addPermissionByGroupId(request);
    }

    @RequestMapping(value = "/permission/editlist", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean updatePermissionByGroupId(@RequestBody AddPermissionRequest request) {
        return permissionService.updatePermissionByGroupId(request);
    }

    @RequestMapping(value = "/permission/deletelist/{groupId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deletePermissionByGroupId(@PathVariable Integer groupId) {
        return permissionService.deletePermissionByGroupId(groupId);
    }

    @RequestMapping(value = "/userpermission/addlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean addUserPermissionByUserId(@RequestBody AddUserPermissionRequest request) {
        return permissionService.addUserPermissionByUserId(request);
    }

    @RequestMapping(value = "/userpermission/delete", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean deleteUserPermissionByUserId(@RequestBody AddUserPermissionRequest request) {
        return permissionService.deleteUserPermissionByUserId(request);
    }

    @RequestMapping(value = "/userpermission/editlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean editUserPermissionByUserId(@RequestBody AddUserPermissionRequest request) {
        return permissionService.editUserPermissionByUserId(request);
    }

    @RequestMapping(value = "/permission/get/{groupid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetGroupPermissionResponse getGroupPermission(@PathVariable Integer groupid) {
        return permissionService.getGroupPermission(groupid);
    }

    @RequestMapping(value = "/userpermission/get/{userid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetUserPermissionResponse getUserPermission(@PathVariable Integer userid) {
        return permissionService.getUserPermission(userid);
    }

    @RequestMapping(value = "/permission/getButtonPermissions/{userId}/{url}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public GetButtonPermissionResponse getButtonPermissions(@PathVariable Integer userId, @PathVariable String url) {
        return permissionService.getButtonPermissions(userId, url);
    }
}
