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
        // Look up the user in the database
        User user = repo.findByUsername(username);

        // If user not found, throw exception
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        // Wrap the User entity in a Spring Security UserDetails implementation
        return new UserPrincipal(user);
    }
}
