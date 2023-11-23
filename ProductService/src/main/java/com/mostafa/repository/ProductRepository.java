package com.mostafa.repository;

import com.mostafa.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:15 AM
 */
@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
