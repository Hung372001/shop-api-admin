package com.backend.shopapi.controller;

import com.backend.shopapi.dto.ApiResponse;
import com.backend.shopapi.model.Admin;
import com.backend.shopapi.security.JwtUtil;
import com.backend.shopapi.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signin")
    public ResponseEntity<?>createdUser(@Valid @RequestBody Admin user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Map<String,String> errors = new HashMap<>();
            for ( FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return  ResponseEntity.badRequest().body(new ApiResponse("Validation failed", 400, errors));
        }


        if (userService.hasUserWithUsername(user.getUsername())){
//            return new ResponseEntity<>("User already exist", HttpStatus.NOT_ACCEPTABLE);
            return ResponseEntity.status(409).body(new ApiResponse("User already exist", 409));


        }
        Admin newUser = userService.createUser(user);
        if (newUser == null) {
            return ResponseEntity.badRequest().body(new ApiResponse("Users creation failed", 400));
        }
        return ResponseEntity.status(201).body(new ApiResponse("Users success", 201));

    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Admin admin, BindingResult bindingResult,HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            Map<String,String> errors = new HashMap<>();
            for ( FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return  ResponseEntity.badRequest().body(new ApiResponse("Validation failed", 400, errors));
        }
        Admin user = userService.authenticateUser(admin.getUsername(), admin.getPassword());
        if (user == null) {
            return ResponseEntity.status(401).body(new ApiResponse("Invalid username or password", 401));
        }

        String token = jwtUtil.generateToken(admin.getUsername(),user.getRole());
        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .sameSite("Lax")
                .path("/")
                .maxAge(24 * 60 * 60) // 1 ngày
                .build();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString()).body(new ApiResponse(token, 200));
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            System.out.printf("token"+ jwtToken);
            String username = jwtUtil.extractUsername(jwtToken);
            Admin user = userService.findByUsername(username);
             if (user != null) {
                 return ResponseEntity.ok(user);
             }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

}
