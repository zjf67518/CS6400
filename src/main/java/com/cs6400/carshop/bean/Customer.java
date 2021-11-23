package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    private Long customer_id;
    private String phone_number;
    private String email;
    private String address;


    private Boolean isIndividual;
    //Individual
    private String driver_license;
    private String first_name;
    private String last_name;

    //Business
    private String tax_id;
    private String business_name;
    private String contact;
    private String title;


}
