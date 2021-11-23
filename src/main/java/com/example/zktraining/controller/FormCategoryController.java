package com.example.zktraining.controller;

import com.example.zktraining.model.Category;
import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

import java.util.HashMap;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class FormCategoryController {

    @Getter
    @Setter
    private Category category;

    private Window window;
    @Getter
    @Setter
    private String mode;

    @Init
    public void init(){
        Execution execution = Executions.getCurrent();
        if (execution.getArg().containsKey("mode")){
            mode = (String) execution.getArg().get("mode");
        }
        category = new Category();
    }

    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
        window = (Window) view;
    }
    @Command
    public void saveStudent(){
        Map<String, Object> arg = new HashMap<>();
        arg.put("item", category);
        BindUtils.postGlobalCommand(null, null, "onLoadDataFormInsertStudent", arg);
        window.detach();
    }

    @Command
    public void cancelForm(){
        window.detach();
    }


}
