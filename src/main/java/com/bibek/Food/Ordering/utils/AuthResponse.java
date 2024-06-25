package com.bibek.Food.Ordering.utils;

import com.bibek.Food.Ordering.enums.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private USER_ROLE role;
}
