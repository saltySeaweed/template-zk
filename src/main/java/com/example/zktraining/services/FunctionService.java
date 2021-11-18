/**
 * @author  Vinhcv
 * @version 1.0
 * @since   2020-08-28
 */

package com.example.zktraining.services;

import com.example.zktraining.ZkTrainingApplication;
import com.example.zktraining.dto.MenuDTO;
import com.example.zktraining.model.Function;
import com.example.zktraining.repo.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FunctionService {
    @Autowired
    private FunctionRepository functionRepository;

    /**
     * start - static MenusService
     */
    public static List<MenuDTO> menus;

    public FunctionService() {
        menus = findAllByIsActive(true);
    }

    /**
     * end - static MenusService
     */
    public List<MenuDTO> findAllByIsActive(Boolean isActive) {
        List<Function> menuList = ZkTrainingApplication.ctx.getBean(FunctionRepository.class).findAllByStatusAndParentIsNullOrderByOrdAsc(isActive);
        if (menuList == null) return null;
        List<MenuDTO> lstRes = new ArrayList<>();
        for (Function item : menuList) {
            lstRes.add(item.toDTO());
        }
        return lstRes;
    }
    public MenuDTO findById(Integer id){
        Function function = ZkTrainingApplication.ctx.getBean(FunctionRepository.class).findByFunctionId(id);
        MenuDTO menuDTO = function.toDTO();
        return menuDTO;
    }

}
