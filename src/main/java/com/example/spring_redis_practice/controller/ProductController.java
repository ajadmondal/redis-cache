package com.example.spring_redis_practice.controller;

import com.example.spring_redis_practice.controller.dto.ProductDto;
import com.example.spring_redis_practice.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@EnableCaching
@Slf4j
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody ProductDto product) {
        return ResponseEntity.ok(productService.save(product));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProduct(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProduct(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> remove(@PathVariable int id)   {
        return ResponseEntity.ok(productService.remove(id));
    }

}
