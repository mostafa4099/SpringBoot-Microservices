package com.mostafa.entity;

import com.mostafa.dto.OrderItemDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 2:37 PM
 */
@Entity
@Table(name = "order_item")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderItem SetOrderItem(OrderItemDto item) {
        this.skuCode = item.getSkuCode();
        this.price = item.getPrice();
        this.quantity = item.getQuantity();

        return this;
    }
}
