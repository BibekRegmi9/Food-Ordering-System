package com.bibek.Food.Ordering.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class RestaurantPojo {
    private String title;


    @Column(length = 1000)
    private List<String> images;

    private String description;
    private Long id;


}
