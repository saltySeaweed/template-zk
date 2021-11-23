package com.example.zktraining.controller;

import com.example.zktraining.model.Category;
import com.example.zktraining.services.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;


import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class CategoryController {
    @WireVariable
    CategoryService categoryService;

    @Getter
    @Setter
    public List<Category> lstCategory;

    @Getter
    @Setter
    public Category category;


    @Init
    public void init(){
        initObject();
    }
    private void initObject(){
//        lstCategory = new ArrayList<>();
        lstCategory = categoryService.getAll();
    }

}
