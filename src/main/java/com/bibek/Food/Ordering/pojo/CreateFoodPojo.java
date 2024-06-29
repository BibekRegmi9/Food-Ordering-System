package com.bibek.Food.Ordering.pojo;

import com.bibek.Food.Ordering.entity.Category;
import com.bibek.Food.Ordering.entity.IngredientsItem;
import lombok.Data;

import java.util.List;

@Data
public class CreateFoodPojo {
    private String name;
    private String description;
    private Long price;
    private Category category;
    private List<String> images;
    private Long restaurantId;
    private boolean isVeg;
    private boolean seasonal;
    private List<IngredientsItem> ingredients;
}
