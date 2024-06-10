package com.example.spring_redis_practice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("Product")
public class Product implements Serializable {
    @Id
    private int id;
    private String name;
    private int qty;
    private long price;
    private int rating;
}
