package com.cs6400.carshop.service;

import com.cs6400.carshop.bean.*;
import com.cs6400.carshop.mapper.CustomerMapper;
import com.cs6400.carshop.mapper.RepairMapper;
import com.cs6400.carshop.mapper.ReportMapper;
import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.utils.Enum.VehicleType;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReportService {
    @Autowired
    private ReportMapper reportMapper;

    @Autowired
    private VehicleMapper vehicleMapper;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private CustomerService customerService;
    @Autowired
    private RepairMapper repairMapper;

    public Map<String, Integer> reportByColor(int input){
        Map<String, Integer> report = initialColorReport();
        if(input == 0){
            ArrayList<String> list = reportMapper.selectAllTransactionVINAllTime();
            for(String vin : list){
                List<String> colors = vehicleMapper.searchColorByVIN(vin);
                if(colors.size()>1){
                    report.put("Multiple", report.get("Multiple") + 1);
                }else{
                    log.info("{}", colors.get(0));
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


    public ArrayList<PartStatistic> reportByPart(){
        return reportMapper.selectPartStatistic();
    }

    public ArrayList<BelowCostStatistic> reportBelowCostSales(){
        List<Vehicle> saleReport = vehicleService.searchSoldVehicleByManager(new SearchInfoConverter());
        ArrayList<BelowCostStatistic> report = new ArrayList<>();
        for(Vehicle vehicle: saleReport){
            BelowCostStatistic belowCostStatistic = new BelowCostStatistic();
            vehicle = vehicleService.searchVehicleDetailByManager(vehicle.getVIN());
            belowCostStatistic.setPurchase_date(vehicle.getPurchase_date());
            belowCostStatistic.setInvoice_price(vehicle.getInvoice_price());
            belowCostStatistic.setSold_price(vehicle.getSold_price());
            belowCostStatistic.setFirst_name(vehicle.getFirst_name());
            belowCostStatistic.setLast_name(vehicle.getLast_name());
            belowCostStatistic.setBusiness_name(vehicle.getBusiness_name());
            belowCostStatistic.setSales_person_user_name(vehicle.getSales_person_user_name());
            belowCostStatistic.setRatio(belowCostStatistic.getSold_price().divide(belowCostStatistic.getInvoice_price(), 3));
            report.add(belowCostStatistic);
        }

        return report;

    }

    private Map<String, Double> initialInventoryByTypeReport(){
        Map<String, Double> report = new HashMap<>();
        report.put("Car", 0.0);
        report.put("Convertible", 0.0);
        report.put("Truck", 0.0);
        report.put("Van", 0.0);
        report.put("SUV", 0.0);
        return report;
    }

    public Map<String, Double> reportInventoryDays(){
        Map<String, Double> report = initialInventoryByTypeReport();
        report.put("Car", reportMapper.selectInventoryDayByVehicleType(1));
        report.put("Convertible", reportMapper.selectInventoryDayByVehicleType(2));
        report.put("Truck", reportMapper.selectInventoryDayByVehicleType(3));
        report.put("Van", reportMapper.selectInventoryDayByVehicleType(4));
        report.put("SUV", reportMapper.selectInventoryDayByVehicleType(5));
        return report;
    }

    public ArrayList<MonthReport> searchMonthlyReport(){
        ArrayList<MonthReport> report = reportMapper.selectMonthlyReport();
        return report;
    }

    public MonthSalesperson searchTopSalesperson(String date){
        return reportMapper.selectTopSalesperson(date);
    }

    public ArrayList<GrossCustomer> searchTop15Customer(){
        ArrayList<GrossCustomer> customers = reportMapper.selectTop15Customer();
        for(GrossCustomer customer : customers){
            Customer customer1 = customerService.searchCustomerById(customer.getCustomer_id());
            customer.setFirst_name(customer1.getFirst_name());
            customer.setLast_name(customer1.getLast_name());
            customer.setBusiness_name(customer1.getBusiness_name());
            customer.setNumber_repairs(reportMapper.selectRepair(customer.getCustomer_id()));
            customer.setNumber_sales(reportMapper.selectSale(customer.getCustomer_id()));
        }
        return customers;
    }

    public ArrayList<SaleDetail> searchSaleDetail(Long customer_id){
        return reportMapper.selectSaleDetail(customer_id);
    }

    public ArrayList<RepairInfo> searchRepairDetail(Long customer_id){

        ArrayList<RepairInfo> list = new ArrayList<>();
        list.addAll(reportMapper.selectRepairInfoByCustomerNotDone(customer_id));
        list.addAll(reportMapper.selectRepairInfoByCustomerDone(customer_id));
        for(RepairInfo info: list){

            info.setPart_cost(repairMapper.searchPartFee(info));
            if (info.getPart_cost() == null) {
                info.setPart_cost(BigDecimal.valueOf(0L));
            }
            info.setTotal_cost(info.getLabel_charge().add(info.getPart_cost()));
        }
        return list;
    }

}
