package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MonthReport {
    private String time;
    private BigDecimal net_income;
    private BigDecimal sale_income;
    private Integer number_vehicle_sold;
    private String ratio;
}
