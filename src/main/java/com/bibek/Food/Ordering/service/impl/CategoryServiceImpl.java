package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.entity.Category;
import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.repository.CategoryRepo;
import com.bibek.Food.Ordering.service.CategoryService;
import com.bibek.Food.Ordering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Category createCategory(String name, Long userId) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(userId);
        Category category = new Category();
        category.setName(name);
        category.setRestaurant(restaurant);

        return categoryRepo.save(category);
    }

    @Override
    public List<Category> findCategoryByRestaurantId(Long id) throws Exception {
        Restaurant restaurant = restaurantService.getRestaurantByUserId(id);
        return categoryRepo.findByRestaurantId(restaurant.getId());
    }

    @Override
    public Category findCategoryById(Long id) throws Exception {
        Optional <Category> optionalCategory = categoryRepo.findById(id);
        if(optionalCategory.isEmpty()){
            throw new Exception("Category not found...");
        }
        return optionalCategory.get();
    }
}
