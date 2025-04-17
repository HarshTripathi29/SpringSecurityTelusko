package com.example.SpringSecurityTelusko.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("hello")
    public String hello(HttpServletRequest request){
        //  It is used to retrieve request-related information when handling HTTP requests in a Spring Boot project.
        return "Hello harsh"+request.getSession().getId();
    }


    // after login we have the sesison id
    // some malicious program may try to steel the session id and login to our account
    // this is called csrf
    // to avoid this issue we should also provide token along with the request.
    // get would be working even without csrf but post would be blocked


    @GetMapping("csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");
    }

    // get the token from spring security
    // send the token in the post request
    // or other way would be not to allow any one from the same site to access the session id

}
