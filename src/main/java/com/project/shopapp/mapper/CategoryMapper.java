package com.project.shopapp.mapper;

import org.mapstruct.Mapper;

import com.project.shopapp.dtos.CategoryDTO;
import com.project.shopapp.models.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO toDTO(Category category);

    Category toEntity(CategoryDTO categoryDTO);
}
