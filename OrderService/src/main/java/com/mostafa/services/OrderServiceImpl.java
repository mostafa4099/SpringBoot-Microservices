package com.mostafa.services;

import com.mostafa.dto.OrderItemDto;
import com.mostafa.dto.OrderRequest;
import com.mostafa.dto.OrderResponse;
import com.mostafa.entity.Order;
import com.mostafa.entity.OrderItem;
import com.mostafa.model.InventoryStockStatus;
import com.mostafa.repository.OrderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:23 AM
 */
@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private WebClient.Builder webClieBuilder;

    @Override
    public ResponseEntity<Object> placeOrder(OrderRequest orderRequest) {
        try {
            List<InventoryStockStatus> stockStatusList = new ArrayList<>();
            List<String> skuCodeList = orderRequest.getOrderItems().stream().map(OrderItemDto::getSkuCode).toList();

            ResponseEntity<List<InventoryStockStatus>> responseEntity = webClieBuilder.build().get()
                    .uri("http://inventory-service/api/inventory",
                            uriBuilder -> uriBuilder.queryParam("sku-codes", skuCodeList).build())
                    .retrieve()
                    .onStatus (HttpStatusCode::isError, ClientResponse::createException)
                    .onStatus (status -> status.isSameCodeAs(HttpStatus.NO_CONTENT), ClientResponse::createException)
                    .toEntityList(InventoryStockStatus.class)
                    .block();

            if (responseEntity.getStatusCode().isSameCodeAs(HttpStatus.OK)) {
                stockStatusList = responseEntity.getBody();
            }

            boolean isAllStocked = stockStatusList.stream().allMatch(InventoryStockStatus::isStock);

            if (!isAllStocked) {
                return new ResponseEntity<>("Failed to save data because of insufficient stock.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());

            List<OrderItem> orderItemList = orderRequest.getOrderItems().stream().map(item ->
                    new OrderItem().SetOrderItem(item)).toList();

            order.setOrderItems(orderItemList);

            Order savedOrder = orderRepository.save(order);

            log.info("Order {} is created.", order.getId());

            OrderResponse orderResponse = new OrderResponse().SetOrderResponse(savedOrder);

            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to save data due to " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
