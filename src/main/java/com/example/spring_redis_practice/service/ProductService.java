package com.example.spring_redis_practice.service;

import com.example.spring_redis_practice.controller.dto.ProductDto;
import com.example.spring_redis_practice.entity.Product;
import com.example.spring_redis_practice.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public ProductDto save(ProductDto productDto) {
        productRepository.save(ProductDto.toProduct(productDto));
        return productDto;
    }

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream().map(ProductDto::toProductDto).toList();
    }

    public ProductDto getProduct(int id) {
        return ProductDto.toProductDto(productRepository.findProductById(id));
    }

    public String remove(int id) {
        return productRepository.deleteProduct(id);
    }
}
