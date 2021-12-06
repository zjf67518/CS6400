package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SaleDetail {



    private Date purchase_date;
    private BigDecimal sold_price;
    private String sales_person_user_name;
    private String VIN;
    private String model_name;
    private Integer model_year;
    private String manufacturer_name;
}
