package com.mostafa.dto;

import com.mostafa.entity.OrderItem;
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
public class OrderRequest {
    private List<OrderItemDto> orderItems;
}
