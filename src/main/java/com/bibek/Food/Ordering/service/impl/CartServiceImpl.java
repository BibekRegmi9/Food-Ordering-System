package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.entity.Cart;
import com.bibek.Food.Ordering.entity.CartItem;
import com.bibek.Food.Ordering.entity.Food;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.repository.CartItemRepo;
import com.bibek.Food.Ordering.repository.CartRepo;
import com.bibek.Food.Ordering.repository.FoodRepo;
import com.bibek.Food.Ordering.request.AddCartItemRequest;
import com.bibek.Food.Ordering.service.CartService;
import com.bibek.Food.Ordering.service.FoodService;
import com.bibek.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private CartItemRepo cartItemRepo;

    @Autowired
    private FoodRepo foodRepo;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(req.getFoodId());

        Cart cart = cartRepo.findByCustomerId(user.getId());

        for(CartItem cartItem : cart.getItem()){
            if(cartItem.getFood().equals(food)){
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem cartItem = new CartItem();
        cartItem.setFood(food);
        cartItem.setCart(cart);
        cartItem.setQuantity(req.getQuantity());
        cartItem.setIngredients(req.getIngredients());
        cartItem.setTotalPrice(req.getQuantity() * food.getPrice());

        CartItem savedCartItem = cartItemRepo.save(cartItem);

        cart.getItem().add(savedCartItem);

        return savedCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);

        if(cartItemOptional.isEmpty()){
            throw new Exception("Cart Item not found...");
        }

        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);

        item.setTotalPrice(item.getFood().getPrice() * quantity);

        return cartItemRepo.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);

        Cart cart = cartRepo.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepo.findById(cartItemId);

        if(cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        CartItem  item = cartItemOptional.get();

        cart.getItem().remove(item);

        return cartRepo.save(cart);
    }

    @Override
    public Long calculateCartTotals(Cart cart) throws Exception {

        Long total = 0L;

        for(CartItem cartItem : cart.getItem()){
            total += cartItem.getFood().getPrice() * cartItem.getQuantity();
        }

        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> cart = cartRepo.findById(id);

        if(cart.isEmpty()){
            throw new Exception("Cart not found with given id");
        }

        return cart.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        return cartRepo.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Cart cart = findCartByUserId(jwt);
        cart.getItem().clear();
        return cartRepo.save(cart);
    }
}
