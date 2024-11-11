package com.backend.shopapi.service.implementation;

import com.backend.shopapi.model.Admin;
import com.backend.shopapi.repository.UserRepository;
import com.backend.shopapi.service.UserService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Admin createUser(Admin user) {
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ADMIN");
        user.setCreatedDate(new Date());
        user.setUpdatedDate(new Date());
        return userRepository.save(user);

    }

    @Override
    public Admin findByUsername(String username) {
        return  userRepository.findByUsername(username);
    }

    @Override
    public boolean hasUserWithUsername(String username) {
        Admin users = userRepository.findByUsername(username);
        if (users == null) {
            return false;
        }
        return true;
    }

    @Override
    public Admin authenticateUser(String username, String password) {
        Admin admin = userRepository.findByUsername(username);
        if (admin != null && passwordEncoder.matches(password,  admin.getPassword())) {
             return admin;
        }
        return null;
    }


}
