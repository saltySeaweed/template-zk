package com.example.zktraining.services.impl;

import com.example.zktraining.dto.SaleDTO;
import com.example.zktraining.model.Sale;
import com.example.zktraining.repo.SaleRepository;
import com.example.zktraining.services.SaleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository repository;

    @Override
    public List<SaleDTO> findAll() {
        System.out.println("1");
//        List<Sale> saleList = new ArrayList<>();
////              saleList=  repository.findAll();
//        System.out.println("2");
        List<SaleDTO> list = new ArrayList<>();
//        saleList.forEach(sale -> {
//            SaleDTO dto = new SaleDTO();
//            System.out.println("3");
//            BeanUtils.copyProperties(sale,dto);
//            System.out.println("4");
//            list.add(dto);
//        });
        return list;
    }

    @Override
    public SaleDTO findById(Integer id) {
        Sale sale = repository.getById(id);
        SaleDTO dto = new SaleDTO();
        BeanUtils.copyProperties(sale,dto);
        return dto;
    }

    @Override
    public SaleDTO create(SaleDTO sale) {
        return null;
    }

    @Override
    public SaleDTO update(SaleDTO saleDTO) {
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        return null;
    }
}
