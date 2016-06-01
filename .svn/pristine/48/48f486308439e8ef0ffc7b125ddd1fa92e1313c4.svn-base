package com.siping.intranet.crm.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siping.intranet.crm.menu.repository.MenuRepository;
import com.siping.smartone.menu.request.MenuTreeRequest;
import com.siping.smartone.menu.response.MenuTreeResponse;

@Service
public class MenuService {
    @Autowired
    private MenuRepository menuRepository;

    public Boolean addMenuTree(MenuTreeRequest request) {
        return menuRepository.addMenuTree(request);
    }

    public Boolean deleteMenuTree(Integer id) {
        return menuRepository.deleteMenuTree(id);
    }

    public Boolean updateMenuTree(MenuTreeRequest request) {
        return menuRepository.updateMenuTree(request);
    }

    public MenuTreeResponse getMenuTree(Integer id) {
        return menuRepository.getMenuTree(id);
    }

    public List<MenuTreeResponse> getTreeList() {
        return menuRepository.getTreeList();
    }
}
