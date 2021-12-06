package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class GrossCustomer {
    private Long customer_id;
    private String first_name;
    private String last_name;
    private String business_name;


    private String first_date;
    private String recent_date;

    private Integer number_sales;
    private Integer number_repairs;

    private BigDecimal gross_income;
}
