package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Business {
    private String tax_id;
    private String business_name;
    private String contact;
    private String title;
    private Long customer_id;
}
