package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Vehicle {
    private String VIN;
    private Integer manufacturer_id;
    private BigDecimal invoice_price;
    private String model_name;
    private Integer model_year;
    private String description;
    private Date added_date;
    private String inventory_clerk_user_name;
    private Integer vehicle_type;
    private String vehicle_name;
    private String manufacturer_name;
    private String color;
    //Car
    private Integer door_number;
    //Convertible
    private Integer seats_number;
    private String roof_type;
    //SUV
    private Integer cupholder_number;
    private String drivetrain_type;
    //Van
    private Integer has_back_door;
    //Truck
    private Integer cargo_capacity;
    private String cover_type;
    private Integer rear_axles_number;
    //卖单信息
    private Integer transaction_id;
    private String purchase_date;
    private Long customer_id;
    private BigDecimal sold_price;
    private String sales_person_user_name;
    private String sales_person_first_name;
    private String sales_person_last_name;
    //买家信息
    private String phone_number;
    private String email;
    private String address;

    //Individual
    private String first_name;
    private String last_name;

    //Business
    private String business_name;
    private String contact;
    private String title;



    //维修信息
    private ArrayList<RepairInfo> repairInfos;

}
