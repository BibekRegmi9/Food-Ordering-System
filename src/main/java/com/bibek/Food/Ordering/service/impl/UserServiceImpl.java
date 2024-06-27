package com.bibek.Food.Ordering.service.impl;

import com.bibek.Food.Ordering.config.JwtProvider;
import com.bibek.Food.Ordering.entity.User;
import com.bibek.Food.Ordering.repository.UserRepo;
import com.bibek.Food.Ordering.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User findUserByJwtToken(String jwt) throws Exception {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return findUserByEmail(email);
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        User user = userRepo.findByEmail(email);

        if(user == null){
            throw  new Exception("User not found");
        }
        return user;
    }
}
