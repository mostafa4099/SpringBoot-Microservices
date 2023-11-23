package com.mostafa.repository;

import com.mostafa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 11/16/2023 10:15 AM
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
