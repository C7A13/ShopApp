package com.project.shopapp.services;

import java.util.List;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;

public interface ICategoryService {

    Category createCategory(CategoryDTO categoryDTO);

    Category getCategoryById(long id);

    List<Category> getAllCategory();

    Category updateCategory(long categoryId, CategoryDTO categoryDTO);

    void deleteCategory(long id);
}
