package com.siping.service.menu.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.siping.smartone.menu.response.MenuNodeResponse;

public class MenuNodeRestRepositoryHelper {
    public static MenuNodeResponse buildMenuNodeResponse(ResultSet rs) throws SQLException{
        MenuNodeResponse menuNode = new MenuNodeResponse();
        menuNode.setId(rs.getInt("id"));
        menuNode.setName(rs.getString("node_name"));
        menuNode.setSequence(rs.getInt("sequence"));
        menuNode.setDescription(rs.getString("description"));
        menuNode.setLevel(rs.getInt("level"));
        menuNode.setParentName(rs.getString("parent_name"));
        menuNode.setParentId(rs.getInt("parent_id"));
        menuNode.setIsRoot(rs.getBoolean("is_root"));
        menuNode.setNodeUrl(rs.getString("node_url"));
        menuNode.setMenuRoot(rs.getString("menu_root"));
        menuNode.setIcon(rs.getString("icon"));
        menuNode.setIsDelete(rs.getBoolean("is_delete"));
        menuNode.setUserAccount(rs.getString("user_account"));
        return menuNode;
    }
}
