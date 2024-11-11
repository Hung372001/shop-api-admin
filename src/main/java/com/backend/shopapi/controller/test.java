package com.backend.shopapi.controller;

import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/ping")
public class test {
    @GetMapping("/")
    public String ping() {
        return "pong";
    }

    @PostMapping("/")
    public String postping() {
        return "pong";
    }

    @GetMapping("/hello")
    public String hello(@CookieValue(value = "jwt", required = false) String myCookieValue) {
        // Sử dụng myCookieValue ở đây
        if (myCookieValue == null) {
            return "Hello, no JSESSIONID cookie present";
        }
        return "Hello, " + myCookieValue;
    }
}
