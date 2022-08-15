package com.epam.training.fooddelivery.data;

import com.epam.training.fooddelivery.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
