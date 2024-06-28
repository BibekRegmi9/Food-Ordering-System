package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

    Restaurant findByOwnerId(Long userId);

    @Query(nativeQuery = true,
    value = "select r from restaurant r where lower(r.name) like lower(concat('%', :query, '%')) OR" +
            "lower(r.cuisineType) like lower(concat('%', :query, '%'))")
    List<Restaurant> findBySearchQuery(String query);
}
