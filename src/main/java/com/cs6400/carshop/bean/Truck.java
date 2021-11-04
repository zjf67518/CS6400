package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Truck {
    private String VIN;
    private Integer cargo_capacity;
    private String cover_type;
    private Integer rear_axles_number;
}
