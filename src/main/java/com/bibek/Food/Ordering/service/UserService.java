package com.bibek.Food.Ordering.service;

import com.bibek.Food.Ordering.entity.User;

public interface UserService {

    public User findUserByJwtToken(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;


}
