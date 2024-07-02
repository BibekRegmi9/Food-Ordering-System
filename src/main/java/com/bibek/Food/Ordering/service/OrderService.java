package com.bibek.Food.Ordering.service;

import com.bibek.Food.Ordering.entity.Order;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.pojo.OrderPojo;

import java.util.List;

public interface OrderService {

    public Order createOrder(OrderPojo pojo, User user) throws Exception;

    public Order updateOrder(Long orderId, String orderStatus);

    public void cancelOrder(Long orderId);

    public List<Order> getUsersOrder(Long userId);

    public List<Order> getRestaurantOrder(Long restaurantId, String orderStatus);
}
