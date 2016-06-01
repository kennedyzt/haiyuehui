package com.siping.service.usr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.DigestUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.smartone.login.UserType;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.login.response.UserLoginResponse;

public class UsrRestRepositoryHelper {
    public static UserLoginResponse buildUserLoginResponse(final ResultSet rs) throws SQLException {
        UserLoginResponse u = new UserLoginResponse();
        u.setId(rs.getInt("id"));
        u.setUsername(rs.getString("user_name"));
        u.setNickname(rs.getString("nickname"));
        u.setEmail(rs.getString("email"));
        u.setObjectId(rs.getInt("object_id"));
        UserType utype = UserType.create(rs.getString("user_type"));
        u.setUserType(utype);
        u.setPwdHash(rs.getString("pwd_hash"));
        u.setPwdIterator(rs.getInt("pwd_iterator"));
        u.setPwdSalt(rs.getString("pwd_salt"));
        u.setLastLoginDate(DateUtils.date(rs.getDate("last_login_date"), rs.getTime("last_login_date")));
        u.setLocal(rs.getString("local"));
        u.setUserAccount(rs.getString("user_account"));
        return u;
    }

    public static UserInfoResponse buildUserInfoResponse(ResultSet rs) throws SQLException{
        UserInfoResponse u = new UserInfoResponse();
        u.setId(rs.getInt("id"));
        u.setUserName(rs.getString("user_name"));
        u.setNickName(rs.getString("nickname"));
        UserType utype = UserType.create(rs.getString("user_type"));
        u.setUserType(utype);
        u.setEmail(rs.getString("email"));
        u.setObjectId(rs.getInt("object_id"));
        u.setLocal(rs.getString("local"));
        u.setUserAccount(rs.getString("user_account"));
        u.setPartnerName(rs.getString("partner_name"));
        u.setIsDeleted(rs.getInt("is_delete"));
        return u;
    }

    public static String loadDynamicUpdateUserSql(UpdateUserRequest request, UserLoginResponse usr, List<Object> updateParam) {
        StringBuilder sql = new StringBuilder("UPDATE usr SET ");
        int count = 0;
        count = assembleParams(request, updateParam, sql, count);
        count = assembleGroupAndEmployee(request, updateParam, sql, count);
        assemblePwdAndUpdateBy(request, usr, updateParam, sql, count);
        sql.append("where id=?");
        updateParam.add(request.getId());
        return sql.toString();
    }

    private static void assemblePwdAndUpdateBy(UpdateUserRequest request, UserLoginResponse usr, List<Object> updateParam, StringBuilder sql, int count) {
        if (StringUtils.hasText(request.getPassword())) {
            if (count != 0)
                sql.append(",pwd_hash=? ");
            else
                sql.append("pwd_hash=? ");
            updateParam.add(DigestUtils.calculatePasswordHashWithSha512(request.getPassword(), usr.getPwdSalt(), usr.getPwdIterator()));
            count++;
        }
        if (null != request.getUpdateBy() && 0 != request.getUpdateBy()) {
            if (count != 0)
                sql.append(",update_by=? ");
            else
                sql.append("update_by=? ");
            updateParam.add(request.getUpdateBy());
        }
    }

    private static int assembleGroupAndEmployee(UpdateUserRequest request, List<Object> updateParam, StringBuilder sql, int count) {
        if (null != request.getObjectId() && 0 != request.getObjectId()) {
            if (count != 0)
                sql.append(",employee_id=? ");
            else
                sql.append("employee_id=? ");
            updateParam.add(request.getObjectId());
            count++;
        }
        return count;
    }

    private static int assembleParams(UpdateUserRequest request, List<Object> updateParam, StringBuilder sql, int count) {
        if (StringUtils.hasText(request.getNickName())) {
            sql.append("nickname=? ");
            updateParam.add(request.getNickName());
            count++;
        }
        if (null != request.getIsDelete() && request.getIsDelete()) {
            if (count != 0)
                sql.append(",is_delete=? ");
            else
                sql.append("is_delete=? ");
            updateParam.add(request.getIsDelete());
            count++;
        }
        return count;
    }

    public static String loadDynamicGetUserSql(UpdateUserRequest request, List<Object> updateParam) {
        StringBuilder sql = new StringBuilder(
            "SELECT u.id,u.user_name,u.nickname,u.email,u.object_id,u.pwd_hash,u.pwd_iterator,u.pwd_salt,u.last_login_date,u.`local`,u.user_type,u.user_account FROM usr u where u.is_delete=0");
        if (null != request.getId()) {
            sql.append(" and u.id=? ");
            updateParam.add(request.getId());
        }
        if (StringUtils.hasText(request.getUserName())) {
            sql.append(" and u.user_name=?");
            updateParam.add(request.getUserName());
        }
        return sql.toString();
    }
}
