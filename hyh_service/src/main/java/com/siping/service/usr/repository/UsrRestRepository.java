package com.siping.service.usr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.PreparedStatementCreatorImpl;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DigestUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.CommonRepository;
import com.siping.smartone.login.request.AddLocalRequest;
import com.siping.smartone.login.request.AddUserRequest;
import com.siping.smartone.login.request.UpdateUserRequest;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.login.request.UserQueryParam;
import com.siping.smartone.login.response.UserInfoResponse;
import com.siping.smartone.login.response.UserLoginResponse;

@Repository
public class UsrRestRepository extends CommonRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public UserLoginResponse login(UserLoginRequest request) {
        UserLoginResponse usr = getUserByUsername(request.getUsername());
        if (null == usr)
            throw new BusinessProcessException("该用户不存在！");
        String pwdHash = DigestUtils.calculatePasswordHashWithSha512(request.getPassword(), usr.getPwdSalt(), usr.getPwdIterator());
        if (!pwdHash.equalsIgnoreCase(usr.getPwdHash())) {
            throw new BusinessProcessException("登录密码错误！");
        }
        return usr;
    }

    private UserLoginResponse getUserByUsername(String username) {
        List<UserLoginResponse> userLoginResponses = jdbcAccessContext.find("USERS.SQL_GET_USER_BY_USERNAME", new RowMapper<UserLoginResponse>() {
            @Override
            public UserLoginResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UsrRestRepositoryHelper.buildUserLoginResponse(rs);
            }
        }, new Object[] { username });
        if (CollectionUtils.isEmpty(userLoginResponses)) {
            return null;
        }
        return userLoginResponses.get(0);
    }

    public Boolean add(AddUserRequest request) {
        String pwdSalt = RandomStringUtils.randomAlphanumeric(6);
        int pwdIteration = RandomUtils.nextInt(10) + 1;
        String pwdHash = DigestUtils.calculatePasswordHashWithSha512(request.getPwd(), pwdSalt, pwdIteration);
        if (null != getUserByUsername(request.getUserName())) {
            throw new BusinessProcessException("不能重复添加用户！");
        }
        Object[] addParams = new Object[] { request.getUserName(), request.getNickName(), request.getEmail(), false, request.getObjectId(), pwdHash, pwdIteration, pwdSalt,
                request.getUserType().toString(), request.getCreateBy(), request.getUserAccount() };

        int add = -1;
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            add = jdbcAccessContext.executeAndReturnPrimarykey(new PreparedStatementCreatorImpl("USERS.SQL_ADD_USER", addParams, jdbcAccessContext), keyHolder);
            request.setId(Integer.parseInt(keyHolder.getKey().toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (-1 == add) {
            return Boolean.FALSE;
        }
        addOperationLog("用户", "新增用户:[" + request.getUserName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean edit(UpdateUserRequest request) {
        String pwdSalt;
        int pwdIteration;
        String pwdHash;

        UserLoginResponse usr = getUserById(request.getId());
        if (null == usr)
            throw new BusinessProcessException("用户不存在，无法编辑！");
        if (!request.getPassword().equals(usr.getPwdHash())) {
            pwdSalt = RandomStringUtils.randomAlphanumeric(6);
            pwdIteration = RandomUtils.nextInt(10) + 1;
            pwdHash = DigestUtils.calculatePasswordHashWithSha512(request.getPassword(), pwdSalt, pwdIteration);
        } else {
            pwdSalt = usr.getPwdSalt();
            pwdIteration = usr.getPwdIterator();
            pwdHash = usr.getPwdHash();
        }

        Object[] editParams = new Object[] { request.getNickName(), request.getEmail(), pwdHash, pwdIteration, pwdSalt, request.getUserType().toString(), request.getCreateBy(),
                request.getUserAccount(), request.getId() };

        int edit = -1;
        try {
            edit = jdbcAccessContext.execute("USERS.SQL_EDIT_USER", editParams);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (-1 == edit) {
            return Boolean.FALSE;
        }
        addOperationLog("用户", "更新用户:[" + usr.getUsername() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    private UserLoginResponse getUserById(Integer id) { // 校验用户是否存在或者查询用户
        List<UserLoginResponse> userLoginResponses = jdbcAccessContext.find("USERS.SQL_GET_USER_BY_ID", new RowMapper<UserLoginResponse>() {
            @Override
            public UserLoginResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UsrRestRepositoryHelper.buildUserLoginResponse(rs);
            }
        }, new Object[] { id });
        if (CollectionUtils.isEmpty(userLoginResponses)) {
            return null;
        }
        return userLoginResponses.get(0);
    }

    public Boolean resetPwd(UpdateUserRequest request) {
        UserLoginResponse usr = getUserById(request.getId());
        if (null == usr)
            throw new BusinessProcessException("用户不存在，无法修改密码！");
        String pwdHash = DigestUtils.calculatePasswordHashWithSha512(request.getOldPwd(), usr.getPwdSalt(), usr.getPwdIterator());
        if (!usr.getPwdHash().equalsIgnoreCase(pwdHash)) {
            throw new BusinessProcessException("原密码输入不正确！");
        }
        String rpwdHash = DigestUtils.calculatePasswordHashWithSha512(request.getPassword(), usr.getPwdSalt(), usr.getPwdIterator());
        int resetPwd = jdbcAccessContext.executeWithoutSqlManager("update usr set pwd_hash = ?,update_by = ? where id = ?", new Object[] { rpwdHash, request.getUpdateBy(), request.getId() });
        if (-1 == resetPwd) {
            return Boolean.FALSE;
        }
        addOperationLog("用户", "重置用户:[" + usr.getUsername() + "]密码", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public UserLoginResponse get(UpdateUserRequest request) {
        List<Object> queryParam = new ArrayList<Object>();
        List<UserLoginResponse> list = jdbcAccessContext.findWithoutSqlManager(UsrRestRepositoryHelper.loadDynamicGetUserSql(request, queryParam), new RowMapper<UserLoginResponse>() {
            @Override
            public UserLoginResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UsrRestRepositoryHelper.buildUserLoginResponse(rs);
            }
        }, queryParam.toArray());
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        throw new BusinessProcessException("用户不存在！");
    }

    public List<UserLoginResponse> getList() {
        return jdbcAccessContext.find("USERS.SQL_GET_ALL_USER", new RowMapper<UserLoginResponse>() {
            @Override
            public UserLoginResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UsrRestRepositoryHelper.buildUserLoginResponse(rs);
            }
        });
    }

    public Boolean delete(Integer id, String userId) {
        UserLoginResponse usr = getUserById(id);
        if (null == usr)
            throw new BusinessProcessException("用户不存在，无法删除！");
        int delete = jdbcAccessContext.execute("USERS.SQL_DELETE_USER", new Object[] { id });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        addOperationLog("用户", "停用用户:[" + usr.getUsername() + "]", null, userId);
        return Boolean.TRUE;
    }

    public Boolean deleteUserRelated(Integer id) {
        UserLoginResponse usr = getUserById(id);
        if (null == usr)
            throw new BusinessProcessException("用户不存在，无法删除！");
        int delete_related = jdbcAccessContext.execute("USERS.SQL_DELETE_USER_RELATED", new Object[] { id });
        if (-1 == delete_related) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean languageSwitch(AddLocalRequest request) {
        if (null == getUserById(Integer.valueOf(request.getUserId())))
            throw new BusinessProcessException("用户不存在！");
        int update = jdbcAccessContext.execute("USERS.SQL_UPDATE_USER_LANGUAGE", new Object[] { request.getLanguage(), Integer.valueOf(request.getUserId()) });
        if (-1 == update) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public PagingResponse<UserInfoResponse> getList(UserQueryParam request) {
        PagingResponse<UserInfoResponse> pagingResponse = new PagingResponse<UserInfoResponse>();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        List<UserInfoResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams), new RowMapper<UserInfoResponse>() {
            @Override
            public UserInfoResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return UsrRestRepositoryHelper.buildUserInfoResponse(rs);
            }
        }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    private String buildGetListSql(UserQueryParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        String keyWords = request.getKeywords();
        Boolean isEnable = request.getIsEnable();
        total.append("SELECT count(*) from usr u where 1=1");
        StringBuilder sql = new StringBuilder(
            "SELECT u.id, u.user_name, u.nickname,u.email, u.is_delete, u.local,u.object_id,b.partner_name,u.user_type, u.user_account from usr u LEFT JOIN business_partner b ON u.object_id = b.id where 1=1");
        if (StringUtils.hasText(keyWords)) {
            sql.append(" and (u.user_name like CONCAT('%',?,'%') or u.nickname like CONCAT('%',?,'%'))");
            total.append(" and (u.user_name like CONCAT('%',?,'%') or u.nickname like CONCAT('%',?,'%'))");
            queryParam.add(keyWords);
            queryParam.add(keyWords);
        }
        if (isEnable != null) {
            sql.append(" and u.is_delete = ?");
            total.append(" and u.is_delete = ?");
            queryParam.add(request.getIsEnable() == true ? 0 : 1);
        } else {
            sql.append(" and u.is_delete = 0");
            total.append(" and u.is_delete = 0");
        }
        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" order by u.id desc ");
        sql.append(" limit ?,? ");
        return sql.toString();
    }

}
