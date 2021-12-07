package com.cs6400.carshop.controller;


import com.cs6400.carshop.bean.*;
import com.cs6400.carshop.service.ReportService;
import com.cs6400.carshop.utils.Enum.AuthorFunction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    @GetMapping("/grossCustomerIncome")
    public String grossCustomerIncome(Model model){
        ArrayList<GrossCustomer> res = reportService.searchTop15Customer();
        model.addAttribute("GrossCustomer", res);
        return "customerIncome";
    }

    @GetMapping("/customerDetail/{customer_id}")
    public String selectedCustomer(@PathVariable("customer_id") Long customer_id, Model model){
        ArrayList<SaleDetail> saleDetails = reportService.searchSaleDetail(customer_id);
        ArrayList<RepairInfo> repairInfos = reportService.searchRepairDetail(customer_id);
        model.addAttribute("SaleDetail", saleDetails);
        model.addAttribute("RepairInfo", repairInfos);
        return "selectedCustomer";
    }

//    @ResponseBody
//    @GetMapping("/repairByType")
//    public Map<String,Integer> repairByType(int input){
//        return null;
//    }
//
//    @ResponseBody
//    @GetMapping("/repairByModel")
//    public Map<String,Integer> repairByModel(int input){
//        return null;
//    }

    @GetMapping("/belowCostSales")
    public String belowCostSales(Model model){
        ArrayList<BelowCostStatistic> res = reportService.reportBelowCostSales();
        model.addAttribute("BelowCostStatistic", res);
        return "belowCostSales";
    }

    @ResponseBody
    @GetMapping("/averageTimeInInventory")
    public Map<String, Double> averageTimeInInventory(){
        Map<String, Double> res = reportService.reportInventoryDays();
        return res;
    }

    @ResponseBody
    @GetMapping("/partsStatistics")
    public ArrayList<PartStatistic> partsStatistics(){
        ArrayList<PartStatistic> res = reportService.reportByPart();
        return res;
    }

    @GetMapping("/monthlySales")
    public String monthlySales(Model model){
        ArrayList<MonthReport> res = reportService.searchDrillDownReport();
        model.addAttribute("MonthReport", res);
        return "monthlySales";
    }
}
