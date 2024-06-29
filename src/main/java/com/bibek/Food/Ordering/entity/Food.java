package com.bibek.Food.Ordering.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private Long price;

    @ManyToOne
    private Category foodCategory;

    @Column(length = 100)
    @ElementCollection
    private List<String> images;

    private boolean available;

    @ManyToOne
    private Restaurant restaurant;

    private boolean isVeg;

    private boolean isSeasonal;

    @ManyToMany
    private List<IngredientsItem> ingredients = new ArrayList<>();


    private Date creationDate;
}
