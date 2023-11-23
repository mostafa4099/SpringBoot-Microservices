package com.mostafa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 2:44 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class InventoryStockStatus {
    private String skuCode;
    private boolean isStock;
}
