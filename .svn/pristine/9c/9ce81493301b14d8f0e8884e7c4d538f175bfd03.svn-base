package com.siping.smartone.login.response;

import java.io.Serializable;
import java.util.List;

import com.siping.smartone.login.UserType;

public class UserInfoResponse implements Serializable {
    private static final long serialVersionUID = -7900031651306555754L;
    private Integer id; // 新增时，该字段为空
    private String userName;
    private String nickName;
    private String email;
    private Boolean hasPermission; // 新增时，该字段为false
    private Integer objectId; // 新增时，该字段可关联员工信息
    private String partnerName; 
    private Integer createBy; // 新增时，该字段为空
    private String pwd; // 新增时，该字段必须有值
    private List<Integer> groups;
    private String local;
    private UserType userType;
    private String userAccount;
    private Integer isDeleted;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getNickName() {
        return nickName;
    }
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Boolean getHasPermission() {
        return hasPermission;
    }
    public void setHasPermission(Boolean hasPermission) {
        this.hasPermission = hasPermission;
    }
    public Integer getObjectId() {
        return objectId;
    }
    public void setObjectId(Integer employeeId) {
        this.objectId = employeeId;
    }
    public Integer getCreateBy() {
        return createBy;
    }
    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public List<Integer> getGroups() {
        return groups;
    }
    public void setGroups(List<Integer> groups) {
        this.groups = groups;
    }
    public String getLocal() {
        return local;
    }
    public void setLocal(String local) {
        this.local = local;
    }
    public UserType getUserType() {
        return userType;
    }
    public void setUserType(UserType userType) {
        this.userType = userType;
    }
    public String getUserAccount() {
        return userAccount;
    }
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    public String getPartnerName() {
        return partnerName;
    }
    public void setPartnerName(String partnerName) {
        this.partnerName = partnerName;
    }
    public Integer getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }
}
