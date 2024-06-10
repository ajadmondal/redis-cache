package com.example.spring_redis_practice.repository;

import com.example.spring_redis_practice.entity.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Slf4j
public class ProductRepository {
    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate template;

    @CacheEvict(value = "products", allEntries = true)
    public Product save(Product product){
        log.info("[save] saving product");
        template.opsForHash().put(HASH_KEY,product.getId(),product);
        return product;
    }

    @Cacheable("products")
    public List<Product> findAll(){
        log.info("[findAll] doing db query");
        return template.opsForHash().values(HASH_KEY);
    }

    @Cacheable(key = "#id", value = "Product")
    public Product findProductById(int id){
        log.info("[findProductById] doing db query");
        return (Product) template.opsForHash().get(HASH_KEY,id);
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "products", allEntries = true),
                    @CacheEvict(key = "#id", value = "Product")
            }
    )
    public String deleteProduct(int id){
        log.info("[delete] deleting product");
        template.opsForHash().delete(HASH_KEY,id);
        return "product removed !!";
    }
}
