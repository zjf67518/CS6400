package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Part {
    private Date start_date;
    private String VIN;
    private String vendor_name;
    private String part_name;
    private BigDecimal unit_price;
    private Integer quantity;
}
