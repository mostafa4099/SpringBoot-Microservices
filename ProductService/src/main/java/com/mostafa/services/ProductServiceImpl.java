package com.mostafa.services;

import com.mostafa.dto.ProductRequest;
import com.mostafa.dto.ProductResponse;
import com.mostafa.entity.Product;
import com.mostafa.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:23 AM
 */
@Service
@AllArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Override
    public ResponseEntity<?> createProduct(ProductRequest productRequest) {
        try {
            Product product = Product.builder()
                    .name(productRequest.getName())
                    .description(productRequest.getDescription())
                    .price(productRequest.getPrice())
                    .build();

            Product savedProduct = productRepository.save(product);

            log.info("Product {} is created.", product.getId());
//            log.info("Product {} is created.", product.getName());

            ProductResponse productResponse = ProductResponse.builder()
                    .id(savedProduct.getId())
                    .name(savedProduct.getName())
                    .description(savedProduct.getDescription())
                    .price(savedProduct.getPrice())
                    .build();

            return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save data due to " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAllProduct() {
        try {
            List<Product> productList = productRepository.findAll();

            if (!CollectionUtils.isEmpty(productList)) {
                List<ProductResponse> productResponseList = productList.stream().map(product ->
                        ProductResponse.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .price(product.getPrice())
                                .build()
                ).toList();

                return new ResponseEntity<>(productResponseList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No data found.", HttpStatus.NOT_FOUND);
            }

        } catch (Exception e) {
            return new ResponseEntity<>("Failed to retrieve data due to " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
