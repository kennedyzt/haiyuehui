package com.siping.intranet.crm.menu.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.stroma.framework.core.converter.JSONConverter;
import org.stroma.framework.core.exception.BusinessProcessException;
import org.stroma.framework.core.json.JSONBinder;
import org.stroma.framework.core.page.PagingResponse;
import org.stroma.framework.core.platform.service.EndPointBuilder;
import org.stroma.framework.core.platform.service.StromaWebServiceApi;

import com.siping.integrate.constant.EndPoint;
import com.siping.smartone.login.request.UserLoginRequest;
import com.siping.smartone.menu.request.GetMenuNodeListForm;
import com.siping.smartone.menu.request.MenuNodeQueryParam;
import com.siping.smartone.menu.request.MenuNodeRequest;
import com.siping.smartone.menu.response.MenuNodeResponse;

@Repository
public class MenuNodeRepository {
    @Autowired
    private StromaWebServiceApi stromaWebServiceApi;
    @Autowired
    private JSONConverter jsonConverter;

    @Value("${site.api.appkeys}")
    private String appKey;

    public Boolean addMenuNode(MenuNodeRequest request) {
        String requestContent = JSONBinder.binder(UserLoginRequest.class).toJSON(request);
        return stromaWebServiceApi.post(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menunode/add").body(requestContent));
    }

    public Boolean deleteMenuNode(Integer id, String userId) {
        if (null == id) {
            throw new BusinessProcessException("菜单节点id不能为空");
        }
        return stromaWebServiceApi.delete(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menunode/delete/%s/%s").arguments(id, userId));
    }

    public Boolean updateMenuNode(MenuNodeRequest request) {
        String requestContent = JSONBinder.binder(MenuNodeRequest.class).toJSON(request);
        return stromaWebServiceApi.put(EndPointBuilder.create(Boolean.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menunode/update").body(requestContent));
    }

    public MenuNodeResponse getMenuNode(Integer id) {
        if (null == id) {
            throw new BusinessProcessException("菜单节点id不能为空");
        }
        return stromaWebServiceApi.get(EndPointBuilder.create(MenuNodeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menunode/get/%s").arguments(id));
    }

    public PagingResponse<MenuNodeResponse> getAllMenuNodes(Integer pageNo, Integer pageSize, GetMenuNodeListForm request) {
        if (null == pageNo && null == pageSize) {
            throw new BusinessProcessException("条件不足不能查询");
        }
        MenuNodeQueryParam userQueryParam = new MenuNodeQueryParam();
        userQueryParam.setPageNo(pageNo);
        userQueryParam.setPageSize(pageSize);
        userQueryParam.setKeywords(request.getKeywords());

        String requestContent = jsonConverter.toString(userQueryParam);
        return stromaWebServiceApi.post(EndPointBuilder.create(PagingResponse.class).elementTypes(MenuNodeResponse.class).endpoint(EndPoint.SERVICE).appKey(appKey).action("/menunode/getlist")
            .body(requestContent));
    }
}
