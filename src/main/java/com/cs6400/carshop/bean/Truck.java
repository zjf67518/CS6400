package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Truck {
    private String VIN;
    private int cargo_capacity;
    private String cover_type;
    private int rear_axles_number;
}
