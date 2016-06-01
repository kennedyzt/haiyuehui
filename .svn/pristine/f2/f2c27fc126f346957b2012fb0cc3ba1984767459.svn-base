package com.siping.intranet.crm.group.serivce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.group.repository.UserGroupRepository;
import com.siping.smartone.group.request.UserGroupRequest;
import com.siping.smartone.group.response.UserGroupResponse;

@Service
public class UserGroupService {
    @Autowired
    private UserGroupRepository userGroupRepository;

    public Boolean addUserGroup(UserGroupRequest request) {
        return userGroupRepository.addUserGroup(request);
    }

    public boolean deleteUserGroup(Integer userGroupId, Integer userid) {
        return userGroupRepository.deleteUserGroup(userGroupId, userid);
    }

    public UserGroupResponse getUserGroup(String id, String userId) {
        return userGroupRepository.getUserGroup(id, userId);
    }

    public UserGroupResponse getUserGroupList(String groupId) {
        return userGroupRepository.getUserGroupList(groupId);
    }
}
