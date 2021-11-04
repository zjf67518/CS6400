package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Part {
    private String start_date;
    private String VIN;
    private String vendor_name;
    private String part_name;
    private BigDecimal unit_price;
    private Integer quantity;
}
