package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    private String VIN;
    private Long manufacturer_id;
    private BigDecimal invoice_price;
    private String model_name;
    private Integer model_year;
    private String description;
    private String added_date;
    private String inventory_clerk_user_name;
    private Integer vehicle_type;
}
