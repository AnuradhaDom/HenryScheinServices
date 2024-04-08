package com.henryschein.userservice.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "henryuser")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Name required")
    @Size(min = 2 ,max = 100 , message = "Name must be between 2 to 100 characters")
    private String name;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid Email format")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
    //@Enumerated(EnumType.STRING)
    private String role;

    private String userName;
    private boolean active;
    private String roles;


}