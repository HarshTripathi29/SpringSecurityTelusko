package com.example.SpringSecurityTelusko.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{



    // finalizes the configuration and creates the actual SecurityFilterChain
    httpSecurity
            // disabling csrf token, take an object of customizer and call disable()
            .csrf(customizer->customizer.disable())

            // authorize the request on these routes
            .authorizeHttpRequests(request->request.anyRequest().authenticated())

            //  This enables basic HTTP authentication (you send Authorization: Basic base64(username:password) header).
            .httpBasic(Customizer.withDefaults())

            //  No server-side sessions are created or stored.Every request must carry its authentication
            //  (i.e., token).Perfect for JWT-based microservices.
            .sessionManagement(session->session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

    // returns an object of security filter chain
    return httpSecurity.build();

}
}
