package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.entity.Address;
import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.pojo.CreateRestaurantRequest;
import com.bibek.Food.Ordering.pojo.RestaurantPojo;
import com.bibek.Food.Ordering.repository.AddressRepo;
import com.bibek.Food.Ordering.repository.RestaurantRepo;
import com.bibek.Food.Ordering.repository.UserRepo;
import com.bibek.Food.Ordering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;
    @Override
    public Restaurant createRestaurant(CreateRestaurantRequest req, User user) {
        Address address = addressRepo.save(req.getAddress());
        Restaurant restaurant = Restaurant.builder()
                .address(address)
                .contactInfo(req.getContactInfo())
                .cuisineType(req.getCuisineType())
                .description(req.getDescription())
                .images(req.getImages())
                .name(req.getName())
                .openingHours(req.getOpeningHours())
                .registrationDate(LocalDateTime.now())
                .owner(user)
                .build();

        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant updateRestaurant(Long restaurantId, CreateRestaurantRequest updatedRestaurant) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);
        if(restaurant.getCuisineType() != null){
            restaurant.setCuisineType(updatedRestaurant.getCuisineType());
        }

        if(restaurant.getDescription() != null){
            restaurant.setDescription(updatedRestaurant.getDescription());
        }

        if(restaurant.getName() != null){
            restaurant.setName(updatedRestaurant.getName());
        }

        return restaurantRepo.save(restaurant);
    }

    @Override
    public void deleteRestaurant(Long restaurantId) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        if(restaurant == null){
            throw new Exception("Restaurant not found");
        }

        restaurantRepo.delete(restaurant);

    }

    @Override
    public List<Restaurant> getAllRestaurant() {
        return restaurantRepo.findAll();
    }

    @Override
    public List<Restaurant> searchRestaurant(String keyword) {
        return restaurantRepo.findBySearchQuery(keyword);
    }

    @Override
    public Restaurant findRestaurantById(Long id) throws Exception {
        Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(id);

        if(optionalRestaurant.isEmpty()){
            throw new Exception("Restaurant not found with id " + id);
        }

        return optionalRestaurant.get();
    }

    @Override
    public Restaurant getRestaurantByUserId(Long userId) throws Exception {
        Restaurant restaurant = restaurantRepo.findByOwnerId(userId);

        if(restaurant == null){
            throw new Exception("Restaurant not found");
        }

        return restaurant;
    }

    @Override
    public RestaurantPojo addToFavorites(Long restaurantId, User user) throws Exception {
        Restaurant restaurant = findRestaurantById(restaurantId);

        RestaurantPojo restaurantPojo = new RestaurantPojo();
        restaurantPojo.setDescription(restaurant.getDescription());
        restaurantPojo.setImages(restaurant.getImages());
        restaurantPojo.setTitle(restaurant.getName());
        restaurantPojo.setId(restaurantId);

        boolean isFavorites = false;
        List<RestaurantPojo> favourites = user.getFavourites();
        for(RestaurantPojo fav: favourites){
            if(fav.getId().equals(restaurantId)){
                isFavorites = true;
                break;
            }
        }

        if(isFavorites){
            favourites.removeIf(x -> x.getId().equals(restaurantId));
        } else {
            favourites.add(restaurantPojo);
        }

        userRepo.save(user);

        return restaurantPojo;
    }

    @Override
    public Restaurant updateRestaurantStatus(Long id) throws Exception {
        Restaurant restaurant = findRestaurantById(id);
        restaurant.setOpen(!restaurant.isOpen());

        return restaurantRepo.save(restaurant);
    }
}
