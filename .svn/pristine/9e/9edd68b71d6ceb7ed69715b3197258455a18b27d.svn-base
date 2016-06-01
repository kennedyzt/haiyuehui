package com.siping.intranet.crm.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.permission.repository.PermissionRepository;
import com.siping.smartone.permission.request.AddPermissionRequest;
import com.siping.smartone.permission.request.AddUserPermissionRequest;
import com.siping.smartone.permission.response.GetButtonPermissionResponse;
import com.siping.smartone.permission.response.GetGroupPermissionResponse;
import com.siping.smartone.permission.response.GetUserPermissionResponse;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    public Boolean addPermissionByGroupId(AddPermissionRequest request) {
        return permissionRepository.addPermissionByGroupId(request);
    }

    public Boolean updatePermissionByGroupId(AddPermissionRequest request) {
        return permissionRepository.updatePermissionByGroupId(request);
    }

    public Boolean addUserPermissionByUserId(AddUserPermissionRequest request) {
        return permissionRepository.addUserPermissionByUserId(request);
    }

    public Boolean deleteUserPermissionByUserId(AddUserPermissionRequest request) {
        return permissionRepository.deleteUserPermissionByUserId(request);
    }

    public Boolean deletePermissionByGroupId(Integer groupId) {
        return permissionRepository.deletePermissionByGroupId(groupId);
    }

    public Boolean editUserPermissionByUserId(AddUserPermissionRequest request) {
        return permissionRepository.editUserPermissionByUserId(request);
    }

    public GetGroupPermissionResponse getGroupPermission(Integer groupId) {
        return permissionRepository.getGroupPermission(groupId);
    }

    public GetUserPermissionResponse getUserPermission(Integer userId) {
        return permissionRepository.getUserPermission(userId);
    }

    public GetButtonPermissionResponse getButtonPermissions(Integer userId, String url) {
        return permissionRepository.getButtonPermissions(userId, url);
    }
}
