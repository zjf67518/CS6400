package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private Integer transaction_id;
    private String purchase_date;
    private Long customer_id;
    private BigDecimal sold_price;
    private String sales_person_user_name;
    private String VIN;


    private Date sale_date;
}
