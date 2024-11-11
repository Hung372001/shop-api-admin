package com.backend.shopapi.service;


import com.backend.shopapi.model.Admin;
import jakarta.validation.constraints.NotBlank;

public interface UserService {
    Admin createUser(Admin user);

    Admin findByUsername(@NotBlank String username);
    boolean hasUserWithUsername(@NotBlank(message = "username required") String username);
    Admin authenticateUser(String username, String password); // Thêm phương thức xác thực

}
