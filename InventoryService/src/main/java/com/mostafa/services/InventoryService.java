package com.mostafa.services;

import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:22 AM
 */
public interface InventoryService {
    ResponseEntity<?> isInStock(String skuCode);

    ResponseEntity<?> inStockedList(List<String> skuCodes);
}
