package com.example.zktraining.services.impl;

import com.example.zktraining.model.Category;
import com.example.zktraining.repo.CategoryRepository;
import com.example.zktraining.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.zk.ui.select.annotation.WireVariable;

import java.util.List;
@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
