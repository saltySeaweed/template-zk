package com.example.zktraining.controller;

import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

import java.util.HashMap;
import java.util.Map;

@VariableResolver(DelegatingVariableResolver.class)
public class FormController {
    @Getter
    @Setter
    private Student student;

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
        student = new Student();
    }

    @AfterCompose
    public void doAfterCompose(@ContextParam(ContextType.VIEW) Component view){
        window = (Window) view;
    }


    @Command
    public void saveStudent(){
        Map<String, Object> arg = new HashMap<>();
        arg.put("item", student);
        BindUtils.postGlobalCommand(null, null, "onLoadDataFormInsertStudent", arg);
        window.detach();
    }

    @Command
    public void cancelForm(){
        window.detach();
    }

}
