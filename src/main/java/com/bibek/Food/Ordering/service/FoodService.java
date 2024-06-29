package com.bibek.Food.Ordering.service;

import com.bibek.Food.Ordering.entity.Category;
import com.bibek.Food.Ordering.entity.Food;
import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.pojo.CreateFoodPojo;

import java.util.List;

public interface FoodService {
    public Food createFood(CreateFoodPojo req, Category category, Restaurant restaurant) throws Exception;

    void deleteFood(Long foodId) throws Exception;

    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVeg,
                                        boolean isNotVeg,
                                        boolean isSeasonal,
                                        String foodCategory);

    public List<Food> searchFood(String keyword);

    public Food findFoodById(Long foodId) throws Exception;

    public Food updateAvailabilityStatus(Long foodId) throws  Exception;
}
