package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Cart;
import com.bibek.Food.Ordering.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepo extends JpaRepository<CartItem, Long> {

    public Cart findByCustomerId(Long userId);
}
