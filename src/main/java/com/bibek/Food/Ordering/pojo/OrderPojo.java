package com.bibek.Food.Ordering.pojo;

import com.bibek.Food.Ordering.entity.Address;
import lombok.Data;

@Data
public class OrderPojo {
    private Long restaurantId;
    private Address deliveryAddress;
}
