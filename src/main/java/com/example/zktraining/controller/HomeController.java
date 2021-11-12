package com.example.zktraining.controller;

import lombok.Getter;
import lombok.Setter;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver(DelegatingVariableResolver.class)
public class HomeController {
    @Getter
    @Setter
    private String title;
    @Init
    public void init(){
        title = "Phuc Oc Cho";
    }
}
