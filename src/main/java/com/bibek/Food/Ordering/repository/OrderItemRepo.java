package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Orderitem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepo extends JpaRepository<Orderitem, Long> {
}
