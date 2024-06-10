package com.example.spring_redis_practice.controller.dto;

import com.example.spring_redis_practice.entity.Product;
import lombok.*;
import org.springframework.data.annotation.Id;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDto {
    private int id;
    private String name;
    private int qty;
    private long price;
    private int rating;

    public static Product toProduct(ProductDto productDto) {
        return Product.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .qty(productDto.getQty())
                .price(productDto.getPrice())
                .rating(productDto.getRating())
                .build();
    }

    public static ProductDto toProductDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .qty(product.getQty())
                .price(product.getPrice())
                .rating(product.getRating())
                .build();
    }
}
