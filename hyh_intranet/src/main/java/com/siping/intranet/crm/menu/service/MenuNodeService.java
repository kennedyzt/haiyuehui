package com.siping.intranet.crm.menu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.stroma.framework.core.page.PagingResponse;

import com.siping.intranet.crm.menu.repository.MenuNodeRepository;
import com.siping.smartone.menu.request.GetMenuNodeListForm;
import com.siping.smartone.menu.request.MenuNodeRequest;
import com.siping.smartone.menu.response.MenuNodeResponse;

@Service
public class MenuNodeService {
    @Autowired
    private MenuNodeRepository menuNodeRepository;

    public Boolean addMenuNode(MenuNodeRequest request) {
        Integer parentId = request.getParentId();
        if (parentId == null || parentId == 0) {
            request.setLevel(0);
        } else {
            MenuNodeResponse parentNode = menuNodeRepository.getMenuNode(parentId);
            request.setLevel(parentNode.getLevel() + 1);
        }

        return menuNodeRepository.addMenuNode(request);
    }

    public Boolean deleteMenuNode(Integer id, String userId) {
        return menuNodeRepository.deleteMenuNode(id, userId);
    }

    public Boolean updateMenuNode(MenuNodeRequest request) {
        Integer parentId = request.getParentId();
        if (parentId == null || parentId == 0) {
            request.setLevel(0);
        } else {
            MenuNodeResponse parentNode = menuNodeRepository.getMenuNode(parentId);
            request.setLevel(parentNode.getLevel() + 1);
        }

        return menuNodeRepository.updateMenuNode(request);
    }

    public MenuNodeResponse getMenuNode(Integer id) {
        return menuNodeRepository.getMenuNode(id);
    }

    public PagingResponse<MenuNodeResponse> getAllMenuNodes(Integer pageNo, Integer pageSize, GetMenuNodeListForm request) {
        return menuNodeRepository.getAllMenuNodes(pageNo, pageSize, request);
    }
}
