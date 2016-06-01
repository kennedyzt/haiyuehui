package com.siping.service.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.group.repository.GroupRestRepository;
import com.siping.smartone.group.request.GetGroupRequest;
import com.siping.smartone.group.request.GroupQueryParam;
import com.siping.smartone.group.request.GroupRequest;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;

@Service
public class GroupRestService extends DBSwitch {
    @Autowired
    private GroupRestRepository groupRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addGroup(GroupRequest request) {
        return groupRestRepository.addGroup(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean editGroup(GroupRequest request) {
        if (groupRestRepository.editGroup(request)) {
            groupRestRepository.deleteGroupPermissions(request.getId(), request.getCreateBy());
            List<Permission> permissions = request.getPermissions();
            if (permissions != null) {
                groupRestRepository.addGroupPermissions(permissions, request.getCreateBy());
            } else {
                return true;
            }
        } else {
            return false;
        }
        return true;
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public GroupResponse get(GetGroupRequest request) {
        return groupRestRepository.get(request.getId(), request.getGroupName());
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deleteGroup(Integer groupId, Integer userId) {
        return groupRestRepository.deleteGroup(groupId, userId);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<GroupResponse> getAllGroupList() {
        return groupRestRepository.getGroupList();
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<GroupResponse> getPageGroupList(GroupQueryParam request) {
        return groupRestRepository.getPageGroupList(request);
    }

    public List<Permission> getGroupPermissions(Integer id, String userAccount) {
        return groupRestRepository.getGroupPermissions(id, userAccount);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean editGroupPermissions(GroupRequest groupRequest) {
        groupRestRepository.deleteGroupPermissions(groupRequest.getId(), groupRequest.getCreateBy());
        List<Permission> permissions = groupRequest.getPermissions();
        if (permissions != null) {
            groupRestRepository.addGroupPermissions(permissions, groupRequest.getCreateBy());
        } else {
            return true;
        }
        return true;
    }
}
