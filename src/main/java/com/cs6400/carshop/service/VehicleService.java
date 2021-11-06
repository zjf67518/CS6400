package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;

    public int selectVehicleForSale(){
        return vehicleMapper.selectCountVehicleForSale();
    }

    public List<Vehicle>searchVehicleUsedByCustomer(SearchInfoConverter searchInfoConverter){
        List<Vehicle> list = vehicleMapper.searchVehicleByAttribute(searchInfoConverter);
        for(Vehicle vehicle : list){
            List<String> colors = vehicleMapper.searchColorByVIN(vehicle.getVIN());
            StringBuilder sb =new StringBuilder();
            for(String color : colors){
                sb.append(color).append(' ');
            }
            vehicle.setColor(sb.toString());
            vehicle.setManufacturer_name(vehicleMapper.searchManufacturerName(vehicle.getManufacturer_id()));
        }
        return list;
    }
}
