package com.mostafa.controller;

import com.mostafa.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:17 AM
 */
@RestController
@RequestMapping("/api/inventory")
@AllArgsConstructor
public class InventoryController {
    private InventoryService inventoryService;

    @GetMapping("/{sku-code}")
    public ResponseEntity<?> isInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @GetMapping
    public ResponseEntity<?> inStockedList(@RequestParam("sku-codes") List<String> skuCodes){
        return inventoryService.inStockedList(skuCodes);
    }
}
