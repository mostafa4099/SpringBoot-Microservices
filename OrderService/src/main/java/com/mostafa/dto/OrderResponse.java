package com.mostafa.dto;

import com.mostafa.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:20 AM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {
    private Long id;
    private String orderNumber;
    private List<OrderItemDto> orderItems;

    public OrderResponse SetOrderResponse(Order order) {
        this.id = order.getId();
        this.orderNumber = order.getOrderNumber();

        this.orderItems = order.getOrderItems().stream().map(item ->
                new OrderItemDto().SetOrderItemDto(item)).toList();

        return this;
    }
}
