package com.example.zktraining.services;

import com.example.zktraining.dto.SaleDTO;
import com.example.zktraining.model.Sale;
import org.springframework.stereotype.Service;

import java.util.List;
@Service(value = "saleService")
public interface SaleService {

    List<SaleDTO> findAll();

    SaleDTO findById(Integer id);

    SaleDTO create(SaleDTO sale);

    SaleDTO update(SaleDTO saleDTO);

    Boolean delete(Integer id);
}
