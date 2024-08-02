package com.t2208e.T2208E_Java_JPA.service.impl;

import com.t2208e.T2208E_Java_JPA.dto.ProductDTO;
import com.t2208e.T2208E_Java_JPA.entity.Manufacturer;
import com.t2208e.T2208E_Java_JPA.entity.Product;
import com.t2208e.T2208E_Java_JPA.repository.ManufacturerRepository;
import com.t2208e.T2208E_Java_JPA.repository.ProductRepository;
import com.t2208e.T2208E_Java_JPA.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(this::toDTO).orElse(null);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        Manufacturer manufacturer = manufacturerRepository.findById(productDTO.getManufacturerId()).orElse(null);
        if (manufacturer == null) {
            throw new RuntimeException("Manufacturer not found.");
        }
        Product product = toEntity(productDTO);
        product.setManufacturer(manufacturer);
        Product savedProduct = productRepository.save(product);
        return toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        // Check if the product exists
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with ID " + id + " not found"));
        // Check if the manufacturer exists
        Manufacturer manufacturer = manufacturerRepository.findById(productDTO.getManufacturerId())
                .orElseThrow(() -> new RuntimeException("Manufacturer with ID " + productDTO.getManufacturerId() + " not found"));
        // Update the existing product's details
        existingProduct.setName(productDTO.getName());
        existingProduct.setType(productDTO.getType());
        existingProduct.setDescription(productDTO.getDescription());
        existingProduct.setImages(productDTO.getImages());
        existingProduct.setPrice(productDTO.getPrice());
        existingProduct.setDiscount(productDTO.getDiscount());
        existingProduct.setManufacturer(manufacturer);
        // Save the updated product
        Product updatedProduct = productRepository.save(existingProduct);

        return toDTO(updatedProduct);
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDTO> searchProductsByNameOrType(String query) {
        // Use the repository method to search by name or type
        List<Product> products = productRepository.searchByNameOrType(query);
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    private ProductDTO toDTO(Product product) {
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getType(),
                product.getDescription(),
                product.getImages(),
                product.getPrice(),
                product.getDiscount(),
                product.getManufacturer().getId()
        );
    }

    private Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setType(dto.getType());
        product.setDescription(dto.getDescription());
        product.setImages(dto.getImages());
        product.setPrice(dto.getPrice());
        product.setDiscount(dto.getDiscount());
        // Manufacturer is set separately in create and update methods
        return product;
    }
}
