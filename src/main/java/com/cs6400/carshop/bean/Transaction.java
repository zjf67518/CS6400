package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private int transaction_id;
    private String purchase_date;
    private int customer_id;
    private BigDecimal sold_price;
    private String sales_person_user_name;
    private String VIN;
}
