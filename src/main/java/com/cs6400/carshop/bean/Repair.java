package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Repair {
    private String start_date;
    private String VIN;
    private String odometer_reading;
    private String complete_date;
    private Integer customer_id;
    private String service_writer_user_name;
    private String description;
    private BigDecimal labor_charge;
}
