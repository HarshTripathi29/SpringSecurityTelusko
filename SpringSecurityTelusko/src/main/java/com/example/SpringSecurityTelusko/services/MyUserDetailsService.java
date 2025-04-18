package com.example.SpringSecurityTelusko.services;

import com.example.SpringSecurityTelusko.entity.User;
import com.example.SpringSecurityTelusko.entity.UserPrincipal;
import com.example.SpringSecurityTelusko.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    /**
     * This method is automatically called by Spring Security during authentication.
     * It loads user data from the database using the provided username.
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Fetching user: " + username);
        User user = repo.findByUsername(username);
        if (user == null) {
            System.out.println("User not found in DB!");
            throw new UsernameNotFoundException("User not found");
        }
        System.out.println("User found: " + user.getUsername());
        return new UserPrincipal(user);
     }

}
