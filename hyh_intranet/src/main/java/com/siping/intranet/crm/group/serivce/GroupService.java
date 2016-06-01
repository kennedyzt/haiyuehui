package com.siping.intranet.crm.group.serivce;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.group.repository.GroupRepository;
import com.siping.smartone.group.request.GetGroupListForm;
import com.siping.smartone.group.request.GroupRequest;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public Boolean addGroup(GroupRequest request) {
        return groupRepository.addGroup(request);
    }

    public Boolean editGroup(GroupRequest request) {
        return groupRepository.editGroup(request);
    }

    public GroupResponse get(String id, String groupName) {
        return groupRepository.get(id, groupName);
    }

    /**
     * 用户返回所有可用的用户组，添加用户时使用
     * @return
     */
    public List<GroupResponse> getAllGroups() {
        return groupRepository.getAllGroups();
    }

    public Boolean deleteGroup(Integer groupId,Integer deleteUserId) {
        return groupRepository.deleteGroup(groupId,deleteUserId);
    }

    public PagingResponse<GroupResponse> getAllGroups(Integer pageNo, Integer pageSize, GetGroupListForm request) {
        return groupRepository.getAllGroups(pageNo, pageSize, request);
    }

    public List<Permission> getGroupPermissions(Integer id, String userAccount) {
        return groupRepository.getGroupPermissions(id, userAccount);
    }

    public Boolean editGroupPermissions(GroupRequest permissions) {
        return groupRepository.editGroupPermissions(permissions);
    }

}
