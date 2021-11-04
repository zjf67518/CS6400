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
    private long manufacturer_id;
    private BigDecimal invoice_price;
    private String model_name;
    private int model_year;
    private String description;
    private String added_date;
    private String inventory_clerk_user_name;
    private String vehicle_type;
}
