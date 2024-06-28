package com.bibek.Food.Ordering.service;

import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.pojo.CreateRestaurantRequest;
import com.bibek.Food.Ordering.pojo.RestaurantPojo;

import java.util.List;

public interface RestaurantService{
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user);

    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception;

    public void deleteRestaurant(Long restaurantId) throws Exception;

    public List<Restaurant> getAllRestaurant();

    public List<Restaurant> searchRestaurant(String keyword);

    public Restaurant findRestaurantById(Long id) throws Exception;

    public Restaurant getRestaurantByUserId(Long userId) throws Exception;

    public RestaurantPojo addToFavorites(Long restaurantId, User user) throws  Exception;

    public Restaurant updateRestaurantStatus(Long id) throws Exception;
}

