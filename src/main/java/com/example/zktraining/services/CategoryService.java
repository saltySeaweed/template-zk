package com.example.zktraining.services;

import com.example.zktraining.controller.CategoryController;
import com.example.zktraining.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAll();
}
