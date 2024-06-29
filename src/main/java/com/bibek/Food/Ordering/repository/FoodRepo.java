package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepo extends JpaRepository<Food, Long> {


    List<Food> findByRestaurantId(Long restaurantId);

    @Query(nativeQuery = true,
            value = "select f from food f where f.name like %:keyword% or f.foodCategory.name like %:keyword%"
    )
    List<Food> searchFood(@Param("keyword") String keyword);
}
