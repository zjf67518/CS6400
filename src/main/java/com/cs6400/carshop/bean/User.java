package com.cs6400.carshop.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String userName;
    private String password;
    private String first_name;
    private String last_name;
}
