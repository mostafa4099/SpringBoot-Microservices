package com.mostafa.dto;

import com.mostafa.entity.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 2:44 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderItemDto SetOrderItemDto(OrderItem item) {
        this.id = item.getId();
        this.skuCode = item.getSkuCode();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();

        return this;
    }
}
