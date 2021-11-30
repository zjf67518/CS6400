package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BelowCostStatistic {
    private String purchase_date;
    private BigDecimal invoice_price;
    private BigDecimal sold_price;
    private BigDecimal ratio;
    //
    private String first_name;
    private String last_name;

    //
    private String business_name;
    //
    private String sales_person_user_name;

}
