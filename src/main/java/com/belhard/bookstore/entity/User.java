package com.belhard.bookstore.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String password;
}
