package com.example.zktraining.controller;

import com.example.zktraining.dto.SaleDTO;
import com.example.zktraining.services.SaleService;
import com.example.zktraining.services.impl.SaleServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import java.util.ArrayList;
import java.util.List;

@VariableResolver(DelegatingVariableResolver.class)
public class SaleController {

    @WireVariable
    SaleService saleService;

    @Getter
    @Setter
    private List<SaleDTO> listAll;

    @Init
    public void init(){
        System.out.println("h1");
        listAll = new ArrayList<>();
        listAll = null;
        findAllSale();
    }

    public void findAllSale(){
        System.out.println("h2");
        listAll = saleService.findAll();
        System.out.println("h3");
        listAll.forEach(s ->{
            System.out.println(s);
        });
    }

}
