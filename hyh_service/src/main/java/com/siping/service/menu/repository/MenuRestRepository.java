package com.siping.service.menu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.JDBCAccessContext;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.util.DateUtils;

import com.siping.smartone.menu.request.MenuTreeRequest;
import com.siping.smartone.menu.response.MenuTreeResponse;

@Repository
public class MenuRestRepository {
    @Autowired
    private JDBCAccessContext jdbcAccessContext;

    public Boolean addMenuTree(MenuTreeRequest request) {
        int delete = jdbcAccessContext.execute("MENU.SQL_INSERT_MENU_TREE", new Object[] { request.getTreeName(), request.getDescription(), request.getIsDelete(), request.getCreateBy() });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Boolean deleteMenuTree(Integer id) {
        int delete = jdbcAccessContext.execute("MENU.SQL_DELETE_MENU_TREE", new Object[] { id });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public MenuTreeResponse getMenuTree(Integer id) {
        return jdbcAccessContext.findUniqueResult("MENU.SQL_GET_MENU_TREE_BY_ID", new RowMapper<MenuTreeResponse>() {
            @Override
            public MenuTreeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMenuTree(rs);
            }
        }, new Object[] { id });
    }

    private MenuTreeResponse buildMenuTree(ResultSet rs) throws SQLException {
        MenuTreeResponse response = new MenuTreeResponse();
        response.setId(rs.getInt("id"));
        response.setTreeName(rs.getString("tree_name"));
        response.setDescription(rs.getString("description"));
        response.setIsDelete(rs.getBoolean("is_delete"));
        response.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
        response.setCreateBy(rs.getInt("create_by"));
        response.setUpdateDate(DateUtils.date(rs.getDate("update_date"), rs.getTime("update_date")));
        response.setUpdateBy(rs.getInt("update_by"));
        return response;
    }

    public Boolean updateMenuTree(MenuTreeRequest request) {
        int delete = jdbcAccessContext.execute("MENU.SQL_UPDATE_MENU_TREE",
            new Object[] { request.getTreeName(), request.getDescription(), request.getIsDelete(), request.getUpdateBy(), request.getId() });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public List<MenuTreeResponse> getList() {
        return jdbcAccessContext.find("MENU.SQL_GET_MENU_TREE_LIST", new RowMapper<MenuTreeResponse>() {
            @Override
            public MenuTreeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return buildMenuTree(rs);
            }
        });
    }
}
