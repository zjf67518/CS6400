package com.cs6400.carshop.service;

import com.cs6400.carshop.mapper.VehicleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;

    public int selectVehicleForSale(){
        return vehicleMapper.selectCountVehicleForSale();
    }
}
