package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.Color;
import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.mapper.ReportMapper;
import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.utils.Enum.VehicleType;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private VehicleService vehicleService;


    public Map<String, Integer> reportByColor(int input){
        Map<String, Integer> report = initialColorReport();
        if(input == 0){
            ArrayList<String> list = reportMapper.selectAllTransactionVINAllTime();
            for(String vin : list){
                List<String> colors = vehicleMapper.searchColorByVIN(vin);
                if(colors.size()>1){
                    report.put("Multiple", report.get("Multiple") + 1);
                }else{
                    report.put(colors.get(0), report.get(colors.get(0)) + 1);
                }
            }
        }else if(input == 1){
            ArrayList<String> list = reportMapper.selectAllTransactionVINByMonth();
            for(String vin : list){
                List<String> colors = vehicleMapper.searchColorByVIN(vin);
                if(colors.size()>1){
                    report.put("Multiple", report.get("Multiple") + 1);
                }else{
                    report.put(colors.get(0), report.get(colors.get(0)) + 1);
                }
            }
        }else{
            ArrayList<String> list = reportMapper.selectAllTransactionVINByYear();
            for(String vin : list){
                List<String> colors = vehicleMapper.searchColorByVIN(vin);
                if(colors.size()>1){
                    report.put("Multiple", report.get("Multiple") + 1);
                }else{
                    report.put(colors.get(0), report.get(colors.get(0)) + 1);
                }
            }
        }
        return report;
    }

    public Map<String, Integer> reportByVehicleType(int input){
        Map<String, Integer> report = initialVehicleTypeReport();
        if(input == 0){
            ArrayList<String> list = reportMapper.selectAllTransactionVINAllTime();
            for(String vin : list){
                Vehicle vehicle = vehicleMapper.searchVehicleDetailByVIN(vin);
                report.put(VehicleType.TransferTypeToName(vehicle.getVehicle_type()), report.get(VehicleType.TransferTypeToName(vehicle.getVehicle_type())) + 1);
            }
        }else if(input == 1){
            ArrayList<String> list = reportMapper.selectAllTransactionVINByMonth();
            for(String vin : list){
                Vehicle vehicle = vehicleMapper.searchVehicleDetailByVIN(vin);
                report.put(VehicleType.TransferTypeToName(vehicle.getVehicle_type()), report.get(VehicleType.TransferTypeToName(vehicle.getVehicle_type())) + 1);
            }
        }else{
            ArrayList<String> list = reportMapper.selectAllTransactionVINByYear();
            for(String vin : list){
                Vehicle vehicle = vehicleMapper.searchVehicleDetailByVIN(vin);
                report.put(VehicleType.TransferTypeToName(vehicle.getVehicle_type()), report.get(VehicleType.TransferTypeToName(vehicle.getVehicle_type())) + 1);
            }
        }
        return report;
    }
    public Map<String, Integer> reportByManufacturer(int input){
        Map<String, Integer> report = new HashMap<>();
        if(input == 0){
            ArrayList<String> list = reportMapper.selectAllTransactionVINAllTime();
            for(String vin : list){
                Vehicle vehicle = vehicleService.searchVehicleDetail(vin);
                if(report.containsKey(vehicle.getManufacturer_name())){
                    report.put(vehicle.getManufacturer_name(), report.get(vehicle.getManufacturer_name()) + 1);
                }else{
                    report.put(vehicle.getManufacturer_name(), 1);
                }
            }
        }else if(input == 1){
            ArrayList<String> list = reportMapper.selectAllTransactionVINByMonth();
            for(String vin : list){
                Vehicle vehicle = vehicleService.searchVehicleDetail(vin);
                if(report.containsKey(vehicle.getManufacturer_name())){
                    report.put(vehicle.getManufacturer_name(),report.get(vehicle.getManufacturer_name()) + 1);
                }else{
                    report.put(vehicle.getManufacturer_name(), 1);
                }
            }
        }else{
            ArrayList<String> list = reportMapper.selectAllTransactionVINByYear();
            for(String vin : list){
                Vehicle vehicle = vehicleService.searchVehicleDetail(vin);
                if(report.containsKey(vehicle.getManufacturer_name())){
                    report.put(vehicle.getManufacturer_name(),report.get(vehicle.getManufacturer_name()) + 1);
                }else{
                    report.put(vehicle.getManufacturer_name(), 1);
                }
            }
        }
        return report;
    }

    private Map<String, Integer> initialColorReport(){
        Map<String, Integer> report = new HashMap<>();
        report.put("Aluminum", 0);
        report.put("Beige", 0);
        report.put("Black", 0);
        report.put("Blue", 0);
        report.put("Brown", 0);
        report.put("Bronze", 0);
        report.put("Claret", 0);
        report.put("Copper", 0);
        report.put("Cream", 0);
        report.put("Gold", 0);
        report.put("Gray", 0);
        report.put("Green", 0);
        report.put("Maroon", 0);
        report.put("Metallic", 0);
        report.put("Orange", 0);
        report.put("Pink", 0);
        report.put("Purple", 0);
        report.put("Red", 0);
        report.put("Rose", 0);
        report.put("Rust", 0);
        report.put("Silver", 0);
        report.put("Tan", 0);
        report.put("Turquoise", 0);
        report.put("White", 0);
        report.put("Yellow", 0);
        report.put("Multiple", 0);
        return report;
    }

    private Map<String, Integer> initialVehicleTypeReport(){
        Map<String, Integer> report = new HashMap<>();
        report.put("Car", 0);
        report.put("Convertible", 0);
        report.put("Truck", 0);
        report.put("Van", 0);
        report.put("SUV", 0);
        return report;
    }
}
