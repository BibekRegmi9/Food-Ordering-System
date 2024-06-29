package com.bibek.Food.Ordering.controller;

import com.bibek.Food.Ordering.entity.Food;
import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.pojo.CreateFoodPojo;
import com.bibek.Food.Ordering.service.FoodService;
import com.bibek.Food.Ordering.service.RestaurantService;
import com.bibek.Food.Ordering.service.UserService;
import com.bibek.Food.Ordering.utils.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<Food> createFood(@RequestBody CreateFoodPojo request,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        Food food = foodService.createFood(request, request.getCategory(), restaurant);

        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteFood(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt
                                           ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        MessageResponse response = new MessageResponse();
        response.setMessage("Food deleted successfully");

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PutMapping
    public ResponseEntity<Food> updateFoodAvailabilityStatus(@PathVariable Long id,
                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.updateAvailabilityStatus(id);

        return new ResponseEntity<>(food, HttpStatus.OK);
    }

}
