package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.entity.IngredientCategory;
import com.bibek.Food.Ordering.entity.IngredientsItem;
import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.repository.IngredientCategoryRepo;
import com.bibek.Food.Ordering.repository.IngredientItemRepo;
import com.bibek.Food.Ordering.service.IngredientsService;
import com.bibek.Food.Ordering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImpl implements IngredientsService{
    @Autowired
    private IngredientCategoryRepo ingredientCategoryRepo;

    @Autowired
    private IngredientItemRepo ingredientItemRepo;

    @Autowired
    private RestaurantService restaurantService;


    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = new IngredientCategory();
        category.setRestaurant(restaurant);
        category.setName(name);
        return ingredientCategoryRepo.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategoryById(Long id) throws Exception {
        Optional<IngredientCategory> optionalIngredientCategory = ingredientCategoryRepo.findById(id);
        if(optionalIngredientCategory.isEmpty()){
            throw new Exception("Ingredient Category not found");
        }
        return optionalIngredientCategory.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long id) throws Exception {
        restaurantService.findRestaurantById(id);
        return ingredientCategoryRepo.findByRestaurantId(id);
    }

    @Override
    public IngredientsItem createIngredientsItem(Long restaurantId, String ingredientName, Long ingredientCategoryId) throws Exception {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);
        IngredientCategory category = findIngredientCategoryById(ingredientCategoryId);
        IngredientsItem item = new IngredientsItem();
        item.setName(ingredientName);
        item.setRestaurant(restaurant);
        item.setCategory(category);

        IngredientsItem savedItem = ingredientItemRepo.save(item);
        category.getIngredients().add(savedItem);
        return null;
    }

    @Override
    public List<IngredientsItem> findRestaurantIngredients(Long restaurantId) {
        return ingredientItemRepo.findByRestaurantId(restaurantId);
    }

    @Override
    public IngredientsItem updateStock(Long id) throws Exception {
        Optional<IngredientsItem> optionalIngredientsItem = ingredientItemRepo.findById(id);
        if(optionalIngredientsItem.isEmpty()){
            throw  new Exception("IngredientItem not found...");
        }
        IngredientsItem item = optionalIngredientsItem.get();
        item.setInStock(!item.isInStock());
        return ingredientItemRepo.save(item);
    }
}
