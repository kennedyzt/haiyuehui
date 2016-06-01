package com.siping.service.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.service.menu.repository.MenuNodeRestRepository;
import com.siping.smartone.menu.request.MenuNodeQueryParam;
import com.siping.smartone.menu.request.MenuNodeRequest;
import com.siping.smartone.menu.response.MenuNodeResponse;

@Service
public class MenuNodeRestService extends DBSwitch {
    @Autowired
    private MenuNodeRestRepository menuNodeRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addMenuNode(MenuNodeRequest request) {
        return menuNodeRestRepository.addMenuNode(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deleteMenuNode(Integer id, String userId) {
        return menuNodeRestRepository.deleteMenuNode(id, userId);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean updateMenuNode(MenuNodeRequest request) {
        return menuNodeRestRepository.updateMenuNode(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public MenuNodeResponse getMenuNode(Integer id) {
        return menuNodeRestRepository.getMenuNode(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public PagingResponse<MenuNodeResponse> getList(MenuNodeQueryParam request) {
        return menuNodeRestRepository.getList(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<MenuNodeResponse> getNodeListByIds(String ids) {
        return menuNodeRestRepository.getNodeListByIds(ids);
    }
}
