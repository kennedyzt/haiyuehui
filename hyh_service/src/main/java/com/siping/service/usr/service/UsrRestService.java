package com.siping.service.usr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.group.repository.UserGroupRestRepository;
import com.siping.service.usr.repository.UsrRestRepository;
import com.siping.smartone.group.request.UserGroupRequest;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.login.request.AddLocalRequest;
import com.siping.smartone.login.request.AddUserRequest;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.request.UserQueryParam;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.login.response.UserLoginResponse;

@Service
public class UsrRestService extends DBSwitch {
    @Autowired
    private UsrRestRepository usrRestRepository;
    @Autowired
    private UserGroupRestRepository userGroupRestRepository;

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public UserLoginResponse login(UserLoginRequest request) {
        return usrRestRepository.login(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean add(AddUserRequest request) {
        if (usrRestRepository.add(request)) {
            if (request.getGroups() != null) {
                for (Integer groupId : request.getGroups()) {
                    UserGroupRequest userGroup = new UserGroupRequest();
                    userGroup.setGroupId(groupId);
                    userGroup.setUserId(request.getId());
                    userGroup.setIsDelete(false);
                    userGroupRestRepository.addUserGroup(userGroup);
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean edit(UpdateUserRequest request) {
        if (usrRestRepository.edit(request)) {
            userGroupRestRepository.deleteGroupOfUser(request.getId(), request.getCreateBy());
            if (request.getGroups() != null) {
                for (Integer groupId : request.getGroups()) {
                    UserGroupRequest userGroup = new UserGroupRequest();
                    userGroup.setGroupId(groupId);
                    userGroup.setUserId(request.getId());
                    userGroup.setIsDelete(false);
                    userGroupRestRepository.addUserGroup(userGroup);
                }
            }
        } else {
            return false;
        }
        return true;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean resetPwd(UpdateUserRequest request) {
        return usrRestRepository.resetPwd(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public UserLoginResponse get(UpdateUserRequest request) {
        UserLoginResponse userLoginResponse = usrRestRepository.get(request);
        return userLoginResponse;
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<UserLoginResponse> getList() {
        return usrRestRepository.getList();
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean delete(Integer id, Integer updateBy) {
        boolean flag = false;
        usrRestRepository.deleteUserRelated(id);
        flag = usrRestRepository.delete(id, updateBy + "");
        return flag;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean languageSwitch(AddLocalRequest request) {
        return usrRestRepository.languageSwitch(request);
    }

    public PagingResponse<UserInfoResponse> getList(UserQueryParam request) {
        return usrRestRepository.getList(request);
    }

    public List<GroupResponse> getGroups(UpdateUserRequest request) {
        List<GroupResponse> groups = userGroupRestRepository.getGroupsOfUser(request.getId());
        return groups;
    }
}
