package com.cs6400.carshop;

import com.cs6400.carshop.bean.Vehicle;
import com.cs6400.carshop.mapper.VehicleMapper;
import com.cs6400.carshop.service.VehicleService;
import com.cs6400.carshop.utils.converter.SearchInfoConverter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Slf4j
@SpringBootTest
public class VehicleTest {
    @Autowired
    private VehicleService vehicleService;
    @Test
    public void testForSale(){
        System.out.println(vehicleService.selectVehicleForSale());
    }

    @Test
    public void testForSearch(){
        SearchInfoConverter searchInfoConverter = new SearchInfoConverter();
        log.info("{}", searchInfoConverter);
        List<Vehicle> list = vehicleService.searchVehicleUsedByCustomer(searchInfoConverter);
        for(Vehicle vehicle : list){
            System.out.println(vehicle);
        }
    }
}
