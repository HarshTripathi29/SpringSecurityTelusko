package com.example.SpringSecurityTelusko.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
public class UserPrincipal implements UserDetails {

    private User user;

    // Constructor accepts the User entity
    public UserPrincipal(User user) {
        this.user = user;
    }

    /**
     * Defines user roles or authorities.
     * You can return multiple roles as a list, here it's hardcoded to "USER".
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    // Returns the password from your User entity
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    // Returns the username from your User entity
    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // These fields are related to account status — you can customize them later
    @Override
    public boolean isAccountNonExpired() {
        return true; // account is not expired
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // account is not locked
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // credentials are valid
    }

    @Override
    public boolean isEnabled() {
        return true; // account is enabled
    }
}
