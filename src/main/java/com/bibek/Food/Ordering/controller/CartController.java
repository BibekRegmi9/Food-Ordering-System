package com.bibek.Food.Ordering.controller;

import com.bibek.Food.Ordering.constants.StringConstants;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.request.AddCartItemRequest;
import com.bibek.Food.Ordering.request.UpdateCartItemRequest;
import com.bibek.Food.Ordering.response.GlobalApiResponse;
import com.bibek.Food.Ordering.service.CartService;
import com.bibek.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController extends BaseController{

    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PutMapping("/cart/add")
    public ResponseEntity<GlobalApiResponse> addItemToCart(@RequestBody AddCartItemRequest request,
                                                           @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_SAVE), cartService.addItemToCart(request, jwt)));

    }


    @PutMapping("/cart-item/update")
    public ResponseEntity<GlobalApiResponse> updateCartItemQuantity(@RequestBody UpdateCartItemRequest request,
                                                                    @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_UPDATE), cartService.updateCartItemQuantity(request.getCartItemId(), request.getQuantity())));

    }


    @DeleteMapping("/cart-item/{id}/remove")
    public ResponseEntity<GlobalApiResponse> removeCartItems(@PathVariable Long id,
                                                  @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_DELETE), cartService.removeItemFromCart(id, jwt)));

    }


    @PutMapping("/cart/clear")
    public ResponseEntity<GlobalApiResponse> clearCart(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_UPDATE), cartService.clearCart(user.getId())));

    }

    @GetMapping("/cart")
    public ResponseEntity<GlobalApiResponse> findCart(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);

        return ResponseEntity.ok(successResponse(customMessageSource.get(StringConstants.SUCCESS_RETRIEVE), cartService.findCartByUserId(user.getId())));

    }
}
