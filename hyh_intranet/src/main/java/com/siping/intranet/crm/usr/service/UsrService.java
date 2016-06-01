package com.siping.intranet.crm.usr.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DigestUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.intranet.crm.group.repository.GroupRepository;
import com.siping.intranet.crm.usr.repository.UsrRepository;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.login.request.AddUserRequest;
import com.siping.smartone.login.request.GetUserListForm;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.login.response.UserLoginResponse;

@Service
public class UsrService {
    @Autowired
    private UsrRepository usrRepository;

    @Autowired
    private GroupRepository groupRepository;

    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse user = new UserLoginResponse();
        if("admin".equals(request.getUsername())){
            Properties prop = new Properties();
            try {
                String pwdHash = DigestUtils.calculatePasswordHashWithSha512(request.getPassword(), "AR1SYc", 7);
                String filePath = getResourcePath("i18n/identify.properties");
                FileInputStream in = new FileInputStream(new File(filePath));
                prop.load(in);
                in.close();
                String pwd = prop.getProperty("siping.i18n.sys.identify");
                if(pwdHash.equals(pwd)){
                    user.setId(0);
                    user.setNickname("系统超级管理员");
                    user.setPwdHash(pwd);
                    user.setPwdIterator(7);
                    user.setPwdSalt("AR1SYc");
                    user.setUsername(request.getUsername());
                    user.setObjectId(null);
                    user.setLocal("CHINESE");
                    return user;
                }else{
                    throw new BusinessProcessException("登录密码错误！");
                }
            } catch (FileNotFoundException e) {
                throw new BusinessProcessException("identify.properties is not found!");
            } catch (IOException e) {
                throw new BusinessProcessException("IO Exception occured");
            } catch (Exception e) {
                throw new BusinessProcessException("Exception occured");
            }
        }else{
            user = usrRepository.login(request);
            if(!"BusiPartner".equals(user.getUserType().toString())){
                List<GroupResponse> groups = usrRepository.getGroupsOfUser(user.getId());
                user.setGroups(groups);
                List<Permission> allpermissions = loadPermissions(groups, user.getUserAccount());
                user.setPermissions(allpermissions);
            }
        }
        return user;
    }

    /**
     * 根据资源名称，计算当前资源的绝对路径.
     * @param _sResourceName : TcdApp.ini
     * @return 在服务器端的完整绝对物理路径
     * @throws Exception
     */
    private String getResourcePath(String _sResourceName) throws Exception {
        URL url = Thread.currentThread().getContextClassLoader().getResource(_sResourceName);
        if (url == null) {
            throw new BusinessProcessException("资源文件[" + _sResourceName + "]没有找到！");
        }
        // 匹配绝对路径
        String sAbsolutePath = null;
        try {
            sAbsolutePath = url.getFile();
            if (sAbsolutePath.indexOf("%") >= 0) {
                // 转换编码为GBK
                String sEncoding = System.getProperty("file.encoding", "GBK");
                sAbsolutePath = URLEncoder.encode(sAbsolutePath, sEncoding);
            }
        } catch (Exception ex) {
            throw new Exception("转换文件[" + url.getFile() + "]的编码失败！", ex);
        }
        return sAbsolutePath;
    }

    public Boolean add(AddUserRequest request) {
        return usrRepository.add(request);
    }

    public Boolean edit(UpdateUserRequest request) {
        return usrRepository.edit(request);
    }

    public Boolean resetPwd(Integer id, String oldPwd, String pwd, Integer updateBy) throws Exception {
        if (null == id) {
            throw new BusinessProcessException("请重新登录！");
        }
        if (!StringUtils.hasText(oldPwd)) {
            throw new BusinessProcessException("请输入原密码！");
        }
        if (!StringUtils.hasText(pwd)) {
            throw new BusinessProcessException("请输入新密码！");
        }
        if(id == 0){
            Properties prop = new Properties();
            String pwdHash = DigestUtils.calculatePasswordHashWithSha512(oldPwd, "AR1SYc", 7);
            String filePath = getResourcePath("i18n/identify.properties");
            FileInputStream in = new FileInputStream(new File(filePath));
            prop.load(in);
            in.close();
            String adminPwd = prop.getProperty("siping.i18n.sys.identify");
            if(!pwdHash.equals(adminPwd)){
                throw new BusinessProcessException("原密码不正确！");
            }
            String newpwdHash = DigestUtils.calculatePasswordHashWithSha512(pwd, "AR1SYc", 7);
            FileOutputStream fos = new FileOutputStream(new File(filePath));
            prop.setProperty("siping.i18n.sys.identify", newpwdHash);
            prop.store(fos, "siping.i18n.sys.identify");
            fos.close();
            return true;
        }
        return usrRepository.resetPwd(id, oldPwd, pwd, updateBy);
    }

    public UserLoginResponse get(int id, String name) {
        UserLoginResponse user =  usrRepository.get(id, name);
        List<GroupResponse> groups = usrRepository.getGroupsOfUser(user.getId());
        user.setGroups(groups);
        return user;
    }

    public List<UserLoginResponse> getList() {
        return usrRepository.getList();
    }

    public Boolean delete(Integer id, Integer updateBy) {
        return usrRepository.delete(id, updateBy);
    }

    public Boolean languageSwitch(String userId, String language) {
        return usrRepository.languageSwitch(userId, language);
    }

    public PagingResponse<UserInfoResponse> getAllUsers(int pageNo, int pageSize, GetUserListForm request) {
        return usrRepository.getAllUsers(pageNo, pageSize, request);
    }

    private List<Permission> loadPermissions(List<GroupResponse> groups, String userAccount) {
        List<Permission> allpermissions = new LinkedList<Permission>();
        if (groups != null && groups.size() != 0) {
            for(GroupResponse group :  groups){
                List<Permission> permissions = groupRepository.getGroupPermissions(group.getId(), userAccount);
                if (permissions == null || permissions.size() == 0) {
                    continue;
                }

                for(Permission permission : permissions){
                    if (permission.getIsAdd() || permission.getIsDelete() || permission.getIsEdit() || permission.getIsPrint()) {
                        boolean has = false;
                        Permission targetPermission = null;
                        for(Permission existPermission : allpermissions){
                            if(permission.getNodeId() == existPermission.getNodeId()){
                                targetPermission = existPermission;
                                has = true;
                                break;
                            }
                        }
                        if(has){
                            if(permission.getIsAdd()){
                                targetPermission.setIsAdd(true);
                            }
                            if(permission.getIsDelete()){
                                targetPermission.setIsDelete(true);
                            }
                            if(permission.getIsEdit()){
                                targetPermission.setIsEdit(true);
                            }
                            if(permission.getIsPrint()){
                                targetPermission.setIsPrint(true);
                            }
                        }else{
                            allpermissions.add(permission);
                        }
                    }
                }
            }
        }
        List<Permission> all_permissions = selectionSort(allpermissions);
        return all_permissions;
    }
    private List<Permission> selectionSort(List<Permission> permissions) {
        List<Permission> all_Permissions = new LinkedList<Permission>();
        int length = permissions.size();
        for(int i=0; i<length; i++){
            int minIndex = minSelection(permissions);
            Permission per = permissions.get(minIndex);
            all_Permissions.add(per);
            permissions.remove(minIndex);
        }
        return all_Permissions;
    }
    private Integer minSelection (List<Permission> permissions) {
        if(permissions.size() > 0) {
            Permission minSeqPermission = permissions.get(0);;//最小顺序值对应的Permission
            int minSeq = minSeqPermission.getSequence(); //最小顺序值
            int minIndex = 0;//最小顺序值对应的Permission的链表游标
            for(int i=1; i<permissions.size(); i++){
                Permission per = permissions.get(i);
                if(per.getSequence() < minSeq) {
                    minSeq = per.getSequence();
                    minIndex = i;
                    minSeqPermission = per;
                }
            } 
            return minIndex;
        } else {
            return -1;
        }
    }
}
