package com.cs6400.carshop.controller;

import com.cs6400.carshop.bean.RegularUser;
import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.service.VehicleService;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

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

        Map<String, Object> map = new HashMap<>();
        List<Vehicle> list = vehicleService.searchVehicleUsedByCustomer(searchInfo);
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
    public String vehicleDetail(@PathVariable("VIN") String VIN, Model model){
        Vehicle vehicle = vehicleService.searchVehicleDetail(VIN);
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
        vehicle.setInventory_clerk_user_name(user.getUserName());
        vehicleService.addVehicle(vehicle);
        log.info(vehicle.toString());
        return "redirect:/VehicleDetail/" + vehicle.getVIN();
    }

}
