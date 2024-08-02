package com.t2208e.T2208E_Java_JPA.service;

import com.t2208e.T2208E_Java_JPA.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    ProductDTO getProductById(Long id);
    ProductDTO createProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    void deleteProduct(Long id);
    List<ProductDTO> searchProductsByNameOrType(String query);
}
