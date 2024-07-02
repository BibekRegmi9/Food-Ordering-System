package com.bibek.Food.Ordering.controller;

import com.bibek.Food.Ordering.constants.StringConstants;
import com.bibek.Food.Ordering.entity.Restaurant;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.pojo.CreateFoodPojo;
import com.bibek.Food.Ordering.response.GlobalApiResponse;
import com.bibek.Food.Ordering.service.FoodService;
import com.bibek.Food.Ordering.service.RestaurantService;
import com.bibek.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/food")
public class AdminFoodController extends BaseController{

    @Autowired
    private FoodService foodService;

    @Autowired
    private UserService userService;

    @Autowired
    private RestaurantService restaurantService;

    @PostMapping
    public ResponseEntity<GlobalApiResponse> createFood(@RequestBody CreateFoodPojo request,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Restaurant restaurant = restaurantService.findRestaurantById(request.getRestaurantId());

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_SAVE), foodService.createFood(request, request.getCategory(), restaurant)));


    }

    @DeleteMapping
    public ResponseEntity<GlobalApiResponse> deleteFood(@PathVariable Long id,
                                                        @RequestHeader("Authorization") String jwt
                                           ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        foodService.deleteFood(id);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_DELETE), null));
    }


    @PutMapping
    public ResponseEntity<GlobalApiResponse> updateFoodAvailabilityStatus(@PathVariable Long id,
                                                                          @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_DELETE), foodService.updateAvailabilityStatus(id)));
    }

}
