package com.cs6400.carshop.controller;

import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.service.VehicleService;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @ResponseBody
    @PostMapping("/SaveSearchInfo")
    public Map saveSearchInfo(SearchInfoConverter searchInfo){
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
        log.info("searchInfo:{}", searchInfo.toString());
        log.info("searchInfo:{}", searchInfo.equals(new SearchInfoConverter()));
        List<Vehicle> list = vehicleService.searchVehicleUsedByCustomer(searchInfo);
        map.put("res", list);
        return map;
    }
}
