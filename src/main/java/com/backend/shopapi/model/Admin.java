package com.backend.shopapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

@Entity
@Table(name = "Admin")
public class Admin {
    @Id()
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    @NotBlank(message = "username required")
    private String username;

    @Column(name="role")
    private String role;

    @Column(name = "password")
    @NotBlank(message = "password required")
    private String password;

    @Column(name="createdDate")
    private Date createdDate;

    @Column(name="updatedDate")
    private Date updatedDate;

    public Admin() {
    }

    public Admin(String username,String role ,Date createdDate, String password, Date updatedDate) {
        this.role = role;
        this.username = username;
        this.createdDate = createdDate;
        this.password = password;
        this.updatedDate = updatedDate;
    }

    public @NotBlank(message = "username required") String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(@NotBlank(message = "Role required") String role) {
        this.role = role;
    }

    public void setUsername(@NotBlank(message = "username required") String username) {
        this.username = username;
    }

    public @NotBlank(message = "password required") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "password required") String password) {
        this.password = password;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}