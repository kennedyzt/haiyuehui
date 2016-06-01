package com.siping.service.usr.ws;

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

import com.siping.service.usr.service.UsrRestService;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.login.request.AddLocalRequest;
import com.siping.smartone.login.request.AddUserRequest;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.request.UserQueryParam;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.login.response.UserLoginResponse;

@Controller
public class UsrRestController extends StromaWebserviceController {
    @Autowired
    private UsrRestService usrRestService;

    @RequestMapping(value = "/usr/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserLoginResponse login(@RequestBody UserLoginRequest request) {
        return usrRestService.login(request);
    }

    @RequestMapping(value = "/usr/add", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean add(@RequestBody AddUserRequest request) {
        return usrRestService.add(request);
    }

    @RequestMapping(value = "/usr/edit", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean edit(@RequestBody UpdateUserRequest request) {
        return usrRestService.edit(request);
    }

    @RequestMapping(value = "/usr/resetpwd", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean resetPwd(@RequestBody UpdateUserRequest request) {
        return usrRestService.resetPwd(request);
    }

    @RequestMapping(value = "/usr/get", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public UserLoginResponse get(@RequestBody UpdateUserRequest request) {
        return usrRestService.get(request);
    }

    @RequestMapping(value = "/usr/getgroups", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<GroupResponse> getGroups(@RequestBody UpdateUserRequest request) {
        return usrRestService.getGroups(request);
    }

    @RequestMapping(value = "/usr/getlist", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<UserLoginResponse> getList() {
        return usrRestService.getList();
    }

    @RequestMapping(value = "/usr/delete/{id}/{updateBy}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean delete(@PathVariable Integer id, @PathVariable Integer updateBy) {
        return usrRestService.delete(id, updateBy);
    }

    @RequestMapping(value = "/usr/languageswitch", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Boolean languageSwitch(@RequestBody AddLocalRequest request) {
        return usrRestService.languageSwitch(request);
    }

    @RequestMapping(value = "/usr/getlist", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public PagingResponse<UserInfoResponse> getList(@RequestBody UserQueryParam request) {
        return usrRestService.getList(request);
    }
}
