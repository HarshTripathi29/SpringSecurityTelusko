package com.example.SpringSecurityTelusko.repositories;

import com.example.SpringSecurityTelusko.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);

}
