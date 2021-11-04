package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Individual {
    private String driver_license;
    private String first_name;
    private String last_name;
    private Long customer_id;
}
