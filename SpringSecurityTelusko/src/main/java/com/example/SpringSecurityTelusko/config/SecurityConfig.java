package com.example.SpringSecurityTelusko.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService; // Inject your custom DB-based service

    /**
     * This bean configures how authentication is handled.
     * DaoAuthenticationProvider connects Spring Security to your DB-backed user details.
     */
    @Bean
    public AuthenticationProvider authProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // Link it to your custom UserDetailsService
        provider.setUserDetailsService(userDetailsService);

        // Use plain text password encoder for testing (not for production!)
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());

        return provider;
    }

    /**
     * This bean configures HTTP security — how requests are authorized, what routes are protected, etc.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // Disable CSRF since we are using stateless API (no session or cookies)
                .csrf(csrf -> csrf.disable())

                // Route access control
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/auth/**").permitAll() // Allow public access to /auth routes
                        .anyRequest().authenticated()            // All other routes require login
                )

                // Use HTTP Basic Auth (sends username:password in the header)
                .httpBasic(Customizer.withDefaults())

                // Don't create or use sessions — each request must be authenticated
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return httpSecurity.build(); // Return the configured filter chain
    }
}
