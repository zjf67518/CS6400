package com.cs6400.carshop.mapper;

import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface VehicleMapper {
    int selectCountVehicleForSale();
    List<Vehicle> searchVehicleByAttribute(SearchInfoConverter searchInfoConverter);
    List<String> searchColorByVIN(String VIN);
    String searchManufacturerName(Integer Manufacturer_id);


    Vehicle searchVehicleDetailByVIN(String VIN);
    Vehicle searchCarDetailByVIN(String VIN);
    Vehicle searchConvertibleDetailByVIN(String VIN);
    Vehicle searchSuvDetailByVIN(String VIN);
    Vehicle searchVanDetailByVIN(String VIN);
    Vehicle searchTruckDetailByVIN(String VIN);

    int insertVehicle(Vehicle vehicle);
    int insertCar(Vehicle vehicle);
    int insertConvertible(Vehicle vehicle);
    int insertSUV(Vehicle vehicle);
    int insertVan(Vehicle vehicle);
    int insertTruck(Vehicle vehicle);

    int insertColor(String VIN, String color);

    BigDecimal searchInvoicePrice(String VIN);

    Vehicle searchVehicleForRepair(String VIN);

    List<Vehicle> searchAllVehicleByAttribute(SearchInfoConverter searchInfoConverter);
    List<Vehicle> searchSoldVehicleByAttribute(SearchInfoConverter searchInfoConverter);

    //插入数据用
    Integer selectManuIdByManuName(String manufacturer_name);
    void insertVehicleTest(Vehicle vehicle);

}
