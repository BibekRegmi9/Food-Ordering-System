package com.bibek.Food.Ordering.controller;

import com.bibek.Food.Ordering.constants.StringConstants;
import com.bibek.Food.Ordering.entity.Order;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.pojo.OrderPojo;
import com.bibek.Food.Ordering.response.GlobalApiResponse;
import com.bibek.Food.Ordering.service.OrderService;
import com.bibek.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @PostMapping("/order/add")
    public ResponseEntity<GlobalApiResponse> createOrder(@RequestBody OrderPojo request,
                                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_SAVE), orderService.createOrder(request, user)));
    }


    @GetMapping("/order/user")
    public ResponseEntity<GlobalApiResponse> getOrderHistory(
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getUsersOrder(user.getId());

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_RETRIEVE), orders));
    }
}
