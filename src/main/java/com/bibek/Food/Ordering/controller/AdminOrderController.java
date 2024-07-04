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
@RequestMapping("/api/admin")
public class AdminOrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;




    @GetMapping("/order/restaurant/{id}")
    public ResponseEntity<GlobalApiResponse> getOrderHistory( @PathVariable Long id,
                                                              @RequestParam(required = false) String order_status,
            @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        List<Order> orders = orderService.getRestaurantOrder(id, order_status);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_RETRIEVE), orders));
    }

    @PutMapping("/order/{id}/{orderStatus}")
    public ResponseEntity<GlobalApiResponse> updateOrderStatus( @PathVariable Long id,
                                                              @PathVariable String orderStatus,
                                                              @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Order orders = orderService.updateOrder(id, orderStatus);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_RETRIEVE), orders));
    }

}
