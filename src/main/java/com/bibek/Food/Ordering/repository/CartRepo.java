package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepo extends JpaRepository<Cart, Long> {
}
