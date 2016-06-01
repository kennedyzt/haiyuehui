package com.siping.smartone.login.response;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.siping.smartone.common.DateJsonDeserializer;
import com.siping.smartone.common.DateJsonSerializer;
import com.siping.smartone.group.request.Permission;
import com.siping.smartone.group.response.GroupResponse;
import com.siping.smartone.login.UserType;

public class UserLoginResponse implements Serializable {
    private static final long serialVersionUID = -5695583107089741986L;
    private Integer id;
    private String nickname;
    private String username;
    private String email;
    private String pwdHash;
    private Integer pwdIterator;
    private String pwdSalt;
    private Date lastLoginDate;
    private String local;
    private Integer objectId;
    private UserType userType;
    private List<GroupResponse> groups;
    private List<Permission> permissions;
    private String userAccount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getObjectId() {
        return objectId;
    }

    public void setObjectId(Integer employeeId) {
        this.objectId = employeeId;
    }

    public String getPwdHash() {
        return pwdHash;
    }

    public void setPwdHash(String pwdHash) {
        this.pwdHash = pwdHash;
    }

    public Integer getPwdIterator() {
        return pwdIterator;
    }

    public void setPwdIterator(Integer pwdIterator) {
        this.pwdIterator = pwdIterator;
    }

    public String getPwdSalt() {
        return pwdSalt;
    }

    public void setPwdSalt(String pwdSalt) {
        this.pwdSalt = pwdSalt;
    }

    @JsonSerialize(using = DateJsonSerializer.class)
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    @JsonDeserialize(using = DateJsonDeserializer.class)
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
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

    public List<GroupResponse> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupResponse> groups) {
        this.groups = groups;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
}
