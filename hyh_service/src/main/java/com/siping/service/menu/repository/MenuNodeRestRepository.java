package com.siping.service.menu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.stroma.framework.core.database.RowMapper;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.util.DateUtils;
import org.stroma.framework.core.util.StringUtils;

import com.siping.service.common.BillsRepository;
import com.siping.smartone.menu.request.MenuNodeQueryParam;
import com.siping.smartone.menu.request.MenuNodeRequest;
import com.siping.smartone.menu.response.MenuNodeResponse;

@Repository
public class MenuNodeRestRepository extends BillsRepository {

    public Boolean addMenuNode(MenuNodeRequest request) {
        int delete = jdbcAccessContext.execute(
            "MENU.SQL_INSERT_MENU_NODE",
            new Object[] { request.getName(), request.getSequence(), request.getDescription(), request.getParentId(), request.getLevel(), request.getIsRoot(), request.getNodeUrl(),
                    request.getMenuRoot(), request.getIcon(), request.getIsDelete(), request.getCreateBy(), request.getUserAccount() });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        addOperationLog("菜单", "新增菜单:[" + request.getName() + "]", null, request.getUpdateBy() + "");
        return Boolean.TRUE;
    }

    public Boolean deleteMenuNode(Integer id, String userId) {
        MenuNodeResponse menuNode = getMenuNode(id);
        jdbcAccessContext.execute("MENU.SQL_DELETE_MENU_NODE_RELATED", new Object[] { id });
        int delete = jdbcAccessContext.execute("MENU.SQL_DELETE_MENU_NODE", new Object[] { id });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        addOperationLog("菜单", "删除菜单:[" + menuNode.getName() + "]", null, userId);
        return Boolean.TRUE;
    }

    public MenuNodeResponse getMenuNode(Integer id) {
        MenuNodeResponse menuNode = jdbcAccessContext.findUniqueResult("MENU.SQL_GET_MENU_NODE_BY_ID", new RowMapper<MenuNodeResponse>() {
            @Override
            public MenuNodeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                MenuNodeResponse response = new MenuNodeResponse();
                response.setId(rs.getInt("id"));
                response.setName(rs.getString("node_name"));
                response.setSequence(rs.getInt("sequence"));
                response.setDescription(rs.getString("description"));
                response.setParentId(rs.getInt("parent_id"));
                response.setParentName(rs.getString("parent_name"));
                response.setIsRoot(rs.getBoolean("is_root"));
                response.setNodeUrl(rs.getString("node_url"));
                response.setMenuRoot(rs.getString("menu_root"));
                response.setIcon(rs.getString("icon"));
                response.setLevel(rs.getInt("level"));
                response.setIsDelete(rs.getBoolean("is_delete"));
                response.setCreateDate(DateUtils.date(rs.getDate("create_date"), rs.getTime("create_date")));
                response.setCreateBy(rs.getInt("create_by"));
                response.setUpdateDate(DateUtils.date(rs.getDate("update_date"), rs.getTime("update_date")));
                response.setUpdateBy(rs.getInt("update_by"));
                response.setUserAccount(rs.getString("user_account"));
                return response;
            }
        }, new Object[] { id });
        return menuNode;
    }

    public Boolean updateMenuNode(MenuNodeRequest request) {
        int delete = jdbcAccessContext.execute(
            "MENU.SQL_UPDATE_MENU_NODE",
            new Object[] { request.getName(), request.getSequence(), request.getDescription(), request.getParentId(), request.getLevel(), request.getIsRoot(), request.getNodeUrl(),
                    request.getMenuRoot(), request.getIcon(), request.getIsDelete(), request.getUpdateBy(), request.getUserAccount(), request.getId() });
        if (-1 == delete) {
            return Boolean.FALSE;
        }
        addOperationLog("菜单", "更新菜单:[" + request.getName() + "]", null, request.getCreateBy() + "");
        return Boolean.TRUE;
    }

    public PagingResponse<MenuNodeResponse> getList(MenuNodeQueryParam request) {
        PagingResponse<MenuNodeResponse> pagingResponse = new PagingResponse<MenuNodeResponse>();
        long begin = (request.getPageNo() - 1) * request.getPageSize();
        List<Object> queryParam = new ArrayList<Object>();
        List<Object> totalParams = new ArrayList<Object>();
        StringBuilder totalRecordsSql = new StringBuilder();
        List<MenuNodeResponse> response = jdbcAccessContext.findWithoutSqlManager(buildGetListSql(request, begin, queryParam, totalRecordsSql, totalParams), new RowMapper<MenuNodeResponse>() {
            @Override
            public MenuNodeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                return MenuNodeRestRepositoryHelper.buildMenuNodeResponse(rs);
            }
        }, queryParam.toArray());
        int totalRecords = jdbcAccessContext.findIntegerWithoutSqlManager(totalRecordsSql.toString(), totalParams.toArray());
        pagingResponse.setRecords(response);
        pagingResponse.setTotalRecords(totalRecords);
        return pagingResponse;
    }

    private String buildGetListSql(MenuNodeQueryParam request, long begin, List<Object> queryParam, StringBuilder total, List<Object> totalParams) {
        Integer pageSize = request.getPageSize();
        String keyWords = request.getKeywords();

        total.append("SELECT count(*) from menu_node m");
        StringBuilder sql = new StringBuilder(
            "SELECT m.id,m.node_name, m.sequence, m.description,m.parent_id,m.level,m.is_root,m.node_url,m.menu_root,m.icon,m.is_delete,m.user_account,mp.node_name parent_name FROM menu_node m left join menu_node mp on m.parent_id = mp.id");
        if (StringUtils.hasText(keyWords)) {
            sql.append(" where (m.node_name like CONCAT('%',?,'%') ) and m.is_delete = 0");
            total.append(" where (m.node_name like CONCAT('%',?,'%') ) and m.is_delete = 0");
            queryParam.add(keyWords);
        } else {
            sql.append(" where m.is_delete = 0");
            total.append(" where m.is_delete = 0");
        }

        totalParams.addAll(queryParam);
        queryParam.add(begin);
        queryParam.add(pageSize);
        sql.append(" limit ?,? ");
        return sql.toString();
    }

    public List<MenuNodeResponse> getNodeListByIds(String ids) {
        String sql = "SELECT m.id,m.node_name, m.sequence, m.description,m.parent_id,m.level,m.is_root,m.node_url,m.menu_root,m.icon,m.is_delete,m.user_account,mp.node_name parent_name FROM menu_node m left join menu_node mp on m.parent_id = mp.id where m.id in(?)";
        if (StringUtils.hasText(ids)) {
            return jdbcAccessContext.findWithoutSqlManager(sql, new RowMapper<MenuNodeResponse>() {
                @Override
                public MenuNodeResponse mapRow(ResultSet rs, int arg1) throws SQLException {
                    return MenuNodeRestRepositoryHelper.buildMenuNodeResponse(rs);
                }
            }, new Object[] { ids });
        }
        return null;
    }
}
