package com.example.zktraining.controller;

import com.example.zktraining.model.Category;
import com.example.zktraining.services.CategoryService;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.Executions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        lstCategory = categoryService.getAll();
    }

    @Command
    public void openFormAddNew(){
        Map<String, Object> arg = new HashMap<>();
        arg.put("mode", "insert");
        Window window = (Window) Executions.createComponents("category/formCategory.zul", null, arg);
        window.doModal();
    }

    @GlobalCommand
    @NotifyChange("lstCategory")
    public void onLoadDataFormInsertStudent(@BindingParam("item")Category category){
        lstCategory.add(category);
    }

}
