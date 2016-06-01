package com.siping.intranet.crm.menu.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.menu.request.MenuTreeRequest;
import com.siping.smartone.menu.response.MenuTreeResponse;

@Repository
public class MenuRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean addMenuTree(MenuTreeRequest request) {
        String requestContent = JSONBinder.binder(UserLoginRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menutree/add").body(requestContent));
    }

    public Boolean deleteMenuTree(Integer id) {
        if (null == id) {
            throw new BusinessProcessException("菜单树id不能为空");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menutree/delete/%s").arguments(id));
    }

    public Boolean updateMenuTree(MenuTreeRequest request) {
        String requestContent = JSONBinder.binder(MenuTreeRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menutree/edit").body(requestContent));
    }

    public MenuTreeResponse getMenuTree(Integer id) {
        if (null == id) {
            throw new BusinessProcessException("菜单数id不能为空");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(MenuTreeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menutree/get/%s").arguments(id));
    }

    @SuppressWarnings("unchecked")
    public List<MenuTreeResponse> getTreeList() {
        return stromaWebServiceApi.get(EndPointBuilder.create(List.class).elementTypes(MenuTreeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menutree/getlist"));
    }
}
