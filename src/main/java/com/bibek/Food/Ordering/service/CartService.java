package com.bibek.Food.Ordering.service;

import com.bibek.Food.Ordering.entity.Cart;
import com.bibek.Food.Ordering.entity.CartItem;
import com.bibek.Food.Ordering.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws  Exception;

    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception;

    public Long calculateCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws Exception;

    public Cart findCartByUserId(String jwt) throws  Exception;

    public Cart clearCart(String jwt) throws Exception;
}