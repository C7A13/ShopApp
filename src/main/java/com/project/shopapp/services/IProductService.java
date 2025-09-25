package com.project.shopapp.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.project.shopapp.dtos.ProductDTO;
import com.project.shopapp.models.Product;
import com.project.shopapp.responses.ProductResponse;

public interface IProductService {
    Product createProduct(ProductDTO productDTO);

    Product getProductById(long id);

    Page<ProductResponse> getAllProducts(PageRequest pageRequest);

    Product updateProduct(long id, ProductDTO productDTO);

    void deleteProduct(long id);
}
