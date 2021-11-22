package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Repair {
    private Date start_date;
    private String VIN;
    private String odometer_reading;
    private Date complete_date;
    private Integer customer_id;
    private String service_writer_user_name;
    private String description;
    private BigDecimal label_charge;
}
