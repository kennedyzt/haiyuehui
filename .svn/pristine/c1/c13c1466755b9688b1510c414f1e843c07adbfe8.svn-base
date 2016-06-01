package com.siping.service.permission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.permission.repository.PermissionRestRepository;
import com.siping.smartone.permission.request.AddPermissionRequest;
import com.siping.smartone.permission.request.AddUserPermissionRequest;
import com.siping.smartone.permission.response.GetButtonPermissionResponse;
import com.siping.smartone.permission.response.GetGroupPermissionResponse;
import com.siping.smartone.permission.response.GetUserPermissionResponse;

@Service
public class PermissionRestService extends DBSwitch {
    @Autowired
    private PermissionRestRepository permissionRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addPermissionByGroupId(AddPermissionRequest request) {
        return permissionRepository.addPermissionByGroupId(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean updatePermissionByGroupId(AddPermissionRequest request) {
        return permissionRepository.updatePermissionByGroupId(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addUserPermissionByUserId(AddUserPermissionRequest request) {
        return permissionRepository.addUserPermissionByUserId(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deleteUserPermissionByUserId(AddUserPermissionRequest request) {
        return permissionRepository.deleteUserPermissionByUserId(request);

    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deletePermissionByGroupId(Integer groupId) {
        return permissionRepository.deletePermissionByGroupId(groupId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean editUserPermissionByUserId(AddUserPermissionRequest request) {
        return permissionRepository.editUserPermissionByUserId(request);
    }

    public GetGroupPermissionResponse getGroupPermission(Integer groupid) {
        return permissionRepository.getGroupPermission(groupid);
    }

    public GetUserPermissionResponse getUserPermission(Integer userid) {
        return permissionRepository.getUserPermission(userid);
    }

    public GetButtonPermissionResponse getButtonPermissions(Integer userid, String url) {
        if(url.contains("=")){
            url = url.replaceAll("=", "/");
        }
        return permissionRepository.getButtonPermissions(userid, url);
    }
}
