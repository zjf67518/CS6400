package com.cs6400.carshop.utils.converter;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class SearchInfoConverter {
    private String VIN;
    private Integer vehicle_type;
    private Integer model_year;
    private String manufacturer_name;
    private String color;
    private BigDecimal list_price;
    private Boolean moreThanPrice;
    private String keyword;
    private String order;
    private Boolean descOrder;
}
