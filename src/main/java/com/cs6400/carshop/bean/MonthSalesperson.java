package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthSalesperson {
    private String first_name;
    private String last_name;
    private Integer number_vehicle_sold;
    private BigDecimal sale_income;

}
