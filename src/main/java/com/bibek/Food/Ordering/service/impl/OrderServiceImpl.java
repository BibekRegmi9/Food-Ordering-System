package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.config.CustomMessageSource;
import com.bibek.Food.Ordering.entity.*;
import com.bibek.Food.Ordering.exceptions.AppException;
import com.bibek.Food.Ordering.pojo.OrderPojo;
import com.bibek.Food.Ordering.repository.*;
import com.bibek.Food.Ordering.service.CartService;
import com.bibek.Food.Ordering.service.OrderService;
import com.bibek.Food.Ordering.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Autowired
    private CustomMessageSource customMessageSource;

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

        Cart cart = cartService.findCartByUserId(user.getId());

        List<Orderitem> orderitems = new ArrayList<>();

        for(CartItem cartItem : cart.getItem()){
            Orderitem orderitem = new Orderitem();
            orderitem.setFood(cartItem.getFood());
            orderitem.setIngredients(cartItem.getIngredients());
            orderitem.setQuantity(cartItem.getQuantity());
            orderitem.setTotalPrice(cartItem.getTotalPrice());

            Orderitem savedOrderItem = orderItemRepo.save(orderitem);
            orderitems.add(savedOrderItem);
        }

        Long totalPrice = cartService.calculateCartTotals(cart);
        order.setItems(orderitems);
        order.setTotalPrice(totalPrice);

        orderRepo.save(order);
        restaurant.getOrders().add(order);

        return order;
    }

    @Override
    public Order updateOrder(Long orderId, String orderStatus) {
        Order order = findOrderById(orderId);

        if(orderStatus.equals("OUT_FOR_DELIVERY") || orderStatus.equals("DELIVERED") || orderStatus.equals("COMPLETED") || orderStatus.equals("PENDING")){
            order.setOrderStatus(orderStatus);

            return  orderRepo.save(order);
        }
        throw new AppException(customMessageSource.get("Invalid Order Status"));
    }

    @Override
    public void cancelOrder(Long orderId) {
        Order order = findOrderById(orderId);
        if(order == null){
            throw new AppException(customMessageSource.get("Order not found"));
        }
        orderRepo.deleteById(orderId);
    }

    @Override
    public List<Order> getUsersOrder(Long userId) {

        return orderRepo.findByCustomerId(userId);
    }

    @Override
    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus) {

        List<Order> orders =  orderRepo.findByRestaurantId(restaurantId);

        if(orderStatus!= null){
            orders = orders.stream().filter(x -> x.getOrderStatus().equals(orderStatus)).collect(Collectors.toList());
        }

        return orders;
    }

    @Override
    public Order findOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        if(optionalOrder.isEmpty()){
            throw new AppException(customMessageSource.get("Order Not Found"));
        }
        return optionalOrder.get();
    }
}
