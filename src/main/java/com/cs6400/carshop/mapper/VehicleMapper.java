package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface VehicleMapper {
    int selectCountVehicleForSale();
    List<Vehicle> searchVehicleByAttribute(SearchInfoConverter searchInfoConverter);
    List<String> searchColorByVIN(String VIN);
    String searchManufacturerName(Integer Manufacturer_id);
}
