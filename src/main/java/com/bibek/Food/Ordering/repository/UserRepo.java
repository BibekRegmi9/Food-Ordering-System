package com.bibek.Food.Ordering.repository;

import com.bibek.Food.Ordering.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {


    public User findByEmail(String username);
}
