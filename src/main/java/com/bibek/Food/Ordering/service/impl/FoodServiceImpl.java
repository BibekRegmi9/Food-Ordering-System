package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.entity.Category;
import com.bibek.Food.Ordering.entity.Food;
import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.pojo.CreateFoodPojo;
import com.bibek.Food.Ordering.repository.FoodRepo;
import com.bibek.Food.Ordering.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepo foodRepo;

    @Override
    public Food createFood(CreateFoodPojo req, Category category, Restaurant restaurant) throws Exception {
        if(req != null){
            Food food = Food.builder()
                    .foodCategory(category)
                    .description(req.getDescription())
                    .images(req.getImages())
                    .name(req.getName())
                    .price(req.getPrice())
                    .ingredients(req.getIngredients())
                    .isSeasonal(req.isSeasonal())
                    .isVeg(req.isVeg())
                    .build();
            Food savedFood =  foodRepo.save(food);

            restaurant.getFoods().add(savedFood);

            return savedFood;
        } else {
            throw new Exception("Request body is empty");
        }

    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setRestaurant(null);
        foodRepo.save(food);
    }

    @Override
    public List<Food> getRestaurantFood(Long restaurantId,
                                        boolean isVeg,
                                        boolean isNotVeg,
                                        boolean isSeasonal,
                                        String foodCategory) {
        List<Food> foods = foodRepo.findByRestaurantId(restaurantId);

        if(isVeg){
            foods = filterByVeg(foods, isVeg);
        }

        if(isNotVeg){
            foods = filterByNonVeg(foods, isNotVeg);
        }

        if(isSeasonal){
            foods = filterBySeasonal(foods, isSeasonal);
        }

        if(foodCategory != null && !foodCategory.equals("")){
            foods = filterByCategory(foods, foodCategory);
        }

        return foods;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(x -> {
            if(x.getFoodCategory() != null){
                return x.getFoodCategory().getName().equals(foodCategory);
            }

            return false;
        }).collect(Collectors.toList());
    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(x -> x.isSeasonal() == isSeasonal).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNotVeg) {
        return foods.stream().filter(x -> x.isVeg() == false).collect(Collectors.toList());
    }

    private List<Food> filterByVeg(List<Food> foods, boolean isVeg) {
        return foods.stream().filter(x -> x.isVeg() == isVeg).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepo.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepo.findById(foodId);

        if(optionalFood.isEmpty()){
            throw new Exception("Food don't exist");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long foodId) throws Exception {
        Food food = findFoodById(foodId);
        food.setAvailable(!food.isAvailable());
        return foodRepo.save(food);
    }
}
