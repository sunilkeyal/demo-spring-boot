package com.example.model;

import lombok.Data;

@Data
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String status;
}

