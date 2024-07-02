package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.entity.*;
import com.bibek.Food.Ordering.pojo.OrderPojo;
import com.bibek.Food.Ordering.repository.*;
import com.bibek.Food.Ordering.service.CartService;
import com.bibek.Food.Ordering.service.OrderService;
import com.bibek.Food.Ordering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private OrderItemRepo orderItemRepo;

    @Autowired
    private AddressRepo addressRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private CartService cartService;

    @Override
    public Order createOrder(OrderPojo pojo, User user) throws Exception {
        Address address = pojo.getDeliveryAddress();

        Address savedAddress = addressRepo.save(address);

        if(!user.getAddresses().contains(savedAddress)){
            user.getAddresses().add(savedAddress);
            userRepo.save(user);
        }

        Restaurant restaurant = restaurantService.findRestaurantById(pojo.getRestaurantId());

        Order order = new Order();
        order.setCustomer(user);
        order.setCreatedAt(new Date());
        order.setOrderStatus("PENDING");
        order.setDeliveryAddress(savedAddress);
        order.setRestaurant(restaurant);

//        Cart cart = cartService.findCartByUserId(user.getId());

    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) {
        return null;
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public List<Order> getUsersOrder(Long userId) {
        return null;
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) {
        return null;
    }
}
