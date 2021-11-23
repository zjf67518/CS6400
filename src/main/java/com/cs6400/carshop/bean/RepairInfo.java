package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RepairInfo {
    private Date start_date;
    private String VIN;
    private Date complete_date;
    private Long customer_id;
    private String service_writer_user_name;
    private BigDecimal label_charge;
    private BigDecimal part_cost;
    private BigDecimal total_cost;

    //顾客信息
    private String phone_number;
    private String email;
    private String address;

    //Individual
    private String first_name;
    private String last_name;

    //Business
    private String business_name;
    private String contact;
    private String title;

}
