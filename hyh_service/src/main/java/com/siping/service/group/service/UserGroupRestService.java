package com.siping.service.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.group.repository.UserGroupRestRepository;
import com.siping.smartone.group.request.GetUserGroupRequest;
import com.siping.smartone.group.request.UserGroupRequest;
import com.siping.smartone.group.response.UserGroupResponse;

@Service
public class UserGroupRestService extends DBSwitch {
    @Autowired
    private UserGroupRestRepository userGroupRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addUserGroup(UserGroupRequest request) {
        return userGroupRestRepository.addUserGroup(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deleteUserGroup(Integer userGroupId, Integer userId) {
        return userGroupRestRepository.deleteUserGroup(userGroupId, userId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public UserGroupResponse getUserGroup(GetUserGroupRequest request) {
        return userGroupRestRepository.getUserGroup(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<UserGroupResponse> getUserGrouplist(GetUserGroupRequest request) {
        return userGroupRestRepository.getUserGroupList(request);
    }

}
