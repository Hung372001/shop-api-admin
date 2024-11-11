package com.backend.shopapi.repository;


import com.backend.shopapi.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Admin, Integer> {
    Admin findByUsername(String username);

}
