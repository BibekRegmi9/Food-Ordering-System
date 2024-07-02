package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    public List<Order> findByCustomerId(Long userId);

    public List<Order> findByRestaurantId(Long restaurantId);

}
