package com.mostafa.services;

import com.mostafa.dto.ProductRequest;
import org.springframework.http.ResponseEntity;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:22 AM
 */
public interface ProductService {
    ResponseEntity<?> createProduct(ProductRequest productRequest);

    ResponseEntity<?> getAllProduct();
}
