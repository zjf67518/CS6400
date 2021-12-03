package com.cs6400.carshop.controller;


import com.cs6400.carshop.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Slf4j
@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/getReport")
    public String findCustomer(){
        return "report";
    }

    @ResponseBody
    @GetMapping("/salesByColor")
    public Map<String,Integer> salesByColor(int input){
        Map<String,Integer> report = reportService.reportByColor(input);
        return report;
    }

    @ResponseBody
    @GetMapping("/salesByType")
    public Map<String,Integer> salesByType(int input){
        Map<String,Integer> report = reportService.reportByVehicleType(input);
        return report;
    }

    @ResponseBody
    @GetMapping("/salesByManufacturer")
    public Map<String,Integer> salesByManufacturer(int input){
        Map<String,Integer> report = reportService.reportByManufacturer(input);
        return report;
    }

    @ResponseBody
    @GetMapping("/grossCustomerIncome")
    public Map<String,Integer> grossCustomerIncome(int input){
        return null;
    }

    @ResponseBody
    @GetMapping("/repairByType")
    public Map<String,Integer> repairByType(int input){
        return null;
    }

    @ResponseBody
    @GetMapping("/repairByModel")
    public Map<String,Integer> repairByModel(int input){
        return null;
    }

    @ResponseBody
    @GetMapping("/belowCostSales")
    public Map<String,Integer> belowCostSales(int input){
        return null;
    }

    @ResponseBody
    @GetMapping("/averageTimeInInventory")
    public Map<String,Integer> averageTimeInInventory(int input){
        return null;
    }

    @ResponseBody
    @GetMapping("/partsStatistics")
    public Map<String,Integer> partsStatistics(int input){
        return null;
    }

    @ResponseBody
    @GetMapping("/monthlySales")
    public Map<String,Integer> monthlySales(int input){
        return null;
    }
}
