package com.bibek.Food.Ordering.entity;

import com.bibek.Food.Ordering.pojo.RestaurantPojo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @JsonIgnore
    @ManyToOne
    private Restaurant restaurant;

    private Long totalAmount;

    private String orderStatus;

    private Date createdAt;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    private List<Orderitem> items;

    private int totalItem;

    private int totalPrice;
}
