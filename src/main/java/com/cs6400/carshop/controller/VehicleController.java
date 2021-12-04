package com.cs6400.carshop.controller;

import com.cs6400.carshop.bean.*;
import com.cs6400.carshop.service.RepairService;
import com.cs6400.carshop.service.TransactionService;
import com.cs6400.carshop.service.VehicleService;
import com.cs6400.carshop.utils.Enum.AuthorFunction;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private RepairService repairService;

    @PostMapping("/SaveSearchInfo")
    public String saveSearchInfo(SearchInfoConverter searchInfo, Model model){
        if (searchInfo.getVehicle_type() != null && searchInfo.getVehicle_type() == 0) {
            searchInfo.setVehicle_type(null);
        }
        if (searchInfo.getKeyword() != null && searchInfo.getKeyword().equals("null")) {
            searchInfo.setKeyword(null);
        }
        if (searchInfo.getOrderby() != null && searchInfo.getOrderby().equals("null")) {
            searchInfo.setOrderby(null);
        }
        if (searchInfo.getColor() != null && searchInfo.getColor().equals("null")) {
            searchInfo.setColor(null);
        }
        if (searchInfo.getManufacturer_name() != null && searchInfo.getManufacturer_name().equals("null")) {
            searchInfo.setManufacturer_name(null);
        }
        if (searchInfo.getVIN() != null && searchInfo.getVIN().equals("null")) {
            searchInfo.setVIN(null);
        }

        List<Vehicle> list = null;
        Map<String, Object> map = new HashMap<>();

        if (searchInfo.getFilterBy() == null || searchInfo.getFilterBy() == 0) {
            list = vehicleService.searchVehicleUsedByCustomer(searchInfo);
        } else if (searchInfo.getFilterBy() == 1) {
            list = vehicleService.searchSoldVehicleByManager(searchInfo);
        } else {
            list = vehicleService.searchAllVehicleByManager(searchInfo);
        }
        log.info("searchInfo:{}", searchInfo.getVIN());

        if (list.isEmpty()) {
            model.addAttribute("msg", "Sorry, it looks like we don’t have that in stock!");
            return "wrongInfo";
        }
        model.addAttribute("vehicles", list);
        map.put("res", list);
        return "dynamic_table";
    }

    @GetMapping("/VehicleDetail/{VIN}")
    public String vehicleDetail(@PathVariable("VIN") String VIN, Model model, HttpSession session){
        RegularUser user = (RegularUser) session.getAttribute("loginUser");
        log.info("{}", user);

        Vehicle vehicle = null;
        if (user == null) {
            vehicle = vehicleService.searchVehicleDetail(VIN);
        } else {
            vehicle = (user.getAuthority() & AuthorFunction.VehicleDetail.getCode()) != AuthorFunction.VehicleDetail.getCode()
                    ? vehicleService.searchVehicleDetail(VIN) : vehicleService.searchVehicleDetailByManager(VIN);
        }

        log.info("{}", vehicle);
        model.addAttribute("vehicle", vehicle);
        return "vehicle_detail";
    }

    @GetMapping(value = {"/addVehicle"})
    public String addVehicle(){
        return "addVehicle";
    }

    @PostMapping("/SaveVehicleInfo")
    public String saveVehicleInfo(Vehicle vehicle, HttpSession session){
        RegularUser user = (RegularUser) session.getAttribute("loginUser");
        vehicle.setInventory_clerk_user_name(user.getUsername());
        vehicleService.addVehicle(vehicle);
        log.info(vehicle.toString());
        return "redirect:/VehicleDetail/" + vehicle.getVIN();
    }

    @GetMapping("/sellVehicle")
    public String sellVehicle(){
        return "transaction";
    }

    @PostMapping("/sellVehicle")
    public String sellVehicle(Transaction transaction, HttpSession session, Model model){
        RegularUser user = (RegularUser) session.getAttribute("loginUser");
        BigDecimal price = vehicleService.searchInvoicePrice(transaction.getVIN());
        BigDecimal mult = new BigDecimal("0.95");

        if (transaction.getSold_price().compareTo(price.multiply(mult)) < 0) {
            model.addAttribute("msg", "Sorry, the price does not meet the requirements.");
            return "wrongInfo";
        }

        transaction.setSales_person_user_name(user.getUsername());
        transactionService.insertTransaction(transaction);
        log.info(transaction.toString());

        return "redirect:/search";
    }

    @GetMapping("/repairVehicle")
    public String repairVehicle(){
        return "repairVehicle";
    }

    @PostMapping("/repairVehicle")
    public String repairVehicle(String VIN, boolean flag, Model model){
        Vehicle Vehicle = vehicleService.whetherVehicleCanRepair(VIN);
        if (Vehicle == null) {
            model.addAttribute("msg", "Sorry! we can't repair it.");
            return "wrongInfo";
        }

        Repair repair = repairService.availableForRepair(VIN);
        log.info("{}", repair);
        if (repair == null && !flag) {
            return "addRepair";
        } else if (repair == null) {
            model.addAttribute("msg", "Sorry! we can't add this part.");
            return "wrongInfo";
        } else if (!flag) {
            return "modifyRepair";
        } else {
            return "addPart";
        }
    }

    @PostMapping("/insertRepair")
    public String insertRepair(Repair repair, HttpSession session){
        RegularUser user = (RegularUser) session.getAttribute("loginUser");
        repair.setService_writer_user_name(user.getUsername());
        log.info(repair.toString());
        repairService.insertRepair(repair);

        return "redirect:/search";
    }

    @PostMapping("/modifyRepair")
    public String modifyRepair(Repair repair, boolean complete){
        log.info(repair.toString());
        repairService.updateRepair(repair);
        if (complete) {
            repairService.completeRepair(repair);
        }
        return "redirect:/search";
    }

    @PostMapping("/insertPart")
    public String insertPart(Part part){
        Repair repair = repairService.availableForRepair(part.getVIN());
        part.setStart_date(repair.getStart_date());
        repairService.insertOrUpdatePart(part);
        return "redirect:/search";
    }
}
