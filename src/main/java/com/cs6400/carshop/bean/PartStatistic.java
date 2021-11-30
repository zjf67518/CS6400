package com.cs6400.carshop.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PartStatistic {
    private String vendor_name;
    private Long quantity;
    private BigDecimal total_part_fee;
}
