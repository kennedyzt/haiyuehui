package com.siping.service.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.stroma.framework.core.database.DBSwitch;

import com.siping.service.menu.repository.MenuRestRepository;
import com.siping.smartone.menu.request.MenuTreeRequest;
import com.siping.smartone.menu.response.MenuTreeResponse;

@Service
public class MenuRestService extends DBSwitch {
    @Autowired
    private MenuRestRepository menuRestRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean addMenuTree(MenuTreeRequest request) {
        return menuRestRepository.addMenuTree(request);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean deleteMenuTree(Integer id) {
        return menuRestRepository.deleteMenuTree(id);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Boolean updateMenuTree(MenuTreeRequest request) {
        return menuRestRepository.updateMenuTree(request);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public MenuTreeResponse getMenuTree(Integer id) {
        return menuRestRepository.getMenuTree(id);
    }

    @Transactional(readOnly = true, propagation = Propagation.NOT_SUPPORTED)
    public List<MenuTreeResponse> getList() {
        return menuRestRepository.getList();
    }
}
