package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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

    @Override
    public String toString() {
        return "Vehicle{" +
                "VIN='" + VIN + '\'' +
                ", manufacturer_id=" + manufacturer_id +
                ", invoice_price=" + invoice_price +
                ", model_name='" + model_name + '\'' +
                ", model_year=" + model_year +
                ", description='" + description + '\'' +
                ", added_date=" + added_date +
                ", inventory_clerk_user_name='" + inventory_clerk_user_name + '\'' +
                ", vehicle_type=" + vehicle_type +
                ", manufacturer_name='" + manufacturer_name + '\'' +
                ", color='" + color + '\'' +
                ", door_number=" + door_number +
                ", seats_number=" + seats_number +
                ", roof_type='" + roof_type + '\'' +
                ", cupholder_number=" + cupholder_number +
                ", drivetrain_type='" + drivetrain_type + '\'' +
                ", has_back_door=" + has_back_door +
                ", cargo_capacity=" + cargo_capacity +
                ", cover_type='" + cover_type + '\'' +
                ", rear_axles_number=" + rear_axles_number +
                '}';
    }
}
