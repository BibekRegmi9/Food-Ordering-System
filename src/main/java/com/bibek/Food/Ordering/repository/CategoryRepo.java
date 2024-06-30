package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Long> {

    public List<Category> findByRestaurantId(Long id);
}
