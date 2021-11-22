package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.utils.Enum.VehicleType;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class VehicleService {
    @Autowired
    private VehicleMapper vehicleMapper;

    public int selectVehicleForSale(){
        return vehicleMapper.selectCountVehicleForSale();
    }

    public List<Vehicle> searchVehicleUsedByCustomer(SearchInfoConverter searchInfoConverter){
        if (searchInfoConverter.getPrice() != null){
            searchInfoConverter.setPrice(searchInfoConverter.getPrice().divide(BigDecimal.valueOf(1.25)));
        }
        List<Vehicle> list = vehicleMapper.searchVehicleByAttribute(searchInfoConverter);
        for(Vehicle vehicle:list){
            List<String> colors = vehicleMapper.searchColorByVIN(vehicle.getVIN());
            StringBuilder sb =new StringBuilder();
            for(String color:colors){
                sb.append(color).append(' ');
            }
            vehicle.setColor(sb.toString());
            vehicle.setManufacturer_name(vehicleMapper.searchManufacturerName(vehicle.getManufacturer_id()));
            vehicle.setInvoice_price(vehicle.getInvoice_price().multiply(BigDecimal.valueOf(1.25)));
            vehicle.setVehicle_name(VehicleType.TransferTypeToName(vehicle.getVehicle_type()));
        }
        return list;
    }

    public Vehicle searchVehicleDetail(String VIN) {
        if (VIN == null) {
            return null;
        }
        Vehicle vehicle = vehicleMapper.searchVehicleDetailByVIN(VIN);
        List<String> colors = vehicleMapper.searchColorByVIN(vehicle.getVIN());
        StringBuilder sb =new StringBuilder();
        for(String color:colors){
            sb.append(color).append(' ');
        }
        vehicle.setColor(sb.toString());
        vehicle.setInvoice_price(vehicle.getInvoice_price().multiply(BigDecimal.valueOf(1.25)));
        vehicle.setManufacturer_name(vehicleMapper.searchManufacturerName(vehicle.getManufacturer_id()));
        vehicle.setVehicle_name(VehicleType.TransferTypeToName(vehicle.getVehicle_type()));
        switch (vehicle.getVehicle_type()){
            case 1:
                Vehicle car = vehicleMapper.searchCarDetailByVIN(vehicle.getVIN());
                vehicle.setDoor_number(car.getDoor_number());
                break;
            case 2:
                Vehicle convertible = vehicleMapper.searchConvertibleDetailByVIN(vehicle.getVIN());
                vehicle.setSeats_number(convertible.getSeats_number());
                vehicle.setRoof_type(convertible.getRoof_type());
                break;
            case 3:
                Vehicle truck = vehicleMapper.searchTruckDetailByVIN(vehicle.getVIN());
                vehicle.setCargo_capacity(truck.getCargo_capacity());
                vehicle.setCover_type(truck.getCover_type());
                vehicle.setRear_axles_number(truck.getRear_axles_number());
                break;
            case 4:
                Vehicle Van = vehicleMapper.searchVanDetailByVIN(vehicle.getVIN());
                vehicle.setHas_back_door(Van.getHas_back_door());
                break;
            case 5:
                Vehicle SUV = vehicleMapper.searchSuvDetailByVIN(vehicle.getVIN());
                vehicle.setCupholder_number(SUV.getCupholder_number());
                vehicle.setDrivetrain_type(SUV.getDrivetrain_type());
                break;
        }
        return vehicle;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public void addVehicle(Vehicle vehicle){
        vehicleMapper.insertVehicle(vehicle);
        switch(vehicle.getVehicle_type()){
            case 1:
                vehicleMapper.insertCar(vehicle);
                break;
            case 2:
                vehicleMapper.insertConvertible(vehicle);
                break;
            case 3:
                vehicleMapper.insertTruck(vehicle);
                break;
            case 4:
                vehicleMapper.insertVan(vehicle);
                break;
            case 5:
                vehicleMapper.insertSUV(vehicle);
                break;
        }
        String Vin = vehicle.getVIN();

        String[] colors = vehicle.getColor().split(",");
        for (String color : colors) {
            vehicleMapper.insertColor(Vin, color);
        }
    }
}
