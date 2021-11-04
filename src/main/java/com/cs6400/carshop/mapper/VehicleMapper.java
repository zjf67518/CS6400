package com.cs6400.carshop.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface VehicleMapper {
    int selectCountVehicleForSale();
}
