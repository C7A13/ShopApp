package com.project.shopapp.mapper;

import org.mapstruct.Mapper;

import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.models.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}
