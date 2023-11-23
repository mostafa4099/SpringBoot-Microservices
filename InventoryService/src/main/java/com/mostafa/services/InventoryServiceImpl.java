package com.mostafa.services;

import com.mostafa.model.InventoryStockStatus;
import com.mostafa.entity.Inventory;
import com.mostafa.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:23 AM
 */
@Service
@AllArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService {
    private InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<?> isInStock(String skuCode) {
        try {
            Optional<Inventory> optionalInventory = inventoryRepository.findBySkuCode(skuCode);

            return new ResponseEntity<>(optionalInventory.isPresent(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to check stock due to " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> inStockedList(List<String> skuCodes) {
        try {
            List<Inventory> inventoryList = inventoryRepository.findAllBySkuCodeIn(skuCodes);

            if (!CollectionUtils.isEmpty(inventoryList)) {
                List<InventoryStockStatus> inventoryDtoList = inventoryList.stream().map(inventory -> new InventoryStockStatus().SetStockStatus(inventory)).toList();
                return new ResponseEntity<>(inventoryDtoList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("No Data Found!", HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to check stock due to " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
