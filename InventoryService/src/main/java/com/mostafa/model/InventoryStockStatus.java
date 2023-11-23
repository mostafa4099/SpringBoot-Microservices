package com.mostafa.model;

import com.mostafa.entity.Inventory;
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

    public InventoryStockStatus SetStockStatus(Inventory inventory) {
        this.skuCode = inventory.getSkuCode();
        this.isStock = inventory.getQuantity()>0;
        return this;
    }
}
