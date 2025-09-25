package com.project.shopapp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.models.Product;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProductMapper {
    Product toEntity(ProductDTO productDTO);

    ProductDTO toDTO(Product product);
}
